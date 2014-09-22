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
  Type t_structural;

  TypeNames(Type t) {
    this.t = t;
    this.t_structural = Names.getStructuralType(t);
  }
  
  public String getWriterFn() {
    if (getIsUnit()) {
      return "write_void";
    } else {
      return "write_" + t.getCType().typeString();
    }
  }
  
  public boolean getIsUnit() {
    return (t_structural instanceof UnitType); 
  }
  
  public boolean getHasData() {
    return !getIsUnit();
  }

  public boolean getIsBaseType() {
    return t_structural.isBaseType();
  }
  
  ///////////////////////////////////////////////////
  //
  // Naming properties
  //
  ///////////////////////////////////////////////////
  
  public String getName() {
    return t.getCType().typeString(); 
  }
  
  public Type getOutputType() { 
//    return (t_structural instanceof ArrayType) ?
//        t : new PointerType(t);
      return new PointerType(t);
  }
  
  public Type getInputType() {
    return t;
    //    return (t_structural.isBaseType() ? t : getOutputType());
  }
  
  public String getOutputTypeName() {
    return getOutputType().getCType().typeString(); 
  }
  
  public String getInputTypeName() {
    return "/* const */" + getInputType().getCType().typeString();
  }
  
  public String getInputToOutput() {
    //Type t = getInputType(); 
    //if (!(t_structural instanceof PointerType)) { return "&"; } else { return ""; }
    return "&";
  }

  public String getValToInput() {
    //if (t_structural.isBaseType()) { return ""; } else { return "&"; }
    return "";
  }
  
  public String getValToOutput() {
//  if (t_structural instanceof ArrayType || t instanceof PointerType) { return ""; } else { return "*"; }
    return "&";
  }
  
  public String getOutputToVal() {
//    WRONG Type t = getRefType(); 
//    if (t instanceof ArrayType) { return ""; } else { return "*"; }
    return "*"; 
  }

  public String getInputToPtr() {
    if (t_structural instanceof ArrayType) { return ""; } else { return "&"; }
  }
  
  public String getOutputToPtr() {
    if (t_structural instanceof ArrayType) { return "*"; } else { return ""; }
  }
  
  public String getValToPtr() {
    if (t_structural instanceof ArrayType) { return ""; } else { return "&"; }
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

