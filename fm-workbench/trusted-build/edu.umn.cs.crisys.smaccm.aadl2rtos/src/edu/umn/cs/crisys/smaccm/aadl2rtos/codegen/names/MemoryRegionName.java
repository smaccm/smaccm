/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

/**
 * @author Whalen
 *
 */
public class MemoryRegionName {
  
  private PortNames pn; 
  private String name; 
  private String region;
  
  public MemoryRegionName(PortNames pn, String name, String region) {
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
