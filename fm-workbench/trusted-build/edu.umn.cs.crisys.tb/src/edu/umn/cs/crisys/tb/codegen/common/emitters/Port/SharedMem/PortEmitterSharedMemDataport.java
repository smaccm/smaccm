/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.SharedMem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.util.*;
import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterEChronos;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterLinux;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterVxWorks;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.codegen.linux.LinuxUtil;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
import edu.umn.cs.crisys.tb.model.type.RecordType;
import edu.umn.cs.crisys.tb.model.type.Type;

/**
 * @author whalen
 * 
 * Note: lots of different ways of structuring this code; For the 
 * moment I'm going to put all the OS-level naming code in the 
 * DataPortEmitter.
 * 
 * TODO: 
 *    - different implementation depending on in-proc vs. out-of-proc
 *    - implement shared memory for out-of-proc.
 *    - implement proc mechanisms as well as top-level mechanism
 *
 */
public class PortEmitterSharedMemDataport implements PortEmitter, PortEmitterLinux, PortEmitterSharedMem {

   public static boolean isApplicable(PortFeature pf) {
      // 
      // Note: we can elaborate this further at will.
      // e.g., we can add checks as to the "fitness" of the ports on the 
      // other connection ends attached to this port.
      //
      // However, this is likely better done at the connection
      // level.  We really should have different connection types.
      // 
      boolean ok = true; 
      ok &= (pf instanceof InputDataPort ||
              pf instanceof OutputDataPort); 
      OSModel model = Util.getElementOSModel(pf);
      OSModel.OSTarget target = model.getOsTarget();

      // CAmkES is not supported using this approach; it is 
      // difficult to add cross-process mutexes for Camkes.
      
      // The other OSes are almost trivial to add, but 
      // I want to get linux working as quickly as possible.
      
      ok &= (//target == OSModel.OSTarget.eChronos ||
             //target == OSModel.OSTarget.VxWorks || 
             target == OSModel.OSTarget.linux);
      
      return ok;
   }
   
   private OSModel model; 
   private PortFeature dataPort;

   
   public PortEmitterSharedMemDataport(PortFeature pf) {
      this.dataPort = pf;
      this.model = Util.getElementOSModel(pf);
   }
   
   public boolean getIsInProc() {
      ProcessImplementation srcProc = Util.getProcessImplementation(this.dataPort);
      for (PortConnection c: this.dataPort.getConnections()) {
         ProcessImplementation destProc ; 
         if (this.dataPort instanceof OutputDataPort) {
            destProc = Util.getProcessImplementation(c.getDestPort());
         } else {
            destProc = Util.getProcessImplementation(c.getSourcePort());
         }
         if (srcProc != destProc) {
            return false;
         }
      }
      return true;
   }
   
   @Override
   public PortFeature getModelElement() { return dataPort; }
   
   public String getSharedMemoryDataType() {
      return "tb_shmem_" + this.getType().getName(); 
   }

   @Override
   
   // Note: for atomic types, we probably don't need the mutex, and therefore
   // we do not need the type definition.  But for now, we will keep it 
   // uniform.

