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

   File: C:\docs\git_smaccm\smaccm\models\Trusted_Build_Test\test13\components\sender\src\smaccm_sender.c
   Created on: 2014/09/24 00:18:36
   using Dulcimer AADL system build tool suite 

   ***AUTOGENERATED CODE: DO NOT MODIFY***

  This C file contains the implementations of the AADL primitives
  used by user-level declarations for thread sender.   

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


#include <smaccm_sender.h>
#include <sender.h>
#include <string.h>


uint32_t * smaccm_tmp_used_Output1;
uint32_t smaccm_max_tmp_array_Output1; 	


///////////////////////////////////////////////////////////////////////////
//
// Functions for dispatching IRQs and Periodic Events
//
///////////////////////////////////////////////////////////////////////////
bool smaccm_occurred_periodic_100_ms;
uint32_t smaccm_time_periodic_100_ms;

bool sender_periodic_100_ms_write_uint32_t(/* const */uint32_t arg) {
	smaccm_occurred_periodic_100_ms = true;
	smaccm_time_periodic_100_ms = arg;
	smaccm_dispatch_mutex_unlock();

	return true;
}



void dispatch_dispatch_periodic_100_ms(/* const */uint32_t periodic_100_ms  ,
		uint32_t * Output1_index) {
	smaccm_max_tmp_array_Output1 = 1;
	smaccm_tmp_used_Output1 = Output1_index;
	 
	*smaccm_tmp_used_Output1 = 0;

	periodic_ping(periodic_100_ms); 

}	



bool ping_Output1() {
	if (*smaccm_tmp_used_Output1 < smaccm_max_tmp_array_Output1) {
		(*smaccm_tmp_used_Output1)++;
		return true;
	} else {
		return false;
	}
}


void smaccm_dispatcher_periodic_100_ms(/* const */uint32_t periodic_100_ms) {
	uint32_t Output1_index ;


	// make the call: 
	dispatch_dispatch_periodic_100_ms(periodic_100_ms , 
				&Output1_index );
	// invoke any resulting `send' operations
	uint32_t smaccm_it; 
	for (smaccm_it = 0; smaccm_it < Output1_index; smaccm_it++) {
	   receiver_inst_dispatch_Input1();	
	}

}
 
int run() {
	// initialization routines ... skipped for now.

	// initial lock to await dispatch input.
	smaccm_dispatch_mutex_lock();

	for(;;) {
		smaccm_dispatch_mutex_lock();


		// drain the queues 
		if (smaccm_occurred_periodic_100_ms) {
			smaccm_occurred_periodic_100_ms = false;
			smaccm_dispatcher_periodic_100_ms(smaccm_time_periodic_100_ms);
		}


	}
	// won't ever get here, but form must be followed
	return 0;
}



/**************************************************************************
  End of autogenerated file: C:\docs\git_smaccm\smaccm\models\Trusted_Build_Test\test13\components\sender\src\smaccm_sender.c
 **************************************************************************/
