package com.rockwellcollins.atc.agree;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AadlBoolean;
import org.osate.aadl2.AadlInteger;
import org.osate.aadl2.AadlPackage;
import org.osate.aadl2.AadlReal;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.ArrayDimension;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ClassifierValue;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ComponentType;
import org.osate.aadl2.DataPort;
import org.osate.aadl2.DataSubcomponentType;
import org.osate.aadl2.DataType;
import org.osate.aadl2.EnumerationLiteral;
import org.osate.aadl2.EventDataPort;
import org.osate.aadl2.Feature;
import org.osate.aadl2.IntegerLiteral;
import org.osate.aadl2.ListValue;
import org.osate.aadl2.NamedElement;
import org.osate.aadl2.NamedValue;
import org.osate.aadl2.Property;
import org.osate.aadl2.PropertyAssociation;
import org.osate.aadl2.PropertyConstant;
import org.osate.aadl2.PropertyExpression;
import org.osate.aadl2.PropertyType;
import org.osate.aadl2.Subcomponent;

import com.rockwellcollins.atc.agree.agree.AbstractionRef;
import com.rockwellcollins.atc.agree.agree.Acc;
import com.rockwellcollins.atc.agree.agree.AgreeFactory;
import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr;
import com.rockwellcollins.atc.agree.agree.ArraySubBinding;
import com.rockwellcollins.atc.agree.agree.ArraySubExpr;
import com.rockwellcollins.atc.agree.agree.ArrayType;
import com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.ComponentRef;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.CustomType;
import com.rockwellcollins.atc.agree.agree.EnumID;
import com.rockwellcollins.atc.agree.agree.EnumLitExpr;
import com.rockwellcollins.atc.agree.agree.EnumStatement;
import com.rockwellcollins.atc.agree.agree.EventExpr;
import com.rockwellcollins.atc.agree.agree.ExistsExpr;
import com.rockwellcollins.atc.agree.agree.Expr;
import com.rockwellcollins.atc.agree.agree.FloorCast;
import com.rockwellcollins.atc.agree.agree.FnDef;
import com.rockwellcollins.atc.agree.agree.FoldLeftExpr;
import com.rockwellcollins.atc.agree.agree.FoldRightExpr;
import com.rockwellcollins.atc.agree.agree.ForallExpr;
import com.rockwellcollins.atc.agree.agree.ForeachExpr;
import com.rockwellcollins.atc.agree.agree.GetPropertyExpr;
import com.rockwellcollins.atc.agree.agree.IfThenElseExpr;
import com.rockwellcollins.atc.agree.agree.IndicesExpr;
import com.rockwellcollins.atc.agree.agree.IntLitExpr;
import com.rockwellcollins.atc.agree.agree.LatchedExpr;
import com.rockwellcollins.atc.agree.agree.LibraryFnDef;
import com.rockwellcollins.atc.agree.agree.LinearizationDef;
import com.rockwellcollins.atc.agree.agree.NamedElmExpr;
import com.rockwellcollins.atc.agree.agree.NodeDef;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.ProjectionExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealCast;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.SubcomponentRef;
import com.rockwellcollins.atc.agree.agree.TagExpr;
import com.rockwellcollins.atc.agree.agree.ThisRef;
import com.rockwellcollins.atc.agree.agree.TimeExpr;
import com.rockwellcollins.atc.agree.agree.TimeFallExpr;
import com.rockwellcollins.atc.agree.agree.TimeOfExpr;
import com.rockwellcollins.atc.agree.agree.TimeRiseExpr;
import com.rockwellcollins.atc.agree.agree.Type;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;

public class AgreeTypeSystem {

