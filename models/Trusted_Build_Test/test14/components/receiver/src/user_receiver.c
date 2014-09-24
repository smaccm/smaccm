#include <smaccm_receiver.h>
#include <receiver.h>

void ping_received() {
   printf("receiver ping received. \n");
}

void periodic_ping(/* const */uint32_t periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %d", periodic_1000_ms); 
}