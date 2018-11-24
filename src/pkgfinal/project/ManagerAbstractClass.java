
package pkgfinal.project;

import java.io.Serializable;

/**
 * 
 * @author Damoon Azarpazhooh || Student number 500-664-523
 * @author Ron Tieu || Student number 500-686-482
 */
public abstract class ManagerAbstractClass implements UserInterface ,Serializable{
  
  
    /**
     * @dev this function would set the current user's username
     * @param s is a String that represents username
     */
    protected abstract void set_username(String s);
    
    /**
     * @dev this function would set the current users password
     * @param s is a String that represents password
     */
    protected abstract void set_password(String s);
    /**
     * @dev this function would let the manager to add money to a customers chequing account.
     * @param username is the username of customer that is depositing money
     * @param amount is the amount of money being deposited.
     * @return a boolean value that shows the success or failure of the operation
     */
    public abstract boolean take_deposit(String username,double amount);
    
}
