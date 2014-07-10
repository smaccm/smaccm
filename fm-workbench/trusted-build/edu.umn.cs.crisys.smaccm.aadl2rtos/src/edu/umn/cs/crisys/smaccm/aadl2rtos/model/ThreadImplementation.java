package edu.umn.cs.crisys.smaccm.aadl2rtos.model;

/**
 * @author Mead, Whalen
 * 
 * TODO:    In "ThreadImplementation"
 *    ! FIXED: only one thread instance for thread implementation (should be list)
 *    -> ISR threads are not distinguished from "regular" threads
 * 
 * TODO: Why the fuck do we have "legacy semaphores" stored in threads?
 * TODO: Get rid of isrThread, all the other ISR shit.
 * 
 */

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;

public class ThreadImplementation extends ThreadImplementationBase {

	private ExternalHandler initEntrypointHandler = null;
	
	private List<ThreadInstance> threadInstanceList = new ArrayList<ThreadInstance>();
	private ArrayList<Dispatcher> dispatcherList = new ArrayList<Dispatcher>();
	private String dispatchProtocol; 
	
	private String smaccmSysSignalOpt = null;
	private String isrHandlerName = null;
	
	// Data port lists
	private ArrayList<InputDataPort> inputDataPortList = new ArrayList<InputDataPort>();
private ArrayList<OutputDataPort> outputDataPortList = new ArrayList<OutputDataPort>();
	private ArrayList<InputEventPort> inputEventPortList = new ArrayList<InputEventPort>();
	private ArrayList<OutputEventPort> outputEventPortList = new ArrayList<OutputEventPort>();
	private ArrayList<OutputEventPort> outputEventDataPortList = new ArrayList<OutputEventPort>();
	private ArrayList<InputEventPort> inputEventDataPortList = new ArrayList<InputEventPort>();
	private ArrayList<SharedDataAccessor> accessorList = new ArrayList<SharedDataAccessor>();
	private ArrayList<String> legacySemaphoreList = new ArrayList<String>();

	private boolean isrThread = false;

	// Constructor
	public ThreadImplementation(String name, int priority, int stackSize, List<String> fileNames, 
	    String generatedEntrypoint, boolean isrThread, String smaccmSysSignalOpt, String isrHandlerName, 
	    String entryPointSourceText, String entryPointFile, String dispatchProtocol, int Period, 
	    List<String> entryPointList) {
	  super(name, priority, stackSize, fileNames);
	  this.generatedEntrypoint = generatedEntrypoint;
	  this.smaccmSysSignalOpt = smaccmSysSignalOpt;
	  this.isrThread = isrThread;
	  this.isrHandlerName = isrHandlerName;

	  // create initializer handler, if it exists.
    if (entryPointSourceText != null && entryPointFile != null) {
      initEntrypointHandler = new ExternalHandler(
          entryPointSourceText, entryPointFile);
    }
    
    this.dispatchProtocol = dispatchProtocol;
    
    if ((dispatchProtocol != null)
        && (dispatchProtocol.equalsIgnoreCase("Periodic") || 
            dispatchProtocol.equalsIgnoreCase("Hybrid"))) {
      
      // TODO: IGNORE THIS CRAP.  Look at your dispatchers *only*.  Yuck.
      // if periodic or hybrid, thread should have a period and a
      // compute entrypoint
      try {
        
        // TODO: fix this! We don't know how long the list of
        // handlers is!
        List<ExternalHandler> handlerList = new ArrayList<ExternalHandler>();
        for (String s: entryPointList) {
          ExternalHandler periodicHandler = new ExternalHandler(
            s, fileNames.get(0));
          handlerList.add(periodicHandler);
        }
        PeriodicDispatcher dispatcher = new PeriodicDispatcher(this, handlerList, Period);
        dispatcherList.add(dispatcher);
      } catch (Exception e) {
        throw new Aadl2RtosException(
            "For thread "
                + this.getName()
                + " with dispatch protocol "
                + (dispatchProtocol.toString())
                + " properties: 'Period', 'Compute_Entrypoint_Source_Text', and 'Source_Text' are required.");
      }
    }
  }

