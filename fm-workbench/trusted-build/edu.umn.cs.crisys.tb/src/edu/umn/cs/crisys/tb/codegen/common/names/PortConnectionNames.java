/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.ThreadEmitter;
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
}
