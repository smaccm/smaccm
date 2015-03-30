#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(const uint32_t periodic_100_ms) {

   printf("sender ping received (%d).  Writing to receiver \n", periodic_100_ms);
   ping_Output1(periodic_100_ms + 1); 
	
}