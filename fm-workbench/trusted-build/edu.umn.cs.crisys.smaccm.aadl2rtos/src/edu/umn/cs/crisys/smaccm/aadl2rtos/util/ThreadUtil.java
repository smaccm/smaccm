package edu.umn.cs.crisys.smaccm.aadl2rtos.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.Element;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NumberType;
import org.osate.aadl2.NumberValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.PropertyType;
import org.osate.aadl2.PropertyValue;
import org.osate.aadl2.RangeType;
import org.osate.aadl2.RangeValue;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.aadl2.impl.StringLiteralImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;

public abstract class ThreadUtil {

	final public static String INITIALIZE_ENTRYPOINT_SOURCE_TEXT_NAME = "Initialize_Entrypoint_Source_Text";
	final public static String COMPUTE_ENTRYPOINT_SOURCE_TEXT_NAME = "Compute_Entrypoint_Source_Text";
	final public static String SOURCE_STACK_SIZE_NAME = "Stack_Size";
	final public static String SOURCE_TEXT_NAME = "Source_Text";
	final public static String COMPUTE_EXECUTION_TIME_NAME = "Compute_Execution_Time";
	final public static String SMACCM_SYS_SIGNAL_NAME_NAME = "SMACCM_SYS::Signal_Name";
  final public static String SMACCM_SYS_SIGNAL_NUMBER_NAME = "SMACCM_SYS::Signal_Number";
	final public static String SMACCM_SYS_IS_ISR_NAME = "SMACCM_SYS::Is_ISR";
  final public static String SMACCM_SYS_MEMORY_PAGES_NAME = "SMACCM_SYS::Memory_Pages";
  final public static String SMACCM_SYS_OS_NAME = "SMACCM_SYS::OS";
  final public static String SMACCM_SYS_HW_NAME = "SMACCM_SYS::HW";
  final public static String SMACCM_SYS_OUTPUT_DIRECTORY_NAME = "SMACCM_SYS::Output_Directory";
  
	final public static String SMACCM_SYS_COMMPRIM_SOURCE_HEADER_NAME = "SMACCM_SYS::CommPrim_Source_Header";
  final public static String SMACCM_SYS_COMMPRIM_SOURCE_TEXT_NAME = "SMACCM_SYS::CommPrim_Source_Text";
  final public static String SMACCM_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT_NAME = "SMACCM_SYS::Compute_Entrypoint_Source_Text";
  final public static String PERIOD_NAME = "Period";
	final public static String PRIORITY_NAME = "Priority";
	final public static String LEGACY_NAME = "SMACCM_SYS::Is_External";
	final public static String LEGACY_MUTEX_LIST_NAME = "SMACCM_SYS::External_Mutex_List";
  final public static String LEGACY_SEMAPHORE_LIST_NAME = "SMACCM_SYS::External_Semaphore_List";
	final public static String LEGACY_ENTRYPOINT_NAME = "SMACCM_SYS::External_Entrypoint";
  final public static String LEGACY_EXTERNAL_IRQ_LIST_NAME = "SMACCM_SYS::External_IRQ_Handler_List";
  final public static String LEGACY_EXTERNAL_IRQ_EVENT_LIST_NAME = "SMACCM_SYS::External_IRQ_Event_List";
  final public static String EXTERNAL_IRQ_LIST_NAME = "SMACCM_SYS::External_IRQ_List";
  final public static String GENERATE_SCHEDULER_SYSTICK_IRQ_NAME = "SMACCM_SYS::Generate_Scheduler_Systick_IRQ";
  final public static String ISR_HANDLER_NAME = "SMACCM_SYS::First_Level_Interrupt_Handler";
  final public static String THREAD_TYPE_NAME = "SMACCM_SYS::Thread_Type";
  final public static String SMACCM_SYS_SENDS_EVENTS_TO_NAME = "SMACCM_SYS::Sends_Events_To";


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
	final public static Property SMACCM_SYS_IS_ISR = Util
	    .getPropertyDefinitionInWorkspace(SMACCM_SYS_IS_ISR_NAME);
  final public static Property SMACCM_SYS_MEMORY_PAGES = Util
      .getPropertyDefinitionInWorkspace(SMACCM_SYS_MEMORY_PAGES_NAME);
	final public static Property SMACCM_SYS_SIGNAL_NAME = Util
			.getPropertyDefinitionInWorkspace(SMACCM_SYS_SIGNAL_NAME_NAME);
  final public static Property SMACCM_SYS_SIGNAL_NUMBER = Util
      .getPropertyDefinitionInWorkspace(SMACCM_SYS_SIGNAL_NUMBER_NAME);
	final public static Property SMACCM_SYS_COMMPRIM_SOURCE_HEADER = Util
	    .getPropertyDefinitionInWorkspace(SMACCM_SYS_COMMPRIM_SOURCE_HEADER_NAME);
	final public static Property SMACCM_SYS_COMMPRIM_SOURCE_TEXT = Util
	    .getPropertyDefinitionInWorkspace(SMACCM_SYS_COMMPRIM_SOURCE_TEXT_NAME);
  final public static Property SMACCM_SYS_OS = Util
      .getPropertyDefinitionInWorkspace(SMACCM_SYS_OS_NAME);
  final public static Property SMACCM_SYS_HW = Util
      .getPropertyDefinitionInWorkspace(SMACCM_SYS_HW_NAME);
  final public static Property SMACCM_SYS_OUTPUT_DIRECTORY = Util
      .getPropertyDefinitionInWorkspace(SMACCM_SYS_OUTPUT_DIRECTORY_NAME);

