package com.rockwellcollins.atc.agree.codegen.translation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.jface.preference.IPreferenceStore;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.codegen.Activator;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssignment;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABAssumption;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABLocalBusVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarDecl;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPersistentVarInit;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPort;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPrimaryFunction;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABProperty;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABStatement;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABExpr;
import com.rockwellcollins.atc.agree.codegen.ast.expr.MATLABIdExpr;
import com.rockwellcollins.atc.agree.codegen.preferences.PreferenceConstants;
import com.rockwellcollins.atc.agree.codegen.visitors.LustreToMATLABExprVisitor;
import com.rockwellcollins.atc.agree.codegen.visitors.LustreToMATLABTypeVisitor;
import com.rockwellcollins.atc.agree.codegen.visitors.MATLABTypeCastExprVisitor;

import jkind.lustre.Equation;
import jkind.lustre.Expr;
import jkind.lustre.NamedType;
import jkind.lustre.Node;
import jkind.lustre.RecordType;
import jkind.lustre.Type;
import jkind.lustre.VarDecl;
import jkind.util.StringNaturalOrdering;

public class LustreToMATLABTranslator {
	public static String intTypeStr = null;
	public static String realTypeStr = null;

	public static MATLABPrimaryFunction translate(Node lustreNode, AgreeProgram agreeProgram) {
		IPreferenceStore prefs = Activator.getDefault().getPreferenceStore();
		intTypeStr = prefs.getString(PreferenceConstants.PREF_INT);
		realTypeStr = prefs.getString(PreferenceConstants.PREF_REAL);

		List<MATLABIdExpr> inputs = new ArrayList<>();
		List<MATLABStatement> statements = new ArrayList<>();
		List<MATLABFunction> functions = new ArrayList<>();
		List<MATLABPersistentVarDecl> persistentVarDecl = new ArrayList<>();
		List<MATLABPort> ports = new ArrayList<>();

		LustreToMATLABExprVisitor exprVisitor = new LustreToMATLABExprVisitor();
		LustreToMATLABTypeVisitor typeVisitor = new LustreToMATLABTypeVisitor();

		// get function name
		String functionName = "check_" + lustreNode.id;

		// add record types
		for (Type type : agreeProgram.globalTypes) {
			if (type instanceof RecordType) {
				RecordType recordType = (RecordType) type;
				SortedMap<String, MATLABType> fields = new TreeMap<>(new StringNaturalOrdering());
				Iterator<Entry<String, Type>> iterator = recordType.fields.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<String, Type> entry = iterator.next();
					fields.put(exprVisitor.updateName(entry.getKey(), recordType.id),
							entry.getValue().accept(typeVisitor));
				}
				exprVisitor.recordTypeMap.put(recordType.id, fields);
			}
		}

		// add input variables to MATLAB function
		// add input and output ports for subsystem to verify
		for (VarDecl inputVar : lustreNode.inputs) {
			String varName = null;
			if (!inputVar.id.equals("time")) {
				// update name
				varName = exprVisitor.updateName(inputVar.id, "");
				// add inputs
				inputs.add(new MATLABIdExpr(varName));
				// translate the Lustre expression to MATLAB expression
				// add input Ids to inputList of the exprVisitor
				// to help identify local variables
				exprVisitor.inputSet.add(inputVar.id);
				// get inputVar and type
				AgreeVar agreeVar = (AgreeVar) inputVar;
				Type type = agreeVar.type;
				if (type instanceof RecordType || type instanceof NamedType) {
					MATLABType portType = (agreeVar.type).accept(typeVisitor);

					SortedMap<String, MATLABType> fields = null;

					// get the fields if it is a RecordType
					if (type instanceof RecordType) {
						fields = exprVisitor.recordTypeMap.get(((RecordType) type).id);
					}

					// if it is from AADL feature, get the feature instance
					if (agreeVar.featInst != null) {
						FeatureInstance featInst = agreeVar.featInst;
						ports.add(new MATLABPort(featInst.getName(), featInst.getDirection().getName(), portType,
								fields));
					}
					// if it is not from AADL feature, but an eq variable from
					// AGREE
					// set them as output variables from the subsystem
					else {
						ports.add(new MATLABPort(inputVar.id, "out", portType, fields));
					}
				}
			}
		}

