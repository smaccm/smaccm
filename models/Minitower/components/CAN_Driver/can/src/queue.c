/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

/* TX/RX message queue, using circular buffer. */

#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include <sync/spinlock.h>
#include <can_inf.h>

struct mq_cb {
	struct can_frame *frames;
	int head;
	int tail;
	int size;
	sync_spinlock_t lock;
};

static struct mq_cb *txq, *rxq;

/* Check if the queue is full. */
static int mq_is_full(struct mq_cb *cb)
{
	return (cb->tail + 1) % cb->size == cb->head;
}

/* Check if the queue is empty. */
static int mq_is_empty(struct mq_cb *cb)
{
	return cb->tail == cb->head;
}

/* Add message to the end of the queue. */
static void mq_append_msg(struct mq_cb *cb, struct can_frame *frame)
{
	memcpy(&cb->frames[cb->tail], frame, sizeof(struct can_frame));
	cb->tail = (cb->tail + 1) % cb->size;
}

/* Retrieve message from the start of the queue. */
static void mq_remove_msg(struct mq_cb *cb, struct can_frame *frame)
{
	memcpy(frame, &cb->frames[cb->head], sizeof(struct can_frame));
	cb->head = (cb->head + 1) % cb->size;
}

/**
 * Initialize TX and RX message queues.
 *
 * @size: Size of the queue, applies to both TX and RX.
 */
void mq_init(int size)
{
	/* Allocate memory for both TX and RX queues at once. */
	txq = (struct mq_cb*)malloc(sizeof(struct mq_cb) * 2);
	if (!txq) {
		printf("Not enough memory!\n");
		return;
	}
	rxq = txq + 1;

	txq->frames = (struct can_frame*)malloc(sizeof(struct can_frame) * size * 2);
	if (!txq->frames) {
		printf("Not enough memory!\n");
		free(txq);
		return;
	}
	rxq->frames = txq->frames + size;

	/* Initialize queues. */
	txq->head = 0;
	txq->tail = 0;
	txq->size = size;
	sync_spinlock_init(&txq->lock);

	rxq->head = 0;
	rxq->tail = 0;
	rxq->size = size;
	sync_spinlock_init(&rxq->lock);
}

/**
 * Add CAN message to TX queue.
 *
 * @frame: CAN message about to add.
 *
 * @return: On success, return 0, -1 otherwise.
 *
 * NOTE: Popping from the queue will block this function.
 */
int tx_queue_push(struct can_frame *frame)
{
	int ret = -1;

	if (!mq_is_full(txq)) {
		sync_spinlock_lock(&txq->lock);
		mq_append_msg(txq, frame);
		sync_spinlock_unlock(&txq->lock);
		ret = 0;
	}

	return ret;
}

/**
 * Retrieve CAN message from TX queue.
 *
 * @frame: CAN message about to fill-in .
 *
 * @return: On success, return frame address.
 *          Return NULL if the queue is empty.
 *
 * NOTE: Pushing to the queue will block this function.
 *       This function may block the IRQ handler. 
 */
struct can_frame *tx_queue_pop(struct can_frame *frame)
{
	struct can_frame *frm = NULL;

	if (!mq_is_empty(txq)) {
		sync_spinlock_lock(&txq->lock);
		mq_remove_msg(txq, frame);
		sync_spinlock_unlock(&txq->lock);
		frm = frame;
	}

	return frm;
}

/**
 * Add CAN message to RX queue.
 *
 * @frame: CAN message about to add.
 *
 * @return: On success, return 0, -1 otherwise.
 *
 * NOTE: Popping from the queue will block this function.
 *       This function may block the IRQ handler. 
 */
int rx_queue_push(struct can_frame *frame)
{
	int ret = -1;

	if (!mq_is_full(rxq)) {
		sync_spinlock_lock(&rxq->lock);
		mq_append_msg(rxq, frame);
		sync_spinlock_unlock(&rxq->lock);
		ret = 0;
	}

	return ret;
}

/**
 * Retrieve CAN message from RX queue.
 *
 * @frame: CAN message about to fill-in .
 *
 * @return: On success, return frame address.
 *          Return NULL if the queue is empty.
 *
 * NOTE: Pushing to the queue will block this function.
 */
struct can_frame *rx_queue_pop(struct can_frame *frame)
{
	struct can_frame *frm = NULL;

	if (!mq_is_empty(rxq)) {
		sync_spinlock_lock(&rxq->lock);
		mq_remove_msg(rxq, frame);
		sync_spinlock_unlock(&rxq->lock);
		frm = frame;
	}

	return frm;
}

