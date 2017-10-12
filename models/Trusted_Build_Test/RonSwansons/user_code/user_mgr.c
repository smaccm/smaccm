#include <tb_manager.h>

#define FORKS 4
#define CLIENTS 6
int forks[FORKS+1] = {0,0,0,0,0};
int start = 0;

int allocfork(int minfork, int maxfork) {
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
		case 2:
			givefork2(fork);
			break;
		case 3:
			givefork3(fork);
			break;
		case 4:
			givefork4(fork);
			break;
		case 5:
			givefork5(fork);
			break;
		case 6:
			givefork6(fork);
			break;
		default:
			printf("givefork: bad client number received.\n");
	}
}


void pollresponse(const RonSwansons__pollresp_impl * pollresp) {
	int client = pollresp->clientid;
	if(pollresp->reqfork1 == 1) {
		printf("pollresponse: received initial fork request from Ron %i.\n",client);
		int fork = allocfork(0,FORKS-1);
		if(fork < 0) {
			printf("pollresponse: could not find initial fork for Ron %i.\n",client);
			RonSwansons__fork_impl forkmsg;
			forkmsg.forkid = -1;
			givefork(client,&forkmsg);
			return;
		}
		RonSwansons__fork_impl forkmsg;
		forkmsg.forkid = fork;
		givefork(client,&forkmsg);
	} else if (pollresp->reqfork2 >= 0 && pollresp->reqfork2 < FORKS) {
	    printf("pollresponse: received second fork request from Ron %i for fork id greater than %i.\n",client,pollresp->reqfork2);
		int fork = allocfork(pollresp->reqfork2,FORKS);
		if(fork < 0) {
			printf("pollresponse: could not find second fork for Ron %i.\n",client);
			RonSwansons__fork_impl forkmsg;
			forkmsg.forkid = -1;
			givefork(client,&forkmsg);
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
		case 2:
			poll2(&fork_data);
			break;	
		case 3:
			poll3(&fork_data);
			break;	
		case 4:
			poll4(&fork_data);
			break;
		case 5:
			poll5(&fork_data);
			break;	
		case 6:
			poll6(&fork_data);
			break;	
		default:
			printf("pollclient: bad client number received %i.\n",client);
	}
}

void process(const int64_t * periodic_dispatcher) {
	int i = 0;
	for(;i<CLIENTS;i++) {
		pollclient(((i+start)%CLIENTS)+1);
	}
	start++;
}

void getfork(const RonSwansons__fork_impl * fork) {
	putfork(fork->clientid,fork->forkid);
}
