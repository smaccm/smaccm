/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import java.io.BufferedWriter;
import java.io.IOException;

import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CommonNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.SharedDataAccessor;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.SharedDataAccessor.AccessType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class HeaderDeclarations {

  static public void writeReaderWriterDecl(ThreadImplementation tw, BufferedWriter out) throws IOException {
    for (DataPort dpiw : tw.getPortList()) {
      String fnName = CommonNames.getReaderWriterFnName(dpiw);
      String argString = ""; 
      if (!dpiw.getDataType().equals(new UnitType())) {
        argString = CommonNames.createRefParameter(dpiw.getDataType(), "arg");
        if (dpiw instanceof OutputPort) {
          argString = "const " + argString;
        }
      }
      out.append("   bool " + fnName + "(" + argString + "); \n\n");
    }
    
    for (SharedDataAccessor sda: tw.getSharedDataAccessors()) {
      if (sda.getAccessType() == AccessType.READ || 
          sda.getAccessType() == AccessType.READ_WRITE) {
        String fnName = CommonNames.getThreadImplReaderFnName(sda);
        String argString = CommonNames.createRefParameter(sda.getSharedData().getDataType(), "arg");
        out.append("   bool " + fnName + "(" + argString + "); \n\n");
      }
      if (sda.getAccessType() == AccessType.WRITE || 
          sda.getAccessType() == AccessType.READ_WRITE) {
        String fnName = CommonNames.getThreadImplWriterFnName(sda);
        String argString = CommonNames.createRefParameter(sda.getSharedData().getDataType(), "arg");
        out.append("   bool " + fnName + "(const " + argString + "); \n\n");
      }
    }
  }

}
