
package pkgfinal.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Damoon Azarpazhooh || Student number 500-664-523
 * @author Ron Tieu || Student number 500-686-482
 */
public class Customer extends CustomerAbstractClass implements Serializable {
   /**
     * Rep Invariant
     * _overdraft = false 
     * _type = "customer"
     * _username.equals(_username.toLowerCase())
     * _password.equals(_password.toLowerCase())
     * 
     * 
     * AF(c)
     * boolean A = _overdraft
     * String B = _type
     * String C = _username
     * String D = _password
     * 
     * System.out.println("Account Overdraft = "+A)
     * System.out.println("Account Type = " +B)
     * System.out.println("Account Username= "+C)
     * System.out.println("Account Password= "+D)
     */

    private boolean _overdraft=false;
    private final String _type = "customer";
    private boolean _authenticated ; 
    private String _username,_password;
    private Account _chequing;
    private Account _saving;
    private Set<String> _approval_set ;
    public Customer (String username, String password , double chequing )
    {
        
        set_username(username);
        set_password(password);
        open_account("chequing", chequing);
        _approval_set = new HashSet<String>();
        
    }
    public Customer (String username, String password , double chequing , double saving)
    {
        set_username(username);
        set_password(password);
        open_account("chequing", chequing);
        open_account("saving", saving);
        _approval_set = new HashSet<String>();

    }
    
