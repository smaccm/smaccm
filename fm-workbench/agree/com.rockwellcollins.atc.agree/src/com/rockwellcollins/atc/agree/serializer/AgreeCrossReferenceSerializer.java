package com.rockwellcollins.atc.agree.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.scoping.IScope;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Namespace;
import org.osate.aadl2.PropertySet;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.xtext.aadl2.serializer.Aadl2CrossReferenceSerializer;

import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.NamedElmExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;

public class AgreeCrossReferenceSerializer extends Aadl2CrossReferenceSerializer {

	private String qualifiedName(Namespace namespace, NamedElement base) {

		String defName = base.getName();

		String prefix = "";

		Namespace defNamespace = AadlUtil.getContainingTopLevelNamespace(base);
		String pkgName = defNamespace.getElementRoot().getName();

		PropertySet propSet = AadlUtil.findImportedPropertySet(pkgName, namespace);
		AadlPackage aadlPackage = AadlUtil.findImportedPackage(pkgName, namespace);

		if (propSet != null || aadlPackage != null) {
			prefix = pkgName + "::";
		}

		return prefix + defName;
	}

	@Override
	@SuppressWarnings("restriction")
	protected String getCrossReferenceNameFromScope(EObject semanticObject, CrossReference crossref, EObject target,
			final IScope scope, org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor errors) {

		Namespace namespace = AadlUtil.getContainingTopLevelNamespace(semanticObject);

		if (semanticObject instanceof NamedElmExpr) {
			NamedElement ne = ((NamedElmExpr) semanticObject).getElm();
			return qualifiedName(namespace, ne);
		} else if (semanticObject instanceof DoubleDotRef) {
			NamedElement ne = ((DoubleDotRef) semanticObject).getElm();
			return qualifiedName(namespace, ne);
		} else if (semanticObject instanceof NodeEq) {
			NodeEq nodeEq = (NodeEq) semanticObject;
			assert (nodeEq.getLhs().size() == 1);
			return nodeEq.getLhs().get(0).getName();
		} else {
			return super.getCrossReferenceNameFromScope(semanticObject, crossref, target, scope, errors);
		}
	}
}
