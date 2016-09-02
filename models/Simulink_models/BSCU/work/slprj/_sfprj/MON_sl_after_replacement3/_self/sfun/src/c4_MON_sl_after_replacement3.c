/* Include files */

#include "MON_sl_after_replacement3_sfun.h"
#include "c4_MON_sl_after_replacement3.h"
#define CHARTINSTANCE_CHARTNUMBER      (chartInstance->chartNumber)
#define CHARTINSTANCE_INSTANCENUMBER   (chartInstance->instanceNumber)
#include "MON_sl_after_replacement3_sfun_debug_macros.h"
#define _SF_MEX_LISTEN_FOR_CTRL_C(S)   sf_mex_listen_for_ctrl_c_with_debugger(S, sfGlobalDebugInstanceStruct);

static void chart_debug_initialization(SimStruct *S, unsigned int
  fullDebuggerInitialization);
static void chart_debug_initialize_data_addresses(SimStruct *S);
static const mxArray* sf_opaque_get_hover_data_for_msg(void *chartInstance,
  int32_T msgSSID);

/* Type Definitions */

/* Named Constants */
#define CALL_EVENT                     (-1)
#define c4_IN_NO_ACTIVE_CHILD          ((uint8_T)0U)

/* Variable Declarations */

/* Variable Definitions */
static real_T _sfTime_;

/* Function Declarations */
static void initialize_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void initialize_params_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void enable_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void disable_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void c4_update_debugger_state_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static const mxArray *get_sim_state_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void set_sim_state_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_st);
static void c4_set_sim_state_side_effects_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void finalize_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void sf_gateway_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void mdl_start_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void initSimStructsc4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void init_script_number_translation(uint32_T c4_machineNumber, uint32_T
  c4_chartNumber, uint32_T c4_instanceNumber);
static const mxArray *c4_sf_marshallOut(void *chartInstanceVoid, void *c4_inData);
static int32_T c4_emlrt_marshallIn(SFc4_MON_sl_after_replacement3InstanceStruct *
  chartInstance, const mxArray *c4_u, const emlrtMsgIdentifier *c4_parentId);
static void c4_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c4_mxArrayInData, const char_T *c4_varName, void *c4_outData);
static const mxArray *c4_b_sf_marshallOut(void *chartInstanceVoid, void
  *c4_inData);
static boolean_T c4_b_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_b_Active, const char_T *c4_identifier);
static boolean_T c4_c_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_u, const emlrtMsgIdentifier *c4_parentId);
static void c4_b_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c4_mxArrayInData, const char_T *c4_varName, void *c4_outData);
static uint8_T c4_d_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_b_is_active_c4_MON_sl_after_replacement3, const char_T *c4_identifier);
static uint8_T c4_e_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_u, const emlrtMsgIdentifier *c4_parentId);
static const mxArray *c4_f_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_b_setSimStateSideEffectsInfo, const char_T *c4_identifier);
static const mxArray *c4_g_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_u, const emlrtMsgIdentifier *c4_parentId);
static const mxArray *sf_get_hover_data_for_msg
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, int32_T c4_ssid);
static void c4_init_sf_message_store_memory
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);
static void init_dsm_address_info(SFc4_MON_sl_after_replacement3InstanceStruct
  *chartInstance);
static void init_simulink_io_address
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance);

/* Function Definitions */
static void initialize_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  if (sf_is_first_init_cond(chartInstance->S)) {
    initSimStructsc4_MON_sl_after_replacement3(chartInstance);
    chart_debug_initialize_data_addresses(chartInstance->S);
  }

  chartInstance->c4_sfEvent = CALL_EVENT;
  _sfTime_ = sf_get_time(chartInstance->S);
  chartInstance->c4_doSetSimStateSideEffects = 0U;
  chartInstance->c4_setSimStateSideEffectsInfo = NULL;
  chartInstance->c4_is_active_c4_MON_sl_after_replacement3 = 0U;
  if (!(sf_get_output_port_reusable(chartInstance->S, 1) != 0)) {
    *chartInstance->c4_Active = false;
    _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_Active, 4U, 1U, 3U,
                          chartInstance->c4_sfEvent, false);
  }
}

static void initialize_params_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  real_T c4_d0;
  sf_mex_import_named("Primary_Side", sf_mex_get_sfun_param(chartInstance->S, 0,
    0), &c4_d0, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c4_Primary_Side = (c4_d0 != 0.0);
}

static void enable_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  _sfTime_ = sf_get_time(chartInstance->S);
}

static void disable_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  _sfTime_ = sf_get_time(chartInstance->S);
}

