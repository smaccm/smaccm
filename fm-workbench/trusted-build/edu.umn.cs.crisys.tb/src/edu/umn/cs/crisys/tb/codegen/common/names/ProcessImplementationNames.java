package edu.umn.cs.crisys.tb.codegen.common.names;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.process.ProcessImplementation;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.util.Util;

public class ProcessImplementationNames {

   ProcessImplementation pi; 
   
   public ProcessImplementationNames(ProcessImplementation pi) {
      this.pi = pi; 
   }
   
   public ProcessImplementation getTbAst() {
      return this.pi; 
   }
   
   public ModelNames getModel() {
      return EmitterFactory.model(this.pi.getModel());
   }
   
   public List<ThreadImplementationNames> getThreadImplementationList() {
      List<ThreadImplementationNames> tin = new ArrayList<>(); 
      for (ThreadImplementation ti: this.pi.getThreadImplementationList()) {
         tin.add(EmitterFactory.threadImplementation(ti));
      }
      return tin; 
   }

   public String getName() {
      return Util.normalizeAadlName(this.getRawName());  
   }
   
   public String getRawName() {
      return this.pi.getName();
   }
   
   public String getHeaderName() {
      return OSModel.getPrefix() + "_" + getName() + ".h";
   }
   
   public String getCFileName() {
      return OSModel.getPrefix() + "_" + getName() + ".c";
   }
   
   public String getMainFunctionName() {
      return OSModel.getPrefix() + "_" + getName() + "_main";
   }
}
