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
typedef TB_THREAD_TYPE 		pthread_t;



void tb_mutex_create(TB_MUTEX_TYPE *mtx);
void tb_mutex_destroy(TB_MUTEX_TYPE *mtx);
void tb_mutex_lock(TB_MUTEX_TYPE *mtx); 
void tb_mutex_unlock(TB_MUTEX_TYPE *mtx);


void tb_sem_create(TB_SEM_TYPE *sem);
void tb_sem_destroy(TB_SEM_TYPE *sem);
void tb_sem_post(TB_SEM_TYPE *sem);
void tb_sem_wait(TB_SEM_TYPE *sem);

void tb_thread_create(TB_THREAD_TYPE *thread, 
					  int priority, 
					  (void *)(*entrypoint)(void *), 
					  void *arg);

/* period is in MICROSECONDS */

int tb_make_periodic_timer (int unsigned period, 
						  void       (*sigev_notify_function) (union sigval), 
						  struct periodic_info *info); 
