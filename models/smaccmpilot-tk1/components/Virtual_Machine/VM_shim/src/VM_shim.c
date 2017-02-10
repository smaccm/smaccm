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

static void tb_server2self_reboot_callback(void *unused) {
    bool b;
    while (tb_server2self_reboot_dequeue(&b)) {
        printf("Reboot requested\n");
        restart_event_emit();
    }
    tb_server2self_reboot_notification_reg_callback(&tb_server2self_reboot_callback, NULL);
}

void pre_init(void) {
    tb_server2self_reboot_notification_reg_callback(&tb_server2self_reboot_callback, NULL);
}

int run(void) {
    SMACCM_DATA__Camera_Bounding_Box_i box;
    box.left = 0;
    box.top = 0;
    while (0) {
        for (int i = 0; i < 200; i++) {
            box.right = i;
            box.bottom = i;
            tb_self2server_write(&box);
        }
    }
    return 0;
}
