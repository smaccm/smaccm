/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

/**
 * @author Whalen
 *
 */
public class MemoryRegionName {
  
  private String name; 
  private Long region;
  
  public MemoryRegionName(String name, Long region) {
    this.name = name;
    this.region = region;
  }

  public String getName() { return name; }
  
  public String getRegion() { return Long.toHexString(region.longValue()); }
    
}
