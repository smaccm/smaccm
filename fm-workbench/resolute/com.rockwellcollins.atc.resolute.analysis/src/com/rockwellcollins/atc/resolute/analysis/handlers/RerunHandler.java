package com.rockwellcollins.atc.resolute.analysis.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osate.aadl2.Element;

public class RerunHandler extends AbstractHandler {
    private final URI uri;
    private final ResoluteHandler resoluteHandler;

    public RerunHandler(Element root, ResoluteHandler resoluteHandler) {
        this.uri = EcoreUtil.getURI(root);
        this.resoluteHandler = resoluteHandler;
    }

    @Override
    public Object execute(ExecutionEvent event) {
        // Manually clear the proofs in case the uri is stale and executeURI does nothing
        resoluteHandler.clearProofs();
        return resoluteHandler.executeURI(uri);
    }
}
