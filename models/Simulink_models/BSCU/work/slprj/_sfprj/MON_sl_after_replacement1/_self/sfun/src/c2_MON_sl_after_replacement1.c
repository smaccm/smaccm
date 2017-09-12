/* Include files */

#include "MON_sl_after_replacement1_sfun.h"
#include "c2_MON_sl_after_replacement1.h"
#define CHARTINSTANCE_CHARTNUMBER      (chartInstance->chartNumber)
#define CHARTINSTANCE_INSTANCENUMBER   (chartInstance->instanceNumber)
#include "MON_sl_after_replacement1_sfun_debug_macros.h"
#define _SF_MEX_LISTEN_FOR_CTRL_C(S)   sf_mex_listen_for_ctrl_c_with_debugger(S, sfGlobalDebugInstanceStruct);

static void chart_debug_initialization(SimStruct *S, unsigned int
  fullDebuggerInitialization);
static void chart_debug_initialize_data_addresses(SimStruct *S);
static const mxArray* sf_opaque_get_hover_data_for_msg(void *chartInstance,
  int32_T msgSSID);

/* Type Definitions */

/* Named Constants */
#define CALL_EVENT                     (-1)

/* Variable Declarations */

/* Variable Definitions */
static real_T _sfTime_;
static const char * c2_debug_family_names[47] = { "Primary_Side",
  "max_Agree_Nodes__Brake_Mode_0",
  "p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0",
  "r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0",
  "lo_Agree_Nodes__Brake_Mode_0",
  "p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0",
  "r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0",
  "med_Agree_Nodes__Brake_Mode_0",
  "p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0",
  "r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0",
  "r_Agree_Nodes__Brake_Mode_0", "Mode", "p_Agree_Nodes__Duration_0",
  "b_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0",
  "i_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0", "c_Agree_Nodes__Duration_0",
  "p_Agree_Nodes__Latch_0", "r_Agree_Nodes__Latch_0", "Error", "Active",
  "pedal_Agree_Nodes__Brake_Cmd_0", "mode_Agree_Nodes__Brake_Cmd_0",
  "r_Agree_Nodes__Brake_Cmd_0", "GUARANTEE0", "GUARANTEE1", "GUARANTEE2",
  "GUARANTEE3", "GUARANTEE4", "mode_Agree_Nodes__Brake_Mode_0",
  "nodeLemma0_Agree_Nodes__Duration_0", "nargin", "nargout", "Panel",
  "CMD_From_COM", "Sync_From", "Pedal", "Fail", "Sync_To", "Status",
  "pre_c_Agree_Nodes__Duration_0",
  "pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0",
  "pre_mode_Agree_Nodes__Brake_Mode_0", "pre_Active", "first_time",
  "pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0",
  "pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0",
  "pre_r_Agree_Nodes__Latch_0" };

static const char * c2_b_debug_family_names[6] = { "nargin", "nargout",
  "first_time", "left", "right", "output" };

static const char * c2_c_debug_family_names[6] = { "nargin", "nargout", "cond",
  "ifBranch", "elseBranch", "output" };

static const char * c2_d_debug_family_names[6] = { "nargin", "nargout",
  "first_time", "left", "right", "output" };

static const char * c2_e_debug_family_names[6] = { "nargin", "nargout", "cond",
  "ifBranch", "elseBranch", "output" };

static const char * c2_f_debug_family_names[5] = { "nargin", "nargout", "left",
  "right", "output" };

/* Function Declarations */
static void initialize_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void initialize_params_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void enable_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void disable_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void c2_update_debugger_state_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static const mxArray *get_sim_state_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void set_sim_state_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_st);
static void finalize_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void sf_gateway_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void mdl_start_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void c2_chartstep_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void initSimStructsc2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);
static void init_script_number_translation(uint32_T c2_machineNumber, uint32_T
  c2_chartNumber, uint32_T c2_instanceNumber);
static const mxArray *c2_sf_marshallOut(void *chartInstanceVoid, void *c2_inData);
static boolean_T c2_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_b_pre_r_Agree_Nodes__Latch_0, const char_T *c2_identifier, boolean_T
   *c2_svPtr);
static boolean_T c2_b_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId, boolean_T *c2_svPtr);
static void c2_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData);
static const mxArray *c2_b_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static int32_T c2_c_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_b_pre_mode_Agree_Nodes__Brake_Mode_0, const char_T *c2_identifier,
   boolean_T *c2_svPtr);
static int32_T c2_d_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId, boolean_T *c2_svPtr);
static void c2_b_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData);
static const mxArray *c2_c_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static const mxArray *c2_d_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static const mxArray *c2_e_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static const mxArray *c2_f_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static const mxArray *c2_g_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static const mxArray *c2_h_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData);
static real_T c2_e_emlrt_marshallIn(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, const mxArray *c2_u, const emlrtMsgIdentifier *c2_parentId);
static void c2_c_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData);
static boolean_T c2_f_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId);
static void c2_d_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData);
static int32_T c2_g_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId);
static void c2_e_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData);
static boolean_T c2_arrowFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_b_first_time, boolean_T c2_left, boolean_T
  c2_right);
static boolean_T c2_isequal(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, int32_T c2_varargin_1, int32_T c2_varargin_2);
static int32_T c2_ifFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_cond, int32_T c2_ifBranch, int32_T c2_elseBranch);
static int32_T c2_b_arrowFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_b_first_time, int32_T c2_left, int32_T c2_right);
static boolean_T c2_b_ifFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_cond, boolean_T c2_ifBranch, boolean_T
  c2_elseBranch);
static boolean_T c2_impliesFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_left, boolean_T c2_right);
static boolean_T c2_b_isequal(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_varargin_1, boolean_T c2_varargin_2);
static const mxArray *c2_Panel_bus_io(void *chartInstanceVoid, void *c2_pData);
static const mxArray *c2_Sync_From_bus_io(void *chartInstanceVoid, void
  *c2_pData);
static const mxArray *c2_Status_bus_io(void *chartInstanceVoid, void *c2_pData);
static uint8_T c2_h_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_b_is_active_c2_MON_sl_after_replacement1, const char_T *c2_identifier);
static uint8_T c2_i_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId);
static void init_dsm_address_info(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance);
static void init_simulink_io_address
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance);

/* Function Definitions */
static void initialize_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  if (sf_is_first_init_cond(chartInstance->S)) {
    initSimStructsc2_MON_sl_after_replacement1(chartInstance);
    chart_debug_initialize_data_addresses(chartInstance->S);
  }

  chartInstance->c2_sfEvent = CALL_EVENT;
  _sfTime_ = sf_get_time(chartInstance->S);
  chartInstance->c2_pre_c_Agree_Nodes__Duration_0_not_empty = false;
  chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0_not_empty
    = false;
  chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty = false;
  chartInstance->c2_pre_Active_not_empty = false;
  chartInstance->c2_first_time_not_empty = false;
  chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0_not_empty
    = false;
  chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0_not_empty
    = false;
  chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty = false;
  chartInstance->c2_is_active_c2_MON_sl_after_replacement1 = 0U;
}

static void initialize_params_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static void enable_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  _sfTime_ = sf_get_time(chartInstance->S);
}

static void disable_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  _sfTime_ = sf_get_time(chartInstance->S);
}

static void c2_update_debugger_state_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static const mxArray *get_sim_state_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  const mxArray *c2_st;
  const mxArray *c2_y = NULL;
  boolean_T c2_hoistedGlobal;
  boolean_T c2_u;
  const mxArray *c2_b_y = NULL;
  boolean_T c2_b_hoistedGlobal;
  boolean_T c2_b_u;
  const mxArray *c2_c_y = NULL;
  int32_T c2_c_hoistedGlobal;
  int32_T c2_c_u;
  const mxArray *c2_d_y = NULL;
  int32_T c2_d_hoistedGlobal;
  int32_T c2_d_u;
  const mxArray *c2_e_y = NULL;
  boolean_T c2_e_hoistedGlobal;
  boolean_T c2_e_u;
  const mxArray *c2_f_y = NULL;
  boolean_T c2_f_hoistedGlobal;
  boolean_T c2_f_u;
  const mxArray *c2_g_y = NULL;
  boolean_T c2_g_hoistedGlobal;
  boolean_T c2_g_u;
  const mxArray *c2_h_y = NULL;
  boolean_T c2_h_hoistedGlobal;
  boolean_T c2_h_u;
  const mxArray *c2_i_y = NULL;
  uint8_T c2_i_hoistedGlobal;
  uint8_T c2_i_u;
  const mxArray *c2_j_y = NULL;
  c2_st = NULL;
  c2_st = NULL;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_createcellmatrix(9, 1), false);
  c2_hoistedGlobal = chartInstance->c2_first_time;
  c2_u = c2_hoistedGlobal;
  c2_b_y = NULL;
  if (!chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty) {
    sf_mex_assign(&c2_b_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0),
                  false);
  } else {
    sf_mex_assign(&c2_b_y, sf_mex_create("y", &c2_u, 11, 0U, 0U, 0U, 0), false);
  }

  sf_mex_setcell(c2_y, 0, c2_b_y);
  c2_b_hoistedGlobal = chartInstance->c2_pre_Active;
  c2_b_u = c2_b_hoistedGlobal;
  c2_c_y = NULL;
  if (!chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty) {
    sf_mex_assign(&c2_c_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0),
                  false);
  } else {
    sf_mex_assign(&c2_c_y, sf_mex_create("y", &c2_b_u, 11, 0U, 0U, 0U, 0), false);
  }

  sf_mex_setcell(c2_y, 1, c2_c_y);
  c2_c_hoistedGlobal = chartInstance->c2_pre_c_Agree_Nodes__Duration_0;
  c2_c_u = c2_c_hoistedGlobal;
  c2_d_y = NULL;
  if (!chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty) {
    sf_mex_assign(&c2_d_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0),
                  false);
  } else {
    sf_mex_assign(&c2_d_y, sf_mex_create("y", &c2_c_u, 6, 0U, 0U, 0U, 0), false);
  }

  sf_mex_setcell(c2_y, 2, c2_d_y);
  c2_d_hoistedGlobal = chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0;
  c2_d_u = c2_d_hoistedGlobal;
  c2_e_y = NULL;
  if (!chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty) {
    sf_mex_assign(&c2_e_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0),
                  false);
  } else {
    sf_mex_assign(&c2_e_y, sf_mex_create("y", &c2_d_u, 6, 0U, 0U, 0U, 0), false);
  }

  sf_mex_setcell(c2_y, 3, c2_e_y);
  c2_e_hoistedGlobal =
    chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0;
  c2_e_u = c2_e_hoistedGlobal;
  c2_f_y = NULL;
  if (!chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty) {
    sf_mex_assign(&c2_f_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0),
                  false);
  } else {
    sf_mex_assign(&c2_f_y, sf_mex_create("y", &c2_e_u, 11, 0U, 0U, 0U, 0), false);
  }

  sf_mex_setcell(c2_y, 4, c2_f_y);
  c2_f_hoistedGlobal =
    chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0;
  c2_f_u = c2_f_hoistedGlobal;
  c2_g_y = NULL;
  if (!chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty) {
    sf_mex_assign(&c2_g_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0),
                  false);
  } else {
    sf_mex_assign(&c2_g_y, sf_mex_create("y", &c2_f_u, 11, 0U, 0U, 0U, 0), false);
  }

  sf_mex_setcell(c2_y, 5, c2_g_y);
  c2_g_hoistedGlobal =
    chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0;
  c2_g_u = c2_g_hoistedGlobal;
  c2_h_y = NULL;
  if (!chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty) {
    sf_mex_assign(&c2_h_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0),
                  false);
  } else {
    sf_mex_assign(&c2_h_y, sf_mex_create("y", &c2_g_u, 11, 0U, 0U, 0U, 0), false);
  }

  sf_mex_setcell(c2_y, 6, c2_h_y);
  c2_h_hoistedGlobal = chartInstance->c2_pre_r_Agree_Nodes__Latch_0;
  c2_h_u = c2_h_hoistedGlobal;
  c2_i_y = NULL;
  if (!chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty) {
    sf_mex_assign(&c2_i_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0),
                  false);
  } else {
    sf_mex_assign(&c2_i_y, sf_mex_create("y", &c2_h_u, 11, 0U, 0U, 0U, 0), false);
  }

  sf_mex_setcell(c2_y, 7, c2_i_y);
  c2_i_hoistedGlobal = chartInstance->c2_is_active_c2_MON_sl_after_replacement1;
  c2_i_u = c2_i_hoistedGlobal;
  c2_j_y = NULL;
  sf_mex_assign(&c2_j_y, sf_mex_create("y", &c2_i_u, 3, 0U, 0U, 0U, 0), false);
  sf_mex_setcell(c2_y, 8, c2_j_y);
  sf_mex_assign(&c2_st, c2_y, false);
  return c2_st;
}

