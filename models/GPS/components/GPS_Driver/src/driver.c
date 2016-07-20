/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

#include <stdio.h>
#include <string.h>

#include <platsupport/chardev.h>
#include <platsupport/serial.h>
#include <utils/util.h>
#include <camkes.h>
#include <ubx.h>

//#define BAUD_RATE 4800
//#define BAUD_RATE 9600
#define BAUD_RATE 38400
//#define BAUD_RATE 57600
//#define BAUD_RATE 115200

#define DEV_ID PS_SERIAL3

struct uart_token {
    size_t cur_bytes;
    size_t req_bytes;
    char* buf;
};

static ps_chardevice_t serial_device;

static void
interrupt_event(void* token)
{
    ps_chardevice_t* device;
    device = (ps_chardevice_t*)token;
    ps_cdev_handle_irq(device, 0);
    interrupt_reg_callback(&interrupt_event, token);
}

void pre_init(void)
{
    /* Iniitialise the UART */
    printf("Initialising UART driver\n");
    if(exynos_serial_init(DEV_ID, (void *) vaddr, NULL, NULL, &serial_device)){
        printf("Failed to initialise UART\n");
        while(1);
    }
    serial_configure(&serial_device, BAUD_RATE, 8, PARITY_NONE, 1);
    serial_device.flags &= ~SERIAL_AUTO_CR;

    /* Prime semaphore */
    read_sem_wait();

    /* Register for IRQs */
    interrupt_reg_callback(&interrupt_event, &serial_device);
}

static void
read_callback(ps_chardevice_t* device, enum chardev_status stat,
              size_t bytes_transfered, void* token){
    struct uart_token* t;
    t = (struct uart_token*)token;
    /* We might get a short read due to a timeout. */
    t->cur_bytes += bytes_transfered;
    t->buf += bytes_transfered;
    read_sem_post();
}

int uart_read(char *c, int rsize)
{
    struct uart_token token;

    token.cur_bytes = 0;
    token.req_bytes = rsize;
    token.buf = c;
    if(ps_cdev_read(&serial_device, token.buf, token.req_bytes, &read_callback, &token) < 0){
        printf("Error reading from UART\n");
        return -1;
    }
    read_sem_wait();

    return token.cur_bytes;
}

static void
write_callback(ps_chardevice_t* device, enum chardev_status stat,
               size_t bytes_transfered, void* token) {
    struct uart_token* t;
    t = (struct uart_token*) token;
    t->cur_bytes += bytes_transfered;
    if (t->cur_bytes == t->req_bytes) {
    }
}

bool write(char *buf, int buf_len) {
    struct uart_token token;

    token.cur_bytes = 0;
    token.req_bytes = buf_len;
    token.buf = (char*) buf;
    if (ps_cdev_write(&serial_device, token.buf, token.req_bytes, &write_callback, &token) < 0) {
        printf("Error writing to UART\n");
        return false;
    }

    return true;
}

static void handle_nav_sol(struct UBX_nav_sol *nav_sol) {
    char *gpsFixText = nav_sol->gpsFix < 0x06 ? GPS_FIX_TEXT[nav_sol->gpsFix] : "Reserved";
    printf("GPS Fix: %s\n", gpsFixText);
    printf("3D Position Accuracy Estimate: %.2fm\n", nav_sol->pAcc / 100.0);
}

static void handle_nav_posllh(struct UBX_nav_posllh *nav_posllh) {
    float lat = nav_posllh->lat * 1.0e-7;
    float lon = nav_posllh->lon * 1.0e-7;
    printf("Latitude: %f\n", lat);
    printf("Longitude: %f\n", lon);
    printf("http://maps.google.com/?q=%f,%f\n", lat, lon);
}

static void handle_payload(uint8_t class, uint8_t id, uint16_t len, union UBX_payload* payload) {
    if (class == UBX_nav_class && id == UBX_nav_sol_id) {
	if (len == UBX_nav_sol_length) {
	    handle_nav_sol(&payload->nav_sol);
	} else {
	    printf("Bad length %d for UBX NAV-SOL\n", len);
	}
    } else if (class == UBX_nav_class && id == UBX_nav_posllh_id) {
	if (len == UBX_nav_posllh_length) {
	    handle_nav_posllh(&payload->nav_posllh);
	} else {
	    printf("Bad length %d for UBX NAV-POSLLH\n", len);
	}
    }
}


#define MAX_PAYLOAD 100

static int handle_packet(uint8_t *buf, int n) {
    if (n < 8) {
	// Not enough bytes for even a minimal packet
	return n;
    }

    if (buf[0] != UBX_sync1) {
	printf("Bad sync1: %02x\n", buf[0]);
	return 1;
    }
    if (buf[1] != UBX_sync2) {
	printf("Bad sync2: %02x\n", buf[1]);
	return 2;
    }
    
    uint8_t class = buf[2];
    uint8_t id = buf[3];
    uint16_t len = buf[4] | (buf[5] << 8);

    if (len + 2 > n) {
	// Not enough bytes for payload + checksums
	return n;
    }

    if (len > MAX_PAYLOAD) {
	printf("Payload too large\n");
	return 6 + len + 2;
    }

    uint8_t payload[MAX_PAYLOAD];
    memcpy(payload, buf + 6, len);

    uint8_t ck_a = buf[6 + len];
    uint8_t ck_b = buf[6 + len + 1];

    uint8_t computed_ck_a = 0;
    uint8_t computed_ck_b = 0;
    for (int i = 2; i < 6 + len; i++) {
	computed_ck_a += buf[i];
	computed_ck_b += computed_ck_a;
    }

    if (ck_a != computed_ck_a || ck_b != computed_ck_b) {
	printf("Bad checksum %02x %02x %02x %02x\n", ck_a, ck_b, computed_ck_a, computed_ck_b);
	return 6 + len + 2;
    }

    handle_payload(class, id, len, (union UBX_payload*) payload);

    return 6 + len + 2;
}

int run(void)
{
    char buf[255];
    int i, n;

    for(;;) {
        n = uart_read((char*) buf, 255);
/*
	printf("Read %d bytes\n", n);
	for (i = 0; i < n; i++) {
	    printf(" %02x", buf[i]);
	    if (i % 10 == 9 && i + 1 < n) {
		printf("\n");
	    }
	}
	printf("\n\n");
*/
	i = 0;
	while (i < n) {
	    i += handle_packet(buf + i, n - i);
	}
	printf("\n");
    }
    return 0;
}
