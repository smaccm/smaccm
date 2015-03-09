/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
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

  public DispatcherNames getDispatcher() {
    if (dp instanceof InputEventPort) {
      Dispatcher d = ((InputEventPort)dp).getOptDispatcher();
      if (d != null) {
        return new DispatcherNames(d);
      }
    }
    throw new Aadl2RtosException("GetDispatcher called on port without a dispatcher.");
  }
  
  public boolean getHasInitializeEntrypoint() {
    boolean result = dp.getInitializeEntrypointSourceText() != null;
    return result;
  }
  
  public ExternalHandlerNames getInitializeEntrypointOpt() {
    return new ExternalHandlerNames(dp.getInitializeEntrypointSourceText());
  }
  
  //////////////////////////////////////////////////////////
  //
  // 'root' name and elements.
  // 
  //////////////////////////////////////////////////////////
  
  public String getName() {
    return dp.getName();
  }
  
  public String getQualifiedName() {
    return dp.getOwner().getName() + "_" + getName();
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
  
  public boolean getHasDispatcher() {
    if (dp instanceof InputEventPort) {
      return ((InputEventPort)dp).getOptDispatcher() != null; 
    }
    return false;
  }
  
  public boolean getIsOutputDataPort() {
    return (dp instanceof OutputDataPort);
  }
  
  public boolean getIsOutputEventPort() {
    return (dp instanceof OutputEventPort);
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
    
  public String getIdlDispatcherName() {
    return "dispatch_" + getName();
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
  
  public String getAadlReaderFnName() {
    ThreadImplementationNames tin = new ThreadImplementationNames(dp.getOwner());
    return getThreadImplReaderFnName(dp.getCommprimFnNameOpt(), tin.getNormalizedName(), getName());
  }
              
  public String getAadlWriterFnName() {
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
    return (new BoolType()).getCType().varString(getQueueFullName()); 
  }



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
