CFLAGS += -DODROID

# Start with the generated Makefile from AADL Trusted Build
include ${PWD}/apps/smaccmpilot/make_template/Makefile


####### VM files

include ${PWD}/apps/smaccmpilot/components/Virtual_Machine/Vchan/Vchan.mk

Virtual_Machine_LIBS := sel4vchan

VM_CFILES := $(patsubst $(SOURCE_DIR)/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM/src/*.c))
VM_OFILES := archive.o
VM_HFILES := $(patsubst ${SOURCE_DIR}/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM/src/*.h))
VM_LIBS += sel4allocman elf sel4simple sel4simple-default cpio sel4arm-vmm sel4dma usbdrivers sel4vchan

VM_shim_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM_shim/src/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
VM_shim_CFILES := \
    $(patsubst $(SOURCE_DIR)/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM_shim/src/*.c))
VM_shim_LIBS += sel4vchan

###### End VM files


###### CAN files

gpio_LIBS += platsupport
spi_LIBS += platsupport
clk_LIBS += platsupport

can_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
can_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can/src/*.c))

spi_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/spi/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
spi_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/spi/src/*.c))

clk_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/clk/include/*.h))
clk_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/clk/src/*.c))

gpio_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/gpio/include/*.h))
gpio_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/gpio/src/*.c))

can_shim_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can_shim/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
can_shim_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can_shim/src/*.c))

###### End CAN


###### UART files

uart_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/UART_Driver/uart/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
uart_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/UART_Driver/uart/src/*.c))

###### End UART


include ${PWD}/tools/camkes/camkes.mk

ifeq (${CONFIG_PLAT_EXYNOS5410},y)
ifeq (${CONFIG_VM_ROOTFS_MMCBLK0P2},y)
ROOTFS := mmcblk0p2
else ifeq (${CONFIG_VM_ROOTFS_MMCBLK1P2},y)
ROOTFS := mmcblk1p2
else
$(error Unknown root filesystem)
endif

ifeq (${CONFIG_VM_VUSB},y)
DEVICE_TREE_SRC := ${SOURCE_DIR}/linux/linux-secure-vusb-dtb
else
DEVICE_TREE_SRC := ${SOURCE_DIR}/linux/linux-secure-dtb
endif

$(STAGE_DIR)/linux/linux-dtb:
	$(Q)mkdir -p $(dir $@)
	sed "s/root=\/dev\/mmcblk1p2/root=\/dev\/${ROOTFS}/g" $(DEVICE_TREE_SRC) > $@

$(STAGE_DIR)/linux/linux:
	$(Q)mkdir -p $(dir $@)
	cp ${SOURCE_DIR}/linux/linux $@
endif

ifeq (${CONFIG_PLAT_TK1},y)
$(STAGE_DIR)/linux/linux-dtb:
	$(Q)mkdir -p $(dir $@)
	cp ${SOURCE_DIR}/linux/linux-tk1-dtb $@

$(STAGE_DIR)/linux/linux:
	$(Q)mkdir -p $(dir $@)
	cp ${SOURCE_DIR}/linux/linux-tk1 $@
endif

COMPONENTS := $(STAGE_DIR)/linux/linux $(STAGE_DIR)/linux/linux-dtb

${BUILD_DIR}/src/Virtual_Machine_inst.vm_obj/static/archive.o: ${COMPONENTS}
	$(Q)mkdir -p $(dir $@)
	${COMMON_PATH}/files_to_obj.sh $@ _cpio_archive $^

$(vm_CFILES:%.c=%.o) $(COMPONENTS): $(srctree)/.config
