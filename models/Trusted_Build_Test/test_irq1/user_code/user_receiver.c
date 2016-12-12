#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void ping_received(const uint32_t * periodic_100_ms) {

   printf("receiver ping received (%d)\n", (int32_t)*periodic_100_ms);

}
