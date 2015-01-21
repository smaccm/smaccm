package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

/**
 * @author Whalen
 * 
 */
import java.io.BufferedWriter;
import java.io.IOException;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.ExternalHandler;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.IRQDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.InputEventDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.PeriodicDispatcher;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.PortConnection;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedDataAccessor;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstance;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadInstancePort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.*;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class SourceDeclarations {
  static final String ind = "   ";
  private IOperatingSystemPrimitives iosp; 
  
	public SourceDeclarations (IOperatingSystemPrimitives iosp) {
			this.iosp = iosp;
	}

  public void writeComment(BufferedWriter out, String comment) throws IOException {
    out.append("\n/**************************************************************************\n"
        + comment
        + " **************************************************************************/\n");
  }
	
	public void writeStdIncludes(BufferedWriter out, String HFile) throws IOException {
		out.append("#include <stdbool.h>\n");
		out.append("#include <stddef.h>\n");
		out.append("#include <stdint.h>\n");
		out.append("#include \"" + HFile + "\"\n\n");
	}
		
	public String memcpyStmt(Type ty, String dst, String src) {
    return "memcpy(" + dst + ", " + src +  
        ", sizeof(" + ty.getCType(0).varString("") + "))";
	}
	
	public String writeToAadlMemcpy(Type ty, String sharedDst, String src) {
	  return memcpyStmt(ty, CommonNames.getVarRef(ty, sharedDst), src);
	}
	
  public String readFromAadlMemcpy(Type ty, String dst, String sharedSrc) {
    return memcpyStmt(ty, dst, CommonNames.getVarRef(ty,  sharedSrc));
  }
	
  public void writeRefDecl(BufferedWriter out, Type t, String id) throws IOException {
    out.append(CommonNames.createRefParameter(t, id + ";\n"));
  }

  public void writeDecl(BufferedWriter out, Type t, String id) throws IOException {
    out.append(t.getCType().varString(id) + ";\n");
  }
  
	public void writeThreadEventDataPortDispatcher(BufferedWriter out, InputEventDispatcher d) throws IOException {
    
	  InputEventPort p = d.getEventPort();
	  String fnName = CommonNames.getInputQueueIsEmptyFnName(p.getOwner(), p);
    out.append(Util.ind(3) + "while (! " + fnName + "()) {\n");
    Type ty = p.getType(); 
    out.append(Util.ind(4) + ty.getCType(0).varString("elem") + ";\n");
    out.append(Util.ind(4) + 
        CommonNames.getThreadImplReaderFnName(p) + "(" + 
        CommonNames.getVarRef(ty, "elem") + ");\n");
    
    for (ExternalHandler handler: d.getExternalHandlerList()) {
      out.append(Util.ind(4) + handler.getHandlerName() + 
          "(/*threadID, */ " + CommonNames.getVarRef(ty, "elem") + ");\n");
    }
    out.append(Util.ind(3) + "}\n");
	}

	public void writeThreadEventPortDispatcher(BufferedWriter out, InputEventDispatcher d) throws IOException {
    InputEventPort p = d.getEventPort();
    String fnName = CommonNames.getInputQueueIsEmptyFnName(p.getOwner(), p);
    out.append(Util.ind(3) + "while (! " + fnName + "()) {\n");
    out.append(Util.ind(4) + CommonNames.getThreadImplReaderFnName(p) + "();\n");
    for (ExternalHandler handler: d.getExternalHandlerList()) {
      out.append(Util.ind(4) + handler.getHandlerName() + "(/*threadID*/);\n");
    }   
    out.append(Util.ind(3) + "}\n");
	}

	
	public void writeThreadPeriodicDispatcher(BufferedWriter out, PeriodicDispatcher d) throws IOException {
    //out.append(Util.ind(3) + "smaccm_millis_from_sys_start = smaccm_get_time_in_ms(); \n");
    for (ExternalHandler handler: d.getExternalHandlerList()) {
      out.append(Util.ind(3) + handler.getHandlerName() + "(&smaccm_millis_from_sys_start);\n");
    }	  
	} 

	public void writeThreadIRQDispatcher(BufferedWriter out, IRQDispatcher d) throws IOException {
	  for (ExternalHandler handler: d.getExternalHandlerList()) {
	    out.append(Util.ind(3) + handler.getHandlerName() + "();\n"); 
	  }
	}
   
  public void writeIsEmpty(BufferedWriter out, ThreadImplementation impl, DataPort inp) throws IOException {
    
    String fnName = CommonNames.getInputQueueIsEmptyFnName(impl, inp);
    out.append("bool " + fnName + "(/* THREAD_ID tid,  */ "); 
    out.append(") {\n\n");  

    // create function result variable.
    out.append(ind + "bool result = false;\n\n");
    
    if (impl.getThreadInstanceList().size() != 1) {
      throw new Aadl2RtosException("In current aadl2rtos implementation, only one thread instance is " + 
          "allowed per thread implementation.  Violating thread implementation: " + 
          impl.getName());
    }
        
    for (ThreadInstance ti: impl.getThreadInstanceList()) {
      writeThreadInstanceComment(out, ti);
      
      ThreadInstancePort tip = new ThreadInstancePort(ti, inp); 
      DataPort destPort = tip.getPort();
      out.append(ind + iosp.lockMutex(tip));
      
      if (destPort instanceof InputDataPort) {
        out.append(ind + "result = false; \n");
      }
      else if (destPort instanceof InputEventPort) {
        InputEventPort iep = (InputEventPort)destPort;
        if (iep.isInputEventPort()) {
          out.append(ind + "result = " + tip.getVarName() + " == 0;\n");
        }
        else if (iep.isInputEventDataPort()) {
           out.append(ind + "result = " + tip.getVarName() + "_is_empty();\n");
        }
      } else {
        throw new Aadl2RtosException("Thread instance had wrong port!");
      }
          

      // unlock the semaphore
      out.append(ind + iosp.unlockMutex(tip));
    }
    out.append(ind + "return result;\n");
    out.append("}\n\n");
  }


  public void writeThreadInstanceComment(BufferedWriter out, ThreadInstance ti) throws IOException {
    out.append(ind + " /////////////////////////////////////////////////////////////////////////\n");
    out.append(ind + " // here is where we would branch based on thread instance id.\n");
    out.append(ind + " // For now we support only one thread instance per thread implementation.\n");
    out.append(ind + " // In this case we would split on destination thread id: " + 
        ti.getName() + "\n");
    out.append(ind + " /////////////////////////////////////////////////////////////////////////\n");
    out.append("\n\n");    
  }
  
  public void writeReader(BufferedWriter out, ThreadImplementation impl, InputPort inp) throws IOException {
    
    Type argType = inp.getType();
    
    out.append("bool " + CommonNames.getThreadImplReaderFnName(inp) 
        + "(/* THREAD_ID tid,  */ "); 
    if (inp.isInputEventPort()) {
      out.append(") {\n\n");  
    } else {
      out.append(CommonNames.createRefParameter(argType, "elem") + ") {\n\n");
    }
    // create function result variable.
    out.append(ind + "bool result = true;\n\n");
    
    if (impl.getThreadInstanceList().size() != 1) {
      throw new Aadl2RtosException("In current aadl2rtos implementation, only one thread instance is " + 
          "allowed per thread implementation.  Violating thread implementation: " + 
          impl.getName());
    }
    for (ThreadInstance ti: impl.getThreadInstanceList()) {
      writeThreadInstanceComment(out, ti);
      
      ThreadInstancePort tip = new ThreadInstancePort(ti, inp); 
      InputPort destPort = (InputPort)tip.getPort();
      Type destPortType = destPort.getType();
      
      // lock the semaphore
      out.append(ind + iosp.lockMutex(tip));
      
      if (destPort.isInputDataPort()) {
        if (destPortType.isBaseType()) {
          out.append(ind + "*elem = " + tip.getVarName() + "; \n");
        } else {
          out.append(ind + readFromAadlMemcpy(destPortType, "elem", tip.getVarName()) + ";\n");
        }
      }
      else if (destPort.isInputEventPort()) {
        out.append(ind + "if (" + tip.getVarName() + " > 0) {\n");
          out.append(ind + ind + tip.getVarName() + " -= 1; \n");
        out.append(ind + "} else {\n");
          out.append(ind + ind + "result = false;\n");
        out.append(ind + "}\n");
      }
      else if (destPort.isInputEventDataPort()) {
        // correct algorithm involves: 
        //
        out.append(ind + "result = " + tip.getVarName() + "_dequeue(elem);\n");
      }
      // unlock the semaphore
      out.append(ind + iosp.unlockMutex(tip));
    }
    out.append(ind + "return result;\n");
    out.append("}\n\n");
  }

  public void writeSharedDataReader(BufferedWriter out, ThreadImplementation impl, SharedDataAccessor outp) throws IOException {
    SharedData sharedData = outp.getSharedData();
    Type dt = sharedData.getType();
    out.append("bool " + CommonNames.getThreadImplReaderFnName(outp) + 
      "(/* THREAD_ID tid,  */ " + 
        CommonNames.createRefParameter(outp.getSharedData().getType(), "elem") + ") {\n\n");
    out.append(ind + "bool result = true;\n\n");
    out.append(ind + iosp.lockMutex(outp.getSharedData()));
    if (dt.isBaseType()) {
      out.append(ind + "*elem = " + sharedData.getVarName() + "; \n");
    } else {
      out.append(ind + this.readFromAadlMemcpy(dt, "elem", sharedData.getVarName()) + ";\n");
    }
    out.append(ind + iosp.unlockMutex(outp.getSharedData()));
    out.append(ind + "return result;\n");
    out.append("}\n\n");
  }
  
  
  public String StringForNonArrayType(Type t, String ret) {
    if (t instanceof ArrayType) {
      return ""; 
    } else {
      return ret; 
    }
  }
  
  public void writeEventDataPortSharedVars(BufferedWriter out, ThreadInstancePort c, InputEventPort dstPort, Type portTy) throws IOException {
    String arraySize = Integer.toString(dstPort.getQueueSize());
    String queueName = c.getVarName();
    String isFullName = c.getIsFullName();
    String head = c.getCircBufferFrontVarName();
    String tail = c.getCircBufferBackVarName();

    out.append(dstPort.getQueueType().getCType().varString(queueName) + "; \n");
    out.append("bool " + isFullName + " = false; \n");
    out.append(dstPort.getCircRefType().getCType().varString(head) + "; \n");
    out.append(dstPort.getCircRefType().getCType().varString(tail) + "; \n\n");
    
    
    // Write is_full function
    out.append("bool " + queueName + "_is_full() {\n");
    out.append(ind + "return (" + tail + " == " + head + ") && (" + isFullName + ");\n");
    out.append("}\n\n");

    // Write is_empty function
    out.append("bool " + queueName + "_is_empty() {\n");
    out.append(ind + "return (" + tail + " == " + head + ") && (!" + isFullName + ");\n");
    out.append("}\n\n");

    // Write enqueue function
    out.append("bool " + queueName + "_enqueue(const " + CommonNames.createRefParameter(portTy, "elem") + ") {\n");
    out.append(ind + "if (" + queueName + "_is_full()) {\n");
    out.append(ind + ind + "return false;\n");
    out.append(ind + "} else {\n");


    if (portTy.isBaseType()) {
      out.append(ind + ind + queueName + "[" + tail + "] = *elem;\n");
    }
    else {
      out.append(ind + ind + 
          writeToAadlMemcpy(portTy, queueName + "[" + tail + "]", "elem") + ";\n");
    }

    out.append(ind + ind + tail + " = (" + tail + " + 1) % " + arraySize + ";\n");
    out.append(ind + ind + "if (" + tail + " == " + head + ") { " + isFullName + " = true; } \n\n");
    out.append(ind + ind + "return true;\n");
    out.append(ind + "}\n");
    out.append("}\n\n");

    // Write dequeue function
    out.append("bool " + queueName + "_dequeue(" + CommonNames.createRefParameter(portTy, "elem") + ") {\n");
    out.append(ind + "if (" + queueName + "_is_empty()) {\n");
    out.append(ind + ind + "return false;\n");
    out.append(ind + "} else {\n");
    if (portTy.isBaseType()) {
      out.append(ind + ind + "*elem = " + queueName + "[" + head + "] ;\n");
    }
    else {
      out.append(ind + ind + 
          readFromAadlMemcpy(portTy, "elem", queueName + "[" + head + "]") + ";\n");
    }
    out.append(ind + ind + head + " = (" + head + " + 1) % " + arraySize + ";\n");
    out.append(ind + ind + isFullName + " = false; \n");
    out.append(ind + ind + "return true;\n");
    out.append(ind + "}\n");
    out.append("}\n\n");
  }
  
  public void writeThreadInstancePortSharedVars(BufferedWriter out, ThreadInstancePort c) throws IOException {
	  
    InputPort dstPort; 
	  Type portTy = c.getPort().getType();
	  
	  if (c.getPort() instanceof InputPort) {
	    dstPort = (InputPort)c.getPort();
	  } else {
      throw new Aadl2RtosException("When writing connection variables, destination " + 
          " port type is not one of: {Data, Event, Event Data} \n");
    }
	  
    writeComment(out, "Shared data for thread instance port: " + c.getNameRoot() + "\n");
    if (dstPort.isInputDataPort()) {
	    out.append(portTy.getCType().varString(c.getVarName()) + "; \n");
	  } else if (dstPort.isInputEventDataPort()) {
	    writeEventDataPortSharedVars(out, c, (InputEventPort)dstPort, portTy);
	  } else if (dstPort.isInputEventPort()) {
	    Type countType = new IntType(32, false);
	    out.append(countType.getCType().varString(c.getVarName()) + "; \n");
	  }
    
	  out.append("\n\n");
	}

  // TODO: normalize ports and shared data in terms of functions.
  public void writeSharedDataVar(BufferedWriter out, SharedData c) throws IOException {
    writeComment(out, "Shared data for shared data port: " + c.getPortName() + "\n");
    out.append(c.getType().getCType().varString(c.getVarName()) + "; \n");
  }
  
  
  public void writeWriter(BufferedWriter out, ThreadImplementation impl, OutputPort outp) throws IOException {
    
    Type argType = outp.getType();
    
    out.append("bool " + CommonNames.getThreadImplWriterFnName(outp) + 
        "(/* THREAD_ID tid,  */ "); 
    if (outp.isOutputEventPort()) {
      out.append(") {\n\n");  
    } else {
      out.append("const " + CommonNames.createRefParameter(argType, "elem") + ") {\n\n");
    }

    // create function result variable.
    out.append(ind + "bool result = true;\n\n");
    
    if (impl.getThreadInstanceList().size() != 1) {
      throw new Aadl2RtosException("In current aadl2rtos implementation, only one thread instance is " + 
          "allowed per thread implementation.  Violating thread implementation: " + 
          impl.getName());
    }
    for (ThreadInstance ti: impl.getThreadInstanceList()) {
      writeThreadInstanceComment(out, ti);
      
      
      for (PortConnection c: outp.getConnections()) {
        InputPort destPort = c.getDestPort();
        Type destPortType = destPort.getType();
        ThreadInstance destThread = c.getDestThreadInstance();
        ThreadInstancePort tip = new ThreadInstancePort(destThread, destPort); 
                
        // lock the semaphore
        // writeEnterCriticalSection(ind, tip.getMutexDefine());
        out.append(ind + iosp.lockMutex(tip));
        
        if (destPort.isInputDataPort()) {
          if (destPortType.isBaseType()) {
            out.append(ind + tip.getVarName() + " = *elem; \n");
          } else {
            out.append(ind + 
                this.writeToAadlMemcpy(destPortType, tip.getVarName(), "elem") + ";\n");
          }
        }
        else if (destPort.isInputEventPort()) {
          out.append(ind + tip.getVarName() + " += 1; \n"); 
        }
        else if (destPort.isInputEventDataPort()) {
          out.append(ind + "result = " + tip.getVarName() + "_enqueue(elem);\n");
        }
        if (destPort.isInputEventPort() || destPort.isInputEventDataPort()) {
          int signalNumber = destPort.getOwner().getSignalNumberForInputEventPort((InputEventPort)destPort); 
          
          // signalNumber = -1 if input event port does not have a dispatcher.
          if (signalNumber != -1) {
            out.append(ind + iosp.signalDispatcherProc(tip)); 
          } 
        }
        
        // unlock the semaphore
        out.append(ind + iosp.unlockMutex(tip)); 
      }
    }
    out.append(ind + "return result;\n");
    out.append("}\n\n");
  }

  public void writeSharedDataWriter(BufferedWriter out, ThreadImplementation impl, SharedDataAccessor outp) throws IOException {
    SharedData sharedData = outp.getSharedData();
    Type dt = sharedData.getType();
    out.append("bool " + CommonNames.getThreadImplWriterFnName(outp) + 
      "(/* THREAD_ID tid,  */ "); 
    out.append("const " + CommonNames.createRefParameter(sharedData.getType(), "elem") + ") {\n\n");
    out.append(ind + "bool result = true;\n\n");
    out.append(ind + iosp.lockMutex(sharedData));
    if (dt.isBaseType()) {
      out.append(ind + sharedData.getVarName() + " = *elem; \n");
    } else {
      out.append(ind + 
          this.writeToAadlMemcpy(dt, sharedData.getVarName(), "elem") + ";\n");
    }    
    out.append(ind + iosp.unlockMutex(sharedData));
    out.append(ind + "return result;\n");
    out.append("}\n\n");
  }
}    
  
