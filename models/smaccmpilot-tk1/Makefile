# This flag is used by encrypt/decrypt to determine big or little endian
# Would be nice to give it a better name
CFLAGS += -DODROID

# Start with the generated Makefile from AADL Trusted Build
include ${PWD}/apps/smaccmpilot-tk1/make_template/Makefile


####### VM files

VM_shim_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM_shim/src/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
VM_shim_CFILES := \
    $(patsubst $(SOURCE_DIR)/%,%,$(wildcard $(SOURCE_DIR)/components/Virtual_Machine/VM_shim/src/*.c))
# VM_shim_LIBS += sel4vchan

###### End VM files


###### CAN files

can_shim_HFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can_shim/include/*.h)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))
can_shim_CFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/CAN_Driver/can_shim/src/*.c))

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
