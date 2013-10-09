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
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.Context;
import org.osate.aadl2.DataSubcomponent;
import org.osate.aadl2.DataType;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.ThisExpr;

public class AgreeEmitterUtilities {

    
    static public PropertyExpression getPropExpression(List<ComponentImplementation> modelParents, NamedElement comp, Property prop) {
        
        PropertyExpression expr = null;
        for(ComponentImplementation compImpl : modelParents){
            expr = PropertyUtils.getContainedSimplePropertyValue(compImpl, comp, prop);
            if(expr != null){
                return expr;
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
