#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif
#include "tb_receiver.h"
#include <inttypes.h>
#ifndef __TB_OS_ECHRONOS
#include <stdio.h>
#endif


void receiver_periodic_ping(const int64_t * periodic_1000_ms) {
	//uint32_t test_data; 
#ifdef __TB_OS_LINUX__
	printf("receiver: periodic dispatch received at time: %"  PRId64 ".", 
	*periodic_1000_ms);
#else
	printf("receiver: periodic dispatch received at time: %lld", *periodic_1000_ms);
#endif
	
    printf("receiver: checking value of test_data on Input1 \n");
    printf("receiver: test_data value: (%d)\n", *((int32_t *)tb_receiver_Input1_var));
}
