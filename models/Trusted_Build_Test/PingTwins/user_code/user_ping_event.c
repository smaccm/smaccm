#include <tb_ping.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>


void ping(const int64_t * periodic_100_m) {
   printf("Ping.\n"); 
   Out();
}

void In() {
	printf("Pong.\n");
}