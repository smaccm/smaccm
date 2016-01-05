package com.rockwellcollins.atc.agree.analysis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import jkind.lustre.BoolExpr;
import jkind.lustre.Expr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.Type;
import jkind.lustre.VarDecl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataType;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;

public class AgreeRecordUtils {

    private static final String dotChar = "__";
    private static final Expr initBool = new BoolExpr(false);
    private static final Expr initReal = new RealExpr(BigDecimal.ZERO);
    private static final Expr initInt = new IntExpr(BigInteger.ZERO);

    public static String getRecordTypeName(com.rockwellcollins.atc.agree.agree.Type type) {
        if (type instanceof PrimType) {
            return ((PrimType) type).getString();
        } else {
            return getIDTypeStr((AgreeUtils.getFinalNestId(((RecordType) type).getRecord())));
        }
    }

    public static String getRecordTypeName(com.rockwellcollins.atc.agree.agree.Type type,
            Map<NamedElement, String> typeMap, Set<jkind.lustre.RecordType> typeExpressions) {
        if (type instanceof PrimType) {
            return ((PrimType) type).getString();
        } else {
            return getRecordTypeName(((RecordType) type).getRecord(), typeMap, typeExpressions);
        }
    }

    public static String getRecordTypeName(NestedDotID recId, Map<NamedElement, String> typeMap,
            Set<jkind.lustre.RecordType> typeExpressions) {
        NamedElement finalId = AgreeUtils.getFinalNestId(recId);
        return getRecordTypeName(finalId, typeMap, typeExpressions);
    }

    public static String getRecordTypeName(NamedElement finalId, Map<NamedElement, String> typeMap,
            Set<jkind.lustre.RecordType> typeExpressions) {

        if (typeMap.containsKey(finalId)) {
            return typeMap.get(finalId);
        }
        recordRecType(finalId, typeMap, typeExpressions);
        return typeMap.get(finalId);
    }

    private static void recordRecType(NamedElement el, Map<NamedElement, String> typeMap,
            Set<jkind.lustre.RecordType> typeExpressions) {
        Map<String, Type> subTypeMap = new HashMap<String, Type>();
        if (el instanceof ComponentImplementation) {
            ComponentImplementation compImpl = (ComponentImplementation) el;
            String typeStr = null;
            if (compImpl.getAllSubcomponents().size() == 0) {
                typeStr = getIDTypeStr(compImpl.getType());
                typeMap.put(el, typeStr);
                return;
            }
            for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
                ComponentImplementation subCompImpl = subComp.getComponentImplementation();
                if (subCompImpl == null) {
                    ComponentType subCompType = subComp.getComponentType();
                    typeStr = getRecordTypeName(subCompType, typeMap, typeExpressions);
                } else {
                    typeStr = getRecordTypeName(subCompImpl, typeMap, typeExpressions);
                }
                if (typeStr != null) {
                    subTypeMap.put(subComp.getName(), getNamedType(typeStr));
                }
            }
        } else if (el instanceof RecordDefExpr) {
            RecordDefExpr agreeRecDef = (RecordDefExpr) el;
            for (Arg arg : agreeRecDef.getArgs()) {

                com.rockwellcollins.atc.agree.agree.Type argType = arg.getType();
                String typeStr = null;
                if (argType instanceof PrimType) {
                    typeStr = ((PrimType) argType).getString();
                } else {
                    NestedDotID nestId = ((RecordType) argType).getRecord();
                    NamedElement namedEl = AgreeUtils.getFinalNestId(nestId);
                    typeStr = getRecordTypeName(namedEl, typeMap, typeExpressions);
                }
                subTypeMap.put(arg.getName(), getNamedType(typeStr));
            }

        } else if (el instanceof ComponentType) {
            String typeStr = getIDTypeStr(el);
            typeMap.put(el, typeStr);
            return;
        }
        String typeStr = getIDTypeStr(el);
        typeMap.put(el, typeStr);
        jkind.lustre.RecordType lustreRecord = new jkind.lustre.RecordType(typeStr, subTypeMap);
        // getInitType(lustreRecord);
        typeExpressions.add(lustreRecord);

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
                        throw new AgreeException(
                                "Unhandled type: '" + fieldType.getClass().getTypeName() + "'");
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

        if (record instanceof ComponentType) {
            ComponentType type = (ComponentType) record;
            do {
                String name = type.getQualifiedName();
                switch (name) {
                case "Base_Types::Boolean":
                    return "bool";
                case "Base_Types::Integer":
                case "Base_Types::Unsigned":
                case "Base_Types::Unsigned_32":
                case "Base_Types::Unsigned_16":
                case "Base_Types::Unsigned_8":
                case "Base_Types::Integer_32":
                case "Base_Types::Integer_16":
                case "Base_Types::Integer_8":
                    return "int";
                case "Base_Types::Float":
                    return "real";
                }
                type = (DataType) type.getExtended();

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
        String nodeName = "";
        EObject container = nodeDef.eContainer();

        while (!(container instanceof AadlPackage)) {
            if (container instanceof ComponentClassifier) {
                nodeName = ((ComponentClassifier) container).getName();
                nodeName = dotChar + nodeName;
                nodeName = nodeName.replace(".", dotChar);
            }
            container = container.eContainer();
        }
        nodeName = ((AadlPackage) container).getName() + nodeName + dotChar + nodeDef.getName();

        return nodeName;
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
            Set<jkind.lustre.RecordType> typeExpressions) {
        List<VarDecl> varList = new ArrayList<VarDecl>();
        for (Arg arg : args) {
            Type type = getNamedType(getRecordTypeName(arg.getType(), typeMap, typeExpressions));
            VarDecl varDecl = new VarDecl(arg.getName(), type);
            varList.add(varDecl);
        }

        return varList;
    }

    public static NamedElement namedElFromId(EObject obj, ComponentInstance compInst) {
        if (obj instanceof NestedDotID) {
            return AgreeUtils.getFinalNestId((NestedDotID) obj);
        } else {
            assert(obj instanceof ThisExpr);

            ThisExpr thisExpr = (ThisExpr) obj;

            NestedDotID nestId = thisExpr.getSubThis();

            while (nestId != null) {
                NamedElement base = nestId.getBase();

                if (base instanceof Subcomponent) {
                    compInst = compInst.findSubcomponentInstance((Subcomponent) base);
                    nestId = nestId.getSub();
                } else if (base instanceof FeatureGroup) {
                    assert(base instanceof FeatureGroup);
                    FeatureInstance featInst = compInst.findFeatureInstance((Feature) base);

                    while (nestId.getSub() != null) {
                        nestId = nestId.getSub();
                        assert(nestId.getBase() instanceof Feature);
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
                    assert(base instanceof DataPort);
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
