package edu.umn.cs.crisys.smaccm.aadl2rtos.codegen.common;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.IdType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.type.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.parse.Model;
import edu.umn.cs.crisys.smaccm.topsort.CyclicException;
import edu.umn.cs.crisys.smaccm.topsort.TopologicalSort;

public class C_Type_Writer {
	
	public static void writeType(BufferedWriter out, IdType ty, int indent) throws IOException {
		// get the underlying type for the id. If it is a structured type,
		// (which I expect) then emit a 'typedef struct'. Else emit a typedef.
		StringBuffer typeName = new StringBuffer();
//		typeName.append("#ifndef " + ty.getTypeId() + "\n");
		typeName.append("typedef ");
		typeName.append(ty.getTypeRef().getCType(indent).varString(ty.getTypeId()) + "; \n");
//		typeName.append("#else \n");
//		typeName.append("#warning Type: " + ty.getTypeId() + " is not being defined by AADL but by an external source. \n");
//		typeName.append("#endif \n \n");
		out.append(typeName.toString());
	}

	public static void writeTypes(BufferedWriter out, Map<String, Type> entryMap, int indent) throws IOException {
    Set<Entry<String, Type>> entrySet = entryMap.entrySet();
    
    try {
      // First we need to get all of the "top-level" types described as
      // ID-types, then
      // we can sort them topologically, then emit them.
      List<Type> idTypes = new ArrayList<Type>();
      for (Entry<String, Type> e : entrySet) {
        idTypes.add(new IdType(e.getKey(), e.getValue()));
      }
      List<Type> sortedTypes = TopologicalSort.performTopologicalSort(idTypes);
      if (sortedTypes.isEmpty()) {
        out.append("\n\n\n // No user defined types.  This space for rent :)\n\n\n"); 
      }
      for (Type t : sortedTypes) {
        if (t instanceof IdType) {
          writeType(out, (IdType) t, indent);
          out.append("\n");
        }
      }
    } catch (CyclicException e) {
      throw new Aadl2RtosException(
          "Error writing datatypes to C header: cyclic reference between types.");
    }	  
	}
	
	
	public static void writeTypes(BufferedWriter out, Model m, int indent) throws IOException {
	  writeTypes(out, m.getAstTypes(), indent);
	}
	
}
