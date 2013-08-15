/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Eq Statement</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link com.rockwellcollins.atc.agree.agree.EqStatement#getArgs <em>Args
 * </em>}</li>
 * <li>{@link com.rockwellcollins.atc.agree.agree.EqStatement#getExpr <em>Expr
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getEqStatement()
 * @model
 * @generated
 */
public interface EqStatement extends NamedElement, SpecStatement {
    /**
     * Returns the value of the '<em><b>Args</b></em>' containment reference
     * list. The list contents are of type
     * {@link com.rockwellcollins.atc.agree.agree.Arg}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Args</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Args</em>' containment reference list.
     * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getEqStatement_Args()
     * @model containment="true"
     * @generated
     */
    EList<Arg> getArgs();

    /**
     * Returns the value of the '<em><b>Expr</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Expr</em>' containment reference.
     * @see #setExpr(Expr)
     * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getEqStatement_Expr()
     * @model containment="true"
     * @generated
     */
    Expr getExpr();

    /**
     * Sets the value of the '
     * {@link com.rockwellcollins.atc.agree.agree.EqStatement#getExpr
     * <em>Expr</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Expr</em>' containment reference.
     * @see #getExpr()
     * @generated
     */
    void setExpr(Expr value);

} // EqStatement
