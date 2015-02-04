#include <smaccm_compb.h>
#include <compbcompb.h>

void aadl_do_doCall(LOIConfig_AADL *aadlConfig, int32_t *result) {

	struct LOIConfig ivoryConfig;

	ivoryConfig.vehicleType = aadlConfig->vehicletype;
	ivoryConfig.vehicleSubType = aadlConfig->vehiclesubtype;
	ivoryConfig.vehicleId = aadlConfig->vehicleid;

	*result = doCall(&ivoryConfig);
}



