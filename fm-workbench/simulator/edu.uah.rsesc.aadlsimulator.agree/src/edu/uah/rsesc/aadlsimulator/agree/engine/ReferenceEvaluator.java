package edu.uah.rsesc.aadlsimulator.agree.engine;

import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;
import jkind.lustre.Expr;
import jkind.lustre.values.Value;

interface ReferenceEvaluator {
	Expr getLustreExpression(ElementRefExpression ref);
	Value getLustreValue(ElementRefExpression ref, int preLevel);
	Value getLustreValue(ConstRefExpression ref, int preLevel);
}
