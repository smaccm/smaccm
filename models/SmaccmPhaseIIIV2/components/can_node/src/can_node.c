#include <string.h>
#include <can_node.h>
#include <smaccm_sys_impl_types.h>
#include <stdio.h>

static bool STATIC_TRUE = true;
static bool STATIC_FALSE = false;

#define MAX_FRAME_LEN 8

void txb0_ack_callback(void *arg) {
    if (status_0_semaphore_trywait() == 0) {
      can_node_Output_statusHandler_0_write_bool(&STATIC_TRUE);
    }
    txb0_ack_reg_callback(txb0_ack_callback, NULL);
}

void txb1_ack_callback(void *arg) {
    if (status_1_semaphore_trywait() == 0) {
      // XXX Ignore the other two mailboxes. Noops.
       /* can_node_Output_statusHandler0_0_write_bool(&STATIC_TRUE); */
    }
    txb1_ack_reg_callback(txb1_ack_callback, NULL);
}

void txb2_ack_callback(void *arg) {
    if (status_2_semaphore_trywait() == 0) {
      // XXX Ignore the other two mailboxes. Noops.
       /* can_node_Output_statusHandler0_0_write_bool(&STATIC_TRUE); */
    }
    txb2_ack_reg_callback(txb2_ack_callback, NULL);
}

void pre_init(void) {
    can_tx_setup(125000);

    txb0_ack_reg_callback(txb0_ack_callback, NULL);
    txb1_ack_reg_callback(txb1_ack_callback, NULL);
    txb2_ack_reg_callback(txb2_ack_callback, NULL);

    status_0_semaphore_wait();
    status_1_semaphore_wait();
    status_2_semaphore_wait();
    printf("Finished setting up CAN node\n");
}

bool sendit(int txb_idx, const can_message *a_frame) {
  if ( a_frame->can_message_id >= (1 << 31) ) {
    printf("Incorrect CAN message ID: %i\n", a_frame->can_message_id);
    return false;
  }
  if ( a_frame->can_message_len > 8 ) {
    printf("Incorrect CAN message length: %i\n", a_frame->can_message_len);
    return false;
  }
  if ( txb_idx != 0 ) { // only send to mailbox 0
    printf("Incorrect CAN message mailbox: %i\n", txb_idx);
    return false;
  }
  if ( a_frame->can_message_id & 1 ) { // extended frames off
    printf("Incorrect CAN extended frame: %i\n", a_frame->can_message_id);
    return false;
  }
  if ( a_frame->can_message_id & 2) { // remote frames off
    printf("Incorrect CAN remote frame: %i\n", a_frame->can_message_id);
    return false;
  }

    can_frame_t d_frame; // Driver frame

    // Right-shift 20: 2 bits to drop flags; 18 to recover 11-bit Ids.
    d_frame.ident.id = a_frame->can_message_id >> 20;

    d_frame.ident.exide = false; // TODO: Support extended IDs
    d_frame.ident.rtr = false;
    d_frame.ident.err = false;
    d_frame.prio = 0;
    d_frame.dlc = a_frame->can_message_len;
    memcpy(d_frame.data, a_frame->can_message_buf, a_frame->can_message_len);

    int ret = can_tx_sendto(txb_idx, d_frame);
    if (ret != 0) {
			// TODO: Should fail with error if this happens
			printf("Critical error: user tried to send without waiting for status\n");
	return false;
    }
    
    return true;
}

bool Input_sender_write_can_message(const can_message *a_frame) {
    status_0_semaphore_post();
    return sendit(0, a_frame);
}

bool Input_abortHandler_write_bool(const bool *x) {
    can_tx_abort(0);
    if (status_0_semaphore_trywait() == 0) {
      can_node_Output_statusHandler_0_write_bool(&STATIC_FALSE);
    }
    return true;
}

int run(void) {
	for (;;) {
		can_frame_t d_frame; // Driver frame
		can_rx_recv(&d_frame);

		can_message a_frame; // AADL frame
		a_frame.can_message_id = d_frame.ident.id << 20;
		a_frame.can_message_len = d_frame.dlc;
		uint8_t len = a_frame.can_message_len;
		if (len > MAX_FRAME_LEN) {
			printf("Unexpected frame length of %d!\n", len);
			return 1;
		} else {
			memcpy(a_frame.can_message_buf, d_frame.data, len);
			can_node_Output_recvHandler_0_write_can_message(&a_frame);
		}
	}
	return 0;
}
