/*
 * Copyright 2016, Data61
 * Commonwealth Scientific and Industrial Research Organisation (CSIRO)
 * ABN 41 687 119 230.
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(D61_BSD)
 */

#include <spi_inf.h>
#include <platsupport/spi.h>
#include <platsupport/gpio.h>
#include <string.h>
#include <camkes.h>
#include <errno.h>
#include <utils/ansi.h>
#include <utils/zf_log.h>


#define SPI_PORT          SPI0
#define SPI_SPEED_DEFAULT 10000000

#define SPI_CS_RELEASE 1
#define SPI_CS_ASSERT  0

#define CLK_RESET_PADDR 0x60006000
#define CLK_RST_CONTROLLER_RST_DEVICES_H_0_OFFSET 0x8
#define SWR_SPI1_RST_SHIFT 9
#define SWR_SPI1_RST (1 << SWR_SPI1_RST_SHIFT)

#define CLK_RST_CONTROLLER_CLK_OUT_ENB_H_0 0x14
#define CLK_ENB_SPI1_SHIFT 9
#define CLK_ENB_SPI1 (1 << CLK_ENB_SPI1_SHIFT)

#define CLK_RST_CONTROLLER_CLK_SOURCE_SPI1_0_OFFSET 0x134
#define CLK_RST_CONTROLLER_CLK_OUT_ENB_H_0_OFFSET 0x14
#define CLK_RST_CONTROLLER_CLK_SOURCE_SHIFT 29

struct spi_slave {
    int id;
    spi_dev_port_t** port;
    void(*cs)(int state);
    spi_slave_config_t cfg;
};


/**
 * Slave parameters. An ID in the table should match the id of an incoming request
 */
#define SLAVE_PARAMS(i, p, s, d, f, g)  \
        {                               \
            .id = i,                    \
            .port = (spi_dev_port_t**)p,\
            .cs = g,                    \
            .cfg = {                    \
                .speed_hz = s,          \
                .nss_udelay = d,        \
                .fb_delay = f           \
             }                          \
        }

const static struct spi_slave slave_params[] = {
    SLAVE_PARAMS(CAN_APP_ID, &spi1_can, 10000000,  10, 1, NULL),
};

/// A handle to the SPI bus that this component drives
static spi_bus_t* spi_bus;
clock_sys_t clock_sys;
int cur_slave_id = -1;

/**
 * Pulls the slave configuration from the database
 */
static const struct spi_slave*
get_slave(int id)
{
    int i;
    for (i = 0; i < ARRAY_SIZE(slave_params); i++) {
        if (slave_params[i].id == id) {
            return &slave_params[i];
        }
    }
    return NULL;
}

static void
chip_select(spi_bus_t* slave, int state)
{
    gpio_spi_can1_cs(state);
}

/**
 * SPI driver calls this when the transfer is complete.
 * All we need to do is store the status and post on the semaphore
 * that the main thread is waiting on.
 */
static void
spi_complete_callback(spi_bus_t* bus, int status, void* token)
{
    *(int*)token = status;
    ZF_LOGD("Posting to bus semaphore");
    bus_sem_post();

    ZF_LOGD("Posted to bus semaphore");
}

/**
 * Called on every SPI IRQ. Redirect control to the driver
 */
void
spi1_int_handle(void)
{
    spi_mutex_lock();
    ZF_LOGD("Handling spi irq event");
    spi_handle_irq(spi_bus);
    spi_mutex_unlock();
    spi1_int_acknowledge();
}

/* Camkes entry point */
void
spi__init(void)
{
    int err;

    /* Initialise the SPI bus */
    err = tegra_spi_init(SPI_PORT, spi1_reg, chip_select, NULL, &clock_sys, &spi_bus);
    assert(!err);
    if (err) {
        ZF_LOGE("Failed to initialise SPI port");
        return;
    }

    /* Prime the semaphore such that the first call to 'wait' will block */
    bus_sem_wait();
}

/**
 * Performs an SPI transfer
 */
static int
do_spi_transfer(const struct spi_slave* slave, void* txbuf, unsigned int wcount,
                void* rxbuf, unsigned int rcount)
{
    int ret;
    int status;

    if (cur_slave_id != slave->id) {
        spi_prepare_transfer(spi_bus, &slave->cfg);
        cur_slave_id = slave->id;
    }
    /* Begin the transfer */
    spi_mutex_lock();
    ret = spi_xfer(spi_bus, txbuf, wcount, rxbuf, rcount, spi_complete_callback, &status);
    spi_mutex_unlock();
    ZF_LOGD("spi_xfer done");
    if (ret >= 0) {
        bus_sem_wait();
        ret = status;
    }
    return ret;
}


uint8_t loopback_tx[] = {0x40, 0x24, 0x60, 0, 0, 0x8, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8};

/**
 * Initiate the transfer of shared data
 */
int
spi_transfer(int id, unsigned int wcount, unsigned int rcount)
{
    const struct spi_slave* slave;
    /* Find the slave configuration */
    slave = get_slave(1);

    assert(slave);
    if (slave == NULL) {
        return -1;
    }
    ZF_LOGD("id: %d\n", id);
    if (99 == id) {
        // FIXME why is this a special case?
        ZF_LOGD("id: 99\n");
        do_spi_transfer(slave, loopback_tx, 14, (*slave->port)->rxbuf, rcount);
    }
    /* Transfer the data from the shared data port */
    return do_spi_transfer(slave, (*slave->port)->txbuf, wcount, (*slave->port)->rxbuf, rcount);
}

/**
 * Initiate the transfer of a single byte, passed as an argument. Return the byte received.
 */
int
spi_transfer_byte(int id, char byte)
{
    const struct spi_slave* slave;
    char data[2];
    int ret;
    /* Find the slave configuration */
    slave = get_slave(id);
    if (slave == NULL) {
        return -1;
    }
    /* Transfer the data from the provided arguments */
    ret = do_spi_transfer(slave, &byte, 1, data, 1);
    if (ret < 0) {
        return ret;
    } else {
        return data[1];
    }
}
