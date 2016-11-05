package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.io.File;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.Type;

public interface PortListEmitter extends NameEmitter {

   
   
   // Any functions with port lists are essentially "static" methods; 
   // they write something for all ports of a particular kind at a 
   // certain level of the generation hierarchy. 
   // Lists are derived by checking class equality on the PortEmitters
   // for all emitters at the level of hierarchy.
   
   // File emitters
   // Called once per OS target
   void writeCommonCFiles(File directory, List<PortFeature> pl);
   void writeCommonHFiles(File directory, List<PortFeature> pl);
   
   // Called once per process
   void writeProcessCFiles(File directory, List<PortFeature> pl); 
   void writeProcessHFiles(File directory, List<PortFeature> pl);
   
   // Called once per thread
   void writeThreadCFiles(File directory, List<PortFeature> pl); 
   void writeThreadHFiles(File directory, List<PortFeature> pl);
   
   
   String writeThreadCIncludes(File commonHFileDir, 
                              File processHFileDir,
                              File threadHFileDir, 
                              List<PortFeature> pl); 
   
}
