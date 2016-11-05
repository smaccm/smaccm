package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.PortFeature;

public interface PortEmitterVxWorks extends NameEmitter {

   public abstract String vxWorksAddCommonHFileDeclarations(); 
   
   public abstract String vxWorksAddMainCFileIncludes();
   public abstract String vxWorksAddMainCFileDeclarations(); 
   public abstract String vxWorksAddMainCFileInitializers(); 
   public abstract String vxWorksAddMainCFileDestructors();
}
