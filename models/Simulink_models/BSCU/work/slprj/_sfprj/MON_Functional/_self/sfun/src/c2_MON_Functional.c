/* Include files */

#include "MON_Functional_sfun.h"
#include "c2_MON_Functional.h"
#define CHARTINSTANCE_CHARTNUMBER      (chartInstance->chartNumber)
#define CHARTINSTANCE_INSTANCENUMBER   (chartInstance->instanceNumber)
#include "MON_Functional_sfun_debug_macros.h"
#define _SF_MEX_LISTEN_FOR_CTRL_C(S)   sf_mex_listen_for_ctrl_c_with_debugger(S, sfGlobalDebugInstanceStruct);

static void chart_debug_initialization(SimStruct *S, unsigned int
  fullDebuggerInitialization);
static void chart_debug_initialize_data_addresses(SimStruct *S);
static const mxArray* sf_opaque_get_hover_data_for_msg(void *chartInstance,
  int32_T msgSSID);

/* Type Definitions */

/* Named Constants */
#define CALL_EVENT                     (-1)
#define c2_IN_NO_ACTIVE_CHILD          ((uint8_T)0U)

/* Variable Declarations */

/* Variable Definitions */
static real_T _sfTime_;

/* Function Declarations */
static void initialize_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void initialize_params_c2_MON_Functional
  (SFc2_MON_FunctionalInstanceStruct *chartInstance);
static void enable_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void disable_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void c2_update_debugger_state_c2_MON_Functional
  (SFc2_MON_FunctionalInstanceStruct *chartInstance);
static const mxArray *get_sim_state_c2_MON_Functional
  (SFc2_MON_FunctionalInstanceStruct *chartInstance);
static void set_sim_state_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_st);
static void c2_set_sim_state_side_effects_c2_MON_Functional
  (SFc2_MON_FunctionalInstanceStruct *chartInstance);
static void finalize_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void sf_gateway_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void mdl_start_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void initSimStructsc2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void init_script_number_translation(uint32_T c2_machineNumber, uint32_T
  c2_chartNumber, uint32_T c2_instanceNumber);
static const mxArray *c2_sf_marshallOut(void *chartInstanceVoid, void *c2_inData);
static int32_T c2_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_b_sfEvent, const char_T *c2_identifier);
static int32_T c2_b_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_u, const emlrtMsgIdentifier *c2_parentId);
static void c2_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData);
static const mxArray *c2_b_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static const mxArray *c2_c_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static uint8_T c2_c_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_b_MANUAL, const char_T *c2_identifier);
static uint8_T c2_d_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_u, const emlrtMsgIdentifier *c2_parentId);
static void c2_b_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData);
static const mxArray *c2_e_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_b_setSimStateSideEffectsInfo, const char_T
  *c2_identifier);
static const mxArray *c2_f_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_u, const emlrtMsgIdentifier *c2_parentId);
static const mxArray *sf_get_hover_data_for_msg
  (SFc2_MON_FunctionalInstanceStruct *chartInstance, int32_T c2_ssid);
static void c2_init_sf_message_store_memory(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void init_dsm_address_info(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);
static void init_simulink_io_address(SFc2_MON_FunctionalInstanceStruct
  *chartInstance);

/* Function Definitions */
static void initialize_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  if (sf_is_first_init_cond(chartInstance->S)) {
    initSimStructsc2_MON_Functional(chartInstance);
    chart_debug_initialize_data_addresses(chartInstance->S);
  }

  chartInstance->c2_sfEvent = CALL_EVENT;
  _sfTime_ = sf_get_time(chartInstance->S);
  chartInstance->c2_doSetSimStateSideEffects = 0U;
  chartInstance->c2_setSimStateSideEffectsInfo = NULL;
  chartInstance->c2_is_active_c2_MON_Functional = 0U;
  if (!(sf_get_output_port_reusable(chartInstance->S, 1) != 0)) {
    *chartInstance->c2_r = 0;
    _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_r, 4U, 1U, 1U,
                          chartInstance->c2_sfEvent, false);
  }
}

