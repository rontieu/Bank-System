
package pkgfinal.project;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author Damoon Azarpazhooh || Student number 500-664-523
 * @author Ron Tieu || Student number 500-686-482
 */
public abstract class CustomerAbstractClass implements UserInterface,Serializable {
   
     
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
      * @dev this function would check and see if the customer has a chequing account in overdraft state or not.
      * @return a boolean value that represents the overdraft state of account
      */
    public abstract boolean is_overdarft();    

    
    /**
     * @dev this function would return a customer's account; eg : saving or chequing.
     * @param s is a string that represents accounts type.
     * @return  an Account object that is  clone of saving or chequing account. 
     */
    public abstract Account get_account(String s) throws CloneNotSupportedException;

  
    /**
     * @dev this function would open an account for customer and initialize it with a balance
     * @param account is a String that represents account type
     * @param balance is a double that represents the opening balance of an account
     * @return a boolean value that shows the success or failure of the operation
     */
    public abstract boolean open_account(String account,double balance);
   
    
    /**
     * @dev closes an account , user cannot close chequing . If saving is 
     * being closed, transfer the value to chequing. 
     * @param account is a String that represents account type
     * @return a boolean value that shows the success or failure of the operation
     */
    public abstract boolean close_account(String account);
   
    /**
     * @dev this function is called when the user receives money from a another user.
     * @param amount a double representing amount of money that is going to be added to chequing account
     * @return a boolean value that shows the success or failure of the operation
     */
    public abstract boolean receive_money(double amount);

  
    /**
     * @dev this function is called when the user is sending money to another user.
     * @param amount a double representing amount of money that is going to be removed from chequing account
     * @return a boolean value that shows the success or failure of the operation
     */
    public abstract boolean send_money(double amount);
  
    
    /**
     * @dev this function is used for internal money transfers.
     * @param isChequing is a boolean that represents if the account money is being take out of is chequing or not.
     * @param value is double that represents the amount of money being transfered.
     * @return is a boolean value that shows the success or failure of operation
     */
    public abstract boolean transfer(boolean isChequing , double value);
    
    /**
     * @dev this function is used to allow other customers to take out money 
     * at will to a certain limit from the customers account (eg: EFT withdrawals)  
     * @param receiverCustomer is a String that represent the 
     * username of customer getting approval to transfer at will
     * @return is a boolean value that shows the success or failure of operation
     */
    public abstract boolean set_approval(String receiverCustomer );

    /**
     * @dev this function is used to withdraw other customers approval to take out money 
     * at will to a certain limit from the customers account (eg: EFT withdrawals)  
     * @param receiverCustomer is a String that represent the 
     * username of customer getting approval to transfer at will
     * @return is a boolean value that shows the success or failure of operation
     */
    public abstract boolean revoke_approval(String receiverCustomer );

    /**
     * @dev this function would show a list of customers that are approved by the 
     * logged in customer to take out money from his/her account
     * @param receiverCustomer a string that represents username of receiver customer
     * @return a boolean that represents if that customer can take out amount at will
     */
    public abstract boolean get_approval(String receiverCustomer);

    
}
