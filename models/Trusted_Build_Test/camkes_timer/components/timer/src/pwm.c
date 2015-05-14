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

#include <timer.h>

#define NS_IN_SECOND 1000000000

pstimer_t *timer_drv = NULL;

void pwm_irq_callback(void *_ UNUSED)
{
	/* Hardware routine. */
	timer_handle_irq(timer_drv, PWM_T4_INTERRUPT);

	/* Signal other components. */
	timer_update_emit();

        printf("Received timer callback.\n");
	irq_reg_callback(pwm_irq_callback, NULL);
}

void pre_init()
{
	pwm_config_t config;

	/*
	 * Provide hardware info to platsupport.
	 */
	config.vaddr = (void*)timerbase;

	timer_drv = pwm_get_timer(&config);
	if (!timer_drv) {
		printf("PWM timer does not exist.\n");
	}

	irq_reg_callback(pwm_irq_callback, NULL);

	/* Run in periodic mode and start the timer. */
	timer_periodic(timer_drv, NS_IN_SECOND);
	timer_start(timer_drv);
}

