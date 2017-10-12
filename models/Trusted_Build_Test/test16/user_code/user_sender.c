#include <tb_sender.h>
#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif

#include <string.h>
#include <stdio.h>

char buff[255];

void sender_periodic_ping(const int64_t * the_time) {

	#ifdef __TB_OS_VXWORKS__
		// VxWorks doesn't support long longs for printing.
		printf("sender: periodic dispatch received at time: %d.  Writing to receiver. \n", (int32_t)(*the_time));
	#elif __TB_OS_ECHRONOS__
		debug_print("sender: periodic dispatch received at time ");
		debug_printhex32((uint32_t)*the_time);
		debug_println(".  Writing to receiver.");
	#else
		printf("sender: periodic dispatch received at time: %d.  Writing to receiver. \n", (int32_t)*the_time);
	#endif	

   sprintf(buff, "hello world from sender: %d", (int32_t)*the_time);
   strcpy((void*)buff1, buff);
}
