#include <smaccm_sender.h>


void periodic_ping(uint32_t periodic_100_ms) {

   printf("sender: periodic dispatch received (%d).  Writing to receiver \n", periodic_100_ms);
   
   uint32_t test_data = periodic_100_ms * 2 + 1;
   printf("sender: sending test data: (%d) to receiver \n", test_data);
   
   ping_Output1(test_data);
}