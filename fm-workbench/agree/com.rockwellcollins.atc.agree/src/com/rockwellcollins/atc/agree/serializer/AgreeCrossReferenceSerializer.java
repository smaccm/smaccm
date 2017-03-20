package com.rockwellcollins.atc.agree.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.scoping.IScope;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.xtext.aadl2.serializer.Aadl2CrossReferenceSerializer;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.RecordExpr;

public class AgreeCrossReferenceSerializer extends Aadl2CrossReferenceSerializer {

	@Override
	@SuppressWarnings("restriction")
	protected String getCrossReferenceNameFromScope(EObject semanticObject, CrossReference crossref, EObject target,
			final IScope scope, org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor errors) {
		if (semanticObject instanceof NestedDotID) {
			NestedDotID dotID = (NestedDotID) semanticObject;
			NamedElement base = dotID.getBase();
			if (base instanceof Subcomponent) {
				return base.getName();
			} else if (scope.getElements(target).iterator().hasNext()) {
				return base.getName();
			} else {
				return base.getQualifiedName();
			}
//		} else if (semanticObject instanceof Arg) {
//			return 
		} else if (semanticObject instanceof NodeEq) {
			NodeEq nodeEq = (NodeEq) semanticObject;
			assert(nodeEq.getLhs().size() == 1);
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
