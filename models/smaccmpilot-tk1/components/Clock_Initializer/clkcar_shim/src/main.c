#include <platsupport/plat/serial.h>
#include <camkes.h>

int run(void)
{
    clkcar_uart_clk_init(TK1_UARTA);
    clkcar_uart_clk_init(TK1_UARTB);
    clkcar_uart_clk_init(TK1_UARTC);
    clkcar_spi_clk_init();

    printf("Clocks initialized\n");
    return 0;
}
