package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

public interface PortConnectionEmitterCamkesVM {

   // For VM support: these functions get called on the 'destination'
   // ports on the VM side of the boundary to contribute 
   // to the Camkes VM component and the appropriate places in the 
   // Camkes assembly.
   
   public String getCamkesAddVMComponentPortLevelDeclarations(); //
   public String getCamkesAddAssemblyFileVMPortDeclarations(); 
   public String getCamkesAddAssemblyFileVMConfigDeclarations(); 
   public String getCamkesAddAssemblyFileVMCompositionPortDeclarations(); 
}
