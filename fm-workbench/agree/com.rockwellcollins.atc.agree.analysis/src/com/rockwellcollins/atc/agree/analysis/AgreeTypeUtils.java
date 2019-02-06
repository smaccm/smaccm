package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataType;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;

import com.rockwellcollins.atc.agree.AgreeAADLEnumerationUtils;
import com.rockwellcollins.atc.agree.AgreeTypeSystem;
import com.rockwellcollins.atc.agree.AgreeTypeSystem.ArrayDef;
import com.rockwellcollins.atc.agree.agree.ArrayType;
import com.rockwellcollins.atc.agree.agree.ComponentRef;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RecordDef;
import com.rockwellcollins.atc.agree.agree.ThisRef;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;

import jkind.lustre.EnumType;
import jkind.lustre.NamedType;
import jkind.lustre.Type;

public class AgreeTypeUtils {

	private static final String dotChar = "__";

//
	// Agree Types to String
	private static Map<AgreeTypeWrapper, String> agreeTypeToTypeStr;
	private static Map<AgreeTypeWrapper, Type> agreeTypeToLustreType;

	private static String typeToString(com.rockwellcollins.atc.agree.agree.Type type) {
		String typeStr = AgreeTypeSystem.typeToString(type);
		typeStr = typeStr.replace(".", "__");
		typeStr = typeStr.replace("::", "__");
		return typeStr;
	}

	// old code
//	private static final Expr initBool = new BoolExpr(false);
//	private static final Expr initReal = new RealExpr(BigDecimal.ZERO);
//	private static final Expr initInt = new IntExpr(BigInteger.ZERO);
//
//	public static String getTypeName(com.rockwellcollins.atc.agree.agree.Type type) {
//		if (type instanceof PrimType) {
//			return ((PrimType) type).getString();
//		} else {
//			return getIDTypeStr(((((RecordType) type).getRecord().getElm())));
//		}
//
//	}

	private static boolean needsLustreTypeExpression(com.rockwellcollins.atc.agree.agree.Type type) {
		if (!(type instanceof DoubleDotRef)) {
			return false;
		}

		NamedElement el = ((DoubleDotRef) type).getElm();
		if (el instanceof RecordDef || el instanceof EnumStatement) {
			return true;
		}

		if (el instanceof ComponentImplementation && ((ComponentImplementation) el).getAllSubcomponents().size() > 0) {
			return true;
		}

		if (AgreeAADLEnumerationUtils.isAADLEnumeration(el)) {
			return true;
		}

		return (el instanceof ComponentType);
	}

	// old code
//	public static String getTypeName(DoubleDotRef recId, Map<NamedElement, String> typeMap, Set<Type> typeExpressions) {
//		NamedElement finalId = recId.getElm();
//		return getTypeName(finalId, typeMap, typeExpressions);
//
//	}

	private Type buildLustreComponentImplementation(String typeStr, ComponentImplementation compImpl) {
		assert (compImpl.getAllSubcomponents().size() > 0);

		Map<String, Type> subTypeMap = new HashMap<>();
		for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
			ComponentImplementation subCompImpl = subComp.getComponentImplementation();
			com.rockwellcollins.atc.agree.agree.Type agreeType = AgreeTypeSystem.typeFromID(subComp);

			// TODO: figure out when the implementation matters or the component type. Right now I am just grabbing the typeFromID.
			// The below code is how I was doing it previously before I realized it was missing array dimensions when a field is
			// an array.

//			if (subCompImpl != null) {
//				agreeType = AgreeTypeSystem.typeFromID(subCompImpl);
//			} else {
//				ComponentType ct = subComp.getComponentType();
//				agreeType = AgreeTypeSystem.typeFromID(ct);
//			}

			assert (agreeType != AgreeTypeSystem.errorType);

			Type lustreType = getLustreType(agreeType);
			subTypeMap.put(subComp.getName(), lustreType);
		}

