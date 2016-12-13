package edu.uah.rsesc.aadlsimulator.ui.services;

@SuppressWarnings("serial")
public class StepForwardCanceledException extends Exception {
	public StepForwardCanceledException(final Throwable cause) {
		super(cause.getMessage(), cause);
	}
}
