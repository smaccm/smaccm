package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

public class PeriodicDispatchMechanism extends DispatchMechanism {

	private double periodInMilliseconds; 
	public PeriodicDispatchMechanism(double _periodInMilliseconds) {
		periodInMilliseconds = _periodInMilliseconds;
	}
	
	public double getPeriodInMilliseconds() { return periodInMilliseconds; }
	
	@Override
	public String toString() {
		return "Periodic Dispatch Mechanism with interval " + periodInMilliseconds;
	}

	@Override
	public int hashCode() {
		return new Double(periodInMilliseconds).intValue();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PeriodicDispatchMechanism) {
			PeriodicDispatchMechanism dp = (PeriodicDispatchMechanism)obj;
			// use of == for double.
			return (getPeriodInMilliseconds() == dp.getPeriodInMilliseconds());
		}
		else 
			return false;
	}

}
