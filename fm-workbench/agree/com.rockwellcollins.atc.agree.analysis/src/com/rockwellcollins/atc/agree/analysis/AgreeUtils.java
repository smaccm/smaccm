package com.rockwellcollins.atc.agree.analysis;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jkind.lustre.BoolExpr;
import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IntExpr;
import jkind.lustre.NamedType;
import jkind.lustre.RealExpr;
import jkind.lustre.RecordExpr;
import jkind.lustre.RecordType;
import jkind.lustre.Type;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.ContainedNamedElement;
import org.osate.aadl2.ContainmentPathElement;
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
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.SystemInstance;
import org.osate.aadl2.properties.PropertyDoesNotApplyToHolderException;
import org.osate.aadl2.properties.PropertyNotPresentException;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;

public class AgreeUtils {

    static public boolean usingKind2() {
        IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
        String solver = prefs.getString(PreferenceConstants.PREF_MODEL_CHECKER);

        return solver.equals(PreferenceConstants.MODEL_CHECKER_KIND2)
                || solver.equals(PreferenceConstants.MODEL_CHECKER_KIND2WEB);
    }
    
    static public PropertyExpression getPropExpression(NamedElement comp, Property prop) {

        PropertyExpression expr;
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

    // TODO: i'm not sure that this function will work in more complicated cases
    // of property inheritance
    public static PropertyExpression getSimplePropertyValue(final Subcomponent context,
            final NamedElement target, final Property pd) {
        if (context == null)
            return target.getNonModalPropertyValue(pd);
        EList<PropertyAssociation> props = context.getOwnedPropertyAssociations();
        for (PropertyAssociation propertyAssociation : props) {
            if (propertyAssociation.getProperty().equals(pd)) {
                // we found a property with the correct type
                // now we need to check whether the applies to points to the
                // holder
                EList<ContainedNamedElement> appliestos = propertyAssociation.getAppliesTos();
                for (ContainedNamedElement containedNamedElement : appliestos) {
                    EList<ContainmentPathElement> cpes = containedNamedElement.getContainmentPathElements();
                    NamedElement pathcxt = cpes.get(cpes.size() - 1).getNamedElement();
                    if (target.equals(pathcxt)) {
                        EList<ModalPropertyValue> vallist = propertyAssociation.getOwnedValues();
                        if (!vallist.isEmpty()) {
                            ModalPropertyValue elem = vallist.get(0);
                            PropertyExpression res = elem.getOwnedValue();
                            if (res instanceof NamedValue) {
                                AbstractNamedValue nv = ((NamedValue) res).getNamedValue();
                                if (nv instanceof Property) {
                                    res = target.getNonModalPropertyValue((Property) nv);
                                } else if (nv instanceof PropertyConstant) {
                                    res = ((PropertyConstant) nv).getConstantValue();
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
        String varName = sub.getName();

        do {
            String name = type.getQualifiedName();
            switch (name) {
            case "Base_Types::Boolean":
                return new AgreeVarDecl(varName, new NamedType("bool"));
            case "Base_Types::Integer":
            case "Base_Types::Unsigned":
            case "Base_Types::Unsigned_32":
            case "Base_Types::Unsigned_16":
            case "Base_Types::Unsigned_8":
            case "Base_Types::Integer_32":
            case "Base_Types::Integer_16":
            case "Base_Types::Integer_8":
                return new AgreeVarDecl(varName, new NamedType("int"));
            case "Base_Types::Float":
                return new AgreeVarDecl(varName, new NamedType("real"));
            }
            type = (DataType) type.getExtended();

        } while (type != null);

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

    public static NamedElement getFinalNestId(NestedDotID dotId) {
        while (dotId.getSub() != null) {
            dotId = dotId.getSub();
        }
        return dotId.getBase();
    }

    public static ComponentImplementation getInstanceImplementation(ComponentInstance compInst) {
        if (compInst instanceof SystemInstance) {
            ComponentImplementation ci;
            ci = ((SystemInstance) compInst).getComponentImplementation();
            return ci;
        }
        try {
            return (ComponentImplementation) compInst.getComponentClassifier();
        } catch (ClassCastException e) {
            return null;
        }
    }

    public static ComponentType getInstanceType(ComponentInstance compInst) {
        if (compInst instanceof SystemInstance) {

            ComponentType ct;
            ct = ((SystemInstance) compInst).getComponentImplementation().getType();
            return ct;

        }
        try {
            return ((ComponentImplementation) compInst.getComponentClassifier()).getType();
        } catch (ClassCastException e) {
            return (ComponentType) compInst.getComponentClassifier();
        }
    }

    // warns the user if there is a cycle
    static public void logCycleWarning(List<Equation> eqs, AgreeRenaming agreeRename,
            boolean throwException) {
        Map<String, Set<String>> idGraph = new HashMap<>();
        List<String> ids = new ArrayList<>();
        AgreeCycleVisitor visitor = new AgreeCycleVisitor();

        for (Equation eq : eqs) {
            for (IdExpr id : eq.lhs) {
                ids.add(id.id);
                idGraph.put(id.id, eq.expr.accept(visitor));
            }
        }

        Set<String> discovered = new HashSet<>();

        StringBuilder exceptionStr = new StringBuilder();
        for (String id : ids) {
            if (discovered.contains(id)) {
                continue;
            }
            List<String> cycle = cycleWarning_Helper(id, new HashSet<String>(), idGraph);

            if (cycle != null) {
                discovered.addAll(cycle);
                String aadlString = agreeRename.rename(id);
                StringBuilder cycleStr = new StringBuilder("Possible cycle: " + aadlString);

                String sep = " -> ";
                for (String cycleId : cycle) {
                    cycleStr.append(sep);
                    aadlString = agreeRename.rename(cycleId);
                    cycleStr.append(aadlString);
                }

                AgreeLogger.logWarning(cycleStr.toString());
                exceptionStr.append(cycleStr);
            }
        }
        if (throwException && !discovered.isEmpty()) {
            throw new AgreeCombinationalCycleException(exceptionStr.toString());
        }
    }

    static public LinkedList<String> cycleWarning_Helper(String visit, Set<String> visited,
            Map<String, Set<String>> graph) {

        if (visited.contains(visit)) {
            return null;
        }

        visited.add(visit);

        Set<String> toVisit = graph.get(visit);

        if (toVisit != null) {

            LinkedList<String> intersection = new LinkedList<>(toVisit);
            intersection.retainAll(visited);
            if (intersection.size() != 0) {
                String firstLink = intersection.getFirst();
                return new LinkedList<>(Collections.singletonList(firstLink));
            }

            for (String nextVisit : toVisit) {
                LinkedList<String> cycle = cycleWarning_Helper(nextVisit, visited, graph);
                if (cycle != null) {
                    cycle.push(nextVisit);
                    return cycle;
                }
            }
        }
        visited.remove(visit);

        return null;
    }

    public static boolean containsTransitiveAgreeAnnex(ComponentInstance compInst) {
        if (containsAgreeAnnex(compInst.getSubcomponent())) {
            return true;
        }
        EList<ComponentInstance> transitiveSubs = compInst.getAllComponentInstances();
        for (ComponentInstance transInst : transitiveSubs) {
            if (AgreeUtils.containsAgreeAnnex(transInst.getSubcomponent())) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsAgreeAnnex(Subcomponent subComp) {

        ComponentImplementation compImpl = subComp.getComponentImplementation();
        if (compImpl != null) {
            if (AnnexUtil.getAllAnnexSubclauses(compImpl, AgreePackage.eINSTANCE.getAgreeContractSubclause())
                    .size() > 0) {
                return true;
            }
        }

        ComponentType compType = subComp.getComponentType();
        if (compType != null) {
            if (AnnexUtil.getAllAnnexSubclauses(compType, AgreePackage.eINSTANCE.getAgreeContractSubclause())
                    .size() > 0) {
                return true;
            }
        }
        return false;
    }
    
    public static Expr getInitValueFromType(Type type){
        if(type instanceof NamedType){
            return getInitValueFromType((NamedType)type);
        }
        if(type instanceof RecordType){
            RecordType recordType = (RecordType)type;
            Map<String, Expr> fieldMap = new HashMap<>();
            for(Entry<String, Type> entry : recordType.fields.entrySet()){
                Expr subExpr = getInitValueFromType(entry.getValue());
                fieldMap.put(entry.getKey(), subExpr);
            }
            return new RecordExpr(recordType.id, fieldMap);
        }
        throw new AgreeException("AGREE cannot figure out an initial type for Lustre type: "+type.getClass());
    }
    
    private static Expr getInitValueFromType(NamedType type){
        if(type.equals(NamedType.BOOL)){
            return new BoolExpr(false);
        }
        if(type.equals(NamedType.INT)){
            return new IntExpr(BigInteger.ZERO);
        }
        if(type.equals(NamedType.REAL)){
            return new RealExpr(BigDecimal.ZERO);
        }
        throw new AgreeException("Unhandled initial type for type '"+type+"'");
    }
    
    public static boolean statementIsContractEqOrProperty(AgreeStatement statement){
        if (statement.reference instanceof EqStatement
                || statement.reference instanceof PropertyStatement) {
            EObject container = statement.reference.eContainer();
            while (!(container instanceof ComponentClassifier)) {
                container = container.eContainer();
            }
            if (container instanceof ComponentImplementation) {
                return false;
            }
            return true;
        }
        return false;
    }

}
