#include <string.h>
#include <can_node.h>
#include <smaccm_wrapper_i_types.h>
#include <stdio.h>

static bool STATIC_TRUE = true;
static bool STATIC_FALSE = false;

void txb0_ack_callback(void *arg) {
    if (status_0_semaphore_trywait() == 0) {
	can_node_status_0_write_bool(&STATIC_TRUE);
    }
    txb0_ack_reg_callback(txb0_ack_callback, NULL);
}

void txb1_ack_callback(void *arg) {
    if (status_1_semaphore_trywait() == 0) {
	can_node_status_1_write_bool(&STATIC_TRUE);
    }
    txb1_ack_reg_callback(txb1_ack_callback, NULL);
}

void txb2_ack_callback(void *arg) {
    if (status_2_semaphore_trywait() == 0) {
	can_node_status_2_write_bool(&STATIC_TRUE);
    }
    txb2_ack_reg_callback(txb2_ack_callback, NULL);
}

void pre_init(void) {
    printf("Setting up CAN node\n");
    can_tx_setup(125000);

    txb0_ack_reg_callback(txb0_ack_callback, NULL);
    txb1_ack_reg_callback(txb1_ack_callback, NULL);
    txb2_ack_reg_callback(txb2_ack_callback, NULL);

    status_0_semaphore_wait();
    status_1_semaphore_wait();
    status_2_semaphore_wait();
    printf("Finished setting up CAN node\n");
}

bool send_write_can__can_frame_i(int txb_idx, const can__can_frame_i *a_frame) {
    if (a_frame->id >= (1 << 29) || a_frame->dlc > 8 || txb_idx < 0 || txb_idx > 2) {
	// TODO: Should fail with error if this happens
	printf("Critical error: bad frame from user code\n");
	return false;
    }

    can_frame_t d_frame; // Driver frame
    d_frame.ident.id = a_frame->id;
    d_frame.ident.exide = false; // TODO: Support extended IDs
    d_frame.ident.rtr = false;
    d_frame.ident.err = false;
    d_frame.prio = 0;
    d_frame.dlc = a_frame->dlc;
    memcpy(d_frame.data, a_frame->payload, a_frame->dlc);

    int ret = can_tx_sendto(txb_idx, d_frame);
    if (ret != 0) {
	// TODO: Should fail with error if this happens
	printf("Critical error: user tried to send without waiting for status\n");
	return false;
    }
    
    return true;
}

bool send_0_write_can__can_frame_i(const can__can_frame_i *a_frame) {
    status_0_semaphore_post();
    return send_write_can__can_frame_i(0, a_frame);
}

bool send_1_write_can__can_frame_i(const can__can_frame_i *a_frame) {
    status_1_semaphore_post();
    return send_write_can__can_frame_i(1, a_frame);
}

bool send_2_write_can__can_frame_i(const can__can_frame_i *a_frame) {
    status_2_semaphore_post();
    return send_write_can__can_frame_i(2, a_frame);
}

bool abort_0_write_void(void) {
    can_tx_abort(0);
    if (status_0_semaphore_trywait() == 0) {
	can_node_status_0_write_bool(&STATIC_FALSE);
    }
    return true;
}

bool abort_1_write_void(void) {
    can_tx_abort(1);
    if (status_1_semaphore_trywait() == 0) {
	can_node_status_1_write_bool(&STATIC_FALSE);
    }
    return true;
}

bool abort_2_write_void(void) {
    can_tx_abort(2);
    if (status_2_semaphore_trywait() == 0) {
	can_node_status_2_write_bool(&STATIC_FALSE);
    }
    return true;
}

int run(void) {
    for (;;) {
	can_frame_t d_frame; // Driver frame
	can_rx_recv(&d_frame);

	can__can_frame_i a_frame; // AADL frame
	a_frame.id = d_frame.ident.id;
	a_frame.dlc = d_frame.dlc;
	uint8_t len = a_frame.dlc;
	if (len > 8) {
	    len = 8;
	}
	memcpy(a_frame.payload, d_frame.data, len);
	can_node_recv_write_can__can_frame_i(&a_frame);
    }
    return 0;
}
