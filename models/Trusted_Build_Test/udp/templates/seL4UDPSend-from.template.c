/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

#include <sel4/sel4.h>
#include <string.h>
#include <lwip/ip_addr.h>

/*? macros.show_includes(me.from_instance.type.includes) ?*/

/*- set ep = alloc('ep', seL4_EndpointObject, write=True, grant=True) -*/

/* assume a dataport symbols exists */
extern void */*? me.from_interface.name?*/_buf;

int /*? me.from_interface.name ?*/_send(void *p, unsigned int len, ip_addr_t addr) {
    seL4_SetMR(0, len);
    seL4_SetMR(1, addr.addr);
    if (len > 4096) {
        len = 4096;
    }
    memcpy(/*? me.from_interface.name?*/_buf, p, len);
    seL4_Call(/*? ep ?*/, seL4_MessageInfo_new(0, 0, 0, 2));
    return len;
}
