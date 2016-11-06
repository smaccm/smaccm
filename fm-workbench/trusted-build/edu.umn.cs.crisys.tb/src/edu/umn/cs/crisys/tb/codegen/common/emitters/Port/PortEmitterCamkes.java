package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.util.List;

public interface PortEmitterCamkes extends PortEmitter {

   // Note: currently does not explicitly cover interrupts, though
   // they may be covered implicitly.
   
   public String getAddComponentPortLevelDeclarations(); //
   public String getAddAssemblyFileCompositionPortDeclarations(); //  
   public String getAddAssemblyFileConfigDeclarations(); //
   public String getAddAssemblyFilePortDeclarations();  //
}
