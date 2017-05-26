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

package com.rockwellcollins.atc.tcg.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

import jkind.api.results.AnalysisResult;
import jkind.api.results.JRealizabilityResult;
import jkind.api.results.PropertyResult;
import jkind.api.results.ResultsUtil;
import jkind.api.results.Status;
import jkind.api.ui.results.AnalysisResultColumnViewer.Column;
import jkind.api.ui.results.Spinner;
import jkind.util.Util;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.rockwellcollins.atc.tcg.util.TcgResultsUtil;

public class TestCaseGeneratorResultLabelProvider extends ColumnLabelProvider {
	private final Column column;
	private static final String ICON_DIR = "/icons/";

	private static final Map<Status, String> TcgStatusToTcgStatusString;
	static {
		TcgStatusToTcgStatusString = new EnumMap<Status, String>(Status.class);
		// WORKING, VALID, INVALID, UNKNOWN, CANCELED, ERROR, WAITING
		TcgStatusToTcgStatusString.put(Status.WORKING,  "Working");
		TcgStatusToTcgStatusString.put(Status.VALID,    "Test Case Found");        /* polarity is inverted for TCG */
		TcgStatusToTcgStatusString.put(Status.INVALID,  "No Test Case Possible");  /* polarity is inverted for TCG */
		TcgStatusToTcgStatusString.put(Status.UNKNOWN,  "Unknown");
		TcgStatusToTcgStatusString.put(Status.CANCELED, "Canceled");
		TcgStatusToTcgStatusString.put(Status.ERROR,    "Error");
		TcgStatusToTcgStatusString.put(Status.WAITING,  "Waiting");
	}
	
	private static final Map<Status, Status> StatusToTcgStatus;
	static {
		StatusToTcgStatus = new EnumMap<Status, Status>(Status.class);
		// WORKING, VALID, INVALID, UNKNOWN, CANCELED, ERROR, WAITING
		StatusToTcgStatus.put(Status.WORKING,  Status.WORKING);
		StatusToTcgStatus.put(Status.VALID,    Status.INVALID);   /* polarity is inverted for TCG */
		StatusToTcgStatus.put(Status.INVALID,  Status.VALID);     /* polarity is inverted for TCG */
		StatusToTcgStatus.put(Status.UNKNOWN,  Status.UNKNOWN);
		StatusToTcgStatus.put(Status.CANCELED, Status.CANCELED);
		StatusToTcgStatus.put(Status.ERROR,    Status.ERROR);
		StatusToTcgStatus.put(Status.WAITING,  Status.WAITING);
	}
	
	public TestCaseGeneratorResultLabelProvider(Column column) {
		this.column = column;
	}

	public TestCaseGeneratorResultLabelProvider(Column column, ColumnViewer viewer) {
		this.column = column;
		workingSpinner = new Spinner(ICON_DIR + "working.gif", viewer);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof PropertyResult) {
			PropertyResult pr = (PropertyResult) element;
			switch (column) {
			case PROPERTY:
				if (pr.getName().equals(Util.REALIZABLE)) {
					return pr.getParent().getName();
				} else {
					return pr.getName();
				}
			case RESULT:
				Status tcgStatus = StatusToTcgStatus.get(pr.getStatus());
				String tcgStatusString = TcgStatusToTcgStatusString.get(tcgStatus);
				switch (tcgStatus) {
				case WAITING:
					return tcgStatusString;
				case WORKING:
					return tcgStatusString + "..." + getProgress(pr) + " ("	+ pr.getElapsed() + "s)";
				default:
					return getFinalStatus(pr) + " (" + pr.getElapsed() + "s)";
				}
			}
		} else if (element instanceof JRealizabilityResult) {
			JRealizabilityResult result = (JRealizabilityResult) element;
			return getText(result.getPropertyResult());
		} else if (element instanceof AnalysisResult) {
			AnalysisResult result = (AnalysisResult) element;
			switch (column) {
			case PROPERTY:
				return result.getName();
			case RESULT:
				return TcgResultsUtil.getMultiStatus(result).toString();
			}
		}

		return "";
	}

	private String getProgress(PropertyResult pr) {
		if (pr.getBaseProgress() == 0) {
			return "";
		}

		return " [no test case for " + pr.getBaseProgress() + " steps]";
	}

	private String getFinalStatus(PropertyResult pr) {
		Status tcgStatus = StatusToTcgStatus.get(pr.getStatus());
		String tcgStatusString = TcgStatusToTcgStatusString.get(tcgStatus);
		if (tcgStatus == Status.UNKNOWN || tcgStatus == Status.CANCELED) {
			return tcgStatusString + getProgress(pr);
		} else {
			return tcgStatusString;
		}
	}

	private static final Image EMPTY_IMAGE   = loadImage(ICON_DIR + "gray_ball.png");
	private static final Image VALID_IMAGE   = loadImage(ICON_DIR + "tick_octagon.png");
	private static final Image INVALID_IMAGE = loadImage(ICON_DIR + "red_delete.png");
	private static final Image UNKNOWN_IMAGE = loadImage(ICON_DIR + "orange_qmark.png");
	private static final Image WAITING_IMAGE = loadImage(ICON_DIR + "waiting.png");
	private static final Image CANCEL_IMAGE  = loadImage(ICON_DIR + "cancel.png");
	private static final Image ERROR_IMAGE   = loadImage(ICON_DIR + "error.png");
	private Spinner workingSpinner;

	private static Image loadImage(String filename) {
		try (InputStream stream = TestCaseGeneratorResultLabelProvider.class.getResourceAsStream(filename)) {
			return new Image(null, new ImageData(stream));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public Image getImage(Object element) {
		if (column == Column.PROPERTY) {
			if (element instanceof PropertyResult) {
				PropertyResult pr = (PropertyResult) element;
				return getStatusImage(pr.getStatus());
			} else if (element instanceof AnalysisResult) {
				AnalysisResult result = (AnalysisResult) element;
				return getStatusImage(TcgResultsUtil.getMultiStatus(result).getOverallStatus());
			}
		}

		return null;
	}

	private Image getStatusImage(Status status) {
		if (status == null) {
			return EMPTY_IMAGE;
		}

		switch (status) {
		case WORKING:
			return workingSpinner.getFrame();
		case VALID:
			return INVALID_IMAGE;
		case INVALID:
			return VALID_IMAGE;
		case UNKNOWN:
			return UNKNOWN_IMAGE;
		case CANCELED:
			return CANCEL_IMAGE;
		case ERROR:
			return ERROR_IMAGE;
		case WAITING:
			return WAITING_IMAGE;
		default:
			return WAITING_IMAGE;
		}
	}

	@Override
	public void dispose() {
		if (workingSpinner != null) {
			workingSpinner.dispose();
		}
	}
}
