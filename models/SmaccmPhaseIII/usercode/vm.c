#include "smaccm_top_i_types.h"

SMACCM_DATA__CameraData_i camera_data;

void run(void){
    printf("Running VM Component\n");
}

void camera_data_camera_data(SMACCM_DATA__CameraData_i * result){
    printf("VM Component is getting request for camera data");
    *result = camera_data;
}
