/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.5
 */
#ifndef __COMMSECENCODESTATE_MONITOR_H__
#define __COMMSECENCODESTATE_MONITOR_H__
#ifdef __cplusplus
extern "C" {
#endif
#include "ivory.h"
#include "hx_cyphertext.h"
#include "smaccm_UART_module.h"
#include "towerUartDeps.h"
#include "frame_datalink_encode_monitor.h"
#include <gec.h>
extern bool validkey;
extern struct gec_sym_key ctx_dl_global_gec_sym_key_enc;
void callback_gec_encode_init(const uint8_t n_var0[24U]);
void callback_plaintext_encode(const uint8_t n_var0[80U]);
void ctx_dl_encode_init(const uint8_t n_var0[24U]);
uint32_t ctx_dl_encode_run(const uint8_t n_var0[80U], uint8_t n_var1[96U]);

#ifdef __cplusplus
}
#endif
#endif /* __COMMSECENCODESTATE_MONITOR_H__ */
