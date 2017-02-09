package edu.umn.cs.crisys.tb.model.rpc;

public class ExternalRemoteProcedureGroup extends RemoteProcedureGroup {

   private String idl4FileName; 
   
   public ExternalRemoteProcedureGroup(String id, String idl4FileName) {
      super(id);
      this.idl4FileName = idl4FileName;
   }

   public String getIdl4FileName() {
      return idl4FileName;
   }

   public void setIdl4FileName(String idl4FileName) {
      this.idl4FileName = idl4FileName;
   }
   
   
}
