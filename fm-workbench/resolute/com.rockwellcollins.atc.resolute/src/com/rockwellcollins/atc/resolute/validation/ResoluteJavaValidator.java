package com.rockwellcollins.atc.resolute.validation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.EcoreUtil2;
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
import org.osate.aadl2.PropertyConstant;
import org.osate.aadl2.PropertyType;
import org.osate.aadl2.RangeType;
import org.osate.aadl2.RecordType;
import org.osate.aadl2.ReferenceType;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SubprogramGroupType;
import org.osate.aadl2.SubprogramType;
import org.osate.aadl2.SystemType;
import org.osate.aadl2.ThreadGroupType;
import org.osate.aadl2.ThreadType;
import org.osate.aadl2.VirtualBusType;
import org.osate.aadl2.VirtualProcessorType;

import com.rockwellcollins.atc.resolute.analysis.external.EvaluateLibraryTypeExtension;
import com.rockwellcollins.atc.resolute.analysis.external.EvaluateTypeExtention;
import com.rockwellcollins.atc.resolute.analysis.external.ResoluteExternalAnalysisType;
import com.rockwellcollins.atc.resolute.analysis.external.ResoluteExternalFunctionLibraryType;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.BinaryExpr;
import com.rockwellcollins.atc.resolute.resolute.BoolExpr;
import com.rockwellcollins.atc.resolute.resolute.BuiltInFnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.CastExpr;
import com.rockwellcollins.atc.resolute.resolute.ClaimBody;
import com.rockwellcollins.atc.resolute.resolute.ConstantDefinition;
import com.rockwellcollins.atc.resolute.resolute.DefinitionBody;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.FunctionBody;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.IfThenElseExpr;
import com.rockwellcollins.atc.resolute.resolute.InstanceOfExpr;
import com.rockwellcollins.atc.resolute.resolute.IntExpr;
import com.rockwellcollins.atc.resolute.resolute.LetBinding;
import com.rockwellcollins.atc.resolute.resolute.LetExpr;
import com.rockwellcollins.atc.resolute.resolute.LibraryFnCallExpr;
import com.rockwellcollins.atc.resolute.resolute.LibraryFnType;
import com.rockwellcollins.atc.resolute.resolute.ListExpr;
import com.rockwellcollins.atc.resolute.resolute.ListFilterMapExpr;
import com.rockwellcollins.atc.resolute.resolute.NestedDotID;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.QuantArg;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.RealExpr;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;
import com.rockwellcollins.atc.resolute.resolute.SetExpr;
import com.rockwellcollins.atc.resolute.resolute.SetFilterMapExpr;
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
		// initialize the types for the external function libraries
		EvaluateLibraryTypeExtension.init(Platform.getExtensionRegistry());
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
			if (fnCallExpr.getFn().eIsProxy()) {
				error(prove, "Could not find claim function");
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

		NestedDotID subThis = thisExpr.getSub();

		if (subThis != null) {
			while (subThis.getSub() != null) {
				subThis = subThis.getSub();
			}
			if (!(subThis.getBase() instanceof Subcomponent)) {
				error(thisExpr, "ID '" + subThis.getBase().getName() + "' is not a subcomponent");
			}

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
			error(consDef.getType(), "Definition expects type " + defType + " but has type " + exprType);
		}
	}

	@Check
	public void checkFuncDef(FunctionDefinition funcDef) {
		DefinitionBody body = funcDef.getBody();
		if (body == null) {
			return; // handled by parse error
		}

		ResoluteType exprType = getExprType(body.getExpr());

		if (body instanceof FunctionBody) {
			FunctionBody funcBody = (FunctionBody) body;
			ResoluteType defType = typeToResoluteType(funcBody.getType());
			if (!defType.subtypeOf(exprType)) {
				error(funcBody.getType(), "Function expects type " + defType + " but has type " + exprType);
			}
		} else {
			if (!exprType.subtypeOf(BaseType.BOOL)) {
				error(funcDef, "Claim must have type bool, but has type " + exprType);
			}
		}

	}

	@Check
	public void checkQuantArg(QuantArg quantArg) {
		// The definition of a quantifier arg expression must not reference
		// the quantified arg being defined.
		for (IdExpr idExpr : EcoreUtil2.getAllContentsOfType(quantArg.getExpr(), IdExpr.class)) {
			if (quantArg.equals(idExpr.getId())) {
				error(quantArg, "Quantifier argument '" + idExpr.getId().getName()
						+ "' cannot be referenced in its own definition.");
			}
		}
	}

	@Check
	public void checkQuantifiedExpr(QuantifiedExpr quantExpr) {
		ResoluteType exprType = getExprType(quantExpr.getExpr());
		if (!exprType.subtypeOf(BaseType.BOOL)) {
			error(quantExpr, "Expected type bool but found type " + exprType);
		}

		for (Arg arg : quantExpr.getArgs()) {
			if (arg instanceof QuantArg) {
				QuantArg qArg = (QuantArg) arg;
				ResoluteType argType = getExprType(qArg.getExpr());
				if (argType.equals(BaseType.FAIL)) {
					return;
				}

				if (!(argType instanceof SetType)) {
					error(quantExpr, "Arguments to quantifier is of type '" + argType + "' but must be of a set type");
				}
			} else {
				ResoluteType argType = typeToResoluteType(arg.getType());
				if (!argType.subtypeOf(BaseType.AADL)) {
					error(arg, "Can only quantify over AADL types");
				}
			}
		}
	}

	@Check
	public void checkLetExpr(LetExpr letExpr) {
		ResoluteType exprType;
		Type letType;
		exprType = getExprType(letExpr.getBinding().getExpr());
		letType = letExpr.getBinding().getType();
		ResoluteType resLetType = typeToResoluteType(letType);
		if (!exprType.subtypeOf(resLetType)) {
			error("types mismatch in let expression for variable '" + letExpr.getBinding().getName() + "'. "
					+ "The binding is of type '" + resLetType + "' but the expression is of type '" + exprType + "'",
					letExpr, ResolutePackage.Literals.LET_EXPR__BINDING);
		}

		// System.out.println("binding=" + letExpr.getBinding());
		//
		// System.out.println("binding=" + letExpr.getBinding().getType());
		// System.out.println("binding=" + letExpr.getBinding().getExpr());
		// System.out.println("expr =" + letExpr.getExpr());
		// System.out.println("exprType =" + exprType);
		// System.out.println("letType =" + letType);

	}

	@Check
	public void checkBinExprCall(BinaryExpr binExpr) {
		String op = binExpr.getOp();
		ResoluteType typeLeft = getExprType(binExpr.getLeft());
		ResoluteType typeRight = getExprType(binExpr.getRight());

		switch (op) {
		case "=>":
		case "or":
		case "orelse":
		case "and":
		case "andthen":
			if (typeLeft.subtypeOf(BaseType.BOOL) && typeRight.subtypeOf(BaseType.BOOL)) {
				return;
			}
			break;

		case "^":
		case "+":
		case "-":
		case "*":
		case "%":
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
			if (typeLeft.subtypeOf(BaseType.STRING) && typeRight.subtypeOf(BaseType.STRING)) {
				return;
			}
			break;

		case "=":
		case "<>":
			if (typeLeft.join(typeRight) != null) {
				return;
			}
			break;

		default:
			error(binExpr, "Unknown binary operator '" + op + "'");
			return;
		}

		error(binExpr, "Operator '" + op + "' not defined on types " + typeLeft + ", " + typeRight);
	}

	@Check
	public void checkFnCallExpr(FnCallExpr funCall) {
		FunctionDefinition funDef = funCall.getFn();
		if (funDef.getName() == null) {
			// Prevent cascading errors when function is not found
			return;
		}

		EList<Expr> actuals = funCall.getArgs();
		EList<Arg> formals = funDef.getArgs();

		if (funDef.getBody() instanceof ClaimBody && !inClaimContext(funCall)) {
			error(funCall, "A claim cannot appear in this context.");
		}

		if (actuals.size() != formals.size()) {
			error(funCall,
					"Function expects " + formals.size() + " arguments but found " + actuals.size() + " arguments");
			return;
		}

		for (int i = 0; i < actuals.size(); i++) {
			ResoluteType formal = typeToResoluteType(formals.get(i).getType());
			ResoluteType actual = getExprType(actuals.get(i));

			if (!actual.subtypeOf(formal)) {
				error(funCall.getArgs().get(i), "Expected type " + formal + " but found type " + actual);
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
			if (be.getOp().equals("and") || be.getOp().equals("or") || (be.getOp().equals("andthen"))
					|| (be.getOp().equals("orelse"))) {
				return inClaimContext(be);
			}
		}

		if (context instanceof QuantifiedExpr) {
			QuantifiedExpr qe = (QuantifiedExpr) context;
			if (qe.getExpr().equals(obj)) {
				return inClaimContext(qe);
			}
		}

		if (context instanceof LetExpr) {
			LetExpr le = (LetExpr) context;
			if (le.getExpr().equals(obj)) {
				return inClaimContext(le);
			}
		}

		return false;
	}

	@Check
	public void checkLibraryFnCallExpr(LibraryFnCallExpr funCall) {
		EList<Expr> actuals = funCall.getArgs();
		List<ResoluteType> actualTypes = getExprTypes(funCall.getArgs());
		if (containsFail(actualTypes)) {
			return;
		}

		ResoluteExternalFunctionLibraryType libraryType = EvaluateLibraryTypeExtension.getLibraryType(funCall.getLibName());
		if (libraryType == null) {
			error(funCall, "Could not find external library '" + funCall.getLibName() + "'");
			return;
		}

		List<ResoluteType> argTypes = libraryType.getArgTypes(funCall.getFnName());

		if (argTypes == null) {
			error(funCall, "External function '" + funCall.getFnName() + "()' not found in library '"
					+ funCall.getLibName() + "'.");
			return;
		}

		if (actuals.size() != argTypes.size()) {
			error(funCall, "External library function '" + funCall.getLibName() + "." + funCall.getFnName()
					+ "()' expects " + argTypes.size() + " arguments but found " + (actuals.size()) + " arguments");
			return;
		}

		for (int i = 0; i < argTypes.size(); i++) {
			ResoluteType actualType = getExprType(actuals.get(i));
			ResoluteType expectedType = argTypes.get(i);

			if (!actualType.subtypeOf(expectedType)) {
				error(funCall.getArgs().get(i), "Expected type " + expectedType + " but found type " + actualType);
			}
		}

	}

	@Check
	public void checkBuiltInFnCallExpr(BuiltInFnCallExpr funCall) {
		EList<Expr> actuals = funCall.getArgs();
		List<ResoluteType> actualTypes = getExprTypes(actuals);
		if (containsFail(actualTypes)) {
			return;
		}

		// Special cases that require a little polymorphism
		switch (funCall.getFn()) {
		case "analysis":
			checkAnalysisCall(funCall, actuals);
			return;

		case "member":
			checkMemberCall(funCall, actualTypes);
			return;

		case "length":
		case "size":
			checkLengthCall(funCall.getFn(), funCall, actualTypes);
			return;

		case "sum":
			checkSumCall(funCall, actualTypes);
			return;
		case "append":
			checkAppendCall(funCall, actualTypes);
			return;
		case "head":
			checkHeadCall(funCall, actualTypes);
			return;
		case "tail":
			checkTailCall(funCall, actualTypes);
			return;
		case "as_set":
			checkAsSetCall(funCall, actualTypes);
			return;

		case "union":
		case "intersect":
			checkBinarySetOpCall(funCall, actualTypes);
			return;
		case "as_list":
			checkAsListCall(funCall, actualTypes);
			return;

		case "property":
			checkPropertyCall(funCall, actualTypes);
			return;

		case "debug":
		{
			return;
		}
		}




		List<ResoluteType> expectedTypes = getExpectedTypes(funCall);
		if (expectedTypes == null) {
			return;
		}

		if (actualTypes.size() != expectedTypes.size()
				// special case for property statement
				&& !(actualTypes.size() + 1 == expectedTypes.size())) {

			error(funCall, "Function expects " + expectedTypes.size() + " arguments but found " + actualTypes.size()
			+ " arguments");
			return;
		}

		for (int i = 0; i < actuals.size(); i++) {
			ResoluteType expected = expectedTypes.get(i);
			ResoluteType actual = getExprType(actuals.get(i));

			if (!actual.subtypeOf(expected)) {
				error(funCall.getArgs().get(i), "Expected type " + expected + " but found type " + actual);
			}
		}
	}

	private void checkAsListCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 1) {
			error(funCall, "function 'as_list' expects one argument");
			return;
		}

		if (!(actualTypes.get(0) instanceof SetType)) {
			error(funCall.getArgs().get(0), "argument to function 'as_list' must be a set");
		}

		return;
	}

	private void checkPropertyCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() == 1) {
			// we assume a property constant
			Expr actual = funCall.getArgs().get(0);
			if (actual instanceof IdExpr) {
				IdExpr idex = (IdExpr) actual;
				if (idex.getId() instanceof PropertyConstant) {
					return;
				}
			}
			error(funCall, "Only parameter expected to be a property constant reference.");
		}
		if(actualTypes.size() == 3){
			ResoluteType type1 = actualTypes.get(1);
			ResoluteType type2 = actualTypes.get(2);
			if (type1 instanceof ParametricType) {
				ParametricType paramType = (ParametricType) type1;
				if(paramType.getParamType().equals(type2)){
					return;
				}
			}
			error(funCall,
					"The second argument is of type '" + type1 + "' and the third argument is of type '" + type2 + "'");
		}

	}

	private boolean containsFail(List<ResoluteType> types) {
		for (ResoluteType type : types) {
			if (type.equals(BaseType.FAIL)) {
				return true;
			}
		}
		return false;
	}

	private void checkAnalysisCall(BuiltInFnCallExpr funCall, EList<Expr> actuals) {
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
		String analysisStr = analysisStrExpr.getVal().getValue().replace("\"", "");
		ResoluteExternalAnalysisType analysisType = EvaluateTypeExtention.getAnalysisType(analysisStr);

		if (analysisType == null) {
			error(funCall, "Could not find external analysis '" + analysisStr + "'");
			return;
		}

		List<ResoluteType> argTypes = analysisType.getArgTypes();
		if (actuals.size() != argTypes.size() + 1) {
			error(funCall, "External analysis '" + analysisStr + "' expects " + argTypes.size()
			+ " additional arguments but found " + (actuals.size() - 1) + " additional arguments");
			return;
		}

		for (int i = 0; i < argTypes.size(); i++) {
			ResoluteType actualType = getExprType(actuals.get(i + 1));
			ResoluteType expectedType = argTypes.get(i);

			if (!actualType.subtypeOf(expectedType)) {
				error(funCall.getArgs().get(i + 1), "Expected type " + expectedType + " but found type " + actualType);
			}
		}
	}

	private void checkLengthCall(String name, BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 1) {
			error(funCall, "function '"+name+"' expects two arguments");
			return;
		}

		if (!(actualTypes.get(0) instanceof ListType || actualTypes.get(0) instanceof SetType)) {
			error(funCall.getArgs().get(0), "Expected list or set type but found type " + actualTypes.get(1));
			return;
		}

		return;
	}

	private void checkMemberCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 2) {
			error(funCall, "function 'member' expects two arguments");
			return;
		}

		if (!(actualTypes.get(1) instanceof ListType || actualTypes.get(1) instanceof SetType)) {
			error(funCall.getArgs().get(1), "Expected list or set type but found type " + actualTypes.get(1));
		} else {
			ResoluteType elementType = actualTypes.get(0);
			ResoluteType aggregateElementType;
			if (actualTypes.get(1) instanceof ListType) {
				aggregateElementType = ((ListType) actualTypes.get(1)).elementType;
			} else if (actualTypes.get(1) instanceof SetType) {
				aggregateElementType = ((SetType) actualTypes.get(1)).elementType;
			} else {
				aggregateElementType = BaseType.FAIL;
			}
			if (elementType.join(aggregateElementType) == null) {
				error(funCall, "function 'member' not defined on arguments of type " + elementType + ", "
						+ aggregateElementType);
			}
		}

		return;
	}

	private void checkSumCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 1) {
			error(funCall, "function 'sum' expects one argument");
			return;
		}

		if (actualTypes.get(0) instanceof ListType) {
			ListType listType = (ListType) actualTypes.get(0);
			if (listType.elementType.subtypeOf(BaseType.INT) || listType.elementType.subtypeOf(BaseType.REAL)) {
				return;
			}
		}

		error(funCall.getArgs().get(0), "function 'sum' not defined on type " + actualTypes.get(0));
		return;
	}

	private void checkAppendCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 2) {
			error(funCall, "function 'append' expects two arguments");
			return;
		}

		if (!(actualTypes.get(0) instanceof ListType)) {
			error(funCall.getArgs().get(0), "first argument to function 'append' must be a list");
		}

		if (!(actualTypes.get(1) instanceof ListType)) {
			error(funCall.getArgs().get(1), "second argument to function 'append' must be a list");
		}

		if (actualTypes.get(0).join(actualTypes.get(1)) == null) {
			error(funCall.getArgs().get(0),
					"function 'append' not defined on types " + actualTypes.get(0) + " and " + actualTypes.get(1));
		}

		return;
	}

	private void checkHeadCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 1) {
			error(funCall, "function 'head' expects one argument");
			return;
		}

		if (!(actualTypes.get(0) instanceof ListType)) {
			error(funCall.getArgs().get(0), "argument to function 'head' must be a list");
		}

		return;
	}

	private void checkTailCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 1) {
			error(funCall, "function 'tail' expects one argument");
			return;
		}

		if (!(actualTypes.get(0) instanceof ListType)) {
			error(funCall.getArgs().get(0), "argument to function 'tail' must be a list");
		}

		return;
	}

	private void checkAsSetCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 1) {
			error(funCall, "function 'as_set' expects one argument");
			return;
		}

		if (!(actualTypes.get(0) instanceof ListType)) {
			error(funCall.getArgs().get(0), "argument to function 'as_set' must be a list");
		}

		return;
	}

	private void checkBinarySetOpCall(BuiltInFnCallExpr funCall, List<ResoluteType> actualTypes) {
		if (actualTypes.size() != 2) {
			error(funCall, "function '" + funCall.getFn() + "' expects two arguments");
			return;
		}

		for (int i = 0; i < 2; i++) {
			if (!(actualTypes.get(i) instanceof SetType)) {
				error(funCall.getArgs().get(i), "Expected set type but found type " + actualTypes.get(i));
				return;
			}
		}

		SetType set1 = (SetType) actualTypes.get(0);
		SetType set2 = (SetType) actualTypes.get(1);

		if (set1.join(set2) == null) {
			error(funCall, "function '" + funCall.getFn() + "' not defined on arguments of type " + set1 + ", " + set2);
			return;
		}

		return;
	}

	private List<ResoluteType> getExpectedTypes(BuiltInFnCallExpr funCall) {
		List<ResoluteType> expectedTypes = new ArrayList<>();

		switch (funCall.getFn()) {
		// Primary type: aadl
		case "has_parent":
		case "has_modes":
		case "parent":
		case "name":
		case "type":
		case "has_type":
		case "features":
		case "is_data_access":
		case "is_data_port":
		case "is_port":

		case "is_processor":
		case "is_virtual_processor":
		case "is_system":
		case "is_bus":
		case "is_virtual_bus":
		case "is_device":
		case "is_memory":
		case "is_thread":
		case "is_process":
		case "is_data":
		case "is_event_port":
		case "is_abstract_feature":
		case "connections":
		case "is_in_array":
		case "has_prototypes":
			expectedTypes.add(BaseType.AADL);
			break;

		case "has_property":
			expectedTypes.add(BaseType.AADL);
			expectedTypes.add(BaseType.PROPERTY);
			break;
		case "property":
			expectedTypes.add(BaseType.AADL);
			expectedTypes.add(BaseType.PROPERTY);
			expectedTypes.add(BaseType.ANY); // this one is optional
			break;
		case "property_member":
			expectedTypes.add(BaseType.RECORD);
			expectedTypes.add(BaseType.STRING);
			break;

		case "has_member":
			expectedTypes.add(BaseType.AADL);
			expectedTypes.add(BaseType.STRING);
			break;

			// Primary type: property
		case "enumerated_values":
			expectedTypes.add(BaseType.PROPERTY);
			break;

			// Primary type: component
		case "subcomponents":
			expectedTypes.add(BaseType.COMPONENT);
			break;

		case "is_of_type":
			expectedTypes.add(BaseType.COMPONENT);
			expectedTypes.add(BaseType.AADL);
			break;

		case "is_bound_to":
			expectedTypes.add(BaseType.AADL);
			expectedTypes.add(BaseType.AADL);
			break;

			// Primary type: connection
		case "source":
		case "destination":
			expectedTypes.add(BaseType.CONNECTION);
			break;

			// Primary type: feature
		case "direction":
			expectedTypes.add(BaseType.FEATURE);
			break;

			// Primary type: range
		case "lower_bound":
		case "upper_bound":
			expectedTypes.add(BaseType.RANGE);
			break;

			// Other
		case "instance":
		case "instances":
			expectedTypes.add(BaseType.AADL);
			break;

			// Error Annex
		case "propagate_error":
		case "contain_error":
		case "receive_error":
		case "error_state_reachable":
			expectedTypes.add(BaseType.COMPONENT);
			expectedTypes.add(BaseType.STRING);
			break;

		case "debug":
			break;

		case "flow_source":
		case "flow_destination":
			expectedTypes.add(BaseType.FLOW_SPECIFICATION);
			break;

		case "flow_elements":
			expectedTypes.add(BaseType.END_TO_END_FLOW);
			break;

		case "flow_specifications":
		case "end_to_end_flows":
			expectedTypes.add(BaseType.COMPONENT);
			break;

		default:
			error(funCall, "Unknown built-in function '" + funCall.getFn() + "'");
			return null;
		}

		return expectedTypes;
	}

	@Check
	public void checkListFilterMapExpr(ListFilterMapExpr mapExpr) {
		Expr filterExpr = mapExpr.getFilter();
		if (filterExpr != null) {
			ResoluteType validType = getExprType(filterExpr);
			if (!validType.subtypeOf(BaseType.BOOL)) {
				error(mapExpr.getFilter(), "Expected type bool but found type " + validType);
			}
		}
	}

	@Check
	public void checkSetFilterMapExpr(SetFilterMapExpr mapExpr) {
		Expr filterExpr = mapExpr.getFilter();
		if (filterExpr != null) {
			ResoluteType validType = getExprType(filterExpr);
			if (!validType.subtypeOf(BaseType.BOOL)) {
				error(mapExpr.getFilter(), "Expected type bool but found type " + validType);
			}
		}
	}

	@Check
	public void checkListExpr(ListExpr listExpr) {
		if (listExpr.getExprs().isEmpty()) {
			return;
		}

		Iterator<Expr> iterator = listExpr.getExprs().iterator();
		ResoluteType common = getExprType(iterator.next());
		int i = 1;
		while (iterator.hasNext()) {
			Expr expr = iterator.next();
			ResoluteType type = getExprType(expr);
			ResoluteType join = common.join(type);
			if (join == null) {
				error(listExpr.getExprs().get(i),
						"Unable to add type " + type + " to list of type " + new ListType(common));
				return;
			} else {
				common = join;
				i++;
			}
		}
	}

	@Check
	public void checkSetExpr(SetExpr setExpr) {
		if (setExpr.getExprs().isEmpty()) {
			return;
		}

		Iterator<Expr> iterator = setExpr.getExprs().iterator();
		ResoluteType common = getExprType(iterator.next());
		int i = 1;
		while (iterator.hasNext()) {
			Expr expr = iterator.next();
			ResoluteType type = getExprType(expr);
			ResoluteType join = common.join(type);
			if (join == null) {
				error(setExpr.getExprs().get(i),
						"Unable to add type " + type + " to set of type " + new SetType(common));
				return;
			} else {
				common = join;
				i++;
			}
		}
	}

	@Check
	public void checkInstanceOfExpr(InstanceOfExpr e) {
		ResoluteType exprType = getExprType(e.getExpr());
		ResoluteType type = typeToResoluteType(e.getType());
		if (!type.subtypeOf(exprType)) {
			error(e, "Cannot compare from " + exprType + " to " + type);
		}
	}

	@Check
	public void checkCastExpr(CastExpr e) {
		ResoluteType exprType = getExprType(e.getExpr());
		ResoluteType type = typeToResoluteType(e.getType());
		if (!type.subtypeOf(exprType)) {
			error(e, "Cannot cast from " + exprType + " to " + type);
		}
	}

	@Check
	public void checkLibraryFnType(LibraryFnType libFnType) {
		// Make sure library exists
		ResoluteExternalFunctionLibraryType libraryType = EvaluateLibraryTypeExtension.getLibraryType(libFnType.getLibName());
		if (libraryType == null) {
			error(libFnType, "Could not find external library '" + libFnType.getLibName() + "'");
			return;
		}

		// Check if library defines the specified type
		if (!libraryType.isTypeDefined(libFnType.getFnType())) {
			error(libFnType,
					"User-defined type '" + libFnType.getFnType() + "' not defined in " + libFnType.getLibName());
		}
	}

	/************* Begin helper functions ***************/

	private Stack<EObject> typeEvalContext = new Stack<>();

	private ResoluteType getExprType(Expr expr) {
		if (typeEvalContext.contains(expr)) {
			error(expr, "Cyclic type definition");
			return BaseType.FAIL;
		}
		try {
			typeEvalContext.push(expr);
			if (expr instanceof ListFilterMapExpr) {
				ListFilterMapExpr filterMapExpr = (ListFilterMapExpr) expr;
				return new ListType(getExprType(filterMapExpr.getMap()));
			}

			if (expr instanceof SetFilterMapExpr) {
				SetFilterMapExpr filterMapExpr = (SetFilterMapExpr) expr;
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

			if (expr instanceof LetExpr) {
				LetExpr letExpr = (LetExpr) expr;
				return getExprType(letExpr.getExpr());
			}

			if (expr instanceof ThisExpr) {
				Classifier parent = expr.getContainingClassifier();
				// A 'this' expression should always have a containing
				// classifier that is of component type. This may not
				// be the case if a 'this' statement appears in a ResoluteLibrary
				// rather than a ResoluteSubclause. In this case an error
				// will be thrown by the type checking function for 'this'
				// statements above
				if (parent instanceof ComponentImplementation) {
					ComponentImplementation ci = (ComponentImplementation) parent;
					ResoluteType type = getComponentType(ci.getType());
					if (type != null) {
						return type;
					}
				} else if (parent instanceof ComponentType) {
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
				if (body instanceof FunctionBody) {
					FunctionBody funcBody = (FunctionBody) body;
					return typeToResoluteType(funcBody.getType());
				} else if (body instanceof ClaimBody) {
					return BaseType.BOOL;
				} else {
					// Prevent cascading errors when function is not found
					return BaseType.FAIL;
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

			if (expr instanceof BuiltInFnCallExpr) {
				return getBuiltInFnCallType((BuiltInFnCallExpr) expr);
			}

			if (expr instanceof LibraryFnCallExpr) {
				return getLibraryFunctionType((LibraryFnCallExpr) expr);
			}

			if (expr instanceof ListExpr) {
				ListExpr listExpr = (ListExpr) expr;
				if (listExpr.getExprs().isEmpty()) {
					return new ListType(BaseType.ANY);
				}

				Iterator<Expr> iterator = listExpr.getExprs().iterator();
				ResoluteType common = getExprType(iterator.next());
				while (iterator.hasNext()) {
					Expr e = iterator.next();
					ResoluteType type = getExprType(e);
					common = common.join(type);
					if (common == null) {
						return BaseType.FAIL;
					}
				}
				return new ListType(common);
			}

			if (expr instanceof SetExpr) {
				SetExpr setExpr = (SetExpr) expr;
				if (setExpr.getExprs().isEmpty()) {
					return new SetType(BaseType.ANY);
				}

				Iterator<Expr> iterator = setExpr.getExprs().iterator();
				ResoluteType common = getExprType(iterator.next());
				while (iterator.hasNext()) {
					Expr e = iterator.next();
					ResoluteType type = getExprType(e);
					common = common.join(type);
					if (common == null) {
						return BaseType.FAIL;
					}
				}
				return new SetType(common);
			}

			if (expr instanceof CastExpr) {
				CastExpr castExpr = (CastExpr) expr;
				return typeToResoluteType(castExpr.getType());
			}

			if (expr instanceof InstanceOfExpr) {
				return BaseType.BOOL;
			}

			error(expr, "Unable to get type for expression");
			return BaseType.FAIL;
		} finally {
			typeEvalContext.pop();
		}
	}

	public ResoluteType getBinaryExprType(BinaryExpr binExpr) {
		ResoluteType leftType = getExprType(binExpr.getLeft());
		ResoluteType rightType = getExprType(binExpr.getRight());

		switch (binExpr.getOp()) {
		case "=>":
		case "or":
		case "orelse":
		case "and":
		case "andthen":
		case "<":
		case "<=":
		case ">":
		case ">=":
		case "=":
		case "<>":
			return BaseType.BOOL;

		case "^":
		case "+":
		case "-":
		case "*":
		case "%":
		case "/": {
			if (leftType.equals(BaseType.REAL) && rightType.equals(BaseType.INT)) {
				return BaseType.REAL;
			}
			if (leftType.equals(BaseType.INT) && rightType.equals(BaseType.REAL)) {
				return BaseType.REAL;
			}
			return leftType;
		}
		}

		error(binExpr, "Unable to get type for binary expression");
		return BaseType.FAIL;
	}

	public ResoluteType getIdExprType(IdExpr id) {
		NamedElement idClass = id.getId();

		if (idClass instanceof ComponentClassifier) {
			ComponentClassifier component = (ComponentClassifier) idClass;
			return new BaseType(component.getCategory());
		}

		if (idClass instanceof Arg) {
			Arg arg = (Arg) idClass;

			if (arg instanceof QuantArg) {

				ResoluteType argType = getExprType(((QuantArg) arg).getExpr());

				if (argType instanceof ListType) {
					return ((ListType) argType).elementType;
				} else if (argType instanceof SetType) {
					return ((SetType) argType).elementType;
				} else {
					return argType;
				}
			}

			return typeToResoluteType(arg.getType());
		}

		if (idClass instanceof Property) {
			Property prop = (Property) idClass;
			ResoluteType type = convertPropertyType(prop.getPropertyType());
			if (type == null) {
				error(id, "Unknown property type");
				return BaseType.FAIL;
			} else {
				return type;
			}
		}

		if (idClass instanceof PropertyConstant) {
			PropertyConstant prop = (PropertyConstant) idClass;
			ResoluteType type = convertPropertyType(prop.getPropertyType());
			if (type == null) {
				error(id, "Unknown property type");
				return BaseType.FAIL;
			} else {
				return type;
			}
		}

		if (idClass instanceof ConstantDefinition) {
			ConstantDefinition consDef = (ConstantDefinition) idClass;
			return typeToResoluteType(consDef.getType());
		}

		if (idClass instanceof FunctionDefinition) {
			return new BaseType("function");
		}

		if (idClass instanceof LetBinding) {
			LetBinding binding = (LetBinding) idClass;
			return typeToResoluteType(binding.getType());
		}

		error(id, "Unable to get type for id expression");
		return BaseType.FAIL;
	}

	public ResoluteType getBuiltInFnCallType(BuiltInFnCallExpr funCall) {
		// Simple predicates
		if (funCall.getFn().startsWith("has_")) {
			return BaseType.BOOL;
		}


		switch (funCall.getFn()) {
		// Primary type: aadl
		case "property":
			if (funCall.getArgs().size() > 2) {
				return getExprType(funCall.getArgs().get(2));
			} else {
				return getPropertyType(funCall);
			}

		case "parent":
			return BaseType.AADL;
		case "name":
			return BaseType.STRING;
		case "type":
			return BaseType.AADL;
		case "features":
			return new SetType(BaseType.FEATURE);

			// Primary type: property
		case "enumerated_values":
			return new SetType(BaseType.STRING);

			// Primary type: component
		case "subcomponents":
			return new SetType(BaseType.COMPONENT);

			// Primary type: connection
		case "source":
		case "destination":
			return BaseType.AADL;

			// Primary type: feature
		case "connections":
			return new SetType(BaseType.CONNECTION);
		case "direction":
			return BaseType.STRING;


		case "is_event_port":
		case "is_processor":
		case "is_virtual_processor":
		case "is_system":
		case "is_bus":
		case "is_virtual_bus":
		case "is_device":
		case "is_memory":
		case "is_thread":
		case "is_process":
		case "is_data":
		case "is_of_type":
		case "is_bound_to":
		case "is_in_array":
		case "has_prototypes":
			return BaseType.BOOL;

			// Primary type: range
		case "upper_bound":
		case "lower_bound":
			return BaseType.INT;

			// Primary type: list or set
		case "member":
			return BaseType.BOOL;
		case "length":
		case "size":
			return BaseType.INT;

			// Primary type: List
		case "sum":
		case "head":
			return getCollectionFnElementType(funCall);
		case "append":
		case "tail":
			return new ListType(getCollectionFnElementType(funCall));
		case "as_set":
			return new SetType(getCollectionFnElementType(funCall));

			// Primary type: set
		case "union":
		case "intersect":
			return getBinarySetOpType(funCall);
		case "as_list":
			return new ListType(getCollectionFnElementType(funCall));

			// Other
		case "instance":
			return BaseType.COMPONENT;
		case "instances":
			return new SetType(BaseType.COMPONENT);
		case "analysis":
			return getAnalysisType(funCall);

		case "debug":
			return BaseType.BOOL;

			// Error Annex
		case "receive_error":
		case "contain_error":
		case "propagate_error":
		case "error_state_reachable":
			return BaseType.BOOL;

		default:
			return BaseType.FAIL;
		}
	}

	private ResoluteType getPropertyType(BuiltInFnCallExpr funCall) {
		if (funCall.getArgs().size() != 2) {
			return BaseType.FAIL;
		}

		Expr propExpr = funCall.getArgs().get(1);
		ResoluteType type = getExprType(propExpr);
		if (!(type instanceof ParametricType)) {
			error(propExpr, "The expressions given to property lookup statements must either "
					+ "be an AADL property or an expression of parameterized property type");
			return BaseType.FAIL;
		}
		ParametricType paramType = (ParametricType) type;
		BaseType baseType = paramType.getBaseType();
		if (!baseType.equals(BaseType.PROPERTY)) {
			error(propExpr, "The base type of the property is '" + baseType + "' but must be of type 'property'");
			return BaseType.FAIL;
		}
		return paramType.getParamType();

	}

	private ResoluteType convertPropertyType(PropertyType propType){
		ResoluteType type = convertPropertyTypeHelper(propType);
		if(type == null){
			return null;
		}
		return new ParametricType(BaseType.PROPERTY, type);
	}

	private ResoluteType convertPropertyTypeHelper(PropertyType propType) {
		ResoluteType type;
		if (propType instanceof AadlBoolean) {
			type = BaseType.BOOL;
		} else if (propType instanceof AadlString || propType instanceof EnumerationType) {
			type = BaseType.STRING;
		} else if (propType instanceof AadlInteger) {
			type = BaseType.INT;
		} else if (propType instanceof AadlReal) {
			type = BaseType.REAL;
		} else if (propType instanceof ClassifierType) {
			type = BaseType.COMPONENT;
		} else if (propType instanceof RangeType) {
			type = BaseType.RANGE;
		} else if (propType instanceof ReferenceType) {
			type = BaseType.AADL;
		} else if (propType instanceof RecordType) {
			type = BaseType.RECORD;
		} else if (propType instanceof org.osate.aadl2.ListType) {
			org.osate.aadl2.ListType listType = (org.osate.aadl2.ListType) propType;
			ResoluteType elementType = convertPropertyTypeHelper(listType.getElementType());
			if (elementType == null) {
				return null;
			} else {
				type = new ListType(elementType);
			}
		} else {
			return null;
		}
		return type;
	}

	private ResoluteType getCollectionFnElementType(BuiltInFnCallExpr funCall) {
		if (funCall.getArgs().size() != 1) {
			return BaseType.FAIL;
		}

		ResoluteType collectionType = getExprType(funCall.getArgs().get(0));
		if (collectionType instanceof ListType) {
			ListType list = (ListType) collectionType;
			return list.elementType;
		} else if (collectionType instanceof SetType) {
			SetType set = (SetType) collectionType;
			return set.elementType;
		} else {
			return BaseType.FAIL;
		}
	}

	private ResoluteType getBinarySetOpType(BuiltInFnCallExpr funCall) {
		if (funCall.getArgs().size() != 2) {
			return BaseType.FAIL;
		}

		if (!(getExprType(funCall.getArgs().get(0)) instanceof SetType))
		{
			return BaseType.FAIL;
		}

		if (!(getExprType(funCall.getArgs().get(1)) instanceof SetType))
		{
			return BaseType.FAIL;
		}

		SetType set1 = (SetType) getExprType(funCall.getArgs().get(0));
		SetType set2 = (SetType) getExprType(funCall.getArgs().get(1));

		ResoluteType join = set1.join(set2);
		if (join == null) {
			return BaseType.FAIL;
		} else {
			return join;
		}
	}

	private ResoluteType getAnalysisType(BuiltInFnCallExpr funCall) {
		if (funCall.getArgs().isEmpty()) {
			return BaseType.FAIL;
		}

		Expr expr = funCall.getArgs().get(0);
		if (!(expr instanceof StringExpr)) {
			return BaseType.FAIL;
		}

		StringExpr analysisStrExpr = (StringExpr) expr;
		String analysisStr = analysisStrExpr.getVal().getValue().replace("\"", "");
		ResoluteExternalAnalysisType analysisType = EvaluateTypeExtention.getAnalysisType(analysisStr);

		if (analysisType == null) {
			return BaseType.FAIL;
		}
		return analysisType.getType();
	}

	private ResoluteType getLibraryFunctionType(LibraryFnCallExpr funCall) {
		if (funCall.getLibName().isEmpty()) {
			return BaseType.FAIL;
		} else if (funCall.getFnName().isEmpty()) {
			return BaseType.FAIL;
		}

		ResoluteExternalFunctionLibraryType libraryType = EvaluateLibraryTypeExtension.getLibraryType(funCall.getLibName());
		if (libraryType == null) {
			return BaseType.FAIL;
		}
		return libraryType.getType(funCall.getFnName());
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

	private ResoluteType typeToResoluteType(Type type) {
		if (type == null) {
			return BaseType.FAIL;
		} else if (type instanceof com.rockwellcollins.atc.resolute.resolute.BaseType) {
			com.rockwellcollins.atc.resolute.resolute.BaseType bt = (com.rockwellcollins.atc.resolute.resolute.BaseType) type;
			Type paramType = bt.getParamType();
			if(paramType != null){
				return new ParametricType(new BaseType(bt.getType()), typeToResoluteType(paramType));
			}
			return new BaseType(bt.getType());
		} else if (type instanceof com.rockwellcollins.atc.resolute.resolute.ListType) {
			com.rockwellcollins.atc.resolute.resolute.ListType st = (com.rockwellcollins.atc.resolute.resolute.ListType) type;
			ResoluteType innerType = typeToResoluteType(st.getType());
			if (innerType == BaseType.FAIL) {
				return BaseType.FAIL;
			} else {
				return new ListType(innerType);
			}
		} else if (type instanceof com.rockwellcollins.atc.resolute.resolute.SetType) {
			com.rockwellcollins.atc.resolute.resolute.SetType st = (com.rockwellcollins.atc.resolute.resolute.SetType) type;
			ResoluteType innerType = typeToResoluteType(st.getType());
			if (innerType == BaseType.FAIL) {
				return BaseType.FAIL;
			} else {
				return new SetType(innerType);
			}
		} else if (type instanceof LibraryFnType) {
			return new BaseType("agree_spec");
		} else {
			error(type, "Unable to convert type");
			throw new IllegalArgumentException();
		}
	}
}
