package edu.umn.cs.crisys.smaccm.aadl2rtos.printing;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osate.analysis.lute.utils.Logger;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Util;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.ThreadWrapper;

public class PrxGenerator {

	Document doc; 
	Model model; 
	Logger log;
	Collection<String> generatedFileNames;
	
	public PrxGenerator(Logger log, Document dom, Model model, 
					    Collection<String> generatedFileNames) {
		super(); 
		this.log = log;
		this.doc = dom;
		this.model = model;
		this.generatedFileNames = generatedFileNames;
	}; 
	
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
        writeRigelModule(parent);
        writeUserEntrypointModules(parent);
        writeGeneratedEntrypointModules(parent);
	};
	
	private void writeUserEntrypointModules(org.w3c.dom.Element parent) {
	    org.w3c.dom.Element e = null;

	    Comment c = doc.createComment("Modules for Thread Entrypoint Files");
	    parent.appendChild(c);
	    
	    Set<String> fileSet = new HashSet<String>(); 
		for (ThreadWrapper w: model.getTaskThreads()) {
			fileSet.addAll(Util.assertNonNull(w.getSourceTextListOpt(), "Thread" + (w.getName()) + " is required to have an associated source file for its entrypoint"));
		}
		for (ThreadWrapper w: model.getISRThreads()) {
			fileSet.addAll(Util.assertNonNull(w.getSourceTextListOpt(), "Thread" + (w.getName()) + " is required to have an associated source file for its entrypoint"));
		}
		for (String s: fileSet) {
	        e = doc.createElement("module");
	        String moduleName = Util.pathRemoveExtension(s);
	        e.setAttribute("name", moduleName);
			parent.appendChild(e);
		}
	}
	
	private void writeGeneratedEntrypointModules(org.w3c.dom.Element parent) {
	    Comment c = doc.createComment("Modules for AADL-Generated Task Main Functions");
	    parent.appendChild(c);
	    for (String s: generatedFileNames) {
		    Element e = doc.createElement("module");
		    e.setAttribute("name", Util.pathRemoveExtension(s));
		    parent.appendChild(e);
	    }
	}
	
    // boilerplate modules from NICTA example
	private void writeBoilerplateModules(org.w3c.dom.Element parent) {
	    org.w3c.dom.Element e = null;
	    org.w3c.dom.Element ec = null;
		
        e = doc.createElement("module");
        e.setAttribute("name", "armv7m/build");
        parent.appendChild(e);
        e = doc.createElement("module");
        e.setAttribute("name", "armv7m/ctxt-switch");
        parent.appendChild(e);
        
        e = doc.createElement("module");
        e.setAttribute("name", "armv7m/semihost-debug");
        parent.appendChild(e);

        e = doc.createElement("module");
        e.setAttribute("name", "generic/debug");
        ec = doc.createElement("ll_debug");
        ec.appendChild(doc.createTextNode("armv7m_semihost_"));
        e.appendChild(ec);
        ec = doc.createElement("prefix");
        e.appendChild(ec);
        parent.appendChild(e);
	}
	
	// vectable module containing ISRs
	private void writeVectableModule(org.w3c.dom.Element parent) {
	    org.w3c.dom.Element e = null;
	    org.w3c.dom.Element ec = null;

	    e = doc.createElement("module");
	    e.setAttribute("name", "armv7m/vectable");
	    parent.appendChild(e);
	    
	    List<ThreadWrapper> ISRs = model.getISRThreads();
	    for (ThreadWrapper i: ISRs) {
	    	ec = doc.createElement(Util.assertNonNull(i.getSmaccmSysSignalOpt(), "ISR thread must have an associated signal"));
	    	ec.appendChild(doc.createTextNode(Util.assertNonNull(i.getComputeEntrypointOpt(), "ISR thread must have a compute entrypoint")));
	    	e.appendChild(ec);
	    }
	}
	
	
	private void printRigelComponent(ThreadWrapper c, Document doc, org.w3c.dom.Element parent) {
		org.w3c.dom.Element e; 
		int index = model.getThreadTaskIndex(c);
		
		e = doc.createElement("task"); 
		parent.appendChild(e);
		
		parent = e; 
		e = doc.createElement("idx");
		e.appendChild(doc.createTextNode(Integer.toString(index)));
		parent.appendChild(e);
		
		e = doc.createElement("name");
		e.appendChild(doc.createTextNode(c.getName()));
		parent.appendChild(e);
		
		e = doc.createElement("entry");
		e.appendChild(doc.createTextNode(c.getGeneratedEntrypoint()));
		parent.appendChild(e);
		
		e = doc.createElement("stack_size");
		e.appendChild(doc.createTextNode(Util.assertNonNull(c.getStackSizeInBytesOpt(), "Task requires stack size").toString()));
		
		parent.appendChild(e);
	}
	
	private void writeRigelModuleTasks(org.w3c.dom.Element parent) {
		
		org.w3c.dom.Element e; 
		e = doc.createElement("num_tasks");
		List<ThreadWrapper> tasks = model.getTaskThreads();
		e.appendChild(doc.createTextNode(Integer.toString(tasks.size())));
		parent.appendChild(e);
		
		e = doc.createElement("tasks");
		parent.appendChild(e);
		
		for (ThreadWrapper i: tasks) {
			printRigelComponent(i, doc, e);
		}
	}

	private void writeRigelModule(org.w3c.dom.Element parent) {
	    org.w3c.dom.Element e = null;
	    org.w3c.dom.Element ec = null;
        // create data elements and place them under root
        e = doc.createElement("module");
        e.setAttribute("name", "armv7m/rtos-rigel");
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
        
        ec = doc.createElement("num_irq_events");
        ec.appendChild(doc.createTextNode("0"));
        e.appendChild(ec);
        
        ec = doc.createElement("irq_events");
        e.appendChild(ec);
        
        writeRigelModuleTasks(e);
        parent.appendChild(e);
	}


}
