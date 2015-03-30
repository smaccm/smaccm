#!/bin/sh

DIR=test_per

# Source AADL_FILES
. $DIR/AADL_FILES

cd $DIR

RAMSES=$HOME/Galois/SMACCM/smaccm-project/source/smaccmpilot-build/phase2/ramses-demo

export RAMSES_DIR=$RAMSES/ramses_resource
export AADL2RTOS_CONFIG_DIR=$RAMSES/aadl2rtos_resource

java -jar $RAMSES/ramses.jar -g rtos -i $AADL2RTOS_CONFIG_DIR -o . -l trace -s sys.impl -m SMACCM_SYS.aadl,$AADL_LIST

