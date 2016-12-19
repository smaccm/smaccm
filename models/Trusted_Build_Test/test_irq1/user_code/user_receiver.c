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

void ping_received(const int64_t * periodic_100_ms) {

   printf("receiver ping received (%d)\n", (int32_t)*periodic_100_ms);

}
