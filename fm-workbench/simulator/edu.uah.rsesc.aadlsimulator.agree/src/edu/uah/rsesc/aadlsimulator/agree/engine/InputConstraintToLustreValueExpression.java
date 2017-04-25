package edu.uah.rsesc.aadlsimulator.agree.engine;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

import edu.uah.rsesc.aadlsimulator.agree.sim.ConstraintEvaluationException;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BinaryExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.BooleanLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ConstRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.ElementRefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.InputConstraint;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntegerLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.IntervalExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.NegativeExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.PreExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomElementExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomIntegerExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RandomRealExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RealLiteral;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.RefExpression;
import edu.uah.rsesc.aadlsimulator.xtext.inputConstraint.util.InputConstraintSwitch;
import jkind.lustre.BinaryExpr;
import jkind.lustre.BinaryOp;
import jkind.lustre.BoolExpr;
import jkind.lustre.Expr;
import jkind.lustre.IntExpr;
import jkind.lustre.RealExpr;
import jkind.lustre.UnaryExpr;
import jkind.lustre.UnaryOp;
import jkind.lustre.values.BooleanValue;
import jkind.lustre.values.IntegerValue;
import jkind.lustre.values.RealValue;
import jkind.lustre.values.Value;
import jkind.util.BigFraction;

/**
 * Builds a lustre expression which will evaluates to a value.
 */
