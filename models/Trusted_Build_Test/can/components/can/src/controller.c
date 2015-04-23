/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

/*
 * Device driver for MCP2515
 * Microchip Technology MCP2515 is a stand-alone
 * Controller Area Network (CAN) controller that
 * implements the CAN specification, version 2.0B.
 * It is capable of transmitting and receiving both standard
 * and extended data and remote frames. The MCP2515
 * has two acceptance masks and six acceptance filters
 * that are used to filter out unwanted messages, thereby
 * reducing the host MCUs overhead. The MCP2515
 * interfaces with microcontrollers (MCUs) via an industry
 * standard Serial Peripheral Interface (SPI).
 *
 * CAN characteristics:
 *  Standard data frame:
 *    up to 8 bytes payload per message
 *    11 bit standard id
 *    18 bit extended id
 *     4 bit data length code
 *
 * Extended:
 *  32 bit s arbitration:  11 bits most significant address
 *                         18 bits less significant address
 *                          3 bits control
 *  6 bits control: data length code
 *
 * This driver uses standard and extended frames.
 * the hardware can listen on up to three addresses at a time.
 * It can buffer two receive frames, and three transmit frames,
 *
 */

#include <stdint.h>
#include <stdio.h>
#include <string.h>

#include "can_inf.h"
#include "mcp2515.h"

#include "can.h"

/* Macros for juggling TXB/RXB registers. */
#define SIDH_SHF      3
#define SIDL_SHF      5
#define EXIDE_SHF     3
#define SIDL_EID_SHF  16
#define EID8_SHF      8
#define RTR_SHF       6

#define BYTE_MASK    0xFF
#define MAX_BUF_LEN  13   //Maximum length of a CAN frame.

#define FILTER_BYTES    4 //Length of RX filter.
#define NUM_OF_MASKS    2 //Number of filter masks.
#define FILTS_OF_MASK0  2 //Number of filters link to Mask 0.
#define FILTS_OF_MASK1  4 //Number of filters link to Mask 1.

/* Controller TXB/RXB/Filter registers */
enum can_buf_regs {SIDH = 0, SIDL, EID8, EID0, DLC, DAT};

/**
 * Set bit timing registers
 *
 * Note: This operation requires configuration mode.
 */
static void hw_set_bit_timing(uint8_t sjw, uint8_t brp, uint8_t propseg,
				uint8_t phseg1, uint8_t phseg2)
{
	uint8_t cnf[3];

	/* Convert into register's length bits. */
	sjw--;
	brp--;
	propseg--;
	phseg1--;
	phseg2--;

	/* Get the CNF registers' value. */
	cnf[2] = (sjw << CNF1_SJW_SHF) | brp;
	cnf[1] = CNF2_BTLMODE | phseg1 << CNF2_PHSEG1_SHF | propseg;
	cnf[0] = phseg2;

	mcp2515_write_nregs(CNF3, cnf, 3);
}

/**
 * Check if a filter is empty.
 *
 * @idx: Filter index(0 ~ 5)
 *
 * @return 0 -- Filter in use.
 *         Other -- Filter is empty.
 *
 * NOTE: Only works in configuration mode.
 */
static int is_filter_empty(uint8_t idx)
{
	uint8_t buf[FILTER_BYTES];
	int ret = 0;

	mcp2515_read_nregs(RXFSIDH(idx), FILTER_BYTES, buf);

	for (int i = 0; i < FILTER_BYTES; i++) {
		ret |= buf[i];
	}

	return !ret;
}

/**
 * Write to filter and mask registers on the controller.
 *
 * @idx: Filter index.
 * @can_id: CAN frame id to be filtered.
 * @mask: Mask of the filter, ignored if zero.
 *
 * NOTE: If mask is zero, it means that some of the other filters are in use.
 *       And the mask registers have been set.
 */
