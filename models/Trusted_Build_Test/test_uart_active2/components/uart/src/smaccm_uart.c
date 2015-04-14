#include <uart.h>

bool input_write_uart__packet_i(const uart__packet_i * input) {
  int r = uart_write(input->uart_num, input->datum);
  if (r < 0) {
    printf("Error from uart_write, return code: %d\n", r);
  }
  return (r >= 0);
}
