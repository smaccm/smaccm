package com.rockwellcollins.atc.agree.analysis;

import jkind.lustre.IdExpr;

public interface IdRewriter {
    public IdExpr rewrite(IdExpr id);
}
