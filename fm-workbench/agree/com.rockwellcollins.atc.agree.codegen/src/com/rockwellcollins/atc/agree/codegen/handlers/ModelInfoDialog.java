package com.rockwellcollins.atc.agree.codegen.handlers;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ModelInfoDialog extends Dialog {
	private final class DirChooserListner implements Listener {
		public void handleEvent(Event event) {
				// Get saved or entered output directory
			    String existingDir = outputText.getText();
				// Prompt for updated output directory
				Display display = parentShell.getDisplay();
				String result[] = new String[1];
				display.syncExec(() -> {
					DirectoryDialog directoryChooser = new DirectoryDialog(parentShell, SWT.SAVE);
					directoryChooser.setMessage("Choose Output Directory");
					if (!existingDir.equals("")) {
						directoryChooser.setFilterPath(existingDir);
					}	
					result[0] = directoryChooser.open();
				});
				String updatedDir = result[0];
				outputText.setText(updatedDir);
			}
	}
	
	private final class OriginalMdlChooserListner implements Listener {
		public void handleEvent(Event event) {
				// Get saved or entered file path
			    String defaultPath = originalText.getText();
				// Prompt for updated output directory
				Display display = parentShell.getDisplay();
				String result[] = new String[1];
				display.syncExec(() -> {
					FileDialog fileChooser = new FileDialog(parentShell, SWT.SAVE);
					fileChooser.setFilterNames(new String[] { "*.slx" });
					fileChooser.setFilterExtensions(new String[] { "*.slx"});
					if (!defaultPath.equals("")) {
						fileChooser.setFilterPath(defaultPath);
					}	
					result[0] = fileChooser.open();
				});
				String updatedFile = result[0];
				originalText.setText(updatedFile);
			}
	}

	private final ModelInfo savedInfo;
	private ModelInfo updatedInfo;
	private Shell parentShell;

	private Text outputText;
	private Text originalText;
	private Text updatedText;
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
		outputText.setLayoutData(gridData);
		
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
		originalText.setLayoutData(gridData);
		
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
		updatedText.setLayoutData(gridData);

		Label subsystemLabel = new Label(container, SWT.NONE);
		subsystemLabel.setText("Subsystem Name:");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 150;
		subsystemLabel.setLayoutData(gridData);

		subsystemText = new Text(container, SWT.BORDER);
		subsystemText.setText(savedInfo.subsystemName);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.horizontalSpan = 2;
		subsystemText.setLayoutData(gridData);

		return container;
	}

	@Override
	protected void okPressed() {
		//for source text property saved in AADL, need to update the separator in the path string
		updatedInfo = new ModelInfo(outputText.getText(), originalText.getText(), updatedText.getText(), subsystemText.getText());
		super.okPressed();
	}
}
