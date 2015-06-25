#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>
#include <stdio.h>

void periodic_ping( const int64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %" PRI64 " \n", *periodic_1000_ms);
	
	test12__a_array_impl test_data;
	bool result = true; 
	while (result) {
		result = receiver_read_Input1(test_data);
		if (result) {
   			printf("receiver: data received: (%d, %d, %d, %d) \n", test_data[0], test_data[1], test_data[2], test_data[3]);
   		} else {
   			printf("receiver: queue emptied.\n");
   		}
   	}		
}
