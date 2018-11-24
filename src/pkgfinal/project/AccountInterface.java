
package pkgfinal.project;

import java.util.Date;
/**
 * 
 * @author Damoon Azarpazhooh || Student number 500-664-523
 * @author Ron Tieu || Student number 500-686-482
 */

public interface AccountInterface {
    /**
     * @dev This function would set the account type to either "saving" or chequing.
     * @param s is a string that represents account type
     * @return a boolean value that shows the success or failure of the operation.
     */
    public boolean setAccountType(String s);
    
    
   /**
    * @dev this function would return the account type , eg: "chequing" or "saving"
    * @return a String that represents account type 
    */
    public String getAccountType();
    
    
    /**
     * @dev this function would add a given value to the account's balance
     * @param amount is a double value that is going to be added to accounts balance
     * @return a boolean value that shows the success or failure of the operation.
     */
    public boolean add (double amount);
    
    
    /**
     * @dev this function would subtract a given value from the account's balance
     * @param amount is a double value that is going to be subtracted to accounts balance
     * @return a boolean value that shows the success or failure of the operation.
     */
    public boolean subtract (double amount);
    
    
     /**
     * @dev this function would show if the account is in an overdraft state(Balance <0) or not
     * @return a boolean value that represents if the account is in overdraft state or not 
     */
    public boolean is_overdraft();
    
    
    /**
     * @dev this function would show the account's balance.
     * @return a double that represents account's balance.
     */
    public double getBalance();


}