static void c4_update_debugger_state_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static const mxArray *get_sim_state_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  const mxArray *c4_st;
  const mxArray *c4_y = NULL;
  boolean_T c4_hoistedGlobal;
  boolean_T c4_u;
  const mxArray *c4_b_y = NULL;
  uint8_T c4_b_hoistedGlobal;
  uint8_T c4_b_u;
  const mxArray *c4_c_y = NULL;
  c4_st = NULL;
  c4_st = NULL;
  c4_y = NULL;
  sf_mex_assign(&c4_y, sf_mex_createcellmatrix(2, 1), false);
  c4_hoistedGlobal = *chartInstance->c4_Active;
  c4_u = c4_hoistedGlobal;
  c4_b_y = NULL;
  sf_mex_assign(&c4_b_y, sf_mex_create("y", &c4_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_setcell(c4_y, 0, c4_b_y);
  c4_b_hoistedGlobal = chartInstance->c4_is_active_c4_MON_sl_after_replacement3;
  c4_b_u = c4_b_hoistedGlobal;
  c4_c_y = NULL;
  sf_mex_assign(&c4_c_y, sf_mex_create("y", &c4_b_u, 3, 0U, 0U, 0U, 0), false);
  sf_mex_setcell(c4_y, 1, c4_c_y);
  sf_mex_assign(&c4_st, c4_y, false);
  return c4_st;
}

static void set_sim_state_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_st)
{
  const mxArray *c4_u;
  chartInstance->c4_doneDoubleBufferReInit = true;
  c4_u = sf_mex_dup(c4_st);
  *chartInstance->c4_Active = c4_b_emlrt_marshallIn(chartInstance, sf_mex_dup
    (sf_mex_getcell("Active", c4_u, 0)), "Active");
  chartInstance->c4_is_active_c4_MON_sl_after_replacement3 =
    c4_d_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "is_active_c4_MON_sl_after_replacement3", c4_u, 1)),
    "is_active_c4_MON_sl_after_replacement3");
  sf_mex_assign(&chartInstance->c4_setSimStateSideEffectsInfo,
                c4_f_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "setSimStateSideEffectsInfo", c4_u, 2)), "setSimStateSideEffectsInfo"), true);
  sf_mex_destroy(&c4_u);
  chartInstance->c4_doSetSimStateSideEffects = 1U;
  c4_update_debugger_state_c4_MON_sl_after_replacement3(chartInstance);
  sf_mex_destroy(&c4_st);
}

static void c4_set_sim_state_side_effects_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  if (chartInstance->c4_doSetSimStateSideEffects != 0) {
    chartInstance->c4_doSetSimStateSideEffects = 0U;
  }
}

static void finalize_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  sf_mex_destroy(&chartInstance->c4_setSimStateSideEffectsInfo);
}

static void sf_gateway_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  boolean_T c4_out;
  boolean_T c4_temp;
  boolean_T c4_b_temp;
  boolean_T c4_c_temp;
  boolean_T c4_b_out;
  boolean_T c4_d_temp;
  boolean_T c4_e_temp;
  boolean_T c4_c_out;
  boolean_T guard1 = false;
  boolean_T guard2 = false;
  c4_set_sim_state_side_effects_c4_MON_sl_after_replacement3(chartInstance);
  _SFD_SYMBOL_SCOPE_PUSH(0U, 0U);
  _sfTime_ = sf_get_time(chartInstance->S);
  _SFD_CC_CALL(CHART_ENTER_SFUNCTION_TAG, 3U, chartInstance->c4_sfEvent);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_Sync_From_Error, 3U, 1U, 0U,
                        chartInstance->c4_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_Error, 2U, 1U, 0U,
                        chartInstance->c4_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_pre_Active, 1U, 1U, 0U,
                        chartInstance->c4_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_Sync_From_Active, 0U, 1U, 0U,
                        chartInstance->c4_sfEvent, false);
  chartInstance->c4_sfEvent = CALL_EVENT;
  _SFD_CC_CALL(CHART_ENTER_DURING_FUNCTION_TAG, 3U, chartInstance->c4_sfEvent);
  _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 0U, chartInstance->c4_sfEvent);
  _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 1U, chartInstance->c4_sfEvent);
  c4_out = (CV_TRANSITION_EVAL(1U, (int32_T)_SFD_CCP_CALL(1U, 0,
              *chartInstance->c4_Error != 0U, chartInstance->c4_sfEvent)) != 0);
  guard1 = false;
  guard2 = false;
  if (c4_out) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 1U, chartInstance->c4_sfEvent);
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 3U, chartInstance->c4_sfEvent);
    *chartInstance->c4_Active = false;
    _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_Active, 4U, 5U, 3U,
                          chartInstance->c4_sfEvent, false);
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 12U, chartInstance->c4_sfEvent);
    guard2 = true;
  } else {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 2U, chartInstance->c4_sfEvent);
    _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 4U, chartInstance->c4_sfEvent);
    c4_temp = (_SFD_CCP_CALL(4U, 0, *chartInstance->c4_pre_Active != 0U,
                chartInstance->c4_sfEvent) != 0);
    if (c4_temp) {
      c4_temp = (_SFD_CCP_CALL(4U, 1, *chartInstance->c4_Sync_From_Active != 0U,
                  chartInstance->c4_sfEvent) != 0);
    }

    c4_b_temp = c4_temp;
    if (c4_b_temp) {
      c4_b_temp = !(_SFD_CCP_CALL(4U, 2, *chartInstance->c4_Sync_From_Error !=
        0U, chartInstance->c4_sfEvent) != 0);
    }

    c4_c_temp = c4_b_temp;
    if (c4_c_temp) {
      c4_c_temp = !(_SFD_CCP_CALL(4U, 3, chartInstance->c4_Primary_Side != 0U,
        chartInstance->c4_sfEvent) != 0);
    }

    c4_b_out = (CV_TRANSITION_EVAL(4U, (int32_T)c4_c_temp) != 0);
    if (c4_b_out) {
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 4U, chartInstance->c4_sfEvent);
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 6U, chartInstance->c4_sfEvent);
      *chartInstance->c4_Active = false;
      _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_Active, 4U, 5U, 6U,
                            chartInstance->c4_sfEvent, false);
      guard2 = true;
    } else {
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 5U, chartInstance->c4_sfEvent);
      _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 7U,
                   chartInstance->c4_sfEvent);
      c4_d_temp = !(_SFD_CCP_CALL(7U, 0, *chartInstance->c4_pre_Active != 0U,
        chartInstance->c4_sfEvent) != 0);
      if (c4_d_temp) {
        c4_e_temp = !(_SFD_CCP_CALL(7U, 1, *chartInstance->c4_Sync_From_Active
          != 0U, chartInstance->c4_sfEvent) != 0);
        if (!c4_e_temp) {
          c4_e_temp = (_SFD_CCP_CALL(7U, 2, *chartInstance->c4_Sync_From_Error
            != 0U, chartInstance->c4_sfEvent) != 0);
        }

        c4_d_temp = c4_e_temp;
      }

      c4_c_out = (CV_TRANSITION_EVAL(7U, (int32_T)c4_d_temp) != 0);
      if (c4_c_out) {
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 7U, chartInstance->c4_sfEvent);
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 9U, chartInstance->c4_sfEvent);
        *chartInstance->c4_Active = true;
        _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_Active, 4U, 5U, 9U,
                              chartInstance->c4_sfEvent, false);
        guard1 = true;
      } else {
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 8U, chartInstance->c4_sfEvent);
        *chartInstance->c4_Active = *chartInstance->c4_pre_Active;
        _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c4_Active, 4U, 5U, 8U,
                              chartInstance->c4_sfEvent, false);
      }
    }
  }

  if (guard2 == true) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 11U, chartInstance->c4_sfEvent);
    guard1 = true;
  }

  if (guard1 == true) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 10U, chartInstance->c4_sfEvent);
  }

  _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 13U, chartInstance->c4_sfEvent);
  _SFD_CC_CALL(EXIT_OUT_OF_FUNCTION_TAG, 3U, chartInstance->c4_sfEvent);
  _SFD_SYMBOL_SCOPE_POP();
  _SFD_CHECK_FOR_STATE_INCONSISTENCY(_MON_sl_after_replacement3MachineNumber_,
    chartInstance->chartNumber, chartInstance->instanceNumber);
}

