package edu.umn.cs.crisys.tb.util;

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
import org.osate.aadl2.Classifier;
import org.osate.aadl2.DataClassifier;
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
import org.osate.aadl2.impl.ClassifierValueImpl;
import org.osate.aadl2.impl.ListValueImpl;
import org.osate.aadl2.impl.StringLiteralImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.impl.InstanceReferenceValueImpl;
import org.osate.aadl2.modelsupport.scoping.Aadl2GlobalScopeUtil;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.tb.TbException;

public abstract class PropertyUtil {

   final public static Property INITIALIZE_ENTRYPOINT_SOURCE_TEXT = Util
         .getPropertyDefinitionInWorkspace("Initialize_Entrypoint_Source_Text");
   final public static Property COMPUTE_ENTRYPOINT_SOURCE_TEXT = Util
         .getPropertyDefinitionInWorkspace("Compute_Entrypoint_Source_Text");
   final public static Property SOURCE_STACK_SIZE = Util
         .getPropertyDefinitionInWorkspace("Stack_Size");
   final public static Property SOURCE_TEXT = Util
         .getPropertyDefinitionInWorkspace("Source_Text");
   final public static Property COMPUTE_EXECUTION_TIME = Util
         .getPropertyDefinitionInWorkspace("Compute_Execution_Time");
   final public static Property TB_SYS_IS_ISR = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Is_ISR");
   final public static Property TB_SYS_MEMORY_PAGES = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Memory_Pages");
   final public static Property TB_SYS_SIGNAL_NAME = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Signal_Name");
   final public static Property TB_SYS_SIGNAL_NUMBER = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Signal_Number");

   final public static String TB_SYS_COMMPRIM_SOURCE_HEADER_NAME =
         "TB_SYS::CommPrim_Source_Header";
   final public static Property TB_SYS_COMMPRIM_SOURCE_HEADER = Util
         .getPropertyDefinitionInWorkspace(TB_SYS_COMMPRIM_SOURCE_HEADER_NAME);

   final public static Property TB_SYS_COMMPRIM_SOURCE_TEXT = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::CommPrim_Source_Text");
   final public static Property TB_SYS_OS = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::OS");
   final public static Property TB_SYS_HW = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::HW");
   final public static Property TB_SYS_OUTPUT_DIRECTORY = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Output_Directory");

   final public static Property PERIOD =
         Util.getPropertyDefinitionInWorkspace("Period");
   final public static Property PRIORITY =
         Util.getPropertyDefinitionInWorkspace("Priority");

   final public static Property LEGACY = Util.getPropertyDefinitionInWorkspace("TB_SYS::Is_External");
   final public static Property LEGACY_MUTEX_LIST = Util.getPropertyDefinitionInWorkspace("TB_SYS::External_Mutex_List");
   final public static Property LEGACY_SEMAPHORE_LIST = Util.getPropertyDefinitionInWorkspace("TB_SYS::External_Semaphore_List");
   final public static Property LEGACY_ENTRYPOINT = Util.getPropertyDefinitionInWorkspace("TB_SYS::External_Entrypoint");
   final public static Property LEGACY_EXTERNAL_IRQ_LIST = Util.getPropertyDefinitionInWorkspace("TB_SYS::External_IRQ_Handler_List");
   final public static Property LEGACY_EXTERNAL_IRQ_EVENT_LIST = Util.getPropertyDefinitionInWorkspace("TB_SYS::External_IRQ_Handler_List");
   final public static Property EXTERNAL_IRQ_LIST = Util.getPropertyDefinitionInWorkspace("TB_SYS::External_IRQ_List");
   final public static Property GENERATE_SCHEDULER_SYSTICK_IRQ = Util.getPropertyDefinitionInWorkspace("TB_SYS::Generate_Scheduler_Systick_IRQ");
   final public static Property ISR_HANDLER = Util.getPropertyDefinitionInWorkspace("TB_SYS::First_Level_Interrupt_Handler");

