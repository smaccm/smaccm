/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include <smaccm_sender.h>
#include <sender.h>
#include <stdio.h>

static int id = 0;
static bool abort_mode = false;

void send(const uint64_t *time) {
    if (abort_mode) {
	printf("Trying to abort\n");
	sender_abort_write_void();
    } else {
	can__can_frame_i frame;

	frame.id = id++;
	frame.dlc = 8;
	frame.payload[0] = 0x01;
	frame.payload[1] = 0x02;
	frame.payload[2] = 0x03;
	frame.payload[3] = 0x04;
	frame.payload[4] = 0x05;
	frame.payload[5] = 0x06;
	frame.payload[6] = 0x07;
	frame.payload[7] = 0x08;
	
	sender_write_output(&frame);
	printf("Sent can frame with id %d, payload: ", frame.id);
	uint8_t i;
	for (i = 0; i < frame.dlc; i++) {
	    printf("0x%02x ", frame.payload[i]);
	}
	printf("\n");

	if (id > 8) {
	    id = 0;
	}
    }

    abort_mode = !abort_mode;
}

void recvStatus(const bool *status) {
    printf("Sender received status: %s\n", *status ? "true" : "false");
}
