#include <smaccm_sender.h>
#include <sender.h>


void periodic_ping(const int64_t *periodic_100_ms) {

   test_uart__uart_packet_impl packet;
   printf("sender ping received (%ll).  Writing to receiver \n", *periodic_100_ms);
   packet.uart_num = 0;
   packet.datum = (uint32_t)(*periodic_100_ms) + 1;
   ping_Output1(&packet); 
	
}
