#include "smaccm_top_i_types.h"
#include <Server.h>

SMACCM_DATA__ServerOutputData_i outputData;
SMACCM_DATA__CameraData_i camera_data;

//This is the RPC called by the "Input" component
//It should get the state the pixhawk by calling
//"pixhawk_notify_pixhawk_data" to determine if
//new data is ready. It should also get the latest
//camera data by calling
void input_input(SMACCM_DATA__ServerInputData_i input){
    printf("Server is receiving input\n");
    bool newPixhawkData;
    pixhawk_notify_pixhawk_data(&newPixhawkData);

    //only access the pixhawk_data structure within this semaphore
    pixhawk_state_sem_wait();
    printf("The first field of the pixhawk data is %d\n", pixhawk_data->Output_parse_frame_0);
    pixhawk_state_sem_post();

    //get the latest camera data
    camera_data_camera_data(&camera_data);
    printf("The camera bounding box is: (l,r,t,b) = (%d,%d,%d,%d)\n",
            camera_data.left, camera_data.right, camera_data.top, camera_data.bottom);

    //the pixhawk_data and camera_data information should be 
    //copied to the "outputData" structure. Then "output_notify_output"
    //should be called with argument "true" if everything is ready to go.

    output_notify_output(true);

    
}