	/*
	public ThreadImplementation(ThreadTypeImpl tti) {
		super(tti);
		generatedEntrypoint = tti.getFullName();

		// determine whether this thread is 'normal' or ISR.
		smaccmSysSignalOpt = Util.getStringValueOpt(tti, ThreadUtil.SMACCM_SYS_SIGNAL_NAME);
		isrThread = (getSmaccmSysSignalOpt() != null);
		if (isrThread) {
			isrHandlerName = Util.getStringValueOpt(tti, ThreadUtil.ISR_HANDLER);
			if (isrHandlerName == null) {
				throw new Aadl2RtosException("Error: ISR Thread: "+ tti.getFullName() + " requires ISR_handler property");
			}
		}

		// create initializer handler, if it exists.
		String entryPointSourceText = (String) Util.getStringValueOpt(tti,
				ThreadUtil.INITIALIZE_ENTRYPOINT_SOURCE_TEXT);

		String entryPointFile = fileNames.get(0);
		if (entryPointSourceText != null && entryPointFile != null) {
			initEntrypointHandler = new ExternalHandler(
					entryPointSourceText, entryPointFile);
		}

		// determine and store dispatch protocol
		try {
			dispatchProtocol = ThreadUtil.getDispatchProtocol(tti);
		} catch (Exception e) {
			throw new Aadl2RtosException(
					"Dispatch protocol not found for thread: " + this.getName());
		}

		if ((dispatchProtocol != null)
				&& (dispatchProtocol.getName().equalsIgnoreCase("Periodic") || dispatchProtocol
						.getName().equalsIgnoreCase("Hybrid"))) {
			// if periodic or hybrid, thread should have a period and a
			// compute entrypoint
			try {
				int period = (int) PropertyUtils.getIntegerValue(tti, ThreadUtil.PERIOD);
				List<String> entrypointNameList = 
						ThreadUtil.getComputeEntrypointList(tti); 

				// TODO: fix this! We don't know how long the list of
				// handlers is!
				List<ExternalHandler> handlerList = new ArrayList<ExternalHandler>();
				for (String s: entrypointNameList) {
					ExternalHandler periodicHandler = new ExternalHandler(
						s, fileNames.get(0));
					handlerList.add(periodicHandler);
				}
				Dispatcher dispatcher = new Dispatcher(this, period, handlerList);
				dispatcherList.add(dispatcher);
			} catch (Exception e) {
				throw new Aadl2RtosException(
						"For thread "
								+ this.getName()
								+ " with dispatch protocol "
								+ (dispatchProtocol.toString())
								+ " properties: 'Period', 'Compute_Entrypoint_Source_Text', and 'Source_Text' are required.");
			}
		}
	}
  */
	
  public List<SharedDataAccessor> getSharedDataAccessors() {
    return this.accessorList;
  }
  
  public void addSharedDataAccessor(SharedDataAccessor sda) {
    this.accessorList.add(sda);
  }
  
	/*
	private void createPortLists(ThreadTypeImpl tti, AstHelper astHelper) {
		// Create port lists
		EList<DataPort> dataPortList = tti.getOwnedDataPorts();
		EList<EventPort> eventPortList = tti.getOwnedEventPorts();
		EList<EventDataPort> eventDataPortList = tti.getOwnedEventDataPorts();

		ArrayList<Port> portList = new ArrayList<Port>();
		portList.addAll(dataPortList);
		portList.addAll(eventPortList);
		portList.addAll(eventDataPortList);

		for (Port port : portList) {
			Type dataType = null;

			DataClassifier classifier = (DataClassifier) port.getClassifier();
			String dcName = Util.normalizeAadlName(classifier);

			if (astHelper.astTypes.containsKey(dcName)) {
				dataType = new IdType(dcName);
			} else {
				dataType = astHelper.createAstType(classifier);
			}
		}
	}
*/
	
	public void addDispatcher(Dispatcher d) {
	  this.dispatcherList.add(d);
	}
	
	public List<Dispatcher> getDispatcherList() {
	  return this.dispatcherList; 
	}

	
	public boolean isISRThread() {
		return isrThread;
	}
	
	public List<String> getLegacySemaphores() {
	    return this.legacySemaphoreList;
	}
	  
	public String getSmaccmSysSignalOpt() {
		return smaccmSysSignalOpt;
	}

	public String getISRHandlerName() {
		return isrHandlerName;
	}

	public ExternalHandler getInitializeEntrypointOpt() {
		return this.initEntrypointHandler;
	}


	public void addThreadInstance(ThreadInstance instance) {
		threadInstanceList.add(instance);
	}

	public List<ThreadInstance> getThreadInstanceList() {
		return threadInstanceList;
	}

	
	public String getDispatchProtocol() {
	  return this.dispatchProtocol;
	}
	
	public int getSignalNumberForInputEventPort(InputEventPort port) {

		for (int i = 0; i < dispatcherList.size(); i++) {
		  Dispatcher d = dispatcherList.get(i);
      if (d instanceof InputEventDispatcher) {
        InputEventDispatcher id = (InputEventDispatcher)d;
        if (id.getEventPort() == port) {
          return i;
        }
      }
		}
		return -1; // didn't find it.
	}
	
