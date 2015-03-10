/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the BSD 2-Clause license. Note that NO WARRANTY is provided.
 * See "LICENSE_BSD2.txt" for details.
 *
 * @TAG(NICTA_BSD)
 */

#include <string.h>
#include <stdio.h>
#include <driver_wrapper.h>

static size_t uart_write(void *buf, size_t count)
{
	char *data = buf;

	for (size_t i = 0; i < count; ++i) {
		uart_put_char(data[i]);
	}

	return count;
}

/* At some point, we should change this interface so that the *client* kills 
  the system when a 'q' is seen; this should be trivial to add a 'kill switch'
  interface
 */
 
int run(void) {
    char c;
    char *str = "This message is sent via UART.\n";


    uart_write(str, strlen(str));

    while (1) {
		c = uart_get_char();
		pci_putchar(c); 
		/* printf("Input from UART: %c\n", c); */
		if (c == 'q') {
			break;
		}
    }

    printf("UART wrapped driver exit...\n");
    return 0;
}
