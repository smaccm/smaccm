/*
 * This class really should be a veneer over the OSATE AST 
 * ThreadWrapper, doing lookups as necessary for important information.
 * 
 * 
 * 
 */

package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.DirectionType;
import org.osate.aadl2.Element;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.PortCategory;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.impl.ListValueImpl;
import org.osate.aadl2.impl.PortImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Util;

public class ThreadWrapper {

	public ThreadTypeImpl thread; 
	private Model model;
	
	final public static String INITIALIZE_ENTRYPOINT_SOURCE_TEXT_NAME = "Initialize_Entrypoint_Source_Text"; 
	final public static String COMPUTE_ENTRYPOINT_SOURCE_TEXT_NAME = "Compute_Entrypoint_Source_Text";
	final public static String SOURCE_STACK_SIZE_NAME = "Source_Stack_Size";
	final public static String SOURCE_TEXT_NAME = "Source_Text";
	final public static String COMPUTE_EXECUTION_TIME_NAME = "Compute_Execution_Time";
	final public static String SMACCM_SYS_SIGNAL_NAME_NAME = "SMACCM_SYS::Signal_Name";
	final public static String PERIOD_NAME = "Period";

	final public static Property INITIALIZE_ENTRYPOINT_SOURCE_TEXT = Util
			.getPropertyDefinitionInWorkspace(INITIALIZE_ENTRYPOINT_SOURCE_TEXT_NAME);
	final public static Property COMPUTE_ENTRYPOINT_SOURCE_TEXT = Util
			.getPropertyDefinitionInWorkspace(COMPUTE_ENTRYPOINT_SOURCE_TEXT_NAME);
	final public static Property SOURCE_STACK_SIZE = Util
			.getPropertyDefinitionInWorkspace(SOURCE_STACK_SIZE_NAME);
	final public static Property SOURCE_TEXT = Util
			.getPropertyDefinitionInWorkspace(SOURCE_TEXT_NAME);
	final public static Property COMPUTE_EXECUTION_TIME = Util
			.getPropertyDefinitionInWorkspace(COMPUTE_EXECUTION_TIME_NAME);
	final public static Property SMACCM_SYS_SIGNAL_NAME = Util
			.getPropertyDefinitionInWorkspace(SMACCM_SYS_SIGNAL_NAME_NAME);
	final public static Property PERIOD = Util.getPropertyDefinitionInWorkspace(PERIOD_NAME);
	
	public ThreadWrapper(ThreadTypeImpl thread, Model model) {
		this.thread = thread;  
		this.model = model;
	}
	
	public String getName() { return Util.normalizeAadlName(thread); }

	// find the data ports associated with some "top level" element.
	public void findPorts(Element elem, List<PortImpl> dpList) {
		if (elem instanceof PortImpl) {
			PortImpl dpi = (PortImpl)elem;
			dpList.add(dpi); 
		}
		for (Element child: elem.getChildren()) {
			findPorts(child, dpList);
		}
	}
	
	public List<PortImplWrapper> getPorts() { 
		List<PortImpl> dpList = new ArrayList<PortImpl>();
		List<PortImplWrapper> portList = new ArrayList<PortImplWrapper>();
		
		findPorts(thread, dpList);
		for (PortImpl dpImp: dpList) {
			portList.add(new PortImplWrapper(model, dpImp));
		}
		return portList;
	}

	public List<PortImplWrapper> getInEventPorts() {
		List<PortImpl> dpList = new ArrayList<PortImpl>();
		List<PortImplWrapper> portList = new ArrayList<PortImplWrapper>();
		
		findPorts(thread, dpList);
		for (PortImpl dpImp: dpList) {
			PortImplWrapper piw = new PortImplWrapper(model, dpImp);
			if (piw.getDirection() == DirectionType.IN && 
				(piw.getCategory() == PortCategory.EVENT ||
				 piw.getCategory() == PortCategory.EVENT_DATA)) {
				portList.add(piw);
			}
		}
		return portList;
	}

	public List<PortImplWrapper> getInDataPorts() {
		List<PortImpl> dpList = new ArrayList<PortImpl>();
		List<PortImplWrapper> portList = new ArrayList<PortImplWrapper>();
		
		findPorts(thread, dpList);
		for (PortImpl dpImp: dpList) {
			PortImplWrapper piw = new PortImplWrapper(model, dpImp);
			if (piw.getDirection() == DirectionType.IN && 
				piw.getCategory() == PortCategory.DATA) {
				portList.add(piw);
			}
		}
		return portList;
	}

