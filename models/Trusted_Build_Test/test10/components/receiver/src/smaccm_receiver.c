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

   File: C:\docs\git_smaccm\smaccm\models\Trusted_Build_Test\test10\components\receiver\src\smaccm_receiver.c
   Created on: 2014/09/23 22:55:00
   using Dulcimer AADL system build tool suite 

   ***AUTOGENERATED CODE: DO NOT MODIFY***

  This C file contains the implementations of the AADL primitives
  used by user-level declarations for thread receiver.   

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


#include <smaccm_receiver.h>
#include <receiver.h>
#include <string.h>


//////////////////////////////////////////////////////
//
// Global data for event data port Input1
//
//////////////////////////////////////////////////////

uint32_t smaccm_queue_Input1 [12];
bool smaccm_queue_full_Input1  = false;
uint32_t smaccm_queue_front_Input1  = 0;
uint32_t smaccm_queue_back_Input1  = 0;

bool smaccm_queue_is_full_Input1() {
	return (smaccm_queue_front_Input1 == smaccm_queue_back_Input1) && (smaccm_queue_full_Input1);
}

bool smaccm_queue_is_empty_Input1() {
	return (smaccm_queue_front_Input1 == smaccm_queue_back_Input1) && (!smaccm_queue_full_Input1); 
}

bool smaccm_queue_read_Input1(uint32_t * Input1) {
	if (smaccm_queue_is_empty_Input1()) {
		return false;
	} else {
		*Input1 = smaccm_queue_Input1[smaccm_queue_back_Input1] ;

		smaccm_queue_back_Input1 = (smaccm_queue_back_Input1 + 1) % 12; 
		smaccm_queue_full_Input1 = false ; 
		return true;
	}
}

bool smaccm_queue_write_Input1(const uint32_t Input1) {
	if (smaccm_queue_is_full_Input1()) {
		return false;
	} else {
		smaccm_queue_Input1[smaccm_queue_front_Input1] = Input1 ;

		smaccm_queue_front_Input1 = (smaccm_queue_front_Input1 + 1) % 12; 		
		if (smaccm_queue_back_Input1 == smaccm_queue_front_Input1) { 
			smaccm_queue_full_Input1 = true ; 
		}
		return true;
	}
}

bool Input1_write_uint32_t(/* const */uint32_t arg) {
	bool result;
	smaccm_Input1_mutex_lock(); 
	result = smaccm_queue_write_Input1(arg);
	smaccm_Input1_mutex_unlock();
	return result;
}


bool receiver_read_Input1(uint32_t * arg) {
	bool result; 
	smaccm_Input1_mutex_lock(); 
	result = smaccm_queue_read_Input1(arg);
	smaccm_Input1_mutex_unlock();
	return result;
}




///////////////////////////////////////////////////////////////////////////
//
// Functions for dispatching IRQs and Periodic Events
//
///////////////////////////////////////////////////////////////////////////
bool smaccm_occurred_periodic_1000_ms;
uint32_t smaccm_time_periodic_1000_ms;

bool receiver_periodic_1000_ms_write_uint32_t(/* const */uint32_t arg) {
	smaccm_occurred_periodic_1000_ms = true;
	smaccm_time_periodic_1000_ms = arg;
	smaccm_dispatch_mutex_unlock();

	return true;
}



void dispatch_dispatch_periodic_1000_ms(/* const */uint32_t periodic_1000_ms  ) {
	periodic_ping(periodic_1000_ms); 
}	



void smaccm_dispatcher_periodic_1000_ms(/* const */uint32_t periodic_1000_ms) {

	// make the call: 
	dispatch_dispatch_periodic_1000_ms(periodic_1000_ms );
}
 
int run() {
	// initialization routines ... skipped for now.

	// initial lock to await dispatch input.
	smaccm_dispatch_mutex_lock();

	for(;;) {
		smaccm_dispatch_mutex_lock();


		// drain the queues 
		if (smaccm_occurred_periodic_1000_ms) {
			smaccm_occurred_periodic_1000_ms = false;
			smaccm_dispatcher_periodic_1000_ms(smaccm_time_periodic_1000_ms);
		}


	}
	// won't ever get here, but form must be followed
	return 0;
}



/**************************************************************************
  End of autogenerated file: C:\docs\git_smaccm\smaccm\models\Trusted_Build_Test\test10\components\receiver\src\smaccm_receiver.c
 **************************************************************************/
