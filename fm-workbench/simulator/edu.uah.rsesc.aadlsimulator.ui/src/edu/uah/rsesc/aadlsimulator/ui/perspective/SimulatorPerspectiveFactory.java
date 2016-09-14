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
package edu.uah.rsesc.aadlsimulator.ui.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import edu.uah.rsesc.aadlsimulator.ui.UIConstants;

public class SimulatorPerspectiveFactory implements IPerspectiveFactory {
	private static final String AADL_NAVIGATOR_VIEW_ID = "org.osate.ui.navigator.AadlNavigator";
	private static final String CONSOLE_VIEW_ID = "org.eclipse.ui.console.ConsoleView";
	private static final String AADL_PERSPECTIVE_ID = "org.osate.ui.perspective.AadlPerspective";
	
	@Override
	public void createInitialLayout(final IPageLayout layout) {
		final String editorArea = layout.getEditorArea();

		// Left
		layout.addView(AADL_NAVIGATOR_VIEW_ID, IPageLayout.LEFT, .20f, editorArea);
		layout.addView(UIConstants.STATUS_VIEW_ID, IPageLayout.BOTTOM, .5f, AADL_NAVIGATOR_VIEW_ID);
		
		// Right
		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.RIGHT, .6f, editorArea);
		layout.addView(UIConstants.PROPERTIES_VIEW_ID, IPageLayout.BOTTOM, .5f, IPageLayout.ID_OUTLINE);
		
		// Bottom
		final IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, .75f, editorArea);
		bottom.addView(UIConstants.VARIABLES_VIEW_ID);
		bottom.addView(CONSOLE_VIEW_ID);
		
		// Add fast views 
		layout.addShowViewShortcut(UIConstants.STATUS_VIEW_ID);
		layout.addShowViewShortcut(UIConstants.VARIABLES_VIEW_ID);
		layout.addShowViewShortcut(UIConstants.PROPERTIES_VIEW_ID);
		layout.addShowViewShortcut(AADL_NAVIGATOR_VIEW_ID);
		layout.addShowViewShortcut(CONSOLE_VIEW_ID);
		
		// Add perspective shortcuts
		layout.addPerspectiveShortcut(AADL_PERSPECTIVE_ID);
	}

}
