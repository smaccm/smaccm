#include <smaccm_sender.h>
#include <stdio.h>

static int len = 1;
static int delta = 1;

void send(const uint64_t *time) {
    uart__packet_i packet;
    for (int i = 0; i < len; i++) {
	packet.payload[i] = 'A' + i;
    }
    packet.payload[len] = '\n';
    packet.length = len + 1;
    
    printf("Sending packet: %s", packet.payload);
    sender_write_output(&packet);

    if (len == 26) {
	delta = -1;
    } else if (len == 1) {
	delta = 1;
    }
    len += delta;
}

void notify(void) {
    printf("Received completion notification\n");
}
