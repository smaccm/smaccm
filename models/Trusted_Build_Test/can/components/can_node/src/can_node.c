#include <string.h>
#include <can_node.h>
#include <smaccm_wrapper_i_types.h>
#include <stdio.h>

void pre_init(void) {
    printf("pre_init\n");
    can_tx_setup(125000);
}

bool client_input_write_can__can_frame_i(const can__can_frame_i * a_frame) {
    if (a_frame->ident.id >= (1 << 29) || a_frame->dlc > 8) {
	return false;
    }

    can_frame_t d_frame; // Driver frame
    d_frame.ident.id = a_frame->ident.id;
    d_frame.ident.exide = a_frame->ident.exide;
    d_frame.ident.rtr = a_frame->ident.rtr;
    d_frame.ident.err = a_frame->ident.err;
    d_frame.prio = 0;
    d_frame.dlc = a_frame->dlc;
    memcpy(d_frame.data, a_frame->payload, a_frame->dlc);

    can_tx_send(d_frame);
    
    return true;
}

int run(void) {
    for (;;) {
	can_frame_t d_frame; // Driver frame
	can_rx_recv(&d_frame);

	can__can_frame_i a_frame; // AADL frame
	a_frame.ident.id = d_frame.ident.id;
	a_frame.ident.exide = d_frame.ident.exide;
	a_frame.ident.rtr = d_frame.ident.rtr;
	a_frame.ident.err = d_frame.ident.err;
	a_frame.dlc = d_frame.dlc;
	uint8_t len = a_frame.dlc;
	if (len > 8) {
	    len = 8;
	}
	memcpy(a_frame.payload, d_frame.data, len);
	can_node_client_output_write_can__can_frame_i(&a_frame);
    }
    return 0;
}
