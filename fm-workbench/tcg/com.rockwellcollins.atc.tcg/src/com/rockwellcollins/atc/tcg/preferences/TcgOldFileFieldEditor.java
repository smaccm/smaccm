/*
Copyright (c) 2016, Rockwell Collins.
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

package com.rockwellcollins.atc.tcg.preferences;

import java.io.File;

import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

public class TcgOldFileFieldEditor extends FileFieldEditor {

	/**
	 * Indicates whether the path must be absolute;
	 * <code>false</code> by default.
	 */
	boolean enforceAbsolute = false;

	TcgOldFileFieldEditor() {
	}

	TcgOldFileFieldEditor(String name, String labelText, boolean enforceAbsolute, Composite parent) {
		init(name, labelText);
		this.enforceAbsolute = enforceAbsolute;
		setErrorMessage(null);
		setChangeButtonText(JFaceResources.getString("openBrowse"));//$NON-NLS-1$
//		setValidateStrategy(VALIDATE_ON_FOCUS_LOST);
		setValidateStrategy(VALIDATE_ON_KEY_STROKE);
		createControl(parent);
	}

	TcgOldFileFieldEditor(String name, String labelText, Composite parent) {
		this(name, labelText, false, parent);
	}


	/**
	 * Method declared on StringButtonFieldEditor.
	 * Opens the file chooser dialog and returns the selected file.
	 */
	@Override
	protected String changePressed() {
		File f = new File(getTextControl().getText());
		if (!f.exists()) {
			f = null;
		}
		File d = TcgPreferenceUtils.getTestCaseOpenFileDialog(getShell(), f);
		if (d == null) {
			return null;
		}
		return d.getAbsolutePath();
	}

	@Override
	public boolean isValid() {
		boolean valid = super.isValid();
		String path = this.getStringValue();
		
		if (path.length() == 0) {
			valid = false;
		}
		else {
			File f = new File(path);
			if (!f.isFile()) {
				valid = false;
			}
		}
		if (!getTextControl().getEnabled()) {
			valid = true;
		}
		return valid;
	}
	
	@Override
	protected boolean checkState() {
		String msg = null;

		String path = getTextControl().getText();
		if (path != null) {
			path = path.trim();
		}
		else {
			path = "";//$NON-NLS-1$
		}

		if (path.length() == 0) {
			if (!isEmptyStringAllowed()) {
				msg = JFaceResources.getString("Enter test suite filename");//$NON-NLS-1$
			}
		}
		else {
			File file = new File(path);
			if (!file.isFile()) {
				msg = JFaceResources.getString("Test suite file does not exist");//$NON-NLS-1$
			}
		}

		if (msg != null) { // error
			this.setErrorMessage(msg);
			return false;
		}

		// OK!
		clearErrorMessage();
		return true;
	}

}
