/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.VMSharedMem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.util.*;
import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterLinux;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.codegen.linux.LinuxUtil;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
import edu.umn.cs.crisys.tb.model.port.OutputPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
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
public class PortEmitterSimpleVMDataport implements PortEmitter, PortEmitterLinux, PortEmitterCamkes {

   public static boolean isApplicable(PortFeature pf) {

      boolean ok = true; 
      ok &= (pf instanceof InputDataPort ||
              pf instanceof OutputDataPort); 
      
      OSModel model = Util.getElementOSModel(pf);
      OSModel.OSTarget target = model.getOsTarget();

      // no fan-in!
      if (pf instanceof InputDataPort &&
          pf.getConnections().size() > 1) {
         return false;   
      }
      
      // all connections must cross VM boundary!
      for (PortConnection pc: pf.getConnections()) {
         PortFeature other;
         if (pf instanceof InputDataPort) {
            other = pc.getSourcePort();
         } else {
            other = pc.getDestPort(); 
         }
         OSModel otherModel = Util.getElementOSModel(other); 
         if (otherModel == model) {
            return false;
         }
      }
      
      return ok;
   }
   
   private OSModel model; 
   private PortFeature dataPort;

   
   public PortEmitterSimpleVMDataport(PortFeature pf) {
      this.dataPort = pf;
      this.model = Util.getElementOSModel(pf);
   }
   
   @Override
   public PortFeature getModelElement() { return dataPort; }
   
   @Override
   public void addPortPublicTypeDeclarations(Map<String, Type> typeList) { }
   @Override
   public void getWritePortCFiles(File directory) { }
   @Override
   public void getWritePortHFiles(File directory) { }
   
   public ST getTemplateST(String stName) {
      STGroupFile template = Util.createTemplate("PortEmitterSimpleVMDataport.stg");
      return template.getInstanceOf(stName); 
   }
   
   @Override
   public String getWritePortPreEntrypoint() { return ""; }
   @Override
   public String getWritePortPostEntrypoint() { return ""; }
   @Override
   public String getWritePortEventResponder() { return ""; }

   @Override
   public String getWritePortThreadInitializer() {
      return "";
   }
   
   /* 
    * #define READY "/dev/camkes_reverse_ready"
#define DONE "/dev/camkes_reverse_done"
#define SRC "/dev/camkes_reverse_src"
#define DEST "/dev/camkes_reverse_dest"

    int ready = open(READY, O_RDWR);
    int done = open(DONE, O_RDWR);
    int src = open(SRC, O_RDWR);
    int dest = open(DEST, O_RDWR);

    const ssize_t src_size = dataport_get_size(src);

    char *src_data = (char*)dataport_mmap(src);
    assert(src_data != MAP_FAILED);

    char *dest_data = (char*)dataport_mmap(dest);
    assert(dest_data != MAP_FAILED);

    * (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter#getWritePortHPrototypes()
    * 
    *
    */
   
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

   /*****************************************************************
    * 
    * Camkes functions
    * 
    *****************************************************************/
   
   @Override
   public String getCamkesAddComponentPortLevelDeclarations() {
      return "dataport " + this.getType().getCamkesName() + " " + this.getName() + "; " + System.lineSeparator(); 
   }

   @Override
   public String getCamkesAddAssemblyFileCompositionPortDeclarations() {
      return "";
   }

   @Override
   public String getCamkesAddAssemblyFileConfigDeclarations() {
      // managed by connection rather than port.
      return "";
   }

   @Override
   public String getCamkesAddAssemblyFilePortDeclarations() {
      return "";
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
   public String getLinuxAddCommonHFileDeclarations() { return ""; }

   @Override
   public String getLinuxAddMainCFileIncludes() { return ""; }

   @Override
   public String getLinuxAddMainCFileDeclarations() { return ""; }

   @Override
   public String getLinuxAddMainCFileInitializers() { return ""; }
   
   @Override
   public String getLinuxAddMainCFileDestructors() { return ""; }

   @Override
   public String getLinuxAddProcessHFileDeclarations() {
      String result = "";
      result += this.getReaderWriterImplVarExternDecl() + System.lineSeparator(); 
      return result;
   }

   @Override
   public String getLinuxAddProcessCFileIncludes() { return ""; }

   @Override
   public String getLinuxAddProcessCFileDeclarations() { return ""; }

   @Override
   public String getLinuxAddProcessCFileInitializers() {
      String result = ""; 
      result += "// Here is where we open the file for port " + this.getName() + System.lineSeparator(); 
      result += "// Here is where we memory map to the file for port " + this.getName() + System.lineSeparator();
      return result;
   }

   @Override
   public String getLinuxAddProcessCFileDestructors() {
      String result = ""; 
      result += "// Here is where we close the memory map to the file for port " + this.getName() + System.lineSeparator();
      result += "// Here is where we close the file for port " + this.getName() + System.lineSeparator(); 
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
   public ThreadImplementationNames getThreadImplementation() {
      return EmitterFactory.threadImplementation(this.dataPort.getOwner());
   }
   
   public String getBackingStoreVar() {
      return Util.getPrefix() + "_" + getName() + "_store";
   }
   
   public String getReaderWriterImplVar() {
      return Util.getPrefix() + "_" + getQualifiedName() + "_var";
   }

   public String getReaderWriterImplVarDeclInternal(String isExtern) {
      return isExtern + this.getType().getName() + " *" + this.getReaderWriterImplVar() + ";" + System.lineSeparator();
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

   
       
   public String getLockStmt() {
      return ""; 
   }

   public String getUnlockStmt() {
      return ""; 
   }

   public String getDataExpr() {
      return this.getReaderWriterImplVar();
   }

   
   public List<PortConnectionEmitter> getConnections() {
      List<PortConnectionEmitter> connections = new ArrayList<>();
      for (PortConnection pc: this.getModelElement().getConnections()) {
         connections.add(EmitterFactory.portConnection(pc));
      }
      return connections;
   }

   // yay! snacks!
}
