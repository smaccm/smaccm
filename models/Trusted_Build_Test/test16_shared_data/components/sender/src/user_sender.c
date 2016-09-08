#include <smaccm_sender.h>
#include <sender.h>
#include <inttypes.h>
#include <string.h>
#include <stdio.h>

char buff[255];

void periodic_ping(const int64_t * periodic_100_ms) {

   printf("sender ping received (%" PRI64 ").  Writing to receiver \n", *periodic_100_ms);
   /* char *shello = "hello world: "; */

   sprintf(buff, "hello world: %s at %ll", get_instance_name(), *periodic_100_ms);
   //strcpy(buff,  "hello world: ");
   // strcpy(buff+13, get_instance_name());


   strcpy((void*)buff1, buff);
}
