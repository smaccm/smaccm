
#include <clock_driver.h>


/**************************************************************************
Initialize the systick signal based on the GCD of the thread periods
 **************************************************************************/
#define SYST_CSR_REG 0xE000E010         // Basic control of SysTick: enable, clock source, interrupt, or poll
#define SYST_RVR_REG 0xE000E014         // Value to load Current Value register when 0 is reached
#define SYST_CVR_REG 0xE000E018         // The current value of the count down
#define SYST_CAV_REG 0xE000E01C         // Calibration value for count down.

#define SYST_CSR_READ() (*((volatile uint32_t*)SYST_CSR_REG))
#define SYST_CSR_WRITE(x) (*((volatile uint32_t*)SYST_CSR_REG) = x)

#define SYST_RVR_READ() (*((volatile uint32_t*)SYST_RVR_REG))
#define SYST_RVR_WRITE(x) (*((volatile uint32_t*)SYST_RVR_REG) = x)

#define SYST_CVR_READ() (*((volatile uint32_t*)SYST_CVR_REG))
#define SYST_CVR_WRITE(x) (*((volatile uint32_t*)SYST_CVR_REG) = x)
#define SYST_CAV_READ() (*((volatile uint32_t*)SYST_CAV_REG))


uint64_t ticks = 0;
uint32_t the_interval = 0;
uint64_t the_CPU_rate = 0;

void clock_init() { }

/* do not pass in a negative number here */
void clock_set_cpu_rate_in_hz(uint64_t rate) {
	the_CPU_rate = rate;
}

void clock_set_interval_in_ms(uint32_t interval) {

   the_interval = interval;
   
   /* The SysTick Calibration Value Register is a read-only register that contains
   the number of pulses for a period of 10ms, in the TENMS field, bits[23:0].
   This register also has a SKEW bit. Bit[30] == 1 indicates that the calibration
   for 10ms in the TENMS section is not exactly 10ms due to clock frequency.
   Bit[31] == 1 indicates that the reference clock is not provided.*/

   /* MWW: 2/4/2016, no longer using CAV; apparently it is unreliable 
   uint32_t cav_value = SYST_CAV_READ(); 
   uint32_t ten_ms_val = cav_value & 0x00ffffff ;   // number of cycles per 10ms
   uint32_t one_ms_val = ten_ms_val / 10;            // number of cycles per 1ms
   */
   
   /* instead compute ticks from CPU rate */
   /* constant 8000 due to sleuthing by Jason Dagit: It looks like the calculation is 
     basically the clock's hertz divided by 1000 to get it into the tens of ms magnitude, 
     plus a factor for the clock divider. In our case, it looks like we're using clock/8, 
     so effectively we need to take the clock speed in hertz and divide by 8000. */
   
   assert(the_CPU_rate); 
   uint32_t ten_ms_val = the_CPU_rate / 8000; 
   uint32_t one_ms_val = ten_ms_val / 10;
 
   uint32_t mult_of_ten_ms = interval / 10;
   uint32_t remainder_of_ten_ms = interval % 10;

   uint32_t desired_rate = (mult_of_ten_ms * ten_ms_val) + (remainder_of_ten_ms * one_ms_val) ;
   SYST_RVR_WRITE(desired_rate);
   SYST_CVR_WRITE(0);
   SYST_CSR_WRITE((1 << 1) | 1);
};

void clock_start_timer(void)
{
	ticks = 0;
}

void clock_irq_callback(void)
{
	ticks++;
}


uint64_t clock_get_time()
{
	return ticks*((uint64_t)the_interval) ;
}