static void write_to_filter(uint8_t idx, struct can_id can_id, uint32_t mask)
{
	uint32_t sid, eid;
	uint8_t buf[FILTER_BYTES];

	/* Check if mask registers need to be set. */
	if (mask) {
		if (can_id.exide) {
			sid = mask >> CAN_EID_BITS;
			eid = mask & CAN_EID_MASK;
		} else {
			sid = mask;
			eid = 0;
		}

		buf[SIDH] = sid >> SIDH_SHF;
		buf[SIDL] = (sid << SIDL_SHF) | (eid >> SIDL_EID_SHF);
		buf[EID8] = eid >> EID8_SHF;
		buf[EID0] = eid & BYTE_MASK;

		/* Filter 0 and 1 use Mask 0, filter 2 ~ 5 use Mask 1. */
		if (idx < FILTS_OF_MASK0) {
			mcp2515_write_nregs(RXMSIDH(0), buf, FILTER_BYTES);
		} else {
			mcp2515_write_nregs(RXMSIDH(1), buf, FILTER_BYTES);
		}
	}

	/* Set filter registers. */
	if (can_id.exide) {
		sid = can_id.id >> CAN_EID_BITS;
		eid = can_id.id & CAN_EID_MASK;
	} else {
		sid = can_id.id;
		eid = 0;
	}

	buf[SIDH] = sid >> SIDH_SHF;
	buf[SIDL] = (sid << SIDL_SHF) | (can_id.exide << EXIDE_SHF) | (eid >> SIDL_EID_SHF);
	buf[EID8] = eid >> EID8_SHF;
	buf[EID0] = eid & BYTE_MASK;

	mcp2515_write_nregs(RXFSIDH(idx), buf, FILTER_BYTES);
}

/**
 * Set CAN baud rate
 *
 * Inspired by http://karmacarpentry.com/can_calc.html
 *
 * According to the CAN specification 2.0 part B,
 * SyncSeg is fixed at 1 TQ(Time Quantum),
 * ProSeg is from 1 ~ 8 TQ,
 * PhSeg1 is from 1 ~ 8 TQ,
 * PhSeg2 is from 2 ~ 8 TQ.
 */
#define OSC_FREQ   20000000U   //Crystal on the daughter board 20MHz.
#define NS_IN_SEC  1000000000U //Number of nanosecond per second.
#define TIME_PER_TICK  DIV_ROUND(NS_IN_SEC, OSC_FREQ)
#define MIN_TQ_NUM    8  //Minimum number of Time Quantum possible.
#define MAX_TQ_NUM    25 //Maximum number of Time Quantum possible.
#define SAMPLE_POINT  30 //Percentage of the sample point towards the end of NBT.
void set_baudrate(uint32_t baudrate)
{
	uint8_t brp = 0;   //Baud rate pre-scaler.
	uint32_t tq_time;  //Number of nanoseconds per TQ.
	uint32_t bit_time; //Nanoseconds that transfer one bit.
	uint32_t error;    //Calculation error.
	uint32_t min_error = 0xFFFFFFFF; //Minimum error.
	uint8_t tq_num = MIN_TQ_NUM;     //Number of TQ in the Nominal Bit Time(NBT).
	uint8_t propseg, phseg1, phseg2;

	/* Find the BRP and number of TQ with minimum timing error. */
	bit_time = DIV_ROUND(NS_IN_SEC, baudrate);
	for (int i = MIN_TQ_NUM; i <= MAX_TQ_NUM; ++i) {
		tq_time = DIV_ROUND(bit_time, i);

		/*
		 * There is an error when we convert "ticks" to "nanoseconds".
		 * The maximum error should be TIME_PER_TICK / 2
		 */
		error = tq_time % TIME_PER_TICK;
		if (2 * error > TIME_PER_TICK) {
			error = TIME_PER_TICK - error;
		}

		/* Choose the minimum one. */
		if (error < min_error) {
			min_error = error;
			tq_num = i;
		}
	}

	/*
	 * Base TQ equals twice the crystal period.
	 * TQ = 2 * BRP * TIME_PER_TICK = 2 * BRP / OSC_FREQ
	 */
	brp = DIV_ROUND(OSC_FREQ, (2 * baudrate * tq_num));

	/*
	 * Nominal Bit Time(NBT) is n times of TQ.
	 * n = syncseg + propseg + phseg1 + phseg2
	 *
	 * Sample point is at the start of phseg2 which should be
	 * at around 70% of the NBT.
	 */
	phseg2 = DIV_ROUND(tq_num * SAMPLE_POINT, 100);

	/* phseg1 should be always greater or equal to phseg2. */
	phseg1 = phseg2;
	propseg = tq_num - 1 - phseg1 - phseg2;

	/*
	 * Propseg is recommended to be small, ideally be 2.
	 * It is defined as twice the sum of the signal's propagation
	 * time on the bus line, including the bus driver delay.
	 */
	if (propseg > 2) {
		phseg1 += (propseg - 2);
		propseg = 2;
	}

	/*
	 * Always set SJW to 1, larger values are for those inaccurate
	 * and unstable clocks.
	 */
	hw_set_bit_timing(1, brp, propseg, phseg1, phseg2);
}

