/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.5
 */
#include "att_control_debug_types.h"

void att_control_debug_get_le(const uint8_t *n_var0, uint32_t n_var1, struct att_control_debug *n_var2)
{
    ivory_serialize_unpack_float_le(n_var0, n_var1, &n_var2->head_setpt);
    ivory_serialize_unpack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 4U), &n_var2->head_rate_setpt);
    ivory_serialize_unpack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 8U), &n_var2->head_ctl_p);
    ivory_serialize_unpack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 12U), &n_var2->head_ctl_d);
    ivory_serialize_unpack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 16U), &n_var2->pitch_setpt);
    ivory_serialize_unpack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 20U), &n_var2->pitch_rate_setpt);
    ivory_serialize_unpack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 24U), &n_var2->roll_setpt);
    ivory_serialize_unpack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 28U), &n_var2->roll_rate_setpt);
}

void att_control_debug_get_be(const uint8_t *n_var0, uint32_t n_var1, struct att_control_debug *n_var2)
{
    ivory_serialize_unpack_float_be(n_var0, n_var1, &n_var2->head_setpt);
    ivory_serialize_unpack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 4U), &n_var2->head_rate_setpt);
    ivory_serialize_unpack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 8U), &n_var2->head_ctl_p);
    ivory_serialize_unpack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 12U), &n_var2->head_ctl_d);
    ivory_serialize_unpack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 16U), &n_var2->pitch_setpt);
    ivory_serialize_unpack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 20U), &n_var2->pitch_rate_setpt);
    ivory_serialize_unpack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 24U), &n_var2->roll_setpt);
    ivory_serialize_unpack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 28U), &n_var2->roll_rate_setpt);
}

void att_control_debug_set_le(uint8_t *n_var0, uint32_t n_var1, const struct att_control_debug *n_var2)
{
    ivory_serialize_pack_float_le(n_var0, n_var1, &n_var2->head_setpt);
    ivory_serialize_pack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 4U), &n_var2->head_rate_setpt);
    ivory_serialize_pack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 8U), &n_var2->head_ctl_p);
    ivory_serialize_pack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 12U), &n_var2->head_ctl_d);
    ivory_serialize_pack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 16U), &n_var2->pitch_setpt);
    ivory_serialize_pack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 20U), &n_var2->pitch_rate_setpt);
    ivory_serialize_pack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 24U), &n_var2->roll_setpt);
    ivory_serialize_pack_float_le(n_var0, (uint32_t) (n_var1 + (uint32_t) 28U), &n_var2->roll_rate_setpt);
}

void att_control_debug_set_be(uint8_t *n_var0, uint32_t n_var1, const struct att_control_debug *n_var2)
{
    ivory_serialize_pack_float_be(n_var0, n_var1, &n_var2->head_setpt);
    ivory_serialize_pack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 4U), &n_var2->head_rate_setpt);
    ivory_serialize_pack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 8U), &n_var2->head_ctl_p);
    ivory_serialize_pack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 12U), &n_var2->head_ctl_d);
    ivory_serialize_pack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 16U), &n_var2->pitch_setpt);
    ivory_serialize_pack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 20U), &n_var2->pitch_rate_setpt);
    ivory_serialize_pack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 24U), &n_var2->roll_setpt);
    ivory_serialize_pack_float_be(n_var0, (uint32_t) (n_var1 + (uint32_t) 28U), &n_var2->roll_rate_setpt);
}
