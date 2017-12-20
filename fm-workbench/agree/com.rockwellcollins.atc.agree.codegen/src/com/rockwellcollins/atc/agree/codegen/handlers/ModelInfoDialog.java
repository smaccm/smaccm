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
	private final class OutputDirChooserListner implements Listener {
		@Override
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

	private final class ImplMdlChooserListner implements Listener {
		@Override
		public void handleEvent(Event event) {
			// Get saved or entered file path
			String defaultPath = implMdlText.getText();
			// Prompt for updated output directory
			FileDialog fileChooser = new FileDialog(parentShell, SWT.OPEN);
			fileChooser.setFilterNames(new String[] { "Simulink Models (*.slx)" });
			fileChooser.setFilterExtensions(new String[] { "*.slx" });
			if (!defaultPath.equals("")) {
				fileChooser.setFileName(defaultPath);
			} else if (!outputText.getText().equals("")) {
				fileChooser.setFilterPath(outputText.getText());
			}
			String updatedFile = fileChooser.open();
			if (updatedFile != null) {
				implMdlText.setText(updatedFile);
			}
		}
	}

	private final Shell parentShell;
	private final ModelInfo savedInfo;
	private ModelInfo updatedInfo;

	private Text outputText;
	private ControlDecoration outputTextError;

	private Text implMdlText;
	private ControlDecoration implMdlTextError;
	private ControlDecoration implMdlTextInfo;

	private Text verifyMdlText;
	private ControlDecoration verifyMdlTextError;

	private Text subsystemText;
	private ControlDecoration subsysTextError;
	private ControlDecoration subsysTextInfo;

	private StringBuffer buffer = new StringBuffer();

	@Override
	public String toString() {
		return buffer.toString();
	}

	protected void write(Object o) {
		buffer.append(o);
	}

	private static final String seperator = System.getProperty("line.separator");

	private void newline() {
		write(seperator);
	}

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
		setTitle("Script Generation Settings for AADL/AGREE Export to Simulink");
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

		FieldDecoration errorFieldDecoration = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_ERROR);
		Image errorImage = errorFieldDecoration.getImage();

		FieldDecoration infoFieldDecoration = FieldDecorationRegistry.getDefault()
				.getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION);
		Image infoImage = infoFieldDecoration.getImage();

		GridLayout layout = new GridLayout(3, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		container.setLayout(layout);

		Label outputLabel = new Label(container, SWT.NONE);
		outputLabel.setText("Output Directory Path:");
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 200;
		outputLabel.setLayoutData(gridData);

		outputText = new Text(container, SWT.BORDER);
		outputText.setText(savedInfo.outputDirPath);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 350;
		gridData.horizontalIndent = 10;
		outputText.setLayoutData(gridData);
		outputText.addModifyListener(e -> validate());

		outputTextError = new ControlDecoration(outputText, SWT.TOP | SWT.LEFT);
		outputTextError.setImage(errorImage);
		outputTextError.hide();

		Button browseOutputButton = new Button(container, SWT.PUSH);
		browseOutputButton.setText("Browse");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 100;
		browseOutputButton.setLayoutData(gridData);
		browseOutputButton.addListener(SWT.Selection, new OutputDirChooserListner());

		Label implMdlLabel = new Label(container, SWT.NONE);
		implMdlLabel.setText("Implementation Model Path:");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 200;
		implMdlLabel.setLayoutData(gridData);

		implMdlText = new Text(container, SWT.BORDER);
		implMdlText.setText(savedInfo.implMdlPath);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 350;
		gridData.horizontalIndent = 10;
		implMdlText.setLayoutData(gridData);
		implMdlText.addModifyListener(e -> validate());

		implMdlTextError = new ControlDecoration(implMdlText, SWT.TOP | SWT.LEFT);
		implMdlTextError.setImage(errorImage);
		implMdlTextError.hide();

		implMdlTextInfo = new ControlDecoration(implMdlText, SWT.TOP | SWT.LEFT);
		implMdlTextInfo.setImage(infoImage);
		implMdlTextInfo.hide();

		Button browseImplButton = new Button(container, SWT.PUSH);
		browseImplButton.setText("Browse");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 100;
		browseImplButton.setLayoutData(gridData);
		browseImplButton.addListener(SWT.Selection, new ImplMdlChooserListner());

		Label verifyMdlLabel = new Label(container, SWT.NONE);
		verifyMdlLabel.setText("Verification Model Name:"); // To insert
															// observer
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 200;
		verifyMdlLabel.setLayoutData(gridData);

		verifyMdlText = new Text(container, SWT.SINGLE | SWT.BORDER);
		verifyMdlText.setText(savedInfo.verifyMdlName);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalIndent = 10;
		verifyMdlText.setLayoutData(gridData);
		verifyMdlText.addModifyListener(e -> validate());

		verifyMdlTextError = new ControlDecoration(verifyMdlText, SWT.TOP | SWT.LEFT);
		verifyMdlTextError.setImage(errorImage);
		verifyMdlTextError.hide();

		// end of row padding
		new Label(container, SWT.NULL);

		Label subsystemLabel = new Label(container, SWT.NONE);
		subsystemLabel.setText("Subsystem to Verify:");
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gridData.widthHint = 150;
		subsystemLabel.setLayoutData(gridData);

		subsystemText = new Text(container, SWT.SINGLE | SWT.BORDER);
		subsystemText.setText(savedInfo.subsystemName);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalIndent = 10;
		subsystemText.setLayoutData(gridData);
		subsystemText.addModifyListener(e -> validate());

		subsysTextError = new ControlDecoration(subsystemText, SWT.TOP | SWT.LEFT);
		subsysTextError.setImage(errorImage);
		subsysTextError.hide();

		subsysTextInfo = new ControlDecoration(subsystemText, SWT.TOP | SWT.LEFT);
		subsysTextInfo.setImage(infoImage);
		subsysTextInfo.hide();

		// end of row padding
		new Label(container, SWT.NULL);

		return container;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, ModelInfoDialogConstants.EXPORT_CONTRACTS_ID, ModelInfoDialogConstants.EXPORT_LABEL, true);
		createButton(parent, ModelInfoDialogConstants.GENERATE_SUBSYSTEM_ID, ModelInfoDialogConstants.GEN_IMPL_LABEL,
				true);
		createButton(parent, ModelInfoDialogConstants.GEN_IMPL_ID, ModelInfoDialogConstants.GEN_VERIFICATION_LABEL,
				true);
		createButton(parent, ModelInfoDialogConstants.VERIFY_SUBSYSTEM_ID, ModelInfoDialogConstants.VERIFY_LABEL, true);
	}

	@Override
	protected Control createContents(Composite parent) {
		Control result = super.createContents(parent);
		validate();
		return result;
	}

	protected void validate() {
		boolean exportButtonEnabled = false;
		boolean genImplButtonEnabled = false;
		boolean genVerificationButtonEnabled = false;
		boolean verifyButtonEnabled = false;
		boolean outputDirError = false;
		boolean implMdlError = false;
		boolean implMdlEmpty = true;
		boolean implMdlInfo = false;
		boolean newImplMdl = false;
		boolean verifyMdlError = false;
		boolean verifyMdlEmpty = true;
		boolean subsysError = false;
		boolean subsysInfo = false;
		buffer.setLength(0);

		if (outputText.getText().equals("")) {
			write("Must fill out the output directory");
			newline();
			outputDirError = true;
		} else if (!new File(outputText.getText()).exists()) {
			write("The output directory does not exist");
			newline();
			outputDirError = true;
		} else {
			exportButtonEnabled = true;
		}

		// not flagging an error if the implMdlText is empty
		String implMdlPathStr = implMdlText.getText();

		if (!implMdlPathStr.equals("")) {
			implMdlEmpty = false;

			// create the file object
			File implMdlFile = new File(implMdlPathStr);

			// if the implementation file path already exists
			// will not enable Generate Implementation button
			if (implMdlFile.exists()) {
				newImplMdl = false;
				write("Generate Implementation button enabled only when the Implementation Model does not exist");
				newline();
				implMdlInfo = true;
			}
			// else, expect user to enter a model path in an existing directory with
			// a valid model file name ends with .slx
			else {
				newImplMdl = true;

				// get the directory
				if (implMdlFile.isDirectory()) {
					// if the file path is a directory, error
					implMdlError = true;
					write("Impl Model Path a directory, not a valid file path");
					newline();
				} else {
					// get the parent directory
					String implMdlDirStr = implMdlFile.getParent();
					// if the parent directory is null, error
					if (implMdlDirStr == null) {
						implMdlError = true;
						write("Impl Model Path needs to locate in an existing directory");
						newline();
					} else {
						File implMdlDirFile = new File(implMdlDirStr);
						// if the directory does not already exists, error
						if (!implMdlDirFile.exists()) {
							implMdlError = true;
							write("Impl Model Path needs to locate in an existing directory");
							newline();
						}
						// if the directory exists, check if the file name is well formed
						else {
							// get the file name
							String implMdlName = implMdlFile.getName();
							if (!isFileNameValid(implMdlName, true)) {
								implMdlError = true;
								write("Model file name must start with a letter, end with .slx, and contain letters/numbers/underscores in between");
								newline();
							}
						}
					}
				}
			}
		}

		// enable Generate Implementation button when the model files
		// when the output directory is valid, the Implementation file field
		// is not empty, and the file doesn't exist, and no error with the file
		// name
		if (!outputDirError && !implMdlEmpty && !implMdlError && newImplMdl) {
			genImplButtonEnabled = true;
		}

		// not flagging an error if the verifyMdlText is empty
		String verifyMdlPathStr = verifyMdlText.getText();
		if (!verifyMdlPathStr.equals("")) {
			verifyMdlEmpty = false;
			if (!isFileNameValid(verifyMdlPathStr, true)) {
				write("Model name must start with a letter, end with .slx, and contain letters/numbers/underscores in between");
				newline();
				verifyMdlError = true;
			} else if (implMdlError || newImplMdl) {
				write("An Implementation Model must exist before creating the verification model");
				newline();
				verifyMdlError = true;
			}
		}

		// not flagging an error if the subsystemText is empty
		String subsystemName = subsystemText.getText();

		// if subsystemName is empty while the other fields are filled correctly
		// display a reminder message
		if (subsystemName.equals("")) {
			if (!outputDirError && !implMdlEmpty && !implMdlError && !newImplMdl && !verifyMdlEmpty
					&& !verifyMdlError) {
				write("The subsystem name must be provided to create the verification model");
				newline();
				subsysInfo = true;
			}
		} else {
			if (!isFileNameValid(subsystemName, false)) {
				write("A Subsystem name must start with a letter, and contain only letters, numbers, and underscores");
				newline();
				subsysError = true;
			} else if (!outputDirError && !implMdlEmpty && !implMdlError && !newImplMdl && !verifyMdlEmpty
					&& !verifyMdlError && !subsysError) {
				genVerificationButtonEnabled = true;
				verifyButtonEnabled = true;
			}
		}

		setExportEnabled(exportButtonEnabled);
		setGenImplEnabled(genImplButtonEnabled);
		setGenVerificationEnabled(genVerificationButtonEnabled);
		setVerifyEnabled(verifyButtonEnabled);

		if (!outputDirError && !implMdlError && !verifyMdlError && !subsysError) {
			setErrorMessage(null);
		} else {
			setErrorMessage(buffer.toString());
		}

		if (!subsysInfo && !implMdlInfo) {
			setMessage(null);
		} else {
			setMessage(buffer.toString());
		}

		if (outputDirError) {
			outputTextError.show();
		} else {
			outputTextError.hide();
		}
		if (implMdlError) {
			implMdlTextError.show();
		} else {
			implMdlTextError.hide();
		}
		if (verifyMdlError) {
			verifyMdlTextError.show();
		} else {
			verifyMdlTextError.hide();
		}
		if (subsysError) {
			subsysTextError.show();
		} else {
			subsysTextError.hide();
		}
		if (implMdlInfo) {
			implMdlTextInfo.show();
		} else {
			implMdlTextInfo.hide();
		}
		if (subsysInfo) {
			subsysTextInfo.show();
		} else {
			subsysTextInfo.hide();
		}
	}

	@Override
	protected void buttonPressed(int buttonId) {
		if (ModelInfoDialogConstants.EXPORT_CONTRACTS_ID == buttonId) {
			exportContractsPressed();
		} else if (ModelInfoDialogConstants.GENERATE_SUBSYSTEM_ID == buttonId) {
			genImplPressed();
		} else if (ModelInfoDialogConstants.GEN_IMPL_ID == buttonId) {
			genVerificationPressed();
		} else if (ModelInfoDialogConstants.VERIFY_SUBSYSTEM_ID == buttonId) {
			verifySubsysPressed();
		} else if (IDialogConstants.CANCEL_ID == buttonId) {
			cancelPressed();
		}
	}

	private void setExportEnabled(boolean enabled) {
		Button export = getButton(ModelInfoDialogConstants.EXPORT_CONTRACTS_ID);
		if (export != null) {
			export.setEnabled(enabled);
		}
	}

	private void setGenImplEnabled(boolean enabled) {
		Button generate = getButton(ModelInfoDialogConstants.GENERATE_SUBSYSTEM_ID);
		if (generate != null) {
			generate.setEnabled(enabled);
		}
	}

	private void setGenVerificationEnabled(boolean enabled) {
		Button update = getButton(ModelInfoDialogConstants.GEN_IMPL_ID);
		if (update != null) {
			update.setEnabled(enabled);
		}
	}

	private void setVerifyEnabled(boolean enabled) {
		Button verify = getButton(ModelInfoDialogConstants.VERIFY_SUBSYSTEM_ID);
		if (verify != null) {
			verify.setEnabled(enabled);
		}
	}

	protected void exportContractsPressed() {
		// for source text property saved in AADL, need to update the separator
		// in the path string
		updatedInfo = new ModelInfo(outputText.getText(), implMdlText.getText(), verifyMdlText.getText(),
				subsystemText.getText(), true, false, false, false);
		super.okPressed();
	}

	protected void genImplPressed() {
		// for source text property saved in AADL, need to update the separator
		// in the path string
		updatedInfo = new ModelInfo(outputText.getText(), implMdlText.getText(), verifyMdlText.getText(),
				subsystemText.getText(), false, true, false, false);
		super.okPressed();
	}

	protected void genVerificationPressed() {
		// for source text property saved in AADL, need to update the separator
		// in the path string
		updatedInfo = new ModelInfo(outputText.getText(), implMdlText.getText(), verifyMdlText.getText(),
				subsystemText.getText(), false, false, true, false);
		super.okPressed();
	}

	protected void verifySubsysPressed() {
		// for source text property saved in AADL, need to update the separator
		// in the path string
		updatedInfo = new ModelInfo(outputText.getText(), implMdlText.getText(), verifyMdlText.getText(),
				subsystemText.getText(), false, false, false, true);
		super.okPressed();
	}

	protected static boolean isFileNameValid(String name, boolean checkNameExtension) {
		boolean valid = true;
		String nameToCheck = name;
		// if checking name extension
		if (checkNameExtension) {
			// The name must end with .slx
			if (!name.endsWith(".slx")) {
				valid = false;
			} else {
				// extract the file name sans ".slx"
				nameToCheck = name.substring(0, name.lastIndexOf('.'));
			}
		}
		// The name must start with a letter and only contains letters, numbers,
		// and underscores
		if (!nameToCheck.matches("^[a-zA-Z][a-zA-Z0-9_]*")) {
			valid = false;
		}
		return valid;
	}
}
