/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.ThreadEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;

/**
 * @author Whalen
 *
 */
public class PortConnectionNames implements NameEmitter {

  PortConnection c;
  
  public PortConnectionNames(PortConnection c) {
    this.c = c;
  }
  
  public PortEmitter getSourcePort() {
    return EmitterFactory.port(c.getSourcePort());
  }
  
  public PortEmitter getDestPort() {
    return EmitterFactory.port(c.getDestPort());
  }
  
  public NameEmitter getDestPortThreadImplementation() {
    return EmitterFactory.threadImplementation(c.getDestThreadInstance().getThreadImplementation());
  }
  
  public NameEmitter getSourcePortThreadImplementation() {
    return EmitterFactory.threadImplementation(c.getSourceThreadInstance().getThreadImplementation());
  }
  
  public String getName() {
	return c.getName();
  }
  
  public String getFullyQualifiedName() {
	  if (c.getSourcePort().getConnections().size() > 1)
		  return c.getSourcePort().getQualifiedName() + "_" + c.getDestPort().getQualifiedName();
	  else
		  return this.c.getSourcePort().getQualifiedName();
  }
  
  // TODO: fix this once we get a better handle on OS-specific 
  // connections.
  public String getOutgoingPortWriterName() {
     OSModel os = c.getSourcePort().getOwner().getModel();
     if (os.getOsTarget() == OSModel.OSTarget.CAmkES) {
        return this.getName() + "_" + this.getSourcePort().getType().getWriterFn();
     } else if (os.getOsTarget() == OSModel.OSTarget.eChronos || 
                os.getOsTarget() == OSModel.OSTarget.VxWorks) {
        return this.getDestPort().getIncomingWriterName(); 
     } else {
        throw new TbException("PortConnection::getOutgoingPortWriterName: unknown OS: " + os.getOsTarget());
     }
  }
}
