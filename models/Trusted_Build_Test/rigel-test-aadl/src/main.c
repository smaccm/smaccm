/*
Unpublished copyright (c) 2013 National ICT Australia (NICTA),
ABN 62 102 206 173.  All rights reserved.

The contents of this document are proprietary to NICTA and you may not
use, copy, modify, sublicense or distribute the contents in any form
except as permitted under the terms of a separately executed licence
agreement with NICTA.

COMMERCIAL LICENSE RIGHTS
Agreement No.: FA8750-12-9-0179
Contractor's Name; Rockwell Collins, Inc.
Contractor's Address: 400 Collins Road N.E., Cedar Rapids, IA 52498

By accepting delivery of the RTOS Code and Documentation, the Licensee
agrees that the software is "commercial" computer software within the
meaning of the applicable acquisition regulations (e.g., FAR 2.101 or
DFARS 227.7202-3).  The terms and conditions of this License shall pertain
to the Licensee's use and disclosure of the software, and shall supersede
any conflicting contractual terms or conditions.

*/
#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>

#include "rtos-kochab.h"

extern void debug_println(const char *msg);

#define SYST_CSR_REG 0xE000E010
#define SYST_RVR_REG 0xE000E014
#define SYST_CVR_REG 0xE000E018

#define SYST_CSR_READ() (*((volatile uint32_t*)SYST_CSR_REG))
#define SYST_CSR_WRITE(x) (*((volatile uint32_t*)SYST_CSR_REG) = x)

#define SYST_RVR_READ() (*((volatile uint32_t*)SYST_RVR_REG))
#define SYST_RVR_WRITE(x) (*((volatile uint32_t*)SYST_RVR_REG) = x)

#define SYST_CVR_READ() (*((volatile uint32_t*)SYST_CVR_REG))
#define SYST_CVR_WRITE(x) (*((volatile uint32_t*)SYST_CVR_REG) = x)


int
main(void)
{
    /* Set the systick reload value */
    SYST_RVR_WRITE(0x000fffff);
    SYST_CVR_WRITE(0);
    SYST_CSR_WRITE((1 << 1) | 1);

    debug_println("Starting RTOS");
    rtos_start();
    /* Should never reach here, but if we do, an infinite loop is
       easier to debug than returning somewhere random. */
    for (;;) ;
}
