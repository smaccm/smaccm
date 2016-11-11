#ifndef __c3_MON_Functional_h__
#define __c3_MON_Functional_h__

/* Include files */
#include "sf_runtime/sfc_sf.h"
#include "sf_runtime/sfc_mex.h"
#include "rtwtypes.h"
#include "multiword_types.h"

/* Type Definitions */
#ifndef typedef_SFc3_MON_FunctionalInstanceStruct
#define typedef_SFc3_MON_FunctionalInstanceStruct

typedef struct {
  SimStruct *S;
  ChartInfoStruct chartInfo;
  uint32_T chartNumber;
  uint32_T instanceNumber;
  int32_T c3_sfEvent;
  boolean_T c3_doneDoubleBufferReInit;
  uint8_T c3_is_active_c3_MON_Functional;
  uint8_T c3_doSetSimStateSideEffects;
  const mxArray *c3_setSimStateSideEffectsInfo;
  boolean_T *c3_p;
  int32_T *c3_c;
  int32_T *c3_pre_c;
} SFc3_MON_FunctionalInstanceStruct;

#endif                                 /*typedef_SFc3_MON_FunctionalInstanceStruct*/

/* Named Constants */

/* Variable Declarations */
extern struct SfDebugInstanceStruct *sfGlobalDebugInstanceStruct;

/* Variable Definitions */

/* Function Declarations */
extern const mxArray *sf_c3_MON_Functional_get_eml_resolved_functions_info(void);

/* Function Definitions */
extern void sf_c3_MON_Functional_get_check_sum(mxArray *plhs[]);
extern void c3_MON_Functional_method_dispatcher(SimStruct *S, int_T method, void
  *data);

#endif