    /**
     * @dev this function return all the customers that have been stored in database.
     * @return a has map of <String, Customer> 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static Map<String, Customer> load_all() throws FileNotFoundException, IOException, ClassNotFoundException
    {

        Map<String, Customer> customer_map = null ;
        try
        {
            FileInputStream fis = new FileInputStream("customers-db.txt"); 
            ObjectInputStream ois = new ObjectInputStream(fis);
            customer_map = (HashMap<String, Customer>)ois.readObject();
        }
        catch (Exception e )
        {
            System.out.println("DB not Found");
            return customer_map;
        }
        
        return customer_map;

         
    }
    
    /**
     * @dev this function would load a customer per given username from the database file.
     * @param account is a String that represents username of the account we are trying to load
     * @return a boolean value that shows the success or failure of the operation
     */
    public static Customer get_customer(String account) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        Customer customer = null ; 
        Map<String, Customer> customer_map = new HashMap<String, Customer>();
        File file = new File("customers-db.txt");
        FileInputStream fis = new FileInputStream(file); 
        ObjectInputStream ois = new ObjectInputStream(fis);
        customer_map = (HashMap<String, Customer>)ois.readObject();
        customer = customer_map.get(account);                
        return customer;
    }
     
    /**
     * @dev this function would update a customer object's state in the database file.
     * @param customer is a Customer object that we want to make changes to  .
     * @return a boolean value that shows if the operation has been successful or not. 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static boolean update_customer(Customer customer) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        Map < String, Customer > customer_map = new HashMap < String, Customer > ();
        Customer temp = null;
       
                FileInputStream fis = new FileInputStream("customers-db.txt"); 
                ObjectInputStream ois = new ObjectInputStream(fis);
                customer_map = (HashMap < String, Customer > ) ois.readObject();
                    temp = customer_map.get(customer.get_username());
                    if (temp != null) 
                    {
                     FileOutputStream writer = new FileOutputStream("customers-db.txt");
                     ObjectOutputStream outputStream = new ObjectOutputStream(writer);
                     customer_map.put(customer.get_username(), customer);
                     outputStream.writeObject(customer_map);
                     writer.close();
                    } 
                    else 
                    {
                     System.out.println("Customer not found!");
                     return false;
                    }
               

        return true;
    }

    /**
     * @dev this function would create a new customer with the given login 
     * credentials and an initial chequing balance
     * @param username a string that represent a customer's username
     * @param password a string that represents a customers password
     * @param chequing a double value that represents a customer's chequing account opening balance.
     * @return a boolean value that shows if the operation has been successful or not. 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static boolean new_customer (String username, String password , double chequing ) throws IOException, ClassNotFoundException
    {
       
        Customer temp = new Customer(username,password,chequing);   
        Map<String, Customer> customer_map = new HashMap<String, Customer>();
        try (   
                FileInputStream fis = new FileInputStream("customers-db.txt"); 
                ObjectInputStream ois = new ObjectInputStream(fis)
            )
                {
                    Customer exists = null ;
                    exists = Customer.get_customer(username);
                   if(exists!=null)
                   {
                       System.out.println("A customer with simillar username already exists ... please try again!\n");
                       return false;
                   }
                  
                   customer_map = (HashMap<String, Customer>)ois.readObject();
                   ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("customers-db.txt"));   
                   customer_map.put(temp.get_username(), temp);
                   outputStream.writeObject(customer_map);
        }
        catch (FileNotFoundException e) 
        {
          
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("customers-db.txt"));   
            customer_map.put(temp.get_username(), temp);
            outputStream.writeObject(customer_map);
            return true;
        
        }
        return true;
    }
    /**
     * @dev this function would create a new customer with the given login 
     * credentials and an initial chequing adn saving balance
     * @param username a string that represent a customer's username
     * @param password a string that represents a customers password
     * @param chequing a double value that represents a customer's chequing account opening balance.
     * @param saving a double value that represents a customer's saving account opening balance.
     * @return a boolean value that shows if the operation has been successful or not. 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static boolean new_customer (String username, String password , double chequing , double saving) throws IOException, ClassNotFoundException
    {
        Customer temp = new Customer(username,password,chequing,saving);   
        Map<String, Customer> customer_map = new HashMap<String, Customer>();
            try (FileInputStream fis = new FileInputStream("customers-db.txt"); 
                ObjectInputStream ois = new ObjectInputStream(fis)) {
                customer_map = (HashMap<String, Customer>)ois.readObject();
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("customers-db.txt"));   
                customer_map.put(temp.get_username(), temp);
                outputStream.writeObject(customer_map);
            }
        catch (FileNotFoundException e) 
        {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("customers-db.txt"));   
            customer_map.put(temp.get_username(), temp);
            outputStream.writeObject(customer_map);
            return true;
        }
        return true;
    }
    
    /**
     * @dev this function would try to search and delete a customer from database based on given username value.
     * @param username is a String that represents the username of the customer we are trying to delete.
     * @return a boolean value that shows if the operation has been successful or not. 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static boolean delete_customer(String username ) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        //Modifies None
        //Effects ; Deletes a customer Object
        Map<String, Customer> customer_map = new HashMap<String, Customer>();
                File file = new File("customers-db.txt");
                FileInputStream fis = new FileInputStream(file); 
                ObjectInputStream ois = new ObjectInputStream(fis);
                customer_map = (HashMap<String, Customer>)ois.readObject();
                Customer temp = customer_map.get(username);
                if(temp!=null)
                {
                    FileOutputStream writer = new FileOutputStream("customers-db.txt");
                    ObjectOutputStream outputStream = new ObjectOutputStream(writer);   
                    customer_map.remove(username);
                    outputStream.writeObject(customer_map);               
                    writer.close();
                }
                else
                {
                    System.out.println("Customer not found!");
                    return false;
                }    
            
      
          
        
        return true;
    }
    
    protected void set_username(String s)
    {
       _username=s.toLowerCase();
    }
    
    
    protected void set_password(String s)
    {
        _password = s; 
    }
    
    
    public String get_username()
    {
        return this._username;
    }
    public boolean authenticate(String username, String password)
    {
        
        if (username.equals(_username) && password.equals(_password) ) 
        {
            _authenticated = true;
            return true;
        }
        else 
        {
            System.out.println("Wrong Username / Password!\n");
            return false;
        }
    }
   
    public boolean open_account(String account,double balance)
    {

         if (!(account.equals("saving")||account.equals("chequing")))
         {
            System.out.println("Account type is not Chequing or Saving!\n");
            return false;
         }
         if(balance<=0)
         {
            System.out.println("Accounts initial balance cannot be less than or equal to zero!\n");
            return false;
         }
         if (account.equals("saving"))
         {
             if ( _saving != null)
             {
                System.out.println("Saving account already exists!\n");
                return false;
             }
             _saving = new Account("saving", balance);
         }
         if (account.equals("chequing"))
         {
            if ( _chequing != null)
            {
               System.out.println("Chequing account already exists!\n");
               return false;
            }
            _chequing = new Account("chequing", balance);
         }
        return true;
    }
    public boolean close_account(String account)
    {
        if (account.equals("chequing"))
        {
            System.out.println("You are not authorized to close chequing account!\n");
            return false;
        }
         double temp = _saving.getBalance();
        _saving = null ; 
        _chequing.add(temp);
        return true;
    }
    public  boolean receive_money(double amount)
    {
        if (amount<=0)
        {
            System.out.println("Recieve value cannot be less than or equal to zero!\n");
            return false;
        }
        _chequing.add(amount);
        return true ; 
    }
    public  boolean is_overdarft()
    {
        boolean temp = _overdraft;
        return temp;
    }
    public boolean send_money(double amount)
    {
        Scanner scanner = new Scanner(System.in);
       
        if(_chequing.getBalance()<0)
        {
            _overdraft = true;
            System.out.println("Your account is in overdraft ... you cannot send any more money!\n");
            return false;
        }
        if (amount<=0)
        {
            System.out.println("Send value cannot be less than or equal to zero!\n");
            return false;
        }
        if(amount > _chequing.getBalance())
        {
            System.out.println("The account money is being taken out of will go to overdraft state if you proceed forward with this transaction... do you want to continue ?\n[Y] / [N]\n");
            String input = scanner.nextLine();
            input = input.toLowerCase();
            if(input.equals("y"))
            {
                 _chequing.subtract(amount);
                 _overdraft = true;
                return true;
            }
            else if (input.equals("n"))
            {
               System.out.println("Transaction Aborted!\n");
               return false;
            }
            else
            {
               System.out.println("invalid keyword detected! please try again ...");
               return false;
            }

        }
        _chequing.subtract(amount);
        return true ; 
    }
    
    public Account get_account(String s) throws CloneNotSupportedException
    {
        Account result = null;
        if(s.equals("chequing"))
        {
                if(this._chequing.getBalance()>=0)
                {
                    _overdraft=false;
                }
                if (this._chequing != null)
                {
                    result = this._chequing.clone();
                }
            
            
        }
        if(s.equals("saving"))
        {
            if (this._saving != null)
                {
                    result = this._saving.clone();
                }
            
        }
        return result;
    }
    
 
    public  boolean transfer(boolean isChequing , double value)
    {
        if(_saving ==null)
        {
            System.out.println("No Saving account found. Please open one before attempting to transfer money!\n");
            return false;
        }
        if (isChequing)
        {
            if(value>_chequing.getBalance())
            {
                System.out.println("Insufficient funds in chequing account!\n");
                return false;
            }
            _chequing.subtract(value);
            _saving.add(value);
        }
        if(!isChequing)
        {
             if(value>_saving.getBalance())
            {
                System.out.println("Insufficient funds in saving account!\n");
                return false;
            }
            _saving.subtract(value);
            _chequing.add(value);
        }
        return true;
    } 
   
    public boolean set_approval(String receiverCustomer )
    {
        Customer customer = null;
        try 
        {
            customer = get_customer(receiverCustomer);    
        } 
        catch (Exception e) 
        {
            System.out.println("DB not Found");
            return false;
        } 
        if(customer == null)
        {
            System.out.println("Customer not Found");
            return false;
        }
        if(customer.get_username().equals(this.get_username()) )
        {
            System.out.println("Redundant operation ... you already have full access to your account !\n");
            return false;
        }
        _approval_set.add(customer.get_username());
        try {
            Customer.update_customer(this);
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean revoke_approval(String receiverCustomer) {
        Customer customer = null;
        try 
        {
            customer = get_customer(receiverCustomer);    
        } 
        catch (Exception e) 
        {
            System.out.println("DB not Found");
            return false;
        } 
        if(customer == null)
        {
            System.out.println("Customer not Found");
            return false;
        }
        if(customer.get_username().equals(this.get_username()) )
        {
            System.out.println("Redundant operation ... you cannot revoke your access to your own account!\n");
            return false;
        }
        _approval_set.remove(customer.get_username());
        try {
            Customer.update_customer(this);
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;    
    }

    public boolean get_approval(String receiverCustomer) {
        Customer customer = null;
        try 
        {
            customer = get_customer(receiverCustomer);    
        } 
        catch (Exception e) 
        {
            System.out.println("DB not Found");
            return false;
        } 
        if(customer == null)
        {
            System.out.println("Customer not Found");
            return false;
        }
        if(customer.get_username().equals(this.get_username()) )
        {
            System.out.println("Redundant operation ... you are already approved to move money ot of your account !\n");
            return false;
        }
        return _approval_set.contains(customer.get_username());
        
    }
    public boolean approval_list() {
        if(_approval_set.isEmpty())
        {
            System.out.println("Approval set is empty");
            return false;
        }
        
        else
        {
            _approval_set.forEach(System.out::println);
        }
        return true;
    }
    public String toString()
    {
        String result = null;
        boolean A = _overdraft;
        String B = _type;
        String C = _username;
        String D = _password;
        result = ("Account Overdraft = "+A+" Account Type = " +B+" Account Username= "+C+" Account Password= "+D);
        return result;
    }
    public boolean repOK() 
    {
        boolean result = false; 
        result = (_type.equals("customer") && (_overdraft == false) && (_username.equals(_username.toLowerCase())) && (_password.equals(_password.toLowerCase())));
        return result;
    }
}