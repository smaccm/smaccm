/*
Unpublished copyright (c) 2013 National ICT Australia (NICTA),
ABN 62 102 206 173.  All rights reserved.

The contents of this document are proprietary to NICTA and you may not
use, copy, modify, sublicense or distribute the contents in any form
except as permitted under the terms of a separately executed licence
agreement with NICTA.

*/
#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>

#include "rtos-kochab.h"
#include "Test_Impl_Instance.h"


extern void debug_println(const char *msg);
extern void debug_printhex32(uint32_t val);
void smaccm_initialize_px4_systick_interrupt();

test__dt_rec A_data;

void
init_A(void) 
{ 
    A_data.field = 0; 
}

void 
exec_periodic_thread_A(void) 
{
    debug_println("Executing A (periodic)");
    if (A_data.field < 10) 
    {
        A_data.field ++ ; 
        thread_a_write_foo_data(&A_data); 
    }
    else 
    {
    }
    debug_println("task A complete");
}


uint8_t B_count ; 
test__dt_rec B_data;
 
void init_B(void) 
{ 
    B_count = 0; 
    B_data.field = 0;
}

void
exec_periodic_thread_B(void)
{
    debug_println("Executing B (periodic)");
	thread_b_read_datap(&B_data); 
	debug_println("Value of B_data after read:"); 
	debug_printhex32(B_data.field); 
	debug_println("task B complete");
}


void main() {
   debug_println("Starting system!");
   smaccm_initialize_px4_systick_interrupt();
   rtos_start();
}