static void initialize_params_c2_MON_Functional
  (SFc2_MON_FunctionalInstanceStruct *chartInstance)
{
  real_T c2_d0;
  real_T c2_d1;
  real_T c2_d2;
  real_T c2_d3;
  sf_mex_import_named("MANUAL", sf_mex_get_sfun_param(chartInstance->S, 1, 0),
                      &c2_d0, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c2_MANUAL = (uint8_T)c2_d0;
  sf_mex_import_named("LO", sf_mex_get_sfun_param(chartInstance->S, 0, 0),
                      &c2_d1, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c2_LO = (uint8_T)c2_d1;
  sf_mex_import_named("MED", sf_mex_get_sfun_param(chartInstance->S, 3, 0),
                      &c2_d2, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c2_MED = (uint8_T)c2_d2;
  sf_mex_import_named("MAX", sf_mex_get_sfun_param(chartInstance->S, 2, 0),
                      &c2_d3, 0, 0, 0U, 0, 0U, 0);
  chartInstance->c2_MAX = (uint8_T)c2_d3;
}

static void enable_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  _sfTime_ = sf_get_time(chartInstance->S);
}

static void disable_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  _sfTime_ = sf_get_time(chartInstance->S);
}

static void c2_update_debugger_state_c2_MON_Functional
  (SFc2_MON_FunctionalInstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static const mxArray *get_sim_state_c2_MON_Functional
  (SFc2_MON_FunctionalInstanceStruct *chartInstance)
{
  const mxArray *c2_st;
  const mxArray *c2_y = NULL;
  int32_T c2_hoistedGlobal;
  int32_T c2_u;
  const mxArray *c2_b_y = NULL;
  uint8_T c2_b_hoistedGlobal;
  uint8_T c2_b_u;
  const mxArray *c2_c_y = NULL;
  c2_st = NULL;
  c2_st = NULL;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_createcellmatrix(2, 1), false);
  c2_hoistedGlobal = *chartInstance->c2_r;
  c2_u = c2_hoistedGlobal;
  c2_b_y = NULL;
  sf_mex_assign(&c2_b_y, sf_mex_create("y", &c2_u, 6, 0U, 0U, 0U, 0), false);
  sf_mex_setcell(c2_y, 0, c2_b_y);
  c2_b_hoistedGlobal = chartInstance->c2_is_active_c2_MON_Functional;
  c2_b_u = c2_b_hoistedGlobal;
  c2_c_y = NULL;
  sf_mex_assign(&c2_c_y, sf_mex_create("y", &c2_b_u, 3, 0U, 0U, 0U, 0), false);
  sf_mex_setcell(c2_y, 1, c2_c_y);
  sf_mex_assign(&c2_st, c2_y, false);
  return c2_st;
}

static void set_sim_state_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_st)
{
  const mxArray *c2_u;
  chartInstance->c2_doneDoubleBufferReInit = true;
  c2_u = sf_mex_dup(c2_st);
  *chartInstance->c2_r = c2_emlrt_marshallIn(chartInstance, sf_mex_dup
    (sf_mex_getcell("r", c2_u, 0)), "r");
  chartInstance->c2_is_active_c2_MON_Functional = c2_c_emlrt_marshallIn
    (chartInstance, sf_mex_dup(sf_mex_getcell("is_active_c2_MON_Functional",
       c2_u, 1)), "is_active_c2_MON_Functional");
  sf_mex_assign(&chartInstance->c2_setSimStateSideEffectsInfo,
                c2_e_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "setSimStateSideEffectsInfo", c2_u, 2)), "setSimStateSideEffectsInfo"), true);
  sf_mex_destroy(&c2_u);
  chartInstance->c2_doSetSimStateSideEffects = 1U;
  c2_update_debugger_state_c2_MON_Functional(chartInstance);
  sf_mex_destroy(&c2_st);
}

static void c2_set_sim_state_side_effects_c2_MON_Functional
  (SFc2_MON_FunctionalInstanceStruct *chartInstance)
{
  if (chartInstance->c2_doSetSimStateSideEffects != 0) {
    chartInstance->c2_doSetSimStateSideEffects = 0U;
  }
}

static void finalize_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  sf_mex_destroy(&chartInstance->c2_setSimStateSideEffectsInfo);
}

