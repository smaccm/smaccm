#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void periodic_ping( const int64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %ll", *periodic_1000_ms);
}
void ping_received(const uint32_t *test_data) {
   printf("receiver: ping_received invoked (%d)\n", *test_data);
}
