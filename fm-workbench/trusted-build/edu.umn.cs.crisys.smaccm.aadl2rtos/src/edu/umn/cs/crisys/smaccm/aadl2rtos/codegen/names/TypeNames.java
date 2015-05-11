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
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.RecordType;
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

  public TypeNames getQueueType() {
    return new TypeNames(new PointerType(t));
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

  // TODO: MWW: This is a hack because CAmkES does not support arrays.
  public TypeNames getCamkesOutputType() { 
    if (t_structural instanceof ArrayType) {
      return new TypeNames(
          new PointerType(TypeNames.constructCamkesArrayContainerIdType((IdType)t))); 
      // throw new Aadl2RtosException("Error during code generation: Array types are incorrectly implemented in CAmkES");
    }
    return getAadlOutputType();
  }
  
  // TODO: MWW: This is a hack because CAmkES does not support arrays.
  public TypeNames getCamkesInputType() {
    if (t_structural instanceof ArrayType) {
      return new TypeNames(
          new PointerType(TypeNames.constructCamkesArrayContainerIdType((IdType)t))); 
      // throw new Aadl2RtosException("Error during code generation: Array types are incorrectly implemented in CAmkES");
    }
    return getAadlInputType();
  }

  // TODO: MWW: This is a hack because CAmkES does not support arrays.
  public String getCamkesName() {
    if (t_structural instanceof ArrayType) {
      return TypeNames.getCamkesArrayContainerName((IdType)t);
    } else {
      return getName();
    }
  }

  // must be called on a structural array type.
  protected String getArrayPtrCast() {
    ArrayType theArrayType = (ArrayType)t_structural;
    return "(" + theArrayType.getElemType().getCType().typeString() + " *)"; 
  }
  
  // MWW: TODO HACK! necessary to get arrays working with CAmkES.
  //               valToCamkesInput
  public String getValToCamkesInput() {
    if (t_structural instanceof ArrayType) {
      String wrapperName = TypeNames.getCamkesArrayContainerName((IdType)t);
      return "(" + wrapperName + " *)"; 
      // throw new Aadl2RtosException("Error during code generation: Unexpected instance of array type.");
    }
    return getValToAadlInput();
  }

  public String getCamkesInputToVal() {
    if (t_structural instanceof ArrayType) {
      return getArrayPtrCast(); 
      // throw new Aadl2RtosException("Error during code generation: Unexpected instance of array type.");
    }
    return getAadlInputToVal();
  }

  public String getCamkesInputToAadlInput() {
    if (t_structural instanceof ArrayType) {
        return getArrayPtrCast();  
    }
    else 
      return ""; 
  }

  // MWW: TODO HACK! necessary to get arrays working with CAmkES.
  public String getAadlInputToCamkesInput() {
    if (t_structural instanceof ArrayType) {
      String wrapperName = TypeNames.getCamkesArrayContainerName((IdType)t);
      return "(" + wrapperName + " *)"; 
    }
    else 
      return "";
  }

  public String getCamkesInputToPtr() {
	  if (t_structural instanceof ArrayType) {
	    return getArrayPtrCast(); 
	  }
	  return getAadlInputToPtr();
  }

  public String getCamkesOutputToPtr() {
    if (t_structural instanceof ArrayType) {
      return getArrayPtrCast();
    }
    return getAadlOutputToPtr();
  }
//  public String getCamkesOutputToAadlOutput() {
//    return ""; 
//  }

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
  
  // TODO MWW: Hacks!  To get arrays working with CAmkES.
  static private String getCamkesArrayContainerName(IdType idt) {
    return "smaccm_" + idt.getTypeId() + "_container";
  }
  
  static private Type constructCamkesArrayContainer(IdType idt) {
    RecordType t = new RecordType(); 
    t.addField("f", idt);
    return t;
  }
  
  static public Type constructCamkesArrayContainerIdType(IdType idt) {
     return new IdType(
            TypeNames.getCamkesArrayContainerName(idt),
            TypeNames.constructCamkesArrayContainer(idt));
  }
}