public class InputConstraintToLustreValueExpression {
	private static final long MAX_RANDOM_VALUE = 0xFFFFFFFFL;
	private final ReferenceEvaluator referenceEvaluator;
	private final InputConstraintSwitch<Expr> evalSwitch = new InputConstraintSwitch<Expr>() {
		private final Random rand = new Random();
		
		@Override
		public Expr caseRandomIntegerExpression(final RandomIntegerExpression object) {
			final IntervalExpression interval = object.getInterval();
			Value leftValue = lustreExprToValue(doSwitch(interval.getLeft()));
			Value rightValue = lustreExprToValue(doSwitch(interval.getRight()));			
			if(leftValue.getClass() != rightValue.getClass()) {
				throw new RuntimeException("Unexpected case. Values are not of matching types");
			}
			
			// Convert left and right values to big integers
			BigInteger leftInteger;
			BigInteger rightInteger;
			if(leftValue instanceof IntegerValue) {
				leftInteger = ((IntegerValue)leftValue).value;
				rightInteger = ((IntegerValue)rightValue).value;
				
				// Adjust left and right values based on whether the bounds are closed
				if(!interval.isLeftClosed()) {
					leftInteger = leftInteger.add(BigInteger.ONE);
				} 
				
				if(!interval.isRightClosed()) {
					rightInteger = rightInteger.subtract(BigInteger.ONE);
				}				
			} else {
				final BigInteger[] leftIntRemainder = bigFractionToInteger(((RealValue)leftValue).value);
				final BigInteger[] rightIntRemainder = bigFractionToInteger(((RealValue)rightValue).value);
				leftInteger = leftIntRemainder[0];
				rightInteger = rightIntRemainder[0];
				
				// Adjust left and right values based on whether the bounds are closed and whether the value was truncated during the conversion to big integer
				if(!interval.isLeftClosed() && leftIntRemainder[1].equals(BigInteger.ZERO)) {
					leftInteger = leftInteger.add(BigInteger.ONE);
				} 

				if(!interval.isRightClosed() && leftIntRemainder[1].equals(BigInteger.ZERO)) {
					rightInteger = rightInteger.subtract(BigInteger.ONE);
				}
			}
			
			final BigInteger range = rightInteger.subtract(leftInteger);
			if(range.compareTo(BigInteger.ZERO) <= 0) {
				throw new ConstraintEvaluationException("Invalid interval range: " + range, null);
			}
			
			// Generate a 32-bit random value
			// Use any random value except for the MAX_RANDOM_VALUE. MAX_RANDOM_VALUE will cause a value outside the range
			// We do this to allow for a roughly even distribution of offsets
			long randomValue;
			do {
				randomValue = Integer.toUnsignedLong(rand.nextInt()); // 32-bit value
			} while(randomValue == MAX_RANDOM_VALUE);
			final BigInteger offset = range.add(BigInteger.ONE).multiply(BigInteger.valueOf(randomValue)).divide(BigInteger.valueOf(MAX_RANDOM_VALUE));
			final BigInteger randomInteger = leftInteger.add(offset);

			return new IntExpr(randomInteger);
		}
		
		@Override
		public Expr caseRandomRealExpression(final RandomRealExpression object) {
			final IntervalExpression interval = object.getInterval();
			Value leftValue = lustreExprToValue(doSwitch(interval.getLeft()));
			Value rightValue = lustreExprToValue(doSwitch(interval.getRight()));			
			if(leftValue.getClass() != rightValue.getClass()) {
				throw new RuntimeException("Unexpected case. Values are not of matching types");
			}
			
			// Convert left and right values to big fractions
			final BigFraction leftFraction;
			final BigFraction rightFraction;
			if(leftValue instanceof IntegerValue) {
				leftFraction = new BigFraction(((IntegerValue)leftValue).value, BigInteger.ONE);
				rightFraction = new BigFraction(((IntegerValue)rightValue).value, BigInteger.ONE);
			} else {
				leftFraction = ((RealValue)leftValue).value;
				rightFraction = ((RealValue)rightValue).value;
			}
			
			// Determine range of the interval
			final BigFraction range = rightFraction.subtract(leftFraction);
			if(range.compareTo(BigFraction.ZERO) <= 0) {
				throw new ConstraintEvaluationException("Invalid interval range: " + range, null);
			}

			// Generate a 32-bit random value
			long randomValue;
			do {
				randomValue = Integer.toUnsignedLong(rand.nextInt()); // 32-bit value
			} while((randomValue == 0 && !interval.isLeftClosed()) || (randomValue == MAX_RANDOM_VALUE && !interval.isRightClosed()));
			
			final BigFraction scale = new BigFraction(BigInteger.valueOf(randomValue), BigInteger.valueOf(MAX_RANDOM_VALUE));
			final BigFraction randomFraction = leftFraction.add(range.multiply(scale));
			return new BinaryExpr(new RealExpr(new BigDecimal(randomFraction.getNumerator())), BinaryOp.DIVIDE, new RealExpr(new BigDecimal(randomFraction.getDenominator())));
		}
		
		@Override
		public Expr caseRandomElementExpression(final RandomElementExpression object) {
			final int index = rand.nextInt(object.getSet().getMembers().size());
			return doSwitch(object.getSet().getMembers().get(index));
		}

		@Override
		public Expr caseIntegerLiteral(final IntegerLiteral object) {
			return new IntExpr(object.getValue());
		}

		@Override
		public Expr caseRealLiteral(final RealLiteral object) {
			return new RealExpr(object.getValue());
		}

		@Override
		public Expr caseBooleanLiteral(final BooleanLiteral object) {
			return new BoolExpr(object.isValue());
		}
 
		public Expr caseBinaryExpression(final BinaryExpression object) {
			final Expr left = doSwitch(object.getLeft());
			final Expr right = doSwitch(object.getRight());
			
			final BinaryOp lustreOperator;
			switch(object.getOp()) {
			case ADDITION:
				lustreOperator = BinaryOp.PLUS;
				break;
				
			case SUBTRACTION:
				lustreOperator = BinaryOp.MINUS;
				break;
				
			case MULTIPLICATION:
				lustreOperator = BinaryOp.MULTIPLY;
				break;
				
			case DIVISION:
				lustreOperator = BinaryOp.DIVIDE;
				break;
				
			default:
				throw new RuntimeException("Unexpected operator: " + object.getOp());
			}
			
			// Return the binary expression
			return new BinaryExpr(left, lustreOperator, right);
		}		
		
		@Override
		public Expr casePreExpression(final PreExpression object) {
			// Only one level of pre is supported for now.
			final RefExpression ref = object.getRef();
			if(ref instanceof ElementRefExpression) {
				final Value lustreValue = referenceEvaluator.getLustreValue(((ElementRefExpression) ref), 1);
				return lustreValueToExpr(lustreValue);
			} else if(ref instanceof ConstRefExpression) {
				final Value lustreValue = referenceEvaluator.getLustreValue(((ConstRefExpression) ref), 1);
				return lustreValueToExpr(lustreValue);
			} else {
				throw new RuntimeException("Unsupported reference: " + ref);
			}
		}
		
		private Expr lustreValueToExpr(final Value lustreValue) {
			// Convert the lustre value into an expression
			if(lustreValue instanceof BooleanValue) {
				return new BoolExpr(((BooleanValue) lustreValue).value);
			} else if(lustreValue instanceof IntegerValue) {
				return new IntExpr(((IntegerValue) lustreValue).value);
			} else if(lustreValue instanceof RealValue) {
				final RealValue realValue = (RealValue)lustreValue;
				return new BinaryExpr(new RealExpr(new BigDecimal(realValue.value.getNumerator())), BinaryOp.DIVIDE, new RealExpr(new BigDecimal(realValue.value.getDenominator())));
			} else if(lustreValue == null) {
				return null;
			} else {
				throw new RuntimeException("Value is of unexpected type: " + lustreValue);
			}
		}

		@Override
		public Expr caseElementRefExpression(final ElementRefExpression object) {
			return referenceEvaluator.getLustreExpression(object);
		}
		
		@Override
		public Expr caseConstRefExpression(final ConstRefExpression object) {
			return lustreValueToExpr(referenceEvaluator.getLustreValue(object, 0));
		}

		@Override
		public Expr caseNegativeExpression(final NegativeExpression object) {
			return new UnaryExpr(UnaryOp.NEGATIVE, doSwitch(object.getValue()));
		}
	};
	
