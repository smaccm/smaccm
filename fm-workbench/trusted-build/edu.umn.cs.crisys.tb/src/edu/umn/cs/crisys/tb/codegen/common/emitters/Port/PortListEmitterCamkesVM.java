package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.io.File;
import java.util.List;

import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.port.PortFeature;

public interface PortListEmitterCamkesVM {

   // Note: currently does not explicitly cover interrupts, though
   // they may be covered implicitly.
   
   public String getCamkesAddVMComponentLevelDeclarations(OSModel vm, List<PortFeature> pl); 
   public String getCamkesAddAssemblyFileVMDeclarations(OSModel vm, List<PortFeature> pl); 
   public String getCamkesAddAssemblyFileVMConfigDeclarations(OSModel vm, List<PortFeature> pl); 
   public String getCamkesAddAssemblyFileVMCompositionDeclarations(OSModel vm, List<PortFeature> pl); 

   public void getAddVMComponentFiles(OSModel vm, List<PortFeature> pl, File componentDir);
   public void getAddLinuxVMFiles(OSModel vm, List<PortFeature> pf, File linuxDir);
}
