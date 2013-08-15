/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Get Property Expr</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl#getComponent
 * <em>Component</em>}</li>
 * <li>
 * {@link com.rockwellcollins.atc.agree.agree.impl.GetPropertyExprImpl#getName
 * <em>Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GetPropertyExprImpl extends ExprImpl implements GetPropertyExpr {
    /**
     * The cached value of the '{@link #getComponent() <em>Component</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getComponent()
     * @generated
     * @ordered
     */
    protected Expr component;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected Expr name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected GetPropertyExprImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return AgreePackage.Literals.GET_PROPERTY_EXPR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Expr getComponent() {
        return component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetComponent(Expr newComponent, NotificationChain msgs) {
        Expr oldComponent = component;
        component = newComponent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    AgreePackage.GET_PROPERTY_EXPR__COMPONENT, oldComponent, newComponent);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setComponent(Expr newComponent) {
        if (newComponent != component) {
            NotificationChain msgs = null;
            if (component != null) {
                msgs = ((InternalEObject) component).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - AgreePackage.GET_PROPERTY_EXPR__COMPONENT, null, msgs);
            }
            if (newComponent != null) {
                msgs = ((InternalEObject) newComponent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - AgreePackage.GET_PROPERTY_EXPR__COMPONENT, null, msgs);
            }
            msgs = basicSetComponent(newComponent, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET,
                    AgreePackage.GET_PROPERTY_EXPR__COMPONENT, newComponent, newComponent));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Expr getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetName(Expr newName, NotificationChain msgs) {
        Expr oldName = name;
        name = newName;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    AgreePackage.GET_PROPERTY_EXPR__NAME, oldName, newName);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setName(Expr newName) {
        if (newName != name) {
            NotificationChain msgs = null;
            if (name != null) {
                msgs = ((InternalEObject) name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - AgreePackage.GET_PROPERTY_EXPR__NAME, null, msgs);
            }
            if (newName != null) {
                msgs = ((InternalEObject) newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - AgreePackage.GET_PROPERTY_EXPR__NAME, null, msgs);
            }
            msgs = basicSetName(newName, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET,
                    AgreePackage.GET_PROPERTY_EXPR__NAME, newName, newName));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
            NotificationChain msgs) {
        switch (featureID) {
        case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
            return basicSetComponent(null, msgs);
        case AgreePackage.GET_PROPERTY_EXPR__NAME:
            return basicSetName(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
            return getComponent();
        case AgreePackage.GET_PROPERTY_EXPR__NAME:
            return getName();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
            setComponent((Expr) newValue);
            return;
        case AgreePackage.GET_PROPERTY_EXPR__NAME:
            setName((Expr) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
            setComponent((Expr) null);
            return;
        case AgreePackage.GET_PROPERTY_EXPR__NAME:
            setName((Expr) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case AgreePackage.GET_PROPERTY_EXPR__COMPONENT:
            return component != null;
        case AgreePackage.GET_PROPERTY_EXPR__NAME:
            return name != null;
        }
        return super.eIsSet(featureID);
    }

} // GetPropertyExprImpl
