#include <smaccm_sender.h>
#include <stdio.h>
#include <inttypes.h>

void periodic_ping(const uint64_t * periodic_100_ms) {

   printf("sender: periodic dispatch received (%" PRIu64 ").  Writing to receiver \n", *periodic_100_ms);
   
   test8__a_struct_impl test_data;
   test_data.field1 = 1.0;
   test_data.field2 = 2.0;
   
   printf("sender: sending test data: (%f, %f) to receiver \n", test_data.field1, test_data.field2);
   
   bool result = ping_Output1(&test_data);
   printf("first attempt at pinging receiver was: %d. \n", result); 

   result = ping_Output1(&test_data);
   printf("second attempt at pinging receiver was: %d. \n", result); 

}