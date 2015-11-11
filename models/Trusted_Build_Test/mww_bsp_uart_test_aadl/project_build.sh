#!/bin/sh

RAMSES_DEMO=/home/robs/work/phase2/ramses-demo

export RAMSES_DIR=$RAMSES_DEMO/ramses_resource

export AADL2RTOS_CONFIG_DIR=$RAMSES_DEMO/aadl2rtos_resource


java -jar $RAMSES_DEMO/ramses.jar -g rtos -i .,$RAMSES_DEMO/aadl2rtos_resource -o . -l trace -s sys.impl -m SMACCM_SYS.aadl,Data_Types.aadl,echoprompt.aadl,settableled.aadl,thread_period_1ms.aadl,uart_unbuffer.aadl,uart5_driver.aadl,thread_period_10ms.aadl,sys.aadl
