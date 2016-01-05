/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

/*
 * spi external interfaces
 */

#ifndef SPI_INF_H
#define SPI_INF_H

#include <stdbool.h>
#include <stdint.h>

#define SPI_TRANS_MAX_SIZE    255

typedef struct spi_buf_t{
    uint8_t txbuf[SPI_TRANS_MAX_SIZE];
    uint8_t rxbuf[SPI_TRANS_MAX_SIZE];
    volatile bool lock;                    //shared buffer lock
}spi_dev_port, *spi_dev_port_p;

#endif
