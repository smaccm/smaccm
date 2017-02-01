package edu.uah.rsesc.aadlsimulator.agree.sim;

public class ConstraintEvaluationException extends AGREESimulatorException {
	private static final long serialVersionUID = 8329894676953987504L;

	public ConstraintEvaluationException(final String msg, final Throwable cause) {
		super(msg + (cause == null ? "" : "\n" + cause.getMessage()), cause);
	}
}
