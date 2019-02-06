package com.rockwellcollins.atc.resolute.analysis.external;

import java.util.List;

import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class ResoluteExternalFunctionLibraryType {

	// This method returns the return type for the specified library function
	public ResoluteType getType(String function) {
		return BaseType.FAIL;
	}

	// This method returns a list of arg types for the specified library function
	public List<ResoluteType> getArgTypes(String function) {
		return null;
	}

	// This method returns whether the class returns a user-defined type with the specified name
	public boolean isTypeDefined(String type) {
		return false;
	}

}
