package com.rockwellcollins.atc.resolute.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.validation.Check;
import org.osate.aadl2.AadlBoolean;
import org.osate.aadl2.AadlInteger;
import org.osate.aadl2.AadlReal;
import org.osate.aadl2.AadlString;
import org.osate.aadl2.AbstractType;
import org.osate.aadl2.BusType;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ClassifierType;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataType;
import org.osate.aadl2.DeviceType;
import org.osate.aadl2.EnumerationType;
import org.osate.aadl2.MemoryType;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.ProcessType;
import org.osate.aadl2.ProcessorType;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyType;
import org.osate.aadl2.SubprogramGroupType;
import org.osate.aadl2.SubprogramType;
import org.osate.aadl2.SystemImplementation;
import org.osate.aadl2.SystemType;
import org.osate.aadl2.ThreadGroupType;
import org.osate.aadl2.ThreadType;
import org.osate.aadl2.VirtualBusType;
import org.osate.aadl2.VirtualProcessorType;

import com.rockwellcollins.atc.resolute.analysis.external.EvaluateTypeExtention;
import com.rockwellcollins.atc.resolute.analysis.external.ResoluteExternalAnalysisType;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.BoolExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltInFuncCallExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltinType;
import com.rockwellcollins.atc.resolute.resolute.ClaimBody;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.DefinitionBody;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FilterMapExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FuncBody;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.IfThenElseExpr;
import com.rockwellcollins.atc.resolute.resolute.IntExpr;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.RealExpr;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;
import com.rockwellcollins.atc.resolute.resolute.StringExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;
import com.rockwellcollins.atc.resolute.resolute.Type;
import com.rockwellcollins.atc.resolute.resolute.UnaryExpr;

//TODO: How do we handle arithmetic operations of complex types (e.g., Time in ms)?
public class ResoluteJavaValidator extends AbstractResoluteJavaValidator {
    @Override
    protected List<EPackage> getEPackages() {
        // initialize the types for the external analysis calls
        EvaluateTypeExtention.init(Platform.getExtensionRegistry());
        return super.getEPackages();
    }

    @Override
    protected boolean isResponsible(Map<Object, Object> context, EObject eObject) {
        return (eObject.eClass().getEPackage() == ResolutePackage.eINSTANCE);
    }

    /*********** begin expression type checking functions *************/

    @Check
    public void checkProveStatement(ProveStatement prove) {
        Expr proveExpr = prove.getExpr();
        if (proveExpr instanceof FnCallExpr) {
            FnCallExpr fnCallExpr = (FnCallExpr) proveExpr;
            if (fnCallExpr.getFn().getBody() instanceof ClaimBody) {
                return;
            }
        }

        error(prove, "Prove statements must contain a claim");
    }

    @Check
    public void checkThisExpr(ThisExpr thisExpr) {
        Classifier parent = thisExpr.getContainingClassifier();
        if (!(parent instanceof ComponentType) && !(parent instanceof ComponentImplementation)) {
            error(thisExpr, "A 'this' expression can only be used in a "
                    + "resolute subclause (inside of a component or component implementation)");
        }
    }

    @Check
    public void checkIfThenElseExpr(IfThenElseExpr iteExpr) {
        ResoluteType thenType = getExprType(iteExpr.getThen());
        ResoluteType elseType = getExprType(iteExpr.getElse());
        ResoluteType condType = getExprType(iteExpr.getCond());

        if (!condType.subtypeOf(BaseType.BOOL)) {
            error(iteExpr.getCond(), "Expected type bool but found type " + condType);
        }

        if (thenType.join(elseType) == null) {
            error(iteExpr, "Inconsistent branch types " + thenType + ", " + elseType);
        }
    }

    @Check
    public void checkUnaryExpr(UnaryExpr unaryExpr) {
        String op = unaryExpr.getOp();
        ResoluteType type = getExprType(unaryExpr.getExpr());

        switch (op) {
        case "not":
            if (type.subtypeOf(BaseType.BOOL)) {
                return;
            }
            break;

        case "-":
            if (type.subtypeOf(BaseType.INT) && !type.subtypeOf(BaseType.REAL)) {
                return;
            }
            break;

        default:
            error(unaryExpr, "Unknown unary operator");
            return;
        }

        error(unaryExpr, "Operator '" + op + "' not defined on type " + type);
    }

    @Check
    public void checkConstDef(ConstantDefinition consDef) {
        ResoluteType exprType = getExprType(consDef.getExpr());
        ResoluteType defType = typeToResoluteType(consDef.getType());

        if (!exprType.subtypeOf(defType)) {
            error(consDef, "Definition expects type " + defType + " but has type " + exprType);
        }
    }

