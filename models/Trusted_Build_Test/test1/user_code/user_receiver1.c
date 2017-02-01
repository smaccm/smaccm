#include <smaccm_receiver.h>
#include <stdio.h>
#ifdef __TB_OS_CAMKES__
	#include <receiver.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif


void ping_received1(const uint32_t *the_time) {
   #ifdef __TB_OS_ECHRONOS__
		debug_print("receiver: periodic dispatch received at time ");
		debug_printhex32((uint32_t)*the_time);
		debug_println("  ");
		debug_println("receiver1 dispatching to receiver2...\n");
   #else
	   printf("receiver: periodic dispatch received at time: %d\n", (int32_t)(*the_time));
	   printf("receiver1 dispatching to receiver2...\n");
   #endif   
   uint32_t output = *the_time + 1; 
   ping_Output1(&output);
}
