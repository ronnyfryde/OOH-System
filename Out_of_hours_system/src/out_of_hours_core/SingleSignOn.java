/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package out_of_hours_core;

/**
 * A single sign on (SSO) is a unique identifier user by employees
 * to locate their accounts and to sign on to the system.
 * @author Skenn
 */
public class SingleSignOn implements java.io.Serializable
{
    // attributes
    private final int SSO;
    
    /**
     * Initialises the SSO value to a valid SSO.
     * @param aNumber
     * @throws Exception if the length of the SSO is not 8 digits.
     */
    public SingleSignOn(int aNumber) throws Exception
    {
        if (String.valueOf(aNumber).length() == 8)
        {
            SSO = aNumber;
        }
        else
        {
            throw new Exception("Invalid Single Sign On Value.");
        }
    }
    
    /**
     * Returns the SSO.
     * @return the single sign on in digit form.
     */
    public int getSSO()
    {
        return this.SSO;
    }
    
    /**
     * Returns a string representation of the SSO
     * @return String of SSO.
     */
    @Override
    public String toString()
    {
        return Integer.toString(getSSO());
    }
}