/**
 * Request MCP2515 operation mode
 */
void set_mode(enum op_mode mode)
{
	mcp2515_bit_modify(CANCTRL, CANCTRL_REQOP_MASK, mode << CANCTRL_REQOP_SHF);
}

/**
 * Get current operation mode of the controller.
 */
enum op_mode get_mode(void)
{
	return mcp2515_read_reg(CANSTAT) >> CANSTAT_OPMOD_SHF;
}

/**
 * Enable rollover
 */
void enable_rollover(void)
{
	mcp2515_bit_modify(RXBCTRL(0), RXBCTRL_BUKT, RXBCTRL_BUKT);
}

/**
 * Set RX buffer filter
 *
 * NOTE: Only works in configuration mode.
 *
 * FIXME: Check if rollover is enabled.
 */
int set_rx_filter(struct can_id can_id, uint32_t mask)
{
	uint8_t buf[FILTER_BYTES];
	uint32_t cur_mask;         //Current value in the mask registers.
	uint8_t sid, eid;

	for (int i = 0; i < NUM_OF_MASKS; i++) {
		/* Read Mask */
		mcp2515_read_nregs(RXMSIDH(i), FILTER_BYTES, buf);

		sid = (buf[SIDH] << SIDH_SHF) | (buf[SIDL] >> SIDL_SHF);
		eid = buf[SIDL] << SIDL_EID_SHF | buf[EID8] << EID8_SHF | buf[EID0];
		cur_mask = sid << CAN_EID_BITS | eid;

		if (!can_id.exide) {
			cur_mask >>= CAN_EID_BITS;
		}

		if (!cur_mask && !eid) {
			/*
			 * If Mask is empty, it means none of the filters which
			 * relate to the mask is in use.
			 */
			write_to_filter(i * FILTS_OF_MASK0, can_id, mask);
			return i * FILTS_OF_MASK0;
		} else if (cur_mask == mask) {
			/*
			 * Some of the filter are in use, the mask has to be the
			 * same, otherwise the old filters would be corrupted.
			 */
			for (int j = i * FILTS_OF_MASK0; j < (i * FILTS_OF_MASK1 + FILTS_OF_MASK0); j++) {
				if (is_filter_empty(j)) {
					write_to_filter(j, can_id, 0);
					return j;
				}
			}
		}
	}

	return -1;
}

/**
 * Clear RX buffer filter.
 *
 * @idx: Filter index.
 *
 * NOTE: Only works in configuration mode.
 */
void clear_rx_filter(uint8_t idx)
{
	int mask_idx, i;
	uint8_t buf[FILTER_BYTES];

	memset(buf, 0, FILTER_BYTES);

	/* Clear filter registers. */
	mcp2515_write_nregs(RXFSIDH(idx), buf, FILTER_BYTES);

	/* Check if mask registers can also be cleared. */
	mask_idx = (idx < FILTS_OF_MASK0) ? 0 : 1;

	for (i = mask_idx * FILTS_OF_MASK0; i < (mask_idx * FILTS_OF_MASK1 + FILTS_OF_MASK0); i++) {
		if (!is_filter_empty(i)) {
			break;
		}
	}

	/* All filters of the mask are empty, clear the mask. */
	if ((mask_idx == 0 && i == FILTS_OF_MASK0) ||
	    (mask_idx == 1 && i == FILTS_OF_MASK1 + FILTS_OF_MASK0)) {
		mcp2515_write_nregs(RXMSIDH(mask_idx), buf, FILTER_BYTES);
	}
}

/**
 * Disable mask of filters.
 * Clear the mask registers will disable filters.
 *
 * @rxb_idx: RX buffer index(0 -- RXB0, 1 -- RXB1, 2 -- both)
 *
 * NOTE: Only works in configuration mode.
 */
