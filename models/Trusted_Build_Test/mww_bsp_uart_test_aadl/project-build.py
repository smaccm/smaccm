#
# Unpublished copyright (c) 2015 National ICT Australia (NICTA),
# ABN 62 102 206 173.  All rights reserved.
#
# The contents of this document are proprietary to NICTA and you may not
# use, copy, modify, sublicense or distribute the contents in any form
# except as permitted under the terms of a separately executed licence
# agreement with NICTA.
#
# COMMERCIAL LICENSE RIGHTS
# Agreement No.: FA8750-12-9-0179
# Contractor's Name; Rockwell Collins, Inc.
# Contractor's Address: 400 Collins Road N.E., Cedar Rapids, IA 52498
#
# By accepting delivery of the RTOS Code and Documentation, the Licensee
# agrees that the software is "commercial" computer software within the
# meaning of the applicable acquisition regulations (e.g., FAR 2.101 or
# DFARS 227.7202-3).  The terms and conditions of this License shall pertain
# to the Licensee's use and disclosure of the software, and shall supersede
# any conflicting contractual terms or conditions.
#
#

from prj import execute, SystemBuildError
import os


def run(system, configuration=None):
    return system_build(system)


def system_build(system):
    inc_path_args = ['-I%s' % i for i in system.include_paths + ['./gen', './include', './libsmaccmpilot/include', 'ivory']]
    common_flags = ['-mthumb', '-g3', '-mlittle-endian', '-mcpu=cortex-m4', '-mfloat-abi=hard', '-mfpu=fpv4-sp-d16']
    a_flags = common_flags
    c_flags = common_flags + ['-Os']

    # Compile all C files.
    c_obj_files = [os.path.join(system.output, os.path.basename(c.replace('.c', '.o'))) for c in system.c_files]

    for c, o in zip(system.c_files, c_obj_files):
        os.makedirs(os.path.dirname(o), exist_ok=True)
        execute(['arm-none-eabi-gcc', '-ffreestanding', '-c', c, '-o', o, '-Wall', '-Werror'] +
                c_flags + inc_path_args)

    # Assemble all asm files.
    asm_obj_files = [os.path.join(system.output, os.path.basename(s.replace('.s', '.o'))) for s in system.asm_files]
    for s, o in zip(system.asm_files, asm_obj_files):
        os.makedirs(os.path.dirname(o), exist_ok=True)
        execute(['arm-none-eabi-as', '-o', o, s] + a_flags + inc_path_args)

    # Perform final link
    obj_files = asm_obj_files + c_obj_files
    execute(['arm-none-eabi-ld', '-T', system.linker_script, '-o', system.output_file] + obj_files)
