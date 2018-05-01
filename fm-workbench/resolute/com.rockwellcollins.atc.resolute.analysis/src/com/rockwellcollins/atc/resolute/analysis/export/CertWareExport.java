package com.rockwellcollins.atc.resolute.analysis.export;

import java.io.IOException;
//
//import net.certware.sacm.SACM.AssuranceCase;
//import net.certware.sacm.SACM.SACMFactory;
//import net.certware.sacm.SACM.Argumentation.Argumentation;
//import net.certware.sacm.SACM.Argumentation.ArgumentationFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.util.ResourceUtil;

import com.rockwellcollins.atc.resolute.analysis.results.ClaimResult;

public class CertWareExport {
	/**
	 * Clients may call this function to see if CertWare is present, if it is
	 * not it will throw a NoClassDefFoundError
	 */
	public static void tryLoad() throws NoClassDefFoundError {
		// Nothing needed since static initialization of this class already
		// tries to load CertWare
	}

	public static void export(ClaimResult cr) throws IOException {
		// CertWareExport export = new CertWareExport();
		// export.export(null, cr);
		// export.save(getURI(cr));
	}

	@SuppressWarnings("unused")
	private static URI getURI(ClaimResult cr) {
		IFile file = ResourceUtil.getFile(cr.getLocation().eResource());
		IPath path = file.getFullPath();
		return URI.createPlatformResourceURI(path.addFileExtension("sacm").toString(), true);
	}
	//
	// private static final SACMFactory sf = SACMFactory.eINSTANCE;
	// private static final ArgumentationFactory af =
	// ArgumentationFactory.eINSTANCE;

	// private AssuranceCase assuranceCase = sf.createAssuranceCase();
	// private int id = 0;
	//
	// public void export(Argumentation parent, ResoluteResult result) {
	// if (result instanceof ClaimResult) {
	// ClaimResult cr = (ClaimResult) result;
	// Argumentation argumentation = af.createArgumentation();
	//
	// argumentation.setId("id" + id++);
	// argumentation.setContent(cr.getText());
	//
	// assuranceCase.getArgument().add(argumentation);
	// if (parent != null) {
	// parent.getArgumentation().add(argumentation);
	// }
	//
	// parent = argumentation;
	// }
	//
	// for (ResoluteResult subresult : result.getChildren()) {
	// export(parent, subresult);
	// }
	// }
	//
	// public void save(URI uri) throws IOException {
	// ResourceSet set = new ResourceSetImpl();
	// Resource res = set.createResource(uri);
	// res.getContents().add(assuranceCase);
	// res.save(null);
	// }
}
