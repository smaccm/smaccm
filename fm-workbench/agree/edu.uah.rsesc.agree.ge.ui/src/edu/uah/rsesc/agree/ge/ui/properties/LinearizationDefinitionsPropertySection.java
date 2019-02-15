package edu.uah.rsesc.agree.ge.ui.properties;

import org.eclipse.jface.viewers.IFilter;
import org.osate.ge.ui.properties.PropertySectionUtil;

import com.rockwellcollins.atc.agree.agree.LinearizationDef;

import edu.uah.rsesc.agree.ge.businessObjectHandlers.LinearizationDefHandler;

public class LinearizationDefinitionsPropertySection extends GenericPropertySection {
	final LinearizationDefHandler handler = new LinearizationDefHandler();

	public static class Filter implements IFilter {
		@Override
		public boolean select(final Object toTest) {
			return AgreePropertySectionUtil.asPackageOrComponentClassifier(toTest) != null;
		}
	}

	@Override
	protected void onAdd() {
		PropertySectionUtil
				.execute(handler.buildCreateOperation(getSelectedBos().bocStream().findFirst().orElse(null)));
	}

	@Override
	protected String getName(final Object element) {
		return handler.getName((LinearizationDef) element);
	}

	@Override
	protected Object[] getSpecStatements() {
		return getSpecStatements(AgreePropertySectionUtil::asPackageOrComponentClassifier, LinearizationDef.class);
	}

}
