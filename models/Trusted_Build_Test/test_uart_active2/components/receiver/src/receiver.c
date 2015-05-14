#include <smaccm_receiver.h>
#include <stdio.h>

void receive(const uint8_t *input) {
    printf("Received input: 0x%x --> %c\n", *input, *input);
}