static void mdl_start_c4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  c4_init_sf_message_store_memory(chartInstance);
}

static void initSimStructsc4_MON_sl_after_replacement3
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static void init_script_number_translation(uint32_T c4_machineNumber, uint32_T
  c4_chartNumber, uint32_T c4_instanceNumber)
{
  (void)c4_machineNumber;
  (void)c4_chartNumber;
  (void)c4_instanceNumber;
}

const mxArray *sf_c4_MON_sl_after_replacement3_get_eml_resolved_functions_info
  (void)
{
  const mxArray *c4_nameCaptureInfo = NULL;
  c4_nameCaptureInfo = NULL;
  sf_mex_assign(&c4_nameCaptureInfo, sf_mex_create("nameCaptureInfo", NULL, 0,
    0U, 1U, 0U, 2, 0, 1), false);
  return c4_nameCaptureInfo;
}

static const mxArray *c4_sf_marshallOut(void *chartInstanceVoid, void *c4_inData)
{
  const mxArray *c4_mxArrayOutData = NULL;
  int32_T c4_u;
  const mxArray *c4_y = NULL;
  SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance;
  chartInstance = (SFc4_MON_sl_after_replacement3InstanceStruct *)
    chartInstanceVoid;
  c4_mxArrayOutData = NULL;
  c4_u = *(int32_T *)c4_inData;
  c4_y = NULL;
  sf_mex_assign(&c4_y, sf_mex_create("y", &c4_u, 6, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c4_mxArrayOutData, c4_y, false);
  return c4_mxArrayOutData;
}

static int32_T c4_emlrt_marshallIn(SFc4_MON_sl_after_replacement3InstanceStruct *
  chartInstance, const mxArray *c4_u, const emlrtMsgIdentifier *c4_parentId)
{
  int32_T c4_y;
  int32_T c4_i0;
  (void)chartInstance;
  sf_mex_import(c4_parentId, sf_mex_dup(c4_u), &c4_i0, 1, 6, 0U, 0, 0U, 0);
  c4_y = c4_i0;
  sf_mex_destroy(&c4_u);
  return c4_y;
}

static void c4_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c4_mxArrayInData, const char_T *c4_varName, void *c4_outData)
{
  const mxArray *c4_b_sfEvent;
  const char_T *c4_identifier;
  emlrtMsgIdentifier c4_thisId;
  int32_T c4_y;
  SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance;
  chartInstance = (SFc4_MON_sl_after_replacement3InstanceStruct *)
    chartInstanceVoid;
  c4_b_sfEvent = sf_mex_dup(c4_mxArrayInData);
  c4_identifier = c4_varName;
  c4_thisId.fIdentifier = c4_identifier;
  c4_thisId.fParent = NULL;
  c4_thisId.bParentIsCell = false;
  c4_y = c4_emlrt_marshallIn(chartInstance, sf_mex_dup(c4_b_sfEvent), &c4_thisId);
  sf_mex_destroy(&c4_b_sfEvent);
  *(int32_T *)c4_outData = c4_y;
  sf_mex_destroy(&c4_mxArrayInData);
}

static const mxArray *c4_b_sf_marshallOut(void *chartInstanceVoid, void
  *c4_inData)
{
  const mxArray *c4_mxArrayOutData = NULL;
  boolean_T c4_u;
  const mxArray *c4_y = NULL;
  SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance;
  chartInstance = (SFc4_MON_sl_after_replacement3InstanceStruct *)
    chartInstanceVoid;
  c4_mxArrayOutData = NULL;
  c4_u = *(boolean_T *)c4_inData;
  c4_y = NULL;
  sf_mex_assign(&c4_y, sf_mex_create("y", &c4_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c4_mxArrayOutData, c4_y, false);
  return c4_mxArrayOutData;
}

static boolean_T c4_b_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_b_Active, const char_T *c4_identifier)
{
  boolean_T c4_y;
  emlrtMsgIdentifier c4_thisId;
  c4_thisId.fIdentifier = c4_identifier;
  c4_thisId.fParent = NULL;
  c4_thisId.bParentIsCell = false;
  c4_y = c4_c_emlrt_marshallIn(chartInstance, sf_mex_dup(c4_b_Active),
    &c4_thisId);
  sf_mex_destroy(&c4_b_Active);
  return c4_y;
}

