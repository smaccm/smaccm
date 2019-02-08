package com.rockwellcollins.atc.agree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.osate.aadl2.AadlBoolean;
import org.osate.aadl2.AadlInteger;
import org.osate.aadl2.AadlReal;
import org.osate.aadl2.AbstractNamedValue;
import org.osate.aadl2.ArrayDimension;
import org.osate.aadl2.ArraySize;
import org.osate.aadl2.ArraySizeProperty;
import org.osate.aadl2.Classifier;
import org.osate.aadl2.ClassifierValue;
import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.DataImplementation;
import org.osate.aadl2.DataType;
import org.osate.aadl2.EnumerationLiteral;
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
import org.osate.aadl2.RealLiteral;
import org.osate.aadl2.StringLiteral;
import org.osate.aadl2.Subcomponent;

import com.rockwellcollins.atc.agree.agree.Arg;
import com.rockwellcollins.atc.agree.agree.ArrayLiteralExpr;
import com.rockwellcollins.atc.agree.agree.ArraySubExpr;
import com.rockwellcollins.atc.agree.agree.ArrayType;
import com.rockwellcollins.atc.agree.agree.ArrayUpdateExpr;
import com.rockwellcollins.atc.agree.agree.BinaryExpr;
import com.rockwellcollins.atc.agree.agree.BoolLitExpr;
import com.rockwellcollins.atc.agree.agree.CallExpr;
import com.rockwellcollins.atc.agree.agree.ComponentRef;
import com.rockwellcollins.atc.agree.agree.ConstStatement;
import com.rockwellcollins.atc.agree.agree.DoubleDotRef;
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
import com.rockwellcollins.atc.agree.agree.NamedID;
import com.rockwellcollins.atc.agree.agree.NodeDef;
import com.rockwellcollins.atc.agree.agree.PreExpr;
import com.rockwellcollins.atc.agree.agree.PrevExpr;
import com.rockwellcollins.atc.agree.agree.PrimType;
import com.rockwellcollins.atc.agree.agree.ProjectionExpr;
import com.rockwellcollins.atc.agree.agree.PropertyStatement;
import com.rockwellcollins.atc.agree.agree.RealCast;
import com.rockwellcollins.atc.agree.agree.RealLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordDef;
import com.rockwellcollins.atc.agree.agree.RecordLitExpr;
import com.rockwellcollins.atc.agree.agree.RecordUpdateExpr;
import com.rockwellcollins.atc.agree.agree.TagExpr;
import com.rockwellcollins.atc.agree.agree.ThisRef;
import com.rockwellcollins.atc.agree.agree.TimeExpr;
import com.rockwellcollins.atc.agree.agree.TimeFallExpr;
import com.rockwellcollins.atc.agree.agree.TimeOfExpr;
import com.rockwellcollins.atc.agree.agree.TimeRiseExpr;
import com.rockwellcollins.atc.agree.agree.Type;
import com.rockwellcollins.atc.agree.agree.UnaryExpr;

public class AgreeTypeSystem {

	public static interface TypeDef {

	}

	public static enum Prim implements TypeDef {
		IntTypeDef("int"), RealTypeDef("real"), BoolTypeDef("bool"), ErrorTypeDef("<error>");

		public final String name;

		Prim(String name) {
			this.name = name;
		}

	}

	public static class RangeIntTypeDef implements TypeDef {
		public final String name;
		public final long low;
		public final long high;

		public RangeIntTypeDef(long low, long high) {
			this.name = "int<" + low + "," + high + ">";
			this.low = low;
			this.high = high;
		}
	}

	public static class RangeRealTypeDef implements TypeDef {
		public final String name;
		public final double low;
		public final double high;

		public RangeRealTypeDef(double f, double g) {
			this.name = "real<" + f + "," + g + ">";
			this.low = f;
			this.high = g;
		}
	}

	public static class EnumTypeDef implements TypeDef {
		public final String name;
		public final List<String> values;

		public EnumTypeDef(String name, List<String> values) {
			this.name = name;
			this.values = new ArrayList<>();
			this.values.addAll(values);
		}
	}

	public static class RecordTypeDef implements TypeDef {
		public final String name;
		public final Map<String, TypeDef> fields;
		public final NamedElement namedElement;

