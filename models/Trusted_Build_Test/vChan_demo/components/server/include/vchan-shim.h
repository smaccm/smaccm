/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include "vmm/vmm.h"
#include "vmm/vchan_copy.h"
#include "vmm/vchan_sharemem.h"

int vchan_con_new_connection(vchan_connect_t con);
int vchan_con_rem_connection(vchan_connect_t con);
intptr_t vchan_con_get_buf(vchan_ctrl_t args, int cmd);
int vchan_con_status(vchan_ctrl_t args);
int vchan_con_alert_status(vchan_ctrl_t args);
void vchan_con_ping();
void vevent_wait(void);
int vevent_poll(void);
int vevent_reg_callback(void (*callback)(void*), void *arg);
bool vevent_in_write_void();

#define share_mem ((vchan_headers_t *)buff1)
