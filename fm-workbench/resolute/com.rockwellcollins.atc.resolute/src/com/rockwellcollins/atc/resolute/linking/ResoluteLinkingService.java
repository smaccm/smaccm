package com.rockwellcollins.atc.resolute.linking;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.PropertyValue;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.xtext.aadl2.properties.linking.PropertiesLinkingService;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;

import com.rockwellcollins.atc.resolute.resolute.ClaimArg;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
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
		if (context instanceof PropertyValue) {
			return getUnitLiteral(context, name);
		}

		if (context instanceof FnCallExpr) {
			return getFunctionDefinition(context, name);
		}

		if (context instanceof ClaimArg) {
			return findClassifier(context, reference, name);
		}

		if (context instanceof IdExpr) {
			EObject e = findClassifier(context, reference, name);
			if (e != null) {
				return e;
			}

			// TODO: This is a blind lookup which is unaware of project
			// dependencies and such
			e = EMFIndexRetrieval.getEObjectOfType(context, reference.getEReferenceType(), name);
			if (e != null) {
				return e;
			}

			e = getConstantDefinition(context, name);
			if (e != null) {
				return e;
			}
		}

		return null;
	}

	private static EObject getFunctionDefinition(EObject context, String name) {
		return getNamedElementByType(context, name, ResolutePackage.Literals.FUNCTION_DEFINITION);
	}

	private static EObject getConstantDefinition(EObject context, String name) {
		return getNamedElementByType(context, name, ResolutePackage.Literals.CONSTANT_DEFINITION);
	}

	private static EObject getNamedElementByType(EObject context, String name, EClass eclass) {
		for (IEObjectDescription desc : EMFIndexRetrieval.getAllEObjectsOfTypeInWorkspace(context,
				eclass)) {
			EObject e = EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
			if (e instanceof NamedElement) {
				NamedElement ne = (NamedElement) e;
				if (name.equals(ne.getName())) {
					return ne;
				}
			}
		}

		return null;
	}

	final private static EClass UNITS_TYPE = Aadl2Package.eINSTANCE.getUnitsType();

	private static UnitLiteral getUnitLiteral(EObject context, String name) {
		// TODO: Scope literals by type, but how to do we know the type of an
		// expression?
		for (IEObjectDescription desc : EMFIndexRetrieval.getAllEObjectsOfTypeInWorkspace(context,
				UNITS_TYPE)) {
			UnitsType unitsType = (UnitsType) EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
			UnitLiteral literal = unitsType.findLiteral(name);
			if (literal != null) {
				return literal;
			}
		}

		return null;
	}
}
