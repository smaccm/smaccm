#include <smaccm_sender.h>
#include "clock_driver.h"
#include <debug.h>

void periodic_ping(const int64_t * periodic_100_ms);


static const uint32_t smaccm_tick_interval = 1000;
static const uint32_t smaccm_hyperperiod_subdivisions = 1;
static uint32_t smaccm_calendar_counter = 0;
static uint32_t smaccm_calendar_ticks = 0;

// TODO: FIX This: we need to use some system function to return the current system time.
static uint32_t smaccm_get_time_in_ms() {
  return (uint32_t)(smaccm_calendar_ticks * smaccm_tick_interval); 
}

static void smaccm_thread_calendar() {
	if ((smaccm_calendar_counter % (1000 / smaccm_tick_interval)) == 0) {
		int64_t time = smaccm_get_time_in_ms();
		periodic_ping(&time);
	}

	smaccm_calendar_counter = (smaccm_calendar_counter + 1) % smaccm_hyperperiod_subdivisions; 
	smaccm_calendar_ticks++; 
}

void timer_flih() {
	clock_irq_callback();
}

void timer_slih(const int64_t *dummy) {
	smaccm_thread_calendar(); 
}

void periodic_ping(const int64_t * periodic_100_ms) {
	debug_print("sender ping received (");
	debug_printhex32((uint32_t)*periodic_100_ms);
	debug_println(").  Writing to receivers ");
    uint32_t newValue = *periodic_100_ms + 1;
    ping_Output1(&newValue);
}

void initialize_timer(const int64_t * sender_initializer) {
	clock_init();
	clock_set_interval_in_ms(1000);
	clock_start_timer();
}
