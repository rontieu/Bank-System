package pkgfinal.project;

import java.io.FileNotFoundException;
import java.io.IOException;
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
public class FinalProject {
   /**
    * @dev this function would check and see if a given string is a number or not.
    * @param s is a string that represents input
    * @return a boolean value that represents if the is string is a number or not
    */
    public static boolean isValidNumber(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
    
    /**
     * @dev when a user wants to log in, this function would get username,password and login mode from console.
     * @return an array of string the represent users input 
     */
    public static String[] render_logged_out_state() {
        Scanner scanner = new Scanner(System.in);
        String[] result = new String[3];
        do {
            System.out.println("Enter your username: ");
            result[0] = scanner.nextLine();
            System.out.println("Enter your password: ");
            result[1] = scanner.nextLine();
            System.out.println("Enter login mode:\n[manager]\t/\t[customer]");
            result[2] = scanner.nextLine();
            System.out.println("\n");
        }
        while (!((result[2].toLowerCase().equals("manager")) || (result[2].toLowerCase().equals("customer"))));


        return result;
    }
    
    /**
     * @dev this function would try to search the database and see if a manager with the given username and password exists.
     * @param username is a string that is passed by program's user through console and represents username
     * @param password is a string that is passed by program's user through console and represents password
     * @return a boolean value that is the result of searching for a manager existing with the given username and password in the database.
     */
    public static boolean authenticateManager(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return true;
        } else {
            Manager temp = null;
            try {
                temp = Manager.get_manager(username);
            } catch (Exception e) {
                System.out.println("DB file not found");
                return false;
            }
            if (temp == null) {
                System.out.println("A manager with the given username does not exist!");
                return false;
            }

            return temp.authenticate(username, password);
        }
    }


    /**
     * @dev this function would try to search the database and see if a customer with the given username and password exists.
     * @param username is a string that is passed by program's user through console and represents username
     * @param password is a string that is passed by program's user through console and represents password
     * @return a boolean value that is the result of searching for a customer existing with the given username and password in the database.
     */
    public static boolean authenticateCustomer(String username, String password) throws FileNotFoundException, IOException, ClassNotFoundException

