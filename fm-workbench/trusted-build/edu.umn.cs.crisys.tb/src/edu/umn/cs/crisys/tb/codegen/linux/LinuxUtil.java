package edu.umn.cs.crisys.tb.codegen.linux;

public class LinuxUtil {

   public static String createMutex() {
      return "semMCreate(SEM_Q_PRIORITY | SEM_INVERSION_SAFE)"; 
   }

   private static String systemHeaders() {
      return "#include <pthread.h> \n" + 
             "#include <semaphore.h> \n";
   }
   
   private static String writeMutexDeclInternal(String extern, String name) {
      return extern + "sem_t " + name + ";\n";
   }
   
   public static String writeMutexDecl(String name) {
      return writeMutexDeclInternal("", name);
   }
   
   public static String writeExternMutexDecl(String name) {
      return writeMutexDeclInternal("extern", name);
   }
   
   
}
