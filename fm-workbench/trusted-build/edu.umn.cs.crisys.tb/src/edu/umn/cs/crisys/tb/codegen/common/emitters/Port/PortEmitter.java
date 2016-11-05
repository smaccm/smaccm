package edu.umn.cs.crisys.tb.codegen.common.emitters.Port;

import java.io.File;
import java.util.List;
import java.util.Map;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.type.Type;

public interface PortEmitter extends NameEmitter {

   // typing - used to add types to the "public" types file.
   void addPortPublicTypeDeclarations(Map<String, Type> typeList);

   // used to construct lists of port features for different calls
   PortFeature getModelElement();
   
   // Called once per port
   void writePortCFiles(File directory);
   void writePortHFiles(File directory);
   
   // String emitters
   // These would be more efficient with StringBuffers or StringWriters, 
   // but I want the simplest possible interface.
   
   String writePortHPrototypes();     //
   String writePortDeclarations();    // 
   String writePortPreEntrypoint();   //  
   String writePortPostEntrypoint();  // 
   String writePortEventResponder();  //  
   String writePortThreadInitializer(); // 
   
   
   //////////////////////////////////////////////////////////////////
   // A couple of string functions necessary for emitter interactions
   //////////////////////////////////////////////////////////////////
   
   String getName();
   String getQualifiedName();
   String getIncomingWriterName(); 
   TypeNames getType(); 
   
}
