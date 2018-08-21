package com.rockwellcollins.atc.agree;

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.ListValue;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.modelsupport.scoping.Aadl2GlobalScopeUtil;
import org.osate.xtext.aadl2.properties.util.PropertyUtils;

public class AgreeAADLPropertyUtils {

	public static String getPropertyEnumString(NamedElement namedEl, String property) {
		Property prop = Aadl2GlobalScopeUtil.get(namedEl, Aadl2Package.eINSTANCE.getProperty(),
				property);
		EnumerationLiteral lit = PropertyUtils.getEnumLiteral(namedEl, prop);
		return lit.getName();
	}

	public static List<PropertyExpression> getPropertyList(NamedElement namedEl, String property) {

		List<PropertyExpression> els = new ArrayList<>();
		Property prop = Aadl2GlobalScopeUtil.get(namedEl, Aadl2Package.eINSTANCE.getProperty(),
				property);
		ListValue listExpr = (ListValue) PropertyUtils.getSimplePropertyListValue(namedEl, prop);
		for (PropertyExpression propExpr : listExpr.getOwnedListElements()) {
			els.add(propExpr);
		}
		return els;
	}

}
