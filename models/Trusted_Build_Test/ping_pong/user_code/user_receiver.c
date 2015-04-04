#include <smaccm_receiver.h>
#include <receiver.h>


uint32_t counter = 0;

void pong_received(const uint32_t *elem) {

   printf("receiver1 ping received (%u)\n", *elem);
   counter++; 
   pong_Output1(&counter);
}

