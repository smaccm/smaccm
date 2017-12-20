package com.rockwellcollins.atc.agree.codegen.preferences;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.rockwellcollins.atc.agree.codegen.Activator;

public class MATLABCodeGenPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public MATLABCodeGenPreferencePage() {
		super(GRID);
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("MATLAB Function Code Generation Settings\n\n");
	}

	private static final String[][] INT_TYPES = { { PreferenceConstants.INT_INT8, PreferenceConstants.INT_INT8 },
			{ PreferenceConstants.INT_UINT8, PreferenceConstants.INT_UINT8 },
			{ PreferenceConstants.INT_INT16, PreferenceConstants.INT_INT16 },
			{ PreferenceConstants.INT_UINT16, PreferenceConstants.INT_UINT16 },
			{ PreferenceConstants.INT_INT32, PreferenceConstants.INT_INT32 },
			{ PreferenceConstants.INT_UINT32, PreferenceConstants.INT_UINT32 },
			{ PreferenceConstants.INT_INT64, PreferenceConstants.INT_INT64 },
			{ PreferenceConstants.INT_UINT64, PreferenceConstants.INT_UINT64 } };
	private ComboFieldEditor intTypeFieldEditor;
	private String selectedIntType;
	private Group dataTypeMappingGroup;

	private static final String[][] REAL_TYPES = { { PreferenceConstants.REAL_SINGLE, PreferenceConstants.REAL_SINGLE },
			{ PreferenceConstants.REAL_DOUBLE, PreferenceConstants.REAL_DOUBLE } };
	private ComboFieldEditor realTypeFieldEditor;
	private String selectedRealType;

	@Override
	public void createFieldEditors() {
		dataTypeMappingGroup = new Group(getFieldEditorParent(), SWT.NONE);
		dataTypeMappingGroup.setLayout(new GridLayout(16, false));
		dataTypeMappingGroup.setText("Data Type Mapping");

		GridLayout layout = new GridLayout(1, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		dataTypeMappingGroup.setLayout(layout);

		Composite labelComposite = new Composite(dataTypeMappingGroup, SWT.NONE);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 400;
		labelComposite.setLayoutData(gridData);

		Label outputLabel = new Label(labelComposite, SWT.NONE);
		outputLabel.setText("AADL types with size specifications will be mapped to\n"
				+ "the corresponding MATLAB types automatically.\n\n"
				+ "AADL types without size specifications will be mapped\n"
				+ "to MATLAB types according to the below settings.\n\n");
		outputLabel.pack();

		Composite dataTypeComposite = new Composite(dataTypeMappingGroup, SWT.NONE);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 400;
		dataTypeComposite.setLayoutData(gridData);

		intTypeFieldEditor = new ComboFieldEditor(PreferenceConstants.PREF_INT,
				"AADL Integer data type maps to MATLAB type", INT_TYPES, dataTypeComposite);
		addField(intTypeFieldEditor);

		realTypeFieldEditor = new ComboFieldEditor(PreferenceConstants.PREF_REAL,
				"AADL Real data type maps to MATLAB type", REAL_TYPES, dataTypeComposite);
		addField(realTypeFieldEditor);

		dataTypeMappingGroup.pack();
	}

	public String getSelectedIntType() {
		return selectedIntType;
	}

	public String getSelectedRealType() {
		return selectedRealType;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if (event.getSource().equals(intTypeFieldEditor)) {
			selectedIntType = (String) event.getNewValue();
		} else if (event.getSource().equals(realTypeFieldEditor)) {
			selectedRealType = (String) event.getNewValue();
		}
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		IPreferenceStore prefs = getPreferenceStore();
		selectedIntType = prefs.getDefaultString(PreferenceConstants.PREF_INT);
		selectedRealType = prefs.getDefaultString(PreferenceConstants.PREF_REAL);
	}

	@Override
	protected void initialize() {
		super.initialize();
		initializeStateVariables();
	}

	private void initializeStateVariables() {
		IPreferenceStore prefs = getPreferenceStore();
		selectedIntType = prefs.getString(PreferenceConstants.PREF_INT);
		selectedRealType = prefs.getString(PreferenceConstants.PREF_REAL);
	}

	@Override
	public void init(IWorkbench workbench) {
	}

}
