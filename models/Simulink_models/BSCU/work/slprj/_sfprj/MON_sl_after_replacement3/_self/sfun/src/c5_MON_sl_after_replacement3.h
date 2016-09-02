#ifndef __c5_MON_sl_after_replacement3_h__
#define __c5_MON_sl_after_replacement3_h__

/* Include files */
#include "sf_runtime/sfc_sf.h"
#include "sf_runtime/sfc_mex.h"
#include "rtwtypes.h"
#include "multiword_types.h"

/* Type Definitions */
#ifndef typedef_SFc5_MON_sl_after_replacement3InstanceStruct
#define typedef_SFc5_MON_sl_after_replacement3InstanceStruct

typedef struct {
  SimStruct *S;
  ChartInfoStruct chartInfo;
  uint32_T chartNumber;
  uint32_T instanceNumber;
  int32_T c5_sfEvent;
  boolean_T c5_doneDoubleBufferReInit;
  uint8_T c5_is_active_c5_MON_sl_after_replacement3;
  uint8_T c5_doSetSimStateSideEffects;
  const mxArray *c5_setSimStateSideEffectsInfo;
  boolean_T *c5_p;
  int32_T *c5_c;
  int32_T *c5_pre_c;
} SFc5_MON_sl_after_replacement3InstanceStruct;

#endif                                 /*typedef_SFc5_MON_sl_after_replacement3InstanceStruct*/

/* Named Constants */

/* Variable Declarations */
extern struct SfDebugInstanceStruct *sfGlobalDebugInstanceStruct;

/* Variable Definitions */

/* Function Declarations */
extern const mxArray
  *sf_c5_MON_sl_after_replacement3_get_eml_resolved_functions_info(void);

/* Function Definitions */
extern void sf_c5_MON_sl_after_replacement3_get_check_sum(mxArray *plhs[]);
extern void c5_MON_sl_after_replacement3_method_dispatcher(SimStruct *S, int_T
  method, void *data);

#endif
