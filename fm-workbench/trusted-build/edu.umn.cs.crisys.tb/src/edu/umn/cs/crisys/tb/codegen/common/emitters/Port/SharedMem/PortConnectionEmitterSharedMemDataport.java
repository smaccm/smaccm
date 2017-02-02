/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.SharedMem;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.ThreadEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.OSModel.OSTarget;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.util.*; 


/**
 * @author Whalen
 *
 */
public class PortConnectionEmitterSharedMemDataport implements PortConnectionEmitterSharedMem {

   PortConnection c;

   static boolean isSharedMemDataport(PortEmitter p) {
      return (p instanceof PortEmitterSharedMem);
   }

   static boolean isSupportedOS(OSTarget p) {
      return (p == OSTarget.linux);
   }

   public static boolean isApplicable(PortConnection c) {
      boolean result = true;

      // This class is suitable for RPC ports for VxWorks, eChronos, and CAmkES.
      PortEmitter source = EmitterFactory.port(c.getSourcePort()); 
      PortEmitter dest = EmitterFactory.port(c.getDestPort()); 

      // applicable if both source and destination ports are RPC dataports
      result &= isSharedMemDataport(source) && isSharedMemDataport(dest);  

      // applicable if OS is linux.
      // Also, not applicable across OSes (VM boundary).
      
      OSModel srcModel = (Util.getElementOSModel(c.getSourcePort()));
      OSModel dstModel = (Util.getElementOSModel(c.getDestPort()));

      result &= isSupportedOS(srcModel.getOsTarget()) && srcModel == dstModel; 

      return result;
   }

   public PortConnectionEmitterSharedMemDataport(PortConnection c) {
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