static void sf_gateway_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  boolean_T c2_temp;
  boolean_T c2_b_temp;
  boolean_T c2_c_temp;
  boolean_T c2_d_temp;
  boolean_T c2_e_temp;
  boolean_T c2_out;
  boolean_T c2_f_temp;
  boolean_T c2_b_out;
  boolean_T c2_g_temp;
  boolean_T c2_c_out;
  boolean_T c2_h_temp;
  boolean_T c2_d_out;
  boolean_T guard1 = false;
  boolean_T guard2 = false;
  boolean_T guard3 = false;
  c2_set_sim_state_side_effects_c2_MON_Functional(chartInstance);
  _SFD_SYMBOL_SCOPE_PUSH(0U, 0U);
  _sfTime_ = sf_get_time(chartInstance->S);
  _SFD_CC_CALL(CHART_ENTER_SFUNCTION_TAG, 1U, chartInstance->c2_sfEvent);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_rise_max, 3U, 1U, 0U,
                        chartInstance->c2_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_rise_med, 2U, 1U, 0U,
                        chartInstance->c2_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_rise_lo, 1U, 1U, 0U,
                        chartInstance->c2_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_pre_mode, 0U, 1U, 0U,
                        chartInstance->c2_sfEvent, false);
  chartInstance->c2_sfEvent = CALL_EVENT;
  _SFD_CC_CALL(CHART_ENTER_DURING_FUNCTION_TAG, 1U, chartInstance->c2_sfEvent);
  _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 0U, chartInstance->c2_sfEvent);
  _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 1U, chartInstance->c2_sfEvent);
  c2_temp = (_SFD_CCP_CALL(1U, 0, CV_RELATIONAL_EVAL(5U, 1U, 0, (real_T)
    *chartInstance->c2_pre_mode, (real_T)chartInstance->c2_LO, 0, 0U,
    *chartInstance->c2_pre_mode == chartInstance->c2_LO) != 0U,
              chartInstance->c2_sfEvent) != 0);
  if (c2_temp) {
    c2_temp = (_SFD_CCP_CALL(1U, 1, *chartInstance->c2_rise_lo != 0U,
                chartInstance->c2_sfEvent) != 0);
  }

  c2_b_temp = c2_temp;
  if (!c2_b_temp) {
    c2_c_temp = (_SFD_CCP_CALL(1U, 2, CV_RELATIONAL_EVAL(5U, 1U, 1, (real_T)
      *chartInstance->c2_pre_mode, (real_T)chartInstance->c2_MED, 0, 0U,
      *chartInstance->c2_pre_mode == chartInstance->c2_MED) != 0U,
      chartInstance->c2_sfEvent) != 0);
    if (c2_c_temp) {
      c2_c_temp = (_SFD_CCP_CALL(1U, 3, *chartInstance->c2_rise_med != 0U,
        chartInstance->c2_sfEvent) != 0);
    }

    c2_b_temp = c2_c_temp;
  }

  c2_d_temp = c2_b_temp;
  if (!c2_d_temp) {
    c2_e_temp = (_SFD_CCP_CALL(1U, 4, CV_RELATIONAL_EVAL(5U, 1U, 2, (real_T)
      *chartInstance->c2_pre_mode, (real_T)chartInstance->c2_MAX, 0, 0U,
      *chartInstance->c2_pre_mode == chartInstance->c2_MAX) != 0U,
      chartInstance->c2_sfEvent) != 0);
    if (c2_e_temp) {
      c2_e_temp = (_SFD_CCP_CALL(1U, 5, *chartInstance->c2_rise_max != 0U,
        chartInstance->c2_sfEvent) != 0);
    }

    c2_d_temp = c2_e_temp;
  }

  c2_out = (CV_TRANSITION_EVAL(1U, (int32_T)c2_d_temp) != 0);
  guard1 = false;
  guard2 = false;
  guard3 = false;
  if (c2_out) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 1U, chartInstance->c2_sfEvent);
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 3U, chartInstance->c2_sfEvent);
    *chartInstance->c2_r = chartInstance->c2_MANUAL;
    _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_r, 4U, 5U, 3U,
                          chartInstance->c2_sfEvent, false);
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 12U, chartInstance->c2_sfEvent);
    guard3 = true;
  } else {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 2U, chartInstance->c2_sfEvent);
    _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 4U, chartInstance->c2_sfEvent);
    c2_f_temp = (_SFD_CCP_CALL(4U, 0, CV_RELATIONAL_EVAL(5U, 4U, 0, (real_T)
      *chartInstance->c2_pre_mode, (real_T)chartInstance->c2_LO, 0, 1U,
      *chartInstance->c2_pre_mode != chartInstance->c2_LO) != 0U,
      chartInstance->c2_sfEvent) != 0);
    if (c2_f_temp) {
      c2_f_temp = (_SFD_CCP_CALL(4U, 1, *chartInstance->c2_rise_lo != 0U,
        chartInstance->c2_sfEvent) != 0);
    }

    c2_b_out = (CV_TRANSITION_EVAL(4U, (int32_T)c2_f_temp) != 0);
    if (c2_b_out) {
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 4U, chartInstance->c2_sfEvent);
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 6U, chartInstance->c2_sfEvent);
      *chartInstance->c2_r = chartInstance->c2_LO;
      _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_r, 4U, 5U, 6U,
                            chartInstance->c2_sfEvent, false);
      guard3 = true;
    } else {
      _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 5U, chartInstance->c2_sfEvent);
      _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 7U,
                   chartInstance->c2_sfEvent);
      c2_g_temp = (_SFD_CCP_CALL(7U, 0, CV_RELATIONAL_EVAL(5U, 7U, 0, (real_T)
        *chartInstance->c2_pre_mode, (real_T)chartInstance->c2_MED, 0, 1U,
        *chartInstance->c2_pre_mode != chartInstance->c2_MED) != 0U,
        chartInstance->c2_sfEvent) != 0);
      if (c2_g_temp) {
        c2_g_temp = (_SFD_CCP_CALL(7U, 1, *chartInstance->c2_rise_med != 0U,
          chartInstance->c2_sfEvent) != 0);
      }

      c2_c_out = (CV_TRANSITION_EVAL(7U, (int32_T)c2_g_temp) != 0);
      if (c2_c_out) {
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 7U, chartInstance->c2_sfEvent);
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 9U, chartInstance->c2_sfEvent);
        *chartInstance->c2_r = chartInstance->c2_MED;
        _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_r, 4U, 5U, 9U,
                              chartInstance->c2_sfEvent, false);
        guard2 = true;
      } else {
        _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 14U, chartInstance->c2_sfEvent);
        _SFD_CT_CALL(TRANSITION_BEFORE_PROCESSING_TAG, 15U,
                     chartInstance->c2_sfEvent);
        c2_h_temp = (_SFD_CCP_CALL(15U, 0, CV_RELATIONAL_EVAL(5U, 15U, 0,
          (real_T)*chartInstance->c2_pre_mode, (real_T)chartInstance->c2_MAX, 0,
          1U, *chartInstance->c2_pre_mode != chartInstance->c2_MAX) != 0U,
          chartInstance->c2_sfEvent) != 0);
        if (c2_h_temp) {
          c2_h_temp = (_SFD_CCP_CALL(15U, 1, *chartInstance->c2_rise_max != 0U,
            chartInstance->c2_sfEvent) != 0);
        }

        c2_d_out = (CV_TRANSITION_EVAL(15U, (int32_T)c2_h_temp) != 0);
        if (c2_d_out) {
          _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 15U, chartInstance->c2_sfEvent);
          _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 17U, chartInstance->c2_sfEvent);
          *chartInstance->c2_r = chartInstance->c2_MAX;
          _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_r, 4U, 5U, 17U,
                                chartInstance->c2_sfEvent, false);
          guard1 = true;
        } else {
          _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 8U, chartInstance->c2_sfEvent);
          *chartInstance->c2_r = *chartInstance->c2_pre_mode;
          _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_r, 4U, 5U, 8U,
                                chartInstance->c2_sfEvent, false);
        }
      }
    }
  }

  if (guard3 == true) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 11U, chartInstance->c2_sfEvent);
    guard2 = true;
  }

  if (guard2 == true) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 16U, chartInstance->c2_sfEvent);
    guard1 = true;
  }

  if (guard1 == true) {
    _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 10U, chartInstance->c2_sfEvent);
  }

  _SFD_CT_CALL(TRANSITION_ACTIVE_TAG, 13U, chartInstance->c2_sfEvent);
  _SFD_CC_CALL(EXIT_OUT_OF_FUNCTION_TAG, 1U, chartInstance->c2_sfEvent);
  _SFD_SYMBOL_SCOPE_POP();
  _SFD_CHECK_FOR_STATE_INCONSISTENCY(_MON_FunctionalMachineNumber_,
    chartInstance->chartNumber, chartInstance->instanceNumber);
}

