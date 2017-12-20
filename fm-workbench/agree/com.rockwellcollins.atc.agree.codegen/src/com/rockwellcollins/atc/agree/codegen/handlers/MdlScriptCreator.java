package com.rockwellcollins.atc.agree.codegen.handlers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;

import com.rockwellcollins.atc.agree.codegen.ast.MATLABBusType;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABPort;
import com.rockwellcollins.atc.agree.codegen.ast.MATLABType;

import jkind.Assert;

public class MdlScriptCreator extends ScriptCreator {

	final String outputDir;
	final String implMdl;
	final String verifMdlName;
	final String subSysName;
	final String scriptName;
	final boolean invokeSLDV;
	final boolean createImplModel;
	List<MATLABPort> ports = new ArrayList<>();
	List<String> inportnames = new ArrayList<>();
	List<String> inporttypes = new ArrayList<>();
	List<String> outportnames = new ArrayList<>();
	List<String> outporttypes = new ArrayList<>();
	public HashMap<String, SortedMap<String, MATLABType>> busdefs = new HashMap<>();

	public MdlScriptCreator(String outputDir, String implMdl, String verifMdlName, String subSysName,
			List<MATLABPort> ports, String scriptName, boolean createImplModel, boolean invokeSLDV) throws Exception {
		Assert.isNotNull(outputDir);
		Assert.isNotNull(implMdl);
		Assert.isNotNull(verifMdlName);
		Assert.isNotNull(subSysName);
		Assert.isNotNull(ports);
		Assert.isNotNull(scriptName);
		Assert.isNotNull(createImplModel);
		Assert.isNotNull(invokeSLDV);
		this.outputDir = outputDir;
		this.implMdl = implMdl;
		this.verifMdlName = verifMdlName;
		this.subSysName = subSysName;
		this.ports = ports;
		this.scriptName = scriptName;
		this.createImplModel = createImplModel;
		this.invokeSLDV = invokeSLDV;
		preparePorts();
		addCopyrightHeader();
		createScript();
		if (invokeSLDV) {
			invokeSLDVScript();
		}
	}

	private void preparePorts() {
		for (MATLABPort port : ports) {
			if (port.direction.equals("in")) {
				inportnames.add(port.name);
				inporttypes.add(portTypeNameUpdate(port.type));
			} else {
				outportnames.add(port.name);
				outporttypes.add(portTypeNameUpdate(port.type));
			}
			// if the port type is a bus type
			// add the bus def to busdefs (no redundant bus defs)
			if (port.fields != null) {
				busdefs.put(portTypeNameUpdate(port.type), port.fields);
			}
		}
	}

	private String portTypeNameUpdate(MATLABType portType) {
		String typeName = "";
		String originalTypeName = portType.toString();
		if (portType instanceof MATLABBusType) {
			typeName += "Bus: ";
		}

		String[] parts = originalTypeName.split("__");
		int size = parts.length;
		if ((originalTypeName.endsWith("__Impl")) && (size >= 2)) {
			typeName += parts[size - 2];
		} else if (size >= 1) {
			typeName += parts[size - 1];
		} else {
			typeName += originalTypeName;
		}
		return typeName;
	}

