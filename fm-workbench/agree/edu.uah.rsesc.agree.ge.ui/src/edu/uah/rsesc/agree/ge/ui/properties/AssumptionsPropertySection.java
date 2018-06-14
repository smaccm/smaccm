package edu.uah.rsesc.agree.ge.ui.properties;

import org.eclipse.jface.viewers.IFilter;
import org.osate.ge.ui.properties.PropertySectionUtil;

import com.rockwellcollins.atc.agree.agree.AssumeStatement;

import edu.uah.rsesc.agree.ge.businessObjectHandlers.AssumeStatementHandler;

public class AssumptionsPropertySection extends GenericPropertySection {
	final AssumeStatementHandler handler = new AssumeStatementHandler();

	public static class Filter implements IFilter {
		@Override
		public boolean select(final Object toTest) {
			return AgreePropertySectionUtil.asComponentType(toTest) != null;
		}
	}

	@Override
	protected void onAdd() {
		PropertySectionUtil
				.execute(handler.buildCreateOperation(getSelectedBos().bocStream().findFirst().orElse(null)));
	}

	@Override
	protected String getName(final Object element) {
		return handler.getName((AssumeStatement) element);
	}

	@Override
	protected Object[] getSpecStatements() {
		return getSpecStatements(AgreePropertySectionUtil::asComponentType, AssumeStatement.class);
	}
}
