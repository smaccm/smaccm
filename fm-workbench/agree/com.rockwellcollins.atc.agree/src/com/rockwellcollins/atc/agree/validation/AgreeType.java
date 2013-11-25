package com.rockwellcollins.atc.agree.validation;

public class AgreeType {
    public static final AgreeType BOOL = new AgreeType("bool");
    public static final AgreeType INT = new AgreeType("int");
    public static final AgreeType REAL = new AgreeType("real");
    public static final AgreeType ERROR = new AgreeType("<error>");

    private String name;

    public AgreeType(String name) {
        this.name = name;
    }

    public boolean isPrimitive() {
        return equals(BOOL) || equals(INT) || equals(REAL);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AgreeType) {
            AgreeType other = (AgreeType) o;
            return name.equals(other.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
