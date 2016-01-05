package com.rockwellcollins.atc.resolute.analysis.values;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.ConnectionInstance;
import org.osate.aadl2.instance.FeatureInstance;

import com.rockwellcollins.atc.resolute.validation.BaseType;
import com.rockwellcollins.atc.resolute.validation.ResoluteType;

public class NamedElementValue extends ResoluteValue {
    final protected NamedElement value;

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
    public ResoluteType getType() {
        if (value instanceof FeatureInstance) {
            FeatureInstance fi = (FeatureInstance) value;
            return new BaseType(fi);
        } else if (value instanceof ConnectionInstance) {
            return BaseType.CONNECTION;
        } else if (value instanceof ComponentInstance) {
            ComponentInstance ci = (ComponentInstance) value;
            return new BaseType(ci.getCategory());
        } else {
            throw new IllegalArgumentException("Unknown NamedElement type: "
                    + value.getClass().getName());
        }
    }

    @Override
    public String toString() {
        if (value instanceof ComponentInstance) {
            ComponentInstance ci = (ComponentInstance) value;
            ComponentClassifier cc = ci.getComponentClassifier();
            String qualifiedName = cc == null ? "<no classifier>" : cc.getQualifiedName();
            return value.getName() + " : " + qualifiedName;
        }

        if (value instanceof FeatureInstance) {
            EObject container = value.eContainer();
            StringBuilder sb = new StringBuilder();
            while (container.eContainer() instanceof ComponentInstance) {
                sb.insert(0, ".");
                sb.insert(0, ((NamedElement) container).getQualifiedName());
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
