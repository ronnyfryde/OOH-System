/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package out_of_hours_core;

import java.util.*;
import out_of_hours_core.SingleSignOn;
import out_of_hours_core.User;
/**
 *
 * @author Skenn
 */
public class Manager {
   
    // Instance variables
    private final String DEFAULT_PASSWORD = "password";
    /**
     * Construct a manager object.
     */
    public Manager()
    {}
    
    // Methods
    /**
     * Creates and returns a new user object.
     * @param aName
     * @param aPhoneNumber
     * @param aEmail
     * @param aSSO
     * @param isEscalation
     * @param isFirstLine
     * @param isManager
     * @return 
     */
    protected User CreateUser(String aName, String aPhoneNumber, String aEmail, SingleSignOn aSSO,
                               boolean isEscalation, boolean isFirstLine, boolean isManager)
    {
        User aUser = new User(aName, aPhoneNumber, aEmail, DEFAULT_PASSWORD, 
            aSSO, isFirstLine, isEscalation, isManager);
        
        return aUser;
    }
    
    /**
     * Sets password for aUser to default password "password".
     * @param aUser
     */
    protected void resetPassword(User aUser)
    {
        aUser.setPassword(DEFAULT_PASSWORD);
    }
    
    /**
     * Sets the SSO for aUser to aSSO.
     * @param aUser
     * @param aSSO 
     */
    protected void setSSO(User aUser, SingleSignOn aSSO)
    {
        aUser.setSSO(aSSO);
    }
    
    /**
     * Sets aUser firstLine and escalation to be false.
     * @param aUser 
     */
    protected void disableEngineer(User aUser)
    {
        aUser.setFirstLine(false);
        aUser.setEscalation(false);
    }
    
    /**
     * Sets aUser engineer isFirstLinee to be aBool.
     * @param aUser
     * @param aBool 
     */
    protected void setFirstLine(User aUser, boolean aBool)
    {
        aUser.setFirstLine(aBool);
    }
    
    /**
     * Sets aUser isEscalation to be aBool.
     * @param aUser
     * @param aBool 
     */
    protected void setEscalation(User aUser, boolean aBool)
    {
        aUser.setEscalation(aBool);
    }

    /**
     * Creates or removes a Manager object for aUser.
     * @param aUser
     * @param aBool 
     */
    protected void setManager(User aUser, boolean aBool)
    {
        aUser.setManager(aBool);
    }
    
    /**
     * Changes Current engineer to new engineer a given shift.
     * @param aShift
     * @param currentEngineer
     * @param newEngineer 
     */
    protected void changeShift(Shift aShift, Engineer currentEngineer, 
                                Engineer newEngineer)
    {
        aShift.changeEngineer(currentEngineer, newEngineer);
    }
    
    /**
     * Creates a Quarter object
     * @param aNum
     * @param aBool
     * @param aDate
     * @return 
     */
    public Quarter createQuarter(int aNum, boolean aBool, Date aDate)
    {
        try
        {
            Quarter aQuarter = new Quarter(aNum, aBool, aDate);
            return aQuarter;
        }
        catch(Exception aException)
        {
            System.out.println("Error: Unable to create quarter: " + aException);
        }
        return null;
    }
    
    /**
     * Generates shifts for the quarter.
     * @param aQuarter 
     */
    public void generateShifts(Quarter aQuarter)
    {
        aQuarter.generateShifts();
    }
    
    /**
     * Assigns first line and escalation engineer to a shift.
     * @param aQuarter
     */
    public void assignShifts(Quarter aQuarter)
    {
        aQuarter.assignShifts();
    }
}
