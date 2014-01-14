package edu.umn.cs.crisys.smaccm.aadl2rtos.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.Element;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.impl.StringLiteralImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadImplementation;

public abstract class ThreadUtil {

	final public static String INITIALIZE_ENTRYPOINT_SOURCE_TEXT_NAME = "Initialize_Entrypoint_Source_Text";
	final public static String COMPUTE_ENTRYPOINT_SOURCE_TEXT_NAME = "Compute_Entrypoint_Source_Text";
	final public static String SOURCE_STACK_SIZE_NAME = "Source_Stack_Size";
	final public static String SOURCE_TEXT_NAME = "Source_Text";
	final public static String COMPUTE_EXECUTION_TIME_NAME = "Compute_Execution_Time";
	final public static String SMACCM_SYS_SIGNAL_NAME_NAME = "SMACCM_SYS::Signal_Name";
	final public static String SMACCM_SYS_COMMPRIM_SOURCE_HEADER_NAME = "SMACCM_SYS::CommPrim_Source_Header";
  final public static String SMACCM_SYS_COMMPRIM_SOURCE_TEXT_NAME = "SMACCM_SYS::CommPrim_Source_Text";
  final public static String SMACCM_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT_NAME = "SMACCM_SYS::Compute_Entrypoint_Source_Text";
  final public static String PERIOD_NAME = "Period";
	final public static String PRIORITY_NAME = "Priority";
	final public static String LEGACY_NAME = "SMACCM_SYS::Is_Legacy";
	final public static String LEGACY_MUTEX_LIST_NAME = "SMACCM_SYS::Legacy_Mutex_List";
  final public static String LEGACY_SEMAPHORE_LIST_NAME = "SMACCM_SYS::Legacy_Semaphore_List";
	final public static String LEGACY_ENTRYPOINT_NAME = "SMACCM_SYS::Legacy_Entrypoint";
  final public static String LEGACY_EXTERNAL_IRQ_LIST_NAME = "SMACCM_SYS::Legacy_IRQ_Handler_List";
  final public static String LEGACY_EXTERNAL_IRQ_EVENT_LIST_NAME = "SMACCM_SYS::Legacy_IRQ_Event_List";
  final public static String EXTERNAL_IRQ_LIST_NAME = "SMACCM_SYS::External_IRQ_List";
  final public static String GENERATE_SCHEDULER_SYSTICK_IRQ_NAME = "SMACCM_SYS::Generate_Scheduler_Systick_IRQ";
  final public static String ISR_HANDLER_NAME = "SMACCM_SYS::ISR_Handler";


	final public static String DISPATCH_PROTOCOL_NAME = "Dispatch_Protocol";
	final public static String QUEUE_SIZE_NAME = "QUEUE_SIZE";
	final public static String ACCESS_RIGHT_NAME = "Access_Right";

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
	final public static Property SMACCM_SYS_COMMPRIM_SOURCE_HEADER = Util
	    .getPropertyDefinitionInWorkspace(SMACCM_SYS_COMMPRIM_SOURCE_HEADER_NAME);
	final public static Property SMACCM_SYS_COMMPRIM_SOURCE_TEXT = Util
	    .getPropertyDefinitionInWorkspace(SMACCM_SYS_COMMPRIM_SOURCE_TEXT_NAME);
	final public static Property PERIOD = Util.getPropertyDefinitionInWorkspace(PERIOD_NAME);
	final public static Property PRIORITY = Util.getPropertyDefinitionInWorkspace(PRIORITY_NAME);

	final public static Property LEGACY = Util.getPropertyDefinitionInWorkspace(LEGACY_NAME);
	final public static Property LEGACY_MUTEX_LIST = Util.getPropertyDefinitionInWorkspace(LEGACY_MUTEX_LIST_NAME);
  final public static Property LEGACY_SEMAPHORE_LIST = Util.getPropertyDefinitionInWorkspace(LEGACY_SEMAPHORE_LIST_NAME);
	final public static Property LEGACY_ENTRYPOINT = Util.getPropertyDefinitionInWorkspace(LEGACY_ENTRYPOINT_NAME);
  final public static Property LEGACY_EXTERNAL_IRQ_LIST = Util.getPropertyDefinitionInWorkspace(LEGACY_EXTERNAL_IRQ_LIST_NAME);
  final public static Property LEGACY_EXTERNAL_IRQ_EVENT_LIST = Util.getPropertyDefinitionInWorkspace(LEGACY_EXTERNAL_IRQ_EVENT_LIST_NAME);
  final public static Property EXTERNAL_IRQ_LIST = Util.getPropertyDefinitionInWorkspace(EXTERNAL_IRQ_LIST_NAME);
  final public static Property GENERATE_SCHEDULER_SYSTICK_IRQ = Util.getPropertyDefinitionInWorkspace(GENERATE_SCHEDULER_SYSTICK_IRQ_NAME);
  final public static Property ISR_HANDLER = Util.getPropertyDefinitionInWorkspace(ISR_HANDLER_NAME);

