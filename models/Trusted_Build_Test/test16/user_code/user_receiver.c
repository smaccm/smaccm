#include <tb_receiver.h>
#include <stdio.h>
#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif
#include <inttypes.h>

void receiver_periodic_ping(const int64_t * the_time) {
	#ifdef __TB_OS_VXWORKS__
		// VxWorks doesn't support long longs for printing.
		printf("receiver: periodic dispatch received at time: %d\n", (int32_t)(*the_time));
	#elif __TB_OS_ECHRONOS__
		debug_print("receiver: periodic dispatch received at time ");
		debug_printhex32((uint32_t)*the_time);
		debug_println("  ");
	#else
		printf("receiver: periodic dispatch received at time: %d \n", (int32_t)*the_time);
	#endif	
	
	printf("data stored in shared buffer: %s\n", (char *)buff2);
}
