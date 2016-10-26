/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection;

import java.io.File;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.util.*;
import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;

/**
 * @author whalen
 * 
 * Note: lots of different ways of structuring this code; For the 
 * moment I'm going to put all the OS-level naming code in the 
 * DataPortEmitter.
 *
 */
public class RPCDataPortEmitter implements PortEmitter {

   
   private PortFeature dataPort;

   public PortFeature getAst() { return dataPort; }
   
   public RPCDataPortEmitter(PortFeature pf) {
      this.dataPort = pf;
   }
   
   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writeCommonCFiles(java.io.File)
    */
   @Override
   public void writeCommonCFiles(File directory) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writeCommonHFiles(java.io.File)
    */
   @Override
   public void writeCommonHFiles(File directory) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writeProcessCFiles(java.io.File)
    */
   @Override
   public void writeProcessCFiles(File directory) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writeProcessHFiles(java.io.File)
    */
   @Override
   public void writeProcessHFiles(File directory) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writeThreadCFiles(java.io.File, java.util.List)
    */
   @Override
   public void writeThreadCFiles(File directory, List<PortFeature> pl) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writeThreadHFiles(java.io.File, java.util.List)
    */
   @Override
   public void writeThreadHFiles(File directory, List<PortFeature> pl) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortCFiles(java.io.File)
    */
   @Override
   public void writePortCFiles(File directory) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortHFiles(java.io.File)
    */
   @Override
   public void writePortHFiles(File directory) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writeThreadIncludes(java.io.File, java.io.File, java.io.File, java.util.List)
    */
   @Override
   public String writeThreadCIncludes(File commonHFileDir, File processHFileDir, File threadHFileDir,
         List<PortFeature> pl) {
      // No-op for data ports
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writeThreadDeclarations(java.util.List)
    * 
    */
   @Override
   public String writeThreadDeclarations(List<PortFeature> pl) {
      // No-op for data ports
      return "";
   }

   protected STGroupFile createTemplate() {
      STGroupFile template = Util.createTemplate("RPCDataportEmitter.stg");
      return template;
   }
   
   

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortPreEntrypoint(int)
    */
   @Override
   public String writePortPreEntrypoint(int indent) {
      // no entrypoint initializer code for RPC dataports
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortPostEntrypoint(int)
    */
   @Override
   public String writePortPostEntrypoint(int indent) {
      // no entrypoint destructor code for RPC dataports
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortEventResponder(int)
    */
   @Override
   public String writePortEventResponder(int indent) {
      // no event responders for data ports; they do not cause dispatch
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortThreadInitializer(int)
    */
   @Override
   public String writePortThreadInitializer(int indent) {
      // RPC Data ports do not have to initialize anything.
      return "";
   }

   @Override
   public String writePortHPrototypes() {
      STGroupFile template = Util.createTemplate("RPCDataportEmitter.stg");
      ST st; 
      PortFeature p = getAst();
      if (p instanceof OutputDataPort) {
         st = template.getInstanceOf("writePortWriterPrototype");         
      } else if (p instanceof InputDataPort) {
         st = template.getInstanceOf("writePortReaderPrototype");         
      } else {
         throw new TbException("Error: writePortHPrototypes: port " + this.getName() + " is not a data port.");
      }
      st.add("port", this);
      return st.render();
   }

   @Override
   public String writePortDeclarations() {
      STGroupFile template = Util.createTemplate("RPCDataportEmitter.stg");
      ST st; 
      PortFeature p = getAst();
      if (p instanceof OutputDataPort) {
         st = template.getInstanceOf("componentLocalReaderDecl");   
      } else if (p instanceof InputDataPort) {
         st = template.getInstanceOf("componentRemoteWriterDecl");         
      } else {
         throw new TbException("Error: writePortDeclarations: port " + this.getName() + " is not a data port.");
      }
      st.add("port", this);
      return st.render();
   }

   @Override
   public String getQualifiedName() {
      return getAst().getQualifiedName();
   }


   /*******************************************************
    * 
    * Functions for StringTemplate callbacks; these are 
    * dependent on the functions used by the StringTemplate.
    * 
    * Note: I could move these into the OS-specific data port
    * classes, but I have chosen to centralize them to help 
    * ensure consistency between names.
    * 
    * For all dataports, this is: 
    *    getName()
    *    getType()
    *    
    * For incoming dataports, this is:
    *    getIncomingWriterName()    <-- OS-specific
    *    getLocalReaderName()
    *    getLockStmt()              <-- OS-specific
    *    getUnlockStmt()            <-- OS-specific
    *    getReaderWriterImplVar()
    *    getReaderWriterImplVarDecl()
    * 
    * For outgoing dataports, this is:
    *    getOutgoingWriterName() <-- OS-specific, tied to incomingWriterName for eChronos and VxWorks
    *    
    */
   
   public String getName() {
      return getAst().getName();
   }
   
   public TypeNames getType() { 
      return new TypeNames(this.getAst().getType()); 
   }

   public String writeType() {
      return "_write" + getAst().getType().getCType().typeString();
   }
   public String getLpcPortWriterName() {
      return Util.getPrefix_() + getAst().getQualifiedName() + 
            writeType();
   }

   // middleware functions; these must be compatible with the OS.
   public String getIncomingPortWriterName() {
      PortFeature dp = getAst();
      String prefix = Util.getPrefix_();
      
      OSModel model = Util.getElementOSModel(dp);
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         return dp.getName() + writeType() ;
      } else if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
         return getLpcPortWriterName();
      } else if (model.getOsTarget() == OSModel.OSTarget.VxWorks) {
         return getLpcPortWriterName();
      } else {
         throw new TbException("Error: getIncomingPortWriterName: OS " + model.getOsTarget() + " is not a known OS target.");
      }
   }

   // local reader/writer name does not have to be compatible with any CAmkES stuff.
   public String getLocalReaderWriterName(String readWrite) {
      PortFeature dp = getAst();
      if (dp.getCommprimFnNameOpt() != null) {
         return dp.getCommprimFnNameOpt();
      } else {
         String result = Util.getPrefix_() +
            dp.getOwner().getNormalizedName() + "_" + readWrite + "_" + dp.getName();
         return result;
      }
   }   
   
   public String getLocalReaderName() {
      return getLocalReaderWriterName("read");
   }
   
   public String getLocalWriterName() {
      return getLocalReaderWriterName("write");
   }   

   
   public String getMutex() {
      return (Util.getPrefix_() + getAst().getOwner().getNormalizedName() + "_" + getAst().getName() + "_mut").toLowerCase();
    }
    
    public String getEChronosMutexConst() {
       return (ModelNames.getEChronosPrefix() + "_MUTEX_ID_" + getMutex()).toUpperCase();
    }
   
   // getOutgoingWriterName()
   public String getLockStmt() {
      OSModel model = Util.getElementOSModel(getAst());
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         return getMutex() + "_lock();" ;
      } else if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
         return "rtos_mutex_lock(" + getEChronosMutexConst() + ");";
      } else if (model.getOsTarget() == OSModel.OSTarget.VxWorks) {
         return "semTake(" + getMutex() + ", WAIT_FOREVER);";
      } else {
         throw new TbException("Error: getLockStmt: OS " + model.getOsTarget() + " is not a known OS target.");
      }
   }

   public String getUnlockStmt() {
      OSModel model = Util.getElementOSModel(getAst());
      if (model.getOsTarget() == OSModel.OSTarget.CAmkES) {
         return getMutex() + "_unlock();" ;
      } else if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
         return "rtos_mutex_unlock(" + getEChronosMutexConst() + ");";
      } else if (model.getOsTarget() == OSModel.OSTarget.VxWorks) {
         return "semGive(" + getMutex() + ");";
      } else {
         throw new TbException("Error: getunlockStmt: OS " + model.getOsTarget() + " is not a known OS target.");
      }
   }

}
