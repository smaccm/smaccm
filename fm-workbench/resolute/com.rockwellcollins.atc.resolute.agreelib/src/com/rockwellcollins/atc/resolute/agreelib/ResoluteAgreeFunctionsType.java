package com.rockwellcollins.atc.resolute.agreelib;

import java.util.ArrayList;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.external.ResoluteExternalFunctionLibraryType;
import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;
import com.rockwellcollins.atc.resolute.validation.SetType;

public class ResoluteAgreeFunctionsType extends ResoluteExternalFunctionLibraryType {

	private static final String AGREE_SPEC_TYPE = "agree_spec";

	@Override
	public ResoluteType getType(String function) {

		switch (function.toLowerCase()) {

		case "hasagreeproperty":
			return BaseType.BOOL;
		case "agreeproperty":
			return new BaseType(AGREE_SPEC_TYPE);
		case "agreepropertyid":
			return BaseType.STRING;
		case "agreepropertydescription":
			return BaseType.STRING;
		case "agreeproperties":
			return new SetType(new BaseType(AGREE_SPEC_TYPE));
		case "agreeassumes":
			return new SetType(new BaseType(AGREE_SPEC_TYPE));
		case "agreeguarantees":
			return new SetType(new BaseType(AGREE_SPEC_TYPE));
		case "agreelemmas":
			return new SetType(new BaseType(AGREE_SPEC_TYPE));
		case "agreeasserts":
			return new SetType(new BaseType(AGREE_SPEC_TYPE));
		case "isassume":
			return BaseType.BOOL;
		case "isguarantee":
			return BaseType.BOOL;
		case "islemma":
			return BaseType.BOOL;
		case "isassert":
			return BaseType.BOOL;
		default:
			return BaseType.FAIL;
		}

	}

	@Override
	public List<ResoluteType> getArgTypes(String function) {

		List<ResoluteType> args = new ArrayList<>();

		switch (function.toLowerCase()) {
		case "hasagreeproperty":
			args.add(BaseType.COMPONENT); // aadl component
			args.add(BaseType.STRING); // agree spec ID
			break;
		case "agreeproperty":
			args.add(BaseType.COMPONENT); // aadl component
			args.add(BaseType.STRING); // agree spec ID
			break;
		case "agreepropertyid":
			args.add(new BaseType(AGREE_SPEC_TYPE)); // agree spec
			break;
		case "agreepropertydescription":
			args.add(new BaseType(AGREE_SPEC_TYPE)); // agree spec
			break;
		case "agreeproperties":
			args.add(BaseType.COMPONENT); // aadl component
			break;
		case "agreeassumes":
			args.add(BaseType.COMPONENT); // aadl component
			break;
		case "agreeguarantees":
			args.add(BaseType.COMPONENT); // aadl component
			break;
		case "agreelemmas":
			args.add(BaseType.COMPONENT); // aadl component
			break;
		case "agreeasserts":
			args.add(BaseType.COMPONENT); // aadl component
			break;
		case "isassume":
			args.add(new BaseType(AGREE_SPEC_TYPE)); // agree spec
			break;
		case "isguarantee":
			args.add(new BaseType(AGREE_SPEC_TYPE)); // agree spec
			break;
		case "islemma":
			args.add(new BaseType(AGREE_SPEC_TYPE)); // agree spec
			break;
		case "isassert":
			args.add(new BaseType(AGREE_SPEC_TYPE)); // agree spec
			break;
		default:
			args = null;
		}

		return args;
	}

	@Override
	public boolean isTypeDefined(String type) {
		return type.equalsIgnoreCase(AGREE_SPEC_TYPE);
	}

}
