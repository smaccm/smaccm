/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

/*
 * Timer interface implementation.
 */

#include <stdio.h>
#include <platsupport/timer.h>

#include <timer.h>

extern pstimer_t *timer_drv;

/*
 * Get current time.
 *
 * TODO: platsupport returns a 64-bit time.
 */
int tm_get_time()
{
	return timer_get_time(timer_drv);
}
