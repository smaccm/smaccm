/* Include files */

#include "MON_sl_after_replacement2_sfun.h"
#include "c3_MON_sl_after_replacement2.h"
#define CHARTINSTANCE_CHARTNUMBER      (chartInstance->chartNumber)
#define CHARTINSTANCE_INSTANCENUMBER   (chartInstance->instanceNumber)
#include "MON_sl_after_replacement2_sfun_debug_macros.h"
#define _SF_MEX_LISTEN_FOR_CTRL_C(S)   sf_mex_listen_for_ctrl_c_with_debugger(S, sfGlobalDebugInstanceStruct);

static void chart_debug_initialization(SimStruct *S, unsigned int
  fullDebuggerInitialization);
static void chart_debug_initialize_data_addresses(SimStruct *S);
static const mxArray* sf_opaque_get_hover_data_for_msg(void *chartInstance,
  int32_T msgSSID);

/* Type Definitions */

/* Named Constants */
#define CALL_EVENT                     (-1)
#define c3_IN_NO_ACTIVE_CHILD          ((uint8_T)0U)

/* Variable Declarations */

/* Variable Definitions */
static real_T _sfTime_;

/* Function Declarations */
static void initialize_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void initialize_params_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void enable_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void disable_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void c3_update_debugger_state_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static const mxArray *get_sim_state_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void set_sim_state_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_st);
static void c3_set_sim_state_side_effects_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void finalize_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void sf_gateway_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void mdl_start_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void initSimStructsc3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void init_script_number_translation(uint32_T c3_machineNumber, uint32_T
  c3_chartNumber, uint32_T c3_instanceNumber);
static const mxArray *c3_sf_marshallOut(void *chartInstanceVoid, void *c3_inData);
static int32_T c3_emlrt_marshallIn(SFc3_MON_sl_after_replacement2InstanceStruct *
  chartInstance, const mxArray *c3_b_sfEvent, const char_T *c3_identifier);
static int32_T c3_b_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_u, const emlrtMsgIdentifier *c3_parentId);
static void c3_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c3_mxArrayInData, const char_T *c3_varName, void *c3_outData);
static const mxArray *c3_b_sf_marshallOut(void *chartInstanceVoid, void
  *c3_inData);
static const mxArray *c3_c_sf_marshallOut(void *chartInstanceVoid, void
  *c3_inData);
static uint8_T c3_c_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_b_MANUAL, const char_T *c3_identifier);
static uint8_T c3_d_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_u, const emlrtMsgIdentifier *c3_parentId);
static void c3_b_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c3_mxArrayInData, const char_T *c3_varName, void *c3_outData);
static const mxArray *c3_e_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_b_setSimStateSideEffectsInfo, const char_T *c3_identifier);
static const mxArray *c3_f_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_u, const emlrtMsgIdentifier *c3_parentId);
static const mxArray *sf_get_hover_data_for_msg
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, int32_T c3_ssid);
static void c3_init_sf_message_store_memory
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);
static void init_dsm_address_info(SFc3_MON_sl_after_replacement2InstanceStruct
  *chartInstance);
static void init_simulink_io_address
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance);

/* Function Definitions */
static void initialize_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  if (sf_is_first_init_cond(chartInstance->S)) {
    initSimStructsc3_MON_sl_after_replacement2(chartInstance);
    chart_debug_initialize_data_addresses(chartInstance->S);
  }

  chartInstance->c3_sfEvent = CALL_EVENT;
  _sfTime_ = sf_get_time(chartInstance->S);
  chartInstance->c3_doSetSimStateSideEffects = 0U;
  chartInstance->c3_setSimStateSideEffectsInfo = NULL;
  chartInstance->c3_is_active_c3_MON_sl_after_replacement2 = 0U;
  if (!(sf_get_output_port_reusable(chartInstance->S, 1) != 0)) {
    *chartInstance->c3_r = 0;
    _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_r, 4U, 1U, 2U,
                          chartInstance->c3_sfEvent, false);
  }
}

static void initialize_params_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  real_T c3_d0;
  real_T c3_d1;
  real_T c3_d2;
  real_T c3_d3;
  sf_mex_import_named("MANUAL", sf_mex_get_sfun_param(chartInstance->S, 1, 0),
                      &c3_d0, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c3_MANUAL = (uint8_T)c3_d0;
  sf_mex_import_named("LO", sf_mex_get_sfun_param(chartInstance->S, 0, 0),
                      &c3_d1, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c3_LO = (uint8_T)c3_d1;
  sf_mex_import_named("MED", sf_mex_get_sfun_param(chartInstance->S, 3, 0),
                      &c3_d2, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c3_MED = (uint8_T)c3_d2;
  sf_mex_import_named("MAX", sf_mex_get_sfun_param(chartInstance->S, 2, 0),
                      &c3_d3, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c3_MAX = (uint8_T)c3_d3;
}

static void enable_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  _sfTime_ = sf_get_time(chartInstance->S);
}

static void disable_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  _sfTime_ = sf_get_time(chartInstance->S);
}

static void c3_update_debugger_state_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static const mxArray *get_sim_state_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  const mxArray *c3_st;
  const mxArray *c3_y = NULL;
  int32_T c3_hoistedGlobal;
  int32_T c3_u;
  const mxArray *c3_b_y = NULL;
  uint8_T c3_b_hoistedGlobal;
  uint8_T c3_b_u;
  const mxArray *c3_c_y = NULL;
  c3_st = NULL;
  c3_st = NULL;
  c3_y = NULL;
  sf_mex_assign(&c3_y, sf_mex_createcellmatrix(2, 1), false);
  c3_hoistedGlobal = *chartInstance->c3_r;
  c3_u = c3_hoistedGlobal;
  c3_b_y = NULL;
  sf_mex_assign(&c3_b_y, sf_mex_create("y", &c3_u, 6, 0U, 0U, 0U, 0), false);
  sf_mex_setcell(c3_y, 0, c3_b_y);
  c3_b_hoistedGlobal = chartInstance->c3_is_active_c3_MON_sl_after_replacement2;
  c3_b_u = c3_b_hoistedGlobal;
  c3_c_y = NULL;
  sf_mex_assign(&c3_c_y, sf_mex_create("y", &c3_b_u, 3, 0U, 0U, 0U, 0), false);
  sf_mex_setcell(c3_y, 1, c3_c_y);
  sf_mex_assign(&c3_st, c3_y, false);
  return c3_st;
}