		// add local variable and their types
		for (VarDecl localVar : lustreNode.locals) {
			// get local var Name and Type
			exprVisitor.localVarTypeMap.put(exprVisitor.updateName(localVar.id, ""), localVar.type.accept(typeVisitor));
		}

		// translate equations to assignments
		if (!lustreNode.equations.isEmpty()) {
			for (Equation equation : lustreNode.equations) {
				// get the variable to assign
				String varId = exprVisitor.updateName(equation.lhs.get(0).id, "");
				MATLABIdExpr varToAssign = new MATLABIdExpr(varId);
				// get the type for the local variable
				// MATLABType type = exprVisitor.localVarTypeMap.get(varId);
				// translate expressions
				MATLABExpr expr = exprVisitor.visit(equation.expr);
				// conduct explicit type cast if it's a constant of double type or int type
				// no need to type cast for assignment from an input variable
				// or operations (including functions) involving known types
				MATLABTypeCastExprVisitor typeCastVisitor = new MATLABTypeCastExprVisitor();
				expr = typeCastVisitor.visit(expr);
				// add any new preVar init from exprVisitor
				Iterator<MATLABPersistentVarInit> persistentVarInitIterator = exprVisitor.persistentVarInits.iterator();
				while (persistentVarInitIterator.hasNext()) {
					MATLABPersistentVarInit persistentVarInit = persistentVarInitIterator.next();
					// add new preVar init to the statements before the assignment
					statements.add(persistentVarInit);
					// remove the new preVar init from exprVisitor
					persistentVarInitIterator.remove();
				}

				// add any new local Bus var init from exprVisitor
				Iterator<MATLABLocalBusVarInit> busVarInitIterator = exprVisitor.localBusVarInits.iterator();
				while (busVarInitIterator.hasNext()) {
					MATLABLocalBusVarInit busVarInit = busVarInitIterator.next();
					// add new local Bus var init to the statements before the assignment
					statements.add(busVarInit);
					// remove the new local Bus var init from exprVisitor
					busVarInitIterator.remove();
				}

				// add assignment
				MATLABAssignment assignment = new MATLABAssignment(varToAssign, expr);
				statements.add(assignment);
			}
		}

		// add persistentVar decl and assignments
		Iterator<Entry<String, MATLABExpr>> it = exprVisitor.persistentVarMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, MATLABExpr> pair = it.next();
			String varToAssign = pair.getKey();
			MATLABExpr expr = pair.getValue();
			statements.add(new MATLABAssignment(new MATLABIdExpr(varToAssign), expr));
			persistentVarDecl.add(new MATLABPersistentVarDecl(varToAssign));
		}

		// translate assertions
		for (Expr assertionExpr : lustreNode.assertions) {
			MATLABExpr expr = exprVisitor.visit(assertionExpr);
			// add assertions
			MATLABAssumption assumption = new MATLABAssumption(expr);
			statements.add(assumption);
		}

		// translate properties
		for (String propertyStr : lustreNode.properties) {
			propertyStr = exprVisitor.updateName(propertyStr, "");
			MATLABProperty property = new MATLABProperty(propertyStr);
			statements.add(property);
		}

		// add definitions for the functions that have been called
		for (Map.Entry<String, MATLABFunction> functionEntry : exprVisitor.functionMap.entrySet()) {
			MATLABFunction function = functionEntry.getValue();
			if (function.functionCalled) {
				functions.add(function);
			}
		}

		// Create primary function AST
		MATLABPrimaryFunction primaryFunction = new MATLABPrimaryFunction(functionName, inputs, persistentVarDecl,
				statements, functions, ports);
		return primaryFunction;
	}

}
