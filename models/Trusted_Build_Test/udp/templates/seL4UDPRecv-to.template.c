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

/* Assume a notification exists */
void /*? me.to_interface.name ?*/_ready_emit(void);

/* Assume a dataport exists */
extern void */*? me.to_interface.name?*/_buf;

void lwip_lock();
void lwip_unlock();

/*- set bufs = configuration[me.to_instance.name].get('%s_buffers' % me.to_interface.name) -*/
/*- set port = configuration[me.to_instance.name].get('%s_port' % me.to_interface.name) -*/

typedef struct udp_message {
    struct pbuf *pbuf;
    ip_addr_t addr;
    uint16_t port;
    struct udp_message *next;
}udp_message_t;

static struct udp_pcb *upcb = NULL;
static udp_message_t message_memory[/*? bufs ?*/] = {
    /*- for i in range(bufs) -*/
        /*- if i == 0 -*/
            {.pbuf = NULL, .port = 0, .next = NULL},
        /*- else -*/
            {.pbuf = NULL, .port = 0, .next = &message_memory[/*? i - 1 ?*/]},
        /*- endif -*/
    /*- endfor -*/
    };
static udp_message_t *free_head = &message_memory[/*? bufs - 1 ?*/];
static udp_message_t *used_head = NULL;

static int need_signal = 1;

static void udprecv(void *arg, struct udp_pcb *pcb, struct pbuf *p, ip_addr_t *addr, u16_t port) {
    lwip_lock();
    if (!free_head) {
        pbuf_free(p);
        lwip_unlock();
        return;
    }
    udp_message_t *m = free_head;
    free_head = free_head->next;

    m->pbuf = p;
    m->addr = *addr;
    m->port = port;

    if (need_signal) {
        /*? me.to_interface.name ?*/_ready_emit();
        need_signal = 0;
    }

    m->next = used_head;
    used_head = m;
    lwip_unlock();
}

void /*? me.to_interface.name ?*/__run(void) {
    while (1) {
        /*- set cnode = alloc_cap('cnode', my_cnode, write=True) -*/
        /*- set reply_cap_slot = alloc_cap('reply_cap_slot', None) -*/
        int len;
        int result UNUSED;
        seL4_Wait(/*? ep ?*/, NULL);
        result = seL4_CNode_SaveCaller(/*? cnode ?*/, /*? reply_cap_slot ?*/, 32);
        assert(result == seL4_NoError);
        lwip_lock();
        len = 0;
        if (!used_head) {
            seL4_SetMR(0, -1);
            len = 1;
            need_signal = 1;
        } else {
            unsigned int packet_len = 0;
            void *p = /*? me.to_interface.name?*/_buf;
            udp_message_t *m = used_head;
            used_head = used_head->next;
            if (!used_head) {
                need_signal = 1;
            }

            for (struct pbuf *q = m->pbuf; q; q = q->next) {
                memcpy(p + packet_len, q->payload, q->len);
                packet_len += q->len;
            }
            pbuf_free(m->pbuf);
            seL4_SetMR(0, used_head ? 0 : 1);
            seL4_SetMR(1, packet_len);
            seL4_SetMR(2, m->port);
            seL4_SetMR(3, m->addr.addr);
            len = 4;

            m->next = free_head;
            free_head = m;
        }
        seL4_Send(/*? reply_cap_slot ?*/, seL4_MessageInfo_new(0, 0, 0, len));
        lwip_unlock();
    }
}

void /*? me.to_interface.name ?*/__init(void) {
    int err;
    lwip_lock();
    upcb = udp_new();
    assert(upcb);
    udp_recv(upcb, udprecv, NULL);
    err = udp_bind(upcb, NULL, /*? port ?*/);
    assert(!err);
    lwip_unlock();
}
