package edu.umn.cs.crisys.smaccm.aadl2rtos;

/*****************************************************
 * AstHelper.
 * 
 * This class should probably go away.  The idea is that
 * we are recording the set of types for the model, but 
 * this is the kind of thing that belongs in the Model class.
 * 
 * What should be stored is a map from strings to structured
 * types.  When we lookup a type, it should return either the 
 * base type or the ID-type associated with the structured
 * type.
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.osate.aadl2.Classifier;
import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.Element;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.impl.DataImplementationImpl;
import org.osate.aadl2.impl.DataPortImpl;
import org.osate.aadl2.impl.DataTypeImpl;
import org.osate.aadl2.impl.ThreadTypeImpl;

import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.ArrayType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.BoolType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.FloatEnum;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.IdType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.IntType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.OpaqueType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.RealType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.RecordType;
import edu.umn.cs.crisys.smaccm.aadl2rtos.ast.Type;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

public class AstHelper {
	private Set<DataClassifier> dataTypes;
	public ArrayList<ThreadTypeImpl> threadTypeImpls;

	// Derived type objects for easier manipulation.
	public Map<String, Type> astTypes = new HashMap<String, Type>();

	public AstHelper(ArrayList<ThreadTypeImpl> threadTypeImpls) {
		dataTypes = new HashSet<DataClassifier>();
		this.threadTypeImpls = new ArrayList<ThreadTypeImpl>();
		this.threadTypeImpls.addAll(threadTypeImpls);
	}

	public Map<String, Type> getAstTypes() {
		return astTypes;
	}

	public void harvestModelData() {
		// Collect thread data types
		for (ThreadTypeImpl t : threadTypeImpls) {
			collectDataTypes(t);
		}

		// create internal ast types from the AADL types
		for (DataClassifier dc : dataTypes) {
			Type t = createAstType(dc);
			if (!t.isBaseType()) {
				astTypes.put(Util.normalizeAadlName(dc), t);
			}
		}
		for (Type t : astTypes.values()) {
			t.init(astTypes);
		}
	}

	// find the data types associated with some "top level" element.
	public void collectDataTypes(Element elem) {
		if (elem instanceof DataPortImpl) {
			DataPortImpl dpi = (DataPortImpl) elem;
			Classifier dpiClass = dpi.getClassifier();

			if (dpiClass instanceof DataTypeImpl || dpiClass instanceof DataImplementationImpl) {
				dataTypes.add((DataClassifier) dpiClass);
			}
		}
		for (Element child : elem.getChildren()) {
			collectDataTypes(child);
		}
	}

	public Type lookupType(DataClassifier dc) {
		String dcName = Util.normalizeAadlName(dc);
		Type ty = createAstType(dc);
		if (astTypes.containsKey(dcName)) {
			return new IdType(dcName);
		} else {
			return ty;
		}
	}

	// what about the recursive aspect of this?
	// So what we want is: for each subcomponent, record the field id and the
	// type name.
	public Type createAstType(DataClassifier dc) {
		if (dc == null) {
			return null;
		}
		String qualifiedName = dc.getQualifiedName();
		String normalizedName = Util.normalizeAadlName(qualifiedName);

		if (astTypes.containsKey(normalizedName)) {
			return astTypes.get(normalizedName);
		}

		// base types defined by the data modeling annex
		if ("Base_Types::Boolean".equals(qualifiedName)) {
			return new BoolType();
		} else if ("Base_Types::Integer_8".equals(qualifiedName)) {
			return new IntType(8, true);
		} else if ("Base_Types::Integer_16".equals(qualifiedName)) {
			return new IntType(16, true);
		} else if ("Base_Types::Integer_32".equals(qualifiedName)) {
			return new IntType(32, true);
		} else if ("Base_Types::Integer_64".equals(qualifiedName)) {
			return new IntType(64, true);
		} else if ("Base_Types::Unsigned_8".equals(qualifiedName)) {
			return new IntType(8, false);
		} else if ("Base_Types::Unsigned_16".equals(qualifiedName)) {
			return new IntType(16, false);
		} else if ("Base_Types::Unsigned_32".equals(qualifiedName)) {
			return new IntType(32, false);
		} else if ("Base_Types::Unsigned_64".equals(qualifiedName)) {
			return new IntType(64, false);
		} else if ("Base_Types::Float_32".equals(qualifiedName)) {
			return new RealType(FloatEnum.SINGLE);
		} else if ("Base_Types::Float_64".equals(qualifiedName)) {
			return new RealType(FloatEnum.DOUBLE);
		} else if ("Base_Types::Character".equals(qualifiedName)) {
			throw new Aadl2RtosException("Character types are currently unsupported");
		} else if ("Base_Types::String" == qualifiedName) {
			throw new Aadl2RtosException("String types are currently unsupported");
		} else if (dc instanceof DataTypeImpl) {
		  DataTypeImpl dti = (DataTypeImpl)dc;
			EnumerationLiteral el = Util.getDataRepresentationName(dti);
			DataClassifier childDc = Util.getBaseType(dti);
		  int size = Util.getDimension(dti);
			Type childElem = createAstType(childDc); 
			if ((el.getName()).equalsIgnoreCase("Array")) {
			  return new ArrayType(childElem, size);
			} else {
			  throw new Aadl2RtosException("Examining type: " + dc.getFullName() + 
			      " found unexpected representation type: '"+ el.getName() + "'; expecting 'Array'.");
			}
		} else if (dc instanceof DataImplementationImpl) {
			RecordType rt = new RecordType();
			DataImplementationImpl dii = (DataImplementationImpl) dc;

			for (DataSubcomponent c : dii.getOwnedDataSubcomponents()) {
				Classifier subClass = c.getClassifier();
				if (subClass instanceof DataClassifier) {
					Type subType = createAstType((DataClassifier) subClass);
					rt.addField(c.getName(), subType);
				} else {
					throw new Aadl2RtosException(
							"In createAstType: Subcomponent is not a data classifier");
				}
			}
			astTypes.put(normalizedName, rt);
			return rt;
		} else {
			throw new Aadl2RtosException(
					"In createAstType: data classifier is not data type or data implementation");
		}
	}
}