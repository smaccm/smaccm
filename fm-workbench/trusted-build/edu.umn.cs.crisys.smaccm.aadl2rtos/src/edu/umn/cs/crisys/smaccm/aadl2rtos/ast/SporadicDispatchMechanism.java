package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

public class SporadicDispatchMechanism extends DispatchMechanism {

    String trigger;

    public SporadicDispatchMechanism(String _trigger) {
        trigger = _trigger;
    }

    public String getTrigger() {
        return trigger;
    }

    @Override
    public String toString() {
        return "Aperiodic Dispatch Mechanism with trigger " + trigger;
    }

    @Override
    public int hashCode() {
        return trigger.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SporadicDispatchMechanism) {
            SporadicDispatchMechanism dp = (SporadicDispatchMechanism) obj;
            return getTrigger().equals(dp.getTrigger());
        } else {
            return false;
        }
    }

}
