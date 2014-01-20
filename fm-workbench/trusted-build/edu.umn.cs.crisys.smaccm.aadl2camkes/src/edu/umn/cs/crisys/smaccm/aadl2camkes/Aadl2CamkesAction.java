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


package edu.umn.cs.crisys.smaccm.aadl2camkes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentCategory;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.Element;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.impl.DataImplementationImpl;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.DataTypeImpl;
import org.osate.aadl2.impl.SubcomponentImpl;
import org.osate.aadl2.impl.ThreadSubcomponentImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;

import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.BoolType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.Component;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.Dataport;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.FloatEnum;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.IdType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.IntType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.OpaqueType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.PeriodicDispatchMechanism;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.RealType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.RecordType;
import edu.umn.cs.crisys.smaccm.aadl2camkes.ast.Type;

import org.osate.aadl2.instance.InstanceObject;
import org.osate.analysis.lute.AadlAction;
import org.osate.analysis.lute.utils.LuteLogger;

public class Aadl2CamkesAction extends AadlAction {
	protected LuteLogger log;
	
	
	// AADL given sets from "top-level" system implementation
	protected Collection<ThreadTypeImpl> threads;
	protected Collection<DataClassifier> data = new ArrayList<DataClassifier>();
	protected Collection<InstanceObject> threadInstances;
	protected Collection<InstanceObject> dataInstances;
	protected Collection<InstanceObject> connectionInstances;
	protected Set<DataClassifier> dataTypes = new HashSet<DataClassifier>();
	
	// AST sets used for constructing ADL
	protected Map<String, Type> astTypes = new HashMap<String, Type>(); 
	
	public Aadl2CamkesAction() {
		super();
	}
	
	@Override
	public IStatus runJob(Element sel, IProgressMonitor monitor, LuteLogger logger) {
		
		log = logger;
		//console = findConsole("aadl2camkes");
		// out = console.newMessageStream(); 		
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
		try { 
			SystemInstance si = im.createSystemInstance(sysimpl, resource);
			convertToCamkesAdl(sysimpl, si); 
		} catch (Aadl2CamkesFailure f) {
			List<String> msgs = f.getMessages();
			for (String msg: msgs) {
				log.error(msg);
			}
			return Status.CANCEL_STATUS;
		} catch (Exception e) {
			log.error(e);
			return Status.CANCEL_STATUS;
		}
		
		return Status.OK_STATUS;
	}
	
	public void convertToCamkesAdl(SystemImplementation sysimpl, SystemInstance si) throws Aadl2CamkesFailure {
		// log.status("creating top level feature sets"); 
		createTopLevelInstanceSets(si); 
		createTopLevelFeatureSets(sysimpl); 
		
		// TODO: We would replace Log.status with an appropriate file stream here.
		log.status("\n\n");
		log.status("Creating CAmkES IDL description"); 
		log.status("===============================\n");
		createIdl(); 
		log.status("\n\n\n");
		log.status("Creating CAmkES ADL description"); 
		log.status("===============================\n");
		createAdl(si); 
	}

	private void createIdl() throws Aadl2CamkesFailure {
		StringBuffer sb = new StringBuffer();
		for (String key : astTypes.keySet()) {
			Type ty = astTypes.get(key).getRootType();
			if (!ty.isBaseType()) {
				if (ty instanceof RecordType) {
					sb.append("struct ").append(key).append(" ").append(ty.toString()).append("\n");
				} else if (ty instanceof OpaqueType) {
					sb.append("struct ").append(key).
						append(" { \n  // TODO: FILL IN STRUCTURE OF OPAQUE TYPE\n}; ");
				}
			}
		}
		log.status(sb.toString());
	}
	
	// find the data ports associated with some "top level" element.
	private void findDataPorts(Element elem, List<DataPortImpl> dpList) {
		if (elem instanceof DataPortImpl) {
			DataPortImpl dpi = (DataPortImpl)elem;
			dpList.add(dpi); 
		}
		for (Element child: elem.getChildren()) {
			findDataPorts(child, dpList);
		}
	}
	