	public int getSignalNumberForDispatcher(Dispatcher dispatcher) {
		for (int i = 0; i < dispatcherList.size(); i++) {
		  if (dispatcher.equals(dispatcherList.get(i))) {
		    return i;
		  }
		}
		throw new Aadl2RtosException("Unable to find expected dispatcher in thread " + this.getName()) ;
	}
	
	public List<ThreadInstancePort> getThreadInstanceInputPorts() {
	  ArrayList<ThreadInstancePort> tips = new ArrayList<ThreadInstancePort>();
	  
	  for (ThreadInstance ti: this.getThreadInstanceList()) {
	    tips.addAll(ti.getThreadInstanceInputPorts());
	  }
	  return tips;
	}
	
  
  /**
   * @return the inputDataPortList
   */
  public ArrayList<InputDataPort> getInputDataPortList() {
    return inputDataPortList;
  }

  /**
   * @param inputDataPortList the inputDataPortList to set
   */
  public void setInputDataPortList(ArrayList<InputDataPort> inputDataPortList) {
    this.inputDataPortList = inputDataPortList;
  }

  public void addInputDataPort(InputDataPort idp) {
    this.inputDataPortList.add(idp);
  }

  /**
   * @return the outputDataPortList
   */
  public ArrayList<OutputDataPort> getOutputDataPortList() {
    return outputDataPortList;
  }

  /**
   * @param outputDataPortList the outputDataPortList to set
   */
  public void setOutputDataPortList(ArrayList<OutputDataPort> outputDataPortList) {
    this.outputDataPortList = outputDataPortList;
  }

  public void addOutputDataPort(OutputDataPort odp) {
    this.outputDataPortList.add(odp);
  }
  /**
   * @return the inputEventPortList
   */
  public ArrayList<InputEventPort> getInputEventPortList() {
    return inputEventPortList;
  }

  /**
   * @param inputEventPortList the inputEventPortList to set
   */
  public void setInputEventPortList(ArrayList<InputEventPort> inputEventPortList) {
    this.inputEventPortList = inputEventPortList;
  }
  
  public void addInputEventPort(InputEventPort inputEventPort) {
    this.inputEventPortList.add(inputEventPort);
  }

  /**
   * @return the outputEventPortList
   */
  public ArrayList<OutputEventPort> getOutputEventPortList() {
    return outputEventPortList;
  }

  /**
   * @param outputEventPortList the outputEventPortList to set
   */
  public void setOutputEventPortList(
      ArrayList<OutputEventPort> outputEventPortList) {
    this.outputEventPortList = outputEventPortList;
  }
  
  public void addOutputEventPort(OutputEventPort oep) {
    this.outputEventPortList.add(oep);
  }

  /**
   * @return the outputEventDataPortList
   */
  public ArrayList<OutputEventPort> getOutputEventDataPortList() {
    return outputEventDataPortList;
  }

  /**
   * @param outputEventDataPortList the outputEventDataPortList to set
   */
  public void setOutputEventDataPortList(
      ArrayList<OutputEventPort> outputEventDataPortList) {
    this.outputEventDataPortList = outputEventDataPortList;
  }

  public void addOutputEventDataPort(OutputEventPort oep) {
    this.outputEventDataPortList.add(oep);
  }
  
  /**
   * @return the inputEventDataPortList
   */
  public ArrayList<InputEventPort> getInputEventDataPortList() {
    return inputEventDataPortList;
  }

  /**
   * @param inputEventDataPortList the inputEventDataPortList to set
   */
  public void setInputEventDataPortList(
      ArrayList<InputEventPort> inputEventDataPortList) {
    this.inputEventDataPortList = inputEventDataPortList;
  }
	
	public void addInputEventDataPort(InputEventPort iep) {
	  this.inputEventDataPortList.add(iep);
	}

  /**
   * @return
   */
  public List<InputPort> getInputPorts() {
    // TODO Auto-generated method stub
    ArrayList<InputPort> inputPorts = new ArrayList<InputPort>();
    inputPorts.addAll(this.inputDataPortList);
    inputPorts.addAll(this.inputEventDataPortList);
    inputPorts.addAll(this.inputEventPortList);
    return inputPorts;
  }
  
  public List<OutputPort> getOutputPorts() {
    ArrayList<OutputPort> outputPorts = new ArrayList<OutputPort>();
    outputPorts.addAll(this.outputDataPortList);
    outputPorts.addAll(this.outputEventDataPortList);
    outputPorts.addAll(this.outputEventPortList);
    return outputPorts;
  }

  /**
   * @return
   */
  public List<DataPort> getPortList() {
    ArrayList<DataPort> ports = new ArrayList<DataPort>();
    ports.addAll(getOutputPorts());
    ports.addAll(getInputPorts()); 
    return ports;
  }
  
  
}