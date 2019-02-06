package com.rockwellcollins.atc.agree.linking;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.linking.impl.IllegalNodeException;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.Element;
<<<<<<< HEAD
import org.osate.aadl2.NamedElement;
=======
import org.osate.aadl2.Namespace;
import org.osate.aadl2.PropertySet;
>>>>>>> origin/develop
import org.osate.aadl2.PropertyValue;
import org.osate.aadl2.RecordType;
import org.osate.aadl2.UnitLiteral;
import org.osate.aadl2.UnitsType;
import org.osate.aadl2.modelsupport.scoping.Aadl2GlobalScopeUtil;
<<<<<<< HEAD
import org.osate.aadl2.util.Aadl2Util;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.linking.PropertiesLinkingService;

import com.rockwellcollins.atc.agree.agree.AbstractionRef;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AssignStatement;
import com.rockwellcollins.atc.agree.agree.ConnectionStatement;
import com.rockwellcollins.atc.agree.agree.CustomType;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.NamedElmExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.OrderStatement;
import com.rockwellcollins.atc.agree.agree.ProjectionExpr;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SubcomponentRef;
=======
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.annexsupport.AnnexUtil;
import org.osate.xtext.aadl2.properties.linking.PropertiesLinkingService;

import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.ConnectionStatement;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FnDefExpr;
import com.rockwellcollins.atc.agree.agree.LibraryFnDefExpr;
import com.rockwellcollins.atc.agree.agree.LinearizationDefExpr;
import com.rockwellcollins.atc.agree.agree.NamedID;
import com.rockwellcollins.atc.agree.agree.NestedDotID;
import com.rockwellcollins.atc.agree.agree.NodeDefExpr;
import com.rockwellcollins.atc.agree.agree.NodeEq;
import com.rockwellcollins.atc.agree.agree.OrderStatement;
import com.rockwellcollins.atc.agree.agree.RecordDefExpr;
import com.rockwellcollins.atc.agree.agree.RecordExpr;
import com.rockwellcollins.atc.agree.agree.RecordType;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
>>>>>>> origin/develop
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.TagExpr;
import com.rockwellcollins.atc.agree.agree.ThisRef;
public class AgreeLinkingService extends PropertiesLinkingService {
	public AgreeLinkingService() {
		super();
	}

	public static String getTokenText(INode node) {
		if (node instanceof ILeafNode) {
			return ((ILeafNode) node).getText();
		} else {
			StringBuilder builder = new StringBuilder(Math.max(node.getTotalLength(), 1));
			boolean hiddenSeen = false;
			for (ILeafNode leaf : node.getLeafNodes()) {
				if (!leaf.isHidden()) {
					if (hiddenSeen && builder.length() > 0) {
						builder.append(' ');
					}
					builder.append(leaf.getText());
					hiddenSeen = false;
				} else {
					hiddenSeen = true;
				}
			}
			return builder.toString();
		}
	}

