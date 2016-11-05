package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.PortFeature;

public interface PortEmitterEChronos extends PortEmitter {

   public String eChronosAddCommonHFileDeclarations(); // 
   
   public String eChronosAddPrxMutexes();  //
   public String eChronosAddPrxSemaphores();  //

   public String addTrampolines();   // 
   public String addInternalIrqs();  // 
   public String addExternalIrqs();  // 
}
