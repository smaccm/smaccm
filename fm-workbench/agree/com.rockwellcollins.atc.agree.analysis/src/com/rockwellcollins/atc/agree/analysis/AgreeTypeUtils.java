package com.rockwellcollins.atc.agree.analysis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.agree.AgreeAADLEnumerationUtils;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.NamedID;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.agree.TypeID;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;

import jkind.lustre.BoolExpr;
import jkind.lustre.EnumType;
import jkind.lustre.Expr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.Type;
import jkind.lustre.VarDecl;

public class AgreeTypeUtils {

	private static final String dotChar = "__";
	private static final Expr initBool = new BoolExpr(false);
	private static final Expr initReal = new RealExpr(BigDecimal.ZERO);
	private static final Expr initInt = new IntExpr(BigInteger.ZERO);

	public static String getTypeName(com.rockwellcollins.atc.agree.agree.Type type) {
		if (type instanceof PrimType) {
			return ((PrimType) type).getString();
		} else {
			return getIDTypeStr(((((RecordType) type).getRecord().getBase())));
		}
	}

	public static String getTypeName(com.rockwellcollins.atc.agree.agree.Type type, Map<NamedElement, String> typeMap,
			Set<Type> typeExpressions) {
		if (type instanceof PrimType) {
			return ((PrimType) type).getString();
		} else {
			return getTypeName(((RecordType) type).getRecord(), typeMap, typeExpressions);
		}
	}

	public static String getTypeName(TypeID recId, Map<NamedElement, String> typeMap, Set<Type> typeExpressions) {
		NamedElement finalId = recId.getBase();
		return getTypeName(finalId, typeMap, typeExpressions);
	}

	private static String getTypeName(NamedElement finalId, Map<NamedElement, String> typeMap,
			Set<Type> typeExpressions) {

		if (finalId == null) {
			return null;
		}

		if (typeMap.containsKey(finalId)) {
			return typeMap.get(finalId);
		}
		recordType(finalId, typeMap, typeExpressions);
		return typeMap.get(finalId);
	}

	public static Type getType(NamedElement finalId, Map<NamedElement, String> typeMap, Set<Type> typeExpressions) {
		String name = getTypeName(finalId, typeMap, typeExpressions);
		if (name == null) {
			return null;
		}
		for (Type type : typeExpressions) {
			if (type.toString().equals(name)) {
				return type;
			}
		}
		return new NamedType(name);
	}

	private static void recordType(NamedElement el, Map<NamedElement, String> typeMap, Set<Type> typeExpressions) {
		Map<String, Type> subTypeMap = new HashMap<>();
		if (el instanceof ComponentImplementation) {
			ComponentImplementation compImpl = (ComponentImplementation) el;
			String typeStr = null;
			Type subType = null;
			if (compImpl.getAllSubcomponents().size() == 0) {
				typeStr = getIDTypeStr(compImpl.getType());
				typeMap.put(el, typeStr);
				return;
			}
			for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
				ComponentImplementation subCompImpl = subComp.getComponentImplementation();
				if (subCompImpl == null) {
					ComponentType subCompType = subComp.getComponentType();
					subType = getType(subCompType, typeMap, typeExpressions);
				} else {
					subType = getType(subCompImpl, typeMap, typeExpressions);
				}
				if (subType != null) {
					subTypeMap.put(subComp.getName(), subType);
				}
			}
		} else if (el instanceof RecordDefExpr) {
			RecordDefExpr agreeRecDef = (RecordDefExpr) el;
			for (Arg arg : agreeRecDef.getArgs()) {

				com.rockwellcollins.atc.agree.agree.Type argType = arg.getType();
				String typeStr = null;
				Type subType = null;
				if (argType instanceof PrimType) {
					typeStr = ((PrimType) argType).getString();
				} else {
					TypeID nestId = ((RecordType) argType).getRecord();
					NamedElement namedEl = (nestId.getBase());
					subType = getType(namedEl, typeMap, typeExpressions);
				}
				if (typeStr != null) {
					subType = getNamedType(typeStr);
				}
				subTypeMap.put(arg.getName(), subType);
			}

		} else if (el instanceof EnumStatement) {
			List<String> vals = new ArrayList<>();
			EnumStatement enumStatement = (EnumStatement) el;
			for (NamedID id : enumStatement.getEnums()) {
				vals.add(id.getName());
			}
			String typeStr = getIDTypeStr(enumStatement);
			typeMap.put(el, typeStr);
			typeExpressions.add(new EnumType(typeStr, vals));
			return;
		} else if (AgreeAADLEnumerationUtils.isAADLEnumeration(el)) {
			String typeStr = getIDTypeStr(el);
			List<String> vals = AgreeAADLEnumerationUtils.getEnumerators((ComponentClassifier) el).stream()
					.map(val -> typeStr.replace("__", "_") + "_" + ((StringLiteral) val).getValue())
					.collect(Collectors.toList());
			typeMap.put(el, typeStr);
			typeExpressions.add(new EnumType(typeStr, vals));
		} else if (el instanceof ComponentType) {
			String typeStr = getIDTypeStr(el);
			typeMap.put(el, typeStr);
			return;
		}

