#include <string.h>
#include <tb_smaccmpilot_tk1_types.h>
#include <camkes.h>
#include <stdio.h>

static bool STATIC_TRUE = true;

#define MAX_FRAME_LEN 8

static void txb0_ack_callback(void *arg) {
    if (status_0_semaphore_trywait() == 0) {
	tb_self2framing_status_enqueue(&STATIC_TRUE);
    }
    txb0_ack_reg_callback(txb0_ack_callback, NULL);
}

static void txb1_ack_callback(void *arg) {
    if (status_1_semaphore_trywait() == 0) {
	// XXX Ignore the other two mailboxes. Noops.
	/* can_node_Output_statusHandler0_0_write_bool(&STATIC_TRUE); */
    }
    txb1_ack_reg_callback(txb1_ack_callback, NULL);
}

static void txb2_ack_callback(void *arg) {
    if (status_2_semaphore_trywait() == 0) {
	// XXX Ignore the other two mailboxes. Noops.
	/* can_node_Output_statusHandler0_0_write_bool(&STATIC_TRUE); */
    }
    txb2_ack_reg_callback(txb2_ack_callback, NULL);
}

static bool sendit(int txb_idx, const SMACCM_DATA__CAN_Frame_i *a_frame) {
    if (a_frame->id >= (1 << 31)) {
	printf("Incorrect CAN message ID: %i\n", a_frame->id);
	return false;
    }
    if (a_frame->buf_len > 8) {
	printf("Incorrect CAN message length: %i\n", a_frame->buf_len);
	return false;
    }
    if (txb_idx != 0) { // only send to mailbox 0
	printf("Incorrect CAN message mailbox: %i\n", txb_idx);
	return false;
    }
    if (a_frame->id & 1) { // extended frames off
	printf("Incorrect CAN extended frame: %i\n", a_frame->id);
	return false;
    }
    if (a_frame->id & 2) { // remote frames off
	printf("Incorrect CAN remote frame: %i\n", a_frame->id);
	return false;
    }

    can_frame_t d_frame; // Driver frame

    // Right-shift 20: 2 bits to drop flags; 18 to recover 11-bit Ids.
    d_frame.ident.id = a_frame->id >> 20;

    d_frame.ident.exide = false;
    d_frame.ident.rtr = false;
    d_frame.ident.err = false;
    d_frame.prio = 0;
    d_frame.dlc = a_frame->buf_len;
    memcpy(d_frame.data, a_frame->buf, a_frame->buf_len);

    int ret = can_tx_sendto(txb_idx, d_frame);

    if (ret != 0) {
	// TODO: Should fail with error if this happens
	printf("Critical error: user tried to send without waiting for status\n");
	return false;
    }

    return true;
}

static void tb_framing2self_callback(void *unused) {
    SMACCM_DATA__CAN_Frame_i a_frame;
    while (tb_framing2self_dequeue(&a_frame)) {
        status_0_semaphore_post();
        sendit(0, &a_frame);
    }
    tb_framing2self_notification_reg_callback(&tb_framing2self_callback, NULL);
}

void pre_init(void) {
    can_tx_setup(125000);

    txb0_ack_reg_callback(txb0_ack_callback, NULL);
    txb1_ack_reg_callback(txb1_ack_callback, NULL);
    txb2_ack_reg_callback(txb2_ack_callback, NULL);

    status_0_semaphore_wait();
    status_1_semaphore_wait();
    status_2_semaphore_wait();

    tb_framing2self_notification_reg_callback(&tb_framing2self_callback, NULL);
    printf("Finished setting up CAN node\n");
}

int run(void) {
    for (;;) {
	can_frame_t d_frame; // Driver frame
	can_rx_recv(&d_frame);
	if (d_frame.dlc > MAX_FRAME_LEN) {
	    printf("Unexpected frame length of %d!\n", d_frame.dlc);
	    continue;
	}

	SMACCM_DATA__CAN_Frame_i a_frame; // AADL frame
	a_frame.id = d_frame.ident.id << 20;
	a_frame.buf_len = d_frame.dlc;
	memcpy(a_frame.buf, d_frame.data, d_frame.dlc);
	bool b = tb_self2framing_frame_enqueue(&a_frame);
	if (!b) {
	    printf("ERROR: CAN Driver: Unable to put CAN message in queue\n");
	}
    }
    return 0;
}
