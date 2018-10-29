package com.rockwellcollins.atc.resolute.stringlib;

import java.util.ArrayList;
import java.util.List;

import com.rockwellcollins.atc.resolute.analysis.execution.EvaluationContext;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteExternalFunctionLibrary;
import com.rockwellcollins.atc.resolute.analysis.execution.ResoluteFailException;
import com.rockwellcollins.atc.resolute.analysis.values.BoolValue;
import com.rockwellcollins.atc.resolute.analysis.values.IntValue;
import com.rockwellcollins.atc.resolute.analysis.values.ListValue;
import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.analysis.values.StringValue;

/**
 * String function library for manipulating strings in Resolute
 */
public class ResoluteStringFunctions extends ResoluteExternalFunctionLibrary {

	@Override
	public ResoluteValue run(EvaluationContext context, String function, List<ResoluteValue> args) {

		switch (function.toLowerCase()) {
		case "concat": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isString());
			assert (arg1.isString());
			return new StringValue(arg0.getString().concat(arg1.getString()));
		}
		case "contains": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isString());
			assert (arg1.isString());
			return new BoolValue(arg0.getString().contains(arg1.getString()));
		}
		case "endswith": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isString());
			assert (arg1.isString());
			return new BoolValue(arg0.getString().endsWith(arg1.getString()));
		}
		case "hashcode": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isString());
			return new IntValue(arg0.getString().hashCode());
		}
		case "indexof": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isString());
			assert (arg1.isString());
			return new IntValue(arg0.getString().indexOf(arg1.getString()));
		}
		case "lastindexof": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isString());
			assert (arg1.isString());
			return new IntValue(arg0.getString().lastIndexOf(arg1.getString()));
		}
		/**
		 * Note that this library method name differs from the name in the java.lang package.
		 * This is necessary to avoid conflict with the Resolute built-in 'length()' method.
		 */
		case "stringlength": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isString());
			return new IntValue(arg0.getString().length());
		}
		case "matches": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isString());
			assert (arg1.isString());
			return new BoolValue(arg0.getString().matches(arg1.getString()));
		}
		case "replace": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			ResoluteValue arg2 = args.get(2);
			assert (arg0.isString());
			assert (arg1.isString());
			assert (arg2.isString());
			return new StringValue(arg0.getString().replace(arg1.getString(), arg2.getString()));
		}
		case "split": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isString());
			assert (arg1.isString());
			List<StringValue> fragments = new ArrayList<>();
			for (String fragment : arg0.getString().split(arg1.getString())) {
				fragments.add(new StringValue(fragment));
			}
			return new ListValue(fragments);
		}
		case "startswith": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			assert (arg0.isString());
			assert (arg1.isString());
			return new BoolValue(arg0.getString().startsWith(arg1.getString()));
		}
		case "substring": {
			ResoluteValue arg0 = args.get(0);
			ResoluteValue arg1 = args.get(1);
			ResoluteValue arg2 = args.get(2);
			assert (arg0.isString());
			assert (arg1.isInt());
			assert (arg2.isInt());
			return new StringValue(
					arg0.getString().substring(new Long(arg1.getInt()).intValue(), new Long(arg2.getInt()).intValue()));
		}
		case "tolowercase": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isString());
			return new StringValue(arg0.getString().toLowerCase());
		}
		case "touppercase": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isString());
			return new StringValue(arg0.getString().toUpperCase());
		}
		case "trim": {
			ResoluteValue arg0 = args.get(0);
			assert (arg0.isString());
			return new StringValue(arg0.getString().trim());
			}
		}

		throw new ResoluteFailException("Function " + function + " not part of String Library.",
				context.getThisInstance().getSubcomponent());

	}

}
