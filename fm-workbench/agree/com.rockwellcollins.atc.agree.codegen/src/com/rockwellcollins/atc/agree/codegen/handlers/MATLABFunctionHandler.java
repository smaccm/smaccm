package com.rockwellcollins.atc.agree.codegen.handlers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;

import jkind.lustre.Node;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.Element;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.instantiation.InstantiateModel;
import org.osate.annexsupport.AnnexUtil;
import org.osate.ui.dialogs.Dialog;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeUtils;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.handlers.AadlHandler;
import com.rockwellcollins.atc.agree.analysis.translation.AgreeNodeToLustreContract;
import com.rockwellcollins.atc.agree.codegen.Activator;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPrimaryFunction;
import com.rockwellcollins.atc.agree.codegen.translation.LustreToMATLABTranslator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class MATLABFunctionHandler extends AadlHandler {
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
        if (!(root instanceof ComponentImplementation)) {
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID,
                    "Must select an AADL Component Implementation");
        }

        try {
            ComponentImplementation ci = (ComponentImplementation) root;

            SystemInstance si = null;
            try {
                si = InstantiateModel.buildInstanceModelFile(ci);
            } catch (Exception e) {
                Dialog.showError("Model Instantiate",
                        "Error while re-instantiating the model: " + e.getMessage());
                return Status.CANCEL_STATUS;
            }

            // SystemType sysType = si.getSystemImplementation().getType();
            ComponentType sysType = AgreeUtils.getInstanceType(si);
            EList<AnnexSubclause> annexSubClauses = AnnexUtil.getAllAnnexSubclauses(sysType,
                    AgreePackage.eINSTANCE.getAgreeContractSubclause());

            if (annexSubClauses.size() == 0) {
                throw new AgreeException(
                        "There is not an AGREE annex in the '" + sysType.getName() + "' system type.");
            }
            
            //Get Agree program
            AgreeProgram agreeProgram = new AgreeASTBuilder().getAgreeProgram(si);

            //Translate Agree Node to Lustre Node with pre-statement flatten, helper nodes inlined,
            //and variable declarations sorted so they are declared before use
            Node lustreNode = AgreeNodeToLustreContract.translate(agreeProgram.topNode, agreeProgram);
            
            //Translate Lustre Node to MATLAB Function AST
            MATLABPrimaryFunction matlabFunction = LustreToMATLABTranslator.translate(lustreNode, agreeProgram);
            
    		// Get the directory to create the output file
    		// File name will be the same as the function name
            Preferences pref = Preferences.userRoot();
            //Retrieve the selected path or use an empty string
            //if no path has previously been selected
            String path = pref.get("DEFAULT_PATH", "");
    		JFileChooser chooser = new JFileChooser();
    		chooser.setDialogTitle("Choose Output Directory");
    		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    		
    		// Set the path that was saved in preferences
    		chooser.setCurrentDirectory(new File(path));
    		
    		int returnVal = chooser.showOpenDialog(null);
    		
    		if (returnVal == JFileChooser.APPROVE_OPTION)
    		{
    		    File directory = chooser.getSelectedFile();
    		    chooser.setCurrentDirectory(directory);
    		    // Save the selected path
    		    pref.put("DEFAULT_PATH", directory.getAbsolutePath());
    		    
    		    Path filePath = Paths.get(directory.toString(), matlabFunction.name+".m");

        		// Write MATLAB function code into the specified file
        		try {
        			BufferedWriter writer = Files.newBufferedWriter(filePath,
        					StandardCharsets.UTF_8);

        			// Write MATLAB function name
        			writer.write(matlabFunction.toString());
        			writer.close();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
    		}
            return Status.OK_STATUS;
        } catch (Throwable e) {
            String messages = getNestedMessages(e);
            e.printStackTrace();
            return new Status(IStatus.ERROR, Activator.PLUGIN_ID, 0, messages, e);
        }
    }
	
	protected String getJobName() {
		 return "AGREE - Generate Matlab Function Single Layer";
	}
	
}