    @Check
    public void checkFuncDef(FunctionDefinition funcDef) {
        DefinitionBody body = funcDef.getBody();
        if(body == null)
            return; //handled by parse error
        
        ResoluteType exprType = getExprType(body.getExpr());
        ResoluteType defType;

        if (body instanceof FuncBody) {
            FuncBody funcBody = (FuncBody) body;
            defType = typeToResoluteType(funcBody.getType());
        } else {
            defType = BaseType.BOOL;
        }

        if (!exprType.subtypeOf(defType)) {
            error(funcDef, "Definition expects type " + defType + " but has type " + exprType);
        }
    }

    @Check
    public void checkQuantifiedExpr(QuantifiedExpr quantExpr) {
        for (Arg arg : quantExpr.getArgs()) {
            ResoluteType argType = typeToResoluteType(arg.getType());
            if (!argType.subtypeOf(BaseType.AADL)) {
                error(arg, "Can only quantify over AADL types");
            }
        }

        ResoluteType exprType = getExprType(quantExpr.getExpr());
        if (!exprType.subtypeOf(BaseType.BOOL)) {
            error(quantExpr, "Expected type bool but found type " + exprType);
        }
    }

    @Check
    public void checkBinExprCall(BinaryExpr binExpr) {
        String op = binExpr.getOp();
        ResoluteType typeLeft = getExprType(binExpr.getLeft());
        ResoluteType typeRight = getExprType(binExpr.getRight());

        switch (op) {
        case "=>":
        case "or":
        case "and":
            if (typeLeft.subtypeOf(BaseType.BOOL) && typeRight.subtypeOf(BaseType.BOOL)) {
                return;
            }
            break;

        case "+":
        case "-":
        case "*":
        case "/":
        case "<":
        case "<=":
        case ">":
        case ">=":
            if (typeLeft.subtypeOf(BaseType.INT) && typeRight.subtypeOf(BaseType.INT)) {
                return;
            }
            if (typeLeft.subtypeOf(BaseType.REAL) && typeRight.subtypeOf(BaseType.REAL)) {
                return;
            }
            break;

        case "=":
        case "<>":
            if (typeLeft.join(typeRight) != null) {
                return;
            }
            break;

        case "in":
            if (typeRight instanceof SetType) {
                SetType setType = (SetType) typeRight;
                if (typeLeft.join(setType.elementType) != null) {
                    return;
                }
            }
            break;

        case "union":
        case "intersect":
            if (typeLeft instanceof SetType && typeRight instanceof SetType
                    && typeLeft.join(typeRight) != null) {
                return;
            }
            break;

        default:
            error(binExpr, "Unknown binary operator '" + op + "' in type checking");
            return;
        }

        error(binExpr, "Operator '" + op + "' not defined on types " + typeLeft + ", " + typeRight);
    }

    @Check
    public void checkFnCallExpr(FnCallExpr funCall) {
        FunctionDefinition funDef = funCall.getFn();
        EList<Expr> actuals = funCall.getArgs();
        EList<Arg> formals = funDef.getArgs();

        if (funDef.getBody() instanceof ClaimBody && !inClaimContext(funCall)) {
            error(funCall, "A claim cannot appear in this context");
        }

        if (actuals.size() != formals.size()) {
            error(funCall,
                    "Function expects " + formals.size() + " arguments but found " + actuals.size()
                            + " arguments");
            return;
        }

        for (int i = 0; i < actuals.size(); i++) {
            ResoluteType formal = typeToResoluteType(formals.get(i).getType());
            ResoluteType actual = getExprType(actuals.get(i));

            if (!actual.subtypeOf(formal)) {
                error(funCall.getArgs().get(i), "Expected type " + formal + " but found type "
                        + actual);
            }
        }
    }

    /**
     * A claim may only appear positively within a ProveStatment or ClaimBody
     */
    private boolean inClaimContext(EObject obj) {
        EObject context = obj.eContainer();

        if (context instanceof ClaimBody || context instanceof ProveStatement) {
            return true;
        }

        if (context instanceof BinaryExpr) {
            BinaryExpr be = (BinaryExpr) context;
            if (be.getOp().equals("=>")) {
                return be.getRight().equals(obj);
            }
            if (be.getOp().equals("and") || be.getOp().equals("or")) {
                return inClaimContext(be);
            }
        }

        if (context instanceof QuantifiedExpr) {
            QuantifiedExpr qe = (QuantifiedExpr) context;
            if (qe.getExpr().equals(obj)) {
                return inClaimContext(qe);
            }
        }

        return false;
    }