static void set_sim_state_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_st)
{
  const mxArray *c2_u;
  chartInstance->c2_doneDoubleBufferReInit = true;
  c2_u = sf_mex_dup(c2_st);
  chartInstance->c2_first_time = c2_emlrt_marshallIn(chartInstance, sf_mex_dup
    (sf_mex_getcell("first_time", c2_u, 0)), "first_time",
    &chartInstance->c2_first_time_not_empty);
  chartInstance->c2_pre_Active = c2_emlrt_marshallIn(chartInstance, sf_mex_dup
    (sf_mex_getcell("pre_Active", c2_u, 1)), "pre_Active",
    &chartInstance->c2_pre_Active_not_empty);
  chartInstance->c2_pre_c_Agree_Nodes__Duration_0 = c2_c_emlrt_marshallIn
    (chartInstance, sf_mex_dup(sf_mex_getcell("pre_c_Agree_Nodes__Duration_0",
       c2_u, 2)), "pre_c_Agree_Nodes__Duration_0",
     &chartInstance->c2_pre_c_Agree_Nodes__Duration_0_not_empty);
  chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0 = c2_c_emlrt_marshallIn
    (chartInstance, sf_mex_dup(sf_mex_getcell(
       "pre_mode_Agree_Nodes__Brake_Mode_0", c2_u, 3)),
     "pre_mode_Agree_Nodes__Brake_Mode_0",
     &chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty);
  chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 =
    c2_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0", c2_u, 4)),
                        "pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0",
                        &chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0_not_empty);
  chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 =
    c2_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0", c2_u, 5)),
                        "pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0",
                        &chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0_not_empty);
  chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 =
    c2_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0", c2_u, 6)),
                        "pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0",
                        &chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0_not_empty);
  chartInstance->c2_pre_r_Agree_Nodes__Latch_0 = c2_emlrt_marshallIn
    (chartInstance, sf_mex_dup(sf_mex_getcell("pre_r_Agree_Nodes__Latch_0", c2_u,
       7)), "pre_r_Agree_Nodes__Latch_0",
     &chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty);
  chartInstance->c2_is_active_c2_MON_sl_after_replacement1 =
    c2_h_emlrt_marshallIn(chartInstance, sf_mex_dup(sf_mex_getcell(
    "is_active_c2_MON_sl_after_replacement1", c2_u, 8)),
    "is_active_c2_MON_sl_after_replacement1");
  sf_mex_destroy(&c2_u);
  c2_update_debugger_state_c2_MON_sl_after_replacement1(chartInstance);
  sf_mex_destroy(&c2_st);
}

static void finalize_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static void sf_gateway_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  _SFD_SYMBOL_SCOPE_PUSH(0U, 0U);
  _sfTime_ = sf_get_time(chartInstance->S);
  _SFD_CC_CALL(CHART_ENTER_SFUNCTION_TAG, 1U, chartInstance->c2_sfEvent);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_Fail, 4U, 1U, 0U,
                        chartInstance->c2_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_Pedal, 3U, 1U, 0U,
                        chartInstance->c2_sfEvent, false);
  _SFD_DATA_RANGE_CHECK((real_T)*chartInstance->c2_CMD_From_COM, 1U, 1U, 0U,
                        chartInstance->c2_sfEvent, false);
  chartInstance->c2_sfEvent = CALL_EVENT;
  c2_chartstep_c2_MON_sl_after_replacement1(chartInstance);
  _SFD_SYMBOL_SCOPE_POP();
  _SFD_CHECK_FOR_STATE_INCONSISTENCY(_MON_sl_after_replacement1MachineNumber_,
    chartInstance->chartNumber, chartInstance->instanceNumber);
}

static void mdl_start_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  (void)chartInstance;
}

