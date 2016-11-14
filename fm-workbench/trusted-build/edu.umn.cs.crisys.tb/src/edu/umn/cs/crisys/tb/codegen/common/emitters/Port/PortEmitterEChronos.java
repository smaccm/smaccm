package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.PortFeature;

public interface PortEmitterEChronos extends PortEmitter {

   public String getEChronosAddCommonHFileDeclarations(); // 
   
   public String getEChronosAddPrxMutexes();  //
   public String getEChronosAddPrxSemaphores();  //

   public String getEChronosAddTrampolines();   // 
   public String getEChronosAddInternalIrqs();  // 
   public String getEChronosAddExternalIrqs();  // 
}
