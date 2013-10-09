package com.rockwellcollins.atc.agree.analysis;

public class AgreeVarDecl implements Comparable<AgreeVarDecl> {
    public String jKindStr = null;
    public String aadlStr = null;
    public String type = null;

    @Override
    public int hashCode() {
        return jKindStr.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AgreeVarDecl)) {
            return false;
        }
        return ((AgreeVarDecl) o).jKindStr.equals(jKindStr);
    }

    @Override
    public int compareTo(AgreeVarDecl o) {
        return jKindStr.compareTo(o.jKindStr);
    }

}