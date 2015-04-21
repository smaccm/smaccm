/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

/* This entire configuration file is a bit of a mess. There is various duplication
 * of information, not to mention the fact that this is all done in macros.
 * Ideally most of the logic for the VMs configuration could be moved to
 * a template and camkes configurations could be used along with some slightly
 * nicer code generation.
 *
 * When modifying this it is important to remember that some macros just expand to
 * their contents, and some macros are expected to be used in boost list expansions
 * and care must be taken to get the syntax correct else difficult to debug build
 * errors will occur. For a boost list an empty list would be defined here as
   #define EMPTY_LIST()
 * Where as a list with one element is
   #define ONE_ELEMENT() \
        element \
   )
 * And multiple elements becomes
   #define MULTI_ELEMENT() \
        element1, \
        element2, \
        element3
   )
 * Importantly note that you cannot have a trailing comma on the last element
 * of a list
 *
 * Many options have _N varients. The N here refers to which VM number the option
 * applies to. This is the mechanism by which different VMs can be given different
 * resources */

#define VM_NUM_GUESTS 2

/* Additional interfaces for the VM subcomponent. This is mostly just used
 * as a stepping stone to exporting interfaces all the way up to the
 * top level camkes spec */
#define PLAT_COMPONENT_INTERFACES() \
    provides Ethdriver ethdriver0_client0; \
    emits Notification ethdriver0_rx_ready0; \
    maybe dataport Buf ethdriver0_packet0; \
    /**/

/* Camkes definitions for defining any connections that are specific
 * to this VM configuration. In this case we are defining connections
 * for UDPServer as well as inter-vm communication
 */
#define PLAT_CONNECT_DEF() \
    /* Give ethernet driver same output as its vm */ \
    connection seL4RPCCall eth_putchar(from ethdriver0.putchar, to serial.vm0); \
    /* Export ethernet driver interface */ \
    connection ExportData export_eth_packet(from  ethdriver0.packet0, to ethdriver0_packet0); \
    connection ExportRPC export_eth_driver(from ethdriver0_client0, to ethdriver0.client0); \
    connection ExportAsynch export_eth_rx_ready(from ethdriver0.rx_ready0, to ethdriver0_rx_ready0); \
    /* Define hardware resources for ethdriver0 */ \
    connection seL4HardwareMMIO ethdrivermmio1(from ethdriver0.EthDriver, to HWEthDriver.mmio); \
    connection seL4IOAPICHardwareInterrupt hwethirq(from HWEthDriver.irq, to ethdriver0.irq); \
    /* Connect hello to the vchan component */ \
    connection seL4Asynch vchan_event(from vchan_0.vevent_sv, to hello.vevent); \
    connection seL4RPCCall hvchan(from hello.vchan_con, to vchan_0.vchan_com); \
    connection seL4SharedData hvchan_sharemem_0(from hello.share_mem, to vchan_0.share_mem); \
    /* Connect hello and vchan to the serial server (pretend to be vm1) */ \
    connection seL4RPCCall hserial(from hello.putchar, to serial.vm1); \
    connection seL4RPCCall vchanserial(from vchan_0.putchar, to serial.vm1); \
    /* Connect vm1 to the vchan component */ \
    connection seL4RPCCall vchan_1(from vm1.vchan_con, to vchan_0.vchan_com); \
    connection seL4SharedData vchan_sharemem_1(from vm1.share_mem, to vchan_0.share_mem); \
    connection seL4Asynch vchan_event_init_1(from vchan_0.vevent_cl, to vm1.vevent); \
    /* Also connect vm0 to the vchan component to get around compilation problems */ \
    connection seL4RPCCall vchan(from vm0.vchan_con, to vchan_0.vchan_com); \
    connection seL4SharedData vchan_sharemem(from vm0.share_mem, to vchan_0.share_mem); \
    connection seL4Asynch vchan_event_init(from vchan_0.vevent_cl, to vm0.vevent); \
    /**/

