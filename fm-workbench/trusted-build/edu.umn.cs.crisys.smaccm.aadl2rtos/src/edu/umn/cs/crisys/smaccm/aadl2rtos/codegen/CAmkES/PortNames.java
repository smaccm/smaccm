/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CommonNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.SourceDeclarations;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.ArrayType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.BoolType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;

/**
 * @author Whalen
 *
 */
public class PortNames {
  DataPort dp; 
  Type indexType = new IntType(32, false); 
  
  PortNames(DataPort dp) {
    this.dp = dp;
  }
  
  //////////////////////////////////////////////////////////
  //
  // Constructors for type names related to port
  // 
  //////////////////////////////////////////////////////////
  public TypeNames getType() {
    TypeNames tyn = new TypeNames(dp.getType());
    return tyn;
  }
  
  public TypeNames getIndexType() {
    TypeNames tyn = new TypeNames(indexType);
    return tyn;
  }
  
  public ThreadImplementationNames getThreadImplementation() {
    return new ThreadImplementationNames(dp.getOwner());
  }
  
  //////////////////////////////////////////////////////////
  //
  // 'root' name and elements.
  // 
  //////////////////////////////////////////////////////////
  
  public String getName() {
    return dp.getName();
  }
  
  public String getQueueSize() {
    if (!(dp instanceof InputEventPort)) {
      throw new Aadl2RtosException("Error: getQueueSize: port " + dp.getName() + " is not an input event port so has no queue.");
    }
    InputEventPort iep = (InputEventPort)dp; 
    return Integer.toString(iep.getQueueSize());
  }

  
  //////////////////////////////////////////////////////////
  //
  // functions for creating local declarations with the port name
  // 
  //////////////////////////////////////////////////////////
  
  public String getNameAsInputParam() {
    TypeNames tpn = this.getType();
    return tpn.getInputType() + " " + getName();
  }
  
  public String getNameAsOutputParam() {
    TypeNames tpn = this.getType();
    return tpn.getOutputType() + " " + getName();
  }

  public String getVarDecl() {
    return dp.getType().getCType().varString(getName());
  }
  
  //////////////////////////////////////////////////////////
  //
  // query functions
  // 
  //////////////////////////////////////////////////////////

  public boolean getHasData() {
    return dp.hasData(); 
  }
  
  public boolean getIsNotArray() {
    return !(dp.getType() instanceof ArrayType);
  }
  
  public boolean getIsBaseType() { return dp.getType().isBaseType(); }

  public boolean getIsOutputDataPort() {
    return (dp instanceof OutputDataPort);
  }
  //////////////////////////////////////////////////////////////
  //
  // Names for mutex function calls
  // 
  //////////////////////////////////////////////////////////////

  
  public String getCamkesMutexLockFnCall() {
    return getMutex() + "_lock()";
  }

  public String getCamkesMutexUnlockFnCall() {
    return getMutex() + "_unlock()";
  }

  //////////////////////////////////////////////////////////////
  //
  // Names for passive component global data related to port
  // 
  //////////////////////////////////////////////////////////////
  
  public String getGlobalMaxDispatchSize() {
    return "smaccm_max_tmp_array_" + getName(); 
  }
  
  public String getGlobalIndex() {
    return "smaccm_tmp_used_" + getName();
  }

  public String getGlobalData() {
    return "smaccm_tmp_array_" + getName();
  }
  
  public String getData() {
    return getName() + "_data";
  }
  
  public String getIndex() {
    return getName() + "_index";
  }

  //////////////////////////////////////////////////////////////
  //
  // Names for port dispatchers
  // 
  //////////////////////////////////////////////////////////////

  public String getDispatcherCFileDispatcherFnName() {
    return "smaccm_" + dp.getOwner().getNormalizedName() + "_" + getName() + "_dispatcher";
  }
  
  public String getPassiveComponentDispatcherName() {
    ThreadImplementationNames tin = new ThreadImplementationNames(dp.getOwner());
    return tin.getComponentInstanceName() + "_" + 
        this.getDispatcherCFileDispatcherFnName(); 
  }  
  
  public String getIdlDispatcherName() {
    return "dispatch_" + getName();
  }
  //////////////////////////////////////////////////////////////
  //
  // Names for parameters/declarations for port data: 
  //  Note: these should be replaced by <Type> <Name> 
  //    declarations.  Much better!
  // 
  //////////////////////////////////////////////////////////////

  public String getDataParam() {
    TypeNames tpn = this.getType(); 
    return tpn.getOutputType() + " " + this.getData();
  }

  public String getIndexParam() {
    TypeNames tpn = new TypeNames(indexType);
    return tpn.getOutputType() + " " + this.getIndex(); 
  }  

