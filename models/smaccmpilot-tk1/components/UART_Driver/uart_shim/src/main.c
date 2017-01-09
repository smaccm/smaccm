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
#include <platsupport/serial.h>
#include <camkes.h>

#define BAUD_RATE 115200
//#define BAUD_RATE 57600

static ps_chardevice_t serial_device;

void
interrupt_handle()
{
    device_lock();
    ps_cdev_handle_irq(&serial_device, /* unused */ 0);
    device_unlock();

    interrupt_acknowledge();
}

void pre_init(void)
{
    ps_io_ops_t uart_shim_io_ops;
    if (camkes_io_ops(&uart_shim_io_ops) != 0) {
        printf("UART Shim: Failed to get IO-ops.\n");
        return;
    }

    device_lock();
    ps_chardevice_t *result = ps_cdev_init(TK1_UARTB_ASYNC, &uart_shim_io_ops, &serial_device);
    device_unlock();

    if (result == NULL) {
        printf("UART Shim: Failed to initialize UART B.\n");
        return;
    }

    // TODO: Set baud rate
    serial_device.flags &= ~SERIAL_AUTO_CR;

    printf("UART initialized\n");
}

static void write_callback(ps_chardevice_t* device,
                           enum chardev_status stat,
                           size_t bytes_transfered,
                           void* token) {
    const bool b = true;
    self2encrypt_write_bool(/* unused */ &b);
}

bool encrypt2self_write_SMACCM_DATA__UART_Packet_i(const SMACCM_DATA__UART_Packet_i *packet) {
    device_lock();
    int result = ps_cdev_write(&serial_device,
                               &packet->buf,
                               packet->buf_len,
                               &write_callback,
                               NULL);
    device_unlock();

    if (result != 0) {
        printf("UART Shim: error writing to UART\n");
        return false;
    }
    return true;
}

static struct SMACCM_DATA__UART_Packet_i read_packet;

void read_callback(ps_chardevice_t *device,
                   enum chardev_status stat,
                   size_t bytes_transfered,
                   void *token) {
    read_packet.buf_len = bytes_transfered;
    if (!self2decrypt_write_SMACCM_DATA__UART_Packet_i(&read_packet)) {
        printf("UART Shim: Unable to put UART packet in queue\n");
    }

    // Re-register
    // Don't device_lock() since this is called from within interrupt_handler()
    ps_cdev_read(&serial_device, read_packet.buf, 255, read_callback, NULL);
}

int run(void)
{
    device_lock();
    ps_cdev_read(&serial_device, read_packet.buf, 255, read_callback, NULL);
    device_unlock();
    return 0;
}
