package edu.umn.cs.crisys.tb.codegen.common;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.codegen.common.names.TypeNames;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.type.ArrayType;
import edu.umn.cs.crisys.tb.model.type.ExternalType;
import edu.umn.cs.crisys.tb.model.type.IdType;
import edu.umn.cs.crisys.tb.model.type.Type;
import edu.umn.cs.crisys.topsort.CyclicException;
import edu.umn.cs.crisys.topsort.TopologicalSort;

public class C_Type_Writer {
	
	public static void writeType(BufferedWriter out, IdType ty, int indent) throws IOException {
		StringBuffer typeName = new StringBuffer();
		typeName.append("typedef ");
		typeName.append(ty.getTypeRef().CTypeDecl(indent, ty.getTypeId()) + "; " + System.lineSeparator());
		out.append(typeName.toString());
	}

	
	public static void writeTypes(BufferedWriter out, Map<String, Type> entryMap, int indent) throws IOException {
    Set<Entry<String, Type>> entrySet = entryMap.entrySet();
    
    try {
      // First we need to get all of the "top-level" types described as
      // ID-types, then we can sort them topologically and emit them.
      List<Type> idTypes = new ArrayList<Type>();
      for (Entry<String, Type> e : entrySet) {
        if (!(e.getValue() instanceof ExternalType)) {
          idTypes.add(new IdType(e.getKey(), e.getValue()));
        }
      }
      List<Type> sortedTypes = TopologicalSort.performElementsOnlyTopologicalSort(idTypes);
      if (sortedTypes.isEmpty()) {
        out.append(System.lineSeparator() + 
                   System.lineSeparator() + 
                   System.lineSeparator() + " // No user defined types.  This space for rent :)" + 
                   System.lineSeparator() + 
                   System.lineSeparator() + 
                   System.lineSeparator()); 
      }
      
      // TODO: MWW HACK!  Create structured type for each array type to support CAmkES
      List<Type> arrayStructs = new ArrayList<Type>(); 
      for (Type t : sortedTypes) {
        IdType ty = (IdType)t; 
        if (ty.getTypeRef() instanceof ArrayType) {
          arrayStructs.add(TypeNames.constructCamkesArrayContainerIdType(ty));
        }
      }
      sortedTypes.addAll(arrayStructs);
      
      for (Type t : sortedTypes) {
         writeType(out, (IdType) t, indent);
         out.append(System.lineSeparator());
      }
    } catch (CyclicException e) {
      throw new TbException(
          "Error writing datatypes to C header: cyclic reference between types.");
    }	  
	}
	
	
	public static void writeTypes(BufferedWriter out, OSModel m, int indent) throws IOException {
	  writeTypes(out, m.getAstTypes(), indent);
	}
	
}
