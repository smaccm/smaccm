/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

#include <stdint.h>
#include <platsupport/clock.h>

#include <clk.h>

clock_sys_t clock_sys;

unsigned int clktree_get_spi1_freq(void){
    clk_t* clk;
    clk = clk_get_clock(&clock_sys, CLK_SPI1);
    return clk_get_freq(clk);
}


unsigned int clktree_set_spi1_freq(unsigned int rate){
    clk_t* clk;
    clk = clk_get_clock(&clock_sys, CLK_SPI1);
    return clk_set_freq(clk, rate);
}


void clktree__init(void){
    int err;
    err = exynos5_clock_sys_init(cmu_cpu_clk,
                                 cmu_core_clk,
                                 NULL,
                                 NULL,
                                 cmu_top_clk,
                                 NULL,
                                 NULL,
                                 NULL,
                                 NULL,
				 NULL,
                                 &clock_sys);
    assert(!err);
    if(err){
        printf("Failed to initialise clock tree\n");
    }
}


