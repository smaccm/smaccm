/*#
 *# Copyright 2014, NICTA
 *#
 *# This software may be distributed and modified according to the terms of
 *# the BSD 2-Clause license. Note that NO WARRANTY is provided.
 *# See "LICENSE_BSD2.txt" for details.
 *#
 *# @TAG(NICTA_BSD)
 #*/

/*- include 'seL4RPCDataport-from.template.c' -*/

/*- set is_reader = True -*/
/*- set instance = me.from_instance.name -*/
/*- set interface = me.from_interface.name -*/
/*- include 'global-endpoint.template.c' -*/
/*- set aep = pop('aep') -*/

seL4_CPtr /*? me.from_interface.name ?*/_aep(void) {
    return /*? aep ?*/;
}
