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

void ping_received() {
   printf("receiver ping received. \n");
}

void periodic_ping(const int64_t *periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %lld", *periodic_1000_ms);
}
