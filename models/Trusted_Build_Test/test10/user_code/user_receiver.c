#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void periodic_ping(const uint64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %" PRIu64 " \n", *periodic_1000_ms); 
	
	uint32_t test_data;
	bool result = true; 
	while (result) {
		result = receiver_read_Input1(&test_data); 
		if (result) {
   			printf("receiver: data received (%d)\n", test_data);
   		} else {
   			printf("receiver: queue emptied.\n");
   		}
   	}		
}