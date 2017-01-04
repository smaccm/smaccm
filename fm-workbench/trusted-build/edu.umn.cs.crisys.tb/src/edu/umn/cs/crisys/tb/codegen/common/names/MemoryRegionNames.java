/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.StringTokenizer;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;

/**
 * @author Whalen
 *
 */
public class MemoryRegionNames implements NameEmitter {
  
  private String name; 
  private String address;
  private int size;
  private PortEmitter pn;
  
  public MemoryRegionNames(PortEmitter pn, String name, String region) {
    this.pn = pn;
    this.name = name;
    StringTokenizer tok = 
          new StringTokenizer(region, ":"); 
    this.address = tok.nextToken();
    String size = tok.nextToken(); 
    if (this.address == null || size == null) {
       throw new TbException("In IRQ port " + pn.getName() + ", memory region is mis-specified");
    }
    try {
       this.size = Integer.decode(size);
    } catch (NumberFormatException ne) {
       throw new TbException("In IRQ port " + pn.getName() + ", memory region is mis-specified");
    }
  }

  public String getName() { return name; }
  
  public String getAddress() { return address; }
    
  public int getSize() { return size; }
  public String getRegionTypeName() {
     return pn.getQualifiedName() + "_" + getName() + "_type";
  }
}
