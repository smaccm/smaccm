/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

#include <spi_inf.h>
#include <platsupport/spi.h>
#include <platsupport/gpio.h>
#include <utils.h>
#include <string.h>
#include <spi.h>
#include <errno.h>

#include <common.h>

#define SPI_PORT          SPI1
#define SPI_SPEED_DEFAULT 10000000

#define SPI_CS_RELEASE 1
#define SPI_CS_ASSERT  0

struct spi_slave_config {
    int id;
    spi_dev_port_p* port;
    int speed_hz;
    int nss_usdelay;
    void (*cs)(int state);
};

/**
 * Slave parameters. An ID in the table should match the id of an incoming request 
 */
#define SLAVE_PARAMS(i, p, s, d, g)     \
        {                               \
            .id = i,                    \
            .port = (spi_dev_port_p*)p, \
            .speed_hz = s,              \
            .nss_usdelay = d,           \
            .cs = g                     \
        }

const static struct spi_slave_config slave_params[] = {
    SLAVE_PARAMS(CAN_APP_ID, &spi1_can, 10000000,  10, &gpio_spi_can_nss),
    SLAVE_PARAMS(-1        , NULL     ,        0,   0, &gpio_spi_mpu_nss),
    SLAVE_PARAMS(-1        , NULL     ,        0,   0, &gpio_spi_acc_mag_nss),
    SLAVE_PARAMS(-1        , NULL     ,        0,   0, &gpio_spi_gyro_nss),
    SLAVE_PARAMS(-1        , NULL     ,        0,   0, &gpio_spi_baro_nss),
    SLAVE_PARAMS(-1        , NULL     ,        0,   0, &gpio_spi_ext_nss)
};

/// A handle to the SPI bus that this component drives
static spi_bus_t* spi_bus;
/// The current speed of the bus to avoid unnecessary recalibration
static long spi_cur_speed;

/**
 * Pulls the slave configuration from the database
 */
static const struct spi_slave_config*
get_slave_config(int id)
{
    int i;
    for(i = 0; i < ARRAY_SIZE(slave_params); i++){
        if(slave_params[i].id == id){
            return &slave_params[i];
        }
    }
    return NULL;
}

static inline void
chip_select(const struct spi_slave_config* cfg, int state)
{
    cfg->cs(state);
    udelay(cfg->nss_usdelay);
}

static inline void
set_speed(const struct spi_slave_config* cfg)
{
    if(spi_cur_speed != cfg->speed_hz){
        clktree_set_spi1_freq(cfg->speed_hz);
        spi_cur_speed = cfg->speed_hz;
    }
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
    bus_sem_post();
}

/**
 * Called on every SPI IRQ. Redirect control to the driver
 */
void
spi_irq_event(void *arg)
{
    (void)arg;
    spi_handle_irq(spi_bus);
    spi1_int_reg_callback(&spi_irq_event, NULL);
}

/* Camkes entry point */
void
spi__init(void)
{
    int err;
    clktree_set_spi1_freq(SPI_SPEED_DEFAULT);
    spi_cur_speed = SPI_SPEED_DEFAULT;

    /* Initialise the SPI bus */
    err = exynos_spi_init(SPI_PORT, spi1_reg, NULL, NULL, &spi_bus); 
    assert(!err);
    if(err){
        LOG_ERROR("Failed to initialise SPI port\n");
        return;
    }
    /* Prime the semaphore such that the first call to 'wait' will block */
    bus_sem_wait();
    /* Register an IRQ callback for the driver */
    spi1_int_reg_callback(&spi_irq_event, spi_bus);
}

/**
 * Performs an SPI transfer
 */
static int
do_spi_transfer(const struct spi_slave_config* cfg, void* txbuf, unsigned int wcount, 
                void* rxbuf, unsigned int rcount)
{
    int ret;
    int status;

    set_speed(cfg);
    chip_select(cfg, SPI_CS_ASSERT);

    /* Begin the transfer */
    ret = spi_xfer(spi_bus, txbuf, wcount, rxbuf, rcount, spi_complete_callback, &status);
    if(ret >= 0){
        bus_sem_wait();
        ret = status;
    }

    chip_select(cfg, SPI_CS_RELEASE);
    return ret;
}

/**
 * Initiate the transfer of shared data
 */
int
spi_transfer(int id, unsigned int wcount, unsigned int rcount)
{
    const struct spi_slave_config* cfg;
    /* Find the slave configuration */
    cfg = get_slave_config(id);
    assert(cfg);
    if(cfg == NULL){
        return -1;
    }
    /* Transfer the data from the shared data port */
    return do_spi_transfer(cfg, (*cfg->port)->txbuf, wcount, (*cfg->port)->rxbuf, rcount);
}

/**
 * Initiate the transfer of a single byte, passed as an argument. Return the byte received.
 */
int
spi_transfer_byte(int id, char byte)
{
    const struct spi_slave_config* cfg;
    char data[2];
    int ret;
    /* Find the slave configuration */
    cfg = get_slave_config(id);
    assert(cfg);
    if(cfg == NULL){
        return -1;
    }
    /* Transfer the data from the provided arguments */
    ret = do_spi_transfer(cfg, &byte, 1, data, 1);
    if(ret < 0){
        return ret;
    }else{
        return data[1];
    }
}

