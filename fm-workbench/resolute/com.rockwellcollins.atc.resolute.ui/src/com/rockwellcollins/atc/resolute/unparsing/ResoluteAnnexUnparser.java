package com.rockwellcollins.atc.resolute.unparsing;

import org.eclipse.xtext.serializer.ISerializer;
import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.annexsupport.AnnexUnparser;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.rockwellcollins.atc.resolute.serializer.ResoluteSerializer;
import com.rockwellcollins.atc.resolute.ui.internal.ResoluteActivator;

public class ResoluteAnnexUnparser implements AnnexUnparser {
	@Inject
	private ISerializer serializer;

	protected ISerializer getSerializer() {
		if (serializer == null) {
			Injector injector = ResoluteActivator.getInstance()
					.getInjector(ResoluteActivator.COM_ROCKWELLCOLLINS_ATC_RESOLUTE_RESOLUTE);
			serializer = injector.getInstance(ResoluteSerializer.class);
		}
		return serializer;
	}

	@Override
	public String unparseAnnexLibrary(AnnexLibrary library, String indent) {
		library.setName(null);
		return indent + getSerializer().serialize(library);
	}

	@Override
	public String unparseAnnexSubclause(AnnexSubclause subclause, String indent) {
		subclause.setName(null);
		return indent + getSerializer().serialize(subclause);
	}
}