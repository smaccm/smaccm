/*
 * generated by Xtext
 */
package com.rockwellcollins.atc.agree.scoping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.Scopes;
import org.eclipse.xtext.scoping.impl.FilteringScope;
import org.osate.aadl2.Aadl2Package;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AnnexLibrary;
import org.osate.aadl2.AnnexSubclause;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ComponentClassifier;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataImplementation;
import org.osate.aadl2.DataType;
import org.osate.aadl2.Element;
import org.osate.aadl2.Feature;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertySet;
import org.osate.aadl2.PublicPackageSection;
import org.osate.aadl2.Subcomponent;
import org.osate.annexsupport.AnnexUtil;

import com.rockwellcollins.atc.agree.AgreeTypeSystem;
import com.rockwellcollins.atc.agree.agree.AgreeContract;
import com.rockwellcollins.atc.agree.agree.AgreeContractLibrary;
import com.rockwellcollins.atc.agree.agree.AgreeContractSubclause;
import com.rockwellcollins.atc.agree.agree.AgreePackage;
import com.rockwellcollins.atc.agree.agree.AssignStatement;
import com.rockwellcollins.atc.agree.agree.ComponentRef;
import com.rockwellcollins.atc.agree.agree.ConnectionStatement;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.EqStatement;
import com.rockwellcollins.atc.agree.agree.ExistsExpr;
import com.rockwellcollins.atc.agree.agree.FnDef;
import com.rockwellcollins.atc.agree.agree.FoldLeftExpr;
import com.rockwellcollins.atc.agree.agree.FoldRightExpr;
import com.rockwellcollins.atc.agree.agree.ForallExpr;
import com.rockwellcollins.atc.agree.agree.ForeachExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.InputStatement;
import com.rockwellcollins.atc.agree.agree.LibraryFnDef;
import com.rockwellcollins.atc.agree.agree.LiftStatement;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.NodeDef;
import com.rockwellcollins.atc.agree.agree.OrderStatement;
import com.rockwellcollins.atc.agree.agree.ProjectionExpr;
import com.rockwellcollins.atc.agree.agree.RecordDef;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SpecStatement;
import com.rockwellcollins.atc.agree.agree.SynchStatement;
import com.rockwellcollins.atc.agree.agree.ThisRef;
import com.rockwellcollins.atc.agree.agree.Type;

/**
 * This class contains custom scoping description.
 *
 * see : http://www.eclipse.org/Xtext/documentation.html#scoping on how and when
 * to use it
 *
 */
public class AgreeScopeProvider extends org.osate.xtext.aadl2.properties.scoping.PropertiesScopeProvider {

	private Set<NamedElement> getNamedElementsFromSpecs(EList<SpecStatement> specs) {
		Set<NamedElement> nelms = new HashSet<>();
		for (SpecStatement spec : specs) {
			if (spec instanceof NamedElement) {
				nelms.add((NamedElement) spec);
			}

			if (spec instanceof EqStatement) {
				EqStatement eq = (EqStatement) spec;
				nelms.addAll(eq.getLhs());
			} else if (spec instanceof InputStatement) {
				nelms.addAll(((InputStatement) spec).getLhs());
			} else if (spec instanceof EnumStatement) {
				nelms.addAll(((EnumStatement) spec).getEnums());
			}
		}
		return nelms;
	}



