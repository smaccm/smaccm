#include <aadl_sender.h>
#ifdef __TB_OS_CAMKES__
	#include <sender.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif

#include <string.h>
#include <stdio.h>


void periodic_ping(const int64_t *the_time) {

	#ifdef __TB_OS_VXWORKS__
		// VxWorks doesn't support long longs for printing.
		printf("sender: periodic dispatch received at time: %d.  Writing to receiver. \n", (int32_t)(*the_time));
	#elif __TB_OS_ECHRONOS__
		debug_print("sender: periodic dispatch received at time ");
		debug_printhex32((uint32_t)*the_time);
		debug_println(".  Writing to receiver.");
	#elif __TB_OS_LINUX__
		printf("sender: periodic dispatch received at time: %" PRIn64 ".  Writing to receiver. \n", *the_time);
	#else
		printf("sender: periodic dispatch received at time: %lld.  Writing to receiver. \n", *the_time);
	#endif	
	
   uint32_t to_send = ((uint32_t)*the_time) + 1;
   ping_Output1(&to_send);
   // This will kill it
   ping_Output1(&to_send);
	
}
