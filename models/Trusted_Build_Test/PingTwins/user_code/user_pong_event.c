#include <user_pong.h>
#include <stdio.h>

void In() {
	printf("In%i: Ping recv'd, sending pong.\n",ID);
	Out();	
}

void pong(const int64_t * periodic_100_m) {}