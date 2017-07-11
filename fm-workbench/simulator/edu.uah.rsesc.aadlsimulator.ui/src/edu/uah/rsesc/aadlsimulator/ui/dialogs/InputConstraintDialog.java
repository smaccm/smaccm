package edu.uah.rsesc.aadlsimulator.ui.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.RowLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import edu.uah.rsesc.aadlsimulator.xtext.InputConstraintStandaloneSetup;
import edu.uah.rsesc.aadlsimulator.xtext.TestReferenceTypeResolver;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BinaryExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BooleanLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraintFactory;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraintPackage;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntegerLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntervalExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.NegativeExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.Operator;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.PreExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomElementExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomIntegerExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomRealExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RealLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ScalarExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.SetExpression;
import edu.uah.rsesc.aadlsimulator.xtext.util.InputConstraintHelper;
import edu.uah.rsesc.aadlsimulator.xtext.util.ResultType;

public class InputConstraintDialog {
	public static class Result {
		private final InputConstraint inputConstraint;
		
		public Result(final InputConstraint inputConstraint) {
			this.inputConstraint = inputConstraint;
		}
		
		/**
		 * A null return value indicates an empty constraint.
		 * @return
		 */
		public InputConstraint getInputConstraint() {
			return inputConstraint;
		}
	}
	
	private class InnerDialog extends Dialog {
		private Composite container;
		private Composite constraintContainer;
		private Label validationMessageLabel;
		
		InnerDialog(final Shell parentShell) {
			super(parentShell);
			setShellStyle(getShellStyle() | SWT.RESIZE);
		}
		
		@Override
		protected void configureShell(final Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("Input Constraint Editor");
			newShell.setMinimumSize(250, 140);
			newShell.setSize(500, 500);
		}
		
		@Override
	  	protected Control createDialogArea(final Composite parent) {
		    final Composite area = (Composite)super.createDialogArea(parent);
		    container = new Composite(area, SWT.NONE);
		    container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		    container.setLayout(RowLayoutFactory.swtDefaults().type(SWT.VERTICAL).wrap(true).create());
		    
		    constraintContainer = new Composite(container, SWT.NONE);
		    constraintContainer.setLayout(RowLayoutFactory.swtDefaults().wrap(true).type(SWT.HORIZONTAL).create());
			    
			validationMessageLabel = new Label(container, SWT.WRAP);
			
		    container.addControlListener(new ControlAdapter() {				
				@Override
				public void controlResized(ControlEvent e) {
					constraintContainer.setLayoutData(new RowData(container.getSize().x, SWT.DEFAULT));
					validationMessageLabel.setLayoutData(new RowData(container.getSize().x, SWT.DEFAULT));
				}
			});
		    
		    refreshContraint();		    
		    
		    return area;
		}
		
		public void refreshContraint() {
			// Clear existing widgets
			for(final Widget w : constraintContainer.getChildren()) {
				w.dispose();
			}
			
			final InputConstraint ic = inputConstraintReference.get();

		    // Use a different prefix depending on whether the input constraint is assigning to a specific value or if it is constraining to be in a set/interval
			final String expressionPrefix;
			if(ic instanceof IntervalExpression ||
					ic instanceof SetExpression) {
				expressionPrefix = " is in";
			} else {
				expressionPrefix = " =";
			}
		    newLabel(constraintContainer, constrainedVariableName + expressionPrefix);
		    createConstraintWidgets(constraintContainer, null, inputConstraintReference);
		    constraintContainer.layout();
	
		    // Validate
		    String validationMessage = null;
	    	final InputConstraintHelper.Result validationResult = model.validate(ic, expectedType, numberOfPreviousSteps);
	    	if(validationResult.getErrorMessage() != null) {
	    		validationMessage = validationResult.getErrorMessage();
	    	}
	    	
	    	// Disable button if constraint validation failed
	    	final Button okBtn = getButton(IDialogConstants.OK_ID);
	    	if(okBtn != null) {
	    		okBtn.setEnabled(validationResult.getErrorMessage() == null);
	    	}

		    validationMessageLabel.setText(validationMessage == null ? "" : validationMessage);
		    
		    container.layout();
		}
	};

	private static interface Reference {
		Reference getParent();
		EClassifier getEType();
		Object get();
		void set(final Object ic);
	}
	
	// Reference used for the top level constraint. Reference changes the object which is stored internally.
	private static class RootConstraintReference implements Reference {
		private InputConstraint value;
		
		public RootConstraintReference(final InputConstraint value) {
			this.value = value;
		}
		
		@Override
		public Reference getParent() {
			return null;
		}
		
		@Override
		public EClassifier getEType() {
			return null;
		}
		
