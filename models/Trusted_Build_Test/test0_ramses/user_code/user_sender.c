#include <smaccm_sender.h>
#include <stdio.h>

void periodic_ping(/* const */uint32_t periodic_100_ms) {
	printf("I'm periodically pinging at time: %d\n", periodic_100_ms); 
}
