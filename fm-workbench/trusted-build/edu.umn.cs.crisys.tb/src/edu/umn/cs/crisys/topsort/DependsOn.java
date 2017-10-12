package edu.umn.cs.crisys.topsort;

import java.util.List;

public interface DependsOn<T> {
	public List<T> dependencies();
}
