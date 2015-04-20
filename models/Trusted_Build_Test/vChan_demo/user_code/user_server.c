#include <smaccm_server.h>
#include <server.h>

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

#include <sel4/sel4.h>
#include <sel4utils/util.h>

#include "vmm/vchan_component.h"
#include "vmm/vchan_copy.h"
#include "vmm/vchan_sharemem.h"

#include "vchan-shim.h"

#define DPRINTF(num, ...) printf(__VA_ARGS__);
#define DBG_SERVER 4

#define VCHAN_CLIENT_DOM 50
#define VCHAN_SERVER_DOM 0
#define VCHAN_PORT 25

#define CORE_SIZE 4096
static char core_buf[CORE_SIZE];
extern char *morecore_area;
extern size_t morecore_size;

static char char_buf[256];

static camkes_vchan_con_t con = {
    .connect = &vchan_con_new_connection,
    .disconnect = &vchan_con_rem_connection,
    .get_buf = &vchan_con_get_buf,
    .status = &vchan_con_status,

    .alert = &vchan_con_ping,
    .wait = &vevent_wait,
    .poll = &vevent_poll,

    .component_dom_num = VCHAN_CLIENT_DOM,
};

void pre_init(void) {
    con.data_buf = (void *) share_mem;
    morecore_area = core_buf;
    morecore_size = CORE_SIZE;
    init_camkes_vchan(&con);
}


// static data to be compatible with AADL dispatch model
static libvchan_t *connection;
static int x = 0;
static vchan_packet_t pak;


/* No blocking means that we need a state machine for the callback. */
typedef enum {config_init,
		config_hello,
		config_string,
		config_receive
} Mode_Type;

Mode_Type mode = config_init;
size_t sz, len;
vchan_header_t head;

static void do_hello_action() {
    /*
	DPRINTF(DBG_SERVER,"hello: waiting to read hello \n");
    libvchan_wait(connection);
	*/
	// DPRINTF(DBG_SERVER,"hello: waiting to read hello \n");
	// DPRINTF(DBG_SERVER,"hello: non-blocking reads don't work, so checking size... \n");

	// expected read.
	sz = libvchan_data_ready(connection);
	// printf("Data available: %d", sz);

	if (sz >= sizeof(head)) {
		DPRINTF(DBG_SERVER,"hello: reading \n");
		sz = libvchan_read(connection, &head, sizeof(head));
		assert(sz == sizeof(head));
		assert(head.msg_type == MSG_HELLO);

		head.msg_type = MSG_ACK;
		len = head.len;

		DPRINTF(DBG_SERVER,"hello: sending hello msg ack\n");

		/* Send off ack */
		sz = libvchan_write(connection, &head, sizeof(head));
		assert(sz == sizeof(head));

		/* change modes */
		mode = config_string;
	} else {
		// DPRINTF(DBG_SERVER, "reading size available = %d\n", sz);
		// DPRINTF(DBG_SERVER,"hello: still waiting for 'hello' \n");
	}
}

static void do_string_action() {
    /* Wait for hello */
    /* Read data */
    // DPRINTF(DBG_SERVER,"hello: waiting to read string \n");
	// DPRINTF(DBG_SERVER,"hello: non-blocking reads don't work, so checking size... \n");
	sz = libvchan_data_ready(connection);
	if (sz >= len) {
		sz = libvchan_read(connection, &char_buf, len);
		assert(sz == len);

		DPRINTF(DBG_SERVER,"hello: got string: %s\n", char_buf);
		mode = config_receive;
	} else {
		// DPRINTF(DBG_SERVER,"hello: still waiting for string \n");
	}
}

static void do_receive_action() {

	// DPRINTF(DBG_SERVER, "Server receive (tentative) \n\n");
    char comp[6];
    sprintf(comp, "I%d", x);
	sz = libvchan_data_ready(connection);
	if (sz >= sizeof(pak)) {
		sz = libvchan_read(connection, &pak, sizeof(pak));
		assert(sz == sizeof(pak));
		assert(strcmp(comp, pak.pnum) == 0);
		x++;
		if (x == NUM_PACKETS) {
			DPRINTF(DBG_SERVER, "Hello: All %d packets received with no errors.  No more packets expected\n\n", x);
		}
	} else {
		// DPRINTF(DBG_SERVER,"hello: still waiting for receive \n");
	}
}

// TODO MWW: Is the argument just whatever you want the callback function
// to pass you back?
static void receive_callback(void *UNKNOWN) {
	switch (mode) {
	case config_hello : do_hello_action() ; break;
	case config_string : do_string_action() ; break ;
	case config_receive : do_receive_action() ; break ;
	default : DPRINTF(DBG_SERVER, "Whoa!  Unexpected mode!");
	}

}

// This needs to change to be compatible with AADL dispatcher model
/* static void rec_packet(libvchan_t * con) {
    size_t sz;
    int x;
    char comp[6];
    vchan_packet_t pak;
    for(x = 0; x < NUM_PACKETS; x++) {
        sprintf(comp, "I%d", x);
        libvchan_wait(con);
        sz = libvchan_read(con, &pak, sizeof(pak));
        assert(sz == sizeof(pak));
        assert(strcmp(comp, pak.pnum) == 0);
    }
}
*/



void init_vchan() {
	DPRINTF(DBG_SERVER,"hello: init\n");

    connection = libvchan_server_init(VCHAN_SERVER_DOM, VCHAN_PORT, 0, 0);
    assert(connection != NULL);

    DPRINTF(DBG_SERVER,"hello: Connection Active\n");

    DPRINTF(DBG_SERVER,"hello: waiting for hello message\n");

    mode = config_hello ;

    DPRINTF(DBG_SERVER,"hello: Registering Callback \n");

    // Need to register callback function HERE
    // TODO MWW: Is the argument just whatever you want the callback function
    // to pass you back?
    int reg_ok = vevent_reg_callback(receive_callback, connection);
    assert(reg_ok == 0);

}
