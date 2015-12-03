#include "smaccm_top_i_types.h"

ivory_string_UartPacket inputPacket;
SMACCM_DATA__ServerInputData_i outputData;

void entry(void){

  printf("Staring Input\n");

  uart_rx(&inputPacket);
  to_svr_input(outputData);

}
