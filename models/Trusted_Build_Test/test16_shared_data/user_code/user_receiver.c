#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void periodic_ping(const uint64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %" PRIu64 "\n", *periodic_1000_ms);
	printf("data stored in shared buffer: %s\n", (char *)buff2);
}
