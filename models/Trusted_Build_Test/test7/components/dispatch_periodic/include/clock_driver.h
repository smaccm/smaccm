#ifndef __Clock_Driver__H
#define __Clock_Driver__H

#include <stdint.h>

void epit_init();
void epit_set_interval(uint32_t interval);
void epit_start_timer(void);
void epit_irq_callback(void);


#endif