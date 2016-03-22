package com.rockwellcollins.atc.agree.codegen.handlers;

import java.io.File;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ModelInfoDialog extends TitleAreaDialog {
	private final class DirChooserListner implements Listener {
		public void handleEvent(Event event) {
			// Get saved or entered output directory
			String existingDir = outputText.getText();
			// Prompt for updated output directory
			DirectoryDialog directoryChooser = new DirectoryDialog(parentShell, SWT.SAVE);
			directoryChooser.setMessage("Choose Output Directory");
			if (!existingDir.equals("")) {
				directoryChooser.setFilterPath(existingDir);
			}
			String updatedDir = directoryChooser.open();
			if (updatedDir != null) {
				outputText.setText(updatedDir);
			}
		}
	}

	private final class OriginalMdlChooserListner implements Listener {
		public void handleEvent(Event event) {
			// Get saved or entered file path
			String defaultPath = originalText.getText();
			// Prompt for updated output directory
			FileDialog fileChooser = new FileDialog(parentShell, SWT.OPEN);
			fileChooser.setFilterNames(new String[] { "Simulink Models (*.slx)" });
			fileChooser.setFilterExtensions(new String[] { "*.slx" });
			if (!defaultPath.equals("")) {
				fileChooser.setFilterPath(defaultPath);
			} else if (!outputText.getText().equals("")) {
				fileChooser.setFilterPath(outputText.getText());
			}
			String updatedFile = fileChooser.open();
			if (updatedFile != null) {
				originalText.setText(updatedFile);
			}
		}
	}

	private final Shell parentShell;
	private final ModelInfo savedInfo;
	private ModelInfo updatedInfo;

	private Text outputText;

	private Text originalText;
	private ControlDecoration originalTextError;

	private Text updatedText;
	private ControlDecoration updatedTextError;

	private Text subsystemText;

	protected ModelInfoDialog(Shell parentShell, ModelInfo savedInfo) {
		super(parentShell);
		this.parentShell = parentShell;
		this.savedInfo = savedInfo;
	}

	public ModelInfo getModelInfo() {
		return updatedInfo;
	}

	@Override
	public void create() {
		super.create();
		setTitle("Model Info for Inserting Simulink Observer");
		// setMessage("This is a TitleAreaDialog", IMessageProvider.INFORMATION);
	}

	@Override
	protected boolean isResizable() {
		return true;

	}

	@Override
	public boolean isHelpAvailable() {
		return false;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);

		FieldDecoration fieldDecoration = FieldDecorationRegistry.getDefault().getFieldDecoration(
				FieldDecorationRegistry.DEC_ERROR);
		Image errorImage = fieldDecoration.getImage();

		GridLayout layout = new GridLayout(3, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		container.setLayout(layout);

		Label outputLabel = new Label(container, SWT.NONE);
		outputLabel.setText("Output Directory Path:");
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 150;
		outputLabel.setLayoutData(gridData);

		outputText = new Text(container, SWT.BORDER);
		outputText.setText(savedInfo.outputDirPath);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 300;
		gridData.horizontalIndent = 10;
		outputText.setLayoutData(gridData);
		outputText.addModifyListener(e -> validate());

		Button browseOutputButton = new Button(container, SWT.PUSH);
		browseOutputButton.setText("Browse");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 100;
		browseOutputButton.setLayoutData(gridData);
		browseOutputButton.addListener(SWT.Selection, new DirChooserListner());

		Label originalLabel = new Label(container, SWT.NONE);
		originalLabel.setText("Original Model Name:");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 150;
		originalLabel.setLayoutData(gridData);

		originalText = new Text(container, SWT.BORDER);
		originalText.setText(savedInfo.originalModelName);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 300;
		gridData.horizontalIndent = 10;
		originalText.setLayoutData(gridData);
		originalText.addModifyListener(e -> validate());

		originalTextError = new ControlDecoration(originalText, SWT.TOP | SWT.LEFT);
		originalTextError.setImage(errorImage);
		originalTextError.hide();

		Button browseOriginalButton = new Button(container, SWT.PUSH);
		browseOriginalButton.setText("Browse");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 100;
		browseOriginalButton.setLayoutData(gridData);
		browseOriginalButton.addListener(SWT.Selection, new OriginalMdlChooserListner());

		Label updatedLabel = new Label(container, SWT.NONE);
		updatedLabel.setText("Updated Model Name:");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 150;
		updatedLabel.setLayoutData(gridData);

		updatedText = new Text(container, SWT.BORDER);
		updatedText.setText(savedInfo.updatedModelName);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.horizontalSpan = 2;
		gridData.horizontalIndent = 10;
		updatedText.setLayoutData(gridData);
		updatedText.addModifyListener(e -> validate());

		updatedTextError = new ControlDecoration(updatedText, SWT.TOP | SWT.LEFT);
		updatedTextError.setImage(errorImage);
		updatedTextError.hide();

		Label subsystemLabel = new Label(container, SWT.NONE);
		subsystemLabel.setText("Subsystem Name:");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 150;
		subsystemLabel.setLayoutData(gridData);

		subsystemText = new Text(container, SWT.BORDER);
		subsystemText.setText(savedInfo.subsystemName);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.horizontalSpan = 2;
		gridData.horizontalIndent = 10;
		subsystemText.setLayoutData(gridData);
		subsystemText.addModifyListener(e -> validate());

		return container;
	}

	@Override
	protected Control createContents(Composite parent) {
		Control result = super.createContents(parent);
		validate();
		return result;
	}

	protected void validate() {
		boolean hasErrors = false;

		if (!updatedText.getText().endsWith(".slx")) {
			setErrorMessage("Updated model name must have .slx extension");
			setOkEnabled(false);
			updatedTextError.show();
			hasErrors = true;
		} else {
			updatedTextError.hide();
		}

		if (!new File(originalText.getText()).exists()) {
			originalTextError.setDescriptionText("Original model file does not exist");
			setErrorMessage("Original model file does not exist");
			setOkEnabled(false);
			originalTextError.show();
			hasErrors = true;
		} else if (!originalText.getText().endsWith(".slx")) {
			setErrorMessage("Original model must have .xls extension");
			setOkEnabled(false);
			originalTextError.show();
			hasErrors = true;
		} else {
			originalTextError.hide();
		}

		if (!hasErrors) {
			setErrorMessage(null);
			setOkEnabled(true);
		}
	}

	private void setOkEnabled(boolean enabled) {
		Button ok = getButton(IDialogConstants.OK_ID);
		if (ok != null) {
			ok.setEnabled(enabled);
		}
	}

	@Override
	protected void okPressed() {
		// for source text property saved in AADL, need to update the separator in the path string
		updatedInfo = new ModelInfo(outputText.getText(), originalText.getText(), updatedText.getText(),
				subsystemText.getText());
		super.okPressed();
	}
}
