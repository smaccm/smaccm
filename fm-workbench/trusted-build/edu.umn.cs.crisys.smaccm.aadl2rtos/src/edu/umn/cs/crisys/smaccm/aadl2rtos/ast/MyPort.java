package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

/**
 * @author Mead, Whalen
 * 
 * TODO: I think we want separate classes for port types.
 * 
 */

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.DirectionType;
import org.osate.aadl2.Port;
import org.osate.aadl2.PortCategory;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.PortUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.ThreadUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class MyPort {

	// private static int portIDCounter = 0;
	// private int portID = 0;
	private int queueSize = 0;

	private String portName;


	// TODO: refactor this into a proper hierarchy!
	private ThreadImplementation owner = null;
	//private InterruptServiceRoutine isrOwner = null;
	private Type dataType = null;
	private PortType portType = null;
	private PortCategory category = null;
	private DirectionType direction = null;
	private List<String> entrypointSourceText = null;
	private String commprimFnNameOpt = null;
	private String commprimHeaderNameOpt = null;
	private String sourceText = null;
	private List<Connection> connections = new ArrayList<Connection>(); 

	public enum PortType {
		INPUT_EVENT_PORT, OUTPUT_EVENT_PORT, INPUT_DATA_PORT, OUTPUT_DATA_PORT, INPUT_EVENT_DATA_PORT, OUTPUT_EVENT_DATA_PORT, OUTPUT_ISR
	}

	public static void init() {
	  
	}
	
	public MyPort(InterruptServiceRoutine isr) {
	  this.direction = DirectionType.OUT;
	  this.portType = PortType.OUTPUT_ISR;
	  //this.isrOwner = isr;
	  this.portName = "ISR_PORT";
	  this.category = PortCategory.EVENT;
	}
	
	// For "manually created" ports
	public MyPort(String portName, 
	              List<String> entrypointSourceText,
	              String sourceText,
                String commprimFnNameOpt,
                String commprimHeaderNameOpt,
	              Type dataType, 
	              ThreadImplementation owner, 
	              PortType portType) {
	  this.portName = portName;
	  this.entrypointSourceText = entrypointSourceText;
	  this.commprimFnNameOpt = commprimFnNameOpt;
	  this.commprimHeaderNameOpt = commprimHeaderNameOpt;
	  this.sourceText = sourceText;
	  this.dataType = dataType;
	  this.owner = owner;
	  this.portType = portType;
	  if (portType == PortType.INPUT_DATA_PORT ||
	      portType == PortType.INPUT_EVENT_DATA_PORT || 
	      portType == PortType.INPUT_EVENT_PORT) {
	    this.direction = DirectionType.IN;
	  } else {
	    this.direction = DirectionType.OUT;
	  }
	  if (portType == PortType.INPUT_DATA_PORT ||
	      portType == PortType.OUTPUT_DATA_PORT) {
	    this.category = PortCategory.DATA;
	  } else if (portType == PortType.INPUT_EVENT_PORT || 
	           portType == PortType.OUTPUT_EVENT_PORT || 
	           portType == PortType.OUTPUT_ISR) {
	    this.category = PortCategory.EVENT;
	  } else if (portType == PortType.INPUT_EVENT_DATA_PORT ||
	             portType == PortType.OUTPUT_EVENT_DATA_PORT) {
	    this.category = PortCategory.EVENT_DATA;
	  } else {
	    throw new Aadl2RtosException("Unhandled port type in MyPort constructor.");
	  }
	}
	
	public MyPort(Port port, Type dataType, ThreadImplementation owner) {
		this.portName = port.getName();
		this.entrypointSourceText = ThreadUtil.getComputeEntrypointList(port);
		this.commprimFnNameOpt = Util.getStringValueOpt(port, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
		this.commprimHeaderNameOpt = Util.getStringValueOpt(port, ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
		this.sourceText = Util.getStringValueOpt(port,ThreadUtil.SOURCE_TEXT);
		// this.classifier = (DataClassifier) port.getClassifier();
		this.dataType = dataType;
		this.owner = owner;
		
		if (port.getCategory() == PortCategory.DATA) {
			if (port.getDirection() == DirectionType.IN) {
				this.portType = PortType.INPUT_DATA_PORT;
				this.direction = DirectionType.IN;
			} else {
				this.portType = PortType.OUTPUT_DATA_PORT;
				this.direction = DirectionType.OUT;
			}
			category = PortCategory.DATA;
		} else if (port.getCategory() == PortCategory.EVENT) {
			if (port.getDirection() == DirectionType.IN) {
				this.portType = PortType.INPUT_EVENT_PORT;
				this.direction = DirectionType.IN;
			} else {
				this.portType = PortType.OUTPUT_EVENT_PORT;
				this.direction = DirectionType.OUT;
			}
			category = PortCategory.EVENT;
			queueSize = PortUtil.getQueueSize(port);
		} else if (port.getCategory() == PortCategory.EVENT_DATA) {
			if (port.getDirection() == DirectionType.IN) {
				this.portType = PortType.INPUT_EVENT_DATA_PORT;
				this.direction = DirectionType.IN;
			} else {
				this.portType = PortType.OUTPUT_EVENT_DATA_PORT;
				this.direction = DirectionType.OUT;
			}
			category = PortCategory.EVENT_DATA;
			queueSize = PortUtil.getQueueSize(port);
			
		}

		// System.out.println("Port type: " + portType.toString());
		// System.out.println("Port id: " + portID);
		// System.out.println("Port name: " + portName);
		// System.out.println("queueSize: " + queueSize);
	}

	public PortType getPortType() {
		return portType;
	}

	public boolean isInputEventPort() {
		return portType == PortType.INPUT_EVENT_PORT;
	}

	public boolean isOutputEventPort() {
		return portType == PortType.OUTPUT_EVENT_PORT;
	}

	public boolean isInputDataPort() {
		return portType == PortType.INPUT_DATA_PORT;
	}

	public boolean isOutputDataPort() {
		return portType == PortType.OUTPUT_DATA_PORT;
	}

	public boolean isInputEventDataPort() {
		return portType == PortType.INPUT_EVENT_DATA_PORT;
	}

	public boolean isOutputEventDataPort() {
		return portType == PortType.OUTPUT_EVENT_DATA_PORT;
	}

	public boolean isOutputISRPort() {
	  return portType == PortType.OUTPUT_ISR;
	}
	
	public Type getDataType() {
		return dataType;
	}

	public DirectionType getDirection() {
		return direction;
	}

	public PortCategory getCategory() {
		return category;
	}

	public String getName() {
		return portName;
	}

	public List<String> getComputeEntrypointOpt() {
		return entrypointSourceText;
	}
	
	public String getCommPrimFnNameOpt() {
	  return this.commprimFnNameOpt; 
	}
	
	public String getCommPrimHeaderNameOpt() {
	  return this.commprimHeaderNameOpt;
	}
	
	public String getSourceFileOpt() {
	  return this.sourceText;
	}

	public int getPortID() {
	  if (this.getOwner() != null) {
	    return this.getOwner().getSignalNumberForInputEventPort(this);
	  } else {
	    throw new Aadl2RtosException("Port " + this.getName() + " does not have an owning thread");
	  }
	  
	}

	public void addConnection(Connection conn) {
	  this.connections.add(conn);
	}
	
	public List<Connection> getConnections() {
	  return connections;
	}
	
	// public void addDestination(String destThreadName) {
	//	portDestinations.add(destThreadName);
	// }

//	public List<String> getDestinationThreads() {
//		return portDestinations;
//	}

	public ThreadImplementation getOwner() {
	  return this.owner;
	}
	
	@Override
	public boolean equals(Object other) {
	  if (other instanceof MyPort) {
	    MyPort newPort = (MyPort) other; 
  	  return (newPort.getName().equalsIgnoreCase(this.getName())
          && newPort.getCategory().equals(this.getCategory().toString())
          && newPort.getDirection().equals(this.getDirection().toString())
          && (newPort.getOwner() == this.getOwner())); 
	  }
	  return false;
	}
	
	public int getQueueSize() {
	  return queueSize;
	}
	

	
/*
	public String getSourceThreadName() {
		return sourceThreadName;
	}

	public String getDestinationThreadName() {
		return destinationThreadName;
	}
*/
}