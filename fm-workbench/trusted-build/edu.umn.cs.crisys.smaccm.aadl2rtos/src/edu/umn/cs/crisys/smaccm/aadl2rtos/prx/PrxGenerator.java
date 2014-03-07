package edu.umn.cs.crisys.smaccm.aadl2rtos.prx;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.ExternalIRQ;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.InterruptServiceRoutine;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.LegacyExternalIRQ;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.LegacyIRQEvent;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.ThreadImplementationBase;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.ThreadInstancePort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.gluecode.Names;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class PrxGenerator {
	Document doc;
	Model model;
	Logger log;
	Collection<String> generatedFileNames;

	public PrxGenerator(Logger log, Document dom, Model model, Collection<String> generatedFileNames) {
		this.log = log;
		this.doc = dom;
		this.model = model;
		this.generatedFileNames = generatedFileNames;
	}

	public void writeToDOM() {
		org.w3c.dom.Element e = null;
		org.w3c.dom.Element parent;

		// Standard DARPA license deal.
		Comment c;
		c = doc.createComment(Util.DARPA_License);
		doc.appendChild(c);

		// create the root element
		org.w3c.dom.Element root = doc.createElement("system");
		doc.appendChild(root);

		e = doc.createElement("modules");
		root.appendChild(e);
		parent = e;

		writeBoilerplateModules(parent);
		writeVectableModule(parent);
		writeKochabModule(parent);
		writeUserEntrypointModules(parent);
		// writeGeneratedEntrypointModules(parent);
	}

	private void writeUserEntrypointModules(org.w3c.dom.Element parent) {
		org.w3c.dom.Element e = null;

		Comment c = doc.createComment("Modules for Thread Entrypoint Files");
		parent.appendChild(c);

		Set<String> fileSet = new HashSet<String>();
//		for (ThreadImplWrapper w : model.getTaskThreads()) {
//			fileSet.addAll(Util.assertNonNull(Util.getSourceTextListOpt(w.getThread(), ThreadUtil.SOURCE_TEXT), "Thread" + (w.getName())
//					+ " is required to have an associated source file for its entrypoint"));
//		}
//		for (ThreadImplWrapper w : model.getISRThreads()) {
//			fileSet.addAll(Util.assertNonNull(Util.getSourceTextListOpt(w.getThread(), ThreadUtil.SOURCE_TEXT), "Thread" + (w.getName())
//					+ " is required to have an associated source file for its entrypoint"));
//		}
		for (String s : fileSet) {
			e = doc.createElement("module");
			String moduleName = Util.pathRemoveExtension(s);
			e.setAttribute("name", moduleName);
			parent.appendChild(e);
		}
	}

	/*
	private void writeGeneratedEntrypointModules(org.w3c.dom.Element parent) {
		Comment c = doc.createComment("Modules for AADL-Generated Task Main Functions");
		parent.appendChild(c);
		for (String s : generatedFileNames) {
			Element e = doc.createElement("module");
			e.setAttribute("name", Util.prxPath(s));
			parent.appendChild(e);
		}
	}
  */
	
	// boilerplate modules from NICTA example
	private void writeBoilerplateModules(org.w3c.dom.Element parent) {
		org.w3c.dom.Element e = null;
		org.w3c.dom.Element ec = null;

		e = doc.createElement("module");
		e.setAttribute("name", "armv7m.build");
		parent.appendChild(e);
		e = doc.createElement("module");
		e.setAttribute("name", "armv7m.ctxt-switch");
		parent.appendChild(e);

		e = doc.createElement("module");
		e.setAttribute("name", "armv7m.semihost-debug");
		parent.appendChild(e);

		e = doc.createElement("module");
		e.setAttribute("name", "generic.debug");
		ec = doc.createElement("ll_debug");
		ec.appendChild(doc.createTextNode("armv7m_semihost_"));
		e.appendChild(ec);
		ec = doc.createElement("prefix");
		e.appendChild(ec);
		parent.appendChild(e);
	}

	// vectable module containing ISRs
	
	private void writeTrampoline(String signalName, String handlerName, org.w3c.dom.Element parent) {
	  org.w3c.dom.Element e;
    org.w3c.dom.Element ec;

	  
	e = doc.createElement("trampoline");
    parent.appendChild(e);
	  
	if (signalName == null || handlerName == null) {
      throw new Aadl2RtosException("Error in PrxGenerator: " + 
          "ISR threads must define both a signalName and a handlerName. (At least) one is not defined.");
    }
    ec = doc.createElement("name");
    e.appendChild(ec);
    ec.appendChild(doc.createTextNode(signalName));
    
    ec = doc.createElement("handler");
    e.appendChild(ec);
    ec.appendChild(doc.createTextNode(handlerName));
	}

	private void writeVectableEntry(String signalName, String handlerName, org.w3c.dom.Element parent) {
    org.w3c.dom.Element e;
    e = doc.createElement(signalName);
    // TODO: here is an example why we want a separate class for ISRs.
    // I have to extract the (single, I hope) entrypoint from the list
    // of entrypoints for the thread.
    try {
      e.appendChild(doc.createTextNode(handlerName));
      parent.appendChild(e);
    } catch (Exception excep) {
      throw new Aadl2RtosException("Error: creating handler name for ISR; handler list in incorrect format for thread: " 
          + handlerName + ".  Exception: " + excep.toString());
    }
	}

  private void writeLegacyVectableEntry(ExternalIRQ eirq, org.w3c.dom.Element parent) {
    org.w3c.dom.Element e;
    org.w3c.dom.Element ec;
    e = doc.createElement("external_irq");
    String externalHandlerName = eirq.getName();
    try {
      ec = doc.createElement("number");
      ec.appendChild(doc.createTextNode(Integer.toString(eirq.getIrqId())));
      e.appendChild(ec);
      ec = doc.createElement("handler");
      ec.appendChild(doc.createTextNode(externalHandlerName));
      e.appendChild(ec);
      parent.appendChild(e);
    } catch (Exception excep) {
      throw new Aadl2RtosException("Error: creating handler name for external ISR; handler list in incorrect format for external IRQ: " 
          + externalHandlerName + ".  Exception: " + excep);
    }
  }

  static String externIrqArray[] = {"wwdg", "pvd", "tamp_stamp", "rtc_wakeup", "flash", "rcc", "exti0", "exti1", "exti2", 
      "exti3", "exti4", "dma1_stream0", "dma1_stream1", "dma1_stream2", "dma1_stream3", "dma1_stream4", "dma1_stream5", "dma1_stream6",
      "adc", "can1_tx", "can1_rx0", "can1_rx1", "can1_sce", "exti9_5", "tim1_brk_tim9", "tim1_up_tim10", "tim1_trg_com_tim11",
      "tim1_cc", "tim2", "tim3", "tim4", "i2c1_ev", "i2c1_er", "i2c2_ev", "i2c2_er", "spi1", "spi2", "usart1", "usart2", "usart3", 
      "exti5_10", "rtc_alarm", "otg_fs_wkup", "tim8_brk_tim12", "tim8_up_tim13", "tim8_trg_com_tim14", "tim8_cc", "dma1_stream7", 
      "fsmc", "sdio", "tim5", "spi3", "uart4", "uart5", "tim6_dac", "tim7", "dma2_stream0", "dma2_stream1", "dma2_stream2", 
      "dma2_stream3", "dma2_stream4", "eth", "eth_wkup", "can2_tx", "can2_rx0", "can2_rx1", "can2_sce", "otg_fs", "dma2_stream5",
      "dma2_stream6", "dma2_stream7", "usart6", "i2c3_ev", "i2c3_er", "otg_hs_ep1_out", "otg_hs_ep1_in", "otg_hs_wkup", "otg_hs",
      "dcmi", "cryp", "hash_rng", "fpu"};
  
  static String internIrqArray[] = {"exception_reset", "nmi", "hardfault", "busfault", 
    "usagefault", "svcall", "debug_monitor", "pendsv", "systick"}; 
  
  static boolean isStringArrayMbr(String[] array, String mbr) {
    for (String i: array) {
      if (mbr.equals(i)) {
        return true;
      }
    }
    return false;
  }
  
  static boolean isExternalIrq(String id) { return isStringArrayMbr(externIrqArray, id); }
  static boolean isInternalIrq(String id) { return isStringArrayMbr(internIrqArray, id); }
  
  private void writeVectableModule(org.w3c.dom.Element parent) {
		org.w3c.dom.Element e = null;
		org.w3c.dom.Element ec = null;
		
		e = doc.createElement("module"); 
		parent.appendChild(e);
		e.setAttribute("name", "armv7m.exception-preempt");
		ec = doc.createElement("handler");
		ec.appendChild(doc.createTextNode("rtos_preempt_handler"));
		e.appendChild(ec);
		ec = doc.createElement("trampolines");
		e.appendChild(ec);
		List<InterruptServiceRoutine> ISRs = model.getISRList();
		for (InterruptServiceRoutine i : ISRs) {
       writeTrampoline(i.getSignalName(), i.getHandlerName(), ec);
		}
		// let the shim do it if it wants to.
		if (model.getThreadCalendar().hasDispatchers() && model.getGenerateSystickIRQ()) {
		  writeTrampoline("systick", Names.getCalendarFnName(), ec);
		}
		for (LegacyExternalIRQ lirq: model.getLegacyExternalIRQs()) {
		  writeTrampoline(lirq.getName(), lirq.getHandlerName(), ec);
		}
		
		e = doc.createElement("module");
		e.setAttribute("name", "armv7m.vectable");
		parent.appendChild(e);

		ec = doc.createElement("flash_load_addr");
		e.appendChild(ec);
		ec.appendChild(doc.createTextNode("0x8000000"));
		ec = doc.createElement("code_addr");
		e.appendChild(ec);
		ec.appendChild(doc.createTextNode("0x8000000"));
		ec = doc.createElement("systick");
		e.appendChild(ec);
		ec.appendChild(doc.createTextNode("exception_preempt_trampoline_systick"));
		ec = doc.createElement("svcall");
		e.appendChild(ec);
		ec.appendChild(doc.createTextNode("SVCHandler"));
		ec = doc.createElement("pendsv");
		e.appendChild(ec);
		ec.appendChild(doc.createTextNode("PendSVHandler"));
	      
		for (InterruptServiceRoutine i : ISRs) {
		  if (isInternalIrq(i.getSignalName())) {
		    writeVectableEntry(i.getSignalName(), i.getHandlerName(), e);
		  } else if (! isExternalIrq(i.getSignalName())) {
		    throw new Aadl2RtosException("IRQ: " + i.getSignalName() + " is not a known internal or external IRQ");
		  }
		}
		
		// write lirqs from static list: 
		//if (!model.getLegacyExternalIRQs().isEmpty()) {
		  ec = doc.createElement("external_irqs");
		  e.appendChild(ec);
		  for (ExternalIRQ eirq: model.getExternalIRQs()) {
  		  writeLegacyVectableEntry(eirq, ec);
  		}
		// }
        if (model.getThreadCalendar().hasDispatchers() && model.getGenerateSystickIRQ()) {
           writeVectableEntry("systick", Names.getCalendarFnName(), e);
        }
	}

	private void printKochabComponent(ThreadImplementationBase c, Document doc, org.w3c.dom.Element parent) {
		org.w3c.dom.Element e;
		
		e = doc.createElement("task");
		parent.appendChild(e);

		parent = e;

		e = doc.createElement("name");
		e.appendChild(doc.createTextNode(c.getName()));
		parent.appendChild(e);

		// periodic task scheduler, in makefile, additional c/o files (add another c file for testing tasks)
		e = doc.createElement("entry");
		e.appendChild(doc.createTextNode(c.getGeneratedEntrypoint()));
		parent.appendChild(e);

		e = doc.createElement("stack_size");
		e.appendChild(doc.createTextNode(Integer.toString(c.getStackSize())));
		parent.appendChild(e);
		
		e = doc.createElement("priority");
		e.appendChild(doc.createTextNode(Integer.toString(c.getPriority())));
		parent.appendChild(e);
	}

	public class PriorityComparator implements Comparator<ThreadImplementationBase> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(
        ThreadImplementationBase arg0,
        ThreadImplementationBase arg1) {
      // TODO Auto-generated method stub
      return Integer.compare(arg1.getPriority(), arg0.getPriority());
    }
	}

	
	private void writeKochabModuleTasks(org.w3c.dom.Element parent) {

		org.w3c.dom.Element e;
		
    //e = doc.createElement("num_tasks");
		//e.appendChild(doc.createTextNode(Integer.toString(tasks.size())));
		//parent.appendChild(e);

		e = doc.createElement("tasks");
		parent.appendChild(e);
		
    List<ThreadImplementationBase> allTasks; 
    
//	if (model.getISRType() == Model.ISRType.SignalingISR) {
	    allTasks = model.getAllThreadImplementations();
//	} else if (model.getISRType() == Model.ISRType.InThreadContextISR) {
//		allTasks = new ArrayList<ThreadImplementationBase>();
//		for (ThreadImplementationBase ti : model.getAllThreadImplementations()) {
//			if (!ti.isISRThread()) {
//				allTasks.add(ti);
//			}
//		}
//	} else {
//		throw new Aadl2RtosException("Error: unknonwn ISR type: " + model.getISRType().toString());
//	}

    int kochabLocation = 0;
    Collections.sort(allTasks, new PriorityComparator());
    for (ThreadImplementationBase i : allTasks) {
      i.setKochabThreadLocation(kochabLocation);
      kochabLocation++;
    }
    for (ThreadImplementationBase ti: allTasks) {
		  printKochabComponent(ti, doc, e);
		}
	}

	private void writeIrqEvent(org.w3c.dom.Element e, String signalName, String taskId, int eventTask, String taskName, int signalNumber) {
    org.w3c.dom.Element ec;
    org.w3c.dom.Element eec;
    ec = doc.createElement("irq_event");
    e.appendChild(ec);
    eec = doc.createElement("name");
    ec.appendChild(eec);
    eec.appendChild(doc.createTextNode(signalName));
    eec = doc.createElement("task_name");
    ec.appendChild(eec);
    ec.appendChild(doc.createComment("Task: " + taskId + " has the " + Integer.toString(eventTask) + "th highest priority in the system."));
    eec.appendChild(doc.createTextNode((taskName)));
    eec = doc.createElement("sig_set");
    ec.appendChild(eec);
    eec.appendChild(doc.createTextNode(Integer.toString(1 << signalNumber)));	  
	}
	
	private ThreadImplementationBase findTIB(String name) {
    for (ThreadImplementationBase elem: model.getAllThreadImplementations()) {
      if (elem.getName().equals(name)) {
        return elem;
      }
    }
    return null;
	}
	
	private void writeKochabModuleIrqEvents(org.w3c.dom.Element parent) {
	  org.w3c.dom.Element e;
    
	  e = doc.createElement("irq_events");
	  parent.appendChild(e);
	  for (InterruptServiceRoutine isr: model.getISRList()) {
      if (isr.getThreadInstances().size() != 1) {
        throw new Aadl2RtosException("Interrupt service routine " + isr.getSignalName() + " must map to exactly one thread instance.");
      }
	    String signalName = isr.getIrqSignalName();
	    ThreadImplementationBase tib = isr.getThreadInstances().get(0).getThreadImplementation(); 
	    String taskId = tib.getName();
	    String taskName = tib.getName();
	    int eventTask = tib.getKochabThreadLocation(); 
	    int signalNumber = isr.getDestinationPort().getPortID();
	    writeIrqEvent(e, signalName, taskId, eventTask, taskName, signalNumber);
	  }

	  // write signal numbers for all periodically dispatched threads.
	  for (Dispatcher d: model.getThreadCalendar().getPeriodicDispatchers()) {
	    String signalName = d.getPeriodicIrqSignalName();
	    for (ThreadInstance ti: d.getOwner().getThreadInstanceList()) {
	      String taskId = ti.getThreadImplementation().getName();
	      String taskName = d.getOwner().getName();
	      int eventTask = ti.getThreadImplementation().getKochabThreadLocation();
	      int signalNumber = d.getOwner().getSignalNumberForDispatcher(d);
	      writeIrqEvent(e, signalName, taskId, eventTask, taskName, signalNumber);
	    }
	  }
	  
	  // write external irq events.
	  for (LegacyIRQEvent lie: model.getLegacyIRQEvents()) {
	    ThreadImplementationBase tib = findTIB(lie.getTaskName());
	    if (tib == null) {
	      throw new Aadl2RtosException("Unable to find thread with name: '" + lie.getTaskName() + "'.");
	    }
	    writeIrqEvent(e, lie.getName(), tib.getName(), tib.getKochabThreadLocation(), lie.getTaskName(), lie.getSigSet());
	  }
	}
	
	private void createMutexOrSemaphore(org.w3c.dom.Element e, String name, String type) {
		org.w3c.dom.Element ec = doc.createElement(type);
		org.w3c.dom.Element ec2 = doc.createElement("name");
		ec2.appendChild(doc.createTextNode(name));
		ec.appendChild(ec2);
		e.appendChild(ec);
	}

	private void writeKochabModuleMutexes(org.w3c.dom.Element parent) {
		org.w3c.dom.Element e;
		List<ThreadInstancePort> instances = model.getAllThreadInstanceInputPorts();
    //e = doc.createElement("num_mutexes");
		//e.appendChild(doc.createTextNode(Integer.toString(instances.size())));
		//parent.appendChild(e);

		e = doc.createElement("mutexes");
        parent.appendChild(e);
        // for building 'flight' the legacy list has to be first in file order so 
        // that these semaphores are assigned starting at ID 0. 
		for (String name : model.getLegacyMutexList()) {
			  createMutexOrSemaphore(e, name, "mutex");
		}
		if (model.getCommMutexPrimitive() == Model.CommMutualExclusionPrimitive.Semaphore) {
			for (ThreadInstancePort i : instances) {
			  createMutexOrSemaphore(e, i.getMutexName(), "mutex");
			}
			for (SharedData d : model.getSharedDataList()) {
			  createMutexOrSemaphore(e, d.getMutexName(), "mutex");
			}
		}
		e = doc.createElement("semaphores");
        parent.appendChild(e);
		for (String name: model.getLegacySemaphoreList()) {
		    createMutexOrSemaphore(e, name, "semaphore");
		}
	}
	
	private void writeKochabModule(org.w3c.dom.Element parent) {
		org.w3c.dom.Element e = null;
		org.w3c.dom.Element ec = null;
		
		// create data elements and place them under root
		e = doc.createElement("module");
		e.setAttribute("name", "armv7m.rtos-kochab");
		ec = doc.createElement("prefix");
		ec.appendChild(doc.createTextNode("rtos_"));
		e.appendChild(ec);

		// TODO: replace the constants for taskid_size, signalset_size,
		// and irqeventid_size with derived numbers based on the
		// number of threads and ...something else... for the others.

		ec = doc.createElement("taskid_size");
		ec.appendChild(doc.createTextNode("8"));
		e.appendChild(ec);

		ec = doc.createElement("signalset_size");
		ec.appendChild(doc.createTextNode("8"));
		e.appendChild(ec);

		ec = doc.createElement("irqeventid_size");
		ec.appendChild(doc.createTextNode("8"));
		e.appendChild(ec);

		writeKochabModuleTasks(e);
		writeKochabModuleMutexes(e);
		writeKochabModuleIrqEvents(e);
		parent.appendChild(e);
	}
}