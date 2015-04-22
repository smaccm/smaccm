/*#
 *# Copyright 2014, NICTA
 *#
 *# This software may be distributed and modified according to the terms of
 *# the BSD 2-Clause license. Note that NO WARRANTY is provided.
 *# See "LICENSE_BSD2.txt" for details.
 *#
 *# @TAG(NICTA_BSD)
 #*/

/*- include 'seL4RPCCall-from.template.c' -*/

/*- set client_id = configuration[me.from_instance.name].get("%s_attributes" % me.from_interface.name) -*/
/*- set client_id = int(client_id.strip('"')) -*/
/*- set dataport_name = me.from_interface.name + "_" + str(client_id) -*/
/*- set dataport_type = lambda('x: "Buf" if x is None else x')
                        (configuration[me.from_instance.name].get("%s_dataport_type" % me.from_interface.name)) -*/

/*- set dataport_section = me.from_interface.name + "_" + str(client_id) -*/

/*# Create a fake instance in the assembly #*/
/*- set port = lambda('x: AST.Port(type = x)')(str(dataport_type)) -*/
/*- set dataport = lambda('x, y: AST.Dataport(x, y)')(port, dataport_name) -*/

/*- do me.to_instance.type.dataports.append(dataport) -*/
/*- do me.from_instance.type.dataports.append(dataport) -*/

/*- set connection_type = lambda('x: AST.Connector(name=x, from_type="Dataport", to_type="Dataport")')(dataport_name) -*/
/*- set connection = lambda('con_type, con_name, from_inst, from_inter, to_inst, to_inter: \
                             AST.Connection(con_type, con_name, from_inst, from_inter, to_inst, to_inter)')
                     (connection_type, dataport_name, me.from_instance, dataport, me.to_instance, dataport) -*/
/*- do composition.connections.append(connection) -*/

/*- set p = Perspective(dataport=dataport_name) -*/
#define SHM_ALIGN (1 << 12)
struct {
    char content[ROUND_UP_UNSAFE(sizeof(/*? dataport_type ?*/),
        PAGE_SIZE_4K)];
} /*? p['dataport_symbol'] ?*/
        __attribute__((aligned(SHM_ALIGN)))
        __attribute__((section("shared_/*? dataport_section ?*/")))
        __attribute__((externally_visible));

volatile /*? dataport_type ?*/ * /*? me.from_interface.name ?*/_buf  =
    (volatile /*? dataport_type ?*/ *) & /*? p['dataport_symbol'] ?*/;
