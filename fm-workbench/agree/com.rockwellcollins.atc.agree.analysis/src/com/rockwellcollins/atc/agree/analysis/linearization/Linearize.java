/**
 * Copyright (c) 2016, Rockwell Collins.
 *
 * Developed with the sponsorship of Defense Advanced Research Projects Agency
 * (DARPA).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this data, including any software or models in source or binary form, as
 * well as any drawings, specifications, and documentation (collectively
 * "the Data"), to deal in the Data without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Data, and to permit persons to whom the
 * Data is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Data.
 *
 * THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE
 * USE OR OTHER DEALINGS IN THE DATA.
 */

package com.rockwellcollins.atc.agree.analysis.linearization;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedList;
import java.util.function.Function;

import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.analysis.AgreeException;

public class Linearize {

	String fn; // Name of function as it will be invoked in AADL
	Function<Double, Double> f; // Function
	double start; // Starting value to linearize from
	double stop; // Stopping value to linearize to
	double bound; // Acceptable bound above and below the actual function
	String ml; // MATLAB specification of the function
	BoundingSegments segments;

	public Linearize(LinearizationDef linDef) {
		fn = linDef.getName();
		// TODO: For now, linearizations are over one variable only
		assert (linDef.getArgs().size() == 1);
		String varName = linDef.getArgs().get(0).getName();
		f = Util.translateNonlinearExpr(linDef.getExprBody(), varName);
		if (linDef.getArgs().size() != 1) {
			throw new AgreeException("Linearization of supported for functions of one variable only.");
		}
		if (linDef.getIntervals().size() != 1) {
			throw new AgreeException("Number of linearization intervals does not match number of args.");
		}
		start = Util.getDoubleValue(linDef.getIntervals().get(0).getStart());
		stop = Util.getDoubleValue(linDef.getIntervals().get(0).getEnd());
		bound = 0.1;
		if (linDef.getPrecision() != null) {
			bound = Util.getDoubleValue(linDef.getPrecision());
		}
		ml = Util.translateNonlinearExprToMatlab(linDef.getExprBody());
		segments = linearize(f, start, stop, bound);
	}

	public Linearize(String fn, Function<Double, Double> f, double start, double stop, double bounds, String matlab) {
		this.fn = fn;
		this.f = f;
		this.start = start;
		this.stop = stop;
		bound = bounds;
		ml = matlab;
		segments = linearize(f, start, stop, bound);
	}

	// Example functions
	@SuppressWarnings("unused")
	private Linearize(String fnm) {
		if (fnm.equals("sqr")) {
			fn = fnm;
			f = (x) -> {
				return x * x;
			};
			start = 0.0;
			stop = 10.0;
			bound = 1.0;
			ml = "x.^2";
		} else if (fnm.equals("cube")) {
			fn = fnm;
			f = (x) -> {
				return x * x * x;
			};
			start = 0.0;
			stop = 10.0;
			bound = 1.0;
			ml = "x.^3";
		} else if (fnm.equals("power1_5")) {
			fn = fnm;
			f = (x) -> {
				return Math.pow(x, 1.5);
			};
			start = 0.0;
			stop = 20.0;
			bound = 1.0;
			ml = "x.^1.5";
		} else if (fnm.equals("reciprocal")) {
			fn = fnm;
			f = (x) -> {
				return 1 / x;
			};
			start = 0.01;
			stop = 100.0;
			bound = 1.0;
			ml = "1 ./ x";
		} else if (fnm.equals("arctan")) {
			fn = fnm;
			f = Math::atan;
			start = -10.0;
			stop = 10.0;
			bound = 0.01;
			ml = "atan(x)";
		} else if (fnm.equals("cos")) {
			fn = fnm;
			f = Math::cos;
			start = 0.0;
			stop = 2.0 * Math.PI;
			bound = 0.01;
			ml = "cos(x)";
		} else if (fnm.equals("tan")) {
			fn = fnm;
			f = Math::tan;
			start = 0.0;
			stop = 0.50 * Math.PI;
			bound = 0.01;
			ml = "tan(x)";
		} else { // default to sin(x)
			fn = "sin";
			f = Math::sin;
			start = 0.0;
			stop = 2.0 * Math.PI;
			bound = 0.25;
			ml = "sin(x)";
		}
		segments = linearize(f, start, stop, bound);
	};

	// Runs the linearize function to compute upper and lower bounds.
	// Outputs the AGREE function and MATLAB script
	public void writeScripts() {
		writeMatlabScript(segments);
		writeAgreeConstraintForm(segments);
		writeAgreeFunctionForm(segments);
	}

	// A line segment is represented by Cartesian coordinates of its endpoints
	public class Segment {
		public double startX, startY, stopX, stopY;

