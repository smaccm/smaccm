package edu.umn.cs.crisys.tb.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.impl.ListValueImpl;
import org.osate.aadl2.impl.PortImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.aadl2.util.Aadl2Util;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;
import org.stringtemplate.v4.STErrorListener;
import org.stringtemplate.v4.STGroupFile;

import com.google.inject.Injector;

import edu.umn.cs.crisys.tb.PluginActivator;
import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
import edu.umn.cs.crisys.tb.model.process.ProcessInstance;

public class Util {
   // TODO: Clean up Util, PortUtil, and PropertyUtil!


   // TODO: MWW: necessary to implement listeners.  This is *ugly* but
   // I don't have a good solution right now.
   private static STErrorListener listener;
   public static void setListener(STErrorListener listener) { Util.listener = listener ; }
   public static STErrorListener getListener() { return listener; }

   private static String prefix = "tb";
   public static String getPrefix() {
      return prefix;
   }

   public static String getPrefix_() {
      return prefix + "_";
   }

   public static void setPrefix(String aprefix) {
      prefix = aprefix;
   }



   public static OSModel getElementOSModel(ModelElement element) {
      if (element == null) {
         return null;
      } else if (element instanceof OSModel) {
         return (OSModel)element;
      } else {
         return getElementOSModel(element.getParent());
      }
   }

   public static ProcessImplementation getProcessImplementation(ModelElement element) {
      if (element == null) {
         return null;
      } else if (element instanceof ProcessImplementation) {
         return (ProcessImplementation)element;
      } else if (element instanceof ProcessInstance) {
         return ((ProcessInstance)element).getProcessImplementation();
      }
      else {
         return getProcessImplementation(element.getParent());
      }
   }

	/**
	* Check whether all connections of a port feature have process implementation matching
	* that of the given port feature.
	*
	* @param pf port feature to check
	* @return true if all connections have the same process implementation as the given port
	*     feature, false otherwise.
	*/
   public static boolean allConnectionsInProcess(PortFeature pf) {
      ProcessImplementation pi = Util.getProcessImplementation(pf);
      for (PortConnection pc : pf.getConnections()) {
			ProcessImplementation pj = Util.getProcessImplementation(pc);
         if (pi != pj) {
			return false;
		}
      }
      return true;
   }

