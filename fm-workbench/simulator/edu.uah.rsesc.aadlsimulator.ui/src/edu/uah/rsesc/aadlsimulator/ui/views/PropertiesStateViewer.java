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
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import edu.uah.rsesc.aadlsimulator.PropertyStatus;
import edu.uah.rsesc.aadlsimulator.PropertyStatus.Style;
import edu.uah.rsesc.aadlsimulator.SimplePropertyStatus;
import edu.uah.rsesc.aadlsimulator.SimpleSimulationPropertiesState;
import edu.uah.rsesc.aadlsimulator.SimpleSimulationPropertiesState.SimpleSimulationProperty;
import edu.uah.rsesc.aadlsimulator.SimulationPropertiesState;

/**
 * Viewer for SimulationPropertiesStates
 */
class PropertiesStateViewer {	
	private class PropertyComparator extends ViewerComparator {
		private Comparator<Object> comparator;
		private int direction = SWT.UP;
		
		public void setComparator(final Comparator<Object> comparator) {
			this.comparator = comparator;
		}

		public void setSortDirection(final int value) {
			this.direction = value;
		}
		
		@Override
		public int compare(final Viewer viewer, final Object p1, final Object p2) {
			if(p1 == null || p2 == null || comparator == null) {
				return 0;
			}
			
			int order = comparator.compare(p1, p2);
			return direction == SWT.UP ? order : -order;
		}
	}
	
	//Headers for the columns
	private final static String nameColumnHeader = "Name";
	private final static String statusColumnHeader = "Status";
	private final Composite container;
	private final TableViewer tableViewer;
	private final StyledText detailsText;
	private MenuManager contextMenuManager = new MenuManager();
	private SimulationPropertiesState propertiesState;
	private PropertyComparator propertyComparator = new PropertyComparator();
	private final Collection<IMenuListener> propertyMenuListeners = new ArrayList<>();
	
	public static interface PropertyFilter {
		/**
		 * Returns whether the given property  makes it through this filter.
		 * @param propertiesState
		 * @param property
		 * @return true if property is included in the filtered set, and false if excluded
		 */
		boolean select(final SimulationPropertiesState propertiesState, final Object property);
	}
	
	public PropertiesStateViewer(final Composite parent) {
		this.container = new Composite(Objects.requireNonNull(parent, "parent must not be null"), SWT.NONE);

		container.addDisposeListener(containerDisposeListener);
		
		GridLayoutFactory.fillDefaults().equalWidth(true).applyTo(container);
		final Composite tableComposite = new Composite(container, SWT.NONE);
		GridLayoutFactory.fillDefaults().equalWidth(true).applyTo(tableComposite);
		GridDataFactory.fillDefaults().hint(SWT.FILL, SWT.FILL).grab(true, true).applyTo(tableComposite);
		final TableColumnLayout tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);
		tableViewer = createTableViewer(tableComposite);
		
		tableViewer.setContentProvider(new ArrayContentProvider() {
			@Override
			public Object[] getElements(final Object inputElement) {
				assert inputElement instanceof SimulationPropertiesState;
				
				final SimulationPropertiesState simulationPropertiesState = (SimulationPropertiesState)inputElement;
				if(simulationPropertiesState.getProperties().isEmpty()) {
					return new Object[0];
				}
				
				return simulationPropertiesState.getProperties().toArray();
			}
		});

