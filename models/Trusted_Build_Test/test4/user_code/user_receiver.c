#include "aadl_receiver.h"
#ifdef __TB_OS_CAMKES__
	#include <receiver.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif
#ifndef __TB_OS_ECHRONOS
	#include <stdio.h>
#endif
#include <inttypes.h>

void receiver_periodic_ping( const int64_t * periodic_1000_ms) {
#ifdef __TB_OS_LINUX__
	printf("receiver: periodic dispatch received at time: %"  PRId64 ".", 
	*periodic_1000_ms);
#else
	printf("receiver: periodic dispatch received at time: %lld", *periodic_1000_ms);
#endif
}


void ping_received(const uint32_t *test_data) {
   printf("receiver: ping_received invoked (%d)\n", *test_data);
}
