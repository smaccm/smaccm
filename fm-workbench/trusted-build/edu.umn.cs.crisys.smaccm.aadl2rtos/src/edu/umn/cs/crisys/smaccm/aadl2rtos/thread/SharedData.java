/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;

import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Type;

/**
 * @author Whalen
 *
 * This class represents a shared data object in AADL.
 */
public class SharedData {

  private String portName;
  private Type dataType = null;

  public SharedData(String portName, Type dataType) {
    this.portName = portName;
    this.dataType = dataType;
  }
  
  public String getPortName() {
    return this.portName;
  }
  
  public String getMutexName() {
     return ("mutex_" + getPortName()).toLowerCase() ; 
  }

  public String getMutexDefine() {
    return ("MUTEX_ID_" + getMutexName()).toUpperCase();
  }
  
  public String getVarName() {
    return "var_" + getPortName(); 
  }
  
  public Type getDataType() {
    return this.dataType;
  }
  
}
