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

#define BUFF_SIZE 4096
char buffer[BUFF_SIZE];

void receiver_periodic_ping( const int64_t * periodic_1000_ms) {
#ifdef __TB_OS_LINUX__
	printf("receiver: periodic dispatch received at time: %"  PRId64 ".", 
	*periodic_1000_ms);
#else
	printf("receiver: periodic dispatch received at time: %lld", *periodic_1000_ms);
#endif
	uint32_t results;
	int position = 0;
    while (tb_receiver_read_Input1(&results) && position < BUFF_SIZE) {
		buffer[position++] = '.';
	}
	buffer[position] = 0;
	printf("Contents of buffer: %s", buffer);
	printf("last result: %d", results);
}