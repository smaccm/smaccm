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

/*

package edu.umn.cs.crisys.smaccm.aadl2camkes;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.Element;
import org.osate.aadl2.Feature;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.impl.DataImplementationImpl;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.DataSubcomponentImpl;
import org.osate.aadl2.impl.DataTypeImpl;
import org.osate.aadl2.impl.ProcessSubcomponentImpl;
import org.osate.aadl2.impl.SubcomponentImpl;
import org.osate.aadl2.impl.SystemSubcomponentImpl;
import org.osate.aadl2.impl.ThreadImpl;
import org.osate.aadl2.impl.ThreadSubcomponentImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.FeatureCategory;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.properties.PropertyLookupException;

import com.rockwellcollins.atc.meta.AadlAction;
import com.rockwellcollins.atc.meta.Logger;

import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.BoolType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.ErrorType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.FloatEnum;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.IntType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.OpaqueType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.RealType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.RecordType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.Type;

import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.impl.ComponentInstanceImpl;
import org.osate.aadl2.instance.impl.FeatureInstanceImpl;
import org.osate.aadl2.instance.impl.SystemInstanceImpl;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

public class Aadl2CamkesAction_bad extends AadlAction {
	protected Logger log;
	MessageConsole console;
	MessageConsoleStream out;
	
	
	// AADL given sets from "top-level" system implementation
	protected Collection<ThreadTypeImpl> threads;
	protected Collection<DataClassifier> data = new ArrayList<DataClassifier>();
	protected Collection<InstanceObject> threadInstances;
	protected Collection<InstanceObject> dataInstances;
	protected Collection<InstanceObject> connectionInstances;
	protected Set<DataClassifier> dataTypes = new HashSet<DataClassifier>();
	
	// AST sets used for constructing ADL
	protected Map<DataClassifier, Type> astTypes; 
	
	public Aadl2CamkesAction() {
		super();
	}
	
	@Override
	public IStatus runJob(Element sel, IProgressMonitor monitor, Logger logger) {
		
		log = logger;
		console = findConsole("aadl2camkes");
		out = console.newMessageStream(); 		
		if (!(sel instanceof SystemImplementation)) {
			log.error("Must select a system implementation");
			return Status.CANCEL_STATUS;
		}
		final SystemImplementation sysimpl = (SystemImplementation) sel;
		
		monitor.beginTask("Running AADL --> CAmkES", IProgressMonitor.UNKNOWN);

		// gather threads and types.
		
		InstantiateModel im = new InstantiateModel(new NullProgressMonitor(), getErrorManager());
		URI uri = OsateResourceUtil.getInstanceModelURI(sysimpl);
		Resource resource = OsateResourceUtil.getEmptyAaxl2Resource(uri);
		SystemInstance si = im.createSystemInstance(sysimpl, resource);
		try { 
			convertToCamkesAdl(sysimpl, si, log); 
		} catch (Aadl2CamkesFailure f) {
			List<String> msgs = f.getMessages();
			for (String msg: msgs) {
				log.error(msg);
			}
			return Status.CANCEL_STATUS;
		}
		return Status.OK_STATUS;
	}
	
	 private MessageConsole findConsole(String name) {
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
	 
	public void convertToCamkesAdl(SystemImplementation sysimpl, SystemInstance si, Logger log) throws Aadl2CamkesFailure {
		out.println("creating top level feature sets"); 
		createTopLevelInstanceSets(si); 
		createTopLevelFeatureSets(sysimpl); 
		
		// TODO: We would replace System.out with an appropriate file stream here.
		out.println("Creating CAmkES IDL description"); 
		createIdl(out, log); 
		out.println("Creating CAmkES ADL description"); 
		createAdl(out, si, log); 
	}

	private void createIdl(MessageConsoleStream ps, Logger log) throws Aadl2CamkesFailure {
		ps.println("// Emitting IDL: ");
		for (String key : astTypes.keySet()) {
			Type ty = astTypes.get(key).getRootType();
			if (!ty.isBaseType()) {
				if (ty instanceof RecordType) {
					ps.println("struct key" + ty.toString());
					ps.println("");
				}
			}
		}
	}
	
	// find the data ports associated with some "top level" element.
	private void findDataPorts(Element elem, List<DataPortImpl> dpList) {
		if (elem instanceof DataPortImpl) {
			DataPortImpl dpi = (DataPortImpl)elem;
			dpList.add(dpi); 
		}
		for (Element child: elem.getChildren()) {
			findDataTypes(child);
		}
	}
	
	private void createComponent(MessageConsoleStream ps, ThreadTypeImpl i, Logger log) {
		List<DataPortImpl> dpList = new ArrayList();
		findDataPorts(i, dpList);
		
	}
	
	
	private void createAdl(MessageConsoleStream ps, SystemInstance si, Logger log) {
		ps.println("// Emitting ADL: ");
		ps.println("// Component types ");
		for (ThreadTypeImpl i: threads) {
			createComponent(ps, i, log);
		}
	}
	
	private PropertyExpression getProperty(NamedElement aadl, String propName) {
		Property property = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(propName);
		if (property == null) return null;
		try {
			PropertyExpression expr = PropertyUtils.getSimplePropertyValue(aadl, property);
			return expr;
		} catch (PropertyLookupException e) {
			return null;
		}
	}

	
	
	// TODO: want some kind of generic tree traverser function.
	// finds all thread classifiers starting from a top-level Element.
	private void findThreads(Element e) {
		if (e == null) { 
			log.error("Null object passed into findThreads"); 
			return; 
		}
		
		if (e instanceof SubcomponentImpl) {
			SubcomponentImpl sub = (SubcomponentImpl)e;
			ComponentImplementation impl = sub.getComponentImplementation();
			if (sub instanceof ThreadSubcomponentImpl) {
				ThreadSubcomponentImpl tsub = (ThreadSubcomponentImpl)sub; 
				Classifier c = tsub.getClassifier();
				if (c instanceof ThreadTypeImpl) {
					ThreadTypeImpl tti = (ThreadTypeImpl)c;
					out.println("Adding thread: " + tti.toString());
					threads.add(tti);
					return; 
				} else {
					log.error("classifier for ThreadSubcomponentImpl is not a ThreadTypeImpl!");
					return;
				}
			}
			else if (impl != null) {
				findThreads(impl);
			}
		}
		for (Element child: e.getChildren()) {
			findThreads(child);
		}		
	}

	// find the data types associated with some "top level" element.
	private void findDataTypes(Element elem) {
		if (elem instanceof DataPortImpl) {
			DataPortImpl dpi = (DataPortImpl)elem;
			Classifier dpiClass = dpi.getClassifier();
			if (dpiClass instanceof DataTypeImpl || 
				dpiClass instanceof DataImplementationImpl) {
				out.println("Adding data type or implementation: " + dpiClass.toString());
				dataTypes.add((DataClassifier)dpiClass);
			}
		}
		for (Element child: elem.getChildren()) {
			findDataTypes(child);
		}
	}
	
	
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
	// what about the recursive aspect of this?
	// So what we want is: for each subcomponent, record the field id and the type name.
	private Type createAstType(DataClassifier dc) {
		
		String qualifiedName = dc.getQualifiedName(); 
		if (astTypes.containsKey(dc)) {
			return astTypes.get(dc);
		}
		// base types defined by the data modeling annex
		if ("Base_Types::Boolean" == qualifiedName) return new BoolType() ; 
		else if ("Base_Types::Integer_8" == qualifiedName)  return new IntType(8, true) ; 
		else if ("Base_Types::Integer_16" == qualifiedName) return new IntType(16, true) ; 
		else if ("Base_Types::Integer_32" == qualifiedName) return new IntType(32, true) ; 
		else if ("Base_Types::Integer_64" == qualifiedName) return new IntType(64, true) ; 
		else if ("Base_Types::Unsigned_8" == qualifiedName) return new IntType(8, false) ; 
		else if ("Base_Types::Unsigned_16" == qualifiedName) return new IntType(16, false) ; 
		else if ("Base_Types::Unsigned_32" == qualifiedName) return new IntType(32, false) ; 
		else if ("Base_Types::Unsigned_64" == qualifiedName) return new IntType(64, false) ; 
		else if ("Base_Types::Float_32" == qualifiedName) return new RealType(FloatEnum.SINGLE);
		else if ("Base_Types::Float_64" == qualifiedName) return new RealType(FloatEnum.DOUBLE);
		else if ("Base_Types::Character" == qualifiedName) {
			throw new Aadl2CamkesException("Character types are currently unsupported");
		} 
		else if ("Base_Types::String" == qualifiedName) {
			throw new Aadl2CamkesException("String types are currently unsupported");
		}
		else if (dc instanceof DataTypeImpl ){
			//TODO : Figure out just how in the heck array properties are being represented here.

			log.warn("Warning: Type: " + Util.toSafeString(dc) + "treated as opaque");
			out.println("Warning: Type: " + Util.toSafeString(dc) + "treated as opaque");
			
			return new OpaqueType();
		}
		else if (dc instanceof DataImplementationImpl) {
			// out.println("Data implementation: " + dc.toString()); 
			RecordType rt = new RecordType(); 
			DataImplementationImpl dii = (DataImplementationImpl)dc ;
			for (DataSubcomponent s: dii.getOwnedDataSubcomponents()) {
				s.getD
				if (s instanceof DataSubcomponentImpl) {
					subType = createAstType((DataSubcomponentImpl)s); 
					rt.addField(s.getName(), subType);
				}
				else {
					log.error("Whoa: subcomponent is not a data subcomponent impl"); 
					return new ErrorType(); 
				}
			}
			return rt;
		}
		else {
			log.error("Whoa: data classifier is not data type or data implementation"); 
			return new ErrorType(); 
		}
	}
	
	private void createAstTypes() {
		out.println("At createAstTypes");
		for (DataClassifier c: dataTypes) {
			Type t = createAstType(c);
			astTypes.put(c, t);
		}
		for (Type t: astTypes.values()) {
			t.init(astTypes);
		}
	}
	
	private void createTopLevelFeatureSets(SystemImplementation sysimpl) {

		out.println("Finding threads");
		threads = new ArrayList<ThreadTypeImpl>(); 
		findThreads(sysimpl);
		
		out.println("Finding data types");
		for (ThreadTypeImpl t: threads) {
			findDataTypes(t);
		}
		
		// create internal ast types from the AADL types
		createAstTypes(); 
		
	}
	
	private void createTopLevelInstanceSets(SystemInstance top) {
		List<ComponentInstance> components = top.getAllComponentInstances();
		
		dataInstances = filterComponentInstances(components, ComponentCategory.DATA);
		threadInstances = filterComponentInstances(components, ComponentCategory.THREAD);
		connectionInstances = new ArrayList<InstanceObject>();
		for (ConnectionInstance ci : top.getAllConnectionInstances()) {
			connectionInstances.add(ci);
		}
	}

	private Collection<InstanceObject> filterComponentInstances(List<ComponentInstance> components, 
			ComponentCategory category) {
		Collection<InstanceObject> result = new ArrayList<InstanceObject>();
		for (ComponentInstance ci : components) {
			if (ci.getCategory() == category) {
				result.add(ci);
			}
		}
		return result;
	}

}
*/
/*	EList<PropertyAssociation> properties = dc.getOwnedPropertyAssociations();
for (PropertyAssociation i: properties) {
	out.println("  Property Association: " + toSafeString(i));
	out.println("  Property: " + toSafeString(i.getProperty()));
	out.println("  In Bindings: " + toSafeStringCollection(i.getInBindings()));
}

PropertyExpression pe = getProperty(dc, "Base_Types::Data_Representation");
if (pe != null) {
	out.println("  Data_Representation Property: " + toSafeString(pe));
}
*/

