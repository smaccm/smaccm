#include "tb_linux_support.h"
#include <assert.h>
#include <stdio.h>
#include <errno.h>


void tb_intraproc_mutex_create(TB_MUTEX_TYPE *mtx) {
	int tb_result;
	tb_result = pthread_mutex_init(mtx, NULL);
	assert(tb_result == 0);
}

void tb_interproc_mutex_create(TB_MUTEX_TYPE *mtx) {
	pthread_mutexattr_t tb_attr;
	int tb_result;
	tb_result = pthread_mutexattr_init(&tb_attr);
	assert(tb_result == 0);
	tb_result = pthread_mutexattr_setpshared(&tb_attr, PTHREAD_PROCESS_SHARED);
	assert(tb_result == 0);
	tb_result = pthread_mutex_init(mtx, &tb_attr);
	assert(tb_result == 0);
	tb_result = pthread_mutexattr_destroy(&tb_attr);
	assert(tb_result == 0);
}

void tb_mutex_destroy(TB_MUTEX_TYPE *mtx) {
	int tb_result; 
	tb_result = pthread_mutex_destroy(mtx); 
	assert(tb_result == 0);
}

void tb_mutex_lock(TB_MUTEX_TYPE *mtx) {
	int tb_result; 
	tb_result = pthread_mutex_lock(mtx); 
	assert(tb_result == 0);
}

void tb_mutex_unlock(TB_MUTEX_TYPE *mtx) {
	int tb_result;
	tb_result = pthread_mutex_unlock(mtx); 
	assert(tb_result == 0);
}

void tb_sem_create(TB_SEM_TYPE *sem) {
	int tb_result = 0;
	tb_result = sem_init(sem,
						 /* NOT-SHARED */ 0,
						 /* initial count */ 1);
	assert(tb_result != -1);
}

void tb_sem_destroy(TB_SEM_TYPE *sem) {
	int tb_result;
	tb_result = sem_destroy(sem);
	assert(tb_result != -1);
}

void tb_sem_post(TB_SEM_TYPE *sem) {
	int tb_result; 
	tb_result = sem_post(sem); 
	assert(tb_result != -1); 
}

void tb_sem_wait(TB_SEM_TYPE *sem) {
	int tb_result; 
	tb_result = sem_wait(sem);
	assert(tb_result != -1); 
}

////////////////////////////////////////////////////////////////////
//
// NB: currently we are not setting memory limits for the processes.
// Is this something we want to support?
//
////////////////////////////////////////////////////////////////////

void tb_thread_create(TB_THREAD_TYPE *thread, 
					  int priority, 
					  void *(*entrypoint)(void *), 
					  void *arg) {
	int tb_result;
	pthread_attr_t tattr; 
	struct sched_param schedule_attributes; 
	
/*	printf("EAGAIN == %d", EAGAIN);
	printf("EINVAL == %d", EINVAL);
	printf("EPERM == %d", EPERM);
	printf("ENOTSUP == %d", ENOTSUP);
*/	tb_result = pthread_attr_init (&tattr);	
	assert(tb_result == 0); 
	tb_result = pthread_attr_setinheritsched(&tattr, 	
		PTHREAD_EXPLICIT_SCHED);
	if (tb_result != 0) {
		printf("result was: %d", tb_result);
	}
	assert(tb_result == 0);
	tb_result = pthread_attr_setschedpolicy(&tattr, SCHED_RR);
	assert(tb_result == 0);
	tb_result = pthread_attr_getschedparam(&tattr, &schedule_attributes); 
	assert(tb_result == 0);
	schedule_attributes.sched_priority = priority; 
	tb_result = pthread_attr_setschedparam(&tattr, &schedule_attributes);
	assert(tb_result == 0);
	tb_result = pthread_create(thread, &tattr, entrypoint, arg);
	if (tb_result == EAGAIN) {
		printf("Error: EAGAIN was the result for creating a thread");
	} else if (tb_result == EINVAL) {
		printf("Error: EINVAL was the result for creating a thread");
	} else if (tb_result == EPERM) {
		printf("Error: EPERM was the result for creating a thread.\n");
		printf("Chances are you are not running with high enough privilege.  Try running as root using 'sudo' \n");
	} else if (tb_result != 0) {
		printf("Error: unknown error!  Code = %d", tb_result);
	}
	assert(tb_result == 0);
	
}


/* NB: period is in MICROSECONDS */
int tb_make_periodic_timer (int unsigned period_in_microseconds, 
						    void (*the_function) (union sigval), 
						    periodic_info *info)
{
	static int next_sig;
	int ret;
	unsigned int ns;
	unsigned int sec;
	struct sigevent sigev;
	timer_t timer_id;
	struct itimerspec itval;

	/* Initialise next_sig first time through. We can't use static
	   initialisation because SIGRTMIN is a function call, not a constant */
	if (next_sig == 0)
		next_sig = SIGRTMIN;
	/* Check that we have not run out of signals */
	if (next_sig > SIGRTMAX)
		return -1;
	info->sig = next_sig;
	next_sig++;

	info->wakeups_missed = 0;

	/* Create the signal mask that will be used in wait_period */
	sigemptyset (&(info->alarm_sig));
	sigaddset (&(info->alarm_sig), info->sig);

	/* Create a timer that will generate the signal we have chosen */
	sigev.sigev_notify = SIGEV_THREAD;
	sigev.sigev_signo = info->sig;
	sigev.sigev_value.sival_ptr = (void *) &timer_id;
	sigev.sigev_notify_function = the_function; 
	sigev.sigev_notify_attributes = NULL;
	ret = timer_create (CLOCK_MONOTONIC, &sigev, &timer_id);
	if (ret == -1)
		return ret;

	/* Make the timer periodic */
	sec = period_in_microseconds/1000000;
	ns = (period_in_microseconds - (sec * 1000000)) * 1000;
	itval.it_interval.tv_sec = sec;
	itval.it_interval.tv_nsec = ns;
	itval.it_value.tv_sec = sec;
	itval.it_value.tv_nsec = ns;
	ret = timer_settime (timer_id, 0, &itval, NULL);
	return ret;
}

