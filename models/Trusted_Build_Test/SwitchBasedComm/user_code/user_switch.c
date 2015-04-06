#include <smaccm_switch.h>
#include <switch.h>

#define RCV_DEBUG "Received message on In%i to be routed to out%i.\n"


void route(const SwitchBasedComm__msg_impl * msg_data) {
	switch(msg_data->toclient) {
		case 0:
			Out1(msg_data);
			break;
		case 1:
			Out2(msg_data);
			break;
		case 2:
			Out3(msg_data);
			break;
	}
}

void In1(const SwitchBasedComm__msg_impl * msg_data) {
	printf(RCV_DEBUG,1,msg_data->toclient);
	route(msg_data);	
}

void In2(const SwitchBasedComm__msg_impl * msg_data) {
	printf(RCV_DEBUG,2,msg_data->toclient);
	route(msg_data);	
}

void In3(const SwitchBasedComm__msg_impl * msg_data) {
	printf(RCV_DEBUG,3,msg_data->toclient);
	route(msg_data);	
}
