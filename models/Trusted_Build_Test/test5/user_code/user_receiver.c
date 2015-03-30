#include <smaccm_receiver.h>
#include <receiver.h>
#include <inttypes.h>

void ping_received(const test5__a_struct_impl *test_data) {
   printf("receiver: ping_received invoked (%f, %f)\n", test_data->field1, test_data->field2);
}

<<<<<<< HEAD
void periodic_ping(const uint32_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %" PRIu64 " \n", *periodic_1000_ms); 
}
=======
void periodic_ping(const uint64_t * periodic_1000_ms) {
	printf("receiver: periodic dispatch received at time: %" PRIu64 " \n", *periodic_1000_ms); 
}
>>>>>>> upstream/develop
