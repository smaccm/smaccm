package edu.umn.cs.crisys.tb.model;

public interface ModelElement {

   public ModelElement getParent();
   
   public Object getExtendedData(String key); 
   public boolean containsExtendedData(String key);
   public void setExtendedData(String key, Object value); 
}
