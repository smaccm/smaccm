/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;

/**
 * @author Whalen
 *
 */
public class MemoryRegionNames implements NameEmitter {
  
  private String name; 
  private String region;
  private PortEmitter pn;
  
  public MemoryRegionNames(PortEmitter pn, String name, String region) {
    this.pn = pn;
    this.name = name;
    this.region = region;
  }

  public String getName() { return name; }
  
  public String getRegion() { return region; }
    
  public String getRegionTypeName() {
     return pn.getQualifiedName() + "_" + getName() + "_type";
  }
}
