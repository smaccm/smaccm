package edu.uah.rsesc.aadlsimulator.agree;

public enum SimulationProgramType {
	MONOLITHIC("Monolithic", true),
	SINGLE_LAYER("Single Layer", false);
	
	private final String name;
	private final boolean monolithic;
	
	SimulationProgramType(final String name, final boolean monolithic) {
		this.name = name;
		this.monolithic = monolithic;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isMonolithic() {
		return monolithic;
	}
}
