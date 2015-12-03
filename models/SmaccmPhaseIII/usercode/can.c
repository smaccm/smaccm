#include "smaccm_top_i_types.h"
SMACCM_DATA__CanSenderData_i receiveData;

void run(void){
    printf("Running CAN Component\n");
}

//note that this is used to both receive data over the CAN
//and receive results from the driver about how the transmit
//went (e.g., success or abort)
void can_rx(SMACCM_DATA__CanSenderData_i * result){
    printf("CAN component received receive request\n");
    *result = receiveData;
}

void can_tx(can_message input){
    printf("CAN component received transmit request\n");
}

