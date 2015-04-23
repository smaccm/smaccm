#include <unistd.h>
#include <string.h>
#include <udp.h>

bool client_input_write_udp__packet_i(const udp__packet_i * arg) {
    // Take packet from AADL interface and pass it to UDPServer
    ip_addr_t addr = { .addr = arg-> addr };
    int len = udp_send_send((uintptr_t) arg->buffer, arg->len, addr);
    return (len == arg->len);
}

int run(void) {
    while (1) {
	// TODO: Read UDP packets from UDPServer and pass them along on AADL interface
	// TODO: Right now we do this via callback but that does not rate limit at all
	sleep(1);
    }

    return 0;
}

void local_memcpy(uint8_t *dest, char volatile *src, size_t n) {
    for (int i = 0; i < n; i++) {
	dest[i] = src[i];
    }
}

void udp_has_data(void *cookie) {
    int status = 0;
    while (status == 0) {
        unsigned int len;
        uint16_t port;
        ip_addr_t addr; 
        status = udp_recv_poll(&len, &port, &addr);
        if (status != -1) {
	    udp__packet_i packet;
	    int max_len = sizeof(packet.buffer) / sizeof(packet.buffer[0]);
	    if (len > max_len) {
		len = max_len;
	    }
	    local_memcpy(packet.buffer, (char volatile *) udp_recv_buf, len);
	    packet.len = len;
	    packet.addr = addr.addr;
	    udp_client_output_write_udp__packet_i(&packet);
        }
    }
    udp_recv_ready_reg_callback(udp_has_data, cookie);
}

void pre_init() {
    udp_recv_ready_reg_callback(udp_has_data, NULL);
}
