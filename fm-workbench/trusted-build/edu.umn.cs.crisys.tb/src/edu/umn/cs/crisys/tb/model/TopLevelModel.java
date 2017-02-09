package edu.umn.cs.crisys.tb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.instance.SystemInstance;

import edu.umn.cs.crisys.tb.model.rpc.InternalRemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.rpc.RemoteProcedureGroup;
import edu.umn.cs.crisys.tb.model.type.Type;

public class TopLevelModel {

   private SystemImplementation systemImplementation;
   private SystemInstance systemInstance;
   public String outputDirectory;

   private List<OSModel> models = new ArrayList<>();
   private Map<String, RemoteProcedureGroup> remoteProcedureGroupMap = new HashMap<>(); 
   private Map<String, Type> astTypes = new HashMap<>();
   // type stuff
   private Set<String> externalTypeHeaders = new HashSet<>(); 

   
   public TopLevelModel(SystemImplementation systemImplementation, 
         SystemInstance systemInstance) {
      this.systemImplementation = systemImplementation;
      this.systemInstance = systemInstance; 
   }

   public SystemImplementation getSystemImplementation() {
      return systemImplementation;
   }

   public void setSystemImplementation(SystemImplementation systemImplementation) {
      this.systemImplementation = systemImplementation;
   }

   public SystemInstance getSystemInstance() {
      return systemInstance;
   }

   public void setSystemInstance(SystemInstance systemInstance) {
      this.systemInstance = systemInstance;
   }

   public String getOutputDirectory() {
      return outputDirectory;
   }

   public void setOutputDirectory(String outputDirectory) {
      this.outputDirectory = outputDirectory;
   }

   public List<OSModel> getModels() {
      return models;
   }

   public void setModels(List<OSModel> models) {
      this.models = models;
   }
   
   /**
    * @return the rpcInterfaces
    */
   public Map<String, RemoteProcedureGroup> getRemoteProcedureGroupMap() {
      return remoteProcedureGroupMap;
   }

   /**
    * @param rpcInterfaces the rpcInterfaces to set
    */
   public void setRemoteProcedureGroupMap(Map<String, RemoteProcedureGroup> rpcInterfaces) {
      this.remoteProcedureGroupMap = rpcInterfaces;
   }

   public Map<String, Type> getAstTypes() {
      return this.astTypes;
   }

   public Set<String> getExternalTypeHeaders() {
      return externalTypeHeaders;
   }

   public void setExternalTypeHeaders(Set<String> externalTypeHeaders) {
      this.externalTypeHeaders = externalTypeHeaders;
   }

   
}
