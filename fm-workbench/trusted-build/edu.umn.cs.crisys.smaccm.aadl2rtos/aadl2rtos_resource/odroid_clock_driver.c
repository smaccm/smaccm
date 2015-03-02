/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include <stdio.h>

#include <platsupport/mach/pwm.h>
#include <platsupport/timer.h>

#include <clock_driver.h>
#include <dispatch_periodic.h>


#define NS_IN_SECOND 1000000000

pstimer_t *timer_drv = NULL;


void clock_init()
{
	pwm_config_t config;

	/*
	 * Provide hardware info to platsupport.
	 */
	config.vaddr = (void*)mem;

	timer_drv = pwm_get_timer(&config);
	if (!timer_drv) {
		printf("PWM timer does not exist.\n");
	}
}

/* Set interrupt interval, in milliseconds. */
void clock_set_interval(uint32_t interval)
{
	timer_periodic(timer_drv, ((uint64_t)interval)*NS_IN_MS);
}

void clock_start_timer(void) {
	timer_start(timer_drv);
}

void clock_irq_callback() {
	/* Hardware routine. */
	timer_handle_irq(timer_drv, PWM_T4_INTERRUPT);

	/* Signal other components (currently unimplemented) */
	/* timer_update_emit(); */
}