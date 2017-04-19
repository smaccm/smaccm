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

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.rockwellcollins.atc.tcg.Activator;

public class TcgPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private BooleanFieldEditor     autoSaveBooleanFieldEditor;
	private RadioGroupFieldEditor  autoSaveDestRadioGroupFieldEditor;
	private TcgNewFileFieldEditor  newFileFieldEditor;
	private TcgOldFileFieldEditor  oldFileFieldEditor;
	private Composite              saveParent;
			
	
	public TcgPreferencePage() {
		super(GRID);
	}
	
	@Override
	public boolean isValid() {
		boolean valid = super.isValid();
		String msg = null;
		
		IPreferenceStore prefs = getPreferenceStore();
		Boolean doAutoSave = prefs.getBoolean(TcgPreferenceConstants.PREF_CB_AUTO_SAVE);
		/*
		 *  Don't care about the valid state of disabled controls
		 */
		if (!doAutoSave) {
			valid = true;
		}
		else {
			if (radioButtonIsNew()) {
				valid = newFileFieldEditor.isValid();
				if (!valid) {
					msg = newFileFieldEditor.getErrorMessage();
				}
			}
			else {
				valid = oldFileFieldEditor.isValid();
				if (!valid) {
					msg = oldFileFieldEditor.getErrorMessage();
				}
			}
		}
		setErrorMessage(msg);
		return valid;
	}
	
	@Override
	protected void performDefaults() {
		super.performDefaults();

		/* enable/disable controls based on default value of auto save checkbox */
		IPreferenceStore prefs = getPreferenceStore();
		Boolean doAutoSave = prefs.getDefaultBoolean(TcgPreferenceConstants.PREF_CB_AUTO_SAVE);
		
		/* enable/disable controls based on default value of auto save radio button group */
		String cbStr = prefs.getDefaultString(TcgPreferenceConstants.PREF_RB_AUTO_SAVE_TYPE);
		Boolean saveToNew = cbStr.equals(TcgPreferenceConstants.PREF_RB_CHOICE_NEW_FILE);

		setConditionalEnablesAutoSave(doAutoSave, saveToNew);
		setConditionalEnablesAutoSaveDestination(saveToNew);
		if (doAutoSave &&
			((saveToNew && !newFileFieldEditor.checkState()) ||
			 (!saveToNew && !oldFileFieldEditor.checkState()))) {
			/*
			 * auto save checkbox is checked  -AND- either
			 *    new file radio button is selected AND new file file chooser is invalid  -OR-
			 *    old file radio button is selected AND old file file chooser is invalid
			 */
			String msg = null;
			if (saveToNew) {
				msg = newFileFieldEditor.getErrorMessage();
			}
			else {
				msg = oldFileFieldEditor.getErrorMessage();
			}
			setValid(false);
			setErrorMessage(msg);
		}
		else {
			setValid(true);
			setErrorMessage(null);
			super.performApply();
		}
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
	
	@Override
	public void createFieldEditors() {
		
		final int PREF_MARGIN_HEIGHT = 12;
		final int PREF_MARGIN_WIDTH = 8;
		final int PREF_INNER_SPACING = 8;

		// top-level Composite, will contain three second-level elements
		Composite parent = getFieldEditorParent();
		Composite groupTop = new Composite(parent, SWT.NONE);
		GridLayout layoutTop = new GridLayout();
		layoutTop.numColumns = 1;
		layoutTop.verticalSpacing = 12;  // affects spacing between the groups
		groupTop.setLayout(layoutTop);

		// second-level Composite (metrics checkboxes)
		Group groupMetrics = new Group(groupTop, SWT.NONE);
		groupMetrics.setText("Test Metrics");
		Composite compMetrics = new Composite(groupMetrics, SWT.NONE);
		FillLayout layoutMetrics = new FillLayout(SWT.VERTICAL);
		layoutMetrics.marginHeight = PREF_MARGIN_HEIGHT;
		layoutMetrics.marginWidth = PREF_MARGIN_WIDTH;
		layoutMetrics.spacing = PREF_INNER_SPACING;
		groupMetrics.setLayout(layoutMetrics);
		
		// second-level Composite (scope checkboxes)
		Group groupScope = new Group(groupTop, SWT.NONE);
		groupScope.setText("Test Scope");
		Composite compScope = new Composite(groupScope, SWT.NONE);
		FillLayout layoutScope = new FillLayout(SWT.VERTICAL);
		layoutScope.marginHeight = PREF_MARGIN_HEIGHT;
		layoutScope.marginWidth = PREF_MARGIN_WIDTH;
		layoutScope.spacing = PREF_INNER_SPACING;
		groupScope.setLayout(layoutScope);
		
		// second-level Composite (monolithic)
		Group groupAnalysis = new Group(groupTop, SWT.NONE);
		groupAnalysis.setText("AGREE Analysis Settings");
		Composite compAnalysis = new Composite(groupAnalysis, SWT.NONE);
		FillLayout layoutAnalysis = new FillLayout(SWT.VERTICAL);
		layoutAnalysis.marginHeight = PREF_MARGIN_HEIGHT;
		layoutAnalysis.marginWidth = PREF_MARGIN_WIDTH;
		layoutAnalysis.spacing = PREF_INNER_SPACING;
		groupAnalysis.setLayout(layoutAnalysis);

		// second-level Composite (file save checkbox, radio group, file choosers)
		Group groupSave = new Group(groupTop, SWT.NONE);
		groupSave.setText("Generated Tests");
		Composite compSave = new Composite(groupSave, SWT.NONE);
		saveParent = compSave;
		GridLayout layoutSave = new GridLayout();
		layoutSave.numColumns = 1;
		layoutSave.marginHeight = PREF_MARGIN_HEIGHT;
		layoutSave.marginWidth = PREF_MARGIN_WIDTH;
		groupSave.setLayout(layoutSave);		
	
		/* metrics checkboxes */
		addField(new BooleanFieldEditor(TcgPreferenceConstants.PREF_CB_UFC,
				 "Perform Unique First Cause (UFC)", compMetrics));
		
		addField(new BooleanFieldEditor(TcgPreferenceConstants.PREF_CB_BOUNDARY_VALUE_CHECKS,
				 "Perform boundary value checks", compMetrics));

		/* scope checkboxes */
		addField(new BooleanFieldEditor(TcgPreferenceConstants.PREF_CB_GEN_TESTS_FOR_ASSUMPTIONS,
				 "Generate tests for assumptions", compScope));
		
		addField(new BooleanFieldEditor(TcgPreferenceConstants.PREF_CB_GEN_TESTS_FOR_EQUATIONS,
				 "Generate tests for equations", compScope));

		/* analysis checkbox */
		addField(new BooleanFieldEditor(TcgPreferenceConstants.PREF_MONOLITHIC_ANALYSIS,
				 "Perform monolithic analysis", compAnalysis));
		
		/* auto save checkbox */
		autoSaveBooleanFieldEditor =
				 new BooleanFieldEditor(TcgPreferenceConstants.PREF_CB_AUTO_SAVE,
										"Automatically save test suite", compSave);
		addField(autoSaveBooleanFieldEditor);

		/* auto save: destination radio buttons */
		autoSaveDestRadioGroupFieldEditor =
				 new RadioGroupFieldEditor(TcgPreferenceConstants.PREF_RB_AUTO_SAVE_TYPE, "Save test suite by", 1,
						 new String [][] { { "generating a new file",     TcgPreferenceConstants.PREF_RB_CHOICE_NEW_FILE },
		        		 				   { "overwriting existing file", TcgPreferenceConstants.PREF_RB_CHOICE_OLD_FILE }
		         		 },
		         		compSave);
		addField(autoSaveDestRadioGroupFieldEditor);

		/* auto save: destination file choosers (one for new file, one for old file -- because of entry validation differences) */
		newFileFieldEditor =
				 new TcgNewFileFieldEditor(TcgPreferenceConstants.PREF_AUTO_SAVE_NEW_FILENAME,
						 "Name for new test suite file:", compSave);
		newFileFieldEditor.setEmptyStringAllowed(false);
		addField(newFileFieldEditor);
		
		oldFileFieldEditor =
				 new TcgOldFileFieldEditor(TcgPreferenceConstants.PREF_AUTO_SAVE_OLD_FILENAME,
						 "Overwrite existing test suite file:", compSave);
		oldFileFieldEditor.setEmptyStringAllowed(false);
		addField(oldFileFieldEditor);

		/* conditionally enable auto save controls */
		IPreferenceStore prefs = getPreferenceStore();
		Boolean doAutoSave = prefs.getBoolean(TcgPreferenceConstants.PREF_CB_AUTO_SAVE);
		Boolean saveToNew = radioButtonIsNew();
		setConditionalEnablesAutoSave(doAutoSave, saveToNew);
	}
	
	/*
	 * return true if radio button group is selecting write to new file
	 */
	private Boolean radioButtonIsNew() {
		IPreferenceStore prefs = getPreferenceStore();
		String rbString = prefs.getString(TcgPreferenceConstants.PREF_RB_AUTO_SAVE_TYPE);
		Boolean b = rbString.equals(TcgPreferenceConstants.PREF_RB_CHOICE_NEW_FILE);
		return b;
	}
	
	private void setConditionalEnablesAutoSave(Boolean doAutoSave, Boolean saveToNew) {
		/*
		 * If doAutoSave is true, enable the radio button group to select the destination.
		 * Conditionally enable the file chooser controls, depending on the radio button states.
		 * Otherwise, disable the radio button group.
		 */
		autoSaveDestRadioGroupFieldEditor.setEnabled(doAutoSave, saveParent);
		if (doAutoSave) {
			setConditionalEnablesAutoSaveDestination(saveToNew);
		}
		else {
			newFileFieldEditor.setEnabled(false, saveParent);
			oldFileFieldEditor.setEnabled(false, saveParent);
		}
	}
	
	private void setConditionalEnablesAutoSaveDestination(Boolean saveToNew) {
		/*
		 * Enable/disable the directory/file choosers depending on saveToNew value.
		 */
 		newFileFieldEditor.setEnabled(saveToNew, saveParent);
		oldFileFieldEditor.setEnabled(!saveToNew, saveParent);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		boolean radioButtonEventNew = false;
		boolean radioButtonEventOld = false;
		
		/* read value of auto save checkbox */
		Boolean doAutoSave = autoSaveBooleanFieldEditor.getBooleanValue();
		
		if (event.getSource().equals(autoSaveDestRadioGroupFieldEditor)) {
			/*
			 * changed control is a radio button - read the changed value and toggle directory/file enable states
			 * need to read radio button via event.getNewValue here since it isn't yet stored in the IPreferenceStore
			 */
			boolean saveToNew = event.getNewValue() == TcgPreferenceConstants.PREF_RB_CHOICE_NEW_FILE;
			setConditionalEnablesAutoSaveDestination(saveToNew);
			/* need to also validate corresponding file chooser */
			radioButtonEventNew = saveToNew;
			radioButtonEventOld = !saveToNew;
			super.performApply();
		}
		else {
			/*
			 * no change to radio buttons, so read the stored value of the radio buttons
			 */
			boolean isNew = radioButtonIsNew();
			radioButtonEventNew = isNew;
			radioButtonEventOld = !isNew;
		}

		if (event.getSource().equals(autoSaveBooleanFieldEditor)) {
			/* changed control is the auto save checkbox */
			setConditionalEnablesAutoSave(doAutoSave, radioButtonEventNew);
			if (doAutoSave) {
				/* checkbox has been checked, so need to update file choosers */
				boolean isNew = radioButtonIsNew();
				radioButtonEventNew = isNew;
				radioButtonEventOld = !isNew;
			}
			else {
				/* checkbox has been unchecked so don't care about the file choosers */
				setValid(true);
				setErrorMessage(null);
			}
			super.performApply();
		}
		
		
		if (event.getSource().equals(newFileFieldEditor) || radioButtonEventNew) {
			/* changed control is the new file FileFieldEditor or the new file radio button */
			if (newFileFieldEditor.isValid()) {
				setValid(true);
				setErrorMessage(null);
				super.performApply();
			}
			else {
				setValid(false);
				setErrorMessage(newFileFieldEditor.getErrorMessage());
			}
		}
		
		if (event.getSource().equals(oldFileFieldEditor) || radioButtonEventOld) {
			/* changed control is the old file FileFieldEditor or the old file radio button */
			if (oldFileFieldEditor.isValid()) {
				setValid(true);
				setErrorMessage(null);
				super.performApply();
			}
			else {
				setValid(false);
				setErrorMessage(oldFileFieldEditor.getErrorMessage());
			}
		}
		//super.updateApplyButton();
		super.propertyChange(event);
	}
	
}