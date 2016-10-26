/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;

/**
 * @author Whalen
 *
 */
public class MemoryRegionNames implements NameEmitter {
  
  private String name; 
  private String region;
  
  public MemoryRegionNames(String name, String region) {
    this.name = name;
    this.region = region;
  }

  public String getName() { return name; }
  
  public String getRegion() { return region; }
    
}
