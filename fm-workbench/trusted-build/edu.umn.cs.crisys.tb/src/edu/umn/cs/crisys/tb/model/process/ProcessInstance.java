package edu.umn.cs.crisys.tb.model.process;

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.instance.ComponentInstance;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.connection.EndpointConnection;
import edu.umn.cs.crisys.tb.model.connection.PortConnection;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstance;

public class ProcessInstance extends ModelElementBase {
   private static int processIdCounter = 0;
   private int processId = 0;

   private ProcessImplementation impl;
   private OSModel model;
   
   private String name; 
   private ArrayList<ThreadInstance> threadInstanceList = new ArrayList<ThreadInstance>();

   public static void init() {
      ProcessInstance.processIdCounter = 0;
   }

   //output ISR port/ ISR connection b/w outputisrport and inputeventport
   public ProcessInstance(String name, ProcessImplementation impl) {
      this.name = name;
      this.impl = impl;
      this.processId = ProcessInstance.processIdCounter++; 
   }

   public ProcessImplementation getProcessImplementation() {
      return this.impl;
   }

   public int getProcessId() {
      return processId;
   }

   public String getName() {
      return this.name;
   }
   
   public String getQualifiedName() {
      return (impl.getName() + "_instance_" + getName() + "_" + getProcessId()).toLowerCase();
   }

   public List<ThreadInstance> getThreadInstanceList() {
      return threadInstanceList;
   }

   @Override
   public ModelElement getParent() {
      // map up to model.
      return this.getModel();
   }
   
   public void addThreadInstance(ThreadInstance ti) {
      this.threadInstanceList.add(ti);
   }

   public OSModel getModel() {
      return model;
   }

   public void setModel(OSModel model) {
      this.model = model;
   }
}
