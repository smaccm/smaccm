package com.rockwellcollins.atc.resolute.analysis.export;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.ui.util.ResourceUtil;

import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;
import com.rockwellcollins.atc.resolute.analysis.results.ResoluteResult;

public class CAZExport {
    /**
     * Clients may call this function to see if CertWare is present, if it is not it will throw a
     * NoClassDefFoundError
     */
    public static void tryLoad() throws NoClassDefFoundError {
        // Nothing needed since static initialization of this class already tries to load CertWare
    }

    /*
    public static void export(ClaimResult cr) {
        
        CAZExport export = new CAZExport();
        export.export(null, cr);
        export.save(getProject(cr));
    }

    private static IProject getProject(ClaimResult cr) {
        IFile file = ResourceUtil.getFile(cr.getLocation().eResource());
        return file.getProject();
    }

    private static final CazFactory f = CazFactory.eINSTANCE;

    private ArgumentDiagram argumentDiagram = f.createArgumentDiagram();
    private int id = 0;

    public void export(Claim parent, ResoluteResult result) {
        if (result instanceof ClaimResult) {
            ClaimResult cr = (ClaimResult) result;
            Claim claim = f.createClaim();

            claim.setIdentifier("id" + id++);
            claim.setContent(cr.getText());

            argumentDiagram.getClaims().add(claim);
            if (parent != null) {
                parent.getSubClaims().add(claim);
            }

            parent = claim;
        }

        for (ResoluteResult subresult : result.getChildren()) {
            export(parent, subresult);
        }
    }

    public void save(IProject project) {
        CazCreationWizard wizard = new CazCreationWizard();
        IStructuredSelection selection = new StructuredSelection(project);
        wizard.init(PlatformUI.getWorkbench(), selection);
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        WizardDialog dialog = new WizardDialog(shell, wizard);

        if (dialog.open() == WizardDialog.OK) {
            Diagram diagram = (Diagram) wizard.getDiagram().getContents().get(0);
            final Resource modelResource = diagram.getElement().eResource();

            TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(modelResource);
            domain.getCommandStack().execute(new RecordingCommand(domain) {
                @Override
                protected void doExecute() {
                    modelResource.getContents().clear();
                    modelResource.getContents().add(argumentDiagram);
                    try {
                        modelResource.save(null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    */
}
