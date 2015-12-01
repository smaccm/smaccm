#include "smaccm_top_i_types.h"

SMACCM_DATA__ServerOutputData_i outputData;
void input_input(SMACCM_DATA__ServerInputData_i input){
    printf("Server is receiving input\n");

    output_output(outputData);
    
}
