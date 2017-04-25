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

#include <platsupport/chardev.h>
#include <platsupport/serial.h>
#include <utils/util.h>
#include <camkes.h>

//#define BAUD_RATE 115200
#define BAUD_RATE 57600

#ifdef CONFIG_PLAT_EXYNOS5410
#define DEV_ID  PS_SERIAL1
#elif CONFIG_PLAT_IMX31
#define DEV_ID  IMX31_UART1
#else
#error
#endif

struct uart_token {
    size_t cur_bytes;
    size_t req_bytes;
    char* buf;
};

static ps_chardevice_t serial_device;

void
interrupt_handle()
{
    ps_cdev_handle_irq(&serial_device, 0);
    interrupt_acknowledge();
}

void pre_init(void)
{
    /* Initialise the UART */
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
    interrupt_acknowledge();
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
	const bool b = true; // This value is ignored
	self2encrypt_write_bool(&b);
    }
}

bool encrypt2self_write_SMACCM_DATA__UART_Packet_i(const SMACCM_DATA__UART_Packet_i *packet) {
    struct uart_token token;

    token.cur_bytes = 0;
    token.req_bytes = packet->buf_len;
    token.buf = (char*) packet->buf;
    if (ps_cdev_write(&serial_device, token.buf, token.req_bytes, &write_callback, &token) < 0) {
        printf("Error writing to UART\n");
        return false;
    }

    return true;
}

int run(void)
{
    struct SMACCM_DATA__UART_Packet_i p;

    for(;;) {
        p.buf_len = uart_read((char*) p.buf, 255);
        bool b = self2decrypt_write_SMACCM_DATA__UART_Packet_i(&p);
        if (!b) {
            printf("ERROR: UART Driver: Unable to put UART packet in queue\n");
        }
    }
    return 0;
}
