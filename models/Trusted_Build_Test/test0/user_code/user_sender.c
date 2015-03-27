#include <smaccm_sender.h>
#include <stdio.h>

void periodic_ping(/* const */uint64_t *periodic_100_ms) {
	printf("I'm periodically pinging at time: %lu\n", *periodic_100_ms);
}
