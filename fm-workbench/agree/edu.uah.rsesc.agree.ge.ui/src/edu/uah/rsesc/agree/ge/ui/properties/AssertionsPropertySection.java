package edu.uah.rsesc.agree.ge.ui.properties;

import org.eclipse.jface.viewers.IFilter;
import org.osate.ge.ui.properties.PropertySectionUtil;

import com.rockwellcollins.atc.agree.agree.AssertStatement;

import edu.uah.rsesc.agree.ge.businessObjectHandlers.AssertStatementHandler;

public class AssertionsPropertySection extends GenericPropertySection {
	final AssertStatementHandler handler = new AssertStatementHandler();

	public static class Filter implements IFilter {
		@Override
		public boolean select(final Object toTest) {
			return AgreePropertySectionUtil.asComponentImplementation(toTest) != null;
		}
	}

	@Override
	protected void onAdd() {
		PropertySectionUtil
				.execute(handler.buildCreateOperation(getSelectedBos().bocStream().findFirst().orElse(null)));
	}

	@Override
	protected String getName(final Object element) {
		return handler.getName((AssertStatement) element);
	}

	@Override
	protected Object[] getSpecStatements() {
		return getSpecStatements(AgreePropertySectionUtil::asComponentImplementation, AssertStatement.class);
	}

}
