CURRENT_DIR := $(dir $(abspath $(lastword ${MAKEFILE_LIST})))

Vchan_CFILES := $(wildcard ${CURRENT_DIR}/src/*.c)

Vchan_LIBS := sel4camkes sel4vchan

