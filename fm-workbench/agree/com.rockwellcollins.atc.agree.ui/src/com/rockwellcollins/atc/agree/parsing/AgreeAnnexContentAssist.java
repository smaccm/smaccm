package com.rockwellcollins.atc.agree.parsing;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.osate.annexsupport.AnnexContentAssist;

public class AgreeAnnexContentAssist implements AnnexContentAssist {

	@Override
	public void callAnnexContentAssist(EObject model, Assignment assignment,
			ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// TODO Auto-generated method stub
		System.out.println();
	}

}
