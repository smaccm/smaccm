/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

/* TX/RX Massage Queue Functions */
#ifndef  QUEUE_H
#define  QUEUE_H

#include <can_inf.h>

void mq_init(int size);
int tx_queue_push(struct can_frame *frame);
struct can_frame *tx_queue_pop(struct can_frame *frame);
int rx_queue_push(struct can_frame *frame);
struct can_frame *rx_queue_pop(struct can_frame *frame);

#endif  /*QUEUE_H*/
