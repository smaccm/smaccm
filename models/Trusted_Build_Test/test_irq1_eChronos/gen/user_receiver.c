#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void ping_received(const int64_t * periodic_100_ms) {

   printf("receiver ping received (%" PRI64 ")\n", *periodic_100_ms);

}
