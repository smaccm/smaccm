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

package com.rockwellcollins.atc.tcg.writers;

import java.io.File;
import java.util.Date;

public class TcgWriterUtils {

	public static String createUniqueFileName(String path) {
		File userFile = new File(path);
		String filename = userFile.getName();
		String base; 
		String ext;
		if (filename.indexOf(".") > 0) {
		    base = filename.substring(0, filename.lastIndexOf("."));
		    ext = filename.substring(filename.lastIndexOf("."));
		} else {
			base = filename;
			ext = "";
		}
		
		String DATE_FORMAT="yyyy-MM-dd_HH-mm-ss";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(DATE_FORMAT);
		File newUserFile = new File(userFile.getParent(), base + "_" + sdf.format(new Date()) + ext);
		return newUserFile.getAbsolutePath();
	}

	public static String getFileNameNoExtension(String path) {
		File userFile = new File(path);
		String filename = userFile.getName();
		String base; 
		if (filename.indexOf(".") > 0) {
		    base = filename.substring(0, filename.lastIndexOf("."));
		} else {
			base = filename;
		}
		return base;
	}
}
