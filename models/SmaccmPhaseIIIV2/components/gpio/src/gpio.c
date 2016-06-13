/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

#include <platsupport/mux.h>
#include <platsupport/gpio.h>
#include <platsupport/irq_combiner.h>

#include "utils.h"

#include "gpio.h"

#define UART0_CTSN  GPIOID(GPA0, 2)
#define UART0_RTSN  GPIOID(GPA0, 3)

#define CAN_CSn     XEINT16
#define CAN_INTn    XEINT15
#define CAN_RESETn  XEINT25
#define MPU_CS      XEINT14
#define MPU_INT     XEINT8
#define ACC_MAG_CS  XEINT21
#define ACC_INT     XEINT18
#define MAG_INT     XEINT23
#define GYRO_CS     XEINT11
#define GYRO_INT    XEINT20
#define BARO_CS     XEINT10
#define SPI_EXT_CS  XEINT13
#define SPI_EXT_INT XEINT19
#define PPM_GPIO    XEINT5
#define LIDAR_INT   UART0_CTSN

#define CAN_EINT_CIRQ      XEINT15_CIRQ
#define MPU_EINT_CIRQ      XEINT8_CIRQ

#define CAN_EINT_IRQ       60
#define MPU_EINT_IRQ       63
#define ACC_EINT_IRQ       64
#define MAG_EINT_IRQ       64
#define GYRO_EINT_IRQ      64
#define SPI_EXT_EINT_IRQ   64

#define PPM_IRQ            58
#define PPM_CIRQ           XEINT5_CIRQ

//#define LIDAR_EINT_IRQ_CIRQ

