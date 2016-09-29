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

#include <camkes/dataport.h>

#include "smaccm_Virtual_Machine.h"
#include "Virtual_Machine.h"

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

static libvchan_t *connection;

// #define DEBUG_SMACCM_VM

#ifdef DEBUG_SMACCM_VM
#define DVM(...) do{ printf("SMACCM VM DEBUG: "); printf(__VA_ARGS__); }while(0)
#else
#define DVM(...) do{}while(0)
#endif

/* Currently only one vchan per component is supported, so we have to
   pick between camera data from the VM or logging CAN data to the VM.
   Defining this flag enables the logging of CAN data to the VM. */
#define VM_LOGGING


void pre_init(void) {
    DVM("virtual machine wrapper pre_init\n");

    con.data_buf = (void *)share_mem;
    connection = libvchan_server_init(0, 25, 0, 0);
    if (connection != NULL) {
        connection = link_vchan_comp(connection, &con);
    }
    assert(connection != NULL);

    DVM("virtual machine connection active\n");
}    

static void rec_packet() {
    char done = 1;
    int data[4];
    SMACCM_DATA__Camera_Bounding_Box_i bbox;

    libvchan_wait(connection);
    int readSize = libvchan_recv(connection, data, 4*sizeof(int));
    assert(readSize == 4*sizeof(int));
    DVM("received bounding box packet\n");

    bbox.left = data[0];
    bbox.right = data[1];
    bbox.top = data[2];
    bbox.bottom = data[3];

    if (Virtual_Machine_self2server_write_SMACCM_DATA__Camera_Bounding_Box_i(&bbox)) {
        DVM("wrote bounding box\n");
    } else {
        DVM("failed to write bouding box\n");
    }

    DVM("Virtual_Machine: sending ack\n");
    libvchan_send(connection, &done, sizeof(char));
}

#ifdef VM_LOGGING
static bool fresh_pending_gidl;
uint8_t pending_gidl[sizeof(SMACCM_DATA__GIDL)];
#endif

int run(void) {
#ifndef VM_LOGGING
    while(1) {
        DVM("virtual machine packet\n");
        rec_packet();
    }
#else
    while (1) {
	framing2self_sem_wait();

	bool local_fresh_pending_gidl = false;
	uint8_t local_pending_gidl[sizeof(SMACCM_DATA__GIDL)];
	framing2self_mut_lock();
	if (fresh_pending_gidl) {
	    local_fresh_pending_gidl = fresh_pending_gidl;
	    fresh_pending_gidl = false;
	    memcpy(local_pending_gidl, pending_gidl, sizeof(SMACCM_DATA__GIDL));
	}
	framing2self_mut_unlock();

	if (local_fresh_pending_gidl) {
	    DVM("Sending GIDL over vchan\n");
	    int res = libvchan_send(connection, &local_pending_gidl, sizeof(SMACCM_DATA__GIDL));
	    if (res < 0) {
		printf("Error: failed send on vchan\n");
	    } else if (res < sizeof(SMACCM_DATA__GIDL)) {
		printf("Error: partial send on vchan (%d bytes)", res);
	    } else {
		DVM("GIDL send successful\n");
	    }
	}
    }
	    
#endif
}

#ifdef VM_LOGGING
bool framing2self_write_SMACCM_DATA__GIDL(const smaccm_SMACCM_DATA__GIDL_container *gidlc) {
    framing2self_mut_lock();
    memcpy(pending_gidl, gidlc, sizeof(SMACCM_DATA__GIDL));
    fresh_pending_gidl = true;
    framing2self_mut_unlock();
    framing2self_sem_post();
    return true;
}
#else
bool framing2self_write_SMACCM_DATA__GIDL(const smaccm_SMACCM_DATA__GIDL_container *gidlc) {
    DVM("VM_LOGGING disabled\n");
    return true;
}
#endif

bool server2self_reboot_write_bool(const bool *arg) {
    printf("Reboot requested\n");
    restart_vm_emit();
    return true;
}
