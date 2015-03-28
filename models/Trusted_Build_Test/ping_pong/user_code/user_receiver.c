#include <smaccm_receiver.h>
#include <receiver.h>


uint32_t counter = 0;

void pong_received(const uint32_t periodic_100_ms) {

   printf("receiver1 ping received (%d)\n", periodic_100_ms);
   counter++; 
   pong_Output1(counter);
}

