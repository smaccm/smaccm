/*
Copyright (c) 2016, Rockwell Collins.
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
package com.rockwellcollins.atc.tcg.standalone;

import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import com.rockwellcollins.atc.tcg.lustre.visitors.GenerateUfcObligationsVisitor;
import jkind.lustre.Program;
import jkind.lustre.parsing.LustreLexer;
import jkind.lustre.parsing.LustreParser;
import jkind.lustre.parsing.LustreToAstVisitor;

public class StandaloneTcg {
	public static class Arguments {
		public final static String OPTION_GENERATE_BOUNDARY_VALUE_TESTS = "--bvt";
		
		public String inputFilename;
		public String outputFilename;
		public boolean enableBoundaryValueTests = false;
		public boolean mainNodeOnly = true;
		
		public boolean isValid() {
			return inputFilename != null && outputFilename != null;
		}
		
		/**
		 * Returns a valid Arguments object or null if the parsed arguments were not valid.
		 * @param args
		 * @return
		 */
		public static Arguments parse(final String[] args) {
			final Arguments appArgs = new Arguments();
			for(final String arg : args) {
				if(arg.startsWith("--")) {
					switch(arg) {
					case OPTION_GENERATE_BOUNDARY_VALUE_TESTS:
						appArgs.enableBoundaryValueTests = true;					
						break;
						
					default:
						return null;
					}
				} else {
					if(appArgs.inputFilename == null) {
						appArgs.inputFilename = arg;
					} else if(appArgs.outputFilename == null) {
						appArgs.outputFilename = arg;
					} else {
						return null;
					}
				}
			}
			
			if(!appArgs.isValid()) {
				return null;
			}
			
			return appArgs;
		}
	}
		
	public static void main(String[] args) throws IOException {
		// Parse command line arguments
		final Arguments appArgs = Arguments.parse(args);
		if(appArgs == null) {
			System.err.println("Usage: java -jar tcg.jar [options] <input_filename> <output_filename>");
			System.err.println("Options:");
			System.err.println(Arguments.OPTION_GENERATE_BOUNDARY_VALUE_TESTS + "            Enables generation of boundary value tests.");
			return;
		}
				
		// Parse the lustre file
		final LustreLexer lexer = new LustreLexer(new ANTLRFileStream(appArgs.inputFilename));
		final CommonTokenStream tokens = new CommonTokenStream(lexer);
		final LustreParser parser = new LustreParser(tokens);
		final Program program = new LustreToAstVisitor().program(parser.program());
		
		// Process it
		final GenerateUfcObligationsVisitor visitor = new GenerateUfcObligationsVisitor(program, null);
		visitor.setGenerateAssumptionObligations(false);		
		visitor.setGenerateEqObligations(false);
		visitor.setGenerateGuaranteeObligations(false);
		visitor.setGenerateBoundaryValueTests(appArgs.enableBoundaryValueTests);
		visitor.setGeneratePropertyObligations(true);		
		program.accept(visitor);
	
		// Construct the new program
		final Program outProgram = visitor.constructNewProgram();
		
		// Write output
		try(PrintWriter out = new PrintWriter(appArgs.outputFilename)) {
		    out.println(outProgram);
		}
	}

}
