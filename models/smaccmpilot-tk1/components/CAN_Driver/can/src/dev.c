/*
 * Copyright 2016, Data61
 * Commonwealth Scientific and Industrial Research Organisation (CSIRO)
 * ABN 41 687 119 230.
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 * @TAG(D61_BSD)
 */

#define ZF_LOG_LEVEL ZF_LOG_WARN

#include <utils/ansi.h>
#include <utils/zf_log.h>

#include <can_inf.h>

#include <mcp2515.h>
#include <queue.h>

#include <camkes.h>

#define mcp2515_dev_debug(fmt, args...) \
    do { \
           ZF_LOGD( A_FG_Y "  %s: " A_FG_RESET, __func__); \
           ZF_LOGD( fmt, ##args); \
           ZF_LOGD("\n"); \
    } while(0)

void mcp2515_dumpstatus(uint8_t status)
{
    switch (status) {
    case IDLE:
        ZF_LOGD("    status: IDLE\n");
        break;
    case PENDING:
        ZF_LOGD("    status: PENDING\n");
        break;
    case LOST:
        ZF_LOGD("    status: LOST\n");
        break;
    case ERR:
        ZF_LOGD("    status: ERR\n");
        break;
    case ABORT:
        ZF_LOGD("    status: ABORT\n");
        break;
    default:
        ZF_LOGD("    bogus status!!\n");
    }
}

#define MSG_QUEUE_SIZE  128

void can_rx__init(void)
{
    ZF_LOGD("CAN device started...\n");

    queue_lock_wait();
    mq_init(MSG_QUEUE_SIZE);
}

int can_tx_setup(int baudrate)
{
    mcp2515_reset();

    /* Wait until reset finishes. */
    while (1) {
        if (get_mode() == REQOP_CONFIG) {
            break;
        }
    }

    set_baudrate(baudrate);
    enable_intrrupt();

    // enable_rollover();
    set_rx_mode(0, RXM_ANY);
    set_mode(REQOP_NORMAL);

    return 0;
}


int can_setup_loopback(int baudrate)
{
    mcp2515_reset();

    /* Wait until reset finishes. */
    while (1) {
        if (get_mode() == REQOP_CONFIG) {
            break;
        }
    }

    set_baudrate(baudrate);
    //enable_intrrupt();

//	enable_rollover();
    set_rx_mode(0, RXM_ANY);

    mcp2515_dev_debug("Setting loop back mode...\n");

    set_mode(REQOP_LOOP);

    mcp2515_dev_debug("waiting for device to enter loopback mode......\n");
    while (1) {
        if (get_mode() == REQOP_LOOP) {
            break;
        }
    }

    mcp2515_dev_debug("device is in loop back mode\n");
    return 0;
}


int can_tx_sendto(int txb_idx, struct can_frame frame)
{
    int ret;
    uint8_t status = txb_status(txb_idx);
    mcp2515_dev_debug("dumping status\n");
    mcp2515_dumpstatus(status);
    if (status == IDLE) {
        frame.prio = get_frame_priority(txb_idx, &frame.ident);
        load_txb(txb_idx, &frame);
        mcp2515_rts(BIT(txb_idx));
        ret = 0;
    } else {
        ret = -1;
    }

    return ret;
}

void can_tx_send(struct can_frame frame)
{
    int ret;

    do {
        ret = tx_queue_push(&frame);
        start_xmit();
    } while (ret < 0);
}

void can_rx_recv(struct can_frame *frame)
{
    queue_lock_wait(); // Wait for frame to be added
    rx_queue_pop(frame);
}


void can_recv_rxb(int rxb_idx, struct can_frame *frame)
{
    recv_rxb(rxb_idx, frame);
}

void can_tx_abort(int txb_idx)
{
    abort_tx(txb_idx);
}

int can_poll_rxbuf()
{
    return poll_rxbuf();
}
int can_tx_status(int txb_idx)
{
    return txb_status(txb_idx);
}

int can_rx_set_filter(struct can_id id, unsigned int mask)
{
    int ret;

    set_mode(REQOP_CONFIG);

    ret = set_rx_filter(id, mask);

    set_rx_mode(0, RXM_BOTH);

    /*  TODO: Check if rollover is on
        The rollover function is broken on this chip.
        This TODO should be addressed when the HW gets fixed which is very unlikely. */
    set_rx_mode(1, RXM_BOTH);

    set_mode(REQOP_NORMAL);

    return ret;
}

void can_rx_clear_filter(int filter_id)
{
    set_mode(REQOP_CONFIG);

    clear_rx_filter(filter_id);

    set_mode(REQOP_NORMAL);
}

void can_rx_disable_filtering(void)
{
    set_mode(REQOP_CONFIG);

    clear_filter_mask(2);

    set_rx_mode(0, RXM_ANY);

    /*  TODO: Check if rollover is on
        The rollover function is broken on this chip.
        This TODO should be addressed when the HW gets fixed which is very unlikely. */
    set_rx_mode(1, RXM_ANY);

    set_mode(REQOP_NORMAL);
}
