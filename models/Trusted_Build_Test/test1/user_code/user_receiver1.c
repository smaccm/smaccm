#include <smaccm_receiver.h>
#include <receiver.h>


void ping_received(const uint32_t *periodic_100_ms) {
   printf("receiver1 ping received (%d)\n", *periodic_100_ms);
   printf("receiver1 dispatching to receiver2...\n", *periodic_100_ms);
   uint32_t output = *periodic_100_ms + 1; 
   ping_Output1(&output);
}
