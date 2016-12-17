package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.io.File;

import edu.umn.cs.crisys.tb.TbFailure;

public interface PortEmitterCamkes extends PortEmitter {

   // Note: currently does not explicitly cover interrupts, though
   // they may be covered implicitly.
   public void getWriteCamkesPortComponents(File componentsDirectory) throws TbFailure;
   public void getWriteCamkesPortIdls(File interfacesDirectory) throws TbFailure;
   public String getCamkesAddComponentPortLevelDeclarations(); //
   public String getCamkesAddAssemblyFileCompositionPortDeclarations(); //  
   public String getCamkesAddAssemblyFileConfigDeclarations(); //
   public String getCamkesAddAssemblyFilePortDeclarations();  //
   
}
