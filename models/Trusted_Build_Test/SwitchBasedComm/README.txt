SwitchBasedComm Trusted Build Test
==================================

TESTED WITH: qemu arm

This application models three identical clients communicating through
a switch. The three clients are modeled using active threads with a single 
input and output port connected to unique ports on a switch. Each client
has a different periodic dispatch interval; 1 ms, 2 ms, and 4 ms, 
respectively.  The switch is modeled using a passive and has three input 
ports and three output ports. A pair of input and output ports are connected 
to each clients output and input ports.

Each time a client is initiated via a periodic dispatch a random client will 
be chosen and a message will be sent to that client via the clients output 
port which should be connected to an input port of a switch. Upon reception 
of a message, the switch checks the destination client identifier and
determines which client should receive that message. Once a target client has 
been selected, it is sent out an output port which should be connected to
the target clients input port. 

WARNING: Due to the dispatch intervals the responsiveness of the machine 
running this application may suffer.

Example output from running this application follows:

Received message on In1 to be routed to out1.
Client 1 received message from 0
Sending message from 0 to 1.
Received message on In1 to be routed to out1.
Client 1 received message from 0
Sending message from 2 to 1.
Received message on In3 to be routed to out1.
Client 1 received message from 2
Sending message from 1 to 0.
Received message on In2 to be routed to out0.
Client 0 received message from 1
Sending message from 0 to 1.
Received message on In1 to be routed to out1.
Client 1 received message from 0
Sending message from 0 to 0.
Received message on In1 to be routed to out0.
Client 0 received message from 0
Sending message from 1 to 0.
Received message on In2 to be routed to out0.
Client 0 received message from 1
Sending message from 0 to 0.
Received message on In1 to be routed to out0.
Client 0 received message from 0
Sending message from 0 to 1.
ReceiveSending message from 2 to 1.
Sending message from 1 to 1.
d message on In1 to be routed to out1.
Received message on In3 to be routed to out1.
Received message on In2 to be routed to out1.
Client 1 received message from 0
Sending message from 0 to 0.
Received message on In1 to be routed to out0.
Client 0 received message from 0
Sending message from 0 to 2.
Received message on In1 to be routed to out2.
Client 2 received message from 0
Sending message from 1 to 0.
Received message on In2 to be routed to out0.
Client 0 received message from 1
Sending message from 0 to 0.
Received message on In1 to be routed to out0.
Client 0 received message from 0
Sending message from 0 to 1.
Received message on In1 to be routed to out1.
Client 1 received message from 0
Sending message from 2 to 0.
Received message on In3 to be routed to out0.
Client 0 received message from 2
Sending message from 1 to 0.
Received message on In2 to be routed to out0.
Client 0 received message from 1
Sending message from 0 to 0.
Received message on In1 to be routed to out0.
Client 0 received message from 0
Sending message from 0 to 2.
Received message on In1 to be routed to out2.
Client 2 received message from 0