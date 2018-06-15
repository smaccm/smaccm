package edu.uah.rsesc.agree.ge;

import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.Element;

public class AgreeGeUtil {
	/**
	 * Returns the containing package or classifier for the element
	 * @param s
	 * @return
	 */
	public static Element getPackageOrClassifier(final Element e) {
		for (Element tmp = e; tmp != null; tmp = tmp.getOwner()) {
			if (tmp instanceof Classifier || tmp instanceof AadlPackage) {
				return tmp;
			}
		}

		return null;
	}
}