static void set_sim_state_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_st)
{
  const mxArray *c3_u;
  chartInstance->c3_doneDoubleBufferReInit = true;
  c3_u = sf_mex_dup(c3_st);
  *chartInstance->c3_r = c3_emlrt_marshallIn(chartInstance, sf_mex_dup
    (sf_mex_getcell("r", c3_u, 0)), "r");
  chartInstance->c3_is_active_c3_MON_sl_after_replacement2 =
    c3_c_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "is_active_c3_MON_sl_after_replacement2", c3_u, 1)),
    "is_active_c3_MON_sl_after_replacement2");
  sf_mex_assign(&chartInstance->c3_setSimStateSideEffectsInfo,
                c3_e_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "setSimStateSideEffectsInfo", c3_u, 2)), "setSimStateSideEffectsInfo"), true);
  sf_mex_destroy(&c3_u);
  chartInstance->c3_doSetSimStateSideEffects = 1U;
  c3_update_debugger_state_c3_MON_sl_after_replacement2(chartInstance);
  sf_mex_destroy(&c3_st);
}

static void c3_set_sim_state_side_effects_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  if (chartInstance->c3_doSetSimStateSideEffects != 0) {
    chartInstance->c3_doSetSimStateSideEffects = 0U;
  }
}

static void finalize_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  sf_mex_destroy(&chartInstance->c3_setSimStateSideEffectsInfo);
}