/* All our guests use the same kernel image, rootfs and cmdline */
#define C162_KERNEL_IMAGE "bzimage"
#define C162_ROOTFS "rootfs.cpio"
#define VM_GUEST_CMDLINE "console=ttyS0,115200 console=tty0 root=/dev/mem i8042.nokbd=y i8042.nomux=y i8042.noaux=y io_delay=udelay noisapnp pci=nomsi"

/* camkes definitions that will get placed in the configuration section
 * of the camkes assembly. Most of the definitions here are to do with
 * the udp server and vm to vm communication that is specific to this
 * configuration. The most important option being set for each VMM is the
   simple_untypedX_pool = Y
 * This gives Y untypeds each of size 2^X to th VMM. Multiple definitions
 * with different X can be given for each VMM. This memory will be used
 * for almost all allocations (including Linux guest memory) by the VMM.
 * The exception is that physical frames will be preferentially allocated
 * from the EXTRA_GUEST_RAM regions instead. Because this configuration
 * has an EXTRA_GUEST_RAM_REGION we make do with relatively small ammounts
 * of untyped memory
 */
#define PLAT_CONFIG_DEF() \
    vm0.simple_untyped24_pool = 2; \
    vm1.simple_untyped24_pool = 2; \
    vm0.guest_ram_mb = 80; \
    vm1.guest_ram_mb = 80; \
    HWEthDriver.mmio_attributes = "0xf1b80000:0x80000"; \
    HWEthDriver.irq_attributes = "16,1,1"; \
    ethdriver0.simple = true; \
    ethdriver0.cnode_size_bits = 12; \
    ethdriver0.iospaces = "0x12:0x1:0x0:0"; \
    ethdriver0.simple_untyped20_pool = 2; \
    vm0.kernel_cmdline = VM_GUEST_CMDLINE; \
    vm0.kernel_image = C162_KERNEL_IMAGE; \
    vm0.kernel_relocs = C162_KERNEL_IMAGE; \
    vm0.initrd_image = C162_ROOTFS; \
    vm0.iospace_domain = 0x0f; \
    vm1.kernel_cmdline = VM_GUEST_CMDLINE; \
    vm1.kernel_image = C162_KERNEL_IMAGE; \
    vm1.kernel_relocs = C162_KERNEL_IMAGE; \
    vm1.initrd_image = C162_ROOTFS; \
    vm1.iospace_domain = 0x10; \
    vm0_config.ram = [ [ 0x21000000,24 ] , [ 0x22000000 , 25 ] , [ 0x24000000 , 24 ] ]; \
    vm1_config.ram = [ [ 0x27000000,24 ] , [ 0x28000000 , 25 ] , [ 0x2A000000 , 24 ] ]; \
    vm0_config.ioports = [ {"start":0xd040, "end":0xd05f, "pci_device":1, "name":"Ethernet"} \
    ]; \
    vm1_config.ioports = [ \
        {"start":0x2f8, "end":0x2ff, "pci_device":0, "name":"COM2 Serial Port"}, \
        {"start":0x2e8, "end":0x2ef, "pci_device":0, "name":"COM4 Serial Port"}, \
        {"start":0x162e, "end":0x162f, "pci_device":0, "name":"PLD Registers"}, \
        {"start":0x378, "end":0x37f, "pci_device":0, "name":"PLD Discrete I/O"}, \
        {"start":0x3f0, "end":0x3f7, "pci_device":0, "name":"PLD Extended Discrete I/O"}, \
        {"start":0x160E, "end":0x160F, "pci_device":0, "name":"CANbus 1 Two address"}, \
        {"start":0x1680, "end":0x16A0, "pci_device":0, "name":"CANbus 1 Multi address"}, \
        {"start":0x161E, "end":0x161F, "pci_device":0, "name":"CANbus 2 Two address"}, \
        {"start":0x16C0, "end":0x16E0, "pci_device":0, "name":"CANbus 2 Multi address"}, \
        {"start":0xe000, "end":0xe01f, "pci_device":1, "name":"Some device"} \
    ]; \
    vm0_config.irqs = [ \
        {"name":"Ethernet", "source":17, "level_trig":1, "active_low":1, "dest":11}, \
        {"name":"USB", "source":23, "level_trig":1, "active_low":1, "dest":14} \
    ]; \
    vm1_config.irqs = [ \
        {"name":"I2C", "source":18, "level_trig":1, "active_low":1, "dest":12}, \
        {"name":"Serial", "source":3, "level_trig":0, "active_low":0, "dest":3} \
    ]; \
    vm0_config.pci_devices_iospace = 1; \
    vm1_config.pci_devices_iospace = 1; \
    vm0_config.pci_devices = [ \
        {"name":"Ethernet", \
            "bus":1, "dev":0, "fun":1, \
            "irq":"Ethernet", \
            "memory":[ \
                {"paddr":0xf1a80000, "size":0x80000, "page_bits":12}, \
                {"paddr":0xf1c08000, "size":0x4000, "page_bits":12}, \
            ], \
        }, \
        {"name":"USB", \
            "bus":0, "dev":0x1d, "fun":0, \
            "irq":"USB", \
            "memory":[ \
                {"paddr":0xf2c07000, "size":0x400, "page_bits":12}, \
            ], \
        }, \
    ]; \
    vm1_config.pci_devices = [ \
        {"name":"I2C", \
            "bus":0, "dev":0x1f, "fun":3, \
            "irq":"I2C", \
            "memory":[ \
                {"paddr":0xf2c05000, "size":0x100, "page_bits":12}, \
            ], \
        }, \
    ]; \
    /**/

