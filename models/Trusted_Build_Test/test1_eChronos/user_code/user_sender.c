#include <smaccm_sender.h>
#include <debug.h>

void periodic_ping(const int64_t *periodic_100_ms) {

	debug_print("sender ping received (");
	debug_printhex32((uint32_t)*periodic_100_ms);
	debug_println(").  Writing to receivers ");
   uint32_t to_send = ((uint32_t)*periodic_100_ms) + 1;
   ping_Output1(&to_send);
	
}
