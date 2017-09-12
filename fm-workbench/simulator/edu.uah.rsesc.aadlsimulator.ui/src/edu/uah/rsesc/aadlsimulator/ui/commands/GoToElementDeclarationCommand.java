/*
Copyright (c) 2015, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
package edu.uah.rsesc.aadlsimulator.ui.commands;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.osate.aadl2.NamedElement;

import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.ext.Command;
import edu.uah.rsesc.aadlsimulator.ui.ext.CommandContext;

public class GoToElementDeclarationCommand implements Command {

	@Override
	public String getLabel(final CommandContext ctx) {
		return "Go To Declaration";
	}

	@Override
	public boolean isAvailable(final CommandContext ctx) {
		// Check if the business object is a state element referencing a handled declarative object 
		if(!ctx.getSimulationEngineState().isElement(ctx.getBusinessObject())) {
			return false; 
		}
		
		final NamedElement namedElement = getHandledDeclarativeObject(ctx);
		return namedElement != null && 
				namedElement.eResource() instanceof XtextResource && 
				((XtextResource)namedElement.eResource()).getResourceServiceProvider().get(IURIEditorOpener.class) != null;
	}

	@Override
	public boolean isActivatable(final CommandContext ctx) {
		return true;
	}

	@Override
	public void activate(final CommandContext ctx) {
		final EObject decl = getHandledDeclarativeObject(ctx);
		final XtextResource res = (XtextResource)decl.eResource();
		final IURIEditorOpener editorOpener = res.getResourceServiceProvider().get(IURIEditorOpener.class);
		if(editorOpener != null) {
			editorOpener.open(EcoreUtil.getURI(decl), true);
		}			
	}
	
	/**
	 * Returns the first declarative object of the element or ancestor element which is of a handled type.
	 * @param ctx
	 * @return
	 */
	private static NamedElement getHandledDeclarativeObject(final CommandContext ctx) {
		final SimulationEngineState engineState = ctx.getSimulationEngineState();
		Object element = ctx.getBusinessObject();
		while(element != null) {
			final EObject declObj = engineState.getElementDeclarativeObject(element);
			if(declObj instanceof NamedElement) {
				return (NamedElement)declObj;
			}
			
			element = engineState.getParentElement(element);
		}
		
		return null;
	}
}
