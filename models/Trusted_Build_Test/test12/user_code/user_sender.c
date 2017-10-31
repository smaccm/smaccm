#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif
#include "tb_sender.h"
#include <inttypes.h>
#ifndef __TB_OS_ECHRONOS
#include <stdio.h>
#endif


void sender_periodic_ping(const int64_t * periodic_100_ms) {

   printf("sender: periodic dispatch received (%d).  Writing to receiver \n", (int32_t)*periodic_100_ms);
   
   test12__a_array_impl test_data;
   test_data[0] = 0;
   test_data[1] = 1;
   test_data[2] = 2;
   test_data[3] = 3;
   printf("sender: sending test data: (%d, %d, %d, %d) to receiver \n", test_data[0], test_data[1], test_data[2], test_data[3]);
   
   bool result = ping_Output1(test_data);
   printf("first attempt at pinging receiver was: %d. \n", result); 

   result = ping_Output1(test_data);
   printf("second attempt at pinging receiver was: %d. \n", result); 

}
