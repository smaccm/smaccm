package com.rockwellcollins.atc.agree.analysis.views;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.ui.console.IPatternMatchListener;
import org.eclipse.ui.console.PatternMatchEvent;
import org.eclipse.ui.console.TextConsole;

public class AgreePatternListener implements IPatternMatchListener {
	private TextConsole console;
	private final Map<String, EObject> refMap;

	public AgreePatternListener(Map<String, EObject> refMap) {
		this.refMap = refMap;
	}

	@Override
	public void connect(TextConsole console) {
		this.console = console;
	}

	@Override
	public void matchFound(PatternMatchEvent event) {
		// remove the brackets
		int offset = event.getOffset() + 1;
		int length = event.getLength() - 2;
		try {
			String name = console.getDocument().get(offset, length);
			EObject ref = findBestReference(name);

			if (ref != null) {
				IHyperlink hyperlink = new AgreeConsoleHyperLink(ref);
				console.addHyperlink(hyperlink, offset, length);
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private EObject findBestReference(String refStr) {

		EObject ref = null;
		while (ref == null && refStr != null && !refStr.equals("")) {
			ref = refMap.get(refStr);
			int index = refStr.lastIndexOf(".");
			if (index == -1) {
				break;
			}
			refStr = refStr.substring(0, index);
		}

		return ref;
	}

	@Override
	public String getPattern() {
		return "\\{.*\\}";
	}

	@Override
	public void disconnect() {
	}

	@Override
	public int getCompilerFlags() {
		return 0;
	}

	@Override
	public String getLineQualifier() {
		return null;
	}
}
