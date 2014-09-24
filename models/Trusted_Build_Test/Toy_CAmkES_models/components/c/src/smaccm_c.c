/**************************************************************************
  Copyright (c) 2013, 2014 Rockwell Collins and the University of Minnesota.
  Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

  Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
  including any software or models in source or binary form, as well as any drawings, specifications, 
  and documentation (collectively "the Data"), to deal in the Data without restriction, including 
  without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
  and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
  subject to the following conditions: 

  The above copyright notice and this permission notice shall be included in all copies or
  substantial portions of the Data.

  THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
  LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
  IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
  FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
  ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA. 

 **************************************************************************/


/**************************************************************************

   File: C:\docs\git_smaccm\smaccm\models\Trusted_Build_Test\Toy_CAmkES_models\components\c\src\smaccm_c.c
   Created on: 2014/09/18 19:30:44
   using Dulcimer AADL system build tool suite 

   ***AUTOGENERATED CODE: DO NOT MODIFY***

  This C file contains the implementations of the AADL primitives
  used by user-level declarations for thread c.   

  The user code runs in terms of "dispatchers", which cause 
  dispatch user-level handlers to execute.  These handlers can 
  communicate using the standard AADL primitives, which are mapped
  to C functions.

  The send/receive handlers are not thread safe; it is assumed that 
  this is handled by the CAmkES sequentialized access to the dispatch
  handler.  There is only one dispatch interface for the component
  containing all of the dispatch points.

  The read/write handlers are thread safe because the writer comes 
  through a dispatch interface but the reader is "local" on a dispatch
  interface and so contention may occur.


 **************************************************************************/


#include <smaccm_c.h>

uint32_t * smaccm_tmp_array_Output1; 
uint32_t * smaccm_tmp_used_Output1;
uint32_t smaccm_max_tmp_array_Output1; 	



void dispatch_dispatch_Input1(/* const */uint32_t Input1  ,
		uint32_t * Output1_data, 
		uint32_t * Output1_index) {
	smaccm_max_tmp_array_Output1 = 1;
	smaccm_tmp_used_Output1 = Output1_index;
	smaccm_tmp_array_Output1 = Output1_data; 
	*smaccm_tmp_used_Output1 = 0;

	eventhandler_C_Input1(Input1); 
}	




bool C_Output(/* const */uint32_t Output1) {
	if (*smaccm_tmp_used_Output1 < smaccm_max_tmp_array_Output1) {
		smaccm_tmp_array_Output1[*smaccm_tmp_used_Output1] = Output1;

		(*smaccm_tmp_used_Output1)++;
		return true;
	} else {
		return false;
	}
}



/**************************************************************************
  End of autogenerated file: C:\docs\git_smaccm\smaccm\models\Trusted_Build_Test\Toy_CAmkES_models\components\c\src\smaccm_c.c
 **************************************************************************/
