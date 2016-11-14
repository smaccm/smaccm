#include <pthread.h>
#include <sys/signal.h>
#include <sys/time.h>
#include <semaphore.h> 

struct periodic_info
{
	int sig;
	sigset_t alarm_sig;
	int wakeups_missed;
};

typedef TB_SEM_TYPE 		sem_t; 
typedef TB_MUTEX_TYPE 		pthread_mutex_t;



/* period is in MICROSECONDS */

static int tb_make_periodic_timer (int unsigned period, 
						  void       (*sigev_notify_function) (union sigval), 
						  struct periodic_info *info); 
