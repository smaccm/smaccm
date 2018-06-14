package edu.uah.rsesc.agree.ge.filtering;

import java.util.Arrays;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExecutableExtensionFactory;

public class SpecStatementFilterFactory implements IExecutableExtensionFactory {
	@Override
	public Object create() throws CoreException {
		return Arrays.asList(SpecStatementFilter.values());
	}
}
