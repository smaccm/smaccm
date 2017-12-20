/**
 */
package com.rockwellcollins.atc.agree.agree;

import org.eclipse.emf.common.util.EList;
import org.osate.aadl2.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Linearization Def Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getArgs <em>Args</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getIntervals <em>Intervals</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getPrecision <em>Precision</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getExprBody <em>Expr Body</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLinearizationDefExpr()
 * @model
 * @generated
 */
public interface LinearizationDefExpr extends NamedElement, SpecStatement, CallDef {
	/**
	 * Returns the value of the '<em><b>Args</b></em>' containment reference list.
	 * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.Arg}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Args</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Args</em>' containment reference list.
	 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLinearizationDefExpr_Args()
	 * @model containment="true"
	 * @generated
	 */
	EList<Arg> getArgs();

	/**
	 * Returns the value of the '<em><b>Intervals</b></em>' containment reference list.
	 * The list contents are of type {@link com.rockwellcollins.atc.agree.agree.LinearizationInterval}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intervals</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intervals</em>' containment reference list.
	 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLinearizationDefExpr_Intervals()
	 * @model containment="true"
	 * @generated
	 */
	EList<LinearizationInterval> getIntervals();

	/**
	 * Returns the value of the '<em><b>Precision</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precision</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precision</em>' containment reference.
	 * @see #setPrecision(Expr)
	 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLinearizationDefExpr_Precision()
	 * @model containment="true"
	 * @generated
	 */
	Expr getPrecision();

	/**
	 * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getPrecision <em>Precision</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precision</em>' containment reference.
	 * @see #getPrecision()
	 * @generated
	 */
	void setPrecision(Expr value);

	/**
	 * Returns the value of the '<em><b>Expr Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expr Body</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expr Body</em>' containment reference.
	 * @see #setExprBody(Expr)
	 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getLinearizationDefExpr_ExprBody()
	 * @model containment="true"
	 * @generated
	 */
	Expr getExprBody();

	/**
	 * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.LinearizationDefExpr#getExprBody <em>Expr Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expr Body</em>' containment reference.
	 * @see #getExprBody()
	 * @generated
	 */
	void setExprBody(Expr value);

} // LinearizationDefExpr