	private Set<NamedElement> getNamedElements(EObject ctx) {

		Set<NamedElement> components = new HashSet<>();
		if (ctx instanceof AadlPackage) {
			PublicPackageSection pubSec = ((AadlPackage) ctx).getPublicSection();
			for (Element el : pubSec.getOwnedElements()) {
				if (el instanceof DataImplementation || el instanceof DataType) {
					components.add((NamedElement) el);
				}
			}

			for (AnnexLibrary annex : AnnexUtil.getAllActualAnnexLibraries(((AadlPackage) ctx),
					AgreePackage.eINSTANCE.getAgreeContractLibrary())) {

				AgreeContract contract = (AgreeContract) ((AgreeContractLibrary) annex).getContract();
				components.addAll(getNamedElementsFromSpecs(contract.getSpecs()));

			}

			components.add((AadlPackage) ctx);

		} else {

			for (AnnexSubclause annex : AnnexUtil.getAllAnnexSubclauses((Classifier) ctx,
					AgreePackage.eINSTANCE.getAgreeContractSubclause())) {
				AgreeContract contract = (AgreeContract) ((AgreeContractSubclause) annex).getContract();
				components.addAll(getNamedElementsFromSpecs(contract.getSpecs()));

			}

			if (ctx instanceof ComponentImplementation) {
				components.addAll(((ComponentImplementation) ctx).getAllSubcomponents());
				components.addAll(((ComponentImplementation) ctx).getAllConnections());
				components.addAll(getNamedElements(((ComponentImplementation) ctx).getType()));

			} else if (ctx instanceof ComponentType) {
				List<Feature> fs = ((ComponentType) ctx).getAllFeatures();
				components.addAll(fs);

			}

			components.addAll(getNamedElements(getAadlContainer(ctx)));

		}

		return components;
//		=======
//				IScope scope_RecordExpr_args(RecordExpr ctx, EReference ref) {
//			DoubleDotRef record = ctx.getRecord();
//			NamedElement recDef = record.getElm();
//			return RecordExprScoper.getRecordComponents(recDef, IScope.NULLSCOPE);
//			>>>>>>> origin/develop
	}


	private EObject getAadlContainer(EObject o) {

		EObject container = o.eContainer();
		if (container == null) {
			return null;
		} else if (container instanceof AadlPackage) {
			return container;
		} else if (container instanceof Classifier) {
			return container;
		} else {
			return getAadlContainer(o.eContainer());
		}
	}


	IScope scope_NamedElement(AgreeContract ctx, EReference ref) {
		EObject container = getAadlContainer(ctx);
		return Scopes.scopeFor(getNamedElements(container), getScope(ctx.eContainer().eContainer(), ref));
	}

	IScope scope_NamedElement(NodeDef ctx, EReference ref) {
		Set<Element> components = new HashSet<>();
		components.addAll(ctx.getArgs());
		components.addAll(ctx.getRets());
		components.addAll(ctx.getNodeBody().getLocs());
		IScope outer = new FilteringScope(getScope(ctx.eContainer(), ref),
				input -> (AgreePackage.eINSTANCE.getNodeDef().isSuperTypeOf(input.getEClass())
						|| AgreePackage.eINSTANCE.getRecordDef().isSuperTypeOf(input.getEClass())
						|| AgreePackage.eINSTANCE.getConstStatement().isSuperTypeOf(input.getEClass())
						|| Aadl2Package.eINSTANCE.getAadlPackage().isSuperTypeOf(input.getEClass())
						|| Aadl2Package.eINSTANCE.getComponentClassifier().isSuperTypeOf(input.getEClass())));
		return Scopes.scopeFor(components, outer);
	}

	IScope scope_NamedElement(ConnectionStatement ctx, EReference ref) {
		EObject container = ctx.getContainingClassifier();
		IScope outerScope = IScope.NULLSCOPE;
		if (container instanceof ComponentImplementation) {
			ComponentImplementation compImpl = (ComponentImplementation) container;
			outerScope = Scopes.scopeFor(compImpl.getAllConnections(), getScope(ctx.eContainer(), ref));
		}
		return outerScope;
	}

	IScope scope_NamedElement(OrderStatement ctx, EReference ref) {
		EObject container = ctx.getContainingClassifier();

		IScope outerScope = IScope.NULLSCOPE;
		if (container instanceof ComponentImplementation) {
			ComponentImplementation compImpl = (ComponentImplementation) container;
			outerScope = Scopes.scopeFor(compImpl.getAllSubcomponents(), getScope(ctx.eContainer(), ref));
		}
		return outerScope;
	}

	IScope scope_NamedElement(SynchStatement ctx, EReference ref) {
		EObject container = ctx.getContainingClassifier();
		while (!(container instanceof ComponentClassifier)) {
			container = container.eContainer();
		}

		if (container instanceof ComponentImplementation) {
			return Scopes.scopeFor(((ComponentImplementation) container).getAllSubcomponents(),
					getScope(ctx.eContainer(), ref));
		}

		return IScope.NULLSCOPE;
	}

