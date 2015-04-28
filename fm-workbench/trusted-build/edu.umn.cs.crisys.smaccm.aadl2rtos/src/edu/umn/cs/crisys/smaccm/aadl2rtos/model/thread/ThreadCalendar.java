/**
 * 
 */
package edu.umn.cs.crisys.smaccm.aadl2rtos.model.thread;

import java.util.ArrayList;
import java.util.List;

import edu.umn.cs.crisys.smaccm.aadl2rtos.Aadl2RtosException;
import edu.umn.cs.crisys.smaccm.aadl2rtos.model.dispatcher.PeriodicDispatcher;

/**
 * @author Whalen
 *
 */
public class ThreadCalendar {

  List<PeriodicDispatcher> periodicDispatchers = new ArrayList<PeriodicDispatcher>();
  
  public ThreadCalendar() {
    
  }
  
  public void addPeriodicDispatcher(PeriodicDispatcher d) {
    periodicDispatchers.add(d);
  }
  
  public List<PeriodicDispatcher> getPeriodicDispatchers() {
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
    for (PeriodicDispatcher d : periodicDispatchers) {
      longest = java.lang.Math.max(longest, d.getPeriod());
    }
    return longest;
  }
  
  public int getGreatestCommonDivisorInMilliseconds() {
    if (periodicDispatchers.size() == 0) {
      throw new Aadl2RtosException("Attempting to compute the GCD of periodic threads, but no periodic threads in model.");
    }
    int gcdVal = periodicDispatchers.get(0).getPeriod();
    for (PeriodicDispatcher d : periodicDispatchers) {
      gcdVal = gcd(d.getPeriod(), gcdVal);
    }
    return gcdVal;
  }
  
  public int getHyperperiodSubdivisions() {
    return (this.getLongestPeriodInMilliseconds() / this.getGreatestCommonDivisorInMilliseconds());
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
  
  
  
}
