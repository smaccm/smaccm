#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(const uint32_t periodic_100_ms) {

   printf("sender: periodic dispatch received (%d).  Writing to receiver \n", periodic_100_ms);
   ping_Output1(periodic_100_ms + 1); 
	
}

void ping_Loopback(const uint32_t val_from_pong) {
   printf("sender: Queued input (Input1) received (%d).  \n", val_from_pong);
}