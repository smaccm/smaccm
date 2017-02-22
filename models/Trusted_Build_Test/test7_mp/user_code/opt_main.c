#include <stdio.h>
#include "tb_toplevel_types.h"

#ifdef __TB_OS_LINUX__

int main(int argc, char **argv) {

	trusted_build_main(10);
	
}

#endif