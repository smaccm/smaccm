#include "smaccm_top_i_types.h"
#include <PixhawkProxy.h>

//SMACCM_DATA__PixHawkData_i vehicle_state;
//extern SMACCM_DATA__PixHawkData_i pixhawk_data;

void entry(void){
  printf("Starting PixHawkProxy\n");
  pixhawk_data->Output_parse_frame_0 = 0;
}

void request_data_pixhawk_data(bool * result){
    static int x = 0;
    x++;
    printf("Pixhawk Proxy received request for data\n");
    pixhawk_data->Output_parse_frame_0 = x;
    *result = true;
}
