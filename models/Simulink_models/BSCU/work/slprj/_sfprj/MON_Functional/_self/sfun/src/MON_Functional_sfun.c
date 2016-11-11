/* Include files */

#include "MON_Functional_sfun.h"
#include "MON_Functional_sfun_debug_macros.h"
#include "c1_MON_Functional.h"
#include "c2_MON_Functional.h"
#include "c3_MON_Functional.h"
#include "c4_MON_Functional.h"

/* Type Definitions */

/* Named Constants */

/* Variable Declarations */

/* Variable Definitions */
uint32_T _MON_FunctionalMachineNumber_;

/* Function Declarations */

/* Function Definitions */
void MON_Functional_initializer(void)
{
}

void MON_Functional_terminator(void)
{
}

/* SFunction Glue Code */
unsigned int sf_MON_Functional_method_dispatcher(SimStruct *simstructPtr,
  unsigned int chartFileNumber, const char* specsCksum, int_T method, void *data)
{
  if (chartFileNumber==1) {
    c1_MON_Functional_method_dispatcher(simstructPtr, method, data);
    return 1;
  }

  if (chartFileNumber==2) {
    c2_MON_Functional_method_dispatcher(simstructPtr, method, data);
    return 1;
  }

  if (chartFileNumber==3) {
    c3_MON_Functional_method_dispatcher(simstructPtr, method, data);
    return 1;
  }

  if (chartFileNumber==4) {
    c4_MON_Functional_method_dispatcher(simstructPtr, method, data);
    return 1;
  }

  return 0;
}

unsigned int sf_MON_Functional_process_check_sum_call( int nlhs, mxArray * plhs[],
  int nrhs, const mxArray * prhs[] )
{

#ifdef MATLAB_MEX_FILE

  char commandName[20];
  if (nrhs<1 || !mxIsChar(prhs[0]) )
    return 0;

  /* Possible call to get the checksum */
  mxGetString(prhs[0], commandName,sizeof(commandName)/sizeof(char));
  commandName[(sizeof(commandName)/sizeof(char)-1)] = '\0';
  if (strcmp(commandName,"sf_get_check_sum"))
    return 0;
  plhs[0] = mxCreateDoubleMatrix( 1,4,mxREAL);
  if (nrhs>1 && mxIsChar(prhs[1])) {
    mxGetString(prhs[1], commandName,sizeof(commandName)/sizeof(char));
    commandName[(sizeof(commandName)/sizeof(char)-1)] = '\0';
    if (!strcmp(commandName,"machine")) {
      ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(2593782939U);
      ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(461697865U);
      ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(3329735708U);
      ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(295505109U);
    } else if (!strcmp(commandName,"exportedFcn")) {
      ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(0U);
      ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(0U);
      ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(0U);
      ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(0U);
    } else if (nrhs==3 && !strcmp(commandName,"chart")) {
      unsigned int chartFileNumber;
      chartFileNumber = (unsigned int)mxGetScalar(prhs[2]);
      switch (chartFileNumber) {
       case 1:
        {
          extern void sf_c1_MON_Functional_get_check_sum(mxArray *plhs[]);
          sf_c1_MON_Functional_get_check_sum(plhs);
          break;
        }

       case 2:
        {
          extern void sf_c2_MON_Functional_get_check_sum(mxArray *plhs[]);
          sf_c2_MON_Functional_get_check_sum(plhs);
          break;
        }

       case 3:
        {
          extern void sf_c3_MON_Functional_get_check_sum(mxArray *plhs[]);
          sf_c3_MON_Functional_get_check_sum(plhs);
          break;
        }

       case 4:
        {
          extern void sf_c4_MON_Functional_get_check_sum(mxArray *plhs[]);
          sf_c4_MON_Functional_get_check_sum(plhs);
          break;
        }

       default:
        ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(0.0);
        ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(0.0);
        ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(0.0);
        ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(0.0);
      }
    } else if (!strcmp(commandName,"target")) {
      ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(2175283100U);
      ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(1852739243U);
      ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(2327506406U);
      ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(1783100865U);
    } else {
      return 0;
    }
  } else {
    ((real_T *)mxGetPr((plhs[0])))[0] = (real_T)(1176148212U);
    ((real_T *)mxGetPr((plhs[0])))[1] = (real_T)(1435145610U);
    ((real_T *)mxGetPr((plhs[0])))[2] = (real_T)(2826289357U);
    ((real_T *)mxGetPr((plhs[0])))[3] = (real_T)(1340964612U);
  }

  return 1;

#else

  return 0;

#endif

}

