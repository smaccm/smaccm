/**
 */
package com.rockwellcollins.atc.resolute.resolute.util;

import com.rockwellcollins.atc.resolute.resolute.ResolutePackage;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ResoluteXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResoluteXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    ResolutePackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the ResoluteResourceFactoryImpl factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected Map<String, Resource.Factory> getRegistrations()
  {
    if (registrations == null)
    {
      super.getRegistrations();
      registrations.put(XML_EXTENSION, new ResoluteResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new ResoluteResourceFactoryImpl());
    }
    return registrations;
  }

} //ResoluteXMLProcessor