		tableViewer.getTable().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(propertiesState != null) {
					for(Object prop : propertiesState.getProperties()) {
						final TableItem tableItem = (TableItem)e.item;
						if(tableItem.getText().equals(propertiesState.getPropertyName(prop))) {
							detailsText.setText(propertiesState.getPropertyDetails(prop));
						}
					} 
				} else {
					tableViewer.setItemCount(0);
					detailsText.setText("");
				}
			}
		});
		
		GridDataFactory.fillDefaults().grab(true, true).hint(SWT.FILL, SWT.FILL).minSize(SWT.DEFAULT, 100).applyTo(tableViewer.getControl());		
		createContextMenu();
		createViewerColumns(tableColumnLayout);
		
		detailsText = createDetailsText();
		GridDataFactory.fillDefaults().grab(true, false).hint(SWT.FILL, 100).minSize(SWT.DEFAULT, 50).applyTo(detailsText);
	}
	
	public void setFilters(final PropertyFilter[] filters) {
		final ViewerFilter[] viewerFilter = new ViewerFilter[] {
			new ViewerFilter() {
				public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
					return filters[0].select(propertiesState, element);
				}
			}
		};
		
		tableViewer.setFilters(viewerFilter);
	}
	
	public void resetFilters() {
		tableViewer.resetFilters();
	}

	private void createViewerColumns(final TableColumnLayout columnLayout) {
		createNameViewerColumn(nameColumnHeader, columnLayout);
		createStatusViewerColumn(statusColumnHeader, columnLayout);
	}

	private StyledText createDetailsText() {
		final StyledText detailsText = new StyledText(container, SWT.READ_ONLY | SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		detailsText.setCaret(null);
		detailsText.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));		
		return detailsText;
	}
	
	private void createStatusViewerColumn(final String header, final TableColumnLayout columnLayout) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.BORDER);
		viewerColumn.getColumn().setText(header);
		final TableColumn column = viewerColumn.getColumn();
		columnLayout.setColumnData(column, new ColumnWeightData(1, 100));
		
		viewerColumn.setLabelProvider(new CellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				if(propertiesState != null) {
					cell.setText(propertiesState.getPropertyStatus(cell.getElement()).getName());
					cell.setForeground(getColor(propertiesState.getPropertyStatus(cell.getElement()).getStyle()));
				}
			}
		});
		
		// Sort by style. The oridinal of the styles is based on its importance
		final Comparator<Object> statusComparator = new Comparator<Object>() {
			@Override
			public int compare(final Object p1, final Object p2) {
				return Integer.compare(propertiesState.getPropertyStatus(p1).getStyle().ordinal(), propertiesState.getPropertyStatus(p2).getStyle().ordinal());
			}
		};
		
		viewerColumn.getColumn().addSelectionListener(createSortingSelectionListener(viewerColumn, statusComparator));
		
		// Sort by status by default
		final Table table = tableViewer.getTable();
		table.setSortColumn(viewerColumn.getColumn());
		table.setSortDirection(SWT.DOWN);
		propertyComparator.setSortDirection(table.getSortDirection());
		propertyComparator.setComparator(statusComparator);
	}

	private void createNameViewerColumn(final String header, final TableColumnLayout columnLayout) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.BORDER);
		viewerColumn.getColumn().setText(header);
		final TableColumn column = viewerColumn.getColumn();
		columnLayout.setColumnData(column, new ColumnWeightData(5, 100));
		
		viewerColumn.setLabelProvider(new StyledCellLabelProvider() {
			@Override
			public void update(final ViewerCell cell) {
				if(propertiesState != null) {
					final String txt = propertiesState.getPropertyName(cell.getElement());
					cell.setText(txt);
					
					// Style disabled properties
					if(propertiesState.isPropertyEnabledForNextFrame(cell.getElement())) {
						cell.setStyleRanges(new StyleRange[0]);
					} else {
						final StyleRange styledRange = new StyleRange(0, cell.getText().length(), Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY), null);
					    cell.setStyleRanges(new StyleRange[] { styledRange});
					}
				}
				
				super.update(cell);
			}
		});

		final Comparator<Object> nameComparator = new Comparator<Object>() {
			@Override
			public int compare(final Object p1, final Object p2) {
				final String p1String = propertiesState.getPropertyName(p1);
				final String p2String = propertiesState.getPropertyName(p2);
				return p1String.compareTo(p2String);
			}
		};
		
		viewerColumn.getColumn().addSelectionListener(createSortingSelectionListener(viewerColumn, nameComparator));
	}


	private SelectionListener createSortingSelectionListener(final TableViewerColumn viewerColumn, final Comparator<Object> comparator) {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				final Table table = tableViewer.getTable();
				table.setSortColumn(viewerColumn.getColumn());
				//Sets the arrow for sorting column
				table.setSortDirection(table.getSortDirection() == SWT.UP ? SWT.DOWN : SWT.UP);
				propertyComparator.setSortDirection(table.getSortDirection());
				propertyComparator.setComparator(comparator);
				tableViewer.refresh();
			}
		};
	}
	
	private void clearSelection() {
		tableViewer.setSelection(null);
		detailsText.setText("");
	}

	/**
	 * @param propertiesState can be null.
	 */
	public void setInput(final SimulationPropertiesState propertiesState) {
		this.propertiesState = propertiesState;
		tableViewer.setInput(propertiesState);
		clearSelection();
	}

	public SimulationPropertiesState getInput() {
		return this.propertiesState;
	}
	
	/**
	 * Determine status column cell foreground color
	 * @param style - style of Status
	 * @return foreground color of status column cell based on Status category
	 */
	private static Color getColor(final Style style) {		
		if(style == PropertyStatus.Style.INFO) {
			return Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
		} else if(style == PropertyStatus.Style.OK) {
			return Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN);
		} else if(style == PropertyStatus.Style.WARNING) {
			return Display.getDefault().getSystemColor(SWT.COLOR_DARK_YELLOW);
		} else if(style == PropertyStatus.Style.ERROR) {
			return Display.getDefault().getSystemColor(SWT.COLOR_DARK_RED);
		}
		
		//Default to black
		return Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
	}

	public Control getControl() {
		return container;
	}
	
	private TableViewer createTableViewer(final Composite tableComposite) {
		final TableViewer statusViewer = new TableViewer(tableComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.SCROLLBAR_OVERLAY | SWT.MULTI);
		statusViewer.setComparator(propertyComparator);
		statusViewer.getTable().setLinesVisible(true);
		statusViewer.getTable().setHeaderVisible(true);

		return statusViewer;
	}

	/**
	 * create dynamic context menu for table viewer
	 * @param tableViewer
	 */
	private void createContextMenu() {
		final Table table = tableViewer.getTable();
		final TableSelectionTracker tableSelectionTracker = new TableSelectionTracker(tableViewer);
		
		contextMenuManager.setRemoveAllWhenShown(true);
		contextMenuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(final IMenuManager mgr) {
				// Only show the menu when there is a single property selected
				if (table.getSelection().length >= 1 && tableSelectionTracker.isSelectionValid()) {
					for(final IMenuListener l : propertyMenuListeners) {
						l.menuAboutToShow(mgr);
					}
				}
			}
		});

		table.setMenu(contextMenuManager.createContextMenu(table));
	}

	/**
	 * Adds a menu listener for the property context menu
	 * @param listener
	 */
	public void addPropertyMenuListener(final IMenuListener listener) {
		propertyMenuListeners.add(listener);
	}
	
	public IStructuredSelection getSelection() {
		return ((IStructuredSelection)tableViewer.getSelection());
	}

	/**
	 *Determine if item is valid for context menu and set item data
	 */
	private static class TableSelectionTracker {
		private ViewerCell selection;
		public TableSelectionTracker(final TableViewer tableViewer) {
			tableViewer.getTable().addMenuDetectListener(new MenuDetectListener() {
				@Override
				public void menuDetected(final MenuDetectEvent e) {
					final Point point = Display.getDefault().map(null, tableViewer.getControl(), new Point(e.x, e.y));  
					selection = tableViewer.getCell(point);
				}
			});
		}
		
		private boolean isSelectionValid() {
			return selection != null;
		}
	}
	
	final DisposeListener containerDisposeListener = new DisposeListener() {
		@Override
		public void widgetDisposed(final DisposeEvent e) {
			contextMenuManager.dispose();
		}
	};
	
	/**
	 * main function for testing the class. When the "Change Input" button is selected, setInput() is called with a cycling value
	 */
	public static void main(final String[] args) {
		// Create display
		final Display display = Display.getDefault();
		final Shell shell = new Shell(display, SWT.SHELL_TRIM);
		shell.setSize(400, 500);
		shell.setText("PropertiesStateViewer Test");

		GridLayoutFactory.fillDefaults().equalWidth(true).applyTo(shell);
		
		final Button changeInputBtn = new Button(shell, SWT.PUSH);
		changeInputBtn.setText("Change Input");
		
		final Button setFilterBtn = new Button(shell, SWT.CHECK);
		setFilterBtn.setText("Set Filter");
		
		final PropertiesStateViewer viewer = new PropertiesStateViewer(shell);
		viewer.addPropertyMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(final IMenuManager manager) {
				final IStructuredSelection selection = viewer.getSelection();
				if(selection.size() == 1) {
					manager.add(new Action("Go To Property") {
						@Override
						public void run() {
							System.out.println("Go To Property: " + viewer.getInput().getPropertyName(selection.getFirstElement()));
						}
					});
				}
			}			
		});
		
		GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).applyTo(viewer.getControl());
		
		setFilterBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!setFilterBtn.getSelection()) {
					setFilterBtn.setSelection(false);
					viewer.resetFilters();
				} else if(viewer.propertiesState != null) {
					PropertyStatusStyleFilter[] s = null;
					if(viewer.propertiesState.getProperties().size() == 4) {
						s = new PropertyStatusStyleFilter[] { new PropertyStatusStyleFilter(PropertyStatus.Style.OK, PropertyStatus.Style.INFO) };
					} else if(viewer.propertiesState.getProperties().size() == 3) {
						s = new PropertyStatusStyleFilter[] { new PropertyStatusStyleFilter(PropertyStatus.Style.WARNING, PropertyStatus.Style.ERROR) };
					} else {
						s = new PropertyStatusStyleFilter[] { new PropertyStatusStyleFilter(PropertyStatus.Style.OK) };
					}
					
					viewer.setFilters(s);
				}
			}
		});
		
		changeInputBtn.addSelectionListener(new SelectionAdapter() {
			private int stateIndex = -1;
			private SimulationPropertiesState[] propertiesStates = createTestPropertiesStates();

			@Override
			public void widgetSelected(SelectionEvent e) {
				stateIndex = (stateIndex + 1) %  propertiesStates.length;
				setFilterBtn.setSelection(false);
				viewer.resetFilters();
				viewer.setInput(propertiesStates[stateIndex]);
			}

			private SimulationPropertiesState[] createTestPropertiesStates() {
				final List<SimulationPropertiesState> states = new ArrayList<>();

				{				
					final List<SimpleSimulationProperty> properties = new ArrayList<>();
					properties.add(new SimpleSimulationProperty("Prop 1-1", "Satisfied", Boolean.TRUE, new SimplePropertyStatus("Satisfied", PropertyStatus.Style.OK), true));
					properties.add(new SimpleSimulationProperty("Prop 1-2", "Warning", Boolean.TRUE, new SimplePropertyStatus("Unsatisfied.", PropertyStatus.Style.WARNING), false));
					properties.add(new SimpleSimulationProperty("Prop 1-3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce a tincidunt enim. Integer aliquet justo vehicula, aliquet ante vel, bibendum.", Boolean.FALSE, new SimplePropertyStatus("Fatal Error", PropertyStatus.Style.ERROR), true));
					properties.add(new SimpleSimulationProperty("Prop 1-4", "Info", null, new SimplePropertyStatus("Notice", PropertyStatus.Style.INFO), true));
					states.add(new SimpleSimulationPropertiesState(properties));
				}

				{				
					final List<SimpleSimulationProperty> properties = new ArrayList<>();
					properties.add(new SimpleSimulationProperty("Prop 2-1", "Error", Boolean.TRUE, new SimplePropertyStatus("Major Error", PropertyStatus.Style.ERROR), false));
					properties.add(new SimpleSimulationProperty("Prop 2-2", "Warning", Boolean.TRUE, new SimplePropertyStatus("Unsatisfied.", PropertyStatus.Style.WARNING), true));
					properties.add(new SimpleSimulationProperty("Prop 2-3", "Error", Boolean.FALSE, new SimplePropertyStatus("Fatal Error", PropertyStatus.Style.ERROR), true));
					states.add(new SimpleSimulationPropertiesState(properties));
				}				

				{				
					final List<SimpleSimulationProperty> properties = new ArrayList<>();
					for(int i = 0; i < 50; i++) {
						properties.add(new SimpleSimulationProperty("Prop 3-" + i, "Info", Boolean.TRUE, new SimplePropertyStatus("Notice", PropertyStatus.Style.INFO), true));
					}
					states.add(new SimpleSimulationPropertiesState(properties));
				}

				{				
					states.add(null);
				}

				return states.toArray(new SimulationPropertiesState[0]);
			}
		});        

		// Run UI
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		// Cleanup
		shell.dispose();
		display.dispose();        
	}	
	
	
}
