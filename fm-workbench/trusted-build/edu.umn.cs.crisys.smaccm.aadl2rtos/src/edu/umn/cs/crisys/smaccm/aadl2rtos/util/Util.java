package edu.umn.cs.crisys.smaccm.aadl2rtos.util;

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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.impl.ClassifierValueImpl;
import org.osate.aadl2.impl.ListValueImpl;
import org.osate.aadl2.impl.PortImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.PluginActivator;

public abstract class Util {

  final public static String DATA_MODEL_DATA_REPRESENTATION_NAME = "Data_Model::Data_Representation";
  final public static String DATA_MODEL_BASE_TYPE_NAME = "Data_Model::Base_Type";
  final public static String DATA_MODEL_DIMENSION_NAME = "Data_Model::Dimension";

  final public static Property DATA_MODEL_DATA_REPRESENTATION = Util
      .getPropertyDefinitionInWorkspace(DATA_MODEL_DATA_REPRESENTATION_NAME);
  final public static Property DATA_MODEL_BASE_TYPE = Util
      .getPropertyDefinitionInWorkspace(DATA_MODEL_BASE_TYPE_NAME);
  final public static Property DATA_MODEL_DIMENSION = Util
      .getPropertyDefinitionInWorkspace(DATA_MODEL_DIMENSION_NAME);

  public static EnumerationLiteral getDataRepresentationName(NamedElement tti) {
    try {
      return PropertyUtils.getEnumLiteral(tti, Util.DATA_MODEL_DATA_REPRESENTATION);
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Data_Representation' not found for type: " + tti.getName());
    }
  }

  public static DataClassifier getBaseType(NamedElement tti) {
    PropertyExpression value ;
    try {
      value = PropertyUtils
          .getSimplePropertyListValue(tti, Util.DATA_MODEL_BASE_TYPE);
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Base_Type' not found for type: " + tti.getName());      
    }
    if (value instanceof ListValueImpl) {
      ListValueImpl listValue = (ListValueImpl) value;
      if (listValue.getOwnedListElements().size() != 1) {
        throw new Aadl2RtosException("For array type: " + tti.getName() + " base type list has more than one element.");
      }
      PropertyExpression sizeExpr = listValue.getOwnedListElements().get(0);
      if (sizeExpr instanceof ClassifierValueImpl && 
          ((ClassifierValueImpl)sizeExpr).getClassifier() instanceof DataClassifier) {
        ClassifierValueImpl castedSize =(ClassifierValueImpl) sizeExpr;  
        Classifier c = castedSize.getClassifier();
        return (DataClassifier)c;
      } else {
        throw new Aadl2RtosException("Classifier returned by Base_Type property does not correspond to list for array type: " + tti.getName());
      }
    } else {
      throw new Aadl2RtosException("Base_Type property is not a list value for array type: " + tti.getName());
    }
  }
  
  public static int getDimension(NamedElement tti) {
    try {
      PropertyExpression value = PropertyUtils.getSimplePropertyValue(tti, Util.DATA_MODEL_DIMENSION);
      if (value instanceof ListValueImpl) {
        ListValueImpl listValue = (ListValueImpl) value;
        if (listValue.getOwnedListElements().size() != 1) {
            throw new Aadl2RtosException("For array type: " + tti.getName() + " only single dimensional arrays are currently supported.");
        }
        PropertyExpression sizeExpr = listValue.getOwnedListElements().get(0);
        IntegerLiteral intLit = (IntegerLiteral) sizeExpr;
        double scaledDim = intLit.getScaledValue();
        return (int)(new Integer((int)scaledDim)); // bits per byte.
      } else {
        throw new Aadl2RtosException("Classifier returned by Dimension property does not correspond to list for array type: " + tti.getName());
      }
    } catch (Exception e) {
      throw new Aadl2RtosException("Required property 'Dimension' not found for type: " + tti.getName());
    }
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
    	if (maxValue <= 256) 
    		return "c_uint8";
    	else if (maxValue <= 65536) {
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

	public static <T> T assertNonNull(T obj, String error) {
		if (obj == null) {
			throw new Aadl2RtosException(error);
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
		return EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
				OsateResourceUtil.getResourceSet(), pdname);
	}
	
	public static File getDirectory(EObject e) {
		URI uri = e.eResource().getURI();
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFile file = root.getFile(new Path(uri.toPlatformString(true)));
		return file.getParent().getLocation().toFile();
	}

	/////////////////////////////////////////////////////////////////////
	//
	// Attempts to find aadl2rtos configuration files in one of three
	// places (in order of preference):
	//   1. A user-defined environment variable: $AADL2RTOS_CONFIG_DIR
	//   2. A directory titled: "aadl2rtos_resources" in the directory containing the 
	//      .jar file
	//   3. A directory titled: "aadl2rtos_resources" within the .jar file itself.
	//      Note: if running from the command line, this is not available!
	//
	// the findConfigFileLocation attempts the first two locations; if it 
	// 'hits', it returns the canonical path to the location of the file;
	// otherwise, it returns null.
	// 
  /////////////////////////////////////////////////////////////////////

	public static final String aadl2rtos_resource = "aadl2rtos_resource";
	
	public static String findConfigFileLocation(String fileName) {
    String envAadlDirString = System.getenv("AADL2RTOS_CONFIG_DIR");
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
          File resourceDir = new File(pluginDir, aadl2rtos_resource);
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
  	        PluginActivator.getDefault().getBundle(), new Path(aadl2rtos_resource + "/" + fileName), false);
  	  }
	  } catch (FileNotFoundException e) {
	    throw new Aadl2RtosException("Unexpected error in findConfigFile: FileNotFoundException after file known to exist! Error: " + e);
	  } catch (IOException e) {
	    // o.k., not able to find the file using the plugin activator.  Nothing more to do here
	    // but return null.
	  }
	  return ins;
	}

	
	public static final String DARPA_License = "Copyright (c) 2013, Rockwell Collins and the University of Minnesota.\n"
			+ "Developed with the sponsorship of the Defense Advanced Research Projects Agency (DARPA).\n"
			+ "\n"
			+ "Permission is hereby granted, free of charge, to any person obtaining a copy of this data,\n"
			+ "including any software or models in source or binary form, as well as any drawings, specifications, \n"
			+ "and documentation (collectively \"the Data\"), to deal in the Data without restriction, including \n"
			+ "without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, \n"
			+ "and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, \n"
			+ "subject to the following conditions: \n"
			+ "\n"
			+ "The above copyright notice and this permission notice shall be included in all copies or \n"
			+ "substantial portions of the Data.\n"
			+ "\n"
			+ "THE DATA IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT \n"
			+ "LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. \n"
			+ "IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE \n"
			+ "FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, \n"
			+ "ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA. \n";
	
  public static URL createURLFromClass(@SuppressWarnings("rawtypes") Class cl) {
    return cl.getProtectionDomain().getCodeSource().getLocation();
  }
  
  public static File getFileFromURL(URL url) throws URISyntaxException {
    return new File(url.toURI());
  }
  
	
}