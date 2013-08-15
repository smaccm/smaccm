package edu.umn.cs.crisys.smaccm.topsort;

import java.util.List;

public interface DependsOn<T> {
    public List<T> dependencies();
}
