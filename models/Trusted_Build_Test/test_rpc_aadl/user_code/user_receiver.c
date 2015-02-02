#include <smaccm_receiver.h>
#include <receiver.h>

void aadl_add_subtract_add(test_rpc_aadl__a_struct_impl *A, uint32_t B, uint32_t *result) {
	*result = A->field2 + B;
}

void aadl_add_subtract_subtract(uint32_t A, uint32_t B, test_rpc_aadl__a_struct_impl *result) {
	result->field2 = A - B;
}
