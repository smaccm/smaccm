package edu.umn.cs.crisys.topsort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// TODO: currently only sorts the elements in the initial list, 
// even if the dependencies contain other elements; this matches
// my current purpose, but may not do what you want!

public class TopologicalSort {

	public static <T2 extends DependsOn<T2>> void topoSortElement(T2 elem, Map<T2, Color> assoc,
	    List<T2> sorted) throws CyclicException {

		Color c = assoc.get(elem);
		if (c == null) {
			assoc.put(elem, Color.GRAY);
			if (elem.dependencies() != null) {
				for (T2 dep : elem.dependencies()) {
				  // Don't consider elements outside of the initial set to sort.
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
	
	public static <T2 extends DependsOn<T2>> List<T2> performElementsOnlyTopologicalSort(Collection<T2> elements)
      throws CyclicException {
	  
    // start from the set under consideration.
	  final Set<T2> elemSet = new HashSet<T2>(elements); 
    final List<T2> allTypesSorted = performTopologicalSort(elements);
    
    List<T2> elementsSorted = new ArrayList<T2>();
    for (T2 e : allTypesSorted) {
      if (elemSet.contains(e)) {
        elementsSorted.add(e);
      }
    }
    return elementsSorted;
	}
};


// check for duplicates...this is currently inefficient!
/*
boolean found = false;
for (T2 other: sorted) {
  if (other.equals(elem)) {
    found = true;
  }
}
if (!found) {
  sorted.add(elem);
}
*/
