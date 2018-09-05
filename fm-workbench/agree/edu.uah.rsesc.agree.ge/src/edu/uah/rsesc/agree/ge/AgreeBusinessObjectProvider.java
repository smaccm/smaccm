package edu.uah.rsesc.agree.ge;

import java.util.stream.Stream;

import javax.inject.Named;

import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.Subcomponent;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ge.BusinessObjectContext;
import org.osate.ge.di.Activate;
import org.osate.ge.di.Names;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeLibrary;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AgreeSubclause;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.LinearizationDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;

public class AgreeBusinessObjectProvider {
	@Activate
	public Stream<?> getBusinessObjects(final @Named(Names.BUSINESS_OBJECT_CONTEXT) BusinessObjectContext boc) {
		final Object bo = boc.getBusinessObject();

		if (bo instanceof AadlPackage) {
			return getAllSpecStatements((AadlPackage) bo).filter(AgreeBusinessObjectProvider::isSupported);
		} else if (bo instanceof Classifier) {
			return getAllSpecStatements((Classifier) bo).filter(AgreeBusinessObjectProvider::isSupported);
		} else if (bo instanceof Subcomponent) {
			final ComponentClassifier cc = ((Subcomponent) bo).getAllClassifier();
			if (cc != null) {
				return getAllSpecStatements(cc).filter(AgreeBusinessObjectProvider::isSupported);
			}
		}

		return null;
	}

	private static boolean isSupported(final SpecStatement bo) {
		return bo instanceof AssertStatement || bo instanceof AssumeStatement || bo instanceof ConstStatement
				|| bo instanceof EnumStatement || bo instanceof EqStatement || bo instanceof GuaranteeStatement
				|| bo instanceof LemmaStatement || bo instanceof LinearizationDefExpr || bo instanceof NodeDefExpr
				|| bo instanceof PropertyStatement || bo instanceof RecordDefExpr;
	}

	public static Stream<SpecStatement> getAllSpecStatements(final Element e) {
		if (e instanceof AadlPackage) {
			return getAllSpecStatements((AadlPackage) e);
		} else if (e instanceof Classifier) {
			return getAllSpecStatements((Classifier) e);
		}

		return Stream.empty();
	}

	public static Stream<SpecStatement> getAllSpecStatements(final AadlPackage pkg) {
		return AnnexUtil.getAllActualAnnexLibraries(pkg, AgreePackage.eINSTANCE.getAgreeLibrary()).stream()
				.map(l -> (AgreeLibrary) l).filter(agreeLib -> agreeLib.getContract() instanceof AgreeContract)
				.flatMap(agreeLib -> ((AgreeContract) agreeLib.getContract()).getSpecs().stream());
	}

	public static Stream<SpecStatement> getAllSpecStatements(final Classifier classifier) {
		// Work around for exception when an implementation does not have a type.
		if (classifier instanceof ComponentImplementation && ((ComponentImplementation) classifier).getType() == null) {
			return Stream.empty();
		}

		Stream<SpecStatement> results = getSpecStatements(classifier);
		if (classifier instanceof ComponentImplementation) {
			final ComponentType ct = ((ComponentImplementation) classifier).getType();
			results = Stream.concat(getSpecStatements(ct), results);
		}

		return results;
	}

	/**
	 * Returns the spec statements for the specified classifier. Does not return spec statements for inherited classifiers.
	 * @param classifier
	 * @return
	 */
	private static Stream<SpecStatement> getSpecStatements(final Classifier classifier) {
		if (classifier == null) {
			return Stream.empty();
		}

		return classifier.getOwnedAnnexSubclauses().stream().map(sc -> AnnexUtil.getParsedAnnex(sc))
				.filter(sc -> sc instanceof AgreeSubclause).map(AgreeSubclause.class::cast)
				.map(sc -> sc.getContract()).filter(c -> c instanceof AgreeContract).map(AgreeContract.class::cast)
				.flatMap(c -> c.getSpecs().stream());
	}
}
