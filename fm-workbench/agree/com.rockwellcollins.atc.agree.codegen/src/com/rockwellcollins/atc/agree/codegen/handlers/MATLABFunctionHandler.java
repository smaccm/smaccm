package com.rockwellcollins.atc.agree.codegen.handlers;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

import jkind.lustre.Node;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.ListValue;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ui.dialogs.Dialog;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.handlers.ModifyingAadlHandler;
import com.rockwellcollins.atc.agree.analysis.translation.AgreeNodeToLustreContract;
import com.rockwellcollins.atc.agree.codegen.Activator;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPrimaryFunction;
import com.rockwellcollins.atc.agree.codegen.translation.LustreToMATLABTranslator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class MATLABFunctionHandler extends ModifyingAadlHandler {
	/**
	 * The constructor.
	 */
	public MATLABFunctionHandler() {
	}

	protected String getNestedMessages(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		while (e != null) {
			if (e.getMessage() != null && !e.getMessage().isEmpty()) {
				pw.println(e.getMessage());
			}
			e = e.getCause();
		}
		pw.close();
		return sw.toString();
	}

	protected IStatus runJob(Element root, IProgressMonitor monitor) {
		Classifier classifier = getOutermostClassifier(root);
		if (!(classifier instanceof ComponentType)) {
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Must select an AADL Component Type");
		}

		ComponentType ct = (ComponentType) classifier;
		ComponentImplementation ci = null;
		try {
			ci = AgreeUtils.compImplFromType(ct);

			SystemInstance si = null;
			try {
				si = InstantiateModel.buildInstanceModelFile(ci);
			} catch (Exception e) {
				Dialog.showError("Model Instantiate", "Error while re-instantiating the model: " + e.getMessage());
				return Status.CANCEL_STATUS;
			}

			// SystemType sysType = si.getSystemImplementation().getType();
			ComponentType sysType = AgreeUtils.getInstanceType(si);
			EList<AnnexSubclause> annexSubClauses = AnnexUtil.getAllAnnexSubclauses(sysType,
					AgreePackage.eINSTANCE.getAgreeContractSubclause());

			if (annexSubClauses.size() == 0) {
				throw new AgreeException("There is not an AGREE annex in the '" + sysType.getName() + "' system type.");
			}

			// Get Agree program
			AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(si);

			// Translate Agree Node to Lustre Node with pre-statement flatten, helper nodes inlined,
			// and variable declarations sorted so they are declared before use
			Node lustreNode = AgreeNodeToLustreContract.translate(agreeProgram.topNode, agreeProgram);

			// Translate Lustre Node to MATLAB Function AST
			MATLABPrimaryFunction matlabFunction = LustreToMATLABTranslator.translate(lustreNode, agreeProgram);

			String dirStr = getOutputDirectory();
			if (dirStr == null || dirStr.isEmpty()) {
				return Status.CANCEL_STATUS;
			}

			String matlabFuncScriptName = matlabFunction.name + ".m";

			Path matlabFuncScriptPath = Paths.get(dirStr, matlabFuncScriptName);

			// Write MATLAB function code into the specified file in the selected output folder
			writeToFile(matlabFuncScriptPath, matlabFunction.toString());

			ModelInfo info = getModelInfo(ct);
			if (info == null) {
				return Status.CANCEL_STATUS;
			}

			// Create Simulink Design Verifier (SLDV) invocation file into the output folder
			SLDVInvocationScriptCreator sldvInvokeScript = new SLDVInvocationScriptCreator(info.originalModelName,
					info.updatedModelName, info.subsystemName, matlabFuncScriptName);

			String sldvInvokeScriptName = matlabFunction.name + "_sldv.m";
			Path sldvInvokeScriptPath = Paths.get(dirStr, sldvInvokeScriptName);
			writeToFile(sldvInvokeScriptPath, sldvInvokeScript.toString());

			// create batch file into the output folder
			MATLABInvocationScriptCreator matlabInvokeScript = new MATLABInvocationScriptCreator(dirStr,
					sldvInvokeScriptName);
			String batchFileName = matlabFunction.name + "_cmd_line.bat";

			Path batchPath = Paths.get(dirStr, batchFileName);
			String batchPathStr = dirStr + "\\" + batchFileName;
			writeToFile(batchPath, matlabInvokeScript.toString());

			// invoke the batch file
			Runtime.getRuntime().exec(batchPathStr);

			return Status.OK_STATUS;
		} catch (Throwable e) {
			String messages = getNestedMessages(e);
			e.printStackTrace();
			return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
		} finally {
			if (ci != null) {
				ci.eResource().getContents().remove(ci);
			}
		}
	}

	private static final String DEFAULT_PATH = "com.rockwellcollins.atc.agree.codegen.default_path";

	/**
	 * Get the directory to create the output file
	 * File name will be the same as the function name
	 */
	protected String getOutputDirectory() {
		// Get saved output directory
		Preferences pref = Preferences.userRoot();
		String savedDir = pref.get(DEFAULT_PATH, "");

		// Prompt for updated output directory
		Shell shell = getWindow().getShell();
		Display display = shell.getDisplay();
		String result[] = new String[1];
		display.syncExec(() -> {
			DirectoryDialog directoryChooser = new DirectoryDialog(shell, SWT.SAVE);
			directoryChooser.setMessage("Choose Output Directory");
			if (!savedDir.equals("")) {
				directoryChooser.setFilterPath(savedDir);
			}
			result[0] = directoryChooser.open();
		});
		String updatedDir = result[0];

		// Save updated output directory
		if (updatedDir != null && !updatedDir.isEmpty()) {
			pref.put(DEFAULT_PATH, updatedDir);
		}
		return updatedDir;
	}

	protected ModelInfo getModelInfo(ComponentType ct) {
		// Get saved model info
		ModelInfo savedInfo = getMatlabExportInfo(ct);

		// Prompt for updated model info
		Shell shell = getWindow().getShell();
		Display display = shell.getDisplay();
		ModelInfo[] result = new ModelInfo[1];
		display.syncExec(() -> {
			ModelInfoDialog dialog = new ModelInfoDialog(shell, savedInfo);
			dialog.open();
			result[0] = dialog.getModelInfo();
		});
		ModelInfo updatedModelInfo = result[0];

		// Save updated model info
		if (updatedModelInfo != null) {
			setMatlabExportInfo(ct, updatedModelInfo);
		}
		return updatedModelInfo;
	}

	private ModelInfo getMatlabExportInfo(ComponentType ct) {
		Property prop = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(ct, "Source_Text");
		try {
			ListValue lv = (ListValue) PropertyUtils.getSimplePropertyListValue(ct, prop);
			return new ModelInfo(getStringElement(lv, 0), getStringElement(lv, 1), getStringElement(lv, 2));
		} catch (PropertyNotPresentException e) {
			return new ModelInfo();
		}
	}

	private String getStringElement(ListValue lv, int k) {
		if (0 <= k && k < lv.getOwnedListElements().size()) {
			StringLiteral sl = (StringLiteral) lv.getOwnedListElements().get(k);
			return sl.getValue();
		} else {
			return "";
		}
	}

	private void setMatlabExportInfo(ComponentType ct, ModelInfo info) {
		ListValue lv = getOrCreateSourceText(ct);

		StringLiteral sl1 = (StringLiteral) lv.createOwnedListElement(Aadl2Package.eINSTANCE.getStringLiteral());
		sl1.setValue(info.originalModelName);

		StringLiteral sl2 = (StringLiteral) lv.createOwnedListElement(Aadl2Package.eINSTANCE.getStringLiteral());
		sl2.setValue(info.updatedModelName);

		StringLiteral sl3 = (StringLiteral) lv.createOwnedListElement(Aadl2Package.eINSTANCE.getStringLiteral());
		sl3.setValue(info.subsystemName);
	}

	private ListValue getOrCreateSourceText(ComponentType ct) {
		Property prop = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(ct, "Source_Text");

		try {
			// Try to find existing property association
			ListValue lv = (ListValue) PropertyUtils.getSimplePropertyListValue(ct, prop);
			lv.getOwnedListElements().clear();
			return lv;
		} catch (PropertyNotPresentException e) {
			// Create property association
			PropertyAssociation pa = ct.createOwnedPropertyAssociation();
			pa.setProperty(prop);
			ModalPropertyValue mpv = pa.createOwnedValue();
			return (ListValue) mpv.createOwnedValue(Aadl2Package.eINSTANCE.getListValue());
		}
	}

	protected String getJobName() {
		return "AGREE - Generate Matlab Function Single Layer";
	}

	public void writeToFile(Path filePath, String contentStr) {
		// Write MATLAB function code into the specified file
		try {
			BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8);

			// Write MATLAB function name
			writer.write(contentStr);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