static void sf_gateway_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  boolean_T c3_temp;
  boolean_T c3_b_temp;
  boolean_T c3_c_temp;
  boolean_T c3_d_temp;
  boolean_T c3_e_temp;
  boolean_T c3_out;
  boolean_T c3_f_temp;
  boolean_T c3_b_out;
  boolean_T c3_g_temp;
  boolean_T c3_c_out;
  boolean_T c3_h_temp;
  boolean_T c3_d_out;
  boolean_T guard1 = false;
  boolean_T guard2 = false;
  boolean_T guard3 = false;
  c3_set_sim_state_side_effects_c3_MON_sl_after_replacement2(chartInstance);
  _SFD_SYMBOL_SCOPE_PUSH(0U, 0U);
  _sfTime_ = sf_get_time(chartInstance->S);
  _SFD_CC_CALL(CHART_ENTER_SFUNCTION_TAG, 2U, chartInstance->c3_sfEvent);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_rise_max, 3U, 1U, 0U,
                        chartInstance->c3_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_rise_med, 2U, 1U, 0U,
                        chartInstance->c3_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_rise_lo, 1U, 1U, 0U,
                        chartInstance->c3_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_pre_mode, 0U, 1U, 0U,
                        chartInstance->c3_sfEvent, false);
  chartInstance->c3_sfEvent = CALL_EVENT;
  _SFD_CC_CALL(CHART_ENTER_DURING_FUNCTION_TAG, 2U, chartInstance->c3_sfEvent);
  _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 0U, chartInstance->c3_sfEvent);
  _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 1U, chartInstance->c3_sfEvent);
  c3_temp = (_SFD_CCP_CALL(1U, 0, CV_RELATIONAL_EVAL(5U, 1U, 0, (real_T)
    *chartInstance->c3_pre_mode, (real_T)chartInstance->c3_LO, 0, 0U,
    *chartInstance->c3_pre_mode == chartInstance->c3_LO) != 0U,
              chartInstance->c3_sfEvent) != 0);
  if (c3_temp) {
    c3_temp = (_SFD_CCP_CALL(1U, 1, *chartInstance->c3_rise_lo != 0U,
                chartInstance->c3_sfEvent) != 0);
  }

  c3_b_temp = c3_temp;
  if (!c3_b_temp) {
    c3_c_temp = (_SFD_CCP_CALL(1U, 2, CV_RELATIONAL_EVAL(5U, 1U, 1, (real_T)
      *chartInstance->c3_pre_mode, (real_T)chartInstance->c3_MED, 0, 0U,
      *chartInstance->c3_pre_mode == chartInstance->c3_MED) != 0U,
      chartInstance->c3_sfEvent) != 0);
    if (c3_c_temp) {
      c3_c_temp = (_SFD_CCP_CALL(1U, 3, *chartInstance->c3_rise_med != 0U,
        chartInstance->c3_sfEvent) != 0);
    }

    c3_b_temp = c3_c_temp;
  }

  c3_d_temp = c3_b_temp;
  if (!c3_d_temp) {
    c3_e_temp = (_SFD_CCP_CALL(1U, 4, CV_RELATIONAL_EVAL(5U, 1U, 2, (real_T)
      *chartInstance->c3_pre_mode, (real_T)chartInstance->c3_MAX, 0, 0U,
      *chartInstance->c3_pre_mode == chartInstance->c3_MAX) != 0U,
      chartInstance->c3_sfEvent) != 0);
    if (c3_e_temp) {
      c3_e_temp = (_SFD_CCP_CALL(1U, 5, *chartInstance->c3_rise_max != 0U,
        chartInstance->c3_sfEvent) != 0);
    }

    c3_d_temp = c3_e_temp;
  }

  c3_out = (CV_TRANSITION_EVAL(1U, (int32_T)c3_d_temp) != 0);
  guard1 = false;
  guard2 = false;
  guard3 = false;
  if (c3_out) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 1U, chartInstance->c3_sfEvent);
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 3U, chartInstance->c3_sfEvent);
    *chartInstance->c3_r = chartInstance->c3_MANUAL;
    _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_r, 4U, 5U, 3U,
                          chartInstance->c3_sfEvent, false);
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 12U, chartInstance->c3_sfEvent);
    guard3 = true;
  } else {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 2U, chartInstance->c3_sfEvent);
    _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 4U, chartInstance->c3_sfEvent);
    c3_f_temp = (_SFD_CCP_CALL(4U, 0, CV_RELATIONAL_EVAL(5U, 4U, 0, (real_T)
      *chartInstance->c3_pre_mode, (real_T)chartInstance->c3_LO, 0, 1U,
      *chartInstance->c3_pre_mode != chartInstance->c3_LO) != 0U,
      chartInstance->c3_sfEvent) != 0);
    if (c3_f_temp) {
      c3_f_temp = (_SFD_CCP_CALL(4U, 1, *chartInstance->c3_rise_lo != 0U,
        chartInstance->c3_sfEvent) != 0);
    }

    c3_b_out = (CV_TRANSITION_EVAL(4U, (int32_T)c3_f_temp) != 0);
    if (c3_b_out) {
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 4U, chartInstance->c3_sfEvent);
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 6U, chartInstance->c3_sfEvent);
      *chartInstance->c3_r = chartInstance->c3_LO;
      _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_r, 4U, 5U, 6U,
                            chartInstance->c3_sfEvent, false);
      guard3 = true;
    } else {
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 5U, chartInstance->c3_sfEvent);
      _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 7U,
                   chartInstance->c3_sfEvent);
      c3_g_temp = (_SFD_CCP_CALL(7U, 0, CV_RELATIONAL_EVAL(5U, 7U, 0, (real_T)
        *chartInstance->c3_pre_mode, (real_T)chartInstance->c3_MED, 0, 1U,
        *chartInstance->c3_pre_mode != chartInstance->c3_MED) != 0U,
        chartInstance->c3_sfEvent) != 0);
      if (c3_g_temp) {
        c3_g_temp = (_SFD_CCP_CALL(7U, 1, *chartInstance->c3_rise_med != 0U,
          chartInstance->c3_sfEvent) != 0);
      }

      c3_c_out = (CV_TRANSITION_EVAL(7U, (int32_T)c3_g_temp) != 0);
      if (c3_c_out) {
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 7U, chartInstance->c3_sfEvent);
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 9U, chartInstance->c3_sfEvent);
        *chartInstance->c3_r = chartInstance->c3_MED;
        _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_r, 4U, 5U, 9U,
                              chartInstance->c3_sfEvent, false);
        guard2 = true;
      } else {
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 14U, chartInstance->c3_sfEvent);
        _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 15U,
                     chartInstance->c3_sfEvent);
        c3_h_temp = (_SFD_CCP_CALL(15U, 0, CV_RELATIONAL_EVAL(5U, 15U, 0,
          (real_T)*chartInstance->c3_pre_mode, (real_T)chartInstance->c3_MAX, 0,
          1U, *chartInstance->c3_pre_mode != chartInstance->c3_MAX) != 0U,
          chartInstance->c3_sfEvent) != 0);
        if (c3_h_temp) {
          c3_h_temp = (_SFD_CCP_CALL(15U, 1, *chartInstance->c3_rise_max != 0U,
            chartInstance->c3_sfEvent) != 0);
        }

        c3_d_out = (CV_TRANSITION_EVAL(15U, (int32_T)c3_h_temp) != 0);
        if (c3_d_out) {
          _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 15U, chartInstance->c3_sfEvent);
          _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 17U, chartInstance->c3_sfEvent);
          *chartInstance->c3_r = chartInstance->c3_MAX;
          _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_r, 4U, 5U, 17U,
                                chartInstance->c3_sfEvent, false);
          guard1 = true;
        } else {
          _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 8U, chartInstance->c3_sfEvent);
          *chartInstance->c3_r = *chartInstance->c3_pre_mode;
          _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c3_r, 4U, 5U, 8U,
                                chartInstance->c3_sfEvent, false);
        }
      }
    }
  }

  if (guard3 == true) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 11U, chartInstance->c3_sfEvent);
    guard2 = true;
  }

  if (guard2 == true) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 16U, chartInstance->c3_sfEvent);
    guard1 = true;
  }

  if (guard1 == true) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 10U, chartInstance->c3_sfEvent);
  }

  _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 13U, chartInstance->c3_sfEvent);
  _SFD_CC_CALL(EXIT_OUT_OF_FUNCTION_TAG, 2U, chartInstance->c3_sfEvent);
  _SFD_SYMBOL_SCOPE_POP();
  _SFD_CHECK_FOR_STATE_INCONSISTENCY(_MON_sl_after_replacement2MachineNumber_,
    chartInstance->chartNumber, chartInstance->instanceNumber);
}

static void mdl_start_c3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  c3_init_sf_message_store_memory(chartInstance);
}

static void initSimStructsc3_MON_sl_after_replacement2
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static void init_script_number_translation(uint32_T c3_machineNumber, uint32_T
  c3_chartNumber, uint32_T c3_instanceNumber)
{
  (void)c3_machineNumber;
  (void)c3_chartNumber;
  (void)c3_instanceNumber;
}

const mxArray *sf_c3_MON_sl_after_replacement2_get_eml_resolved_functions_info
  (void)
{
  const mxArray *c3_nameCaptureInfo = NULL;
  c3_nameCaptureInfo = NULL;
  sf_mex_assign(&c3_nameCaptureInfo, sf_mex_create("nameCaptureInfo", NULL, 0,
    0U, 1U, 0U, 2, 0, 1), false);
  return c3_nameCaptureInfo;
}

