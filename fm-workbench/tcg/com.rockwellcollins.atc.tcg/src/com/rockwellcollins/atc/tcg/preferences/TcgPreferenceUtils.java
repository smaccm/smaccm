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

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import com.rockwellcollins.atc.tcg.Activator;


public class TcgPreferenceUtils {
	
	public enum AutoSaveType { DO_NOT_SAVE, SAVE_TO_NEW_FILE, SAVE_TO_OLD_FILE };
	
	public static class AutoSaveFileInfo {
		public AutoSaveType  autoSaveType;
		public String        saveDestination;
		
		protected AutoSaveFileInfo() {
		}
		
		protected AutoSaveFileInfo(AutoSaveType autoSaveType, String saveDestination) {
			this.autoSaveType = autoSaveType;
			this.saveDestination = saveDestination;
		}
	}
	
	public static Boolean getUFC() {
        IPreferenceStore prefs = getPreferenceStore();
        return prefs.getBoolean(TcgPreferenceConstants.PREF_CB_UFC);
	}
	
	public static Boolean getPerformBoundaryValueChecks() {
        IPreferenceStore prefs = getPreferenceStore();
        return prefs.getBoolean(TcgPreferenceConstants.PREF_CB_BOUNDARY_VALUE_CHECKS);
	}

	public static Boolean getGenerateTestsForAssumptions() {
        IPreferenceStore prefs = getPreferenceStore();
        return prefs.getBoolean(TcgPreferenceConstants.PREF_CB_GEN_TESTS_FOR_ASSUMPTIONS);
	}
	
	public static Boolean getGenerateTestsForEquations() {
		IPreferenceStore prefs = getPreferenceStore();
		return prefs.getBoolean(TcgPreferenceConstants.PREF_CB_GEN_TESTS_FOR_EQUATIONS);
	}
	
	public static Boolean getUseMonolithicAnalysis() {
		IPreferenceStore prefs = getPreferenceStore();
		return prefs.getBoolean(TcgPreferenceConstants.PREF_MONOLITHIC_ANALYSIS);
	}
	
	public static AutoSaveFileInfo getAutoSaveFileInfo() {
		AutoSaveType autoSaveType = getAutoSaveType(); 
		String saveDest = getAutoSaveDestination();
		AutoSaveFileInfo asfi = new AutoSaveFileInfo(autoSaveType, saveDest);
		return asfi;
	}
	
	
	/*
	 * 
	 */

	protected static AutoSaveType getAutoSaveType() {
		AutoSaveType asVal;
		IPreferenceStore prefs = getPreferenceStore();
		Boolean doAutoSave = prefs.getBoolean(TcgPreferenceConstants.PREF_CB_AUTO_SAVE);
		if (!doAutoSave) {
			asVal = AutoSaveType.DO_NOT_SAVE;
		}
		else {
			asVal = readAutoSaveRadioButtons();
		}
		return asVal;
	}
	
	protected static String getAutoSaveDestination() {
		String saveDest = null;
		IPreferenceStore prefs = getPreferenceStore();
		Boolean doAutoSave = prefs.getBoolean(TcgPreferenceConstants.PREF_CB_AUTO_SAVE);
		if (doAutoSave) {
			switch (readAutoSaveRadioButtons()) {
			case SAVE_TO_NEW_FILE:
				saveDest = prefs.getString(TcgPreferenceConstants.PREF_AUTO_SAVE_NEW_FILENAME);
				break;
			case SAVE_TO_OLD_FILE:
				saveDest = prefs.getString(TcgPreferenceConstants.PREF_AUTO_SAVE_OLD_FILENAME);
				break;
			default:
				saveDest = "";
			}
		}
		return saveDest;
	}


	protected static AutoSaveType readAutoSaveRadioButtons() {
		AutoSaveType asVal;
		IPreferenceStore prefs = getPreferenceStore();
		// read the radio button value
		String str = prefs.getString(TcgPreferenceConstants.PREF_RB_AUTO_SAVE_TYPE);
		if (str.equals(TcgPreferenceConstants.PREF_RB_CHOICE_NEW_FILE)) {
			asVal = AutoSaveType.SAVE_TO_NEW_FILE;
		}
		else if (str.equals(TcgPreferenceConstants.PREF_RB_CHOICE_OLD_FILE)) {
			asVal = AutoSaveType.SAVE_TO_OLD_FILE;
		}
		else {
			throw new IndexOutOfBoundsException("Value of radio button " +
												TcgPreferenceConstants.PREF_RB_AUTO_SAVE_TYPE +
												" is out of range (" + str + ")");
		}
		return asVal;
	}
	
	
	/*
	 * 
	 */
    private static IPreferenceStore getPreferenceStore() {
        return Activator.getDefault().getPreferenceStore();
    }

	/**
	 * Helper to open the file chooser dialog.
	 * @param startingDirectory the directory to open the dialog on.
	 * @return File The File the user selected or <code>null</code> if they do not.
	 */
	public static File getTestCaseOpenFileDialog(Shell shell, File startingDirectory) {
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		if (startingDirectory != null) {
			dialog.setFileName(startingDirectory.getPath());
		}
		String [] filterNames = new String [] {"XML Files", "All Files (*)"};
		String [] filterExtensions = new String [] {"*.xml", "*"};
		dialog.setFilterNames (filterNames);
		dialog.setFilterExtensions (filterExtensions);

		String file = dialog.open();
		if (file != null) {
			file = file.trim();
			if (file.length() > 0) {
				return new File(file);
			}
		}
		return null;
	}

	public static File getTestCaseOpenDirectoryDialog(Shell shell, File startingDirectory) {
		FileDialog dialog = new FileDialog(shell, SWT.OPEN);
		if (startingDirectory != null) {
			dialog.setFileName(startingDirectory.getPath());
		}
		String file = dialog.open();
		if (file != null) {
			file = file.trim();
			if (file.length() > 0) {
				return new File(file);
			}
		}
		return null;
	}

}