unsigned int sf_MON_Functional_autoinheritance_info( int nlhs, mxArray * plhs[],
  int nrhs, const mxArray * prhs[] )
{

#ifdef MATLAB_MEX_FILE

  char commandName[32];
  char aiChksum[64];
  if (nrhs<3 || !mxIsChar(prhs[0]) )
    return 0;

  /* Possible call to get the autoinheritance_info */
  mxGetString(prhs[0], commandName,sizeof(commandName)/sizeof(char));
  commandName[(sizeof(commandName)/sizeof(char)-1)] = '\0';
  if (strcmp(commandName,"get_autoinheritance_info"))
    return 0;
  mxGetString(prhs[2], aiChksum,sizeof(aiChksum)/sizeof(char));
  aiChksum[(sizeof(aiChksum)/sizeof(char)-1)] = '\0';

  {
    unsigned int chartFileNumber;
    chartFileNumber = (unsigned int)mxGetScalar(prhs[1]);
    switch (chartFileNumber) {
     case 1:
      {
        if (strcmp(aiChksum, "zvoJrMZ2TLHvPtz6zVPShE") == 0) {
          extern mxArray *sf_c1_MON_Functional_get_autoinheritance_info(void);
          plhs[0] = sf_c1_MON_Functional_get_autoinheritance_info();
          break;
        }

        plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
        break;
      }

     case 2:
      {
        if (strcmp(aiChksum, "xK5TcgJO4zS2wC1ksyI1t") == 0) {
          extern mxArray *sf_c2_MON_Functional_get_autoinheritance_info(void);
          plhs[0] = sf_c2_MON_Functional_get_autoinheritance_info();
          break;
        }

        plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
        break;
      }

     case 3:
      {
        if (strcmp(aiChksum, "ZPdzABN1zXZIL61eh3MNZG") == 0) {
          extern mxArray *sf_c3_MON_Functional_get_autoinheritance_info(void);
          plhs[0] = sf_c3_MON_Functional_get_autoinheritance_info();
          break;
        }

        plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
        break;
      }

     case 4:
      {
        if (strcmp(aiChksum, "is2DE0mVJR8Beyt8nge1TB") == 0) {
          extern mxArray *sf_c4_MON_Functional_get_autoinheritance_info(void);
          plhs[0] = sf_c4_MON_Functional_get_autoinheritance_info();
          break;
        }

        plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
        break;
      }

     default:
      plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
    }
  }

  return 1;

#else

  return 0;

#endif

}

unsigned int sf_MON_Functional_get_eml_resolved_functions_info( int nlhs,
  mxArray * plhs[], int nrhs, const mxArray * prhs[] )
{

#ifdef MATLAB_MEX_FILE

  char commandName[64];
  if (nrhs<2 || !mxIsChar(prhs[0]))
    return 0;

  /* Possible call to get the get_eml_resolved_functions_info */
  mxGetString(prhs[0], commandName,sizeof(commandName)/sizeof(char));
  commandName[(sizeof(commandName)/sizeof(char)-1)] = '\0';
  if (strcmp(commandName,"get_eml_resolved_functions_info"))
    return 0;

  {
    unsigned int chartFileNumber;
    chartFileNumber = (unsigned int)mxGetScalar(prhs[1]);
    switch (chartFileNumber) {
     case 1:
      {
        extern const mxArray
          *sf_c1_MON_Functional_get_eml_resolved_functions_info(void);
        mxArray *persistentMxArray = (mxArray *)
          sf_c1_MON_Functional_get_eml_resolved_functions_info();
        plhs[0] = mxDuplicateArray(persistentMxArray);
        mxDestroyArray(persistentMxArray);
        break;
      }

     case 2:
      {
        extern const mxArray
          *sf_c2_MON_Functional_get_eml_resolved_functions_info(void);
        mxArray *persistentMxArray = (mxArray *)
          sf_c2_MON_Functional_get_eml_resolved_functions_info();
        plhs[0] = mxDuplicateArray(persistentMxArray);
        mxDestroyArray(persistentMxArray);
        break;
      }

     case 3:
      {
        extern const mxArray
          *sf_c3_MON_Functional_get_eml_resolved_functions_info(void);
        mxArray *persistentMxArray = (mxArray *)
          sf_c3_MON_Functional_get_eml_resolved_functions_info();
        plhs[0] = mxDuplicateArray(persistentMxArray);
        mxDestroyArray(persistentMxArray);
        break;
      }

     case 4:
      {
        extern const mxArray
          *sf_c4_MON_Functional_get_eml_resolved_functions_info(void);
        mxArray *persistentMxArray = (mxArray *)
          sf_c4_MON_Functional_get_eml_resolved_functions_info();
        plhs[0] = mxDuplicateArray(persistentMxArray);
        mxDestroyArray(persistentMxArray);
        break;
      }

     default:
      plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
    }
  }

  return 1;

#else

  return 0;

#endif

}