static const mxArray *c3_sf_marshallOut(void *chartInstanceVoid, void *c3_inData)
{
  const mxArray *c3_mxArrayOutData = NULL;
  int32_T c3_u;
  const mxArray *c3_y = NULL;
  SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance;
  chartInstance = (SFc3_MON_sl_after_replacement2InstanceStruct *)
    chartInstanceVoid;
  c3_mxArrayOutData = NULL;
  c3_u = *(int32_T *)c3_inData;
  c3_y = NULL;
  sf_mex_assign(&c3_y, sf_mex_create("y", &c3_u, 6, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c3_mxArrayOutData, c3_y, false);
  return c3_mxArrayOutData;
}

static int32_T c3_emlrt_marshallIn(SFc3_MON_sl_after_replacement2InstanceStruct *
  chartInstance, const mxArray *c3_b_sfEvent, const char_T *c3_identifier)
{
  int32_T c3_y;
  emlrtMsgIdentifier c3_thisId;
  c3_thisId.fIdentifier = c3_identifier;
  c3_thisId.fParent = NULL;
  c3_thisId.bParentIsCell = false;
  c3_y = c3_b_emlrt_marshallIn(chartInstance, sf_mex_dup(c3_b_sfEvent),
    &c3_thisId);
  sf_mex_destroy(&c3_b_sfEvent);
  return c3_y;
}

static int32_T c3_b_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_u, const emlrtMsgIdentifier *c3_parentId)
{
  int32_T c3_y;
  int32_T c3_i0;
  (void)chartInstance;
  sf_mex_import(c3_parentId, sf_mex_dup(c3_u), &c3_i0, 1, 6, 0U, 0, 0U, 0);
  c3_y = c3_i0;
  sf_mex_destroy(&c3_u);
  return c3_y;
}

static void c3_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c3_mxArrayInData, const char_T *c3_varName, void *c3_outData)
{
  const mxArray *c3_b_sfEvent;
  const char_T *c3_identifier;
  emlrtMsgIdentifier c3_thisId;
  int32_T c3_y;
  SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance;
  chartInstance = (SFc3_MON_sl_after_replacement2InstanceStruct *)
    chartInstanceVoid;
  c3_b_sfEvent = sf_mex_dup(c3_mxArrayInData);
  c3_identifier = c3_varName;
  c3_thisId.fIdentifier = c3_identifier;
  c3_thisId.fParent = NULL;
  c3_thisId.bParentIsCell = false;
  c3_y = c3_b_emlrt_marshallIn(chartInstance, sf_mex_dup(c3_b_sfEvent),
    &c3_thisId);
  sf_mex_destroy(&c3_b_sfEvent);
  *(int32_T *)c3_outData = c3_y;
  sf_mex_destroy(&c3_mxArrayInData);
}

static const mxArray *c3_b_sf_marshallOut(void *chartInstanceVoid, void
  *c3_inData)
{
  const mxArray *c3_mxArrayOutData = NULL;
  boolean_T c3_u;
  const mxArray *c3_y = NULL;
  SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance;
  chartInstance = (SFc3_MON_sl_after_replacement2InstanceStruct *)
    chartInstanceVoid;
  c3_mxArrayOutData = NULL;
  c3_u = *(boolean_T *)c3_inData;
  c3_y = NULL;
  sf_mex_assign(&c3_y, sf_mex_create("y", &c3_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c3_mxArrayOutData, c3_y, false);
  return c3_mxArrayOutData;
}

static const mxArray *c3_c_sf_marshallOut(void *chartInstanceVoid, void
  *c3_inData)
{
  const mxArray *c3_mxArrayOutData = NULL;
  uint8_T c3_u;
  const mxArray *c3_y = NULL;
  SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance;
  chartInstance = (SFc3_MON_sl_after_replacement2InstanceStruct *)
    chartInstanceVoid;
  c3_mxArrayOutData = NULL;
  c3_u = *(uint8_T *)c3_inData;
  c3_y = NULL;
  sf_mex_assign(&c3_y, sf_mex_create("y", &c3_u, 3, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c3_mxArrayOutData, c3_y, false);
  return c3_mxArrayOutData;
}

static uint8_T c3_c_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_b_MANUAL, const char_T *c3_identifier)
{
  uint8_T c3_y;
  emlrtMsgIdentifier c3_thisId;
  c3_thisId.fIdentifier = c3_identifier;
  c3_thisId.fParent = NULL;
  c3_thisId.bParentIsCell = false;
  c3_y = c3_d_emlrt_marshallIn(chartInstance, sf_mex_dup(c3_b_MANUAL),
    &c3_thisId);
  sf_mex_destroy(&c3_b_MANUAL);
  return c3_y;
}

static uint8_T c3_d_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_u, const emlrtMsgIdentifier *c3_parentId)
{
  uint8_T c3_y;
  uint8_T c3_u0;
  (void)chartInstance;
  sf_mex_import(c3_parentId, sf_mex_dup(c3_u), &c3_u0, 1, 3, 0U, 0, 0U, 0);
  c3_y = c3_u0;
  sf_mex_destroy(&c3_u);
  return c3_y;
}

static void c3_b_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c3_mxArrayInData, const char_T *c3_varName, void *c3_outData)
{
  const mxArray *c3_b_MANUAL;
  const char_T *c3_identifier;
  emlrtMsgIdentifier c3_thisId;
  uint8_T c3_y;
  SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance;
  chartInstance = (SFc3_MON_sl_after_replacement2InstanceStruct *)
    chartInstanceVoid;
  c3_b_MANUAL = sf_mex_dup(c3_mxArrayInData);
  c3_identifier = c3_varName;
  c3_thisId.fIdentifier = c3_identifier;
  c3_thisId.fParent = NULL;
  c3_thisId.bParentIsCell = false;
  c3_y = c3_d_emlrt_marshallIn(chartInstance, sf_mex_dup(c3_b_MANUAL),
    &c3_thisId);
  sf_mex_destroy(&c3_b_MANUAL);
  *(uint8_T *)c3_outData = c3_y;
  sf_mex_destroy(&c3_mxArrayInData);
}

