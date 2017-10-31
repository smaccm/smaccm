package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.PortFeature;

public interface PortEmitterLinux extends NameEmitter {

   public abstract String getLinuxAddCommonHFileDeclarations(); //
   public abstract String getLinuxAddMainCFileIncludes();  //
   public abstract String getLinuxAddMainCFileDeclarations(); //  
   public abstract String getLinuxAddMainCFileInitializers(); //
   public abstract String getLinuxAddMainCFileDestructors(); //

   public abstract String getLinuxAddProcessHFileDeclarations(); //
   public abstract String getLinuxAddProcessCFileIncludes();     //
   public abstract String getLinuxAddProcessCFileDeclarations(); //  
   public abstract String getLinuxAddProcessCFileInitializers(); //
   public abstract String getLinuxAddProcessCFileDestructors();  //
}
