/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include <smaccm_receiver.h>
#include <stdio.h>

void receive(const can__can_frame_i *frame) {
    printf("Recieved can frame: id <%08x>, payload <%02x, %02x, %02x, ...>\n",
	   frame->ident.id, frame->payload[0], frame->payload[1], frame->payload[2]);
}

