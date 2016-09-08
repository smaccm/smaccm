#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void periodic_ping(const int64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %" PRI64 "\n", *periodic_1000_ms);
	printf("data stored in shared buffer: %s\n", (char *)buff2);
}
