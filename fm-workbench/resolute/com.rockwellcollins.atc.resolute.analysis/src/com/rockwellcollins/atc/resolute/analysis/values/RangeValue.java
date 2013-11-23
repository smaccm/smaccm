package com.rockwellcollins.atc.resolute.analysis.values;

public class RangeValue extends ResoluteValue {
    
	final public static int TYPE_INTEGER = 1;
	final public static int TYPE_REAL    = 2;
	
	private int    type;
	private double minDouble = 0;
    private double maxDouble = 0;
	private long  minLong = 0;
    private long  maxLong = 0;
    
    public RangeValue(double min, double max) 
    {
        this.minDouble = min;
        this.maxDouble = max;
        this.type = TYPE_REAL;
    }
    
    public RangeValue(long min, long max) 
    {
        this.minLong = min;
        this.maxLong = max;
        this.type = TYPE_INTEGER;
    }

    public int getType ()
    {
    	return this.type;
    }
    
    public double getMinimumDouble() {
        return minDouble;
    }
    
    public long getMinimumLong() {
        return minLong;
    }
    
    public double getMaximumDouble() {
        return maxDouble;
    }
    
    public long getMaximumLong() {
        return maxLong;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RangeValue) {
            RangeValue dv = (RangeValue) obj;
            return (
            		(dv.getMaximumDouble() == maxDouble) &&
            		(dv.getMaximumLong() == maxLong) &&
            		(dv.getMinimumDouble() == minDouble) &&
            		(dv.getMinimumLong() == minLong)
            	   );
        }

        return false;
    }
}
