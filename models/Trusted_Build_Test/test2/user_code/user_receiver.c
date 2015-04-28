#include <smaccm_receiver.h>
#include <receiver.h>

void ping_received(const struct A_Struct *test_data) {

   printf("receiver ping received (%f, %f)\n", test_data->field1, test_data->field2);
}
