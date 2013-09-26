package com.rockwellcollins.atc.agree.analysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ContainedNamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.Subcomponent;
import org.osate.aadl2.SystemImplementation;

public class DeclarativePropertyBuilder {
    
    private Map<Subcomponent, List<Object>> subCompProps = new HashMap<>();
    private SystemImplementation sysImpl;

    public DeclarativePropertyBuilder(SystemImplementation sysImpl){
        this.sysImpl = sysImpl;
    }

    /*
    public void gatherProperties(ComponentImplementation compImpl){
        
        for(Subcomponent subComp : compImpl.getAllSubcomponents()){
            
            for(PropertyAssociation propAssoc : subComp.getOwnedPropertyAssociations()){
                Property prop = propAssoc.getProperty();
                for(ContainedNamedElement contEl : propAssoc.getAppliesTos()){
                    contEl.
                }
            }
        }
        
    }
    */
    
    
}
