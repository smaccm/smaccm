#include <smaccm_receiver2.h>
#include <debug.h>

void ping_received2(const uint32_t *periodic_100_ms) {

	   debug_print("receiver2 ping received");
	   debug_printhex32(*periodic_100_ms);
	   debug_println("");
}