static void c2_chartstep_c2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  int32_T c2_hoistedGlobal;
  int32_T c2_b_hoistedGlobal;
  boolean_T c2_c_hoistedGlobal;
  c2_PanelMsgImpl c2_b_Panel;
  int32_T c2_b_CMD_From_COM;
  c2_SyncMsgImpl c2_b_Sync_From;
  int32_T c2_b_Pedal;
  boolean_T c2_b_Fail;
  c2_SyncMsgImpl c2_b_Sync_To;
  c2_ChannelStatusImpl c2_b_Status;
  uint32_T c2_debug_family_var_map[47];
  boolean_T c2_Primary_Side;
  boolean_T c2_max_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_lo_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_med_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0;
  int32_T c2_r_Agree_Nodes__Brake_Mode_0;
  int32_T c2_Mode;
  boolean_T c2_p_Agree_Nodes__Duration_0;
  boolean_T c2_b_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0;
  int32_T c2_i_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0;
  int32_T c2_c_Agree_Nodes__Duration_0;
  boolean_T c2_p_Agree_Nodes__Latch_0;
  boolean_T c2_r_Agree_Nodes__Latch_0;
  boolean_T c2_Error;
  boolean_T c2_Active;
  int32_T c2_pedal_Agree_Nodes__Brake_Cmd_0;
  int32_T c2_mode_Agree_Nodes__Brake_Cmd_0;
  int32_T c2_r_Agree_Nodes__Brake_Cmd_0;
  boolean_T c2_GUARANTEE0;
  boolean_T c2_GUARANTEE1;
  boolean_T c2_GUARANTEE2;
  boolean_T c2_GUARANTEE3;
  boolean_T c2_GUARANTEE4;
  int32_T c2_mode_Agree_Nodes__Brake_Mode_0;
  boolean_T c2_nodeLemma0_Agree_Nodes__Duration_0;
  real_T c2_nargin = 7.0;
  real_T c2_nargout = 0.0;
  boolean_T c2_b0;
  boolean_T c2_b1;
  boolean_T c2_b2;
  boolean_T c2_b3;
  boolean_T c2_b4;
  boolean_T c2_b5;
  boolean_T c2_b6;
  boolean_T c2_b7;
  boolean_T c2_b8;
  int64_T c2_i0;
  boolean_T c2_b9;
  boolean_T c2_b10;
  boolean_T c2_b11;
  boolean_T guard1 = false;
  boolean_T guard2 = false;
  boolean_T guard3 = false;
  boolean_T guard4 = false;
  boolean_T guard5 = false;
  boolean_T guard6 = false;
  boolean_T guard7 = false;
  boolean_T guard8 = false;
  boolean_T guard9 = false;
  boolean_T guard10 = false;
  boolean_T guard11 = false;
  boolean_T guard12 = false;
  boolean_T guard13 = false;
  boolean_T guard14 = false;
  _SFD_CC_CALL(CHART_ENTER_DURING_FUNCTION_TAG, 1U, chartInstance->c2_sfEvent);
  c2_hoistedGlobal = *chartInstance->c2_CMD_From_COM;
  c2_b_hoistedGlobal = *chartInstance->c2_Pedal;
  c2_c_hoistedGlobal = *chartInstance->c2_Fail;
  c2_b_Panel.MAX = *(boolean_T *)&((char_T *)chartInstance->c2_Panel)[0];
  c2_b_Panel.MED = *(boolean_T *)&((char_T *)chartInstance->c2_Panel)[1];
  c2_b_Panel.LO = *(boolean_T *)&((char_T *)chartInstance->c2_Panel)[2];
  c2_b_CMD_From_COM = c2_hoistedGlobal;
  c2_b_Sync_From.Active = *(boolean_T *)&((char_T *)chartInstance->c2_Sync_From)
    [0];
  c2_b_Sync_From.Error = *(boolean_T *)&((char_T *)chartInstance->c2_Sync_From)
    [1];
  c2_b_Pedal = c2_b_hoistedGlobal;
  c2_b_Fail = c2_c_hoistedGlobal;
  c2_b_Sync_To.Active = *(boolean_T *)&((char_T *)chartInstance->c2_Sync_To)[0];
  c2_b_Sync_To.Error = *(boolean_T *)&((char_T *)chartInstance->c2_Sync_To)[1];
  c2_b_Status.Error = *(boolean_T *)&((char_T *)chartInstance->c2_Status)[0];
  c2_b_Status.Active = *(boolean_T *)&((char_T *)chartInstance->c2_Status)[1];
  c2_b_Status.Cmd = *(int32_T *)&((char_T *)chartInstance->c2_Status)[4];
  _SFD_SYMBOL_SCOPE_PUSH_EML(0U, 47U, 47U, c2_debug_family_names,
    c2_debug_family_var_map);
  _SFD_SYMBOL_SCOPE_ADD_EML(&c2_Primary_Side, 0U, c2_e_sf_marshallOut);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_max_Agree_Nodes__Brake_Mode_0, 1U,
    c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&c2_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0, 2U,
     c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&c2_r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0, 3U,
     c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_lo_Agree_Nodes__Brake_Mode_0, 4U,
    c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&c2_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0, 5U,
     c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&c2_r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0, 6U,
     c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_med_Agree_Nodes__Brake_Mode_0, 7U,
    c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&c2_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0, 8U,
     c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&c2_r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0, 9U,
     c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_r_Agree_Nodes__Brake_Mode_0, 10U,
    c2_f_sf_marshallOut, c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_Mode, 11U, c2_f_sf_marshallOut,
    c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_p_Agree_Nodes__Duration_0, 12U,
    c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&c2_b_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0, 13U, c2_e_sf_marshallOut,
     c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&c2_i_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0, 14U, c2_f_sf_marshallOut,
     c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_c_Agree_Nodes__Duration_0, 15U,
    c2_f_sf_marshallOut, c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_p_Agree_Nodes__Latch_0, 16U,
    c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_r_Agree_Nodes__Latch_0, 17U,
    c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_Error, 18U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_Active, 19U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_pedal_Agree_Nodes__Brake_Cmd_0, 20U,
    c2_f_sf_marshallOut, c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_mode_Agree_Nodes__Brake_Cmd_0, 21U,
    c2_f_sf_marshallOut, c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_r_Agree_Nodes__Brake_Cmd_0, 22U,
    c2_f_sf_marshallOut, c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_GUARANTEE0, 23U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_GUARANTEE1, 24U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_GUARANTEE2, 25U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_GUARANTEE3, 26U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_GUARANTEE4, 27U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_mode_Agree_Nodes__Brake_Mode_0, 28U,
    c2_f_sf_marshallOut, c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nodeLemma0_Agree_Nodes__Duration_0,
    29U, c2_e_sf_marshallOut, c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargin, 30U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargout, 31U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML(&c2_b_Panel, 32U, c2_g_sf_marshallOut);
  _SFD_SYMBOL_SCOPE_ADD_EML(&c2_b_CMD_From_COM, 33U, c2_f_sf_marshallOut);
  _SFD_SYMBOL_SCOPE_ADD_EML(&c2_b_Sync_From, 34U, c2_d_sf_marshallOut);
  _SFD_SYMBOL_SCOPE_ADD_EML(&c2_b_Pedal, 35U, c2_f_sf_marshallOut);
  _SFD_SYMBOL_SCOPE_ADD_EML(&c2_b_Fail, 36U, c2_e_sf_marshallOut);
  _SFD_SYMBOL_SCOPE_ADD_EML(&c2_b_Sync_To, 37U, c2_d_sf_marshallOut);
  _SFD_SYMBOL_SCOPE_ADD_EML(&c2_b_Status, 38U, c2_c_sf_marshallOut);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&chartInstance->c2_pre_c_Agree_Nodes__Duration_0, 39U, c2_b_sf_marshallOut,
     c2_b_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0, 40U,
     c2_sf_marshallOut, c2_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0, 41U,
     c2_b_sf_marshallOut, c2_b_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&chartInstance->c2_pre_Active, 42U,
    c2_sf_marshallOut, c2_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&chartInstance->c2_first_time, 43U,
    c2_sf_marshallOut, c2_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0, 44U,
     c2_sf_marshallOut, c2_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0, 45U,
     c2_sf_marshallOut, c2_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE
    (&chartInstance->c2_pre_r_Agree_Nodes__Latch_0, 46U, c2_sf_marshallOut,
     c2_sf_marshallIn);
  CV_EML_FCN(0, 0);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 3);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 4);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 5);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 6);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 7);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 8);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 9);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 10);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 12);
  c2_Primary_Side = false;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 13);
  c2_max_Agree_Nodes__Brake_Mode_0 = c2_b_Panel.MAX;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 14);
  c2_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 =
    c2_max_Agree_Nodes__Brake_Mode_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 16);
  if (CV_EML_IF(0, 1, 0,
                !chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0_not_empty))
  {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 17);
    chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 =
      false;
    chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0_not_empty
      = true;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 21);
  if (CV_EML_IF(0, 1, 1, !chartInstance->c2_first_time_not_empty)) {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 22);
    chartInstance->c2_first_time = true;
    chartInstance->c2_first_time_not_empty = true;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 25);
  guard14 = false;
  if (!chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0) {
    if (c2_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0) {
      c2_b0 = true;
    } else {
      guard14 = true;
    }
  } else {
    guard14 = true;
  }

  if (guard14 == true) {
    c2_b0 = false;
  }

  c2_r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 = c2_arrowFunction
    (chartInstance, chartInstance->c2_first_time, false, c2_b0);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 26);
  c2_lo_Agree_Nodes__Brake_Mode_0 = c2_b_Panel.LO;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 27);
  c2_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 =
    c2_lo_Agree_Nodes__Brake_Mode_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 29);
  if (CV_EML_IF(0, 1, 2,
                !chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0_not_empty))
  {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 30);
    chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 =
      false;
    chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0_not_empty
      = true;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 33);
  guard13 = false;
  if (!chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0) {
    if (c2_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0) {
      c2_b1 = true;
    } else {
      guard13 = true;
    }
  } else {
    guard13 = true;
  }

  if (guard13 == true) {
    c2_b1 = false;
  }

  c2_r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 = c2_arrowFunction
    (chartInstance, chartInstance->c2_first_time, false, c2_b1);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 34);
  c2_med_Agree_Nodes__Brake_Mode_0 = c2_b_Panel.MED;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 35);
  c2_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 =
    c2_med_Agree_Nodes__Brake_Mode_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 37);
  if (CV_EML_IF(0, 1, 3,
                !chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0_not_empty))
  {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 38);
    chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 =
      false;
    chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0_not_empty
      = true;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 41);
  guard12 = false;
  if (!chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0) {
    if (c2_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0) {
      c2_b2 = true;
    } else {
      guard12 = true;
    }
  } else {
    guard12 = true;
  }

  if (guard12 == true) {
    c2_b2 = false;
  }

  c2_r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 = c2_arrowFunction
    (chartInstance, chartInstance->c2_first_time, false, c2_b2);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 43);
  if (CV_EML_IF(0, 1, 4,
                !chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty))
  {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 44);
    chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0 = 0;
    chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty = true;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 47);
  guard11 = false;
  if (c2_isequal(chartInstance,
                 chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0, 2)) {
    if (c2_r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0) {
      c2_b3 = true;
    } else {
      guard11 = true;
    }
  } else {
    guard11 = true;
  }

  if (guard11 == true) {
    c2_b3 = false;
  }

  guard10 = false;
  if (c2_isequal(chartInstance,
                 chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0, 3)) {
    if (c2_r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0) {
      c2_b4 = true;
    } else {
      guard10 = true;
    }
  } else {
    guard10 = true;
  }

  if (guard10 == true) {
    c2_b4 = false;
  }

  guard9 = false;
  if (c2_isequal(chartInstance,
                 chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0, 4)) {
    if (c2_r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0) {
      c2_b5 = true;
    } else {
      guard9 = true;
    }
  } else {
    guard9 = true;
  }

  if (guard9 == true) {
    c2_b5 = false;
  }

  guard8 = false;
  if (chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0 != 2) {
    if (c2_r_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0) {
      c2_b6 = true;
    } else {
      guard8 = true;
    }
  } else {
    guard8 = true;
  }

  if (guard8 == true) {
    c2_b6 = false;
  }

  guard7 = false;
  if (chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0 != 3) {
    if (c2_r_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0) {
      c2_b7 = true;
    } else {
      guard7 = true;
    }
  } else {
    guard7 = true;
  }

  if (guard7 == true) {
    c2_b7 = false;
  }

  guard6 = false;
  if (chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0 != 4) {
    if (c2_r_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0) {
      c2_b8 = true;
    } else {
      guard6 = true;
    }
  } else {
    guard6 = true;
  }

  if (guard6 == true) {
    c2_b8 = false;
  }

  c2_r_Agree_Nodes__Brake_Mode_0 = c2_b_arrowFunction(chartInstance,
    chartInstance->c2_first_time, 1, c2_ifFunction(chartInstance, c2_b3, 1,
    c2_ifFunction(chartInstance, c2_b4, 1, c2_ifFunction(chartInstance, c2_b5, 1,
    c2_ifFunction(chartInstance, c2_b6, 2, c2_ifFunction(chartInstance, c2_b7, 3,
    c2_ifFunction(chartInstance, c2_b8, 4,
                  chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0)))))));
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 48);
  c2_Mode = c2_r_Agree_Nodes__Brake_Mode_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 49);
  c2_p_Agree_Nodes__Duration_0 = !c2_isequal(chartInstance, c2_b_Status.Cmd,
    c2_b_CMD_From_COM);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 50);
  c2_b_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0 = c2_p_Agree_Nodes__Duration_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 51);
  c2_i_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0 = c2_ifFunction(chartInstance,
    c2_b_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0, 1, 0);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 53);
  if (CV_EML_IF(0, 1, 5,
                !chartInstance->c2_pre_c_Agree_Nodes__Duration_0_not_empty)) {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 54);
    chartInstance->c2_pre_c_Agree_Nodes__Duration_0 = 0;
    chartInstance->c2_pre_c_Agree_Nodes__Duration_0_not_empty = true;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 57);
  c2_i0 = (int64_T)chartInstance->c2_pre_c_Agree_Nodes__Duration_0 + 1LL;
  if (c2_i0 > 2147483647LL) {
    CV_SATURATION_EVAL(4, 0, 0, 0, 1);
    c2_i0 = 2147483647LL;
  } else {
    if (CV_SATURATION_EVAL(4, 0, 0, 0, c2_i0 < -2147483648LL)) {
      c2_i0 = -2147483648LL;
    }
  }

  c2_c_Agree_Nodes__Duration_0 = c2_b_arrowFunction(chartInstance,
    chartInstance->c2_first_time,
    c2_i_Agree_Nodes__b2i_0_Agree_Nodes__Duration_0, c2_ifFunction(chartInstance,
    c2_p_Agree_Nodes__Duration_0, (int32_T)c2_i0, 0));
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 58);
  c2_p_Agree_Nodes__Latch_0 = (c2_c_Agree_Nodes__Duration_0 > 15);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 60);
  if (CV_EML_IF(0, 1, 6, !chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty))
  {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 61);
    chartInstance->c2_pre_r_Agree_Nodes__Latch_0 = false;
    chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty = true;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 64);
  guard5 = false;
  if (c2_p_Agree_Nodes__Latch_0) {
    guard5 = true;
  } else if (chartInstance->c2_pre_r_Agree_Nodes__Latch_0) {
    guard5 = true;
  } else {
    c2_b9 = false;
  }

  if (guard5 == true) {
    c2_b9 = true;
  }

  c2_r_Agree_Nodes__Latch_0 = c2_arrowFunction(chartInstance,
    chartInstance->c2_first_time, c2_p_Agree_Nodes__Latch_0, c2_b9);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 65);
  c2_Error = c2_r_Agree_Nodes__Latch_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 67);
  if (CV_EML_IF(0, 1, 7, !chartInstance->c2_pre_Active_not_empty)) {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 68);
    chartInstance->c2_pre_Active = false;
    chartInstance->c2_pre_Active_not_empty = true;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 71);
  guard3 = false;
  guard4 = false;
  if (chartInstance->c2_pre_Active) {
    if (c2_b_Sync_From.Active) {
      if (!c2_b_Sync_From.Error) {
        c2_b10 = true;
      } else {
        guard3 = true;
      }
    } else {
      guard4 = true;
    }
  } else {
    guard4 = true;
  }

  if (guard4 == true) {
    guard3 = true;
  }

  if (guard3 == true) {
    c2_b10 = false;
  }

  guard1 = false;
  guard2 = false;
  if (!chartInstance->c2_pre_Active) {
    if (!c2_b_Sync_From.Active) {
      guard2 = true;
    } else if (c2_b_Sync_From.Error) {
      guard2 = true;
    } else {
      guard1 = true;
    }
  } else {
    guard1 = true;
  }

  if (guard2 == true) {
    c2_b11 = true;
  }

  if (guard1 == true) {
    c2_b11 = false;
  }

  c2_Active = c2_arrowFunction(chartInstance, chartInstance->c2_first_time,
    !c2_b_Sync_From.Active, c2_b_ifFunction(chartInstance, c2_Error, false,
    c2_b_ifFunction(chartInstance, c2_b10, false, c2_b_ifFunction(chartInstance,
    c2_b11, true, chartInstance->c2_pre_Active))));
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 72);
  c2_pedal_Agree_Nodes__Brake_Cmd_0 = c2_b_Pedal;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 73);
  c2_mode_Agree_Nodes__Brake_Cmd_0 = c2_Mode;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 74);
  c2_r_Agree_Nodes__Brake_Cmd_0 = c2_ifFunction(chartInstance, c2_isequal
    (chartInstance, c2_mode_Agree_Nodes__Brake_Cmd_0, 2), 170, c2_ifFunction
    (chartInstance, c2_isequal(chartInstance, c2_mode_Agree_Nodes__Brake_Cmd_0,
    3), 340, c2_ifFunction(chartInstance, c2_isequal(chartInstance,
    c2_mode_Agree_Nodes__Brake_Cmd_0, 4), 510, c2_pedal_Agree_Nodes__Brake_Cmd_0)));
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 75);
  c2_GUARANTEE0 = c2_impliesFunction(chartInstance, !c2_b_Fail, c2_isequal
    (chartInstance, c2_b_Status.Cmd, c2_r_Agree_Nodes__Brake_Cmd_0));
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 76);
  c2_GUARANTEE1 = !c2_b_isequal(chartInstance, c2_b_Status.Error, c2_Error);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 77);
  c2_GUARANTEE2 = c2_b_isequal(chartInstance, c2_b_Sync_To.Error, c2_Error);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 78);
  c2_GUARANTEE3 = c2_b_isequal(chartInstance, c2_b_Status.Active, c2_Active);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 79);
  c2_GUARANTEE4 = c2_b_isequal(chartInstance, c2_b_Sync_To.Active, c2_Active);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 80);
  c2_mode_Agree_Nodes__Brake_Mode_0 = c2_Mode;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 81);
  c2_nodeLemma0_Agree_Nodes__Duration_0 = (c2_c_Agree_Nodes__Duration_0 >= 0);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 82);
  chartInstance->c2_pre_c_Agree_Nodes__Duration_0 = c2_c_Agree_Nodes__Duration_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 83);
  chartInstance->c2_pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0 =
    c2_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 84);
  chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0 =
    c2_mode_Agree_Nodes__Brake_Mode_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 85);
  chartInstance->c2_pre_Active = c2_Active;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 86);
  chartInstance->c2_first_time = false;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 87);
  chartInstance->c2_pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0 =
    c2_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 88);
  chartInstance->c2_pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0 =
    c2_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 89);
  chartInstance->c2_pre_r_Agree_Nodes__Latch_0 = c2_r_Agree_Nodes__Latch_0;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 91);
  CV_EML_TESTOBJECTIVE(0, 1, 0, c2_GUARANTEE0);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 93);
  CV_EML_TESTOBJECTIVE(0, 1, 1, c2_GUARANTEE1);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 95);
  CV_EML_TESTOBJECTIVE(0, 1, 2, c2_GUARANTEE2);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 97);
  CV_EML_TESTOBJECTIVE(0, 1, 3, c2_GUARANTEE3);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 99);
  CV_EML_TESTOBJECTIVE(0, 1, 4, c2_GUARANTEE4);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 101);
  CV_EML_TESTOBJECTIVE(0, 1, 5, c2_nodeLemma0_Agree_Nodes__Duration_0);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, -101);
  _SFD_SYMBOL_SCOPE_POP();
  _SFD_CC_CALL(EXIT_OUT_OF_FUNCTION_TAG, 1U, chartInstance->c2_sfEvent);
}

