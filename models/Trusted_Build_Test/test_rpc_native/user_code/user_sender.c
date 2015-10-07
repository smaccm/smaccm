#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(const int64_t *periodic_100_ms) {
   printf("sender ping received (%ll).  Writing to receiver \n", *periodic_100_ms);
   test_rpc_native__a_struct_impl my_struct;
   my_struct.field1 = 0.0;
   my_struct.field2 = 32;
   uint32_t arg1 = 10;
   uint32_t arg2 = 5;
   uint32_t result;
   add_subtract_add(my_struct, arg2, &result);
   printf("Result of 'add' call to receiver with arguments 32, 5 : (%d) \n", result);
   add_subtract_subtract(&arg1, arg2, &my_struct);
   printf("Result of 'subtract' call to receiver with arguments 10, 5 : (%d) \n", my_struct.field2);
}
