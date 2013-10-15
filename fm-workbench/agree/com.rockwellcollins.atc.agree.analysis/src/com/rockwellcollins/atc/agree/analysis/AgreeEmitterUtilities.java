package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.Type;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.VarDecl;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AbstractConnectionEnd;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.ContainedNamedElement;
import org.osate.aadl2.ContainmentPathElement;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataType;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyConstant;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.ThisExpr;

public class AgreeEmitterUtilities {

    
    static public PropertyExpression getPropExpression(List<NamedElement> modelParents, NamedElement comp, Property prop) {
        
        PropertyExpression expr = null;
        for(NamedElement compImpl : modelParents){
            expr = PropertyUtils.getContainedSimplePropertyValue(compImpl, comp, prop);
            if(expr != null){
                return expr;
            }
            if(compImpl instanceof Subcomponent){
                expr = getSimplePropertyValue((Subcomponent)compImpl, comp, prop);
                if(expr != null){
                    return expr;
                }
            }
        }

        try {
            comp.getPropertyValue(prop); // this just checks to see if the
            // property is associated
            expr = PropertyUtils.getSimplePropertyValue(comp, prop);
            return expr;
        } catch (PropertyDoesNotApplyToHolderException propException) {
            return null;
        } catch (PropertyNotPresentException propNotPresentException) {            
            return null;
        }
    }
    
    public static PropertyExpression getSimplePropertyValue(final Subcomponent context,final NamedElement target, final Property pd){
        if (context == null) return target.getNonModalPropertyValue(pd);
        EList<PropertyAssociation> props = context.getOwnedPropertyAssociations();
        for (PropertyAssociation propertyAssociation : props) {
            if (propertyAssociation.getProperty().equals(pd)){
                // we found a property with the corect type
                // now we need to check whether the applies to points to the holder
                EList<ContainedNamedElement> appliestos = propertyAssociation.getAppliesTos();
                for (ContainedNamedElement containedNamedElement : appliestos) {
                    EList<ContainmentPathElement> cpes = containedNamedElement.getContainmentPathElements();
                    NamedElement pathcxt = cpes.get(cpes.size()-1).getNamedElement();
                    if (target.equals(pathcxt)){
                        EList<ModalPropertyValue> vallist = propertyAssociation.getOwnedValues();
                        if (!vallist.isEmpty()){
                            ModalPropertyValue elem = vallist.get(0);
                            PropertyExpression res = elem.getOwnedValue();
                            if (res instanceof NamedValue){
                                AbstractNamedValue nv = ((NamedValue)res).getNamedValue();
                                if (nv instanceof Property){
                                    res = target.getNonModalPropertyValue((Property)nv);
                                } else if (nv instanceof PropertyConstant){
                                    res = ((PropertyConstant)nv).getConstantValue();
                                } 
                            }

                            return res;
                        }

                    }
                }
            }
        }
        return null;
    }


    static public AgreeVarDecl dataTypeToVarType(DataSubcomponent sub) {

        DataType type = (DataType) sub.getAllClassifier();
        AgreeVarDecl newStrType = new AgreeVarDecl();
        newStrType.jKindStr = sub.getName();
        newStrType.aadlStr = sub.getName();

        do {
            String name = type.getQualifiedName();
            switch (name) {
            case "Base_Types::Boolean":
                newStrType.type = "bool";
                return newStrType;
            case "Base_Types::Integer":
                newStrType.type = "int";
                return newStrType;
            case "Base_Types::Float":
                newStrType.type = "real";
                return newStrType;
            }
            type = (DataType) type.getExtended();

        } while (type != null);

        // validation should make sure that this never happens
        assert (false);
        return null;

    }

    static public String dataTypeToVarType(DataType sub) {
        String name = sub.getQualifiedName();

        switch (name) {
        case "Base_Types::Boolean":
            return "bool";
        case "Base_Types::Integer":
            return "int";
        case "Base_Types::Float":
            return "real";
        }

        return null;
    }
    
    
    public String getFnCallExprName(FnCallExpr expr) {
        NestedDotID dotId = expr.getFn();
        NamedElement namedEl = getFinalNestId(dotId);
        return namedEl.getName();
    }

    static public List<VarDecl> argsToVarDeclList(String nameTag, EList<Arg> args) {
        List<VarDecl> varList = new ArrayList<VarDecl>();
        for (Arg arg : args) {
            Type type = new NamedType(arg.getType().getString());
            VarDecl varDecl = new VarDecl(nameTag + arg.getName(), type);
            varList.add(varDecl);
        }

        return varList;
    }

    public static NamedElement getFinalNestId(NestedDotID dotId) {
        while (dotId.getSub() != null) {
            dotId = dotId.getSub();
        }
        return dotId.getBase();
    }

    
    
}
