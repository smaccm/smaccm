#include <smaccm_sender.h>
#include <inttypes.h>
#include <stdio.h>
void periodic_ping(const int64_t * periodic_100_ms) {

   printf("sender: periodic dispatch received (%lld).  Writing to receiver \n", *periodic_100_ms);
   
   uint32_t test_data = ((uint32_t)*periodic_100_ms) * 2 + 1;
   printf("sender: sending test data: (%d) to receiver \n", test_data);
   
   ping_Output1(&test_data);
}
