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
package edu.uah.rsesc.aadlsimulator.ui.dialogs;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.PlatformUI;
import org.jzy3d.bridge.swt.Bridge;
import org.jzy3d.chart.controllers.mouse.selection.AWTScatterMouseSelector;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.BoundingBox3d;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.IntegerCoord2d;
import org.jzy3d.plot3d.primitives.selectable.SelectableScatter;
import org.jzy3d.plot3d.rendering.compat.GLES2CompatUtils;
import org.jzy3d.plot3d.rendering.scene.Scene;
import org.jzy3d.plot3d.rendering.view.Camera;
import org.jzy3d.plot3d.rendering.view.View;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.glu.GLU;

import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.CellEditingSupport;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.ChartElement;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.ChartPoint;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.YAxisTableItem;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;

public class VariablesChartDialog {
	private final ChartHelper chartHelper;
	private final Composite container;
	private final StyledText xValueLabel;
	private final StyledText zValueLabel;
	private final ComboViewer xValueComboViewer;
	private final ComboViewer zValueComboViewer;
	private final TableViewer keyTableViewer;
	private final ScatterMouseSelector scatterMouseSelector;
	private final SimulationEngineState engineState;
	private final ConnectedPointsScatter connectedPointScatter;
	private final List<Integer> currentSimulationSteps;

	public VariablesChartDialog(final Shell shell, final SimulationUIService simulationUIService) {
		final IWorkbenchListener workbenchListener = new IWorkbenchListener() {
			@Override
			public boolean preShutdown(IWorkbench workbench, boolean forced) {
				if(shell != null && !shell.isDisposed()) {
					shell.close();
				}

				return true;
			}

			@Override
			public void postShutdown(IWorkbench workbench) {}
		};

		PlatformUI.getWorkbench().addWorkbenchListener(workbenchListener);
		
		BasicConfigurator.configure();
		
		this.chartHelper = new ChartHelper(simulationUIService);
		try {
			this.engineState = simulationUIService.getCurrentState().getEngineState();
			this.connectedPointScatter = createConnectedPointScatter();
			this.scatterMouseSelector = new ScatterMouseSelector();
			scatterMouseSelector.register(chartHelper.get2DChart());
	
			chartHelper.configureShell(shell, "Simulation Variables Chart");
			this.container = chartHelper.createContainer(shell);
			
			final Composite tableComposite = ChartHelper.createTableComposite(container);
			this.keyTableViewer = chartHelper.createTableViewer(tableComposite, new Action("Remove") {
				@Override
				public void run() {
					final Table table = keyTableViewer.getTable();
					final int removeIndex = table.getSelectionIndex();
	
					// Keep TableViewer items checked/unchecked
					final List<Boolean> selected = new ArrayList<>();
					for(int i = removeIndex+1; i < table.getItemCount(); i++) {
						selected.add(table.getItems()[i].getChecked());
					}
	
					keyTableViewer.setInput(chartHelper.getYAxisTableItems().remove(table.getSelectionIndex()));
	
					for(int i = 0; i < selected.size(); i++) {
						table.getItems()[i+removeIndex].setChecked(selected.get(i));
					}
	
					updateChart(true);
					keyTableViewer.refresh();
				}
			}, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					if(e.detail == SWT.CHECK) {
						updateChart(false);
					}
				}
			});
	