   final public static Property DISPATCH_PROTOCOL = Util.getPropertyDefinitionInWorkspace("Dispatch_Protocol");
   final public static Property QUEUE_SIZE = Util.getPropertyDefinitionInWorkspace("Queue_Size");
   final public static Property ACCESS_RIGHT = Util.getPropertyDefinitionInWorkspace("Access_Right");
   final public static Property THREAD_TYPE = Util.getPropertyDefinitionInWorkspace("TB_SYS::Thread_Type");
   final public static Property TB_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT =
         Util.getPropertyDefinitionInWorkspace("TB_SYS::Compute_Entrypoint_Source_Text");
   final public static Property TB_SYS_SENDS_EVENTS_TO = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Sends_Events_To");
   final public static Property CAMKES_OWNER_THREAD = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::CAmkES_Owner_Thread");
   final public static Property USE_OS_REAL_TIME_EXTENSIONS = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Use_OS_Real_Time_Extensions");
   final public static Property C_TYPE_NAME = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::C_Type_Name");
   final public static Property C_TYPE_SIZE = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::C_Type_Size");
   final public static Property PASS_BY_REFERENCE = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::By_Reference");
   final public static Property EXTERNAL_TIMER_COMPONENT = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::External_Timer_Component");
   final public static Property CAMKES_EXTERNAL_TIMER_INTERFACE_PATH = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::CAmkES_External_Timer_Interface_Path");
   final public static Property CAMKES_EXTERNAL_TIMER_COMPLETE_PATH = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::CAmkES_External_Timer_Complete_Path");
   final public static Property CAMKES_INTERNAL_TIMER_TIMERS_PER_CLIENT = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::CAmkES_Internal_Timer_Timers_Per_Client");
   final public static Property CAMKES_TIME_SERVER_AADL_THREAD_MIN_INDEX = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::CAmkES_Time_Server_AADL_Thread_Min_Index");
   final public static Property MAILBOX = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Mailbox");
   final public static Property REQUIRES_TIME_SERVICES = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::Requires_Time_Services");
   final public static Property CAMKES_DATAPORT_RPC_MIN_INDEX = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::CAmkES_Dataport_RPC_Min_Index");
   final public static Property ECHRONOS_GENERATE_C_MODULES = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::eChronos_Generate_C_Modules");
   final public static Property ECHRONOS_C_MODULE_PATH = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::eChronos_C_Module_Path");
   final public static Property ECHRONOS_FLASH_LOAD_ADDRESS = Util
         .getPropertyDefinitionInWorkspace("TB_SYS::eChronos_Flash_Load_Address");
   final public static Property ACTUAL_PROCESSOR_BINDING = Util
         .getPropertyDefinitionInWorkspace("Actual_Processor_Binding");

   public static int getPriority(ThreadTypeImpl tti) {
      int priority = 0;

      try {
         priority = (int) PropertyUtils.getIntegerValue(tti, PropertyUtil.PRIORITY);
      } catch(Exception e) {
         throw new TbException("Error: required property 'Priority' not found in thread: " + tti.getName());
      }
      return priority;
   }

   public static boolean getIsExternal(NamedElement tti) {
      boolean legacy = false;

      try {
         legacy = PropertyUtils.getBooleanValue(tti, PropertyUtil.LEGACY);
      } catch(Exception e) {}
      return legacy;
   }

   public static boolean getIsPassByReference(NamedElement tti) {
      boolean pbr = false;

      try {
         pbr = PropertyUtils.getBooleanValue(tti, PropertyUtil.PASS_BY_REFERENCE);
      } catch(Exception e) {}
      return pbr;
   }

   public static Map<String, String> getMemoryRegions(NamedElement tti) {
      Map<String, String> m = new HashMap<>();
      List<String> elems = getStringList(tti, PropertyUtil.TB_SYS_MEMORY_PAGES);
      if (elems.size() % 2 != 0) {
         throw new TbException("Property 'Memory_Regions' should be list of strings arranged as string, hexidecimal integer pairs representing the name and memory region");
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
      }
      return list;
   }

