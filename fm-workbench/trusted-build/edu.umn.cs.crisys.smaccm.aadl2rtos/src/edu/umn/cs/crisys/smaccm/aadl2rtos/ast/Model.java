/*
Copyright (c) 2011, 2013 Rockwell Collins and the University of Minnesota.
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

package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.osate.aadl2.instance.InstanceObject;
import org.osate.aadl2.instance.SystemInstance;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Util;

public class Model {
    protected Logger log;

    // Lexical information
    public String osNamePrefix;
    public String aadlNamePrefix;

    // File information information
    public String glueCodeFileName;
    public String locationOpt;

    // Top level containers for the implementation and instance model.
    public SystemImplementation sysimpl;
    public SystemInstance sysinst;

    // Classifier (type) objects
    public Collection<ThreadTypeImpl> threads;
    public Set<DataClassifier> dataTypes = new HashSet<DataClassifier>();

    // Instance objects
    public Collection<InstanceObject> threadInstances;
    public Collection<InstanceObject> dataInstances;
    public Collection<ConnectionInstance> connectionInstances;

    // Derived type objects for easier manipulation.
    public Map<String, Type> astTypes = new HashMap<String, Type>();

    public Model(Logger logger) {
        super();
        this.log = logger;
    };

    public void init(SystemImplementation sysimpl, SystemInstance si) throws Aadl2RtosFailure {
        this.sysimpl = sysimpl;
        this.sysinst = si;
        createTopLevelFeatureSets(sysimpl);
        createTopLevelInstanceSets(si);
    }

    public int getThreadTaskIndex(ThreadWrapper tti) {
        List<ThreadWrapper> taskThreads = getTaskThreads();
        return taskThreads.indexOf(tti);
    }

    // find the data ports associated with some "top level" element.
    public void findDataPorts(Element elem, List<DataPortImpl> dpList) {
        if (elem instanceof DataPortImpl) {
            DataPortImpl dpi = (DataPortImpl) elem;
            dpList.add(dpi);
        }
        for (Element child : elem.getChildren()) {
            findDataPorts(child, dpList);
        }
    }

    private void findThreads(Element e) {
        if (e instanceof SubcomponentImpl) {
            SubcomponentImpl sub = (SubcomponentImpl) e;
            ComponentImplementation impl = sub.getComponentImplementation();
            if (sub instanceof ThreadSubcomponentImpl) {
                ThreadSubcomponentImpl tsub = (ThreadSubcomponentImpl) sub;
                Classifier c = tsub.getClassifier();
                if (c instanceof ThreadTypeImpl) {
                    ThreadTypeImpl tti = (ThreadTypeImpl) c;
                    threads.add(tti);
                    return;
                } else {
                    String error = "In findThreads: classifier for ThreadSubcomponentImpl is not a ThreadTypeImpl.n";
                    log.error(error);
                    throw new Aadl2RtosException(error);
                }
            } else if (impl != null) {
                findThreads(impl);
            }
        }
        for (Element child : e.getChildren()) {
            findThreads(child);
        }
    }

    public List<ThreadWrapper> getAllThreads() {
        List<ThreadWrapper> allThreads = new ArrayList<ThreadWrapper>();
        for (ThreadTypeImpl th : threads) {
            ThreadWrapper tw = new ThreadWrapper(th, this);
            allThreads.add(tw);
        }
        return allThreads;
    }

    public List<ThreadWrapper> getTaskThreads() {
        List<ThreadWrapper> taskThreads = new ArrayList<ThreadWrapper>();
        for (ThreadTypeImpl th : threads) {
            ThreadWrapper tw = new ThreadWrapper(th, this);
            if (!tw.isISRThread()) {
                taskThreads.add(tw);
            }
        }
        return taskThreads;
    }

    public List<ThreadWrapper> getISRThreads() {
        List<ThreadWrapper> taskThreads = new ArrayList<ThreadWrapper>();
        for (ThreadTypeImpl th : threads) {
            ThreadWrapper tw = new ThreadWrapper(th, this);
            if (tw.isISRThread()) {
                taskThreads.add(tw);
            }
        }
        return taskThreads;
    }

    // find the data types associated with some "top level" element.
    private void findDataTypes(Element elem) {
        if (elem instanceof DataPortImpl) {
            DataPortImpl dpi = (DataPortImpl) elem;
            Classifier dpiClass = dpi.getClassifier();
            if (dpiClass instanceof DataTypeImpl || dpiClass instanceof DataImplementationImpl) {
                // log.status("Adding data type or implementation: " +
                // dpiClass.toString());
                dataTypes.add((DataClassifier) dpiClass);
            }
        }
        for (Element child : elem.getChildren()) {
            findDataTypes(child);
        }
    }

    public Type lookupType(DataClassifier dc) {
        String dcName = Util.normalizeAadlName(dc);
        if (astTypes.containsKey(dcName)) {
            return new IdType(dcName);
        } else {
            return createAstType(dc);
        }
    }

    // what about the recursive aspect of this?
    // So what we want is: for each subcomponent, record the field id and the
    // type name.
    private Type createAstType(DataClassifier dc) {

        String qualifiedName = dc.getQualifiedName();
        String normalizedName = Util.normalizeAadlName(qualifiedName);
        // log.status("qualifiedName: " + Util.toSafeString(qualifiedName));
        if (astTypes.containsKey(normalizedName)) {
            return astTypes.get(normalizedName);
        }
        // base types defined by the data modeling annex
        if ("Base_Types::Boolean".equals(qualifiedName)) {
            return new BoolType();
        } else if ("Base_Types::Integer_8".equals(qualifiedName)) {
            return new IntType(8, true);
        } else if ("Base_Types::Integer_16".equals(qualifiedName)) {
            return new IntType(16, true);
        } else if ("Base_Types::Integer_32".equals(qualifiedName)) {
            return new IntType(32, true);
        } else if ("Base_Types::Integer_64".equals(qualifiedName)) {
            return new IntType(64, true);
        } else if ("Base_Types::Unsigned_8".equals(qualifiedName)) {
            return new IntType(8, false);
        } else if ("Base_Types::Unsigned_16".equals(qualifiedName)) {
            return new IntType(16, false);
        } else if ("Base_Types::Unsigned_32".equals(qualifiedName)) {
            return new IntType(32, false);
        } else if ("Base_Types::Unsigned_64".equals(qualifiedName)) {
            return new IntType(64, false);
        } else if ("Base_Types::Float_32".equals(qualifiedName)) {
            return new RealType(FloatEnum.SINGLE);
        } else if ("Base_Types::Float_64".equals(qualifiedName)) {
            return new RealType(FloatEnum.DOUBLE);
        } else if ("Base_Types::Character".equals(qualifiedName)) {
            throw new Aadl2RtosException("Character types are currently unsupported");
        } else if ("Base_Types::String" == qualifiedName) {
            throw new Aadl2RtosException("String types are currently unsupported");
        } else if (dc instanceof DataTypeImpl) {
            // TODO : Figure out just how in the heck array properties are being
            // represented here.
            log.warn("Type: " + Util.toSafeString(dc) + "treated as opaque");
            return new OpaqueType();
        } else if (dc instanceof DataImplementationImpl) {
            // log.status("Data implementation: " + dc.toString());
            RecordType rt = new RecordType();
            DataImplementationImpl dii = (DataImplementationImpl) dc;
            for (DataSubcomponent c : dii.getOwnedDataSubcomponents()) {
                Classifier subClass = c.getClassifier();
                if (subClass instanceof DataClassifier) {
                    Type subType = createAstType((DataClassifier) subClass);
                    rt.addField(c.getName(), subType);
                } else {
                   // throw new Aadl2RtosException(
                   //         "In createAstType: Subcomponent is not a data classifier");
                }
            }
            return rt;
        } else {
            throw new Aadl2RtosException(
                    "In createAstType: data classifier is not data type or data implementation");
        }
    }

    private void createAstTypes() {
        // log.status("At createAstTypes");
        for (DataClassifier c : dataTypes) {
            // log.status("   For type: " + Util.toSafeString(c));
            Type t = createAstType(c);
            if (!t.isBaseType()) {
                // log.status("Adding type: " + Util.normalizeAadlName(c) +
                // " to map.");
                astTypes.put(Util.normalizeAadlName(c), t);
            }
        }
        for (Type t : astTypes.values()) {
            t.init(astTypes);
        }
    }

    private void createTopLevelFeatureSets(SystemImplementation sysimpl) {

        // log.status("Finding threads");
        threads = new ArrayList<ThreadTypeImpl>();
        findThreads(sysimpl);

        // log.status("Finding data types");
        for (ThreadTypeImpl t : threads) {
            findDataTypes(t);
        }

        // create internal ast types from the AADL types
        createAstTypes();
    }

    private void createTopLevelInstanceSets(SystemInstance top) {
        List<ComponentInstance> components = top.getAllComponentInstances();

        dataInstances = filterComponentInstances(components, ComponentCategory.DATA);
        threadInstances = filterComponentInstances(components, ComponentCategory.THREAD);
        connectionInstances = new ArrayList<ConnectionInstance>();
        for (ConnectionInstance ci : top.getAllConnectionInstances()) {
            connectionInstances.add(ci);

            /*
             * // TODO: remove this. log.status(ci.toString()); log.status("   "
             * + ci.getDestination().toString()); log.status("  " +
             * ci.getDestination().getInstantiatedObjects().toString());
             * log.status("       " +
             * ci.getDestination().getComponentInstance().toString());
             * log.status("   " + ci.getSource().toString());
             * ComponentInstanceImpl compInst =
             * (ComponentInstanceImpl)ci.getSource().getOwner();
             * log.status("   " +
             * compInst.basicGetSubcomponent().getComponentType());
             */
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
