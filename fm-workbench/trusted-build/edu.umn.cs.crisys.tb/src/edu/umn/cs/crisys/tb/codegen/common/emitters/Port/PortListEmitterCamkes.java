package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.io.File;
import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;

public interface PortListEmitterCamkes extends NameEmitter {

   // Emit IDL - this is problematic and probably has to be 
   // done centrally, but I left the interface here just the same.
   public abstract void camkesGenerateModelIdl(
         File interfacesDir, 
         OSModel mdl, 
         List<PortFeature> pl) ;

   public abstract String camkesAddComponentLevelDeclarations(
         ThreadImplementation ti,
         List<PortFeature> ports); 

   public abstract String camkesAddAssemblyIdlImports(
         OSModel mdl, 
         List<PortFeature> pl) ; 

}
