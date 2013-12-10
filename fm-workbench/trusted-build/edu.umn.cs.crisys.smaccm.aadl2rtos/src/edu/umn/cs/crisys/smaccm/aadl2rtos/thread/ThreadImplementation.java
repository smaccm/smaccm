package edu.umn.cs.crisys.smaccm.aadl2rtos.thread;

/**
 * @author Mead, Whalen
 * 
 * TODO:    In "ThreadImplementation"
 *    ! FIXED: only one thread instance for thread implementation (should be list)
 *    -> ISR threads are not distinguished from "regular" threads
 * 
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.AstHelper;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.ThreadUtil;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class ThreadImplementation extends ThreadImplementationBase {

	private ExternalHandler initEntrypointHandler = null;
	
	private List<ThreadInstance> threadInstanceList = new ArrayList<ThreadInstance>();
	private ArrayList<Dispatcher> dispatcherList = new ArrayList<Dispatcher>();
	private EnumerationLiteral dispatchProtocol; 
	
	private String smaccmSysSignalOpt = null;
	private String isrHandlerName = null;
	
	// Data port lists
	private ArrayList<MyPort> inputDataPortList = new ArrayList<MyPort>();
	private ArrayList<MyPort> outputDataPortList = new ArrayList<MyPort>();
	private ArrayList<MyPort> inputEventPortList = new ArrayList<MyPort>();
	private ArrayList<MyPort> outputEventPortList = new ArrayList<MyPort>();
	private ArrayList<MyPort> outputEventDataPortList = new ArrayList<MyPort>();
	private ArrayList<MyPort> inputEventDataPortList = new ArrayList<MyPort>();
	private ArrayList<SharedDataAccessor> accessorList = new ArrayList<SharedDataAccessor>();
	private ArrayList<String> legacySemaphoreList = new ArrayList<String>();

	private boolean isrThread = false;

	// Constructor
	public ThreadImplementation(ThreadTypeImpl tti, AstHelper astHelper) {
		super(tti, astHelper);
		generatedEntrypoint = tti.getFullName();

		// determine whether this thread is 'normal' or ISR.
		smaccmSysSignalOpt = Util.getStringValueOpt(tti, ThreadUtil.SMACCM_SYS_SIGNAL_NAME);
		isrHandlerName = Util.getStringValueOpt(tti, ThreadUtil.ISR_HANDLER);
		isrThread = (getSmaccmSysSignalOpt() != null);

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
	// TODO: remove isDestination!
	public void addPort(MyPort newPort) {
		// Make sure this is not a duplicate.
		ArrayList<MyPort> portList = getPortList();
		for (MyPort port : portList) {
		  if (newPort.equals(port)) {
				return;
			}
		}

		if (newPort.isInputDataPort()) {
			inputDataPortList.add(newPort);
		} else if (newPort.isOutputDataPort()) {
			outputDataPortList.add(newPort);
		} else if (newPort.isInputEventPort()) {
			inputEventPortList.add(newPort);
		} else if (newPort.isOutputEventPort()) {
			outputEventPortList.add(newPort);
		} else if (newPort.isInputEventDataPort()) {
			inputEventDataPortList.add(newPort);
		} else if (newPort.isOutputEventDataPort()) {
			outputEventDataPortList.add(newPort);
		}
	}

	public ArrayList<MyPort> getOutputEventPortList() {
		ArrayList<MyPort> portList = new ArrayList<MyPort>();
		portList.addAll(outputEventPortList);
		return portList;
	}

	public ArrayList<MyPort> getOutputEventDatatPortList() {
		return outputEventDataPortList;
	}

	public List<MyPort> getInputDataPorts() {
		return inputDataPortList;
	}

	public List<MyPort> getOutputPorts() {
    ArrayList<MyPort> portList = new ArrayList<MyPort>();
    portList.addAll(outputEventPortList);
    portList.addAll(outputEventDataPortList);
    portList.addAll(this.outputDataPortList);
    return portList;
	}
	
	public List<MyPort> getInputPorts() {
    ArrayList<MyPort> portList = new ArrayList<MyPort>();
    portList.addAll(inputEventPortList);
    portList.addAll(inputEventDataPortList);
    portList.addAll(this.inputDataPortList);
    return portList;
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

	public ArrayList<MyPort> getPortList() {
		ArrayList<MyPort> portList = new ArrayList<MyPort>();
		portList.addAll(inputDataPortList);
		portList.addAll(outputDataPortList);
		portList.addAll(inputEventPortList);
		portList.addAll(outputEventPortList);
		portList.addAll(inputEventDataPortList);
		portList.addAll(outputEventDataPortList);
		return portList;
	}

	public ArrayList<MyPort> getEventPorts() {
		ArrayList<MyPort> portList = new ArrayList<MyPort>();
		portList.addAll(inputEventPortList);
		portList.addAll(outputEventPortList);
		portList.addAll(inputEventDataPortList);
		portList.addAll(outputEventDataPortList);
		return portList;
	}

	public void addThreadInstance(ThreadInstance instance) {
		threadInstanceList.add(instance);
	}

	public List<ThreadInstance> getThreadInstanceList() {
		return threadInstanceList;
	}

	public ArrayList<MyPort> getInputEventPorts() {
		ArrayList<MyPort> portList = new ArrayList<MyPort>();
		portList.addAll(inputEventPortList);
		portList.addAll(inputEventDataPortList);
		return portList;
	}
	
	public EnumerationLiteral getDispatchProtocol() {
	  return this.dispatchProtocol;
	}
	
	public int getSignalNumberForInputEventPort(MyPort port) {
		int signal = -1;
		boolean found = false;

		for (int i = 0; i < dispatcherList.size(); i++) {
			if ((dispatcherList.get(i).getEventPort() == port) && !found) {
				signal = i;
				found = true;
				break;
			}
		}
		
		return signal;
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
}