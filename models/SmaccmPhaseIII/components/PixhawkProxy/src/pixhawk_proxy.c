#include "smaccm_top_i_types.h"
#include <PixhawkProxy.h>

//SMACCM_DATA__PixHawkData_i vehicle_state;
//extern SMACCM_DATA__PixHawkData_i pixhawk_data;

SMACCM_DATA__CanSenderData_i can_data;
void entry(void){
  printf("Starting PixHawkProxy\n");
  static int x = 0;
  x++;

  can_rx(&can_data);
  //critical section for the pixhawk_data
  pixhawk_state_sem_wait();  
  pixhawk_data->Output_parse_frame_0 = x;
  pixhawk_state_sem_post();  

}

void request_data_pixhawk_data(bool * result){
    static int x = 0;
    //x++;
    printf("Pixhawk Proxy received request for data\n");
    //pixhawk_data->Output_parse_frame_0 = x;
    *result = true;
}
