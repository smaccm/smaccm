/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fold Right Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getBinding <em>Binding</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getArray <em>Array</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getAccumulator <em>Accumulator</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getInitial <em>Initial</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getExpr <em>Expr</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFoldRightExpr()
 * @model
 * @generated
 */
public interface FoldRightExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Binding</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Binding</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Binding</em>' containment reference.
   * @see #setBinding(NamedID)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFoldRightExpr_Binding()
   * @model containment="true"
   * @generated
   */
  NamedID getBinding();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getBinding <em>Binding</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Binding</em>' containment reference.
   * @see #getBinding()
   * @generated
   */
  void setBinding(NamedID value);

  /**
   * Returns the value of the '<em><b>Array</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array</em>' containment reference.
   * @see #setArray(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFoldRightExpr_Array()
   * @model containment="true"
   * @generated
   */
  Expr getArray();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getArray <em>Array</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array</em>' containment reference.
   * @see #getArray()
   * @generated
   */
  void setArray(Expr value);

  /**
   * Returns the value of the '<em><b>Accumulator</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Accumulator</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Accumulator</em>' containment reference.
   * @see #setAccumulator(NamedID)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFoldRightExpr_Accumulator()
   * @model containment="true"
   * @generated
   */
  NamedID getAccumulator();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getAccumulator <em>Accumulator</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Accumulator</em>' containment reference.
   * @see #getAccumulator()
   * @generated
   */
  void setAccumulator(NamedID value);

  /**
   * Returns the value of the '<em><b>Initial</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Initial</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Initial</em>' containment reference.
   * @see #setInitial(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFoldRightExpr_Initial()
   * @model containment="true"
   * @generated
   */
  Expr getInitial();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getInitial <em>Initial</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Initial</em>' containment reference.
   * @see #getInitial()
   * @generated
   */
  void setInitial(Expr value);

  /**
   * Returns the value of the '<em><b>Expr</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expr</em>' containment reference.
   * @see #setExpr(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getFoldRightExpr_Expr()
   * @model containment="true"
   * @generated
   */
  Expr getExpr();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.FoldRightExpr#getExpr <em>Expr</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expr</em>' containment reference.
   * @see #getExpr()
   * @generated
   */
  void setExpr(Expr value);

} // FoldRightExpr
