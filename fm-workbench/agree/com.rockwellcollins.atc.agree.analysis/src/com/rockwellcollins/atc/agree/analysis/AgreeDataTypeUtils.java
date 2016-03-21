package com.rockwellcollins.atc.agree.analysis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import jkind.lustre.ArrayType;
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
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ClassifierValue;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataType;
import org.osate.aadl2.Feature;
import org.osate.aadl2.FeatureGroup;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.properties.PropertyNotPresentException;

import com.rockwellcollins.atc.agree.AgreeAADLPropertyUtils;
import com.rockwellcollins.atc.agree.agree.AgreeDataType;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.ThisExpr;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.validation.AgreeArrayType;

public class AgreeDataTypeUtils {

    private static final String dotChar = "__";
    private static final Expr initBool = new BoolExpr(false);
    private static final Expr initReal = new RealExpr(BigDecimal.ZERO);
    private static final Expr initInt = new IntExpr(BigInteger.ZERO);

    private static Type primTypeToLustreType(PrimType type){
        String name = ((PrimType) type).getString();
        switch (name){
        case "int": return NamedType.INT;
        case "real": return NamedType.REAL;
        case "bool": return NamedType.BOOL;
        }
        throw new AgreeException("Unhandled primative type '"+name+"'");
    }
    public static Type getLustreTypeName(com.rockwellcollins.atc.agree.agree.Type type,
            Map<NamedElement, Type> typeMap, Set<jkind.lustre.RecordType> typeExpressions) {
        if (type instanceof PrimType) {
            return primTypeToLustreType((PrimType) type);
        } else {
            return getLustreTypeName(((AgreeDataType) type).getData(), typeMap, typeExpressions);
        }
    }

    public static Type getLustreTypeName(NestedDotID recId, Map<NamedElement, Type> typeMap,
            Set<jkind.lustre.RecordType> typeExpressions) {
        NamedElement finalId = AgreeUtils.getFinalNestId(recId);
        return getLustreTypeName(finalId, typeMap, typeExpressions);
    }

    public static Type getLustreTypeName(NamedElement finalId, Map<NamedElement, Type> typeMap,
            Set<jkind.lustre.RecordType> typeExpressions) {

        if (typeMap.containsKey(finalId)) {
            return typeMap.get(finalId);
        }
        recordType(finalId, typeMap, typeExpressions);
        return typeMap.get(finalId);
    }