static const mxArray *c3_e_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_b_setSimStateSideEffectsInfo, const char_T *c3_identifier)
{
  const mxArray *c3_y = NULL;
  emlrtMsgIdentifier c3_thisId;
  c3_y = NULL;
  c3_thisId.fIdentifier = c3_identifier;
  c3_thisId.fParent = NULL;
  c3_thisId.bParentIsCell = false;
  sf_mex_assign(&c3_y, c3_f_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c3_b_setSimStateSideEffectsInfo), &c3_thisId), false);
  sf_mex_destroy(&c3_b_setSimStateSideEffectsInfo);
  return c3_y;
}

static const mxArray *c3_f_emlrt_marshallIn
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, const mxArray
   *c3_u, const emlrtMsgIdentifier *c3_parentId)
{
  const mxArray *c3_y = NULL;
  (void)chartInstance;
  (void)c3_parentId;
  c3_y = NULL;
  sf_mex_assign(&c3_y, sf_mex_duplicatearraysafe(&c3_u), false);
  sf_mex_destroy(&c3_u);
  return c3_y;
}

static const mxArray *sf_get_hover_data_for_msg
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance, int32_T c3_ssid)
{
  (void)chartInstance;
  (void)c3_ssid;
  return NULL;
}

static void c3_init_sf_message_store_memory
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static void init_dsm_address_info(SFc3_MON_sl_after_replacement2InstanceStruct
  *chartInstance)
{
  (void)chartInstance;
}

static void init_simulink_io_address
  (SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance)
{
  chartInstance->c3_pre_mode = (int32_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 0);
  chartInstance->c3_rise_lo = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 1);
  chartInstance->c3_rise_med = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 2);
  chartInstance->c3_rise_max = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 3);
  chartInstance->c3_r = (int32_T *)ssGetOutputPortSignal_wrapper
    (chartInstance->S, 1);
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

void sf_c3_MON_sl_after_replacement2_get_check_sum(mxArray *plhs[])
{
  ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(3835018122U);
  ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(2947369329U);
  ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(2585585184U);
  ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(603499795U);
}

mxArray* sf_c3_MON_sl_after_replacement2_get_post_codegen_info(void);
mxArray *sf_c3_MON_sl_after_replacement2_get_autoinheritance_info(void)
{
  const char *autoinheritanceFields[] = { "checksum", "inputs", "parameters",
    "outputs", "locals", "postCodegenInfo" };

  mxArray *mxAutoinheritanceInfo = mxCreateStructMatrix(1, 1, sizeof
    (autoinheritanceFields)/sizeof(autoinheritanceFields[0]),
    autoinheritanceFields);

  {
    mxArray *mxChecksum = mxCreateString("xK5TcgJO4zS2wC1ksyI1t");
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(8));
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(3));
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(3));
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(3));
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(3));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,3,"type",mxType);
    }

    mxSetField(mxData,3,"complexity",mxCreateDoubleScalar(0));
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(8));
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
      sf_c3_MON_sl_after_replacement2_get_post_codegen_info();
    mxSetField(mxAutoinheritanceInfo,0,"postCodegenInfo",mxPostCodegenInfo);
  }

  return(mxAutoinheritanceInfo);
}

mxArray *sf_c3_MON_sl_after_replacement2_third_party_uses_info(void)
{
  mxArray * mxcell3p = mxCreateCellMatrix(1,0);
  return(mxcell3p);
}

mxArray *sf_c3_MON_sl_after_replacement2_jit_fallback_info(void)
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

mxArray *sf_c3_MON_sl_after_replacement2_updateBuildInfo_args_info(void)
{
  mxArray *mxBIArgs = mxCreateCellMatrix(1,0);
  return mxBIArgs;
}

mxArray* sf_c3_MON_sl_after_replacement2_get_post_codegen_info(void)
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

static const mxArray *sf_get_sim_state_info_c3_MON_sl_after_replacement2(void)
{
  const char *infoFields[] = { "chartChecksum", "varInfo" };

  mxArray *mxInfo = mxCreateStructMatrix(1, 1, 2, infoFields);
  const char *infoEncStr[] = {
    "100 S1x2'type','srcId','name','auxInfo'{{M[1],M[1780],T\"r\",},{M[8],M[0],T\"is_active_c3_MON_sl_after_replacement2\",}}"
  };

  mxArray *mxVarInfo = sf_mex_decode_encoded_mx_struct_array(infoEncStr, 2, 10);
  mxArray *mxChecksum = mxCreateDoubleMatrix(1, 4, mxREAL);
  sf_c3_MON_sl_after_replacement2_get_check_sum(&mxChecksum);
  mxSetField(mxInfo, 0, infoFields[0], mxChecksum);
  mxSetField(mxInfo, 0, infoFields[1], mxVarInfo);
  return mxInfo;
}

static const mxArray* sf_opaque_get_hover_data_for_msg(void* chartInstance,
  int32_T msgSSID)
{
  return sf_get_hover_data_for_msg
    ( (SFc3_MON_sl_after_replacement2InstanceStruct *) chartInstance, msgSSID);
}

