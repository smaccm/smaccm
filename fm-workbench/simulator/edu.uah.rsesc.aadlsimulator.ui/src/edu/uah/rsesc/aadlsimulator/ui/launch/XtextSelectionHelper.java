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
package edu.uah.rsesc.aadlsimulator.ui.launch;

import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.EObjectAtOffsetHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;
import org.osate.aadl2.ComponentImplementation;

/**
 * Helper class for getting the currently selected model object from XText. Should be used with care to avoid deadlocks with ongoing document read operations.
 *
 */
class XtextSelectionHelper {
	private static EObjectAtOffsetHelper eObjectAtOffsetHelper = new EObjectAtOffsetHelper();
	
	public ComponentImplementation getCurrentComponentImplementation() {
		final IEditorPart activeEditor = getActiveEditor();
		if (activeEditor == null) {
			return null;
		}
		
		return getCurrentComponentImplementation(activeEditor);
	}
	/**
	 * 
	 * @return a component implementation based on the current context in the Xtext text editor. Returns null if one cannot be determined.
	 */
	public ComponentImplementation getCurrentComponentImplementation(final IEditorPart editorPart) {
		EObject obj = getSelection(editorPart);
		while(obj != null) {
			if(obj instanceof ComponentImplementation) {
				return (ComponentImplementation)obj;
			}
			
			obj = obj.eContainer();
		}
		
		return null;
	}
	
	private EObject getSelection(final IEditorPart editorPart) {
		final XtextEditor xtextEditor = (XtextEditor)editorPart.getAdapter(XtextEditor.class);
		if (xtextEditor == null) {
			return null;
		}
		
		return getEObjectFromSelection(xtextEditor, xtextEditor.getSelectionProvider().getSelection());		
	}
	
	private IEditorPart getActiveEditor() {
		final IWorkbench wb = PlatformUI.getWorkbench();
		if(wb == null) {
			return null;
		}
		
		final IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
		if(win == null) {
			return null;
		}
		
		final IWorkbenchPage page = win.getActivePage();
		if(page == null) {
			return null;
		}
		
		return  page.getActiveEditor();
	}
	
	public EObject getEObject(final EObjectNode node) {
		Objects.requireNonNull(node, "node must not be null");
		return node.getDocument().readOnly(new IUnitOfWork<EObject, XtextResource>() {
			@Override
			public EObject exec(final XtextResource res) throws Exception {
				return res == null ? null : node.getEObject(res);
			}					
		});
	}
	
	private static EObject getEObjectFromSelection(final XtextEditor editor, final ISelection selection) {
		assert(editor != null);
		assert(selection != null);
		
		return editor.getDocument().readOnly(
			new IUnitOfWork<EObject, XtextResource>() {
				public EObject exec(XtextResource resource)
						throws Exception {
					EObject targetElement = null;
					if (selection instanceof IStructuredSelection) {
						IStructuredSelection ss = (IStructuredSelection) selection;
						Object eon = ss.getFirstElement();
						if (eon instanceof EObjectNode) {
							targetElement = ((EObjectNode) eon)
									.getEObject(resource);
						}
					} else {
						final int offset = ((ITextSelection) selection).getOffset();
						targetElement = eObjectAtOffsetHelper.resolveContainedElementAt(resource, offset);
	
						// Check if the node for the semantic element that was retrieved by the offset helper starts after the line number of the node
						// retrieved using the offset. If it is, return the container. This prevents returning a classifier when the select is actually in
						// whitespace before the classifier.
						final IParseResult parseResult = resource.getParseResult();
						if(targetElement != null && parseResult != null) {
							final ILeafNode leaf = NodeModelUtils.findLeafNodeAtOffset(parseResult.getRootNode(), offset);
							final INode targetElementNode = NodeModelUtils.getNode(targetElement);
							if(leaf.getStartLine() < targetElementNode.getStartLine()) {
								targetElement = targetElement.eContainer();
							}
						}
						
						// If there isn't a selected element, that usually means the selection is outside of the root package. Get the first EObject in the resource
						if(targetElement == null && resource.getContents().size() > 0) {
							targetElement = resource.getContents().get(0);	
						}
					}
	
					return targetElement;
				}
			});
	}
}
