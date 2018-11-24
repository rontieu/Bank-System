
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
import java.util.Map;
    /**
     * 
     * @author Damoon Azarpazhooh || Student number 500-664-523
     * @author Ron Tieu || Student number 500-686-482
     */
public class Manager extends ManagerAbstractClass implements Serializable  {
    /**
     * Rep Invariant
     * _username.equals(_username.toLowerCase())
     * _password.equals(_password.toLowerCase())
     * 
     * AF(c)
     * String A = _username
     * String B = _password
     * 
     * System.out.println("Account Username= "+A)
     * System.out.println("Account Password= "+B)
     * 
     */
    
    protected boolean _authenticated ; 
    protected String _username,_password;
    public Manager (String username, String password)
     {
        set_username(username);
        set_password(password);
     }
    /**
     * @dev this function would load a manager per given username from the database file.
     * @param account is a String that represents username of the account we are trying to load
     * @return a boolean value that shows the success or failure of the operation
     */
    public static Manager get_manager(String account) throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        Manager manager = null ; 
        Map<String, Manager> manager_map = new HashMap<String, Manager>();

            File file = new File("managers-db.txt");
            FileInputStream fis = new FileInputStream(file); 
            ObjectInputStream ois = new ObjectInputStream(fis);
                    manager_map = (HashMap<String, Manager>)ois.readObject();
            manager = manager_map.get(account);   
        
        
             
        return manager;
    }
    
    /**
     * @dev this function would create a new manager with the given login credentials and store it in the database
     * @param username is a String that represents username
     * @param password is a String that represents password
     * @return a boolean value that shows the success or failure of the operation
     */
    public static boolean new_manager(String username, String password) throws FileNotFoundException, IOException, ClassNotFoundException {
    Manager temp = new Manager(username,password);   
        Map<String, Manager> manager_map = new HashMap<String, Manager>();
        try (   
                FileInputStream fis = new FileInputStream("managers-db.txt"); 
                ObjectInputStream ois = new ObjectInputStream(fis)
            )
                {
                    Manager exists = null ;
                    exists = Manager.get_manager(username);
                   if(exists!=null)
                   {
                       System.out.println("A manager with simillar username already exists ... please try again!\n");
                       return false;
                   }
                  
                   manager_map = (HashMap<String, Manager>)ois.readObject();
                   ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("managers-db.txt"));   
                   manager_map.put(temp.get_username(), temp);
                   outputStream.writeObject(manager_map);
        }
        catch (FileNotFoundException e) 
        {
          
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("managers-db.txt"));   
            manager_map.put(temp.get_username(), temp);
            outputStream.writeObject(manager_map);
            return true;
        
        }
        return true;
        }
    
    /**
     * @dev this function return all the managers that have been stored in database.
     * @return a hash map of <String, Manager> 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static Map<String, Manager> load_all() throws FileNotFoundException, IOException, ClassNotFoundException
    {

        Map<String, Manager> manager_map = null ;
        try
        {
            FileInputStream fis = new FileInputStream("managers-db.txt"); 
            ObjectInputStream ois = new ObjectInputStream(fis);
            manager_map = (HashMap<String, Manager>)ois.readObject();
        }
        catch (Exception e )
        {
            System.out.println("DB not Found");
            return manager_map;
        }
        
        return manager_map;

         
    }
    
    /**
     * @dev this function would try to search and delete a manager from database based on given username value.
     * @param username is a String that represents the username of the manager we are trying to delete.
     * @return a boolean value that shows if the operation has been successful or not. 
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static boolean delete_manager(String username ) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        //Modifies None
        //Effects ; Deletes a customer Object
        Map<String, Manager> manager_map = new HashMap<String, Manager>();
                File file = new File("managers-db.txt");
                FileInputStream fis = new FileInputStream(file); 
                ObjectInputStream ois = new ObjectInputStream(fis);
                manager_map = (HashMap<String, Manager>)ois.readObject();
                Manager temp = manager_map.get(username);
                if(temp!=null)
                {
                    FileOutputStream writer = new FileOutputStream("managers-db.txt");
                    ObjectOutputStream outputStream = new ObjectOutputStream(writer);   
                    manager_map.remove(username);
                    outputStream.writeObject(manager_map);               
                    writer.close();
                }
                else
                {
                    System.out.println("Manager not found!");
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
    
   
    public boolean take_deposit(String username, double amount) {
            Customer customer = null;
            try
            {
                customer = Customer.get_customer(username);
            }
            catch(Exception e)
            {
                System.out.println("DB file not found");
                return false;
            }
            if(customer == null)
            {
                System.out.println("Customer not found");
                return false;
            }
           
            customer.receive_money(amount);
            return true;
                    
    }
     public String toString()
    {
        String result = null;
        String A = _username;
        String B = _password;
        result = ("Account Username= "+A+" Account Password= "+B);
        return result;
    }
    public boolean repOK() 
    {
        boolean result = false ; 
        result = ((_username.equals(_username.toLowerCase())) && (_password.equals(_password.toLowerCase())));
        return result;
    }
}