static void initSimStructsc2_MON_sl_after_replacement1
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
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

static const mxArray *c2_sf_marshallOut(void *chartInstanceVoid, void *c2_inData)
{
  const mxArray *c2_mxArrayOutData;
  boolean_T c2_u;
  const mxArray *c2_y = NULL;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_mxArrayOutData = NULL;
  c2_u = *(boolean_T *)c2_inData;
  c2_y = NULL;
  if (!chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty) {
    sf_mex_assign(&c2_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0), false);
  } else {
    sf_mex_assign(&c2_y, sf_mex_create("y", &c2_u, 11, 0U, 0U, 0U, 0), false);
  }

  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static boolean_T c2_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_b_pre_r_Agree_Nodes__Latch_0, const char_T *c2_identifier, boolean_T
   *c2_svPtr)
{
  boolean_T c2_y;
  emlrtMsgIdentifier c2_thisId;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_b_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c2_b_pre_r_Agree_Nodes__Latch_0), &c2_thisId, c2_svPtr);
  sf_mex_destroy(&c2_b_pre_r_Agree_Nodes__Latch_0);
  return c2_y;
}

static boolean_T c2_b_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId, boolean_T *c2_svPtr)
{
  boolean_T c2_y;
  boolean_T c2_b12;
  (void)chartInstance;
  if (mxIsEmpty(c2_u)) {
    *c2_svPtr = false;
  } else {
    *c2_svPtr = true;
    sf_mex_import(c2_parentId, sf_mex_dup(c2_u), &c2_b12, 1, 11, 0U, 0, 0U, 0);
    c2_y = c2_b12;
  }

  sf_mex_destroy(&c2_u);
  return c2_y;
}

static void c2_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData)
{
  const mxArray *c2_b_pre_r_Agree_Nodes__Latch_0;
  const char_T *c2_identifier;
  boolean_T *c2_svPtr;
  emlrtMsgIdentifier c2_thisId;
  boolean_T c2_y;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_b_pre_r_Agree_Nodes__Latch_0 = sf_mex_dup(c2_mxArrayInData);
  c2_identifier = c2_varName;
  c2_svPtr = &chartInstance->c2_pre_r_Agree_Nodes__Latch_0_not_empty;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_b_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c2_b_pre_r_Agree_Nodes__Latch_0), &c2_thisId, c2_svPtr);
  sf_mex_destroy(&c2_b_pre_r_Agree_Nodes__Latch_0);
  *(boolean_T *)c2_outData = c2_y;
  sf_mex_destroy(&c2_mxArrayInData);
}

static const mxArray *c2_b_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData;
  int32_T c2_u;
  const mxArray *c2_y = NULL;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_mxArrayOutData = NULL;
  c2_u = *(int32_T *)c2_inData;
  c2_y = NULL;
  if (!chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty) {
    sf_mex_assign(&c2_y, sf_mex_create("y", NULL, 0, 0U, 1U, 0U, 2, 0, 0), false);
  } else {
    sf_mex_assign(&c2_y, sf_mex_create("y", &c2_u, 6, 0U, 0U, 0U, 0), false);
  }

  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static int32_T c2_c_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_b_pre_mode_Agree_Nodes__Brake_Mode_0, const char_T *c2_identifier,
   boolean_T *c2_svPtr)
{
  int32_T c2_y;
  emlrtMsgIdentifier c2_thisId;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_d_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c2_b_pre_mode_Agree_Nodes__Brake_Mode_0), &c2_thisId, c2_svPtr);
  sf_mex_destroy(&c2_b_pre_mode_Agree_Nodes__Brake_Mode_0);
  return c2_y;
}

static int32_T c2_d_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId, boolean_T *c2_svPtr)
{
  int32_T c2_y;
  int32_T c2_i1;
  (void)chartInstance;
  if (mxIsEmpty(c2_u)) {
    *c2_svPtr = false;
  } else {
    *c2_svPtr = true;
    sf_mex_import(c2_parentId, sf_mex_dup(c2_u), &c2_i1, 1, 6, 0U, 0, 0U, 0);
    c2_y = c2_i1;
  }

  sf_mex_destroy(&c2_u);
  return c2_y;
}

