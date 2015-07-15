#include <smaccm_sender.h>
#include <sender.h>
#include <inttypes.h>
#include "clock_driver.h"

void periodic_ping(const int64_t * periodic_100_ms);


const uint32_t smaccm_tick_interval = 1000;
const uint32_t smaccm_hyperperiod_subdivisions = 1;
uint32_t smaccm_calendar_counter = 0;
uint32_t smaccm_calendar_ticks = 0;

// TODO: FIX This: we need to use some system function to return the current system time.
uint32_t smaccm_get_time_in_ms() {
  return (uint32_t)(smaccm_calendar_ticks * smaccm_tick_interval); 
}

void smaccm_thread_calendar() {
	if ((smaccm_calendar_counter % (1000 / smaccm_tick_interval)) == 0) {
		periodic_ping(smaccm_get_time_in_ms()); 
	}

	smaccm_calendar_counter = (smaccm_calendar_counter + 1) % smaccm_hyperperiod_subdivisions; 
	smaccm_calendar_ticks++; 
}

void timer_flih() {
	epit_irq_callback(); 
}

void timer_slih() {
	smaccm_thread_calendar(); 
}

void periodic_ping(const int64_t * periodic_100_ms) {
   printf("sender ping received (%" PRI64 ").  Writing to receiver \n", *periodic_100_ms);
   uint32_t newValue = periodic_100_ms + 1;
   ping_Output1(&newValue);
}

void initialize_timer() {
	epit_init();
	epit_set_interval(1000);
	epit_start_timer();
}
