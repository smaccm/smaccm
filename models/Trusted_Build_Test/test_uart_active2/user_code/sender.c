#include <smaccm_sender_thread.h>
#include <string.h>
#include <stdio.h>

char *str = "CHARMANDER\n";
int i = 0;

void send(const uint64_t * periodic_100_ms) {
  test_uart__uart_packet_i packet;
  packet.uart_num = 0;
  packet.charmander = str[i];

  printf("Sending code: 0x%x\n", str[i]);
  sender_thread_write_output(&packet);

  if (++i >= strlen(str)) {
    i = 0;
  }
}
