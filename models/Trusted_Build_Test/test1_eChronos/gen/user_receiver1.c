#include <smaccm_receiver.h>
#include <debug.h>

void ping_received1(const uint32_t *periodic_100_ms) {
   debug_print("receiver1 ping received");
   debug_printhex32(*periodic_100_ms);
   debug_println("");
   uint32_t newValue = 2*(*periodic_100_ms);
   ping_Output2(&newValue);
}
