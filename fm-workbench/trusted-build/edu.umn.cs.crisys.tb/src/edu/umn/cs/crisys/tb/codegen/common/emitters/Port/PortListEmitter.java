package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.io.File;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.process.ProcessInstance;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstance;
import edu.umn.cs.crisys.tb.model.type.Type;

public interface PortListEmitter extends NameEmitter {
   
   // Any functions with port lists are essentially "static" methods; 
   // they write something for all ports of a particular kind at a 
   // certain level of the generation hierarchy. 
   // Lists are derived by checking class equality on the PortEmitters
   // for all emitters at the level of hierarchy.
   
   // File emitters
   // Called once per OS target
   // NB: THE OS TARGET CALLBACKS ARE CURRENTLY UNIMPLEMENTED!
   void writeCommonCFiles(OSModel model, File directory, List<PortFeature> pl);
   void writeCommonHFiles(OSModel model, File directory, List<PortFeature> pl);
   
   // Called once per process
   void writeProcessCFiles(ProcessInstance pi, File directory, List<PortFeature> pl); 
   void writeProcessHFiles(ProcessInstance pi, File directory, List<PortFeature> pl);
   
   // Called once per thread
   void writeThreadCFiles(ThreadInstance ti, File directory, List<PortFeature> ports); 
   void writeThreadHFiles(ThreadInstance ti, File directory, List<PortFeature> ports);
   
   
   // The header prototypes can be additional include files or other declarations.
   String writeThreadHPrototypes(ThreadInstance ti, List<PortFeature> pl);
   String writeThreadCIncludes(ThreadInstance ti, List<PortFeature> pl); 
   
}