static void c2_b_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData)
{
  const mxArray *c2_b_pre_mode_Agree_Nodes__Brake_Mode_0;
  const char_T *c2_identifier;
  boolean_T *c2_svPtr;
  emlrtMsgIdentifier c2_thisId;
  int32_T c2_y;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_b_pre_mode_Agree_Nodes__Brake_Mode_0 = sf_mex_dup(c2_mxArrayInData);
  c2_identifier = c2_varName;
  c2_svPtr = &chartInstance->c2_pre_mode_Agree_Nodes__Brake_Mode_0_not_empty;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_d_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c2_b_pre_mode_Agree_Nodes__Brake_Mode_0), &c2_thisId, c2_svPtr);
  sf_mex_destroy(&c2_b_pre_mode_Agree_Nodes__Brake_Mode_0);
  *(int32_T *)c2_outData = c2_y;
  sf_mex_destroy(&c2_mxArrayInData);
}

static const mxArray *c2_c_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData;
  c2_ChannelStatusImpl c2_u;
  const mxArray *c2_y = NULL;
  boolean_T c2_b_u;
  const mxArray *c2_b_y = NULL;
  boolean_T c2_c_u;
  const mxArray *c2_c_y = NULL;
  int32_T c2_d_u;
  const mxArray *c2_d_y = NULL;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_mxArrayOutData = NULL;
  c2_u = *(c2_ChannelStatusImpl *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_createstruct("structure", 2, 1, 1), false);
  c2_b_u = c2_u.Error;
  c2_b_y = NULL;
  sf_mex_assign(&c2_b_y, sf_mex_create("y", &c2_b_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_addfield(c2_y, c2_b_y, "Error", "Error", 0);
  c2_c_u = c2_u.Active;
  c2_c_y = NULL;
  sf_mex_assign(&c2_c_y, sf_mex_create("y", &c2_c_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_addfield(c2_y, c2_c_y, "Active", "Active", 0);
  c2_d_u = c2_u.Cmd;
  c2_d_y = NULL;
  sf_mex_assign(&c2_d_y, sf_mex_create("y", &c2_d_u, 6, 0U, 0U, 0U, 0), false);
  sf_mex_addfield(c2_y, c2_d_y, "Cmd", "Cmd", 0);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static const mxArray *c2_d_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData;
  c2_SyncMsgImpl c2_u;
  const mxArray *c2_y = NULL;
  boolean_T c2_b_u;
  const mxArray *c2_b_y = NULL;
  boolean_T c2_c_u;
  const mxArray *c2_c_y = NULL;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_mxArrayOutData = NULL;
  c2_u = *(c2_SyncMsgImpl *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_createstruct("structure", 2, 1, 1), false);
  c2_b_u = c2_u.Active;
  c2_b_y = NULL;
  sf_mex_assign(&c2_b_y, sf_mex_create("y", &c2_b_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_addfield(c2_y, c2_b_y, "Active", "Active", 0);
  c2_c_u = c2_u.Error;
  c2_c_y = NULL;
  sf_mex_assign(&c2_c_y, sf_mex_create("y", &c2_c_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_addfield(c2_y, c2_c_y, "Error", "Error", 0);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static const mxArray *c2_e_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData = NULL;
  boolean_T c2_u;
  const mxArray *c2_y = NULL;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_u = *(boolean_T *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_create("y", &c2_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static const mxArray *c2_f_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData = NULL;
  int32_T c2_u;
  const mxArray *c2_y = NULL;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_u = *(int32_T *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_create("y", &c2_u, 6, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static const mxArray *c2_g_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData;
  c2_PanelMsgImpl c2_u;
  const mxArray *c2_y = NULL;
  boolean_T c2_b_u;
  const mxArray *c2_b_y = NULL;
  boolean_T c2_c_u;
  const mxArray *c2_c_y = NULL;
  boolean_T c2_d_u;
  const mxArray *c2_d_y = NULL;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_mxArrayOutData = NULL;
  c2_u = *(c2_PanelMsgImpl *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_createstruct("structure", 2, 1, 1), false);
  c2_b_u = c2_u.MAX;
  c2_b_y = NULL;
  sf_mex_assign(&c2_b_y, sf_mex_create("y", &c2_b_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_addfield(c2_y, c2_b_y, "MAX", "MAX", 0);
  c2_c_u = c2_u.MED;
  c2_c_y = NULL;
  sf_mex_assign(&c2_c_y, sf_mex_create("y", &c2_c_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_addfield(c2_y, c2_c_y, "MED", "MED", 0);
  c2_d_u = c2_u.LO;
  c2_d_y = NULL;
  sf_mex_assign(&c2_d_y, sf_mex_create("y", &c2_d_u, 11, 0U, 0U, 0U, 0), false);
  sf_mex_addfield(c2_y, c2_d_y, "LO", "LO", 0);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static const mxArray *c2_h_sf_marshallOut(void *chartInstanceVoid, void
  *c2_inData)
{
  const mxArray *c2_mxArrayOutData = NULL;
  real_T c2_u;
  const mxArray *c2_y = NULL;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxArrayOutData = NULL;
  c2_u = *(real_T *)c2_inData;
  c2_y = NULL;
  sf_mex_assign(&c2_y, sf_mex_create("y", &c2_u, 0, 0U, 0U, 0U, 0), false);
  sf_mex_assign(&c2_mxArrayOutData, c2_y, false);
  return c2_mxArrayOutData;
}

static real_T c2_e_emlrt_marshallIn(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, const mxArray *c2_u, const emlrtMsgIdentifier *c2_parentId)
{
  real_T c2_y;
  real_T c2_d0;
  (void)chartInstance;
  sf_mex_import(c2_parentId, sf_mex_dup(c2_u), &c2_d0, 1, 0, 0U, 0, 0U, 0);
  c2_y = c2_d0;
  sf_mex_destroy(&c2_u);
  return c2_y;
}

static void c2_c_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData)
{
  const mxArray *c2_nargout;
  const char_T *c2_identifier;
  emlrtMsgIdentifier c2_thisId;
  real_T c2_y;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_nargout = sf_mex_dup(c2_mxArrayInData);
  c2_identifier = c2_varName;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_e_emlrt_marshallIn(chartInstance, sf_mex_dup(c2_nargout), &c2_thisId);
  sf_mex_destroy(&c2_nargout);
  *(real_T *)c2_outData = c2_y;
  sf_mex_destroy(&c2_mxArrayInData);
}

static boolean_T c2_f_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId)
{
  boolean_T c2_y;
  boolean_T c2_b13;
  (void)chartInstance;
  sf_mex_import(c2_parentId, sf_mex_dup(c2_u), &c2_b13, 1, 11, 0U, 0, 0U, 0);
  c2_y = c2_b13;
  sf_mex_destroy(&c2_u);
  return c2_y;
}

static void c2_d_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData)
{
  const mxArray *c2_nodeLemma0_Agree_Nodes__Duration_0;
  const char_T *c2_identifier;
  emlrtMsgIdentifier c2_thisId;
  boolean_T c2_y;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_nodeLemma0_Agree_Nodes__Duration_0 = sf_mex_dup(c2_mxArrayInData);
  c2_identifier = c2_varName;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_f_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c2_nodeLemma0_Agree_Nodes__Duration_0), &c2_thisId);
  sf_mex_destroy(&c2_nodeLemma0_Agree_Nodes__Duration_0);
  *(boolean_T *)c2_outData = c2_y;
  sf_mex_destroy(&c2_mxArrayInData);
}

static int32_T c2_g_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId)
{
  int32_T c2_y;
  int32_T c2_i2;
  (void)chartInstance;
  sf_mex_import(c2_parentId, sf_mex_dup(c2_u), &c2_i2, 1, 6, 0U, 0, 0U, 0);
  c2_y = c2_i2;
  sf_mex_destroy(&c2_u);
  return c2_y;
}

static void c2_e_sf_marshallIn(void *chartInstanceVoid, const mxArray
  *c2_mxArrayInData, const char_T *c2_varName, void *c2_outData)
{
  const mxArray *c2_mode_Agree_Nodes__Brake_Mode_0;
  const char_T *c2_identifier;
  emlrtMsgIdentifier c2_thisId;
  int32_T c2_y;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mode_Agree_Nodes__Brake_Mode_0 = sf_mex_dup(c2_mxArrayInData);
  c2_identifier = c2_varName;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_g_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c2_mode_Agree_Nodes__Brake_Mode_0), &c2_thisId);
  sf_mex_destroy(&c2_mode_Agree_Nodes__Brake_Mode_0);
  *(int32_T *)c2_outData = c2_y;
  sf_mex_destroy(&c2_mxArrayInData);
}

const mxArray *sf_c2_MON_sl_after_replacement1_get_eml_resolved_functions_info
  (void)
{
  const mxArray *c2_nameCaptureInfo = NULL;
  c2_nameCaptureInfo = NULL;
  sf_mex_assign(&c2_nameCaptureInfo, sf_mex_create("nameCaptureInfo", NULL, 0,
    0U, 1U, 0U, 2, 0, 1), false);
  return c2_nameCaptureInfo;
}

static boolean_T c2_arrowFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_b_first_time, boolean_T c2_left, boolean_T
  c2_right)
{
  boolean_T c2_output;
  uint32_T c2_debug_family_var_map[6];
  real_T c2_nargin = 3.0;
  real_T c2_nargout = 1.0;
  _SFD_SYMBOL_SCOPE_PUSH_EML(0U, 6U, 6U, c2_b_debug_family_names,
    c2_debug_family_var_map);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargin, 0U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargout, 1U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_b_first_time, 2U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_left, 3U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_right, 4U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_output, 5U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  CV_EML_FCN(0, 2);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 114);
  if (CV_EML_IF(0, 1, 9, c2_b_first_time)) {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 115);
    c2_output = c2_left;
  } else {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 117);
    c2_output = c2_right;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, -117);
  _SFD_SYMBOL_SCOPE_POP();
  return c2_output;
}

static boolean_T c2_isequal(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, int32_T c2_varargin_1, int32_T c2_varargin_2)
{
  boolean_T c2_p;
  int32_T c2_b_varargin_1;
  int32_T c2_b_varargin_2;
  int32_T c2_x1;
  int32_T c2_x2;
  boolean_T c2_b_p;
  int32_T c2_b_x1;
  int32_T c2_b_x2;
  boolean_T c2_c_p;
  (void)chartInstance;
  c2_b_varargin_1 = c2_varargin_1;
  c2_b_varargin_2 = c2_varargin_2;
  c2_p = false;
  c2_x1 = c2_b_varargin_1;
  c2_x2 = c2_b_varargin_2;
  c2_b_p = true;
  c2_b_x1 = c2_x1;
  c2_b_x2 = c2_x2;
  c2_c_p = (c2_b_x1 == c2_b_x2);
  if (!c2_c_p) {
    c2_b_p = false;
  }

  if (!c2_b_p) {
  } else {
    c2_p = true;
  }

  return c2_p;
}

static int32_T c2_ifFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_cond, int32_T c2_ifBranch, int32_T c2_elseBranch)
{
  int32_T c2_output;
  uint32_T c2_debug_family_var_map[6];
  real_T c2_nargin = 3.0;
  real_T c2_nargout = 1.0;
  _SFD_SYMBOL_SCOPE_PUSH_EML(0U, 6U, 6U, c2_c_debug_family_names,
    c2_debug_family_var_map);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargin, 0U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargout, 1U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_cond, 2U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_ifBranch, 3U, c2_f_sf_marshallOut,
    c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_elseBranch, 4U, c2_f_sf_marshallOut,
    c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_output, 5U, c2_f_sf_marshallOut,
    c2_e_sf_marshallIn);
  CV_EML_FCN(0, 1);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 106);
  if (CV_EML_IF(0, 1, 8, c2_cond)) {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 107);
    c2_output = c2_ifBranch;
  } else {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 109);
    c2_output = c2_elseBranch;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, -109);
  _SFD_SYMBOL_SCOPE_POP();
  return c2_output;
}