	public static String typeToString(Type typ) {

		if (typ instanceof PrimType) {
			return ((PrimType) typ).getName();
		} else if (typ instanceof CustomType) {
			NamedElement stem = ((CustomType) typ).getStem();
			if (stem == null) {
				EObject o = ((CustomType) typ).getLeaf().eContainer();
				while (!(o instanceof AadlPackage)) {
					o = o.eContainer();
				}
				return ((AadlPackage) o).getName() + "::" + ((CustomType) typ).getLeaf().getName();
			} else {
				return ((CustomType) typ).getStem().getName() + "." + ((CustomType) typ).getLeaf().getName();
			}
		} else if (typ instanceof ArrayType) {
			String stemString = typeToString(((ArrayType) typ).getStem());
			return stemString + "[" + ((ArrayType) typ).getSize() + "]";
		}

		return "<type_error>//" + typ.toString();
	}

	private static PrimType mkRealType() {
		PrimType pt = AgreeFactory.eINSTANCE.createPrimType();
		pt.setName("real");
		return pt;
	}

	private static PrimType mkIntType() {
		PrimType pt = AgreeFactory.eINSTANCE.createPrimType();
		pt.setName("int");
		return pt;
	}

	private static PrimType mkBoolType() {
		PrimType pt = AgreeFactory.eINSTANCE.createPrimType();
		pt.setName("bool");
		return pt;
	}

	private static PrimType mkErrorType() {
		PrimType pt = AgreeFactory.eINSTANCE.createPrimType();
		pt.setName("<#error#>");
		return pt;
	}

	private static ArrayType mkArrayType(Type stem, int size) {
		ArrayType at = AgreeFactory.eINSTANCE.createArrayType();
		at.setStem(stem);
		at.setSize(Integer.toString(size));
		return at;
	}


	public static Type mkCustomType(NamedElement cc) {

		if (cc instanceof ComponentType && ((ComponentType) cc).getExtended() != null) {
			return mkCustomType(((Classifier) cc).getExtended());
		} else if (cc instanceof AadlBoolean) {
			return boolType;
		} else if (cc instanceof AadlInteger) {
			return intType;
		} else if (cc instanceof AadlReal) {
			return realType;
		} else {
			CustomType ct = AgreeFactory.eINSTANCE.createCustomType();
			ct.setStem(null);
			ct.setLeaf(cc);
			return ct;
		}
	}

	private static Type trySimp(Type t) {
		if (t instanceof CustomType) {
			String typeString = typeToString(t);
			if (typeString.equals("Base_Types::Integer")) {
				return intType;
			} else if (typeString.equals("Base_Types::Float")) {
				return realType;
			} else if (typeString.contentEquals("Base_Types::Boolean")) {
				return boolType;
			} else {
				return t;
			}
		} else {
			return t;
		}
	}

	public static boolean typesEqual(Type t1, Type t2) {


		if (t1 instanceof ArrayType && t2 instanceof ArrayType) {
			String size1 = ((ArrayType) t1).getSize();
			String size2 = ((ArrayType) t2).getSize();

			if (!size1.contentEquals(size2)) {
				return false;
			} else {
				Type stem1 = ((ArrayType) t1).getStem();
				Type stem2 = ((ArrayType) t2).getStem();

				return typesEqual(stem1, stem2);
			}
		} else {
			Type t10 = trySimp(t1);
			Type t20 = trySimp(t2);
			return typeToString(t10).equals(typeToString(t20));

		}
	}

	public final static PrimType boolType = mkBoolType();
	public final static PrimType intType = mkIntType();
	public final static PrimType realType = mkRealType();

	public final static PrimType errorType = mkErrorType();

	public static class ArrayDef {
		public boolean isArray = false;
		public int dimension = 0;
		public NamedElement baseType = null;
	}

	private static int intFromPropExp(PropertyExpression pe) {
		if (pe instanceof IntegerLiteral) {
			return java.lang.Math.toIntExact(((IntegerLiteral) pe).getValue());

		} else if (pe instanceof NamedValue) {
			NamedValue nv = (NamedValue) pe;
			AbstractNamedValue anv = nv.getNamedValue();
			if (anv instanceof PropertyConstant) {
				return intFromPropExp(((PropertyConstant) anv).getConstantValue());
			}
		}

		return -1;
	}

