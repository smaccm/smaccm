#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(const uint32_t periodic_100_ms) {

   printf("sender ping received (%d).  Writing to receiver \n", periodic_100_ms);
   
   test2__a_struct_impl test_data;
   test_data.field1 = 1.0;
   test_data.field2 = 2.0; 
   ping_Output1(&test_data);
   
   bool result = ping_Output1(&test_data);
   printf("second attempt at pinging receiver was: %d", result); 
}