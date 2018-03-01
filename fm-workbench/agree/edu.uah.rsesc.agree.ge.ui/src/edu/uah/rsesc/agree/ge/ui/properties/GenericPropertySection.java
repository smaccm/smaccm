package edu.uah.rsesc.agree.ge.ui.properties;

import java.util.Objects;
import java.util.function.Function;

import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.GlobalURIEditorOpener;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory;
import org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess;
import org.eclipse.xtext.ui.editor.embedded.IEditedResourceProvider;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.Element;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.instance.InstanceObject;
import org.osate.ge.BusinessObjectContext;
import org.osate.ge.BusinessObjectSelection;
import org.osate.ge.operations.Operation;
import org.osate.ge.operations.StepResultBuilder;
import org.osate.ge.ui.properties.PropertySectionUtil;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.rockwellcollins.atc.agree.services.AgreeGrammarAccess;
import com.rockwellcollins.atc.agree.ui.internal.AgreeActivator;

import edu.uah.rsesc.agree.ge.AgreeBusinessObjectProvider;
import edu.uah.rsesc.agree.ge.ui.util.TextConversionUtil;

@SuppressWarnings("restriction")
public abstract class GenericPropertySection extends AbstractPropertySection {
	private static class EmbeddedEditorResourceProvider implements IEditedResourceProvider {
		@Inject
		private IResourceSetProvider resourceSetProvider;

		@Inject
		private FileExtensionProvider ext;

		@Inject
		private AgreeGrammarAccess ga;

		@Override
		public XtextResource createResource() {
			final ResourceSet rs = resourceSetProvider.get(null);
			final URI uri = URI.createURI("synthetic:/ignore." + ext.getPrimaryFileExtension());
			final XtextResource result = (XtextResource) rs.createResource(uri);
			rs.getResources().add(result);
			result.setEntryPoint(ga.getSpecStatementAccess().getRule());
			result.setValidationDisabled(true);
			return result;
		}

	}

	private BusinessObjectSelection selectedBos;
	private Button addBtn;
	private Button deleteBtn;
	private Button openSourceBtn;

	private ListViewer listViewer;
	private EmbeddedEditor editor;
	private EmbeddedEditorModelAccess model;

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	@Override
	public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		final Composite composite = getWidgetFactory().createPlainComposite(parent, SWT.NONE);
		composite.setLayout(GridLayoutFactory.swtDefaults().numColumns(2).create());

		// Create Statement List Viewer
		listViewer = new ListViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		listViewer.setContentProvider(ArrayContentProvider.getInstance());
		listViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				final String name = getName(element);
				String suffix = "";
				final NamedElement container = getContainingPackageOrComponentClassifier(element);
				if (container != null) {
					suffix = " (" + container.getQualifiedName() + ")";
				}
				return name + suffix;
			}

			private NamedElement getContainingPackageOrComponentClassifier(final Object obj) {
				if (obj instanceof ComponentClassifier) {
					return (ComponentClassifier) obj;
				} else if (obj instanceof AadlPackage) {
					return (AadlPackage) obj;
				} else if (obj instanceof EObject) {
					return getContainingPackageOrComponentClassifier(((EObject) obj).eContainer());
				} else if (obj instanceof BusinessObjectContext) {
					return getContainingPackageOrComponentClassifier(((BusinessObjectContext) obj).getBusinessObject());
				} else if (obj != null) {
					return getContainingPackageOrComponentClassifier(Adapters.adapt(obj, BusinessObjectContext.class));
				} else {
					return null;
				}
			}
		});

		listViewer.addDoubleClickListener(event -> {
			onOpenSource();
		});

		listViewer.getControl()
				.setLayoutData(GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).create());

		// Create viewer for the selected element
		final AgreeActivator agreeActivator = AgreeActivator.getInstance();
		final Injector injector = agreeActivator.getInjector(AgreeActivator.COM_ROCKWELLCOLLINS_ATC_AGREE_AGREE);
		final EmbeddedEditorResourceProvider resourceProvider = injector
				.getInstance(EmbeddedEditorResourceProvider.class);
		final EmbeddedEditorFactory editorFactory = injector.getInstance(EmbeddedEditorFactory.class);
		editor = editorFactory.newEditor(resourceProvider).readOnly().withParent(composite);
		model = editor.createPartialEditor();

		// Register handler for when selected element changes
		listViewer.addSelectionChangedListener(event -> {
			onSelectionChanged();
		});

		// Add Buttons
		final Composite buttons = getWidgetFactory().createPlainComposite(composite, SWT.NONE);
		buttons.setLayoutData(GridDataFactory.fillDefaults().span(2, 1).create());
		buttons.setLayout(RowLayoutFactory.fillDefaults().wrap(false).create());

		addBtn = getWidgetFactory().createButton(buttons, "Add", SWT.PUSH);
		addBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onAdd();
			}
		});

		deleteBtn = getWidgetFactory().createButton(buttons, "Delete", SWT.PUSH);
		deleteBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onDelete();
			}
		});

		openSourceBtn = getWidgetFactory().createButton(buttons, "Open AADL Source", SWT.PUSH);
		openSourceBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				onOpenSource();
			}
		});

		// TODO: Experimental focus listener that allows updating the model with changes. However, issues regarding proper handling of errors when the viewer
		// loses focus needs to be handled. See comments in the code.
		// When using this listener, the editor should not be configured as read-only.