	@Override
	protected void createScript() throws Exception {
		boolean firstPort = true;
		boolean firstBusObj = true;
		boolean firstSignal = true;
		writeline("%% This script generates an implementation or verification model for the given component");
		writeline("%% Initialize variables that come from the dialog in OSATE%%");
		writeline("% 'Output Directory'");
		writeline("dir_path = " + "'" + outputDir + "';");
		writeline("% 'Model to Insert Observer'");
		writeline("implMdlPath = '" + implMdl + "';");
		writeline("% 'Subsystem to verify'");
		writeline("subsysName = " + "'" + subSysName + "';");
		newline();
		writeline("%% Initialize varables based on generation parameters and AADL elements");
		writeline("% name of verification script (contians AGREE contract scripting)");
		writeline("scriptName = " + "'" + scriptName + "';");
		writeline("% type of model to generate: 1 = implementation model, 2 = verification model");
		write("generate_mode = ");
		if (createImplModel) {
			writeline("1");
		} else {
			writeline("2");
		}
		writeline("%% the I/O from AADL");
		firstPort = true;
		write("inportnames = {");
		for (String inName : inportnames) {
			if (!firstPort) {
				write(", ");
			}
			write("'" + inName + "'");
			firstPort = false;
		}
		write("};");
		newline();
		firstPort = true;
		write("inporttypes = {");
		for (String inType : inporttypes) {
			if (!firstPort) {
				write(", ");
			}
			write("'" + inType + "'");
			firstPort = false;
		}
		write("};");
		newline();
		newline();
		firstPort = true;
		write("outportnames = {");
		for (String outName : outportnames) {
			if (!firstPort) {
				write(", ");
			}
			write("'" + outName + "'");
			firstPort = false;
		}
		write("};");
		newline();
		firstPort = true;
		write("outporttypes = {");
		for (String outType : outporttypes) {
			if (!firstPort) {
				write(", ");
			}
			write("'" + outType + "'");
			firstPort = false;
		}
		writeline("};");
		firstBusObj = true;
		write("busdefs =      {");

		Iterator<Entry<String, SortedMap<String, MATLABType>>> busIterator = busdefs.entrySet().iterator();
		while (busIterator.hasNext()) {
			if (!firstBusObj) {
				writeline(",';'...");
				write("                ");
			}
			Entry<String, SortedMap<String, MATLABType>> busEntry = busIterator.next();
			// print bus type
			write("'" + busEntry.getKey() + "',");
			firstSignal = true;
			Iterator<Entry<String, MATLABType>> elemIterator = busEntry.getValue().entrySet().iterator();
			while (elemIterator.hasNext()) {
				if (!firstSignal) {
					write(",");
				}
				Entry<String, MATLABType> elemEntry = elemIterator.next();
				// print bus element type followed by element ID
				write("'" + portTypeNameUpdate(elemEntry.getValue()) + "','" + elemEntry.getKey() + "'");
				firstSignal = false;
			}
			firstBusObj = false;
		}
		writeline(",';'};");
		newline();
		writeline("%% Initialize varables based on general settings, not set by the exporter script");
		newline();
		writeline("% these are computed from variables above");
		writeline("[~,implMdlNameNoExtension,~] = fileparts(implMdlPath);");
		writeline("[~,verifMdlNameNoExtension,~] = fileparts('" + verifMdlName + "');");

		InputStream stream = getClass().getResourceAsStream("/resources/FixedScript.txt");
		String str = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			if (stream != null) {
				while ((str = reader.readLine()) != null) {
					writeline(str);
				}
			}
		} finally {
			try {
				stream.close();
			} catch (Throwable ignore) {
			}
		}

	}

	public void invokeSLDVScript() {
		writeline("opts = sldvoptions;");
		writeline("opts.Mode = 'PropertyProving';");
		writeline("opts.Assertions = 'EnableAll';");
		writeline("opts.AutomaticStubbing = 'on';");
		writeline("opts.ProofAssumptions = 'EnableAll';");
		writeline("opts.ProvingStrategy = 'Prove';");
		writeline("opts.SaveReport = 'on';");
		writeline("opts.OutputDir = './sldv_output';");
		writeline("[status, files] = sldvrun(verifMdlNameNoExtension, opts);");
	}

	public void addCopyrightHeader() throws Exception {
		InputStream stream = getClass().getResourceAsStream("/resources/CopyrightHeader.txt");
		String str = "";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			if (stream != null) {
				while ((str = reader.readLine()) != null) {
					writeline(str);
				}
			}
		} finally {
			try {
				stream.close();
			} catch (Throwable ignore) {
			}
		}
	}
}