#define CS_FUNC(gpio)             \
    void                          \
    gpio_##gpio(int state)        \
    {                             \
        if(state)                 \
            gpio_set(&o_##gpio);  \
        else                      \
            gpio_clr(&o_##gpio);  \
    }


mux_sys_t exynos_mux;
gpio_sys_t gpio_sys;
irq_combiner_t irq_combiner;

/* SPI chip select */
gpio_t o_spi_can_nss;
gpio_t o_spi_mpu_nss;
gpio_t o_spi_acc_mag_nss;
gpio_t o_spi_gyro_nss;
gpio_t o_spi_baro_nss;
gpio_t o_spi_ext_nss;
CS_FUNC(spi_can_nss);
CS_FUNC(spi_mpu_nss);
CS_FUNC(spi_acc_mag_nss);
CS_FUNC(spi_gyro_nss);
CS_FUNC(spi_baro_nss);
CS_FUNC(spi_ext_nss);

/* SPI slave private IRQ */
gpio_t i_spi_can_int;
gpio_t i_spi_mpu_int;
gpio_t i_spi_acc_int;
gpio_t i_spi_mag_int;
gpio_t i_spi_gyro_int;
gpio_t i_spi_ext_int;
/* Lidar interrupt */
gpio_t i_lidar_int;

/* CAN reset */
gpio_t o_can_resetn;

/* PPM Input */
gpio_t i_ppm;

static void
irq_grp26_event(void* arg){
    if(gpio_is_pending(&i_ppm)){
        gpio_pending_clear(&i_ppm);
        printf("               <<PPM INT>>\n");
        /* TODO: Call handler */
    }
    irq_grp26_int_reg_callback(&irq_grp26_event, NULL);
}

static void
irq_grp28_event(void * arg){
    if(gpio_is_pending(&i_spi_mpu_int)){
        gpio_pending_clear(&i_spi_mpu_int);
        printf("               <<MPU INT>>\n");
        /* TODO: Call handler */
    }
    irq_grp28_int_reg_callback(&irq_grp28_event, NULL);
}

static void
irq_grp31_event(void *arg){
    if(gpio_is_pending(&i_spi_can_int)){
        gpio_pending_clear(&i_spi_can_int);
	CANInt_emit();
    }
    irq_grp31_int_reg_callback(&irq_grp31_event, NULL);
}

static void
irq_xint16_31_event(void *arg){
#if 0
    if(gpio_is_pending(&i_spi_acc_int)){
        gpio_pending_clear(&i_spi_acc_int);
        printf("               <<ACC INT>>\n");
        /* TODO: Call handler */
    }
    if(gpio_is_pending(&i_spi_mag_int)){
        gpio_pending_clear(&i_spi_mag_int);
        printf("               <<MAG INT>>\n");
        /* TODO: Call handler */
    }
    if(gpio_is_pending(&i_spi_gyro_int)){
        gpio_pending_clear(&i_spi_gyro_int);
        printf("               <<GYRO INT>>\n");
        /* TODO: Call handler */
    }
#endif
    if(gpio_is_pending(&i_spi_ext_int)){
        gpio_pending_clear(&i_spi_ext_int);
        printf("               <<SPI EXT INT>>\n");
        /* TODO: Call handler */
    }
    xint16_31_int_reg_callback(&irq_xint16_31_event, NULL);
}


void gpio__init(void) {
    exynos_mux_init(gpio1base, gpio2base, NULL, NULL, &exynos_mux);
    exynos_gpio_sys_init(&exynos_mux, &gpio_sys);
    exynos_irq_combiner_init(irqcbase, &irq_combiner);

    /* Enable UART0, UART3 and SPI0. */
    mux_feature_enable(&exynos_mux, MUX_UART0);
    mux_feature_enable(&exynos_mux, MUX_UART1);
    mux_feature_enable(&exynos_mux, MUX_UART3);
    mux_feature_enable(&exynos_mux, MUX_SPI1);

    /* SPI chip selects */
    gpio_new(&gpio_sys, CAN_CSn,    GPIO_DIR_OUT, &o_spi_can_nss);
    gpio_new(&gpio_sys, MPU_CS,     GPIO_DIR_OUT, &o_spi_mpu_nss);
    gpio_new(&gpio_sys, ACC_MAG_CS, GPIO_DIR_OUT, &o_spi_acc_mag_nss);
    gpio_new(&gpio_sys, GYRO_CS,    GPIO_DIR_OUT, &o_spi_gyro_nss);
    gpio_new(&gpio_sys, BARO_CS,    GPIO_DIR_OUT, &o_spi_baro_nss);
    gpio_new(&gpio_sys, SPI_EXT_CS, GPIO_DIR_OUT, &o_spi_ext_nss);
    gpio_set(&o_spi_can_nss);
    gpio_set(&o_spi_mpu_nss);
    gpio_set(&o_spi_acc_mag_nss);
    gpio_set(&o_spi_gyro_nss);
    gpio_set(&o_spi_baro_nss);
    gpio_set(&o_spi_ext_nss);
    /* SPI private IRQ */
    gpio_new(&gpio_sys, CAN_INTn,    GPIO_DIR_IRQ_FALL, &i_spi_can_int);
    gpio_new(&gpio_sys, MPU_INT,     GPIO_DIR_IRQ_FALL, &i_spi_mpu_int);
    //gpio_new(&gpio_sys, ACC_INT,     GPIO_DIR_IRQ_FALL, &i_spi_acc_int);
    //gpio_new(&gpio_sys, MAG_INT,     GPIO_DIR_IRQ_FALL, &i_spi_mag_int);
    //gpio_new(&gpio_sys, GYRO_INT,    GPIO_DIR_IRQ_FALL, &i_spi_gyro_int);
    gpio_new(&gpio_sys, SPI_EXT_INT, GPIO_DIR_IRQ_FALL, &i_spi_ext_int);

    /* LIDAR sync IRQ  */
    gpio_new(&gpio_sys, LIDAR_INT,   GPIO_DIR_IRQ_FALL, &i_lidar_int);

    /* CAN reset */
    gpio_new(&gpio_sys, CAN_RESETn,  GPIO_DIR_OUT,      &o_can_resetn);
    gpio_set(&o_can_resetn);

    /* PPM */
    gpio_new(&gpio_sys, PPM_GPIO,    GPIO_DIR_IRQ_FALL, &i_ppm);

    /* Configure IRQs that appear on the combiner */
    irq_combiner_enable_irq(&irq_combiner, CAN_EINT_CIRQ);
    irq_combiner_enable_irq(&irq_combiner, MPU_EINT_CIRQ);
    irq_combiner_enable_irq(&irq_combiner, PPM_CIRQ);
    xint16_31_int_reg_callback(&irq_xint16_31_event, NULL);
    irq_grp28_int_reg_callback(&irq_grp28_event, NULL);
    irq_grp31_int_reg_callback(&irq_grp31_event, NULL);
    irq_grp26_int_reg_callback(&irq_grp26_event, NULL);
}

int gpio_mmc_config(int peripheral, int flags)
{
	/* TODO: To be moved to libplatsupport. */
    return -1;
}





int run(void)
{
	while (0) {
		if (!gpio_get(&i_spi_can_int)){
			printf("Interrupt Pass ...\n");
			break;
		}
	}
    return 0;
}
