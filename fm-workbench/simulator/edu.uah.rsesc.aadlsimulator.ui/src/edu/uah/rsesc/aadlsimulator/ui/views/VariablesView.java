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
package edu.uah.rsesc.aadlsimulator.ui.views;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.ColorDescriptor;
import org.eclipse.jface.resource.DeviceResourceManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerColumn;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.ge.GraphicalEditor;
import org.osate.ge.services.GraphicalEditorService;
import org.osate.ui.UiUtil;
import org.osgi.framework.FrameworkUtil;
import edu.uah.rsesc.aadlsimulator.Rational;
import edu.uah.rsesc.aadlsimulator.SimulationEngine;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.VariableType;
import edu.uah.rsesc.aadlsimulator.ui.ValueFormatter;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.InputConstraintDialog;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.InputConstraintDialog.Model;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.InputConstraintDialog.Result;
import edu.uah.rsesc.aadlsimulator.ui.events.BeforeStepForwardEvent;
import edu.uah.rsesc.aadlsimulator.ui.events.SimulatorUIEvents;
import edu.uah.rsesc.aadlsimulator.ui.ext.Command;
import edu.uah.rsesc.aadlsimulator.ui.ext.CommandContext;
import edu.uah.rsesc.aadlsimulator.ui.ext.SimpleCommandContext;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorStateListener;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorUIExtensionService;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.xtext.ui.InputConstraintInjectionHelper;
import edu.uah.rsesc.aadlsimulator.xtext.util.InputConstraintHelper;
import edu.uah.rsesc.aadlsimulator.xtext.util.ResultType;
import edu.uah.rsesc.aadlsimulator.xtext.util.ReferenceTypeResolver;

public class VariablesView {
	private final static int VARIABLE_NAME_COLUMN_INDEX = 0;
	
	// Helper classes
	class VariableContentProvider implements ITreeContentProvider {
		private SimulatorState currentState;

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
			assert newInput == null || newInput instanceof SimulatorState;
			currentState = (SimulatorState)newInput;
		}

		@Override
		public Object[] getElements(final Object inputElement) {
			assert inputElement instanceof SimulatorState;
			final SimulatorState state = (SimulatorState)inputElement;
			if(state.getEngineState() == null) {
				return new Object[0];
			}
			return state.getEngineState().getRootElements().toArray();
		}

		@Override
		public Object[] getChildren(final Object parentElement) {
			if(currentState == null) {
				return new Object[0];
			}		

			return currentState.getEngineState().getChildElements(parentElement).toArray();
		}

		@Override
		public Object getParent(final Object element) {
			if(currentState == null || currentState.getEngineState() == null) {
				return null;
			}

			return currentState.getEngineState().getParentElement(element);
		}

		@Override
		public boolean hasChildren(final Object element) {
			if(currentState == null) {
				return false;
			}

			return currentState.getEngineState().getChildElements(element).size() > 0;
		}

