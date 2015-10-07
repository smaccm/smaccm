#include <smaccm_sender.h>
#include <sender.h>
#include <inttypes.h>

void periodic_ping(const int64_t * periodic_100_ms) {

   printf("sender ping received (%" PRI64 ").  Writing to receiver \n", *periodic_100_ms);
   ping_Output1(); 
}
