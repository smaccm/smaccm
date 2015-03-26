#include <smaccm_receiver.h>
#include <receiver.h>


void ping_received(uint32_t *periodic_100_ms) {

   printf("receiver1 ping received (%d)\n", *periodic_100_ms);
}
