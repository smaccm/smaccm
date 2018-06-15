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
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.AgreePackage;

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
		final AgreeContractSubclause agreeSubclause;
		if (element instanceof Classifier) {
			agreeSubclause = getOrCreateAgreeSubclause((Classifier) element);
		} else if (element instanceof AgreeContractSubclause) {
			agreeSubclause = (AgreeContractSubclause) element;
		} else {
			agreeSubclause = null;
		}

		final AgreeContract contract;
		if (agreeSubclause == null) {
			// Libraries
			final AgreeContractLibrary agreeLibrary;
			if (element instanceof AadlPackage) {
				agreeLibrary = getOrCreateAgreeLibrary((AadlPackage) element);
			} else if (element instanceof AgreeContractLibrary) {
				agreeLibrary = (AgreeContractLibrary) element;
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

	private static AgreeContractSubclause getOrCreateAgreeSubclause(final Classifier c) {
		return (AgreeContractSubclause) GraphicalAnnexUtil.getOrCreateParsedAnnexSubclause(c, AGREE_ANNEX_NAME,
				AgreePackage.eINSTANCE.getAgreeContractSubclause());
	}

	private static AgreeContractLibrary getOrCreateAgreeLibrary(final AadlPackage pkg) {
		return (AgreeContractLibrary) GraphicalAnnexUtil.getOrCreateParsedAnnexLibrary(pkg, AGREE_ANNEX_NAME,
				AgreePackage.eINSTANCE.getAgreeContractLibrary());
	}

	private static NamedElement getAgreeAnnexElement(final Element e) {
		if (e instanceof Classifier) {
			return GraphicalAnnexUtil.getFirstParsedAnnexSubclause((Classifier) e, AGREE_ANNEX_NAME,
					AgreePackage.eINSTANCE.getAgreeContractSubclause());
		} else if (e instanceof AadlPackage) {
			return GraphicalAnnexUtil.getFirstParsedAnnexLibrary((AadlPackage) e, AGREE_ANNEX_NAME,
					AgreePackage.eINSTANCE.getAgreeContractLibrary());
		} else {
			throw new RuntimeException("Unsupported element : " + e);
		}
	}
}
