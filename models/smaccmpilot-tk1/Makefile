# This flag is used by encrypt/decrypt to determine where endian.h is found
# Would be nice to give it a better name
CFLAGS += -DODROID

# Start with the generated Makefile from AADL Trusted Build
include ${PWD}/apps/smaccmpilot-tk1/make_template/Makefile


PerfMon_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/PerfMon/src/*.c))


####### VM files

VM_shim_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM_shim/src/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
VM_shim_CFILES := \
    $(patsubst $(SOURCE_DIR)/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM_shim/src/*.c))
# VM_shim_LIBS += sel4vchan

VM_obj_CFILES   := $(patsubst $(SOURCE_DIR)/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM/src/*.c))
VM_obj_OFILES   := archive.o
VM_obj_HFILES := $(patsubst ${SOURCE_DIR}/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM/src/*.h))
VM_obj_LIBS += sel4allocman elf sel4simple sel4simple-default cpio sel4arm-vmm sel4dma usbdrivers

###### End VM files


###### CAN files

can_shim_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can_shim/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
can_shim_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can_shim/src/*.c))

spi_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/spi/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
spi_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/spi/src/*.c))

can_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
can_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can/src/*.c))

include ${PWD}/projects/DARPA-tk1/components/gpio/gpio.mk

###### End CAN


###### UART files

uart_shim_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/UART_Driver/uart_shim/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
uart_shim_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/UART_Driver/uart_shim/src/*.c))

###### End UART


###### Clock_Initializer files

clkcar_shim_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/Clock_Initializer/clkcar_shim/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
clkcar_shim_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/Clock_Initializer/clkcar_shim/src/*.c))

include ${PWD}/projects/DARPA-tk1/components/clkcar/clkcar.mk

###### Clock_Initializer UART


include ${PWD}/tools/camkes/camkes.mk


DEVICE_TREE_SRC := ${SOURCE_DIR}/linux/linux-tk1-dtb-secure
TK1_LINUX_BINARY := ${SOURCE_DIR}/linux/linux-tk1-debian

$(STAGE_DIR)/linux/linux-dtb: $(DEVICE_TREE_SRC)
	$(Q)mkdir -p $(dir $@)
	cp $(DEVICE_TREE_SRC) $@

$(STAGE_DIR)/linux/linux: $(TK1_LINUX_BINARY)
	$(Q)mkdir -p $(dir $@)
	cp $(TK1_LINUX_BINARY) $@

COMPONENTS := $(STAGE_DIR)/linux/linux $(STAGE_DIR)/linux/linux-dtb

${BUILD_DIR}/src/Virtual_Machine_inst.vm_obj/static/archive.o: ${COMPONENTS}
	$(Q)mkdir -p $(dir $@)
	${COMMON_PATH}/files_to_obj.sh $@ _cpio_archive $^

$(VM_obj_CFILES:%.c=%.o) $(COMPONENTS): $(srctree)/.config
