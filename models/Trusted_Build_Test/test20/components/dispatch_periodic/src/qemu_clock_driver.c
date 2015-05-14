/*
 * The contents of this package are proprietary to National ICT Australia Limited
 * (NICTA), ABN 62 102 206 173 and you may not use, copy, modify, sublicense or
 * distribute the contents in any form except as permitted under the terms of a
 * separately executed license agreement with NICTA, such as (if applicable to
 * you) one of the following:
 *
 * 1. SMACCM Project License Agreement (Technical Area 4), by and between NICTA and Rockwell Collins, effective 29 January 2013.
 * 2. SMACCM Project Licence Agreement (Technical Area 4), by and between NICTA and Regents of the University of Minnesota, effective 5 April 2013.
 * 3. SMACCM Project Licence Agreement (Technical Area 3), by and between NICTA and Galois, Inc., effective 21 February 2013.
 */

/* @LICENCE("NICTA", "2013")@ */

#include <autoconf.h>
#include <stdio.h>
#include <clock_driver.h>
#include <dispatch_periodic.h>


#define KZM_EPIT_BASE_ADDR  (unsigned int)mem
#define KZM_EPIT_CTRL_ADDR  (KZM_EPIT_BASE_ADDR + 0x00)
#define KZM_EPIT_STAT_ADDR  (KZM_EPIT_BASE_ADDR + 0x04)
#define KZM_EPIT_LOAD_ADDR  (KZM_EPIT_BASE_ADDR + 0x08)
#define KZM_EPIT_COMP_ADDR  (KZM_EPIT_BASE_ADDR + 0x0C)
#define KZM_EPIT_CNT_ADDR   (KZM_EPIT_BASE_ADDR + 0x10)

#define REG_VAL(x)         *((volatile uint32_t *)(x))

#define CTRL_EN       (1 << 0)   /* EPIT enable */
#define CTRL_ENMOD    (1 << 1)   /* EPIT enable mode */
#define CTRL_OCIEN    (1 << 2)   /* EPIT interrupt enable */
#define CTRL_RLD      (1 << 3)   /* Counter reload control */
#define CTRL_SWR      (1 << 17)  /* Software reset */

#define CTRL_CLKSRC_SHIFT   (24)    /* Clock source */
#define CTRL_PRESCALE_SHIFT (4)     /* Prescalar */

//#define IPG_CLK_KHZ       (53200)     /* Clock frequency in KHz */
#define IPG_CLK_KHZ       (66000)     /* Clock frequency in KHz */
#define CLKSRC_IPG        (0x1)     /* IPG clock */
#define CLKSRC_IPG_HIGH   (0x2)     /* IPG clock high frequency */
#define CLKSRC_IPG_32K    (0x3)     /* IPG 32K clock */

void clock_init()
{
	REG_VAL(KZM_EPIT_CTRL_ADDR) = 0;

	/* Disable EPIT and reset. */
	REG_VAL(KZM_EPIT_CTRL_ADDR) = CTRL_SWR;

	/* Select Clock source */
	REG_VAL(KZM_EPIT_CTRL_ADDR) = (CLKSRC_IPG << CTRL_CLKSRC_SHIFT);

	/* Reload from load register */
	REG_VAL(KZM_EPIT_CTRL_ADDR) |= (CTRL_RLD | CTRL_ENMOD);

	/* Enable interrupt */
	REG_VAL(KZM_EPIT_CTRL_ADDR) |= CTRL_OCIEN;

}

/* Set interrupt interval, in milliseconds. */
void clock_set_interval(uint32_t interval)
{
	REG_VAL(KZM_EPIT_LOAD_ADDR) = (IPG_CLK_KHZ * interval) ;
	REG_VAL(KZM_EPIT_COMP_ADDR) = 0;
}

void clock_start_timer(void)
{
	REG_VAL(KZM_EPIT_STAT_ADDR) = 0x1;

	/* Enable timer */
	REG_VAL(KZM_EPIT_CTRL_ADDR) |= CTRL_EN;
}

void clock_irq_callback(void)
{
	/* Clear status bit. */
	REG_VAL(KZM_EPIT_STAT_ADDR) = 0x1;
}
