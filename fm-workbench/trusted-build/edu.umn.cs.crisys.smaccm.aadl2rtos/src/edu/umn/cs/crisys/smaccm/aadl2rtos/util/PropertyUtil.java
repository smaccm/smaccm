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
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.PropertyValue;
import org.osate.aadl2.RangeValue;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.aadl2.impl.StringLiteralImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;

public abstract class PropertyUtil {

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
  final public static String CAMKES_OWNER_THREAD_NAME = "SMACCM_SYS::CAmkES_Owner_Thread";
  final public static String USE_OS_REAL_TIME_EXTENSIONS_NAME = "SMACCM_SYS::Use_OS_Real_Time_Extensions";

	final public static String DISPATCH_PROTOCOL_NAME = "Dispatch_Protocol";
	final public static String QUEUE_SIZE_NAME = "QUEUE_SIZE";
	final public static String ACCESS_RIGHT_NAME = "Access_Right";
  final public static String C_TYPE_NAME_NAME = "SMACCM_SYS::C_Type_Name";
  final public static String PASS_BY_REFERENCE_NAME = "SMACCM_SYS::By_Reference";
  final public static String EXTERNAL_TIMER_COMPONENT_NAME = "SMACCM_SYS::External_Timer_Component";
  final public static String CAMKES_EXTERNAL_TIMER_INTERFACE_PATH_NAME = "SMACCM_SYS::CAmkES_External_Timer_Interface_Path"; 
  final public static String CAMKES_EXTERNAL_TIMER_COMPLETE_PATH_NAME = "SMACCM_SYS::CAmkES_External_Timer_Complete_Path";
  final public static String CAMKES_INTERNAL_TIMER_TIMERS_PER_CLIENT_NAME = "SMACCM_SYS::CAmkES_Internal_Timer_Timers_Per_Client"; 
  final public static String CAMKES_TIME_SERVER_AADL_THREAD_MIN_INDEX_NAME = "SMACCM_SYS::CAmkES_Time_Server_AADL_Thread_Min_Index"; 
  final public static String MAILBOX_NAME = "SMACCM_SYS::Mailbox"; 
  final public static String REQUIRES_TIME_SERVICES_NAME = "SMACCM_SYS::Requires_Time_Services";
  final public static String CAMKES_DATAPORT_RPC_MIN_INDEX_NAME = "SMACCM_SYS::CAmkES_Dataport_RPC_Min_Index";
  final public static String ECHRONOS_GENERATE_C_MODULES_NAME = "SMACCM_SYS::eChronos_Generate_C_Modules";
  final public static String ECHRONOS_C_MODULE_PATH_NAME = "SMACCM_SYS::eChronos_C_Module_Path";
  final public static String ECHRONOS_FLASH_LOAD_ADDRESS_NAME = "SMACCM_SYS::eChronos_Flash_Load_Address";
  
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
  final public static Property CAMKES_OWNER_THREAD = Util
      .getPropertyDefinitionInWorkspace(CAMKES_OWNER_THREAD_NAME);
  final public static Property USE_OS_REAL_TIME_EXTENSIONS = Util
	  .getPropertyDefinitionInWorkspace(USE_OS_REAL_TIME_EXTENSIONS_NAME);
  final public static Property C_TYPE_NAME = Util
      .getPropertyDefinitionInWorkspace(C_TYPE_NAME_NAME);
  final public static Property PASS_BY_REFERENCE = Util
      .getPropertyDefinitionInWorkspace(PASS_BY_REFERENCE_NAME);
  final public static Property EXTERNAL_TIMER_COMPONENT = Util
      .getPropertyDefinitionInWorkspace(EXTERNAL_TIMER_COMPONENT_NAME);
  final public static Property CAMKES_EXTERNAL_TIMER_INTERFACE_PATH = Util
      .getPropertyDefinitionInWorkspace(CAMKES_EXTERNAL_TIMER_INTERFACE_PATH_NAME);
  final public static Property CAMKES_EXTERNAL_TIMER_COMPLETE_PATH = Util
      .getPropertyDefinitionInWorkspace(CAMKES_EXTERNAL_TIMER_COMPLETE_PATH_NAME);
  final public static Property CAMKES_INTERNAL_TIMER_TIMERS_PER_CLIENT = Util
      .getPropertyDefinitionInWorkspace(CAMKES_INTERNAL_TIMER_TIMERS_PER_CLIENT_NAME);
  final public static Property CAMKES_TIME_SERVER_AADL_THREAD_MIN_INDEX = Util
      .getPropertyDefinitionInWorkspace(CAMKES_TIME_SERVER_AADL_THREAD_MIN_INDEX_NAME);
  final public static Property MAILBOX = Util
	  .getPropertyDefinitionInWorkspace(MAILBOX_NAME); 
  final public static Property REQUIRES_TIME_SERVICES = Util
      .getPropertyDefinitionInWorkspace(REQUIRES_TIME_SERVICES_NAME);
  final public static Property CAMKES_DATAPORT_RPC_MIN_INDEX = Util
      .getPropertyDefinitionInWorkspace(CAMKES_DATAPORT_RPC_MIN_INDEX_NAME);
  final public static Property ECHRONOS_GENERATE_C_MODULES = Util
      .getPropertyDefinitionInWorkspace(ECHRONOS_GENERATE_C_MODULES_NAME);
  final public static Property ECHRONOS_C_MODULE_PATH = Util
      .getPropertyDefinitionInWorkspace(ECHRONOS_C_MODULE_PATH_NAME);
  final public static Property ECHRONOS_FLASH_LOAD_ADDRESS = Util
	      .getPropertyDefinitionInWorkspace(ECHRONOS_FLASH_LOAD_ADDRESS_NAME);

  
	public static int getPriority(ThreadTypeImpl tti) {
		int priority = 0;

		try {
			priority = (int) PropertyUtils.getIntegerValue(tti, PropertyUtil.PRIORITY);
		} catch(Exception e) {
		  throw new Aadl2RtosException("Error: required property 'Priority' not found in thread: " + tti.getName());
		}
		return priority;
	}

