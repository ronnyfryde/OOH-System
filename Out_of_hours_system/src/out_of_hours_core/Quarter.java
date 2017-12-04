/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package out_of_hours_core;

import java.util.*;

/**
 * A Quarter in the year.
 * @author Skenn
 */
public class Quarter implements Comparable<Quarter> {
    
    // Instance variables
    private final int quarter;
    private final boolean isLong;
    private SortedSet<Shift> shifts;
    private Date startDate;
    
   /**
    * Constructor for quarter initialises quarter, isLong, shifts and startDate.
    * @param quarterNum
    * @param isLong
    * @param startDate
     * @throws java.lang.Exception if the start date is not a Monday or Friday.
    */
    public Quarter(int quarterNum, boolean isLong, Date startDate) throws Exception
    {
        System.out.println("Date = " + startDate);
        
        // Using a Calendar find the day of the week.
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        System.out.println("day = " + day);
        System.out.println("Calendar.Monday = " + Calendar.MONDAY);
        
        if ( (day == Calendar.MONDAY) || (day == Calendar.FRIDAY) )
        {
            this.quarter = quarterNum;
            this.isLong = isLong;
            this.startDate = startDate;
            this.shifts = new TreeSet<>();  
        }
        else
        {
            // Throw an exception if the start date is not a Monday or Friday.
            throw new Exception("Shift start date must be a Monday or Friday.");
        }          
    }
       
   /**
    * Returns the start date of the receiver.
    * @return the date the quarter starts on.
    */
    public Date getStartDate()
    {
        return this.startDate;
    }
    
    /**
     * Returns the isLong boolean of the receiver which indicates if the quarter
     * will be 13 weeks or 14 weeks (true if the quarter is 14 weeks).
     * @return True if the quarter is 14 weeks long.
     */
    public boolean getLong()
    {
        return this.isLong;
    }
    
    /**
     * Returns the quarter of the receiver.
     * @return an integer representing the quarter of the year.
     */
    public int getQuarter()
    {
        return this.quarter;
    }
    
    /**
     * Returns the number of shifts in the quarter.
     * @return The number of shifts that have been created for the quarter.
     */
    public int getNumberOfShifts()
    {
        return this.shifts.size();
    }
    
    /**
     * Returns the shifts for the receiver.
     * @return A set of shifts linked to the quarter.
     */
    public SortedSet<Shift> getShifts()
    {
        return this.shifts;
    }
    
    // Methods
    
    /**
     * Generate the shifts for the quarter.
     */
    protected void generateShifts()
    {
        this.shifts.add(new Shift(this.startDate));
       
        
        if (isLong){
           /*
            * If we've got a long quarter then we need to fill 14 weeks
            * 28 shifts, if not it's 13 weeks and 26 shifts.
            */
            long newDate = 0;
            for(int i=0; i < 27; i++)
            {
                this.shifts.add(new Shift(this.shifts.last().getEndDate()));
            }            
        }
        else
        {
            for(int i=0; i < 25; i++)
            {
                this.shifts.add(new Shift(this.shifts.last().getEndDate()));
            }
        }
    }
    
    /**
     * Assigns engineers to each shift in the shifts sorted set.
     */
    public void assignShifts()
    {
        for (Shift aShift : this.shifts)
        {
            aShift.assignEngineers();
        }
    }
    
    /**
     * Returns true is o is a Quarter object with startDate
     * that is equal to that of the receiver, otherwise return false.
     * 
     * @param o the object to be checked for equality with the receiver
     * @return true if the receiver and argument are equal, false otherwise
     */
    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof Quarter))
        {
            return false;
        }
        Quarter q = (Quarter) o;
        return (q.startDate.equals(this.startDate));
    }
    
    /**
     * Returns the hashcode of the Quarter.
     *
     * @return hashcode
     */
    @Override
    public int hashCode()
    {
        int code = 17;
        code = 37*code + this.startDate.hashCode();
        return code;
    }

    /**
     * Returns a negative integer if the receiver starts before aQuarter
     * a positive integer is the receiver starts after aQuarter
     * and zero otherwise.
     * 
     * @param aQuarter the quarter to be compared with the receiver.
     * @return -1 if the Quarter is before aQuarter, 1 if the shift 
     * is after aQuarter
     * and 0 otherwise.
     */
    @Override
    public int compareTo(Quarter aQuarter) 
    {
        if (this.startDate.compareTo(aQuarter.startDate) < 0)
            return -1;
        if (this.startDate.compareTo(aQuarter.startDate) > 0)
            return 1;
        return 0;
    }
}
