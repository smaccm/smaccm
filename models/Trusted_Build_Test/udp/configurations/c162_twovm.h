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

/* If VM_CONFIGURATION_EXTRA_RAM is defined then the corresponding _N definitions
 * will be used to find ranges for extra ram. These are boost lists and contain
 * physical address and 2^n size pairs. Each range must fall within a single
 * untyped that is given to the rootserver (capdl-loader) by the kernel. The capdl-loader
 * has a configuration option that can be turned on that will print out what
 * device untypeds it has received */
#define VM_CONFIGURATION_EXTRA_RAM
#define VM_CONFIGURATION_EXTRA_RAM_0() ( \
        (0x21000000,24), \
        (0x22000000,25), \
        (0x24000000,24) \
    ) \
    /**/
#define VM_CONFIGURATION_EXTRA_RAM_1() ( \
        (0x27000000,24), \
        (0x28000000,25), \
        (0x2A000000,24) \
    ) \
    /**/

/* Passthrough IRQs are used to give a guest a direct hardware interrupt. The format here
 * is (source_irq, level_triggered, active_low, dest_irq).
 * source_irq - The interrupt index on the I/O APIC for this device. Note that seL4
 *              does not interrupt remapping of any kind and will just use the default
 * level_triggered - 1 if level triggred 0 if edge triggered. Generally PCI devices are
 *                   level triggered and old ISA devices (serial ports etc) are edge
 *                   triggered
 * active_low - 1 if triggered when low, 0 if triggered when high. Generally
 *              PCI interrupts are active low, and ISA interrupts are active high
 * dest_irq - The IRQ number on the PIC we emulate to Linux to deliver this interrupt
 *            to. Frequently the source and dest irqs will need to be different,
 *            in this case the VMM supports a way to indicate in the PCI config space
 *            virtualization where the interrupt is. See the comments on
 *            VM_GUEST_PASSTHROUGH_DEVICES
 */
#define VM_PASSTHROUGH_IRQ_0() ( \
        /* Eth1 */ \
        (17, 1, 1, 11), \
        /* Usb */ \
        (23, 1, 1, 14) \
    ) \
    /**/

#define VM_PASSTHROUGH_IRQ_1() ( \
        /* serial */ \
        (3, 0, 0, 3), \
        /* I2C */ \
        (18, 1, 1, 12) \
    ) \
    /**/

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

/* Define any IOSpaces that need be created and populated with mappings
 * in the IOMMU. Each entry here is the format
   "iospace_domain:pci_bus:pci_device:pci_fun"
 * The iospace domain needs to match the definition in
 * VM_GUEST_IOSPACE_DOMAIN_N There needs to be a definition here for
 * every PCI device given to the guest in VM_GUEST_PASSTHROUGH_DEVICES
 */
#define VM_CONFIGURATION_IOSPACES_0() ( \
    /* Eth1 */ \
    0xf:0x1:0x0:1, \
    /* USB */ \
    0xf:0x0:0x1d:0 \
    ) \
    /**/

#define VM_CONFIGURATION_IOSPACES_1() ( \
    /* I2C */ \
    0x10:0x0:0x1f:3 \
    ) \
    /**/

/* This is a list of any memory mapped IO regions that will be needed when
 * giving the guest PCI devices. When the VMM gives the guest access to
 * devices in VM_GUEST_PASSTHROUGH_DEVICES, the memory regions for the
 * bars need to be in this list. Essentially this list requests the
 * capdl-loader give the VMM these resources, which we then may or may
 * not actually give to Linux, depending on whether a device that Linux
 * uses actually needs it. Format is
   "physical_address:size:page_bits"
 * Size can be less than a page, although in practice the actual region
 * requested will be round up to a multiple of the page size.
 * page_bits is size of the frame that backs this region. This is to account
 * for seL4 potentially giving large frames for device regions that can
 * support it. In practice this doesn't happen at the moment and this
 * value should always be 12
 */
#define VM_CONFIGURATION_MMIO_0() ( \
    /* Eth1 */ \
    0xf1a80000:0x80000:12, \
    0xf1c08000:0x4000:12, \
    /* USB */ \
    0xf2c07000:0x400:12 \
    ) \
    /**/

#define VM_CONFIGURATION_MMIO_1() ( \
    /* I2C */ \
    0xf2c05000:0x100:12 \
    ) \
    /**/

/* Definitions of legacy I/O ports that should be given to each guest.
 * The format of each entry is
   (first_port, last_port, not_PCI, description)
 * the first and last ports are inclusive
 * not_PCI is an option that exists for legacy reasons and indicates
 * whether the I/O port will be found and given to the guest from
 * scanning the bars when giving passthrough devices, or whether it
 * should just be given to the guest. This means that if 1 is given
 * and port range is also in a device, then an error will occur as
 * the VMM will attempt to give the port twice to the guest.
 * Similarly if 0 is given and the port does not appear in a PCI
 * device then the range will *not* be given to Linux
 */
#define VM_CONFIGURATION_IOPORT_0() ( \
    /* Ethernet ports */ \
    (0xd040, 0xd05f, 0, "Eth1") \
    )
    /**/

#define VM_CONFIGURATION_IOPORT_1() ( \
    (0x2f8, 0x2ff, 1, "COM2 Serial Port"), \
    (0x2e8, 0x2ef, 1, "COM4 Serial Port"), \
    (0x162e, 0x162f, 1, "PLD Registers"), \
    (0x378, 0x37f, 1, "PLD Discrete I/O"), \
    (0x3f0, 0x3f7, 1, "PLD Extended Discrete I/O"), \
    (0x160E, 0x160F, 1, "CANbus 1 Two address"), \
    (0x1680, 0x16A0, 1, "CANbus 1 Multi address"), \
    (0x161E, 0x161F, 1, "CANbus 2 Two address"), \
    (0x16C0, 0x16E0, 1, "CANbus 2 Multi address"), \
    /* device */ \
    (0xe000, 0xe01f, 0, "Some device") \
    ) \
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
    /**/

/* List of pci devices that should be given as passthrough to the guest
 * Format is
   {.ven = pci_vendor_id, .dev = pci_device_id, .fun = pci_function, .irq = irq_remap}
 * The pci_function is an option argument, and is used as a rudimentary
 * way of picking when there are multiple of the same device. This assumes
 * that duplicate devices will be on the same pci bus/device and only
 * differ by function. A value of -1 means the device can appear at
 * any function
 * irq_remap is where we will tell the guest the IRQ is. This should probably
 * match at least one dest_irq in VM_PASSTHROUGH_IRQ. Can be -1 to use the
 * default *PIC* IRQ
 */
#define VM_GUEST_PASSTHROUGH_DEVICES_0() \
    {.ven = 0x8086, .dev = 0x150e, .fun = 1, .irq = 11}, /* Network */ \
    {.ven = 0x8086, .dev = 0x3b34, .fun = -1, .irq = 14}, /* USB */ \
    /**/

#define VM_GUEST_PASSTHROUGH_DEVICES_1() \
    {.ven = 0x8086, .dev = 0x3b30, .fun = -1, .irq = 12}, /* SMBus (I2C) */ \
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

