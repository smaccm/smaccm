package com.rockwellcollins.atc.agree.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.scoping.IScope;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Namespace;
import org.osate.aadl2.PropertySet;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.xtext.aadl2.serializer.Aadl2CrossReferenceSerializer;

import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.RecordExpr;

public class AgreeCrossReferenceSerializer extends Aadl2CrossReferenceSerializer {

	@Override
	@SuppressWarnings("restriction")
	protected String getCrossReferenceNameFromScope(EObject semanticObject, CrossReference crossref, EObject target,
			final IScope scope, org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor errors) {

		Namespace namespace = AadlUtil.getContainingTopLevelNamespace(semanticObject);

		if (semanticObject instanceof NestedDotID) {
			NestedDotID dotID = (NestedDotID) semanticObject;
			NamedElement base = dotID.getBase();


			if (base instanceof Subcomponent) {
				return base.getName();

			} else if (scope.getElements(target).iterator().hasNext()) {

				String defName = base.getName();

				Namespace defNamespace = AadlUtil.getContainingTopLevelNamespace(base);
				String prefix = "";
				String pkgName = defNamespace.getElementRoot().getName();

				PropertySet propSet = AadlUtil.findImportedPropertySet(pkgName, namespace);
				AadlPackage aadlPackage = AadlUtil.findImportedPackage(pkgName, namespace);
				if (propSet != null || aadlPackage != null) {
					prefix = pkgName + "::";
				}

				return prefix + defName;

			} else {
				return base.getQualifiedName();
			}
//		} else if (semanticObject instanceof Arg) {
//			return
		} else if (semanticObject instanceof NodeEq) {
			NodeEq nodeEq = (NodeEq) semanticObject;
			assert (nodeEq.getLhs().size() == 1);
			return nodeEq.getLhs().get(0).getName();
		} else if (semanticObject instanceof RecordExpr) {
			// RecordExpr recordExpr = (RecordExpr) semanticObject;
			NamedElement namedElement = (NamedElement) target;
			return namedElement.getName();
		} else {
			return super.getCrossReferenceNameFromScope(semanticObject, crossref, target, scope, errors);
		}
	}
}
