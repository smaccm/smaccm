package edu.umn.cs.crisys.smaccm.aadl2rtos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.impl.PortImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;
import org.osate.aadl2.instance.ConnectionInstanceEnd;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

public class Util {

    public static String normalizeAadlName(String name) {
        return name.replaceAll("[^A-Za-z0-9]", "_");
    }

    public static String normalizeAadlName(NamedElement n) {
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

    public static PortImpl getPortImplFromConnectionInstanceEnd(ConnectionInstanceEnd ci) {
        return (PortImpl) ci.getInstantiatedObjects().get(0);
    }

    public static ThreadTypeImpl getThreadFromPortImpl(PortImpl pi) {
        return (ThreadTypeImpl) pi.getContainingClassifier();
    }

    // @SuppressWarnings("rawtypes")
    public static <T> String toSafeStringCollection(Collection<T> c) {
        boolean first = true;
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (Object e : c) {
            if (!first) {
                sb.append(", ");
            } else {
                first = false;
            }
            sb.append(toSafeString(e));
        }
        sb.append("]");

        return sb.toString();
    }

    public static Property getPropertyDefinitionInWorkspace(String pdname) {
        return EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
                OsateResourceUtil.getResourceSet(), pdname);
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

    public static URL createURLFromClass(Class cl) {
      return cl.getProtectionDomain().getCodeSource().getLocation();
    }
    
    public static File getFileFromURL(URL url) throws URISyntaxException {
      return new File(url.toURI());
    }
    
}
