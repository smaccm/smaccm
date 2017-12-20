package com.rockwellcollins.atc.agree.analysis.views;

import com.rockwellcollins.atc.agree.analysis.Activator;
import com.rockwellcollins.atc.agree.analysis.preferences.PreferenceConstants;

import jkind.api.ui.counterexample.CounterexampleStepLabelProvider;
import jkind.api.ui.counterexample.SignalGroup;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.Value;

public class AgreeCounterexampleStepLabelProvider extends CounterexampleStepLabelProvider {

	private final int step;

	private final boolean displayAsDecimal;

	public AgreeCounterexampleStepLabelProvider(int step) {
		super(step);
		this.step = step;
		displayAsDecimal = Activator.getDefault().getPreferenceStore()
				.getBoolean(PreferenceConstants.PREF_DISPLAY_DECIMAL_CEX);
	}

	@Override
	public String getText(Object element) {
		if (element instanceof SignalGroup) {
			SignalGroup group = (SignalGroup) element;
			if (group.isSingleton()) {
				Value value = group.getSignals().get(0).getValue(step);
				if (value == null) {
					return "";
				} else if (value instanceof RealValue && displayAsDecimal) {
					return ((RealValue) value).value.toTruncatedDecimal(12, "...");
				} else {
					return value.toString();
				}
			}
		}

		return "";
	}

	@Override
	public String getToolTipText(Object element) {
		if (element instanceof SignalGroup) {
			SignalGroup group = (SignalGroup) element;
			if (group.isSingleton()) {
				Value value = group.getSignals().get(0).getValue(step);
				if (value == null) {
					return null;
				} else if (value instanceof RealValue && displayAsDecimal) {
					RealValue rv = (RealValue) value;
					return rv.toString();
				}
			}
		}

		return null;
	}

	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 500;
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 10000;
	}

}
