package edu.umn.cs.crisys.tb.codegen.common.emitters;

import java.io.File;

public interface ThreadEmitter extends NameEmitter {

   // 
   // MWW: Right now, the .stg file is mostly in control; it may be better to 
   // revisit this later, to leave more under the control of the Java code.
   //    
   String stgWritePortListIncludes(); 
   String stgWritePortListDeclarations();
   String stgWritePortListPreEntrypoint(); 
   String stgWritePortListPostEntrypoint();
   String stgWritePortListDispatchLoopEventResponder();
   
   void GenerateCFiles(File directory); 
}
