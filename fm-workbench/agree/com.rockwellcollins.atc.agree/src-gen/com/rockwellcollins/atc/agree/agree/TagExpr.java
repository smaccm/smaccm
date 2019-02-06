/**
 */
package com.rockwellcollins.atc.agree.agree;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag Expr</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.TagExpr#getStem <em>Stem</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.TagExpr#getTag <em>Tag</em>}</li>
 * </ul>
 *
 * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getTagExpr()
 * @model
 * @generated
 */
public interface TagExpr extends Expr
{
  /**
   * Returns the value of the '<em><b>Stem</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Stem</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Stem</em>' containment reference.
   * @see #setStem(Expr)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getTagExpr_Stem()
   * @model containment="true"
   * @generated
   */
  Expr getStem();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.TagExpr#getStem <em>Stem</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Stem</em>' containment reference.
   * @see #getStem()
   * @generated
   */
  void setStem(Expr value);

  /**
   * Returns the value of the '<em><b>Tag</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tag</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tag</em>' attribute.
   * @see #setTag(String)
   * @see com.rockwellcollins.atc.agree.agree.AgreePackage#getTagExpr_Tag()
   * @model
   * @generated
   */
  String getTag();

  /**
   * Sets the value of the '{@link com.rockwellcollins.atc.agree.agree.TagExpr#getTag <em>Tag</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tag</em>' attribute.
   * @see #getTag()
   * @generated
   */
  void setTag(String value);

} // TagExpr
