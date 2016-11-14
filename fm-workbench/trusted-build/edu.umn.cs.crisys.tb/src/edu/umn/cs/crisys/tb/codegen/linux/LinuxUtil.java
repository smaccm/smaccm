package edu.umn.cs.crisys.tb.codegen.linux;

import edu.umn.cs.crisys.tb.model.type.ExternalType;
import edu.umn.cs.crisys.tb.model.type.Type;

public class LinuxUtil {

   private static String systemHeaders() {
      return "#include \"tb_linux_support.h\" \n";
   }
   
   public static final String mutexTypeName = "pthread_mutex_t";
   public static final String semTypeName = "sem_t";
   
   public static final Type mutexType = 
         new ExternalType(mutexTypeName, "<pthread.h>");
   
   public static final Type semType = 
         new ExternalType(semTypeName, "<semaphore.h>");
   
   private static String writeMutexDeclInternal(String extern, String name) {
      return extern + mutexTypeName + name + ";\n";
   }
   
   
   public static String writeMutexDecl(String name) {
      return writeMutexDeclInternal("", name);
   }
   
   public static String writeExternMutexDecl(String name) {
      return writeMutexDeclInternal("extern ", name);
   }

   
   public static String createMutex(String name) {
      return "tb_mutex_create(&" + name + "); \n";
   }
   
   public static String deleteMutex(String name) {
      return "tb_mutex_destroy(&" + name + "); \n";
   }
   
   public static String lockMutex(String name) {
      return "tb_mutex_lock(&" + name + "); \n";
   }
   
   public static String unlockMutex(String name) {
      return "tb_umtex_unlock(&" + name + "); \n";
   }
   
   public static String postSem(String name) {
      return "tb_sem_post(&" + name + ");";
   }
   
   public static String waitSem(String name) {
      return "tb_sem_wait(&" + name + ");";
   }
   
   public static String writeSemDeclInternal(String extern, String name) {
      return extern + "sem_t " + name + ";\n";
   }
   
}
