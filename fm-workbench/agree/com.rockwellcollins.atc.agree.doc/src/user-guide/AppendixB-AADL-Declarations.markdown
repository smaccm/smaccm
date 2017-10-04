# AADL Declarations {#sec:aadl-declarations .Appendix}

There are two kinds of declarations that are of interest for AGREE.
First, there are the AADL components that define the architecture that
is reasoned about in AGREE. Second, there are local declarations within
AGREE annex blocks. In this appendix, we will only provide a cursory
overview of the AADL declarations; for a complete overview, we recommend
the standard reference SAE Aerospace Standard AS5506B: Architecture
Analysis and Design Language and the Addison Wesley book: Model-Based
Engineering with AADL.

![Overview of AADL Components](../../media/image30.png){#fig:overview_of_aadl_components width="4.2962959317585305in"
height="2.8171806649168856in"}

*Figure courtesy of Peter Feiler: SAE AADL V2: An Overview*

AADL can be used to describe both software and the physical platform on
which it executes, as shown in @fig:overview_of_aadl_components. In the current version of
AGREE, only the application software is directly annotated for analysis;
information about the physical platform is used to structure the
analysis[^1], but currently is not annotated. Therefore, it is possible
to create AGREE annexes in *thread, thread group, process,* and *system*
components.

For each component type, AADL distinguishes between *types,
implementations,* and *instances.* In AGREE, we are primarily concerned
with *types* and *implementations*, which are shown in @fig:component_types_and_impl*.* The
component *type* defines the publicly visible interface to the
component: the inputs and outputs to the components (defined by *ports*)
as well as input *parameters,* shared memory *access*, and publicly
callable *subprograms*. For Java programmers, this is roughly analogous
to an *interface*.

![Component Types and Implementations in AADL](../../media/image31.png){#fig:component_types_and_impl width="4.608031496062992in" height="2.9722222222222223in"}

*Figure courtesy of Peter Feiler: SAE AADL V2: An Overview*

The type does not contain any of the internal structure of the
component, however. Instead, *Implementations* of a type describe the
internal structure of a component. To make this concrete, we examine a
portion of our toy model from Chapter 1 in in the code segment below. The top\_level
*system* defines two *ports:* Input, an **in** **data** **port** of type
Integer, and Output, an **out** **data** **port** of type Integer. AADL
defines three different kinds of ports: **data** **port**s, **event**
**ports**, and **event data port**s. These ports have different
semantics within AADL; data ports describe data that is periodically
updated by a source process and sampled by a destination process. Event
and event data ports cause events to be dispatched to a receiver
process, which (usually) then executes to process the event.

For AGREE, since we abstract the timing model of the architecture, all
of these port types are currently equivalent and all ports behave
(roughly) as **data** **port**s. In future versions of AGREE, these
ports will be distinguished and an accurate representation of the
different behaviors will be supported.

~~~
system top_level
    features
        Input: **in** **data** **port** Base\_Types::Integer;
        Output: **out** **data** **port** Base\_Types::Integer;
    annex agree {**
        assume "System input range" : Input < 10;
        guarantee "System output range" : Output < 50;
    **};
end top_level;

system implementation top_level.Impl
    subcomponents
        A_sub : system A ;
        B_sub : system B ;
        C_sub : system C ;
    connections
        IN_TO_A : port Input -> A_sub.Input
            {Communication_Properties::Timing => immediate;};
        A_TO_B : port A_sub.Output -> B_sub.Input
            {Communication_Properties::Timing => immediate;};
        A_TO_C : port A_sub.Output -> C_sub.Input1
            {Communication_Properties::Timing => immediate;};
        B_TO_C : port B_sub.Output -> C_sub.Input2
            {Communication_Properties::Timing => immediate;};
        C_TO_Output : port C_sub.Output -> Output
            {Communication_Properties::Timing => immediate;};
end top_level.Impl;
~~~

In the system implementation, we see the decomposition of the top\_level
system into subsystems A, B, and C, and the connections between
subcomponents and the top-level system interface. When connecting ports,
AADL supports *properties* that allow aspects of the communication over
the port to be further explained. In this model, each of the connections
are *immediate* (that is, the data transfer occurs within the same
frame); it is also possible to create a *delayed* connection, in which
the output of the sender is buffered until the next frame.

**Note 1:** By default, AGREE assumes that connections are *immediate.*
The best practice is to explicitly state whether each connection is
*immediate* or *delayed.*

**Note 2:** Currently in AGREE, the initial value of *delayed
connections* is set to the "zero value" for the type: this is 0 for
integers, 0.0 for reals, and false for Booleans. An option to change
this value will be added to future versions of the tool.

From a synchronous dataflow perspective, an immediate connection occurs
in the same time step and induces a dataflow relationship between the
sender and the receiver. For example, since A\_sub has an immediate
connection to B\_sub, B\_sub must be evaluated "after" A\_sub within the
time step. The immediate connections have to form a *partial order*;
that is, if *X* sends to *Y* through an immediate connection, then if
*Y* also sends to *X*, it cannot do so through an immediate connection.
Intuitively, if there were immediate connections in both directions, *X*
would have to be scheduled before *Y* within the frame and vice versa.

Currently AGREE only supports port-based communications. In particular,
it does not support remote-procedure-call (RPC-style) communication.
This will be revisited in the future, but for the moment, the procedure
call semantics require additional work to translate into our composition
framework.

[^1]: In the current version of AGREE, the platform is assumed to be
    synchronous, so this isn’t really true; platforms all behave
    equivalently. In future releases, we will account for the system
    architecture in terms of timing and accounting for physical
    failures.