	IScope scope_NamedElement(FnDef ctx, EReference ref) {
		return Scopes.scopeFor(ctx.getArgs(), getScope(ctx.eContainer(), ref));
//			=======
////	protected IScope scope_NamedElement(DoubleDotRef ctx, EReference ref) {
////		return getScope(ctx.eContainer(), ref);
////	}
//
//					protected IScope scope_NamedElement(NestedDotID ctx, EReference ref) {
//				return Scopes.scopeFor(getCorrespondingAadlElement(ctx, ref), getScope(ctx.eContainer(), ref));
//				>>>>>>> origin/develop
	}

	IScope scope_NamedElement(LinearizationDef ctx, EReference ref) {
		return Scopes.scopeFor(ctx.getArgs(), getScope(ctx.eContainer(), ref));
	}


	IScope scope_NamedElement(LibraryFnDef ctx, EReference ref) {
		return Scopes.scopeFor(ctx.getArgs(), getScope(ctx.eContainer(), ref));
	}

	IScope scope_NamedElement(SpecStatement ctx, EReference ref) {
		return getScope(ctx.eContainer(), ref);
	}

	IScope scope_NamedElement(AssignStatement ctx, EReference ref) {
		return getScope(ctx.eContainer(), ref);
	}

	// Expressions

	IScope scope_NamedElement(ForallExpr ctx, EReference ref) {

		IScope prevScope = getScope(ctx.eContainer(), ref);
		List<NamedElement> bs = new ArrayList<NamedElement>();
		bs.add(ctx.getBinding());
		return Scopes.scopeFor(bs, prevScope);
	}

	IScope scope_NamedElement(ExistsExpr ctx, EReference ref) {
		IScope prevScope = getScope(ctx.eContainer(), ref);

		List<NamedElement> bs = new ArrayList<NamedElement>();
		bs.add(ctx.getBinding());
		return Scopes.scopeFor(bs, prevScope);
	}

	IScope scope_NamedElement(ForeachExpr ctx, EReference ref) {
		IScope prevScope = getScope(ctx.eContainer(), ref);

		List<NamedElement> bs = new ArrayList<NamedElement>();
		bs.add(ctx.getBinding());
		return Scopes.scopeFor(bs, prevScope);
	}

	IScope scope_NamedElement(FoldLeftExpr ctx, EReference ref) {
		IScope prevScope = getScope(ctx.eContainer(), ref);

		List<NamedElement> bs = new ArrayList<NamedElement>();
		bs.add(ctx.getAccumulator());
		bs.add(ctx.getBinding());
		return Scopes.scopeFor(bs, prevScope);
	}

	IScope scope_NamedElement(FoldRightExpr ctx, EReference ref) {
		IScope prevScope = getScope(ctx.eContainer(), ref);
		List<NamedElement> bs = new ArrayList<NamedElement>();
		bs.add(ctx.getAccumulator());
		bs.add(ctx.getBinding());
		return Scopes.scopeFor(bs, prevScope);
	}


//	protected IScope scope_DoubleDotRef_namedElm(DoubleDotRef ctx, EReference ref) {
//		if (ctx.getStem() == null) {
//			return getScope(ctx.eContainer(), ref);
//		} else {
//			NamedElement stem = ctx.getStem();
//
//			List<NamedElement> namedSpecs = new ArrayList<NamedElement>();
//			if (stem instanceof AadlPackage) {
//				for (AnnexLibrary annex : AnnexUtil.getAllActualAnnexLibraries(((AadlPackage) stem),
//						AgreePackage.eINSTANCE.getAgreeContractLibrary())) {
//
//					AgreeContract contract = (AgreeContract) ((AgreeContractLibrary) annex).getContract();
//					namedSpecs.addAll(getNamedElementsFromSpecs(contract.getSpecs()));
//
//				}
//			}
//
//			return Scopes.scopeFor(namedSpecs, getScope(stem, ref));
//		}
//	}

