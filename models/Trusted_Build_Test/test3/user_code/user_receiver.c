#include <smaccm_receiver.h>
#include <receiver.h>


void ping_received(const test3__a_struct_wrapper_impl *test_data) {
   printf("receiver ping received (%d, %d, %d, %d)\n", test_data->f[0], test_data->f[1], test_data->f[2], test_data->f[3]);
}
