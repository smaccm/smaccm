#ifndef __c3_MON_sl_after_replacement_h__
#define __c3_MON_sl_after_replacement_h__

/* Include files */
#include "sf_runtime/sfc_sf.h"
#include "sf_runtime/sfc_mex.h"
#include "rtwtypes.h"
#include "multiword_types.h"

/* Type Definitions */
#ifndef typedef_SFc3_MON_sl_after_replacementInstanceStruct
#define typedef_SFc3_MON_sl_after_replacementInstanceStruct

typedef struct {
  SimStruct *S;
  ChartInfoStruct chartInfo;
  uint32_T chartNumber;
  uint32_T instanceNumber;
  int32_T c3_sfEvent;
  boolean_T c3_doneDoubleBufferReInit;
  uint8_T c3_is_active_c3_MON_sl_after_replacement;
  uint8_T c3_MANUAL;
  uint8_T c3_LO;
  uint8_T c3_MED;
  uint8_T c3_MAX;
  uint8_T c3_doSetSimStateSideEffects;
  const mxArray *c3_setSimStateSideEffectsInfo;
  int32_T *c3_pre_mode;
  boolean_T *c3_rise_lo;
  boolean_T *c3_rise_med;
  boolean_T *c3_rise_max;
  int32_T *c3_r;
} SFc3_MON_sl_after_replacementInstanceStruct;

#endif                                 /*typedef_SFc3_MON_sl_after_replacementInstanceStruct*/

/* Named Constants */

/* Variable Declarations */
extern struct SfDebugInstanceStruct *sfGlobalDebugInstanceStruct;

/* Variable Definitions */

/* Function Declarations */
extern const mxArray
  *sf_c3_MON_sl_after_replacement_get_eml_resolved_functions_info(void);

/* Function Definitions */
extern void sf_c3_MON_sl_after_replacement_get_check_sum(mxArray *plhs[]);
extern void c3_MON_sl_after_replacement_method_dispatcher(SimStruct *S, int_T
  method, void *data);

#endif