   public static String stackTraceString(Exception e) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      return sw.toString();
   }

   // Normalize AADL string name
   public static String normalizeAadlName(String name) {
      return name.replaceAll("[^A-Za-z0-9]", "_");
   }

   // Normalize AADL name from NamedElement
   public static String normalizeAadlName(NamedElement n) {
      if (n == null) {
         return "";
      }
      return normalizeAadlName(n.getQualifiedName());
   }

   public static String throwableToString(Throwable t) {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(baos);
      t.printStackTrace(ps);
      return baos.toString();
   }

   public static String toSafeString(Object e) {
      try {
         return e.toString();
      } catch (Throwable t) {
         return "Unable to print toString()";
      }
   }

   public static String getCUintTypeForMaxValue(int maxValue) {
      if (maxValue <= 256) {
		return "c_uint8";
	} else if (maxValue <= 65536) {
         return "c_uint16";
      } else {
         return "c_uint32";
      }

   }


   /*
    * getStringValue Fixes a bug in the PropertyUtils version...returns "" if
    * the string is not defined, so it is not possible to distinguish between
    * no property and empty string.
    */
   public static String getStringValue(final NamedElement ph, final Property pd) {
      final PropertyExpression pv = PropertyUtils.getSimplePropertyValue(ph, pd);
      return ((StringLiteral) pv).getValue();
   }

   public static String getStringValueOpt(NamedElement i, Property prop) {
      try {
         String s = getStringValue(i, prop);
         return s;
      } catch (Exception e) {
         return null;
      }
   }

   public static String pathRemoveExtension(String s) {
      // null test.
      if (s == null) {
         return null;
      }
      int start = s.lastIndexOf(File.separator) + 1;
      int end = s.lastIndexOf(".");
      end = start < end ? end : s.length();
      String name = s.substring(0, end);
      return name;
   }

   public static String fileNameFromPath(String s) {
      File f = new File(s);
      return f.getName();
   }

   public static String prxPath(String s) {
      return pathRemoveExtension(s)
            .replace("/", ".")
            .replace("\\", ".");
   }

   public static Integer getIntegerValueOpt(NamedElement i, Property prop) {
      try {
         return new Integer((int) PropertyUtils.getIntegerValue(i, prop));
      } catch (Exception e) {
         return null;
      }

   }

   public static int moduloCeiling(int modulus, int val) {
      return ((val % modulus) == 0) ? val : val + (modulus - (val % modulus));
   }

   public static <T> T assertNonNull(T obj, String error) {
      if (obj == null) {
         throw new TbException(error);
      }
      return obj;
   }

   public static String ind(int count) {
      int i = 0;
      StringBuffer s = new StringBuffer();
      while (i < count) {
         s.append("   ");
         i++;
      }
      return s.toString();
   }

   public static List<String> getSourceTextListOpt(NamedElement thread, Property property) {
      try {
         PropertyExpression value = PropertyUtils
               .getSimplePropertyListValue(thread, property);
         if (value instanceof ListValueImpl) {
            ListValueImpl listValue = (ListValueImpl) value;
            ArrayList<String> files = new ArrayList<String>();
            for (PropertyExpression v : listValue.getOwnedListElements()) {
               if (v instanceof StringLiteral) {
                  String s = ((StringLiteral) v).getValue();
                  files.add(s);
               } else {
                  return null;
               }
            }
            return files;
         } else {
            return null;
         }
      } catch (Exception e) {
         return null;
      }
   }

   public static PortImpl getPortImplFromConnectionInstanceEnd(ConnectionInstanceEnd ci) {
      return (PortImpl) ci.getInstantiatedObjects().get(0);
   }

   public static ThreadTypeImpl getThreadFromPortImpl(PortImpl pi) {
      return (ThreadTypeImpl) pi.getContainingClassifier();
   }

   public static Double getPeriodInPicoSecondsOpt(NamedElement thread) {
      // Temporary
      PropertyExpression value = PropertyUtils.getSimplePropertyValue(thread, PropertyUtil.PERIOD);
      if (value instanceof IntegerLiteral) {
         IntegerLiteral intLit = (IntegerLiteral) value;
         return intLit.getScaledValue();
      }
      return null;
   }

   public static Property getPropertyDefinitionInWorkspace(String pdname) {
		Injector injector = IResourceServiceProvider.Registry.INSTANCE
				.getResourceServiceProvider(URI.createFileURI("dummy.aadl")).get(Injector.class);
		final ResourceDescriptionsProvider resourceDescriptionsProvider = injector
				.getInstance(ResourceDescriptionsProvider.class);
		final IResourceDescriptions resourceDescriptions = resourceDescriptionsProvider
				.getResourceDescriptions(new XtextResourceSet());
		for (IEObjectDescription eobjDesc : resourceDescriptions
				.getExportedObjectsByType(Aadl2Package.eINSTANCE.getProperty())) {
			if (eobjDesc.getName().toString("::").equalsIgnoreCase(pdname)) {
				EObject res = eobjDesc.getEObjectOrProxy();
				res = EcoreUtil.resolve(res, OsateResourceUtil.getResourceSet());
				if (!Aadl2Util.isNull(res)) {
					return (Property) res;
				}
			}
		}
		throw new TbException(
				"Error: " + pdname + " does not correspond to any " + "system implementation in the workspace.\n");
   }

   public static File getDirectory(EObject e) {
      URI uri = e.eResource().getURI();
      IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
      IFile file = root.getFile(new Path(uri.toPlatformString(true)));
      return file.getParent().getLocation().toFile();
   }

   /////////////////////////////////////////////////////////////////////
   //
   // Attempts to find trusted build configuration files in one of three
   // places (in order of preference):
   //   1. A user-defined environment variable: $TB_CONFIG_DIR
   //   2. A directory titled: "tb_resources" in the directory containing the
   //      .jar file
   //   3. A directory titled: "tb_resources" within the .jar file itself.
   //      Note: if running from the command line, this is not available!
   //
   // the findConfigFileLocation attempts the first two locations; if it
   // 'hits', it returns the canonical path to the location of the file;
   // otherwise, it returns null.
   //
   /////////////////////////////////////////////////////////////////////

   public static final String tb_resource = "tb_resource";

   public static String findConfigFileLocation(String fileName) {
      String envAadlDirString = System.getenv("TB_CONFIG_DIR");
      //Map<String, String> myMap = System.getenv();
      File envAadlDir = null;
      try {
         if (envAadlDirString != null) {
            envAadlDir = new File(envAadlDirString);
            if (envAadlDir.exists()) {
               File fileNameLoc = new File(envAadlDir, fileName);
               if (fileNameLoc.exists()) {
                  return fileNameLoc.getCanonicalPath();
               }
            }
         }

         PluginActivator pi = PluginActivator.getDefault();
         if (pi != null) {
            URL url = createURLFromClass(pi.getClass());
            File pluginDir = getFileFromURL(url);
            if (pluginDir != null && pluginDir.exists()) {
               File resourceDir = new File(pluginDir, tb_resource);
               if (resourceDir.exists()) {
                  File fileNameLoc = new File(resourceDir, fileName);
                  if (fileNameLoc.exists()) {
                     return fileNameLoc.getCanonicalPath();
                  }
               }
            }
         }
      } catch (URISyntaxException e) { }
      catch (IOException e) { }

      return null;
   }

   public static InputStream findConfigFile(String fileName) {
      InputStream ins = null;

      try {
         String fileLocation = findConfigFileLocation(fileName);
         if (fileLocation != null) {
            File f = new File(fileLocation);
            ins = new FileInputStream(f);
         }
         else {
            ins = FileLocator.openStream(
                  PluginActivator.getDefault().getBundle(), new Path(tb_resource + "/" + fileName), false);
         }
      } catch (FileNotFoundException e) {
         throw new TbException("Unexpected error in findConfigFile: FileNotFoundException after file known to exist! Error: " + e);
      } catch (IOException e) {
         // o.k., not able to find the file using the plugin activator.  Nothing more to do here
         // but return null.
      }
      return ins;
   }

   public static URL createURLFromClass(@SuppressWarnings("rawtypes") Class cl) {
      return cl.getProtectionDomain().getCodeSource().getLocation();
   }

   public static File getFileFromURL(URL url) throws URISyntaxException {
      return new File(url.toURI());
   }

   public static STGroupFile createTemplate(String fname) {
      String path = Util.findConfigFileLocation(fname);
      if (path == null) {
        path = Util.tb_resource + "/" + fname;
      }
      STGroupFile templates = new STGroupFile(path);
      templates.setListener(listener);
      return templates;
   }

   public static final String DARPA_License = "Copyright (c) 2013, Rockwell Collins and the University of Minnesota." + System.lineSeparator()
         + "Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA)." + System.lineSeparator()
         + System.lineSeparator()
         + "Permission is hereby granted, free of charge, to any person obtaining a copy of this data," + System.lineSeparator()
         + "including any software or models in source or binary form, as well as any drawings, specifications, " + System.lineSeparator()
         + "and documentation (collectively \"the Data\"), to deal in the Data without restriction, including " + System.lineSeparator()
         + "without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, " + System.lineSeparator()
         + "and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, " + System.lineSeparator()
         + "subject to the following conditions: " + System.lineSeparator()
         + System.lineSeparator()
         + "The above copyright notice and this permission notice shall be included in all copies or " + System.lineSeparator()
         + "substantial portions of the Data." + System.lineSeparator()
         + System.lineSeparator()
         + "THE DATA IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT " + System.lineSeparator()
         + "LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. " + System.lineSeparator()
         + "IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE " + System.lineSeparator()
         + "FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, " + System.lineSeparator()
         + "ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA. " + System.lineSeparator();


   /******************************************************************
    *
    * File writing helper functions
    *
    ******************************************************************/
   public static String getDate() {
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date d = new Date();
      return dateFormat.format(d);
   }
}