    {
        Customer temp = null;
        try 
        {
            temp = Customer.get_customer(username);
        } 
        catch (Exception e)
        {
            System.out.println("DB not Found");
            return false;
        }

        if (temp != null) 
        {
            return temp.authenticate(username, password);
        } 
        else 
        {
            System.out.println("Customer Not found!\n");
            return false;
        }

    }
    /**
     * @dev this function would show managers menu and allows a manager to interact with the program
     * @return a boolean value that is true unless the user signs out or the program throws an exception
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static boolean render_logged_in_manager_state() throws FileNotFoundException, IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        //Manager manager = new Manager();
        boolean loggedIn = true;
        while (loggedIn) 
        {
            System.out.println("Instructions:");
            System.out.println("0-Logout:\n\texit ");
            System.out.println("1-Create a new manager:\n\tnew manager,{username},{password}");
            System.out.println("2-Delete a manager:\n\tdelete manager,{username}\n\t**Username is not case sensetive!\n");
            System.out.println("3-List managers:\n\tlist managers ");
            System.out.println("4-List customers:\n\tlist customers");
            System.out.println("5-Create a customer :\n\tnew customer,{username},{Password},{chequing}\n\tnew customer,{username},{Password},{chequing},{saving}\n\t**Username is not case sensetive!\n");
            System.out.println("6-Delete a customer:\n\tdelete customer,{username}\n\t**Username is not case sensetive!\n");

            String[] input = scanner.nextLine().split(",");
            boolean isValid = valid_manager_instruction(input);
            while (!isValid) 
            {
                System.out.println("invalid keyword detected! please try again ...");
                input = scanner.nextLine().split(",");
                isValid = valid_manager_instruction(input);
            }
            if (input[0].equals("exit")) 
            {
                return false;
            }
            else if (input[0].equals("new manager")) 
            {
                Manager.new_manager(input[1], input[2]);
                return true;
            } 
            else if (input[0].equals("delete manager")) 
            {
                Manager temp = null;
                try 
                {
                    temp = Manager.get_manager(input[1]);
                } 
                catch (Exception e)
                {
                    System.out.println("DB not Found");
                    return false;
                }
                if (temp == null)
                {
                    System.out.println("Manager does not exist");

                } 
                else 
                {
                    try 
                    {
                        Manager.delete_manager(temp.get_username());
                    } 
                    catch (Exception e) 
                    {
                        System.out.println("DB not Found");
                        return false;
                    }
                }
                System.out.println();
                System.out.println();
                return true;
            } 
            else if (input[0].equals("list customers")) 
            {
                Map < String, Customer > temp = null;
                temp = Customer.load_all();
                if (temp == null) 
                {
                    return false;
                }
                if (temp != null) 
                {
                    Set < String > keys = temp.keySet();
                    for (String key: keys) 
                    {
                        System.out.println(key);
                    }
                    System.out.println();
                    System.out.println();
                }
                return true;
            }
            else if (input[0].equals("list managers")) 
            {
                Map < String, Manager > temp = null;
                temp = Manager.load_all();
                if (temp == null)
                {
                    return false;
                }
                if (temp != null) 
                {
                    Set < String > keys = temp.keySet();
                    for (String key: keys) {
                        System.out.println(key);
                    }

                    System.out.println();
                    System.out.println();
                }

                return true;
            } 
            else if (input[0].equals("new customer")) 
            {
                if (input.length == 4) 
                {
                    Customer.new_customer(input[1], input[2], Double.valueOf(input[3]));
                }
                else if (input.length == 5)
                {
                    Customer.new_customer(input[1], input[2], Double.valueOf(input[3]), Double.valueOf(input[4]));
                }
                return true;
            }
            if (input[0].equals("delete customer")) 
            {
                Customer temp = null;
                try 
                {
                    temp = Customer.get_customer(input[1]);
                } 
                catch (Exception e) 
                {
                    System.out.println("DB not Found");
                    return false;
                }
                if (temp == null) 
                {
                    System.out.println("Customer does not exist");

                }
                else 
                {
                    try 
                    {
                        Customer.delete_customer(temp.get_username());
                    } 
                    catch (Exception e)
                    {
                        System.out.println("DB not Found");
                        return false;
                    }
                }
                System.out.println();
                System.out.println();
                return true;
            }
        }
        return true;

    }
    /**
     * @dev this function would check the given instruction by the manager to see if it matches
     * the menu items and operations that are permitted to be executed. 
     * @param input is an array of string that is the given instruction
     * @return a boolean value that shows if the given instruction is valid or not.
     */
    public static boolean valid_manager_instruction(String[] input) {

        if (input.length == 1) 
        {
            input[0] = input[0].toLowerCase();
            switch (input[0]) 
            {
                case "exit":
                    return true;
                case "list customers":
                    return true;
                case "list managers":
                    return true;
                default:
                    return false;
            }
        } 
        else if (input.length == 2)
        {
            input[0] = input[0].toLowerCase();
            input[1] = input[1].toLowerCase();
            return input[0].equals("delete customer") || input[0].equals("delete manager");
        } 
        else if (input.length == 3) 
        {
            input[0] = input[0].toLowerCase();
            return input[0].equals("new manager");
        } 
        else if (input.length == 4 || input.length == 5) 
        {
            input[0] = input[0].toLowerCase();
            input[1] = input[1].toLowerCase();
            if (input[0].equals("new customer")) 
            {
                if (isValidNumber(input[3])) 
                {
                    return true;
                }
                else if (isValidNumber(input[3]) && isValidNumber(input[4])) 
                {
                    return true;
                } 
                else 
                {
                    return false;
                }
            } 
            else 
            {
                return false;
            }
        }
        return false;
    }
    
