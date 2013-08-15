package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.impl.DataPortImpl;

public class DataPortImplWrapper {

    DataPortImpl impl;
    ThreadWrapper owner;
    Model model;

    public DataPortImplWrapper(Model model, DataPortImpl impl) {
        this.model = model;
        this.impl = impl;
    }

    public String getName() {
        return impl.getName();
    }

    public Type getType() {
        return model.lookupType((DataClassifier) impl.getClassifier());
    }

    @Override
    public String toString() {
        return "DataPortImplWrapper " + getName() + " : " + (getType().toString());
    }

    @Override
    public int hashCode() {
        return impl.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DataPortImplWrapper) {
            DataPortImplWrapper dp = (DataPortImplWrapper) obj;
            return impl.equals(dp.impl);
        } else {
            return false;
        }
    }

}
