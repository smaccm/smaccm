package edu.umn.cs.crisys.tb.codegen.eChronos;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;

public class EChronosUtil {

   public static String addPrxMutex(String name) {
      return 
            "<mutex>" + System.lineSeparator() +  
            "  <name>" + name + "</name>" + System.lineSeparator() + 
            "</mutex>" + System.lineSeparator(); 
   }
   
   public static String eChronosPortWriterPrototype(String writerName, Type ty) {
      String toReturn = ""; 
      toReturn += "bool " + writerName;
      if (ty instanceof UnitType) {
         toReturn += "(void); " + System.lineSeparator();
      } else {
         toReturn += "(const " + (EmitterFactory.type(ty)).getCamkesName() + " * arg); " + System.lineSeparator();
      }
      return toReturn;
      
   }
}
