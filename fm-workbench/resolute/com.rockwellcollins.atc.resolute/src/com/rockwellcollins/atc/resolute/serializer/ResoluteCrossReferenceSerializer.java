package com.rockwellcollins.atc.resolute.serializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.scoping.IScope;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.xtext.aadl2.serializer.Aadl2CrossReferenceSerializer;

import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.ClaimArg;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.LetBinding;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;

public class ResoluteCrossReferenceSerializer extends Aadl2CrossReferenceSerializer {

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
		} else if (semanticObject instanceof ClaimArg) {
			ClaimArg claimArg = (ClaimArg) semanticObject;

			if (claimArg.getArg() instanceof Arg) {
				Arg arg = (Arg) claimArg.getArg();
				return arg.getName();
			} else if (claimArg.getArg() instanceof ConstantDefinition) {
				ConstantDefinition constant = (ConstantDefinition) claimArg.getArg();
				return constant.getQualifiedName();
			} else {
				LetBinding binding = (LetBinding) claimArg.getArg();
				return binding.getName();
			}

		} else {
			return super.getCrossReferenceNameFromScope(semanticObject, crossref, target, scope, errors);
		}
	}

}
