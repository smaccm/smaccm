#include <smaccm_receiver.h>
#include <stdio.h>
#ifdef __TB_OS_CAMKES__
	#include <receiver.h>
	#include <inttypes.h>
#elif __TB_OS_ECHRONOS__
	#include <debug.h>
#endif

void ping_received(const test3__a_array_impl test_data) {
   #ifdef __TB_OS_ECHRONOS
      debug_printf("receiver: ping received (");
      debug_printhex32(test_data[0]);
      debug_printf(", ");   
      debug_printhex32(test_data[1]);
      debug_printf(", ");   
      debug_printhex32(test_data[2]);
      debug_printf(", ");   
      debug_printhex32(test_data[3]);
      debug_printf(", ");   
      debug_println(")");
   #else
      printf("receiver: ping received (%d, %d, %d, %d)\n", test_data[0], test_data[1], test_data[2], test_data[3]);
   #endif
}
