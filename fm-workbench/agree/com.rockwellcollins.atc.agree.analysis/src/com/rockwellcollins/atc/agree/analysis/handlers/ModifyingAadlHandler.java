package com.rockwellcollins.atc.agree.analysis.handlers;

import java.util.function.Function;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

public abstract class ModifyingAadlHandler extends AadlHandler {
	protected Function<IUnitOfWork<IStatus, XtextResource>, IStatus> getAadlOperation(XtextEditor xtextEditor) {
		return xtextEditor.getDocument()::modify;
	}
}
