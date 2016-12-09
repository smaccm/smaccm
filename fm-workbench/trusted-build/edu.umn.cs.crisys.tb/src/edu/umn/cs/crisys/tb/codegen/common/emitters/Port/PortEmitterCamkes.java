package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.util.List;

public interface PortEmitterCamkes extends PortEmitter {

   // Note: currently does not explicitly cover interrupts, though
   // they may be covered implicitly.
   
   public String getCamkesAddComponentPortLevelDeclarations(); //
   public String getCamkesAddAssemblyFileCompositionPortDeclarations(); //  
   public String getCamkesAddAssemblyFileConfigDeclarations(); //
   public String getCamkesAddAssemblyFilePortDeclarations();  //
   
   // For VM support: these functions get called on the 'destination'
   // ports on the VM side of the boundary to contribute 
   // to the Camkes VM component and the appropriate places in the 
   // Camkes assembly.
   
   // public String getCamkesAddVMPortLevelDeclarations(); //
   
   
}
