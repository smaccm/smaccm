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
import org.eclipse.jface.layout.GridDataFactory;
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
import org.eclipse.swt.widgets.Button;
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
import org.jzy3d.plot3d.rendering.scene.Scene;
import org.jzy3d.plot3d.rendering.view.View;

import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.CellEditingSupport;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.ChartElement;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.ChartPoint;
import edu.uah.rsesc.aadlsimulator.ui.dialogs.ChartHelper.YAxisTableItem;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;

public class SimulatePossibilitiesChartDialog {
	private final ChartHelper chartHelper;
	private final Composite container;
	private final Button setValueBtn;
	private final StyledText xValueLabel;
	private final StyledText zValueLabel;
	private final ComboViewer xValueComboViewer;
	private final ComboViewer zValueComboViewer;
	private final TableViewer keyTableViewer;
	private final ScatterMouse scatterMouseSelector;
	private final SelectableScatter selectableScatter;
	private final SetValueSelectionAdapter setValueSelectionAdapter;
	private final List<SimulationEngineState> simulationEngineStates;

	public SimulatePossibilitiesChartDialog(final Shell shell, final SimulationUIService simulationUIService, final List<SimulationEngineState> simulationEngineStates) {
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
			this.simulationEngineStates = simulationEngineStates;
			this.selectableScatter = createSelectableScatter();
			this.scatterMouseSelector = new ScatterMouse();
			scatterMouseSelector.register(chartHelper.get2DChart());
	
			chartHelper.configureShell(shell, "Simulation Possibilities Chart");
	
			this.container = chartHelper.createContainer(shell);
			final Composite tableComposite = ChartHelper.createTableComposite(container);
	
			this.keyTableViewer = chartHelper.createTableViewer(tableComposite, new Action("Remove") {
				@Override
				public void run() {
					final Table table = keyTableViewer.getTable();
					final int removeIndex = table.getSelectionIndex();
	
					// Keep TableViewer items checked/unchecked after item is removed
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
					// Update chart when TableItem is checked/unchecked
					if(e.detail == SWT.CHECK) {
						updateChart(false);
					}
				}
			});
	
			final CellEditingSupport cellEditingSupport = chartHelper.createCellEditingSupport(keyTableViewer);
			cellEditingSupport.getCellEditor(null).getControl().addListener(SWT.Selection, new Listener() {
				@Override
				public void handleEvent(Event event) {
					// Handle new Y-Value selection
					final CCombo combo = (CCombo)event.widget;
					final Table table = keyTableViewer.getTable();
					final YAxisTableItem yAxisTableItem = (YAxisTableItem)keyTableViewer.getCell(combo.getLocation()).getElement();
					final List<YAxisTableItem> yAxisTableItems = chartHelper.getYAxisTableItems();
	
					// Add new Y-Element TableItem if necessary
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
					combo.setText(((YAxisTableItem)keyTableViewer.getCell(combo.getLocation()).getElement()).getChartElement().toString());
				}
			});
	
			chartHelper.configureKeyTableViewer(cellEditingSupport);
	
			final Composite chart3DComposite = chartHelper.createChartComposite(container, true);
			Bridge.adapt(chart3DComposite, (Component)chartHelper.get3DChart().getCanvas());
	
			final Composite chart2DComposite = chartHelper.createChartComposite(container, false);
			Bridge.adapt(chart2DComposite, (Component)chartHelper.get2DChart().getCanvas());
			
			final Composite menuComposite = chartHelper.createXZComposite(container);
			ChartHelper.createLabel(menuComposite, "X-Axis:");
	
			this.xValueComboViewer = chartHelper.createComboViewer(menuComposite, new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					updateChart(true);
				}
			}, chartHelper.getChartElements());
	
			this.xValueLabel = chartHelper.createValueLabel(menuComposite);
			ChartHelper.createLabel(menuComposite, "Z-Axis:");
	
			this.zValueComboViewer = chartHelper.createComboViewer(menuComposite, new ISelectionChangedListener() {
				@Override
				public void selectionChanged(SelectionChangedEvent event) {
					clearValueInfo();
					final Object zChartElementData = ((ChartElement)zValueComboViewer.getStructuredSelection().getFirstElement()).getSimulationStateElement();
					final boolean chartBool = chartHelper.is2DChart();
	
					if((chartBool && zChartElementData != null) || (!chartBool && zChartElementData == null)) {
						scatterMouseSelector.register(chartBool ? chartHelper.get3DChart() : chartHelper.get2DChart());
						updateView(chartBool);
						chartHelper.setColor(zValueLabel);
					}
	
					updateChart(true);
				}
	
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
	
			this.zValueLabel = chartHelper.createValueLabel(menuComposite);
			zValueLabel.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
	
			this.setValueSelectionAdapter = new SetValueSelectionAdapter();
	
			this.setValueBtn = new Button(menuComposite, SWT.NONE);
			GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).applyTo(setValueBtn);
			setValueBtn.setText("Step Forward");
			setValueBtn.setEnabled(false);
			setValueBtn.addSelectionListener(setValueSelectionAdapter);
	
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

	private void clearValueInfo() {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				xValueLabel.setText("");
				zValueLabel.setText("");
				for(final YAxisTableItem yElement : chartHelper.getYAxisTableItems()) {
					yElement.setYValueString("");
				}

				setValueBtn.setEnabled(false);
				keyTableViewer.refresh();
			}
		});
	}
	
	public List<ChartElement> getChartElements() {
		return chartHelper.getChartElements();
	}

	public SetValueSelectionAdapter getSetValueSelectionAdapter() {
		return setValueSelectionAdapter;
	}

	private void updateChart(boolean resetChartBounds) {
		populateChart();
		chartHelper.setChartPointsAndBounds(resetChartBounds, selectableScatter);
		scatterMouseSelector.clearLastSelection();
		container.forceFocus();
		clearValueInfo();
	}

	private void updateChartPoints(final Object xComboSelection, final YAxisTableItem yAxisTableItem, final Object zComboSelection, final Color color) {
		final ChartElement yChartElement = yAxisTableItem.getChartElement();

		if(yChartElement != null && yChartElement.getSimulationStateElement() != null) {
			chartHelper.getChartPoints().addAll(createPoints(xComboSelection, yChartElement.getSimulationStateElement(), zComboSelection, color));
			keyTableViewer.update(yAxisTableItem, null);
		}
	}

	private List<ChartPoint> createPoints(final Object xElementData, final Object yElementData, final Object zElementData, final Color color) {
		final List<ChartPoint> chartPoints = new ArrayList<>();

		for(final SimulationEngineState state : simulationEngineStates) {
			final Object xElementValue = state.getElementValue(state.getNumberOfFrames()-1, xElementData);
			final Object yElementValue = state.getElementValue(state.getNumberOfFrames()-1, yElementData);

			if(xElementValue != null && yElementValue != null) {
				Object zElementValue = null;
				if(zElementData != null) {
					zElementValue = state.getElementValue(state.getNumberOfFrames()-1, zElementData);
				}

				final ChartPoint chartPoint = chartHelper.createChartPoint(simulationEngineStates.indexOf(state), color);
				if(!chartHelper.setChartPointInfo(chartPoint, xElementValue, yElementValue, zElementValue)) {
					chartPoints.add(chartPoint);
				}
			}
		}

		return chartPoints;
	}

	private void populateChart() {
		final Object xComboSelection = xValueComboViewer.getStructuredSelection().getFirstElement();
		final Object zComboSelection = zValueComboViewer.getStructuredSelection().getFirstElement();
		Object xChartElementData = null;
		Object zChartElementData = null;

		if(xComboSelection != null) {
			xChartElementData = ((ChartElement)xComboSelection).getSimulationStateElement();
		}

		if(zComboSelection != null) {
			zChartElementData = ((ChartElement)zComboSelection).getSimulationStateElement();
		}

		chartHelper.clearChartPoints();
		if(xChartElementData != null && (chartHelper.is2DChart() || (zChartElementData != null))) {
			final List<YAxisTableItem> yAxisTableItems = chartHelper.getYAxisTableItems();
			final Color[] yAxisTableItemColors = chartHelper.getYAxisTableItemColors();

			for(final YAxisTableItem yAxisTableItem : yAxisTableItems) {
				if(keyTableViewer.getTable().getItem(yAxisTableItems.indexOf(yAxisTableItem)).getChecked()) {
					updateChartPoints(xChartElementData, yAxisTableItem, zChartElementData, yAxisTableItemColors[yAxisTableItems.indexOf(yAxisTableItem) % yAxisTableItemColors.length]);
				}
			}
		}
	}

	// Scatter plot with selectable points
	private SelectableScatter createSelectableScatter() {
		final SelectableScatter selectableScatter = new SelectableScatter(new Coord3d[]{new Coord3d(3,10,0)}, new Color[]{new Color(0.0f, 0.0f, 0.0f, 0.0f)});
		selectableScatter.setDisplayed(true);
		selectableScatter.setWidth(ChartHelper.getPointWidth());
		selectableScatter.getBounds().add(new BoundingBox3d(-5, 5, -5, 5, 0, 1));
		selectableScatter.setBoundingBoxColor(new Color(0,0,0,0.5f));

		// Chart is initially 2D
		chartHelper.get2DChart().addDrawable(selectableScatter);

		return selectableScatter;
	}

	public class SetValueSelectionAdapter extends SelectionAdapter {
		private Integer indexOfState = null;

		public SimulationEngineState getSelectedState() {
			return indexOfState == null ? null : simulationEngineStates.get(indexOfState);
		}

		@Override
		public void widgetSelected(final SelectionEvent e) {
			indexOfState = scatterMouseSelector.selectedIndex;
			container.getShell().close();
		}
	}

	private class ScatterMouse extends AWTScatterMouseSelector {
		private java.awt.Point selectionPoint; // Mouse point selected by user
		private Integer selectedIndex; // Index of plot point selected
		private boolean dragging = false;
		private int mouseButton;

		private ScatterMouse() {
			super(selectableScatter);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			selectionPoint = e.getPoint();
			mouseButton = e.getButton();
			clearLastSelection();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			dragging = true;
			selectionPoint = chartHelper.handleChartTransformation(mouseButton ,selectionPoint, e.getPoint());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(!dragging && e.getButton() == 1) {
				selectionPoint = e.getPoint();
				// Reset highlighting for new selection
				selectableScatter.resetHighlighting();
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
							final SimulationEngineState simulationState = simulationEngineStates.get(chartPoint.getIndexOfState());
							final Object xVal = ((ChartElement)((IStructuredSelection)xValueComboViewer.getStructuredSelection()).getFirstElement()).getSimulationStateElement();
							final int frame = simulationState.getNumberOfFrames()-1;

							xValueLabel.setText(ChartHelper.valueToString(simulationState.getElementValue(frame, xVal)));

							if(!chartHelper.is2DChart()) {
								zValueLabel.setText(ChartHelper.valueToString(simulationState.getElementValue(frame, ((ChartElement)((IStructuredSelection)zValueComboViewer.getStructuredSelection()).getFirstElement()).getSimulationStateElement())));
							}

							for(final YAxisTableItem yElement : chartHelper.getYAxisTableItems()) {
								if(yElement.getChartElement().getSimulationStateElement() != null) {
									if(!chartHelper.getChartPoints().isEmpty()) {
										yElement.setYValueString(ChartHelper.valueToString(simulationState.getElementValue(frame, yElement.getChartElement().getSimulationStateElement())));
									}
								}
							}

							// Enable button on selection
							setValueBtn.setEnabled(true);
							keyTableViewer.refresh();
						}
					});
				} else {
					clearValueInfo();
				}
			}
		}
	}
}