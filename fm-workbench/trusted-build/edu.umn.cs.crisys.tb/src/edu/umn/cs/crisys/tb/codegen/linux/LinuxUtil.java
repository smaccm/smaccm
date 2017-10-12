package edu.umn.cs.crisys.tb.codegen.linux;

import edu.umn.cs.crisys.tb.model.type.ExternalType;
import edu.umn.cs.crisys.tb.model.type.Type;

public class LinuxUtil {

   private static String systemHeaders() {
      return "#include \"tb_linux_support.h\" " + System.lineSeparator();
   }
   
   public static final String mutexTypeName = "TB_MUTEX_TYPE";
   public static final String semTypeName = "TB_SEM_TYPE";
   
   public static final Type mutexType = 
         new ExternalType(mutexTypeName, "<pthread.h>");
   
   public static final Type semType = 
         new ExternalType(semTypeName, "<semaphore.h>");
   
   private static String writeMutexDeclInternal(String extern, String name) {
      return extern + mutexTypeName + " " + name + ";" + System.lineSeparator();
   }
   
   
   public static String writeMutexDecl(String name) {
      return writeMutexDeclInternal("", name);
   }
   
   public static String writeExternMutexDecl(String name) {
      return writeMutexDeclInternal("extern ", name);
   }

   
   public static String createInterprocMutex(String name) {
      return "tb_interproc_mutex_create(&" + name + "); " + System.lineSeparator();
   }
   
   public static String createIntraprocMutex(String name) {
      return "tb_intraproc_mutex_create(&" + name + "); " + System.lineSeparator();
   }
   
   public static String deleteMutex(String name) {
      return "tb_mutex_destroy(&" + name + "); " + System.lineSeparator();
   }
   
   public static String lockMutex(String name) {
      return "tb_mutex_lock(&" + name + "); " + System.lineSeparator();
   }
   
   public static String unlockMutex(String name) {
      return "tb_mutex_unlock(&" + name + "); " + System.lineSeparator();
   }
   
   public static String postSem(String name) {
      return "tb_sem_post(&" + name + ");";
   }
   
   public static String waitSem(String name) {
      return "tb_sem_wait(&" + name + ");";
   }
   
   public static String writeSemDeclInternal(String extern, String name) {
      return extern + "sem_t " + name + ";" + System.lineSeparator();
   }
   
}
