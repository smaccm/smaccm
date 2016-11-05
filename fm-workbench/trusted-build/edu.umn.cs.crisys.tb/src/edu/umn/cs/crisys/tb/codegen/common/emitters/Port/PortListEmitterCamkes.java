package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.PortFeature;

public interface PortListEmitterCamkes extends NameEmitter {

   // Emit IDL - this is problematic and probably has to be 
   // done centrally, but I left the interface here just the same.
   public abstract void camkesGenerateModelIdl(List<PortFeature> pl) ;
   public abstract String camkesAddComponentLevelDeclarations(List<PortFeature> ports); 
   public abstract String camkesAddAssemblyIdlImports(List<PortFeature> pl) ; 

}
