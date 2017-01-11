/*
 * Copyright 2016, Data61
 * Commonwealth Scientific and Industrial Research Organisation (CSIRO)
 * ABN 41 687 119 230.
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 * @TAG(D61_BSD)
 */

/*
 * MCP2515 - SPI interface instruction set.
 */

#define ZF_LOG_LEVEL ZF_LOG_WARN

#include <stdint.h>
#include <stdio.h>
#include <string.h>
#include <utils/ansi.h>
#include <utils/zf_log.h>
#include <sync/spinlock.h>
#include <camkes.h>
#include "mcp2515.h"
#include "spi_inf.h"

#define DEV_ID CAN_APP_ID
static spi_dev_port_t *spi_dev = NULL;

void pre_init(void)
{
    spi_dev = (spi_dev_port_t *)spi_can;
}

/*
 * Soft reset - put MCP2515 into default state.
 */
void mcp2515_reset(void)
{

    ZF_LOGD("CMD_RESET, spi_transfer(DEV_ID, 1, 0)");
    spi_lock_lock();
    spi_dev->txbuf[0] = CMD_RESET;
    spi_transfer(DEV_ID, 1, 0);
    spi_lock_unlock();
    ZF_LOGD("mcp2515 spi_transfer done");
}

/* Read register */
uint8_t mcp2515_read_reg(uint8_t reg)
{
    uint8_t ret;

    ZF_LOGD("CMD_READ, spi_transfer(DEV_ID, 2, 1)\n");
    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_READ;
    spi_dev->txbuf[1] = reg;

    spi_transfer(DEV_ID, 2, 1);

    ret = spi_dev->rxbuf[2];

    spi_lock_unlock();

    return ret;
}

/* Read registers in series */
void mcp2515_read_nregs(uint8_t reg, int count, uint8_t *buf)
{
    if (!buf) {
        ZF_LOGE("Empty buffer!\n");
        return;
    }

    ZF_LOGD("CMD_READ, spi_transfer(DEV_ID, 2, %d)", count);
    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_READ;
    spi_dev->txbuf[1] = reg;

    spi_transfer(DEV_ID, 2, count);

    memcpy(buf, &spi_dev->rxbuf[2], count);

    spi_lock_unlock();
}

/* Write to register. */
void mcp2515_write_reg(uint8_t reg, uint8_t val)
{

    ZF_LOGD("CMD_WRITE, spi_transfer(DEV_ID, 3, 0)");
    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_WRITE;
    spi_dev->txbuf[1] = reg;
    spi_dev->txbuf[2] = val;

    spi_transfer(DEV_ID, 3, 0);

    spi_lock_unlock();
}

/* Write to registers. */
void mcp2515_write_nregs(uint8_t reg, uint8_t *buf, int count)
{
    if (!buf) {
        ZF_LOGE("Empty buffer!");
        return;
    }

    ZF_LOGD("CMD_WRITE, spi_transfer(DEV_ID, 2 + %d, 0)", count);
    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_WRITE;
    spi_dev->txbuf[1] = reg;

    memcpy(&spi_dev->txbuf[2], buf, count);

    spi_transfer(DEV_ID, 2 + count, 0);

    spi_lock_unlock();
}

/* Bit Modify Instruction:
 * provides a means for settig or clearing individual bits in specific status
 * and control registers. Not available for all registers.
 * The following listed registers allow the user to use this instruction:
 * BFPCTRL, TXRTSCTRL, CANCTRL, CNF1, CNF2, CNF3, CANINTE, CANINTF, EFLG,
 * TXB0CTRL, TXB1CTRL, TXB2CTRL, RXB0CTRL, RXB1CTRL
 */
void mcp2515_bit_modify(uint8_t reg, uint8_t mask, uint8_t val)
{

    ZF_LOGD("CMD_BIT_MODIFY, spi_transfer(DEV_ID, 4, 0)") ;
    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_BIT_MODIFY;
    spi_dev->txbuf[1] = reg;
    spi_dev->txbuf[2] = mask;
    spi_dev->txbuf[3] = val;

    spi_transfer(DEV_ID, 4, 0);

    spi_lock_unlock();
}

/*
 * Read Status Instruction -
 * Allows single instruction access to some of the often used status bits
 * for message reception and transmission.
 * 		Bit		Descriptions
 * 	--------------------------------
 * 		0 		CANNINTF.RX0IF		// RXB0 Full Interrupt Flag bit
 *		1 		CANNINTFL.RX1IF		// RXB1 Full Interrupt Flag bit
 *		2		TXB0CNTRL.TXREQ		// TXB0 Message Transmit Request bit
 *		3		CANINTF.TX0IF		// TXB0 Empty Interrupt Flag bit
 *		4		TXB1CNTRL.TXREQ		// TXB1 Message Transmit Request bit
 *		5		CANINTF.TX1IF		// TXB1 Empty Interrupt Flag bit
 *		6		TXB2CNTRL.TXREQ		// TXB2 Message Transmit Request bit
 *		7		CANINTF.TX2IF		// TXB2 Empty Interrupt Flag bit
 *	---------------------------------
 */
