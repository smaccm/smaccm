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

package com.rockwellcollins.atc.tcg.preferences;

public class TcgPreferenceConstants {

	/* strings for field editor names */

	/* checkboxes */
	public static final String PREF_CB_UFC = "tcgUFC";
	public static final String PREF_CB_BOUNDARY_VALUE_CHECKS = "tcgBoundaryValueChecks";
	public static final String PREF_CB_GEN_TESTS_FOR_ASSUMPTIONS = "tcgGenTestsForAssumptions";
	public static final String PREF_CB_GEN_TESTS_FOR_EQUATIONS = "tcgGenTestsForEquations";
	public static final String PREF_CB_AUTO_SAVE = "tcgAutoSave";

	/* radio button group */
	public static final String PREF_RB_AUTO_SAVE_TYPE = "tcgAutoSaveType";         // group
	public static final String PREF_RB_CHOICE_NEW_FILE = "tcgAutoSaveToNewFile";   // choice
	public static final String PREF_RB_CHOICE_OLD_FILE = "tcgAutoSaveToOldFile";   // choice
	
	/* file choosers */
	public static final String PREF_AUTO_SAVE_OLD_FILENAME = "tcgNewFilename";
	public static final String PREF_AUTO_SAVE_NEW_FILENAME = "tcgOldFilename";
	public static final String PREF_MONOLITHIC_ANALYSIS = "tcgMonolithicAnalysis";
}