    private static void recordType(NamedElement el, Map<NamedElement, Type> typeMap,
            Set<jkind.lustre.RecordType> typeExpressions) {
        Map<String, Type> subTypeMap = new HashMap<String, Type>();
        if (el instanceof ComponentClassifier) {
            Type type = null;
            if (el instanceof ComponentImplementation) {
                ComponentImplementation compImpl = (ComponentImplementation) el;
                if (compImpl.getAllSubcomponents().size() == 0) {
                    //this is either an array type or a native type
                    type = getIDType(compImpl.getType());
                    if(type == null){
                        type = getIDType(compImpl);
                    }
                } else {
                    for (Subcomponent subComp : compImpl.getAllSubcomponents()) {
                        ComponentClassifier subCompClass = subComp.getComponentImplementation();
                        if (subCompClass == null) {
                            subCompClass = subComp.getComponentType();
                        }
                        Type subType = getLustreTypeName(subCompClass, typeMap, typeExpressions);
                        if (subType != null) {
                            //type string will be null if it is a type that AGREE does not reason about
                            subTypeMap.put(subComp.getName(), subType);
                        }
                    }
                    type = getIDType(el);
                    jkind.lustre.RecordType lustreRecord = new jkind.lustre.RecordType(type.toString(), subTypeMap);
                    typeExpressions.add(lustreRecord);
                }
            }else{
                type = getIDType(el);
            }
            typeMap.put(el, type);
        } else if (el instanceof RecordDefExpr) {
            RecordDefExpr agreeRecDef = (RecordDefExpr) el;
            for (Arg arg : agreeRecDef.getArgs()) {

                com.rockwellcollins.atc.agree.agree.Type argType = arg.getType();
                Type type = null;
                if (argType instanceof PrimType) {
                    type = primTypeToLustreType((PrimType) argType);
                } else {
                    NestedDotID nestId = ((AgreeDataType) argType).getData();
                    NamedElement namedEl = AgreeUtils.getFinalNestId(nestId);
                    type = getLustreTypeName(namedEl, typeMap, typeExpressions);
                }
                subTypeMap.put(arg.getName(), type);
            }

            Type type = getIDType(el);
            typeMap.put(el, type);
            jkind.lustre.RecordType lustreRecord = new jkind.lustre.RecordType(type.toString(), subTypeMap);
            // getInitType(lustreRecord);
            typeExpressions.add(lustreRecord);
            
        }
    }

   
    public static Type getIDType(NamedElement el) {
        String typeStr = null;
        EObject container = el.eContainer();
        
        Type arrayType = getArrayType(el);
        
        if(arrayType != null){
            return arrayType;
        }

        if (el instanceof ComponentType) {
            ComponentType type = (ComponentType) el;
            do {
                String name = type.getQualifiedName();
                switch (name) {
                case "Base_Types::Boolean":
                    return NamedType.BOOL;
                case "Base_Types::Integer":
                case "Base_Types::Unsigned":
                case "Base_Types::Unsigned_32":
                case "Base_Types::Unsigned_16":
                case "Base_Types::Unsigned_8":
                case "Base_Types::Integer_32":
                case "Base_Types::Integer_16":
                case "Base_Types::Integer_8":
                    return NamedType.INT;
                case "Base_Types::Float":
                    return NamedType.REAL;
                }
                type = (DataType) type.getExtended();

            } while (type != null);
            AgreeLogger.logWarning("Reference to component type '" + el.getName()
                    + "' is not among the types reasoned about by AGREE");
            return null;
        } else if (el instanceof ComponentImplementation) {
            typeStr = el.getName();
        } else {
            while (!(container instanceof ComponentClassifier) && !(container instanceof AadlPackage)) {
                container = container.eContainer();
            }
            if (container instanceof ComponentClassifier) {
                ComponentClassifier compClass = (ComponentClassifier) container;
                typeStr = compClass.getName() + AgreeASTBuilder.dotChar + el.getName();
            } else {
                typeStr = el.getName();
            }
        }
        // get the name of the containing package
        while (!(container instanceof AadlPackage)) {
            container = container.eContainer();
        }
        typeStr = ((AadlPackage) container).getName() + AgreeASTBuilder.dotChar + typeStr;
        typeStr = typeStr.replace(".", "__");
        typeStr = typeStr.replace("::", "____");

        return new NamedType(typeStr);
    }

    private static Type getArrayType(NamedElement el) {
        String dataRep = null;
        Classifier type = null;
        List<PropertyExpression> propDimensions = null;
        List<Long> dimensions = new ArrayList<>();
        try {
            if (el != null) {
                dataRep = AgreeAADLPropertyUtils.getPropertyEnumString(el, "Data_Model::Data_Representation");
                if (dataRep.equals("Array")) {
                    PropertyExpression typeExpr = AgreeAADLPropertyUtils.getPropertyList(el, "Data_Model::Base_Type").get(0);
                    type = ((ClassifierValue) typeExpr).getClassifier();
                    propDimensions = AgreeAADLPropertyUtils.getPropertyList(el, "Data_Model::Dimension");

                    for(PropertyExpression propExpr : propDimensions){
                        dimensions.add(((IntegerLiteral)propExpr).getValue());
                    }
                    // reverse array dimensions for Lustre arrays
                    Collections.reverse(dimensions);
                    return getArrayType(getIDType(type), dimensions);
                }
            }
        } catch (Throwable e) {
        }
        ;
        return null;
    }
    
    private static Type getArrayType(Type type, List<Long> dimensions) {
        for(long n : dimensions){
            type = new ArrayType(type, (int)n);
        }
        return type;
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
