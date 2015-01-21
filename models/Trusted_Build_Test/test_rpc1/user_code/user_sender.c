#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(const uint32_t periodic_100_ms) {
   printf("sender ping received (%d).  Writing to receiver \n", periodic_100_ms);
   uint32_t result;
   add_subtract_add_two_numbers(10, 5, &result);
   printf("Result of 'add' call to receiver with arguments 10, 5 : (%d) \n", result);
   add_subtract_subtract_two_numbers(10, 5, &result);  
   printf("Result of 'subtract' call to receiver with arguments 10, 5 : (%d) \n", result);
}