	public static boolean getIsExternal(NamedElement tti) {
		boolean legacy = false;

		try {
			legacy = (boolean) PropertyUtils.getBooleanValue(tti, PropertyUtil.LEGACY);
		} catch(Exception e) {}
		return legacy;
	}

  public static boolean getIsPassByReference(NamedElement tti) {
    boolean pbr = false;

    try {
      pbr = (boolean) PropertyUtils.getBooleanValue(tti, PropertyUtil.PASS_BY_REFERENCE);
    } catch(Exception e) {}
    return pbr;
  }

	public static Map<String, String> getMemoryRegions(NamedElement tti) {
	  Map<String, String> m = new HashMap<>();
    List<String> elems = getStringList(tti, PropertyUtil.SMACCM_SYS_MEMORY_PAGES);
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
      lit = PropertyUtils.getEnumLiteral(tti, PropertyUtil.THREAD_TYPE);
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
      lit = PropertyUtils.getEnumLiteral(tti, PropertyUtil.SMACCM_SYS_OS);
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'OS' not found for system instance: " + tti.getName() + "Exception: " + e.toString());
    }
    return lit.getName();
  }

  public static String getHW(NamedElement tti) {
    try {
      return (PropertyUtils.getEnumLiteral(tti, PropertyUtil.SMACCM_SYS_HW).getName());
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'HW' not found for system instance: " + tti.getName() + "Exception: " + e.toString());
    }
  }
    
	public static List<String> getExternalMutexList(NamedElement tti) {
    return getStringList(tti, PropertyUtil.LEGACY_MUTEX_LIST);
  }

  public static List<String> getExternalSemaphoreList(NamedElement tti) {
    return getStringList(tti, PropertyUtil.LEGACY_SEMAPHORE_LIST);
  }

  public static List<String> getLegacyIRQEventList(NamedElement tti) {
    return getStringList(tti, PropertyUtil.LEGACY_EXTERNAL_IRQ_EVENT_LIST);
  }
	
  public static List<String> getLegacyIRQList(NamedElement tti) {
	return getStringList(tti, PropertyUtil.LEGACY_EXTERNAL_IRQ_LIST);
  }

  public static List<String> getExternalIRQList(NamedElement tti) {
	return getStringList(tti, PropertyUtil.EXTERNAL_IRQ_LIST);
  }
  
	// returns null if there is no handler list.
	public static List<String> getComputeEntrypointList(NamedElement container) {
		ArrayList<String> handlerList = new ArrayList<String>(); 
		try {
			EList<Element> eList = 
				PropertyUtils.getSimplePropertyListValue(container,  
					PropertyUtil.SMACCM_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT).getChildren();
			
			for (Element e : eList) {
				StringLiteralImpl str = (StringLiteralImpl) e; 
				handlerList.add(str.getValue());
			}
			return handlerList;
			
		} catch (Exception e) {}
		try {
			String str = Util.getStringValue(container, PropertyUtil.COMPUTE_ENTRYPOINT_SOURCE_TEXT);
			handlerList.add(str);
			return handlerList;			
		} catch (Exception e) {
			return null; 
		}
	}
	
  public static int getStackSizeInBytes(NamedElement thread) {
    try {
      PropertyExpression value = PropertyUtils.getSimplePropertyValue(thread, PropertyUtil.SOURCE_STACK_SIZE);
      IntegerLiteral intLit = (IntegerLiteral) value;
      double valInBits = intLit.getScaledValue();
      return (new Integer((int) (valInBits / 8.0))); // bits per byte.
    }
    catch (Exception ee) { }
    throw new Aadl2RtosException("Required property 'Stack_Size' not found or in incorrect format for thread: " + thread.getName());
  }

  public static EnumerationLiteral getDispatchProtocol(ThreadTypeImpl tti) {
    try {
      return PropertyUtils.getEnumLiteral(tti, PropertyUtil.DISPATCH_PROTOCOL);
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
           (IntegerLiteral) PropertyUtils.getSimplePropertyValue(t, PropertyUtil.PERIOD);
      double valInPicoseconds = intLit.getScaledValue();
      return valInPicoseconds / 1000000.0; // microseconds per picosecond.
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Compute_Execution_Time' not found for thread: " + t.getName());
    }
  }

  public static double getMinComputeExecutionTimeInMicroseconds(NamedElement t) {
    try {
      final PropertyExpression pv = PropertyUtils.getSimplePropertyValue(t, PropertyUtil.COMPUTE_EXECUTION_TIME);
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
      final PropertyExpression pv = PropertyUtils.getSimplePropertyValue(t, PropertyUtil.COMPUTE_EXECUTION_TIME);
      final RangeValue rv = (RangeValue) pv;
      final PropertyValue minVal = rv.getMaximumValue();
      IntegerLiteral intLit = (IntegerLiteral) minVal;
      double valInPicoseconds = intLit.getScaledValue();
      return valInPicoseconds / 1000000.0; // microseconds per picosecond.
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Compute_Execution_Time' not found for thread: " + t.getName());
    }
  }

  public static int getQueueSize(org.osate.aadl2.Port port) {
    try {
      int queueSize = (int) PropertyUtils.getIntegerValue(port,
        PropertyUtil.QUEUE_SIZE);
      return queueSize; 
    } catch (Exception e) {
      throw new Aadl2RtosException("Error: required property 'Queue_Size' missing from port: " + port.getName());
    }   
  }

  public static boolean getUseOsRealTimeExtensions(NamedElement elem) {
      return (boolean) PropertyUtils.getBooleanValue(elem, PropertyUtil.USE_OS_REAL_TIME_EXTENSIONS);
  }
  /*
  public static String getCommPrimSourceTextOpt(org.osate.aadl2.Port port) {
    try {
      String sourceText = Util.getStringValueOpt(port,
        PropertyUtil.SMACCM_SYS_COMMPRIM_SOURCE_TEXT);
      return sourceText; 
    } catch (Exception e) {
      throw new Aadl2RtosException("Error reading CommPrimSourceText property from port: " + port.getName());
    }
  } 
  */

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
}