		jkind.lustre.RecordType lustreRecord = new jkind.lustre.RecordType(typeStr, subTypeMap);
		return lustreRecord;
	}

	private Type buildLustreAADLEnumeration(String typeStr, NamedElement el) {
		assert (AgreeAADLEnumerationUtils.isAADLEnumeration(el));
		List<String> vals = AgreeAADLEnumerationUtils.getEnumerators((ComponentClassifier) el).stream()
				.map(val -> typeStr.replace("__", "_") + "_" + ((StringLiteral) val).getValue())
				.collect(Collectors.toList());
		jkind.lustre.EnumType lustreType = new EnumType(typeStr, vals);
		return lustreType;
	}

	// EGM: not happy about this code because it is a checked that is repeated in AgreeTypeUtils and other places in the code. Type normalization in needed, but
	// it sadly side-effects on the parse tree so cannot be used. Maybe add this check to NamedType.get()? It makes much more sense there.
	private Type getNamedType(String typeStr) {
		if (typeStr.contains("Boolean")) {
			typeStr = "bool";
		} else if (typeStr.contains("Integer") || typeStr.contains("Natural") || typeStr.contains("Unsinged")) {
			typeStr = "int";
		} else if (typeStr.contains("Real") || typeStr.contains("Float")) {
			typeStr = "real";
		}

		return NamedType.get(typeStr);
	}

	private Type buildLustreComponentType(String typeStr, ComponentType ct) {
		ArrayDef array = (ct instanceof DataType) ? AgreeTypeSystem.arrayDefFromAadl((DataType) ct) : null;
		if (array != null && array.isArray) {
			Type baseType = getLustreType(array.baseType);
			return new jkind.lustre.ArrayType(baseType, array.dimension);
		}

		NamedElement el = ct.getExtended();
		if (el != null) {
			return this.getLustreType(el);
		}

		return getNamedType(typeStr);
	}


	private Type buildLustreType(String typeStr, NamedElement el) {

// old code
//				private static void recordType(NamedElement el, Map<NamedElement, String> typeMap, Set<Type> typeExpressions) {
//			Map<String, Type> subTypeMap = new HashMap<>();
//			if (el instanceof ComponentImplementation) {
//				ComponentImplementation compImpl = (ComponentImplementation) el;
//				String typeStr = null;
//				Type subType = null;
//				if (compImpl.getAllSubcomponents().size() == 0) {
//					typeStr = getIDTypeStr(compImpl.getType());
//					typeMap.put(el, typeStr);
//					return;
//				}
//				for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
//					ComponentImplementation subCompImpl = subComp.getComponentImplementation();
//					if (subCompImpl == null) {
//						ComponentType subCompType = subComp.getComponentType();
//						subType = getType(subCompType, typeMap, typeExpressions);
//					} else {
//						subType = getType(subCompImpl, typeMap, typeExpressions);
//					}
//					if (subType != null) {
//						subTypeMap.put(subComp.getName(), subType);
//					}
//				}
//			} else if (el instanceof RecordDefExpr) {
//				RecordDefExpr agreeRecDef = (RecordDefExpr) el;
//				for (Arg arg : agreeRecDef.getArgs()) {
//
//					com.rockwellcollins.atc.agree.agree.Type argType = arg.getType();
//					String typeStr = null;
//					Type subType = null;
//					if (argType instanceof PrimType) {
//						typeStr = ((PrimType) argType).getString();
//					} else {
//						DoubleDotRef nestId = ((RecordType) argType).getRecord();
//						NamedElement namedEl = (nestId.getElm());
//						subType = getType(namedEl, typeMap, typeExpressions);
//					}
//					if (typeStr != null) {
//						subType = getNamedType(typeStr);
//					}
//					subTypeMap.put(arg.getName(), subType);
//				}
//

		// Order matters: check for AADL enumeration before general DataType
		if (el instanceof ComponentImplementation) {
			return buildLustreComponentImplementation(typeStr, (ComponentImplementation) el);
		} else if (AgreeAADLEnumerationUtils.isAADLEnumeration(el)) {
			return buildLustreAADLEnumeration(typeStr, el);
		} else if (el instanceof ComponentType) {
			return buildLustreComponentType(typeStr, (ComponentType) el);
		}

		// TODO: add in the other types: RecordDef and EnumStatement (see old code below)
		throw new AgreeException("ERROR: '" + typeStr + "' not handled");
	}

	public static int getArraySize(ArrayType array) {
		return Integer.parseInt(array.getSize());
	}

	private Type buildLustreType(com.rockwellcollins.atc.agree.agree.Type agreeType) {
		String typeStr = getTypeString(agreeType);

		if (needsLustreTypeExpression(agreeType)) {
			assert (agreeType instanceof DoubleDotRef);
			NamedElement el = ((DoubleDotRef) agreeType).getElm();
			return buildLustreType(typeStr, el);
		}

		if (agreeType instanceof PrimType) {
			return getNamedType(typeStr);
		}

		if (agreeType instanceof ArrayType) {
			ArrayType arrayType = (ArrayType) agreeType;
			Type baseType = getLustreType(arrayType.getStem());
			int size = getArraySize(arrayType);
			jkind.lustre.ArrayType lustreArrayType = new jkind.lustre.ArrayType(baseType, size);
			return lustreArrayType;
		}

		throw new AgreeException("ERROR: " + typeStr + " unhandled");
	}


	public AgreeTypeUtils() {
		agreeTypeToTypeStr = new HashMap<>();
		agreeTypeToLustreType = new HashMap<>();
	}

	public Type getLustreType(com.rockwellcollins.atc.agree.agree.Type type) {
//		com.rockwellcollins.atc.agree.agree.Type agreeType = AgreeTypeSystem.normalizeType(type);
		com.rockwellcollins.atc.agree.agree.Type agreeType = type;
		AgreeTypeWrapper wrapper = new AgreeTypeWrapper(agreeType);
		Type lustreType = agreeTypeToLustreType.get(wrapper);

		if (lustreType == null) {
			lustreType = buildLustreType(agreeType);
			// old code
//			=======
//					public static String getIDTypeStr(NamedElement record) {
//				String typeStr = null;
//				EObject container = record.eContainer();
//
//				if (record instanceof ComponentType && !AgreeAADLEnumerationUtils.isAADLEnumeration(record)) {
//					ComponentType type = (ComponentType) record;
//					do {
//						String name = type.getQualifiedName();
//						switch (name) {
//						case "Base_Types::Boolean":
//						case "Base_Types::Integer":
//						case "Base_Types::Unsigned":
//						case "Base_Types::Unsigned_64":
//						case "Base_Types::Unsigned_32":
//						case "Base_Types::Unsigned_16":
//						case "Base_Types::Unsigned_8":
//						case "Base_Types::Integer_64":
//						case "Base_Types::Integer_32":
//						case "Base_Types::Integer_16":
//						case "Base_Types::Integer_8":
//						case "Base_Types::Float":
//						case "Base_Types::Float_32":
//						case "Base_Types::Float_64":
//							return name.replace("::", "__");
//						}
//						type = type.getExtended();
//
//					} while (type != null);
//					AgreeLogger.logWarning("Reference to component type '" + record.getName()
//					+ "' is not among the types reasoned about by AGREE");
//					return null;
//				} else if (record instanceof ComponentImplementation) {
//					typeStr = record.getName();
//				} else {
//					while (!(container instanceof ComponentClassifier) && !(container instanceof AadlPackage)) {
//						container = container.eContainer();
//					}
//					if (container instanceof ComponentClassifier) {
//						ComponentClassifier compClass = (ComponentClassifier) container;
//						typeStr = compClass.getName() + AgreeASTBuilder.dotChar + record.getName();
//					} else {
//						typeStr = record.getName();
//					}
//					>>>>>>> origin/develop
		}

		agreeTypeToLustreType.put(wrapper, lustreType);
		return lustreType;
	}

	public Type getLustreType(NamedElement ne) {
		com.rockwellcollins.atc.agree.agree.Type agreeType = AgreeTypeSystem.typeFromID(ne);
		assert (agreeType != AgreeTypeSystem.errorType);
		return getLustreType(agreeType);
	}

	public List<Type> getLustreTypes() {
		List<Type> typeList = new ArrayList<>(agreeTypeToLustreType.values());
		typeList.removeIf(
				type -> ((type instanceof NamedType) && ((NamedType) type).isBuiltin())
				|| (type instanceof jkind.lustre.ArrayType));
		return new ArrayList<>(typeList);
	}

	public String getTypeString(com.rockwellcollins.atc.agree.agree.Type type) {
		/*
		 * EGM: BUG? normalize-type mutates ArrayType by setting it's stem to null.
		 * That is fine unless getTypeString is called from elsewhere, and elsewhere uses the old type.
		 *
		 * com.rockwellcollins.atc.agree.agree.Type agreeType = AgreeTypeSystem.normalizeType(type);
		 */
		com.rockwellcollins.atc.agree.agree.Type agreeType = type;
		AgreeTypeWrapper wrapper = new AgreeTypeWrapper(agreeType);
		String typeStr = agreeTypeToTypeStr.get(wrapper);

		if (typeStr == null) {
			typeStr = AgreeTypeUtils.typeToString(agreeType);
			agreeTypeToTypeStr.put(wrapper, typeStr);
		}

		return typeStr;
	}

	public String getTypeString(NamedElement ne) {
		com.rockwellcollins.atc.agree.agree.Type agreeType = AgreeTypeSystem.typeFromID(ne);
		assert (agreeType != AgreeTypeSystem.errorType);
		return getTypeString(agreeType);
	}

	// UTILITY METHODS STILL USED IN AgreeASTBuilder

	public static String getNodeName(NamedElement nodeDef) {
		EObject container = nodeDef.eContainer();
		List<String> segments = new ArrayList<>();

		segments.add(nodeDef.getName());
		while (container != null) {
			if (container instanceof ComponentClassifier || container instanceof AadlPackage) {
				segments.add(0, ((NamedElement) container).getName().replace(".", AgreeASTBuilder.dotChar));
			}
			container = container.eContainer();
		}

		return String.join(AgreeASTBuilder.dotChar, segments);
	}

	public static String getObjectLocationPrefix(EObject obj) {
		String objPrefix = "";
		EObject container = obj.eContainer();

		while (!(container instanceof AadlPackage)) {
			if (container instanceof ComponentClassifier) {
				objPrefix = ((ComponentClassifier) container).getName();
				objPrefix = dotChar + objPrefix;
				objPrefix = objPrefix.replace(".", dotChar);
			}
			container = container.eContainer();
		}
		objPrefix = ((AadlPackage) container).getName() + objPrefix + dotChar;

		return objPrefix;
	}

	public static NamedElement namedElFromId(ComponentRef obj, ComponentInstance compInst) {
		if (obj instanceof DoubleDotRef) {
			return ((DoubleDotRef) obj).getElm();
		} else if (obj instanceof ThisRef) {
			return compInst;
		}
		return null;
	}