		public Segment(double startX, double startY, double stopX, double stopY) {
			this.startX = startX;
			this.startY = startY;
			this.stopX = stopX;
			this.stopY = stopY;
		}
	}

	// Distance holds the distance the non-linear function extends
	// below and above a straight line through its endpoints
	public class Distance {

		public double below, above;

		public Distance(double below, double above) {
			this.below = below;
			this.above = above;
		}
	}

	// Bounding segments holds a list of line segments that bound the
	// non-linear function below and above
	public class BoundingSegments {

		public LinkedList<Segment> lower;
		public LinkedList<Segment> upper;

		public BoundingSegments() {
			lower = new LinkedList<>();
			upper = new LinkedList<>();
		}

		public BoundingSegments(LinkedList<Segment> lower, LinkedList<Segment> upper) {
			this.lower = lower;
			this.upper = upper;
		}

		public BoundingSegments(Segment lower, Segment upper) {
			this.lower = new LinkedList<>();
			this.lower.add(lower);
			this.upper = new LinkedList<>();
			this.upper.add(upper);
		}
	}

	private BoundingSegments combine(BoundingSegments x, BoundingSegments y) {
		BoundingSegments r = new BoundingSegments();
		r.lower.addAll(x.lower);
		r.upper.addAll(x.upper);
		r.lower.addAll(y.lower);
		r.upper.addAll(y.upper);
		return r;
	}

	private static final String DASHES = new String(new char[100]).replace("\0", "-");
	private static final String directory = "C:\\Users\\kfhoech\\Desktop\\SoSITE\\SoS Examples\\Linear Approximation\\";

	// Linearizes a function and finds either the upper or lower bounds
	private BoundingSegments linearize(Function<Double, Double> f, double start, double stop, double bound) {

		Distance dist = distance(f, start, stop);

		// Found lower and upper segments whose distance lies within the
		// acceptable bounds
		if (dist.above < bound & dist.below > -bound) {
			return new BoundingSegments(
					new Segment(start, f.apply(start) + dist.below, stop, f.apply(stop) + dist.below),
					new Segment(start, f.apply(start) + dist.above, stop, f.apply(stop) + dist.above));
		}
		;

		// Bound is exceeded. Divide in half and try again.
		double mid = (start + stop) / 2.0;
		return combine(linearize(f, start, mid, bound), linearize(f, mid, stop, bound));
	}

	// Returns the maximum distance of the nonlinear function
	// below and above a straight line between its end points
	private Distance distance(Function<Double, Double> f, double start, double stop) {

		int checks = 100000; // number of points to check between start and stop
								// positions

		double distAbove = 0.0;
		double distBelow = 0.0;
		double dist = 0.0;
		double step = (stop - start) / checks;
		double x = start;
		double y = f.apply(start);
		double slope = (f.apply(stop) - f.apply(start)) / (stop - start);
		while (x < stop) {
			dist = f.apply(x) - (y + slope * (x - start));
			if (dist > distAbove) {
				distAbove = dist;
			}
			if (dist < distBelow) {
				distBelow = dist;
			}
			x = x + step;
		}
		return new Distance(distBelow, distAbove);
	}

	// Pads string s on the right to length n
	private static String padRight(String s, int n) {
		return String.format("%1$-" + n + "s", s);
	}

	// Pads string s on the left to length n
	private static String padLeft(String s, int n) {
		return String.format("%1$" + n + "s", s);
	}

	// Pads double d on the right to length n
	private static String padRight(double d, int n) {
		return padRight(Double.toString(d), n);
	}

	// Pads double d on the left to length n
	@SuppressWarnings("unused")
	private static String padLeft(double d, int n) {
		return padLeft(Double.toString(d), n);
	}

