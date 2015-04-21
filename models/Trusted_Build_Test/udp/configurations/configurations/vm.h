/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include <autoconf.h>
#include <boost/preprocessor/arithmetic.hpp>
#include <boost/preprocessor/cat.hpp>
#include <boost/preprocessor/list.hpp>
#include <boost/preprocessor/comparison.hpp>
#include <boost/preprocessor/stringize.hpp>
#include <boost/preprocessor/expand.hpp>
#include <boost/preprocessor/tuple.hpp>
#include <boost/preprocessor/control.hpp>

#define _VAR_STRINGIZE(...) #__VA_ARGS__
#define VAR_STRINGIZE(...) _VAR_STRINGIZE(__VA_ARGS__)

#define VM_CONFIGURATION_HEADER() BOOST_PP_STRINGIZE(CAMKES_VM_CONFIG.h)
#include VM_CONFIGURATION_HEADER()

/* Now define a bunch of general definitions for constructing the VM */

#define CAT BOOST_PP_CAT

/* Each VM has 1 timer assigned to it, and the serial server also uses 1 */
#define VTIMER_FIRST 1
#define VTIMER_NUM   1
#define VTIMERNUM_I(t, n) BOOST_PP_INC(BOOST_PP_ADD(VTIMER_FIRST, BOOST_PP_ADD(t, BOOST_PP_MUL(VTIMER_NUM, n))))
#define VTIMERNUM(t, n) VTIMERNUM_I(t, n)
#define VTIMER(t, n) VTIMER_I(t, n)
#define VTIMER_I(t, n) BOOST_PP_CAT(timer, VTIMERNUM(t, n))

#define VM_NUM_TIMERS BOOST_PP_ADD(VTIMER_FIRST, BOOST_PP_MUL(VTIMER_NUM, VM_NUM_GUESTS))

#define VM_NUM_TIMER_CLIENTS VM_NUM_TIMERS

/* For all the async sources on the intready endpoint the high bit
 * is set to indicate that an async event occured, and the low bits
 * indicate which async events */

/* The timer completions are also on the interrupt manager badge */
#define VM_INIT_TIMER_BADGE 134217729 /* BIT(27) | BIT(0) */

#define VM_PIC_BADGE_IRQ_0 134217730 /* BIT(27) | BIT(1) */
#define VM_PIC_BADGE_IRQ_1 134217732 /* BIT(27) | BIT(2) */
#define VM_PIC_BADGE_IRQ_2 134217736 /* BIT(27) | BIT(3) */
#define VM_PIC_BADGE_IRQ_3 134217744 /* BIT(27) | BIT(4) */
#define VM_PIC_BADGE_IRQ_4 134217760 /* BIT(27) | BIT(5) */
#define VM_PIC_BADGE_IRQ_5 134217792 /* BIT(27) | BIT(6) */
#define VM_PIC_BADGE_IRQ_6 134217856 /* BIT(27) | BIT(7) */
#define VM_PIC_BADGE_IRQ_7 134217984 /* BIT(27) | BIT(8) */
#define VM_PIC_BADGE_IRQ_8 134218240 /* BIT(27) | BIT(9) */
#define VM_PIC_BADGE_IRQ_9 134218752 /* BIT(27) | BIT(10) */
#define VM_PIC_BADGE_IRQ_10 134219776 /* BIT(27) | BIT(11) */
#define VM_PIC_BADGE_IRQ_11 134221824 /* BIT(27) | BIT(12) */
#define VM_PIC_BADGE_IRQ_12 134225920 /* BIT(27) | BIT(13) */
#define VM_PIC_BADGE_IRQ_13 134234112 /* BIT(27) | BIT(14) */
#define VM_PIC_BADGE_IRQ_14 134250496 /* BIT(27) | BIT(15) */
#define VM_PIC_BADGE_IRQ_15 134283264 /* BIT(27) | BIT(16) */

#define VM_PIC_BADGE_SERIAL_HAS_DATA 134348800 /* BIT(27) | BIT(17) */

/* First available badge for user bits */
#define VM_FIRST_BADGE_BIT 18

/* Base definition of the Init component. This gets
 * extended in the per Vm configuration */
#define VM_INIT_DEF() \
    control; \
    uses PutChar putchar; \
    uses PutChar guest_putchar; \
    uses PCIConfig pci_config; \
    uses RTC system_rtc; \
    uses ExtraRAM ram; \
    uses VMIOPorts ioports; \
    uses VMIRQs irqs; \
    uses VMPCIDevices pci_devices; \
    consumes HaveInterrupt intready; \
    uses Timer init_timer; \
    dataport Buf serial_buffer; \
    attribute string kernel_cmdline; \
    attribute string kernel_image; \
    attribute string kernel_relocs; \
    attribute string initrd_image; \
    attribute int iospace_domain; \
    attribute int guest_ram_mb; \
    /**/

