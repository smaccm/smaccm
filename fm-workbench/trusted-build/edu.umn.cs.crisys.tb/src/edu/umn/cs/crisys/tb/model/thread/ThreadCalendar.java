/**
 * 
 */
package edu.umn.cs.crisys.tb.model.thread;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.tb.TbException;
import edu.umn.cs.crisys.tb.model.ModelElement;
import edu.umn.cs.crisys.tb.model.ModelElementBase;
import edu.umn.cs.crisys.tb.model.OSModel;
import edu.umn.cs.crisys.tb.model.port.InputPeriodicPort;

/**
 * @author Whalen
 *
 */
public class ThreadCalendar extends ModelElementBase {

  OSModel m; 
  List<InputPeriodicPort> periodicDispatchers = new ArrayList<InputPeriodicPort>();
  
  public ThreadCalendar(OSModel m) {
    this.m = m;
  }
  
  public void addPeriodicPort(InputPeriodicPort d) {
    periodicDispatchers.add(d);
  }
  
  public List<InputPeriodicPort> getPeriodicDispatchers() {
    return periodicDispatchers;
  }
  
  public OSModel getModel() {
	  return m;
  }
  
  public boolean hasDispatchers() {return !periodicDispatchers.isEmpty(); }
  
  private static int gcd(int a, int b)
  {
      if (a <= 0 || b <= 0) {
        throw new TbException("When attempting to compute gcd, at least one number is negative.");
      }
      while (b > 0)
      {
          int temp = b;
          b = a % b; // % is remainder
          a = temp;
      }
      return a;
  }

  private int lcm(int a, int b) {
	  return Math.multiplyExact(a, b) / gcd(a, b);
  }
  
  public int getLongestPeriodInMilliseconds() {
    int longest = 0;
    for (InputPeriodicPort d : periodicDispatchers) {
      longest = java.lang.Math.max(longest, d.getPeriod());
    }
    return longest;
  }
  
  public int getGreatestCommonDivisorInMilliseconds() {
    if (periodicDispatchers.size() == 0) {
      throw new TbException("Attempting to compute the GCD of periodic threads, but no periodic threads in model.");
    }
    int gcdVal = periodicDispatchers.get(0).getPeriod();
    for (InputPeriodicPort d : periodicDispatchers) {
      gcdVal = gcd(d.getPeriod(), gcdVal);
    }
    return gcdVal;
  }
  
  
  public int getLeastCommonMultipleInMilliseconds() {
     if (periodicDispatchers.size() == 0) {
         throw new TbException("Attempting to compute the LCM of periodic threads, but no periodic threads in model.");
     }
     int lcmVal = periodicDispatchers.get(0).getPeriod();
     for (InputPeriodicPort d : periodicDispatchers) {
    	 lcmVal = lcm(d.getPeriod(), lcmVal);
     }
	 return lcmVal; 
  }
  
  public int getHyperperiodSubdivisions() {
    return (this.getLeastCommonMultipleInMilliseconds() / this.getGreatestCommonDivisorInMilliseconds());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((periodicDispatchers == null) ? 0 : periodicDispatchers.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ThreadCalendar other = (ThreadCalendar) obj;
    if (periodicDispatchers == null) {
      if (other.periodicDispatchers != null)
        return false;
    } else if (!periodicDispatchers.equals(other.periodicDispatchers))
      return false;
    return true;
  }

@Override
public ModelElement getParent() {
   // TODO Auto-generated method stub
   return this.getModel();
}
  
  
  
}
