#include <smaccm_receiver.h>
#include <stdio.h>
#ifdef __TB_OS_CAMKES__
	#include <receiver.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif

void ping_received(const struct A_Struct *test_data) {
   #ifdef __TB_OS_ECHRONOS__
	   debug_printf("receiver ping received; eChronos printing floats as hex values (");
       debug_printhex32(test_data->field1);
       debug_printf(", ");
       debug_printhex32(test_data->field2);
       debug_println(")");
   #else
       printf("receiver ping received (%f, %f)\n", test_data->field1, test_data->field2);
   #endif
}
