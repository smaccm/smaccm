#include <smaccm_uart_thread.h>
#include <uart_thread.h>

bool input_write_test_uart__uart_packet_i(const test_uart__uart_packet_i * input) {
  int r = uart_write(input->uart_num, input->charmander);
  if (r < 0) {
    printf("Error during uart sending, code: %d\n", r);
  }
  return (r >= 0);
}
