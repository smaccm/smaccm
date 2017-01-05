/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include <stdio.h>
#include <stdint.h>
#include <autoconf.h>

#include <camkes.h>

int run(void) {
    SMACCM_DATA__Camera_Bounding_Box_i box;
    box.left = 0;
    box.top = 0;
    while (1) {
        for (int i = 0; i < 200; i++) {
            box.right = i;
            box.bottom = i;
            self2server_write_SMACCM_DATA__Camera_Bounding_Box_i(&box);
        }
    }
    return 0;
}

bool server2self_reboot_write_bool(const bool *arg) {
    printf("Reboot requested (not implemented)\n");
    return true;
}
