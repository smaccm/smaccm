package edu.uah.rsesc.agree.ge.businessObjectHandlers;

import javax.inject.Named;

import org.osate.ge.di.IsApplicable;
import org.osate.ge.di.Names;

import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;

public class AgreeContractLibraryHandler {
	@IsApplicable
	public boolean isApplicable(final @Named(Names.BUSINESS_OBJECT) AgreeContractLibrary lib) {
		return true;
	}
}
