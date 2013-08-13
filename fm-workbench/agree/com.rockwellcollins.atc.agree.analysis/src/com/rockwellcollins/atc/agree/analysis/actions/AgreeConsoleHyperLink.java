package com.rockwellcollins.atc.agree.analysis.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.console.IHyperlink;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;

import com.google.inject.Injector;
import com.rockwellcollins.atc.agree.ui.internal.AgreeActivator;

public class AgreeConsoleHyperLink implements IHyperlink{

	private static GlobalURIEditorOpener uriEd;
	private EObject ref;
	
	public AgreeConsoleHyperLink(EObject o){
		ref = o;
	}
	
	@Override
	public void linkEntered() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void linkExited() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void linkActivated() {
		uriEd.open(EcoreUtil.getURI(ref), true);
		
	}

	public static void setGlobalURIEditorOpener(GlobalURIEditorOpener g){
		uriEd = g;
	}
	
}
