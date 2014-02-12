/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.ast;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;

/**
 * @author Whalen
 *
 */
public class ThreadCalendar {

  List<Dispatcher> periodicDispatchers = new ArrayList<Dispatcher>();
  
  public ThreadCalendar() {
    
  }
  
  public void addPeriodicDispatcher(Dispatcher d) {
    periodicDispatchers.add(d);
  }
  
  public List<Dispatcher> getPeriodicDispatchers() {
    return periodicDispatchers;
  }
  
  public boolean hasDispatchers() {return !periodicDispatchers.isEmpty(); }
  
  private static int gcd(int a, int b)
  {
      if (a <= 0 || b <= 0) {
        throw new Aadl2RtosException("When attempting to compute gcd, at least one number is negative.");
      }
      while (b > 0)
      {
          int temp = b;
          b = a % b; // % is remainder
          a = temp;
      }
      return a;
  }
  
  public int getLongestPeriodInMilliseconds() {
    int longest = 0;
    for (Dispatcher d : periodicDispatchers) {
      longest = java.lang.Math.max(longest, d.getPeriod());
    }
    return longest;
  }
  
  public int getGreatestCommonDivisorInMilliseconds() {
    if (periodicDispatchers.size() == 0) {
      throw new Aadl2RtosException("Attempting to compute the GCD of periodic threads, but no periodic threads in model.");
    }
    int gcdVal = periodicDispatchers.get(0).getPeriod();
    for (Dispatcher d : periodicDispatchers) {
      gcdVal = gcd(d.getPeriod(), gcdVal);
    }
    return gcdVal;
  }
  
  public int getHyperperiodSubdivisions() {
    return (this.getLongestPeriodInMilliseconds() / this.getGreatestCommonDivisorInMilliseconds());
  }
}
