#include <tb_ping.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>

void ping(const int64_t * periodic_100_m) {
   PingTwins__msg_impl msg_data;
   int dice = rand()%4;
   switch(dice) {
     case 0: 
       printf("Ping o1.\n"); 
       Out1(&msg_data);
       printf("Ping o2.\n"); 
       Out2(&msg_data);
       break;
     case 1:
       printf("Ping o2.\n"); 
       Out2(&msg_data);
       printf("Ping o1.\n"); 
       Out1(&msg_data);
       break;
     case 2:
       printf("Ping o1.\n"); 
       Out1(&msg_data);
       break;
     case 3:
       printf("Ping o2.\n"); 
       Out2(&msg_data);
       break;
  }
   
}

void In1(const PingTwins__msg_impl * msg_data) {
	printf("Pong recv'd on i1\n");
}

void In2(const PingTwins__msg_impl * msg_data) {
	printf("Pong recv'd on i2\n");
}