    @Check
    public void checkBuiltInFuncCallExpr(BuiltInFuncCallExpr funCall) {
        EList<Expr> actuals = funCall.getArgs();
        List<ResoluteType> actualTypes = getExprTypes(actuals);
        List<ResoluteType> expectedTypes = new ArrayList<>();

        switch (funCall.getFn()) {
        case "connected":
            expectedTypes.add(BaseType.COMPONENT);
            expectedTypes.add(BaseType.CONNECTION);
            expectedTypes.add(BaseType.COMPONENT);
            break;

        case "property_lookup":
        case "property_exists":
            expectedTypes.add(BaseType.AADL);
            expectedTypes.add(BaseType.PROPERTY);
            break;

        case "class_of":
            expectedTypes.add(BaseType.COMPONENT);
            expectedTypes.add(BaseType.COMPONENT);
            break;

        case "type":
        case "has_type":
            expectedTypes.add(BaseType.AADL);
            break;

        case "bound":
            expectedTypes.add(BaseType.COMPONENT);
            expectedTypes.add(BaseType.COMPONENT);
            break;

        case "contained":
            expectedTypes.add(BaseType.AADL);
            expectedTypes.add(BaseType.COMPONENT);
            break;

        case "conn_source":
        case "conn_dest":
            expectedTypes.add(BaseType.CONNECTION);
            break;

        case "sum":
            if (actuals.size() != 1) {
                error(funCall, "function 'sum' expects one argument");
                return;
            }

            if (actualTypes.get(0) instanceof SetType) {
                SetType setType = (SetType) actualTypes.get(0);
                if (setType.elementType.subtypeOf(BaseType.INT)
                        || setType.elementType.subtypeOf(BaseType.REAL)) {
                    return;
                }
            }

            error(funCall.getArgs().get(0),
                    "function 'sum' not defined on type " + actualTypes.get(0));
            return;

        case "analysis":
            if (actuals.size() < 1) {
                error(funCall, "Function 'analysis' expects at least one argument");
                return;
            }

            Expr expr = actuals.get(0);
            if (!(expr instanceof StringExpr)) {
                error(funCall, "The first argument of 'analysis' must be a literal string");
                return;
            }

            StringExpr analysisStrExpr = (StringExpr) expr;
            String analysisStr = analysisStrExpr.getVal().getValue();
            analysisStr = analysisStr.replace("\"", "");
            ResoluteExternalAnalysisType analysisType = EvaluateTypeExtention.getType(analysisStr);

            if (analysisType == null) {
                error(funCall, "Could not find external analysis '" + analysisStr + "'");
                return;
            }

            List<ResoluteType> argTypes = analysisType.getArgTypes();
            int expectArgSize = argTypes.size();

            if (actuals.size() != expectArgSize + 1) {
                error(funCall, "External analysis '" + analysisStr + "' expects " + expectArgSize
                        + " additional arguments but found " + (actuals.size() - 1)
                        + " additional arguments");
                return;
            }

            for (int i = 0; i < argTypes.size(); i++) {
                ResoluteType actualType = getExprType(actuals.get(i + 1));
                ResoluteType expectedType = argTypes.get(i);

                if (!actualType.subtypeOf(expectedType)) {
                    error(funCall.getArgs().get(i + 1), "Expected type " + expectedType
                            + " but found type " + actualType);
                }
            }

            return;

        default:
            error(funCall, "Unknown builtin function '" + funCall.getFn() + "'");
            return;
        }

        if (actualTypes.size() != expectedTypes.size()) {
            error(funCall, "Function expects " + expectedTypes.size() + " arguments but found "
                    + actualTypes.size() + " arguments");
            return;
        }

        for (int i = 0; i < actualTypes.size(); i++) {
            ResoluteType expected = expectedTypes.get(i);
            ResoluteType actual = getExprType(actuals.get(i));

            if (!actual.subtypeOf(expected)) {
                error(funCall.getArgs().get(i), "Expected type " + expected + " but found type "
                        + actual);
            }
        }
    }

    @Check
    public void checkFilterMapExpr(FilterMapExpr mapExpr) {
        Expr filterExpr = mapExpr.getFilter();
        if (filterExpr != null) {
            ResoluteType validType = getExprType(filterExpr);
            if (!validType.subtypeOf(BaseType.BOOL)) {
                error(mapExpr.getFilter(), "Expected type bool but found type " + validType);
            }
        }
    }

