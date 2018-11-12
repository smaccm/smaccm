package com.rockwellcollins.atc.resolute.agreelib;

import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.annexsupport.AnnexUtil;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.NamedSpecStatement;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.impl.AssertStatementImpl;
import com.rockwellcollins.atc.agree.agree.impl.AssumeStatementImpl;
import com.rockwellcollins.atc.agree.agree.impl.GuaranteeStatementImpl;
import com.rockwellcollins.atc.agree.agree.impl.LemmaStatementImpl;
import com.rockwellcollins.atc.resolute.analysis.execution.EvaluationContext;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteExternalFunctionLibrary;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteFailException;
import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.NamedElementValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.analysis.values.SetValue;
import com.rockwellcollins.atc.resolute.analysis.values.StringValue;

public class ResoluteAgreeFunctions extends ResoluteExternalFunctionLibrary {

	@Override
	public ResoluteValue run(EvaluationContext context, String function, List<ResoluteValue> args) {

		switch (function.toLowerCase()) {
		case "hasagreeproperty": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isNamedElement());
			assert (arg1.isString());
			return hasAgreeProperty(arg0.getNamedElement(), arg1.getString());
		}
		case "agreeproperty": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isNamedElement());
			assert (arg1.isString());
			return agreeProperty(arg0.getNamedElement(), arg1.getString());
		}
		case "agreepropertyid": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return agreePropertyId(arg0.getNamedElement());
		}
		case "agreepropertydescription": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return agreePropertyDescription(arg0.getNamedElement());
		}
		case "agreeproperties": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return agreeProperties(arg0.getNamedElement());
		}
		case "agreeassumes": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return agreeAssumes(arg0.getNamedElement());
		}
		case "agreeguarantees": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return agreeGuarantees(arg0.getNamedElement());
		}
		case "agreelemmas": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return agreeLemmas(arg0.getNamedElement());
		}
		case "agreeasserts": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return agreeAsserts(arg0.getNamedElement());
		}
		case "isassume": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return isAssume(arg0.getNamedElement());
		}
		case "isguarantee": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return isGuarantee(arg0.getNamedElement());
		}
		case "islemma": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return isLemma(arg0.getNamedElement());
		}
		case "isassert": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isNamedElement());
			return isAssert(arg0.getNamedElement());
		}
		}

		throw new ResoluteFailException("Function " + function + " not part of Agree Library.",
				context.getThisInstance().getSubcomponent());
	}

	private BoolValue hasAgreeProperty(NamedElement component, String specID) {

		ComponentInstance ci = (ComponentInstance) component;

		ComponentClassifier cc = ci.getComponentClassifier();
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(cc,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			AgreeContract contract = (AgreeContract) ((AgreeContractSubclause) annex).getContract();
			for (SpecStatement spec : contract.getSpecs()) {
				if (spec instanceof NamedSpecStatement) {
					if (specID.equalsIgnoreCase(((NamedSpecStatement) spec).getName())) {
						return new BoolValue(true);
					}
				}
			}
		}

		return new BoolValue(false);

	}

	private NamedElementValue agreeProperty(NamedElement component, String specID) {
		ComponentInstance ci = (ComponentInstance) component;
		ComponentClassifier cc = ci.getComponentClassifier();
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(cc,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			AgreeContract contract = (AgreeContract) ((AgreeContractSubclause) annex).getContract();
			for (SpecStatement spec : contract.getSpecs()) {
				if (spec instanceof NamedSpecStatement) {
					if (specID.equalsIgnoreCase(((NamedSpecStatement) spec).getName())) {
						return new NamedElementValue((NamedSpecStatement) spec);
					}
				}
			}
		}
		throw new ResoluteFailException("AGREE property " + specID + " not found on " + cc.getName(),
				ci.getSubcomponent());
	}

	private StringValue agreePropertyId(NamedElement spec) {
		NamedSpecStatement specStatement = (NamedSpecStatement) spec;
		return new StringValue(specStatement.getName());
	}

	private StringValue agreePropertyDescription(NamedElement spec) {
		NamedSpecStatement specStatement = (NamedSpecStatement) spec;
		return new StringValue(specStatement.getStr());
	}

	private SetValue agreeProperties(NamedElement component) {
		ComponentInstance ci = (ComponentInstance) component;
		ComponentClassifier cc = ci.getComponentClassifier();
		SortedSet<ResoluteValue> result = new TreeSet<ResoluteValue>();
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(cc,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			AgreeContract contract = (AgreeContract) ((AgreeContractSubclause) annex).getContract();
			for (SpecStatement spec : contract.getSpecs()) {
				if (spec instanceof NamedSpecStatement) {
					NamedElement ne = (NamedElement) spec;
					if (ne.getName() != null) {
						result.add(new NamedElementValue(ne));
					}
				}
			}
		}
		return new SetValue(Collections.unmodifiableSortedSet(result));
	}

	private SetValue agreeAssumes(NamedElement component) {
		ComponentInstance ci = (ComponentInstance) component;
		ComponentClassifier cc = ci.getComponentClassifier();
		SortedSet<ResoluteValue> result = new TreeSet<ResoluteValue>();
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(cc,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			AgreeContract contract = (AgreeContract) ((AgreeContractSubclause) annex).getContract();
			for (SpecStatement spec : contract.getSpecs()) {
				if (spec instanceof AssumeStatement) {
					NamedElement ne = (NamedElement) spec;
					if (ne.getName() != null) {
						result.add(new NamedElementValue(ne));
					}
				}
			}
		}
		return new SetValue(Collections.unmodifiableSortedSet(result));
	}

	private SetValue agreeGuarantees(NamedElement component) {
		ComponentInstance ci = (ComponentInstance) component;
		ComponentClassifier cc = ci.getComponentClassifier();
		SortedSet<ResoluteValue> result = new TreeSet<ResoluteValue>();
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(cc,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			AgreeContract contract = (AgreeContract) ((AgreeContractSubclause) annex).getContract();
			for (SpecStatement spec : contract.getSpecs()) {
				if (spec instanceof GuaranteeStatement) {
					NamedElement ne = (NamedElement) spec;
					if (ne.getName() != null) {
						result.add(new NamedElementValue(ne));
					}
				}
			}
		}
		return new SetValue(Collections.unmodifiableSortedSet(result));
	}

	private SetValue agreeLemmas(NamedElement component) {
		ComponentInstance ci = (ComponentInstance) component;
		ComponentClassifier cc = ci.getComponentClassifier();
		SortedSet<ResoluteValue> result = new TreeSet<ResoluteValue>();
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(cc,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			AgreeContract contract = (AgreeContract) ((AgreeContractSubclause) annex).getContract();
			for (SpecStatement spec : contract.getSpecs()) {
				if (spec instanceof LemmaStatement) {
					NamedElement ne = (NamedElement) spec;
					if (ne.getName() != null) {
						result.add(new NamedElementValue(ne));
					}
				}
			}
		}
		return new SetValue(Collections.unmodifiableSortedSet(result));
	}

	private SetValue agreeAsserts(NamedElement component) {
		ComponentInstance ci = (ComponentInstance) component;
		ComponentClassifier cc = ci.getComponentClassifier();
		SortedSet<ResoluteValue> result = new TreeSet<ResoluteValue>();
		for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses(cc,
				AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
			AgreeContract contract = (AgreeContract) ((AgreeContractSubclause) annex).getContract();
			for (SpecStatement spec : contract.getSpecs()) {
				if (spec instanceof AssertStatement) {
					NamedElement ne = (NamedElement) spec;
					if (ne.getName() != null) {
						result.add(new NamedElementValue(ne));
					}
				}
			}
		}
		return new SetValue(Collections.unmodifiableSortedSet(result));
	}

	private BoolValue isAssume(NamedElement specStatement) {
		NamedSpecStatement spec = (NamedSpecStatement) specStatement;
		return new BoolValue(spec instanceof AssumeStatementImpl);
	}

	private BoolValue isGuarantee(NamedElement specStatement) {
		NamedSpecStatement spec = (NamedSpecStatement) specStatement;
		return new BoolValue(spec instanceof GuaranteeStatementImpl);
	}

	private BoolValue isLemma(NamedElement specStatement) {
		NamedSpecStatement spec = (NamedSpecStatement) specStatement;
		return new BoolValue(spec instanceof LemmaStatementImpl);
	}

	private BoolValue isAssert(NamedElement specStatement) {
		NamedSpecStatement spec = (NamedSpecStatement) specStatement;
		return new BoolValue(spec instanceof AssertStatementImpl);
	}

}