	public InputConstraintToLustreValueExpression(final ReferenceEvaluator referenceEvaluator) {
		this.referenceEvaluator = Objects.requireNonNull(referenceEvaluator, "referenceEvaluator must not be null");
	}
	
	public Expr eval(final InputConstraint ic) {
		return evalSwitch.doSwitch(ic);
	}
	
	// Only binary, unary, and literal expressions are supported
	private static Value lustreExprToValue(final Expr lustreExpr) {
		if(lustreExpr instanceof BoolExpr) {
			return BooleanValue.fromBoolean(((BoolExpr) lustreExpr).value);
		} else if(lustreExpr instanceof IntExpr) {
			return new IntegerValue(((IntExpr) lustreExpr).value);
		} else if(lustreExpr instanceof RealExpr) {
			final RealExpr realExpr = (RealExpr)lustreExpr;
			return new RealValue(BigFraction.valueOf(realExpr.value));
		} else if(lustreExpr instanceof BinaryExpr) {
			final BinaryExpr binaryExpr = (BinaryExpr)lustreExpr;
			final Value leftValue = lustreExprToValue(binaryExpr.left);
			final Value rightValue = lustreExprToValue(binaryExpr.right);
			return leftValue.applyBinaryOp(binaryExpr.op, rightValue);
		} else if(lustreExpr instanceof UnaryExpr) {
			final UnaryExpr unaryExpr = (UnaryExpr)lustreExpr;
			final Value operandValue = lustreExprToValue(unaryExpr.expr);
			return operandValue.applyUnaryOp(unaryExpr.op);
		}
		
		throw new RuntimeException("Unsupported expression: " + lustreExpr);		
	}
	
	private static BigInteger[] bigFractionToInteger(final BigFraction bigFraction) {
		return bigFraction.getNumerator().divideAndRemainder(bigFraction.getDenominator());
	}	
}
