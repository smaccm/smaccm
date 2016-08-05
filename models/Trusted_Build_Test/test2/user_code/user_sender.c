#include <smaccm_sender.h>
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
	#else
		printf("sender: periodic dispatch received at time: %lld.  Writing to receiver. \n", *the_time);
	#endif	
	   
   A_Struct test_data;
   test_data.field1 = 1.0;
   test_data.field2 = 2.0; 
   ping_Output1(&test_data);
   
   bool result = ping_Output1(&test_data);
	#ifdef __TB_OS_ECHRONOS__
		debug_print("sender: second attempt at pinging receiver was: ");
		debug_printhex32((uint32_t)result);
		debug_println(".  ");
	#else
        printf("sender: second attempt at pinging receiver was: %d\n", result);
    #endif
}
