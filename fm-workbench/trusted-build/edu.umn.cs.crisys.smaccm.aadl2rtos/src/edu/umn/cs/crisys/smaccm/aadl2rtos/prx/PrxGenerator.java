package edu.umn.cs.crisys.smaccm.aadl2rtos.prx;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osate.analysis.lute.utils.Logger;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.gluecode.Names;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.InterruptServiceRoutine;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadInstancePort;
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
		writeGeneratedEntrypointModules(parent);
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

	private void writeGeneratedEntrypointModules(org.w3c.dom.Element parent) {
		Comment c = doc.createComment("Modules for AADL-Generated Task Main Functions");
		parent.appendChild(c);
		for (String s : generatedFileNames) {
			Element e = doc.createElement("module");
			e.setAttribute("name", Util.prxPath(s));
			parent.appendChild(e);
		}
	}

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
	  
	  if (signalName == null || handlerName == null) {
      throw new Aadl2RtosException("Error in PrxGenerator: " + 
          "ISR threads must define both a signalName and a handlerName. (At least) one is not defined.");
    }
    e = doc.createElement("name");
    parent.appendChild(e);
    e.appendChild(doc.createTextNode(signalName));
    
    e = doc.createElement("handler");
    parent.appendChild(e);
    e.appendChild(doc.createTextNode(handlerName));
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
          + handlerName + ".  Exception: " + excep);
    }
	}

	private void writeVectableModule(org.w3c.dom.Element parent) {
		org.w3c.dom.Element e = null;
		org.w3c.dom.Element ec = null;
		org.w3c.dom.Element eec = null;
		org.w3c.dom.Element eeec = null;
		
		e = doc.createElement("module"); 
		parent.appendChild(e);
		e.setAttribute("name", "armv7m.exception-preempt");
		ec = doc.createElement("handler");
		ec.appendChild(doc.createTextNode("rtos_preempt_handler"));
		e.appendChild(ec);
		ec = doc.createElement("trampolines");
		e.appendChild(ec);
		eec = doc.createElement("trampoline");
		ec.appendChild(eec);
		List<InterruptServiceRoutine> ISRs = model.getISRList();
		for (InterruptServiceRoutine i : ISRs) {
      writeTrampoline(i.getSignalName(), i.getHandlerName(), eec);
		}
		if (model.getThreadCalendar().hasDispatchers()) {
		  writeTrampoline("systick", Names.getCalendarFnName(), eec);
		}
		
		e = doc.createElement("module");
		e.setAttribute("name", "armv7m.vectable");
		parent.appendChild(e);
		/*  <flash_load_addr>0x8000000</flash_load_addr> */
		ec = doc.createElement("flash_load_addr");
		e.appendChild(ec);
		ec.appendChild(doc.createTextNode("0x8000000"));
		for (InterruptServiceRoutine i : ISRs) {
		  writeVectableEntry(i.getSignalName(), i.getHandlerName(), e);
		}
    if (model.getThreadCalendar().hasDispatchers()) {
      writeVectableEntry("systick", Names.getCalendarFnName(), e);
    }
	}

	private void printKochabComponent(ThreadInstance c, Document doc, org.w3c.dom.Element parent) {
		org.w3c.dom.Element e;
		
		// TODO: This will only work if we have one thread instance per thread.
		List<ThreadInstance> til = c.getThreadImplementation().getThreadInstanceList();
		if (til.size() != 1) {
		  throw new Aadl2RtosException("At printRigelComponent: Only models with one thread instance per implementation " + 
		      "are currently supported.");
		}
		e = doc.createElement("task");
		parent.appendChild(e);

		parent = e;

		e = doc.createElement("name");
		e.appendChild(doc.createTextNode(c.getName()));
		parent.appendChild(e);

		e = doc.createElement("entry");
		e.appendChild(doc.createTextNode(c.getGeneratedEntrypoint()));
		parent.appendChild(e);

		e = doc.createElement("stack_size");
		e.appendChild(doc.createTextNode(Integer.toString(c.getStackSize())));
		parent.appendChild(e);
		
		e = doc.createElement("priority");
		e.appendChild(doc.createTextNode(Integer.toString(c.getThreadImplementation().getPriority())));
		parent.appendChild(e);
	}

	public class PriorityComparator implements Comparator<ThreadImplementation> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(
        ThreadImplementation arg0,
        ThreadImplementation arg1) {
      // TODO Auto-generated method stub
      return Integer.compare(arg1.getPriority(), arg0.getPriority());
    }
	}

	
	private void writeKochabModuleTasks(org.w3c.dom.Element parent) {

		org.w3c.dom.Element e;
    List<ThreadImplementation> tasks = model.getAllThreads();
		//e = doc.createElement("num_tasks");
		//e.appendChild(doc.createTextNode(Integer.toString(tasks.size())));
		//parent.appendChild(e);

		e = doc.createElement("tasks");
		parent.appendChild(e);
		
		int kochabLocation = 0;
		Collections.sort(tasks, new PriorityComparator());
		
		for (ThreadImplementation i : tasks) {
		  for (ThreadInstance j: i.getThreadInstanceList()) {
		    printKochabComponent(j, doc, e);
		    j.setKochabThreadLocation(kochabLocation);
		    kochabLocation++;
		  }
		}
	}

	private void writeIrqEvent(org.w3c.dom.Element e, String signalName, int eventTask, int signalNumber) {
    org.w3c.dom.Element ec;
    org.w3c.dom.Element eec;
    ec = doc.createElement("irq_event");
    e.appendChild(ec);
    eec = doc.createElement("name");
    ec.appendChild(eec);
    eec.appendChild(doc.createTextNode(signalName));
    eec = doc.createElement("task");
    ec.appendChild(eec);
    eec.appendChild(doc.createTextNode(Integer.toString(eventTask)));
    eec = doc.createElement("sig_set");
    ec.appendChild(eec);
    eec.appendChild(doc.createTextNode(Integer.toString(1 << signalNumber)));	  
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
	    int eventTask = isr.getThreadInstances().get(0).getKochabThreadLocation(); 
	    int signalNumber = isr.getDestinationPort().getPortID();
	    writeIrqEvent(e, signalName, eventTask, signalNumber);
	  }

	  // write signal numbers for all periodically dispatched threads.
	  for (Dispatcher d: model.getThreadCalendar().getPeriodicDispatchers()) {
	    String signalName = d.getPeriodicIrqSignalName();
	    for (ThreadInstance ti: d.getOwner().getThreadInstanceList()) {
	      int eventTask = ti.getKochabThreadLocation();
	      int signalNumber = d.getOwner().getSignalNumberForDispatcher(d);
	      writeIrqEvent(e, signalName, eventTask, signalNumber);
	    }
	  }
	}
	
	private void createMutex(org.w3c.dom.Element e, String name) {
    org.w3c.dom.Element ec = doc.createElement("mutex");
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
		for (ThreadInstancePort i : instances) {
		  createMutex(e, i.getMutexName());
		}
		for (SharedData d : model.getSharedDataList()) {
		  createMutex(e, d.getMutexName());
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