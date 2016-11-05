/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.util.*;
import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterEChronos;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitterVxWorks;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.ExternalHandler;
import edu.umn.cs.crisys.tb.model.port.InputDataPort;
import edu.umn.cs.crisys.tb.model.port.InputPort;
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
 */
public class PortEmitterRPCDataport implements PortEmitter, PortEmitterCamkes, PortEmitterEChronos, PortEmitterVxWorks {

   public static boolean isApplicable(PortFeature pf) {
      // 
      // Note: we can elaborate this further at will.
      // e.g., we can add checks as to the "fitness" of the ports on the 
      // other connection ends attached to this port.
      //
      // However, this is likely better done at the connection
      // level.  We really should have different connection types.
      // 
      return (pf instanceof InputDataPort ||
              pf instanceof OutputDataPort); 
   }
   
   private OSModel model; 
   private PortFeature dataPort;

   
   public PortEmitterRPCDataport(PortFeature pf) {
      this.dataPort = pf;
      this.model = Util.getElementOSModel(pf);
   }
   
   @Override
   public PortFeature getModelElement() { return dataPort; }
   

   @Override
   public void addPortPublicTypeDeclarations(Map<String, Type> typeList) {
      // Data ports don't add any new types.
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


   
   public ST getTemplateST(String stName) {
      STGroupFile template = Util.createTemplate("PortEmitterRPCDataport.stg");
      return template.getInstanceOf(stName); 
   }
   
   

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortPreEntrypoint(int)
    */
   @Override
   public String writePortPreEntrypoint() {
      // no entrypoint initializer code for RPC dataports
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortPostEntrypoint(int)
    */
   @Override
   public String writePortPostEntrypoint() {
      // no entrypoint destructor code for RPC dataports
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortEventResponder(int)
    */
   @Override
   public String writePortEventResponder() {
      // no event responders for data ports; they do not cause dispatch
      return "";
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.emitters.PortConnection.PortCWriter#writePortThreadInitializer(int)
    */
   @Override
   public String writePortThreadInitializer() {
      String result = ""; 
      ExternalHandler eh = this.getModelElement().getInitializeEntrypointSourceText(); 
      if (eh != null) {
         result += eh.getHandlerName() + "();\n";
      }
      return result;
   }
   
   @Override
   public String writePortHPrototypes() {
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
      return st.render();
   }

   @Override
   public String writePortDeclarations() {
      ST st; 
      PortFeature p = getModelElement();
      if (p instanceof OutputDataPort) {
         st = getTemplateST("componentRemoteWriterDecl");   
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
    * VxWorks-specific functions (implementing RPCEventDataPortCamkes)
    * 
    ************************************************************/

   public String createMutex() {
      return "semMCreate(SEM_Q_PRIORITY | SEM_INVERSION_SAFE)"; 
   }
   
   public String writeMutexDecl(String extern) {
      return extern + "SEM_ID " + this.getMutex() + ";\n";
   }
   
   @Override
   public String vxWorksAddCommonHFileDeclarations() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += writeMutexDecl("extern ");
         toReturn += eChronosAddCommonHFileDeclarations();
      } 
      return toReturn;
   }

   @Override
   public String vxWorksAddMainCFileIncludes() {
      return "";
   }

   @Override
   public String vxWorksAddMainCFileDeclarations() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += writeMutexDecl("");
      }
      return toReturn;
   }

   @Override
   public String vxWorksAddMainCFileInitializers() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += this.getMutex() + " = " + createMutex() + ";\n";
         toReturn += "assert(" + this.getMutex() + " != NULL );\n";
      }
      ExternalHandler initializer = 
            this.getModelElement().getInitializeEntrypointSourceText();
      if (initializer != null) {
         toReturn += initializer.getHandlerName() + "();\n ";
      }

      return toReturn;
   }

   @Override
   public String vxWorksAddMainCFileDestructors() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += "semDelete(" + this.getMutex() + ");\n";
      }
      return toReturn;
   }

   /************************************************************
    * 
    * eChronos-specific functions (implementing RPCEventDataPortCamkes)
    * 
    ************************************************************/

   public String eChronosAddPrxMutexes() {
      String toReturn = "";
      if (this.getModelElement() instanceof InputPort) {
         toReturn += 
            "<mutex>\n" + 
            "  <name>" + this.getMutex() + "</name>\n" + 
            "</mutex>\n"; 
      }
      return toReturn;
   }
      
   public String eChronosAddPrxSemaphores() {
      return "";
   }

   @Override
   public String eChronosAddCommonHFileDeclarations() {
      String toReturn = ""; 
      if (this.getModelElement() instanceof InputPort) {
         toReturn += "bool " + getIncomingWriterName() + 
               "(const " + this.getType().getCamkesInputType().getName() + " arg); \n";
      }
      return toReturn;
   }

   @Override
   public String addTrampolines() { return ""; }
   
   @Override
   public String addInternalIrqs() { return ""; }
   
   @Override
   public String addExternalIrqs() { return ""; }
   
   /************************************************************
    * 
    * CAmkES-specific functions (implementing RPCDataPortCamkes)
    * 
    ************************************************************/

   private String addComponentInputDataPortDeclarations() {
      String element = 
         "provides " + this.getType().getReaderWriterInterfaceName() + " " + 
               this.getName() + ";\n" + 
         "has mutex " + this.getMutex() + ";\n";
      return element;
   }
   
   private String addComponentOutputDataPortDeclarations() {
      String element = 
         "uses " + this.getType().getReaderWriterInterfaceName() + " " + 
               this.getQualifiedName() + ";\n"; 
      return element; 
   }
   
   @Override
   public String addComponentPortLevelDeclarations() {
      // TODO Auto-generated method stub
      PortFeature pf = this.getModelElement();
      if (pf instanceof InputPort) {
         return addComponentInputDataPortDeclarations();
      } else if (pf instanceof OutputPort) {
         return addComponentOutputDataPortDeclarations();
      } else {
         throw new TbException("RPCEventDataPortEmitter::addComponentPortLevelDeclarations: event data port emitter used with non-event data port class: " + pf.getName());
      }
   }

   public String addAssemblyConnection(PortConnection conn, OSModel model) {
      ST st = getTemplateST("connectReaderWriter"); 
      st.add("connection", EmitterFactory.portConnection(conn));
      st.add("model", EmitterFactory.model(model));
      return st.render();
   }
  
   @Override
   public String addAssemblyFileCompositionPortDeclarations() {
      PortFeature pf = this.getModelElement();
      if (pf instanceof OutputPort) {
         String result = ""; 
         for (PortConnection conn : pf.getConnections()) {
            result += addAssemblyConnection(conn, this.model); 
         }
         return result;         
      } else {
         return "";
      }
   }
   
   @Override
   public String addAssemblyFileConfigDeclarations() {
      return "";
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
    *    
    */
   
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

   // middleware functions; these must be compatible with the OS.
   public String getIncomingWriterName() {
      PortFeature dp = getModelElement();
      String prefix = Util.getPrefix_();
      
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
      return (Util.getPrefix_() + getModelElement().getOwner().getNormalizedName() + "_" + getModelElement().getName() + "_mut").toLowerCase();
    }
    
    public String getEChronosMutexConst() {
       return (ModelNames.getEChronosPrefix() + "_MUTEX_ID_" + getMutex()).toUpperCase();
    }
   
   public String getLockStmt() {
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
