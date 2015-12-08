#include "smaccm_top_i_types.h"

#define receiveBuffLen 512;
ivory_string_UartPacket receivedData[receiveBuffLen];

static int buffBegin = 0;
static int buffEnd = 0;

//TODO actually finish this logic
void run(void){
    printf("Running UART component\n");

    uart_state_sem_wait();
    //fill up buffer
    assert((buffBegin + 1) % receiveBuffLen != buffEnd);
    uart_state_sem_post();

}

void uart_rx(ivory_string_UartPacket * result){
    printf("UART component received request to receive data\n");

    uart_state_sem_wait();
    *result = receivedData[0];
    uart_state_sem_post();
    
}


void uart_tx(ivory_string_UartPacket input){
    printf("UART component received request to transmit data\n");
    printf("The first element of this data is %d\n", input.ivory_string_UartPacket_data[0]);
}
