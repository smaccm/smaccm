#ifndef __c4_MON_sl_after_replacement3_h__
#define __c4_MON_sl_after_replacement3_h__

/* Include files */
#include "sf_runtime/sfc_sf.h"
#include "sf_runtime/sfc_mex.h"
#include "rtwtypes.h"
#include "multiword_types.h"

/* Type Definitions */
#ifndef typedef_SFc4_MON_sl_after_replacement3InstanceStruct
#define typedef_SFc4_MON_sl_after_replacement3InstanceStruct

typedef struct {
  SimStruct *S;
  ChartInfoStruct chartInfo;
  uint32_T chartNumber;
  uint32_T instanceNumber;
  int32_T c4_sfEvent;
  boolean_T c4_doneDoubleBufferReInit;
  uint8_T c4_is_active_c4_MON_sl_after_replacement3;
  boolean_T c4_Primary_Side;
  uint8_T c4_doSetSimStateSideEffects;
  const mxArray *c4_setSimStateSideEffectsInfo;
  boolean_T *c4_Sync_From_Active;
  boolean_T *c4_Active;
  boolean_T *c4_pre_Active;
  boolean_T *c4_Error;
  boolean_T *c4_Sync_From_Error;
} SFc4_MON_sl_after_replacement3InstanceStruct;

#endif                                 /*typedef_SFc4_MON_sl_after_replacement3InstanceStruct*/

/* Named Constants */

/* Variable Declarations */
extern struct SfDebugInstanceStruct *sfGlobalDebugInstanceStruct;

/* Variable Definitions */

/* Function Declarations */
extern const mxArray
  *sf_c4_MON_sl_after_replacement3_get_eml_resolved_functions_info(void);

/* Function Definitions */
extern void sf_c4_MON_sl_after_replacement3_get_check_sum(mxArray *plhs[]);
extern void c4_MON_sl_after_replacement3_method_dispatcher(SimStruct *S, int_T
  method, void *data);

#endif
