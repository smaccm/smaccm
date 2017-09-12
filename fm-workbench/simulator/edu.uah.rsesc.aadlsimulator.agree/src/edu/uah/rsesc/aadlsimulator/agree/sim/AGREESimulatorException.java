/*
Copyright (c) 2015, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
package edu.uah.rsesc.aadlsimulator.agree.sim;
import edu.uah.rsesc.aadlsimulator.agree.LustreProgramProvider;
import jkind.lustre.Program;

public class AGREESimulatorException extends RuntimeException implements LustreProgramProvider {
	private static final long serialVersionUID = 8367313238232929898L;
	private final Program lustreProgram;
	
	public AGREESimulatorException(final Program lustreProgram, final Throwable cause) {
		this(lustreProgram, cause, null);
	}
	
	public AGREESimulatorException(final Program lustreProgram, final Throwable cause, final String additionalMessage) {
		super("AGREE Simulator Exception : " + 
				(cause.getMessage() == null ? cause.getClass().getName() : cause.getMessage()) +
				(additionalMessage == null ? "" : ("\n" + additionalMessage)), cause);
		this.lustreProgram  = lustreProgram;
	}
	
	public AGREESimulatorException(final String msg, final Program lustreProgram) {
		super(msg);
		this.lustreProgram  = lustreProgram;
	}
	
	public AGREESimulatorException(final String msg, final Throwable cause) {
		super(msg, cause);
		this.lustreProgram = null;
	}
	
	@Override
	public Program getLustreProgram() {
		return lustreProgram;
	}
}
