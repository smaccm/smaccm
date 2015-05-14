#include <smaccm_receiver.h>
#include <receiver.h>


void ping_received(const test_uart__uart_packet_impl * Input1) {

   printf("receiver1 ping received (%d)\n", Input1->datum);
}