static void mdl_start_c2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  c2_init_sf_message_store_memory(chartInstance);
}

static void initSimStructsc2_MON_Functional(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  (void)chartInstance;
}

static void init_script_number_translation(uint32_T c2_machineNumber, uint32_T
  c2_chartNumber, uint32_T c2_instanceNumber)
{
  (void)c2_machineNumber;
  (void)c2_chartNumber;
  (void)c2_instanceNumber;
}

const mxArray *sf_c2_MON_Functional_get_eml_resolved_functions_info(void)
{
  const mxArray *c2_nameCaptureInfo = NULL;
  c2_nameCaptureInfo = NULL;
  sf_mex_assign(&c2_nameCaptureInfo, sf_mex_create("nameCaptureInfo", NULL, 0,
    0U, 1U, 0U, 2, 0, 1), false);
  return c2_nameCaptureInfo;
}

static const mxArray *c2_sf_marshallOut(void *chartInstanceVoid, void *c2_inData)
{
  const mxArray *c2_mxArrayOutData = NULL;
  int32_T c2_u;
  const mxArray *c2_y = NULL;
  SFc2_MON_FunctionalInstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_FunctionalInstanceStruct *)chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_u = *(int32_T *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_create("y", &c2_u, 6, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static int32_T c2_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_b_sfEvent, const char_T *c2_identifier)
{
  int32_T c2_y;
  emlrtMsgIdentifier c2_thisId;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_b_emlrt_marshallIn(chartInstance, sf_mex_dup(c2_b_sfEvent),
    &c2_thisId);
  sf_mex_destroy(&c2_b_sfEvent);
  return c2_y;
}

static int32_T c2_b_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_u, const emlrtMsgIdentifier *c2_parentId)
{
  int32_T c2_y;
  int32_T c2_i0;
  (void)chartInstance;
  sf_mex_import(c2_parentId, sf_mex_dup(c2_u), &c2_i0, 1, 6, 0U, 0, 0U, 0);
  c2_y = c2_i0;
  sf_mex_destroy(&c2_u);
  return c2_y;
}

