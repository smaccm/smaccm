package edu.uah.rsesc.agree.ge.ui.tooltips;

import javax.inject.Named;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.osate.ge.di.Activate;
import org.osate.ge.di.Names;

import com.rockwellcollins.atc.agree.agree.SpecStatement;

import edu.uah.rsesc.agree.ge.ui.util.TextConversionUtil;

public class SpecStatementTooltip {
	@Activate
	public void getTooltip(final Composite parent, @Named(Names.BUSINESS_OBJECT) SpecStatement bo) {
		final Label lbl = new Label(parent, SWT.NONE);
		lbl.setText(TextConversionUtil.toText(bo));
	}
}
