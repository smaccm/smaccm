package edu.umn.cs.crisys.tb.codegen.common.emitters.Port.RPC;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.TbFailure;
import edu.umn.cs.crisys.tb.codegen.common.emitters.EmitterFactory;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitter;
import edu.umn.cs.crisys.tb.codegen.common.emitters.Port.PortListEmitterCamkes;
import edu.umn.cs.crisys.tb.codegen.common.names.ModelNames;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.port.InitializerPort;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;
import edu.umn.cs.crisys.tb.model.port.PortFeature;
import edu.umn.cs.crisys.tb.model.process.ProcessInstance;
import edu.umn.cs.crisys.tb.model.thread.ThreadImplementation;
import edu.umn.cs.crisys.tb.model.thread.ThreadInstance;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.tb.model.type.UnitType;
import edu.umn.cs.crisys.tb.util.Util;

/*****************************************************************
 * 
 * @author whalen
 *
 * This class is just an example of a PortListEmitter; it illustrates
 * how you could write one to do something interesting (say, write 
 * a C file based on all ports that are relevant to a particular 
 * emitter).
 *
 */

public class PortListEmitterRPC implements PortListEmitter, PortListEmitterCamkes {

   // probably a better way to do this with a lambda and 'filter'.
   private List<PortEmitterRPC> relevantPorts(List<PortFeature> pl) {
      List<PortEmitterRPC> rpcs = new ArrayList<>(); 
      for (PortFeature pf: pl) {
         PortEmitter pe = EmitterFactory.port(pf);
         if (pe instanceof PortEmitterRPC) {
            rpcs.add((PortEmitterRPC)pe); 
         }
      }
      return rpcs;
   }

   public Set<Type> relevantPortTypes(List<PortFeature> pl) {
      Set<Type> types = new HashSet<>(); 
      for (PortEmitterRPC perpc : relevantPorts(pl)) {
         types.add(perpc.getModelElement().getType());
      }
      // types.add(new UnitType());

      return types;
   }
 
   
   public ST getIdlTemplateST(String stName) {
      STGroupFile template = Util.createTemplate("CamkesIdl4ReaderWriter.stg");
      return template.getInstanceOf(stName); 
   }


   private void writeIdl4File(File directory, OSModel mdl, Type t) {

      // construct template arguments
      TypeNames tn = EmitterFactory.type(t); 
      ModelNames mn = EmitterFactory.model(mdl);
      String name = tn.getReaderWriterInterfaceName();
      String date = Util.getDate();  

      File genericFile = new File(directory, tn.getReaderWriterIdlFileName()); 
      String path = genericFile.getAbsolutePath();

      // construct template
      ST st = getIdlTemplateST("idlBody");
      st.add("model", mn);
      st.add("type", tn);
      st.add("name", name);
      st.add("date", date);
      st.add("path", path);

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(genericFile))) {
         writer.append(st.render() + System.lineSeparator());
      } catch (IOException e) {
         throw new TbException("IO Exception occurred when creating file: " + path);
      }
   }

   @Override
   // generate any .idl4 files associated with the RPC ports 
   public void camkesGenerateModelIdl(File directory, OSModel mdl, List<PortFeature> pl) {

      // Find set of unique types associated with all RPC ports
      Set<Type> types = relevantPortTypes(pl); 

      // Create the reader/writer interfaces for these types.
      for (Type t: types) {
         writeIdl4File(directory, mdl, t); 
      }
   }


   @Override
   public String camkesAddComponentLevelDeclarations(ThreadImplementation ti, List<PortFeature> pl) {
      String result = ""; 
      STGroupFile template = Util.createTemplate("CamkesComponentCamkes.stg");
      for (Type t: relevantPortTypes(pl)) {
         ST st = template.getInstanceOf("importReaderWriterIdl"); 
         st.add("type", EmitterFactory.type(t));
         result += st.render() + System.lineSeparator(); 
      }
      return result;
   }

   @Override
   public String camkesAddAssemblyIdlImports(OSModel model, List<PortFeature> pl) {
      return "";
   }

   @Override
   public void writeCommonCFiles(OSModel model, File directory, List<PortFeature> pl) {
      // No-op for RPC ports
   }

   @Override
   public void writeCommonHFiles(OSModel model, File directory, List<PortFeature> pl) {
      // No-op for RPC ports

   }

   @Override
   public void writeProcessCFiles(ProcessInstance pi, File directory, List<PortFeature> pl) {
      // No-op for RPC ports
   }

   @Override
   public void writeProcessHFiles(ProcessInstance pi, File directory, List<PortFeature> pl) {
      // No-op for RPC ports
   }

   @Override
   // This is just an example file to show how you might emit a file if there are
   // any "relevant ports" for the thread that is being analyzed.
   
   public void writeThreadCFiles(ThreadInstance ti, File directory, List<PortFeature> pl) {
      List<PortEmitterRPC> relevantPorts = this.relevantPorts(pl);
      if (!relevantPorts.isEmpty()) {
         File genericFile = new File(directory, ti.getName() + "_dummy.txt"); 
         String path = genericFile.getAbsolutePath(); 
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(genericFile))) {
            writer.append("This space for rent!  This is an example of how you might generate code specific to a thread for a set of ports." + System.lineSeparator());
         } catch (IOException e) {
            throw new TbException("IO Exception occurred when creating file: " + path);
         }
      }
      
   }

   @Override
   public void writeThreadHFiles(ThreadInstance ti, File directory, List<PortFeature> pl) {

   }

   @Override
   public String writeThreadCIncludes(ThreadInstance ti, List<PortFeature> pl) {
      return "";
   }

   @Override
   public String writeThreadHPrototypes(ThreadInstance ti, List<PortFeature> pl) {
      return "";
   }


}
