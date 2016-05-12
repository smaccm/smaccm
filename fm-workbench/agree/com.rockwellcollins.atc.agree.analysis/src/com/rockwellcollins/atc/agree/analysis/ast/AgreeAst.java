package com.rockwellcollins.atc.agree.analysis.ast;

import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeAstVisitor;

public interface AgreeAst {
    public abstract <T> T accept(AgreeAstVisitor<T> visitor);
}