		@Override
		public InputConstraint get() {
			return value;
		}

		@Override
		public void set(final Object value) {
			if(value != null && !(value instanceof InputConstraint)) {
				throw new RuntimeException("value must be null or be an InputConstraint");
			}
			this.value = (InputConstraint)value;
		}
	}
	
	private static class ListMemberReference implements Reference {
		private final Reference parent; 
		private final EClassifier eType;
		@SuppressWarnings("rawtypes")
		private final List owner;
		private int idx;
		
		// An index of -1 indicates that the reference is for a new element.
		public ListMemberReference(final Reference parent, final EClassifier eType, @SuppressWarnings("rawtypes") final List owner, final int idx) {
			this.parent = Objects.requireNonNull(parent, "parent must not be null");
			this.eType = Objects.requireNonNull(eType, "eType must not be null");
			this.owner = Objects.requireNonNull(owner, "owner must not be null");
			this.idx = idx;
		}
		
		@Override
		public Reference getParent() {
			return parent;
		}
		
		@Override
		public EClassifier getEType() {
			return eType;
		}

		@Override
		public Object get() {
			return idx == -1 ? null : owner.get(idx);
		}

		@SuppressWarnings("unchecked")
		@Override
		public void set(final Object newValue) {
			// Null values indicate that the existing element should be removed
			if(newValue == null) {
				if(idx != -1) {
					owner.remove(idx);
				}
			} else {			
				// Add if the element isn't in the list
				if(idx == -1) {
					owner.add(newValue);
				} else {
					owner.set(idx, newValue);
				}
			}
		}		
	}
	
	private static class StructuralFeatureReference implements Reference {
		private final Reference parent;
		private final EObject owner;
		private final EStructuralFeature feature;
		
		public StructuralFeatureReference(final Reference parent, final EObject owner, final EStructuralFeature feature) {
			this.parent = Objects.requireNonNull(parent, "parent must not be null");
			this.owner = Objects.requireNonNull(owner, "owner must not be null");
			this.feature = Objects.requireNonNull(feature, "feature must not be null");
		}
		
		public EStructuralFeature getEStructuralFeature() {
			return feature;
		}
		
		@Override
		public Reference getParent() {
			return parent;
		}
		
		@Override
		public EClassifier getEType() {
			return feature.getEType();
		}
		
		@Override
		public Object get() {
			return owner.eGet(feature);
		}
		
		@Override
		public void set(final Object value) {
			owner.eSet(feature, value);
		}
	}
	
	// Provides required information and capabilities to the dialog
	public interface Model {
		String unparse(InputConstraint ic);
		InputConstraintHelper.Result parseAndValidate(final String str, final ResultType expectedType, final int numberOfPreviousSteps);
		InputConstraintHelper.Result validate(final InputConstraint ic, final ResultType expectedType, final int numberOfPreviousSteps);
		ResultType getElementReferenceType(final ElementRefExpression reference);		
		ResultType getConstReferenceType(final ConstRefExpression reference);
		Stream<ElementRefExpression> getVariables();
		Stream<ConstRefExpression> getConstants();
	}	
	
	private static InputConstraintPackage icp = InputConstraintPackage.eINSTANCE;
	private final Model model;
	private final RootConstraintReference inputConstraintReference;
	private final ResultType expectedType;
	private final int numberOfPreviousSteps;
	private final String constrainedVariableName;
	private final InnerDialog dlg;
		
	protected InputConstraintDialog(final Shell parentShell,
			final Model model,
			final String constrainedVariableName,
			final InputConstraint initialInputConstraint,
			final ResultType expectedType, 
			final int numberOfPreviousSteps) {
		this.model = Objects.requireNonNull(model, "model must not be null");
		this.constrainedVariableName = Objects.requireNonNull(constrainedVariableName, "constrainedVariableName must not be null");
		this.expectedType = Objects.requireNonNull(expectedType, "expectedType must not be null");
		this.numberOfPreviousSteps = numberOfPreviousSteps;
		this.dlg = new InnerDialog(parentShell);
		
		// If one was specified, make a copy of the initial input constraint
		this.inputConstraintReference = new RootConstraintReference(initialInputConstraint == null ? null : EcoreUtil.copy(initialInputConstraint));
	}
	