	final public static Property PERIOD = 
	     Util.getPropertyDefinitionInWorkspace(PERIOD_NAME);
	final public static Property PRIORITY = 
	    Util.getPropertyDefinitionInWorkspace(PRIORITY_NAME);
	
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
  final public static Property THREAD_TYPE = Util.getPropertyDefinitionInWorkspace(THREAD_TYPE_NAME);
	final public static Property SMACCM_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT =
	    Util.getPropertyDefinitionInWorkspace(SMACCM_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT_NAME);
  final public static Property SMACCM_SYS_SENDS_EVENTS_TO = Util
      .getPropertyDefinitionInWorkspace(SMACCM_SYS_SENDS_EVENTS_TO_NAME);

	/*
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
*/

	public static int getPriority(ThreadTypeImpl tti) {
		int priority = 0;

		try {
			priority = (int) PropertyUtils.getIntegerValue(tti, ThreadUtil.PRIORITY);
		} catch(Exception e) {
		  throw new Aadl2RtosException("Error: required property 'Priority' not found in thread: " + tti.getName());
		}
		return priority;
	}

	public static boolean getIsExternal(NamedElement tti) {
		boolean legacy = false;

		try {
			legacy = (boolean) PropertyUtils.getBooleanValue(tti, ThreadUtil.LEGACY);
		} catch(Exception e) {}
		return legacy;
	}

	public static Map<String, String> getMemoryRegions(NamedElement tti) {
	  Map<String, String> m = new HashMap<>();
    List<String> elems = getStringList(tti, ThreadUtil.SMACCM_SYS_MEMORY_PAGES);
    if (elems.size() % 2 != 0) {
      throw new Aadl2RtosException("Property 'Memory_Regions' should be list of strings arranged as string, hexidecimal integer pairs representing the name and memory region");	      
    }
    for (Iterator<String> it = elems.iterator(); it.hasNext(); ) {
      String name = it.next();
      String regionName = it.next();
      m.put(name, regionName);
    }
    return m;
	}

	public static List<String> getStringList(NamedElement tti, Property p) {
		ArrayList<String> list = new ArrayList<String>();

		try {
			EList<Element> eList = PropertyUtils.getSimplePropertyListValue(tti, p).getChildren();
			
			for (Element e : eList) {
				StringLiteralImpl str = (StringLiteralImpl) e;
				list.add(str.getValue());			}
		} catch(Exception e) {
		  // throw new Aadl2RtosException("Expected property " + p.getName() + " not found in component: " + tti.getName());
	  }
		return list;
	}

	public static Boolean getIsIsr(NamedElement tti) {
	  try {
	    return PropertyUtils.getBooleanValue(tti, SMACCM_SYS_IS_ISR);
	  } catch(Exception e) {}
	  return false;
	}
	
	public static boolean getThreadType(NamedElement tti) {
	  EnumerationLiteral lit = null; 
	  try {
      lit = PropertyUtils.getEnumLiteral(tti, ThreadUtil.THREAD_TYPE);
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Thread_Type' not found for thread: " + tti.getName() + "Exception: " + e.toString());
    }
    if ("Active".equals(lit.getName())) {
      return false;
    } else if ("Passive".equals(lit.getName())) {
      return true;
    } else {
      throw new Aadl2RtosException("Property 'Thread_Type' can only take values 'Active', 'Passive': " + tti.getName());
    }
	}
	
  public static String getOS(NamedElement tti) {
    EnumerationLiteral lit = null; 
    try {
      lit = PropertyUtils.getEnumLiteral(tti, ThreadUtil.SMACCM_SYS_OS);
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'OS' not found for system instance: " + tti.getName() + "Exception: " + e.toString());
    }
    return lit.getName();
  }

  public static String getHW(NamedElement tti) {
    try {
      return (PropertyUtils.getEnumLiteral(tti, ThreadUtil.SMACCM_SYS_HW).getName());
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'HW' not found for system instance: " + tti.getName() + "Exception: " + e.toString());
    }
  }
  
	public static List<String> getExternalMutexList(NamedElement tti) {
    return getStringList(tti, ThreadUtil.LEGACY_MUTEX_LIST);
  }

