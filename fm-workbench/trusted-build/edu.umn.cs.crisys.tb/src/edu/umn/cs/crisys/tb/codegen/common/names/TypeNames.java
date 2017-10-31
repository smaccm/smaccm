/**
 * 
 */
package edu.umn.cs.crisys.tb.codegen.common.names;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.emitters.NameEmitter;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.type.ArrayType;
import edu.umn.cs.crisys.tb.model.type.BoolType;
import edu.umn.cs.crisys.tb.model.type.IdType;
import edu.umn.cs.crisys.tb.model.type.IntType;
import edu.umn.cs.crisys.tb.model.type.PointerType;
import edu.umn.cs.crisys.tb.model.type.RecordType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class TypeNames implements NameEmitter {
  Type t;
  Type t_structural;

  static public Type getStructuralType(Type ty) {
	  try {
		  if (ty instanceof IdType) {
			  Type returnType = ((IdType) ty).getRootType();
			  return returnType;
		  } else return ty;
	  } catch (TbFailure r) {
		  throw new TbException(r.toString());
	  }
  }
  
  public TypeNames(Type t) {
    this.t = t;
    if (t == null) {
      throw new TbException("Unexpected null seen for type when constructing TypeNames object");
    }
    this.t_structural = getStructuralType(t);
  }
  
  public String getWriterFn() {
     return "write_" + t.getCType().typeString();
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
	    return (t instanceof PointerType) || (t instanceof ArrayType);
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
  public String getCamkesName() {
    if (t_structural instanceof ArrayType) {
      return TypeNames.getCamkesArrayContainerName((IdType)t);
    } else {
      return getName();
    }
  }
  

  public String getCamkesAutoCorresName() {
    if(t_structural instanceof IntType) {
      IntType it = (IntType) t_structural;
      return (new Integer(it.getBitSize())).toString()+" word";
    } else if(t_structural instanceof BoolType) {
      return "8 word";
    }
    else {
      return getCamkesName()+"_C";
    }
  }
  
  public String getCamkesAutoCorresValidFun() {
    if(t_structural instanceof IntType) {
      IntType it = (IntType) t_structural;
      return "is_valid_w"+(new Integer(it.getBitSize())).toString();
    } else if(t_structural instanceof BoolType) {
      return "is_valid_w8";
    }
    else {
      return "is_valid_" + getCamkesName()+"_C";
    }
  }
  
  public String getCamkesAutoCorresDerefFun() {
    if(t_structural instanceof IntType) {
      IntType it = (IntType) t_structural;
      return "heap_w"+(new Integer(it.getBitSize())).toString();
    } else if(t_structural instanceof BoolType) {
      return "heap_w8";
    }
    else {
      return "heap_" + getCamkesName()+"_C";
    }
  }
  
  public String getCamkesAutoCorresUpdateFun() {
    if(t_structural instanceof IntType) {
      IntType it = (IntType) t_structural;
      return "heap_w"+(new Integer(it.getBitSize())).toString()+"_update";
    } else if(t_structural instanceof BoolType) {
      return "heap_w8_update";
    } else {
      return "update_" + getCamkesName();
    }
  }
  
  public boolean getIsWord() {
    if(t_structural instanceof IntType || t_structural instanceof BoolType) {
      return true;
    }
    return false;
  }
  // must be called on a structural array type.
  protected String getArrayPtrCast() {
    ArrayType theArrayType = (ArrayType)t_structural;
    return "(" + theArrayType.getElemType().getCType().typeString() + " *)"; 
  }

  public String getCamkesInputToVal() {
    if (t_structural instanceof ArrayType) {
      return getArrayPtrCast(); 
    }
    return getAadlInputToVal();
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
    return t.getCType().typeString()+"_writer";
  }    

  public String getReaderWriterIdlFileName() {
    return getReaderWriterInterfaceName() + ".idl4";
  }    

  public String getSharedDataIdlFileName() {
    return getReaderWriterInterfaceName() + ".idl4";
  }    

  /* MWW: small hack */
  public static String getPrefix() { return OSModel.getPrefix(); }
  
  public String getSharedDataInterfaceName() {
    return getPrefix() + "_" + getName() + "_shared_var";
  }    
  
  public String getSharedDataInterfaceReadFnName() {
    return "read_" + getName();
  }
  
  public String getSharedDataInterfaceWriteFnName() {
    return "write_" + getName();
  }
  
  public int getOverApproximateSizeInBytes() {
     try {
        return this.t.getOverApproximateSizeInBytes(4);
     } catch (TbFailure te) {
        throw new TbException(te.getMessage());
     }
  }
  
  public static int PAGE_SIZE = 4096;
  
  public int getFrameSizeInBytes() {
     int size = getOverApproximateSizeInBytes();
     int frames = size / PAGE_SIZE + ((size % PAGE_SIZE == 0) ? 0 : 1);
     return frames * PAGE_SIZE;
  }
  
  // TODO MWW: Hacks!  To get arrays working with CAmkES.
  static private String getCamkesArrayContainerName(IdType idt) {
    return getPrefix() + "_" + idt.getTypeId() + "_container";
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

