/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.gluecode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// import org.apache.commons.io.FilenameUtils;

import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Model;

/**
 * @author Whalen
 * 
 * For the make stuff, I'm struggling a bit to determine 
 * whether Java is the right language for it.  This part 
 * of the exercise is relatively grubby.  We make the 
 * makefile, but expect files to be in specific locations.
 * 
 * What we will get from AADL is a list of .c and .h files 
 * that are listed as sources in AADL as well as the 
 * generated file that we create for all of the stub code.
 * In addition, we may get .o files that represent 
 * pre-built object code.
 * 
 * From the 'prj gen <config>' we will get, in the same 
 * directory as the config file, a handful of stock .c, .h 
 * and .s files that have to be built.
 * 
 * We will build the files in their native directories but 
 * copy the .o files to a common location.  This should 
 * make sure that any relative paths in the .c files are 
 * maintained.  The link will be in terms of all of these
 * .o files in a common location.  This way we will avoid
 * cluttering up the native code locations.
 * 
 * O.k., for shared variables, how do we move beyond processes?
 * Simply by defining data access at different levels.
 * For now, we don't care; just need processes.  Eventually
 * we will want systems.
 * 
 *   shared_dt: requires data access dt.rec 
 *    { Access_Right => read_only; };
 *
 * 
 */
public class MakefileWriter {

  Model model;
  File file;
  File outputDirectory;
  String outputDirectoryName;
  
  
  List<String> cppFiles = new ArrayList<String>();
  List<String> cFiles = new ArrayList<String>();
  List<String> sFiles = new ArrayList<String>();
  List<String> oFiles = new ArrayList<String>();
  List<String> aFiles = new ArrayList<String>();
  
  public MakefileWriter(String outputDirectoryName, File file, Model model) {
    this.model = model;
    this.file = file;
    this.outputDirectoryName = outputDirectoryName;
    outputDirectory = new File(outputDirectoryName);
    
    // add the directories if they do not exist.
    outputDirectory.mkdirs();
    
    Set<String> sourceFiles = model.getSourceFiles();
    List<String> libraryFiles = model.getLibraryFiles();
    
    for (String s: sourceFiles) {
      if (s.endsWith(".c")) {
        cFiles.add(s);
      } else if (s.endsWith(".cpp")) {
    	  cppFiles.add(s);
      } else if (s.endsWith(".s")) {
        sFiles.add(s);
      } else if (s.endsWith(".o")) {
        oFiles.add(s);
      } else {
        throw new Aadl2RtosException("Unknown file type: " + s + "when creating makefile.");
      }
    }
  
    for (String s: libraryFiles) {
        if (s.endsWith(".a")) {
      	  aFiles.add(s);
        } else {
          throw new Aadl2RtosException("Unknown file type: " + s + "when creating makefile.");
        }
      }
   
    /*
     * add OS files to files.
     */
    cFiles.add("generic.debug.c");
    cFiles.add("rtos-kochab.c");
    
    sFiles.add("armv7m.exception-preempt.s");
    sFiles.add("ctxt-switch.s");
    sFiles.add("vectable.s");
    sFiles.add("semihost-debug.s");
  }

  String getOutputFile(String file) {
    // TODO: Really should use Apache Commons IO
    //String noExt = FilenameUtils.removeExtension(file);
    String fName = new File(file).getName();
    String noExt = fName.substring(0, fName.lastIndexOf('.'));
    
    String outputFile = "$(OUT_DIR)" + noExt + ".o";
    return outputFile;
  }
  
  String getParentDirectory(String file) {
    File f = new File(file);
    return f.getParent();
  }

  String getParentDirectoryString(String cFile) {
    String parentDirectory = getParentDirectory(cFile);
    String parentDirString = "";
    if (parentDirectory != "" && parentDirectory != null) {
      parentDirString = "-I" + parentDirectory;
    } 
    return parentDirString;
  }
  
  String buildCFileString(String cFile) {
    String outputFile = this.getOutputFile(cFile);
    String parentDirectory = getParentDirectoryString(cFile); 
    String build = 
        "\tarm-none-eabi-gcc -ffreestanding -c " + cFile + " -o " + outputFile + 
        " $(CFLAGS) -I. " + parentDirectory + " \n";  
    return build;
  }
  
  String buildCppFileString(String cFile) {
	    String outputFile = this.getOutputFile(cFile);
	    String parentDirectory = getParentDirectoryString(cFile); 
	    String build = 
	        "\tarm-none-eabi-g++ -ffreestanding -c " + cFile + " -o " + outputFile + 
	        " $(CFLAGS) -I. " + parentDirectory + " \n";  
	    return build;
	  }

  String buildSFileString(String sFile) {
    // TODO: Really should use Apache Commons IO
    String outputFile = this.getOutputFile(sFile);
    String parentDirectory = getParentDirectoryString(sFile);
    String build = 
        "\tarm-none-eabi-as -o " + outputFile + " " + sFile + " $(ASFLAGS) -I. " + parentDirectory +" \n"; 
    return build;
  }
  
