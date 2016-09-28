/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.names;

/**
 * @author Whalen
 *
 */
public class MemoryRegionName {
  
  private String name; 
  private String region;
  
  public MemoryRegionName(String name, String region) {
    this.name = name;
    this.region = region;
  }

  public String getName() { return name; }
  
  public String getRegion() { return region; }
    
}
