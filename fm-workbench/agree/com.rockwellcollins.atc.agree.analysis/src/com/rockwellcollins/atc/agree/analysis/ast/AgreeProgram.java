package com.rockwellcollins.atc.agree.analysis.ast;

import java.util.List;

import com.rockwellcollins.atc.agree.analysis.ast.visitors.AgreeAstVisitor;

import jkind.lustre.Node;
import jkind.lustre.Type;

public class AgreeProgram implements AgreeAst{
    public final List<AgreeNode> agreeNodes;
    public final List<Node> globalLustreNodes;
    public final List<Type> globalTypes;
    public final AgreeNode topNode;

    public AgreeProgram(List<AgreeNode> agreeNodes, List<Node> globalLustreNodes, List<Type> globalTypes,
            AgreeNode topNode) {
        this.agreeNodes = jkind.util.Util.safeList(agreeNodes);
        this.globalLustreNodes = jkind.util.Util.safeList(globalLustreNodes);
        this.globalTypes = jkind.util.Util.safeList(globalTypes);
        this.topNode = topNode;
    }

    @Override
    public <T> T accept(AgreeAstVisitor<T> visitor) {
        return visitor.visit(this);
    }

}
