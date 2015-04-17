#
# Copyright 2014, NICTA
#
# This software may be distributed and modified according to the terms of
# the GNU General Public License version 2. Note that NO WARRANTY is provided.
# See "LICENSE_GPLv2.txt" for details.
#
# @TAG(NICTA_GPL)
#

KERNEL_FILENAME := bzimage
ROOTFS_FILENAME := rootfs.cpio

ARCHIVE_DEPS := ${STAGE_DIR}/${KERNEL_FILENAME} ${STAGE_DIR}/${ROOTFS_FILENAME}

${STAGE_DIR}/${KERNEL_FILENAME}: $(SOURCE_DIR)/linux/${KERNEL_FILENAME}
	@echo "[EXTRACT-VMLINUX] $@"
	$(Q)mkdir -p $(@D)
	${PWD}/tools/elf/extract-vmlinux $< > $@

${STAGE_DIR}/${ROOTFS_FILENAME}: ${SOURCE_DIR}/linux/${ROOTFS_FILENAME}
	@echo "[CP] $@"
	@cp $< $@

${BUILD_DIR}/src/vm_vm0/static/archive.o: ${ARCHIVE_DEPS}
	$(Q)mkdir -p $(dir $@)
	@echo "[CPIO] $@"
	$(Q)${COMMON_PATH}/files_to_obj.sh $@ _cpio_archive $^
	@echo "[CPIO] done."

${BUILD_DIR}/src/vm_vm1/static/archive.o: ${ARCHIVE_DEPS}
	$(Q)mkdir -p $(dir $@)
	@echo "[CPIO] $@"
	$(Q)${COMMON_PATH}/files_to_obj.sh $@ _cpio_archive $^
	@echo "[CPIO] done."

