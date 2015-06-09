#
# Copyright 2014, NICTA
#
# This software may be distributed and modified according to the terms of
# the GNU General Public License version 2. Note that NO WARRANTY is provided.
# See "LICENSE_GPLv2.txt" for details.
#
# @TAG(NICTA_GPL)
#

CROSS =
CC = $(CROSS)gcc
LD = $(CROSS)ld

CFLAGS=-O3 -Wall -Werror -I ../includes

FILES = sel4_vchan_testsuite
DEPS=includes/%.h

all: $(FILES)
	mv sel4_vchan_testsuite ../

sel4_vchan_testsuite: sel4_vchan_testsuite.o
	$(CC) $(CFLAGS) -o $@ sel4_vchan_testsuite.c sel4libvchan.c

clean:
	rm $(FILES)
