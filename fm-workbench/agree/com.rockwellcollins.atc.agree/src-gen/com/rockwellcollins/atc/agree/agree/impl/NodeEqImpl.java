/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.osate.aadl2.impl.ElementImpl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.NodeEq;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Node Eq</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link com.rockwellcollins.atc.agree.agree.impl.NodeEqImpl#getNames <em>
 * Names</em>}</li>
 * <li>{@link com.rockwellcollins.atc.agree.agree.impl.NodeEqImpl#getExpr <em>
 * Expr</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class NodeEqImpl extends ElementImpl implements NodeEq {
    /**
     * The cached value of the '{@link #getNames() <em>Names</em>}' reference
     * list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getNames()
     * @generated
     * @ordered
     */
    protected EList<Arg> names;

    /**
     * The cached value of the '{@link #getExpr() <em>Expr</em>}' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getExpr()
     * @generated
     * @ordered
     */
    protected Expr expr;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected NodeEqImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return AgreePackage.Literals.NODE_EQ;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Arg> getNames() {
        if (names == null) {
            names = new EObjectResolvingEList<Arg>(Arg.class, this, AgreePackage.NODE_EQ__NAMES);
        }
        return names;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Expr getExpr() {
        return expr;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetExpr(Expr newExpr, NotificationChain msgs) {
        Expr oldExpr = expr;
        expr = newExpr;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    AgreePackage.NODE_EQ__EXPR, oldExpr, newExpr);
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
    public void setExpr(Expr newExpr) {
        if (newExpr != expr) {
            NotificationChain msgs = null;
            if (expr != null) {
                msgs = ((InternalEObject) expr).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - AgreePackage.NODE_EQ__EXPR, null, msgs);
            }
            if (newExpr != null) {
                msgs = ((InternalEObject) newExpr).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - AgreePackage.NODE_EQ__EXPR, null, msgs);
            }
            msgs = basicSetExpr(newExpr, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.NODE_EQ__EXPR,
                    newExpr, newExpr));
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
        case AgreePackage.NODE_EQ__EXPR:
            return basicSetExpr(null, msgs);
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
        case AgreePackage.NODE_EQ__NAMES:
            return getNames();
        case AgreePackage.NODE_EQ__EXPR:
            return getExpr();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case AgreePackage.NODE_EQ__NAMES:
            getNames().clear();
            getNames().addAll((Collection<? extends Arg>) newValue);
            return;
        case AgreePackage.NODE_EQ__EXPR:
            setExpr((Expr) newValue);
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
        case AgreePackage.NODE_EQ__NAMES:
            getNames().clear();
            return;
        case AgreePackage.NODE_EQ__EXPR:
            setExpr((Expr) null);
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
        case AgreePackage.NODE_EQ__NAMES:
            return names != null && !names.isEmpty();
        case AgreePackage.NODE_EQ__EXPR:
            return expr != null;
        }
        return super.eIsSet(featureID);
    }

} // NodeEqImpl
