#include "smaccm_top_i_types.h"
#include <Server.h>

SMACCM_DATA__ServerOutputData_i outputData;

//extern SMACCM_DATA__PixHawkData_i pixhawk_data;

void input_input(SMACCM_DATA__ServerInputData_i input){
    printf("Server is receiving input\n");
    bool newPixhawkData;
    pixhawk_notify_pixhawk_data(&newPixhawkData);

    //only access the pixhawk_data structure within these semaphores
    pixhawk_state_sem_wait();
    printf("The first field of the pixhawk data is %d\n", pixhawk_data->Output_parse_frame_0);
    pixhawk_state_sem_post();

    output_notify_output(newPixhawkData);

    
}
