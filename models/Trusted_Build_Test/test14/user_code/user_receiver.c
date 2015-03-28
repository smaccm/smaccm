#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void ping_received() {
   printf("receiver ping received. \n");
}

void periodic_ping(const uint64_t *periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %" PRIu64 "", *periodic_1000_ms); 
}