/* VM and per VM componenents */
#define VM_COMP_DEF(num) \
    component Init##num vm##num; \
    component VMConfig CAT(vm##num, _config); \
    /**/

#define VM_CONNECT_DEF(num) \
    /* Connect all the components to the serial server */ \
    connection seL4RPCCall serial_vm##num(from vm##num.putchar, to serial.vm##num); \
    connection seL4RPCCall serial_guest_vm##num(from vm##num.guest_putchar, to serial.guest##num); \
    /* Connect the emulated serial input to the serial server */ \
    connection seL4ProdCon serial_input##num(from serial.CAT(guest##num,_buffer), to vm##num.serial_buffer); \
    connection seL4GlobalAsynch serial_input_ready##num(from serial.CAT(guest##num,_has_data), to vm##num.intready); \
    /* Temporarily connect the VM directly to the RTC */ \
    connection seL4RPCCall rtctest##num(from vm##num.system_rtc, to rtc.rtc); \
    /* Connect the VM to the timer server */ \
    connection seL4TimeServer CAT(pit##num,_timer)(from vm##num.init_timer, to time_server.the_timer); \
    /* Connect config space to main VM */ \
    connection seL4RPCCall pciconfig##num(from vm##num.pci_config, to pci_config.pci_config); \
    /* Connect the fake hardware devices */ \
    connection seL4ExtraRAM extra_ram##num(from vm##num.ram, to CAT(vm##num,_config).ram); \
    connection seL4VMIOPorts vm_ioports##num(from vm##num.ioports, to CAT(vm##num,_config).ioports); \
    connection seL4VMIRQs vm_irqs##num(from vm##num.irqs, to CAT(vm##num,_config).irqs); \
    connection seL4VMPCIDevices vm_pci_devices##num(from vm##num.pci_devices, to CAT(vm##num,_config).pci_devices); \
    /**/

#define VM_CONFIG_LIST(num, func, list, name) \
    BOOST_PP_EXPR_IF( \
        BOOST_PP_COMPL(BOOST_PP_LIST_IS_NIL(BOOST_PP_TUPLE_TO_LIST(CAT(list, num)()))), \
        vm##num.name = \
            VAR_STRINGIZE( \
                BOOST_PP_LIST_ENUM( \
                    BOOST_PP_LIST_TRANSFORM( \
                        func, \
                        num, \
                        BOOST_PP_TUPLE_TO_LIST(CAT(list,num)()) \
                    ) \
                ) \
            ) \
        ; \
    ) \
/**/

#ifdef CONFIG_APP_CAMKES_VM_GUEST_DMA_ONE_TO_ONE
#define VM_MAYBE_ZONE_DMA(num) vm##num.mmio = "0x8000:0x97000:12";
#else
#define VM_MAYBE_ZONE_DMA(num)
#endif

/* Generate IOSpace capabilities if using the IOMMU */
#ifdef CONFIG_APP_CAMKES_VM_GUEST_DMA_IOMMU
#define IOSPACE_OUTPUT(r, data, elem) elem
#define VM_MAYBE_IOSPACE(num) VM_CONFIG_LIST(num, IOSPACE_OUTPUT, VM_CONFIGURATION_IOSPACES_, iospaces)
#else
#define VM_MAYBE_IOSPACE(num)
#endif

#define MMIO_OUTPUT(r, data, elem) elem
#define VM_MMIO(num) VM_CONFIG_LIST(num, MMIO_OUTPUT, VM_CONFIGURATION_MMIO_, mmios)

#define VM_IRQ_OUTPUT(r, data, elem) BOOST_PP_TUPLE_ELEM(0, elem)
#define VM_IRQS(num) VM_CONFIG_LIST(num, VM_IRQ_OUTPUT, VM_PASSTHROUGH_IRQ_, irqs)

#define VM_CONFIG_DEF(num) \
    vm##num.init_timer_global_endpoint = BOOST_PP_STRINGIZE(vm##num); \
    vm##num.init_timer_badge = BOOST_PP_STRINGIZE(VM_INIT_TIMER_BADGE); \
    vm##num.init_timer_attributes = BOOST_PP_STRINGIZE(VTIMERNUM(0, num)); \
    vm##num.intready_global_endpoint = BOOST_PP_STRINGIZE(vm##num); \
    serial.CAT(guest##num,_has_data_global_endpoint) = BOOST_PP_STRINGIZE(vm##num); \
    serial.CAT(guest##num,_has_data_badge) = BOOST_PP_STRINGIZE(VM_PIC_BADGE_SERIAL_HAS_DATA); \
    vm##num.cnode_size_bits = 21; \
    vm##num.simple = true; \
    VM_IRQS(num) \
    VM_MAYBE_ZONE_DMA(num) \
    VM_MAYBE_IOSPACE(num) \
    VM_MMIO(num) \
    /**/

