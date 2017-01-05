#include <string.h>
#include <tb_smaccmcopter_types.h>
#include <camkes.h>
#include <stdio.h>

bool framing2self_write_SMACCM_DATA__CAN_Frame_i(const SMACCM_DATA__CAN_Frame_i *a_frame) {
    printf("CAN not implemented\n");
    return true;
}

int run(void) {
    return 0;
}
