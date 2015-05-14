PingTwins Trusted Build Test
============================

TESTED WITH: qemu arm

This application will start one active thread that is executed every 100ms; 
there are also two passive threads. The former thread is named ping, and the 
latter two are named pong1 and pong2. The ping thread has two input ports 
two output ports. The pong1 and pong2 threads each have an input and output 
port. Each input port of ping is wired to a different output port of either
pong1 or pong2, and each output port of ping is wired to a different input 
port of pong1 or pong2. When the ping thread is activated it will take one
of four actions:
1) it will ping pong1 then pong2
2) it will ping pong2 then pong1
3) it will ping pong1
4) it will ping pong2

When either pong1 or pong2 receives a ping it will send a pong back to the 
ping thread. One should be able to follow these interactions via output 
messages. In the output that follows "o1" corresponds to the output attached
to pong1's input and "o2" corresponds to the output attached to pong2's input,
"In1" corresponds to the input of pong1 and "In2" corresponds to the input of
pong2, and "i1" corresponds to one input on ping and "i2" corresponds to the 
other input on ping:

Ping o1.
Ping o2.
In1: Ping recv'd, sending pong.
In2: Ping recv'd, sending pong.
Pong recv'd on i1
Pong recv'd on i2
Ping o1.
In1: Ping recv'd, sending pong.
Pong recv'd on i1
Ping o2.
In2: Ping recv'd, sending pong.
Pong recv'd on i2
Ping o2.
In2: Ping recv'd, sending pong.
Pong recv'd on i2
Ping o1.
Ping o2.
In1: Ping recv'd, sending pong.
In2: Ping recv'd, sending pong.
Pong recv'd on i1
Pong recv'd on i2
Ping o1.
In1: Ping recv'd, sending pong.
Pong recv'd on i1
Ping o1.
In1: Ping recv'd, sending pong.
Pong recv'd on i1
Ping o1.
Ping o2.
In1: Ping recv'd, sending pong.
In2: Ping recv'd, sending pong.
Pong recv'd on i1
Pong recv'd on i2
Ping o1.
Ping o2.
In1: Ping recv'd, sending pong.
In2: Ping recv'd, sending pong.
Pong recv'd on i1
Pong recv'd on i2
Ping o1.
Ping o2.
In1: Ping recv'd, sending pong.
In2: Ping recv'd, sending pong.
Pong recv'd on i1
Pong recv'd on i2
Ping o2.
In2: Ping recv'd, sending pong.
Pong recv'd on i2
