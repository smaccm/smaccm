/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include "includes/sel4libvchan.h"
#include "includes/libvchan.h"
#include "includes/vmm_manager.h"
#include "includes/vchan_copy.h"

#include <sys/ioctl.h>
#include <string.h>
#include <fcntl.h>
#include <stdio.h>
#include <sys/mman.h>
#include <errno.h>
#include <stdlib.h>
#include <unistd.h>
#include <assert.h>
#include <sys/eventfd.h>
#include <time.h>
#include <fcntl.h>

static int libvchan_checkbuf(libsel4vchan_t *com, int checktype, int nowait);
libvchan_t *libvchan_new_instance(int domain, int port, size_t read_min, size_t write_min, int server);
int vchan_readwrite(libvchan_t *ctrl, const void *data, size_t size, int cmd, int stream);
static uint64_t u;

int fd_set_blocking(int fd, int blocking);

/**
 * Set a file descriptor to blocking or non-blocking mode.
 *
 * @param fd The file descriptor
 * @param blocking 0:non-blocking mode, 1:blocking mode
 * @return 1:success, 0:failure.
 **/
int fd_set_blocking(int fd, int blocking) {
    /* Save the current flags */
    int flags = fcntl(fd, F_GETFL, 0);
    if (flags == -1)
        return 0;

    if (blocking)
        flags &= ~O_NONBLOCK;
    else
        flags |= O_NONBLOCK;
    return fcntl(fd, F_SETFL, flags) != -1;
}



/**
* Set up a vchan, including granting pages
* @return structure, or NULL in case of an error
*/
libvchan_t *libvchan_server_init(int domain, int port, size_t read_min, size_t write_min) {

    libvchan_t *ret = libvchan_new_instance(domain, port, read_min, write_min, 1);
    if(ret == NULL) {
        printf("libvchan: server initialisation FAILED!\n");
    }
    return ret;

}

/**
* Connect to an existing vchan. Note: you can reconnect to an existing vchan
* safely, however no locking is performed, so you must prevent multiple clients
* from connecting to a single server.
*/
libvchan_t *libvchan_client_init(int domain, int port) {
    libvchan_t *ret =  libvchan_new_instance(domain, port, 0, 0, 0);
    if(ret == NULL) {
        printf("libvchan: client initialisation FAILED!\n");
    }

    return ret;
}

/**
* Waits for reads or writes to unblock, or for a close
*/
int libvchan_wait(libvchan_t *ctrl) {
    return libvchan_checkbuf(ctrl->sel4vchan, 0, 0);
}

 /** Amount of data ready to read, in bytes */
int libvchan_data_ready(libvchan_t *ctrl) {
    return libvchan_checkbuf(ctrl->sel4vchan, 1, NOWAIT_DATA_READY);
}

/** Amount of data it is possible to send without blocking */
int libvchan_buffer_space(libvchan_t *ctrl) {
    return libvchan_checkbuf(ctrl->sel4vchan, 1, NOWAIT_BUF_SPACE);
}

/**
* Packet-based receive: always reads exactly $size bytes.
* @param ctrl The vchan control structure
* @param data Buffer for data that was read
* @param size Size of the buffer and amount of data to read
* @return -1 on error, 0 if nonblocking and insufficient data is available, or $size
*/
int libvchan_recv(libvchan_t *ctrl, void *data, size_t size) {
    return vchan_readwrite(ctrl, data, size, VCHAN_RECV, 0);
}


/**
* Packet-based send: send entire buffer if possible
* @param ctrl The vchan control structure
* @param data Buffer for data to send
* @param size Size of the buffer and amount of data to send
* @return -1 on error, 0 if nonblocking and insufficient space is available, or $size
*/
int libvchan_send(libvchan_t *ctrl, const void *data, size_t size) {
    return vchan_readwrite(ctrl, data, size, VCHAN_SEND, 0);
}

/**
* Stream-based receive: reads as much data as possible.
* @param ctrl The vchan control structure
* @param data Buffer for data that was read
* @param size Size of the buffer
* @return -1 on error, otherwise the amount of data read (which may be zero if
* the vchan is nonblocking)
*/
int libvchan_read(libvchan_t *ctrl, void *data, size_t size) {
    return vchan_readwrite(ctrl, data, size, VCHAN_RECV, 1);
}


/**
* Stream-based send: send as much data as possible.
* @param ctrl The vchan control structure
* @param data Buffer for data to send
* @param size Size of the buffer
* @return -1 on error, otherwise the amount of data sent (which may be zero if
* the vchan is nonblocking)
*/
int libvchan_write(libvchan_t *ctrl, const void *data, size_t size) {
    return vchan_readwrite(ctrl, data, size, VCHAN_SEND, 1);
}

/**
* Query the state of the vchan shared page:
* return 0 when one side has called libxenvchan_close() or crashed
* return 1 when both sides are open
* return 2 [server only] when no client has yet connected
*/
int libvchan_is_open(libvchan_t *ctrl) {

    int res;
    libsel4vchan_t *com = ctrl->sel4vchan;
    vchan_check_args_t args = {
        .v.port     = com->port_num,
        .v.dest     = com->domain_num,
    };

    res = ioctl(com->driver_fd, VM_IOCTL_CMD(SEL4_VCHAN_STATE, vchan_check_args_t), &args);
    if(res < 0) {
        return -1;
    }

    return args.state;

}

