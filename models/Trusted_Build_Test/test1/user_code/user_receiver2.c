#include <smaccm_receiver2.h>
#include <stdio.h>
#ifdef __TB_OS_CAMKES__
	#include <receiver2.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif


void ping_received2(const uint32_t *periodic_100_ms) {
   #ifdef __TB_OS_ECHRONOS__
		debug_print("receiver2 ping received ");
		debug_printhex32((uint32_t)*the_time);
		debug_println("  ");
   #else
       printf("receiver2 ping received (%d)\n", *periodic_100_ms);
   #endif
}
