package com.rockwellcollins.atc.resolute.parsing;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.nodemodel.INode;
import org.osate.annexsupport.AnnexLinkingService;

import com.google.inject.Inject;
import com.rockwellcollins.atc.resolute.linking.ResoluteLinkingService;

// Based on EMV2AnnexLinkingService from Error Model annex
public class ResoluteAnnexLinkingService implements AnnexLinkingService {
    @Inject
    public ResoluteLinkingService linkingService;

    @Inject
    public IQualifiedNameProvider nameProvider;

    public List<EObject> resolveAnnexReference(String annexName, EObject context,
            EReference reference, INode node) {
        return linkingService.getLinkedObjects(context, reference, node);
    }

    public QualifiedName getFullyQualifiedName(final EObject obj) {
        return nameProvider.getFullyQualifiedName(obj);
    }
}