	public final static ArrayDef arrayDefFromAadl(DataType typedef) {

		List<PropertyAssociation> pas = typedef.getAllPropertyAssociations();

		ArrayDef ad = new ArrayDef();

		for (PropertyAssociation choice : pas) {
			Property p = choice.getProperty();

			PropertyExpression v = choice.getOwnedValues().get(0).getOwnedValue();

			String key = p.getQualifiedName();

			if (key.equals("Data_Model::Data_Representation")) {
				if (v instanceof NamedValue) {
					AbstractNamedValue anv = ((NamedValue) v).getNamedValue();
					if (anv instanceof EnumerationLiteral) {
						EnumerationLiteral el = (EnumerationLiteral) anv;
						ad.isArray = el.getName().equals("Array");
					}
				}

			} else if (key.equals("Data_Model::Base_Type")) {
				if (v instanceof ListValue) {
					if (v instanceof ListValue) {
						ListValue l = (ListValue) v;
						PropertyExpression pe = l.getOwnedListElements().get(0);
						if (pe instanceof ClassifierValue) {
							ad.baseType = ((ClassifierValue) pe).getClassifier();
						}
					}
				}

			} else if (key.equals("Data_Model::Dimension")) {
				if (v instanceof ListValue) {
					ListValue l = (ListValue) v;
					PropertyExpression pe = l.getOwnedListElements().get(0);
					ad.dimension = intFromPropExp(pe);
				}
			}

		}

		return ad;

	}

