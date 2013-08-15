package com.rockwellcollins.atc.resolute.analysis;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public class ResoluteProofNode implements Comparable<ResoluteProofNode> {
    final private ResoluteProofNode parent;
    final private EObject eobj;

    private ResoluteValue returnVal;
    private List<ResoluteProofNode> children;
    private String exprStr;
    private int numDescendants;
    private Map<String, EObject> claimReferences;

    /****** begin constructors *********/

    public ResoluteProofNode(ResoluteProofNode parent, EObject eobj) {
        this.parent = parent;
        this.eobj = eobj;
        this.children = new LinkedList<>();
        this.claimReferences = new HashMap<>();
    }

    /****** begin get methods **********/

    public EObject getEObject() {
        return eobj;
    }

    public int getNumDescendants() {
        return numDescendants;
    }

    public ResoluteProofNode getParent() {
        return parent;
    }

    public String getExprStr() {
        return exprStr;
    }

    public ResoluteValue getRetVal() {
        return returnVal;
    }

    public List<ResoluteProofNode> getChildren() {
        return children;
    }

    public Map<String, EObject> getClaimReferences() {
        return claimReferences;
    }

    /****** begin set methods *********/

    public void setNumDescendants(int i) {
        numDescendants = i;
    }

    public void setExprStr(String exprStr) {
        this.exprStr = exprStr;
    }

    public void setRetVal(ResoluteValue returnVal) {
        this.returnVal = returnVal;
    }

    /******* begin utility methods *******/

    public void addChild(ResoluteProofNode node) {
        children.add(node);
    }

    public void addClaimReference(String name, EObject eObj) {
        claimReferences.put(name, eObj);
    }

    public void removeChildIndex(int i) {
        assert (i < children.size());
        children.remove(i);
    }

    public void sortChildrenByNumDescendants() {
        Collections.sort(children);
    }

    // note that this compares the wrong direction
    // we want descending
    @Override
    public int compareTo(ResoluteProofNode otherNode) {
        return otherNode.numDescendants - this.numDescendants;
    }
}
