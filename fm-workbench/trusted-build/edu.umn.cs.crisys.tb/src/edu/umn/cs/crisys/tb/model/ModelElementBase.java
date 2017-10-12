package edu.umn.cs.crisys.tb.model;

import java.util.HashMap;
import java.util.Map;

public abstract class ModelElementBase implements ModelElement {

   Map<String, Object> extendedDataMap = new HashMap<String, Object>();
   public abstract ModelElement getParent();
   
   public Object getExtendedData(String key) {
      return extendedDataMap.get(key);
   }
   public boolean containsExtendedData(String key) {
      return extendedDataMap.containsKey(key);
   }
   public void setExtendedData(String key, Object value) {
      extendedDataMap.put(key, value);
   }
   
   public Map<String, Object> getExtendedDataMap() { return extendedDataMap; }
}
