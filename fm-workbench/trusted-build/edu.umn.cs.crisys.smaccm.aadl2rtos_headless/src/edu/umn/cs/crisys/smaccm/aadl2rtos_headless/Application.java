package edu.umn.cs.crisys.smaccm.aadl2rtos_headless;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
  public Object start(final IApplicationContext context) throws Exception {  
    System.out.println("Hello RCP World!");  
    @SuppressWarnings("rawtypes")
    final Map args = context.getArguments();  
    final String[] appArgs = (String[]) args.get("application.args");  
    for (final String arg : appArgs) {  
     System.out.println(arg);  
    }  
    // int fileIndex=0;
    IWorkspace ws = ResourcesPlugin.getWorkspace();
    IWorkspaceRoot root = ws.getRoot();
    IProject[] projects = root.getProjects(); 
    System.out.println("Here are the projects!");
    for (IProject proj : projects) {
      System.out.println(proj);
      System.out.println(proj.getName());
      if (proj.getName().equals("flight")) {
        System.out.println("found flight!");
        proj.open(null);
        IResource ires = root.findMember("/flight/smaccmpilot-build/smaccmpilot-stm32f4/build/px4fmu17_ioar_aadl/gen/apps/flight/flight_gen.aadl");
        Resource res = OsateResourceUtil.getResource(ires);
        if (res != null) {
           System.out.println(res);
           EList<EObject> list = res.getContents(); 
           EObject first = list.get(0); 
           System.out.println(first);
        }
      }
    }
    System.out.println("Workspace root: " + root.toString());
/*    if (appArgs[fileIndex].equals("-pdelaunch")) {
      fileIndex++;
      Resource file = OsateResourceUtil.getResource(URI.createURI(appArgs[fileIndex]));
      EList list = file.getContents();
      System.out.println("Hello!");
      // AadlSpec spec = (AadlSpec) list.get(0);
//      System.out.println(" Spec name = "+spec.getName());
    }
*/
    return IApplication.EXIT_OK;  
   } 
	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		// nothing to do
	}
}
