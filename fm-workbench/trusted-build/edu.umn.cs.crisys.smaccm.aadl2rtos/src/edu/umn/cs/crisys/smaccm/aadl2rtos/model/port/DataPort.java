package edu.umn.cs.crisys.smaccm.aadl2rtos.model.port;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.PortConnection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

public abstract class DataPort {


  private String name;
  private ThreadImplementation owner = null;
  private Type dataType = null;
  private String commprimFnNameOpt = null;
  private String commprimHeaderNameOpt = null;
  private ExternalHandler initializeEntrypointSourceText = null;
  private String sourceText = null;
  private List<PortConnection> connections = new ArrayList<PortConnection>(); 
  private List<String> implementationFileList = new ArrayList<String>();

	public DataPort(String portName, 
	                Type dataType,
	                ThreadImplementation owner) {
		this.name = portName;
		this.dataType = dataType;
		this.owner = owner;
	}

	
	
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String portName) {
    this.name = portName;
  }

  
  /**
   * @return the initializeEntrypointSourceText
   */
  public ExternalHandler getInitializeEntrypointSourceText() {
    return initializeEntrypointSourceText;
  }



  /**
   * @param initializeEntrypointSourceText the initializeEntrypointSourceText to set
   */
  public void setInitializeEntrypointSourceText(
      ExternalHandler initializeEntrypointSourceText) {
    this.initializeEntrypointSourceText = initializeEntrypointSourceText;
  }

  /**
   * @return the owner
   */
  public ThreadImplementation getOwner() {
    return owner;
  }

  /**
   * @param owner the owner to set
   */
  public void setOwner(ThreadImplementation owner) {
    this.owner = owner;
  }

  /**
   * @return the dataType
   */
  public Type getType() {
    return dataType;
  }

  /**
   * @param dataType the dataType to set
   */
  public void setDataType(Type dataType) {
    this.dataType = dataType;
  }

  /**
   * @return the commprimFnNameOpt
   */
  public String getCommprimFnNameOpt() {
    return commprimFnNameOpt;
  }

  /**
   * @param commprimFnNameOpt the commprimFnNameOpt to set
   */
  public void setCommprimFnNameOpt(String commprimFnNameOpt) {
    this.commprimFnNameOpt = commprimFnNameOpt;
  }

  /**
   * @return the commprimHeaderNameOpt
   */
  public String getCommprimHeaderNameOpt() {
    return commprimHeaderNameOpt;
  }

  /**
   * @param commprimHeaderNameOpt the commprimHeaderNameOpt to set
   */
  public void setCommprimHeaderNameOpt(String commprimHeaderNameOpt) {
    this.commprimHeaderNameOpt = commprimHeaderNameOpt;
  }

  /**
   * @return the sourceText
   */
  public String getSourceText() {
    return sourceText;
  }

  /**
   * @param sourceText the sourceText to set
   */
  public void setSourceText(String sourceText) {
    this.sourceText = sourceText;
  }

  /**
   * @return the connections
   */
  public List<PortConnection> getConnections() {
    return connections;
  }

  /**
   * @param connections the connections to set
   */
  public void setConnections(List<PortConnection> connections) {
    this.connections = connections;
  }

  public void addConnection(PortConnection conn) {
    this.connections.add(conn);
  }

	@Override
	public int hashCode() {
		return name.hashCode() * dataType.hashCode();
	}

  @Override
  public boolean equals(Object other) {
    if (other instanceof DataPort) {
      DataPort newPort = (DataPort) other; 
      return (newPort.getName().equalsIgnoreCase(this.getName())
          && (newPort.getOwner() == this.getOwner())); 
    }
    return false;
  }

  public boolean hasData() {
    return !(this.getType() instanceof UnitType); 
  }

  public List<String> getImplementationFileList() {
    return implementationFileList;
  }

  public void setImplementationFileList(List<String> implementationFileList) {
    if (implementationFileList == null) {
    	this.implementationFileList = new ArrayList<>();
    }
    else {
    	this.implementationFileList = implementationFileList;
    }
  }


}