		if (subTypeMap.size() > 0) {
			String typeStr = getIDTypeStr(el);
			typeMap.put(el, typeStr);
			jkind.lustre.RecordType lustreRecord = new jkind.lustre.RecordType(typeStr, subTypeMap);
			typeExpressions.add(lustreRecord);
		}

	}

	public static Expr getInitialType(String typeStr, Set<jkind.lustre.RecordType> typeExpressions) {

		switch (typeStr) {
		case "bool":
			return initBool;
		case "real":
			return initReal;
		case "int":
			return initInt;
		default:
		}

		boolean foundType = false;
		Map<String, Expr> fieldExprs = new HashMap<>();
		for (jkind.lustre.RecordType type : typeExpressions) {
			if (type.id.equals(typeStr)) {
				foundType = true;
				for (Entry<String, Type> field : type.fields.entrySet()) {
					Type fieldType = field.getValue();
					if (!(fieldType instanceof NamedType) && !(fieldType instanceof RecordType)) {
						throw new AgreeException("Unhandled type: '" + fieldType.getClass().getTypeName() + "'");
					}
					Expr fieldExpr = getInitialType(fieldType.toString(), typeExpressions);
					fieldExprs.put(field.getKey(), fieldExpr);
				}
			}
		}
		if (!foundType) {
			throw new AgreeException("Could not find type: '" + typeStr + "'");
		}

		return new RecordExpr(typeStr, fieldExprs);
	}

	public static String getIDTypeStr(NamedElement record) {
		String typeStr = null;
		EObject container = record.eContainer();

		if (record instanceof ComponentType && !AgreeAADLEnumerationUtils.isAADLEnumeration(record)) {
			ComponentType type = (ComponentType) record;
			do {
				String name = type.getQualifiedName();
				switch (name) {
				case "Base_Types::Boolean":
				case "Base_Types::Integer":
				case "Base_Types::Unsigned":
				case "Base_Types::Unsigned_64":
				case "Base_Types::Unsigned_32":
				case "Base_Types::Unsigned_16":
				case "Base_Types::Unsigned_8":
				case "Base_Types::Integer_64":
				case "Base_Types::Integer_32":
				case "Base_Types::Integer_16":
				case "Base_Types::Integer_8":
				case "Base_Types::Float":
				case "Base_Types::Float_32":
				case "Base_Types::Float_64":
					return name.replace("::", "__");
				}
				type = type.getExtended();

			} while (type != null);
			AgreeLogger.logWarning("Reference to component type '" + record.getName()
			+ "' is not among the types reasoned about by AGREE");
			return null;
		} else if (record instanceof ComponentImplementation) {
			typeStr = record.getName();
		} else {
			while (!(container instanceof ComponentClassifier) && !(container instanceof AadlPackage)) {
				container = container.eContainer();
			}
			if (container instanceof ComponentClassifier) {
				ComponentClassifier compClass = (ComponentClassifier) container;
				typeStr = compClass.getName() + AgreeASTBuilder.dotChar + record.getName();
			} else {
				typeStr = record.getName();
			}
		}
		// get the name of the containing package
		while (!(container instanceof AadlPackage)) {
			container = container.eContainer();
		}
		typeStr = ((AadlPackage) container).getName() + AgreeASTBuilder.dotChar + typeStr;
		typeStr = typeStr.replace(".", "__");
		typeStr = typeStr.replace("::", "____");

		return typeStr;
	}

	public static NamedType getNamedType(String name) {
		switch (name) {
		case "bool":
			return NamedType.BOOL;
		case "real":
			return NamedType.REAL;
		case "int":
			return NamedType.INT;
		default:
			return new NamedType(name);
		}
	}

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

	public static List<VarDecl> argsToVarDeclList(EList<Arg> args, Map<NamedElement, String> typeMap,
			Set<Type> typeExpressions) {
		List<VarDecl> varList = new ArrayList<>();
		for (Arg arg : args) {
			Type type = getNamedType(getTypeName(arg.getType(), typeMap, typeExpressions));
			VarDecl varDecl = new VarDecl(arg.getName(), type);
			varList.add(varDecl);
		}

		return varList;
	}

	public static NamedElement namedElFromId(EObject obj, ComponentInstance compInst) {
		if (obj instanceof NestedDotID) {
			return AgreeUtils.getFinalNestId((NestedDotID) obj);
		} else {
			assert (obj instanceof ThisExpr);

			ThisExpr thisExpr = (ThisExpr) obj;

			NestedDotID nestId = thisExpr.getSubThis();

			while (nestId != null) {
				NamedElement base = nestId.getBase();

				if (base instanceof Subcomponent) {
					compInst = compInst.findSubcomponentInstance((Subcomponent) base);
					nestId = nestId.getSub();
				} else if (base instanceof FeatureGroup) {
					assert (base instanceof FeatureGroup);
					FeatureInstance featInst = compInst.findFeatureInstance((Feature) base);

					while (nestId.getSub() != null) {
						nestId = nestId.getSub();
						assert (nestId.getBase() instanceof Feature);
						Feature subFeat = (Feature) nestId.getBase();
						FeatureInstance eqFeatInst = null;
						for (FeatureInstance subFeatInst : featInst.getFeatureInstances()) {
							if (subFeatInst.getFeature().equals(subFeat)) {
								eqFeatInst = subFeatInst;
								break;
							}
						}
						featInst = eqFeatInst;
					}

					return featInst;
				} else {
					assert (base instanceof DataPort);
					return compInst.findFeatureInstance((DataPort) base);
				}

			}
			return compInst;
		}
	}

	public static void addToRenamingAll(Map<String, String> renamings, Map<String, String> varRenaming) {
		for (Entry<String, String> entry : renamings.entrySet()) {
			addToRenaming(entry.getKey(), entry.getValue(), varRenaming);
		}
	}

	public static void addToRenaming(String key, String value, Map<String, String> varRenaming) {
		if (varRenaming.containsValue(value)) {
			// TODO: this could be done another way if its to slow
			for (Entry<String, String> entry : varRenaming.entrySet()) {
				if (entry.getValue().equals(value) && !entry.getKey().equals(key)) {
					throw new AgreeException("There is a name collision with a multiple variables"
							+ ", assumptions, or guarantees named '" + value + "' which will cause "
							+ "issues with reporting results");
				}
			}
		}
		varRenaming.put(key, value);
	}

}
