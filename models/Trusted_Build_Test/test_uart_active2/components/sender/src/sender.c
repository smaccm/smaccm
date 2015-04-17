#include <smaccm_sender.h>
#include <stdio.h>

char alpha = 'A';

char getNextAlpha() {
  char result = alpha;
  alpha++;
  if (alpha > 'Z') {
    alpha = 'A';
  }
  return result;
}

void send(const uint64_t *time) {
  uart__packet_i packet;
  packet.uart_num = 0;
  packet.datum = getNextAlpha();

  printf("Sending code: 0x%x --> %c\n", packet.datum, packet.datum);
  sender_write_output(&packet);
}
