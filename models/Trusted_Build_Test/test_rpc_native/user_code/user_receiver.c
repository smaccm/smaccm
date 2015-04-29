#include <smaccm_receiver.h>
#include <receiver.h>

void add_subtract_add(const test_rpc_native__a_struct_impl A, const uint32_t B, uint32_t *result) {
	*result = A.field2 + B;
}

void add_subtract_subtract(const uint32_t *A, const uint32_t B, test_rpc_native__a_struct_impl *result) {
	result->field2 = *A - B;
}
