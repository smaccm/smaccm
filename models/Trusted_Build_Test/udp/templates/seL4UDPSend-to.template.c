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
#include <camkes/dataport.h>
#include <lwip/udp.h>
#include <sync/sem-bare.h>
#include <string.h>

/*- set ep = alloc('ep', seL4_EndpointObject, read=True, write=True) -*/

/* assume a dataport symbols exists */
extern void */*? me.to_interface.name?*/_buf;

/*- set ports = configuration[me.to_instance.name].get('%s_ports' % me.to_interface.name) -*/

void lwip_lock();
void lwip_unlock();

static struct udp_pcb *upcb;

void /*? me.to_interface.name ?*/__run(void) {
    while(1) {
        /*- set cnode = alloc_cap('cnode', my_cnode, write=True) -*/
        /*- set reply_cap_slot = alloc_cap('reply_cap_slot', None) -*/
        int result UNUSED;
        unsigned int len;
        ip_addr_t addr;
        struct pbuf *p;
        seL4_Wait(/*? ep ?*/, NULL);
        result = seL4_CNode_SaveCaller(/*? cnode ?*/, /*? reply_cap_slot ?*/, 32);
        assert(result == seL4_NoError);

        len = seL4_GetMR(0);
        if (len < 4096) {
            addr.addr = seL4_GetMR(1);
            lwip_lock();
            p = pbuf_alloc(PBUF_TRANSPORT, len, PBUF_RAM);
            if (p) {
                memcpy(p->payload, /*? me.to_interface.name?*/_buf, len);
                udp_sendto(upcb, p, &addr, /*? ports['dest'] ?*/);
                pbuf_free(p);
            }
            lwip_unlock();
        }
        seL4_Send(/*? reply_cap_slot ?*/, seL4_MessageInfo_new(0, 0, 0, 0));
    }
}

void /*? me.to_interface.name ?*/__init(void) {
    lwip_lock();
    upcb = udp_new();
    assert(upcb);
    /* we cheat here and set a local port without using the actual lwip bind function.
     * this is because we want to persuade lwip to send packets with this as the from
     * port, but we don't actually want to receive packets here */
    upcb->local_port = /*? ports['source'] ?*/;
    lwip_unlock();
}
