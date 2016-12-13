package edu.uah.rsesc.aadlsimulator.ui.events;

import java.util.Objects;

public class BeforeStepForwardEvent {
	private Throwable stepCancellationReason = null;
	
	public boolean isStepCanceled() {
		return stepCancellationReason != null;
	}

	// If the step has been canceled, this method is guaranteed to return a non-null value
	public Throwable getCancellationReason() {
		return stepCancellationReason;
	}
	
	public void cancelStep(final Throwable reason) {
		stepCancellationReason = Objects.requireNonNull(reason, "reason must not be null");
	}
}
