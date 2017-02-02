/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC;


import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortConnectionEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.Notification.PortEmitterNotification;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.OSModel.OSTarget;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.OutputEventPort;
import edu.umn.cs.crisys.tb.util.*; 


/**
 * @author Whalen
 *
 */
public class PortConnectionEmitterRPCImpl implements PortConnectionEmitterCamkes, PortConnectionEmitterRPC {

  PortConnection c;
  
  static boolean isRpcDataport(PortEmitter p) {
     return (p instanceof PortEmitterRPC);
  }
  
  static boolean isNotificationDataport(PortEmitter p) {
     return (p instanceof PortEmitterNotification);
  }
  
  static boolean isSupportedOS(OSTarget p) {
     return (p == OSTarget.CAmkES || 
             p == OSTarget.eChronos || 
             p == OSTarget.VxWorks ||
             p == OSTarget.linux);
  }
  
  public static boolean isApplicable(PortConnection c) {
     boolean result = true;
     
     // This class is suitable for RPC ports for VxWorks, eChronos, and CAmkES.
     PortEmitter source = EmitterFactory.port(c.getSourcePort()); 
     PortEmitter dest = EmitterFactory.port(c.getDestPort()); 

     // applicable if both source and destination ports are RPC dataports
     result &= (isRpcDataport(source) && isRpcDataport(dest)) || 
               (isNotificationDataport(source) && isNotificationDataport(dest));  
     
     // applicable if OS is one of {eChronos, VxWorks, Camkes}
     OSTarget srcOS = (Util.getElementOSModel(c.getSourcePort())).getOsTarget();
     OSTarget dstOS = (Util.getElementOSModel(c.getDestPort())).getOsTarget();
     
     result &= isSupportedOS(srcOS) && isSupportedOS(dstOS); 
     
     return result;
  }
  
  public PortConnectionEmitterRPCImpl(PortConnection c) {
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
	  if (c.getSourcePort().getConnections().size() > 1)
		  return c.getSourcePort().getQualifiedName() + "_" + c.getDestPort().getQualifiedName();
	  else
		  return this.c.getSourcePort().getQualifiedName();
  }
  
   public String getOutgoingPortName() {
      OSModel os = c.getSourcePort().getOwner().getModel();
      if (os.getOsTarget() == OSModel.OSTarget.CAmkES &&
            this.c.getSourcePort().getConnections().size() > 1) {
         return this.getName();
      } else {
         return this.getSourcePort().getName(); 
      }
   }
  // connections.
  /* (non-Javadoc)
 * @see edu.umn.cs.crisys.tb.codegen.common.names.PortConnectionEmitter#getOutgoingPortWriterName()
 */
@Override
public String getOutgoingPortWriterName() {
     OSModel os = c.getSourcePort().getOwner().getModel();
     // TODO: This should probably be rewritten and the name generation
     // for output ports, at minimum for camkes, should be hidden in a PortEmitter method.
     if (os.getOsTarget() == OSModel.OSTarget.CAmkES) {
         if (this.getSourcePort().getModelElement() instanceof OutputEventPort) {
           return Util.getPrefix() + "_" +getOutgoingPortName() + "_enqueue";
         } else {
           return Util.getPrefix() + "_" + getOutgoingPortName() + "_" + this.getSourcePort().getType().getWriterFn();
         }
     } else if (os.getOsTarget() == OSModel.OSTarget.eChronos || 
                os.getOsTarget() == OSModel.OSTarget.VxWorks ||
                os.getOsTarget() == OSModel.OSTarget.linux) {
        
        // which RPC type are we supporting?
        PortEmitterRPC rpcPort = (PortEmitterRPC)this.getDestPort(); 
        
        return rpcPort.getIncomingWriterName(); 
     } else {
        throw new TbException("PortConnection::getOutgoingPortWriterName: unknown OS: " + os.getOsTarget());
     }
  }

// Decision point here - should we remove this code from the 
// port and add it here?
@Override
public String writeAssemblyPortConnection() {
   String result = ""; 
   
   return result;
}


@Override
public int getConnectionID() {
  return c.getConnectionID();
}

public String getConnector() {
   if (c.containsExtendedData("CamkesCustomConnector")) {
      return (String)c.getExtendedData("CamkesCustomConnector");
   } else {
      return "seL4RPCCall";
   }
}

}
