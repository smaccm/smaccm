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

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.resource.DeviceResourceManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.jzy3d.chart.Chart;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.BoundingBox3d;
import org.jzy3d.maths.Coord2d;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Scale;
import org.jzy3d.plot3d.primitives.axes.AxeBox;
import org.jzy3d.plot3d.primitives.selectable.SelectableScatter;
import org.jzy3d.plot3d.rendering.canvas.CanvasAWT;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.compat.GLES2CompatUtils;
import org.jzy3d.plot3d.rendering.view.Camera;
import org.jzy3d.plot3d.rendering.view.View;
import org.jzy3d.plot3d.rendering.view.ViewportMode;
import org.jzy3d.plot3d.rendering.view.modes.ViewPositionMode;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2GL3;

import edu.uah.rsesc.aadlsimulator.Rational;
import edu.uah.rsesc.aadlsimulator.SimulationEngineState;
import edu.uah.rsesc.aadlsimulator.VariableType;
import edu.uah.rsesc.aadlsimulator.ui.ValueFormatter;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;

public class ChartHelper {
	private final SimulationUIService simulationUIService;
	private final Chart chart2d;
	private final Chart chart3d;
	private final DeviceResourceManager resourceMgr;
	private final org.eclipse.swt.graphics.Color[] yAxisSWTColors;
	private final List<ChartElement> chartElements;
	private final List<YAxisTableItem> yAxisTableItems = new ArrayList<>();
	private final List<ChartPoint> chartPoints = new ArrayList<>();
	private final Color[] yAxisJzy3dColors = new Color[] {new Color(0, 175, 52), new Color(255, 150, 0), new Color(175, 0, 175), new Color(0, 58, 238)};
	private boolean is2DChart = true;
	private char pressedKey = ' ';

	public ChartHelper(final SimulationUIService simulationUIService) {
		this.simulationUIService = simulationUIService;
		this.chartElements = createChartElements();
		this.resourceMgr = new DeviceResourceManager(Display.getCurrent());
		this.yAxisSWTColors = new org.eclipse.swt.graphics.Color[] {
				resourceMgr.createColor(new RGB(0, 175, 52)),
				resourceMgr.createColor(new RGB(255, 150, 00)),
				resourceMgr.createColor(new RGB(175, 0, 175)),
				resourceMgr.createColor(new RGB(0, 58, 238))
		};

		this.chart2d = create2DChart();
		this.chart3d = create3DChart();
	}	

	public void dispose() {
		chart3d.dispose();
		chart2d.dispose();
		resourceMgr.dispose();
	}

	public void configureShell(final Shell shell, final String title) {
		shell.setText(title);
		GridLayoutFactory.fillDefaults().extendedMargins(0, 0, 0, 9).applyTo(shell);
	}

	public Color[] getYAxisTableItemColors() {
		return yAxisJzy3dColors;
	}

	public Composite createContainer(final Shell shell) {
		final Composite container = new Composite(shell, SWT.NONE);
		GridLayoutFactory.fillDefaults().equalWidth(false).numColumns(3).applyTo(container);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(container);
		container.addKeyListener(getKeyListener());

		return container;
	}

