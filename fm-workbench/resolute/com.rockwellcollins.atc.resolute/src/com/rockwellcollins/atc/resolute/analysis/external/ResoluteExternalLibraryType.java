package com.rockwellcollins.atc.resolute.analysis.external;

import java.util.List;

import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public interface ResoluteExternalLibraryType {

	// This method returns the return type for the specified library function
	public ResoluteType getType(String function);

	// This method returns a list of arg types for the specified library function
	public List<ResoluteType> getArgTypes(String function);
}