static void c2_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData)
{
  const mxArray *c2_b_sfEvent;
  const char_T *c2_identifier;
  emlrtMsgIdentifier c2_thisId;
  int32_T c2_y;
  SFc2_MON_FunctionalInstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_FunctionalInstanceStruct *)chartInstanceVoid;
  c2_b_sfEvent = sf_mex_dup(c2_mxArrayInData);
  c2_identifier = c2_varName;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_b_emlrt_marshallIn(chartInstance, sf_mex_dup(c2_b_sfEvent),
    &c2_thisId);
  sf_mex_destroy(&c2_b_sfEvent);
  *(int32_T *)c2_outData = c2_y;
  sf_mex_destroy(&c2_mxArrayInData);
}

static const mxArray *c2_b_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData = NULL;
  boolean_T c2_u;
  const mxArray *c2_y = NULL;
  SFc2_MON_FunctionalInstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_FunctionalInstanceStruct *)chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_u = *(boolean_T *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_create("y", &c2_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static const mxArray *c2_c_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData = NULL;
  uint8_T c2_u;
  const mxArray *c2_y = NULL;
  SFc2_MON_FunctionalInstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_FunctionalInstanceStruct *)chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_u = *(uint8_T *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_create("y", &c2_u, 3, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static uint8_T c2_c_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_b_MANUAL, const char_T *c2_identifier)
{
  uint8_T c2_y;
  emlrtMsgIdentifier c2_thisId;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_d_emlrt_marshallIn(chartInstance, sf_mex_dup(c2_b_MANUAL),
    &c2_thisId);
  sf_mex_destroy(&c2_b_MANUAL);
  return c2_y;
}

static uint8_T c2_d_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_u, const emlrtMsgIdentifier *c2_parentId)
{
  uint8_T c2_y;
  uint8_T c2_u0;
  (void)chartInstance;
  sf_mex_import(c2_parentId, sf_mex_dup(c2_u), &c2_u0, 1, 3, 0U, 0, 0U, 0);
  c2_y = c2_u0;
  sf_mex_destroy(&c2_u);
  return c2_y;
}

static void c2_b_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData)
{
  const mxArray *c2_b_MANUAL;
  const char_T *c2_identifier;
  emlrtMsgIdentifier c2_thisId;
  uint8_T c2_y;
  SFc2_MON_FunctionalInstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_FunctionalInstanceStruct *)chartInstanceVoid;
  c2_b_MANUAL = sf_mex_dup(c2_mxArrayInData);
  c2_identifier = c2_varName;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_d_emlrt_marshallIn(chartInstance, sf_mex_dup(c2_b_MANUAL),
    &c2_thisId);
  sf_mex_destroy(&c2_b_MANUAL);
  *(uint8_T *)c2_outData = c2_y;
  sf_mex_destroy(&c2_mxArrayInData);
}

static const mxArray *c2_e_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_b_setSimStateSideEffectsInfo, const char_T
  *c2_identifier)
{
  const mxArray *c2_y = NULL;
  emlrtMsgIdentifier c2_thisId;
  c2_y = NULL;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  sf_mex_assign(&c2_y, c2_f_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c2_b_setSimStateSideEffectsInfo), &c2_thisId), false);
  sf_mex_destroy(&c2_b_setSimStateSideEffectsInfo);
  return c2_y;
}

static const mxArray *c2_f_emlrt_marshallIn(SFc2_MON_FunctionalInstanceStruct
  *chartInstance, const mxArray *c2_u, const emlrtMsgIdentifier *c2_parentId)
{
  const mxArray *c2_y = NULL;
  (void)chartInstance;
  (void)c2_parentId;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_duplicatearraysafe(&c2_u), false);
  sf_mex_destroy(&c2_u);
  return c2_y;
}

static const mxArray *sf_get_hover_data_for_msg
  (SFc2_MON_FunctionalInstanceStruct *chartInstance, int32_T c2_ssid)
{
  (void)chartInstance;
  (void)c2_ssid;
  return NULL;
}

static void c2_init_sf_message_store_memory(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  (void)chartInstance;
}

static void init_dsm_address_info(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  (void)chartInstance;
}

static void init_simulink_io_address(SFc2_MON_FunctionalInstanceStruct
  *chartInstance)
{
  chartInstance->c2_pre_mode = (int32_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 0);
  chartInstance->c2_rise_lo = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 1);
  chartInstance->c2_rise_med = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 2);
  chartInstance->c2_rise_max = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 3);
  chartInstance->c2_r = (int32_T *)ssGetOutputPortSignal_wrapper
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