static void chart_debug_initialization(SimStruct *S, unsigned int
  fullDebuggerInitialization)
{
  if (!sim_mode_is_rtw_gen(S)) {
    SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance =
      (SFc3_MON_sl_after_replacement2InstanceStruct *)sf_get_chart_instance_ptr
      (S);
    if (ssIsFirstInitCond(S) && fullDebuggerInitialization==1) {
      /* do this only if simulation is starting */
      {
        unsigned int chartAlreadyPresent;
        chartAlreadyPresent = sf_debug_initialize_chart
          (sfGlobalDebugInstanceStruct,
           _MON_sl_after_replacement2MachineNumber_,
           3,
           0,
           18,
           0,
           9,
           0,
           0,
           0,
           0,
           0,
           &chartInstance->chartNumber,
           &chartInstance->instanceNumber,
           (void *)S);

        /* Each instance must initialize its own list of scripts */
        init_script_number_translation(_MON_sl_after_replacement2MachineNumber_,
          chartInstance->chartNumber,chartInstance->instanceNumber);
        if (chartAlreadyPresent==0) {
          /* this is the first instance */
          sf_debug_set_chart_disable_implicit_casting
            (sfGlobalDebugInstanceStruct,
             _MON_sl_after_replacement2MachineNumber_,chartInstance->chartNumber,
             1);
          sf_debug_set_chart_event_thresholds(sfGlobalDebugInstanceStruct,
            _MON_sl_after_replacement2MachineNumber_,
            chartInstance->chartNumber,
            0,
            0,
            0);
          _SFD_SET_DATA_PROPS(0,1,1,0,"pre_mode");
          _SFD_SET_DATA_PROPS(1,1,1,0,"rise_lo");
          _SFD_SET_DATA_PROPS(2,1,1,0,"rise_med");
          _SFD_SET_DATA_PROPS(3,1,1,0,"rise_max");
          _SFD_SET_DATA_PROPS(4,2,0,1,"r");
          _SFD_SET_DATA_PROPS(5,10,0,0,"LO");
          _SFD_SET_DATA_PROPS(6,10,0,0,"MANUAL");
          _SFD_SET_DATA_PROPS(7,10,0,0,"MAX");
          _SFD_SET_DATA_PROPS(8,10,0,0,"MED");
          _SFD_CH_SUBSTATE_COUNT(0);
          _SFD_CH_SUBSTATE_DECOMP(0);
        }

        _SFD_CV_INIT_CHART(0,0,0,0);
        _SFD_CV_INIT_TRANS(0,0,NULL,NULL,0,NULL);

        {
          static unsigned int sStartGuardMap[] = { 5, 24, 45, 65, 87, 107 };

          static unsigned int sEndGuardMap[] = { 19, 31, 60, 73, 102, 115 };

          static int sPostFixPredicateTree[] = { 0, 1, -3, 2, 3, -3, -2, 4, 5,
            -3, -2 };

          _SFD_CV_INIT_TRANS(1,6,&(sStartGuardMap[0]),&(sEndGuardMap[0]),11,
                             &(sPostFixPredicateTree[0]));
        }

        {
          static unsigned int sStartRelationalopMap[] = { 5, 45, 87 };

          static unsigned int sEndRelationalopMap[] = { 19, 60, 102 };

          static int sRelationalopEps[] = { 0, 0, 0 };

          static int sRelationalopType[] = { 0, 0, 0, 0, 0, 0 };

          _SFD_CV_INIT_TRANSITION_RELATIONALOP(1,3,&(sStartRelationalopMap[0]),
            &(sEndRelationalopMap[0]),&(sRelationalopEps[0]),
            &(sRelationalopType[0]));
        }

        _SFD_CV_INIT_TRANS(2,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(3,0,NULL,NULL,0,NULL);

        {
          static unsigned int sStartGuardMap[] = { 4, 23 };

          static unsigned int sEndGuardMap[] = { 18, 30 };

          static int sPostFixPredicateTree[] = { 0, 1, -3 };

          _SFD_CV_INIT_TRANS(4,2,&(sStartGuardMap[0]),&(sEndGuardMap[0]),3,
                             &(sPostFixPredicateTree[0]));
        }

        {
          static unsigned int sStartRelationalopMap[] = { 4 };

          static unsigned int sEndRelationalopMap[] = { 18 };

          static int sRelationalopEps[] = { 0 };

          static int sRelationalopType[] = { 1, 1 };

          _SFD_CV_INIT_TRANSITION_RELATIONALOP(4,1,&(sStartRelationalopMap[0]),
            &(sEndRelationalopMap[0]),&(sRelationalopEps[0]),
            &(sRelationalopType[0]));
        }

        _SFD_CV_INIT_TRANS(5,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(6,0,NULL,NULL,0,NULL);

        {
          static unsigned int sStartGuardMap[] = { 3, 23 };

          static unsigned int sEndGuardMap[] = { 18, 31 };

          static int sPostFixPredicateTree[] = { 0, 1, -3 };

          _SFD_CV_INIT_TRANS(7,2,&(sStartGuardMap[0]),&(sEndGuardMap[0]),3,
                             &(sPostFixPredicateTree[0]));
        }

        {
          static unsigned int sStartRelationalopMap[] = { 3 };

          static unsigned int sEndRelationalopMap[] = { 18 };

          static int sRelationalopEps[] = { 0 };

          static int sRelationalopType[] = { 1, 1 };

          _SFD_CV_INIT_TRANSITION_RELATIONALOP(7,1,&(sStartRelationalopMap[0]),
            &(sEndRelationalopMap[0]),&(sRelationalopEps[0]),
            &(sRelationalopType[0]));
        }

        _SFD_CV_INIT_TRANS(14,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(9,0,NULL,NULL,0,NULL);

        {
          static unsigned int sStartGuardMap[] = { 3, 23 };

          static unsigned int sEndGuardMap[] = { 18, 31 };

          static int sPostFixPredicateTree[] = { 0, 1, -3 };

          _SFD_CV_INIT_TRANS(15,2,&(sStartGuardMap[0]),&(sEndGuardMap[0]),3,
                             &(sPostFixPredicateTree[0]));
        }

        {
          static unsigned int sStartRelationalopMap[] = { 3 };

          static unsigned int sEndRelationalopMap[] = { 18 };

          static int sRelationalopEps[] = { 0 };

          static int sRelationalopType[] = { 1, 1 };

          _SFD_CV_INIT_TRANSITION_RELATIONALOP(15,1,&(sStartRelationalopMap[0]),
            &(sEndRelationalopMap[0]),&(sRelationalopEps[0]),
            &(sRelationalopType[0]));
        }

        _SFD_CV_INIT_TRANS(8,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(17,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(10,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(16,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(11,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(12,0,NULL,NULL,0,NULL);
        _SFD_CV_INIT_TRANS(13,0,NULL,NULL,0,NULL);
        _SFD_SET_DATA_COMPILED_PROPS(0,SF_INT32,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(1,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(2,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(3,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(4,SF_INT32,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_sf_marshallOut,(MexInFcnForType)c3_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(5,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_c_sf_marshallOut,(MexInFcnForType)c3_b_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(6,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_c_sf_marshallOut,(MexInFcnForType)c3_b_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(7,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_c_sf_marshallOut,(MexInFcnForType)c3_b_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(8,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c3_c_sf_marshallOut,(MexInFcnForType)c3_b_sf_marshallIn);
      }
    } else {
      sf_debug_reset_current_state_configuration(sfGlobalDebugInstanceStruct,
        _MON_sl_after_replacement2MachineNumber_,chartInstance->chartNumber,
        chartInstance->instanceNumber);
    }
  }
}

static void chart_debug_initialize_data_addresses(SimStruct *S)
{
  if (!sim_mode_is_rtw_gen(S)) {
    SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance =
      (SFc3_MON_sl_after_replacement2InstanceStruct *)sf_get_chart_instance_ptr
      (S);
    if (ssIsFirstInitCond(S)) {
      /* do this only if simulation is starting and after we know the addresses of all data */
      {
        _SFD_SET_DATA_VALUE_PTR(0U, chartInstance->c3_pre_mode);
        _SFD_SET_DATA_VALUE_PTR(1U, chartInstance->c3_rise_lo);
        _SFD_SET_DATA_VALUE_PTR(2U, chartInstance->c3_rise_med);
        _SFD_SET_DATA_VALUE_PTR(3U, chartInstance->c3_rise_max);
        _SFD_SET_DATA_VALUE_PTR(4U, chartInstance->c3_r);
        _SFD_SET_DATA_VALUE_PTR(6U, &chartInstance->c3_MANUAL);
        _SFD_SET_DATA_VALUE_PTR(5U, &chartInstance->c3_LO);
        _SFD_SET_DATA_VALUE_PTR(8U, &chartInstance->c3_MED);
        _SFD_SET_DATA_VALUE_PTR(7U, &chartInstance->c3_MAX);
      }
    }
  }
}

static const char* sf_get_instance_specialization(void)
{
  return "sGzxgwr31j8KjkMHo1xeKeG";
}

static void sf_opaque_initialize_c3_MON_sl_after_replacement2(void
  *chartInstanceVar)
{
  chart_debug_initialization(((SFc3_MON_sl_after_replacement2InstanceStruct*)
    chartInstanceVar)->S,0);
  initialize_params_c3_MON_sl_after_replacement2
    ((SFc3_MON_sl_after_replacement2InstanceStruct*) chartInstanceVar);
  initialize_c3_MON_sl_after_replacement2
    ((SFc3_MON_sl_after_replacement2InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_enable_c3_MON_sl_after_replacement2(void *chartInstanceVar)
{
  enable_c3_MON_sl_after_replacement2
    ((SFc3_MON_sl_after_replacement2InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_disable_c3_MON_sl_after_replacement2(void
  *chartInstanceVar)
{
  disable_c3_MON_sl_after_replacement2
    ((SFc3_MON_sl_after_replacement2InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_gateway_c3_MON_sl_after_replacement2(void
  *chartInstanceVar)
{
  sf_gateway_c3_MON_sl_after_replacement2
    ((SFc3_MON_sl_after_replacement2InstanceStruct*) chartInstanceVar);
}

static const mxArray* sf_opaque_get_sim_state_c3_MON_sl_after_replacement2
  (SimStruct* S)
{
  return get_sim_state_c3_MON_sl_after_replacement2
    ((SFc3_MON_sl_after_replacement2InstanceStruct *)sf_get_chart_instance_ptr(S));/* raw sim ctx */
}

static void sf_opaque_set_sim_state_c3_MON_sl_after_replacement2(SimStruct* S,
  const mxArray *st)
{
  set_sim_state_c3_MON_sl_after_replacement2
    ((SFc3_MON_sl_after_replacement2InstanceStruct*)sf_get_chart_instance_ptr(S),
     st);
}

static void sf_opaque_terminate_c3_MON_sl_after_replacement2(void
  *chartInstanceVar)
{
  if (chartInstanceVar!=NULL) {
    SimStruct *S = ((SFc3_MON_sl_after_replacement2InstanceStruct*)
                    chartInstanceVar)->S;
    if (sim_mode_is_rtw_gen(S) || sim_mode_is_external(S)) {
      sf_clear_rtw_identifier(S);
      unload_MON_sl_after_replacement2_optimization_info();
    }

    finalize_c3_MON_sl_after_replacement2
      ((SFc3_MON_sl_after_replacement2InstanceStruct*) chartInstanceVar);
    utFree(chartInstanceVar);
    if (ssGetUserData(S)!= NULL) {
      sf_free_ChartRunTimeInfo(S);
    }

    ssSetUserData(S,NULL);
  }
}

static void sf_opaque_init_subchart_simstructs(void *chartInstanceVar)
{
  initSimStructsc3_MON_sl_after_replacement2
    ((SFc3_MON_sl_after_replacement2InstanceStruct*) chartInstanceVar);
}

extern unsigned int sf_machine_global_initializer_called(void);
static void mdlProcessParameters_c3_MON_sl_after_replacement2(SimStruct *S)
{
  int i;
  for (i=0;i<ssGetNumRunTimeParams(S);i++) {
    if (ssGetSFcnParamTunable(S,i)) {
      ssUpdateDlgParamAsRunTimeParam(S,i);
    }
  }

  if (sf_machine_global_initializer_called()) {
    initialize_params_c3_MON_sl_after_replacement2
      ((SFc3_MON_sl_after_replacement2InstanceStruct*)sf_get_chart_instance_ptr
       (S));
  }
}

static void mdlSetWorkWidths_c3_MON_sl_after_replacement2(SimStruct *S)
{
  /* Actual parameters from chart:
     LO MANUAL MAX MED
   */
  const char_T *rtParamNames[] = { "LO", "MANUAL", "MAX", "MED" };

  ssSetNumRunTimeParams(S,ssGetSFcnParamsCount(S));

  /* registration for LO*/
  ssRegDlgParamAsRunTimeParam(S, 0, 0, rtParamNames[0], SS_UINT8);

  /* registration for MANUAL*/
  ssRegDlgParamAsRunTimeParam(S, 1, 1, rtParamNames[1], SS_UINT8);

  /* registration for MAX*/
  ssRegDlgParamAsRunTimeParam(S, 2, 2, rtParamNames[2], SS_UINT8);

  /* registration for MED*/
  ssRegDlgParamAsRunTimeParam(S, 3, 3, rtParamNames[3], SS_UINT8);
  ssMdlUpdateIsEmpty(S, 1);
  if (sim_mode_is_rtw_gen(S) || sim_mode_is_external(S)) {
    mxArray *infoStruct = load_MON_sl_after_replacement2_optimization_info
      (sim_mode_is_rtw_gen(S), sim_mode_is_modelref_sim(S), sim_mode_is_external
       (S));
    int_T chartIsInlinable =
      (int_T)sf_is_chart_inlinable(sf_get_instance_specialization(),infoStruct,3);
    ssSetStateflowIsInlinable(S,chartIsInlinable);
    ssSetRTWCG(S,1);
    ssSetEnableFcnIsTrivial(S,1);
    ssSetDisableFcnIsTrivial(S,1);
    ssSetNotMultipleInlinable(S,sf_rtw_info_uint_prop
      (sf_get_instance_specialization(),infoStruct,3,
       "gatewayCannotBeInlinedMultipleTimes"));
    sf_set_chart_accesses_machine_info(S, sf_get_instance_specialization(),
      infoStruct, 3);
    sf_update_buildInfo(S, sf_get_instance_specialization(),infoStruct,3);
    if (chartIsInlinable) {
      ssSetInputPortOptimOpts(S, 0, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 1, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 2, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 3, SS_REUSABLE_AND_LOCAL);
      sf_mark_chart_expressionable_inputs(S,sf_get_instance_specialization(),
        infoStruct,3,4);
      sf_mark_chart_reusable_outputs(S,sf_get_instance_specialization(),
        infoStruct,3,1);
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

    sf_set_rtw_dwork_info(S,sf_get_instance_specialization(),infoStruct,3);
    ssSetHasSubFunctions(S,!(chartIsInlinable));
  } else {
  }

  ssSetOptions(S,ssGetOptions(S)|SS_OPTION_WORKS_WITH_CODE_REUSE);
  ssSetChecksum0(S,(1042393825U));
  ssSetChecksum1(S,(3228247057U));
  ssSetChecksum2(S,(2839487307U));
  ssSetChecksum3(S,(1538456094U));
  ssSetmdlDerivatives(S, NULL);
  ssSetExplicitFCSSCtrl(S,1);
  ssSupportsMultipleExecInstances(S,1);
}

static void mdlRTW_c3_MON_sl_after_replacement2(SimStruct *S)
{
  if (sim_mode_is_rtw_gen(S)) {
    ssWriteRTWStrParam(S, "StateflowChartType", "Stateflow");
  }
}

static void mdlStart_c3_MON_sl_after_replacement2(SimStruct *S)
{
  SFc3_MON_sl_after_replacement2InstanceStruct *chartInstance;
  chartInstance = (SFc3_MON_sl_after_replacement2InstanceStruct *)utMalloc
    (sizeof(SFc3_MON_sl_after_replacement2InstanceStruct));
  if (chartInstance==NULL) {
    sf_mex_error_message("Could not allocate memory for chart instance.");
  }

  memset(chartInstance, 0, sizeof(SFc3_MON_sl_after_replacement2InstanceStruct));
  chartInstance->chartInfo.chartInstance = chartInstance;
  chartInstance->chartInfo.isEMLChart = 0;
  chartInstance->chartInfo.chartInitialized = 0;
  chartInstance->chartInfo.sFunctionGateway =
    sf_opaque_gateway_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.initializeChart =
    sf_opaque_initialize_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.terminateChart =
    sf_opaque_terminate_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.enableChart =
    sf_opaque_enable_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.disableChart =
    sf_opaque_disable_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.getSimState =
    sf_opaque_get_sim_state_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.setSimState =
    sf_opaque_set_sim_state_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.getSimStateInfo =
    sf_get_sim_state_info_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.zeroCrossings = NULL;
  chartInstance->chartInfo.outputs = NULL;
  chartInstance->chartInfo.derivatives = NULL;
  chartInstance->chartInfo.mdlRTW = mdlRTW_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.mdlStart = mdlStart_c3_MON_sl_after_replacement2;
  chartInstance->chartInfo.mdlSetWorkWidths =
    mdlSetWorkWidths_c3_MON_sl_after_replacement2;
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
  mdl_start_c3_MON_sl_after_replacement2(chartInstance);
}

void c3_MON_sl_after_replacement2_method_dispatcher(SimStruct *S, int_T method,
  void *data)
{
  switch (method) {
   case SS_CALL_MDL_START:
    mdlStart_c3_MON_sl_after_replacement2(S);
    break;

   case SS_CALL_MDL_SET_WORK_WIDTHS:
    mdlSetWorkWidths_c3_MON_sl_after_replacement2(S);
    break;

   case SS_CALL_MDL_PROCESS_PARAMETERS:
    mdlProcessParameters_c3_MON_sl_after_replacement2(S);
    break;

   default:
    /* Unhandled method */
    sf_mex_error_message("Stateflow Internal Error:\n"
                         "Error calling c3_MON_sl_after_replacement2_method_dispatcher.\n"
                         "Can't handle method %d.\n", method);
    break;
  }
}
