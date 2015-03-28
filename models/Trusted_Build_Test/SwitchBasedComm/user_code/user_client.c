#include <user_client.h>
#include <stdlib.h>
#include <stdint.h>

void create_msg(const uint64_t * periodic_100_m) {
   SwitchBasedComm__msg_impl msg_data;
   msg_data.toclient = rand()%3;
   msg_data.fromclient = ID; 
   printf("Sending message from %i to %i.\n",ID,msg_data.toclient); 
   Out(&msg_data);
}

void In(const SwitchBasedComm__msg_impl * msg_data) {
	printf("Client %i received message from %i\n",ID,msg_data->fromclient);
}