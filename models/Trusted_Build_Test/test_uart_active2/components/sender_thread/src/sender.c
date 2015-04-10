#include <smaccm_sender_thread.h>
#include <string.h>
#include <stdio.h>

char *str = "CHARMANDER\n";
int i = 0;

char getNextCharmander() {
  char result = str[i];
  i++;
  if (i >= strlen(str)) {
    i = 0;
  }
  return result;
}


char alpha = 'A';

char getNextAlpha() {
  char result = alpha;
  alpha++;
  if (alpha > 'Z') {
    alpha = 'A';
  }
  return result;
}

void send(const uint64_t * periodic_100_ms) {
  test_uart__uart_packet_i packet;
  packet.uart_num = 0;
  packet.charmander = getNextAlpha();

  printf("Sending code: 0x%x --> %c\n", packet.charmander, packet.charmander);
  sender_thread_write_output(&packet);
}
