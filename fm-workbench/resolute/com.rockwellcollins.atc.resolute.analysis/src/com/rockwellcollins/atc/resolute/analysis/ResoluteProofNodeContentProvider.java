package com.rockwellcollins.atc.resolute.analysis;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ResoluteProofNodeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		@SuppressWarnings("rawtypes")
		List roots = (List) inputElement;
		return roots.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ResoluteProofNode) {
			ResoluteProofNode node = (ResoluteProofNode) parentElement;
			return node.getChildren().toArray();
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof ResoluteProofNode) {
			ResoluteProofNode node = (ResoluteProofNode) element;
			return node.getParent();
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof ResoluteProofNode) {
			ResoluteProofNode node = (ResoluteProofNode) element;
			return !node.getChildren().isEmpty();
		} else {
			throw new IllegalArgumentException();
		}
	}

}