static int32_T c2_b_arrowFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_b_first_time, int32_T c2_left, int32_T c2_right)
{
  int32_T c2_output;
  uint32_T c2_debug_family_var_map[6];
  real_T c2_nargin = 3.0;
  real_T c2_nargout = 1.0;
  _SFD_SYMBOL_SCOPE_PUSH_EML(0U, 6U, 6U, c2_d_debug_family_names,
    c2_debug_family_var_map);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargin, 0U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargout, 1U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_b_first_time, 2U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_left, 3U, c2_f_sf_marshallOut,
    c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_right, 4U, c2_f_sf_marshallOut,
    c2_e_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_output, 5U, c2_f_sf_marshallOut,
    c2_e_sf_marshallIn);
  CV_EML_FCN(0, 2);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 114);
  if (CV_EML_IF(0, 1, 9, c2_b_first_time)) {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 115);
    c2_output = c2_left;
  } else {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 117);
    c2_output = c2_right;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, -117);
  _SFD_SYMBOL_SCOPE_POP();
  return c2_output;
}

static boolean_T c2_b_ifFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_cond, boolean_T c2_ifBranch, boolean_T
  c2_elseBranch)
{
  boolean_T c2_output;
  uint32_T c2_debug_family_var_map[6];
  real_T c2_nargin = 3.0;
  real_T c2_nargout = 1.0;
  _SFD_SYMBOL_SCOPE_PUSH_EML(0U, 6U, 6U, c2_e_debug_family_names,
    c2_debug_family_var_map);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargin, 0U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargout, 1U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_cond, 2U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_ifBranch, 3U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_elseBranch, 4U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_output, 5U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  CV_EML_FCN(0, 1);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 106);
  if (CV_EML_IF(0, 1, 8, c2_cond)) {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 107);
    c2_output = c2_ifBranch;
  } else {
    _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 109);
    c2_output = c2_elseBranch;
  }

  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, -109);
  _SFD_SYMBOL_SCOPE_POP();
  return c2_output;
}

static boolean_T c2_impliesFunction(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_left, boolean_T c2_right)
{
  boolean_T c2_output;
  uint32_T c2_debug_family_var_map[5];
  real_T c2_nargin = 2.0;
  real_T c2_nargout = 1.0;
  boolean_T c2_b14;
  boolean_T guard1 = false;
  _SFD_SYMBOL_SCOPE_PUSH_EML(0U, 5U, 5U, c2_f_debug_family_names,
    c2_debug_family_var_map);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargin, 0U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_nargout, 1U, c2_h_sf_marshallOut,
    c2_c_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_left, 2U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_right, 3U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  _SFD_SYMBOL_SCOPE_ADD_EML_IMPORTABLE(&c2_output, 4U, c2_e_sf_marshallOut,
    c2_d_sf_marshallIn);
  CV_EML_FCN(0, 3);
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, 122);
  guard1 = false;
  if (!c2_left) {
    guard1 = true;
  } else if (c2_right) {
    guard1 = true;
  } else {
    c2_b14 = false;
  }

  if (guard1 == true) {
    c2_b14 = true;
  }

  c2_output = c2_b14;
  _SFD_EML_CALL(0U, chartInstance->c2_sfEvent, -122);
  _SFD_SYMBOL_SCOPE_POP();
  return c2_output;
}

static boolean_T c2_b_isequal(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance, boolean_T c2_varargin_1, boolean_T c2_varargin_2)
{
  boolean_T c2_p;
  boolean_T c2_b_varargin_1;
  boolean_T c2_b_varargin_2;
  boolean_T c2_x1;
  boolean_T c2_x2;
  boolean_T c2_b_p;
  boolean_T c2_b_x1;
  boolean_T c2_b_x2;
  boolean_T c2_c_p;
  (void)chartInstance;
  c2_b_varargin_1 = c2_varargin_1;
  c2_b_varargin_2 = c2_varargin_2;
  c2_p = false;
  c2_x1 = c2_b_varargin_1;
  c2_x2 = c2_b_varargin_2;
  c2_b_p = true;
  c2_b_x1 = c2_x1;
  c2_b_x2 = c2_x2;
  c2_c_p = ((int32_T)c2_b_x1 == (int32_T)c2_b_x2);
  if (!c2_c_p) {
    c2_b_p = false;
  }

  if (!c2_b_p) {
  } else {
    c2_p = true;
  }

  return c2_p;
}

static const mxArray *c2_Panel_bus_io(void *chartInstanceVoid, void *c2_pData)
{
  const mxArray *c2_mxVal = NULL;
  c2_PanelMsgImpl c2_tmp;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxVal = NULL;
  c2_tmp.MAX = *(boolean_T *)&((char_T *)(c2_PanelMsgImpl *)c2_pData)[0];
  c2_tmp.MED = *(boolean_T *)&((char_T *)(c2_PanelMsgImpl *)c2_pData)[1];
  c2_tmp.LO = *(boolean_T *)&((char_T *)(c2_PanelMsgImpl *)c2_pData)[2];
  sf_mex_assign(&c2_mxVal, c2_g_sf_marshallOut(chartInstance, &c2_tmp), false);
  return c2_mxVal;
}

static const mxArray *c2_Sync_From_bus_io(void *chartInstanceVoid, void
  *c2_pData)
{
  const mxArray *c2_mxVal = NULL;
  c2_SyncMsgImpl c2_tmp;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxVal = NULL;
  c2_tmp.Active = *(boolean_T *)&((char_T *)(c2_SyncMsgImpl *)c2_pData)[0];
  c2_tmp.Error = *(boolean_T *)&((char_T *)(c2_SyncMsgImpl *)c2_pData)[1];
  sf_mex_assign(&c2_mxVal, c2_d_sf_marshallOut(chartInstance, &c2_tmp), false);
  return c2_mxVal;
}

static const mxArray *c2_Status_bus_io(void *chartInstanceVoid, void *c2_pData)
{
  const mxArray *c2_mxVal = NULL;
  c2_ChannelStatusImpl c2_tmp;
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)
    chartInstanceVoid;
  c2_mxVal = NULL;
  c2_tmp.Error = *(boolean_T *)&((char_T *)(c2_ChannelStatusImpl *)c2_pData)[0];
  c2_tmp.Active = *(boolean_T *)&((char_T *)(c2_ChannelStatusImpl *)c2_pData)[1];
  c2_tmp.Cmd = *(int32_T *)&((char_T *)(c2_ChannelStatusImpl *)c2_pData)[4];
  sf_mex_assign(&c2_mxVal, c2_c_sf_marshallOut(chartInstance, &c2_tmp), false);
  return c2_mxVal;
}

static uint8_T c2_h_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_b_is_active_c2_MON_sl_after_replacement1, const char_T *c2_identifier)
{
  uint8_T c2_y;
  emlrtMsgIdentifier c2_thisId;
  c2_thisId.fIdentifier = c2_identifier;
  c2_thisId.fParent = NULL;
  c2_thisId.bParentIsCell = false;
  c2_y = c2_i_emlrt_marshallIn(chartInstance, sf_mex_dup
    (c2_b_is_active_c2_MON_sl_after_replacement1), &c2_thisId);
  sf_mex_destroy(&c2_b_is_active_c2_MON_sl_after_replacement1);
  return c2_y;
}

static uint8_T c2_i_emlrt_marshallIn
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance, const mxArray
   *c2_u, const emlrtMsgIdentifier *c2_parentId)
{
  uint8_T c2_y;
  uint8_T c2_u0;
  (void)chartInstance;
  sf_mex_import(c2_parentId, sf_mex_dup(c2_u), &c2_u0, 1, 3, 0U, 0, 0U, 0);
  c2_y = c2_u0;
  sf_mex_destroy(&c2_u);
  return c2_y;
}

static void init_dsm_address_info(SFc2_MON_sl_after_replacement1InstanceStruct
  *chartInstance)
{
  (void)chartInstance;
}

