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

#include <sel4/sel4.h>
#include <sel4utils/util.h>

#include <VchanProxy.h>

#include "vmm/vmm.h"
#include "vmm/vchan_copy.h"
#include "vmm/vchan_sharemem.h"

#define DPRINTF(num, ...) printf(__VA_ARGS__);

int32_t rpc_in_new_connection(vchan_connect_t con) {
//  DPRINTF(DBG_SERVER, "Proxy: new connection \n");
   return (int32_t)vchan_com_out_new_connection(con);
}


int32_t rpc_in_rem_connection(vchan_connect_t con) {
//  DPRINTF(DBG_SERVER, "Proxy: rem connection \n");
   return (int32_t)vchan_com_out_rem_connection(con);
}

int32_t rpc_in_get_buf(vchan_ctrl_t con, int32_t cmd) {
//  DPRINTF(DBG_SERVER, "Proxy: get buff \n");
   return (int32_t)vchan_com_out_get_buf(con, (int)cmd);
}

int32_t rpc_in_status(vchan_ctrl_t con) {
//  DPRINTF(DBG_SERVER, "Proxy: in status \n");
  return (int32_t)vchan_com_out_status(con);
}

int32_t rpc_in_alert_status(vchan_ctrl_t con) {
//  DPRINTF(DBG_SERVER, "Proxy: alert status \n");
  return (int32_t)vchan_com_out_alert_status(con);
}

void rpc_in_ping() {
//  DPRINTF(DBG_SERVER, "Proxy: ping \n");
  vchan_com_out_ping();
}

void vevent_out_callback(void *arg) {
//  DPRINTF(DBG_SERVER, "Proxy: vevent out callback \n");
 vevent_in_write_void();
//  DPRINTF(DBG_SERVER, "Proxy: vevent out sent with result %d \n", result);
  vevent_out_reg_callback(vevent_out_callback, arg);
}

int run(void) {
//  DPRINTF(DBG_SERVER, "Proxy: started \n");
  vevent_out_reg_callback(vevent_out_callback, NULL);
  return 0;
}
