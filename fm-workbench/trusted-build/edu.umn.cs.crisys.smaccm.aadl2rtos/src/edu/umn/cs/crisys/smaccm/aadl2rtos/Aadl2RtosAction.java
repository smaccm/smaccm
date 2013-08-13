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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.osate.aadl2.Element;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.analysis.lute.AadlAction;
import org.osate.analysis.lute.utils.Logger;
import org.w3c.dom.Document;

import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.printing.BrtosEntrypointSkeletonGenerator;
import edu.umn.cs.crisys.smaccm.aadl2rtos.printing.PrxGenerator;


public class Aadl2RtosAction extends AadlAction {
	protected Logger log;
	
	// Model object contains information from the AST nicely collated.
	protected Model aadlModel; 
	
	// Path to file location that contained instance element.
	protected String fp;
	
	// Path 
	
	public Aadl2RtosAction() {
		super();
	}
	
	// TODO: Figure out a principled way to deal with file locations.
	// Allow overriding file names as properties?
	
	@Override
	public IStatus runJob(Element sel, IProgressMonitor monitor, Logger logger) {
		
		log = logger;
		aadlModel = new Model(logger);
		
		if (!(sel instanceof SystemImplementation)) {
			log.error("Must select a system implementation");
			return Status.CANCEL_STATUS;
		}
		final SystemImplementation sysimpl = (SystemImplementation) sel;	
		
		monitor.beginTask("Generating BRTOS Configuration for AADL Model", 
					IProgressMonitor.UNKNOWN);

		try { 
			// Create instance model
			InstantiateModel im = new InstantiateModel(new NullProgressMonitor(), getErrorManager());
			URI uri = OsateResourceUtil.getInstanceModelURI(sysimpl);
			Resource resource = OsateResourceUtil.getEmptyAaxl2Resource(uri);
			SystemInstance si = im.createSystemInstance(sysimpl, resource);
			
			// Now harvest the stuff we need from the OSATE AST. 
			aadlModel.init(sysimpl, si);
			
			// Print out C skeletons
			File dir = getDirectory(sysimpl);
			BrtosEntrypointSkeletonGenerator gen = 
				new BrtosEntrypointSkeletonGenerator(log, aadlModel, dir);
			gen.write();
			
			// Print out the .prx file
			List<String> cFiles = new ArrayList<String>();
			cFiles.add("gen/" + gen.getCFile().getName());
			genPrxFile(cFiles, dir); 
		} catch (Aadl2RtosFailure f) {
			log.error("Analysis Exception");
			List<String> msgs = f.getMessages();
			for (String msg: msgs) {
				log.error(msg);
			}
			return Status.CANCEL_STATUS;
		} catch (Exception e) {
			log.error(Util.throwableToString(e));
			return Status.CANCEL_STATUS;
		}
		
		try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(0, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
		
		return Status.OK_STATUS;
	}

	public File getDirectory(EObject e) {
		URI uri = e.eResource().getURI();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(new Path(uri.toPlatformString(true)));
		return file.getParent().getLocation().toFile();
	}
	
	public void writeXmlFile(Document dom, File file) {
		try { 
	        Transformer tr = TransformerFactory.newInstance().newTransformer();
	        tr.setOutputProperty(OutputKeys.INDENT, "yes");
	        tr.setOutputProperty(OutputKeys.METHOD, "xml");
	        tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	        //tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
	        tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	
	        // send DOM to file
	        ByteArrayOutputStream bs = new ByteArrayOutputStream();
	        
	        tr.transform(new DOMSource(dom), new StreamResult(bs));
	        //log.status(bs);
	        OutputStream outputStream = new FileOutputStream(file);
	        bs.writeTo(outputStream);
	        outputStream.close();
		} catch (TransformerException te) {
	        log.error("writeXmlFile::Error while transforming XML: " + te.getMessage());
	    } catch (IOException ie) {
	    	log.error("writeXmlFile::Error while writing file: " + ie.getMessage());
	    }
	}
	
	public void genPrxFile(List<String> cFiles, File dir) throws Aadl2RtosFailure {
		Document dom;
		PrxGenerator prxGen;

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    try {
	        // use factory to get an instance of document builder
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        // create instance of DOM
	        dom = db.newDocument();
	        
	        // create the PRX file
	        prxGen = new PrxGenerator(log, dom, aadlModel, cFiles);
	        prxGen.writeToDOM();

	        File file = new File(dir, Util.normalizeAadlName(aadlModel.sysimpl) + ".prx");
        	log.status("Creating .prx file in location: " + file); 
	        writeXmlFile(dom, file);
	    }
	    catch (ParserConfigurationException pce) {
		    log.error("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
		}
	}
}

/*	EList<PropertyAssociation> properties = dc.getOwnedPropertyAssociations();
for (PropertyAssociation i: properties) {
	log.status("  Property Association: " + toSafeString(i));
	log.status("  Property: " + toSafeString(i.getProperty()));
	log.status("  In Bindings: " + toSafeStringCollection(i.getInBindings()));
}

PropertyExpression pe = getProperty(dc, "Base_Types::Data_Representation");
if (pe != null) {
	log.status("  Data_Representation Property: " + toSafeString(pe));
}
*/

/*
private boolean isBaseType(DataClassifier dc) {
	if (dc instanceof DataTypeImpl) {
		String qualifiedName = dc.getQualifiedName(); 
		return 
				("Base_Types::Boolean" == qualifiedName) ||
				("Base_Types::Integer_8" == qualifiedName) || 
				("Base_Types::Integer_16" == qualifiedName) || 
				("Base_Types::Integer_32" == qualifiedName) ||
				("Base_Types::Integer_64" == qualifiedName) || 
				("Base_Types::Unsigned_8" == qualifiedName) || 
				("Base_Types::Unsigned_16" == qualifiedName) ||
				("Base_Types::Unsigned_32" == qualifiedName) ||
				("Base_Types::Unsigned_64" == qualifiedName) ||
				("Base_Types::Float_32" == qualifiedName) || 
				("Base_Types::Float_64" == qualifiedName) || 
				("Base_Types::Character" == qualifiedName) || 
				("Base_Types::String" == qualifiedName) ; 
	} else {
		return false;
	}
}
*/

/*
private void printElement(MessageConsoleStream ps, Element e, Logger log, String indent) {
	log.status(indent + "Element: " + Util.toSafeString(e)); 
	log.status(indent + "characteristics: ");
	indent = indent + "   ";
	if (e instanceof ComponentInstanceImpl) {
		InstanceObject te = (InstanceObject) e;
		PropertyExpression pe = getProperty(te, "Period");
		if (null != pe) {
			log.status(indent + "Period = " + Util.toSafeString(pe)); 
		} else {
			log.status(indent + "No period property");
		}
		pe = getProperty(te, "Dispatch_Protocol"); 
		if (null != pe) {
			log.status(indent + "Dispatch_Protocol = "+ Util.toSafeString(pe)); 
		} else {
			log.status(indent + "No Dispatch_Protocol property");
		}
	}
	if (e instanceof FeatureInstanceImpl) {
		FeatureInstanceImpl impl = (FeatureInstanceImpl) e; 
		log.status(indent + "- is a FeatureInstanceImpl");
		Feature f = impl.getFeature();
		if (f instanceof DataPort) {
			DataPort dp = (DataPort) f;
			log.status(indent + "- is a DataPort feature: "+ Util.toSafeString(dp));
			log.status(indent + Util.toSafeString(dp.getDataFeatureClassifier())); 
			log.status(indent + "- PortImplWrapper children: ");
			for (Element j: f.getChildren()) {
				printElement(ps, j, log, indent + "   ");
			}
		}
		DirectionType dir = impl.getDirection(); 
		log.status(indent + "- has direction: " + dir.toString());
		EList<ConnectionInstance> cons = impl.getSrcConnectionInstances();
		log.status(indent + "- with src connectionInstances: ");
		for (ConnectionInstance c: cons) {
			log.status(indent + "   " + Util.toSafeString(c)); 
		}
		cons = impl.getDstConnectionInstances();
		log.status(indent + "- with dst connectionInstances: ");
		for (ConnectionInstance c: cons) {
			log.status(indent + "   " + Util.toSafeString(c)); 
		}
	}
	
	log.status(indent + "- with children: ");
	indent = indent + "   ";
	for (Element j: e.getChildren()) {
		printElement(ps, j, log, indent + "   ");
	}
}
*/

/*		for (Element i: threadInstances) {
ComponentInstanceImpl ti = (ComponentInstanceImpl)i ; 
log.status("For thread instance " + ti);
log.status("Containing classifier: " + toSafeString(ti.getContainingClassifier()));
log.status("Containing component impl: " + toSafeString(ti.getContainingComponentImpl()));
log.status("ThreadWrapper classifier: " + toSafeString(ti.getComponentClassifier()));
log.status("Owner " + toSafeString(ti.getOwner()));
log.status("Qualified name: " + ti.getQualifiedName());
log.status("Path name: " + ti.getPathName());
log.status("Enclosing system implementation: " + toSafeString(ti.getSystemInstance().getSystemImplementation()));
log.status("Impl: " + toSafeString(ti.getSystemImplementation()));
}
*/


/*
log.status("Elements: "); 
for (Element e: t.getChildren()) {
	log.status("   " + e.toString()); 
}
	
}
log.status("Features: "); 
for (Feature ci: sysimpl.getAllFeatures()) {
log.status("   " + ci.toString()); 
if (ci instanceof org.osate.aadl2.Data) {
	dataTypes.add(ci);
} else if (ci instanceof org.osate.aadl2.DataImplementation) {
	dataTypes.add(ci); 
}
}
*/
/*	 private MessageConsole findConsole(String name) {
ConsolePlugin plugin = ConsolePlugin.getDefault();
IConsoleManager conMan = plugin.getConsoleManager();
IConsole[] existing = conMan.getConsoles();
for (int i = 0; i < existing.length; i++)
   if (name.equals(existing[i].getName()))
      return (MessageConsole) existing[i];
//no console found, so create a new one
MessageConsole myConsole = new MessageConsole(name, null);
conMan.addConsoles(new IConsole[]{myConsole});
return myConsole;
}
*/	 
