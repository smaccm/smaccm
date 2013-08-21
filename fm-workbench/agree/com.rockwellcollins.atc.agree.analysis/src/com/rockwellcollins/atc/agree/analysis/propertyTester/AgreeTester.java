package com.rockwellcollins.atc.agree.analysis.propertyTester;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.osate.aadl2.Aadl2Factory;

public class AgreeTester extends PropertyTester {
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        System.out.println("Testing: " + property);
        if (property.equals("isComponentImplementation")) {
            return isComponentImplementation(receiver);
        }

        return false;
    }

    private static final EClass ComponentImplementationEClass = Aadl2Factory.eINSTANCE
            .getAadl2Package().getComponentImplementation();
    
    private boolean isComponentImplementation(Object receiver) {
        if (receiver instanceof IStructuredSelection) {
            IStructuredSelection iss = (IStructuredSelection) receiver;
            if (iss.size() == 1) {
                if (iss.getFirstElement() instanceof EObjectNode) {
                    EObjectNode node = (EObjectNode) iss.getFirstElement();
                    return ComponentImplementationEClass.isSuperTypeOf(node.getEClass());
                }
            }
        }

        return false;
    }
}