	protected IScope scope_GetPropertyExpr_prop(GetPropertyExpr ctx, EReference ref) {

		IScope prevScope = getScope(ctx.eContainer(), ref);

		ComponentRef cr = ctx.getComponentRef();
		if (cr instanceof ThisRef) {
			List<Property> ps = new ArrayList<>();

			EObject container = ctx.getContainingClassifier();
			while (container != null) {
				if (container instanceof Classifier) {
					List<PropertyAssociation> pas = ((Classifier) container).getAllPropertyAssociations();
					for (PropertyAssociation pa : pas) {
						ps.add(pa.getProperty());
					}
					container = ((Classifier) container).eContainer();
				} else if (container instanceof AadlPackage) {
					for (PropertySet propSet : EcoreUtil2.getAllContentsOfType(container, PropertySet.class)) {
						for (Property p : propSet.getOwnedProperties()) {
							ps.add(p);
						}
//								=======
//										EList<EObject> refs  = null;
//
//								if (container instanceof NestedDotID) {
//									NestedDotID parent = (NestedDotID) container;
//									refs = parent.eCrossReferences();
//
//									if (refs.size() != 1) {
//										return new HashSet<>(); // this will throw a parsing error
//									}
//									container = refs.get(0); // figure out what this type this portion
//
//									// of the nest id is so we can figure out
//									// what we could possibly link to
//
//									if (container instanceof ThreadSubcomponent) {
//										container = ((ThreadSubcomponent) container).getComponentType();
//										result.addAll(getAadlElements(container));
//									} else if (container instanceof Subcomponent) {
//										container = ((Subcomponent) container).getComponentImplementation();
//										if (container == null) { // no implementation is provided
//											container = refs.get(0);
//											container = ((Subcomponent) container).getClassifier();
//										}
//										result.addAll(getAadlElements(container));
//									} else if (container instanceof DataPort) {
//										container = ((DataPort) container).getDataFeatureClassifier();
//										result.addAll(getAadlElements(container));
//									} else if (container instanceof EventDataPort) {
//										container = ((EventDataPort) container).getDataFeatureClassifier();
//										result.addAll(getAadlElements(container));
//									} else if (container instanceof AadlPackage) {
//										result.addAll(getAadlElements(container));
//									} else if (container instanceof FeatureGroupImpl) {
//										container = ((FeatureGroupImpl) container).getAllFeatureGroupType();
//										result.addAll(getAadlElements(container));
//									} else if (container instanceof Arg || container instanceof ConstStatement) {
//										Type type;
//
//										if (container instanceof Arg) {
//											type = ((Arg) container).getType();
//										} else {
//											type = ((ConstStatement) container).getType();
//										}
//
//										if (type instanceof RecordType) {
//											DoubleDotRef elID = ((RecordType) type).getRecord();
//											NamedElement namedEl = elID.getElm();
//
//											if (namedEl instanceof ComponentImplementation) {
//												ComponentImplementation componentImplementation = (ComponentImplementation) namedEl;
//												EList<Subcomponent> subs = componentImplementation.getAllSubcomponents();
//												result.addAll(subs);
//											} else if (namedEl instanceof RecordDefExpr) {
//												result.addAll(((RecordDefExpr) namedEl).getArgs());
//												>>>>>>> origin/develop
					}
					container = null;
				} else {
					container = container.eContainer();
				}
			}

			return Scopes.scopeFor(ps, prevScope);

		} else if (cr instanceof DoubleDotRef) {
			NamedElement ne = ((DoubleDotRef) cr).getElm();
			if (ne instanceof Subcomponent) {
				List<PropertyAssociation> pas = ((Subcomponent) ne).getOwnedPropertyAssociations();
				List<Property> ps = new ArrayList<>();
				for (PropertyAssociation pa : pas) {
					ps.add(pa.getProperty());
				}
				return Scopes.scopeFor(ps, prevScope);
			}
		}

		return IScope.NULLSCOPE;
	}



//	IScope scope_DoubleDotRef_stem(DoubleDotRef ctx, EReference ref) {
//		IScope prevScope = getScope(ctx.eContainer(), ref);
//		return Scopes.scopeFor(new ArrayList<EObject>(), prevScope);
//	}

//	IScope scope_DoubleDotRef_elm(DoubleDotRef ctx, EReference ref) {
//		IScope prevScope = getScope(ctx.eContainer(), ref);
//
//		CallExpr ce = (CallExpr) ctx.eContainer();
//		Classifier cc = ce.getContainingClassifier();
//		List<Abstraction> abstractions = EcoreUtil2.getAllContentsOfType(cc, Abstraction.class);
//
//		if (cc instanceof ComponentImplementation) {
//			Classifier c = ((ComponentImplementation) cc).getType();
//			List<Abstraction> parentAbstractions = EcoreUtil2.getAllContentsOfType(c, Abstraction.class);
//			abstractions.addAll(parentAbstractions);
//		}
//
//		return Scopes.scopeFor(abstractions, prevScope);
//
//	}
//
//
//
//	protected IScope scope_DoubleDotRef_elm(DoubleDotRef ctx, EReference ref) {
//
//		IScope prevScope = getScope(ctx.eContainer(), ref);
//		EObject container = ((GetPropertyExpr) ctx.eContainer()).getContainingComponentImpl();
//		if (container instanceof ComponentImplementation) {
//			return Scopes.scopeFor(((ComponentImplementation) ctx).getAllSubcomponents(), prevScope);
//		}
//		return prevScope;
//	}


