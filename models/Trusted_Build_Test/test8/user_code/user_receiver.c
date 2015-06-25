#include <smaccm_receiver.h>
#include <stdio.h>
#include <inttypes.h>

void periodic_ping(const int64_t * periodic_1000_ms) {
	test8__a_struct_impl test_data;
	
	printf("receiver: periodic dispatch received at time: %" PRI64 "\n", *periodic_1000_ms);
    printf("receiver: checking value of test_data on Input1 \n");
    ping_Input1(&test_data);
    printf("receiver: test_data (%f, %f)\n", test_data.field1, test_data.field2);
}
