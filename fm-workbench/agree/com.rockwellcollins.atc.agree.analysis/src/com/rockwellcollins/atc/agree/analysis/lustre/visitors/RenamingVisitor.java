package com.rockwellcollins.atc.agree.analysis.lustre.visitors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.FeatureGroup;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.AssertStatement;
import com.rockwellcollins.atc.agree.agree.AssumeStatement;
import com.rockwellcollins.atc.agree.agree.GuaranteeStatement;
import com.rockwellcollins.atc.agree.agree.LemmaStatement;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.analysis.AgreeException;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout;
import com.rockwellcollins.atc.agree.analysis.AgreeRenaming;
import com.rockwellcollins.atc.agree.analysis.AgreeLayout.SigType;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeASTBuilder;
import com.rockwellcollins.atc.agree.analysis.ast.AgreeVar;
import com.rockwellcollins.atc.agree.analysis.translation.LustreAstBuilder;

import jkind.lustre.Node;
import jkind.lustre.Program;
import jkind.lustre.VarDecl;
import jkind.lustre.visitors.AstIterVisitor;

public class RenamingVisitor extends AstIterVisitor{

    private final AgreeRenaming renaming;
    private final AgreeLayout layout;
    private final Program program;
    private boolean isMainNode;
    
    public static void addRenamings(Program program, AgreeRenaming renaming, AgreeLayout layout){
        RenamingVisitor visitor = new RenamingVisitor(program, renaming, layout);
        for(Node node : program.nodes){
            visitor.visit(node);
        }
    }
    
    private RenamingVisitor(Program program, AgreeRenaming renaming, AgreeLayout layout){
        this.renaming = renaming;
        this.layout = layout;
        this.program = program;
    }
    
    @Override
    public Void visit(Node node){
        isMainNode = node.id.equals(program.main);
        visitVarDecls(node.inputs);
        visitVarDecls(node.outputs);
        visitVarDecls(node.locals);
        return null;
    }
    
    @Override
    public Void visit(VarDecl e) {
        String prefix;

        if (e instanceof AgreeVar) {
            AgreeVar var = (AgreeVar) e;

            if (!isMainNode && !(var.reference instanceof GuaranteeStatement)) {
                return null;
            }

            String category = getCategory(var);
            if (isMainNode) {
                prefix = "";
            } else {
                prefix = category + ".";
            }
            String refStr = getReferenceStr(var);
            renaming.addExplicitRename(prefix + var.id, refStr);
            renaming.addToRefMap(prefix + var.id, var.reference);

            if (!layout.getCategories().contains(category)) {
                layout.addCategory(category);
            }
            layout.addElement(category, refStr, SigType.INPUT);

        }
        return null;
    }

    private String getReferenceStr(AgreeVar var) {

        String prefix = getCategory(var);
        if (prefix == null) {
            return null;
        }
        if (var.id.endsWith(AgreeASTBuilder.clockIDSuffix)) {
            return null;
        }

        String seperator = (prefix == "" ? "" : ".");
        EObject reference = var.reference;
        if (reference instanceof GuaranteeStatement) {
            return ((GuaranteeStatement) reference).getStr();
        } else if (reference instanceof AssumeStatement) {
            return prefix + " assume: " + ((AssumeStatement) reference).getStr();
        } else if (reference instanceof LemmaStatement) {
            return prefix + " lemma: " + ((LemmaStatement) reference).getStr();
        } else if (reference instanceof AssertStatement) {
            throw new AgreeException("We really didn't expect to see an assert statement here");
        } else if (reference instanceof Arg) {
            return prefix + seperator + ((Arg) reference).getName();
        } else if (reference instanceof DataPort) {
            return prefix + seperator + ((DataPort) reference).getName();
        } else if (reference instanceof EventDataPort) {
            return prefix + seperator + ((EventDataPort) reference).getName()+"._EVENT_";
        } else if (reference instanceof FeatureGroup) {
            return prefix + seperator + ((FeatureGroup) reference).getName();
        } else if (reference instanceof PropertyStatement) {
            return prefix + seperator + ((PropertyStatement) reference).getName();
        } else if (reference instanceof ComponentType || reference instanceof ComponentImplementation) {
            return "Result";
        }
        throw new AgreeException("Unhandled reference type: '" + reference.getClass().getName() + "'");
    }
    
    private String getCategory(AgreeVar var) {
        if (var.compInst == null || var.reference == null) {
            return null;
        }
        return LustreAstBuilder.getRelativeLocation(var.compInst.getInstanceObjectPath());
    }
    
}
