package edu.umn.cs.crisys.smaccm.aadl2rtos.gluecode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Logger;
import edu.umn.cs.crisys.smaccm.aadl2rtos.AstHelper;
import edu.umn.cs.crisys.smaccm.aadl2rtos.Model;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.MyPort;
import edu.umn.cs.crisys.smaccm.aadl2rtos.thread.ThreadImplementation;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Pair;
import edu.umn.cs.crisys.smaccm.aadl2rtos.util.Util;

// TODO: need to discuss where to place .c / .h files for User-provided thread functions

public class BrtosEntrypointSkeletonGenerator {
	private Model model;
	private SourceWriter sourceWriter;
	private HeaderWriter headerWriter;
	private AstHelper astHelper;

	private Logger log;
	private List<MyPort> events;
	// private Map<ThreadImplementation, Set<Pair<MyPort, MyPort>>> threadSourcePorts;

	private File CFile;
	private File HFile;

	public List<ThreadImplementation> allThreads;

	// so write threadName_write_portName for each port.

	public BrtosEntrypointSkeletonGenerator(Logger log, Model model, File dir, AstHelper astHelper) {
		this.log = log;
		this.astHelper = astHelper;
		this.model = model;
		this.allThreads = model.getThreadImplementations();
		// this.threadSourcePorts = model.getThreadSourcePorts();

		// Create source directories
		File genDir = new File(dir, "gen");
		genDir.mkdirs();

		// Create new source files
		String name = Util.normalizeAadlName(model.getSystemInstanceName());
		CFile = new File(genDir, name + ".c");
		HFile = new File(genDir, name + ".h");

		this.model.getSourceFiles().add(CFile.getPath());

		// Define signal set from event ports
		// write implementations for event ports set the signals
		// main tasking threads wait for signal set
		// and dispatch to appropriate entrypoint

		defineSignalSet();
	}

	public File getHFile() {
		return HFile;
	}

	public File getCFile() {
		return CFile;
	}

	public int getSignalSetSize() {
		return events.size();
	}

	// For each thread, get all events (determined by the out-event ports)
	private void defineSignalSet() {
		events = new ArrayList<MyPort>();		

		for (ThreadImplementation tw : allThreads) {
			events.addAll(tw.getOutputEventPortList());
			events.addAll(tw.getOutputEventDatatPortList());
		}
	}

	public void write() {
		try {
			log.status("Writing AADL middleware header files...");
			BufferedWriter hwriter = new BufferedWriter(new FileWriter(getHFile()));
			this.headerWriter = new HeaderWriter(hwriter, CFile, HFile, model, astHelper);
			headerWriter.writeHeader();

			log.status("Writing AADL middleware source files...");
			BufferedWriter cwriter = new BufferedWriter(new FileWriter(getCFile()));
			this.sourceWriter = new SourceWriter(cwriter, CFile, HFile, model, astHelper, events);
			sourceWriter.writeSource();

			hwriter.close();
			cwriter.close();
		} catch (IOException e) {
			log.error("Unable to create output streams.\n");
		}
	}
}