/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.5
 */
#include "CAN_Framing.h"

static void component_entry_aux(void);

static void component_entry_aux_2(void);

static void component_entry_aux_3(void);

void component_entry(const int64_t *n_var0)
{
    component_entry_aux();
    component_entry_aux_2();
    component_entry_aux_3();
    return;
}

void component_init(const int64_t *n_var0)
{ }

void component_entry_aux(void)
{
    uint8_t n_local0[80U] = {};
    uint8_t *n_ref1 = n_local0;
    bool n_r2 = tb_server2self_dequeue(n_ref1);
    
    if (n_r2) {
        callback_input_tb_server2self_dequeue_handler(n_ref1);
    }
    return;
}

void component_entry_aux_2(void)
{
    struct SMACCM_DATA__CAN_Frame_i n_local0 = {};
    struct SMACCM_DATA__CAN_Frame_i *n_ref1 = &n_local0;
    bool n_r2 = tb_can2self_frame_dequeue(n_ref1);
    
    if (n_r2) {
        callback_input_tb_can2self_frame_dequeue_handler(n_ref1);
    }
    return;
}

void component_entry_aux_3(void)
{
    bool n_local0 = false;
    bool *n_ref1 = &n_local0;
    bool n_r2 = tb_can2self_status_dequeue(n_ref1);
    
    if (n_r2) {
        callback_input_tb_can2self_status_dequeue_handler(n_ref1);
    }
    return;
}
