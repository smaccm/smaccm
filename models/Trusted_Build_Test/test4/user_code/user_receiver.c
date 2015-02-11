#include <smaccm_receiver.h>
#include <receiver.h>

void ping_received(uint32_t test_data) {
   printf("receiver: ping_received invoked (%d)\n", test_data);
}

void periodic_ping(/* const */uint32_t periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %d", periodic_1000_ms); 
}