  public static List<String> getExternalSemaphoreList(NamedElement tti) {
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
    throw new Aadl2RtosException("Required property 'Stack_Size' not found or in incorrect format for thread: " + thread.getName());
  }

  public static EnumerationLiteral getDispatchProtocol(ThreadTypeImpl tti) {
    try {
      return PropertyUtils.getEnumLiteral(tti, ThreadUtil.DISPATCH_PROTOCOL);
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Dispatch_Protocol' not found for thread: " + tti.getName());
    }
  }
  
  final private static EClass UNITS_TYPE = Aadl2Package.eINSTANCE.getUnitsType();
  public static UnitLiteral findUnitLiteral(Element context, String name) {
    for (IEObjectDescription desc : EMFIndexRetrieval
            .getAllEObjectsOfTypeInWorkspace(UNITS_TYPE)) {
        UnitsType unitsType = (UnitsType) EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
        UnitLiteral literal = unitsType.findLiteral(name);
        if (literal != null) {
            return literal;
        }
    }

    return null; 
}
 

  /*
   * 
   * MWW: 4/12/2015
   * There is an error when I use this code; I think that the PropertyUtils code is a bit
   * wonky.  The issue is that when I find the UnitLiteral type, it finds a type with the 
   * correct name (Time_Units) but the pointer for the type does not match the pointer 
   * to the unit type declared for the property, which is also named Time_Units.  Perhaps
   * there are two copies of this property lurking in the model, or perhaps the 
   * findUnitLiteral code somehow generates a fresh copy.  In any case, the code doesn't 
   * work, so I have to do it myself.
   * 
   */
  /*
  public static double getPeriodInMicroseconds(NamedElement t) {
    try {
      UnitLiteral ms_lit = findUnitLiteral(t, "ms");
      if (ms_lit == null) {
        throw new Aadl2RtosException("For property 'Period': Unable to find unit type 'ms'");
      }
      return PropertyUtils.getScaledNumberValue(t, ThreadUtil.PERIOD, ms_lit);
    } catch (Aadl2RtosException e) {
      throw e;
    }
    catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Period' not found for thread: " + t.getName());
    }
  }
  */

  /* 
   * MWW: This *also does not work*.
   * The Units conversion stuff just seems to be broken.  Even after doing it 
   * This way, the conversion factors for Time_Units are wrong.  'ms' and 'us' are
   * converted to the same value.  Disappointing.
   * 
  */
  /*
  public static double getPeriodInMicroseconds(NamedElement t) {
    try {
      PropertyExpression pv = PropertyUtils.getSimplePropertyValue(t, ThreadUtil.PERIOD);
      PropertyType pt = (PropertyType) ThreadUtil.PERIOD.getType();
      final UnitsType theUnitsType = ((NumberType) pt).getUnitsType();
      UnitLiteral us_lit = theUnitsType.findLiteral("us");
      if (us_lit == null) {
        throw new Aadl2RtosException("For property 'Period': Unable to find unit type 'ms'");
      }
      return ((NumberValue) pv).getScaledValue(us_lit);
    } catch (Aadl2RtosException e) {
      throw e;
    }
    catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Period' not found for thread: " + t.getName());
    }
  }
  */

  public static double getPeriodInMicroseconds(NamedElement t) {
    try {
      final IntegerLiteral intLit = 
           (IntegerLiteral) PropertyUtils.getSimplePropertyValue(t, ThreadUtil.PERIOD);
      double valInPicoseconds = intLit.getScaledValue();
      return valInPicoseconds / 1000000.0; // microseconds per picosecond.
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Compute_Execution_Time' not found for thread: " + t.getName());
    }
  }

  public static double getMinComputeExecutionTimeInMicroseconds(NamedElement t) {
    try {
      final PropertyExpression pv = PropertyUtils.getSimplePropertyValue(t, ThreadUtil.COMPUTE_EXECUTION_TIME);
      final RangeValue rv = (RangeValue) pv;
      final PropertyValue minVal = rv.getMinimumValue();
      IntegerLiteral intLit = (IntegerLiteral) minVal;
      double valInPicoseconds = intLit.getScaledValue();
      return valInPicoseconds / 1000000.0; // microseconds per picosecond.
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Compute_Execution_Time' not found for thread: " + t.getName());
    }
  }

  public static double getMaxComputeExecutionTimeInMicroseconds(NamedElement t) {
    try {
      final PropertyExpression pv = PropertyUtils.getSimplePropertyValue(t, ThreadUtil.COMPUTE_EXECUTION_TIME);
      final RangeValue rv = (RangeValue) pv;
      final PropertyValue minVal = rv.getMaximumValue();
      IntegerLiteral intLit = (IntegerLiteral) minVal;
      double valInPicoseconds = intLit.getScaledValue();
      return valInPicoseconds / 1000000.0; // microseconds per picosecond.
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Compute_Execution_Time' not found for thread: " + t.getName());
    }
  }


}