#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(const uint64_t *periodic_100_ms) {

   printf("sender: periodic dispatch received (%llu).  Writing to receiver \n", *periodic_100_ms);
   uint32_t elem = (uint32_t)(*periodic_100_ms) + 1;
   ping_Output1(&elem);
	
}

void ping_Loopback(const uint32_t *val_from_pong) {
   printf("sender: Queued input (Input1) received (%u).  \n", *val_from_pong);
}
