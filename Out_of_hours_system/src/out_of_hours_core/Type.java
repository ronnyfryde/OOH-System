/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package out_of_hours_core;

/**
 * Provides three constants FIRSTLINE, ESCALATION, DISABLED.
 */
public enum Type implements java.io.Serializable
{
    /**
     * Represents FIRSTLINE.
     */
    FIRST,
    /**
     * Represents ESCALATION.
     */
    ESCALATION,
    /**
     * Represent DISABLED.
     */
    DISABLED;
    
    
    //public protocol
    
    /**
     * Returns a string version of the engineer type
     * FIRSTLINE
     * ESCALATION
     * DISABLED.
     *
     * @return a String object representing the receiver
     */
    @Override
    public String toString()
    {
        switch (this)
        {
            case FIRST: return "FIRSTLINE";
            case ESCALATION: return "ESCALATION";
            default: return "DISABLED";
        }
    }
}