		public RecordTypeDef(String name, Map<String, TypeDef> fields, NamedElement namedElement) {
			this.name = name;
			this.fields = new HashMap<>();
			this.fields.putAll(fields);
			this.namedElement = namedElement;
		}
	}

	public static class ArrayTypeDef implements TypeDef {
		public final String name;
		public final TypeDef baseType;
		public final int dimension;

		public ArrayTypeDef(TypeDef baseType, int dimension) {
			this.name = "__" + nameOfTypeDef(baseType) + "[" + dimension + "]";
			this.dimension = dimension;
			this.baseType = baseType;
		}
	}

	public static String nameOfTypeDef(TypeDef td) {
		if (td instanceof Prim) {
			return ((Prim) td).name();
		} else if (td instanceof EnumTypeDef) {
			return ((EnumTypeDef) td).name;
		} else if (td instanceof ArrayTypeDef) {
			return ((ArrayTypeDef) td).name;
		} else if (td instanceof RecordTypeDef) {
			return ((RecordTypeDef) td).name;
		} else {
			throw new RuntimeException("Error: typeDefName");
		}
	}

//	public static String classifierToString(Classifier c) {
//		EObject o = c.eContainer();
//		while (!(o instanceof AadlPackage)) {
//			o = o.eContainer();
//		}
//		return ((AadlPackage) o).getName() + "::" + c.getName();
//	}
//
//	public static String typeToString(Type typ) {
//
//		if (typ instanceof PrimType) {
//			return ((PrimType) typ).getName();
//		} else if (typ instanceof DoubleDotRef) {
//
//			NamedElement ne = ((DoubleDotRef) typ).getElm();
//			if (ne instanceof Classifier) {
//				return classifierToString((Classifier) ne);
//			} else if (ne instanceof RecordDef) {
//				return ne.getFullName();
//			}
//
//		} else if (typ instanceof ArrayType) {
//			String stemString = typeToString(((ArrayType) typ).getStem());
//			return stemString + "[" + ((ArrayType) typ).getSize() + "]";
//		}
//
//		return "<type_error>//" + typ.toString();
//	}


	private static Classifier baseAadlClassifier(Classifier dt) {

		Classifier parent = dt.getExtended();
		if (parent == null) {
			return dt;
		} else {
			return baseAadlClassifier(parent);
		}

	}

	public static TypeDef typeDefFromNE(NamedElement ne) {
		if (ne instanceof Classifier) {
			return typeDefFromClassifier((Classifier) ne);
		} else if (ne instanceof Classifier) {
			return typeDefFromClassifier((Classifier) ne);
		} else if (ne instanceof RecordDef) {

			EList<Arg> args = ((RecordDef) ne).getArgs();
			Map<String, TypeDef> fields = new HashMap<>();
			for (Arg arg : args) {
				String key = arg.getName();
				TypeDef typeDef = typeDefFromType(arg.getType());
				fields.put(key, typeDef);
			}
			return new RecordTypeDef(ne.getQualifiedName(), fields, ne);

		} else if (ne instanceof EnumStatement) {
			String name = ne.getQualifiedName();
			List<String> enumValues = new ArrayList<String>();

			for (NamedID nid : ((EnumStatement) ne).getEnums()) {
				enumValues.add(nid.getQualifiedName());
			}
			return new EnumTypeDef(name, enumValues);

		} else {
			return Prim.ErrorTypeDef;
		}
	}

