#include <pthread.h>
#include <sys/signal.h>
#include <sys/time.h>
#include <semaphore.h> 

/**************************************************************************
  Copyright (c) 2013-2016 Rockwell Collins and the University of
  Minnesota. Developed with the sponsorship of the Defense Advanced
  Research Projects Agency (DARPA).

  Permission is hereby granted, free of charge, to any person
  obtaining a copy of this data, including any software or models
  in source or binary form, as well as any drawings,
  specifications, and documentation (collectively "the Data"), to
  deal in the Data without restriction, including without
  limitation the rights to use, copy, modify, merge, publish,
  distribute, sublicense, and/or sell copies of the Data, and to
  permit persons to whom the Data is furnished to do so, subject to
  the following conditions:

  The above copyright notice and this permission notice shall be
  included in all copies or substantial portions of the Data.

  THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS, SPONSORS,
  DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
  CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
  CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
  CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE
  DATA.
 **************************************************************************/

#ifndef __TB_LINUX_SUPPORT
#define __TB_LINUX_SUPPORT
 
typedef struct 
{
	int sig;
	sigset_t alarm_sig;
	int wakeups_missed;
} periodic_info;

typedef sem_t				TB_SEM_TYPE; 
typedef pthread_mutex_t		TB_MUTEX_TYPE;
typedef pthread_t			TB_THREAD_TYPE;


void tb_interproc_mutex_create(TB_MUTEX_TYPE *mtx);
void tb_intraproc_mutex_create(TB_MUTEX_TYPE *mtx);
void tb_mutex_destroy(TB_MUTEX_TYPE *mtx);
void tb_mutex_lock(TB_MUTEX_TYPE *mtx); 
void tb_mutex_unlock(TB_MUTEX_TYPE *mtx);


void tb_sem_create(TB_SEM_TYPE *sem);
void tb_sem_destroy(TB_SEM_TYPE *sem);
void tb_sem_post(TB_SEM_TYPE *sem);
void tb_sem_wait(TB_SEM_TYPE *sem);

void tb_thread_create(TB_THREAD_TYPE *thread, 
					  int priority, 
					  void *(*entrypoint)(void *), 
					  void *arg);

/* period is in MICROSECONDS */

int tb_make_periodic_timer (int unsigned period, 
						  void       (*the_function) (union sigval), 
						  periodic_info *info); 

#endif // #define __TB_LINUX_SUPPORT

						  