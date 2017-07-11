package edu.uah.rsesc.aadlsimulator.ui.views;

import java.util.Objects;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

abstract class VariableValueCellEditor extends DialogCellEditor {
	private final Color constraintErrorColor;
	private Text text;
	private Button button;	
	private ModifyListener textModifyListener;
	private Listener focusInFilter;
	
	public VariableValueCellEditor(final Composite parent,
			final Color constraintErrorColor) {
		super(parent);
		this.constraintErrorColor = Objects.requireNonNull(constraintErrorColor, "constraintErrorColor must not be null");
	}
	
	@Override
	public void dispose() {
		if(text != null && !text.isDisposed() && textModifyListener != null) {
			text.removeModifyListener(textModifyListener);
		}
		
		final Display display = Display.getCurrent();
		if(display != null && !display.isDisposed()) {
			display.removeFilter(SWT.FocusIn, getFocusInFilter());
		}
		
		super.dispose();
	}
	
    protected Button createButton(Composite parent) {
        this.button = super.createButton(parent);
        return button;
    }
    
	@Override
	protected Control createContents(final Composite cell) {	
		text = new Text(cell, SWT.NONE);
		text.setLayoutData(GridDataFactory.fillDefaults().create());	
		text.addModifyListener(getTextModifyListener());		
		Display.getCurrent().addFilter(SWT.FocusIn, getFocusInFilter());
		
		return text;
	}
	
	private ModifyListener getTextModifyListener() {
		if(textModifyListener == null) {
			textModifyListener = new ModifyListener() {					
				@Override
				public void modifyText(final ModifyEvent e) {
					final boolean oldValidState = isValueValid();
				    final boolean newValidState = isCorrect(text.getText());
					setValue(text.getText());
					
					valueChanged(oldValidState, newValidState);
				}
			};
		}
		
		return textModifyListener;
	}
	
	private Listener getFocusInFilter() {
		if(focusInFilter == null) {
			focusInFilter = new Listener() {
				private Widget lastFocusIn = null;
				
				@Override
				public void handleEvent(final Event event) {
					// CHECK FOR NULL OR DISPOSED
					if(lastFocusIn == text) {
						if(event.widget != button) {
							VariableValueCellEditor.this.focusLost();
						}
					}
						
					lastFocusIn = event.widget;
				}
			};
		}
		
		return focusInFilter;
	}
	
    @Override
	protected void doSetFocus() {
    	if(text != null) {
    		text.setFocus();
    	}
    }
    
	@Override
    protected void updateContents(Object value) {
        if (text == null) {
			return;
		}

        final String newTxtValue = value == null ? "" : value.toString(); 
       	if(!newTxtValue.equals(text.getText())) {
       		text.setText(newTxtValue);
       	}
    }	

	public String getRawValue() {
		final Object value = doGetValue();
		return value == null ? "" : value.toString();
	}
	
	public void setErrorState(final VariablesView.ConstraintError constraintError) {
		final String tooltip;
		final Color backgroundColor;
		if(constraintError == null) {
			tooltip = null;
			backgroundColor = null;
		} else {
			tooltip = constraintError.errorMessage;
			backgroundColor = constraintErrorColor;
		}
		
		text.setToolTipText(tooltip);
		text.setBackground(backgroundColor);				
		
		if(button != null) {
			button.setEnabled(constraintError == null);
		}
	}
}