#include <user_pong.h>
#include <stdio.h>

void In(const PingTwins__msg_impl * msg_data) {
	printf("In%i: Ping(%i) recv'd, sending pong.\n",ID,msg_data->dummy);
	Out(msg_data);	
}

void pong(const int64_t * periodic_100_m) {}