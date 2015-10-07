/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include <stdio.h>
#include <stdint.h>
#include <autoconf.h>

#include <sel4/sel4.h>
#include <sel4utils/util.h>

#include <sel4vchan/vmm_manager.h>
#include <sel4vchan/vchan_copy.h>
#include <sel4vchan/vchan_sharemem.h>
#include <sel4vchan/libvchan.h>
#include <sel4vchan/vchan_component.h>

#include <camera_vm.h>

#include <camkes/dataport.h>
#define NUM_PACKETS 80

#include "smaccm_camera_vm.h"

static camkes_vchan_con_t con = {
    .connect = &vchan_con_new_connection,
    .disconnect = &vchan_con_rem_connection,
    .get_buf = &vchan_con_get_buf,
    .status = &vchan_con_status,

    .alert = &vchan_con_ping,
    .wait = &vevent_wait,
    .poll = &vevent_poll,

    .dest_dom_number = 0,
    .source_dom_number = 50,
};

#define DEBUG_CAMERA_VM

#ifdef DEBUG_CAMERA_VM
#define DHELL(...) do{ printf("CAMERA VM DEBUG: "); printf(__VA_ARGS__); }while(0)
#else
#define DHELL(...) do{}while(0)
#endif


static int verify_packet(vchan_packet_t *pak) {
    for(int i = 0; i < 4; i++) {
        if(pak->datah[i] != i + pak->pnum) {
            /* Malformed data */
            return 0;
        }
    }
    return 1;
}

static void rec_packet(libvchan_t * con) {
    size_t sz;
    char done = 1;
    int x, pnum;
    char comp[6];
    vchan_packet_t pak;

    libvchan_wait(con);
    sz = libvchan_read(con, &pnum, sizeof(int));
    assert(sz == sizeof(int));

    DHELL("camera_vm: number of packets to recieve = %d\n", pnum);

    for(x = 0; x < pnum; x++) {
	camera_vm__bbox_i bbox;

        libvchan_wait(con);
        /* Buffer sanity checking */
        assert(libvchan_data_ready(con) != 0);
        //assert(libvchan_buffer_space(con) == FILE_DATAPORT_MAX_SIZE);
        /* Perform read operation */
        sz = libvchan_read(con, &pak, sizeof(pak));
        /* See if the given packet is correct */
        assert(sz == sizeof(pak));
        assert(pak.guard == TEST_VCHAN_PAK_GUARD);
        //DHELL("camera_vm.packet %d|%d\n", x, sizeof(pak));

	bbox.left = pak.datah[0];
	bbox.right = pak.datah[1];
	bbox.top = pak.datah[2];
	bbox.bottom = pak.datah[3];

	// TODO: Fill in bbox
	if (camera_vm_bbox_out_write_camera_vm__bbox_i(&bbox)) {
	    printf("Wrote bbox\n");
	} else {
	    printf("Failed to write bbox\n");
	}
    }

    DHELL("camera_vm: sending ack\n");

    sz = libvchan_write(con, &done, sizeof(char));
    assert(sz == sizeof(char));
}


int run(void) {
    libvchan_t *connection;

    printf("vm_camera wrapper init\n");

    con.data_buf = (void *)share_mem;
    connection = libvchan_server_init(0, 25, 0, 0);
    if(connection != NULL)
        connection = link_vchan_comp(connection, &con);
    assert(connection != NULL);

    printf("vm_camera connection active\n");

    while(1) {
        printf("camera_vm.packet\n");
        rec_packet(connection);
    }
}
