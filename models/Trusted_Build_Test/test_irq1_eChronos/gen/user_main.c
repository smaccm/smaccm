#include <rtos-kochab.h>
#include <smaccm_decls.h>
#include <debug.h>

void
fatal(const RtosErrorId error_id)
{
    debug_print("FATAL ERROR: ");
    debug_printhex32(error_id);
    debug_println("");
    for (;;)
    {
    }
}

int
main(void)
{
	// bool result = initialize_timer();
	//if (!result) {
	//	debug_print("Unable to initialize periodic dispatcher.");
	//	fatal(-1);
	//}

    debug_println("Starting RTOS");
    rtos_start();
    /* Should never reach here, but if we do, an infinite loop is
       easier to debug than returning somewhere random. */
    for (;;) ;
}
