package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import org.osate.aadl2.DataClassifier;
import org.osate.aadl2.DirectionType;
import org.osate.aadl2.PortCategory;
import org.osate.aadl2.impl.PortImpl;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Util;

public class PortImplWrapper {

    public PortImpl impl;
    Model model;

    public PortImplWrapper(Model model, PortImpl impl) {
        this.model = model;
        this.impl = impl;
    }

    public String getName() {
        return impl.getName();
    }

    public Type getType() {
        return model.lookupType((DataClassifier) impl.getClassifier());
    }

    public DirectionType getDirection() {
        return impl.getDirection();
    }

    public PortCategory getCategory() {
        return impl.getCategory();
    }

    public String getComputeEntrypointOpt() {
        return Util.getStringValueOpt(impl, ThreadWrapper.COMPUTE_ENTRYPOINT_SOURCE_TEXT);
    }

    @Override
    public String toString() {
        return "PortImplWrapper " + getName() + " : " + (getType().toString());
    }

    @Override
    public int hashCode() {
        return impl.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PortImplWrapper) {
            PortImplWrapper dp = (PortImplWrapper) obj;
            return impl.equals(dp.impl);
        } else {
            return false;
        }
    }

}
