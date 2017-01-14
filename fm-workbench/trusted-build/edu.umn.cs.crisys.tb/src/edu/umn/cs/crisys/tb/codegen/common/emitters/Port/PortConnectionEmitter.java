package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;

public interface PortConnectionEmitter extends NameEmitter {

   PortEmitter getSourcePort();
   PortEmitter getDestPort();

   String getName();
   String getFullyQualifiedName();
   
   int getConnectionID();

}