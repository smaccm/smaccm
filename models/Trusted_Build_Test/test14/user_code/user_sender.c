#include <tb_sender.h>
#include <camkes.h>
#include <inttypes.h>

void periodic_ping(const int64_t * periodic_100_ms) {

   printf("sender ping received (%lld).  Writing to receiver \n", *periodic_100_ms);
   ping_Output1(); 
}