	public static TypeDef typeDefFromClassifier(Classifier clsfr) {
		Classifier c = baseAadlClassifier(clsfr);

		if (c instanceof AadlBoolean || c.getName().contains("Boolean")) {
			return Prim.BoolTypeDef;
		} else if (c instanceof AadlInteger || c.getName().contains("Integer") || c.getName().contains("Natural")
				|| c.getName().contains("Unsigned")) {
			return Prim.IntTypeDef;
		} else if (c instanceof AadlReal || c.getName().contains("Float")) {
			return Prim.RealTypeDef;
		} else if (c instanceof DataType) {

			List<PropertyAssociation> pas = c.getAllPropertyAssociations();

			boolean prop_isArray = false;
			int prop_arraySize = 0;
			TypeDef prop_arrayBaseType = null;
			boolean prop_isEnum = false;
			List<String> prop_enumValues = null;

			for (PropertyAssociation choice : pas) {
				Property p = choice.getProperty();

				PropertyExpression v = choice.getOwnedValues().get(0).getOwnedValue();

				String key = p.getQualifiedName();

				if (key.equals("Data_Model::Data_Representation")) {
					if (v instanceof NamedValue) {
						AbstractNamedValue anv = ((NamedValue) v).getNamedValue();
						if (anv instanceof EnumerationLiteral) {
							EnumerationLiteral el = (EnumerationLiteral) anv;
							prop_isArray = el.getName().equals("Array");
							prop_isEnum = el.getName().equals("Enum");
						} else {
							return Prim.ErrorTypeDef;
						}
					} else {
						return Prim.ErrorTypeDef;
					}

				} else if (key.equals("Data_Model::Enumerators")) {
					if (v instanceof ListValue) {
						EList<PropertyExpression> peList = ((ListValue) v).getOwnedListElements();
						prop_enumValues = new ArrayList<>();
						for (PropertyExpression pe : peList) {
							if (pe instanceof StringLiteral) {
								String enumString = ((StringLiteral) pe).getValue();
								prop_enumValues.add(enumString);
							} else {
								return Prim.ErrorTypeDef;
							}
						}
					} else {
						return Prim.ErrorTypeDef;
					}

				} else if (key.equals("Data_Model::Base_Type")) {
					if (v instanceof ListValue) {
						ListValue l = (ListValue) v;
						PropertyExpression pe = l.getOwnedListElements().get(0);
						if (pe instanceof ClassifierValue) {
							prop_arrayBaseType = typeDefFromClassifier(((ClassifierValue) pe).getClassifier());
						} else {
							return Prim.ErrorTypeDef;
						}

					} else {
						return Prim.ErrorTypeDef;
					}

				} else if (key.equals("Data_Model::Dimension")) {
					if (v instanceof ListValue) {
						ListValue l = (ListValue) v;
						PropertyExpression pe = l.getOwnedListElements().get(0);
						prop_arraySize = intFromPropExp(pe);

					} else {
						return Prim.ErrorTypeDef;
					}
				} else {
					return Prim.ErrorTypeDef;
				}

			}


			if (prop_isArray && prop_arraySize > 0 && prop_arrayBaseType != null) {

				return new ArrayTypeDef(prop_arrayBaseType, prop_arraySize);

			} else if (prop_isEnum && prop_enumValues != null) {
				String name = c.getQualifiedName();
				return new EnumTypeDef(name, prop_enumValues);

			} else {
				return Prim.ErrorTypeDef;
			}


		} else if (c instanceof DataImplementation) {
			EList<Subcomponent> subcomps = ((DataImplementation) c).getAllSubcomponents();
			Map<String, TypeDef> fields = new HashMap<>();
			for (Subcomponent sub : subcomps) {
				String fieldName = sub.getName();
				TypeDef typeDef = typeDefFromClassifier(sub.getClassifier());
				fields.put(fieldName, typeDef);
			}
			String name = c.getQualifiedName();
			return new RecordTypeDef(name, fields, c);


		} else {
			return Prim.ErrorTypeDef;
		}
	}


	public static TypeDef typeDefFromType(Type t) {

		if (t instanceof PrimType) {

			int lowSign = ((PrimType) t).getLowNeg() == null ? 1 : -1;
			int highSign = ((PrimType) t).getHighNeg() == null ? 1 : -1;

			String lowStr = ((PrimType) t).getRangeLow();
			String highStr = ((PrimType) t).getRangeHigh();


			if (((PrimType) t).getName().equals("int")) {
				if (lowStr == null || highStr == null) {
					return Prim.IntTypeDef;
				} else {

					long low = Long.valueOf(lowStr) * lowSign;
					long high = Long.valueOf(highStr) * highSign;
					return new RangeIntTypeDef(low, high);
				}
			} else if (((PrimType) t).getName().equals("real")) {
				if (lowStr == null || highStr == null) {
					return Prim.RealTypeDef;
				} else {
					double low = Double.valueOf(lowStr) * lowSign;
					double high = Double.valueOf(highStr) * highSign;
					return new RangeRealTypeDef(low, high);
				}
			} else if (((PrimType) t).getName().equals("bool")) {
				return Prim.BoolTypeDef;
			} else {
				return Prim.ErrorTypeDef;
			}

		} else if (t instanceof ArrayType) {
			String size = ((ArrayType) t).getSize();
			TypeDef baseTypeDef = typeDefFromType(((ArrayType) t).getStem());

			return new ArrayTypeDef(baseTypeDef, Integer.parseInt(size));

		} else if (t instanceof DoubleDotRef) {
			return typeDefFromNE(((DoubleDotRef) t).getElm());
		} else {
			return Prim.ErrorTypeDef;
		}

	}