/*
private void printElement(MessageConsoleStream ps, Element e, Logger log, String indent) {
	ps.println(indent + "Element: " + Util.toSafeString(e)); 
	ps.println(indent + "characteristics: ");
	indent = indent + "   ";
	if (e instanceof ComponentInstanceImpl) {
		InstanceObject te = (InstanceObject) e;
		PropertyExpression pe = getProperty(te, "Period");
		if (null != pe) {
			ps.println(indent + "Period = " + Util.toSafeString(pe)); 
		} else {
			ps.println(indent + "No period property");
		}
		pe = getProperty(te, "Dispatch_Protocol"); 
		if (null != pe) {
			ps.println(indent + "Dispatch_Protocol = "+ Util.toSafeString(pe)); 
		} else {
			ps.println(indent + "No Dispatch_Protocol property");
		}
	}
	if (e instanceof FeatureInstanceImpl) {
		FeatureInstanceImpl impl = (FeatureInstanceImpl) e; 
		ps.println(indent + "- is a FeatureInstanceImpl");
		Feature f = impl.getFeature();
		if (f instanceof DataPort) {
			DataPort dp = (DataPort) f;
			ps.println(indent + "- is a DataPort feature: "+ Util.toSafeString(dp));
			ps.println(indent + Util.toSafeString(dp.getDataFeatureClassifier())); 
			ps.println(indent + "- Dataport children: ");
			for (Element j: f.getChildren()) {
				printElement(ps, j, log, indent + "   ");
			}
		}
		DirectionType dir = impl.getDirection(); 
		ps.println(indent + "- has direction: " + dir.toString());
		EList<ConnectionInstance> cons = impl.getSrcConnectionInstances();
		ps.println(indent + "- with src connectionInstances: ");
		for (ConnectionInstance c: cons) {
			ps.println(indent + "   " + Util.toSafeString(c)); 
		}
		cons = impl.getDstConnectionInstances();
		ps.println(indent + "- with dst connectionInstances: ");
		for (ConnectionInstance c: cons) {
			ps.println(indent + "   " + Util.toSafeString(c)); 
		}
	}
	
	ps.println(indent + "- with children: ");
	indent = indent + "   ";
	for (Element j: e.getChildren()) {
		printElement(ps, j, log, indent + "   ");
	}
}
*/

/*		for (Element i: threadInstances) {
ComponentInstanceImpl ti = (ComponentInstanceImpl)i ; 
out.println("For thread instance " + ti);
out.println("Containing classifier: " + toSafeString(ti.getContainingClassifier()));
out.println("Containing component impl: " + toSafeString(ti.getContainingComponentImpl()));
out.println("Component classifier: " + toSafeString(ti.getComponentClassifier()));
out.println("Owner " + toSafeString(ti.getOwner()));
out.println("Qualified name: " + ti.getQualifiedName());
out.println("Path name: " + ti.getPathName());
out.println("Enclosing system implementation: " + toSafeString(ti.getSystemInstance().getSystemImplementation()));
out.println("Impl: " + toSafeString(ti.getSystemImplementation()));
}
*/


/*
out.println("Elements: "); 
for (Element e: t.getChildren()) {
	out.println("   " + e.toString()); 
}
	
}
out.println("Features: "); 
for (Feature ci: sysimpl.getAllFeatures()) {
out.println("   " + ci.toString()); 
if (ci instanceof org.osate.aadl2.Data) {
	dataTypes.add(ci);
} else if (ci instanceof org.osate.aadl2.DataImplementation) {
	dataTypes.add(ci); 
}
}
*/
