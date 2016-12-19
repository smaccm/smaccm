#include "tb_receiver.h"
#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
#endif
#include <inttypes.h>
#ifndef __TB_OS_ECHRONOS__
#include <stdio.h>
#endif

void ping_received(const test5__a_struct_impl *test_data) {
   printf("receiver: ping_received invoked (%f, %f)\n", test_data->field1, test_data->field2);
}

void receiver_periodic_ping(const int64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %d \n", 
		(int32_t)*periodic_1000_ms);
}