   public void addPortPublicTypeDeclarations(Map<String, Type> typeList) {
      String typeName = getSharedMemoryDataType();
      RecordType shmemType = new RecordType();
      shmemType.addField("mtx", LinuxUtil.mutexType);
      shmemType.addField("data", this.getModelElement().getType());
      typeList.put(typeName, shmemType);
   }



   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortCFiles(java.io.File)
    */
   @Override
   public void getWritePortCFiles(File directory) {
      // No-op for data ports
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortHFiles(java.io.File)
    */
   @Override
   public void getWritePortHFiles(File directory) {
      // No-op for data ports
   }


   
   public ST getTemplateST(String stName) {
      STGroupFile template = Util.createTemplate("PortEmitterSharedMemDataport.stg");
      return template.getInstanceOf(stName); 
   }
   
   

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortPreEntrypoint(int)
    */
   @Override
   public String getWritePortPreEntrypoint() {
      // no entrypoint initializer code for RPC dataports
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortPostEntrypoint(int)
    */
   @Override
   public String getWritePortPostEntrypoint() {
      // no entrypoint destructor code for RPC dataports
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortEventResponder(int)
    */
   @Override
   public String getWritePortEventResponder() {
      // no event responders for data ports; they do not cause dispatch
      return "";
   }

   public String writeOptPortThreadInitializerPrototype(String v) {
      String result = ""; 
      ExternalHandler eh = this.getModelElement().getInitializeEntrypointSourceText(); 
      if (eh != null) {
         result += eh.getHandlerName() + "(" + v + ");" + System.lineSeparator();
      }
      return result;
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortThreadInitializer(int)
    */
   @Override
   public String getWritePortThreadInitializer() {
      return writeOptPortThreadInitializerPrototype("");
   }
   
   @Override
   public String getWritePortHPrototypes() {
      String result = ""; 
      
      ST st; 
      PortFeature p = getModelElement();
      if (p instanceof OutputDataPort) {
         st = getTemplateST("writePortWriterPrototype");         
      } else if (p instanceof InputDataPort) {
         st = getTemplateST("writePortReaderPrototype");         
      } else {
         throw new TbException("Error: writePortHPrototypes: port " + this.getName() + " is not a data port.");
      }
      st.add("port", this);
      result += st.render();
      result += writeOptPortThreadInitializerPrototype("void");
      
      return result;
   }

   @Override
   public String getWritePortDeclarations() {
      ST st; 
      PortFeature p = getModelElement();
      if (p instanceof OutputDataPort) {
         st = getTemplateST("componentLocalWriterDecl");   
      } else if (p instanceof InputDataPort) {
         st = getTemplateST("componentLocalReaderDecl");         
      } else {
         throw new TbException("Error: writePortDeclarations: port " + this.getName() + " is not a data port.");
      }
      st.add("port", this);
      return st.render();
   }

   /************************************************************
    * 
    * Linux-specific functions (implementing RPCEventDataPortCamkes)
    * 
    ************************************************************/

   public String addCommonHFileDeclarations() {
      String result = ""; 
      return result;
   }
   
   @Override
   public String getLinuxAddCommonHFileDeclarations() {
      String result = ""; 
      if (this.getModelElement() instanceof InputPort) {
         // Out-of-proc shared memory is delared at the OS-level.
         if (!getIsInProc()) {
            result += this.getReaderWriterImplVarExternDecl(); 
         }
         result += addCommonHFileDeclarations();
      } 
      return result;
   }

   @Override
   public String getLinuxAddMainCFileIncludes() {
      return "";
   }

   @Override
   public String getLinuxAddMainCFileDeclarations() {
      String toReturn = ""; 
      return toReturn;
   }
   
   public String getInitializers() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         ST st = this.getTemplateST("portInitializer");
         st.add("port", this);
         toReturn += st.render();
      }
      ExternalHandler initializer = 
            this.getModelElement().getInitializeEntrypointSourceText();
      if (initializer != null) {
         toReturn += initializer.getHandlerName() + "(); " + System.lineSeparator();
      }      
      return toReturn;
   }

   @Override
   public String getLinuxAddMainCFileInitializers() {
      String toReturn = ""; 
      if (!this.getIsInProc()) {
         toReturn = getInitializers();
      }
      return toReturn;
   }

   public String getDestructors() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         ST st = this.getTemplateST("portDestructor");
         st.add("port", this);
         toReturn += st.render();
      }
      return toReturn;
   }
   
   @Override
   public String getLinuxAddMainCFileDestructors() {
      String toReturn = ""; 
      if (!this.getIsInProc()) {
         toReturn += getDestructors(); 
      }
      return toReturn;
   }

