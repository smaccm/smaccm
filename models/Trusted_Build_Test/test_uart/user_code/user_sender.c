#include <smaccm_sender.h>
#include <sender.h>
#include <inttypes.h>

void periodic_ping(const uint64_t * periodic_100_ms) {

   test_uart__uart_packet_impl packet;
   printf("sender ping received (%" PRIu64 ").  Writing to receiver \n", periodic_100_ms);
   packet.uart_num = 1; 
   packet.datum = periodic_100_ms + 1;
   ping_Output1(&packet); 
	
}