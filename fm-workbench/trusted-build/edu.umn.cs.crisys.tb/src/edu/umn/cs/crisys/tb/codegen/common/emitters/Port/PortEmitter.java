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

   PortFeature getModelElement();
   
   // Called once per port/thread combination to write additional .c/.h files associated with this 
   // port.
   void getWritePortCFiles(File directory);
   void getWritePortHFiles(File directory);
   
   ///////////////////////////////////////////////////////////////////////////
   // various integration points with the .C file for a thread implementation.
   ///////////////////////////////////////////////////////////////////////////

   // to write additional prototypes for the thread header file
   String getWritePortHPrototypes();     
   
   // to write additional declarations for the port in the thread .c file
   String getWritePortDeclarations();    
   
   // to write initialization code prior to the call of the user entrypoint for
   // a dispatch.
   String getWritePortPreEntrypoint();   
   
   // to write post-processing code after a call to the user entrypoint.
   String getWritePortPostEntrypoint();  
   
   // to write dispatch code for the port (if it is dispatchable)
   String getWritePortEventResponder();  
   
   // to write initialization code for the port prior to the main thread
   // dispatch loop.
   String getWritePortThreadInitializer(); 
   
   
   //////////////////////////////////////////////////////////////////
   // A couple of string functions necessary for emitter interactions
   //////////////////////////////////////////////////////////////////
   
   String getName();
   String getQualifiedName();
   TypeNames getType();
   
}
