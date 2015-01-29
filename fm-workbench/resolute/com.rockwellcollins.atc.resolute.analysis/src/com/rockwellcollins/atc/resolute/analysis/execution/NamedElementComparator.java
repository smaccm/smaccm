package com.rockwellcollins.atc.resolute.analysis.execution;

import java.util.Comparator;

import org.osate.aadl2.NamedElement;

public class NamedElementComparator implements Comparator<NamedElement> {
    @Override
    public int compare(NamedElement arg0, NamedElement arg1) {
        if (arg0 == arg1) {
            return 0;
        }
        
        int r = arg0.getName().compareToIgnoreCase(arg1.getName());
        if (r != 0) {
            return r;
        }
        
        r = Integer.compare(arg0.hashCode(), arg1.hashCode());
        if (r == 0) {
            throw new IllegalArgumentException("Unable to order distinct objects via hashcode");
        }
        return r;
    }
}
