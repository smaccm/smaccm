package edu.umn.cs.crisys.tb.model.connection;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.port.*;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstance;
import edu.umn.cs.crisys.tb.model.type.Type;


public class PortConnection extends ModelElementBase {
   // increment a counter to maintain globally unique list of connection ids.
   private static int connectionIDCounter = 0;
   private int connectionID = 0;

   private String name;
   private ThreadInstance sourceThreadInstance;
   private ThreadInstance destThreadInstance;
   private OutputPort sourcePort; 
   private InputPort destPort;

   // MWW: Added for ground team to construct mailbox-type dataports
   private boolean useMailbox;

   public enum ConnectionType {
      DATA_CONNECTION, EVENT_CONNECTION
   }

   public static void init() {
      PortConnection.connectionIDCounter = 0;
   }

   public PortConnection(String name,
         ThreadInstance sourceThreadInstance, 
         ThreadInstance destThreadInstance, 
         OutputPort sourcePort, 
         InputPort destPort) {	
      this.name = name;
      this.connectionID = connectionIDCounter;
      this.sourceThreadInstance = sourceThreadInstance;
      this.destThreadInstance = destThreadInstance;
      this.sourcePort = sourcePort;
      this.destPort = destPort;
      this.useMailbox = false;

      PortConnection.connectionIDCounter++;
   }

   public ThreadInstance getSourceThreadInstance() {
      return this.sourceThreadInstance;
   }

   public ThreadInstance getDestThreadInstance() {
      return this.destThreadInstance;
   }

   public OutputPort getSourcePort() {
      return this.sourcePort;
   }

   public InputPort getDestPort() {
      return this.destPort;
   }

   public String getName() {
      return name;
   }

   public int getConnectionID() {
      return connectionID;
   }

   public boolean isMailbox() {
      return useMailbox;
   }

   public void setIsMailbox(boolean val) {
      useMailbox = val;
   }

   @Override
   public ModelElement getParent() {
      return null;
   }
   
   public Type getType() {
     return this.destPort.getType();
   }
}