			final CellEditingSupport cellEditingSupport = chartHelper.createCellEditingSupport(keyTableViewer);
			cellEditingSupport.getCellEditor(null).getControl().addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event event) {
					final CCombo combo = (CCombo)event.widget;
					final Table table = keyTableViewer.getTable();
					final List<YAxisTableItem> yAxisTableItems = chartHelper.getYAxisTableItems();
					final YAxisTableItem yAxisTableItem = (YAxisTableItem)keyTableViewer.getCell(combo.getLocation()).getElement();
	
					// Add YAxisTableItem if necessary
					if(yAxisTableItems.indexOf(yAxisTableItem) == yAxisTableItems.size()-1) {
						keyTableViewer.setInput(yAxisTableItems.add(chartHelper.createYAxisTableItem()));
						table.getItem(table.getItems().length-1).setChecked(true);
					}
	
					cellEditingSupport.setValue(yAxisTableItem, combo.getSelectionIndex());
					updateChart(true);
				}
			});
	
			cellEditingSupport.getCellEditor(null).getControl().addFocusListener(new FocusAdapter() {
				@Override
				public void focusGained(FocusEvent event) {
					final CCombo combo = (CCombo)event.widget;
					final ChartElement chartElement = ((YAxisTableItem)keyTableViewer.getCell(combo.getLocation()).getElement()).getChartElement();
	
					combo.setText(chartElement.toString());
				}
			});
	
			chartHelper.configureKeyTableViewer(cellEditingSupport);
	
			final Composite chart3DComposite = chartHelper.createChartComposite(container, true);
			Bridge.adapt(chart3DComposite, (Component)chartHelper.get3DChart().getCanvas());
	
			final Composite chart2DComposite = chartHelper.createChartComposite(container, false);
			Bridge.adapt(chart2DComposite, (Component)chartHelper.get2DChart().getCanvas());
			
			final Composite xzComposite = chartHelper.createXZComposite(container);
			ChartHelper.createLabel(xzComposite, "X-Axis:");
	
			this.currentSimulationSteps = getCurrentSimulationSteps();
			this.xValueComboViewer = chartHelper.createComboViewer(xzComposite, null, new ChartElement[]{chartHelper.createStepsElement()});
			// Select Step as x axis variable
			xValueComboViewer.getCombo().select(0);
			// Cannot be changed
			xValueComboViewer.getCombo().setEnabled(false);
	
			this.xValueLabel = chartHelper.createValueLabel(xzComposite);
			ChartHelper.createLabel(xzComposite, "Z-Axis:");
			this.zValueComboViewer = chartHelper.createComboViewer(xzComposite, new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					clearValueInfo();
					final Object zAGREEElementData = ((ChartElement)zValueComboViewer.getStructuredSelection().getFirstElement()).getSimulationStateElement();
					final boolean chartBool = chartHelper.is2DChart();
	
					if((chartBool && zAGREEElementData != null) || (!chartBool && zAGREEElementData == null)) {
						scatterMouseSelector.register(chartBool ? chartHelper.get3DChart() : chartHelper.get2DChart());
						updateView(chartBool);
						chartHelper.setColor(zValueLabel);
					}
	
					updateChart(true);
				}
	
				// Switch between 2D and 3D charts
				private void updateView(final boolean chartBool) {
					chartHelper.setIs2DChart(!chartBool);
	
					GridData gridData = (GridData)chart3DComposite.getLayoutData();
					gridData.exclude = !chartBool;
					chart3DComposite.setLayoutData(gridData);
	
					gridData = (GridData)chart2DComposite.getLayoutData();
					gridData.exclude = chartBool;
	
					chart2DComposite.setLayoutData(gridData);
					chart3DComposite.setVisible(chartBool);
					chart2DComposite.setVisible(!chartBool);
					
					container.layout();
				}
			}, chartHelper.getChartElements());
	
			this.zValueLabel = chartHelper.createValueLabel(xzComposite);
			zValueLabel.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
	
			shell.open();
			shell.forceActive();
			shell.forceFocus();
			
			while(!container.isDisposed()) {
				if(!Display.getDefault().readAndDispatch()) {
					Display.getDefault().sleep();
				}
			}
		} finally {
			chartHelper.dispose();		
			PlatformUI.getWorkbench().removeWorkbenchListener(workbenchListener);
		}
	}
	
	private void updateChart(final boolean resetChartBounds) {
		populateChart();
		chartHelper.setChartPointsAndBounds(resetChartBounds, connectedPointScatter);
		scatterMouseSelector.clearLastSelection();
		container.forceFocus();
		clearValueInfo();
	}

	private ArrayList<Integer> getCurrentSimulationSteps() {
		final ArrayList<Integer> steps = new ArrayList<>();
		for(int i = 1; i <= engineState.getNumberOfFrames(); i++) {
			steps.add(new Integer(i));
		}

		return steps;
	}

	private void updateChart(final YAxisTableItem yElement, final Object zComboSelection, final Color color) {
		final ChartElement yComboSelection = yElement.getChartElement();

		if(yComboSelection != null && yComboSelection.getSimulationStateElement() != null) {
			chartHelper.getChartPoints().addAll(createPoints(yComboSelection.getSimulationStateElement(), zComboSelection, color));
			keyTableViewer.update(yElement, null);
		}
	}

	public void populateChart() {
		chartHelper.getChartPoints().clear();
		final Object zComboSelection = zValueComboViewer.getStructuredSelection().getFirstElement();
		Object zAGREEElementData = null;

		if(zComboSelection != null) {
			zAGREEElementData = ((ChartElement)zComboSelection).getSimulationStateElement();
		}

		final List<YAxisTableItem> yAxisTableItems = chartHelper.getYAxisTableItems();
		final Color[] yElementColors = chartHelper.getYAxisTableItemColors();

		for(final YAxisTableItem yAxisTableItem : yAxisTableItems) {
			if(keyTableViewer.getTable().getItem(yAxisTableItems.indexOf(yAxisTableItem)).getChecked()) {
				updateChart(yAxisTableItem, zAGREEElementData, yElementColors[yAxisTableItems.indexOf(yAxisTableItem) % yElementColors.length]);
			}
		}
	}

	private List<ChartPoint> createPoints(final Object yElementData, final Object zElementData, final Color color) {
		final List<ChartPoint> chartPoints = new ArrayList<>();
		for(int i = 0; i < engineState.getNumberOfFrames(); i++) {
			final Object xElementValue = currentSimulationSteps.get(i);
			final Object yElementValue = engineState.getElementValue(i, yElementData);

			if(xElementValue != null && yElementValue != null) {
				Object zElementValue = null;
				if(zElementData != null) {
					zElementValue = engineState.getElementValue(i, zElementData);
				}

				final ChartPoint chartPoint = chartHelper.createChartPoint(i, color);
				if(!chartHelper.setChartPointInfo(chartPoint, xElementValue, yElementValue, zElementValue)) {
					chartPoints.add(chartPoint);
				}
			}
		}

		return chartPoints;
	}

	private void clearValueInfo() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				xValueLabel.setText("");
				zValueLabel.setText("");
				for(final YAxisTableItem yElement : chartHelper.getYAxisTableItems()) {
					yElement.setYValueString("");
				}

				keyTableViewer.refresh();
			}
		});
	}

	private class ScatterMouseSelector extends AWTScatterMouseSelector {
		private java.awt.Point selectionPoint; // Mouse point selected by user
		private Integer selectedIndex; // Index of plot point selected
		private boolean dragging = false;
		private int button;

		public ScatterMouseSelector() {
			super(connectedPointScatter);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			selectionPoint = e.getPoint();
			button = e.getButton();
			clearLastSelection();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			dragging = true;
			selectionPoint = chartHelper.handleChartTransformation(button, selectionPoint, e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// If user is not dragging and is left click
			if(!dragging && e.getButton() == 1) {
				selectionPoint = e.getPoint();
				// Reset highlighting for new selection
				connectedPointScatter.resetHighlighting();
				// Highlight selected point
				releaseSelection(e);
			}

			selectionPoint = null;
			dragging = false;
		}

		@Override
		protected void processSelection(Scene scene, View view, int width, int height) {
			if(!chartHelper.getChartPoints().isEmpty()) {
				selectedIndex = null;
				view.project();

				final int pointRadius = (int)ChartHelper.getPointWidth()/2;
				// Bounds to see if mouse was down in point bound
				final IntegerCoord2d boundIn = new IntegerCoord2d(selectionPoint.x-pointRadius, selectionPoint.y-pointRadius);
				final IntegerCoord2d boundOut = new IntegerCoord2d(selectionPoint.x+pointRadius, selectionPoint.y+pointRadius);

				final Coord3d[] projection = scatter.getProjection();
				for(int i = 0; i < projection.length; i++) {
					// Check if point is selected
					if(matchRectangleSelection(boundIn, boundOut, projection[i], width, height)) {
						selectedIndex = i;
					}
				}

				// If point is selected
				if(selectedIndex != null) {
					scatter.setHighlighted(selectedIndex, true);
					final ChartPoint chartPoint = chartHelper.getChartPoints().get(selectedIndex);
					selectedIndex = chartPoint.getIndexOfState();

					// Set table and label values
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							final int indexOfState = chartPoint.getIndexOfState();
							xValueLabel.setText(currentSimulationSteps.get(indexOfState).toString());

							if(!chartHelper.is2DChart()) {
								zValueLabel.setText(ChartHelper.valueToString(engineState.getElementValue(indexOfState, ((ChartElement)((IStructuredSelection)zValueComboViewer.getStructuredSelection()).getFirstElement()).getSimulationStateElement())));
							}

							for(final YAxisTableItem yElement : chartHelper.getYAxisTableItems()) {
								if(yElement.getChartElement().getSimulationStateElement() != null) {
									if(!chartHelper.getChartPoints().isEmpty()) {
										yElement.setYValueString(ChartHelper.valueToString(engineState.getElementValue(indexOfState, yElement.getChartElement().getSimulationStateElement())));
									}
								}
							}

							keyTableViewer.refresh();
						}
					});
				} else {
					clearValueInfo();
				}
			}
		}
	}




	public class ConnectedPointsScatter extends SelectableScatter {
		public ConnectedPointsScatter(Coord3d[] coordinates, Color[] colors) {
			super(coordinates, colors);
		}

		@Override
		public void draw(GL gl, GLU glu, Camera cam) {
			if(coordinates != null && coordinates.length > 0 && colors != null && colors.length > 0) {
				if (gl.isGL2()) {
					gl.getGL2().glBegin(GL.GL_LINES);
					Coord3d prevCoord3d = coordinates[0];
					Color prevColor = colors[0];

					for(int i = 1; i < coordinates.length; i++) {
						final Coord3d c = coordinates[i];
						final Color color = colors[i];

						if(prevColor == color) {
							// Draw line point to point
							gl.getGL2().glColor4f(color.r, color.g, color.b, color.a);
							gl.getGL2().glVertex3f(prevCoord3d.x, prevCoord3d.y, prevCoord3d.z);
							gl.getGL2().glVertex3f(c.x, c.y, c.z);
						}

						prevCoord3d = c;
						prevColor = color;
					}

					gl.getGL2().glEnd();
				} else {
					gl.getGL2().glBegin(GL.GL_LINES);
					Coord3d prevCoord3d = coordinates[0];
					Color prevColor = colors[0];

					for(int i = 1; i < coordinates.length; i++) {
						final Coord3d c = coordinates[i];
						final Color color = colors[i];

						if(prevColor == color) {
							// Draw line point to point
							GLES2CompatUtils.glColor4f(color.r, color.g, color.b, color.a);
							GLES2CompatUtils.glVertex3f(prevCoord3d.x, prevCoord3d.y, prevCoord3d.z);
							GLES2CompatUtils.glVertex3f(c.x, c.y, c.z);
						}

						prevCoord3d = c;
						prevColor = color;
					}

					GLES2CompatUtils.glEnd();
				}
			}

			super.draw(gl, glu, cam);

		}
	}

	// Scatter plot with selectable connected points
	public ConnectedPointsScatter createConnectedPointScatter() {
		final ConnectedPointsScatter selectableScatter = new ConnectedPointsScatter(new Coord3d[]{new Coord3d(3,10,0)}, new Color[]{new Color(0.0f, 0.0f, 0.0f, 0.0f)});
		selectableScatter.setDisplayed(true);
		selectableScatter.setWidth(ChartHelper.getPointWidth());
		selectableScatter.getBounds().add(new BoundingBox3d(-5, 5, -5, 5, 0, 1));
		selectableScatter.setBoundingBoxColor(new Color(0,0,0,0.5f));

		// Chart is initially 2D
		chartHelper.get2DChart().addDrawable(selectableScatter);

		return selectableScatter;
	}
}
