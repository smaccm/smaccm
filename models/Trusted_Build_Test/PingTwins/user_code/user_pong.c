#include <user_pong.h>
#include <stdio.h>

void In(const PingTwins__msg_impl * msg_data) {
	PingTwins__msg_impl data = {0,};
	data.dummy = msg_data->dummy+(ID-1);
	printf("In%i: Ping(%i) recv'd, sending pong.\n",ID,data.dummy);
	Out(&data);	
}

void pong(const int64_t * periodic_100_m) {}