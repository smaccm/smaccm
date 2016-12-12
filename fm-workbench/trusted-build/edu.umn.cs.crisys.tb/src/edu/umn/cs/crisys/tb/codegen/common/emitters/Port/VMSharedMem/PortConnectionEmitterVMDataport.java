/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.VMSharedMem;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.ThreadEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitterCamkesVM;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.SharedMem.PortEmitterSharedMem;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.OSModel.OSTarget;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.util.*; 


/**
 * @author Whalen
 *
 */
public class PortConnectionEmitterVMDataport implements PortConnectionEmitter, PortConnectionEmitterCamkesVM {

   PortConnection c;

   static boolean isSimpleVmDataport(PortEmitter p) {
      return (p instanceof PortEmitterSimpleVMDataport);
   }

   static boolean isSupportedOS(OSTarget p) {
      return (p == OSTarget.linux || 
              p == OSTarget.CAmkES);
   }

   public static boolean isApplicable(PortConnection c) {
      boolean result = true;

      // This class is suitable for RPC ports for VxWorks, eChronos, and CAmkES.
      PortEmitter source = EmitterFactory.port(c.getSourcePort()); 
      PortEmitter dest = EmitterFactory.port(c.getDestPort()); 

      // applicable if both source and destination ports are RPC dataports
      result &= isSimpleVmDataport(source) && isSimpleVmDataport(dest);  

      // applicable if OS is one of {linux, Camkes} and the linux OS is the 
      // child of the camkes OS.
      
      OSModel srcModel = (Util.getElementOSModel(c.getSourcePort())); 
      OSModel dstModel = (Util.getElementOSModel(c.getDestPort())); 
      
      OSTarget srcOS = srcModel.getOsTarget();
      OSTarget dstOS = dstModel.getOsTarget();

      boolean vmOption1 =  
                (srcOS == OSTarget.CAmkES) && 
                (dstOS == OSTarget.linux) && 
                (dstModel.getParent() == srcModel); 
      
      boolean vmOption2 = 
                (dstOS == OSTarget.CAmkES) && 
                (srcOS == OSTarget.linux) &&
                (srcModel.getParent() == dstModel);
      
      
      result &= vmOption1 || vmOption2; 

      return result;
   }

   /* 
    * 
    * Method only works if invariant from isApplicable is true.
    * 
    */
   private boolean srcIsHost() {
      OSModel srcModel = (Util.getElementOSModel(c.getSourcePort())); 
      OSModel dstModel = (Util.getElementOSModel(c.getDestPort())); 
      
      OSTarget srcOS = srcModel.getOsTarget();
      OSTarget dstOS = dstModel.getOsTarget();

      return (srcOS == OSTarget.CAmkES) && 
                  (dstOS == OSTarget.linux) && 
                  (dstModel.getParent() == srcModel); 
   }
   
   private OSModel getHostOS() {
      if (srcIsHost()) {
         return (Util.getElementOSModel(c.getSourcePort()));
      } else {
         return (Util.getElementOSModel(c.getDestPort()));
      }
   }
   
   private OSModel getVmOS() {
      if (srcIsHost()) {
         return (Util.getElementOSModel(c.getDestPort()));
      } else {
         return (Util.getElementOSModel(c.getSourcePort()));
      }
   }
   
   public int getConnectionNumber() {
      OSModel model = getHostOS(); 
      // connections are 1-indexed in Camkes for some reason.
      return model.getVmCrossingConnections().indexOf(c) + 1;
   }
   
   public PortConnectionEmitterVMDataport(PortConnection c) {
      this.c = c;
      
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.names.PortConnectionEmitter#getSourcePort()
    */
   @Override
   public PortEmitter getSourcePort() {
      return EmitterFactory.port(c.getSourcePort());
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.names.PortConnectionEmitter#getDestPort()
    */
   @Override
   public PortEmitter getDestPort() {
      return EmitterFactory.port(c.getDestPort());
   }

   public NameEmitter getDestPortThreadImplementation() {
      return EmitterFactory.threadImplementation(c.getDestThreadInstance().getThreadImplementation());
   }

   public NameEmitter getSourcePortThreadImplementation() {
      return EmitterFactory.threadImplementation(c.getSourceThreadInstance().getThreadImplementation());
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.names.PortConnectionEmitter#getName()
    */
   @Override
   public String getName() {
      return c.getName();
   }

   /* (non-Javadoc)
    * @see edu.umn.cs.crisys.tb.codegen.common.names.PortConnectionEmitter#getFullyQualifiedName()
    */
   @Override
   public String getFullyQualifiedName() {
      return c.getSourcePort().getQualifiedName() + "_" + c.getDestPort().getQualifiedName();
   }

   public ST getTemplateST(String stName) {
      STGroupFile template = Util.createTemplate("PortEmitterSimpleVMDataport.stg");
      return template.getInstanceOf(stName); 
   }

   /******************************************************************
    * 
    * Camkes declarations
    * 
    ******************************************************************/
   
   @Override
   public String getCamkesAddVMComponentPortLevelDeclarations() {
      String result = ""; 
      // result = "// Connection: " + this.getName() + " should be a port on the linux side of " + System.lineSeparator();
      return result;
   }

   @Override
   public String getCamkesAddAssemblyFileVMPortDeclarations() {
      String result = ""; 
//      result = "// Connection: " + this.getName() + " connects a port on the linux side of " + System.lineSeparator() + 
//            "// Here we need to implement its assembly equivalent declarations in Camkes to be the edge " + System.lineSeparator() + 
//            "// in the VM that communicates with Linux.  ";
      result += "dataport " + this.getSourcePort().getType().getCamkesName() + " " + this.getName() + ";" + System.lineSeparator();
      return result;
   }

   @Override        
   public String getCamkesAddAssemblyFileVMCompositionPortDeclarations() {
      String result = ""; 
      //result = "// Port: " + this.getName() + " should be a port on the linux side of " + System.lineSeparator() + 
      //      "// Here we need to implement its assembly equivalent configuration in Camkes to be the edge " + System.lineSeparator() + 
      //      "// in the VM that communicates with Linux.  ";
      OSModel vm = this.getVmOS(); 
      String templateName;
      if (srcIsHost()) {
         templateName = "connectCamkesSourceToVm";
      } else {
         templateName = "connectVmToCamkesDest"; 
      }
      ST st = this.getTemplateST(templateName);
      st.add("vm", EmitterFactory.model(vm));
      st.add("connection", this);
      result += st.render();
      return result;
   }

   @Override
   public String getCamkesAddAssemblyFileVMConfigDeclarations() {
      String result = ""; 
      result = "// Connection: " + this.getName() + " connects a port on the linux side of " + System.lineSeparator() + 
            "// Here we need to implement its assembly equivalent connections in Camkes to be the edge " + System.lineSeparator() + 
            "// in the VM that communicates with Linux.  ";
      ST st = getTemplateST("assemblyConfigConnection");
      st.add("connection", this);
      st.add("vm", EmitterFactory.model(getVmOS()));
      
      return result;
   }

}
