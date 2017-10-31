package edu.umn.cs.crisys.tb.model.rpc;

import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;

public abstract class RemoteProcedureGroup extends ModelElementBase {
   private String id;

   public RemoteProcedureGroup(String id) {
      this.id = id;
   }
   /**
    * @return the id
    */
   public String getId() {
     return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(String id) {
     this.id = id;
   }

   @Override
   public ModelElement getParent() {
      return null;
   }

   public abstract String getIdl4FileName();
}
