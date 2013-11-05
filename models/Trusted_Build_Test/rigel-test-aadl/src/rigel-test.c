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

void systick(void)
{
   systick_signal_write_chEmitter();
}

Rigel_Test_AADL__dt_rec A_data;

void
init_A(void) 
{ 
    A_data.field = 0; 
}

void 
exec_signalCh_threadA(void) 
{
    if (A_data.field < 10) 
    {
        A_data.field ++ ; 
        debug_println("task a");
        thread_a_write_foo_data(&A_data); 
    }
    else 
    {
        debug_println("A now (still) is waiting for ticks");
    }
    debug_println("task a complete (will yield)");
}


uint8_t B_count ; 
 
void init_B(void) 
{ 
    B_count = 0; 
}

void
exec_signalCh_threadB(void)
{
    debug_println("task b");
    debug_println("tock!");
}

void 
exec_B_ASignal(void)
{
    debug_println("task b");
    debug_println("signal from A.");
}


