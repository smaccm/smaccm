package edu.uah.rsesc.agree.ge.ui.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.Strings;

public class TextConversionUtil {
	public static String toText(final EObject selectedElement) {
		final ICompositeNode elementNode = NodeModelUtils.findActualNodeFor(selectedElement);

		if (elementNode == null) {
			return "";
		}

		//
		// Format the node text to remove indentation from the beginning of lines.
		//

		final String rawTxt = Strings.emptyIfNull(elementNode.getText());

		// Break the text into lines and replace tabs with spaces
		String[] lines = rawTxt.replaceAll("\t", "    ").split("\n");

		// Remove starting and ending lines which contain only whitespace
		int firstNonBlankLineIndex;
		for (firstNonBlankLineIndex = 0; firstNonBlankLineIndex < lines.length; firstNonBlankLineIndex++) {
			if (!lines[firstNonBlankLineIndex].trim().isEmpty()) {
				break;
			}
		}

		int lastNonBlankLineIndex;
		for (lastNonBlankLineIndex = lines.length - 1; lastNonBlankLineIndex >= 0; lastNonBlankLineIndex--) {
			if (!lines[lastNonBlankLineIndex].trim().isEmpty()) {
				break;
			}
		}

		lines = Arrays.copyOfRange(lines, firstNonBlankLineIndex, lastNonBlankLineIndex + 1);

		// Determine Number of leading spaces.
		final int numberOfLeadingSpaces = Arrays.stream(lines).filter(line -> !line.trim().isEmpty())
				.mapToInt(TextConversionUtil::getNumberOfLeadingSpaces).min().orElse(0);

		// Build a string with the common white space removed.
		return Arrays.stream(lines).map(line -> line.substring(Math.min(line.length(), numberOfLeadingSpaces)))
				.collect(Collectors.joining("\n"));
	}

	private static int getNumberOfLeadingSpaces(final String line) {
		int i;
		for (i = 0; i < line.length(); i++) {
			if (line.charAt(i) != ' ') {
				break;
			}
		}

		return i;
	}
}
