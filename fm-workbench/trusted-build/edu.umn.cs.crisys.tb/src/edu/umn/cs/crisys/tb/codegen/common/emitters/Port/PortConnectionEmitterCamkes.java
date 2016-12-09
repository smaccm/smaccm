package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

public interface PortConnectionEmitterCamkes extends PortConnectionEmitter {

   // Currently this is not called from the assembly file; 
   // I suspect it should be called for each connection within the 
   // model, decoupling the components, and more notably, the ports, 
   // from the connections a little bit.  Currently the 
   // connections are constructed from the ports.
   String writeAssemblyPortConnection();
   String getOutgoingPortWriterName();
   String getOutgoingPortName();

}