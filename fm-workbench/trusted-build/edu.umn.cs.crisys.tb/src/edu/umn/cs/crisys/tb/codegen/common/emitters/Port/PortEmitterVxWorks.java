package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.PortFeature;

public interface PortEmitterVxWorks extends NameEmitter {

   public abstract String getVxWorksAddCommonHFileDeclarations(); 
   public abstract String getVxWorksAddMainCFileIncludes();  
   public abstract String getVxWorksAddMainCFileDeclarations();   
   public abstract String getVxWorksAddMainCFileInitializers(); 
   public abstract String getVxWorksAddMainCFileDestructors(); 
}