	private List<NamedElement> getFieldsFromType(Type typ) {

//		=======
//				protected Set<Element> getAadlElements(EObject ctx) {
//			Set<Element> components = new HashSet<>();
//			if (ctx instanceof ComponentType) {
//				components.addAll(getAllAgreeElements(ctx));
//				components.addAll(((ComponentType) ctx).getAllFeatures());
//
//			} else if (ctx instanceof ComponentImplementation) {
//				components.addAll(((ComponentImplementation) ctx).getAllSubcomponents());
//				components.addAll(((ComponentImplementation) ctx).getAllConnections());
//				components.addAll(getAllAgreeElements(ctx));
//				components.addAll(getAadlElements(((ComponentImplementation) ctx).getType()));
//				>>>>>>> origin/develop

		if (typ instanceof DoubleDotRef) {

			NamedElement leaf = ((DoubleDotRef) typ).getElm();



			if (leaf instanceof RecordDef) {
				List<NamedElement> result = new LinkedList<>();
				result.addAll(((RecordDef) leaf).getArgs());
				return result;

			} else if (leaf instanceof DataImplementation) {
				List<NamedElement> result = new LinkedList<>();
				ComponentImplementation componentImplementation = (ComponentImplementation) leaf;
				List<Subcomponent> subs = componentImplementation.getAllSubcomponents();
				result.addAll(subs);
				return result;

			} else if (leaf instanceof Classifier) {

				return new ArrayList<NamedElement>(getNamedElements(leaf));

			} else if (leaf instanceof AadlPackage) {

				List<NamedElement> result = new LinkedList<>();
				AadlPackage ap = (AadlPackage) leaf;
				List<NamedElement> namedSpecs = new ArrayList<NamedElement>();

				for (AnnexLibrary annex : AnnexUtil.getAllActualAnnexLibraries(ap,
						AgreePackage.eINSTANCE.getAgreeContractLibrary())) {

					AgreeContract contract = (AgreeContract) ((AgreeContractLibrary) annex).getContract();
					namedSpecs.addAll(getNamedElementsFromSpecs(contract.getSpecs()));

				}

				result.addAll(namedSpecs);
				return result;

			} else {
				return new LinkedList<>();
			}
		}
		return new LinkedList<>();

	}

	IScope scope_RecordLitExpr_args(RecordLitExpr ctx, EReference ref) {
		IScope prevScope = getScope(ctx.eContainer(), ref);
		NamedElement recDef = ctx.getRecordType().getElm();
		Set<Element> components = new HashSet<>();
		if (recDef instanceof DataImplementation) {
			components.addAll(((DataImplementation) recDef).getAllSubcomponents());
			return Scopes.scopeFor(components, prevScope);
		} else if (recDef instanceof RecordDef) {
			components.addAll(((RecordDef) recDef).getArgs());
			return Scopes.scopeFor(components, prevScope);
		}
		return prevScope;
	}

	IScope scope_RecordUpdateExpr_key(RecordUpdateExpr ctx, EReference ref) {
		IScope prevScope = getScope(ctx.eContainer(), ref);
		Type typ = AgreeTypeSystem.infer(ctx.getRecord());
		return Scopes.scopeFor(getFieldsFromType(typ), prevScope);
	}


	protected IScope scope_ProjectionExpr_field(ProjectionExpr ctx, EReference ref) {
		Type typ = AgreeTypeSystem.infer(ctx.getExpr());
		return Scopes.scopeFor(getFieldsFromType(typ));
	}



	IScope scope_LiftStatement_subcomp(LiftStatement ctx, EReference ref) {
		IScope prevScope = getScope(ctx.eContainer(), ref);
		ComponentImplementation container = ctx.getContainingComponentImpl();
		return Scopes.scopeFor(container.getAllSubcomponents(), prevScope);
	}




}