   @Override
   public String getLinuxAddProcessHFileDeclarations() {
      String result = "";
      if (getIsInProc()) {
         result += this.getReaderWriterImplVarExternDecl() + System.lineSeparator(); 
      }
      return result;
   }

   @Override
   public String getLinuxAddProcessCFileIncludes() {
      // TODO Auto-generated method stub
      return "";
   }

   @Override
   public String getLinuxAddProcessCFileDeclarations() {
      // TODO Auto-generated method stub
      return "";
   }

   @Override
   public String getLinuxAddProcessCFileInitializers() {
      String result = "";
      if (this.getIsInProc()) {
         result += this.getInitializers();
      }
      return result;
   }

   @Override
   public String getLinuxAddProcessCFileDestructors() {
      String result = "";
      if (this.getIsInProc()) {
         result += this.getDestructors();
      }
      return result;
   }
   
   /*******************************************************
    * 
    * Functions for StringTemplate callbacks for all OSes; 
    * these are 
    * dependent on the functions used by the StringTemplate.
    * 
    * Note: I could move these into the OS-specific data port
    * classes, but I have chosen to centralize them to help 
    * ensure consistency between names.
    * 
    * For all dataports, this is: 
    *    getName()
    *    getQualifiedName()
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
    *    getConnections
    */

   public String getBackingStoreVar() {
      return Util.getPrefix() + "_" + getName() + "_store";
   }
   
   @Override
   public String getReaderWriterImplVar() {
      return Util.getPrefix() + "_" + getQualifiedName() + "_var";
   }

   public String getReaderWriterImplVarDeclInternal(String isExtern) {
      return isExtern + this.getSharedMemoryDataType() + " *" + this.getReaderWriterImplVar() + ";" + System.lineSeparator();
   }
   
   public String getReaderWriterImplVarDecl() {
      return this.getReaderWriterImplVarDeclInternal("");
   }
   
   public String getReaderWriterImplVarExternDecl() {
      return this.getReaderWriterImplVarDeclInternal("extern ");
   }
   
   public String getName() {
      return getModelElement().getName();
   }
      
   @Override
   public String getQualifiedName() {
      return getModelElement().getQualifiedName();
   }

   public TypeNames getType() { 
      return EmitterFactory.type(this.getModelElement().getType()); 
   }

   public String writeType() {
      return "_write" + getModelElement().getType().getCType().typeString();
   }

   public String getLpcPortWriterName() {
      return Util.getPrefix_() + getModelElement().getQualifiedName() + 
            writeType();
   }

   // local reader/writer name does not have to be compatible with any CAmkES stuff.
   public String getLocalReaderWriterName(String readWrite) {
      PortFeature dp = getModelElement();
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
      return "(" + getReaderWriterImplVar() + "->mtx)";
    }
       
   public String getLockStmt() {
      if (model.getOsTarget() == OSModel.OSTarget.linux) {
         return "tb_mutex_lock(&" + getMutex() + ");" + System.lineSeparator();
      } else {
         throw new TbException("Error: getLockStmt: OS " + model.getOsTarget() + " is not a known OS target.");
      }
   }

   public String getUnlockStmt() {
      if (model.getOsTarget() == OSModel.OSTarget.linux) {
         return "tb_mutex_unlock(&" + getMutex() + ")" + System.lineSeparator();
      } else {
         throw new TbException("Error: getunlockStmt: OS " + model.getOsTarget() + " is not a known OS target.");
      }
   }

   public String getDataExpr() {
      return "(" + this.getReaderWriterImplVar() + "->data)";
   }

   public String getMutexExpr() {
      return this.getReaderWriterImplVar() + "->mtx";
   }
   
   public List<PortConnectionEmitter> getConnections() {
      List<PortConnectionEmitter> connections = new ArrayList<>();
      for (PortConnection pc: this.getModelElement().getConnections()) {
         connections.add(EmitterFactory.portConnection(pc));
      }
      return connections;
   }
   
}
