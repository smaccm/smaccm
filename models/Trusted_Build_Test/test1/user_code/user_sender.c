#include <smaccm_sender.h>
#include <sender.h>
#include <inttypes.h>


void periodic_ping(const uint64_t *periodic_100_ms) {

   printf("sender ping received (%" PRIu64 ").  Writing to receiver \n", *periodic_100_ms);
   uint32_t to_send = ((uint32_t)*periodic_100_ms) + 1;
   ping_Output1(&to_send);
	
}
