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
void putfork(int fork) {
	forks[fork] = 0;
}

void givefork(int client,RonSwansons__fork_impl * fork) {
	switch(client) {
		case 1:
			givefork1(fork);
			break;
		default:
			printf("givefork: bad client number received.\n");
	}
}


void pollresponse(int client, RonSwansons__pollresp_impl * pollresp) {
	if(pollresp->reqfork1 == 1) {
		int fork = getfork(0,FORKS-1);
		if(fork < 0) {
			return;
		}
		RonSwansons__fork_impl forkmsg;
		forkmsg.forkid = fork;
		givefork(client,&forkmsg);
	} else if (pollresp->reqfork2 >= 0 && pollresp->reqfork2 < FORKS) {
		int fork = getfork(pollresp->reqfork2,FORKS);
		if(fork < 0) {
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
	printf("pollclient: polling Ron %i.\n",client);
	switch(client) {
		case 1:
			poll1();
			break;
		default:
			printf("pollclient: bad client number received %i.\n",client);
	}
}

void process(const uint32_t periodic) {
	int i = 0;
	for(;i<CLIENTS;i++) {
		pollclient(1);
	}
}

void pollresponse1(RonSwansons__pollresp_impl * pollresp) {
	pollresponse(1,pollresp);
}

void getfork1(RonSwansons__fork_impl * fork) {
	putfork(fork->forkid);
}
