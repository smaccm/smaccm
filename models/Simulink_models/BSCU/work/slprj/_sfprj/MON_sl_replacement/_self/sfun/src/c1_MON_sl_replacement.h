#ifndef __c1_MON_sl_replacement_h__
#define __c1_MON_sl_replacement_h__

/* Include files */
#include "sf_runtime/sfc_sf.h"
#include "sf_runtime/sfc_mex.h"
#include "rtwtypes.h"
#include "multiword_types.h"

/* Type Definitions */
#ifndef typedef_SFc1_MON_sl_replacementInstanceStruct
#define typedef_SFc1_MON_sl_replacementInstanceStruct

typedef struct {
  SimStruct *S;
  ChartInfoStruct chartInfo;
  uint32_T chartNumber;
  uint32_T instanceNumber;
  int32_T c1_sfEvent;
  boolean_T c1_doneDoubleBufferReInit;
  uint8_T c1_is_active_c1_MON_sl_replacement;
  uint8_T c1_MANUAL;
  uint8_T c1_LO;
  uint8_T c1_MED;
  uint8_T c1_MAX;
  int32_T c1_BRAKE_PRESSURE_LO;
  int32_T c1_BRAKE_PRESSURE_MAX;
  int32_T c1_BRAKE_PRESSURE_MED;
  uint8_T c1_doSetSimStateSideEffects;
  const mxArray *c1_setSimStateSideEffectsInfo;
  int32_T *c1_mode;
  int32_T *c1_pedal;
  int32_T *c1_r;
} SFc1_MON_sl_replacementInstanceStruct;

#endif                                 /*typedef_SFc1_MON_sl_replacementInstanceStruct*/

/* Named Constants */

/* Variable Declarations */
extern struct SfDebugInstanceStruct *sfGlobalDebugInstanceStruct;

/* Variable Definitions */

/* Function Declarations */
extern const mxArray *sf_c1_MON_sl_replacement_get_eml_resolved_functions_info
  (void);

/* Function Definitions */
extern void sf_c1_MON_sl_replacement_get_check_sum(mxArray *plhs[]);
extern void c1_MON_sl_replacement_method_dispatcher(SimStruct *S, int_T method,
  void *data);

#endif
