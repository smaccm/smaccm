#include <smaccm_receiver.h>
#include <inttypes.h>
#include <stdio.h>

void periodic_ping(const uint64_t * periodic_1000_ms) {
	uint32_t test_data; 
	
	printf("receiver: periodic dispatch received at time: %" PRIu64 "", *periodic_1000_ms);
    printf("receiver: checking value of test_data on Input1 \n");
    ping_Input1(&test_data);
    printf("receiver: test_data value: (%d)\n", test_data);
}
