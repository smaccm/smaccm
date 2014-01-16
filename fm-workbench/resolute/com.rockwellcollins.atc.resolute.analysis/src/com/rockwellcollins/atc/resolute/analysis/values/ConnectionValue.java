package com.rockwellcollins.atc.resolute.analysis.values;

import org.osate.aadl2.instance.ConnectionInstance;

import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteFailException;

public class ConnectionValue extends NamedElementValue {
    public final boolean fReverseDirection;

    public ConnectionValue(ConnectionInstance value, boolean fReverseDirection) {
        super(value);
        this.fReverseDirection = fReverseDirection;
        if (fReverseDirection && !value.isBidirectional()) {
            throw new ResoluteFailException(
                    "Attempt at reversing the direction of a non-bidirection connection", value);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return ((ConnectionValue) obj).fReverseDirection == this.fReverseDirection;
        }
        return false;
    }
}