void sf_c2_MON_Functional_get_check_sum(mxArray *plhs[])
{
  ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(1299959090U);
  ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(315894534U);
  ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(2795128694U);
  ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(2486545317U);
}

mxArray* sf_c2_MON_Functional_get_post_codegen_info(void);
mxArray *sf_c2_MON_Functional_get_autoinheritance_info(void)
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
    mxArray* mxPostCodegenInfo = sf_c2_MON_Functional_get_post_codegen_info();
    mxSetField(mxAutoinheritanceInfo,0,"postCodegenInfo",mxPostCodegenInfo);
  }

  return(mxAutoinheritanceInfo);
}

mxArray *sf_c2_MON_Functional_third_party_uses_info(void)
{
  mxArray * mxcell3p = mxCreateCellMatrix(1,0);
  return(mxcell3p);
}

mxArray *sf_c2_MON_Functional_jit_fallback_info(void)
{
  const char *infoFields[] = { "fallbackType", "fallbackReason",
    "hiddenFallbackType", "hiddenFallbackReason", "incompatibleSymbol" };

  mxArray *mxInfo = mxCreateStructMatrix(1, 1, 5, infoFields);
  mxArray *fallbackType = mxCreateString("pre");
  mxArray *fallbackReason = mxCreateString("sldvModelRefHarness");
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

mxArray *sf_c2_MON_Functional_updateBuildInfo_args_info(void)
{
  mxArray *mxBIArgs = mxCreateCellMatrix(1,0);
  return mxBIArgs;
}

mxArray* sf_c2_MON_Functional_get_post_codegen_info(void)
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

static const mxArray *sf_get_sim_state_info_c2_MON_Functional(void)
{
  const char *infoFields[] = { "chartChecksum", "varInfo" };

  mxArray *mxInfo = mxCreateStructMatrix(1, 1, 2, infoFields);
  const char *infoEncStr[] = {
    "100 S1x2'type','srcId','name','auxInfo'{{M[1],M[1780],T\"r\",},{M[8],M[0],T\"is_active_c2_MON_Functional\",}}"
  };

  mxArray *mxVarInfo = sf_mex_decode_encoded_mx_struct_array(infoEncStr, 2, 10);
  mxArray *mxChecksum = mxCreateDoubleMatrix(1, 4, mxREAL);
  sf_c2_MON_Functional_get_check_sum(&mxChecksum);
  mxSetField(mxInfo, 0, infoFields[0], mxChecksum);
  mxSetField(mxInfo, 0, infoFields[1], mxVarInfo);
  return mxInfo;
}

static const mxArray* sf_opaque_get_hover_data_for_msg(void* chartInstance,
  int32_T msgSSID)
{
  return sf_get_hover_data_for_msg( (SFc2_MON_FunctionalInstanceStruct *)
    chartInstance, msgSSID);
}

static void chart_debug_initialization(SimStruct *S, unsigned int
  fullDebuggerInitialization)
{
  if (!sim_mode_is_rtw_gen(S)) {
    SFc2_MON_FunctionalInstanceStruct *chartInstance =
      (SFc2_MON_FunctionalInstanceStruct *)sf_get_chart_instance_ptr(S);
    if (ssIsFirstInitCond(S) && fullDebuggerInitialization==1) {
      /* do this only if simulation is starting */
      {
        unsigned int chartAlreadyPresent;
        chartAlreadyPresent = sf_debug_initialize_chart
          (sfGlobalDebugInstanceStruct,
           _MON_FunctionalMachineNumber_,
           2,
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
        init_script_number_translation(_MON_FunctionalMachineNumber_,
          chartInstance->chartNumber,chartInstance->instanceNumber);
        if (chartAlreadyPresent==0) {
          /* this is the first instance */
          sf_debug_set_chart_disable_implicit_casting
            (sfGlobalDebugInstanceStruct,_MON_FunctionalMachineNumber_,
             chartInstance->chartNumber,1);
          sf_debug_set_chart_event_thresholds(sfGlobalDebugInstanceStruct,
            _MON_FunctionalMachineNumber_,
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

          static int sRelationalopType[] = { 0, 0, 0 };

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

          static int sRelationalopType[] = { 1 };

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

          static int sRelationalopType[] = { 1 };

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

          static int sRelationalopType[] = { 1 };

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
          (MexFcnForType)c2_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(1,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(2,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(3,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_b_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(4,SF_INT32,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_sf_marshallOut,(MexInFcnForType)c2_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(5,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_c_sf_marshallOut,(MexInFcnForType)c2_b_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(6,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_c_sf_marshallOut,(MexInFcnForType)c2_b_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(7,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_c_sf_marshallOut,(MexInFcnForType)c2_b_sf_marshallIn);
        _SFD_SET_DATA_COMPILED_PROPS(8,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_c_sf_marshallOut,(MexInFcnForType)c2_b_sf_marshallIn);
      }
    } else {
      sf_debug_reset_current_state_configuration(sfGlobalDebugInstanceStruct,
        _MON_FunctionalMachineNumber_,chartInstance->chartNumber,
        chartInstance->instanceNumber);
    }
  }
}

static void chart_debug_initialize_data_addresses(SimStruct *S)
{
  if (!sim_mode_is_rtw_gen(S)) {
    SFc2_MON_FunctionalInstanceStruct *chartInstance =
      (SFc2_MON_FunctionalInstanceStruct *)sf_get_chart_instance_ptr(S);
    if (ssIsFirstInitCond(S)) {
      /* do this only if simulation is starting and after we know the addresses of all data */
      {
        _SFD_SET_DATA_VALUE_PTR(0U, chartInstance->c2_pre_mode);
        _SFD_SET_DATA_VALUE_PTR(1U, chartInstance->c2_rise_lo);
        _SFD_SET_DATA_VALUE_PTR(2U, chartInstance->c2_rise_med);
        _SFD_SET_DATA_VALUE_PTR(3U, chartInstance->c2_rise_max);
        _SFD_SET_DATA_VALUE_PTR(4U, chartInstance->c2_r);
        _SFD_SET_DATA_VALUE_PTR(6U, &chartInstance->c2_MANUAL);
        _SFD_SET_DATA_VALUE_PTR(5U, &chartInstance->c2_LO);
        _SFD_SET_DATA_VALUE_PTR(8U, &chartInstance->c2_MED);
        _SFD_SET_DATA_VALUE_PTR(7U, &chartInstance->c2_MAX);
      }
    }
  }
}

static const char* sf_get_instance_specialization(void)
{
  return "sNEIykul4qEUgVrxwTgXqCF";
}

static void sf_opaque_initialize_c2_MON_Functional(void *chartInstanceVar)
{
  chart_debug_initialization(((SFc2_MON_FunctionalInstanceStruct*)
    chartInstanceVar)->S,0);
  initialize_params_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*)
    chartInstanceVar);
  initialize_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*)
    chartInstanceVar);
}

static void sf_opaque_enable_c2_MON_Functional(void *chartInstanceVar)
{
  enable_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*) chartInstanceVar);
}

static void sf_opaque_disable_c2_MON_Functional(void *chartInstanceVar)
{
  disable_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*)
    chartInstanceVar);
}

static void sf_opaque_gateway_c2_MON_Functional(void *chartInstanceVar)
{
  sf_gateway_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*)
    chartInstanceVar);
}

