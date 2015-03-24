#include <user_rs.h>
#include <stdlib.h>
#include <stdint.h>
int waiting = 0;
int eatpolls = 0;
int fork1 = -1;
int fork2 = -1;


void poll() {
	
	printf("Ron %i: I have been polled.\n",ID);

	int take_action = rand()%4;
	if (take_action != 0 || waiting!=0) return;
	
	printf("Ron %i: I am taking action.\n",ID);
	
	if(fork1<0) {
		// Ron has no forks.
		printf("Ron %i: I am requesting an initial fork.\n",ID);		
		RonSwansons__pollresp_impl pollresp;
		pollresp.reqfork1 = 1;
		pollresp.reqfork2 = -1;
		waiting++;
		pollresponse(&pollresp);
	} else if (fork2<0) {
		// Ron has one fork.
		printf("Ron %i: I am requesting a second fork with an id greater than %i.\n",ID,fork1);
		RonSwansons__pollresp_impl pollresp;
		pollresp.reqfork1 = 0;
		pollresp.reqfork2 = fork1;
		waiting++;
		pollresponse(&pollresp);
	} else if (eatpolls>0) {
		// Ron is eating 
		printf("Ron %i: Nom nom nom with forks %i and %i.\n",ID,fork1,fork2);
		eatpolls--;
	} else {
		// Return forks.
		printf("Ron %i: I am returning forks %i and %i.\n",ID,fork1,fork2);
		RonSwansons__fork_impl fork1msg;
		RonSwansons__fork_impl fork2msg;
		fork1msg.forkid = fork1;
		fork1 = -1;
		fork2msg.forkid = fork2;
		fork2 = -1;
		givefork(&fork1msg);
		givefork(&fork2msg);
	}
}

void getfork(RonSwansons__fork_impl * fork_data) {
	if (fork1 < 0) {
		fork1 = fork_data->forkid;
		printf("Ron %i: I received an initial fork with id %i.\n",ID,fork1);		
	} else if (fork2 < 0) {
		fork2 = fork_data->forkid;
		eatpolls = rand()%50;
		printf("Ron %i: I received a second fork with id %i and will eat for %i polls.\n",ID,fork2,eatpolls);
	} else {
		printf("getfork: already have two forks!\n");
	}
}