		public SimulatorState getCurrentState() {
			return currentState;
		}
	}
	
	static class ConstraintError {
		public final String constraint;
		public final String errorMessage;
		
		public ConstraintError(final String constraint, final String errorMessage) {
			this.constraint = Objects.requireNonNull(constraint, "constraint must not be null");
			this.errorMessage = Objects.requireNonNull(errorMessage, "errorMessage must not be null");
		}
	}

	class NextValueEditingSupport extends EditingSupport {
		private final SimulationUIService simulationUIService;
		private final VariableValueCellEditor cellEditor;
		private Object currentElement;
		
		final ReferenceTypeResolver refResolver = new ReferenceTypeResolver() {						
			@Override
			public ResultType getElementReferenceType(final ElementRefExpression reference) {
				final SimulationEngineState engineState = getVariableContentProvider().getCurrentState().getEngineState();
				final String referencePath = String.join(".", reference.getIds());
				final Object referencedElement = engineState.findElementByPath(referencePath);
				if(referencedElement == null) {
					return null;
				}

				final VariableType referencedVariableType = engineState.getElementType(referencedElement);
				return variableTypeToResultType(referencedVariableType);
			}
			
			@Override
			public ResultType getConstReferenceType(final ConstRefExpression reference) {
				final SimulationEngineState engineState = getVariableContentProvider().getCurrentState().getEngineState();
				final String constantId = String.join("::", reference.getPackageSegments()) + "::" + reference.getConstantName();
				final Object referencedConstant = engineState.findConstantById(constantId);
				if(referencedConstant == null) {
					return null;
				}

				final VariableType referencedVariableType = engineState.getConstantType(referencedConstant);
				return variableTypeToResultType(referencedVariableType);
			}
		};
		
		public NextValueEditingSupport(final SimulationUIService simulationUIService, final TreeViewer treeViewer) {
			super(treeViewer);
			this.simulationUIService = Objects.requireNonNull(simulationUIService, "simulationUIService must not be null");
			this.cellEditor = new VariableValueCellEditor(treeViewer.getTree(), constraintErrorColor) {
				@Override
				protected Object openDialogBox(final Control cellEditorWindow) {
					final SimulationEngineState engineState = getVariableContentProvider().getCurrentState().getEngineState();
					final Model model = new DefaultInputConstraintDialogModel(inputConstraintHelper, refResolver, engineState);					
					final InputConstraintHelper.Result parseResult = inputConstraintHelper.parse(getRawValue());
					if(!parseResult.isValid()) {
						throw new RuntimeException("Unable to parse current constraint.");
					}
					
					final VariableType variableType = Objects.requireNonNull(engineState.getElementType(currentElement), "unable to retrieve element type");
					final String variableName = Objects.requireNonNull(engineState.getElementName(currentElement), "unable to retrieve element name");
					final Result result = InputConstraintDialog.show(this.getControl().getShell(), model, variableName, parseResult.getInputConstraint(), variableTypeToResultType(variableType), engineState.getNumberOfFrames());
					return result == null || result.getInputConstraint() == null ? null : model.unparse(result.getInputConstraint());
				}
			};

			// Configure validator
			cellEditor.setValidator(new ICellEditorValidator() {
				@Override
				public String isValid(final Object value) {
					// Check if the value is valid
					if(value == null || (value instanceof String && ((String)value).trim().length() == 0)) {
						return null;
					}

					final VariableType variableType = getVariableContentProvider().getCurrentState().getEngineState().getElementType(currentElement);
					final SimulationEngineState engineState = getVariableContentProvider().getCurrentState().getEngineState();
					final InputConstraintHelper.Result result = inputConstraintHelper.parseAndValidate(value.toString(), refResolver, variableTypeToResultType(variableType), engineState.getNumberOfFrames());
					if(result.isValid()) {
						return null;
					} else {
						return result.getErrorMessage();
					}
				}				
			});
			
			cellEditor.addListener(new ICellEditorListener() {
				@Override
				public void applyEditorValue() {
					// Update the viewer if the value failed validation. If validation succeeded, update() is called as part of setValue()
					if(cellEditor.getErrorMessage() != null) {
						getViewer().update(currentElement, null);			
					}
				}

				@Override
				public void cancelEditor() {
					elementToConstraintErrorMap.remove(currentElement);
					updateCellEditorErrorState();
					getViewer().update(currentElement, null);
				}

				@Override
				public void editorValueChanged(final boolean oldValidState, final boolean newValidState) {
					// Update based on validation results
					if(currentElement != null) {
						if(newValidState) {
							if(!oldValidState) {
								elementToConstraintErrorMap.remove(currentElement);
							}
						} else {
							final String errorMessage = cellEditor.getErrorMessage();
							elementToConstraintErrorMap.put(currentElement, new ConstraintError(cellEditor.getRawValue(), errorMessage));
						}
						
						updateCellEditorErrorState();
					}
				}				
			});
		}
		
		public void dispose() {
			if(cellEditor != null) {
				cellEditor.dispose();
			}
		}
		
		public void updateCellEditorErrorState() {
			cellEditor.setErrorState(elementToConstraintErrorMap.get(currentElement));
		}

		VariableContentProvider getVariableContentProvider() {
			assert treeViewer.getContentProvider() instanceof VariableContentProvider;
			return (VariableContentProvider)treeViewer.getContentProvider();
		}

		@Override
		protected boolean canEdit(final Object element) {
			// Only allow editing elements which do not have a value provider or have a ConstantValueProvider and are of a supported type.
			final SimulationEngine simulationEngine = simulationUIService.getCurrentState().getSimulationEngine();
			final VariableType variableType = getVariableContentProvider().getCurrentState().getEngineState().getElementType(element);
			return simulationEngine != null && simulationEngine.getCurrentState().canStepForward() && (variableType == VariableType.INTEGER || variableType == VariableType.REAL || variableType == VariableType.BOOLEAN);
		}

		@Override
		protected CellEditor getCellEditor(final Object element) {
			// Store the element currently being edited
			currentElement = element;
			updateCellEditorErrorState();
			return cellEditor;
		}

		@Override
		protected Object getValue(final Object element) {
			// Provide invalid value first
			final ConstraintError constraintError = elementToConstraintErrorMap.get(element);
			if(constraintError != null) {
				return constraintError.constraint;
			}			
			
			final SimulationEngineState engineState = simulationUiService.getCurrentState().getEngineState();
			if(engineState== null) {
				return "";
			}
			
			final InputConstraint ic = engineState.getElementInputConstraintForNextFrame(element);
			if(ic == null) {
				return "";
			}

			final String stringValue = inputConstraintHelper.unparse(ic);
			return stringValue;
		}

		@Override
		protected void setValue(final Object element, final Object value) {	
			try {
				// Parse the constraint. 
				final InputConstraintHelper.Result parseResult = inputConstraintHelper.parse(value instanceof String ? value.toString() : null);
				if(parseResult.isValid()) {
					// Set constraint
					final SimulationEngine engine = simulationUiService.getCurrentState().getSimulationEngine();
					if(engine != null) {
						engine.setInputConstraint(element, parseResult.getInputConstraint());
						getViewer().update(element, null);
					}
				} else {
					// Parsing should pass because it should have already been parsed and validated.
					throw new RuntimeException(parseResult.getErrorMessage());
				}
			} catch(final Exception ex) {
				final Status status = new Status(IStatus.ERROR, FrameworkUtil.getBundle(getClass()).getSymbolicName(), "An error occured parsing an input constraint.", ex);
				StatusManager.getManager().handle(status, StatusManager.LOG | StatusManager.SHOW);
			}
		}
		
		
		Shell getShell() {
			return treeViewer.getTree().getShell();
		}
	}
	
	static ResultType variableTypeToResultType(final VariableType vt) {
		switch(vt) {
		case BOOLEAN:
			return ResultType.BOOLEAN;
						
		case REAL:
			return ResultType.REAL;
			
		case INTEGER:
			return ResultType.INTEGER;
			
		case NONE:
			return null;
		}
		
		throw new RuntimeException("Unhandled type: " + vt);
	}
	
	private class ValueCellLabelProvider extends OwnerDrawLabelProvider {
		private final VariableContentProvider contentProvider;
		private int frameIndex = 0;

		public ValueCellLabelProvider(final VariableContentProvider contentProvider) {
			this.contentProvider = contentProvider;
		}

		public void setFrameIndex(final int value) {
			this.frameIndex = value;
		}

		@Override
		public void initialize(ColumnViewer viewer, ViewerColumn column) {
			super.initialize(viewer, column, true);
		}

		@Override
		public void update(final ViewerCell cell) {
			cell.setText(getText(cell.getElement()));
			cell.setForeground(getForeground(cell.getElement()));

			super.update(cell);
		}
		
		private Color getForeground(final Object element) {
			final SimulatorState state = contentProvider.getCurrentState();
			final Color foreground;
			if(state == null) {
				foreground = null;
			} else {
				final SimulationEngineState engineState = state.getEngineState();
				if(frameIndex > 0 && frameIndex < engineState.getNumberOfFrames()) {
					final Object value = engineState.getElementValue(frameIndex, element);
					final Object prevValue = engineState.getElementValue(frameIndex-1, element);
					foreground = value != null && value.equals(prevValue) ? unchangedValueColor : null;
				} else {
					foreground = null;
				}
			}
			
			return foreground;
		}

		private String getText(final Object element) {
			// Get the name of the frame state element
			final SimulatorState state = contentProvider.getCurrentState();
			if(state == null || state.getEngineState().getNumberOfFrames() <= frameIndex) {
				return "";
			}

			return ValueFormatter.formatValue(state.getEngineState().getElementValue(frameIndex, element));
		}		
		
		@Override
		public String getToolTipText(final Object element) {
			// Get the name of the frame state element
			final SimulatorState state = contentProvider.getCurrentState();
			if(state != null && state.getEngineState().getNumberOfFrames() > frameIndex) {
				final Object value = state.getEngineState().getElementValue(frameIndex, element);
				if(value instanceof Rational) {
					final String formattedRational = ValueFormatter.formatValue(state.getEngineState().getElementValue(frameIndex, element));
					if(Rational.isTruncatedValue(formattedRational)) {
						return value.toString() + " \u2248 " + formattedRational;
					}
				}
			}
			
			return super.getToolTipText(element);
		}

		// Customize drawing to support highlighting
		@Override
		protected void measure(Event event, Object element) {
		}
		
		@Override
		protected void erase(Event event, Object element) {
			// If the cell is highlighted, draw the color in the background
			for (final CellColorInfo cellColorInfo : cellColorInfos) {
				final ValueColumn valueColumn = valueColumns.get(event.index - (treeViewer.getTree().getColumnCount() - valueColumns.size()));
				if (element.equals(cellColorInfo.treeItemData) && valueColumn.getFrameIndex() == cellColorInfo.getFrameIndex()) {
					final GC gc = event.gc;
					final Color oldBackground = event.gc.getBackground();
					gc.setBackground(cellColorInfo.getCellBackgroundColor());
					final Rectangle bounds = event.getBounds();
					gc.fillRectangle(bounds);
					gc.setBackground(oldBackground);
					
					// Ensure that selection and hover indicators are not drawn for the highlighted cell
					event.detail &= ~SWT.SELECTED;
					event.detail &= ~SWT.HOT;
					break;
				}
			}
			
			// Disable drawing foreground. paint() will draw the foreground text in order to fix coloring issues when the row is selected.
			event.detail &= ~SWT.FOREGROUND;
		}
		
		@Override
		protected void paint(final Event event, final Object element) {
			// Draw just the text with the appropriate background color
			final TreeItem item = (TreeItem) event.item;
			final Rectangle textBounds = item.getTextBounds(event.index);
			final GC gc = event.gc;
			final String txt = item.getText(event.index); 
			gc.setForeground(item.getForeground(event.index));
			String drawTxt = txt;
			
			// Check the width of the text and truncate it as necessary
			final int maxWidth = Math.max(gc.getClipping().width-6, textBounds.width);
			if(maxWidth > 0) {
				String truncatedTxt = txt;
				while(gc.textExtent(drawTxt).x > maxWidth && truncatedTxt.length() > 0) {
					truncatedTxt = truncatedTxt.substring(0, truncatedTxt.length()-1);
					drawTxt = truncatedTxt + "...";
				}
				
				gc.drawString(drawTxt, textBounds.x, textBounds.y, true);
			}
		}

	}

	/**
	 * Extend tree viewer to allow finding the tree item by the element object
	 *
	 */
	public static class VariablesTreeViewer extends TreeViewer {
		public VariablesTreeViewer(final Composite parent, final int style) {
			super(parent, style);
		}

		public TreeItem findTreeItemByElement(final Object element) {
			final Widget w = super.findItem(element);
			return w instanceof TreeItem ? (TreeItem)w : null;
		}		
	}

	// Stores information about a column. Needed because TreeViewer/TreeViewerColumn doesn't provide a mechanism for accessing the TreeViewerColumn and label provider
	public static class ValueColumn {
		private TreeColumn column;
		private ValueCellLabelProvider labelProvider;

		public ValueColumn(final TreeColumn column, final ValueCellLabelProvider labelProvider) {
			assert column != null;
			assert labelProvider != null;
			this.column = column;
			this.labelProvider = labelProvider;
		}

		public void setText(final String value) {
			column.setText(value);
		}

		public void setFrameIndex(final int value) {
			labelProvider.setFrameIndex(value);
		}

		public int getFrameIndex() {
			return labelProvider.frameIndex;
		}

		public void dispose() {
			column.dispose();
		}
	}

	private static final class TreeSelectionTracker {
		private int selectedColumnIndex = -1;
		private Object treeItemData = null;

		public TreeSelectionTracker(final TreeViewer treeViewer) {
			treeViewer.getTree().addMenuDetectListener(new MenuDetectListener() {				
				@Override
				public void menuDetected(final MenuDetectEvent e) {
					final Point point = Display.getDefault().map(null, treeViewer.getTree(), new Point(e.x, e.y));  
					final ViewerCell cell = treeViewer.getCell(point);
					if(cell != null && cell.getItem() != null) {
						treeItemData = cell.getItem().getData();
						selectedColumnIndex = cell.getColumnIndex();
					} else {
						treeItemData = null;
					}
				}
			});
		}

		public boolean isSelectionValid() {
			return treeItemData != null; 
		}

		public Object getSelectedTreeItemData() {
			return treeItemData;
		}

		public int getSelectedColumnIndex() {
			return selectedColumnIndex;
		}
	}

	//Holds information about highlighted cells
	private class CellColorInfo {
		final private Object treeItemData;
		final private int frameIndex;
		private Color cellBackgroundColor;

		public CellColorInfo(final Object treeItemData, final int frameIndex, final RGB cellBackgroundRgb) {
			this.treeItemData = Objects.requireNonNull(treeItemData, "treeItemData must not be null");
			this.frameIndex = frameIndex;
			this.cellBackgroundColor = colorManager.createColor(Objects.requireNonNull(cellBackgroundRgb, "cellBackgroundRgb must not be null"));
		}
		
		public void dispose() {
			colorManager.destroy(ColorDescriptor.createFrom(cellBackgroundColor));
		}

		public Color getCellBackgroundColor() {
			return cellBackgroundColor;
		}

		public int getFrameIndex() {
			return frameIndex;
		}

		public Object getTreeItemData () {
			return treeItemData;
		}
	}

	private final SimulatorStateListener stateListener = new SimulatorStateListener() {
		@Override
		public void onSimulatorStateChanged(final SimulatorState simulatorState) {
			update(simulatorState);
		}
	};

	private void update(SimulatorState simulatorState) {
		// Store the simulation engine state and store the element to constraint error map when the number of frames changes.
		// Don't clear in all cases because there are other changes which shouldn't affect the simulation variables view
		if(currentEngineState == null || 
				simulatorState.getEngineState() == null ||
				simulatorState.getEngineState().getNumberOfFrames() != currentEngineState.getNumberOfFrames()) {
			elementToConstraintErrorMap.clear();
			currentEngineState = simulatorState.getEngineState();
		}
		
		if(treeViewer != null && !editingTreeViewer) {
			startEditingTreeViewer();					
			final TreeItem topItem = treeViewer.getTree().getTopItem();
			final Object topElement = topItem == null ? null : topItem.getData();

			// Update the input and preserve expanded tree paths
			final TreePath[] expandedPaths = treeViewer.getExpandedTreePaths();
			treeViewer.setInput(simulatorState);
			treeViewer.setExpandedTreePaths(expandedPaths);

			if(topElement != null) {
				final TreeItem newTopTreeItem = treeViewer.findTreeItemByElement(topElement);
				if(newTopTreeItem != null) {
					treeViewer.getTree().setTopItem(newTopTreeItem);
				}
			}

			update();

			treeViewer.getTree().setRedraw(true);
			stopEditingTreeViewer();
		}
	}

	// Comparator which sorts CellColorInfos based on their frame index. CellColorInfos with matching frame indices are sorted based on their hash code.
	private final Comparator<CellColorInfo> cellColorInfoComparator = new Comparator<CellColorInfo>() {
		@Override
		public int compare(final CellColorInfo c1, final CellColorInfo c2) {
			int result = Integer.valueOf(c2.getFrameIndex()).compareTo(Integer.valueOf(c1.getFrameIndex()));
			if(result == 0) {
				result = Integer.compare(c1.hashCode(), c2.hashCode());
			}

			return result;
		}
	};

	final InputConstraintHelper inputConstraintHelper = InputConstraintInjectionHelper.getInstance(InputConstraintHelper.class);
	private final SimulationUIService simulationUiService;
	private final SimulatorUIExtensionService extService;
	private VariablesTreeViewer treeViewer;
	private NextValueEditingSupport nextValueEditingSupport;
	private TreeSelectionTracker treeSelectionTracker;
	private Slider frameSlider;
	private int frameScrollOffset; // The number of frames from the last one to display.
	private boolean editingTreeViewer = false; // Used to prevent re-entry into the tree viewer. isBusy() is not sufficient. Should not be access directly. Use start/stopEditingTreeViewer()
	private final List<ValueColumn> valueColumns = new ArrayList<ValueColumn>(); // Stores information about the columns for the values for individual frames.
	private final TreeSet<CellColorInfo> cellColorInfos = new TreeSet<CellColorInfo>(cellColorInfoComparator); // Stores information about cells to be highlighted
	private final RGB green = new RGB(180, 255, 180);
	private final RGB red = new RGB(255, 180, 180);
	private final RGB yellow = new RGB(255, 255, 180);
	private RGB customRgb = null;
	private ResourceManager colorManager;
	private Color unchangedValueColor; // Foreground color for values that are the same as the most recent values.
	private Color constraintErrorColor; // Background color used to indicate an error in parsing or validating the constraint.
	private Image greenImage;
	private Image redImage;
	private Image yellowImage;
	private Image customColorImage;
	private MenuManager contextMenuMgr = new MenuManager();
	Map<Object, ConstraintError> elementToConstraintErrorMap = new HashMap<>(); // Stores the current errors related to user inputs
	private SimulationEngineState currentEngineState;
	
	final ControlListener resizeListener = new ControlAdapter() {
		public void controlResized(final ControlEvent e) {
			if(!editingTreeViewer) {
				startEditingTreeViewer();
				update();				
				stopEditingTreeViewer();
			}
		}
	};
	
	final SelectionListener valueColumnSelectedListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(final SelectionEvent e) {
			final ValueColumn selectedValueColumn = getValueColumn((TreeColumn)e.getSource());
			if(selectedValueColumn != null) {
				simulationUiService.setSelectedFrameIndex(selectedValueColumn.getFrameIndex());
			}
		}
	};
	
	// Disabled when introducing the VariableViewPart to prevent workspaces from using this class directly due to stored workspace settings. @Inject
	public VariablesView(final SimulationUIService simulationUiService, final SimulatorUIExtensionService extService) {
		this.simulationUiService = Objects.requireNonNull(simulationUiService, "simulationUiService must not be null");
		this.extService = Objects.requireNonNull(extService, "extService must not be null");
	}

	@PostConstruct
	void createView(final Composite parent) {		
		final Display display = parent.getDisplay();
		colorManager = new DeviceResourceManager(display);
		unchangedValueColor = colorManager.createColor(new RGB(192, 192, 192));
		constraintErrorColor = colorManager.createColor(red);
		greenImage = createColorIconImage(display, green);
		redImage = createColorIconImage(display, red);
		yellowImage = createColorIconImage(display, yellow);
		customColorImage = createColorIconImage(display, customRgb);
		
		// Create root composite
		final Composite root = new Composite(parent, SWT.NONE);
		final GridLayout rootLayout = new GridLayout(1, false);
		root.setLayout(rootLayout);

		// Create the variable tree viewer
		treeViewer = new VariablesTreeViewer(root, SWT.FULL_SELECTION);
		ColumnViewerToolTipSupport.enableFor(treeViewer); // Enable tooltip support
		treeSelectionTracker = new TreeSelectionTracker(treeViewer);

		GridDataFactory.fillDefaults().grab(true, true).applyTo(treeViewer.getTree());
		treeViewer.setUseHashlookup(true);
		final VariableContentProvider contentProvider = new VariableContentProvider();
		treeViewer.setContentProvider(contentProvider);

		treeViewer.setInput(null);
		treeViewer.setComparator(new ViewerComparator() {
			@SuppressWarnings("unchecked")
			public int compare(final Viewer viewer, final Object e1, final Object e2) {
				if(contentProvider.currentState == null) {
					return 0;
				}

				// Sort component instances before other variables
				final InstanceObject io1 = contentProvider.currentState.getEngineState().getElementInstanceObject(e1);
				final InstanceObject io2 = contentProvider.currentState.getEngineState().getElementInstanceObject(e2);
				if(io1 instanceof ComponentInstance && !(io2 instanceof ComponentInstance)) {
					return -1;
				} else if(io2 instanceof ComponentInstance && !(io1 instanceof ComponentInstance)) {
					return 1;
				}
				
				// Sort feature instances before the remainder
				if(io1 instanceof FeatureInstance && !(io2 instanceof FeatureInstance)) {
					return -1;
				} else if(io2 instanceof FeatureInstance && !(io1 instanceof FeatureInstance)) {
					return 1;
				}
				
				final String n1 = contentProvider.currentState.getEngineState().getElementName(e1);
				final String n2 = contentProvider.currentState.getEngineState().getElementName(e2);
				return getComparator().compare(n1, n2);
			}
		});
		
		treeViewer.setFilters(new ViewerFilter[] {
			new ViewerFilter() {
				@Override
				public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
					return !contentProvider.currentState.getEngineState().isElementHidden(element);
				}				
			}
		});
		
		// Create columns
		final TreeViewerColumn nameColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		nameColumn.getColumn().setWidth(200);
		nameColumn.getColumn().setText("Name");
		nameColumn.getColumn().addControlListener(resizeListener);
		nameColumn.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				cell.setText(getText(cell.getElement()));
				cell.setImage(getImage(cell.getElement()));
			}
			
			private String getText(final Object element) {
				// Get the name of the frame state element
				final SimulatorState state = contentProvider.getCurrentState();
				if(state == null) {
					return "";
				}

				return state.getEngineState().getElementName(element);
			}
			
			private Image getImage(final Object element) {
				final SimulatorState state = contentProvider.getCurrentState();
				if(state == null) {
					return null;
				}

				final InstanceObject instanceObject = state.getEngineState().getElementInstanceObject(element);				
				return instanceObject == null ? null : UiUtil.getModelElementLabelProvider().getImage(instanceObject);
			}
		});

		final TreeViewerColumn nextValueColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
		nextValueColumn.getColumn().setWidth(100);
		nextValueColumn.getColumn().setText("Next Value");
		nextValueColumn.setLabelProvider(new OwnerDrawLabelProvider() {
			@Override
			public void initialize(final ColumnViewer viewer, final ViewerColumn column) {
				super.initialize(viewer, column, true);
			}
			
			@Override
			public void update(final ViewerCell cell) {
				final String constraintTxt = getConstraintText(cell.getElement());
				final String txt;
				final Color foreground;
				if(constraintTxt == null) {
					// Attempt to retrieve the value for the next frame from from the engine state.					
					final SimulatorState state = contentProvider.getCurrentState();
					if(state == null) {
						txt = "";
					} else {
						final SimulationEngineState engineState = state.getEngineState();
						txt = ValueFormatter.formatValue(engineState.getElementValue(engineState.getNumberOfFrames(), cell.getElement()));						
					}
					foreground = unchangedValueColor;
				} else {
					txt = constraintTxt;
					foreground = null;
				}
				
				cell.setText(txt);
				cell.setForeground(foreground);
				
				super.update(cell);
			}
			
			private String getConstraintText(final Object element) {
				// Return the invalid value if the input that was entered is not correct
				final ConstraintError constraintError = elementToConstraintErrorMap.get(element);
				if(constraintError != null) {
					return constraintError.constraint;
				}
				
				final SimulationEngineState engineState = simulationUiService.getCurrentState().getEngineState();
				if(engineState == null) {
					return null;
				}
						
				final InputConstraint ic = engineState.getElementInputConstraintForNextFrame(element);
				if(ic == null) {
					return null;
				}

				return inputConstraintHelper.unparse(ic);
			}
			
			@Override
			public String getToolTipText(final Object element) {
				final ConstraintError constraintError = elementToConstraintErrorMap.get(element);
				if(constraintError == null) {
					return super.getToolTipText(element);
				} else {
					return constraintError.errorMessage;
				}
			}

			@Override
			protected void measure(final Event event, final Object element) {				
			}
			
			@Override
			protected void erase(final Event event, final Object element) {
				// Color cells which contain invalid values
				if(elementToConstraintErrorMap.containsKey(element)) {
					final GC gc = event.gc;
					final Color oldBackground = event.gc.getBackground();
					gc.setBackground(constraintErrorColor);
					final Rectangle bounds = event.getBounds();
					gc.fillRectangle(bounds);
					gc.setBackground(oldBackground);
					
					// Ensure that selection and hover indicators are not drawn for the highlighted cell
					event.detail &= ~SWT.SELECTED;
					event.detail &= ~SWT.HOT;
				}
				
				// Disable drawing foreground. paint() will draw the foreground text in order to fix coloring issues when the row is selected.
				event.detail &= ~SWT.FOREGROUND;
			}

			@Override
			protected void paint(final Event event, final Object element) {
				// Draw just the text with the appropriate background color
				final TreeItem item = (TreeItem) event.item;
				final Rectangle textBounds = item.getTextBounds(event.index);
				final GC gc = event.gc;
				final String txt = item.getText(event.index); 
				gc.setForeground(item.getForeground(event.index));
				String drawTxt = txt;
				
				// Check the width of the text and truncate it as necessary
				final int maxWidth = Math.max(gc.getClipping().width-6, textBounds.width);
				if(maxWidth > 0) {
					String truncatedTxt = txt;
					while(gc.textExtent(drawTxt).x > maxWidth && truncatedTxt.length() > 0) {
						truncatedTxt = truncatedTxt.substring(0, truncatedTxt.length()-1);
						drawTxt = truncatedTxt + "...";
					}
					
					gc.drawString(drawTxt, textBounds.x, textBounds.y, true);
				}
			}
		});

		nextValueEditingSupport = new NextValueEditingSupport(simulationUiService, treeViewer);
		nextValueColumn.setEditingSupport(nextValueEditingSupport);
		nextValueColumn.getColumn().addControlListener(resizeListener);

		// Make headers and lines visible
		final Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		createContextMenu(tree);

		// Listen for tree resizing
		tree.addControlListener(resizeListener);

		// Create the slider
		frameSlider = new Slider(root, SWT.HORIZONTAL);
		frameSlider.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if(!editingTreeViewer) {
					startEditingTreeViewer();
					frameScrollOffset = frameSlider.getMaximum() - frameSlider.getSelection() - frameSlider.getThumb();
					update();
					stopEditingTreeViewer();
				}
			}			
		});
		GridDataFactory.fillDefaults().grab(true, false).applyTo(frameSlider);

		// Add listener and populate UI with current state
		simulationUiService.addStateChangeListener(stateListener);
		stateListener.onSimulatorStateChanged(simulationUiService.getCurrentState());
	}

	private void startEditingTreeViewer() {
		editingTreeViewer = true;
		treeViewer.getTree().setRedraw(false);
	}

	private void stopEditingTreeViewer() {
		treeViewer.getTree().setRedraw(true);
		editingTreeViewer = false;
	}

	// Updates controls and refreshes the tree viewer
	private void update() {
		final Tree tree = treeViewer.getTree();

		// Get the number of frames
		final SimulatorState simState = (SimulatorState)treeViewer.getInput();

		final int numberOfFrames;
		if(simState == null || simState.getEngineState() == null) {
			numberOfFrames = 0;
		} else {
			numberOfFrames = simState.getEngineState().getNumberOfFrames();
		}

		// Remove extra columns
		for(int valueColumnIndex = valueColumns.size() - 1; valueColumnIndex >= 0 && (valueColumns.size() > numberOfFrames || tree.getHorizontalBar().getVisible()); valueColumnIndex--) {
			final ValueColumn c = valueColumns.get(valueColumnIndex);
			c.dispose();
			valueColumns.remove(valueColumnIndex);
		}

		// Add columns to fill the visible space without causing the tree viewer to have a scrollbar.
		// The max number of columns should be the total number of columns.
		for(int frameIndex = valueColumns.size(); frameIndex < numberOfFrames; frameIndex++) {
			final ValueColumn c = createFrameValueColumn(treeViewer, (VariableContentProvider)treeViewer.getContentProvider());
			if(tree.getHorizontalBar().getVisible()) {
				c.dispose();
				break;
			}

			// Add the tree viewer column to the list
			valueColumns.add(c);
		}

		// Set the text and frame index for the columns based on the columns which are to be shown.
		final int numberOfShownFrames = valueColumns.size();

		final int leftFrameIndex = Math.max(0, numberOfFrames - numberOfShownFrames - frameScrollOffset);
		for(int offset = 0; offset < numberOfShownFrames; offset++) {
			final ValueColumn c = valueColumns.get(offset);
			c.setFrameIndex(leftFrameIndex + offset);
			c.setText(buildColumnHeader(c.getFrameIndex()));
		}

		// Required to populate rows
		treeViewer.refresh();

		// Update scrollbar state
		final int hiddenFrameCount = numberOfFrames - numberOfShownFrames;
		frameSlider.setEnabled(hiddenFrameCount != 0);
		if(hiddenFrameCount > 0) {
			frameSlider.setMinimum(0);
			frameSlider.setMaximum(numberOfFrames);
			frameSlider.setIncrement(1);
			frameSlider.setPageIncrement(numberOfShownFrames);
			frameSlider.setThumb(numberOfShownFrames);
			frameSlider.setSelection(frameSlider.getMaximum() - frameSlider.getThumb() - frameScrollOffset);
		} else {
			frameScrollOffset = 0; // Reset the frame index if the scrollbar is not needed
		}

		// Remove cell color infos that reference invalid frames
		final Iterator<CellColorInfo> infoIt = cellColorInfos.iterator();
		while(infoIt.hasNext()) {
			final CellColorInfo info = infoIt.next();
			if(info.getFrameIndex() >= numberOfFrames) {
				info.dispose();
				infoIt.remove();
			}
		}
	}

	// Creates a new column at the end of the tree viewer and returns an object that can be used to set the columns label and frame index
	private ValueColumn createFrameValueColumn(final TreeViewer treeViewer, final VariableContentProvider contentProvider) {
		final ValueCellLabelProvider valueLabelProvider = new ValueCellLabelProvider(contentProvider);

		final TreeViewerColumn valueColumn = new TreeViewerColumn(treeViewer, SWT.NONE, treeViewer.getTree().getColumnCount());
		valueColumn.getColumn().setWidth(100);
		valueColumn.getColumn().setText("Step ####");
		valueColumn.getColumn().setResizable(false);
		valueColumn.setLabelProvider(valueLabelProvider);
		valueColumn.getColumn().addControlListener(resizeListener);
		valueColumn.getColumn().addSelectionListener(valueColumnSelectedListener);

		return new ValueColumn(valueColumn.getColumn(), valueLabelProvider);
	}

	@PreDestroy
	public void preDestroy() {
		simulationUiService.removeStateChangeListener(stateListener);
		
		if(nextValueEditingSupport != null) {
			nextValueEditingSupport.dispose();
		}
		
		// Dispose of OS resources
		colorManager.dispose();
		greenImage.dispose();
		redImage.dispose();
		yellowImage.dispose();
		customColorImage.dispose();
		contextMenuMgr.dispose();
	}
	
	public void scrollToAndSelectStateElement(final Object stateElement) {
		// Expand the tree so that the item is visible
		treeViewer.expandToLevel(stateElement, 1);
		
		// Find the tree item
		final TreeItem treeItem = treeViewer.findTreeItemByElement(stateElement);
		
		if(treeItem != null) {
			// Scroll if the item is not visible
			final Rectangle treeItemBounds = treeItem.getBounds();
			final Rectangle treeBounds = treeViewer.getTree().getBounds();
			final int treeItemAreaHeight = treeBounds.height - treeViewer.getTree().getHeaderHeight(); // Take into account the size of the header because tree item bounds start at Y=0 but are not drawn at Y=0			
			final boolean treeItemVisible = treeItemBounds.y >= 0 && treeItemAreaHeight >= treeItemBounds.y && treeItemAreaHeight >= (treeItemBounds.y+treeItemBounds.height);
			if(!treeItemVisible) {
				treeViewer.getTree().setTopItem(treeItem);
			}
			
			// Select the item
			treeViewer.getTree().select(treeItem);
		}
	}

	@Inject @Optional
	void beforeCreateStepArguments(final @UIEventTopic(SimulatorUIEvents.BEFORE_STEP_FORWARD) BeforeStepForwardEvent event) {
		// Take the focus away from cell editors widgets
		if(treeViewer != null) {
			final Control focusControl = Display.getCurrent().getFocusControl();
			final Tree tree = treeViewer.getTree();
			for(Control c = focusControl; c != null; c = c.getParent()) {
				if(c == tree) {
					treeViewer.getTree().forceFocus();
					break;
				}
			}
		}
		
		// Cancel the step
		if(elementToConstraintErrorMap.size() > 0) {
			event.cancelStep(new Throwable("One or more user constraints is invalid."));
		}
	}

	private void createContextMenu(final Tree tree) {
		contextMenuMgr.setRemoveAllWhenShown(true);
		contextMenuMgr.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager mgr) {
				if (tree.getSelection().length == 1) {
					fillContextMenu(contextMenuMgr, tree);
				}
			}
		});

		final Menu menu = contextMenuMgr.createContextMenu(tree);
		tree.setMenu(menu);
	}

	//Fill context menu dynamically
	private void fillContextMenu(final MenuManager contextMenu, final Tree tree) {
		if(treeSelectionTracker.isSelectionValid()) {
			final int selectedColumnIndex = treeSelectionTracker.getSelectedColumnIndex();
			final int valueColumnsSize = valueColumns.size();
			final int valueColumnIndex = selectedColumnIndex - (treeViewer.getTree().getColumnCount() - valueColumnsSize);
			final SimulationEngine engine = simulationUiService.getCurrentState().getSimulationEngine();
			
			// Create the Show in Graphical View Menu Item
			if(selectedColumnIndex == VARIABLE_NAME_COLUMN_INDEX) {
				final Object element = treeSelectionTracker.getSelectedTreeItemData();
				final InstanceObject io = (engine != null && engine.getCurrentState() != null) ? engine.getCurrentState().getElementInstanceObject(element) : null;
				if(io != null) {
					contextMenu.add(new Action("Show in Graphical View") {
						@Override
						public void run() {
							showInGraphicalView(io);
						}
					});
				}
				
				// Populate context menu using command extensions
				final CommandContext cmdCtx = new SimpleCommandContext(element, engine.getCurrentState(), engine);
				
				for(final Command cmd : extService.getCommands()) {
					if(cmd.isAvailable(cmdCtx)) {
						contextMenu.add(new Action(cmd.getLabel(cmdCtx)) {
							@Override
							public boolean isEnabled() {
								return cmd.isActivatable(cmdCtx);
							}
							
							@Override
							public void run() {
								cmd.activate(cmdCtx);
							}
						});
					}
				}
			}
			
			// Highlight Menu
			final MenuManager subMenu = new MenuManager("Highlight");
			if (valueColumnIndex >= 0) {
				final int frameIndex = valueColumns.get(valueColumnIndex).getFrameIndex();
				final Object treeItemData = treeSelectionTracker.getSelectedTreeItemData();

				ImageDescriptor imageDesc = ImageDescriptor.createFromImage(greenImage);
				subMenu.add(new Action("Green", imageDesc) {
					@Override
					public void run() {
						highlightCell(treeItemData, frameIndex, green);
					}
				});

				imageDesc = ImageDescriptor.createFromImage(redImage);
				subMenu.add(new Action("Red", imageDesc) {
					@Override
					public void run() {
						highlightCell(treeItemData, frameIndex, red);
					}
				});

				imageDesc = ImageDescriptor.createFromImage(yellowImage);
				subMenu.add(new Action("Yellow", imageDesc) {
					@Override
					public void run() {
						highlightCell(treeItemData, frameIndex, yellow);
					}
				});

				if (customRgb != null) {
					fillColorIconImage(customColorImage, customRgb);
					imageDesc = ImageDescriptor.createFromImage(customColorImage);
					subMenu.add(new Action("Previous", imageDesc) {
						@Override
						public void run() {
							highlightCell(treeItemData, frameIndex, customRgb);
						}
					});
				}

				subMenu.add(new Action("Custom...") {
					@Override
					public void run() {
						final Shell shell = new Shell();
						final Rectangle rect = tree.getParent().getShell().getBounds();
						//offset width and height of color dialog box from center of screen/parent shell
						shell.setLocation((rect.x + rect.width/2) - 150, (rect.y + rect.height/2) - 250);
						final ColorDialog colorDialog = new ColorDialog(shell);
						colorDialog.open();
						if (colorDialog.getRGB() != null) {
							final RGB cellColor = colorDialog.getRGB();
							highlightCell(treeItemData, frameIndex, cellColor);
							customRgb = cellColor;
						}
					}
				});
				contextMenu.add(subMenu);
				
				final CellColorInfo cellColorInfo = getCellColorInfo(treeItemData, frameIndex);
				if (cellColorInfo != null) {
					final CellColorInfo removeCellColorInfo = cellColorInfo;
					contextMenu.add(new Action("Reset Highlight") {
						@Override
						public void run() {
							if(treeViewer != null) {
								cellColorInfos.remove(removeCellColorInfo);
								treeViewer.getTree().redraw();
							}
						}
					});
				}
			}
			
			// Reset all Highlights
			if (!cellColorInfos.isEmpty()) {
				//Resets tree, erases highlights
				contextMenu.add(new Action("Reset All Highlights") {
					@Override
					public void run() {
						final MessageBox dialog = new MessageBox(tree.getShell(), SWT.ICON_QUESTION | SWT.OK | SWT.CANCEL);
						dialog.setText("Reset All Highlights");
						dialog.setMessage("Are you sure you want to reset all highlighted cells?");

						if (dialog.open() == SWT.OK) {
							if(treeViewer != null) {
								cellColorInfos.clear();
								treeViewer.getTree().redraw();
							}
						}		
					}
				});
			}
		}
	}
	
	public void setFocus() {
		if(treeViewer != null) {
			treeViewer.getTree().setFocus();
		}
	}
	
	private void showInGraphicalView(final InstanceObject io) {
		assert(io != null);	
		
		// Open the graphical editor
		final GraphicalEditorService editorService = Objects.requireNonNull((GraphicalEditorService)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getService(GraphicalEditorService.class), "unable to retrieve Graphical Editor Service");
		final SimulationEngine engine = simulationUiService.getCurrentState().getSimulationEngine();
		if(engine != null && engine.getSystemInstance() != null) {			
			final GraphicalEditor editor = editorService.openBusinessObject(engine.getSystemInstance());
			editor.selectDiagramElementsForBusinessObject(io);
		};				
	}
	
	private Image createColorIconImage(final Device device, final RGB rgb) {
		final Image image = new Image(device, 16, 16);

		if(rgb != null) {
			fillColorIconImage(image, rgb);
		}

		return image;
	}
	
	private void fillColorIconImage(final Image image, final RGB rgb) {
		final GC gc = new GC(image);
		final Color color = new Color(image.getDevice(), rgb);
		gc.setBackground(color);
		gc.fillRectangle(0, 0, 16, 16);
		color.dispose();
		gc.dispose();
	}
	
	private void highlightCell(final Object treeItemData, final int frameIndex, final RGB rgb) {
		final CellColorInfo existingCellColorInfo = getCellColorInfo(treeItemData, frameIndex);
		//If selected cell is already in cellColorInfos, update color
		if (existingCellColorInfo != null) {
			existingCellColorInfo.dispose();
			cellColorInfos.remove(existingCellColorInfo);
		} 
		
		// Create a new cell color info
		cellColorInfos.add(new CellColorInfo(treeItemData, frameIndex, rgb));
		treeViewer.getTree().redraw();
	}
	
	private CellColorInfo getCellColorInfo(final Object treeItemData, final int frameIndex) {
		for (final CellColorInfo cellColorInfo : cellColorInfos) {
			if (cellColorInfo.getFrameIndex() == frameIndex && cellColorInfo.getTreeItemData().equals(treeItemData)) {
				return cellColorInfo;
			}
		}
		return null;
	}
	
	private ValueColumn getValueColumn(final TreeColumn treeColumn) {
		for(final ValueColumn valueColumn : valueColumns) {
			if(valueColumn.column == treeColumn) {
				return valueColumn;
			}
		}
		
		return null;
	}
	
	private String buildColumnHeader(final int columnFrameIndex) {
		String columnTxt = "Step " + (columnFrameIndex + 1);
		if(simulationUiService.getCurrentState().getSelectedFrameIndex() == columnFrameIndex) {
			columnTxt += "*";
		}
		return columnTxt;
	}
}