	private Component createComponent(ThreadTypeImpl i) {
		
		String name = Util.normalizeAadlName(i);
		
		List<DataPortImpl> dpList = new ArrayList<DataPortImpl>();
		List<Dataport> dataportList = new ArrayList<Dataport>();
		
		findDataPorts(i, dpList);
		for (DataPortImpl dpImp: dpList) {
			dataportList.add(new Dataport(dpImp.getName(), 
					lookupType((DataClassifier)dpImp.getClassifier())));
		}
		
		// TODO: set dispatch protocol
		
		return new Component(name, dataportList, new PeriodicDispatchMechanism(10.0));
	}
	
	
	private Type lookupType(DataClassifier dc) {
		String dcName = Util.normalizeAadlName(dc);
		if (astTypes.containsKey(dcName)) {
			return new IdType(dcName);
		} else {
			return createAstType(dc);
		}
	}
	
	
	private void printComponent(Component c, StringBuffer sb) {
		
		sb.append("component ").append(c.getName()).append(" { \n");
		sb.append("  control; \n");
		for (Dataport d: c.getDataports()) {
			sb.append("  dataport ").append(d.getType()).append(" ").append(d.getId()).append("; \n");
		}
		sb.append("} ; \n\n");
	}
	
	int connection_number = 0; 
	
	private String createUniqueConnectionNumber() {
		connection_number  = connection_number + 1;
		return "conn_" + connection_number ; 
	}
	
	private void createAdl(SystemInstance si) {
	
		StringBuffer sb = new StringBuffer();
		
		for (ThreadTypeImpl i: threads) {
			Component c = createComponent(i);
			printComponent(c, sb);
		}
		// log.status("// Assembly"); 
		sb.append("assembly {\n");
		sb.append("  composition {\n");
		for (InstanceObject instance: threadInstances) {
			Classifier instanceClass = ((ComponentInstance)instance).getSubcomponent().getClassifier();
			sb.append("    component ").append(Util.normalizeAadlName(instanceClass)).append(" ").
				append(instance.getName()).append("; \n");
		}
		
		sb.append("\n\n");
		
		// for each connection instance right now, check to see if the 
		// source and destination are both threads; if so, add the connection.
		// TODO: what about hardware ports?!?
		// TODO: What about event ports?!?
		for (InstanceObject instance: connectionInstances) {
			ConnectionInstance conn = (ConnectionInstance) instance;
			ConnectionInstanceEnd connSrc = conn.getSource(); 
			ConnectionInstanceEnd connDst = conn.getDestination(); 
			Element src = connSrc.getOwner();
			Element dst = connDst.getOwner(); 
			if (src instanceof ComponentInstance && 
				dst instanceof ComponentInstance && 
				(((ComponentInstance)src).getCategory() == ComponentCategory.THREAD) && 
				(ComponentCategory.THREAD == ((ComponentInstance)dst).getCategory())) {
				
				 ComponentInstance compSrc = (ComponentInstance)src;
				 ComponentInstance compDst = (ComponentInstance)dst; 
				 
				 sb.append("    connection seL4SharedData ").append(createUniqueConnectionNumber()).
				 	append(" (from ").append(compSrc.getName()).append(".").append(connSrc.getName()). 
				 	append(",").append(" to ").append(compDst.getName()).append(".").
				 	append(connDst.getName()).append("); \n");
			}
		}
		sb.append("  }\n");
		sb.append("}\n");
		log.status(sb);
	}
	
