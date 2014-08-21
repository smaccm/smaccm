package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.CAmkES;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import org.w3c.dom.Document;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.C_Type_Renderer;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.C_Type_Writer;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.eChronos.HeaderWriter;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.eChronos.MakefileWriter;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.eChronos.PrxGenerator;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.eChronos.SourceWriter;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

// TODO: need to discuss where to place .c / .h files for User-provided thread functions

public class CAmkES_CodeGenerator {
	private Model model;
	private Logger log;
	private File rootDirectory;
	private File componentsDirectory;
	private File interfacesDirectory;
	private STGroup templates;
	private String date;
	
	public List<ThreadImplementation> allThreads;

	// so write threadName_write_portName for each port.

	public CAmkES_CodeGenerator(Logger log, Model model, File dir) {
		this.log = log;
		this.model = model;
		this.rootDirectory = dir;
		
		this.templates = new STGroupFile("templates/CaMKes.stg");
		//templates.registerRenderer(Type.class, new C_Type_Renderer());

		// Create component directory
		componentsDirectory = 
		    new File(rootDirectory, "components");
		
		interfacesDirectory = 
		    new File(rootDirectory, "interfaces");
		interfacesDirectory.mkdirs();
		System.out.println(interfacesDirectory.getAbsolutePath());

    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date d = new Date();
    date = dateFormat.format(d);  
	}

	public void constructDispatchInterface(ThreadImplementation ti) throws Aadl2RtosFailure {

	  // interface directory should be already constructed
	  String name = ti.getNormalizedName();
	  File interfaceFile = new File(interfacesDirectory, Names.getComponentIdlFileName(ti));
    String path = interfaceFile.getAbsolutePath();
    
	  try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
	    
	    ST st = templates.getInstanceOf("dispatchInterfaceIdlPrefix");
	    st.add("name", name);
	    st.add("date", date);
	    st.add("path", path);
	    
	    writer.append(st.render());
	    
  	  // construct dispatchers based on 'send' types.
  	  // TODO: We will eventually want add'l dispatchers once we 
  	  // add support for RPCs
	    ST di = templates.getInstanceOf("dispatcherProc");
	    di.add("name", name);
	    di.add("datatypeHeader", Names.getSystemTypeHeaderName(model));

	    for (Dispatcher d : ti.getDispatcherList()) {
  	      ST dispatcher = templates.getInstanceOf("dispatcherPrototype");
  	      dispatcher.add("dispatcher", d);
  	      di.add("dispatchers", dispatcher);
  	  }
  	  writer.append(di.render());
  	  
  	  st = templates.getInstanceOf("dispatchInterfaceIdlPostfix"); 
  	  st.add("path", path);
  	  writer.append(st.render());
  	  
	  } catch (IOException e) {
	    log.error("IO Exception occurred when constructing a component dispatch interface.");
	    throw new Aadl2RtosFailure();
	  }
	}
	
	public void createComponentHeader(ThreadImplementation ti) throws Aadl2RtosFailure {
	  String name = ti.getNormalizedName();
    File interfaceFile = new File(interfacesDirectory, Names.getComponentIdlFileName(ti));
    String path = interfaceFile.getAbsolutePath();
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(interfaceFile))) {
      
    } catch (IOException e) {
      log.error("IO Exception occurred when constructing a component header.");
      throw new Aadl2RtosFailure();
    }
	}
	
	public void createComponent(ThreadImplementation ti) throws Aadl2RtosFailure { 
	  
    /*
     * For each component, we construct an .idl4 file 
     * for its dispatch interface (if any).  
     * 
     * A single interface per thread means that all 
     * dispatches to the thread are synchronized by CAmkES.
     * 
     * Time to get to brass tacks here.  How is interface 
     * different for passive and active components? 
     * 
     * Interface is the same; how it is coded is different.
     * 
     */
	  constructDispatchInterface(ti);
	  
	  String name = ti.getNormalizedName();
	  
	  File componentDirectory = new File(componentsDirectory, name);
	  componentDirectory.mkdirs();
	  
    File camkesFile = new File(componentDirectory, Names.getComponentCamkesFileName(ti));
    File CFile = new File(componentDirectory, Names.getComponentGlueCodeCFileName(ti));
    File HFile = new File(componentDirectory, Names.getComponentGlueCodeHFileName(ti));

    this.model.getSourceFiles().add(CFile.getPath());

    // write header for component...is this necessary?
    //BufferedWriter hwriter = 
    //    new BufferedWriter(new FileWriter(HFile));
    
	}
	
	public void createComponents() throws Aadl2RtosFailure {
	  List<ThreadImplementation> tis = model.getThreadImplementations();
	  for (ThreadImplementation ti: tis) {
	    createComponent(ti);
	  }
	}
	
	public void writeTypesHeader() throws Aadl2RtosFailure {

    String hname = Names.getSystemTypeHeaderName(model);
    String sysInstanceName = model.getSystemInstanceName(); 

    File HFile = new File(interfacesDirectory, hname);
    String path = HFile.getAbsolutePath();

    try (BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile))) { 
      ST st = templates.getInstanceOf("datatypesPrefix");
      st.add("sysInstanceName", sysInstanceName);
      st.add("date", date);
      st.add("path", path);
      hwriter.append(st.render());
      
      C_Type_Writer.writeTypes(hwriter, model, 6);

      st = templates.getInstanceOf("datatypesPostfix"); 
      st.add("sysInstanceName", sysInstanceName);
      st.add("hname", hname);
      hwriter.append(st.render());
      
  	} catch (IOException e) {
      log.error("IOException occurred during CAmkES write: " + e);
      throw new Aadl2RtosFailure();
    }
	}
	
	public void write() throws Aadl2RtosFailure {
	  writeTypesHeader();
	  createComponents();
	}

  private void writeXmlFile(Document dom, File file) {
    try {
      Transformer tr = TransformerFactory.newInstance().newTransformer();
      tr.setOutputProperty(OutputKeys.INDENT, "yes");
      tr.setOutputProperty(OutputKeys.METHOD, "xml");
      tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      // tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
      tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

      // send DOM to file
      ByteArrayOutputStream bs = new ByteArrayOutputStream();

      tr.transform(new DOMSource(dom), new StreamResult(bs));

      OutputStream outputStream = new FileOutputStream(file);
      bs.writeTo(outputStream);
      outputStream.close();

    } catch (TransformerException te) {
      log.error("writeXmlFile::Error while transforming XML: " + te.getMessage());
    } catch (IOException ie) {
      log.error("writeXmlFile::Error while writing file: " + ie.getMessage());
    }
  }

  private void genPrxFile(List<String> cFiles, File dir, Model model) throws Aadl2RtosFailure {
    Document dom;
    PrxGenerator prxGen;

    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
      // use factory to get an instance of document builder
      DocumentBuilder db = dbf.newDocumentBuilder();

      // create instance of DOM
      dom = db.newDocument();

      // create the PRX file
      prxGen = new PrxGenerator(log, dom, model, cFiles);
      prxGen.writeToDOM();

      File file = new File(dir, Util.normalizeAadlName(model.getSystemImplementationName()) + ".prx");
      log.status("Creating .prx file in location: " + file);
      writeXmlFile(dom, file);

    } catch (ParserConfigurationException pce) {
      log.error("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
    }
  }
}