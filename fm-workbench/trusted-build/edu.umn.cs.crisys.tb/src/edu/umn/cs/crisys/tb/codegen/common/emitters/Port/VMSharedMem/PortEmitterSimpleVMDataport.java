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
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterLinux;
import edu.umn.cs.crisys.tb.codegen.common.names.ThreadImplementationNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.OSModel.OSTarget;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.port.OutputDataPort;
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
      if (Util.getElementOSModel(this.dataPort).getOsTarget() == OSTarget.linux) {
         ST st = this.getTemplateST("linuxPortInitializer");
         st.add("port", this);
         return st.render();
      }
      else 
           return "";
   }
   
   @Override
   public String getWritePortHPrototypes() {
      return getReaderWriterImplVarExternDecl(); 
   }

   @Override
   public String getWritePortDeclarations() {
      return getReaderWriterImplVarDecl(); 
   }

   /*****************************************************************
    * 
    * Camkes functions
    * 
    *****************************************************************/
   
   @Override
   public String getCamkesAddComponentPortLevelDeclarations() {
      int size = this.getType().getFrameSizeInBytes();
      return "dataport Buf(" + size + ") " + this.getName() + "; " + System.lineSeparator(); 
   }

   @Override
   public String getCamkesAddAssemblyFileCompositionPortDeclarations() {
      return "";
   }

   @Override
   public String getCamkesAddAssemblyFileConfigDeclarations() {
      ST st = this.getTemplateST("assemblyConfigPort");
      st.add("port", this);
      return st.render();
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

   public String addCommonHFileDeclarations() { return ""; }
   
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
   public String getLinuxAddProcessHFileDeclarations() { return ""; }

   @Override
   public String getLinuxAddProcessCFileIncludes() { return ""; }

   @Override
   public String getLinuxAddProcessCFileDeclarations() { return ""; }

   @Override
   public String getLinuxAddProcessCFileInitializers() {
      return "";
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

   public boolean getIsInputPort() {
      return (this.dataPort instanceof InputPort);
   }
   
   public boolean getIsOutputPort() {
      return !getIsInputPort();
   }

   
   public String getReaderWriterImplVarDeclInternal(String isExtern) {
      return isExtern + "volatile " + this.getType().getName() + " *" + this.getReaderWriterImplVar() + ";" + System.lineSeparator();
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

      
   public List<PortConnectionEmitter> getConnections() {
      List<PortConnectionEmitter> connections = new ArrayList<>();
      for (PortConnection pc: this.getModelElement().getConnections()) {
         connections.add(EmitterFactory.portConnection(pc));
      }
      return connections;
   }

@Override
public void getWriteCamkesPortComponents(File componentsDirectory) {
	// TODO Auto-generated method stub
	
}

@Override
public void getWriteCamkesPortIdls(File interfacesDirectory) {
	// TODO Auto-generated method stub
	
}

@Override
public List<String> getCamkesAddComponentPortImports() {
  // TODO Auto-generated method stub
  return null;
}

   // We do not allow "fan in" on mailboxes or my simple port type.
   public PortEmitter getSourcePort() {
      if (getIsOutputPort()) { return this; }
      else {
         assert(this.dataPort.getConnections().size() == 1);
         return 
               EmitterFactory.port(this.dataPort.getConnections().get(0).getSourcePort());
      }
   }

  @Override
  public String getCamkesAddPreInitStatements() {
    // TODO Auto-generated method stub
    return "";
  }

  @Override
  public String getCamkesAddAssemblyFileCompositionPortConnections() {
    // TODO Auto-generated method stub
    return "";
  }

  @Override
  public String getCamkesAddMakeFilePortDefinitions() { return ""; }
}
