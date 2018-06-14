package edu.uah.rsesc.agree.ge.ui.properties;

import org.eclipse.jface.viewers.IFilter;
import org.osate.ge.ui.properties.PropertySectionUtil;

import com.rockwellcollins.atc.agree.agree.EnumStatement;

import edu.uah.rsesc.agree.ge.businessObjectHandlers.EnumerationStatementHandler;

public class EnumerationsPropertySection extends GenericPropertySection {
	final EnumerationStatementHandler handler = new EnumerationStatementHandler();

	public static class Filter implements IFilter {
		@Override
		public boolean select(final Object toTest) {
			return AgreePropertySectionUtil.asPackage(toTest) != null;
		}
	}

	@Override
	protected void onAdd() {
		PropertySectionUtil
				.execute(handler.buildCreateOperation(getSelectedBos().bocStream().findFirst().orElse(null)));
	}

	@Override
	protected String getName(final Object element) {
		return handler.getName((EnumStatement) element);
	}

	@Override
	protected Object[] getSpecStatements() {
		return getSpecStatements(AgreePropertySectionUtil::asPackage, EnumStatement.class);
	}

}
