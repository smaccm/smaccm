/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

/* standard */
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#include "utils.h"

#include "canbus.h"

/* application common */
#include "can_inf.h"
#include "spi_inf.h"
#include "common.h"

void txb0_ack_callback(void *arg)
{
	txb0_ack_reg_callback(txb0_ack_callback, NULL);
	printf("%s\n", __func__);
}

void txb1_ack_callback(void *arg)
{
	txb1_ack_reg_callback(txb1_ack_callback, NULL);
	printf("%s\n", __func__);
}

void txb2_ack_callback(void *arg)
{
	txb2_ack_reg_callback(txb2_ack_callback, NULL);
	printf("%s\n", __func__);
}

int run(void)
{
	struct can_frame tx, rx;
	int error = 0;

	error = txb0_ack_reg_callback(txb0_ack_callback, NULL);
	error = txb1_ack_reg_callback(txb1_ack_callback, NULL);
	error = txb2_ack_reg_callback(txb2_ack_callback, NULL);

	/* Initialize CAN controller. */
	printf("Start CAN Loop-back Test\n");
	can_setup(125000);

//	can_id.id = 0xF;
//	can_set_filter(can_id, 0xF);

	/* Prepare CAN frame. */
	tx.ident.id = 0x123;
	tx.ident.exide = 0;
	tx.ident.rtr = 0;
	tx.dlc = 8;
	tx.data[0] = 0x08;
	tx.data[1] = 0x07;
	tx.data[2] = 0x06;
	tx.data[3] = 0x05;
	tx.data[4] = 0x04;
	tx.data[5] = 0x03;
	tx.data[6] = 0x02;
	tx.data[7] = 0x01;

	while (1) {
		/* Send message */
//		can_send(tx);
		tx.ident.id = 0x300;
		error = can_sendto(0, tx);
		if (error) {
			can_abort(0);
			printf("Send error: 0\n");
		}

		tx.ident.id = 0x100;
		error = can_sendto(1, tx);
		if (error) {
			can_abort(1);
			printf("Send error: 1\n");
		}

		tx.ident.id = 0x200;
		error = can_sendto(2, tx);
		if (error) {
			can_abort(2);
			printf("Send error: 2\n");
		}
//		printf("Send: error(%d), id(%x), data(%x, %x, %x, %x, %x, %x, %x, %x)\n",
//			error, tx.ident.id,
//			tx.data[0], tx.data[1], tx.data[2], tx.data[3],
//			tx.data[4], tx.data[5], tx.data[6], tx.data[7]);
//
//		tx.ident.id++;

		/* Receive message */
//		can_recv(&rx);
//		printf("Recv: error(%d), id(%x), data(%x, %x, %x, %x, %x, %x, %x, %x)\n",
//			error, rx.ident.id,
//			rx.data[0], rx.data[1], rx.data[2], rx.data[3],
//			rx.data[4], rx.data[5], rx.data[6], rx.data[7]);
	}

	return 0;
}

