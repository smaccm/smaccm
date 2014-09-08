/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CommonNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.SourceDeclarations;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.ArrayType;
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
  
  
  //  For arguments. 
  
  public String getName() {
    return dp.getName();
  }
  
  
  public String getAsRefParam() {
    return Names.createRefParameter(dp.getType(), getName());
  }
  
  public String getVarDecl() {
    return dp.getType().getCType().varString(getName());
  }
  
  public boolean getHasData() {
    return dp.hasData(); 
  }
  
  //////////////////////////////////////////////////////////////
  //
  // Names for passive dispatch of passive components
  // 
  //////////////////////////////////////////////////////////////
  
  public String getDataParam() {
    return Names.createRefParameter(dp.getType(), getData()); 
  }

  public boolean getIsNotArray() {
    return !(dp.getType() instanceof ArrayType);
  }
  
  public String getIndexParam() {
    return Names.createRefParameter(indexType,  getIndex());
  }  

  public String getIndexDecl() {
    return indexType.getCType().varString(getIndex());
  }
  
  
  public String getGlobalMaxDispatchSize() {
    return "smaccm_max_tmp_array_" + getName(); 
  }
  
  public String getGlobalIndex() {
    return "smaccm_tmp_used_" + getName();
  }
  
  public String getGlobalIndexParam() {
    return Names.createRefParameter(indexType, getGlobalIndex());
  }
  
  public String getGlobalDataParam() {
    return Names.createRefParameter(dp.getType(), getGlobalData());
  }
  
  public String getGlobalMaxDispatchSizeParam() {
    return indexType.toString() + " " + getGlobalMaxDispatchSize(); 
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
  // Names for shared variable queues: 
  // 
  // Need: 
  //    Queue front
  //    Queue back
  //    Array for queue
  //    Name for isEmpty();
  //
  // It might be nice to have an idiom to create a "fresh" 
  // unique variable whenever we want.
  // 
  // createNewVarId();
  // 
  // Then whenever we perform assignments, we use this new var id
  // until the next call to createNewVarId().
  //
  //////////////////////////////////////////////////////////////
  
  public String getSharedVarBase() { return "smaccm_queue_"; }
  
  public String getQueueFrontName() { return "smaccm_queue_front_" + this.getName(); }
  public String getQueueBackName()  { return "smaccm_queue_back_" + this.getName(); }
  public String getQueueDataName()  { return "smaccm_queue_" + this.getName(); }
  public String getQueueIsEmptyName() { return "smaccm_queue_is_empty_" + this.getName(); }
  public String getQueueReadName() { return "smaccm_queue_read_" + this.getName(); }
  public String getQueueWriteName() { return "smaccm_queue_write_" + this.getName(); }
  
  public String getIsEmptyFnCall() {
    return getQueueIsEmptyName() + "()"; 
  }
  
  
  public String getReaderFnName() {
    return Names.getThreadImplReaderFnName(dp); 
  }
              
  public String getWriterFnName() {
    return Names.getThreadImplWriterFnName(dp); 
  }
  
  public String getGlobalDataSlot() {
    return getGlobalData() + "[" + getGlobalIndex() + "]";
  }
  
  public String getCopyToGlobalDataStmt() {
    if (dp.getType().isBaseType()) {
      return getGlobalDataSlot() + " = *" + getName() + ";";
    } else {
      return CommonNames.memcpyStmt(dp.getType(), getGlobalDataSlot(), getName()) + ";";
    }
  }
  
  public String getVarRef() {
    return Names.getVarRef(dp.getType(), getName());
  }

  public String getMutex() {
    return Names.getReaderWriterMutexName(dp);
  }
  
  public String getCamkesMutexLockFnCall() {
    return Names.getReaderWriterMutexName(dp) + "_lock()";
  }

  public String getCamkesMutexUnlockFnCall() {
    return Names.getReaderWriterMutexName(dp) + "_unlock()";
  }
  
  public String getReaderWriterImplVar() {
    return "smaccm_" + getName() + "_var";
  }

  public String getReaderWriterImplVarDecl() {
    return dp.getType().getCType().varString(getReaderWriterImplVar());
  }
  
  // NOTE: must be the same as DispatcherNames::getDispatcherName() for port dispatchers;
  public String getDispatcherName() {
    return "smaccm_" + dp.getOwner().getNormalizedName() + "_" + getName() + "_dispatcher";
  }
  
  public String getCopyToImplVarStmt() {
    if (dp.getType().isBaseType()) {
      return getReaderWriterImplVar() + " = *" + getName() + ";";
    } else {
      return CommonNames.memcpyStmt(dp.getType(), getReaderWriterImplVar(), getName()) + ";";
    }
  }
  
  public String getCopyFromImplVarStmt() {
    if (dp.getType().isBaseType()) {
      return "*" + getName() + " = " + getReaderWriterImplVar() + ";";
    } else {
      return CommonNames.memcpyStmt(dp.getType(), getName(), 
          CommonNames.getVarRef(dp.getType(), getReaderWriterImplVar())) + ";";
    }
  }

  public String getPassiveComponentDispatcherName() {
    return Names.getComponentInstanceName(dp.getOwner()) + "_" + 
        this.getDispatcherName(); 
  }  
  
};
