package com.rockwellcollins.atc.agree.analysis.ast;

import org.osate.aadl2.Connection;

import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeASTVisitor;

public class AgreeOverriddenConnection implements AgreeConnection {

	public final AgreeStatement statement;
	public final Connection aadlConn;

	public AgreeOverriddenConnection(AgreeStatement statement, Connection aadlConn) {
		this.statement = statement;
		this.aadlConn = aadlConn;
	}

	@Override
	public <T> T accept(AgreeASTVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
