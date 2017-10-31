package edu.umn.cs.crisys.tb.codegen.toplevel;

import java.io.File;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.core.runtime.Status;

import edu.umn.cs.crisys.tb.Logger;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.CAmkES.CAmkES_CodeGenerator;
import edu.umn.cs.crisys.tb.codegen.VxWorks.VxWorks_CodeGenerator;
import edu.umn.cs.crisys.tb.codegen.eChronos.EChronos_CodeGenerator;
import edu.umn.cs.crisys.tb.codegen.linux.Linux_CodeGenerator;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.TopLevelModel;
import edu.umn.cs.crisys.tb.util.Util;

public class TopLevelCodeGenerator {

   TopLevelModel tlm; 
   File aadlDirectory;
   File outputDirectory;
   String optOutputDirectoryArg;
   Logger logger;

   public TopLevelCodeGenerator(TopLevelModel tlm, 
         File aadlDirectory, 
         File optOutputDirectoryArg, 
         Logger logger) {
      this.tlm = tlm;
      this.aadlDirectory = aadlDirectory;
      if (optOutputDirectoryArg != null) {
         tlm.setOutputDirectory(optOutputDirectoryArg.getAbsolutePath());
      }
      this.logger = logger;
   }


   private void configureTopLevelOutputDirectory() {
      if (tlm.getOutputDirectory() != null) {
         File temp = new File(tlm.getOutputDirectory());
         if (temp.isAbsolute()) {
            outputDirectory = temp;
         } else {
            outputDirectory = new File(aadlDirectory, tlm.getOutputDirectory());
         }
      } else {
         outputDirectory = new File(aadlDirectory, "tb");
         tlm.setOutputDirectory(outputDirectory.getPath());
      }
      outputDirectory.mkdirs(); 
   }

   private void setOSModelOutputDirectory(OSModel model) {
      model.setOutputDirectory((new File(outputDirectory, model.getPathName())).getAbsolutePath());
   }

   private boolean generateCodeForOSModel(OSModel model) {
      try {
         if (model.getOsTarget() == OSModel.OSTarget.eChronos) {
            EChronos_CodeGenerator gen = new EChronos_CodeGenerator(logger, model, aadlDirectory, outputDirectory);
            gen.write();
         } else if (model.getOsTarget() == OSModel.OSTarget.CAmkES ){
            CAmkES_CodeGenerator gen = new CAmkES_CodeGenerator(logger, model, aadlDirectory, outputDirectory);
            gen.write();
         } else if (model.getOsTarget() == OSModel.OSTarget.VxWorks ){
            VxWorks_CodeGenerator gen = new VxWorks_CodeGenerator(logger, model, aadlDirectory, outputDirectory);
            gen.write();
         } else if (model.getOsTarget() == OSModel.OSTarget.linux) {
            Linux_CodeGenerator gen = new Linux_CodeGenerator(logger, model, aadlDirectory, outputDirectory);
            gen.write();
         } else {
            logger.error("trusted build OS target: [" + model.getOsTarget() + "] not recognized.");
         } 
      } catch (TbFailure f) {
         logger.error("Analysis Exception");
         List<String> msgs = f.getMessages();
         for (String msg : msgs) {
            logger.error(msg);
         }
         logger.error("Context: " + Util.stackTraceString(f));
         return false;
      } catch (Exception e) {
         logger.error(e);
         logger.error("Context: " + Util.stackTraceString(e));
         return false;
      }
      return true;
   }

   public void generate() throws TbFailure {
      configureTopLevelOutputDirectory();
      for (OSModel m: tlm.getModels()) {
         setOSModelOutputDirectory(m);
         if (!generateCodeForOSModel(m))
            throw new TbFailure(); 
      }
   }
}