    /**
     * @dev this function would check the given instruction by the customer to see if it matches
     * the menu items and operations that are permitted to be executed.
     * @param input is an array of string that is the given instruction
     * @return a boolean value that shows if the given instruction is valid or not.
     */
    public static boolean valid_customer_instruction(String[] input) {

        switch (input.length) {
            case 1:
            {
                 input[0] = input[0].toLowerCase();
                switch (input[0]) 
                {
                    case "balance":
                        return true;
                    case "exit":
                        return true;
                    case "list approvals":
                        return true;
                    default:
                        return false;
                }
            }      
            case 2:
            {
                input[0] = input[0].toLowerCase();
                return (input[0].equals("approve")) || (input[0].equals("revoke"));
            }
            case 3:
            {
                input[0] = input[0].toLowerCase();
                input[1] = input[1].toLowerCase();
                if (input[0].equals("move") && (input[1].equals("chequing") || input[1].equals("saving"))) {
                    if (!isValidNumber(input[2])) {
                        return false;
                    }
                    return Double.parseDouble(input[2]) >= 0;
                } else if (input[0].equals("deposit") || input[0].equals("withdraw")) {
                    if (!isValidNumber(input[2])) {
                        return false;
                    }
                    return Double.parseDouble(input[2]) >= 0;
                } else {
                    return false;
                }
            }     
            default:
                break;
        }
        return false;
    }
    /**
     * @dev this function would show a specific customer menu
     * @param _username is a string that represents the customer that just logged in.
     * @return a boolean value that shows the success or failure of the operation
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static boolean render_logged_in_customer_state(String _username) throws FileNotFoundException, IOException, ClassNotFoundException {


        Scanner scanner = new Scanner(System.in);
        Customer temp = null;
        try {
            temp = Customer.get_customer(_username);
        } catch (Exception e) {
            System.out.println("DB not Found");
            return false;
        }
        if (temp.is_overdarft()) {
            System.out.println("***********************************************************************************************************");
            System.out.println("**\tYour account is in overdraft ... please vist a branch manager and deposit money to avoid penalties!\t**");
            System.out.println("***********************************************************************************************************");
        }
        System.out.println("Instructions:");
        System.out.println("0-Logout:\texit");
        System.out.println("1-Show balances:\n\tbalance");
        System.out.println("2-Move Money :\nTo move from Saving:\n\tmove,saving,{value}\n\nTo move from Chequing\n\tmove,chequing,{value}");
        System.out.println("3-To send money to another user :\n\tdeposit,{username},{amount}");
        System.out.println("4-To allow users to withdraw money from your chequing account :\n\tapprove,{username}");
        System.out.println("5-To revoke a users approval to withdraw money from your chequing account :\n\trevoke,{username}");
        System.out.println("6-To list all the users that can withdraw money from your account :\n\tlist approvals");
        System.out.println("7-To withdraw money from another user :\n\twithdraw,{username},{amount}");

        String[] input = scanner.nextLine().split(",");
        boolean isValid = valid_customer_instruction(input);
        while (!isValid) {
            System.out.println("Invalid keyword detected! please try again ...");
            input = scanner.nextLine().split(",");
            isValid = valid_customer_instruction(input);
        }
        if (input[0].equals("exit")) {
            return false;
        }
        switch (input[0]) {
            case "balance":
                try {
                    if (temp.get_account("chequing") != null) {
                        System.out.println("Chequing Balance =\t" + temp.get_account("chequing").getBalance());
                    }
                    if (temp.get_account("saving") != null) {
                        System.out.println("Saving Balance =\t" + temp.get_account("saving").getBalance());
                    }
                    return true;
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(FinalProject.class.getName()).log(Level.SEVERE, null, ex);
                }
            case "move":
                if (input[1].equals("saving")) {
                    if (isValidNumber(input[2])) {
                        double transfer = Double.parseDouble(input[2]);
                        temp.transfer(false, transfer);
                        try {
                            Customer.update_customer(temp);
                        } catch (Exception e) {
                            System.out.println("DB File not found");
                            return false;
                        }
                    }
                } else if (input[1].equals("chequing")) {
                    if (isValidNumber(input[2])) {
                        double transfer = Double.parseDouble(input[2]);
                        temp.transfer(true, transfer);
                        try {
                            Customer.update_customer(temp);
                        } catch (Exception e) {
                            System.out.println("DB File not found");
                            return false;
                        }

                    }
                }
                return true;
            case "withdraw":
                {
                    Customer customer = null;
                    try {
                        customer = Customer.get_customer(input[1]);
                    } catch (Exception e) {
                        System.out.println("DB File not found");
                        return false;
                    }
                    if (customer == null) {
                        System.out.println("Customer Not found!\n");
                    } else {
                        if (!isValidNumber(input[2])) {
                            System.out.println("Wrong transfer value , please try again!\n");
                        } else if (customer.get_username().equals(temp.get_username())) {
                            System.out.println("Sender and reciever accounts cannot be the same...Please try again!\n");
                        } else if (customer.is_overdarft()) {
                            System.out.println("The account you are trwing to withdraw money from is in overdraft state ... please try again later!\n");
                        } else {
                            if (customer.get_approval(temp.get_username())) {
                                double transfer = Double.parseDouble(input[2]);
                                temp.receive_money(transfer);
                                customer.send_money(transfer);
                                Customer.update_customer(temp);
                                Customer.update_customer(customer);
                            } else {
                                System.out.println("You have not been authorized by the customer to take money out of his/her account");
                            }

                        }

                    }
                    return true;
                }
            case "deposit":
                {
                    Customer customer = null;
                    try {
                        customer = Customer.get_customer(input[1]);
                    } catch (Exception e) {
                        System.out.println("DB File not found");
                        return false;
                    }
                    if (customer == null) {
                        System.out.println("Customer Not found!\n");
                    } else {
                        if (!isValidNumber(input[2])) {
                            System.out.println("Wrong transfer value , please try again!\n");
                        } else if (customer.get_username().equals(temp.get_username())) {
                            System.out.println("Sender and reciever accounts cannot be the same...Please try again!\n");
                        } else {
                            double transfer = Double.parseDouble(input[2]);
                            temp.send_money(transfer);
                            customer.receive_money(transfer);
                            Customer.update_customer(temp);
                            Customer.update_customer(customer);
                        }

                    }
                    return true;
                }
            case "approve":
                {
                    Customer customer = null;
                    try {
                        customer = Customer.get_customer(input[1]);
                    } catch (Exception e) {
                        System.out.println("DB File not found");
                        return false;
                    }
                    if (customer == null) {
                        System.out.println("Customer Not found!\n");
                    } else {
                        if (temp.get_approval(input[1])) {
                            System.out.println("You have already approved the customer to withdraw from you account!\n");
                        } else {
                            temp.set_approval(input[1]);
                            //Customer.update_customer(temp);
                        }

                    }
                    return true;
                }
            case "revoke":
                {
                    Customer customer = null;
                    try {
                        customer = Customer.get_customer(input[1]);
                    } catch (Exception e) {
                        System.out.println("DB File not found");
                        return false;
                    }
                    if (customer == null) {
                        System.out.println("Customer Not found!\n");
                    } else {
                        if (temp.get_approval(input[1])) {
                            temp.revoke_approval(input[1]);
                            //Customer.update_customer(temp);
                        } else {

                            System.out.println("The customer is not in the list of withdrawal approvals.!\n");
                        }

                    }
                    return true;
                }
            case "list approvals":
                temp.approval_list();
                return true;
            default:
                break;
        }
        return true;
    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        Store store = new Store();
        System.out.println("Bank Interface\n\n");
        String _username = null, _password = null, _type = null;
        while (!(store.get_state().equals("exit"))) {
            switch (store.get_state()) {
                case "logged_out":
                    {

                        String[] credentials = render_logged_out_state();
                        _username = credentials[0];
                        _password = credentials[1];
                        _type = credentials[2];
                        if (credentials[2].toLowerCase().equals("manager")) {
                            if (authenticateManager(_username, _password)) {
                                store.reducer("manager");
                            } else {
                                _username = null;
                                _password = null;
                                store.reducer("wrong_password");
                            }
                            break;

                        } else if (credentials[2].toLowerCase().equals("customer")) {
                            if (authenticateCustomer(_username, _password)) {
                                store.reducer("regular_customer");
                            } else {
                                _username = null;
                                _password = null;
                                store.reducer("wrong_password");
                            }
                            break;


                        }


                    }
                case "wrong_password":
                    {
                        String[] credentials = render_logged_out_state();
                        _username = credentials[0];
                        _password = credentials[1];
                        if (authenticateManager(_username, _password))
                            store.reducer("manager");
                        else if (authenticateCustomer(_username, _password))
                            store.reducer("regular_customer");
                        else {
                            _username = null;
                            _password = null;
                            store.reducer("wrong_password");
                        }
                        break;
                    }
                case "logged_in_manager":
                    {
                        System.out.println("Authenticated!");
                        System.out.println("Welcome Manager!");
                        System.out.println("****************************************************");
                        boolean temp = !render_logged_in_manager_state();
                        if (temp) {
                            _username = null;
                            _password = null;
                            store.reducer("log_out");

                        }
                        break;

                    }
                case "logged_in_regular_customer":
                    {
                        System.out.println("Authenticated!");
                        System.out.println("Welcome Valuable Customer!");
                        System.out.println("****************************************************");
                        boolean temp = !render_logged_in_customer_state(_username);
                        if (temp) {
                            _username = null;
                            _password = null;
                            store.reducer("log_out");
                        }
                        break;
                    }
                default:
                    {
                        break;
                    }

            }
        }
    }
}