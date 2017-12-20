package com.rockwellcollins.atc.agree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.ListValue;
import org.osate.aadl2.ModalPropertyValue;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyExpression;

public class AgreeAADLEnumerationUtils {

//	private static AgreeContractLibrary getAnnexLibrary(PublicPackageSection publicSection) {
//		AgreePackage agreePackage = AgreeFactory.eINSTANCE.getAgreePackage();
//		AgreeContractLibrary result = (AgreeContractLibrary) publicSection
//				.createOwnedAnnexLibrary(agreePackage.getAgreeContractLibrary());
//		result.setContract(AgreeFactory.eINSTANCE.createAgreeContract());
//		return result;
//	}
//
//	private static AgreeContract getAgreeContract() {
//		AadlPackage aadlPackage = EMFIndexRetrieval.getPackageInWorkspace("Base_Types",
//				OsateResourceUtil.getResourceSet());
//		PublicPackageSection publicSection = aadlPackage.getPublicSection();
//		List<AnnexLibrary> annexLibraries = publicSection.getOwnedAnnexLibraries().stream()
//				.filter(annex -> (annex instanceof AgreeContractLibrary)).collect(Collectors.toList());
//		AgreeContractLibrary agreeContractLibrary = annexLibraries.isEmpty() ? getAnnexLibrary(publicSection)
//				: (AgreeContractLibrary) annexLibraries.get(0);
//		AgreeContract agreeContract = (AgreeContract) agreeContractLibrary.getContract();
//		return agreeContract;
//	}
//
//	private static EnumStatement getEnumStatement(ComponentClassifier enumerationType) {
//		String enumerationTypeName = enumerationType.getName();
//		AgreeContract agreeContract = getAgreeContract();
//		List<SpecStatement> matchingSpecs = agreeContract.getSpecs().stream()
//				.filter(spec -> (spec instanceof EnumStatement)
//						&& ((EnumStatement) spec).getName().equalsIgnoreCase(enumerationTypeName))
//				.collect(Collectors.toList());
//		if (matchingSpecs.isEmpty()) {
//			EnumStatement enumStmt = AgreeFactory.eINSTANCE.createEnumStatement();
//			enumStmt.setName(enumerationTypeName);
//			for (PropertyExpression enumerator : getEnumerators(enumerationType)) {
//				if (enumerator instanceof StringLiteral) {
//					NamedID namedID = AgreeFactory.eINSTANCE.createNamedID();
//					namedID.setName(((StringLiteral) enumerator).getValue());
//					enumStmt.getEnums().add(namedID);
//				}
//			}
//			return enumStmt;
//		} else {
//			return (EnumStatement) matchingSpecs.get(0);
//		}
//	}

	public static boolean isAADLEnumeration(EObject eobj) {
		// TODO: There's a better way to do this...
		// Once "Enumeration_Type" has been canonicalized into Base_Types,
		// we can merely look for that as an ancestor of the dataClass.
		return (eobj instanceof ComponentClassifier) && ((ComponentClassifier) eobj).getAllPropertyAssociations()
				.stream()
				.anyMatch(pa -> "Data_Representation".equals(pa.getProperty().getName()) && pa.getOwnedValues().stream()
						.anyMatch(pv -> (pv.getOwnedValue() instanceof NamedValue)
								&& (((NamedValue) pv.getOwnedValue())
										.getNamedValue() instanceof org.osate.aadl2.EnumerationLiteral)
								&& "Enum"
										.equals(((EnumerationLiteral) ((NamedValue) pv.getOwnedValue()).getNamedValue())
												.getName())));
	}

//	public static List<NamedID> getMatchingEnumIDAsList(ComponentClassifier enumerationType, String qualifiedName) {
//		List<NamedID> result = new ArrayList<>();
//		EnumStatement enumStatement = getEnumStatement(enumerationType);
//		for (NamedID enumerator : enumStatement.getEnums()) {
//			String qualifiedEnumeratorName = enumerationType.getQualifiedName().replace("::", ".") + "."
//					+ enumerator.getName();
//			if (qualifiedEnumeratorName.equalsIgnoreCase(qualifiedName)) {
//				result.add(enumerator);
//			}
//		}
//		return result;
//	}

