#include "smaccm_top_i_types.h"

SMACCM_DATA__CameraData_i camera_data;

void run(void){
    printf("Running VM Component\n");
}

void camera_data_camera_data(SMACCM_DATA__CameraData_i * result){
    static int x = 0;
    printf("VM Component is getting request for camera data");
    camera_data.left = x++;
    camera_data.right = x++;
    camera_data.top = x++;
    camera_data.bottom = x++;

    *result = camera_data;
}