unsigned int sf_MON_Functional_third_party_uses_info( int nlhs, mxArray * plhs[],
  int nrhs, const mxArray * prhs[] )
{
  char commandName[64];
  char tpChksum[64];
  if (nrhs<3 || !mxIsChar(prhs[0]))
    return 0;

  /* Possible call to get the third_party_uses_info */
  mxGetString(prhs[0], commandName,sizeof(commandName)/sizeof(char));
  commandName[(sizeof(commandName)/sizeof(char)-1)] = '\0';
  mxGetString(prhs[2], tpChksum,sizeof(tpChksum)/sizeof(char));
  tpChksum[(sizeof(tpChksum)/sizeof(char)-1)] = '\0';
  if (strcmp(commandName,"get_third_party_uses_info"))
    return 0;

  {
    unsigned int chartFileNumber;
    chartFileNumber = (unsigned int)mxGetScalar(prhs[1]);
    switch (chartFileNumber) {
     case 1:
      {
        if (strcmp(tpChksum, "s7ocolEgUeBxh20iYZ4GzaB") == 0) {
          extern mxArray *sf_c1_MON_Functional_third_party_uses_info(void);
          plhs[0] = sf_c1_MON_Functional_third_party_uses_info();
          break;
        }
      }

     case 2:
      {
        if (strcmp(tpChksum, "sNEIykul4qEUgVrxwTgXqCF") == 0) {
          extern mxArray *sf_c2_MON_Functional_third_party_uses_info(void);
          plhs[0] = sf_c2_MON_Functional_third_party_uses_info();
          break;
        }
      }

     case 3:
      {
        if (strcmp(tpChksum, "sJreB9Rm60ixVYPzZZrzeLD") == 0) {
          extern mxArray *sf_c3_MON_Functional_third_party_uses_info(void);
          plhs[0] = sf_c3_MON_Functional_third_party_uses_info();
          break;
        }
      }

     case 4:
      {
        if (strcmp(tpChksum, "sCTvb8FbuXJtSAmWe2QKtm") == 0) {
          extern mxArray *sf_c4_MON_Functional_third_party_uses_info(void);
          plhs[0] = sf_c4_MON_Functional_third_party_uses_info();
          break;
        }
      }

     default:
      plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
    }
  }

  return 1;
}

unsigned int sf_MON_Functional_jit_fallback_info( int nlhs, mxArray * plhs[],
  int nrhs, const mxArray * prhs[] )
{
  char commandName[64];
  char tpChksum[64];
  if (nrhs<3 || !mxIsChar(prhs[0]))
    return 0;

  /* Possible call to get the jit_fallback_info */
  mxGetString(prhs[0], commandName,sizeof(commandName)/sizeof(char));
  commandName[(sizeof(commandName)/sizeof(char)-1)] = '\0';
  mxGetString(prhs[2], tpChksum,sizeof(tpChksum)/sizeof(char));
  tpChksum[(sizeof(tpChksum)/sizeof(char)-1)] = '\0';
  if (strcmp(commandName,"get_jit_fallback_info"))
    return 0;

  {
    unsigned int chartFileNumber;
    chartFileNumber = (unsigned int)mxGetScalar(prhs[1]);
    switch (chartFileNumber) {
     case 1:
      {
        if (strcmp(tpChksum, "s7ocolEgUeBxh20iYZ4GzaB") == 0) {
          extern mxArray *sf_c1_MON_Functional_jit_fallback_info(void);
          plhs[0] = sf_c1_MON_Functional_jit_fallback_info();
          break;
        }
      }

     case 2:
      {
        if (strcmp(tpChksum, "sNEIykul4qEUgVrxwTgXqCF") == 0) {
          extern mxArray *sf_c2_MON_Functional_jit_fallback_info(void);
          plhs[0] = sf_c2_MON_Functional_jit_fallback_info();
          break;
        }
      }

     case 3:
      {
        if (strcmp(tpChksum, "sJreB9Rm60ixVYPzZZrzeLD") == 0) {
          extern mxArray *sf_c3_MON_Functional_jit_fallback_info(void);
          plhs[0] = sf_c3_MON_Functional_jit_fallback_info();
          break;
        }
      }

     case 4:
      {
        if (strcmp(tpChksum, "sCTvb8FbuXJtSAmWe2QKtm") == 0) {
          extern mxArray *sf_c4_MON_Functional_jit_fallback_info(void);
          plhs[0] = sf_c4_MON_Functional_jit_fallback_info();
          break;
        }
      }

     default:
      plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
    }
  }

  return 1;
}

