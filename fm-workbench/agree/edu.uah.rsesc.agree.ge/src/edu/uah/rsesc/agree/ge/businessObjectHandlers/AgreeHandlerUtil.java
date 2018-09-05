package edu.uah.rsesc.agree.ge.businessObjectHandlers;

import java.util.function.Function;

import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.ge.GraphicalAnnexUtil;
import org.osate.ge.operations.StepResult;

import com.google.common.base.Strings;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.AgreeLibrary;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;

public class AgreeHandlerUtil {
	private static final String AGREE_ANNEX_NAME = "agree";

	public static <E extends NamedElement> Function<E, StepResult<NamedElement>> toBusinessObjectToModify() {
		return e -> {
			final NamedElement annexElement = AgreeHandlerUtil.getAgreeAnnexElement(e);
			return StepResult.forValue(annexElement == null ? e : annexElement);
		};
	}


	public static AgreeContract getOrCreateAgreeContract(final Object element) {
		// Subclauses
		final AgreeSubclause agreeSubclause;
		if (element instanceof Classifier) {
			agreeSubclause = getOrCreateAgreeSubclause((Classifier) element);
		} else if (element instanceof AgreeSubclause) {
			agreeSubclause = (AgreeSubclause) element;
		} else {
			agreeSubclause = null;
		}

		final AgreeContract contract;
		if (agreeSubclause == null) {
			// Libraries
			final AgreeLibrary agreeLibrary;
			if (element instanceof AadlPackage) {
				agreeLibrary = getOrCreateAgreeLibrary((AadlPackage) element);
			} else if (element instanceof AgreeLibrary) {
				agreeLibrary = (AgreeLibrary) element;
			} else {
				throw new RuntimeException("Specified business object is not a classifier or a subclause: " + element);
			}

			if (agreeLibrary.getContract() == null) {
				agreeLibrary.setContract(AgreeFactory.eINSTANCE.createAgreeContract());
			}

			if (!(agreeLibrary.getContract() instanceof AgreeContract)) {
				throw new RuntimeException("Contract is not an AGREE contract");
			}

			contract = (AgreeContract) agreeLibrary.getContract();
		} else {
			if (agreeSubclause.getContract() == null) {
				agreeSubclause.setContract(AgreeFactory.eINSTANCE.createAgreeContract());
			}

			if (!(agreeSubclause.getContract() instanceof AgreeContract)) {
				throw new RuntimeException("Contract is not an AGREE contract");
			}

			contract = (AgreeContract) agreeSubclause.getContract();
		}
		return contract;
	}

	public static String toDisplayName(final String name) {
		return Strings.isNullOrEmpty(name) ? "<unnamed>" : name;
	}

	private static AgreeSubclause getOrCreateAgreeSubclause(final Classifier c) {
		return (AgreeSubclause) GraphicalAnnexUtil.getOrCreateParsedAnnexSubclause(c, AGREE_ANNEX_NAME,
				AgreePackage.eINSTANCE.getAgreeSubclause());
	}

	private static AgreeLibrary getOrCreateAgreeLibrary(final AadlPackage pkg) {
		return (AgreeLibrary) GraphicalAnnexUtil.getOrCreateParsedAnnexLibrary(pkg, AGREE_ANNEX_NAME,
				AgreePackage.eINSTANCE.getAgreeLibrary());
	}

	private static NamedElement getAgreeAnnexElement(final Element e) {
		if (e instanceof Classifier) {
			return GraphicalAnnexUtil.getFirstParsedAnnexSubclause((Classifier) e, AGREE_ANNEX_NAME,
					AgreePackage.eINSTANCE.getAgreeSubclause());
		} else if (e instanceof AadlPackage) {
			return GraphicalAnnexUtil.getFirstParsedAnnexLibrary((AadlPackage) e, AGREE_ANNEX_NAME,
					AgreePackage.eINSTANCE.getAgreeLibrary());
		} else {
			throw new RuntimeException("Unsupported element : " + e);
		}
	}
}
