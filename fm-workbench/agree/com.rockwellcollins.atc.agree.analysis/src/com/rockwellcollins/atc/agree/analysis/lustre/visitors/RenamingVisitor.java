package com.rockwellcollins.atc.agree.analysis.lustre.visitors;

import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;

import jkind.lustre.Node;
import jkind.lustre.visitors.AstIterVisitor;

public class RenamingVisitor extends AstIterVisitor{

    private final AgreeRenaming renaming;
    private final StringBuilder prefix;
    
    public static void addRenamings(Node node, AgreeRenaming renaming){
        new RenamingVisitor(renaming).visit(node);
    }
    
    private RenamingVisitor(AgreeRenaming renaming){
        this.renaming = renaming;
        this.prefix = new StringBuilder("");
    }
    
}
