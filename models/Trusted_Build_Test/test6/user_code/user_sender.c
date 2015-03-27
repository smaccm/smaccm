#include <smaccm_sender.h>
#include <sender.h>
#include <inttypes.h>

void periodic_ping(const uint64_t * periodic_100_ms) {

   printf("sender: periodic dispatch received (%" PRIu64 ").  Writing to receiver \n", *periodic_100_ms);
   
   test6__a_array_impl test_data;
   test_data[0] = 0;
   test_data[1] = 1;
   test_data[2] = 2;
   test_data[3] = 3;
   printf("sender: sending test data: (%d, %d, %d, %d) to receiver \n", test_data[0], test_data[1], test_data[2], test_data[3]);
   
   bool result = ping_Output1(&test_data);
   printf("sender: first attempt at pinging receiver was: %d. \n", result); 

   result = ping_Output1(&test_data);
   printf("sender: second attempt at pinging receiver was: %d. \n", result); 

}