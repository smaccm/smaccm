/**
 */
package com.rockwellcollins.atc.agree.agree.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * <!-- begin-user-doc --> The <b>Resource Factory</b> associated with the
 * package. <!-- end-user-doc -->
 * 
 * @see com.rockwellcollins.atc.agree.agree.util.AgreeResourceImpl
 * @generated
 */
public class AgreeResourceFactoryImpl extends ResourceFactoryImpl {
    /**
     * Creates an instance of the resource factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public AgreeResourceFactoryImpl() {
        super();
    }

    /**
     * Creates an instance of the resource. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Resource createResource(URI uri) {
        XMLResource result = new AgreeResourceImpl(uri);
        result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
        result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

        result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);

        result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE,
                Boolean.TRUE);
        result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE,
                Boolean.TRUE);

        result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
        return result;
    }

} // AgreeResourceFactoryImpl
