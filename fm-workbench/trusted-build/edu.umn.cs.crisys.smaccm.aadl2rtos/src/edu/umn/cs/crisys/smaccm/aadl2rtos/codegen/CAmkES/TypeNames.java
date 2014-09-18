/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CommonNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.ArrayType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.PointerType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class TypeNames {
  Type t; 

  TypeNames(Type t) {this.t = t;}
  
  public String getWriterFn() {
    if (getIsUnit()) {
      return "write_void";
    } else {
      return "write_" + t.getCType().typeString();
    }
  }
  
  public boolean getIsUnit() {
    return (t instanceof UnitType); 
  }
  
  public boolean getHasData() {
    return !getIsUnit();
  }
  
  public String getName() {
    return t.getCType().typeString(); 
  }
  
  public Type getOutputType() { 
//    return (CommonNames.getStructuralType(t) instanceof ArrayType) ?
//        t : new PointerType(t);
      return new PointerType(t);
  }
  
  public Type getInputType() {
    return (t.isBaseType() ? t : getOutputType());
  }
  
  public String getOutputTypeName() {
    return getOutputType().getCType().typeString(); 
  }
  
  public String getInputTypeName() {
    return "/* const */" + getInputType().getCType().typeString();
  }

  public String getInputAsValName() {
    Type t = getInputType(); 
    if (t instanceof PointerType) { return "*"; } else { return ""; }
  }
  
  public String getInputAsOutput() {
    Type t = getInputType(); 
    if (t instanceof PointerType) { return "&"; } else { return ""; }
  }

  public String getValAsInput() {
    if (t.isBaseType()) { return ""; } else { return "&"; }
  }
  
  public String getValAsOutput() {
    return "&";
  }
  
  public String getOutputAsVal() {
//    Type t = getRefType(); 
//    if (t instanceof ArrayType) { return ""; } else { return "*"; }
    return "*"; 
  }
  
  public String getReaderWriterInterfaceName() {
    return t.getCType().typeString() + "_writer";
  }    

  public String getReaderWriterIdlFileName() {
    return getReaderWriterInterfaceName() + ".idl4";
  }    

  public String getSharedDataIdlFileName() {
    return getReaderWriterInterfaceName() + ".idl4";
  }    

  public String getSharedDataInterfaceName() {
    return "smaccm_" + getName() + "_shared_var";
  }    
  
  public String getSharedDataInterfaceReadFnName() {
    return "read_" + getName();
  }
  
  public String getSharedDataInterfaceWriteFnName() {
    return "write_" + getName();
  }
}

