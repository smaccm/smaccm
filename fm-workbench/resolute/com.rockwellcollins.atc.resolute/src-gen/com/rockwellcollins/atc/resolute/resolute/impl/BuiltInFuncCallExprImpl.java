/**
 */
package com.rockwellcollins.atc.resolute.resolute.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import com.rockwellcollins.atc.resolute.resolute.BuiltInFuncCallExpr;
import com.rockwellcollins.atc.resolute.resolute.Expr;
import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Built In Func Call Expr</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link com.rockwellcollins.atc.resolute.resolute.impl.BuiltInFuncCallExprImpl#getFn
 * <em>Fn</em>}</li>
 * <li>
 * {@link com.rockwellcollins.atc.resolute.resolute.impl.BuiltInFuncCallExprImpl#getArgs
 * <em>Args</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class BuiltInFuncCallExprImpl extends ExprImpl implements BuiltInFuncCallExpr {
    /**
     * The default value of the '{@link #getFn() <em>Fn</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFn()
     * @generated
     * @ordered
     */
    protected static final String FN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFn() <em>Fn</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getFn()
     * @generated
     * @ordered
     */
    protected String fn = FN_EDEFAULT;

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
    protected BuiltInFuncCallExprImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ResolutePackage.Literals.BUILT_IN_FUNC_CALL_EXPR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getFn() {
        return fn;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setFn(String newFn) {
        String oldFn = fn;
        fn = newFn;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET,
                    ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__FN, oldFn, fn));
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
                    ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__ARGS);
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
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__ARGS:
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
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__FN:
            return getFn();
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__ARGS:
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
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__FN:
            setFn((String) newValue);
            return;
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__ARGS:
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
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__FN:
            setFn(FN_EDEFAULT);
            return;
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__ARGS:
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
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__FN:
            return FN_EDEFAULT == null ? fn != null : !FN_EDEFAULT.equals(fn);
        case ResolutePackage.BUILT_IN_FUNC_CALL_EXPR__ARGS:
            return args != null && !args.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (fn: ");
        result.append(fn);
        result.append(')');
        return result.toString();
    }

} // BuiltInFuncCallExprImpl
