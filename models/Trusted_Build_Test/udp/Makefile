TARGETS := $(notdir ${SOURCE_DIR}).cdl
ADL := wrapper_i_assembly.camkes

# Subdirectory containing extra templates.
TEMPLATES := templates

VM_CONFIG := c162_twovm
VM_NUM_VM := 2

include TimeServer/TimeServer.mk
include SerialServer/SerialServer.mk
include RTC/RTC.mk
include Ethdriver/Ethdriver.mk
include UDPServer/UDPServer.mk
include HelloWorld/HelloWorld.mk
include Vchan/Vchan.mk
include PCIConfigIO/PCIConfigIO.mk
include Init/Init.mk

# We want to run C99
CFLAGS += -std=gnu99 "-DCAMKES_VM_CONFIG=${VM_CONFIG}" "-DBOOST_PP_VARIADICS" -I${SOURCE_DIR}/configurations -I${SOURCE_DIR}/../../components/VM/configurations

CAMKES_FLAGS += --cpp-flag="-DBOOST_PP_VARIADICS" --cpp-flag=-I${SOURCE_DIR}/configurations --cpp-flag=-I${SOURCE_DIR}/../../components/VM --cpp-flag="-DCAMKES_VM_CONFIG=${VM_CONFIG}" --cache-dir=${PWD}/build/camkes_cache

echo_CFILES :=  \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/echo/src/*.c)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/echo/src/plat/${PLAT}/*.c)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/echo/src/arch/${ARCH}/*.c))

echo_HFILES := \
   $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/echo/include/*.h)) \
   $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))

echo_ASMFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/echo/crt/arch-${ARCH}/crt0.S)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/echo/src/*.S)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/echo/src/arch/${ARCH}/*.S)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/echo/src/plat/${PLAT}/*.S))

udp_CFILES :=  \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/udp/src/*.c)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/udp/src/plat/${PLAT}/*.c)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/udp/src/arch/${ARCH}/*.c))

udp_HFILES := \
   $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/udp/include/*.h)) \
   $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/include/*.h))

udp_ASMFILES := \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/udp/crt/arch-${ARCH}/crt0.S)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/udp/src/*.S)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/udp/src/arch/${ARCH}/*.S)) \
    $(patsubst ${SOURCE_DIR}/%,%,$(wildcard ${SOURCE_DIR}/components/udp/src/plat/${PLAT}/*.S))


include ${PWD}/tools/camkes/camkes.mk

include ${SOURCE_DIR}/configurations/${VM_CONFIG}.makefile
