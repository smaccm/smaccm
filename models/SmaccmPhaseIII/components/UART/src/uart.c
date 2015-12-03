#include "smaccm_top_i_types.h"

ivory_string_UartPacket receivedData;

void run(void){
    printf("Running UART component\n");
}

void uart_rx(ivory_string_UartPacket * result){
    printf("UART component received request to receive data\n");
    *result = receivedData;
}


void uart_tx(ivory_string_UartPacket input){
    printf("UART component received request to transmit data\n");
    printf("The first element of this data is %d\n", input.ivory_string_UartPacket_data[0]);
}
