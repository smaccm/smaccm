package com.rockwellcollins.atc.agree.analysis.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osate.aadl2.Element;

public class RerunHandler extends AbstractHandler {
	private final URI uri;
	private final VerifyHandler verifyHandler;

	public RerunHandler(Element root, VerifyHandler verifyHandler) {
		uri = EcoreUtil.getURI(root);
		this.verifyHandler = verifyHandler;
	}

	@Override
	public Object execute(ExecutionEvent event) {
		// Manually clear the view in case the uri is stale and executeURI does
		// nothing
		verifyHandler.clearView();
		verifyHandler.setCalledFromRerun();
		return verifyHandler.executeURI(uri);
	}
}
