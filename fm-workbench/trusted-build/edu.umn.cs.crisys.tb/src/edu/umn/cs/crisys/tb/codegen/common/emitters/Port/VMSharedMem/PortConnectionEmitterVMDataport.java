/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.VMSharedMem;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.OSModel.OSTarget;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.util.*; 


/**
 * @author Whalen
 *
 */
public class PortConnectionEmitterVMDataport implements PortConnectionEmitter {

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

  @Override
  public int getConnectionID() {
    return c.getConnectionID();
  }



}
