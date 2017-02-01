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
package edu.uah.rsesc.aadlsimulator.agree.ui.menus;

import java.util.Objects;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;

import edu.uah.rsesc.aadlsimulator.agree.engine.AGREESimulationEngine;
import edu.uah.rsesc.aadlsimulator.agree.services.AGREESimulatorExtensionService;
import edu.uah.rsesc.aadlsimulator.agree.services.AGREESimulatorExtensionService.ExporterProxy;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulationUIService;
import edu.uah.rsesc.aadlsimulator.ui.services.SimulatorState;

/**
 * Adds exporters to a menu
 *
 */
public class ExporterMenuContributionItem extends ContributionItem {
	@Override
	public boolean isDynamic() {
		return true;
	}

    @Override
    public void fill(final Menu menu, int index) {  
		final SimulationUIService simUiService = (SimulationUIService)Objects.requireNonNull(PlatformUI.getWorkbench().getService(SimulationUIService.class), "Unable to retrieve Simulation UI Service");
		final SimulatorState simulatorState = simUiService.getCurrentState();
		final boolean visible = simulatorState.getEngineState() != null && simulatorState.getSimulationEngine() instanceof AGREESimulationEngine;

		if(visible) {
			final AGREESimulatorExtensionService extService = Objects.requireNonNull((AGREESimulatorExtensionService)PlatformUI.getWorkbench().getService(AGREESimulatorExtensionService.class), "unable to get AGREE Simulator Extension service");
			for(final ExporterProxy exporter : extService.getExporters()) {
				final IAction exporterAction = new ExportAction(exporter);
				final MenuItem exporterMenuItem = new MenuItem(menu, SWT.CHECK, index++);
				exporterMenuItem.setEnabled(simUiService.getCurrentState().getEngineState().getNumberOfFrames() > 0);
				exporterMenuItem.setText(exporter.getName());
				exporterMenuItem.addSelectionListener(new SelectionAdapter() {
					public void widgetSelected(final SelectionEvent e) {
						exporterAction.run();
					}
				});
			}
		}
    }
}