unsigned int sf_MON_Functional_updateBuildInfo_args_info( int nlhs, mxArray *
  plhs[], int nrhs, const mxArray * prhs[] )
{
  char commandName[64];
  char tpChksum[64];
  if (nrhs<3 || !mxIsChar(prhs[0]))
    return 0;

  /* Possible call to get the updateBuildInfo_args_info */
  mxGetString(prhs[0], commandName,sizeof(commandName)/sizeof(char));
  commandName[(sizeof(commandName)/sizeof(char)-1)] = '\0';
  mxGetString(prhs[2], tpChksum,sizeof(tpChksum)/sizeof(char));
  tpChksum[(sizeof(tpChksum)/sizeof(char)-1)] = '\0';
  if (strcmp(commandName,"get_updateBuildInfo_args_info"))
    return 0;

  {
    unsigned int chartFileNumber;
    chartFileNumber = (unsigned int)mxGetScalar(prhs[1]);
    switch (chartFileNumber) {
     case 1:
      {
        if (strcmp(tpChksum, "s7ocolEgUeBxh20iYZ4GzaB") == 0) {
          extern mxArray *sf_c1_MON_Functional_updateBuildInfo_args_info(void);
          plhs[0] = sf_c1_MON_Functional_updateBuildInfo_args_info();
          break;
        }
      }

     case 2:
      {
        if (strcmp(tpChksum, "sNEIykul4qEUgVrxwTgXqCF") == 0) {
          extern mxArray *sf_c2_MON_Functional_updateBuildInfo_args_info(void);
          plhs[0] = sf_c2_MON_Functional_updateBuildInfo_args_info();
          break;
        }
      }

     case 3:
      {
        if (strcmp(tpChksum, "sJreB9Rm60ixVYPzZZrzeLD") == 0) {
          extern mxArray *sf_c3_MON_Functional_updateBuildInfo_args_info(void);
          plhs[0] = sf_c3_MON_Functional_updateBuildInfo_args_info();
          break;
        }
      }

     case 4:
      {
        if (strcmp(tpChksum, "sCTvb8FbuXJtSAmWe2QKtm") == 0) {
          extern mxArray *sf_c4_MON_Functional_updateBuildInfo_args_info(void);
          plhs[0] = sf_c4_MON_Functional_updateBuildInfo_args_info();
          break;
        }
      }

     default:
      plhs[0] = mxCreateDoubleMatrix(0,0,mxREAL);
    }
  }

  return 1;
}

void MON_Functional_debug_initialize(struct SfDebugInstanceStruct* debugInstance)
{
  _MON_FunctionalMachineNumber_ = sf_debug_initialize_machine(debugInstance,
    "MON_Functional","sfun",0,4,0,0,0);
  sf_debug_set_machine_event_thresholds(debugInstance,
    _MON_FunctionalMachineNumber_,0,0);
  sf_debug_set_machine_data_thresholds(debugInstance,
    _MON_FunctionalMachineNumber_,0);
}

void MON_Functional_register_exported_symbols(SimStruct* S)
{
}

static mxArray* sRtwOptimizationInfoStruct= NULL;
typedef struct SfOptimizationInfoFlagsTag {
  boolean_T isRtwGen;
  boolean_T isModelRef;
  boolean_T isExternal;
} SfOptimizationInfoFlags;

static SfOptimizationInfoFlags sOptimizationInfoFlags;
void unload_MON_Functional_optimization_info(void);
mxArray* load_MON_Functional_optimization_info(boolean_T isRtwGen, boolean_T
  isModelRef, boolean_T isExternal)
{
  if (sOptimizationInfoFlags.isRtwGen != isRtwGen ||
      sOptimizationInfoFlags.isModelRef != isModelRef ||
      sOptimizationInfoFlags.isExternal != isExternal) {
    unload_MON_Functional_optimization_info();
  }

  sOptimizationInfoFlags.isRtwGen = isRtwGen;
  sOptimizationInfoFlags.isModelRef = isModelRef;
  sOptimizationInfoFlags.isExternal = isExternal;
  if (sRtwOptimizationInfoStruct==NULL) {
    sRtwOptimizationInfoStruct = sf_load_rtw_optimization_info("MON_Functional",
      "MON_Functional");
    mexMakeArrayPersistent(sRtwOptimizationInfoStruct);
  }

  return(sRtwOptimizationInfoStruct);
}

void unload_MON_Functional_optimization_info(void)
{
  if (sRtwOptimizationInfoStruct!=NULL) {
    mxDestroyArray(sRtwOptimizationInfoStruct);
    sRtwOptimizationInfoStruct = NULL;
  }
}
