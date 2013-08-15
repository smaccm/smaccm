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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.CallDef;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FnCallExpr;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Fn Call Expr</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link com.rockwellcollins.atc.agree.agree.impl.FnCallExprImpl#getFn <em>
 * Fn</em>}</li>
 * <li>{@link com.rockwellcollins.atc.agree.agree.impl.FnCallExprImpl#getArgs
 * <em>Args</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class FnCallExprImpl extends ExprImpl implements FnCallExpr {
    /**
     * The cached value of the '{@link #getFn() <em>Fn</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFn()
     * @generated
     * @ordered
     */
    protected CallDef fn;

    /**
     * The cached value of the '{@link #getArgs() <em>Args</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getArgs()
     * @generated
     * @ordered
     */
    protected EList<Expr> args;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected FnCallExprImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return AgreePackage.Literals.FN_CALL_EXPR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public CallDef getFn() {
        if (fn != null && fn.eIsProxy()) {
            InternalEObject oldFn = (InternalEObject) fn;
            fn = (CallDef) eResolveProxy(oldFn);
            if (fn != oldFn) {
                if (eNotificationRequired()) {
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            AgreePackage.FN_CALL_EXPR__FN, oldFn, fn));
                }
            }
        }
        return fn;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CallDef basicGetFn() {
        return fn;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFn(CallDef newFn) {
        CallDef oldFn = fn;
        fn = newFn;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.FN_CALL_EXPR__FN,
                    oldFn, fn));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<Expr> getArgs() {
        if (args == null) {
            args = new EObjectContainmentEList<Expr>(Expr.class, this,
                    AgreePackage.FN_CALL_EXPR__ARGS);
        }
        return args;
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
        case AgreePackage.FN_CALL_EXPR__ARGS:
            return ((InternalEList<?>) getArgs()).basicRemove(otherEnd, msgs);
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
        case AgreePackage.FN_CALL_EXPR__FN:
            if (resolve) {
                return getFn();
            }
            return basicGetFn();
        case AgreePackage.FN_CALL_EXPR__ARGS:
            return getArgs();
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
        case AgreePackage.FN_CALL_EXPR__FN:
            setFn((CallDef) newValue);
            return;
        case AgreePackage.FN_CALL_EXPR__ARGS:
            getArgs().clear();
            getArgs().addAll((Collection<? extends Expr>) newValue);
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
        case AgreePackage.FN_CALL_EXPR__FN:
            setFn((CallDef) null);
            return;
        case AgreePackage.FN_CALL_EXPR__ARGS:
            getArgs().clear();
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
        case AgreePackage.FN_CALL_EXPR__FN:
            return fn != null;
        case AgreePackage.FN_CALL_EXPR__ARGS:
            return args != null && !args.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // FnCallExprImpl
