/*
Copyright (c) 2013, Rockwell Collins and the University of Minnesota.
Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data,
including any software or models in source or binary form, as well as any drawings, specifications,
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
 */

package edu.umn.cs.crisys.smaccm.aadl2rtos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osate.aadl2.Element;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.w3c.dom.Document;

import edu.umn.cs.crisys.smaccm.aadl2rtos.gluecode.BrtosEntrypointSkeletonGenerator;
import edu.umn.cs.crisys.smaccm.aadl2rtos.gluecode.MakefileWriter;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.AadlModelParser;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.prx.PrxGenerator;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class Aadl2RtosAction extends AadlAction {
	private Logger log;

	// Model object contains information from the AST nicely collated.
	// TODO: Figure out a principled way to deal with file locations.
	// Allow overriding file names as properties?

	@Override
	public IStatus runJob(Element sel, IProgressMonitor monitor, Logger logger) {
		log = logger;

		if (!(sel instanceof SystemImplementation)) {
			log.error("Aadl2RtosAction.runJob: Must select a system implementation");
			return Status.CANCEL_STATUS;
		}

		final SystemImplementation sysimpl = (SystemImplementation) sel;
		
		log.info("This is the sysimpl name: "+ sysimpl.getName());
		log.info("More stuff: " + sysimpl.getFullName());
		log.info("And more: " + sysimpl.getQualifiedName());
		
		
		
		monitor.beginTask("Generating BRTOS Configuration for AADL Model", IProgressMonitor.UNKNOWN);

		try {
			// Create instance model
			InstantiateModel im = new InstantiateModel(new NullProgressMonitor(), getErrorManager());
			URI uri = OsateResourceUtil.getInstanceModelURI(sysimpl);
			Resource resource = OsateResourceUtil.getEmptyAaxl2Resource(uri);
			
			// Aha!  The big time sink is here!
			SystemInstance si = im.createSystemInstance(sysimpl, resource);

			// Now harvest the stuff we need from the OSATE AST.
			Model model = new Model(sysimpl, si);
			
			// AadlModelParser initializes the Model class from the OSATE hierarchy
			// (it is a factory).
			new AadlModelParser(sysimpl, si, model, logger);
			
			// Print out C skeletons
			File dir = Util.getDirectory(sysimpl);
			BrtosEntrypointSkeletonGenerator gen = new BrtosEntrypointSkeletonGenerator(log, model, dir);
			gen.write();

			// Print out the .prx file
			List<String> cFiles = new ArrayList<String>();
			cFiles.add("gen/" + gen.getCFile().getName());
			genPrxFile(cFiles, dir, model);
			
			// Print out the makefile
			log.status("Creating Makefile in location: " + dir.getPath());
			MakefileWriter mw = new MakefileWriter(new File(dir, "out").getPath(), new File(dir, "Makefile"), model);
			mw.writeMakeFile();
			
		} catch (Aadl2RtosFailure f) {
			log.error("Analysis Exception");
			List<String> msgs = f.getMessages();
			for (String msg : msgs) {
				log.error(msg);
			}
			return Status.CANCEL_STATUS;
		} catch (Exception e) {
			log.error(e);
			return Status.CANCEL_STATUS;
		}

		try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(0, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return Status.OK_STATUS;
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

			File file = new File(dir, Util.normalizeAadlName(model.getSystemImplementation()) + ".prx");
			log.status("Creating .prx file in location: " + file);
			writeXmlFile(dom, file);

		} catch (ParserConfigurationException pce) {
			log.error("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
		}
	}
}