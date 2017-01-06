#ifdef __TB_OS_CAMKES__
	#include <camkes.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif
#include <inttypes.h>
#ifndef __TB_OS_ECHRONOS
#include <stdio.h>
#endif

#include "tb_toplevel_types.h"

bool ping_Output1(const uint32_t * Output1);

void sender_periodic_ping(const int64_t *the_time) {
	
   uint32_t to_send = ((uint32_t)*the_time) + 1;
   ping_Output1(&to_send);
   ping_Output1(&to_send);
	
}