#define VM_INIT_COMPONENT() \
    component Init0 { \
        include "vmm/vchan_sharemem.h"; \
        uses VchanInterface vchan_con; \
        consumes VchanEvent vevent; \
        dataport vchan_headers_t share_mem; \
        VM_INIT_DEF() \
    } \
    component Init1 { \
        include "vmm/vchan_sharemem.h"; \
        uses VchanInterface vchan_con; \
        consumes VchanEvent vevent; \
        dataport vchan_headers_t share_mem; \
        VM_INIT_DEF() \
    } \
    /**/

#define VCHAN_COMPONENT_DEF() \
    static camkes_vchan_con_t vchan_camkes_component = { \
    .connect = &vchan_con_new_connection, \
    .disconnect = &vchan_con_rem_connection, \
    .get_buf = &vchan_con_get_buf, \
    .status = &vchan_con_status,\
    .alert_status = &vchan_con_alert_status, \
    .reg_callback = &vevent_reg_callback, \
    .alert = &vchan_con_ping, \
    .component_dom_num = 0, \
    }; \
    /**/

#define VCHAN_COMPONENT_INIT_MEM() \
    vchan_camkes_component.data_buf = (void *) share_mem; \
    init_camkes_vchan(&vchan_camkes_component); \
    /**/

#define VM_ASYNC_DEVICE_BADGES_0() \
    /**/

#define VM_ASYNC_DEVICE_BADGES_1() \
    /**/

#define VM_INIT_SOURCE_DEFS() \
    /**/

#define VM_DEVICE_INIT_FN_0() \
    /**/

#define VM_DEVICE_INIT_FN_1() \
    /**/

#define PLAT_COMPONENT_DEF() \
    component Vchan vchan_0; \
    component HelloWorld hello; \
    component Ethdriver ethdriver0; \
    component HWEthDriver HWEthDriver; \
    /**/

#define VM_NUM_ETHDRIVERS 1

#define VM_ETHDRIVER_IOSPACE_0() 0x12
#define VM_ETHDRIVER_PCI_BDF_0() ( (1 << 8) | (0 << 3) | 0)

#define VM_ETHDRIVER_NUM_CLIENTS 1
#define VM_ETHDRIVER_CLIENTS_0() ( \
    (06, 00, 00, 12, 13, 14) \
    ) \
    /**/

