#include <smaccm_receiver.h>
#include <receiver.h>

void ping_received(const uint32_t *test_data) {
   printf("receiver: ping_received invoked (%d)\n", *test_data);
}

void periodic_ping(const uint64_t *periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %llu", *periodic_1000_ms);
}