/// returns nonzero if the peer has closed connection
int libvchan_is_eof(struct libvchan *ctrl) {
    /* Other side has closed connection */
    if(libvchan_is_open(ctrl) == 0) {
        return -1;
    }

    return 0;
}

/**
* Returns the event file descriptor for this vchan. When this FD is readable,
* libvchan() will not block, and the state of the vchan has changed since
* the last invocation of libvchan().
*/
int libvchan_fd_for_select(struct libvchan *ctrl) {
    return ctrl->sel4vchan->event_fd;
}


/*
    Perform a read or write
    @stream - if 1, don't mind if not all data is sent/recieved
              if 0, return an error if the data sent/recieved != size
    @size - Amount of data to read or write
    @cmd - Either VCHAN_READ or VCHAN_SEND
    @data - data source or destination
    return either size of data written or retrived, or error
*/
int vchan_readwrite(libvchan_t *ctrl, const void *data, size_t size, int cmd, int stream) {

    int res;
    libsel4vchan_t *com = ctrl->sel4vchan;

    vchan_args_t args = {
        .v.port = com->port_num,
        .v.dest = com->domain_num,
        .mmap_ptr = (void *)data,
        .size = size,
        .stream = stream,
        .mmap_phys_ptr = 0,
    };

    if(size == 0) {
        return size;
    }

    res = ioctl(com->driver_fd, VM_IOCTL_CMD(cmd, vchan_args_t), &args);

    if(cmd == VCHAN_RECV) {
        fd_set_blocking(com->event_fd, 0);
        eventfd_read(com->event_fd, &u);
        fd_set_blocking(com->event_fd, 1);
    }

    if(stream == 0 && args.size != res) {
        return -1;
    } else {
        return res;
    }

    return 0;
}

/*
     Check the state of the shared bufffer
     @nowait - if 0, block until data is recieved
             - if NOWAIT_DATA_READY, return how much data is in the buffer to read
             - if NOWAIT_BUF_SPACE, return how much data can be read into buffer
    return -1 on error, 1 if nowait and data is avaliable, or above
*/
static int libvchan_checkbuf(libsel4vchan_t *com, int nowait, int checktype) {
    int err;

    vchan_check_args_t args = {
        .v.dest = com->domain_num,
        .v.port = com->port_num,
        .nowait = nowait,
        .checktype = checktype,
        .state = -80,
    };

    err = ioctl(com->driver_fd, VM_IOCTL_CMD(SEL4_VCHAN_WAIT, vchan_check_args_t), &args);

    if(nowait == 0) {
        fd_set_blocking(com->event_fd, 0);
        eventfd_read(com->event_fd, &u);
        fd_set_blocking(com->event_fd, 1);
    }

    if (err < 0) {
        return err;
    }

    return args.state;
}


/*
    Create a new server/client instance
    @domain -  the other vm to connect to
    @port - the port number of this instance, this allows multiple connections to a given vm
    @read_min - ignored
    @write_min - ignored
    @server - 1 if this instance is the server, 0 if this instance is the client
    returns a new instance, or NULL if there was an error creating the instance
*/
libvchan_t *libvchan_new_instance(int domain, int port, size_t read_min, size_t write_min, int server) {

    int evfd, fd, res;
    evfd = eventfd(0, 0);
    if(evfd < 0) {
        return NULL;
    }

    vchan_connect_t args = {
        .v.dest = domain,
        .v.port = port,
        .server = server,
        .eventfd = evfd,
    };

    libvchan_t *v = malloc(sizeof(libvchan_t));
    if(v == NULL) {
        printf("libvchan: failed to malloc\n");
        return NULL;
    }

    /* Link up to sel4 driver interface */
    if ((fd = open("/dev/vmm_manager", O_RDWR)) < 0) {
        printf("libvchan: failed to open /dev/vmm_manager\n");
        return NULL;
    }

    /* Get the id of the vm this driver is running off */
    res = ioctl(fd, VM_IOCTL_CMD(SEL4_VCHAN_CONNECT, vchan_connect_t), &args);
    if (res < 0)
        return NULL;


    /* Fill out struct */
    libsel4vchan_t *ret_type = (libsel4vchan_t *) malloc(sizeof(libsel4vchan_t));
    if(ret_type == NULL) {
        printf("libvchan: failed to malloc\n");
        return NULL;
    }

    if(server) {
        ret_type->is_server = 1;
        ret_type->server_persists = 1;
    }

    ret_type->blocking = 1;
    ret_type->driver_fd = fd;
    ret_type->event_fd = evfd;
    ret_type->port_num = port;
    ret_type->domain_num = domain;

    v->sel4vchan = ret_type;
    return v;
}

/**
 * Notify the peer of closing.
 */
void libvchan_close(libvchan_t *ctrl) {

    libsel4vchan_t *com = ctrl->sel4vchan;

    vchan_connect_t args = {
        .v.dest         =   com->domain_num,
        .v.port         =   com->port_num,
        .server         =   com->is_server,
    };

    ioctl(com->driver_fd, VM_IOCTL_CMD(SEL4_VCHAN_CLOSE, vchan_connect_t), &args);
    close(com->driver_fd);
    close(com->event_fd);

    free(com);
    free(ctrl);

}
