/*
 * Copyright 2015, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

/*
 * Work out the frame priority based on CAN ID.
 */

#include <stdlib.h>
#include <string.h>

#include <can_inf.h>
#include <mcp2515.h>

struct canid_cache {
	uint32_t canid:29;
	uint32_t exide:1;
	uint32_t priority:2;
};

static struct canid_cache txb_cache[3];

void canid_clear_cache(int txb_idx)
{
	memset(&txb_cache[txb_idx], 0, sizeof(struct canid_cache));
}

static int canid_cache_is_valid(int txb_idx)
{
	return txb_cache[txb_idx].canid ? 1 : 0;
}

int get_frame_priority(int txb_idx, struct can_id *id)
{
	/* Check if there is a message pending. */
	if (canid_cache_is_valid(txb_idx)) {
		return -1;
	}

	/* Put the frame into the cache with the highest priority. */
	txb_cache[txb_idx].canid = id->id;
	txb_cache[txb_idx].exide = id->exide;

	for (int i = 0; i < 3; i++) {
		txb_cache[i].priority = 0;
	}

	for (int i = 0; i < 3; i++) {
		for (int j = i + 1; j < 3; j++) {
			if (txb_cache[i].canid < txb_cache[j].canid) {
				txb_cache[i].priority++;
			} else if (txb_cache[i].canid > txb_cache[j].canid) {
				txb_cache[j].priority++;
			}
		}
	}

	for (int i = 0; i < 3; i++) {
		if (canid_cache_is_valid(i) && i != txb_idx) {
			mcp2515_bit_modify(TXBCTRL(i), TXBCTRL_TXP_MASK, txb_cache[i].priority);
		}
	}

	return txb_cache[txb_idx].priority;
}