    /************* Begin helper functions ***************/

    private ResoluteType getExprType(Expr expr) {
        if (expr instanceof FilterMapExpr) {
            FilterMapExpr filterMapExpr = (FilterMapExpr) expr;
            return new SetType(getExprType(filterMapExpr.getMap()));
        }

        if (expr instanceof IfThenElseExpr) {
            IfThenElseExpr iteExpr = (IfThenElseExpr) expr;
            return getExprType(iteExpr.getThen());
        }

        if (expr instanceof UnaryExpr) {
            UnaryExpr unaryExpr = (UnaryExpr) expr;
            return getExprType(unaryExpr.getExpr());
        }

        if (expr instanceof ThisExpr) {
            Classifier parent = expr.getContainingClassifier();
            // A 'this' expression should always have a containing
            // classifier that is of component type. This may not
            // be the case if a 'this' statement appears in a ResoluteLibrary
            // rather than a ResoluteSubclause. In this case an error
            // will be thrown by the type checking function for 'this'
            // statements above
            if (parent instanceof ComponentType) {
                ResoluteType type = getComponentType((ComponentType) parent);
                if (type != null) {
                    return type;
                }
            }

            return BaseType.COMPONENT;
        }

        if (expr instanceof FailExpr) {
            return BaseType.FAIL;
        }

        if (expr instanceof BinaryExpr) {
            BinaryExpr binExpr = (BinaryExpr) expr;
            return getBinaryExprType(binExpr);
        }

        if (expr instanceof BoolExpr) {
            return BaseType.BOOL;
        }

        if (expr instanceof QuantifiedExpr) {
            QuantifiedExpr quantExpr = (QuantifiedExpr) expr;
            return getExprType(quantExpr.getExpr());
        }

        if (expr instanceof FnCallExpr) {
            FnCallExpr funCall = (FnCallExpr) expr;
            DefinitionBody body = funCall.getFn().getBody();
            if (body instanceof FuncBody) {
                FuncBody funcBody = (FuncBody) body;
                return typeToResoluteType(funcBody.getType());
            } else {
                return BaseType.BOOL;
            }
        }

        if (expr instanceof IntExpr) {
            return BaseType.INT;
        }

        if (expr instanceof RealExpr) {
            return BaseType.REAL;
        }

        if (expr instanceof StringExpr) {
            return BaseType.STRING;
        }

        if (expr instanceof IdExpr) {
            IdExpr id = (IdExpr) expr;
            return getIdExprType(id);
        }

        if (expr instanceof BuiltInFuncCallExpr) {
            BuiltInFuncCallExpr funCall = (BuiltInFuncCallExpr) expr;
            return getBuiltInFnCallType(funCall);
        }

        error(expr, "Unable to get type for expression");
        return BaseType.FAIL;
    }

    public ResoluteType getBinaryExprType(BinaryExpr binExpr) {
        ResoluteType leftType = getExprType(binExpr.getLeft());
        ResoluteType rightType = getExprType(binExpr.getRight());

        switch (binExpr.getOp()) {
        case "=>":
        case "or":
        case "and":
        case "<":
        case "<=":
        case ">":
        case ">=":
        case "=":
        case "<>":
        case "in":
            return BaseType.BOOL;

        case "+":
        case "-":
        case "*":
        case "/":
            return leftType;

        case "union":
        case "intersect":
            return leftType.join(rightType);
        }

        error(binExpr, "Unable to get type for binary expression");
        return BaseType.FAIL;
    }

    public ResoluteType getIdExprType(IdExpr id) {
        NamedElement idClass = id.getId();

        if (idClass instanceof ComponentClassifier) {
            ComponentClassifier component = (ComponentClassifier) idClass;
            return new BaseType(component.getCategory().toString());
        }

        if (idClass instanceof Arg) {
            Arg arg = (Arg) idClass;
            return typeToResoluteType(arg.getType());
        }

        if (idClass instanceof Property) {
            return BaseType.PROPERTY;
        }

        if (idClass instanceof ConstantDefinition) {
            ConstantDefinition consDef = (ConstantDefinition) idClass;
            return typeToResoluteType(consDef.getType());
        }

        if (idClass instanceof FunctionDefinition) {
            return new BaseType("function");
        }

        error(id, "Unable to get type for id expression");
        return BaseType.FAIL;
    }