	public static boolean typesEqual(TypeDef t1, TypeDef t2) {
		if (t1 instanceof Prim || t2 instanceof Prim) {
			return t1 == t2;
		} else {
			return nameOfTypeDef((t1)).contentEquals(nameOfTypeDef((t2)));
		}
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

	private static TypeDef inferPropExp(PropertyExpression pe) {
		if (pe instanceof IntegerLiteral) {
			return Prim.IntTypeDef;

		} else if (pe instanceof RealLiteral) {
			return Prim.RealTypeDef;

		} else if (pe instanceof NamedValue) {
			NamedValue nv = (NamedValue) pe;
			AbstractNamedValue anv = nv.getNamedValue();
			if (anv instanceof PropertyConstant) {
				return inferPropExp(((PropertyConstant) anv).getConstantValue());
			} else {
				throw new RuntimeException("Error: typeFromPropExp - NamedValue");
			}
		} else {
			throw new RuntimeException("Error: typeFromPropExp");
		}

	}


	public static TypeDef infer(Expr expr) {

		if (expr instanceof ProjectionExpr) {
			NamedElement field = ((ProjectionExpr) expr).getField();
			return inferFromNamedElement(field);

		} else if (expr instanceof TagExpr) {

			String tag = ((TagExpr) expr).getTag();
			if (tag != null) {
				switch (tag) {
				case "_CLK":
				case "_INSERT":
				case "_REMOVE":
					return Prim.BoolTypeDef;
				case "_COUNT":
					return Prim.IntTypeDef;
				}
			}

		} else if (expr instanceof ArraySubExpr) {
			Expr arrExpr = ((ArraySubExpr) expr).getExpr();
			TypeDef arrType = infer(arrExpr);
			if (arrType instanceof ArrayTypeDef) {
				return ((ArrayTypeDef) arrType).baseType;
			}

		} else if (expr instanceof IndicesExpr) {
			TypeDef arrType = infer(((IndicesExpr) expr).getArray());
			if (arrType instanceof ArrayTypeDef) {
				int dimension = ((ArrayTypeDef) arrType).dimension;
				return new ArrayTypeDef(Prim.IntTypeDef, dimension);
			}

		} else if (expr instanceof ForallExpr) {
			return Prim.BoolTypeDef;

		} else if (expr instanceof ExistsExpr) {
			return Prim.BoolTypeDef;

		} else if (expr instanceof ForeachExpr) {
			TypeDef stemType = infer(((ForeachExpr) expr).getExpr());
			TypeDef arrType = infer(((ForeachExpr) expr).getArray());

			if (arrType instanceof ArrayTypeDef) {
				int dimension = ((ArrayTypeDef) arrType).dimension;
				return new ArrayTypeDef(stemType, dimension);
			}

		} else if (expr instanceof FoldLeftExpr) {
			return infer(((FoldLeftExpr) expr).getExpr());

		} else if (expr instanceof FoldRightExpr) {
			return infer(((FoldRightExpr) expr).getExpr());

		} else if (expr instanceof BinaryExpr) {
			TypeDef leftType = infer(((BinaryExpr) expr).getLeft());
			String op = ((BinaryExpr) expr).getOp();

			switch (op) {
			case "->":
				return leftType;
			case "=>":
			case "<=>":
			case "and":
			case "or":
			case "<>":
			case "!=":
			case "<":
			case "<=":
			case ">":
			case ">=":
			case "=":
				return Prim.BoolTypeDef;
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

					return inferFromNamedElement(pt);

				} else {

					EObject container = expr.getContainingClassifier();
					List<PropertyAssociation> pas = ((Classifier) container).getAllPropertyAssociations();
					for (PropertyAssociation choice : pas) {
						if (choice.getProperty().getName().equals(prop.getName())) {
							PropertyType pt = choice.getProperty().getPropertyType();
							return inferFromNamedElement(pt);
						}
					}
				}

			} else if (cr instanceof DoubleDotRef) {

				EObject container = expr.getContainingComponentImpl();
				if (container instanceof ComponentImplementation) {
					List<Subcomponent> subcomps = ((ComponentImplementation) container).getAllSubcomponents();
					for (Subcomponent choice : subcomps) {

						List<PropertyAssociation> pas = choice.getOwnedPropertyAssociations();
						for (PropertyAssociation pchoice : pas) {

							if (pchoice.getProperty().getName().equals(prop.getName())) {
								PropertyType pt = pchoice.getProperty().getPropertyType();
								return inferFromNamedElement(pt);
							}
						}

					}


				}

			}

		} else if (expr instanceof IntLitExpr) {
			return Prim.IntTypeDef;

		} else if (expr instanceof RealLitExpr) {
			return Prim.RealTypeDef;

		} else if (expr instanceof BoolLitExpr) {
			return Prim.BoolTypeDef;

		} else if (expr instanceof FloorCast) {
			return Prim.IntTypeDef;

		} else if (expr instanceof RealCast) {
			return Prim.RealTypeDef;

		} else if (expr instanceof EventExpr) {
			return Prim.BoolTypeDef;

		} else if (expr instanceof TimeExpr) {
			return Prim.RealTypeDef;

		} else if (expr instanceof EnumLitExpr) {
			return typeDefFromType(((EnumLitExpr) expr).getEnumType());

		} else if (expr instanceof LatchedExpr) {
			return infer(((LatchedExpr) expr).getExpr());

		} else if (expr instanceof TimeOfExpr) {
			return Prim.RealTypeDef;

		} else if (expr instanceof TimeRiseExpr) {
			return Prim.RealTypeDef;

		} else if (expr instanceof TimeFallExpr) {
			return Prim.RealTypeDef;

		} else if (expr instanceof TimeOfExpr) {
			return Prim.RealTypeDef;

		} else if (expr instanceof TimeRiseExpr) {
			return Prim.RealTypeDef;

		} else if (expr instanceof TimeFallExpr) {
			return Prim.RealTypeDef;

		} else if (expr instanceof PreExpr) {
			return infer(((PreExpr) expr).getExpr());

		} else if (expr instanceof ArrayLiteralExpr) {
			EList<Expr> elems = ((ArrayLiteralExpr) expr).getElems();
			Expr first = elems.get(0);
			int size = elems.size();
			TypeDef firstType = infer(first);

			return new ArrayTypeDef(firstType, size);

		} else if (expr instanceof ArrayUpdateExpr) {
			return infer(((ArrayUpdateExpr) expr).getArray());

		} else if (expr instanceof RecordLitExpr) {
			return typeDefFromType(((RecordLitExpr) expr).getRecordType());

		} else if (expr instanceof RecordUpdateExpr) {
			return infer(((RecordUpdateExpr) expr).getRecord());

		} else if (expr instanceof NamedElmExpr) {
			NamedElement ne = ((NamedElmExpr) expr).getElm();
			return inferFromNamedElement(ne);

		} else if (expr instanceof CallExpr) {

			CallExpr fnCall = ((CallExpr) expr);
			DoubleDotRef dotId = fnCall.getRef();
			NamedElement namedEl = dotId.getElm();

			if (isInLinearizationBody(fnCall)) {
				// extract in/out arguments
				if (namedEl instanceof LinearizationDef) {
					return Prim.RealTypeDef;
				} else if (namedEl instanceof LibraryFnDef) {
					LibraryFnDef fnDef = (LibraryFnDef) namedEl;
					return typeDefFromType(fnDef.getType());
				}

			} else {
				// extract in/out arguments
				if (namedEl instanceof FnDef) {
					FnDef fnDef = (FnDef) namedEl;
					return typeDefFromType(fnDef.getType());
				} else if (namedEl instanceof NodeDef) {
					NodeDef nodeDef = (NodeDef) namedEl;
					List<Type> outDefTypes = typesFromArgs(nodeDef.getRets());
					if (outDefTypes.size() == 1) {
						return typeDefFromType(outDefTypes.get(0));
					}
				} else if (namedEl instanceof LinearizationDef) {
					return Prim.RealTypeDef;
				} else if (namedEl instanceof LibraryFnDef) {
					LibraryFnDef fnDef = (LibraryFnDef) namedEl;
					return typeDefFromType(fnDef.getType());
				}

			}


		}
		return Prim.ErrorTypeDef;

	}

