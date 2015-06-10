/*
 * Copyright 2014, NICTA
 *
 * This software may be distributed and modified according to the terms of
 * the GNU General Public License version 2. Note that NO WARRANTY is provided.
 * See "LICENSE_GPLv2.txt" for details.
 *
 * @TAG(NICTA_GPL)
 */

#include <smaccm_receiver.h>
#include <stdio.h>

void receive(const camera_vm__bbox_i * bbox_in) {
    printf("Received bounding box with the following data:\n");
    printf("left: %d\n", bbox_in->left);
    printf("right: %d\n", bbox_in->right);
    printf("top: %d\n", bbox_in->top);
    printf("bottom: %d\n", bbox_in->bottom);
}

