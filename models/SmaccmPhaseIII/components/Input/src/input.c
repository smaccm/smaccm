#include "smaccm_top_i_types.h"

ivory_string_UartPacket inputPacket;
SMACCM_DATA__ServerInputData_i outputData;

//This is the entry point. It is periodically dispatched
void entry(void){

  printf("Staring Input\n");

  //receives packets from the uart and 
  //puts then in "inputPacket"
  uart_rx(&inputPacket);

  //sends data to the "Output" component
  to_svr_input(outputData);

}