static void init_simulink_io_address
  (SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance)
{
  chartInstance->c2_Panel = (c2_PanelMsgImpl *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 0);
  chartInstance->c2_CMD_From_COM = (int32_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 1);
  chartInstance->c2_Sync_From = (c2_SyncMsgImpl *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 2);
  chartInstance->c2_Pedal = (int32_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 3);
  chartInstance->c2_Fail = (boolean_T *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 4);
  chartInstance->c2_Sync_To = (c2_SyncMsgImpl *)ssGetInputPortSignal_wrapper
    (chartInstance->S, 5);
  chartInstance->c2_Status = (c2_ChannelStatusImpl *)
    ssGetInputPortSignal_wrapper(chartInstance->S, 6);
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

void sf_c2_MON_sl_after_replacement1_get_check_sum(mxArray *plhs[])
{
  ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(3763867441U);
  ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(1034933975U);
  ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(1779632397U);
  ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(2398370411U);
}

mxArray* sf_c2_MON_sl_after_replacement1_get_post_codegen_info(void);
mxArray *sf_c2_MON_sl_after_replacement1_get_autoinheritance_info(void)
{
  const char *autoinheritanceFields[] = { "checksum", "inputs", "parameters",
    "outputs", "locals", "postCodegenInfo" };

  mxArray *mxAutoinheritanceInfo = mxCreateStructMatrix(1, 1, sizeof
    (autoinheritanceFields)/sizeof(autoinheritanceFields[0]),
    autoinheritanceFields);

  {
    mxArray *mxChecksum = mxCreateString("qKzD6V0QYSAaY7w0HIpKLF");
    mxSetField(mxAutoinheritanceInfo,0,"checksum",mxChecksum);
  }

  {
    const char *dataFields[] = { "size", "type", "complexity" };

    mxArray *mxData = mxCreateStructMatrix(1,7,3,dataFields);

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,0,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(13));
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(8));
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(13));
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
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(8));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,3,"type",mxType);
    }

    mxSetField(mxData,3,"complexity",mxCreateDoubleScalar(0));

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,4,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(1));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,4,"type",mxType);
    }

    mxSetField(mxData,4,"complexity",mxCreateDoubleScalar(0));

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,5,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(13));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,5,"type",mxType);
    }

    mxSetField(mxData,5,"complexity",mxCreateDoubleScalar(0));

    {
      mxArray *mxSize = mxCreateDoubleMatrix(1,0,mxREAL);
      double *pr = mxGetPr(mxSize);
      mxSetField(mxData,6,"size",mxSize);
    }

    {
      const char *typeFields[] = { "base", "fixpt", "isFixedPointType" };

      mxArray *mxType = mxCreateStructMatrix(1,1,sizeof(typeFields)/sizeof
        (typeFields[0]),typeFields);
      mxSetField(mxType,0,"base",mxCreateDoubleScalar(13));
      mxSetField(mxType,0,"fixpt",mxCreateDoubleMatrix(0,0,mxREAL));
      mxSetField(mxType,0,"isFixedPointType",mxCreateDoubleScalar(0));
      mxSetField(mxData,6,"type",mxType);
    }

    mxSetField(mxData,6,"complexity",mxCreateDoubleScalar(0));
    mxSetField(mxAutoinheritanceInfo,0,"inputs",mxData);
  }

  {
    mxSetField(mxAutoinheritanceInfo,0,"parameters",mxCreateDoubleMatrix(0,0,
                mxREAL));
  }

  {
    mxSetField(mxAutoinheritanceInfo,0,"outputs",mxCreateDoubleMatrix(0,0,mxREAL));
  }

  {
    mxSetField(mxAutoinheritanceInfo,0,"locals",mxCreateDoubleMatrix(0,0,mxREAL));
  }

  {
    mxArray* mxPostCodegenInfo =
      sf_c2_MON_sl_after_replacement1_get_post_codegen_info();
    mxSetField(mxAutoinheritanceInfo,0,"postCodegenInfo",mxPostCodegenInfo);
  }

  return(mxAutoinheritanceInfo);
}

mxArray *sf_c2_MON_sl_after_replacement1_third_party_uses_info(void)
{
  mxArray * mxcell3p = mxCreateCellMatrix(1,0);
  return(mxcell3p);
}

mxArray *sf_c2_MON_sl_after_replacement1_jit_fallback_info(void)
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

mxArray *sf_c2_MON_sl_after_replacement1_updateBuildInfo_args_info(void)
{
  mxArray *mxBIArgs = mxCreateCellMatrix(1,0);
  return mxBIArgs;
}

mxArray* sf_c2_MON_sl_after_replacement1_get_post_codegen_info(void)
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

static const mxArray *sf_get_sim_state_info_c2_MON_sl_after_replacement1(void)
{
  const char *infoFields[] = { "chartChecksum", "varInfo" };

  mxArray *mxInfo = mxCreateStructMatrix(1, 1, 2, infoFields);
  const char *infoEncStr[] = {
    "100 S1x9'type','srcId','name','auxInfo'{{M[4],M[0],T\"first_time\",S'l','i','p'{{M1x2[282 292],M[0],}}},{M[4],M[0],T\"pre_Active\",S'l','i','p'{{M1x2[260 270],M[0],}}},{M[4],M[0],T\"pre_c_Agree_Nodes__Duration_0\",S'l','i','p'{{M1x2[110 139],M[0],}}},{M[4],M[0],T\"pre_mode_Agree_Nodes__Brake_Mode_0\",S'l','i','p'{{M1x2[214 248],M[0],}}},{M[4],M[0],T\"pre_p_Agree_Nodes__Rise_0_Agree_Nodes__Brake_Mode_0\",S'l','i','p'{{M1x2[304 355],M[0],}}},{M[4],M[0],T\"pre_p_Agree_Nodes__Rise_1_Agree_Nodes__Brake_Mode_0\",S'l','i','p'{{M1x2[367 418],M[0],}}},{M[4],M[0],T\"pre_p_Agree_Nodes__Rise_2_Agree_Nodes__Brake_Mode_0\",S'l','i','p'{{M1x2[151 202],M[0],}}},{M[4],M[0],T\"pre_r_Agree_Nodes__Latch_0\",S'l','i','p'{{M1x2[430 456],M[0],}}},{M[8],M[0],T\"is_active_c2_MON_sl_after_replacement1\",}}"
  };

  mxArray *mxVarInfo = sf_mex_decode_encoded_mx_struct_array(infoEncStr, 9, 10);
  mxArray *mxChecksum = mxCreateDoubleMatrix(1, 4, mxREAL);
  sf_c2_MON_sl_after_replacement1_get_check_sum(&mxChecksum);
  mxSetField(mxInfo, 0, infoFields[0], mxChecksum);
  mxSetField(mxInfo, 0, infoFields[1], mxVarInfo);
  return mxInfo;
}

