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
#include <stdio.h>

static int id = 0;

void send(const uint64_t *time) {
    can__can_frame_i frame;

    frame.ident.id = id++;
    frame.ident.exide = true;
    frame.ident.rtr = false;
    frame.ident.err = false;
    frame.dlc = 8;
    frame.payload[0] = 0x01;
    frame.payload[1] = 0x02;
    frame.payload[2] = 0x03;
    frame.payload[3] = 0x04;
    frame.payload[4] = 0x05;
    frame.payload[5] = 0x06;
    frame.payload[6] = 0x07;
    frame.payload[7] = 0x08;

    printf("User code sending CAN frame\n");
    sender_write_output(&frame);

    if (id > 8) {
	id = 0;
    }
}