   public static Boolean getIsIsr(NamedElement tti) {
      try {
         return PropertyUtils.getBooleanValue(tti, TB_SYS_IS_ISR);
      } catch(Exception e) {}
      return false;
   }

   public static boolean getThreadType(NamedElement tti) {
      EnumerationLiteral lit = null;
      try {
         lit = PropertyUtils.getEnumLiteral(tti, PropertyUtil.THREAD_TYPE);
      } catch (Exception e) {
         throw new TbException("Required property 'Thread_Type' not found for thread: " + tti.getName() + "Exception: " + e.toString());
      }
      if ("Active".equals(lit.getName())) {
         return false;
      } else if ("Passive".equals(lit.getName())) {
         return true;
      } else {
         throw new TbException("Property 'Thread_Type' can only take values 'Active', 'Passive': " + tti.getName());
      }
   }

   public static String getOS(NamedElement tti) {
      EnumerationLiteral lit = null;
      try {
         lit = PropertyUtils.getEnumLiteral(tti, PropertyUtil.TB_SYS_OS);
      } catch (Exception e) {
         throw new TbException("Required property 'OS' not found for system instance: " + tti.getName() + "Exception: " + e.toString() + "; is your AADL file a part of an AADL project?");
      }
      return lit.getName();
   }

   public static String getHW(NamedElement tti) {
      try {
         return (PropertyUtils.getEnumLiteral(tti, PropertyUtil.TB_SYS_HW).getName());
      } catch (Exception e) {
         throw new TbException("Required property 'HW' not found for system instance: " + tti.getName() + "Exception: " + e.toString());
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
                     PropertyUtil.TB_SYS_COMPUTE_ENTRYPOINT_SOURCE_TEXT).getChildren();

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
      throw new TbException("Required property 'Stack_Size' not found or in incorrect format for thread: " + thread.getName());
   }

   public static EnumerationLiteral getDispatchProtocol(ThreadTypeImpl tti) {
      try {
         return PropertyUtils.getEnumLiteral(tti, PropertyUtil.DISPATCH_PROTOCOL);
      } catch (Exception e) {
         throw new TbException("Required property 'Dispatch_Protocol' not found for thread: " + tti.getName());
      }
   }

   final private static EClass UNITS_TYPE = Aadl2Package.eINSTANCE.getUnitsType();
   public static UnitLiteral findUnitLiteral(Element context, String name) {

		for (IEObjectDescription desc : Aadl2GlobalScopeUtil.getAllEObjectDescriptions(context, UNITS_TYPE)) {
         UnitsType unitsType = (UnitsType) EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
         UnitLiteral literal = unitsType.findLiteral(name);
         if (literal != null) {
            return literal;
         }
      }

      return null;
   }



   public static double getPeriodInMicroseconds(NamedElement t) {
      try {
         final IntegerLiteral intLit =
               (IntegerLiteral) PropertyUtils.getSimplePropertyValue(t, PropertyUtil.PERIOD);
         double valInPicoseconds = intLit.getScaledValue();
         return valInPicoseconds / 1000000.0; // microseconds per picosecond.
      } catch (Exception e) {
         throw new TbException("Required property 'Compute_Execution_Time' not found for thread: " + t.getName());
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
         throw new TbException("Required property 'Compute_Execution_Time' not found for thread: " + t.getName());
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
         throw new TbException("Required property 'Compute_Execution_Time' not found for thread: " + t.getName());
      }
   }

   public static int getQueueSize(org.osate.aadl2.Port port) {
      try {
         int queueSize = (int) PropertyUtils.getIntegerValue(port,
               PropertyUtil.QUEUE_SIZE);
         return queueSize;
      } catch (Exception e) {
         throw new TbException("Error: required property 'Queue_Size' missing from port: " + port.getName());
      }
   }

   public static boolean getUseOsRealTimeExtensions(NamedElement elem) {
      return PropertyUtils.getBooleanValue(elem, PropertyUtil.USE_OS_REAL_TIME_EXTENSIONS);
   }

