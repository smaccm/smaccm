package com.rockwellcollins.atc.agree.analysis;

import java.util.List;

import jkind.JKindException;
import jkind.api.results.JKindResult;
import jkind.api.results.PropertyResult;
import jkind.api.results.Renaming;
import jkind.api.results.Status;
import jkind.results.InvalidProperty;
import jkind.results.Property;
import jkind.results.UnknownProperty;
import jkind.results.ValidProperty;

public class ConsistencyResult extends JKindResult {

	private final  int consistLength;

	public ConsistencyResult(String name, List<String> properties,
			Renaming renaming, int consistLength) {
		super(name);
		this.renaming = renaming;
		this.consistLength = consistLength;
		for(String property : properties){
			addProperty(property, true);
		}
	}
	
	
	/**
	 * Add a new property to track
	 * 
	 * @param property
	 *            Property to be tracked (pre-renaming)
	 * @param invertStatus
	 *            True if finding a model means success. Otherwise false.
	 * @return The PropertyResult object which will store the results of the
	 *         property
	 */
	public PropertyResult addProperty(String property, boolean invertStatus) {
		if (renaming != null) {
			property = renaming.rename(property);
			if (property == null) {
				return null;
			}
		}

		PropertyResult propertyResult = new PropertyResult(property, renaming, invertStatus){
			@Override
			public void setProperty(Property original) {
				if (renaming == null) {
					property = original;
				} else {
					property = renaming.rename(original);
				}

				if (property instanceof ValidProperty) {
					throw new JKindException("Consistency checks should never prove UNSAT");
				} else if (property instanceof InvalidProperty) {
					setStatus(Status.VALID);
				} else if (property instanceof UnknownProperty) {
					setStatus(Status.UNKNOWN);
				}
			}
		};
		
		propertyResults.add(propertyResult);
		propertyResult.setParent(this);
		pcs.fireIndexedPropertyChange("propertyResults", propertyResults.size() - 1, null,
				propertyResult);
		addStatus(propertyResult.getStatus());
		propertyResult.addPropertyChangeListener(this);
		return propertyResult;
	}
	
	public void setBaseProgress(int k) {
		
		if(k > consistLength){
			multiStatus.add(Status.VALID);
		}
		
		for (PropertyResult pr : propertyResults) {
			pr.setBaseProgress(k);
		}
	}

}