static boolean_T c4_c_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_u, const emlrtMsgIdentifier *c4_parentId)
{
  boolean_T c4_y;
  boolean_T c4_b0;
  (void)chartInstance;
  sf_mex_import(c4_parentId, sf_mex_dup(c4_u), &c4_b0, 1, 11, 0U, 0, 0U, 0);
  c4_y = c4_b0;
  sf_mex_destroy(&c4_u);
  return c4_y;
}

static void c4_b_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c4_mxArrayInData, const char_T *c4_varName, void *c4_outData)
{
  const mxArray *c4_b_Active;
  const char_T *c4_identifier;
  emlrtMsgIdentifier c4_thisId;
  boolean_T c4_y;
  SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance;
  chartInstance = (SFc4_MON_sl_after_replacement3InstanceStruct *)
    chartInstanceVoid;
  c4_b_Active = sf_mex_dup(c4_mxArrayInData);
  c4_identifier = c4_varName;
  c4_thisId.fIdentifier = c4_identifier;
  c4_thisId.fParent = NULL;
  c4_thisId.bParentIsCell = false;
  c4_y = c4_c_emlrt_marshallIn(chartInstance, sf_mex_dup(c4_b_Active),
    &c4_thisId);
  sf_mex_destroy(&c4_b_Active);
  *(boolean_T *)c4_outData = c4_y;
  sf_mex_destroy(&c4_mxArrayInData);
}

static uint8_T c4_d_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_b_is_active_c4_MON_sl_after_replacement3, const char_T *c4_identifier)
{
  uint8_T c4_y;
  emlrtMsgIdentifier c4_thisId;
  c4_thisId.fIdentifier = c4_identifier;
  c4_thisId.fParent = NULL;
  c4_thisId.bParentIsCell = false;
  c4_y = c4_e_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c4_b_is_active_c4_MON_sl_after_replacement3), &c4_thisId);
  sf_mex_destroy(&c4_b_is_active_c4_MON_sl_after_replacement3);
  return c4_y;
}

static uint8_T c4_e_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_u, const emlrtMsgIdentifier *c4_parentId)
{
  uint8_T c4_y;
  uint8_T c4_u0;
  (void)chartInstance;
  sf_mex_import(c4_parentId, sf_mex_dup(c4_u), &c4_u0, 1, 3, 0U, 0, 0U, 0);
  c4_y = c4_u0;
  sf_mex_destroy(&c4_u);
  return c4_y;
}

static const mxArray *c4_f_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_b_setSimStateSideEffectsInfo, const char_T *c4_identifier)
{
  const mxArray *c4_y = NULL;
  emlrtMsgIdentifier c4_thisId;
  c4_y = NULL;
  c4_thisId.fIdentifier = c4_identifier;
  c4_thisId.fParent = NULL;
  c4_thisId.bParentIsCell = false;
  sf_mex_assign(&c4_y, c4_g_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c4_b_setSimStateSideEffectsInfo), &c4_thisId), false);
  sf_mex_destroy(&c4_b_setSimStateSideEffectsInfo);
  return c4_y;
}

static const mxArray *c4_g_emlrt_marshallIn
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, const mxArray
   *c4_u, const emlrtMsgIdentifier *c4_parentId)
{
  const mxArray *c4_y = NULL;
  (void)chartInstance;
  (void)c4_parentId;
  c4_y = NULL;
  sf_mex_assign(&c4_y, sf_mex_duplicatearraysafe(&c4_u), false);
  sf_mex_destroy(&c4_u);
  return c4_y;
}

static const mxArray *sf_get_hover_data_for_msg
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance, int32_T c4_ssid)
{
  (void)chartInstance;
  (void)c4_ssid;
  return NULL;
}

static void c4_init_sf_message_store_memory
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static void init_dsm_address_info(SFc4_MON_sl_after_replacement3InstanceStruct
  *chartInstance)
{
  (void)chartInstance;
}

static void init_simulink_io_address
  (SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance)
{
  chartInstance->c4_Sync_From_Active = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 0);
  chartInstance->c4_Active = (boolean_T *)ssGetOutputPortSignal_wrapper
    (chartInstance->S, 1);
  chartInstance->c4_pre_Active = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 1);
  chartInstance->c4_Error = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 2);
  chartInstance->c4_Sync_From_Error = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 3);
}

/* SFunction Glue Code */
#ifdef utFree
#undef utFree
#endif

#ifdef utMalloc
#undef utMalloc
#endif

#ifdef __cplusplus

extern "C" void *utMalloc(size_t size);
extern "C" void utFree(void*);

#else

extern void *utMalloc(size_t size);
extern void utFree(void*);

#endif

void sf_c4_MON_sl_after_replacement3_get_check_sum(mxArray *plhs[])
{
  ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(3911255366U);
  ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(26898983U);
  ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(1359245885U);
  ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(1496106901U);
}