	private Chart create2DChart() {
		final Chart chart2d = AWTChartComponentFactory.chart(Quality.Nicest, "awt");
		chart2d.stopAnimator();
		
		final View view = chart2d.getView();
		view.getCamera().setViewportMode(ViewportMode.STRETCH_TO_FILL);
		chart2d.setViewMode(ViewPositionMode.TOP);
		// Initialize viewpoint
		chart2d.setViewPoint(new Coord3d(5,5,0));

		((CanvasAWT)chart2d.getCanvas()).addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				final float factor = 1 + (e.getWheelRotation()/10.0f);
				final BoundingBox3d curScale = view.getBounds();

				final Coord3d center = curScale.getCenter();
				final double xMin = center.x + (curScale.getXmin()-center.x) * factor;
				final double xMax = center.x + (curScale.getXmax()-center.x) * factor;
				final double yMin = center.y + (curScale.getYmin()-center.y) * factor;
				final double yMax = center.y + (curScale.getYmax()-center.y) * factor;

				Scale xScale = null;
				if(xMin<xMax) {
					xScale = new Scale(xMin, xMax);
				}

				Scale yScale = null;
				if(yMin<yMax) {
					yScale = new Scale(yMin, yMax);
				}

				// Check scale limits
				if(canScaleAxis(xScale, factor, curScale.getXmax(), curScale.getXmin(), xMax, xMin)) {
					curScale.setXmin((float)xScale.getMin());
					curScale.setXmax((float)xScale.getMax());
				}

				// Check scale limits
				if(canScaleAxis(yScale, factor, curScale.getYmax(), curScale.getYmin(), yMax, yMin)) {
					curScale.setYmin((float)yScale.getMin());
					curScale.setYmax((float)yScale.getMax());
				}

				view.setBoundManual(curScale);
				chart2d.getCanvas().forceRepaint();
			}
		});

		return chart2d;
	}

	public Chart create3DChart() {
		final Chart chart3d = AWTChartComponentFactory.chart(Quality.Nicest, "awt");
		chart3d.stopAnimator();
		
		final View view = chart3d.getView();
		view.getCamera().setViewportMode(ViewportMode.STRETCH_TO_FILL);
		view.setAxe(new ChartAxe(view.getBounds()));
		// Set Y label to "Z" for depth plane
		chart3d.getAxeLayout().setYAxeLabel("Z");
		// Set Z label to "Y" for vertical plane
		chart3d.getAxeLayout().setZAxeLabel("Y");
		// Initialize viewpoint
		chart3d.setViewPoint(new Coord3d(-1.5, 0.5, 1.5));

		((CanvasAWT)chart3d.getCanvas()).addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				final float factor = 1 + (e.getWheelRotation()/10.0f);
				final BoundingBox3d curScale = view.getBounds();

				final Coord3d center = curScale.getCenter();
				final double xMin = center.x + (curScale.getXmin()-center.x) * factor;
				final double xMax = center.x + (curScale.getXmax()-center.x) * factor;
				final double yMin = center.y + (curScale.getYmin()-center.y) * factor;
				final double yMax = center.y + (curScale.getYmax()-center.y) * factor;
				final double zMin = center.z + (curScale.getZmin()-center.z) * factor;
				final double zMax = center.z + (curScale.getZmax()-center.z) * factor;

				Scale xScale = null;
				if(xMin<xMax) {
					xScale = new Scale(xMin, xMax);
				}

				Scale yScale = null;
				if(yMin<yMax) {
					yScale = new Scale(yMin, yMax);
				} 

				Scale zScale = null;
				if(zMin<zMax) {
					zScale = new Scale(zMin, zMax);
				}

				if(canScaleAxis(xScale, factor, curScale.getXmax(), curScale.getXmin(), xMax, xMin)) {
					curScale.setXmin((float)xScale.getMin());
					curScale.setXmax((float)xScale.getMax());
				}

				if(canScaleAxis(yScale, factor, curScale.getYmax(), curScale.getYmin(), yMax, yMin)) {
					curScale.setYmin((float)yScale.getMin());
					curScale.setYmax((float)yScale.getMax());
				}

				if(canScaleAxis(zScale, factor, curScale.getZmax(), curScale.getZmin(), zMax, zMin)) {
					curScale.setZmin((float)zScale.getMin());
					curScale.setZmax((float)zScale.getMax());
				}

				view.setBoundManual(curScale);
				chart3d.getCanvas().forceRepaint();
			}
		});

		((CanvasAWT)chart3d.getCanvas()).addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				pressedKey = e.getKeyChar();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				pressedKey = ' ';
			}
		});

		return chart3d;
	}

	private static boolean canScaleAxis(final Scale scale, final float factor, final float boundsMax, final float boundsMin, final double max, final double min) {
		return scale != null && (boundsMax - boundsMin > 0.5 || factor > 1) && max-min < Math.pow(2,22);
	}

	public List<ChartElement> getChartElements() {
		return chartElements;
	}

	public List<YAxisTableItem> getYAxisTableItems() {
		return yAxisTableItems;
	}

	public Chart get2DChart() {
		return chart2d;
	}

	public Chart get3DChart() {
		return chart3d;
	}

	private KeyListener getKeyListener() {
		return new KeyListener() {
			@Override
			public void keyReleased(org.eclipse.swt.events.KeyEvent e) {
				pressedKey = ' ';
			}

			@Override
			public void keyPressed(org.eclipse.swt.events.KeyEvent e) {
				pressedKey = e.character;
			}
		};
	}

	public static float getPointWidth() {
		return 12;
	}

	public ChartPoint createChartPoint(final int indexOfState, final Color color) {
		return new ChartPoint(indexOfState, color);
	}

	public List<ChartPoint> getChartPoints() {
		return chartPoints;
	}

	public void setChartPointsAndBounds(final boolean resetBounds, final SelectableScatter selectableScatter) {
		final Chart chart = is2DChart ? chart2d : chart3d;
		BoundingBox3d simPlotBounds;

		if(!chartPoints.isEmpty() && resetBounds) {
			simPlotBounds = new BoundingBox3d();
			
			for(final YAxisTableItem yAxisTableItem : yAxisTableItems) {
				if(yAxisTableItem.chartElement != null) {
					final Float minX = Collections.min(chartPoints, xValueComparator).x;
					final Float maxX = Collections.max(chartPoints, xValueComparator).x;
					final Float minY = Collections.min(chartPoints, yValueComparator).y;
					final Float maxY = Collections.max(chartPoints, yValueComparator).y;
					final Float minZ = Collections.min(chartPoints, zValueComparator).z;
					final Float maxZ = Collections.max(chartPoints, zValueComparator).z;

					if(minY != null && maxY != null && minX != null && maxX != null) {
						simPlotBounds.setXmin(minX);
						simPlotBounds.setXmax(maxX);
						simPlotBounds.setYmin(minY);
						simPlotBounds.setYmax(maxY);

						if(minZ != null && maxZ != null) {
							simPlotBounds.setZmin(minZ);
							simPlotBounds.setZmax(maxZ);
						}
					}
				}
			}
		} else {
			simPlotBounds = chart.getView().getBounds();
		}

		if(simPlotBounds.getXmin() == simPlotBounds.getXmax()) {
			simPlotBounds.setXmin(simPlotBounds.getXmin()-0.5f);
			simPlotBounds.setXmax(simPlotBounds.getXmax()+0.5f);
		}

		if(simPlotBounds.getYmin() == simPlotBounds.getYmax()) {
			simPlotBounds.setYmin(simPlotBounds.getYmin()-0.5f);
			simPlotBounds.setYmax(simPlotBounds.getYmax()+0.5f);
		}

		if(is2DChart) {
			simPlotBounds.setZmin(0);
			simPlotBounds.setZmax(1);
		} else if(simPlotBounds.getZmin() == simPlotBounds.getZmax()) {
			simPlotBounds.setZmin(simPlotBounds.getZmin()-0.5f);
			simPlotBounds.setZmax(simPlotBounds.getZmax()+0.5f);
		}

		final List<Coord3d> coords = new ArrayList<>();
		final List<Color> colors = new ArrayList<>();
		for(final ChartPoint point : chartPoints) {
			coords.add(new Coord3d(point.x, point.y, point.z));
			colors.add(point.color);
		}

		selectableScatter.clear();
		selectableScatter.setData(coords.toArray(new Coord3d[coords.size()]));
		selectableScatter.setColors(colors.toArray(new Color[colors.size()]));

		chart.getScene().add(selectableScatter);
		chart.getView().setBoundManual(simPlotBounds);
		chart.getCanvas().forceRepaint();
	}

	// Gray StyledText when not available
	public void setColor(final StyledText styledText) {
		styledText.setBackground(is2DChart ? Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_LIGHT_SHADOW) : Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
	}

	public YAxisTableItem createYAxisTableItem() {
		return new YAxisTableItem();
	}

	public static String valueToString(final Object elementValue) {
		if(elementValue instanceof BigInteger) {
			return ((BigInteger)elementValue).toString();
		} else if(elementValue instanceof Rational) {
			return ValueFormatter.formatValue(elementValue);
		} else if(elementValue instanceof Boolean) {
			return elementValue == Boolean.FALSE ? Boolean.FALSE.toString() : Boolean.TRUE.toString();
		}

		return "";
	}

	public void setIs2DChart(final boolean is2DChart) {
		this.is2DChart = is2DChart;
	}

	public StyledText createValueLabel(final Composite menuComposite) {
		final StyledText styledText = new StyledText(menuComposite, SWT.BORDER);
		GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).hint(150, 17).applyTo(styledText);

		styledText.setLeftMargin(5);
		styledText.setEnabled(false);
		styledText.setEditable(false);
		styledText.setCaret(null);

		return styledText;
	}

	public CellEditingSupport createCellEditingSupport(final TableViewer tableViewer) {
		return new CellEditingSupport(tableViewer);
	}

	public static Composite createTableComposite(Composite container) {
		final Composite composite = new Composite(container, SWT.BORDER);
		GridLayoutFactory.fillDefaults().applyTo(composite);
		GridDataFactory.fillDefaults().hint(350, 100).applyTo(composite);

		return composite;
	}

	private List<ChartElement> createChartElements() {
		final List<ChartElement> elements = new ArrayList<>();
		elements.add(new ChartElement("", null));

		final SimulationEngineState engineState = simulationUIService.getCurrentState().getEngineState();
		for(final Object element : engineState.getRootElements()) {
			elements.addAll(getChartElements(engineState, element));
		}

		elements.sort(elementComparator);

		return elements;
	}

	private List<ChartElement> getChartElements(final SimulationEngineState simulationState, final Object element) {
		final List<ChartElement> allChildren = new ArrayList<>();
		if(!simulationState.getChildElements(element).isEmpty()) {
			for(final Object childElement : simulationState.getChildElements(element)) {
				allChildren.addAll(getChartElements(simulationState, childElement));
			}
		} else {
			if(!simulationState.isElementHidden(element)) {
				String elementStr = simulationState.getElementName(element);
				Object parent = simulationState.getParentElement(element);
				while(parent != null) {
					elementStr = simulationState.getElementName(parent) + "." + elementStr;
					parent = simulationState.getParentElement(parent);
				}

				final VariableType variableType = simulationState.getElementType(element);

				if(variableType == VariableType.INTEGER || variableType == VariableType.REAL || variableType == VariableType.BOOLEAN) {
					final ChartElement chartElement = new ChartElement(elementStr, element);
					allChildren.add(chartElement);
				}
			}
		}

		return allChildren;
	}

	public ChartElement createStepsElement() {
		return new ChartElement("Step", null);
	}

	public TableViewer createTableViewer(final Composite tableComposite, final Action action, final SelectionAdapter selectionAdapter) {
		final TableViewer keyTableViewer = new TableViewer(tableComposite, SWT.FULL_SELECTION | SWT.CHECK);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(keyTableViewer.getControl());
		
		keyTableViewer.getTable().addKeyListener(getKeyListener());
		keyTableViewer.setContentProvider(new IStructuredContentProvider() {
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			}

			@Override
			public void dispose() {
			}

			@Override
			public Object[] getElements(Object inputElement) {
				return yAxisTableItems.toArray(new Object[yAxisTableItems.size()]);
			}
		});

		keyTableViewer.getTable().addSelectionListener(selectionAdapter);
		keyTableViewer.getTable().addKeyListener(getKeyListener());
		createChartContextMenu(keyTableViewer, action);

		return keyTableViewer;
	}

	private static void createChartContextMenu(final TableViewer keyTableViewer, final Action action) {
		final MenuManager contextMenu = new MenuManager();
		contextMenu.setRemoveAllWhenShown(true);
		contextMenu.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu(contextMenu);
			}

			private void fillContextMenu(final MenuManager contextMenu) {
				if(keyTableViewer.getTable().getItems().length > 1) {
					contextMenu.add(action);
				}
			}
		});

		keyTableViewer.getTable().setMenu(contextMenu.createContextMenu(keyTableViewer.getTable()));
	}

	private static LabelProvider getLabelProvider() {
		return new LabelProvider() {
			@Override
			public String getText(Object element) {
				return element.toString();
			}
		};
	}

	public ComboViewer createComboViewer(final Composite parent, final ISelectionChangedListener listener, final Object input) {
		final ComboViewer comboViewer = new ComboViewer(parent, SWT.PUSH);
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).grab(true, false).applyTo(comboViewer.getCombo());
		if(listener != null) {
			comboViewer.addSelectionChangedListener(listener);
		}

		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.getCombo().addKeyListener(getKeyListener());
		comboViewer.setLabelProvider(getLabelProvider());
		comboViewer.setInput(input);

		return comboViewer;
	}

	public static void createLabel(final Composite menuComposite, final String txt) {
		final Label label = new Label(menuComposite, SWT.NONE);
		label.setText(txt);
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).applyTo(label);
	}

	private CellLabelProvider createElementLabelProvider() {
		return new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				cell.setForeground(yAxisSWTColors[yAxisTableItems.indexOf(cell.getElement()) % yAxisSWTColors.length]);

				final ChartElement chartElement = ((YAxisTableItem)cell.getElement()).getChartElement();
				if(chartElement == null) {
					cell.setText("");
				} else {
					cell.setText(chartElement.toString());
				}
			}
		};
	}

	private CellLabelProvider createValueLabelProvider() {
		return new CellLabelProvider() {
			@Override
			public void update(ViewerCell cell) {
				final YAxisTableItem yAxisTableItem = ((YAxisTableItem)cell.getElement());
				cell.setText(yAxisTableItem.getYValueString());
			}
		};
	}

	public Composite createXZComposite(final Composite parent) {
		final Composite menuComposite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(7).applyTo(menuComposite);
		GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.CENTER).grab(false, false).span(3, 1).applyTo(menuComposite);

		return menuComposite;
	}

	public Composite createChartComposite(final Composite parent, final boolean exclude) {
		final Composite chartComposite = new Composite(parent, SWT.BORDER);
		GridLayoutFactory.fillDefaults().applyTo(chartComposite);
		GridDataFactory.fillDefaults().span(2, 1).grab(true, true).exclude(exclude).applyTo(chartComposite);

		return chartComposite;
	}

	public void configureKeyTableViewer(final EditingSupport editingSupport) {
		final TableViewer keyTableViewer = (TableViewer)editingSupport.getViewer();
		final TableViewerColumn viewerElementColumn = new TableViewerColumn(keyTableViewer, SWT.LEFT);
		final TableColumn elementColumn = viewerElementColumn.getColumn();
		elementColumn.setText("Y-Axis");

		final TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableColumnLayout.setColumnData(elementColumn, new ColumnWeightData(1, 175));
		viewerElementColumn.setEditingSupport(editingSupport);
		viewerElementColumn.setLabelProvider(createElementLabelProvider());

		final TableViewerColumn viewerValueColumn = new TableViewerColumn(keyTableViewer, SWT.NONE);
		final TableColumn valueColumn = viewerValueColumn.getColumn();
		valueColumn.setText("Value");
		tableColumnLayout.setColumnData(valueColumn, new ColumnWeightData(2, 140));

		viewerValueColumn.setLabelProvider(createValueLabelProvider());

		keyTableViewer.getTable().getParent().setLayout(tableColumnLayout);
		keyTableViewer.getTable().setLinesVisible(true);
		keyTableViewer.getTable().setHeaderVisible(true);
		keyTableViewer.setUseHashlookup(true);
		keyTableViewer.setInput(yAxisTableItems.add(createYAxisTableItem()));
		keyTableViewer.getTable().getItem(yAxisTableItems.size()-1).setChecked(true);
	}

	public Point handleChartTransformation(final int button, final Point selectionPoint, final Point point) {
		if(selectionPoint != null) {
			if(is2DChart) {
				final View view = chart2d.getView();
				final BoundingBox3d bounds = view.getBounds();

				if(button == 1) {
					// Shift Y on left click drag
					float factor = (bounds.getYmax() - bounds.getYmin()) * (float)((point.y - selectionPoint.y) / 500.0);
					float minVal = bounds.getYmin() + factor;
					float maxVal = bounds.getYmax() + factor;

					view.setBoundManual(new BoundingBox3d(bounds.getXmin(), bounds.getXmax(), minVal, maxVal, bounds.getZmin(), bounds.getZmax()));
				} else if(button == 3) {
					// Shift X on right click drag
					float factor = (bounds.getXmax() - bounds.getXmin()) * (float)((selectionPoint.x - point.x) / 500.0);
					float minVal = bounds.getXmin() + factor;
					float maxVal = bounds.getXmax() + factor;

					view.setBoundManual(new BoundingBox3d(minVal, maxVal, bounds.getYmin(), bounds.getYmax(), bounds.getZmin(), bounds.getZmax()));
				}
				
				chart2d.getCanvas().forceRepaint();
			} else {
				final View view = chart3d.getView();
				final BoundingBox3d bounds = view.getBounds();
				final boolean[] hiddenQuads = ((ChartAxe)view.getAxe()).getHiddenQuads(view.getCurrentGL(), view.getCamera());

				if(pressedKey == 'y') {
					// Shift Z axis
					float factor = (bounds.getZmax() - bounds.getZmin()) * (float)((point.y - selectionPoint.y) / 500.0);
					view.setBoundManual(new BoundingBox3d(bounds.getXmin(), bounds.getXmax(), bounds.getYmin(), bounds.getYmax(), bounds.getZmin() + factor, bounds.getZmax() + factor));
				} else if(pressedKey == 'x') {
					// Shift X axis
					float factor;
					// Determine factor based on hidden quads
					if(hiddenQuads[2]) {
						factor = (bounds.getXmax() - bounds.getXmin()) * (float)((point.x - selectionPoint.x) / 500.0);
					} else {
						factor = (bounds.getXmax() - bounds.getXmin()) * (float)((selectionPoint.x - point.x) / 500.0);
					}

					view.setBoundManual(new BoundingBox3d(bounds.getXmin() + factor, bounds.getXmax() + factor, bounds.getYmin(), bounds.getYmax(), bounds.getZmin(), bounds.getZmax()));
				} else if(pressedKey == 'z') {
					// Shift Y axis
					float factor;
					// Determine factor based on hidden quads
					if(hiddenQuads[0]) {
						factor = (bounds.getYmax() - bounds.getYmin()) * (float)((selectionPoint.x - point.x) / 500.0);
					} else {
						factor = (bounds.getYmax() - bounds.getYmin()) * (float)((point.x - selectionPoint.x) / 500.0);
					}

					view.setBoundManual(new BoundingBox3d(bounds.getXmin(), bounds.getXmax(), bounds.getYmin() + factor, bounds.getYmax() + factor, bounds.getZmin(), bounds.getZmax()));
				} else {
					// Rotate 3D Chart
					float x = (float)((point.x-selectionPoint.x)/100.0);
					float y = (float)((point.y-selectionPoint.y)/100.0);
					if(chart3d.getViewPoint().y + y < 0) {
						view.rotate(new Coord2d(x, -get3DChart().getViewPoint().y));
					} else {
						view.rotate(new Coord2d(x,y));
					}
				}
				
				chart3d.getCanvas().forceRepaint();
			}
		}

		return point;
	}

	public void clearChartPoints() {
		chartPoints.clear();
	}

	public boolean is2DChart() {
		return is2DChart;
	}

	public boolean setChartPointInfo(final ChartPoint chartPoint, final Object xElementValue, final Object yElementValue, final Object zElementValue) {
		chartPoint.x = getAxisValue(xElementValue);

		if(is2DChart) {
			chartPoint.y = getAxisValue(yElementValue);
			chartPoint.z = 0.0f;
			
			return chartPoint.x == null || chartPoint.y == null;
		}

		// Y value is set to zElementValue to show Z axis as depth plane
		chartPoint.y = getAxisValue(zElementValue);
		// Z value is set to yElementValue to show Y axis as vertical plane
		chartPoint.z = getAxisValue(yElementValue);
		
		return chartPoint.x == null || chartPoint.y == null || chartPoint.z == null;
	}

	private static Float getAxisValue(final Object elementValue) {
		if(elementValue instanceof BigInteger) {
			return ((BigInteger)elementValue).floatValue();
		} else if(elementValue instanceof Rational) {
			return ((Rational)elementValue).floatValue();
		} else if(elementValue instanceof Boolean) {
			return elementValue == Boolean.FALSE ? 0.0f : 1;
		} else if(elementValue instanceof Integer) {
			return ((Integer)elementValue).floatValue();
		}

		return null;
	}

	private static final Comparator<ChartElement> elementComparator = new Comparator<ChartElement>() {
		@Override
		public int compare(final ChartElement chartPoint1, final ChartElement chartPoint2) {
			if(chartPoint1.toString().contains(".") && !chartPoint2.toString().contains(".")) {
				return 1;
			} else if(!chartPoint1.toString().contains(".") && chartPoint2.toString().contains(".")) {
				return -1;
			}

			return chartPoint1.toString().toLowerCase().compareTo(chartPoint2.toString().toLowerCase());
		}
	};

	private static final Comparator<ChartPoint> xValueComparator = new Comparator<ChartPoint>() {
		@Override
		public int compare(final ChartPoint chartPoint1, final ChartPoint chartPoint2) {
			if(chartPoint1.x != null && chartPoint2.x != null) {
				return Double.compare(chartPoint1.x, chartPoint2.x);
			}

			return 0;
		}
	};

	private static final Comparator<ChartPoint> yValueComparator = new Comparator<ChartPoint>() {
		@Override
		public int compare(final ChartPoint chartPoint1, final ChartPoint chartPoint2) {
			if(chartPoint1.y != null && chartPoint2.y != null) {
				return Double.compare(chartPoint1.y, chartPoint2.y);
			}

			return 0;
		}
	};

	private static final Comparator<ChartPoint> zValueComparator = new Comparator<ChartPoint>() {
		@Override
		public int compare(final ChartPoint chartPoint1, final ChartPoint chartPoint2) {
			if(chartPoint1.z != null && chartPoint2.z != null) {
				return Double.compare(chartPoint1.z, chartPoint2.z);
			}

			return 0;
		}
	};

	public class ChartElement {
		private final String name;
		private final Object simulationStateElement;

		public ChartElement(final String name, final Object simulationStateElement) {
			this.name = name;
			this.simulationStateElement = simulationStateElement;
		}

		public Object getSimulationStateElement() {
			return simulationStateElement;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public class CellEditingSupport extends EditingSupport {
		private ComboBoxCellEditor comboBoxCellEditor;

		private CellEditingSupport(final TableViewer tableViewer) {
			super(tableViewer);
			final Table table = tableViewer.getTable();
			final String elementsToString = chartElements.toString();
			this.comboBoxCellEditor = new ComboBoxCellEditor(table, elementsToString.substring(1, elementsToString.length()-1).split(", "));
		}

		@Override
		protected boolean canEdit(final Object element) {
			return comboBoxCellEditor.getItems().length != 0;
		}

		@Override
		public CellEditor getCellEditor(final Object element) {
			return comboBoxCellEditor;
		}

		@Override
		protected Object getValue(final Object element) {
			return Arrays.asList(comboBoxCellEditor.getItems()).indexOf(element);
		}

		@Override
		public void setValue(final Object element, final Object value) {
			int index = (int)value;
			if(index != -1) {
				((YAxisTableItem)element).chartElement = chartElements.get((int)value);
				getViewer().update(element, null);
			}
		}
	}

	private class ChartAxe extends AxeBox {
		private ChartAxe(BoundingBox3d bbox) {
			super(bbox);
		}

		@Override
		public void drawFace(GL gl) {
			if(gl.isGL2()) {
				gl.getGL2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE);
				gl.getGL2().glColor4f(0, 0, 0, 1f);
			} else {
				GLES2CompatUtils.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_LINE);
				GLES2CompatUtils.glColor4f(0, 0, 0, 1f);
			}

			gl.glLineWidth(1.0f);
			gl.glPolygonOffset(1.0f, 1.0f);
			drawCube(gl, GL2.GL_RENDER);
		}

		@Override
		public void drawGrid(GL gl) {
			final Color gridcolor = layout.getGridColor();

			if(gl.isGL2()) {
				gl.getGL2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE);
				gl.getGL2().glColor4f(0, 0, 0, 1f);
				gl.getGL2().glLineWidth(1);
				drawFace(gl);

				// Draw grids on non hidden quads
				gl.getGL2().glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE);
				gl.getGL2().glColor4f(gridcolor.r, gridcolor.g, gridcolor.b, gridcolor.a);
				gl.getGL2().glLineWidth(1);
				gl.getGL2().glLineStipple(1, (short) 0xAAAA);
			} else {
				GLES2CompatUtils.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE);
				GLES2CompatUtils.glColor4f(0, 0, 0, 1f);
				gl.glLineWidth(1);
				drawCube(gl, GL2.GL_RENDER);

				// Draw grids on non hidden quads
				GLES2CompatUtils.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE);
				GLES2CompatUtils.glColor4f(gridcolor.r, gridcolor.g, gridcolor.b, gridcolor.a);
				GLES2CompatUtils.glLineWidth(1);
				GLES2CompatUtils.glLineStipple(1, (short) 0xAAAA);
			}

			gl.glEnable(GL2.GL_LINE_STIPPLE);

			for(int quad = 0; quad < 6; quad++) {
				if(!quadIsHidden[quad]) {
					drawGridOnQuad(gl, quad);
				}
			}

			gl.glDisable(GL2.GL_LINE_STIPPLE);
		}

		@Override
		public boolean[] getHiddenQuads(GL gl, Camera cam) {
			return super.getHiddenQuads(gl, cam);
		}

		@Override
		protected void drawCube(GL gl, int mode) {
			for(int q = 0; q < 6; q++) {
				if(gl.isGL2()) {
					if (mode == GL2.GL_FEEDBACK)
						gl.getGL2().glPassThrough(q);
					gl.getGL2().glBegin(GL2GL3.GL_LINE_LOOP);
					for(int v = 0; v < 4; v++) {
						Coord3d quadCoord = new Coord3d(quadx[q][v], quady[q][v], quadz[q][v]);
						vertexGL2(gl, quadCoord);
					}
					gl.getGL2().glEnd();
				} else {
					if(mode == GL2.GL_FEEDBACK)
						GLES2CompatUtils.glPassThrough(q);
					GLES2CompatUtils.glBegin(GL2GL3.GL_LINE_LOOP);
					for(int v = 0; v < 4; v++) {
						Coord3d quadCoord = new Coord3d(quadx[q][v], quady[q][v], quadz[q][v]);
						vertexGLES2(quadCoord);
					}
					GLES2CompatUtils.glEnd();
				}
			}
		}
	}

	public class ChartPoint {
		private final int indexOfState;
		private final Color color;
		public Float x;
		public Float y;
		public Float z;

		private ChartPoint(final int indexOfState, final Color color) {
			this.indexOfState = indexOfState;
			this.color = color;
		}

		public int getIndexOfState() {
			return indexOfState;
		}
	}

	public class YAxisTableItem {
		private String yValueString = "";
		private ChartElement chartElement = new ChartElement("", null);

		public ChartElement getChartElement() {
			return chartElement;
		}

		public String getYValueString() {
			return yValueString;
		}

		public void setYValueString(final String yValueString) {
			this.yValueString = yValueString;
		}
	}
}