	private void createConstraintWidgets(final Composite container, 
			final Object parentValue,
			final Reference ref) {
		final Object value = ref.get();

		if(value instanceof IntervalExpression) {
			final IntervalExpression ie = (IntervalExpression)value;
			if(ref.getEType() == icp.getIntervalExpression()) {
				newLabel(container, "interval");
			} else {
				newLink(container, ref, "interval");
			}
			
			newLink(container, new StructuralFeatureReference(ref, ie, icp.getIntervalExpression_LeftClosed()), ie.isLeftClosed() ? "[" : "(");
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, ie, icp.getIntervalExpression_Left()));
			newLabel(container, ",");
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, ie, icp.getIntervalExpression_Right()));
			newLink(container, new StructuralFeatureReference(ref, ie, icp.getIntervalExpression_RightClosed()), ie.isRightClosed() ? "]" : ")");
		} else if(value instanceof SetExpression) {
			final SetExpression se = (SetExpression)value;
			if(ref.getEType() == icp.getSetExpression()) {
				newLabel(container, "set");
			} else {
				newLink(container, ref, "set");
			}
			
			newLabel(container, "{");			
			
			final int numberOfMembers = se.getMembers().size();
			for(int i = 0; i < numberOfMembers; i++) {
				createConstraintWidgets(container, value, new ListMemberReference(ref, icp.getSetExpression_Members().getEType(), se.getMembers(), i));
				newLabel(container, ",");
			}
			
			newLink(container, new ListMemberReference(ref, icp.getSetExpression_Members().getEType(), se.getMembers(), -1), "<add>");

			newLabel(container, "}");
		} else if(value instanceof BinaryExpression) {
			final BinaryExpression be = (BinaryExpression)value;
			
			boolean showParentheses = parentValue instanceof NegativeExpression;
			if(!showParentheses && parentValue instanceof BinaryExpression) {
				final BinaryExpression parentBe = (BinaryExpression)parentValue;
				final boolean isAddOrSubstract = be.getOp() == Operator.ADDITION || be.getOp() == Operator.SUBTRACTION;
				final boolean isParentAddOrSubstract = parentBe.getOp() == Operator.ADDITION || parentBe.getOp() == Operator.SUBTRACTION;
				if(isAddOrSubstract && !isParentAddOrSubstract) {
					showParentheses = true;
				}					
			}

			if(showParentheses) {
				newLabel(container, "(");
			}
			
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, be, icp.getBinaryExpression_Left()));
			newLink(container, new StructuralFeatureReference(ref, be, icp.getBinaryExpression_Op()), be.getOp().toString());
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, be, icp.getBinaryExpression_Right()));
			
			if(showParentheses) {
				newLabel(container, ")");
			}
		} else if(value instanceof NegativeExpression) {
			final NegativeExpression ne = (NegativeExpression)value;
			newLabel(container, "-");	
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, ne, icp.getNegativeExpression_Value()));			
		} else if(value instanceof PreExpression) {
			newLink(container, ref, "previous value of");
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, (PreExpression)value, icp.getPreExpression_Ref()));
		} else if(value instanceof RandomElementExpression) {
			newLink(container, ref, "random element");
			newLabel(container, "in");
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, (RandomElementExpression)value, icp.getRandomElementExpression_Set()));
		} else if(value instanceof RandomIntegerExpression) {
			newLink(container, ref, "random integer");
			newLabel(container, "in");
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, (RandomIntegerExpression)value, icp.getRandomIntegerExpression_Interval()));
		} else if(value instanceof RandomRealExpression) {
			newLink(container, ref, "random real");
			newLabel(container, "in");
			createConstraintWidgets(container, value, new StructuralFeatureReference(ref, (RandomRealExpression)value, icp.getRandomRealExpression_Interval()));
		} else if(value instanceof ConstRefExpression) {
			newLink(container, ref, model.unparse((ConstRefExpression)value));
		} else if(value instanceof ElementRefExpression) {
			newLink(container, ref, model.unparse((ElementRefExpression)value));
		} else if(value instanceof RealLiteral) {
			final RealLiteral rl = (RealLiteral)value;
			newLink(container, ref, rl.getValue().toString());
		} else if(value instanceof IntegerLiteral) {
			final IntegerLiteral il = (IntegerLiteral)value;
			newLink(container, ref, il.getValue().toString());
		} else if(value instanceof BooleanLiteral) {
			final BooleanLiteral bl = (BooleanLiteral)value;
			newLink(container, ref, Boolean.toString(bl.isValue()));
		} else if(value == null) {
			newLink(container, ref, "<select>");
		} else {
			throw new RuntimeException("Unexpected value: " + value);
		}
	}	
	
	private static void newLabel(final Composite container, final String txt) {
		final Label newLabel = new Label(container, SWT.NONE);
		newLabel.setText(txt);
	}
	
	private void newLink(final Composite container, 
			final Reference originalRef,
			final String txt) {
		// Determine Text for the Node
		final Link newLink = new Link(container, SWT.NONE);
		newLink.setText("<a>" + txt + "</a>");
		newLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {	
				final Menu menu = new Menu(newLink);
				// Dispose of the old menu if it exists
				if(newLink.getMenu() != null) {
					newLink.getMenu().dispose();
				}
				newLink.setMenu(menu);
				menu.setVisible(true);
				
				// Edit the negative expression itself rather than actual value
				Reference ref = originalRef;
				if((ref.getParent() != null && ref.getParent().get() instanceof NegativeExpression)) {
					ref = ref.getParent();
				}
					
				// Populate menu
				if(ref instanceof RootConstraintReference) {
					addEditExpressionMenuItem(menu, ref);
					addScalarExpressionsMenuItems(menu, true, ref);
					addNewEClassMenuItem(menu, ref, "Interval", icp.getIntervalExpression());
					addNewEClassMenuItem(menu, ref, "Set", icp.getSetExpression());
				} else {
					final EClassifier type = ref.getEType();
					if(ref instanceof ListMemberReference && ref.get() != null) {
						addRemoveMenuItem(menu, ref);
					} 
					
					if(ref instanceof StructuralFeatureReference && ((StructuralFeatureReference)ref).getEStructuralFeature() == icp.getIntervalExpression_LeftClosed()) {
					    addBooleanMenuItem(menu, ref, "( - Exclusive", false);
						addBooleanMenuItem(menu, ref, "[ - Inclusive", true);
					} else if(ref instanceof StructuralFeatureReference && ((StructuralFeatureReference)ref).getEStructuralFeature() == icp.getIntervalExpression_RightClosed()) {
						addBooleanMenuItem(menu, ref, ") - Exclusive", false);
						addBooleanMenuItem(menu, ref, "] - Inclusive", true);
					} else if(type == icp.getScalarExpression()) {	
						addEditExpressionMenuItem(menu, ref);

						final EStructuralFeature sf = ref instanceof StructuralFeatureReference ? ((StructuralFeatureReference)ref).getEStructuralFeature() : null;
						final boolean showBoolean = sf != icp.getIntervalExpression_Left() &&
								sf != icp.getIntervalExpression_Right();						
						addScalarExpressionsMenuItems(menu, showBoolean, ref);
					} else if(type == icp.getElementRefExpression()) {			
						addElementReferenceMenuItems(menu, ref);
					} else if(type == icp.getOperator()) {
						final Reference parent = ref.getParent();
						addEditExpressionMenuItem(menu, parent);
						addNegateMenuItem(menu, parent.getParent() != null && parent.getParent().get() instanceof NegativeExpression ? parent.getParent() : parent);
						addOperatorMenuItem(menu, ref, Operator.ADDITION);
						addOperatorMenuItem(menu, ref, Operator.SUBTRACTION);
						addOperatorMenuItem(menu, ref, Operator.MULTIPLICATION);
						addOperatorMenuItem(menu, ref, Operator.DIVISION);
					} else {
						throw new RuntimeException("Unhandled type: " + type);
					}
				}
			}
		});
	}
	
	private void addReferenceMenuItems(final Menu menu, 
			final Reference ref) {
		final MenuItem constantsMenuItem = new MenuItem(menu, SWT.CASCADE);
		constantsMenuItem.setText("Constants");
		
		final Menu constantsMenu = new Menu(menu);
		constantsMenuItem.setMenu(constantsMenu);
	    		
		final Map<String, List<ConstRefExpression>> pkgToConstants = new HashMap<>();						
		Iterable<ConstRefExpression> constIteratable = () -> model.getConstants().iterator();
		for(final ConstRefExpression constExpression : constIteratable) {
			final String pkgName = String.join("::", constExpression.getPackageSegments().toArray(new String[0])).toLowerCase();
			List<ConstRefExpression> pkgConstants = pkgToConstants.get(pkgName);
			if(pkgConstants== null) {
				pkgConstants = new ArrayList<>();
				pkgToConstants.put(pkgName, pkgConstants);
			}
			
			pkgConstants.add(constExpression);
		}	
		
		final SelectionListener constMenuItemSelectionListener = new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		final ConstRefExpression constExpr = (ConstRefExpression)e.widget.getData();
	    		ref.set(constExpr);
	    		dlg.refreshContraint();					    		
	    	}
		};
		
		final MenuListener constMenuListener = new MenuListener() {
			@Override
			public void menuHidden(final MenuEvent e) {
			}

			@Override
			public void menuShown(final MenuEvent e) {
				final Menu pkgConstantsMenu = (Menu)e.widget;
				// Only create menu items the first time the menu is shown
				if(pkgConstantsMenu.getItemCount() == 0) {
					final String pkgName = (String)pkgConstantsMenu.getData();
					for(final ConstRefExpression constExpr : (Iterable<ConstRefExpression>)pkgToConstants.get(pkgName).
							stream().
							sorted((ce1,ce2) -> ce1.getConstantName().
							compareToIgnoreCase(ce2.getConstantName()))::iterator) {
						final MenuItem constMenuItem = new MenuItem(pkgConstantsMenu, SWT.CASCADE);
						constMenuItem.setText(constExpr.getConstantName());
						constMenuItem.setData(constExpr);
						constMenuItem.addSelectionListener(constMenuItemSelectionListener);
					}
				}
			}							
		};
		
		for(final String pkgName : pkgToConstants.keySet().stream().sorted().collect(Collectors.toList())) {
			final Menu pkgMenu = new Menu(constantsMenu);
			final MenuItem pkgMenuItem = new MenuItem(constantsMenu, SWT.CASCADE);
			pkgMenuItem.setText(pkgName);
			pkgMenuItem.setMenu(pkgMenu);
			pkgMenu.addMenuListener(constMenuListener);
			pkgMenu.setData(pkgName);
		}
		
		addElementReferenceMenuItems(menu, ref);
	}

	private void addElementReferenceMenuItems(final Menu menu, 
			final Reference ref) {
		// Variables
		final MenuItem variablesMenuItem = new MenuItem(menu, SWT.CASCADE);
		variablesMenuItem.setText("Variables");
		
		final Menu variablesMenu = new Menu(menu);
		variablesMenuItem.setMenu(variablesMenu);
		
		final SelectionListener variableMenuItemSelectionListener = new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		final ElementRefExpression variable = (ElementRefExpression)e.widget.getData();
	    		ref.set(variable);
	    		dlg.refreshContraint();					    		
	    	}
		};
		
		// Create menu items
		// Sort variables to ensure menu items are created in a order that ensures the parent menu item exists.
		final Map<String, MenuItem> idToMenuItemMap = new HashMap<>();
		for(final ElementRefExpression variable : model.getVariables().sorted(InputConstraintDialog::compareElementRef).collect(Collectors.toList())) {
			final List<String> ids = variable.getIds();
			final String variableId = String.join(".", ids);
			final String parentId = String.join(".", ids.subList(0, ids.size()-1));
					
			final Menu parentMenu;
			if(parentId.length() == 0) {
				parentMenu = variablesMenu;
			} else {
				final MenuItem parentMenuItem = Objects.requireNonNull(idToMenuItemMap.get(parentId), "Unable to retrieve parent menu item");
				if(parentMenuItem.getMenu() == null) {
					final Menu newMenu = new Menu(parentMenuItem.getParent());
					parentMenuItem.setMenu(newMenu);
					
					// Create menu item for the variable itself. Unable to select menu items which have children.
					final MenuItem selfMenuItem = new MenuItem(newMenu, SWT.NONE);
					selfMenuItem.setText("<self>");
					selfMenuItem.setData(parentMenuItem.getData());
					selfMenuItem.addSelectionListener(variableMenuItemSelectionListener);
				}
				
				parentMenu = parentMenuItem.getMenu();
			}
			
			// Create a menu item for the variable
			final MenuItem variableMenuItem = new MenuItem(parentMenu, SWT.CASCADE);
			variableMenuItem.setText(ids.get(ids.size()-1));
			variableMenuItem.setData(variable);
			variableMenuItem.addSelectionListener(variableMenuItemSelectionListener);
			idToMenuItemMap.put(variableId, variableMenuItem);
			
		}
	}
	
	private static int compareElementRef(final ElementRefExpression v1, final ElementRefExpression v2) {
		final int s1 = v1.getIds().size();
		final int s2 = v2.getIds().size();
		if(s1 == s2) {
			return v1.getIds().get(s1-1).compareToIgnoreCase(v2.getIds().get(s2-1));
		} else {
			return Integer.compare(v1.getIds().size(), v2.getIds().size());
		}
	}
	
	private void addScalarExpressionsMenuItems(final Menu menu, 
			boolean showBoolean,
			final Reference ref) {
		if(ref.get() instanceof ScalarExpression && !(ref.get() instanceof BooleanLiteral)) {
			addNegateMenuItem(menu, ref);
		}
		
		addTextExpressionMenuItem(menu, ref, "Integer...", ResultType.INTEGER, "0");
		addTextExpressionMenuItem(menu, ref, "Real...", ResultType.REAL, "0.0");
		
		if(showBoolean) {
			final Menu booleanMenu = new Menu(menu);
			final MenuItem booleanMenuItem = new MenuItem(menu, SWT.CASCADE);
			booleanMenuItem.setText("Boolean");
			booleanMenuItem.setMenu(booleanMenu);
			addBooleanLiteralMenuItem(booleanMenu, ref, true);
			addBooleanLiteralMenuItem(booleanMenu, ref, false);
		}
		
		addReferenceMenuItems(menu, ref);
		addNewEClassMenuItem(menu, ref, "Random Integer", icp.getRandomIntegerExpression());
		addNewEClassMenuItem(menu, ref, "Random Real", icp.getRandomRealExpression());
		addNewEClassMenuItem(menu, ref, "Random Element", icp.getRandomElementExpression());						
		addPreviousValueMenuItem(menu, ref);
	}
	
	private void addRemoveMenuItem(final Menu menu, final Reference ref) {
		final MenuItem menuItem = new MenuItem(menu, SWT.NONE);
	    menuItem.setText("Remove");
	    menuItem.addSelectionListener(new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		ref.set(null);
	    		dlg.refreshContraint();					    		
	    	}
	    });
	}
	
	private void addNegateMenuItem(final Menu menu, final Reference ref) {
		final MenuItem menuItem = new MenuItem(menu, SWT.CHECK);
		menuItem.setSelection(ref.get() instanceof NegativeExpression);
	    menuItem.setText("Negate");
	    
	    menuItem.addSelectionListener(new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		final boolean checked = ((MenuItem)e.widget).getSelection();
	    		if(checked) {
	    			final NegativeExpression negExpr = (NegativeExpression)InputConstraintFactory.eINSTANCE.create(InputConstraintPackage.eINSTANCE.getNegativeExpression());
	    			negExpr.setValue((ScalarExpression)ref.get());
	    			ref.set(negExpr);
	    		} else {
    				ref.set(((NegativeExpression)ref.get()).getValue());
	    		}
	    		
	    		dlg.refreshContraint();
	    	}
	    });
	}
	
	private void addPreviousValueMenuItem(final Menu menu, final Reference ref) {
		final MenuItem menuItem = new MenuItem(menu, SWT.CHECK);
		menuItem.setSelection(ref.get() instanceof PreExpression);
	    menuItem.setText("Previous Value");	    
	    menuItem.addSelectionListener(new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		final boolean checked = ((MenuItem)e.widget).getSelection();
	    		if(checked) {
	    			final PreExpression preExpr = (PreExpression)InputConstraintFactory.eINSTANCE.create(InputConstraintPackage.eINSTANCE.getPreExpression());
	    			final Object oldValue = ref.get();
	    			if(oldValue instanceof ElementRefExpression) {
	    				preExpr.setRef((ElementRefExpression)oldValue);
	    			}
	    			ref.set(preExpr);
	    		} else {
    				ref.set(((PreExpression)ref.get()).getRef());
	    		}
	    		
	    		dlg.refreshContraint();
	    	}
	    });
	}	
	
	private void addBooleanMenuItem(final Menu menu, final Reference ref, final String label, final boolean newValue) {
		final MenuItem menuItem = new MenuItem(menu, SWT.RADIO);
		menuItem.setSelection(ref.get() == Boolean.valueOf(newValue));
	    menuItem.setText(label);
	    menuItem.addSelectionListener(new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		if(((MenuItem)e.widget).getSelection()) {
	    			ref.set(newValue);
	    			dlg.refreshContraint();
	    		}
	    	}
	    });
	}
	
	private void addBooleanLiteralMenuItem(final Menu menu, final Reference ref, final boolean newValue) {
		final MenuItem menuItem = new MenuItem(menu, SWT.RADIO);
		menuItem.setSelection(ref.get() instanceof BooleanLiteral && ((BooleanLiteral)ref.get()).isValue() == newValue);
	    menuItem.setText(Boolean.toString(newValue));
	    menuItem.addSelectionListener(new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		if(((MenuItem)e.widget).getSelection()) {
		    		final BooleanLiteral lit = InputConstraintFactory.eINSTANCE.createBooleanLiteral();
		    		lit.setValue(newValue);
		    		
		    		// Remove parent negation for boolean literals
					final Reference refToEdit = (ref.getParent() != null && ref.getParent().get() instanceof NegativeExpression) ? ref.getParent() : ref;
					
		    		refToEdit.set(lit);
		    		dlg.refreshContraint();					    		
	    		}
	    	}
	    });
	}
	
	private void addOperatorMenuItem(final Menu menu, final Reference ref, final Operator newValue) {
		final MenuItem menuItem = new MenuItem(menu, SWT.RADIO);
		menuItem.setSelection(ref.get() == newValue);
	    menuItem.setText(newValue.toString());
	    menuItem.addSelectionListener(new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		if(((MenuItem)e.widget).getSelection()) {
		    		ref.set(newValue);
		    		dlg.refreshContraint();					    		
    			}
	    	}
	    });
	}
	
	private void addNewEClassMenuItem(final Menu menu, final Reference ref, final String label, final EClass eClass) {
		final MenuItem menuItem = new MenuItem(menu, SWT.RADIO);
		final boolean isInstanceOfEClass = eClass.isInstance(ref.get());
		menuItem.setSelection(isInstanceOfEClass);
		menuItem.setEnabled(!isInstanceOfEClass);
	    menuItem.setText(label);
	    menuItem.addSelectionListener(new SelectionAdapter() {
	    	@Override
			public void widgetSelected(final SelectionEvent e) {
	    		if(((MenuItem)e.widget).getSelection()) {
	    			final Object oldValue = ref.get();	 
	    			
	    			// Store interval from current expression in case it is needed to initialize new object
	    			final IntervalExpression oldInterval;	    			
	    			if(oldValue instanceof RandomIntegerExpression) {
	    				oldInterval = ((RandomIntegerExpression) oldValue).getInterval();
	    			} else if(oldValue instanceof RandomRealExpression) {
	    				oldInterval = ((RandomRealExpression) oldValue).getInterval();
	    			} else if(oldValue instanceof IntervalExpression) {
	    				oldInterval = (IntervalExpression)oldValue;
	    			} else {
	    				oldInterval = null;
	    			}

	    			final Object newValue;
	    			if(eClass == icp.getIntervalExpression() && oldInterval != null) {
	    				newValue = oldInterval;
	    			} else {
	    				newValue = InputConstraintFactory.eINSTANCE.create(eClass); 
	    			}

		    		ref.set(newValue);
	
		    		// Perform initialization on the new object
		    		if(newValue instanceof RandomIntegerExpression || newValue instanceof RandomRealExpression) {
		    			final IntervalExpression newInterval = oldInterval == null ? InputConstraintFactory.eINSTANCE.createIntervalExpression() : oldInterval;		    			
		    			if(newValue instanceof RandomIntegerExpression) {
		    				((RandomIntegerExpression)newValue).setInterval(newInterval);
		    			} else if(newValue instanceof RandomRealExpression) {
			    			((RandomRealExpression)newValue).setInterval(newInterval);
			    		}
		    		} else if(newValue instanceof RandomElementExpression) {
		    			((RandomElementExpression)newValue).setSet(InputConstraintFactory.eINSTANCE.createSetExpression());
		    		} else if(newValue instanceof PreExpression && oldValue instanceof ElementRefExpression) {
	    				((PreExpression)newValue).setRef((ElementRefExpression)oldValue);
	    			} 
	
		    		dlg.refreshContraint();					    		
	    		}
	    	}
	    });
	}

	private void addEditExpressionMenuItem(final Menu menu, final Reference ref) {
		addTextExpressionMenuItem(menu, ref, "Edit...", null, "");
	}
	
	/**
	 * Returns null if unparse fails.
	 * @param ic
	 * @return
	 */
	private String unparse(final InputConstraint ic) {
	    String exprTxt = null;
		try {
			exprTxt = model.unparse(ic).trim();
		} catch(final Exception ex) {
			// Ignore any unparse errors
			ex.printStackTrace();
		}
		
		return exprTxt;
	}
	
	/**
	 * This function will bubble up to edit the parent if the parent is a binary or negative expression
	 * @param menu
	 * @param ref
	 * @param label
	 * @param expectedType
	 * @param defaultExpressionTxt used if the reference is null
	 */
	private void addTextExpressionMenuItem(final Menu menu, Reference ref, final String label, final ResultType expectedType, final String defaultExpressionTxt) {
		// If the value is part of a binary expression, prompt the user to edit the entire containing binary expression.
		while(ref.getParent() != null && 
				(ref.getParent().get() instanceof BinaryExpression ||
				ref.getParent().get() instanceof NegativeExpression)) {
			ref = ref.getParent();
		}		

		final Reference refToEdit = ref;		
	    final MenuItem menuItem = new MenuItem(menu, SWT.NONE);
	    menuItem.setText(label);
	    		
	    // Disable the menu item if unable to unparse
	    final InputConstraint ic = (InputConstraint)refToEdit.get();
	    final String exprTxt = unparse(ic);
		menuItem.setEnabled(exprTxt != null);	
		if(menuItem.isEnabled()) {
		    menuItem.addSelectionListener(new SelectionAdapter() {
		    	@Override
				public void widgetSelected(final SelectionEvent e) {
		    		final InputDialog txtExpressionDlg = new InputDialog(dlg.getShell(), "Edit Expression", "Edit Expression", exprTxt, new IInputValidator() {
		    			@Override
		    			public String isValid(final String value) {
		    				return getParseAndValidateResult(value, expectedType).getErrorMessage();
		    			}
		    		});
		    		
		    		if(txtExpressionDlg.open() == Dialog.OK) {
		    			refToEdit.set(getParseAndValidateResult(txtExpressionDlg.getValue(), expectedType).getInputConstraint());
		    			dlg.refreshContraint();
		    		}		
		    	}
		    });
		}
	}
	
	private InputConstraintHelper.Result getParseAndValidateResult(final String str, final ResultType expectedType) {
		return model.parseAndValidate(str, expectedType, numberOfPreviousSteps);
	}
	
	/**
	 * A null return value indicates that the dialog was canceled.
	 * @return
	 */
	private Result open() {
		return dlg.open() == Dialog.OK ? new Result(inputConstraintReference.get()) : null;
	}
	
	/**
	 * A null return value indicates that the dialog was canceled.
	 * @param parentShell
	 * @param ic
	 * @return
	 */
	public static Result show(final Shell parentShell,
			final Model model,
			final String constrainedVariableName, 
			final InputConstraint ic,
			final ResultType expectedType, 
			final int numberOfPreviousSteps) {
		final InputConstraintDialog dlg = new InputConstraintDialog(parentShell, model, constrainedVariableName, ic, expectedType, numberOfPreviousSteps);
		return dlg.open();
	}
			
	public static void main(final String[] args) {
		new Display();

		final com.google.inject.Injector injector = new InputConstraintStandaloneSetup().createInjectorAndDoEMFRegistration();
		final InputConstraintHelper icHelper = injector.getInstance(InputConstraintHelper.class);
		final TestReferenceTypeResolver resolver = new TestReferenceTypeResolver();
		
		// Create model to provide information to the dialog.
		final Model model = new Model() {			
			@Override
			public String unparse(final InputConstraint ic) {
				return icHelper.unparse(ic);
			}
			
			@Override
			public InputConstraintHelper.Result parseAndValidate(final String str, final ResultType expectedType, final int numberOfPreviousSteps) {
				return icHelper.parseAndValidate(str, resolver, expectedType, numberOfPreviousSteps);
			}
			
			@Override 
			public InputConstraintHelper.Result validate(final InputConstraint ic, final ResultType expectedType, final int numberOfPreviousSteps) {
				return icHelper.validate(ic, resolver,  expectedType, numberOfPreviousSteps);
			}
			
			@Override
			public Stream<ElementRefExpression> getVariables() {
				final Stream.Builder<ElementRefExpression> builder = Stream.builder();
				builder.add(createElementRefExpression("a1", "b", "d"));
				builder.add(createElementRefExpression("a1"));
				builder.add(createElementRefExpression("a1", "b"));
				builder.add(createElementRefExpression("a1", "b", "c"));
				builder.add(createElementRefExpression("a2", "b"));
				builder.add(createElementRefExpression("a2"));
				return builder.build();
			}
			
			private ElementRefExpression createElementRefExpression(String ... ids) {
				final ElementRefExpression result = InputConstraintFactory.eINSTANCE.createElementRefExpression();
				result.getIds().addAll(Arrays.asList(ids));
				return result;
			}
			
			@Override
			public ResultType getElementReferenceType(final ElementRefExpression reference) {
				return resolver.getElementReferenceType(reference);
			}
			
			@Override
			public Stream<ConstRefExpression> getConstants() {
				final Stream.Builder<ConstRefExpression> builder = Stream.builder();
				builder.add(createConstRefExpression("C1", "my_pkg"));
				builder.add(createConstRefExpression("C2", "my_pkg", "inner_pkg"));
				builder.add(createConstRefExpression("C3A", "my_pkg", "inner_pkg", "inner_inner_pkg"));
				builder.add(createConstRefExpression("C3B", "my_pkg", "inner_pkg", "inner_inner_pkg"));
				builder.add(createConstRefExpression("C3C", "my_pkg", "inner_pkg", "inner_inner_pkg"));
				builder.add(createConstRefExpression("C3D", "my_pkg", "inner_pkg", "inner_inner_pkg"));
				return builder.build();
			}
			
			private ConstRefExpression createConstRefExpression(final String constantName, final String ... packageSegments) {
				final ConstRefExpression result = InputConstraintFactory.eINSTANCE.createConstRefExpression();
				result.setConstantName(constantName);
				result.getPackageSegments().addAll(Arrays.asList(packageSegments));
				return result;
			}
			
			@Override
			public ResultType getConstReferenceType(final ConstRefExpression reference) {
				return resolver.getConstReferenceType(reference);
			}
		};
		
		// Show the dialog
		final InputConstraint initialConstraint = icHelper.parse("5 + 5").getInputConstraint();
		final Result result = InputConstraintDialog.show(null, model, "z", initialConstraint, ResultType.INTEGER, 1);
		if(result == null) {
			System.out.println("Dialog canceled");
		} else {
			System.out.println("Result:");
			System.out.println(result.inputConstraint);
		}
	}
}
