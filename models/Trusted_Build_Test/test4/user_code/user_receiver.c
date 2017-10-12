#include "tb_receiver.h"
#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif
#ifndef __TB_OS_ECHRONOS
	#include <stdio.h>
#endif
#include <inttypes.h>
#include <stdint.h>

char buffer[4096];

void receiver_periodic_ping( const int64_t * periodic_1000_ms) {
#ifdef __TB_OS_LINUX__
	printf("receiver: periodic dispatch received at time: %"  PRId64 ".\n", 
	*periodic_1000_ms);
#else
	printf("receiver: periodic dispatch received at time: %lld\n", *periodic_1000_ms);
#endif
	int32_t results;
	int position = 0;
    while (tb_Input1_dequeue(&results)) {
		buffer[position++] = '.';
		printf("receiver_periodic_ping: Received %d\n", results);
	}
	buffer[position] = 0;
	printf("Contents of buffer: %s\n", buffer);
}

void ping_received(const uint32_t * results) {
    printf("ping_received: Received %d\n",*results);
    return;
}
