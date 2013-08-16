package com.rockwellcollins.atc.agree.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jkind.excel.Layout;

public class AgreeLayout implements Layout {
	private List<String> categories = new ArrayList<>();
	private Map<String, String> signalCategories = new HashMap<>();
	
	public void addCategory(String category) {
		categories.add(category);
	}

	public void addElement(String category, String signal) {
		signalCategories.put(signal, category);
	}
	
	@Override
	public List<String> getCategories() {
		return categories;
	}

	@Override
	public String getCategory(String signal) {
		return signalCategories.get(signal);
	}
}
