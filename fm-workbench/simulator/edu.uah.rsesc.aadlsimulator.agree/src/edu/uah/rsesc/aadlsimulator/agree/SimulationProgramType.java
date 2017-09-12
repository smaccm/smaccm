package edu.uah.rsesc.aadlsimulator.agree;

public enum SimulationProgramType {
	MONOLITHIC("Monolithic", true, false),
	MONOLITHIC_INDUCTIVE("Monolithic Inductive", true, true),
	SINGLE_LAYER("Single Layer", false, false),
	SINGLE_LAYER_INDUCTIVE("Single Layer Inductive", false, true);
	
	private final String name;
	private final boolean monolithic;
	private final boolean inductive;
	
	SimulationProgramType(final String name, final boolean monolithic, final boolean inductive) {
		this.name = name;
		this.monolithic = monolithic;
		this.inductive = inductive;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isMonolithic() {
		return monolithic;
	}
	
	public boolean isInductive() {
		return inductive;
	}
}
