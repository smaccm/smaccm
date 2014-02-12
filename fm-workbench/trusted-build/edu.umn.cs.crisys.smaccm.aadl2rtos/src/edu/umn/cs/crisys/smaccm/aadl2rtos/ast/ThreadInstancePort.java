/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.type.ArrayType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.type.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.type.Type;

/**
 * @author Whalen
 *
 */
public class ThreadInstancePort {
  MyPort port;
  ThreadInstance inst; 

  public ThreadInstancePort(ThreadInstance inst, MyPort port) {
    this.port = port;
    this.inst = inst;
  }
  
  public MyPort getPort() {
    return this.port;
  }
  
  public ThreadInstance getThreadInstance() {
    return this.inst;
  }
  
  public String getNameRoot() {
    return port.getName() + "_Instance_" + this.inst.getThreadId();
  }
  // semaphore names, because they are defined in the .prx file, 
  // must be lower case.
  public String getMutexName() {
    return ("mutex_" + getNameRoot()).toLowerCase() ; 
  }
  
  public String getMutexDefine() {
    return ("MUTEX_ID_" + getMutexName()).toUpperCase();
  }
  
  public String getVarName() {
    return "var_" + getNameRoot(); 
  }
  
  public String getQueueSizeName() {
    return ("QS_" + getNameRoot()).toUpperCase();
  }
  
  public String getIsFullName() {
	  return "is_full_" + getNameRoot(); 
  }
  
   // Functions for input event ports ONLY.  This is why we need to split this
  // class up.
  public String getCircBufferFrontVarName() {
    return this.getVarName() + "_front";
  }
  
  public String getCircBufferBackVarName() {
    return this.getVarName() + "_back";
  }

  public Type getType() {
    Type ty = this.getPort().getDataType();
    return ty;
  }
  
  public int getArraySize() {
	  return this.getPort().getQueueSize();
  }
  
  public Type getQueueType() {
    Type ty = this.getPort().getDataType();
    return new ArrayType(ty, this.getArraySize());
  }
  
  public Type getCircRefType() {
	  if (this.getArraySize() <= 256) {
		  return new IntType(8, false);
	  } else if (this.getArraySize() <= 65536) {
		  return new IntType(16, false);
	  } else {
         return new IntType(32, false);
	  }
  }

  
}
