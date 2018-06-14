package edu.uah.rsesc.agree.ge.filtering;

import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.Subcomponent;
import org.osate.ge.ContentFilter;

import com.rockwellcollins.atc.agree.agree.SpecStatement;

public class AgreeFilter implements ContentFilter {
	public static final String ID = "agree.all";

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public String getName() {
		return "AGREE";
	}

	@Override
	public boolean isApplicable(final Object bo) {
		return bo instanceof AadlPackage || bo instanceof ComponentClassifier || bo instanceof Subcomponent;
	}

	@Override
	public boolean test(Object bo) {
		return bo instanceof SpecStatement;
	}

}
