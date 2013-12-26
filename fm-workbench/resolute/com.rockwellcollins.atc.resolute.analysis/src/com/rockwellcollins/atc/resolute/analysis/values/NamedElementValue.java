package com.rockwellcollins.atc.resolute.analysis.values;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;

public class NamedElementValue extends ResoluteValue {
    final private NamedElement value;

    public NamedElementValue(NamedElement value) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot create null NamedElementValue");

        }
        this.value = value;
    }

    @Override
    public boolean isNamedElement() {
        return true;
    }

    @Override
    public NamedElement getNamedElement() {
        return value;
    }

    @Override
    public String toString() {
        if (value instanceof ComponentInstance) {
            ComponentInstance ci = (ComponentInstance) value;
            ComponentClassifier cc = ci.getComponentClassifier();
            return value.getName() + " : " + cc.getQualifiedName();
        }
        
        if(value instanceof FeatureInstance){
            EObject container = value.eContainer();
            StringBuilder sb = new StringBuilder();
            while(container.eContainer() instanceof ComponentInstance){
                sb.insert(0, ".");
                sb.insert(0, ((NamedElement)container).getQualifiedName());
                container = container.eContainer();
            }
            sb.append(value.getQualifiedName());
            return sb.toString();
        }

        return value.getQualifiedName();
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NamedElementValue) {
            NamedElementValue nev = (NamedElementValue) obj;
            return nev.value.equals(value);
        }

        return false;
    }
}
