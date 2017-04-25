#include <smaccm_receiver.h>
#include <stdio.h>
//#include <receiver.h>
//#include <inttypes.h>

void receiver_periodic_ping(const int64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %d\n", *periodic_1000_ms);
	printf("data stored in shared buffer: %s\n", (char *)buff2);
}
