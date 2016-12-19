#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif
#include "tb_receiver.h"
#include <inttypes.h>
#ifndef __TB_OS_ECHRONOS
#include <stdio.h>
#endif

void receiver_periodic_ping(const int64_t *periodic_1000_ms) {
	test9__a_array_impl test_data;
	
	printf("receiver: periodic dispatch received at time: %d\n", 
	(int32_t)*periodic_1000_ms);
    printf("receiver: checking value of test_data on Input1 \n");
    ping_Input1(test_data);
    printf("receiver: ping_received invoked (%d, %d, %d, %d)\n", test_data[0], test_data[1], test_data[2], test_data[3]);
}