	public static Type infer(Expr expr) {

		if (expr instanceof ProjectionExpr) {
			NamedElement field = ((ProjectionExpr) expr).getField();
			return typeFromID(field);

		} else if (expr instanceof TagExpr) {

			String tag = ((TagExpr) expr).getTag();
			if (tag != null) {
				switch (tag) {
				case "_CLK":
				case "_INSERT":
				case "_REMOVE":
					return boolType;
				case "_COUNT":
					return intType;
				}
			}

		} else if (expr instanceof ArraySubExpr) {
			Expr arrExpr = ((ArraySubExpr) expr).getExpr();
			Type arrType = infer(arrExpr);
			if (arrType instanceof ArrayType) {

				Type t = ((ArrayType) arrType).getStem();
				return t;
			} else if (arrType instanceof CustomType) {
				NamedElement typedef = ((CustomType) arrType).getLeaf();

				if (typedef instanceof DataType) {
					ArrayDef ad = arrayDefFromAadl((DataType) typedef);

					if (ad.isArray && ad.dimension > 0 && ad.baseType != null) {
						return mkCustomType(ad.baseType);
					}

				}
			}

		} else if (expr instanceof IndicesExpr) {
			Type arrType = infer(((IndicesExpr) expr).getArray());

			if (arrType instanceof CustomType) {
				NamedElement typedef = ((CustomType) arrType).getLeaf();
				if (typedef instanceof DataType) {
					ArrayDef ad = arrayDefFromAadl((DataType) typedef);
					return mkArrayType(intType, ad.dimension);
				}
			} else if (arrType instanceof ArrayType) {
				int size = Integer.parseInt(((ArrayType) arrType).getSize());
				return mkArrayType(intType, size);
			}

		} else if (expr instanceof ForallExpr) {
			return boolType;

		} else if (expr instanceof ExistsExpr) {
			return boolType;

		} else if (expr instanceof ForeachExpr) {
			Type stemType = infer(((ForeachExpr) expr).getExpr());
			Type arrType = infer(((ForeachExpr) expr).getArray());
			if (arrType instanceof ArrayType) {
				int size = Integer.parseInt(((ArrayType) arrType).getSize());
				return mkArrayType(stemType, size);
			}

		} else if (expr instanceof FoldLeftExpr) {
			return infer(((FoldLeftExpr) expr).getExpr());

		} else if (expr instanceof FoldRightExpr) {
			return infer(((FoldRightExpr) expr).getExpr());

		} else if (expr instanceof BinaryExpr) {
			Type leftType = infer(((BinaryExpr) expr).getLeft());
			String op = ((BinaryExpr) expr).getOp();

			switch (op) {
			case "->":
				return leftType;
			case "=>":
			case "<=>":
			case "and":
			case "or":
				return boolType;
			case "<>":
			case "!=":
				return boolType;
			case "<":
			case "<=":
			case ">":
			case ">=":
			case "=":
				return boolType;
			case "+":
			case "-":
			case "*":
			case "/":
			case "mod":
			case "div":
			case "^":
				return leftType;
			}

		} else if (expr instanceof UnaryExpr) {
			return infer(((UnaryExpr) expr).getExpr());

		} else if (expr instanceof IfThenElseExpr) {
			return infer(((IfThenElseExpr) expr).getB());

		} else if (expr instanceof PrevExpr) {
			return infer(((PrevExpr) expr).getInit());

		} else if (expr instanceof GetPropertyExpr) {


			ComponentRef cr = ((GetPropertyExpr) expr).getComponentRef();
			NamedElement prop = ((GetPropertyExpr) expr).getProp();
			if (cr instanceof ThisRef) {

				if (prop instanceof Property) {
					PropertyType pt = ((Property) prop).getPropertyType();
					return mkCustomType(pt);

				} else {

					EObject container = expr.getContainingClassifier();
					List<PropertyAssociation> pas = ((Classifier) container).getAllPropertyAssociations();
					for (PropertyAssociation choice : pas) {
						if (choice.getProperty().getName().equals(prop.getName())) {
							PropertyType pt = choice.getProperty().getPropertyType();
							return mkCustomType(pt);
						}
					}
				}

			} else if (cr instanceof SubcomponentRef) {

				EObject container = expr.getContainingComponentImpl();
				if (container instanceof ComponentImplementation) {
					List<Subcomponent> subcomps = ((ComponentImplementation) container).getAllSubcomponents();
					for (Subcomponent choice : subcomps) {

						List<PropertyAssociation> pas = choice.getOwnedPropertyAssociations();
						for (PropertyAssociation pchoice : pas) {

							if (pchoice.getProperty().getName().equals(prop.getName())) {
								PropertyType pt = pchoice.getProperty().getPropertyType();
								return mkCustomType(pt);
							}
						}

					}


				}

			}

		} else if (expr instanceof IntLitExpr) {
			return intType;

		} else if (expr instanceof RealLitExpr) {
			return realType;

		} else if (expr instanceof BoolLitExpr) {
			return boolType;

		} else if (expr instanceof FloorCast) {
			return intType;

		} else if (expr instanceof RealCast) {
			return realType;

		} else if (expr instanceof EventExpr) {
			return boolType;

		} else if (expr instanceof TimeExpr) {
			return realType;

		} else if (expr instanceof EnumLitExpr) {
			return ((EnumLitExpr) expr).getEnumType();

		} else if (expr instanceof LatchedExpr) {
			return infer(((LatchedExpr) expr).getExpr());

		} else if (expr instanceof TimeOfExpr) {
			return realType;

		} else if (expr instanceof TimeRiseExpr) {
			return realType;

		} else if (expr instanceof TimeFallExpr) {
			return realType;

		} else if (expr instanceof TimeOfExpr) {
			return realType;

		} else if (expr instanceof TimeRiseExpr) {
			return realType;

		} else if (expr instanceof TimeFallExpr) {
			return realType;

		} else if (expr instanceof PreExpr) {
			return infer(((PreExpr) expr).getExpr());

		} else if (expr instanceof ArrayLiteralExpr) {
			EList<Expr> elems = ((ArrayLiteralExpr) expr).getElems();
			Expr first = elems.get(0);
			int size = elems.size();
			Type firstType = infer(first);
			return mkArrayType(firstType, size);

		} else if (expr instanceof ArrayUpdateExpr) {
			return infer(((ArrayUpdateExpr) expr).getArray());

		} else if (expr instanceof RecordLitExpr) {
			return ((RecordLitExpr) expr).getRecordType();

		} else if (expr instanceof RecordUpdateExpr) {
			return infer(((RecordUpdateExpr) expr).getRecord());

		} else if (expr instanceof NamedElmExpr) {
			NamedElement ne = ((NamedElmExpr) expr).getNamedElm();
			return typeFromID(ne);

		} else if (expr instanceof CallExpr) {

			CallExpr fnCall = ((CallExpr) expr);
			AbstractionRef dotId = fnCall.getAbstractionRef();
			NamedElement namedEl = dotId.getLeaf();

			if (isInLinearizationBody(fnCall)) {
				// extract in/out arguments
				if (namedEl instanceof LinearizationDef) {
					return realType;
				} else if (namedEl instanceof LibraryFnDef) {
					LibraryFnDef fnDef = (LibraryFnDef) namedEl;
					return fnDef.getType();
				}

			} else {
				// extract in/out arguments
				if (namedEl instanceof FnDef) {
					FnDef fnDef = (FnDef) namedEl;
					return fnDef.getType();
				} else if (namedEl instanceof NodeDef) {
					NodeDef nodeDef = (NodeDef) namedEl;
					List<Type> outDefTypes = typesFromArgs(nodeDef.getRets());
					if (outDefTypes.size() == 1) {
						return outDefTypes.get(0);
					}
				} else if (namedEl instanceof LinearizationDef) {
					return realType;
				} else if (namedEl instanceof LibraryFnDef) {
					LibraryFnDef fnDef = (LibraryFnDef) namedEl;
					return fnDef.getType();
				}

			}

		}
		return errorType;

	}

//	} else if (namedEl instanceof Feature) {
//		return null;
//
//	} else if (namedEl instanceof Subcomponent) {
//		return null;
//
//	}
//
//	else if (namedEl instanceof DataSubcomponent) {
//		// this is for checking "Base_Types::Boolean" etc...
//		ComponentClassifier compClass = ((DataSubcomponent) namedEl).getAllClassifier();
//		if (compClass instanceof DataImplementation) {
//			return null; // infer(compClass);
//		}
//		return null; // AgreeTypeSystem.infer(compClass);
//

//	} else if (namedEl instanceof EqStatement) {
//		return null; // infer(namedEl);
//

//
//	} else if (namedEl instanceof DataAccess) {
//		return null; // mkClassifierType(((NamedElement) ((DataAccess) namedEl).getFeatureClassifier()));
//
//	} else if (namedEl instanceof DataType) {
//		return null; // infer(namedEl);
//
//	} else if (namedEl instanceof DataImplementation) {
//		return null; // infer(namedEl);
//
//	} else if (namedEl instanceof NamedID) {
//		return null; // infer(namedEl);
//
//	} else if (namedEl instanceof ClassifierType || namedEl instanceof Subcomponent) {
//		return null; // new Type.Name("component");
//
//	}

