#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(const uint32_t periodic_100_ms) {
   printf("sender ping received (%d).  Writing to receiver \n", periodic_100_ms);
   test_rpc_aadl__a_struct_impl my_struct;
   my_struct.field1 = 0.0;
   my_struct.field2 = 32; 
   uint32_t result;
   aadl_add_subtract_add(&my_struct, 5, &result);
   printf("Result of 'add' call to receiver with arguments 10, 5 : (%d) \n", result);
   aadl_add_subtract_subtract(10, 5, &my_struct);  
   printf("Result of 'subtract' call to receiver with arguments 10, 5 : (%d) \n", my_struct.field2);
}