  String buildLinkerString() {
    StringBuffer sb = new StringBuffer("\tarm-none-eabi-ld -T default.ld -o system "); 
    for (String str: cFiles) {
      sb.append(" " + getOutputFile(str));
    }
    for (String str: cppFiles) {
        sb.append(" " + getOutputFile(str));
      }
    for (String str: sFiles) {
      sb.append(" " + getOutputFile(str));
    }
    for (String str: oFiles) {
      sb.append(" " + str);
    }
    for (String str: aFiles) {
      sb.append(" " + str);
    }
    return sb.toString();
  }
  
  public void writeMakeFile() throws IOException {
    BufferedWriter out = new BufferedWriter(new FileWriter(this.file));
    
    
    /*
     * Note: assumes output directory has been created.
     */
    
    
    String header = 
        "# -*- Mode: makefile-gmake; indent-tabs-mode: t; tab-width: 2 -*-\n" + 
        "#\n" + 
        "# Makefile --- STM324 firmware build system.\n" + 
        "#\n" + 
        "# Copyright (C) 2012, Galois, Inc.\n" + 
        "# All Rights Reserved.\n" + 
        "#\n" + 
        "# This software is released under the \"BSD3\" license.  Read the file\n" + 
        "# \"LICENSE\" for more information.\n" + 
        "#\n" + 
        "\n" + 
        "OUT_DIR := " + outputDirectory + "\n" + 
        "CONFIG_TOOLCHAIN := cortex-m4\n" + 
        "CONFIG_CORTEX_M4_PREFIX := arm-none-eabi-\n" + 
        "CONFIG_BOARD := px4\n" + 
        "\n" + 
        "include mk/arch/$(CONFIG_TOOLCHAIN).mk\n" + 
        "include mk/board/$(CONFIG_BOARD).mk\n" + 
        "include mk/export.mk\n" + 
        "\n" +
        "CFLAGS += -DIVORY_DEPLOY\n\n" + 
        "all:\n";
    
    out.append(header);
    for (String str: cFiles) {
      out.append(buildCFileString(str));
    }
    for (String str: cppFiles) {
    	out.append(buildCppFileString(str));
    }
    for (String str: sFiles) {
      out.append(buildSFileString(str));
    }
    out.append(buildLinkerString());
    out.close();
  }
}

/* EXAMPLE: 
# -*- Mode: makefile-gmake; indent-tabs-mode: t; tab-width: 2 -*-
#
# Makefile --- STM324 firmware build system.
#
# Copyright (C) 2012, Galois, Inc.
# All Rights Reserved.
#
# This software is released under the "BSD3" license.  Read the file
# "LICENSE" for more information.
#

CONFIG_TOOLCHAIN := cortex-m4
CONFIG_CORTEX_M4_PREFIX := arm-none-eabi-
CONFIG_BOARD := px4

include mk/arch/$(CONFIG_TOOLCHAIN).mk
include mk/board/$(CONFIG_BOARD).mk
include mk/export.mk

all:
  arm-none-eabi-gcc -ffreestanding -c generic.debug.c -o out/test__Test_Impl/generic.debug.o $(CFLAGS) -Iout/kochab-system
  arm-none-eabi-gcc -ffreestanding -c rtos-kochab.c -o out/test__Test_Impl/rtos-kochab.o $(CFLAGS) -Iout/kochab-system
  arm-none-eabi-gcc -ffreestanding -c test.c -o out/test__Test_Impl/test.o $(CFLAGS) -Iout/kochab-system
  arm-none-eabi-gcc -ffreestanding -c led.c -o out/test__Test_Impl/led.o $(CFLAGS) -I.
  arm-none-eabi-gcc -ffreestanding -c gpio.c -o out/test__Test_Impl/gpio.o $(CFLAGS) -I.
  arm-none-eabi-gcc -ffreestanding -c rcc.c -o out/test__Test_Impl/rcc.o $(CFLAGS) -I.
  arm-none-eabi-gcc -ffreestanding -c gen/Test_Impl_Instance.c -o out/test__Test_Impl/Test_Impl_Instance.o $(CFLAGS) -I.
  arm-none-eabi-as -o out/test__Test_Impl/armv7m.exception-preempt.o armv7m.exception-preempt.s $(ASFLAGS) -Iout/kochab-system
  arm-none-eabi-as -o out/test__Test_Impl/ctxt-switch.o ctxt-switch.s $(ASFLAGS) -Iout/kochab-system
  arm-none-eabi-as -o out/test__Test_Impl/vectable.o vectable.s $(ASFLAGS) -Iout/kochab-system
  arm-none-eabi-as -o out/test__Test_Impl/semihost-debug.o semihost-debug.s $(ASFLAGS) -Iout/kochab-system
  arm-none-eabi-ld -T default.ld -o out/test__Test_Impl/system out/test__Test_Impl/ctxt-switch.o out/test__Test_Impl/armv7m.exception-preempt.o out/test__Test_Impl/vectable.o out/test__Test_Impl/semihost-debug.o out/test__Test_Impl/generic.debug.o out/test__Test_Impl/rtos-kochab.o out/test__Test_Impl/test.o out/test__Test_Impl/Test_Impl_Instance.o out/test__Test_Impl/led.o out/test__Test_Impl/gpio.o out/test__Test_Impl/rcc.o 
*/