package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;


import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.ThreadUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public abstract class Port {

	protected static int portIDCounter = 0;
	private int portID = 0;
	org.osate.aadl2.Port aadlPort; 
	
	private String functionName;
	private String headerFileName;
	private String portName;
	private ThreadImplementation owner; 
	private Type dataType = null;
	// private DataClassifier classifier = null;


	public Port(org.osate.aadl2.Port port, Type dataType, ThreadImplementation owner) {
		this.portName = port.getName();
		this.dataType = dataType;
		this.owner = owner;
		this.portID = portIDCounter;
		Port.portIDCounter++;

		try {
      this.headerFileName = Util.getStringValue(port,ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_HEADER);
		  this.functionName = Util.getStringValue(port,ThreadUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
		} catch (Exception e) {
      throw new Aadl2RtosException("For port: " + this.getName() + 
          "either headerFileName or functionName property is not defined.  Original error: " + 
          e.toString());
    }
		
		/* This belongs in the creation logic 
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
			queueSize = (int) PropertyUtils.getIntegerValue(port,
					ThreadUtil.QUEUE_SIZE);
		} else if (port.getCategory() == PortCategory.EVENT_DATA) {
			if (port.getDirection() == DirectionType.IN) {
				this.portType = PortType.INPUT_EVENT_DATA_PORT;
				this.direction = DirectionType.IN;
			} else {
				this.portType = PortType.OUTPUT_EVENT_DATA_PORT;
				this.direction = DirectionType.OUT;
			}
			category = PortCategory.EVENT_DATA;
			queueSize = (int) PropertyUtils.getIntegerValue(port,
					ThreadUtil.QUEUE_SIZE);
		}
    */
		
		// System.out.println("Port type: " + portType.toString());
		// System.out.println("Port id: " + portID);
		// System.out.println("Port name: " + portName);
		// System.out.println("queueSize: " + queueSize);
	}


	public Type getType() {
		return dataType;
	}

	public String getName() {
		return portName;
	}

	public int getPortID() {
		return portID;
	}

  public org.osate.aadl2.Port getAADLPort( ) {
    return aadlPort;
  }
  
  public String getFunctionName() {
    return functionName;
  }
  
  public String getHeaderFileName() {
    return headerFileName;
  }
  
  public ThreadImplementation getOwner() {
    return owner;
  }
}