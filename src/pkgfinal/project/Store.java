package pkgfinal.project;
/**
 * 
 * @author Damoon Azarpazhooh || Student number 500-664-523
 * @author Ron Tieu || Student number 500-686-482
 */
public class Store implements StoreInterface{
    /**
     * Rep Invariant
     * this.state = "logged_in_manager";
     * this.state = "logged_in_regular_customer";
     * this.state = "exit";
     * this.state = "wrong_password";
     * this.state = "logged_out";
     * 
     * AF(c)
     * String A = this.state
     * System.out.println("Current State = "+A)
     * 
     */
    
    private String state;
    
    public String get_state()
    {
       String temp = state;
       return temp;
    }
    public void reducer (String Action)
    {
        //TODO : Add Default
        switch (this.state){
            
            case "logged_out" :
            {
                if(Action.equals("manager"))
                    this.state = "logged_in_manager";
                else if (Action.equals("regular_customer"))
                    this.state = "logged_in_regular_customer";
            
                else if (Action.equals("exit"))
                    this.state = "exit";
                else 
                    this.state = "wrong_password";
                break;

            }
            case "logged_in_manager" :
            {
                if(Action.equals("log_out"))
                    this.state = "logged_out";
                else if (Action.equals("exit"))
                    this.state = "exit";
                break;
            }
            case "logged_in_regular_customer" :
            {
                if(Action.equals("log_out"))
                    this.state = "logged_out";
                else if (Action.equals("exit"))
                    this.state = "exit";
                break;
            }
           
            case "wrong_password" :
            {
                if(Action.equals("manager"))
                    this.state = "logged_in_manager";
                else if (Action.equals("regular_customer"))
                    this.state = "logged_in_regular_customer";
     //TODO: DELETE THESE LINES IF NOT NEEDED
     //           else if (Action.equals("business_customer")) 
     //               this.state = "logged_in_business_customer";
                else if (Action.equals("exit"))
                    this.state = "exit";
                else 
                    this.state = "wrong_password";
                break;
            }
        }
                    
    }
    public Store()
    {
        this.state = "logged_out";
    }
     public String toString()
    {
        String result = null;
        String A = this.state;
        result = ("Current State = "+A);
        return result;
    }
    public boolean repOK() 
    {
        boolean result = false ; 
        result = (("logged_in_manager".equals(this.state)) || ("logged_in_regular_customer".equals(this.state)) || ("exit".equals(this.state)) || ("wrong_password".equals(this.state)) || ("logged_out".equals(this.state)));
        return result;
    }
}
