#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void ping_received(const uint64_t * periodic_100_ms) {

   printf("receiver ping received (%" PRIu64 ")\n", *periodic_100_ms);
}
