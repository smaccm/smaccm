/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CommonNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.eChronos.Names;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.Dispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.ExternalHandler;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.InputEventDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.PeriodicDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.DataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor.AccessType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;

/**
 * @author Whalen
 *
 */
public class HeaderDeclarations {

  static public String udeEntrypointDecl(Dispatcher d, String fnName) {
    if (d instanceof InputEventDispatcher && 
        ((InputEventDispatcher)d).getEventPort().isInputEventDataPort()) {
      InputEventDispatcher ied = (InputEventDispatcher)d;
      return "   void " + fnName
          + "(const " + Names.createRefParameter(ied.getEventPort().getType(), "elem") + " ); \n\n";
    }
    else if (d instanceof PeriodicDispatcher) {
      return "   void " + fnName
          + "(uint32_t *millis_from_sys_start); \n\n";
    }
    else {
      return "   void " + fnName
        + "(); \n\n";
    }
  }

  static public void writeThreadUdeDecls(BufferedWriter out, ThreadImplementation tw) throws IOException {
    ExternalHandler initHandler = tw.getInitializeEntrypointOpt();
    if ((initHandler != null)) {
      out.append("   void " + initHandler.getHandlerName() + "();\n\n");
    }

    // compute entrypoints for thread.
    List<Dispatcher> dl = tw.getDispatcherList();
    for (Dispatcher d: dl) {
      List<ExternalHandler> handlers = d.getExternalHandlerList();
      for (ExternalHandler eh: handlers) {
        out.append(udeEntrypointDecl(d, eh.getHandlerName()));
      }
    }
  }

  
  static public void writeReaderWriterDecl(ThreadImplementation tw, BufferedWriter out) throws IOException {
    for (DataPort dpiw : tw.getPortList()) {
      String fnName = CommonNames.getReaderWriterFnName(dpiw);
      String argString = ""; 
      if (!dpiw.getType().equals(new UnitType())) {
        argString = CommonNames.createRefParameter(dpiw.getType(), "arg");
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
        String argString = CommonNames.createRefParameter(sda.getSharedData().getType(), "arg");
        out.append("   bool " + fnName + "(" + argString + "); \n\n");
      }
      if (sda.getAccessType() == AccessType.WRITE || 
          sda.getAccessType() == AccessType.READ_WRITE) {
        String fnName = CommonNames.getThreadImplWriterFnName(sda);
        String argString = CommonNames.createRefParameter(sda.getSharedData().getType(), "arg");
        out.append("   bool " + fnName + "(const " + argString + "); \n\n");
      }
    }
  }

}