	/*
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
	*/
	
	
	// TODO: want some kind of generic tree traverser function.
	// finds all thread classifiers starting from a top-level Element.
	private void findThreads(Element e) {
		if (e instanceof SubcomponentImpl) {
			SubcomponentImpl sub = (SubcomponentImpl)e;
			ComponentImplementation impl = sub.getComponentImplementation();
			if (sub instanceof ThreadSubcomponentImpl) {
				ThreadSubcomponentImpl tsub = (ThreadSubcomponentImpl)sub; 
				Classifier c = tsub.getClassifier();
				if (c instanceof ThreadTypeImpl) {
					ThreadTypeImpl tti = (ThreadTypeImpl)c;
					threads.add(tti);
					return; 
				} else {
					String error = "In findThreads: classifier for ThreadSubcomponentImpl is not a ThreadTypeImpl.n";
					log.error(error);
					throw new Aadl2CamkesException(error);
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
				// log.status("Adding data type or implementation: " + dpiClass.toString());
				dataTypes.add((DataClassifier)dpiClass);
			}
		}
		for (Element child: elem.getChildren()) {
			findDataTypes(child);
		}
	}
	
	
	// what about the recursive aspect of this?
	// So what we want is: for each subcomponent, record the field id and the type name.
	private Type createAstType(DataClassifier dc) {
		
		String qualifiedName = dc.getQualifiedName(); 
		String normalizedName = Util.normalizeAadlName(qualifiedName);
		// log.status("qualifiedName: " + Util.toSafeString(qualifiedName));
		if (astTypes.containsKey(normalizedName)) {
			return astTypes.get(normalizedName);
		}
		// base types defined by the data modeling annex
		if ("Base_Types::Boolean".equals(qualifiedName)) return new BoolType() ; 
		else if ("Base_Types::Integer_8".equals(qualifiedName))  return new IntType(8, true) ; 
		else if ("Base_Types::Integer_16".equals(qualifiedName)) return new IntType(16, true) ; 
		else if ("Base_Types::Integer_32".equals(qualifiedName)) return new IntType(32, true) ; 
		else if ("Base_Types::Integer_64".equals(qualifiedName)) return new IntType(64, true) ; 
		else if ("Base_Types::Unsigned_8".equals(qualifiedName)) return new IntType(8, false) ; 
		else if ("Base_Types::Unsigned_16".equals(qualifiedName)) return new IntType(16, false) ; 
		else if ("Base_Types::Unsigned_32".equals(qualifiedName)) return new IntType(32, false) ; 
		else if ("Base_Types::Unsigned_64".equals(qualifiedName)) return new IntType(64, false) ; 
		else if ("Base_Types::Float_32".equals(qualifiedName)) return new RealType(FloatEnum.SINGLE);
		else if ("Base_Types::Float_64".equals(qualifiedName)) return new RealType(FloatEnum.DOUBLE);
		else if ("Base_Types::Character".equals(qualifiedName)) {
			throw new Aadl2CamkesException("Character types are currently unsupported");
		} 
		else if ("Base_Types::String" == qualifiedName) {
			throw new Aadl2CamkesException("String types are currently unsupported");
		}
		else if (dc instanceof DataTypeImpl ){
			//TODO : Figure out just how in the heck array properties are being represented here.

			log.warn("Type: " + Util.toSafeString(dc) + "treated as opaque");
			
			return new OpaqueType();
		}
		else if (dc instanceof DataImplementationImpl) {
			// log.status("Data implementation: " + dc.toString()); 
			RecordType rt = new RecordType(); 
			DataImplementationImpl dii = (DataImplementationImpl)dc ;
			for (DataSubcomponent c: dii.getOwnedDataSubcomponents()) {
				Classifier subClass = c.getClassifier();
				if (subClass instanceof DataClassifier) {
					Type subType = createAstType((DataClassifier)subClass); 
					rt.addField(c.getName(), subType);
				}
				else {
					throw new Aadl2CamkesException("In createAstType: Subcomponent is not a data classifier"); 
				}
			}
			return rt;
		}
		else {
			throw new Aadl2CamkesException("In createAstType: data classifier is not data type or data implementation"); 
		}
	}
	
	private void createAstTypes() {
		// log.status("At createAstTypes");
		for (DataClassifier c: dataTypes) {
			// log.status("   For type: " + Util.toSafeString(c));
			Type t = createAstType(c);
			if (!t.isBaseType()) {
				// log.status("Adding type: " + Util.normalizeAadlName(c) + " to map.");
				astTypes.put(Util.normalizeAadlName(c), t);
			}
		}
		for (Type t: astTypes.values()) {
			t.init(astTypes);
		}
	}
	
	private void createTopLevelFeatureSets(SystemImplementation sysimpl) {

		// log.status("Finding threads");
		threads = new ArrayList<ThreadTypeImpl>(); 
		findThreads(sysimpl);
		
		// log.status("Finding data types");
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
			log.status(indent + "- Dataport children: ");
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
log.status("Component classifier: " + toSafeString(ti.getComponentClassifier()));
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
