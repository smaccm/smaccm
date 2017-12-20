package com.rockwellcollins.atc.agree.codegen.handlers;

import java.io.BufferedReader;
import java.io.FileReader;

public abstract class ScriptCreator {
	protected StringBuilder sb = new StringBuilder();

	@Override
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

	protected void writeline(Object o) {
		write(o);
		newline();
	}

	protected abstract void createScript() throws Exception;

	// copy script text from an existing file
	protected void copyScript(String scriptFileName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("CommonScriptImplMdl.txt"));
		try {
			String line = br.readLine();

			while (line != null) {
				write(line);
				newline();
				line = br.readLine();
			}
		} finally {
			br.close();
		}
	}
}
