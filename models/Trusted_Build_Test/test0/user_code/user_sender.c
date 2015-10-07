#include <smaccm_sender.h>
#include <stdio.h>
#include <inttypes.h>

void periodic_ping(const int64_t *periodic_100_ms) {
	printf("I'm periodically pinging at time:  %" PRI64 "\n", *periodic_100_ms);
}
