/* This file has been autogenerated by Ivory
 * Compiler version  0.1.0.5
 */
#ifndef __XYZ_TYPES_H__
#define __XYZ_TYPES_H__
#ifdef __cplusplus
extern "C" {
#endif
#include "ivory.h"
#include "ivory_serialize.h"
typedef struct xyz {
            float x;
            float y;
            float z;
        } xyz;
void xyz_get_le(const uint8_t *n_var0, uint32_t n_var1, struct xyz *n_var2);
void xyz_get_be(const uint8_t *n_var0, uint32_t n_var1, struct xyz *n_var2);
void xyz_set_le(uint8_t *n_var0, uint32_t n_var1, const struct xyz *n_var2);
void xyz_set_be(uint8_t *n_var0, uint32_t n_var1, const struct xyz *n_var2);

#ifdef __cplusplus
}
#endif
#endif /* __XYZ_TYPES_H__ */