//		editor.getViewer().getControl().addFocusListener(new FocusListener() {
//			private EObject elementBeingEdited = null;
//
//			@Override
//			public void focusGained(final FocusEvent e) {
//				final IStructuredSelection elementSelection = listViewer.getStructuredSelection();
//				if (elementSelection.size() == 1) {
//					elementBeingEdited = (EObject) elementSelection.getFirstElement();
//				}
//			}
//
//			@Override
//			public void focusLost(final FocusEvent e) {
//				if (elementBeingEdited != null) {
//					// TODO: Check if a change was actually made?
//
//					final EObject originalElementBeingEdited = elementBeingEdited;
//
//					try {
//						try {
//							editor.getDocument().readOnly(res -> {
//								final EObject newObj = res.getContents().get(0);
//
//								// Test serialization
//								res.getSerializer().serialize(newObj);
//
//								PropertySectionUtil.execute(Operation.create(op -> {
//									final IStructuredSelection selection = listViewer.getStructuredSelection();
//									final EObject obj = (EObject) selection.getFirstElement();
//									op.map((tag) -> StepResultBuilder.create(obj).build()).modifyPreviousResult(o -> {
//										EcoreUtil.replace(o, newObj);
//										return StepResultBuilder.create().build();
//									});
//								}));
//
//								return null;
//							});
//						} finally {
//							elementBeingEdited = null;
//						}
//
//					} catch (final RuntimeException ex) {
//						// TODO: Message wording
//						switch (MessageDialog.open(MessageDialog.ERROR, null, "Error Updating Model",
//								"Error updating model.",
//								SWT.NONE, "Edit", "Revert")) {
//						// Edit
//						case 0:
//							// TODO: Selecting edit can result in the same message appearing because if the selection in the diagram has changed, the entire
//							// property section will lose focus.
//							editor.getViewer().getControl().setFocus();
//							elementBeingEdited = originalElementBeingEdited;
//							break;
//
//						// Revert
//						case 1:
//							updateSourceViewer();
//							break;
//						}
//					}
//				}
//			}
//		});
	}

	@Override
	public void setInput(final IWorkbenchPart part, final ISelection selection) {
		super.setInput(part, selection);

		selectedBos = Adapters.adapt(selection, BusinessObjectSelection.class);

		addBtn.setEnabled(selectedBos.bocStream().count() == 1
				&& selectedBos.boStream(Element.class).allMatch(bo -> !(bo instanceof InstanceObject)));
	}

	@Override
	public void refresh() {
		listViewer.setInput(getSpecStatements());
		onSelectionChanged();
	}

	protected final BusinessObjectSelection getSelectedBos() {
		return selectedBos;
	}

	private void onSelectionChanged() {
		updateSourceViewer();
		deleteBtn.setEnabled(listViewer.getStructuredSelection().size() > 0);
		openSourceBtn.setEnabled(listViewer.getStructuredSelection().size() == 1);
	}

	private void updateSourceViewer() {
		final String txt;

		final IStructuredSelection selection = listViewer.getStructuredSelection();
		switch (selection.size()) {
		case 0:
			txt = "";
			break;

		case 1:
			txt = TextConversionUtil.toText((EObject) selection.getFirstElement());
			break;

		default:
			txt = "<Multiple Elements Selected>";
			break;

		}

		model.updateModel(txt);
		editor.getViewer().setSelectedRange(0, 0);
	}

	private void onOpenSource() {
		final IStructuredSelection selection = listViewer.getStructuredSelection();
		if (selection.size() == 1) {
			openSource(selection.getFirstElement());
		}
	}

	private void openSource(final Object obj) {
		final EObject boEObj = (EObject) obj;
		final URI uri = Objects.requireNonNull(EcoreUtil.getURI(boEObj), "Unable to get URI for business object");
		if (!(boEObj.eResource() instanceof XtextResource)) {
			throw new RuntimeException("The resource of the business object resource is not an XtextResource");
		}

		final XtextResource res = (XtextResource) boEObj.eResource();
		final GlobalURIEditorOpener opener = Objects.requireNonNull(
				res.getResourceServiceProvider().get(GlobalURIEditorOpener.class),
				"unable to get global URI Editor opener");
		opener.open(uri, true);
	}

	protected abstract void onAdd();

	protected void onDelete() {
		final IStructuredSelection selection = listViewer.getStructuredSelection();
		if (MessageDialog.openQuestion(null, "Confirm Delete",
				"Are you sure you want to delete " + selection.size() + " elements?")) {
			PropertySectionUtil.execute(Operation.create(op -> {
				for (final Object o : selection.toArray()) {
					op.map((tag) -> StepResultBuilder.create(o).build()).modifyPreviousResult(bo -> {
						EcoreUtil.remove((EObject) bo);
						return StepResultBuilder.create().build();
					});
				}
			}));
		}
	}

	protected abstract String getName(final Object element);

	protected abstract Object[] getSpecStatements();

	protected final Object[] getSpecStatements(Function<? super Object, ? extends Element> boToSpecStatmentOwner,
			final Class<?> elementType) {
		return getSelectedBos().bocStream().map(boc -> boc.getBusinessObject()).map(boToSpecStatmentOwner)
				.filter(c -> c != null).distinct().flatMap(AgreeBusinessObjectProvider::getAllSpecStatements)
				.filter(e -> elementType.isInstance(e)).distinct().toArray();
	}
}
