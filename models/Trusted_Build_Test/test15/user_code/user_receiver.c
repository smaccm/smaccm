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
	printf("receiver: periodic dispatch received at time: %d", (int32_t)*periodic_1000_ms);

	bool result = true; 
	while (result) {
		result = read_input1(); 
		if (result) {
   			printf("receiver: event consumed\n");
   		} else {
   			printf("receiver: queue emptied.\n");
   		}
   	}		
}