uint8_t mcp2515_read_status(void)
{
    uint8_t ret;

    ZF_LOGD("CMD_READ_STATUS, spi_transfer(DEV_ID, 1, 1)") ;

    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_READ_STATUS;

    spi_transfer(DEV_ID, 1, 1);

    ret = spi_dev->rxbuf[1];

    spi_lock_unlock();

    return ret;
}

/*
 * Read RX Status Instruction -
 * Used to quickly determine which filter matched the message and message type (standard, extended, remote).
 * After the command byte is sent, the controller will return 8 bits of data contain the status data.
 * 	Bit	7 6		Received Message
 * 	--------------------------------
 * 		0 0 	No Rx message
 * 		0 1		Message in RXB0
 * 		1 0		Message in RXB1
 * 		1 1 	Message in both buffers
 *	---------------------------------
 *	Bit	4 3		Msg Type Received
 *	---------------------------------
 *		0 0		Standard data frame
 *		0 1		Standard remote frame
 *		1 0		Extended data frame
 *		1 1		Extended remote frame
 *	---------------------------------
 *	Bit	2 1	0	Filter Match
 *	---------------------------------
 *		0 0	0	RXF0
 *		0 0	1	RXF1
 *		0 1	0	RXF2
 *		0 1	1	RXF3
 *		1 0 0 	RXF4
 *		1 0 1	RXF5
 *		1 1 0	RXF0 (Rollover to RXB1)
 *		1 1 1	RXF1 (Rollover to RXB1)
 *	---------------------------------
 */
uint8_t mcp2515_rx_status(void)
{
    uint8_t ret;

    ZF_LOGD("CMD_RX_STATUS, spi_transfer(DEV_ID, 1, 1)") ;
    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_RX_STATUS;

    spi_transfer(DEV_ID, 1, 1);

    ret = spi_dev->rxbuf[1];

    spi_lock_unlock();

    return ret;
}

/**
 * Request to send instruction.
 *
 * @mask: Three bits mask(Bit0 -- TXB0, Bit1 -- TXB1, Bit2 -- TXB2)
 */
void mcp2515_rts(uint8_t mask)
{

    ZF_LOGD("CMD_RTS | 0x%20x, spi_transfer(DEV_ID, 1, 0)", mask) ;
    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_RTS | mask;
    spi_transfer(DEV_ID, 1, 0);

    spi_lock_unlock();
}

/**
 * Load TX buffer instruction.
 *
 * @buf: Data to send.
 * @len: Buffer length.
 * @idx: TX buffer index(0, 1 or 2).
 * @flag: 0 -- whole CAN frame;
 *        1 -- payload only.
 */
void mcp2515_load_txb(uint8_t *buf, uint8_t len, uint8_t idx, uint8_t flag)
{
    /*
     * Three bits mask indicates six possible locations.
     * The highest two bits refer to TXB2 and TXB1.
     * If the lowest bit is set, address points to TX buffer's data register.
     */
    uint8_t mask = (idx * 2) | flag;


    ZF_LOGD("CMD_LOAD_TXB | 0x%02x, spi_transfer(DEV_ID, %d + 1, 0)", mask, len) ;
    ZF_LOGD("buf[0]: 0x%02x buf[1]: 0x%02x buf[2]: 0x%02x buf[3]: 0x%02x", buf[0], buf[1], buf[2], buf[3]);
    ZF_LOGD("buf[4]: 0x%02x buf[5]: 0x%02x buf[6]: 0x%02x buf[7]: 0x%02x", buf[4], buf[5], buf[6], buf[7]);
    ZF_LOGD("buf[8]: 0x%02x buf[9]: 0x%02x buf[10]: 0x%02x buf[11]: 0x%02x", buf[8], buf[9], buf[10], buf[11]);
    ZF_LOGD("buf[12]: 0x%02x buf[13]: 0x%02x buf[14]: 0x%02x buf[15]: 0x%02x", buf[12], buf[13], buf[14], buf[15]);

    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_LOAD_TXB | mask;
    memcpy(spi_dev->txbuf + 1, buf, len);

    /* Buffer length plus one byte instruction. */
    spi_transfer(DEV_ID, len + 1, 0);
    ZF_LOGD("spi_transfer done");
    spi_lock_unlock();
}

/**
 * Read RX buffer instruction.
 *
 * @buf: Read buffer.
 * @len: Buffer length.
 * @idx: RX buffer index(0 or 1).
 * @flag: 0 -- whole CAN frame;
 *        1 -- payload only.
 */
void mcp2515_read_rxb(uint8_t *buf, uint8_t len, uint8_t idx, uint8_t flag)
{
    /*
     * The high bit refer to RXB0 and RXB1.
     * If the low bit is set, address points to RX buffer's data register.
     */
    uint8_t mask = 0;
    ZF_LOGD("CMD_READ_RXB | 0x%02x, spi_transfer(DEV_ID, 1, %d)\n", mask, len) ;
    spi_lock_lock();

    spi_dev->txbuf[0] = CMD_READ_RXB | mask;

    spi_transfer(DEV_ID, 1, len);
    memcpy(buf, spi_dev->rxbuf + 1, len);

    spi_lock_unlock();
}