	@Override
	public List<EObject> getLinkedObjects(EObject context, EReference reference, INode node)
			throws IllegalNodeException {

		String name = getCrossRefNodeAsString(node);

		if (context instanceof PropertyValue) {

			return findUnitLiteralAsList((Element) context, name);
		}


<<<<<<< HEAD
		if (context instanceof ThisRef || context instanceof SubcomponentRef
				|| context instanceof LiftStatement
				|| context instanceof TagExpr
				|| context instanceof ProjectionExpr
				|| context instanceof NamedElmExpr
				|| context instanceof AssignStatement
				|| context instanceof CustomType || context instanceof AbstractionRef
				|| context instanceof NodeEq || context instanceof SynchStatement
				|| context instanceof RecordLitExpr || context instanceof RecordType || context instanceof GetPropertyExpr
				|| context instanceof RecordUpdateExpr || context instanceof EventExpr
				|| context instanceof OrderStatement || context instanceof ConnectionStatement) {

=======
		if (context instanceof DoubleDotRef || context instanceof NestedDotID
				|| context instanceof NodeEq
				|| context instanceof SynchStatement
				|| context instanceof RecordExpr || context instanceof RecordType || context instanceof Expr
				|| context instanceof RecordUpdateExpr || context instanceof EventExpr
				|| context instanceof OrderStatement || context instanceof ConnectionStatement) {


>>>>>>> origin/develop
			EObject e = getIndexedObject(context, reference, name);
			if (e == null) {
				e = findClassifier(context, reference, name);
			}
			if (e == null) {
				e = defObjectFromQualifiedType(context, reference, name);
			}

			if (e == null) {
				e = findClassifier(context, reference, name);
			}

			if (e == null) {
				e = getElm(context, reference, name);
			}

			if (e == null) {
				e = findPropertySetElement(context, reference, name);
			}

			if (e != null) {
				return Collections.singletonList(e);
			}

<<<<<<< HEAD
			// This code will only link to objects in the projects visible from the current project
			Iterable<IEObjectDescription> allObjectTypes = Aadl2GlobalScopeUtil.getAllEObjectDescriptions(context,
					reference.getEReferenceType());

			String contextProject = context.eResource().getURI().segment(1);
			List<String> visibleProjects = getVisibleProjects(contextProject);

			for (IEObjectDescription eod : allObjectTypes) {

				if (isVisible(eod, visibleProjects)) {

					EObject res = eod.getEObjectOrProxy();

					res = EcoreUtil.resolve(res, context.eResource().getResourceSet());

					if (sameName(eod, name)) {
						if (!Aadl2Util.isNull(res)) {
							return Collections.singletonList(res);
						}
					}
				}
			}
=======
>>>>>>> origin/develop
		}

		return super.getLinkedObjects(context, reference, node);
	}

<<<<<<< HEAD
	private EObject defObjectFromQualifiedType(EObject context, EReference reference, String name) {
		String[] segments = name.split("\\.");
		if (segments.length == 2) {
			String pkgName = segments[0];
			String localName = segments[1];
			EObject eo = getIndexedObject(context, reference, pkgName);
			if (eo instanceof AadlPackage) {
				for (AnnexLibrary annex : AnnexUtil.getAllActualAnnexLibraries(((AadlPackage) eo),
						AgreePackage.eINSTANCE.getAgreeContractLibrary())) {
					AgreeContract contract = (AgreeContract) ((AgreeContractLibrary) annex).getContract();
					for (SpecStatement spec : contract.getSpecs()) {
						if (spec instanceof NamedElement) {
							if (((NamedElement) spec).getName().equals(localName)) {
								return spec;
							}
						}
					}
				}
			}
		}
		return null;
	}

	private static boolean sameName(IEObjectDescription eod, String name) {
		return eod.getName().toString().equalsIgnoreCase(name.replace("::", "."));
	}
=======
>>>>>>> origin/develop

	private Element getElm(EObject context, EReference reference, String name) {
		String[] segments = name.split("::");

		if (segments.length >= 2) {
			String pkgName = String.join("::", Arrays.copyOf(segments, segments.length - 1));

			String statementName = segments[segments.length - 1];

			Namespace namespace = AadlUtil.getContainingTopLevelNamespace(context);

			PropertySet propSet = AadlUtil.findImportedPropertySet(pkgName, namespace);

			if (propSet != null) {
				Element elm = propSet.findNamedElement(statementName);
				return elm;
			}

			AadlPackage aadlPackage = AadlUtil.findImportedPackage(pkgName, namespace);


			for (AnnexLibrary annex : AnnexUtil.getAllActualAnnexLibraries(aadlPackage,
					AgreePackage.eINSTANCE.getAgreeContractLibrary())) {

				AgreeContract contract = (AgreeContract) ((AgreeContractLibrary) annex).getContract();
				for (SpecStatement spec : contract.getSpecs()) {
					if (spec instanceof RecordDefExpr) {
						if (((RecordDefExpr) spec).getName().equals(statementName)) {
							return (spec);
						}

					} else if (spec instanceof FnDefExpr) {
						if (((FnDefExpr) spec).getName().equals(statementName)) {
							return (spec);
						}

					} else if (spec instanceof LibraryFnDefExpr) {
						if (((LibraryFnDefExpr) spec).getName().equals(statementName)) {
							return (spec);
						}

					} else if (spec instanceof NodeDefExpr) {
						if (((NodeDefExpr) spec).getName().equals(statementName)) {
							return (spec);
						}

					} else if (spec instanceof LinearizationDefExpr) {
						if (((LinearizationDefExpr) spec).getName().equals(statementName)) {
							return (spec);
						}

					} else if (spec instanceof ConstStatement) {
						if (((ConstStatement) spec).getName().equals(statementName)) {
							return (spec);
						}

					} else if (spec instanceof EnumStatement) {
						if (((EnumStatement) spec).getName().equals(statementName)) {
							return (spec);
						}

						EList<NamedID> enums = ((EnumStatement) spec).getEnums();
						for (NamedID nid : enums) {
							if (nid.getName().contentEquals(statementName)) {
								return nid;
							}
						}

					}
				}

			}
		}



		return null;
	}

	private static List<EObject> findUnitLiteralAsList(Element context, String name) {
		EObject e = findUnitLiteral(context, name);
		if (e == null) {
			return Collections.<EObject> emptyList();
		}
		return Collections.singletonList(e);
	}

	final private static EClass UNITS_TYPE = Aadl2Package.eINSTANCE.getUnitsType();

	private static UnitLiteral findUnitLiteral(Element context, String name) {
		// TODO: Scope literals by type, but how to do we know the type of an
		// expression?
		for (IEObjectDescription desc : Aadl2GlobalScopeUtil.getAllEObjectDescriptions(context, UNITS_TYPE)) {
			UnitsType unitsType = (UnitsType) EcoreUtil.resolve(desc.getEObjectOrProxy(), context);
			UnitLiteral literal = unitsType.findLiteral(name);
			if (literal != null) {
				return literal;
			}
		}

		return null;
	}
}
