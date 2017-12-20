package com.rockwellcollins.atc.agree.analysis.lustre.visitors;

import jkind.lustre.IdExpr;

public interface IdRewriter {
	public IdExpr rewrite(IdExpr id);
}
