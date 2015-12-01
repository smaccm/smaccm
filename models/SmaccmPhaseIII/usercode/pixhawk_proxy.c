#include "smaccm_top_i_types.h"

SMACCM_DATA__PixHawkData_i vehicle_state;

void entry(void){
  printf("Starting PixHawkProxy\n");
}

void pixhawk_data_pixhawk_data(SMACCM_DATA__PixHawkData_i * result){
    printf("Pixhawk Proxy received request for data\n");
    *result = vehicle_state;
}
