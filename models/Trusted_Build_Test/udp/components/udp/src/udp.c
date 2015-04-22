#include <udp.h>

void putchar_putchar(int c) {
    printf("Putchar: %c\n", c);
}

bool client_input_write_udp__packet_i(const udp__packet_i * arg) {
    // Take packet from AADL interface and pass it to UDPServer
    int len = udp_send_send(arg->buffer, arg->len, arg->addr);
    return (len == arg->len);
}

int run(void) {
    while (1) {
	// TODO: Read UDP packets from UDPServer and pass them along on AADL interface
	// TODO: Right now we do this via callback but that does not rate limit at all
	sleep(1000);
    }

    return 0;
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
	    // TODO: Should we be doing this check or just rely on the underlying driver?
	    if (len > 4096) {
		len = 4096;
	    }
	    memcpy(packet.buffer, udp_recv_buf, len);
	    packet.len = len;
	    packet.addr = addr;
	    udp_client_output_write_udp__packet_i(&packet);
        }
    }
    udp_recv_ready_reg_callback(udp_has_data, cookie);
}

void pre_init() {
    udp_recv_ready_reg_callback(udp_has_data, NULL);
}
