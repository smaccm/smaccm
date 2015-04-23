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
	return 0;
}

