#include <smaccm_sender.h>
//#include <sender.h>
//#include <inttypes.h>
#include <string.h>
#include <stdio.h>

char buff[255];

void sender_periodic_ping(const int64_t * periodic_100_ms) {

   printf("sender ping received (%d).  Writing to receiver \n", *periodic_100_ms);
   /* char *shello = "hello world: "; */

   sprintf(buff, "hello world from sender: %d", *periodic_100_ms);
   strcpy((void*)buff1, buff);
}