    public ResoluteType getBuiltInFnCallType(BuiltInFuncCallExpr funCall) {
        switch (funCall.getFn()) {
        case "connected":
        case "class_of":
        case "bound":
        case "contained":
        case "property_exists":
            return BaseType.BOOL;

        case "property_lookup":
            if (funCall.getArgs().size() != 2) {
                return BaseType.FAIL;
            }

            Expr propExpr = funCall.getArgs().get(1);
            if (!(propExpr instanceof IdExpr)) {
                error(funCall, "Cannot perform property lookup without literal property reference");
                return BaseType.FAIL;
            }

            IdExpr idExpr = (IdExpr) propExpr;
            if (!(idExpr.getId() instanceof Property)) {
                error(funCall, "Cannot perform property lookup without literal property reference");
                return BaseType.FAIL;
            }

            Property prop = (Property) idExpr.getId();
            PropertyType propType = prop.getPropertyType();

            if (propType instanceof AadlBoolean) {
                return BaseType.BOOL;
            }
            if (propType instanceof AadlString || propType instanceof EnumerationType) {
                return BaseType.STRING;
            }
            if (propType instanceof AadlInteger) {
                return BaseType.INT;
            }
            if (propType instanceof AadlReal) {
                return BaseType.REAL;
            }
            if (propType instanceof ClassifierType) {
                return BaseType.COMPONENT;
            }

            break;

        case "type":
            return BaseType.DATA;

        case "has_type":
            return BaseType.BOOL;

        case "conn_source":
        case "conn_dest":
            return BaseType.COMPONENT;

        case "sum":
            if (funCall.getArgs().size() != 1) {
                return BaseType.FAIL;
            }

            Expr setExpr = funCall.getArgs().get(0);
            SetType setType = (SetType) getExprType(setExpr);
            return setType.elementType;

        case "analysis":
            List<Expr> args = funCall.getArgs();

            if (args.isEmpty()) {
                return BaseType.FAIL;
            }

            Expr expr = args.get(0);
            if (!(expr instanceof StringExpr)) {
                return BaseType.FAIL;
            }

            StringExpr analysisStrExpr = (StringExpr) expr;
            String analysisStr = analysisStrExpr.getVal().getValue().replace("\"", "");
            ResoluteExternalAnalysisType analysisType = EvaluateTypeExtention.getType(analysisStr);

            if (analysisType == null) {
                return BaseType.FAIL;
            }
            return analysisType.getType();
        }

        error(funCall, "Unable to get type for function call expression");
        return BaseType.FAIL;
    }

    static private ResoluteType getComponentType(ComponentType comp) {
        if (comp instanceof AbstractType) {
            return BaseType.ABSTRACT;
        } else if (comp instanceof BusType) {
            return BaseType.BUS;
        } else if (comp instanceof DataType) {
            return BaseType.DATA;
        } else if (comp instanceof DeviceType) {
            return BaseType.DEVICE;
        } else if (comp instanceof MemoryType) {
            return BaseType.MEMORY;
        } else if (comp instanceof ProcessorType) {
            return BaseType.PROCESSOR;
        } else if (comp instanceof ProcessType) {
            return BaseType.PROCESS;
        } else if (comp instanceof SubprogramGroupType) {
            return BaseType.SUBPROGRAM_GROUP;
        } else if (comp instanceof SubprogramType) {
            return BaseType.SUBPROGRAM;
        } else if (comp instanceof SystemType) {
            return BaseType.SYSTEM;
        } else if (comp instanceof ThreadGroupType) {
            return BaseType.THREAD_GROUP;
        } else if (comp instanceof ThreadType) {
            return BaseType.THREAD;
        } else if (comp instanceof VirtualBusType) {
            return BaseType.VIRTUAL_BUS;
        } else if (comp instanceof VirtualProcessorType) {
            return BaseType.VIRTUAL_PROCESSOR;
        }

        return null;
    }

    private List<ResoluteType> getExprTypes(List<Expr> exprs) {
        List<ResoluteType> result = new ArrayList<>();
        for (Expr expr : exprs) {
            result.add(getExprType(expr));
        }
        return result;
    }

    public ResoluteType typeToResoluteType(Type type) {
        if (type instanceof BuiltinType) {
            BuiltinType bt = (BuiltinType) type;
            return new BaseType(bt.getName());
        } else if (type instanceof com.rockwellcollins.atc.resolute.resolute.SetType) {
            com.rockwellcollins.atc.resolute.resolute.SetType st = (com.rockwellcollins.atc.resolute.resolute.SetType) type;
            return new SetType(typeToResoluteType(st.getType()));
        } else {
            error(type, "Unable to convert type");
            throw new IllegalArgumentException();
        }
    }
}