static const mxArray* sf_opaque_get_sim_state_c2_MON_Functional(SimStruct* S)
{
  return get_sim_state_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct *)
    sf_get_chart_instance_ptr(S));     /* raw sim ctx */
}

static void sf_opaque_set_sim_state_c2_MON_Functional(SimStruct* S, const
  mxArray *st)
{
  set_sim_state_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*)
    sf_get_chart_instance_ptr(S), st);
}

static void sf_opaque_terminate_c2_MON_Functional(void *chartInstanceVar)
{
  if (chartInstanceVar!=NULL) {
    SimStruct *S = ((SFc2_MON_FunctionalInstanceStruct*) chartInstanceVar)->S;
    if (sim_mode_is_rtw_gen(S) || sim_mode_is_external(S)) {
      sf_clear_rtw_identifier(S);
      unload_MON_Functional_optimization_info();
    }

    finalize_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*)
      chartInstanceVar);
    utFree(chartInstanceVar);
    if (ssGetUserData(S)!= NULL) {
      sf_free_ChartRunTimeInfo(S);
    }

    ssSetUserData(S,NULL);
  }
}

static void sf_opaque_init_subchart_simstructs(void *chartInstanceVar)
{
  initSimStructsc2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*)
    chartInstanceVar);
}

extern unsigned int sf_machine_global_initializer_called(void);
static void mdlProcessParameters_c2_MON_Functional(SimStruct *S)
{
  int i;
  for (i=0;i<ssGetNumRunTimeParams(S);i++) {
    if (ssGetSFcnParamTunable(S,i)) {
      ssUpdateDlgParamAsRunTimeParam(S,i);
    }
  }

  if (sf_machine_global_initializer_called()) {
    initialize_params_c2_MON_Functional((SFc2_MON_FunctionalInstanceStruct*)
      sf_get_chart_instance_ptr(S));
  }
}