	public static Type typeFromID(NamedElement ne) {

		if (ne instanceof PropertyStatement) {
			return infer(((PropertyStatement) ne).getExpr());

		} else if (ne instanceof EnumID && ne.eContainer() instanceof EnumStatement) {

			return mkCustomType((EnumStatement) ne.eContainer());

		} else if (ne instanceof ArraySubBinding) {

			EObject container = ne.eContainer();

			Expr arrExpr = null;

			if (container instanceof ForallExpr) {
				arrExpr = ((ForallExpr) container).getArray();

			} else if (container instanceof ExistsExpr) {
				arrExpr = ((ExistsExpr) container).getArray();

			} else if (container instanceof ForeachExpr) {
				arrExpr = ((ForeachExpr) container).getArray();

			} else if (container instanceof FoldLeftExpr) {
				arrExpr = ((FoldLeftExpr) container).getArray();

			} else if (container instanceof FoldRightExpr) {
				arrExpr = ((FoldRightExpr) container).getArray();

			}

			if (arrExpr != null) {
				Type arrType = infer(arrExpr);
				if (arrType instanceof ArrayType) {
					Type stem = ((ArrayType) arrType).getStem();
					return stem;

				} else if (arrType instanceof CustomType) {
					NamedElement typedef = ((CustomType) arrType).getLeaf();
					if (typedef instanceof DataType) {
						ArrayDef ad = arrayDefFromAadl((DataType) typedef);
						if (ad.isArray && ad.dimension > 0 && ad.baseType != null) {
							return mkCustomType(ad.baseType);
						}
					}
				}
			}

		} else if (ne instanceof Acc) {

			EObject container = ne.eContainer();

			if (container instanceof FoldLeftExpr) {
				Expr initExpr = ((FoldLeftExpr) container).getInitial();
				Type initType = infer(initExpr);
				return initType;
			} else if (container instanceof FoldRightExpr) {
				Expr initExpr = ((FoldRightExpr) container).getInitial();
				Type initType = infer(initExpr);
				return initType;
			}

		} else if (ne instanceof ConstStatement) {
			return ((ConstStatement) ne).getType();

		} else if (ne instanceof Arg) {
			Type t = ((Arg) ne).getType();
			return t;


		} else if (ne instanceof DataPort) {
			DataSubcomponentType dt = ((DataPort) ne).getDataFeatureClassifier();
			return mkCustomType(dt);

		} else if (ne instanceof EventDataPort) {
			DataSubcomponentType dt = ((EventDataPort) ne).getDataFeatureClassifier();
			return mkCustomType(dt);

		} else if (ne instanceof Subcomponent) {
			Subcomponent sub = (Subcomponent) ne;

			Classifier cl = sub.getClassifier();
			List<ArrayDimension> dims = sub.getArrayDimensions();
			if (dims.size() == 0) {
				return mkCustomType(cl);
			} else if (dims.size() == 1) {
				ArrayDimension dim = dims.get(0);
				long size = dim.getSize().getSize();
				return mkArrayType(mkCustomType(cl), java.lang.Math.toIntExact(size));

			}

		} else if (ne instanceof Feature) {
			Classifier cl = ((Feature) ne).getClassifier();
			List<ArrayDimension> dims = ((Feature) ne).getArrayDimensions();
			if (dims.size() == 0) {
				return mkCustomType(cl);
			} else if (dims.size() == 1) {
				ArrayDimension dim = dims.get(0);
				long size = dim.getSize().getSize();
				return mkArrayType(mkCustomType(cl), java.lang.Math.toIntExact(size));

			}


		} else if (ne instanceof AadlPackage) {
			return mkCustomType(ne);

		}

//		} else if (namedEl instanceof DataAccess) {
//		DataAccessType t = (((DataAccess) namedEl).getFeatureClassifier());
//		return mkTypeFromAadlType(t);
		return errorType;
	}

	public static boolean hasType(NamedElement ne) {
		return AgreeTypeSystem.errorType != AgreeTypeSystem.typeFromID(ne);
	}

	public static boolean isInLinearizationBody(Expr expr) {
		boolean result = false;
		EObject current = expr;
		while (current != null && current instanceof Expr) {
			EObject container = current.eContainer();
			if (container instanceof LinearizationDef) {
				result = ((LinearizationDef) container).getExprBody().equals(current);
			}
			current = container;
		}
		return result;
	}

	public static List<Type> typesFromArgs(List<Arg> args) {
		ArrayList<Type> list = new ArrayList<>();
		for (Arg arg : args) {
			list.add(arg.getType());
		}
		return list;
	}

}