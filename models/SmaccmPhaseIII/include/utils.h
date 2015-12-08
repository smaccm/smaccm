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
 * utils.h
 *
 *  Created on: Aug 6, 2013
 *      Author: jxie
 */

#ifndef UTILS_H_
#define UTILS_H_


/**
 * Frequently used helper functions.
 */
#include <stdint.h>
#include <stdlib.h>
#include <stdbool.h>

/**
 * Read from 32-bit register.
 */
inline uint32_t readl(const void *address)
{
	//printf("reading @ %x\n", address);
	return *((volatile uint32_t *)(address));
}

/**
 * Write to 32-bit register.
 */
inline void writel(uint32_t value, const void *address)
{
	//printf("writing %x @ %x\n", value, address);
	*((volatile uint32_t *)(address)) = value;
}

inline void spinlock_lock(int *lock)
{
	while (!__sync_bool_compare_and_swap(lock, 0, 1));
}

inline void spinlock_unlock(int *lock)
{
	while (!__sync_bool_compare_and_swap(lock, 1, 0));
}

inline void barrier(void)
{
	__sync_synchronize();
}

#define swab(x) __be32_to_cpu(x)

static inline uint32_t
__be32_to_cpu(uint32_t x){
    int i;
    uint32_t ret;
    char* a = (char*)&x;
    char* b = (char*)&ret;
    for(i = 0; i < sizeof(x); i++){
        b[i] = a[sizeof(x) - i - 1];
    }
    return ret;
}

#if defined(PLAT_EXYNOS5250)
#define DEFAULT_CPUFREQ 1000000000UL
#elif defined(PLAT_EXYNOS5410)
#define DEFAULT_CPUFREQ 1000000000UL
#elif defined(PLAT_EXYNOS4)
#define DEFAULT_CPUFREQ 1000000000UL
#elif defined(PLAT_IMX6)
#define DEFAULT_CPUFREQ  792000000UL
#else
#define DEFAULT_CPUFREQ 0UL
#endif


static unsigned long cpufreq_hint = DEFAULT_CPUFREQ;
#define CYCLES_PER_US (cpufreq_hint / 1000000)

void ps_udelay(unsigned long us);

static void
ps_do_cycle_delay(int32_t cycles)
{
    /* Loop while the number of required instructions is +ve
     * We unfold the loop to avoid branch predictor optimisation.
     * We tolerate some error if the number of instruction is not
     * a multiple of 16.
     */
    asm volatile(
        "1:"
        "    subs %0, %0, #1;"  /*  1 */
        "    subs %0, %0, #1;"  /*  2 */
        "    subs %0, %0, #1;"  /*  3 */
        "    subs %0, %0, #1;"  /*  4 */
        "    subs %0, %0, #1;"  /*  5 */
        "    subs %0, %0, #1;"  /*  6 */
        "    subs %0, %0, #1;"  /*  7 */
        "    subs %0, %0, #1;"  /*  8 */
        "    subs %0, %0, #1;"  /*  9 */
        "    subs %0, %0, #1;"  /* 10 */
        "    subs %0, %0, #1;"  /* 11 */
        "    subs %0, %0, #1;"  /* 12 */
        "    subs %0, %0, #1;"  /* 13 */
        "    subs %0, %0, #1;"  /* 14 */
        "    subs %0, %0, #1;"  /* 15 */
        "    subs %0, %0, #1;"  /* 16 */
        "    bpl 1b" : "+r"(cycles) );
}

void
ps_udelay(unsigned long us)
{
    if (cpufreq_hint == 0) {
        printf("%s:%d - Unable to determine CPU frequency for delay loop\n", __FILE__, __LINE__);
        cpufreq_hint = 2 * 1000 * 1000 * 1000;
    }
    while (us--) {
        ps_do_cycle_delay(CYCLES_PER_US);
    }
}

/* A guess based on a 800MHz memory clock */
static __inline__ void udelay(uint64_t usec)
{
    // volatile static int x;
    // while (usec--) {
    //     for (x = 0; x < 4000; x++)
    //         ;
    // }
    ps_udelay(usec / 10);
}

static __inline__ int min(int a, int b)
{
    return a > b ? b : a;
}

static __inline__ void _acquire_spin_lock(volatile bool* lock){
	while(!__sync_bool_compare_and_swap(lock,0,1)){
		//seL4_Yield();
	}
}

static __inline__ void _release_spin_lock(volatile bool* lock){
	//release lock
	//*lock = 0;
	__sync_bool_compare_and_swap(lock,1,0);
}

static __inline__ void clrbits_le32(uint32_t * reg, uint32_t mask){
	*reg = *reg & (~mask);
}

static __inline__ void setbits_le32(uint32_t * reg, uint32_t mask){
	*reg = *reg | mask;
}

static __inline__ void clrsetbits_le32(uint32_t *reg, uint32_t clrm, uint32_t setm){
	*reg = ((*reg) & (~clrm)) | (setm);
}


/*
 * General Purpose Utilities
 */
#define min(X, Y)				\
	({ typeof(X) __x = (X);			\
		typeof(Y) __y = (Y);		\
		(__x < __y) ? __x : __y; })

#define max(X, Y)				\
	({ typeof(X) __x = (X);			\
		typeof(Y) __y = (Y);		\
		(__x > __y) ? __x : __y; })

#define min3(X, Y, Z)				\
	({ typeof(X) __x = (X);			\
		typeof(Y) __y = (Y);		\
		typeof(Z) __z = (Z);		\
		__x < __y ? (__x < __z ? __x : __z) :	\
		(__y < __z ? __y : __z); })

#define max3(X, Y, Z)				\
	({ typeof(X) __x = (X);			\
		typeof(Y) __y = (Y);		\
		typeof(Z) __z = (Z);		\
		__x > __y ? (__x > __z ? __x : __z) :	\
		(__y > __z ? __y : __z); })

#define MIN3(x, y, z)  min3(x, y, z)
#define MAX3(x, y, z)  max3(x, y, z)

/*
 * Return the absolute value of a number.
 *
 * This handles unsigned and signed longs, ints, shorts and chars.  For all
 * input types abs() returns a signed long.
 *
 */
#define abs(x) ({						\
		long ret;					\
		if (sizeof(x) == sizeof(long)) {		\
			long __x = (x);				\
			ret = (__x < 0) ? -__x : __x;		\
		} else {					\
			int __x = (x);				\
			ret = (__x < 0) ? -__x : __x;		\
		}						\
		ret;						\
	})

#endif /* UTILS_H_ */