//
//	// OLD CODE FOR MAPPING AGREE TYPES TO LUSTRE TYPES AND STRINGS
//
//	private static String getTypeName(NamedElement finalId, Map<NamedElement, String> typeMap,
//			Set<Type> typeExpressions) {
//
//		if (finalId == null) {
//			return null;
//		}
//
//		if (typeMap.containsKey(finalId)) {
//			return typeMap.get(finalId);
//		}
//		recordType(finalId, typeMap, typeExpressions);
//		return typeMap.get(finalId);
//	}
//
//	public static Type getType(NamedElement finalId, Map<NamedElement, String> typeMap, Set<Type> typeExpressions) {
//		String name = getTypeName(finalId, typeMap, typeExpressions);
//		if (name == null) {
//			return null;
//		}
//		for (Type type : typeExpressions) {
//			if (type.toString().equals(name)) {
//				return type;
//			}
//		}
//		return new NamedType(name);
//	}
//
//	private static void recordType(NamedElement el, Map<NamedElement, String> typeMap, Set<Type> typeExpressions) {
//		Map<String, Type> subTypeMap = new HashMap<>();
//		if (el instanceof ComponentImplementation) {
//			ComponentImplementation compImpl = (ComponentImplementation) el;
//			String typeStr = null;
//			Type subType = null;
//			if (compImpl.getAllSubcomponents().size() == 0) {
//				typeStr = getIDTypeStr(compImpl.getType());
//				typeMap.put(el, typeStr);
//				return;
//			}
//			for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
//				ComponentImplementation subCompImpl = subComp.getComponentImplementation();
//				if (subCompImpl == null) {
//					ComponentType subCompType = subComp.getComponentType();
//					subType = getType(subCompType, typeMap, typeExpressions);
//				} else {
//					subType = getType(subCompImpl, typeMap, typeExpressions);
//				}
//				if (subType != null) {
//					subTypeMap.put(subComp.getName(), subType);
//				}
//			}
//		} else if (el instanceof RecordDef) {
//			RecordDef agreeRecDef = (RecordDef) el;
//			for (Arg arg : agreeRecDef.getArgs()) {
//
//				com.rockwellcollins.atc.agree.agree.Type argType = arg.getType();
//				String typeStr = null;
//				Type subType = null;
//				if (argType instanceof PrimType) {
//					typeStr = ((PrimType) argType).getName();
//				} else {
//					DoubleDotRef nestId = ((DoubleDotRef) argType);
//					NamedElement namedEl = nestId.getNamedElm();
//					subType = getType(namedEl, typeMap, typeExpressions);
//				}
//				if (typeStr != null) {
//					subType = getNamedType(typeStr);
//				}
//				subTypeMap.put(arg.getName(), subType);
//			}
//
//		} else if (el instanceof EnumStatement) {
//			List<String> vals = new ArrayList<>();
//			EnumStatement enumStatement = (EnumStatement) el;
//			for (EnumID id : enumStatement.getEnums()) {
//				vals.add(id.getName());
//			}
//			String typeStr = getIDTypeStr(enumStatement);
//			typeMap.put(el, typeStr);
//			typeExpressions.add(new EnumType(typeStr, vals));
//			return;
//		} else if (AgreeAADLEnumerationUtils.isAADLEnumeration(el)) {
//			String typeStr = getIDTypeStr(el);
//			List<String> vals = AgreeAADLEnumerationUtils.getEnumerators((ComponentClassifier) el).stream()
//					.map(val -> typeStr.replace("__", "_") + "_" + ((StringLiteral) val).getValue())
//					.collect(Collectors.toList());
//			typeMap.put(el, typeStr);
//			typeExpressions.add(new EnumType(typeStr, vals));
//		} else if (el instanceof ComponentType) {
//			String typeStr = getIDTypeStr(el);
//			typeMap.put(el, typeStr);
//			return;
//		}
//
//		if (subTypeMap.size() > 0) {
//			String typeStr = getIDTypeStr(el);
//			typeMap.put(el, typeStr);
//			jkind.lustre.RecordType lustreRecord = new jkind.lustre.RecordType(typeStr, subTypeMap);
//			typeExpressions.add(lustreRecord);
//		}
//
//	}
//
//	public static String getIDTypeStr(NamedElement record) {
//		String typeStr = null;
//		EObject container = record.eContainer();
//
//		if (record instanceof ComponentType && !AgreeAADLEnumerationUtils.isAADLEnumeration(record)) {
//			ComponentType type = (ComponentType) record;
//			do {
//				String name = type.getQualifiedName();
//				switch (name) {
//				case "Base_Types::Boolean":
//				case "Base_Types::Integer":
//				case "Base_Types::Unsigned":
//				case "Base_Types::Unsigned_64":
//				case "Base_Types::Unsigned_32":
//				case "Base_Types::Unsigned_16":
//				case "Base_Types::Unsigned_8":
//				case "Base_Types::Integer_64":
//				case "Base_Types::Integer_32":
//				case "Base_Types::Integer_16":
//				case "Base_Types::Integer_8":
//				case "Base_Types::Float":
//				case "Base_Types::Float_32":
//				case "Base_Types::Float_64":
//					return name.replace("::", "__");
//				}
//				type = type.getExtended();
//
//			} while (type != null);
//			AgreeLogger.logWarning("Reference to component type '" + record.getName()
//			+ "' is not among the types reasoned about by AGREE");
//			return null;
//		} else if (record instanceof ComponentImplementation) {
//			typeStr = record.getName();
//		} else {
//			while (!(container instanceof ComponentClassifier) && !(container instanceof AadlPackage)) {
//				container = container.eContainer();
//			}
//			if (container instanceof ComponentClassifier) {
//				ComponentClassifier compClass = (ComponentClassifier) container;
//				typeStr = compClass.getName() + AgreeASTBuilder.dotChar + record.getName();
//			} else {
//				typeStr = record.getName();
//			}
//		}
//		// get the name of the containing package
//		while (!(container instanceof AadlPackage)) {
//			container = container.eContainer();
//		}
//		typeStr = ((AadlPackage) container).getName() + AgreeASTBuilder.dotChar + typeStr;
//		typeStr = typeStr.replace(".", "__");
//		typeStr = typeStr.replace("::", "____");
//
//		return typeStr;
//	}
//
//	public static NamedType getNamedType(String name) {
//		switch (name) {
//		case "bool":
//			return NamedType.BOOL;
//		case "real":
//			return NamedType.REAL;
//		case "int":
//			return NamedType.INT;
//		default:
//			return new NamedType(name);
//		}
//	}

}
