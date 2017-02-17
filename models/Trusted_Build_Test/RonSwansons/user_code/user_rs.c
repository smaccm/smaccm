#include <user_rs.h>
#include <stdlib.h>
#include <stdint.h>
#include <stdio.h>
int waiting = 0;
int eatpolls = 0;
int fork1 = -1;
int fork2 = -1;
int seeded = 0;

void poll(const RonSwansons__fork_impl * fork_data) {
	
	printf("Ron %i: I am taking action.\n",ID);
	
	if(fork1<0) {
		// Ron has no forks.
		printf("Ron %i: I am requesting an initial fork.\n",ID);		
		RonSwansons__pollresp_impl pollresp;
		pollresp.clientid = ID;
		pollresp.reqfork1 = 1;
		pollresp.reqfork2 = -1;
		waiting++;
		pollresponse(&pollresp);
	} else if (fork2<0) {
		// Ron has one fork.
		printf("Ron %i: I am requesting a second fork with an id greater than %i.\n",ID,fork1);
		RonSwansons__pollresp_impl pollresp;
		pollresp.clientid = ID;
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
		fork1msg.clientid = ID;
		fork1msg.forkid = fork1;
		fork1 = -1;
		fork2msg.clientid = ID;
		fork2msg.forkid = fork2;
		fork2 = -1;
		bool f1 = givefork(&fork1msg);
		if(!f1) {
			printf("Ron %i: failed to return fork %i.\n",ID,fork1);
		}
		bool f2 = givefork(&fork2msg);
		if(!f2) {
			printf("Ron %i: failed to return fork %i.\n",ID,fork2);
		}
	}
}

void getfork(const RonSwansons__fork_impl * fork_data) {
	if (fork_data->forkid<0) {
		printf("Ron %i: no forks available.\n",ID);
		waiting--;
		return;
	}

	if (fork1 < 0) {
		fork1 = fork_data->forkid;
		printf("Ron %i: I received an initial fork with id %i.\n",ID,fork1);
		waiting--;		
	} else if (fork2 < 0) {
		fork2 = fork_data->forkid;
		eatpolls = 10;
		printf("Ron %i: I received a second fork with id %i and will eat for %i polls.\n",ID,fork2,eatpolls);
		waiting--;
	} else {
		printf("getfork: already have two forks!\n");
	}
}

void alarm(const int64_t * periodic_100_m) {}
