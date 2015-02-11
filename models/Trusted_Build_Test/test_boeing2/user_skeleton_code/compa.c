#include <smaccm_compa.h>
#include <compacompa.h>

int32_t doCall(struct LOIConfig *ivoryConfig) {

	LOIConfig_AADL aadlConfig;
	int32_t result; 

	aadlConfig.vehicletype = ivoryConfig->vehicleType;
	aadlConfig.vehiclesubtype = ivoryConfig->vehicleSubType;
	aadlConfig.vehicleid = ivoryConfig->vehicleId;

	aadl_do_doCall(&aadlConfig, &result);
	
	return result;
}

void periodic_entrypoint(uint32_t periodic_250_ms) {
	caller(); 
}


