#include "tb_receiver2.h"
#include <stdio.h>
#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif

void ping_received2(const test6__a_array_impl * test_data) {
   #ifdef __TB_OS_ECHRONOS
      debug_printf("receiver2: ping received (");
      debug_printhex32(test_data[0]);
      debug_printf(", ");   
      debug_printhex32(test_data[1]);
      debug_printf(", ");   
      debug_printhex32(test_data[2]);
      debug_printf(", ");   
      debug_printhex32(test_data[3]);
      debug_printf(", ");   
      debug_println(")");
   #else
      printf("receiver2: ping received (%d, %d, %d, %d)\n", (*test_data)[0], (*test_data)[1], (*test_data)[2], (*test_data)[3]);
   #endif
}

void periodic_ping2(const int64_t *the_time) {
	#ifdef __TB_OS_VXWORKS__
		// VxWorks doesn't support long longs for printing.
		printf("receiver2: periodic dispatch received at time: %d\n", (int32_t)(*the_time));
	#elif __TB_OS_ECHRONOS__
		debug_print("receiver2: periodic dispatch received at time ");
		debug_printhex32((uint32_t)*the_time);
		debug_println("  ");
	#else
		printf("receiver2: periodic dispatch received at time: %d \n", (int32_t)*the_time);
	#endif	
}
