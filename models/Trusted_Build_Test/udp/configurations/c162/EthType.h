/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */
#ifndef VMM_CONFIG_ETHTYPE_H
#define VMM_CONFIG_ETHTYPE_H

typedef struct EthDriverMMIO {
    char buf[0x80000];
} EthDriverMMIO_t;

#endif