	// Writes the AGREE specification in constraint form to a file
	private void writeAgreeConstraintForm(BoundingSegments s) {
		String file = directory + fn + ".aadl";
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println("Unable to open file " + file);
			e.printStackTrace();
		}
		out.println(DASHES);
		out.println("-- " + fn + " = " + ml + " over domain " + start + ".." + stop + " with bound of " + bound);
		out.println("-- Created " + new Date());
		out.println(DASHES);
		out.println("node " + fn + "(x: real, r: real) returns (constraint: bool);");
		out.println("  let constraint =");
		writeAgreeBoundsConstraintForm(s.upper, "<=", out);
		out.print("\n      ) and \n");
		writeAgreeBoundsConstraintForm(s.lower, ">=", out);
		out.println("\n   );\n");
		out.println("    lemma \"" + fn + " domain valid\" : x >= " + start + " and x < " + stop + ";");
		out.print("\n  tel;");
		out.close();
		System.out.println("Wrote AGREE specfictation (constraint form) for function " + fn + " to " + directory);
	}

	// Writes the upper or lower bounds to the AGREE specification
	private void writeAgreeBoundsConstraintForm(LinkedList<Segment> b, String relop, PrintWriter out) {
		double slope = 0.0;
		boolean firstLine = true;
		for (Segment seg : b) {
			if (firstLine) {
				out.print("    ( ");
			} else {
				out.print(" and \n      ");
			}
			firstLine = false;

			slope = (seg.stopY - seg.startY) / (seg.stopX - seg.startX);
			out.print("( x >= " + padRight(seg.startX, 25) + " and " + " x < " + padRight(seg.stopX, 25) + " => r "
					+ relop + " ( " + padRight(slope, 25) + " * ( x - " + padRight(seg.startX, 25) + ")) + "
					+ padRight(seg.startY, 25) + ")");
		}
	}

	// Writes the AGREE specification for the function to a file
	private void writeAgreeFunctionForm(BoundingSegments s) {
		String file = directory + fn + "_fn.aadl";
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println("Unable to open file " + file);
			e.printStackTrace();
		}
		out.println(DASHES);
		out.println("-- " + fn + " = " + ml + " over [" + start + "," + stop + "] with bounds of " + bound);
		out.println("-- Created " + new Date());
		out.println(DASHES);
		out.println("node " + fn + "(x: real, v: real) returns (r: real);");
		out.println("let r =");
		writeAgreeBoundsFunctionForm(s, out);
		out.println(padLeft("0.0;", 66));
		out.println("");
		out.println("    lemma \"" + fn + " domain valid\" : x >= " + start + " and x < " + stop + ";\n");
		out.println("    lemma \"" + fn + " variance valid\" : v >= -1.0 and v <= 1.0;");
		out.println("tel;");
		out.close();
		System.out.println("Wrote AGREE specfictation (function form) for function " + fn + " to " + directory);
	}

	// Writes the upper or lower bounds to the AGREE specification
	private void writeAgreeBoundsFunctionForm(BoundingSegments s, PrintWriter out) {
		double slope = 0.0;
		Segment slb;
		Segment sub;
		for (int i = 0; i < s.lower.size(); i++) {
			slb = s.lower.get(i);
			sub = s.upper.get(i);
			slope = (slb.stopY - slb.startY) / (slb.stopX - slb.startX);
			out.print("    ");
			out.print("if ( x >= " + padRight(slb.startX, 25) + " and " + " x < " + padRight(slb.stopX, 25) + " ) then "
					+ padRight((slb.startY + sub.startY) / 2.0, 25) + " + (" + padRight(slope, 25) + " * ( x - "
					+ padRight(slb.startX, 25) + ")) + " + padRight((sub.startY - slb.startY) / 2.0, 25)
					+ " * v else\n");
		}
	}

	// Writes a MATLAB script to plot the function and the upper and lower
	// bounds
	private void writeMatlabScript(BoundingSegments s) {
		String file = directory + fn + ".m";
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(file));
		} catch (IOException e) {
			System.out.println("Unable to open file " + file);
			e.printStackTrace();
		}
		out.println("% Created " + new Date());
		out.println("clear;");
		out.println("figure(1);");
		out.println("lbx = " + segmentsX(s.lower) + ";");
		out.println("lby = " + segmentsY(s.lower) + ";");
		double xf = s.lower.getFirst().startX;
		double xl = s.lower.getLast().stopX;
		out.println("x = " + xf + ":" + (xl - xf) / 100 + ":" + xl + ";");
		out.println("y = " + ml + ";");
		out.println("ubx = " + segmentsX(s.upper) + ";");
		out.println("uby = " + segmentsY(s.upper) + ";");
		out.println("plot( lbx, lby, x, y, ubx, uby );");
		out.println("title(\'" + fn.replace("_", "\\_") + ", Bounds = " + bound + ", Segments = " + s.lower.size()
				+ "\');");
		out.close();
		System.out.println("Wrote MATLAB script for function " + fn + " to " + directory);
	}

	// Formats the stop and start X positions for each line segment into MATLAB
	// vectors
	private String segmentsX(LinkedList<Segment> segs) {
		String x = " [ ";
		for (Segment s : segs) {
			x += s.startX + " " + s.stopX + " ";
		}
		x += "] ";
		return x;
	}

	// Formats the stop and start Y positions for each line segment into MATLAB
	// vectors
	private String segmentsY(LinkedList<Segment> segs) {
		String y = " [ ";
		for (Segment s : segs) {
			y += s.startY + " " + s.stopY + " ";
		}
		y += "] ";
		return y;
	}

}