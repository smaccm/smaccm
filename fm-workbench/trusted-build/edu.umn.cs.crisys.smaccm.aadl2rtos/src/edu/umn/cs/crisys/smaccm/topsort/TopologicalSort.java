package edu.umn.cs.crisys.smaccm.topsort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologicalSort {

	public static <T2 extends DependsOn<T2>> void topoSortElement(T2 elem, Map<T2, Color> assoc,
			List<T2> sorted) throws CyclicException {

		Color c = assoc.get(elem);
		if (c == null) {
			assoc.put(elem, Color.GRAY);
			if (elem.dependencies() != null) {
				for (T2 dep : elem.dependencies()) {
					topoSortElement(dep, assoc, sorted);
				}
			}
			assoc.put(elem, Color.BLACK);
			sorted.add(elem);
		} else if (c == Color.GRAY) {
			throw new CyclicException("Cycle detected during topological sort.");
		}
	};

	public static <T2 extends DependsOn<T2>> List<T2> performTopologicalSort(Collection<T2> elements)
			throws CyclicException {
		Map<T2, Color> colorAssoc = new HashMap<T2, Color>();
		List<T2> sorted = new ArrayList<T2>(elements.size());
		for (T2 e : elements) {
			topoSortElement(e, colorAssoc, sorted);
		}
		return sorted;
	}
};
