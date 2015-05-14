/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.ArrayType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IdType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.PointerType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

/**
 * @author Whalen
 *
 */
public class TypeNames {
  Type t;
  Type t_structural;

  static public Type getStructuralType(Type ty) {
	  try {
		  if (ty instanceof IdType) {
			  Type returnType = ((IdType) ty).getRootType();
			  return returnType;
		  } else return ty;
	  } catch (Aadl2RtosFailure r) {
		  throw new Aadl2RtosException(r.toString());
	  }
  }
  
  public TypeNames(Type t) {
    this.t = t;
    if (t == null) {
      throw new Aadl2RtosException("Unexpected null seen for type when constructing TypeNames object");
    }
    this.t_structural = getStructuralType(t);
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

  /* AADL-native types for inputs and outputs + conversion functions 
   * 
   * The way this works is we assume a 'base' type for a declaration that we
   * emit in the code generator.  This declaration may be used as an input or
   * output, in which case its type is adjusted for efficient use of stack
   * resources and C's weird parameter rules (arrays are implicitly passed by 
   * pointer).  
   * 
   * The conversion functions assume that we use the *BASE TYPE* as the 
   * baseline and WILL NOT WORK if you start from the 'adjusted type', e.g.:
   *  Type t = // some base type 
   *  t.getOutputType().getOutputToInput();
   * 
   * Instead use: 
   *  t.getOutputToInput() 
   * 
   * 
   */ 
  public boolean isNativePointerArg() {
	    return (t_structural instanceof PointerType) || 
	           (t_structural instanceof ArrayType);
	  }
  
  public TypeNames getAadlOutputType() { 
    return new TypeNames(isNativePointerArg() ?
        t : new PointerType(t));
  }
  
  public TypeNames getAadlInputType() {
    return getAadlOutputType();
  }
  
  
  public String getAadlInputToAadlOutput() {
    return "";
  }

  public String getValToAadlInput() {
    if (isNativePointerArg()) { return ""; } else { return "&"; }
  }
  
  public String getValToAadlOutput() {
    if (isNativePointerArg()) { return ""; } else { return "&"; }
  }
  
  // AADL outputs are always pointers
  public String getAadlOutputToVal() {
    return "*"; 
  }

  public String getAadlInputToVal() {
	    return "*"; 
	  }

  public String getAadlInputToPtr() {
    return ""; 
  }
  
  public String getAadlOutputToPtr() {
    return "";
  }
  
  public String getValToPtr() {
    if (isNativePointerArg()) { return ""; } else { return "&"; }
  }    

  /* Conversion to CAMKES types */
  /* CAmkES-native types for inputs and outputs + conversion functions;
   * now these follow the same conventions as AADL */ 
  public TypeNames getCamkesOutputType() { 
    if (t_structural instanceof ArrayType) {
      throw new Aadl2RtosException("Error during code generation: Array types are incorrectly implemented in CAmkES");
    }
    return getAadlOutputType();
  }
  
  public TypeNames getCamkesInputType() {
    if (t_structural instanceof ArrayType) {
      throw new Aadl2RtosException("Error during code generation: Array types are incorrectly implemented in CAmkES");
    }
    return getAadlInputType();
  }

  public String getValToCamkesInput() {
    return getValToAadlInput();
  }

  public String getCamkesInputToVal() {
	    return getAadlInputToVal();
	  }

  public String getCamkesInputToAadlInput() {
	  return ""; 
  }

  public String getAadlInputToCamkesInput() {
	  return "";
  }

  public String getCamkesInputToPtr() {
	  return getAadlInputToPtr();
  }
  public String getCamkesOutputToAadlOutput() {
    return ""; 
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
  
  // for creating dispatcher structures for components
  static public String getDispatchStructTypeName(ThreadImplementation ti, 
	      OutputEventPort key, Integer val) {
	    return "smaccm_" + Util.normalizeAadlName(key.getType().toString()) 
	        + "_struct_" + val; 
  }
  
}

