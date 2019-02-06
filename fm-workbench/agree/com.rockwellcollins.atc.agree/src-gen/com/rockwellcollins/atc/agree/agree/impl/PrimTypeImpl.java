/**
 */
package com.rockwellcollins.atc.agree.agree.impl;

import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.PrimType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Prim Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl#getLowNeg <em>Low Neg</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl#getRangeLow <em>Range Low</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl#getHighNeg <em>High Neg</em>}</li>
 *   <li>{@link com.rockwellcollins.atc.agree.agree.impl.PrimTypeImpl#getRangeHigh <em>Range High</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PrimTypeImpl extends TypeImpl implements PrimType
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getLowNeg() <em>Low Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLowNeg()
   * @generated
   * @ordered
   */
  protected static final String LOW_NEG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLowNeg() <em>Low Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLowNeg()
   * @generated
   * @ordered
   */
  protected String lowNeg = LOW_NEG_EDEFAULT;

  /**
   * The default value of the '{@link #getRangeLow() <em>Range Low</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeLow()
   * @generated
   * @ordered
   */
  protected static final String RANGE_LOW_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRangeLow() <em>Range Low</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeLow()
   * @generated
   * @ordered
   */
  protected String rangeLow = RANGE_LOW_EDEFAULT;

  /**
   * The default value of the '{@link #getHighNeg() <em>High Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHighNeg()
   * @generated
   * @ordered
   */
  protected static final String HIGH_NEG_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getHighNeg() <em>High Neg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHighNeg()
   * @generated
   * @ordered
   */
  protected String highNeg = HIGH_NEG_EDEFAULT;

  /**
   * The default value of the '{@link #getRangeHigh() <em>Range High</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeHigh()
   * @generated
   * @ordered
   */
  protected static final String RANGE_HIGH_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRangeHigh() <em>Range High</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRangeHigh()
   * @generated
   * @ordered
   */
  protected String rangeHigh = RANGE_HIGH_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PrimTypeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return AgreePackage.Literals.PRIM_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.PRIM_TYPE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLowNeg()
  {
    return lowNeg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLowNeg(String newLowNeg)
  {
    String oldLowNeg = lowNeg;
    lowNeg = newLowNeg;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.PRIM_TYPE__LOW_NEG, oldLowNeg, lowNeg));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRangeLow()
  {
    return rangeLow;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRangeLow(String newRangeLow)
  {
    String oldRangeLow = rangeLow;
    rangeLow = newRangeLow;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.PRIM_TYPE__RANGE_LOW, oldRangeLow, rangeLow));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getHighNeg()
  {
    return highNeg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHighNeg(String newHighNeg)
  {
    String oldHighNeg = highNeg;
    highNeg = newHighNeg;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.PRIM_TYPE__HIGH_NEG, oldHighNeg, highNeg));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRangeHigh()
  {
    return rangeHigh;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRangeHigh(String newRangeHigh)
  {
    String oldRangeHigh = rangeHigh;
    rangeHigh = newRangeHigh;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, AgreePackage.PRIM_TYPE__RANGE_HIGH, oldRangeHigh, rangeHigh));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case AgreePackage.PRIM_TYPE__NAME:
        return getName();
      case AgreePackage.PRIM_TYPE__LOW_NEG:
        return getLowNeg();
      case AgreePackage.PRIM_TYPE__RANGE_LOW:
        return getRangeLow();
      case AgreePackage.PRIM_TYPE__HIGH_NEG:
        return getHighNeg();
      case AgreePackage.PRIM_TYPE__RANGE_HIGH:
        return getRangeHigh();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case AgreePackage.PRIM_TYPE__NAME:
        setName((String)newValue);
        return;
      case AgreePackage.PRIM_TYPE__LOW_NEG:
        setLowNeg((String)newValue);
        return;
      case AgreePackage.PRIM_TYPE__RANGE_LOW:
        setRangeLow((String)newValue);
        return;
      case AgreePackage.PRIM_TYPE__HIGH_NEG:
        setHighNeg((String)newValue);
        return;
      case AgreePackage.PRIM_TYPE__RANGE_HIGH:
        setRangeHigh((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case AgreePackage.PRIM_TYPE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case AgreePackage.PRIM_TYPE__LOW_NEG:
        setLowNeg(LOW_NEG_EDEFAULT);
        return;
      case AgreePackage.PRIM_TYPE__RANGE_LOW:
        setRangeLow(RANGE_LOW_EDEFAULT);
        return;
      case AgreePackage.PRIM_TYPE__HIGH_NEG:
        setHighNeg(HIGH_NEG_EDEFAULT);
        return;
      case AgreePackage.PRIM_TYPE__RANGE_HIGH:
        setRangeHigh(RANGE_HIGH_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case AgreePackage.PRIM_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case AgreePackage.PRIM_TYPE__LOW_NEG:
        return LOW_NEG_EDEFAULT == null ? lowNeg != null : !LOW_NEG_EDEFAULT.equals(lowNeg);
      case AgreePackage.PRIM_TYPE__RANGE_LOW:
        return RANGE_LOW_EDEFAULT == null ? rangeLow != null : !RANGE_LOW_EDEFAULT.equals(rangeLow);
      case AgreePackage.PRIM_TYPE__HIGH_NEG:
        return HIGH_NEG_EDEFAULT == null ? highNeg != null : !HIGH_NEG_EDEFAULT.equals(highNeg);
      case AgreePackage.PRIM_TYPE__RANGE_HIGH:
        return RANGE_HIGH_EDEFAULT == null ? rangeHigh != null : !RANGE_HIGH_EDEFAULT.equals(rangeHigh);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", lowNeg: ");
    result.append(lowNeg);
    result.append(", rangeLow: ");
    result.append(rangeLow);
    result.append(", highNeg: ");
    result.append(highNeg);
    result.append(", rangeHigh: ");
    result.append(rangeHigh);
    result.append(')');
    return result.toString();
  }

} //PrimTypeImpl