	public static Property getEnumeratorProperty(ComponentClassifier componentClassifier) {
		Property result = null;
		if (isAADLEnumeration(componentClassifier)) {
			for (PropertyAssociation propertyAssociation : componentClassifier.getOwnedPropertyAssociations().stream()
					.filter(pa -> "Enumerators".equalsIgnoreCase(pa.getProperty().getName()))
					.collect(Collectors.toList())) {
				result = propertyAssociation.getProperty();
			}
		}
		return result;
	}

	public static List<PropertyExpression> getEnumerators(ComponentClassifier componentClassifier) {
		List<PropertyExpression> result = new ArrayList<>();
		if (isAADLEnumeration(componentClassifier)) {
			for (PropertyAssociation propertyAssociation : componentClassifier.getOwnedPropertyAssociations().stream()
					.filter(pa -> "Enumerators".equalsIgnoreCase(pa.getProperty().getName()))
					.collect(Collectors.toList())) {
				for (ModalPropertyValue propertyValue : propertyAssociation.getOwnedValues()) {
					PropertyExpression propertyExpression = propertyValue.getOwnedValue();
					if (propertyExpression instanceof ListValue) {
						for (PropertyExpression listElement : ((ListValue) propertyExpression).getOwnedListElements()) {
							result.add(listElement);
						}
					}
				}
			}
		}
		return result;
	}

//	public static List<EObject> getMatchingEnumeratorAsList(ComponentClassifier enumerationType, String qualifiedName) {
//		List<EObject> result = new ArrayList<>();
//		if (isAADLEnumeration(enumerationType)) {
//			for (PropertyAssociation propertyAssociation : enumerationType.getOwnedPropertyAssociations().stream()
//					.filter(pa -> "Enumerators".equalsIgnoreCase(pa.getProperty().getName()))
//					.collect(Collectors.toList())) {
//				for (ModalPropertyValue propertyValue : propertyAssociation.getOwnedValues()) {
//					PropertyExpression propertyExpression = propertyValue.getOwnedValue();
//					if (propertyExpression instanceof ListValue) {
//						for (PropertyExpression listElement : ((ListValue) propertyExpression).getOwnedListElements()) {
//							if (listElement instanceof StringLiteral) {
//								String extendedName = enumerationType.getQualifiedName().replace("::", ".") + "."
//										+ ((StringLiteral) listElement).getValue();
//								if (qualifiedName.equalsIgnoreCase(extendedName)) {
//									com.rockwellcollins.atc.agree.agree.NamedID namedID = com.rockwellcollins.atc.agree.agree.AgreeFactory.eINSTANCE
//											.createNamedID();
//									namedID.setName(((StringLiteral) listElement).getValue());
//
////									EnumerationLiteral enumLiteral = org.osate.aadl2.Aadl2Factory.eINSTANCE
////											.createEnumerationLiteral();
////									enumLiteral.setName(((StringLiteral) listElement).getValue());
////									enumLiteral.setPropertyValue(propertyAssociation.getProperty(),
////											((ListValue) propertyExpression).getOwnedListElements());
//									result.add(namedID);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return result;

//		for (PropertyExpression enumerator : getEnumerators(enumerationType)) {
//			// Enumerator values are (unfortunately) only AADL string literals
//			if (enumerator instanceof StringLiteral) {
//				String extendedName = enumerationType.getQualifiedName().replace("::", ".") + "."
//						+ ((StringLiteral) enumerator).getValue();
//				if (qualifiedName.equalsIgnoreCase(extendedName)) {
//					EnumerationLiteral enumLiteral = org.osate.aadl2.Aadl2Factory.eINSTANCE.createEnumerationLiteral();
//					enumLiteral.setName(((StringLiteral) enumerator).getValue());
//					enumLiteral.setPropertyValue(getEnumeratorProperty(enumerationType), pv);
//					result.add(enumLiteral);
//				}
//			}
//		}
//		return result;
//	}

}
