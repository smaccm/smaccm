package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.eChronos;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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

import org.w3c.dom.Document;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

// TODO: need to discuss where to place .c / .h files for User-provided thread functions

public class EChronos_CodeGenerator {
	private Model model;
	// private AstHelper astHelper;

	private Logger log;
	private List<OutputEventPort> events;
	private File dir;
	
	public List<ThreadImplementation> allThreads;

	// so write threadName_write_portName for each port.

	public EChronos_CodeGenerator(Logger log, Model model, File dir) {
		this.log = log;
		this.model = model;
		this.dir = dir;
	}

	public int getSignalSetSize() {
		return events.size();
	}

	// For each thread, get all events (determined by the out-event ports)
	private void defineSignalSet() {
		events = new ArrayList<OutputEventPort>();		

		for (ThreadImplementation tw : model.getThreadImplementations()) {
			events.addAll(tw.getOutputEventPortList());
			events.addAll(tw.getOutputEventDataPortList());
		}
	}

	public void write() throws Aadl2RtosFailure {
	  
    // Create source directories
    File genDir = new File(dir, "gen");
    genDir.mkdirs();

    // Create new source files
    String name = Util.normalizeAadlName(model.getSystemInstanceName());
    File CFile = new File(genDir, name + ".c");
    File HFile = new File(genDir, name + ".h");

    this.model.getSourceFiles().add(CFile.getPath());

    // Define signal set from event ports
    // write implementations for event ports set the signals
    // main tasking threads wait for signal set
    // and dispatch to appropriate entrypoint
    defineSignalSet();

    
    // TODO: This code may leave file handles open.
    try {
			log.status("Writing AADL middleware header files...");
			BufferedWriter hwriter = new BufferedWriter(new FileWriter(HFile));
			HeaderWriter headerWriter = new HeaderWriter(hwriter, CFile, HFile, model);
			headerWriter.writeHeader();
      hwriter.close();

			log.status("Writing AADL middleware source files...");
			BufferedWriter cwriter = new BufferedWriter(new FileWriter(CFile));
			SourceWriter sourceWriter = new SourceWriter(cwriter, CFile, HFile, model, events);
			sourceWriter.writeSource();
			cwriter.close();


      // Print out the .prx file
      List<String> cFiles = new ArrayList<String>();
      cFiles.add("gen/" + CFile.getName());
      genPrxFile(cFiles, dir, model);
      
      // Print out the makefile
      log.status("Creating Makefile in location: " + dir.getPath());
      MakefileWriter mw = new MakefileWriter(new File(dir, "out").getPath(), new File(dir, "Makefile"), model);
      mw.writeMakeFile();
			
		} catch (IOException e) {
			log.error("Unable to create output streams.\n");
		}
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