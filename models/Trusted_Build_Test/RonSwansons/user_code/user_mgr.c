#include <smaccm_manager.h>
#include <manager.h>

#define FORKS 4
#define CLIENTS 1
int forks[FORKS+1] = {0,0,0,0,0};

int getfork(int minfork, int maxfork) {
	int i = minfork;
	for(;i<=maxfork;i++) {
		if(forks[i] == 0) break;
	}
	if(i>maxfork) {
		return -1;
	}
	forks[i] = 1;
	return i;
}
void putfork(int client, int fork) {
	printf("putfork1: received fork %i from Ron %i.\n",fork,client);
	forks[fork] = 0;
}

void givefork(int client,const RonSwansons__fork_impl * fork) {
	printf("givefork: giving fork %i to Ron %i\n",fork->forkid,client);
	switch(client) {
		case 1:
			givefork1(fork);
			break;
		default:
			printf("givefork: bad client number received.\n");
	}
}


void pollresponse(int client, const RonSwansons__pollresp_impl * pollresp) {
	if(pollresp->reqfork1 == 1) {
		printf("pollresponse: received initial fork request from Ron %i.\n",client);
		int fork = getfork(0,FORKS-1);
		if(fork < 0) {
			printf("pollresponse: could not find initial fork for Ron %i",client);
			return;
		}
		RonSwansons__fork_impl forkmsg;
		forkmsg.forkid = fork;
		givefork(client,&forkmsg);
	} else if (pollresp->reqfork2 >= 0 && pollresp->reqfork2 < FORKS) {
	    printf("pollresponse: received second fork request from Ron %i for fork id greater than %i.\n",client,pollresp->reqfork2);
		int fork = getfork(pollresp->reqfork2,FORKS);
		if(fork < 0) {
			printf("pollresponse: could not find second fork for Ron %i",client);
			return;
		} 
		RonSwansons__fork_impl forkmsg;
		forkmsg.forkid = fork;
		givefork(client, &forkmsg);
	} else {
		printf("Bad poll response received on pollresponseport%i.\n",client);
	}
}

void pollclient(int client) {
	const RonSwansons__fork_impl fork_data;
	printf("pollclient: polling Ron %i.\n",client);
	switch(client) {
		case 1:
			poll1(&fork_data);
			break;
		default:
			printf("pollclient: bad client number received %i.\n",client);
	}
}

void process(const uint64_t * periodic) {
	int i = 0;
	for(;i<CLIENTS;i++) {
		pollclient(i+1);
	}
}

void pollresponse1(const RonSwansons__pollresp_impl * pollresp) {
	pollresponse(1,pollresp);
}

void getfork1(const RonSwansons__fork_impl * fork) {
	putfork(1,fork->forkid);
}
