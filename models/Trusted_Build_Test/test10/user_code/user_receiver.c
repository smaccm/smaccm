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

void receiver_periodic_ping(const int64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %d \n", 
	(int32_t)*periodic_1000_ms); 
	
	uint32_t test_data;
	bool result = true; 
	while (result) {
		result = read_input1(&test_data); 
		if (result) {
   			printf("receiver: data received (%d)\n", test_data);
   		} else {
   			printf("receiver: final data received (%d)\n", test_data);
   			printf("receiver: queue emptied.\n");
   		}
   	}		
}