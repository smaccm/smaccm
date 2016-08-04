package com.rockwellcollins.atc.z3;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class Z3Plugin {
	public static String getZ3Directory() {
		Bundle bundle = Platform.getBundle("com.rockwellcollins.atc.z3");
		String archDir = getArchDir();
		String exeName = getExecutableName();
		URL url = bundle.getEntry("binaries/" + archDir + "/" + exeName);
		try {
			URL fileUrl = FileLocator.toFileURL(url);
			File exe = new File(fileUrl.getPath());
			exe.setExecutable(true);
			return exe.getParent();
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to extract z3 from plug-in", e);
		}
	}

	private static String getArchDir() {
		String name = System.getProperty("os.name").toLowerCase();
		String arch = System.getProperty("os.arch").toLowerCase();
		
		if (name.contains("windows")) {
			if (arch.contains("64")) {
				return "x64-win";
			} else {
				return "x86-win";
			}
		} else if (name.contains("mac os x")) {
			return "x64-osx";
		} else if (arch.contains("64")) {
			return "x64-linux";
		} else {
			return "x86-linux";
		}
	}

	private static String getExecutableName() {
		boolean isWindows = System.getProperty("os.name").startsWith("Windows");
		return isWindows ? "z3.exe" : "z3";
	}
}
