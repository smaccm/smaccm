package com.rockwellcollins.atc.agree.analysis.ast.visitors;

import java.util.Iterator;
import java.util.List;

import com.rockwellcollins.atc.agree.analysis.ast.AgreeAADLConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeEquation;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeNode;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeOverriddenConnection;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeProgram;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeStatement;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;

import jkind.lustre.Location;
import jkind.lustre.Node;
import jkind.lustre.RecordType;
import jkind.lustre.Type;
import jkind.lustre.TypeDef;
import jkind.lustre.visitors.PrettyPrintVisitor;

public class AgreeASTPrettyprinter extends PrettyPrintVisitor implements AgreeASTVisitor<Void> {
	private StringBuilder sb = new StringBuilder();
	private String main;

	@Override
	public String toString() {
		return sb.toString();
	}

	@Override
	protected void write(Object o) {
		sb.append(o);
	}

	private static final String seperator = System.getProperty("line.separator");

	private void newline() {
		write(seperator);
	}

	@Override
	public Void visit(AgreeProgram program) {
		if (program.containsRealTimePatterns) {
			write("-- Program contains real-time patterns");
			newline();
			newline();
		}

		write("-- Program top-level node is: " + program.topNode.id);
		newline();
		newline();

		if (!program.globalTypes.isEmpty()) {
			for (Type type : program.globalTypes) {

				String name = "dummy";
				if (type instanceof RecordType) {
					name = ((RecordType) type).id;
				}
				TypeDef typeDef = new TypeDef(Location.NULL, name, type);
				typeDef.accept(this);
				newline();
			}
			newline();
		}

		if (!program.globalLustreNodes.isEmpty()) {
			Iterator<Node> iterator = program.globalLustreNodes.iterator();
			while (iterator.hasNext()) {
				iterator.next().accept(this);
				newline();
				if (iterator.hasNext()) {
					newline();
				}
			}
			newline();
		}

		Iterator<AgreeNode> iterator = program.agreeNodes.iterator();
		while (iterator.hasNext()) {
			iterator.next().accept(this);
			newline();
			if (iterator.hasNext()) {
				newline();
			}
		}
		newline();

		return null;
	}

	@Override
	public Void visit(AgreeConnection e) {
		connection(e);
		return null;
	}

	@Override
	public Void visit(AgreeNode node) {
		write("agree node ");
		write(node.id);
		write("(");
		newline();
		agreeVarDecls(node.inputs);
		newline();
		write(") returns (");
		newline();
		agreeVarDecls(node.outputs);
		newline();
		write(");");
		newline();

		if (!node.locals.isEmpty()) {
			write("var");
			newline();
			agreeVarDecls(node.locals);
			write(";");
			newline();
		}

		if (node.id.equals(main)) {
			write("  --%MAIN;");
			newline();
		}

		write("let");
		newline();

		if (!node.connections.isEmpty()) {
			for (AgreeConnection connection : node.connections) {
				connection(connection);
			}
			newline();
		}

		write("  children {");
		for (AgreeNode subNode : node.subNodes) {
			write(subNode.id);
			write(" ");
		}
		write("  }");
		newline();
		newline();

		statementList("assertions", node.assertions);
		statementList("assumptions", node.assumptions);
		statementList("guarantees", node.guarantees);
		statementList("lemmas", node.lemmas);
		statementList("pattern props", node.patternProps);

		for (AgreeEquation equation : node.localEquations) {
			write("  ");
			visit(equation);
			newline();
			newline();
		}

		if (node.clockConstraint != null) {
			write("  clock constraint: ");
			node.clockConstraint.accept(this);
			newline();
		}

		if (node.initialConstraint != null) {
			write("  initial constraint: ");
			node.initialConstraint.accept(this);
			newline();
		}

		if (node.clockVar != null) {
			write("  clock variable: ");
			visit(node.clockVar);
			newline();
		}

		write("  timing model: " + node.timing);
		newline();

		write("  -- TBD: event models to go along with timing model");
		newline();
		newline();

		write("tel;");
		return null;
	}

	private void statementList(String type, List<AgreeStatement> stmts) {
		if (!stmts.isEmpty()) {
			write("  " + type + " {");
			newline();
			Iterator<AgreeStatement> iterator = stmts.iterator();
			while (iterator.hasNext()) {
				iterator.next().accept(this);
				newline();
				if (iterator.hasNext()) {
					newline();
				}
			}
			write("  }");
			newline();
			newline();
		}
	}

	// Note: this method (and the fact that I'm using instanceof)
	// means that the AGREE Ast visitor is broken
	private void connection(AgreeConnection conn) {
		if (conn instanceof AgreeAADLConnection) {
			AgreeAADLConnection aadl = (AgreeAADLConnection) conn;
			write("  ");
			write(aadl.type);
			write(aadl.delayed ? " delayed" : "");
			write(aadl.latched ? " latched" : "");
			write(" connection {");
			newline();
			write("    ");
			write(aadl.sourceNode == null ? "NULL NODE" : aadl.sourceNode.id);
			write("." + aadl.sourceVarName.id);
			write(" ->");
			write(aadl.destinationNode == null ? " NULL NODE" : " " + aadl.destinationNode.id);
			write("." + aadl.destinationVarName.id);
			newline();
			write("  }");
			newline();
		} else if (conn instanceof AgreeOverriddenConnection) {
			AgreeOverriddenConnection aadl = (AgreeOverriddenConnection) conn;
			write("  connection {");
			newline();
			write("    ");
			aadl.statement.accept(this);
			newline();
			write("    aadl: " + aadl.aadlConn.toString());
			newline();
			write("  }");
			newline();
		}
	}

	private void agreeVarDecls(List<AgreeVar> varDecls) {
		Iterator<AgreeVar> iterator = varDecls.iterator();
		while (iterator.hasNext()) {
			write("  ");
			visit(iterator.next());
			if (iterator.hasNext()) {
				write(";");
				newline();
			}
		}
	}

	@Override
	public Void visit(AgreeStatement e) {
		write("    ");
		if (e.string != null && !e.string.equals("")) {
			write("\"" + e.string + "\" : ");
		}
		e.expr.accept(this);

		return null;
	}

	@Override
	public Void visit(AgreeVar e) {
		super.visit(e);
		return null;
	}

	@Override
	public Void visit(AgreeEquation agreeEquation) {
		super.visit(agreeEquation);
		return null;
	}

}
