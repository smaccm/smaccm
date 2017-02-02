#include <tb_ping.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>


void ping(const int64_t * periodic_100_m) {
   static int counter = 0;
   PingTwins__msg_impl msg_data;
   msg_data.dummy = counter;
   printf("Ping.\n"); 
   Out(&msg_data);
   counter+=2;
}

void In(const PingTwins__msg_impl * msg_data) {
	printf("Pong(%i).\n",msg_data->dummy);
}