  public String getIndexDecl() {
    return indexType.getCType().varString(getIndex());
  }
  
  
  public String getGlobalIndexParam() {
    return this.getType().getOutputType() + " " + this.getGlobalIndex();
  }
  
  public String getGlobalDataParam() {
    return this.getType().getOutputType() + " " + this.getGlobalData();
  }
  
  public String getGlobalMaxDispatchSizeParam() {
    return indexType.toString() + " " + getGlobalMaxDispatchSize(); 
  }
  
  //////////////////////////////////////////////////////////////
  //
  // Names for dispatcher thread global data for send/receive ports
  // 
  //////////////////////////////////////////////////////////////
  
  public String getQueueFrontName() { return "smaccm_queue_front_" + this.getName(); }
  public String getQueueBackName()  { return "smaccm_queue_back_" + this.getName(); }
  public String getQueueDataName()  { return "smaccm_queue_" + this.getName(); }
  public String getQueueFullName() { return "smaccm_queue_full_" + this.getName(); }

  public String getQueueIsFullName() { return "smaccm_queue_is_full_" + this.getName(); }
  public String getQueueIsEmptyName() { return "smaccm_queue_is_empty_" + this.getName(); }
  public String getQueueReadName() { return "smaccm_queue_read_" + this.getName(); }
  public String getQueueWriteName() { return "smaccm_queue_write_" + this.getName(); }

  public String getThreadImplReaderFnName(String commPrim, String tName, String varName) {
    if (commPrim != null) {
      return commPrim;
    } else {
      return tName + "_read_" + varName;
    }
  }
  
  public String getThreadImplWriterFnName(String commPrim, String tName, String varName) {
    if (commPrim != null) {
      return commPrim;
    } else {
      return tName + "_write_" + varName;
    }
  }
  
  public String getReaderFnName() {
    ThreadImplementationNames tin = new ThreadImplementationNames(dp.getOwner());
    return getThreadImplReaderFnName(dp.getCommprimFnNameOpt(), tin.getNormalizedName(), getName());
  }
              
  public String getWriterFnName() {
    ThreadImplementationNames tin = new ThreadImplementationNames(dp.getOwner());
    return getThreadImplWriterFnName(dp.getCommprimFnNameOpt(), tin.getNormalizedName(), getName());
  }
  
  //////////////////////////////////////////////////////////////
  //
  // Names for dispatcher thread global data for read/write ports
  // and shared variables. 
  // 
  //////////////////////////////////////////////////////////////

  public String getReaderWriterImplVar() {
    return "smaccm_" + getName() + "_var";
  }

  public String getMutex() {
    return "smaccm_" + dp.getName() + "_mutex";
  }
  
  ////////////////////////////////////////////////////////////
  // 
  // Decl functions; these should be eliminated and replaced
  // with a <type> <varName> declaration in stringtemplate.
  //
  ////////////////////////////////////////////////////////////
  
  public String getQueueDataDecl()  { 
    if (!(dp instanceof InputEventPort)) {
      throw new Aadl2RtosException("Error: getQueueDecl: port " + dp.getName() + " is not an input event port so has no queue.");
    }
    InputEventPort iep = (InputEventPort)dp; 
    return iep.getQueueType().getCType().varString(getQueueDataName());
  }
  
  
  public String getQueueFrontDecl() {
    return indexType.getCType().varString(getQueueFrontName());
  }
  
  public String getQueueBackDecl() {
    return indexType.getCType().varString(getQueueBackName()); 
  }

  public String getQueueFullDecl() {
    return (new BoolType()).getCType().varString(getQueueBackName()); 
  }

  ////////////////////////////////////////////////////////////
  // 
  // FnCall and Stmt functions; these should be eliminated and replaced
  // with a <varName>(<args>) declaration in stringtemplate.
  //
  ////////////////////////////////////////////////////////////


  ////////////////////////////////////////////////////////////
  // 
  // FnCall and Stmt functions; these should be eliminated and replaced
  // with a <varName>(<args>) declaration in stringtemplate.
  //
  ////////////////////////////////////////////////////////////

  public String getIsEmptyFnCall() {
    return getQueueIsEmptyName() + "()"; 
  }
  
  public String getIsFullFnCall() {
    return getQueueIsFullName() + "()"; 
  }
  
  public String getGlobalDataSlot() {
    return getGlobalData() + "[*" + getGlobalIndex() + "]";
  }
  
  public String getVarRef() {
    return this.getType().getName() + this.getName(); 
  }

  public String getReaderWriterImplVarDecl() {
    return dp.getType().getCType().varString(getReaderWriterImplVar());
  }
   
};
