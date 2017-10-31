#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif
#include "tb_sender.h"
#include <inttypes.h>
#ifndef __TB_OS_ECHRONOS
#include <stdio.h>
#endif

void sender_periodic_ping(const int64_t * periodic_100_ms) {

   printf("sender ping received (%d).  Writing to receiver \n", (int32_t)*periodic_100_ms);
   ping_Output1(); 
}
