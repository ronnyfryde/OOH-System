/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package out_of_hours_core;

import java.io.Serializable;
import java.util.*;
/**
 * This is the coordinating class the will control interaction to objects in
 * the core system.
 * 
 * @author Skenn
 */
public class OohCoord implements Serializable
{
    // Links
    private Collection<User> users;
    private Collection<Quarter> quarters;
    
    /**
     * Constructor: Creates a new OohCoord object. There can only be one
     * coordinating object in a system.
     */
    public OohCoord()
    {
        users = new HashSet<>();
        quarters = new TreeSet<>();
    }
    
    //Getters
    
    /**
     * Returns a set of users linked to the receiver.
     * @return all users in the system.
     */
    public Collection<User> getUsers()
    {
        return this.users;
    }
    
    /**
     * Returns all the shifts for the quarter provided
     * as an argument.
     * @param aQuarter containing shifts.
     * @return The shifts in the quarter.
     */
    public Collection<Shift> getShifts(Quarter aQuarter)
    {
        return aQuarter.getShifts();
    }
    
    /**
     * Returns all quarters.
     * @return all quarters in the system
     */
    public Collection<Quarter> getQuarters()
    {
        return this.quarters;
    }
    
    /**
     * Returns the two types of engineer assigned to the shift.
     * @param aShift that has assigned engineers.
     * @return the engineers assigned to the shift.
     */
    public Map<Type, Engineer> getShiftEngineers(Shift aShift)
    {
        Map<Type, Engineer> aMap = new HashMap<>();
        
        aMap.put(Type.FIRST, aShift.getFirstLineEngineer());
        aMap.put(Type.ESCALATION, aShift.getEscalationEngineer());
        
        return aMap;
    }
    
    //Setters
    /**
     * Adds an engineer to the available engineers list for a given Type
     * for the provided shift.
     * @param aShift
     * @param aType
     * @param aEngineer 
     */
    public void setAvailableForShift(Shift aShift, Type aType, Engineer aEngineer)
    {
        if (aType.equals(Type.FIRST))
        {
            aShift.addToFirstLineEngineers(aEngineer);
        }
        else if (aType.equals(Type.ESCALATION))
        {
            aShift.addToAvailableEscalationEngineers(aEngineer);
        }
    }
    
    /**
     * Remove aEngineer from available engineers collection
     * for a given type and shift.
     * @param aShift
     * @param aType
     * @param aEngineer 
     */
    public void setUnvailableForShift(Shift aShift, Type aType, Engineer aEngineer)
    {
        if (aType.equals(Type.FIRST))
        {
            aShift.removeFromFirstLineEngineers(aEngineer);
        }
        else if (aType.equals(Type.ESCALATION))
        {
            aShift.removeFromAvailableEscalationEngineers(aEngineer);
        }
    }
    
    //Methods
    
    public void changeShift(Manager aManager, Shift aShift, 
                            Engineer currentEngineer, Engineer newEngineer)
    {
        aManager.changeShift(aShift, currentEngineer, newEngineer);
    }
    
    /**
     * Returns a User object is aSSO matches a users SSO and aPassword
     * matches that Users Password.
     * @param aSSO
     * @param aPassword
     * @return the user object with the specified unique identifier.
     * @throws Exception if the username or password is incorrect.
     */
    public User login(SingleSignOn aSSO, String aPassword) throws Exception
    {
        for (User aUser : users)
        {
            if ( (aUser.getSSO().equals(aSSO) ) && 
                    (aUser.getPassword().equals(aPassword) ) )
            {
               return aUser;
            }
        }
        throw new Exception("No such user or password incorrect");
    }
    
    /**
     * Create a quarter and adds the quarters to the quarters collection.
     * @param aManager
     * @param quarterNum
     * @param isLong
     * @param startDate 
     */
    public void createQuarter(Manager aManager, int quarterNum, Boolean isLong, 
                            Date startDate)
    {
        Quarter aQuarter = aManager.createQuarter(quarterNum, isLong, startDate);
        this.quarters.add(aQuarter);
    }
    
    /**
     * Generates the shifts for a quarter.
     * @param aManager
     * @param aQuarter 
     */
    public void generateShifts(Manager aManager, Quarter aQuarter)
    {
        aManager.generateShifts(aQuarter);
    }
    
    /**
     * Assigns engineers for all the shifts in a given quarter.
     * @param aManager
     * @param aQuarter 
     */
    public void assignShifts(Manager aManager, Quarter aQuarter)
    {
        aManager.assignShifts(aQuarter);
    }

    public void getCurrentQuarter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Creates a new user and adds it to the users collection.
     * @param aManager
     * @param aName
     * @param PhoneNumber
     * @param email
     * @param aSSO
     * @param isEscalation
     * @param isFirstLine
     * @param isManager 
     */
    public void createUser(Manager aManager, String aName, String PhoneNumber,
                            String email, SingleSignOn aSSO, boolean isEscalation,
                            boolean isFirstLine, boolean isManager)
    {
        // Create the new users
        User aUser = aManager.CreateUser(aName, PhoneNumber, email, aSSO, 
                            isEscalation, isFirstLine, isManager);
        
        // Preserves the new user in the users list.
        this.users.add(aUser);
        
        // Needs to save the users list each time this occurs.
    }
}