	public static TypeDef inferFromNamedElement(NamedElement ne) {


		if (ne instanceof PropertyStatement) {
			return infer(((PropertyStatement) ne).getExpr());

		} else if (ne instanceof NamedID && ne.eContainer() instanceof EnumStatement) {

			EnumStatement enumDef = (EnumStatement) ne.eContainer();
			String name = enumDef.getQualifiedName();
			List<String> enumValues = new ArrayList<String>();

			for (NamedID nid : enumDef.getEnums()) {
				enumValues.add(nid.getQualifiedName());
			}
			return new EnumTypeDef(name, enumValues);

		} else if (ne instanceof NamedID) {

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
				TypeDef arrType = infer(arrExpr);
				if (arrType instanceof ArrayTypeDef) {
					return ((ArrayTypeDef) arrType).baseType;
				}
			}

			if (container instanceof FoldLeftExpr) {
				Expr initExpr = ((FoldLeftExpr) container).getInitial();
				TypeDef initType = infer(initExpr);
				return initType;

			} else if (container instanceof FoldRightExpr) {
				Expr initExpr = ((FoldRightExpr) container).getInitial();
				TypeDef initType = infer(initExpr);
				return initType;
			}


		} else if (ne instanceof ConstStatement) {
			return typeDefFromType(((ConstStatement) ne).getType());

		} else if (ne instanceof Arg) {
			return typeDefFromType(((Arg) ne).getType());

		} else if (ne instanceof Subcomponent) {
			Subcomponent sub = (Subcomponent) ne;
			Classifier cl = sub.getClassifier();
			List<ArrayDimension> dims = sub.getArrayDimensions();
			TypeDef clsTypeDef = typeDefFromClassifier(cl);
			if (dims.size() == 0) {
				return clsTypeDef;
			} else if (dims.size() == 1) {
				long size = getArrayDimension(dims.get(0));
				return new ArrayTypeDef(clsTypeDef, Math.toIntExact(size));
			}

		} else if (ne instanceof Feature) {
			Classifier cl = ((Feature) ne).getClassifier();
			List<ArrayDimension> dims = ((Feature) ne).getArrayDimensions();
			TypeDef clsTypeDef = typeDefFromClassifier(cl);
			if (dims.size() == 0) {
				return clsTypeDef;
			} else if (dims.size() == 1) {
				long size = getArrayDimension(dims.get(0));
				return new ArrayTypeDef(clsTypeDef, Math.toIntExact(size));
			}

//		} else if (ne instanceof AadlPackage) {
//			return mkDoubleDotRef(ne);

		} else if (ne instanceof PropertyConstant) {
			PropertyExpression pe = ((PropertyConstant) ne).getConstantValue();
			return inferPropExp(pe);

		}

		return Prim.ErrorTypeDef;

	}

	private static long getArrayDimension(ArrayDimension arrayDimension) {
		ArraySize arraySize = arrayDimension.getSize();
		long size = arraySize.getSize();
		if (size == 0) {
			ArraySizeProperty arraySizeProperty = arraySize.getSizeProperty();
			if (arraySizeProperty instanceof PropertyConstant) {
				PropertyExpression pe = ((PropertyConstant) arraySizeProperty).getConstantValue();
				size = intFromPropExp(pe);
			}
		}
		assert size > 0;
		return size;
	}

	public static boolean hasType(NamedElement ne) {
		return AgreeTypeSystem.inferFromNamedElement(ne) != (Prim.ErrorTypeDef);
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