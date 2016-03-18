package com.rockwellcollins.atc.agree.codegen.handlers;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ModelInfoDialog extends Dialog {
	private final ModelInfo savedInfo;
	private ModelInfo updatedInfo;

	private Text originalText;
	private Text updatedText;
	private Text subsystemText;

	protected ModelInfoDialog(Shell parentShell, ModelInfo savedInfo) {
		super(parentShell);
		this.savedInfo = savedInfo;
	}

	public ModelInfo getModelInfo() {
		return updatedInfo;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Model Info for Inserting Simulink Observer");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		container.setLayout(layout);

		Label originalLabel = new Label(container, SWT.NONE);
		originalLabel.setText("Original Model Name:");

		originalText = new Text(container, SWT.BORDER);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 300;
		originalText.setLayoutData(gridData);
		originalText.setText(savedInfo.originalModelName);

		Label updatedLabel = new Label(container, SWT.NONE);
		updatedLabel.setText("Updated Model Name:");

		updatedText = new Text(container, SWT.BORDER);
		updatedText.setLayoutData(gridData);
		updatedText.setText(savedInfo.updatedModelName);

		Label subsystemLabel = new Label(container, SWT.NONE);
		subsystemLabel.setText("Subsystem Name:");

		subsystemText = new Text(container, SWT.BORDER);
		subsystemText.setLayoutData(gridData);
		subsystemText.setText(savedInfo.subsystemName);

		return container;
	}

	@Override
	protected void okPressed() {
		updatedInfo = new ModelInfo(originalText.getText(), updatedText.getText(), subsystemText.getText());
		super.okPressed();
	}
}
