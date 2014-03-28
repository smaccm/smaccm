package com.rockwellcollins.atc.aadl2sexp.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

public class Aadl2SexpHandler extends AbstractHandler {
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IEditorPart editor = HandlerUtil.getActiveEditorChecked(event);
        if (!(editor instanceof XtextEditor)) {
            return null;
        }

        XtextEditor xtextEditor = (XtextEditor) editor;
        Sexp sexp = xtextEditor.getDocument().readOnly(
                new IUnitOfWork<Sexp, XtextResource>() {
                    @Override
                    public Sexp exec(XtextResource state) throws Exception {
                        return Ast2SexpSwitch.convert(state.getContents().get(0));
                    }
                });

        System.out.println(sexp);
        return null;
    }
}
