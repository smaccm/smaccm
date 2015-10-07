package com.rockwellcollins.atc.resolute.linking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.PropertyValue;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.aadl2.util.Aadl2Util;
import org.osate.xtext.aadl2.properties.linking.PropertiesLinkingService;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

import com.rockwellcollins.atc.resolute.resolute.ClaimArg;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.QuantArg;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;

public class ResoluteLinkingService extends PropertiesLinkingService {
    @Override
    public List<EObject> getLinkedObjects(EObject context, EReference reference, INode node)
            throws IllegalNodeException {
        String name = getCrossRefNodeAsString(node);
        EObject e = getLinkedObject(context, reference, name);
        if (e != null) {
            return Collections.singletonList(e);
        }
        return super.getLinkedObjects(context, reference, node);
    }

    private EObject getLinkedObject(EObject context, EReference reference, String name) {
//		name = name.replaceAll("::", ".");
        
        if (context instanceof PropertyValue) {
            return getUnitLiteral(context, name);
        }

        if (context instanceof FnCallExpr) {
            return getFunctionDefinition(context, name);
        }

        if (context instanceof ClaimArg) {
			if (reference == ResolutePackage.eINSTANCE.getClaimArg_Unit()) {
				return getUnitLiteral(context, name);
			}
            return getIndexedObject(context, reference, name);
        }
        
        if(context instanceof QuantArg) {
            return getIndexedObject(context, reference, name);
        }

        if (context instanceof IdExpr || context instanceof NestedDotID) {
            EObject e = getIndexedObject(context, reference, name);
            if (e != null) {
                return e;
            }
            
			Iterable<IEObjectDescription> allObjectTypes = EMFIndexRetrieval.getAllEObjectsOfTypeInWorkspace(context,
					reference.getEReferenceType());
            
            URI contextUri = context.eResource().getURI();
            String contextProject = contextUri.segment(1);
            for (IEObjectDescription eod : allObjectTypes) {
                if (eod.getName().toString().equalsIgnoreCase(name)) {
                    EObject res = eod.getEObjectOrProxy();
                    res = EcoreUtil.resolve(res, context.eResource().getResourceSet());
                    if (!Aadl2Util.isNull(res)){
                        URI linkUri = res.eResource().getURI();
                        if(linkUri.segment(1).equals(contextProject)){
                            return res;
                        }
                    }
                }
            }
            
           // e = EMFIndexRetrieval.getEObjectOfType(context, reference.getEReferenceType(), name);
           // if (e != null) {
           //     return e;
           // }

            e = getConstantDefinition(context, name);
            if (e != null) {
                return e;
            }

			e = findPropertySetElement(context, reference, name);
			if (e != null) {
				return e;
			}
        }

        return null;
    }

	@Override
	public String getQualifiedName(String packageOrPropertySetName, String elementName) {
		if (packageOrPropertySetName == null) {
			return elementName;
		} else {
			return packageOrPropertySetName + "." + elementName;
		}
	}

    private static EObject getFunctionDefinition(EObject context, String name) {
        return getNamedElementByType(context, name, ResolutePackage.Literals.FUNCTION_DEFINITION);
    }

    private static EObject getConstantDefinition(EObject context, String name) {
        return getNamedElementByType(context, name, ResolutePackage.Literals.CONSTANT_DEFINITION);
    }

    private static EObject getNamedElementByType(EObject context, String name, EClass eclass) {
        // This code will only link to objects in the projects visible from the current project
		Iterable<IEObjectDescription> allObjectTypes = EMFIndexRetrieval.getAllEObjectsOfTypeInWorkspace(context,
				eclass);

        String contextProject = context.eResource().getURI().segment(1);
        List<String> visibleProjects = getVisibleProjects(contextProject);

        for (IEObjectDescription eod : allObjectTypes) {
            if (sameName(eod, name) && isVisible(eod, visibleProjects)) {
                EObject res = eod.getEObjectOrProxy();
                res = EcoreUtil.resolve(res, context.eResource().getResourceSet());
                if (!Aadl2Util.isNull(res)) {
                    return res;
                }
            }
        }

        return null;
    }

    private static boolean sameName(IEObjectDescription eod, String name) {
        return eod.getName().getLastSegment().equalsIgnoreCase(name);
    }

    private static boolean isVisible(IEObjectDescription eod, List<String> visibleProjects) {
        URI uri = eod.getEObjectURI();
        String project = uri.segment(1);
        return visibleProjects.contains(project);
    }

    private static List<String> getVisibleProjects(String contextProjectName) {
        List<String> result = new ArrayList<>();
        result.add(contextProjectName);

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IProject contextProject = root.getProject(URI.decode(contextProjectName));
		if (!contextProject.exists() || !contextProject.isAccessible() || !contextProject.isOpen())
			return result;
        try {
            IProjectDescription description = contextProject.getDescription();
            for (IProject referencedProject : description.getReferencedProjects()) {
                result.add(URI.encodeSegment(referencedProject.getName(), false));
            }
        } catch (CoreException ex) {
            ex.printStackTrace();
        }

        return result;
    }

    final private static EClass UNITS_TYPE = Aadl2Package.eINSTANCE.getUnitsType();

    private static UnitLiteral getUnitLiteral(EObject context, String name) {
        // TODO: Scope literals by type, but how to do we know the type of an
        // expression?
		for (IEObjectDescription desc : EMFIndexRetrieval.getAllEObjectsOfTypeInWorkspace(context, UNITS_TYPE)) {
            UnitsType unitsType = (UnitsType) EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
            UnitLiteral literal = unitsType.findLiteral(name);
            if (literal != null) {
                return literal;
            }
        }

        return null;
    }
    
}
