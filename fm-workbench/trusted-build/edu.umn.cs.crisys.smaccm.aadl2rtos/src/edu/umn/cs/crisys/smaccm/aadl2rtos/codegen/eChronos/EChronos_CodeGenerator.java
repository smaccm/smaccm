package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.eChronos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosFailure;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common.CodeGeneratorBase;
import edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.names.ModelNames;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputDataPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.port.OutputEventPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.SharedData;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.UnitType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;

public class EChronos_CodeGenerator extends CodeGeneratorBase {

	// so write threadName_write_portName for each port.

	public EChronos_CodeGenerator(Logger log, Model model, File aadlDirectory, File outputDir) {
		super(log, model, aadlDirectory, outputDir, "eChronos");
	}

	 public void createAssemblyHeader() throws Aadl2RtosFailure {
	   ModelNames mn = new ModelNames(model);
	   String name = "smaccm_decls.h";
	   File assemblyFile = new File(includeDirectory, name);
     String path = assemblyFile.getAbsolutePath();
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(assemblyFile))) {
       STGroupFile stg = this.createTemplate("AssemblyHeader.stg");
       writeBoilerplateDTHeader(name, path, writer, stg.getInstanceOf("headerPrefix"), false);
 
       ST st = stg.getInstanceOf("headerBody");
       st.add("model", mn);
       writer.append(st.render());
     
       writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("headerPostfix"));
     } catch (IOException e) {
       log.error("Problem creating EChronos assembly header file.");
       throw new Aadl2RtosFailure(); 
     }
 }

 public void createPrxFile() throws Aadl2RtosFailure {
     ModelNames mn = new ModelNames(model);
     String name = mn.getEChronosPrxFileName();
     File file = new File(outputDirectory, name);
     String path = file.getAbsolutePath();
     String platform; 
     
     if (model.getHWTarget().equals("PX4")) {
       platform = "Prx-stm32f4-discovery.stg";
     } else {
       log.error("eChronos code generator: currently only PX4 hardware platform is supported.\n");
       throw new Aadl2RtosFailure();
     }
     
     try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
       STGroupFile stg = this.createTemplate(platform);
       writeBoilerplateDTHeader(name, path, writer, stg.getInstanceOf("prxPrefix"), false);
 
       ST st = stg.getInstanceOf("prxBody");
       st.add("model", mn);
       writer.append(st.render());
     
       writeBoilerplateFooter(name, path, writer, stg.getInstanceOf("prxPostfix"));
     } catch (IOException e) {
       log.error("Problem creating EChronos assembly header file.");
       throw new Aadl2RtosFailure(); 
     }
 }


  @Override
  public void osSpecificComponentFiles(ThreadImplementation ti,
      File componentDirectory, File srcDirectory, File includeDirectory)
      throws Aadl2RtosFailure {

    // This space (currently) for rent...
  }

  @Override
  public void createPeriodicDispatcherComponent() throws Aadl2RtosFailure {
    log.warn("eChronos code generator currently does not autogenerate the periodic dispatcher");
  }


  @Override
  public void write() throws Aadl2RtosFailure {
    createTypesHeader();
    createComponents();
    createAssemblyHeader();
    createPrxFile();

    // final check for errors from string template.
    if (listener.isErrorOccurred()) {
      throw new Aadl2RtosFailure();
    }
  }

  protected Set<Type> getUserTypes() {
    // write dispatcher types
    Set<Type> rwTypeSet = new HashSet<Type>();
    
    // "for free types" that are always necessary; void for event ports
    // and uint32_t for periodic dispatchers.  Note if the dispatcher 
    // time type changes, it may break code, so perhaps we should 
    // store the time type somewhere (model?); 
    // MWW: updated: store this in the periodic dispatcher class.
    
    rwTypeSet.add(new UnitType());
    //rwTypeSet.add(new IntType(32, false));  
    rwTypeSet.add(InputPeriodicPort.getPortType());  
    
    for (ThreadImplementation ti : model.getAllThreadImplementations()) {
      for (OutputDataPort d : ti.getOutputDataPortList()) {
        rwTypeSet.add(d.getType());
      }
      for (OutputEventPort d : ti.getOutputEventDataPortList()) {
        rwTypeSet.add(d.getType());
      }
      for (InputDataPort d : ti.getInputDataPortList()) {
        rwTypeSet.add(d.getType());
      }
      for (InputEventPort d : ti.getInputEventDataPortList()) {
        rwTypeSet.add(d.getType());
      }
    }
    for (SharedData d : model.getSharedDataList()) {
       rwTypeSet.add(d.getType());
    }
    return rwTypeSet ; 
  }
}