	final public static Property DISPATCH_PROTOCOL = Util.getPropertyDefinitionInWorkspace(DISPATCH_PROTOCOL_NAME);
	final public static Property QUEUE_SIZE = Util.getPropertyDefinitionInWorkspace(QUEUE_SIZE_NAME);
	final public static Property ACCESS_RIGHT = Util.getPropertyDefinitionInWorkspace(ACCESS_RIGHT_NAME);
	final public static Property SMACCM_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT =
	    Util.getPropertyDefinitionInWorkspace(SMACCM_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT_NAME);

	public static List<ThreadImplementation> getTaskThreads(Collection<ThreadImplementation> collection) {
		List<ThreadImplementation> taskThreads = new ArrayList<ThreadImplementation>();
		for (ThreadImplementation th : collection) {
			if (!th.isISRThread()) {
				taskThreads.add(th);
			}
		}
		return taskThreads;
	}

	public static List<ThreadImplementation> getISRThreads(Collection<ThreadImplementation> collection) {
		List<ThreadImplementation> taskThreads = new ArrayList<ThreadImplementation>();

		for (ThreadImplementation th : collection) {
			if (th.isISRThread()) {
				taskThreads.add(th);
			}
		}
		return taskThreads;
	}

	public static int getThreadTaskIndex(ThreadImplementation tti, ArrayList<ThreadImplementation> threads) {
		List<ThreadImplementation> taskThreads = getTaskThreads(threads);
		return taskThreads.indexOf(tti);
	}

	public static int getPriority(ThreadTypeImpl tti) {
		int priority = 0;

		try {
			priority = (int) PropertyUtils.getIntegerValue(tti, ThreadUtil.PRIORITY);
		} catch(Exception e) {
		  throw new Aadl2RtosException("Error: required property 'Priority' not found in thread: " + tti.getName());
		}
		return priority;
	}

	public static boolean getLegacyValue(ThreadTypeImpl tti) {
		boolean legacy = false;

		try {
			legacy = (boolean) PropertyUtils.getBooleanValue(tti, ThreadUtil.LEGACY);
		} catch(Exception e) {}
		return legacy;
	}

	public static List<String> getStringList(NamedElement tti, Property p) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			EList<Element> eList = PropertyUtils.getSimplePropertyListValue(tti, p).getChildren();
			
			for (Element e : eList) {
				StringLiteralImpl str = (StringLiteralImpl) e;
				list.add(str.getValue());			}
		} catch(Exception e) {}
		return list;
	}

  public static List<String> getLegacyMutexList(NamedElement tti) {
    return getStringList(tti, ThreadUtil.LEGACY_MUTEX_LIST);
  }

  public static List<String> getLegacySemaphoreList(NamedElement tti) {
    return getStringList(tti, ThreadUtil.LEGACY_SEMAPHORE_LIST);
  }

  public static List<String> getLegacyIRQEventList(NamedElement tti) {
    return getStringList(tti, ThreadUtil.LEGACY_EXTERNAL_IRQ_EVENT_LIST);
  }
	
  public static List<String> getLegacyIRQList(NamedElement tti) {
	return getStringList(tti, ThreadUtil.LEGACY_EXTERNAL_IRQ_LIST);
  }

  public static List<String> getExternalIRQList(NamedElement tti) {
	return getStringList(tti, ThreadUtil.EXTERNAL_IRQ_LIST);
  }
  
	// returns null if there is no handler list.
	public static List<String> getComputeEntrypointList(NamedElement container) {
		ArrayList<String> handlerList = new ArrayList<String>(); 
		try {
			EList<Element> eList = 
				PropertyUtils.getSimplePropertyListValue(container,  
					ThreadUtil.SMACCM_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT).getChildren();
			
			for (Element e : eList) {
				StringLiteralImpl str = (StringLiteralImpl) e; 
				handlerList.add(str.getValue());
			}
			return handlerList;
			
		} catch (Exception e) {}
		try {
			String str = Util.getStringValue(container, ThreadUtil.COMPUTE_ENTRYPOINT_SOURCE_TEXT);
			handlerList.add(str);
			return handlerList;			
		} catch (Exception e) {
			return null; 
		}
	}
	
  public static int getStackSizeInBytes(NamedElement thread) {
    try {
      PropertyExpression value = PropertyUtils.getSimplePropertyValue(thread, ThreadUtil.SOURCE_STACK_SIZE);
      IntegerLiteral intLit = (IntegerLiteral) value;
      double valInBits = intLit.getScaledValue();
      return (new Integer((int) (valInBits / 8.0))); // bits per byte.
    }
    catch (Exception ee) { }
    throw new Aadl2RtosException("Required property 'Source_Stack_Size' not found or in incorrect format for thread: " + thread.getName());
  }

  public static EnumerationLiteral getDispatchProtocol(ThreadTypeImpl tti) {
    try {
      return PropertyUtils.getEnumLiteral(tti, ThreadUtil.DISPATCH_PROTOCOL);
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Dispatch_Protocol' not found for thread: " + tti.getName());
    }
  }
  

  
}