#include "tb_sender.h"
#include <inttypes.h>
#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
#endif
#ifdef __TB_OS_ECHRONOS__
	#include <debug.h>
#else
	#include <stdio.h>
#endif

void periodic_ping(const int64_t * the_time) {

	#ifdef __TB_OS_ECHRONOS__
		debug_print("sender: periodic dispatch received at time ");
		debug_printhex32((uint32_t)*the_time);
		debug_println(".  Writing to receiver.");
	#else
		// VxWorks doesn't support long longs for printing.
		printf("sender: periodic dispatch received at time: %d.  Writing to receiver. \n", (int32_t)(*the_time));
	#endif	
	   
   test6__a_array_impl test_data;
   test_data[0] = 0;
   test_data[1] = 1;
   test_data[2] = 2;
   test_data[3] = 3;
   
   #ifdef __TB_OS_ECHRONOS__
      debug_printf("sender: sending array: ");
      debug_printhex32(test_data[0]);
      debug_printf(", ");
      debug_printhex32(test_data[1]);
      debug_printf(", ");
      debug_printhex32(test_data[2]);
      debug_printf(", ");
      debug_printhex32(test_data[3]);
      debug_printf(", ");
      debug_println(") to receiver \n");

	  bool result = ping_Output1(test_data);
	  debug_printf("sender: first attempt at pinging receiver was: ");
	  debug_printhex32(result);
	  debug_println(". "); 
	
      result = ping_Output1(test_data);
	  debug_printf("sender: second attempt at pinging receiver was: ");
	  debug_printhex32(result);
	  debug_println(". "); 
   #else      
      printf("sender: sending array: (%d, %d, %d, %d) to receiver \n", test_data[0], test_data[1], test_data[2], test_data[3]);
   
      bool result = ping_Output1(test_data);
      printf("sender: first attempt at pinging receiver was: %d. \n", result); 

      result = ping_Output1(test_data);
      printf("sender: second attempt at pinging receiver was: %d. \n", result); 
   #endif

}
