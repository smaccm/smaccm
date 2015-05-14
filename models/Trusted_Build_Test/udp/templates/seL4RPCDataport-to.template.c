/*#
 *# Copyright 2014, NICTA
 *#
 *# This software may be distributed and modified according to the terms of
 *# the BSD 2-Clause license. Note that NO WARRANTY is provided.
 *# See "LICENSE_BSD2.txt" for details.
 *#
 *# @TAG(NICTA_BSD)
 #*/

/*- include 'seL4RPCCall-to.template.c' -*/

/*# Enumerate all the incoming interfaces #*/
/*- set dataports = [] -*/
/*- for id, c in enumerate(composition.connections) -*/
    /*- if c.to_instance.name == me.to_instance.name -*/
        /*- if c.type.name == me.type.name -*/
            /*- set client_id = configuration[c.from_instance.name].get("%s_attributes" % c.from_interface.name) -*/
            /*- set client_id = int(client_id.strip('"')) -*/
            /*- set dataport_name = c.from_interface.name + "_" + str(client_id) -*/
            /*- set dataport_type = lambda('x: "Buf" if x is None else x')
                                    (configuration[c.from_instance.name].get("%s_dataport_type" % c.from_interface.name)) -*/
            /*- set dataport_section = me.to_interface.name + "_" + str(client_id) -*/
            /*- set p = Perspective(dataport=dataport_name) -*/
            #define SHM_ALIGN (1 << 12)
            struct {
                char content[ROUND_UP_UNSAFE(sizeof(/*? dataport_type ?*/),
                    PAGE_SIZE_4K)];
            } /*? p['dataport_symbol'] ?*/
                    __attribute__((aligned(SHM_ALIGN)))
                    __attribute__((section("shared_/*? dataport_section ?*/")))
                    __attribute__((externally_visible));

            volatile /*? dataport_type ?*/ * /*? me.to_interface.name ?*/_buf_/*? client_id ?*/ =
                (volatile /*? dataport_type ?*/ *) & /*? p['dataport_symbol'] ?*/;
            /*- do dataports.append( (me.to_interface.name + "_buf_" + str(client_id), client_id) ) -*/
        /*- endif -*/
    /*- endif -*/
/*- endfor -*/

void */*? me.to_interface.name ?*/_buf(unsigned int client_id) {
    /*- if len(dataports) == 0 -*/
        return NULL;
    /*- else -*/
        switch (client_id) {
            /*- for symbol, id in dataports -*/
            case /*? id ?*/:
                return (void*)/*? symbol ?*/;
            /*- endfor -*/
        default:
            return NULL;
        }
    /*- endif -*/
}

unsigned int /*? me.to_interface.name ?*/_num_badges(void) {
    return /*? len(dataports) ?*/;
}

unsigned int /*? me.to_interface.name ?*/_enumerate_badge(unsigned int i) {
    /*- if len(dataports) == 0 -*/
        return -1;
    /*- else -*/
        switch (i) {
            /*- for symbol, id in dataports -*/
                case /*? loop.index0 ?*/:
                    return /*? id ?*/;
            /*- endfor -*/
        default:
            return -1;
        }
    /*- endif -*/
}
