/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

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
