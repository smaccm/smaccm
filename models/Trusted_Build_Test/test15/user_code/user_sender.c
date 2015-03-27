#include <smaccm_sender.h>
#include <sender.h>
#include <inttypes.h>

void periodic_ping(const uint64_t * periodic_100_ms) {

   printf("sender ping received (%" PRIu64 ").  Writing to receiver \n", *periodic_100_ms);
   ping_Output1(); 
}