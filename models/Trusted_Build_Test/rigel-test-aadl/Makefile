# -*- Mode: makefile-gmake; indent-tabs-mode: t; tab-width: 2 -*-
#
# Makefile --- STM324 firmware build system.
#
# Copyright (C) 2012, Galois, Inc.
# All Rights Reserved.
#
# This software is released under the "BSD3" license.  Read the file
# "LICENSE" for more information.
#

OUT_DIR := C:\docs\svn_smaccm\architecture\Examples\rigel-test-aadl\out
CONFIG_TOOLCHAIN := cortex-m4
CONFIG_CORTEX_M4_PREFIX := arm-none-eabi-
CONFIG_BOARD := px4

include mk/arch/$(CONFIG_TOOLCHAIN).mk
include mk/board/$(CONFIG_BOARD).mk
include mk/export.mk

CFLAGS += -DIVORY_DEPLOY

all:
	arm-none-eabi-gcc -ffreestanding -c C:\docs\svn_smaccm\architecture\Examples\rigel-test-aadl\gen\Test_Impl_Instance.c -o $(OUT_DIR)Test_Impl_Instance.o $(CFLAGS) -I. -IC:\docs\svn_smaccm\architecture\Examples\rigel-test-aadl\gen 
	arm-none-eabi-gcc -ffreestanding -c src/rigel-test.c -o $(OUT_DIR)rigel-test.o $(CFLAGS) -I. -Isrc 
	arm-none-eabi-gcc -ffreestanding -c generic.debug.c -o $(OUT_DIR)generic.debug.o $(CFLAGS) -I.  
	arm-none-eabi-gcc -ffreestanding -c rtos-kochab.c -o $(OUT_DIR)rtos-kochab.o $(CFLAGS) -I.  
	arm-none-eabi-as -o $(OUT_DIR)armv7m.exception-preempt.o armv7m.exception-preempt.s $(ASFLAGS) -I.  
	arm-none-eabi-as -o $(OUT_DIR)ctxt-switch.o ctxt-switch.s $(ASFLAGS) -I.  
	arm-none-eabi-as -o $(OUT_DIR)vectable.o vectable.s $(ASFLAGS) -I.  
	arm-none-eabi-as -o $(OUT_DIR)semihost-debug.o semihost-debug.s $(ASFLAGS) -I.  
	arm-none-eabi-ld -T default.ld -o system  $(OUT_DIR)Test_Impl_Instance.o $(OUT_DIR)rigel-test.o $(OUT_DIR)generic.debug.o $(OUT_DIR)rtos-kochab.o $(OUT_DIR)armv7m.exception-preempt.o $(OUT_DIR)ctxt-switch.o $(OUT_DIR)vectable.o $(OUT_DIR)semihost-debug.o