#ifndef __Clock_Driver__H
#define __Clock_Driver__H

#include <stdint.h>

void clock_init();
void clock_set_interval_in_ms(uint32_t interval);
void clock_set_interval_in_us(uint32_t interval);
void clock_start_timer(void);
void clock_irq_callback(void);
uint64_t clock_get_time();


#endif
