#include <smaccm_receiver2.h>
#include <receiver2.h>


void ping_received(const uint32_t periodic_100_ms) {

   printf("receiver2 ping received (%d)\n", periodic_100_ms);
}
