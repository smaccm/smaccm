#include <smaccm_receiver.h>
#include <debug.h>

void ping_received(const uint32_t * periodic_100_ms) {
	   debug_print("receiver1 ping received");
	   debug_printhex32(*periodic_100_ms);
	   debug_println("");
}