mxArray* sf_c4_MON_sl_after_replacement3_get_post_codegen_info(void);
mxArray *sf_c4_MON_sl_after_replacement3_get_autoinheritance_info(void)
{
  const char *autoinheritanceFields[] = { "checksum", "inputs", "parameters",
    "outputs", "locals", "postCodegenInfo" };

  mxArray *mxAutoinheritanceInfo = mxCreateStructMatrix(1, 1, sizeof
    (autoinheritanceFields)/sizeof(autoinheritanceFields[0]),
    autoinheritanceFields);

  {
    mxArray *mxChecksum = mxCreateString("is2DE0mVJR8Beyt8nge1TB");
    mxSetField(mxAutoinheritanceInfo,0,"checksum",mxChecksum);
  }

  {
    const char *dataFields[] = { "size", "type", "complexity" };

    mxArray *mxData = mxCreateStructMatrix(1,4,3,dataFields);

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,0,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(1));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,0,"type",mxType);
    }

    mxSetField(mxData,0,"complexity",mxCreateDoubleScalar(0));

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,1,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(1));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,1,"type",mxType);
    }

    mxSetField(mxData,1,"complexity",mxCreateDoubleScalar(0));

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,2,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(1));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,2,"type",mxType);
    }

    mxSetField(mxData,2,"complexity",mxCreateDoubleScalar(0));

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,3,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(1));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,3,"type",mxType);
    }

    mxSetField(mxData,3,"complexity",mxCreateDoubleScalar(0));
    mxSetField(mxAutoinheritanceInfo,0,"inputs",mxData);
  }

  {
    const char *dataFields[] = { "size", "type", "complexity" };

    mxArray *mxData = mxCreateStructMatrix(1,1,3,dataFields);

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,0,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(1));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,0,"type",mxType);
    }

    mxSetField(mxData,0,"complexity",mxCreateDoubleScalar(0));
    mxSetField(mxAutoinheritanceInfo,0,"parameters",mxData);
  }

  {
    const char *dataFields[] = { "size", "type", "complexity" };

    mxArray *mxData = mxCreateStructMatrix(1,1,3,dataFields);

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,0,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(1));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,0,"type",mxType);
    }

    mxSetField(mxData,0,"complexity",mxCreateDoubleScalar(0));
    mxSetField(mxAutoinheritanceInfo,0,"outputs",mxData);
  }

  {
    mxSetField(mxAutoinheritanceInfo,0,"locals",mxCreateDoubleMatrix(0,0,mxREAL));
  }

  {
    mxArray* mxPostCodegenInfo =
      sf_c4_MON_sl_after_replacement3_get_post_codegen_info();
    mxSetField(mxAutoinheritanceInfo,0,"postCodegenInfo",mxPostCodegenInfo);
  }

  return(mxAutoinheritanceInfo);
}

mxArray *sf_c4_MON_sl_after_replacement3_third_party_uses_info(void)
{
  mxArray * mxcell3p = mxCreateCellMatrix(1,0);
  return(mxcell3p);
}

mxArray *sf_c4_MON_sl_after_replacement3_jit_fallback_info(void)
{
  const char *infoFields[] = { "fallbackType", "fallbackReason",
    "hiddenFallbackType", "hiddenFallbackReason", "incompatibleSymbol" };

  mxArray *mxInfo = mxCreateStructMatrix(1, 1, 5, infoFields);
  mxArray *fallbackType = mxCreateString("pre");
  mxArray *fallbackReason = mxCreateString("forceDebug");
  mxArray *hiddenFallbackType = mxCreateString("none");
  mxArray *hiddenFallbackReason = mxCreateString("");
  mxArray *incompatibleSymbol = mxCreateString("");
  mxSetField(mxInfo, 0, infoFields[0], fallbackType);
  mxSetField(mxInfo, 0, infoFields[1], fallbackReason);
  mxSetField(mxInfo, 0, infoFields[2], hiddenFallbackType);
  mxSetField(mxInfo, 0, infoFields[3], hiddenFallbackReason);
  mxSetField(mxInfo, 0, infoFields[4], incompatibleSymbol);
  return mxInfo;
}

mxArray *sf_c4_MON_sl_after_replacement3_updateBuildInfo_args_info(void)
{
  mxArray *mxBIArgs = mxCreateCellMatrix(1,0);
  return mxBIArgs;
}

mxArray* sf_c4_MON_sl_after_replacement3_get_post_codegen_info(void)
{
  const char* fieldNames[] = { "exportedFunctionsUsedByThisChart",
    "exportedFunctionsChecksum" };

  mwSize dims[2] = { 1, 1 };

  mxArray* mxPostCodegenInfo = mxCreateStructArray(2, dims, sizeof(fieldNames)/
    sizeof(fieldNames[0]), fieldNames);

  {
    mxArray* mxExportedFunctionsChecksum = mxCreateString("");
    mwSize exp_dims[2] = { 0, 1 };

    mxArray* mxExportedFunctionsUsedByThisChart = mxCreateCellArray(2, exp_dims);
    mxSetField(mxPostCodegenInfo, 0, "exportedFunctionsUsedByThisChart",
               mxExportedFunctionsUsedByThisChart);
    mxSetField(mxPostCodegenInfo, 0, "exportedFunctionsChecksum",
               mxExportedFunctionsChecksum);
  }

  return mxPostCodegenInfo;
}

static const mxArray *sf_get_sim_state_info_c4_MON_sl_after_replacement3(void)
{
  const char *infoFields[] = { "chartChecksum", "varInfo" };

  mxArray *mxInfo = mxCreateStructMatrix(1, 1, 2, infoFields);
  const char *infoEncStr[] = {
    "100 S1x2'type','srcId','name','auxInfo'{{M[1],M[1780],T\"Active\",},{M[8],M[0],T\"is_active_c4_MON_sl_after_replacement3\",}}"
  };

  mxArray *mxVarInfo = sf_mex_decode_encoded_mx_struct_array(infoEncStr, 2, 10);
  mxArray *mxChecksum = mxCreateDoubleMatrix(1, 4, mxREAL);
  sf_c4_MON_sl_after_replacement3_get_check_sum(&mxChecksum);
  mxSetField(mxInfo, 0, infoFields[0], mxChecksum);
  mxSetField(mxInfo, 0, infoFields[1], mxVarInfo);
  return mxInfo;
}

