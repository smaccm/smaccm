package edu.uah.rsesc.agree.ge.ui.properties;

import org.eclipse.jface.viewers.IFilter;
import org.osate.ge.ui.properties.PropertySectionUtil;

import com.rockwellcollins.atc.agree.agree.LemmaStatement;

import edu.uah.rsesc.agree.ge.businessObjectHandlers.LemmaStatementHandler;

public class LemmasPropertySection extends GenericPropertySection {
	private final LemmaStatementHandler handler = new LemmaStatementHandler();

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
		return handler.getName((LemmaStatement) element);
	}

	@Override
	protected Object[] getSpecStatements() {
		return getSpecStatements(AgreePropertySectionUtil::asComponentImplementation, LemmaStatement.class);
	}

}
