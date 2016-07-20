/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

#include <stdint.h>
#include <stdio.h>

#include <sync/spinlock.h>

#include "mcp2515.h"
#include <queue.h>

#include "can.h"

/* Debug information level
 *	0: no output
 *	1: unexpected failures
 *	2: all debug output
 */
#define DEBUG 2

#define TXIF_MASK (CANINTF_TX0IF | CANINTF_TX1IF | CANINTF_TX2IF)
#define RXIF_MASK (CANINTF_RX0IF | CANINTF_RX1IF)
#define TXIF_SHF  2

#define TXB_NUM  3 //Number of TX buffers on the controller.

static volatile uint8_t xmit_stopped = 1;
static sync_spinlock_t txb_lock = 0;

/**
 * Message error
 *
 * This error is intended to be used for detecting baud rate in Listen-only
 * mode.
 */
static void report_message_error(void)
{
#if DEBUG > 0
	printf("%s\n", __func__);
#endif
	enum op_mode mode;

	mode = get_mode();
	if (mode == REQOP_LISTEN) {
		set_mode(REQOP_CONFIG);
		/* Auto baud rate detection. */
		set_mode(REQOP_LISTEN);
	}

	/* Clear interrupt flags */
	mcp2515_bit_modify(CANINTF, CANINTE_MERRE, 0);
}

/**
 * Report errors
 *
 * MCP2515 does automatic error recovery when the it enters bus-off mode.
 * There is nothing we can do other than report to the user.
 */
static void report_error(void)
{
	uint8_t flags;

	/* Read the error flag */
	flags = mcp2515_read_reg(EFLG);

	if (flags & (EFLG_RX0OVR | EFLG_RX1OVR)) {
#if DEBUG > 1
		printf("CAN: Receive buffer overflow!\n");
#endif
		/* Overflow flags need to be cleared manually. */
		mcp2515_bit_modify(EFLG, flags & (EFLG_RX0OVR | EFLG_RX1OVR), 0);
	}

#if DEBUG > 0
	if (flags & EFLG_TXBO) {
		printf("CAN: Enter Bus-off mode, too many errors.\n");
	}

	if (flags & EFLG_RXEP) {
		printf("CAN: Enter receive error-passive mode.\n");
	}

	if (flags & EFLG_TXEP) {
		printf("CAN: Enter transmit error-passive mode.\n");
	}
#endif

#if DEBUG > 1
	if (flags & EFLG_TXWAR) {
		printf("CAN: Transmit error warning.\n");
	}

	if (flags & EFLG_RXWAR) {
		printf("CAN: Receive error warning.\n");
	}
#endif

	/* Clear interrupt flags */
	mcp2515_bit_modify(CANINTF, CANINTF_ERRIF, 0);
}

/**
 * Put message queue into TXB.
 *
 * @tx: TXB bits(Bit 0 -- TXB0, Bit 1 -- TXB1, Bit 2 -- TXB2)
 */
static void transmit_message(uint8_t tx)
{
	struct can_frame frame;
	uint8_t rts = 0;

	/* Clear interrupt flags */
	mcp2515_bit_modify(CANINTF, tx << TXIF_SHF, 0);

	/* Load messages into empty TX buffers. */
	for (int i = TXB_NUM - 1; i >= 0; i--) {
		if (tx & BIT(i)) {
			canid_clear_cache(i);
			switch (i) {
				case 0:
					txb0_ack_emit();
					break;
				case 1:
					txb1_ack_emit();
					break;
				case 2:
					txb2_ack_emit();
					break;
				default:
					break;
			}

			if (tx_queue_pop(&frame)) {
				/* Make sure the previous message is sent. */
				while (mcp2515_read_reg(TXBCTRL(i) & TXBCTRL_TXREQ));
				load_txb(i, &frame);
				rts |= BIT(i);
			} else {
				/* The TX queue is empty, stop transmission. */
				sync_spinlock_lock(&txb_lock);
				xmit_stopped = 1;
				sync_spinlock_unlock(&txb_lock);
			}
		}
	}

	/* Initiating transmission */
	mcp2515_rts(rts);
}

/**
 * Put RXB into message queue.
 *
 * @rx: RXB bits(0 -- None, 1 -- RXB0, 2 -- RXB1, 3 -- Both)
 */
static void receive_message(uint8_t rx)
{
	struct can_frame frame;
	int ret = 0;

	if (rx & BIT(0)) {
		recv_rxb(0, &frame);
		ret += rx_queue_push(&frame);
		queue_lock_post();
	}

	if (rx & BIT(1)) {
		recv_rxb(1, &frame);
		ret += rx_queue_push(&frame);
		queue_lock_post();
	}

#if DEBUG > 1
	/* If the RX queue is full, warn user. */
	if (ret) {
		printf("CAN: Drop %d message(s).\n", -ret);
	}
#endif

	/* There is no need to clear the RX interrupt flag because the "Read RX
	 * Buffer" instruction will clear it automatically.
	 */
}

/**
 * IRQ handler
 *
 * NOTE: The interrupt flags are cleared individually because
 *       some of the operations may change the interrupt flags on the
 *       controller while we are still in the interrupt handler. Hence
 *       it will cause wrong interrupt acknowledgement. Note that, disabling
 *       the interrupts won't help, CANINTF is independent to CANINTE,
 *       it will change anyway.
 */
static void irq_handler(void *arg UNUSED)
{
	uint8_t flags;

	/* Read interrupt flags */
	flags = mcp2515_read_reg(CANINTF);

	if (flags & CANINTF_MERRF) {
		report_message_error();
	}

	if (flags & CANINTF_WAKIF) {
		/* 
		 * XXX: Not implemented
		 * Clear interrupt flags
		 */
		mcp2515_bit_modify(CANINTF, CANINTF_WAKIF, 0);
	}

	if (flags & CANINTF_ERRIF) {
		report_error();
	}

	if (flags & TXIF_MASK) {
		transmit_message((flags & TXIF_MASK) >> TXIF_SHF);
	}

	if (flags & RXIF_MASK) {
		receive_message(flags & RXIF_MASK);
	}

	Int_reg_callback(irq_handler, NULL);
}

/**
 * Enable all interrupts except "wake up".
 */
void enable_intrrupt(void)
{
	uint8_t val;

	/* Enable RX full interrupts. */
	val = CANINTE_RX0IE | CANINTE_RX1IE;

	/* Enable TX empty interrupts. */
	val |= CANINTE_TX0IE | CANINTE_TX1IE | CANINTE_TX2IE;

	/* Enable message error interrupt. */
	val |= CANINTE_MERRE;

	/* Enable error interrupt. */
	val |= CANINTE_ERRIE;

	mcp2515_write_reg(CANINTE, val);

	Int_reg_callback(irq_handler, NULL);
}

/**
 * Start TX interrupt delivery.
 */
void start_xmit(void)
{
	/* Restart transmission if it is disabled. */
	if (xmit_stopped) {
		sync_spinlock_lock(&txb_lock);
		/* Find empty TX buffer, trigger interrupt. */
		for (int i = 0; i < TXB_NUM; i++) {
			if (!(mcp2515_read_reg(TXBCTRL(i)) & TXBCTRL_TXREQ)) {
				mcp2515_bit_modify(CANINTF, CANINTF_TX0IF << i, 0xFF);
				xmit_stopped = 0;
			}
		}
		sync_spinlock_unlock(&txb_lock);
	}
}
