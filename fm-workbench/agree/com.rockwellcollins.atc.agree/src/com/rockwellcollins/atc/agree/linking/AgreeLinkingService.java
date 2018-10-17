package com.rockwellcollins.atc.agree.linking;

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
import org.osate.aadl2.Element;
import org.osate.aadl2.PropertyValue;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.aadl2.modelsupport.scoping.Aadl2GlobalScopeUtil;
import org.osate.aadl2.util.Aadl2Util;
import org.osate.xtext.aadl2.properties.linking.PropertiesLinkingService;

import com.rockwellcollins.atc.agree.agree.ConnectionStatement;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.NamedID;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.OrderStatement;
import com.rockwellcollins.atc.agree.agree.RecordExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.TypeID;

public class AgreeLinkingService extends PropertiesLinkingService {
	public AgreeLinkingService() {
		super();
	}

	@Override
	public List<EObject> getLinkedObjects(EObject context, EReference reference, INode node)
			throws IllegalNodeException {
		String name = getCrossRefNodeAsString(node);
		// TODO This will have to be changed in the develop branch
		name = getCrossRefNodeAsString(node);

		if (context instanceof PropertyValue) {
			return findUnitLiteralAsList((Element) context, name);
		}

		if (context instanceof TypeID || context instanceof NestedDotID || context instanceof NodeEq
				|| context instanceof SynchStatement
				|| context instanceof RecordExpr || context instanceof RecordType || context instanceof GetPropertyExpr
				|| context instanceof RecordUpdateExpr || context instanceof EventExpr
				|| context instanceof OrderStatement || context instanceof ConnectionStatement) {

			// EObject e = findClassifier(context, reference, name);
			EObject e = getIndexedObject(context, reference, name);
			if (e == null) {
				e = findClassifier(context, reference, name);
			}

			if (e != null) {
				return Collections.singletonList(e);
			}

			// This code will only link to objects in the projects visible from the current project
			Iterable<IEObjectDescription> allObjectTypes = Aadl2GlobalScopeUtil.getAllEObjectDescriptions(context,
					reference.getEReferenceType());

			String contextProject = context.eResource().getURI().segment(1);
			List<String> visibleProjects = getVisibleProjects(contextProject);

			for (IEObjectDescription eod : allObjectTypes) {
				if (isVisible(eod, visibleProjects)) {
					EObject res = eod.getEObjectOrProxy();
					res = EcoreUtil.resolve(res, context.eResource().getResourceSet());

					if (res.eContainer() instanceof EnumStatement && res instanceof NamedID) {
						// special code for AGREE enumerated types
						if (((NamedID) res).getName().equals(name)) {
							return Collections.singletonList(res);
						}
					}
					if (sameName(eod, name)) {
						if (!Aadl2Util.isNull(res)) {
							return Collections.singletonList(res);
						}
					}
				}
			}
		}

		return super.getLinkedObjects(context, reference, node);
	}

	private static boolean sameName(IEObjectDescription eod, String name) {
		return eod.getName().toString().equalsIgnoreCase(name.replace("::", "."));
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

	private static List<EObject> findUnitLiteralAsList(Element context, String name) {
		EObject e = findUnitLiteral(context, name);
		if (e == null) {
			return Collections.<EObject> emptyList();
		}
		return Collections.singletonList(e);
	}

	final private static EClass UNITS_TYPE = Aadl2Package.eINSTANCE.getUnitsType();

	private static UnitLiteral findUnitLiteral(Element context, String name) {
		// TODO: Scope literals by type, but how to do we know the type of an
		// expression?
		for (IEObjectDescription desc : Aadl2GlobalScopeUtil.getAllEObjectDescriptions(context, UNITS_TYPE)) {
			UnitsType unitsType = (UnitsType) EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
			UnitLiteral literal = unitsType.findLiteral(name);
			if (literal != null) {
				return literal;
			}
		}

		return null;
	}
}