static void chart_debug_initialization(SimStruct *S, unsigned int
  fullDebuggerInitialization)
{
  if (!sim_mode_is_rtw_gen(S)) {
    SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance =
      (SFc2_MON_sl_after_replacement1InstanceStruct *)sf_get_chart_instance_ptr
      (S);
    if (ssIsFirstInitCond(S) && fullDebuggerInitialization==1) {
      /* do this only if simulation is starting */
      {
        unsigned int chartAlreadyPresent;
        chartAlreadyPresent = sf_debug_initialize_chart
          (sfGlobalDebugInstanceStruct,
           _MON_sl_after_replacement1MachineNumber_,
           2,
           1,
           1,
           0,
           7,
           0,
           0,
           0,
           0,
           0,
           &chartInstance->chartNumber,
           &chartInstance->instanceNumber,
           (void *)S);

        /* Each instance must initialize its own list of scripts */
        init_script_number_translation(_MON_sl_after_replacement1MachineNumber_,
          chartInstance->chartNumber,chartInstance->instanceNumber);
        if (chartAlreadyPresent==0) {
          /* this is the first instance */
          sf_debug_set_chart_disable_implicit_casting
            (sfGlobalDebugInstanceStruct,
             _MON_sl_after_replacement1MachineNumber_,chartInstance->chartNumber,
             1);
          sf_debug_set_chart_event_thresholds(sfGlobalDebugInstanceStruct,
            _MON_sl_after_replacement1MachineNumber_,
            chartInstance->chartNumber,
            0,
            0,
            0);
          _SFD_SET_DATA_PROPS(0,1,1,0,"Panel");
          _SFD_SET_DATA_PROPS(1,1,1,0,"CMD_From_COM");
          _SFD_SET_DATA_PROPS(2,1,1,0,"Sync_From");
          _SFD_SET_DATA_PROPS(3,1,1,0,"Pedal");
          _SFD_SET_DATA_PROPS(4,1,1,0,"Fail");
          _SFD_SET_DATA_PROPS(5,1,1,0,"Sync_To");
          _SFD_SET_DATA_PROPS(6,1,1,0,"Status");
          _SFD_STATE_INFO(0,0,2);
          _SFD_CH_SUBSTATE_COUNT(0);
          _SFD_CH_SUBSTATE_DECOMP(0);
        }

        _SFD_CV_INIT_CHART(0,0,0,0);

        {
          _SFD_CV_INIT_STATE(0,0,0,0,0,0,NULL,NULL);
        }

        _SFD_CV_INIT_TRANS(0,0,NULL,NULL,0,NULL);

        /* Initialization of MATLAB Function Model Coverage */
        _SFD_CV_INIT_EML(0,1,4,0,10,6,1,0,0,0,0,0);
        _SFD_CV_INIT_EML_FCN(0,0,"eML_blk_kernel",0,-1,5533);
        _SFD_CV_INIT_EML_FCN(0,1,"ifFunction",5535,-1,5656);
        _SFD_CV_INIT_EML_FCN(0,2,"arrowFunction",5658,-1,5776);
        _SFD_CV_INIT_EML_FCN(0,3,"impliesFunction",5778,-1,5860);
        _SFD_CV_INIT_EML_TESTOBJECTIVE(0,1,0,"cvmetric_Sldv_proof",5362,-1,5384);
        _SFD_CV_INIT_EML_TESTOBJECTIVE(0,1,1,"cvmetric_Sldv_proof",5386,-1,5408);
        _SFD_CV_INIT_EML_TESTOBJECTIVE(0,1,2,"cvmetric_Sldv_proof",5410,-1,5432);
        _SFD_CV_INIT_EML_TESTOBJECTIVE(0,1,3,"cvmetric_Sldv_proof",5434,-1,5456);
        _SFD_CV_INIT_EML_TESTOBJECTIVE(0,1,4,"cvmetric_Sldv_proof",5458,-1,5480);
        _SFD_CV_INIT_EML_TESTOBJECTIVE(0,1,5,"cvmetric_Sldv_proof",5482,-1,5528);
        _SFD_CV_INIT_EML_SATURATION(0,1,0,3382,-1,3422);
        _SFD_CV_INIT_EML_IF(0,1,0,605,668,-1,736);
        _SFD_CV_INIT_EML_IF(0,1,1,739,761,-1,785);
        _SFD_CV_INIT_EML_IF(0,1,2,1103,1166,-1,1234);
        _SFD_CV_INIT_EML_IF(0,1,3,1555,1618,-1,1686);
        _SFD_CV_INIT_EML_IF(0,1,4,1883,1929,-1,1981);
        _SFD_CV_INIT_EML_IF(0,1,5,3154,3195,-1,3242);
        _SFD_CV_INIT_EML_IF(0,1,6,3504,3542,-1,3585);
        _SFD_CV_INIT_EML_IF(0,1,7,3751,3773,-1,3800);
        _SFD_CV_INIT_EML_IF(0,1,8,5592,5601,5622,5652);
        _SFD_CV_INIT_EML_IF(0,1,9,5715,5730,5747,5772);
        _SFD_SET_DATA_COMPILED_PROPS(0,SF_STRUCT,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_Panel_bus_io,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(1,SF_INT32,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_f_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(2,SF_STRUCT,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_Sync_From_bus_io,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(3,SF_INT32,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_f_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(4,SF_UINT8,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_e_sf_marshallOut,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(5,SF_STRUCT,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_Sync_From_bus_io,(MexInFcnForType)NULL);
        _SFD_SET_DATA_COMPILED_PROPS(6,SF_STRUCT,0,NULL,0,0,0,0.0,1.0,0,0,
          (MexFcnForType)c2_Status_bus_io,(MexInFcnForType)NULL);
      }
    } else {
      sf_debug_reset_current_state_configuration(sfGlobalDebugInstanceStruct,
        _MON_sl_after_replacement1MachineNumber_,chartInstance->chartNumber,
        chartInstance->instanceNumber);
    }
  }
}

static void chart_debug_initialize_data_addresses(SimStruct *S)
{
  if (!sim_mode_is_rtw_gen(S)) {
    SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance =
      (SFc2_MON_sl_after_replacement1InstanceStruct *)sf_get_chart_instance_ptr
      (S);
    if (ssIsFirstInitCond(S)) {
      /* do this only if simulation is starting and after we know the addresses of all data */
      {
        _SFD_SET_DATA_VALUE_PTR(0U, chartInstance->c2_Panel);
        _SFD_SET_DATA_VALUE_PTR(1U, chartInstance->c2_CMD_From_COM);
        _SFD_SET_DATA_VALUE_PTR(2U, chartInstance->c2_Sync_From);
        _SFD_SET_DATA_VALUE_PTR(3U, chartInstance->c2_Pedal);
        _SFD_SET_DATA_VALUE_PTR(4U, chartInstance->c2_Fail);
        _SFD_SET_DATA_VALUE_PTR(5U, chartInstance->c2_Sync_To);
        _SFD_SET_DATA_VALUE_PTR(6U, chartInstance->c2_Status);
      }
    }
  }
}

static const char* sf_get_instance_specialization(void)
{
  return "sGA6Dtlg90tTcviHCNVoOCG";
}

static void sf_opaque_initialize_c2_MON_sl_after_replacement1(void
  *chartInstanceVar)
{
  chart_debug_initialization(((SFc2_MON_sl_after_replacement1InstanceStruct*)
    chartInstanceVar)->S,0);
  initialize_params_c2_MON_sl_after_replacement1
    ((SFc2_MON_sl_after_replacement1InstanceStruct*) chartInstanceVar);
  initialize_c2_MON_sl_after_replacement1
    ((SFc2_MON_sl_after_replacement1InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_enable_c2_MON_sl_after_replacement1(void *chartInstanceVar)
{
  enable_c2_MON_sl_after_replacement1
    ((SFc2_MON_sl_after_replacement1InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_disable_c2_MON_sl_after_replacement1(void
  *chartInstanceVar)
{
  disable_c2_MON_sl_after_replacement1
    ((SFc2_MON_sl_after_replacement1InstanceStruct*) chartInstanceVar);
}

static void sf_opaque_gateway_c2_MON_sl_after_replacement1(void
  *chartInstanceVar)
{
  sf_gateway_c2_MON_sl_after_replacement1
    ((SFc2_MON_sl_after_replacement1InstanceStruct*) chartInstanceVar);
}

static const mxArray* sf_opaque_get_sim_state_c2_MON_sl_after_replacement1
  (SimStruct* S)
{
  return get_sim_state_c2_MON_sl_after_replacement1
    ((SFc2_MON_sl_after_replacement1InstanceStruct *)sf_get_chart_instance_ptr(S));/* raw sim ctx */
}

static void sf_opaque_set_sim_state_c2_MON_sl_after_replacement1(SimStruct* S,
  const mxArray *st)
{
  set_sim_state_c2_MON_sl_after_replacement1
    ((SFc2_MON_sl_after_replacement1InstanceStruct*)sf_get_chart_instance_ptr(S),
     st);
}

static void sf_opaque_terminate_c2_MON_sl_after_replacement1(void
  *chartInstanceVar)
{
  if (chartInstanceVar!=NULL) {
    SimStruct *S = ((SFc2_MON_sl_after_replacement1InstanceStruct*)
                    chartInstanceVar)->S;
    if (sim_mode_is_rtw_gen(S) || sim_mode_is_external(S)) {
      sf_clear_rtw_identifier(S);
      unload_MON_sl_after_replacement1_optimization_info();
    }

    finalize_c2_MON_sl_after_replacement1
      ((SFc2_MON_sl_after_replacement1InstanceStruct*) chartInstanceVar);
    utFree(chartInstanceVar);
    if (ssGetUserData(S)!= NULL) {
      sf_free_ChartRunTimeInfo(S);
    }

    ssSetUserData(S,NULL);
  }
}

static void sf_opaque_init_subchart_simstructs(void *chartInstanceVar)
{
  initSimStructsc2_MON_sl_after_replacement1
    ((SFc2_MON_sl_after_replacement1InstanceStruct*) chartInstanceVar);
}

extern unsigned int sf_machine_global_initializer_called(void);
static void mdlProcessParameters_c2_MON_sl_after_replacement1(SimStruct *S)
{
  int i;
  for (i=0;i<ssGetNumRunTimeParams(S);i++) {
    if (ssGetSFcnParamTunable(S,i)) {
      ssUpdateDlgParamAsRunTimeParam(S,i);
    }
  }

  if (sf_machine_global_initializer_called()) {
    initialize_params_c2_MON_sl_after_replacement1
      ((SFc2_MON_sl_after_replacement1InstanceStruct*)sf_get_chart_instance_ptr
       (S));
  }
}

static void mdlSetWorkWidths_c2_MON_sl_after_replacement1(SimStruct *S)
{
  /* Set overwritable ports for inplace optimization */
  ssMdlUpdateIsEmpty(S, 1);
  if (sim_mode_is_rtw_gen(S) || sim_mode_is_external(S)) {
    mxArray *infoStruct = load_MON_sl_after_replacement1_optimization_info
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
      ssSetInputPortOptimOpts(S, 4, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 5, SS_REUSABLE_AND_LOCAL);
      ssSetInputPortOptimOpts(S, 6, SS_REUSABLE_AND_LOCAL);
      sf_mark_chart_expressionable_inputs(S,sf_get_instance_specialization(),
        infoStruct,2,7);
    }

    {
      unsigned int outPortIdx;
      for (outPortIdx=1; outPortIdx<=0; ++outPortIdx) {
        ssSetOutputPortOptimizeInIR(S, outPortIdx, 1U);
      }
    }

    {
      unsigned int inPortIdx;
      for (inPortIdx=0; inPortIdx < 7; ++inPortIdx) {
        ssSetInputPortOptimizeInIR(S, inPortIdx, 1U);
      }
    }

    sf_set_rtw_dwork_info(S,sf_get_instance_specialization(),infoStruct,2);
    ssSetHasSubFunctions(S,!(chartIsInlinable));
  } else {
  }

  ssSetOptions(S,ssGetOptions(S)|SS_OPTION_WORKS_WITH_CODE_REUSE);
  ssSetChecksum0(S,(1799529076U));
  ssSetChecksum1(S,(1990716629U));
  ssSetChecksum2(S,(3688057042U));
  ssSetChecksum3(S,(1079845204U));
  ssSetmdlDerivatives(S, NULL);
  ssSetExplicitFCSSCtrl(S,1);
  ssSetStateSemanticsClassicAndSynchronous(S, true);
  ssSupportsMultipleExecInstances(S,1);
}

static void mdlRTW_c2_MON_sl_after_replacement1(SimStruct *S)
{
  if (sim_mode_is_rtw_gen(S)) {
    ssWriteRTWStrParam(S, "StateflowChartType", "Embedded MATLAB");
  }
}

static void mdlStart_c2_MON_sl_after_replacement1(SimStruct *S)
{
  SFc2_MON_sl_after_replacement1InstanceStruct *chartInstance;
  chartInstance = (SFc2_MON_sl_after_replacement1InstanceStruct *)utMalloc
    (sizeof(SFc2_MON_sl_after_replacement1InstanceStruct));
  if (chartInstance==NULL) {
    sf_mex_error_message("Could not allocate memory for chart instance.");
  }

  memset(chartInstance, 0, sizeof(SFc2_MON_sl_after_replacement1InstanceStruct));
  chartInstance->chartInfo.chartInstance = chartInstance;
  chartInstance->chartInfo.isEMLChart = 1;
  chartInstance->chartInfo.chartInitialized = 0;
  chartInstance->chartInfo.sFunctionGateway =
    sf_opaque_gateway_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.initializeChart =
    sf_opaque_initialize_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.terminateChart =
    sf_opaque_terminate_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.enableChart =
    sf_opaque_enable_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.disableChart =
    sf_opaque_disable_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.getSimState =
    sf_opaque_get_sim_state_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.setSimState =
    sf_opaque_set_sim_state_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.getSimStateInfo =
    sf_get_sim_state_info_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.zeroCrossings = NULL;
  chartInstance->chartInfo.outputs = NULL;
  chartInstance->chartInfo.derivatives = NULL;
  chartInstance->chartInfo.mdlRTW = mdlRTW_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.mdlStart = mdlStart_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.mdlSetWorkWidths =
    mdlSetWorkWidths_c2_MON_sl_after_replacement1;
  chartInstance->chartInfo.callGetHoverDataForMsg = NULL;
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
  mdl_start_c2_MON_sl_after_replacement1(chartInstance);
}

void c2_MON_sl_after_replacement1_method_dispatcher(SimStruct *S, int_T method,
  void *data)
{
  switch (method) {
   case SS_CALL_MDL_START:
    mdlStart_c2_MON_sl_after_replacement1(S);
    break;

   case SS_CALL_MDL_SET_WORK_WIDTHS:
    mdlSetWorkWidths_c2_MON_sl_after_replacement1(S);
    break;

   case SS_CALL_MDL_PROCESS_PARAMETERS:
    mdlProcessParameters_c2_MON_sl_after_replacement1(S);
    break;

   default:
    /* Unhandled method */
    sf_mex_error_message("Stateflow Internal Error:\n"
                         "Error calling c2_MON_sl_after_replacement1_method_dispatcher.\n"
                         "Can't handle method %d.\n", method);
    break;
  }
}
