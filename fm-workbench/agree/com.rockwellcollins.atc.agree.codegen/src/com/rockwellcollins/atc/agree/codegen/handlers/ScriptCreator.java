package com.rockwellcollins.atc.agree.codegen.handlers;

public abstract class ScriptCreator {
	protected StringBuilder sb = new StringBuilder();


	public String toString() {
		return sb.toString();
	}

	protected void write(Object o) {
		sb.append(o);
	}

	protected static final String seperator = System.getProperty("line.separator");

	protected void newline() {
		write(seperator);
	}
	
	protected abstract void createScript();
}
