#include "smaccm_top_i_types.h"
#include <Output.h>

ivory_string_UartPacket sendData;

//This function is called by the "Server" component.
//It should copy data from "input_data" structure
//and send it via "can_tx" 
void input_notify_output(bool input){
    static char x;
    printf("Output component received data\n");
  
    x++;

    output_state_sem_wait();
    //access the "input_data" structure here
    output_state_sem_post();
    sendData.ivory_string_UartPacket_data[0] = x;

    //data is then sent to the uart
    uart_tx(sendData);
}
