package com.rockwellcollins.atc.resolute.stringlib;

import java.util.ArrayList;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.external.ResoluteExternalFunctionLibraryType;
import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ListType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class ResoluteStringFunctionsType extends ResoluteExternalFunctionLibraryType {

	@Override
	public ResoluteType getType(String function) {

		switch (function.toLowerCase()) {

		case "concat":
			return BaseType.STRING;
		case "contains":
			return BaseType.BOOL;
		case "endswith":
			return BaseType.BOOL;
		case "hashcode":
			return BaseType.INT;
		case "indexof":
			return BaseType.INT;
		case "lastindexof":
			return BaseType.INT;
		case "stringlength":
			return BaseType.INT;
		case "matches":
			return BaseType.BOOL;
		case "replace":
			return BaseType.STRING;
		case "split":
			return new ListType(BaseType.STRING);
		case "startswith":
			return BaseType.BOOL;
		case "substring":
			return BaseType.STRING;
		case "tolowercase":
			return BaseType.STRING;
		case "touppercase":
			return BaseType.STRING;
		case "trim":
			return BaseType.STRING;
		default:
			return BaseType.FAIL;
		}

	}

	@Override
	public List<ResoluteType> getArgTypes(String function) {

		List<ResoluteType> args = new ArrayList<>();

		switch (function.toLowerCase()) {

		case "concat": {
			args.add(BaseType.STRING); // first string
			args.add(BaseType.STRING); // second string
			break;
		}

		case "contains": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.STRING); // substring
			break;
		}

		case "endswith": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.STRING); // suffix
			break;
		}

		case "hashcode": {
			args.add(BaseType.STRING);
			break;
		}

		case "indexof": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.STRING); // substring
			break;
		}

		case "lastindexof": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.STRING); // substring
			break;
		}

		case "stringlength": {
			args.add(BaseType.STRING); // string
			break;
		}

		case "matches": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.STRING); // regex
			break;
		}

		case "replace": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.STRING); // old substring
			args.add(BaseType.STRING); // new substring
			break;
		}

		case "split": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.STRING); // regex
			break;
		}

		case "startswith": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.STRING); // prefix
			break;
		}

		case "substring": {
			args.add(BaseType.STRING); // string
			args.add(BaseType.INT); // start index
			args.add(BaseType.INT); // end index
			break;
		}

		case "tolowercase": {
			args.add(BaseType.STRING); // string
			break;
		}

		case "touppercase": {
			args.add(BaseType.STRING); // string
			break;
		}
		case "trim": {
			args.add(BaseType.STRING); // string
			break;
		}
		default:
			args = null;
		}

		return args;
	}

}
