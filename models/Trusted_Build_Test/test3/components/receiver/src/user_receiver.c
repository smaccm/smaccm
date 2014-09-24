#include <smaccm_receiver.h>
#include <receiver.h>

void ping_received(test3__a_array_impl test_data) {

   printf("receiver ping received (%d, %d, %d, %d)\n", test_data[0], test_data[1], test_data[2], test_data[3]);
}
