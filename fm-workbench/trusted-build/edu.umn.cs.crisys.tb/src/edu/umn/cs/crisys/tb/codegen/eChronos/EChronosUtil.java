package edu.umn.cs.crisys.tb.codegen.eChronos;

import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.model.port.InputPort;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;

public class EChronosUtil {

   public static String addPrxMutex(String name) {
      return 
            "<mutex>\n" + 
            "  <name>" + name + "</name>\n" + 
            "</mutex>\n"; 
   }
   
   public static String eChronosPortWriterPrototype(String writerName, Type ty) {
      String toReturn = ""; 
      toReturn += "bool " + writerName;
      if (ty instanceof UnitType) {
         toReturn += "(void); \n";
      } else {
         toReturn += "(const " + (EmitterFactory.type(ty)).getCamkesInputType().getName() + " arg); \n";
      }
      return toReturn;
      
   }
}
