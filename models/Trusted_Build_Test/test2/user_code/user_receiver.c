#include <smaccm_receiver.h>
#include <receiver.h>

void ping_received(test2__a_struct_impl *test_data) {

   printf("receiver ping received (%f, %f)\n", test_data->field1, test_data->field2);
}
