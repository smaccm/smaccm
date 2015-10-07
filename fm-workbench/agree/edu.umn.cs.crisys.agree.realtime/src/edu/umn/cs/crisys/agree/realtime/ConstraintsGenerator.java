package edu.umn.cs.crisys.agree.realtime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

import org.osate.aadl2.Property;
import org.osate.aadl2.modelsupport.resources.OsateResourceUtil;
import org.osate.xtext.aadl2.properties.util.EMFIndexRetrieval;
import org.osate.xtext.aadl2.properties.util.PropertyUtils; 

import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.Expr;
import jkind.lustre.IdExpr;
import jkind.lustre.IntExpr;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.extentions.AgreeAutomater;

public class ConstraintsGenerator implements AgreeAutomater{

	@Override
	public AgreeProgram transform(AgreeProgram program) {
		//TODO return a new AgreeProgram maniuplated however you want
		
		return program;
//		AgreeVar input = program.topNode.inputs.get(0);
//		AgreeVar output = program.topNode.outputs.get(0);
//		
//		IdExpr inputId = new IdExpr(input.id);
//		IdExpr outputId = new IdExpr(output.id);
//		
//		Expr newAssertion = new BinaryExpr(inputId, BinaryOp.EQUAL, outputId);
//		AgreeStatement agreeAssertion = new AgreeStatement("", newAssertion, null);
//		List<AgreeStatement> assertions = new ArrayList<>();
//		
//		//assertions.add(agreeAssertion);
//		assertions.addAll(program.topNode.assumptions);
//		
//		Property commTimingProp = EMFIndexRetrieval.getPropertyDefinitionInWorkspace(
//				OsateResourceUtil.getResourceSet(), "Timing_Properties::Period");
//		  
//		long intVal = PropertyUtils.getIntegerValue(program.topNode.compInst, commTimingProp);
//		//PropertyUtils.getEnumLiteral(conn, commTimingProp);
//		
//		// assert output = "whatever the peroid is"
//		Expr intExpr = new IntExpr(BigInteger.valueOf(intVal));
//		Expr outConstr = new BinaryExpr(outputId, BinaryOp.EQUAL, intExpr);
//		assertions.add(new AgreeStatement("", outConstr, null));
//		
//		
//		AgreeNode newTopNode = new AgreeNode(program.topNode.id, program.topNode.inputs, program.topNode.outputs, program.topNode.locals, 
//				program.topNode.connections, program.topNode.subNodes, assertions, program.topNode.assumptions, 
//				program.topNode.guarantees, program.topNode.lemmas, program.topNode.clockConstraint, program.topNode.initialConstraint, 
//				program.topNode.clockVar, program.topNode.reference, program.topNode.timing, program.topNode.compInst);
//		
//		AgreeProgram newProgram = new AgreeProgram(program.agreeNodes, program.globalLustreNodes, program.globalTypes, newTopNode);
//		
//		return newProgram;
	}

}
