/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include "includes/vmm_manager.h"
#include "includes/vchan_copy.h"
#include "includes/libvchan.h"

#include <sys/ioctl.h>
#include <string.h>
#include <fcntl.h>
#include <stdio.h>
#include <sys/mman.h>
#include <stdint.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>
#include <assert.h>
#include <time.h>

static int send_packet(libvchan_t *con, int num_packets);

static int send_packet(libvchan_t *con, int num_packets) {
	size_t sz;
	vchan_packet_t pak;
	int x, i;
	char fnack;

	printf("testsuite: packet start\n");

	/* Check that buffer data is correct */
	sz = libvchan_data_ready(con);
	if(sz != 0) {
		printf("error: incorrect start packet buffer size (data ready) %d\n", sz);
		return -1;
	}

	sz = libvchan_buffer_space(con);
	if(sz != FILE_DATAPORT_MAX_SIZE) {
		printf("error: incorrect start packet buffer size (bspace) %d\n", sz);
		return -1;
	}

	/* Start */

	sz = libvchan_send(con, &num_packets, sizeof(int));
	if(sz < sizeof(int)) {
		printf("--BAD PACKET NUM -- SEND\n");
		return -1;
	}

	printf("testsuite: send packets\n");

	for(x = 0; x < num_packets; x++) {
		pak.pnum = x;
		for(i = 0; i < 4; i++) {
			pak.datah[i] = i + x;
		}
		pak.guard = TEST_VCHAN_PAK_GUARD;

		while(libvchan_buffer_space(con) < sizeof(pak));
		sz = libvchan_send(con, &pak, sizeof(pak));
		if(sz < sizeof(pak)) {
			printf("--BAD PACKET -- SEND\n");
			return -1;
		}
	}

	printf("testsuite: waiting for ack..\n");

	libvchan_wait(con);
	sz = libvchan_read(con, &fnack, sizeof(char));
	if(sz < sizeof(char) || ! fnack) {
		return -1;
	}

	printf("testsuite: pack end\n");
	return 0;
}

int main(int argc, char **argv) {
	int ecount = 0;

	printf("testsuite: Creating connection in image\n");
	libvchan_t *ctrl = libvchan_client_init(50, 25);
	assert(ctrl != NULL);
	printf("testsuite: Connection Established!\n");

	ecount += send_packet(ctrl, 2000);
	printf("testsuite: %d errors\n", ecount);

	return 0;
}