static void mdlSetWorkWidths_c2_MON_Functional(SimStruct *S)
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
    mxArray *infoStruct = load_MON_Functional_optimization_info
      (sim_mode_is_rtw_gen(S), sim_mode_is_modelref_sim(S), sim_mode_is_external
       (S));
    int_T chartIsInlinable =
      (int_T)sf_is_chart_inlinable(sf_get_instance_specialization(),infoStruct,2);
    ssSetStateflowIsInlinable(S,chartIsInlinable);
    ssSetRTWCG(S,1);
    ssSetEnableFcnIsTrivial(S,1);
    ssSetDisableFcnIsTrivial(S,1);
    ssSetNotMultipleInlinable(S,sf_rtw_info_uint_prop
      (sf_get_instance_specialization(),infoStruct,2,
       "gatewayCannotBeInlinedMultipleTimes"));
    sf_set_chart_accesses_machine_info(S, sf_get_instance_specialization(),
      infoStruct, 2);
    sf_update_buildInfo(S, sf_get_instance_specialization(),infoStruct,2);
    if (chartIsInlinable) {
      ssSetInputPortOptimOpts(S, 0, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 1, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 2, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 3, SS_REUSABLE_AND_LOCAL);
      sf_mark_chart_expressionable_inputs(S,sf_get_instance_specialization(),
        infoStruct,2,4);
      sf_mark_chart_reusable_outputs(S,sf_get_instance_specialization(),
        infoStruct,2,1);
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

    sf_set_rtw_dwork_info(S,sf_get_instance_specialization(),infoStruct,2);
    ssSetHasSubFunctions(S,!(chartIsInlinable));
  } else {
  }

  ssSetOptions(S,ssGetOptions(S)|SS_OPTION_WORKS_WITH_CODE_REUSE);
  ssSetChecksum0(S,(2111192163U));
  ssSetChecksum1(S,(2626082510U));
  ssSetChecksum2(S,(4111504384U));
  ssSetChecksum3(S,(4272336199U));
  ssSetmdlDerivatives(S, NULL);
  ssSetExplicitFCSSCtrl(S,1);
  ssSupportsMultipleExecInstances(S,1);
}

static void mdlRTW_c2_MON_Functional(SimStruct *S)
{
  if (sim_mode_is_rtw_gen(S)) {
    ssWriteRTWStrParam(S, "StateflowChartType", "Stateflow");
  }
}

static void mdlStart_c2_MON_Functional(SimStruct *S)
{
  SFc2_MON_FunctionalInstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_FunctionalInstanceStruct *)utMalloc(sizeof
    (SFc2_MON_FunctionalInstanceStruct));
  if (chartInstance==NULL) {
    sf_mex_error_message("Could not allocate memory for chart instance.");
  }

  memset(chartInstance, 0, sizeof(SFc2_MON_FunctionalInstanceStruct));
  chartInstance->chartInfo.chartInstance = chartInstance;
  chartInstance->chartInfo.isEMLChart = 0;
  chartInstance->chartInfo.chartInitialized = 0;
  chartInstance->chartInfo.sFunctionGateway =
    sf_opaque_gateway_c2_MON_Functional;
  chartInstance->chartInfo.initializeChart =
    sf_opaque_initialize_c2_MON_Functional;
  chartInstance->chartInfo.terminateChart =
    sf_opaque_terminate_c2_MON_Functional;
  chartInstance->chartInfo.enableChart = sf_opaque_enable_c2_MON_Functional;
  chartInstance->chartInfo.disableChart = sf_opaque_disable_c2_MON_Functional;
  chartInstance->chartInfo.getSimState =
    sf_opaque_get_sim_state_c2_MON_Functional;
  chartInstance->chartInfo.setSimState =
    sf_opaque_set_sim_state_c2_MON_Functional;
  chartInstance->chartInfo.getSimStateInfo =
    sf_get_sim_state_info_c2_MON_Functional;
  chartInstance->chartInfo.zeroCrossings = NULL;
  chartInstance->chartInfo.outputs = NULL;
  chartInstance->chartInfo.derivatives = NULL;
  chartInstance->chartInfo.mdlRTW = mdlRTW_c2_MON_Functional;
  chartInstance->chartInfo.mdlStart = mdlStart_c2_MON_Functional;
  chartInstance->chartInfo.mdlSetWorkWidths = mdlSetWorkWidths_c2_MON_Functional;
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
  mdl_start_c2_MON_Functional(chartInstance);
}

void c2_MON_Functional_method_dispatcher(SimStruct *S, int_T method, void *data)
{
  switch (method) {
   case SS_CALL_MDL_START:
    mdlStart_c2_MON_Functional(S);
    break;

   case SS_CALL_MDL_SET_WORK_WIDTHS:
    mdlSetWorkWidths_c2_MON_Functional(S);
    break;

   case SS_CALL_MDL_PROCESS_PARAMETERS:
    mdlProcessParameters_c2_MON_Functional(S);
    break;

   default:
    /* Unhandled method */
    sf_mex_error_message("Stateflow Internal Error:\n"
                         "Error calling c2_MON_Functional_method_dispatcher.\n"
                         "Can't handle method %d.\n", method);
    break;
  }
}