static const mxArray* sf_opaque_get_hover_data_for_msg(void* chartInstance,
  int32_T msgSSID)
{
  return sf_get_hover_data_for_msg
    ( (SFc4_MON_sl_after_replacement3InstanceStruct *) chartInstance, msgSSID);
}

static void chart_debug_initialization(SimStruct *S, unsigned int
  fullDebuggerInitialization)
{
  if (!sim_mode_is_rtw_gen(S)) {
    SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance =
      (SFc4_MON_sl_after_replacement3InstanceStruct *)sf_get_chart_instance_ptr
      (S);
    if (ssIsFirstInitCond(S) && fullDebuggerInitialization==1) {
      /* do this only if simulation is starting */
      {
        unsigned int chartAlreadyPresent;
        chartAlreadyPresent = sf_debug_initialize_chart
          (sfGlobalDebugInstanceStruct,
           _MON_sl_after_replacement3MachineNumber_,
           4,
           0,
           14,
           0,
           6,
           0,
           0,
           0,
           0,
           0,
           &chartInstance->chartNumber,
           &chartInstance->instanceNumber,
           (void *)S);

        /* Each instance must initialize its own list of scripts */
        init_script_number_translation(_MON_sl_after_replacement3MachineNumber_,
          chartInstance->chartNumber,chartInstance->instanceNumber);
        if (chartAlreadyPresent==0) {
          /* this is the first instance */
          sf_debug_set_chart_disable_implicit_casting
            (sfGlobalDebugInstanceStruct,
             _MON_sl_after_replacement3MachineNumber_,chartInstance->chartNumber,
             1);
          sf_debug_set_chart_event_thresholds(sfGlobalDebugInstanceStruct,
            _MON_sl_after_replacement3MachineNumber_,
            chartInstance->chartNumber,
            0,
            0,
            0);
          _SFD_SET_DATA_PROPS(0,1,1,0,"Sync_From_Active");
          _SFD_SET_DATA_PROPS(1,1,1,0,"pre_Active");
          _SFD_SET_DATA_PROPS(2,1,1,0,"Error");
          _SFD_SET_DATA_PROPS(3,1,1,0,"Sync_From_Error");
          _SFD_SET_DATA_PROPS(4,2,0,1,"Active");
          _SFD_SET_DATA_PROPS(5,10,0,0,"Primary_Side");
          _SFD_CH_SUBSTATE_COUNT(0);
          _SFD_CH_SUBSTATE_DECOMP(0);
        }

        _SFD_CV_INIT_CHART(0,0,0,0);
        _SFD_CV_INIT_TRANS(0,0,NULL,NULL,0,NULL);

        {
          static unsigned int sStartGuardMap[] = { 1 };

          static unsigned int sEndGuardMap[] = { 6 };

          static int sPostFixPredicateTree[] = { 0 };

          _SFD_CV_INIT_TRANS(1,1,&(sStartGuardMap[0]),&(sEndGuardMap[0]),1,
                             &(sPostFixPredicateTree[0]));
        }

        _SFD_CV_INIT_TRANS(2,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(3,0,NULL,NULL,0,NULL);

        {
          static unsigned int sStartGuardMap[] = { 1, 15, 38, 61 };

          static unsigned int sEndGuardMap[] = { 11, 31, 53, 73 };

          static int sPostFixPredicateTree[] = { 0, 1, -3, 2, -1, -3, 3, -1, -3
          };

          _SFD_CV_INIT_TRANS(4,4,&(sStartGuardMap[0]),&(sEndGuardMap[0]),9,
                             &(sPostFixPredicateTree[0]));
        }

        _SFD_CV_INIT_TRANS(5,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(6,0,NULL,NULL,0,NULL);

        {
          static unsigned int sStartGuardMap[] = { 3, 22, 43 };

          static unsigned int sEndGuardMap[] = { 13, 38, 58 };

          static int sPostFixPredicateTree[] = { 0, -1, 1, -1, 2, -2, -3 };

          _SFD_CV_INIT_TRANS(7,3,&(sStartGuardMap[0]),&(sEndGuardMap[0]),7,
                             &(sPostFixPredicateTree[0]));
        }

        _SFD_CV_INIT_TRANS(8,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(9,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(10,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(11,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(12,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(13,0,NULL,NULL,0,NULL);
        _SFD_SET_DATA_COMPILED_PROPS(0,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c4_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(1,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c4_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(2,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c4_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(3,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c4_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(4,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c4_b_sf_marshallOut,(MexInFcnForType)c4_b_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(5,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c4_b_sf_marshallOut,(MexInFcnForType)c4_b_sf_marshallIn);
      }
    } else {
      sf_debug_reset_current_state_configuration(sfGlobalDebugInstanceStruct,
        _MON_sl_after_replacement3MachineNumber_,chartInstance->chartNumber,
        chartInstance->instanceNumber);
    }
  }
}

static void chart_debug_initialize_data_addresses(SimStruct *S)
{
  if (!sim_mode_is_rtw_gen(S)) {
    SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance =
      (SFc4_MON_sl_after_replacement3InstanceStruct *)sf_get_chart_instance_ptr
      (S);
    if (ssIsFirstInitCond(S)) {
      /* do this only if simulation is starting and after we know the addresses of all data */
      {
        _SFD_SET_DATA_VALUE_PTR(0U, chartInstance->c4_Sync_From_Active);
        _SFD_SET_DATA_VALUE_PTR(4U, chartInstance->c4_Active);
        _SFD_SET_DATA_VALUE_PTR(1U, chartInstance->c4_pre_Active);
        _SFD_SET_DATA_VALUE_PTR(2U, chartInstance->c4_Error);
        _SFD_SET_DATA_VALUE_PTR(3U, chartInstance->c4_Sync_From_Error);
        _SFD_SET_DATA_VALUE_PTR(5U, &chartInstance->c4_Primary_Side);
      }
    }
  }
}

static const char* sf_get_instance_specialization(void)
{
  return "s6FAl9lqtfcVMNtlNAif9fH";
}

static void sf_opaque_initialize_c4_MON_sl_after_replacement3(void
  *chartInstanceVar)
{
  chart_debug_initialization(((SFc4_MON_sl_after_replacement3InstanceStruct*)
    chartInstanceVar)->S,0);
  initialize_params_c4_MON_sl_after_replacement3
    ((SFc4_MON_sl_after_replacement3InstanceStruct*) chartInstanceVar);
  initialize_c4_MON_sl_after_replacement3
    ((SFc4_MON_sl_after_replacement3InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_enable_c4_MON_sl_after_replacement3(void *chartInstanceVar)
{
  enable_c4_MON_sl_after_replacement3
    ((SFc4_MON_sl_after_replacement3InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_disable_c4_MON_sl_after_replacement3(void
  *chartInstanceVar)
{
  disable_c4_MON_sl_after_replacement3
    ((SFc4_MON_sl_after_replacement3InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_gateway_c4_MON_sl_after_replacement3(void
  *chartInstanceVar)
{
  sf_gateway_c4_MON_sl_after_replacement3
    ((SFc4_MON_sl_after_replacement3InstanceStruct*) chartInstanceVar);
}

static const mxArray* sf_opaque_get_sim_state_c4_MON_sl_after_replacement3
  (SimStruct* S)
{
  return get_sim_state_c4_MON_sl_after_replacement3
    ((SFc4_MON_sl_after_replacement3InstanceStruct *)sf_get_chart_instance_ptr(S));/* raw sim ctx */
}

static void sf_opaque_set_sim_state_c4_MON_sl_after_replacement3(SimStruct* S,
  const mxArray *st)
{
  set_sim_state_c4_MON_sl_after_replacement3
    ((SFc4_MON_sl_after_replacement3InstanceStruct*)sf_get_chart_instance_ptr(S),
     st);
}

static void sf_opaque_terminate_c4_MON_sl_after_replacement3(void
  *chartInstanceVar)
{
  if (chartInstanceVar!=NULL) {
    SimStruct *S = ((SFc4_MON_sl_after_replacement3InstanceStruct*)
                    chartInstanceVar)->S;
    if (sim_mode_is_rtw_gen(S) || sim_mode_is_external(S)) {
      sf_clear_rtw_identifier(S);
      unload_MON_sl_after_replacement3_optimization_info();
    }

    finalize_c4_MON_sl_after_replacement3
      ((SFc4_MON_sl_after_replacement3InstanceStruct*) chartInstanceVar);
    utFree(chartInstanceVar);
    if (ssGetUserData(S)!= NULL) {
      sf_free_ChartRunTimeInfo(S);
    }

    ssSetUserData(S,NULL);
  }
}

static void sf_opaque_init_subchart_simstructs(void *chartInstanceVar)
{
  initSimStructsc4_MON_sl_after_replacement3
    ((SFc4_MON_sl_after_replacement3InstanceStruct*) chartInstanceVar);
}

extern unsigned int sf_machine_global_initializer_called(void);
static void mdlProcessParameters_c4_MON_sl_after_replacement3(SimStruct *S)
{
  int i;
  for (i=0;i<ssGetNumRunTimeParams(S);i++) {
    if (ssGetSFcnParamTunable(S,i)) {
      ssUpdateDlgParamAsRunTimeParam(S,i);
    }
  }

  if (sf_machine_global_initializer_called()) {
    initialize_params_c4_MON_sl_after_replacement3
      ((SFc4_MON_sl_after_replacement3InstanceStruct*)sf_get_chart_instance_ptr
       (S));
  }
}

static void mdlSetWorkWidths_c4_MON_sl_after_replacement3(SimStruct *S)
{
  /* Actual parameters from chart:
     Primary_Side
   */
  const char_T *rtParamNames[] = { "Primary_Side" };

  ssSetNumRunTimeParams(S,ssGetSFcnParamsCount(S));

  /* registration for Primary_Side*/
  ssRegDlgParamAsRunTimeParam(S, 0, 0, rtParamNames[0], SS_BOOLEAN);
  ssMdlUpdateIsEmpty(S, 1);
  if (sim_mode_is_rtw_gen(S) || sim_mode_is_external(S)) {
    mxArray *infoStruct = load_MON_sl_after_replacement3_optimization_info
      (sim_mode_is_rtw_gen(S), sim_mode_is_modelref_sim(S), sim_mode_is_external
       (S));
    int_T chartIsInlinable =
      (int_T)sf_is_chart_inlinable(sf_get_instance_specialization(),infoStruct,4);
    ssSetStateflowIsInlinable(S,chartIsInlinable);
    ssSetRTWCG(S,1);
    ssSetEnableFcnIsTrivial(S,1);
    ssSetDisableFcnIsTrivial(S,1);
    ssSetNotMultipleInlinable(S,sf_rtw_info_uint_prop
      (sf_get_instance_specialization(),infoStruct,4,
       "gatewayCannotBeInlinedMultipleTimes"));
    sf_set_chart_accesses_machine_info(S, sf_get_instance_specialization(),
      infoStruct, 4);
    sf_update_buildInfo(S, sf_get_instance_specialization(),infoStruct,4);
    if (chartIsInlinable) {
      ssSetInputPortOptimOpts(S, 0, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 1, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 2, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 3, SS_REUSABLE_AND_LOCAL);
      sf_mark_chart_expressionable_inputs(S,sf_get_instance_specialization(),
        infoStruct,4,4);
      sf_mark_chart_reusable_outputs(S,sf_get_instance_specialization(),
        infoStruct,4,1);
    }

    {
      unsigned int outPortIdx;
      for (outPortIdx=1; outPortIdx<=1; ++outPortIdx) {
        ssSetOutputPortOptimizeInIR(S, outPortIdx, 1U);
      }
    }

    {
      unsigned int inPortIdx;
      for (inPortIdx=0; inPortIdx < 4; ++inPortIdx) {
        ssSetInputPortOptimizeInIR(S, inPortIdx, 1U);
      }
    }

    sf_set_rtw_dwork_info(S,sf_get_instance_specialization(),infoStruct,4);
    ssSetHasSubFunctions(S,!(chartIsInlinable));
  } else {
  }

  ssSetOptions(S,ssGetOptions(S)|SS_OPTION_WORKS_WITH_CODE_REUSE);
  ssSetChecksum0(S,(645941136U));
  ssSetChecksum1(S,(444472430U));
  ssSetChecksum2(S,(1231278852U));
  ssSetChecksum3(S,(2586291620U));
  ssSetmdlDerivatives(S, NULL);
  ssSetExplicitFCSSCtrl(S,1);
  ssSupportsMultipleExecInstances(S,1);
}

static void mdlRTW_c4_MON_sl_after_replacement3(SimStruct *S)
{
  if (sim_mode_is_rtw_gen(S)) {
    ssWriteRTWStrParam(S, "StateflowChartType", "Stateflow");
  }
}

static void mdlStart_c4_MON_sl_after_replacement3(SimStruct *S)
{
  SFc4_MON_sl_after_replacement3InstanceStruct *chartInstance;
  chartInstance = (SFc4_MON_sl_after_replacement3InstanceStruct *)utMalloc
    (sizeof(SFc4_MON_sl_after_replacement3InstanceStruct));
  if (chartInstance==NULL) {
    sf_mex_error_message("Could not allocate memory for chart instance.");
  }

  memset(chartInstance, 0, sizeof(SFc4_MON_sl_after_replacement3InstanceStruct));
  chartInstance->chartInfo.chartInstance = chartInstance;
  chartInstance->chartInfo.isEMLChart = 0;
  chartInstance->chartInfo.chartInitialized = 0;
  chartInstance->chartInfo.sFunctionGateway =
    sf_opaque_gateway_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.initializeChart =
    sf_opaque_initialize_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.terminateChart =
    sf_opaque_terminate_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.enableChart =
    sf_opaque_enable_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.disableChart =
    sf_opaque_disable_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.getSimState =
    sf_opaque_get_sim_state_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.setSimState =
    sf_opaque_set_sim_state_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.getSimStateInfo =
    sf_get_sim_state_info_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.zeroCrossings = NULL;
  chartInstance->chartInfo.outputs = NULL;
  chartInstance->chartInfo.derivatives = NULL;
  chartInstance->chartInfo.mdlRTW = mdlRTW_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.mdlStart = mdlStart_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.mdlSetWorkWidths =
    mdlSetWorkWidths_c4_MON_sl_after_replacement3;
  chartInstance->chartInfo.callGetHoverDataForMsg =
    sf_opaque_get_hover_data_for_msg;
  chartInstance->chartInfo.extModeExec = NULL;
  chartInstance->chartInfo.restoreLastMajorStepConfiguration = NULL;
  chartInstance->chartInfo.restoreBeforeLastMajorStepConfiguration = NULL;
  chartInstance->chartInfo.storeCurrentConfiguration = NULL;
  chartInstance->chartInfo.callAtomicSubchartUserFcn = NULL;
  chartInstance->chartInfo.callAtomicSubchartAutoFcn = NULL;
  chartInstance->chartInfo.debugInstance = sfGlobalDebugInstanceStruct;
  chartInstance->S = S;
  sf_init_ChartRunTimeInfo(S, &(chartInstance->chartInfo), false, 0);
  init_dsm_address_info(chartInstance);
  init_simulink_io_address(chartInstance);
  if (!sim_mode_is_rtw_gen(S)) {
  }

  chart_debug_initialization(S,1);
  mdl_start_c4_MON_sl_after_replacement3(chartInstance);
}

void c4_MON_sl_after_replacement3_method_dispatcher(SimStruct *S, int_T method,
  void *data)
{
  switch (method) {
   case SS_CALL_MDL_START:
    mdlStart_c4_MON_sl_after_replacement3(S);
    break;

   case SS_CALL_MDL_SET_WORK_WIDTHS:
    mdlSetWorkWidths_c4_MON_sl_after_replacement3(S);
    break;

   case SS_CALL_MDL_PROCESS_PARAMETERS:
    mdlProcessParameters_c4_MON_sl_after_replacement3(S);
    break;

   default:
    /* Unhandled method */
    sf_mex_error_message("Stateflow Internal Error:\n"
                         "Error calling c4_MON_sl_after_replacement3_method_dispatcher.\n"
                         "Can't handle method %d.\n", method);
    break;
  }
}
