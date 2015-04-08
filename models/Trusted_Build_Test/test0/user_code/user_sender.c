#include <smaccm_sender.h>
#include <stdio.h>
#include <inttypes.h>

void periodic_ping(const uint64_t *periodic_100_ms) {
	printf("I'm periodically pinging at time:  %" PRIu64 "\n", *periodic_100_ms);
}
