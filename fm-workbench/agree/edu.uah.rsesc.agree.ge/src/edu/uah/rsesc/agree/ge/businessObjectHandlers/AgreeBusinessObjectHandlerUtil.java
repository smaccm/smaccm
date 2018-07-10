package edu.uah.rsesc.agree.ge.businessObjectHandlers;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.EqStatement;

import edu.uah.rsesc.agree.ge.AgreeBusinessObjectProvider;
import edu.uah.rsesc.agree.ge.AgreeGeUtil;

class AgreeBusinessObjectHandlerUtil {
	private final static SortedSet<String> reservedWords;
	static {
		reservedWords = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		reservedWords.add("assert");
		reservedWords.add("assume");
		reservedWords.add("const");
		reservedWords.add("enum");
		reservedWords.add("eq");
		reservedWords.add("guarantee");
		reservedWords.add("lemma");
		reservedWords.add("linearization");
		reservedWords.add("node");
		reservedWords.add("property");
		reservedWords.add("type");
	}

	public static boolean shouldContributePaletteEntries(final Object diagramBo) {
		return diagramBo == null || diagramBo instanceof AadlPackage || diagramBo instanceof ComponentClassifier;
	}

	public static String validateName(final NamedElement ne, final String name) {
		// If the name hasn't changed or has only changed case
		if (name.equalsIgnoreCase(ne.getName())) {
			return null;
		}

		if (!isValidIdentifier(name)) {
			return "The specified name is not a valid identifier.";
		}

		final Set<String> nameSet = buildNameSet(AgreeGeUtil.getPackageOrClassifier(ne));
		if(nameSet.contains(name)) {
			return "The specified name conflicts with an existing model element.";
		}

		// The value is valid
		return null;
	}

	private static boolean isValidIdentifier(final String value) {
		if (reservedWords.contains(value)) {
			return false;
		}

		return value.matches("[a-zA-Z]([_]?[a-zA-Z0-9])*");
	}

	public static String buildUniqueIdentifier(final AgreeContract contract, String type) {
		final Element annexOwner = AgreeGeUtil.getPackageOrClassifier(contract);
		String baseIdentifier = "new";
		if (annexOwner instanceof ComponentImplementation) {
			baseIdentifier += "_impl";
		}

		baseIdentifier += "_" + type;

		final Set<String> existingIdentifiers = buildNameSet(annexOwner);

		// Resolve naming conflicts
		String newIdentifier = baseIdentifier;
		boolean done = false;
		int num = 1;
		do {
			if (existingIdentifiers.contains(newIdentifier)) {
				num++;
				newIdentifier = baseIdentifier + num;
			} else {
				done = true;
			}
		} while (!done);

		return newIdentifier;
	}

	/**
	 *
	 * @param element
	 * @return a set which uses a case insensitive comparison rather than using hashCode() and equals().
	 */
	private static Set<String> buildNameSet(final Element element) {
		if (!(element instanceof AadlPackage || element instanceof Classifier)) {
			throw new RuntimeException("element must be a package or a classifier");
		}

		// Build a TreeSet which contains any identifiers which are in use in the scope. A TreeSet with a case insensitive comparator is used
		// to produce a set which will not allow duplicate case insensitive names.
		final Set<String> results = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		AgreeBusinessObjectProvider.getAllSpecStatements(element).forEachOrdered(ss -> {
			if(ss instanceof NamedElement) {
				final String name = ((NamedElement) ss).getName();
				if(name != null) {
					results.add(name);
				}
			} else if (ss instanceof EqStatement) {
				final EqStatement eq = (EqStatement) ss;
				for (final Arg lhArg : eq.getLhs()) {
					if (lhArg != null && lhArg.getName() != null) {
						results.add(lhArg.getName());
					}
				}
			}
		});

		return results;
	}
}
