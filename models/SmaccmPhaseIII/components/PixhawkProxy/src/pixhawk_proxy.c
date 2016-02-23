#include "smaccm_top_i_types.h"
#include <PixhawkProxy.h>

//SMACCM_DATA__PixHawkData_i vehicle_state;
//extern SMACCM_DATA__PixHawkData_i pixhawk_data;

SMACCM_DATA__CanSenderData_i can_data;

//This function is called whenever the PixHawkProxy component
//is dispatched. It is scheduled periodically. it should
//call "can_rx" to determine if can data has been received
//recently. If so it should copy this data to the "pixhawk_data"
//structure.
void entry(void){
  printf("Starting PixHawkProxy\n");
  static int x = 0;
  x++;

  can_rx(&can_data);
  //critical section for the pixhawk_data.
  //this is where you will put the data written
  //to "can_data" and put it into the "pixhawk_data"
  //structure.  The "Server" component reads the
  //"pixhawk_data" whenever the "request_data"pixhawk_data"
  //function sets the result variable to true. Right now
  //I am just incrementing the the value of the first field
  //for demonstration

  pixhawk_state_sem_wait();  
  pixhawk_data->Output_parse_frame_0 = x;
  pixhawk_state_sem_post();  

}

//This function sets "result" to true if the pixhawk's
//state has been updated.
void request_data_pixhawk_data(bool * result){
    printf("Pixhawk Proxy received request for data\n");
    *result = true;
}
