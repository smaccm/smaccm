#include <smaccm_receiver.h>
#include <stdio.h>

void receive(const uart__packet_i * input) {
  printf("Received input: 0x%x --> %c\n", input->datum);
}
