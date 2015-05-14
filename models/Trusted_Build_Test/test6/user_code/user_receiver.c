#include <smaccm_receiver.h>
#include <receiver.h>

void ping_received(const test6__a_array_impl test_data) {
   printf("receiver: ping_received invoked (%d, %d, %d, %d)\n", test_data[0], test_data[1], test_data[2], test_data[3]);
}

void periodic_ping(const uint64_t *periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %llu \n", *periodic_1000_ms);
}