	public List<PortImplWrapper> getOutEventPorts() {
		List<PortImpl> dpList = new ArrayList<PortImpl>();
		List<PortImplWrapper> portList = new ArrayList<PortImplWrapper>();
		
		findPorts(thread, dpList);
		for (PortImpl dpImp: dpList) {
			PortImplWrapper piw = new PortImplWrapper(model, dpImp);
			if (piw.getDirection() == DirectionType.OUT && 
				(piw.getCategory() == PortCategory.EVENT ||
				 piw.getCategory() == PortCategory.EVENT_DATA)) {
				portList.add(piw);
			}
		}
		return portList;
	}

	
	public static List<PortImplWrapper> 
		filterPortDirection(List<PortImplWrapper> ports, DirectionType dir) {
		
		List<PortImplWrapper> filterList = new ArrayList<PortImplWrapper>();
		for (PortImplWrapper i: ports) {
			if (i.getDirection() == dir) {
				filterList.add(i);
			}
		}
		return filterList;
	}
	
	public static List<PortImplWrapper> 
	filterPortCategory(List<PortImplWrapper> ports, PortCategory p) {
	
		List<PortImplWrapper> filterList = new ArrayList<PortImplWrapper>();
		for (PortImplWrapper i: ports) {
			if (i.getCategory() == p) {
				filterList.add(i);
			}
		}
		return filterList;
	}

	
	public String getGeneratedEntrypoint() {
		return getName();
	}
	
	// ThreadWrapper properties
	public String getInitializeEntrypointOpt() { 
		return Util.getStringValueOpt(thread, INITIALIZE_ENTRYPOINT_SOURCE_TEXT);
	}
	public String getComputeEntrypointOpt() {
		return Util.getStringValueOpt(thread, COMPUTE_ENTRYPOINT_SOURCE_TEXT);
	}
	
	public String getSmaccmSysSignalOpt() {
		return Util.getStringValueOpt(thread, SMACCM_SYS_SIGNAL_NAME);
	}
	
	public List<String> getSourceTextListOpt() {
		try { 
			PropertyExpression value = PropertyUtils.getSimplePropertyListValue(thread,  SOURCE_TEXT);
			if (value instanceof ListValueImpl) {
				ListValueImpl listValue = (ListValueImpl)value;
				ArrayList<String> files = new ArrayList<String>();
				for (PropertyExpression v: listValue.getOwnedListElements()) {
					if (v instanceof StringLiteral) {
						String s = ((StringLiteral)v).getValue();
						files.add(s);
					}
					else
						return null;
				}
				return files;
			} else
				return null;
		} catch (Exception e) {
			return null; 
		}
	}
	
	public boolean isISRThread() {
		return (getSmaccmSysSignalOpt() != null);
	}

	public Integer getStackSizeInBytesOpt() { 
		// Temporary
		PropertyExpression value = PropertyUtils.getSimplePropertyValue(thread, SOURCE_STACK_SIZE);
		if (value instanceof IntegerLiteral) {
			IntegerLiteral intLit = (IntegerLiteral) value;
			double valInBits = intLit.getScaledValue();
			return (new Integer((int)(valInBits/8.0)));  // bits per byte.
		}		
		return null; 
	}
	
	public Double getPeriodInPicoSecondsOpt() {
		// Temporary
		PropertyExpression value = PropertyUtils.getSimplePropertyValue(thread, PERIOD);
		if (value instanceof IntegerLiteral) {
			IntegerLiteral intLit = (IntegerLiteral) value;
			return intLit.getScaledValue();
		}		
		return null; 
	}
	
	/*
	public Integer getMaxExecutionTime() {
		getScaledRangeMaximum()
		
	}
	*/
	
	// public DispatchMechanism getDispatchMechanism() { return dispatchMechanism; }
	

	
	
	@Override
	public String toString() {
		return "ThreadWrapper " + getName();
	}

	@Override
	public int hashCode() {
		return thread.hashCode(); 
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ThreadWrapper) {
			ThreadWrapper dp = (ThreadWrapper)obj;
			return (thread.equals(dp.thread));
		}
		else 
			return false;
	}
	
	
}
