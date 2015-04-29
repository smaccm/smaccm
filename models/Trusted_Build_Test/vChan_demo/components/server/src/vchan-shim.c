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

#include <server.h>
#include "smaccm_vChan_demo_impl_types.h"

#include "vmm/vmm.h"
#include "vmm/vchan_copy.h"
#include "vmm/vchan_sharemem.h"

#include "vchan-shim.h"

#define DPRINTF(num, ...) printf(__VA_ARGS__);

/*
void copy_vChan_demo__structs_vchan_ctrl(vchan_ctrl_t *dest,  vchan_ctrl_t *src) {
   dest->domain = src->domain;
   dest->dest = src->dest;
   dest->thePort = src->port;
}

void copy_vChan_demo__structs_vchan_connect(vchan_connect_t *dest, vchan_connect_t *src) {
   dest->server = src->server;
   dest->eventfd = src->eventfd;
   dest->event_mon = src->event_mon;
   copy_vChan_demo__structs_vchan_ctrl(&(dest->v), &(src->v));
}
*/

int vchan_con_new_connection(vchan_connect_t con) {
   // int32_t ret;
   // vChan_demo__structs_vchan_connect conp;
   // copy_vChan_demo__structs_vchan_connect(&conp, &con);
   return (int)rpc_new_connection(con);
}

int vchan_con_rem_connection(vchan_connect_t con) {
//   int32_t ret;
   // vChan_demo__structs_vchan_connect conp;
   // copy_vChan_demo__structs_vchan_connect(&conp, &con);
   return (int)rpc_rem_connection(con);
}

intptr_t vchan_con_get_buf(vchan_ctrl_t args, int cmd) {
   // int32_t ret;
   // vChan_demo__structs_vchan_ctrl argsp;
   // copy_vChan_demo__structs_vchan_ctrl(&argsp, &args);
   return (int)rpc_get_buf(args, (int32_t)cmd);
}

int vchan_con_status(vchan_ctrl_t args) {
//   int32_t ret;
   // vChan_demo__structs_vchan_ctrl argsp;
   // copy_vChan_demo__structs_vchan_ctrl(&argsp, &args);
   return (int)rpc_status(args);
}

int vchan_con_alert_status(vchan_ctrl_t args) {
//   int32_t ret;
//   vChan_demo__structs_vchan_ctrl argsp;
//   copy_vChan_demo__structs_vchan_ctrl(&argsp, &args);
   return (int)rpc_alert_status(args);
}

void vchan_con_ping() {
   rpc_ping();
}

void vevent_wait(void) {
  vevent_sem_wait();
}

int vevent_poll(void) {
  // TODO :
  // MWW: what is this supposed to do?
  return 0;
}

static void (*shim_callback)(void *) = NULL;
static void *shim_callback_arg = NULL;

int vevent_reg_callback(void (*callback)(void*), void *arg) {
  shim_callback = callback;
  shim_callback_arg = arg;
  return 0;
}

// MWW: this is the callback that I want so that AADL code can process the 
// 'raw' RPC and call this function.
// I want this function to call my callback function as well after 
// the sem_post().

bool vchan_shim_handle_vevent() {
  // set semaphore to be ued by wait and poll
  vevent_sem_post();

  // MWW: insert call to user callback HERE
  if (shim_callback != NULL) {
     shim_callback(shim_callback_arg);
  }
  return true;
}

// MWW: so I want to get rid of this function.
//bool vevent_write_void() {
  // set semaphore to be ued by wait and poll
//  smaccm_receiver_vevent_sem_post();
//}