   /**********************************************************
    *
    * Element typing functions
    *
    */


   final public static Property DATA_MODEL_DATA_REPRESENTATION = Util
         .getPropertyDefinitionInWorkspace("Data_Model::Data_Representation");


   public static EnumerationLiteral getDataRepresentationName(NamedElement tti) {
      try {
         return PropertyUtils.getEnumLiteral(tti, DATA_MODEL_DATA_REPRESENTATION);
      } catch (Exception e) {
         throw new TbException("Required property 'Data_Representation' not found for type: " + tti.getName());
      }
   }

   final public static Property DATA_MODEL_BASE_TYPE = Util
         .getPropertyDefinitionInWorkspace("Data_Model::Base_Type");


   public static DataClassifier getBaseType(NamedElement tti) {
      PropertyExpression value ;
      try {
         value = PropertyUtils
               .getSimplePropertyListValue(tti, DATA_MODEL_BASE_TYPE);
      } catch (Exception e) {
         throw new TbException("Required property 'Base_Type' not found for type: " + tti.getName());
      }
      if (value instanceof ListValueImpl) {
         ListValueImpl listValue = (ListValueImpl) value;
         if (listValue.getOwnedListElements().size() != 1) {
            throw new TbException("For array type: " + tti.getName() + " base type list has more than one element.");
         }
         PropertyExpression sizeExpr = listValue.getOwnedListElements().get(0);
         if (sizeExpr instanceof ClassifierValueImpl &&
               ((ClassifierValueImpl)sizeExpr).getClassifier() instanceof DataClassifier) {
            ClassifierValueImpl castedSize =(ClassifierValueImpl) sizeExpr;
            Classifier c = castedSize.getClassifier();
            return (DataClassifier)c;
         } else {
            throw new TbException("Classifier returned by Base_Type property does not correspond to list for array type: " + tti.getName());
         }
      } else {
         throw new TbException("Base_Type property is not a list value for array type: " + tti.getName());
      }
   }

   final public static Property DATA_MODEL_DIMENSION = Util
         .getPropertyDefinitionInWorkspace("Data_Model::Dimension");


   public static int getDimension(NamedElement tti) {
      try {
         PropertyExpression value = PropertyUtils.getSimplePropertyValue(tti, DATA_MODEL_DIMENSION);
         if (value instanceof ListValueImpl) {
            ListValueImpl listValue = (ListValueImpl) value;
            if (listValue.getOwnedListElements().size() != 1) {
               throw new TbException("For array type: " + tti.getName() + " only single dimensional arrays are currently supported.");
            }
            PropertyExpression sizeExpr = listValue.getOwnedListElements().get(0);
            IntegerLiteral intLit = (IntegerLiteral) sizeExpr;
            double scaledDim = intLit.getScaledValue();
            return (new Integer((int)scaledDim)); // bits per byte.
         } else {
            throw new TbException("Classifier returned by Dimension property does not correspond to list for array type: " + tti.getName());
         }
      } catch (Exception e) {
         throw new TbException("Required property 'Dimension' not found for type: " + tti.getName());
      }
   }

   public static ComponentInstance getProcessorBinding(NamedElement procInstance) {
      PropertyExpression value;
      try {
         value = PropertyUtils.getSimplePropertyListValue(procInstance, ACTUAL_PROCESSOR_BINDING);
      } catch (Exception e) {
         throw new TbException("Required property 'Actual_Processor_Binding' not found for object: " + procInstance.getName());
      }

      ListValueImpl listValue = (ListValueImpl) value;
      if (listValue.getOwnedListElements().size() != 1) {
         throw new TbException("For processor instance: " + procInstance.getName() + " no processor binding!");
      }
      InstanceReferenceValueImpl refVal =
            (InstanceReferenceValueImpl)listValue.getOwnedListElements().get(0);
      ComponentInstance theResult =
            (ComponentInstance)refVal.getReferencedInstanceObject();
      return theResult;
   }
}