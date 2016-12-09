package edu.umn.cs.crisys.tb.codegen.VxWorks;

public class VxWorksUtil {

   public static String createMutex() {
      return "semMCreate(SEM_Q_PRIORITY | SEM_INVERSION_SAFE)"; 
   }

   private static String writeMutexDeclInternal(String extern, String name) {
      return extern + "SEM_ID " + name + ";" + System.lineSeparator();
   }
   
   public static String writeMutexDecl(String name) {
      return writeMutexDeclInternal("", name);
   }
   
   public static String writeExternMutexDecl(String name) {
      return writeMutexDeclInternal("extern ", name);
   }
   
   
   

}
