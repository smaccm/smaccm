#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(uint32_t periodic_100_ms) {

   printf("sender ping received (%d).  Writing to receiver \n", periodic_100_ms);
   
   test3__a_struct_wrapper_impl test_data;
   test_data.f[0] = 0;
   test_data.f[1] = 1;
   test_data.f[2] = 2;
   test_data.f[3] = 3;
   printf("sending array: (%d, %d, %d, %d) to receiver \n", test_data.f[0], test_data.f[1], test_data.f[2], test_data.f[3]);
   
   bool result = ping_Output1(&test_data);
   printf("first attempt at pinging receiver was: %d. \n", result); 

   result = ping_Output1(&test_data);
   printf("second attempt at pinging receiver was: %d. \n", result); 

}