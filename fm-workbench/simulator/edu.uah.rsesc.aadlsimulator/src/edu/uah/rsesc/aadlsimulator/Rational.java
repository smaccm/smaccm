/*
Copyright (c) 2015, Rockwell Collins.
Developed with the sponsorship of Defense Advanced Research Projects Agency (DARPA).

Permission is hereby granted, free of charge, to any person obtaining a copy of this data, 
including any software or models in source or binary form, as well as any drawings, specifications, 
and documentation (collectively "the Data"), to deal in the Data without restriction, including
without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Data, and to permit persons to whom the Data is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or 
substantial portions of the Data.

THE DATA IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS, SPONSORS, DEVELOPERS, CONTRIBUTORS, OR COPYRIGHT HOLDERS BE LIABLE 
FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, 
ARISING FROM, OUT OF OR IN CONNECTION WITH THE DATA OR THE USE OR OTHER DEALINGS IN THE DATA.
*/
package edu.uah.rsesc.aadlsimulator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class Rational {
	private static String ELLIPSIS = "...";
	public final BigInteger numerator;
	public final BigInteger denominator;
	
	public Rational(final BigInteger numerator, final BigInteger denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result	+ ((denominator == null) ? 0 : denominator.hashCode());
		result = prime * result	+ ((numerator == null) ? 0 : numerator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Rational other = (Rational) obj;
		if (denominator == null) {
			if (other.denominator != null) {
				return false;
			}
		} else if (!denominator.equals(other.denominator)) {
			return false;
		}
		if (numerator == null) {
			if (other.numerator != null) {
				return false;
			}
		} else if (!numerator.equals(other.numerator)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return numerator + "/" + denominator;
	}
	
	public float floatValue() {
		float result = numerator.floatValue() / denominator.floatValue();
		if (Double.isFinite(result)) {
			return result;
		} else {
			BigDecimal numDec = new BigDecimal(numerator);
			BigDecimal denomDec = new BigDecimal(denominator);
			return numDec.divide(denomDec, MathContext.DECIMAL64).floatValue();
		}
	}

	public String toDecimalString(final int numberOfDigitsRight) {
		String result = "";
		
		if(denominator == BigInteger.ZERO) {
			return "Undefined. Division by zero.";
		}

		// Handle negative sign
		boolean isNegative = denominator.compareTo(BigInteger.ZERO) * numerator.compareTo(BigInteger.ZERO) < 0;
		if(isNegative) {
			result += "-";
		}
		
		final BigInteger absDenominator = denominator.abs();
		BigInteger[] divResultAndRemainder = numerator.abs().divideAndRemainder(absDenominator);
		result += divResultAndRemainder[0].toString();

		if(divResultAndRemainder[1] != BigInteger.ZERO) {
			result += "."; 
		
			// Calculate the appropriate number of digits
			for(int i = 0; i < numberOfDigitsRight && divResultAndRemainder[1] != BigInteger.ZERO; i++) {
				divResultAndRemainder = divResultAndRemainder[1].multiply(BigInteger.TEN).divideAndRemainder(absDenominator);
				result += divResultAndRemainder[0].toString();
			}
		}
		
		// Show ellipsis if value was truncated
		if(divResultAndRemainder[1] != BigInteger.ZERO) {
			result += ELLIPSIS;
		}
		
		return result;
	}
	
	/**
	 * Returns whether a decimal string returned by toDecimalString() represents a truncated value
	 * @param decimalString
	 * @return
	 */
	public static boolean isTruncatedValue(final String decimalString) {
		return decimalString.endsWith(ELLIPSIS);
	}	

	// Test for decimal string conversion
	public static void main(final String[] args) {
		testValueAllSigns(1, 3);
		testValueAllSigns(100, 3);
		testValueAllSigns(100, 5);
		testValueAllSigns(10, 4);
		testValueAllSigns(0, 5);
		testValueAllSigns(1, 0);
	}
	
	private static void testValueAllSigns(final long numerator, final long denominator) {
		testValue(numerator, denominator);
		testValue(-numerator, denominator);
		testValue(numerator, -denominator);
		testValue(-numerator, -denominator);
	}
	
	private static void testValue(final long numerator, final long denominator) {
		final Rational rationalValue = new Rational(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
		final String decimalStr = rationalValue.toDecimalString(4);
		System.out.println(rationalValue.toString() + " = " + decimalStr + " : Truncated? " + isTruncatedValue(decimalStr));
	}	
}