void clear_filter_mask(uint8_t rxb_idx)
{
	/* Two sets of mask registers */
	uint8_t buf[FILTER_BYTES * 2];

	memset(buf, 0, FILTER_BYTES * 2);

	if (rxb_idx >= 2) {
		mcp2515_write_nregs(RXMSIDH(0), buf, FILTER_BYTES * 2);
	} else {
		mcp2515_write_nregs(RXMSIDH(rxb_idx), buf, FILTER_BYTES);
	}
}

/*
 * Load CAN frame into TX buffer.
 *
 * @txb_idx: TX buffer identifier.
 * @frame: CAN frame to be sent.
 *
 * TODO: Send payload only if IDs(and others) remain the same.
 */
void load_txb(int txb_idx, struct can_frame *frame)
{
	uint32_t sid, eid;
	uint8_t buf[MAX_BUF_LEN];

	memset(buf, 0, MAX_BUF_LEN);

	/* Separate standard ID and extended ID if extended frame */
	if (frame->ident.exide) {
		sid = frame->ident.id >> CAN_EID_BITS;
		eid = frame->ident.id & CAN_EID_MASK;
	} else {
		sid = frame->ident.id;
		eid = 0;
	}

	/* Convert CAN frame to transmit buffer form */
	buf[SIDH] = sid >> SIDH_SHF;
	buf[SIDL] = (sid << SIDL_SHF) | (frame->ident.exide << EXIDE_SHF) | (eid >> SIDL_EID_SHF);
	buf[EID8] = eid >> EID8_SHF;
	buf[EID0] = eid & BYTE_MASK;
	buf[DLC] = (frame->ident.rtr << RTR_SHF) | frame->dlc;

	/* Copy payload */
	memcpy(buf + DAT, frame->data, frame->dlc);

	/* Load to registers on the controller */
	mcp2515_load_txb(buf, frame->dlc + DAT, txb_idx, 0);

	/* Set TX buffer priority. */
	mcp2515_bit_modify(TXBCTRL(txb_idx), TXBCTRL_TXP_MASK, frame->prio);
}

/**
 * Receive from RX buffer.
 *
 * @rxb_idx: RX buffer identifier
 * @frame: CAN frame to be filled.
 *
 * TODO: Only receive payload if IDs can be ignored.
 */
void recv_rxb(int rxb_idx, struct can_frame *frame)
{
	uint32_t sid, eid;
	uint8_t buf[MAX_BUF_LEN];

	/* Read RX buffer from the controller */
	mcp2515_read_rxb(buf, MAX_BUF_LEN, rxb_idx, 0);

	/* All frames has standard ID */
	sid = (buf[SIDH] << SIDH_SHF) | (buf[SIDL] >> SIDL_SHF);

	/* See if it is an extended frame */
	frame->ident.exide = buf[SIDL] >> EXIDE_SHF;
	if (frame->ident.exide) {
		eid = buf[SIDL] << SIDL_EID_SHF | buf[EID8] << EID8_SHF | buf[EID0];
		frame->ident.id = sid << CAN_EID_BITS | eid;
	} else {
		frame->ident.id = sid;
	}

	/* Remote frames do not have payload */
	frame->ident.rtr = buf[DLC] >> RTR_SHF;
	if (frame->ident.rtr) {
		frame->dlc = 0;
	} else {
		frame->dlc = buf[DLC];
	}

	/* Copy in payload */
	memcpy(frame->data, buf + DAT, frame->dlc);
}

/**
 * Aborting transmission
 *
 * @txb_idx: TX buffer identifier.
 */
void abort_tx(int txb_idx)
{
	mcp2515_bit_modify(TXBCTRL(txb_idx), TXBCTRL_TXREQ, 0);
}

/**
 * TX buffer status
 *
 * @txb_idx: TX buffer identifier.
 */
int txb_status(int txb_idx)
{
	uint8_t status = mcp2515_read_reg(TXBCTRL(txb_idx));

	if (status & TXBCTRL_TXREQ) {
		if (status & TXBCTRL_MLOA) {
			status = LOST;
		} else if (status & TXBCTRL_TXERR) {
			status = ERR;
		} else if (status & TXBCTRL_ABTF) {
			status = ABORT;
		} else {
			status = PENDING;
		}
	} else {
		status = IDLE;
	}

	return status;
}

