#ifndef __c2_MON_sl_after_replacement_h__
#define __c2_MON_sl_after_replacement_h__

/* Include files */
#include "sf_runtime/sfc_sf.h"
#include "sf_runtime/sfc_mex.h"
#include "rtwtypes.h"
#include "multiword_types.h"

/* Type Definitions */
#ifndef struct_PanelMsgImpl_tag
#define struct_PanelMsgImpl_tag

struct PanelMsgImpl_tag
{
  boolean_T MAX;
  boolean_T MED;
  boolean_T LO;
};

#endif                                 /*struct_PanelMsgImpl_tag*/

#ifndef typedef_c2_PanelMsgImpl
#define typedef_c2_PanelMsgImpl

typedef struct PanelMsgImpl_tag c2_PanelMsgImpl;

#endif                                 /*typedef_c2_PanelMsgImpl*/

#ifndef struct_SyncMsgImpl_tag
#define struct_SyncMsgImpl_tag

struct SyncMsgImpl_tag
{
  boolean_T Active;
  boolean_T Error;
};

#endif                                 /*struct_SyncMsgImpl_tag*/

#ifndef typedef_c2_SyncMsgImpl
#define typedef_c2_SyncMsgImpl

typedef struct SyncMsgImpl_tag c2_SyncMsgImpl;

#endif                                 /*typedef_c2_SyncMsgImpl*/

#ifndef struct_ChannelStatusImpl_tag
#define struct_ChannelStatusImpl_tag

struct ChannelStatusImpl_tag
{
  boolean_T Error;
  boolean_T Active;
  int32_T Cmd;
};

#endif                                 /*struct_ChannelStatusImpl_tag*/

#ifndef typedef_c2_ChannelStatusImpl
#define typedef_c2_ChannelStatusImpl

typedef struct ChannelStatusImpl_tag c2_ChannelStatusImpl;

#endif                                 /*typedef_c2_ChannelStatusImpl*/

#ifndef typedef_SFc2_MON_sl_after_replacementInstanceStruct
#define typedef_SFc2_MON_sl_after_replacementInstanceStruct

typedef struct {
  SimStruct *S;
  ChartInfoStruct chartInfo;
  uint32_T chartNumber;
  uint32_T instanceNumber;
  int32_T c2_sfEvent;
  boolean_T c2_doneDoubleBufferReInit;
  uint8_T c2_is_active_c2_MON_sl_after_replacement;
  int32_T c2_pre_c_Agree_Nodes__Duration_0;
  boolean_T c2_pre_c_Agree_Nodes__Duration_0_not_empty;
  boolean_T c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0_not_empty;
  int32_T c2_pre_mode_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty;
  boolean_T c2_pre_Active;
  boolean_T c2_pre_Active_not_empty;
  boolean_T c2_first_time;
  boolean_T c2_first_time_not_empty;
  boolean_T c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0_not_empty;
  boolean_T c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0_not_empty;
  boolean_T c2_pre_r_Agree_Nodes__Latch_0;
  boolean_T c2_pre_r_Agree_Nodes__Latch_0_not_empty;
  c2_PanelMsgImpl *c2_Panel;
  int32_T *c2_CMD_From_COM;
  c2_SyncMsgImpl *c2_Sync_From;
  int32_T *c2_Pedal;
  boolean_T *c2_Fail;
  c2_SyncMsgImpl *c2_Sync_To;
  c2_ChannelStatusImpl *c2_Status;
} SFc2_MON_sl_after_replacementInstanceStruct;

#endif                                 /*typedef_SFc2_MON_sl_after_replacementInstanceStruct*/

/* Named Constants */

/* Variable Declarations */
extern struct SfDebugInstanceStruct *sfGlobalDebugInstanceStruct;

/* Variable Definitions */

/* Function Declarations */
extern const mxArray
  *sf_c2_MON_sl_after_replacement_get_eml_resolved_functions_info(void);

/* Function Definitions */
extern void sf_c2_MON_sl_after_replacement_get_check_sum(mxArray *plhs[]);
extern void c2_MON_sl_after_replacement_method_dispatcher(SimStruct *S, int_T
  method, void *data);

#endif
