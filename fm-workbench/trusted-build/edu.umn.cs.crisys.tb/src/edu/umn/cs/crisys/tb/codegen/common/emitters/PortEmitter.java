package edu.umn.cs.crisys.tb.codegen.common.emitters;

import java.io.File;
import java.util.List;

import edu.umn.cs.crisys.tb.model.port.PortFeature;

public interface PortEmitter extends NameEmitter {

   // File emitters
   // Called once per OS target
   void writeCommonCFiles(File directory);
   void writeCommonHFiles(File directory);
   
   // Called once per process
   void writeProcessCFiles(File directory); 
   void writeProcessHFiles(File directory);
   
   // Called once per thread
   void writeThreadCFiles(File directory, List<PortFeature> pl); 
   void writeThreadHFiles(File directory, List<PortFeature> pl);
   
   // Called once per port
   void writePortCFiles(File directory);
   void writePortHFiles(File directory);
   
   // String emitters
   // These would be more efficient with StringBuffers or StringWriters, 
   // but I want the simplest possible interface.
   
   String writePortHPrototypes();
   
   String writeThreadCIncludes(File commonHFileDir, 
                              File processHFileDir,
                              File threadHFileDir, 
                              List<PortFeature> pl); 
   
   String writeThreadDeclarations(List<PortFeature> pl);
   String writePortDeclarations();
   String writePortPreEntrypoint(int indent); 
   String writePortPostEntrypoint(int indent);
   String writePortEventResponder(int indent); 
   String writePortThreadInitializer(int indent);
   
   
   ///////////////////////////////////
   // A couple of string functions..
   ///////////////////////////////////
   
   String getName();
   String getQualifiedName();
}
