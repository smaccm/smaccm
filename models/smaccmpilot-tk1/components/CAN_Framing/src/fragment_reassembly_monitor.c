/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.5
 */
#include "fragment_reassembly_monitor.h"

uint8_t reassembly_next_idx_0x100 = (uint8_t) 0U;

uint8_t reassembly_buf_0x100[80U];

void callback_receive_msg(const struct can_message *n_var0)
{
    uint32_t n_deref0 = n_var0->can_message_id;
    bool n_cse8 = (bool) (0 != (uint8_t) (uint16_t) ((uint16_t) (uint32_t) ((uint32_t) (n_deref0 >> (uint32_t) 1U) & (uint32_t) 1U) & (uint16_t) 255U));
    
    if ((bool) !n_cse8) {
        uint32_t n_cse10 = (uint32_t) ((uint32_t) (n_deref0 >> (uint32_t) 2U) & (uint32_t) 536870911U);
        uint32_t n_cse12 = (uint32_t) (n_cse8 ? n_cse10 : (uint32_t) (n_cse10 >> (uint32_t) 18U));
        
        if ((bool) (n_cse12 >= (uint32_t) 256U)) {
            uint8_t n_deref1 = reassembly_next_idx_0x100;
            uint32_t n_cse14 = (uint32_t) (n_cse12 - (uint32_t) 256U);
            uint8_t n_cse21 = (uint8_t) ((bool) (n_cse14 <= (uint32_t) UINT8_MAX) ? (uint8_t) n_cse14 : 0);
            uint8_t n_cse22 = (uint8_t) ((uint8_t) 1U + n_cse21);
            
            if ((bool) !(bool) ((bool) (n_cse22 == n_deref1) || (bool) (n_cse21 > (uint8_t) 9U))) {
                int32_t n_deref2 = n_var0->can_message_len;
                
                if ((bool) ((bool) !(bool) ((bool) (0 == n_cse21) || (bool) (n_cse21 == n_deref1)) || (bool) (n_deref2 != (int32_t) 8))) {
                    reassembly_next_idx_0x100 = (uint8_t) 0U;
                } else {
                    int32_t n_cse34 = (int32_t) ((int32_t) 8 * (int32_t) n_cse21);
                    
                    ASSERTS((bool) ((bool) (n_cse34 >= (int32_t) 0) && (bool) (n_cse34 < (int32_t) 80)));
                    ASSERTS((bool) ((bool) (n_deref2 >= (int32_t) 0) && (bool) (n_deref2 <= (int32_t) 8)));
                    for (int32_t n_ix3 = (int32_t) 0; n_ix3 <= (int32_t) 7; n_ix3++) {
                        if ((bool) (n_ix3 < n_deref2)) {
                            int32_t n_cse48 = (int32_t) (n_cse34 + n_ix3);
                            
                            if ((bool) (n_cse48 < (int32_t) 80)) {
                                uint8_t n_deref4 = n_var0->can_message_buf[n_ix3];
                                
                                reassembly_buf_0x100[n_cse48 % 80] = n_deref4;
                            }
                        }
                    }
                    if ((bool) (n_cse21 < (uint8_t) 9U)) {
                        reassembly_next_idx_0x100 = n_cse22;
                    } else {
                        uint8_t n_local5[80U] = {};
                        uint8_t *n_ref6 = n_local5;
                        
                        for (int32_t n_ix7 = (int32_t) 0; n_ix7 <= (int32_t) 79; n_ix7++) {
                            ivory_serialize_unpack_uint8_be((const uint8_t *) reassembly_buf_0x100, (uint32_t) n_ix7, &n_ref6[n_ix7]);
                        }
                        callback_output_tb_self2server_enqueue_2(n_ref6);
                    }
                }
            }
        }
    }
}
