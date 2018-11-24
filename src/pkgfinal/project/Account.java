
package pkgfinal.project;
import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @author Damoon Azarpazhooh || Student number 500-664-523
 * @author Ron Tieu || Student number 500-686-482
 */
public class Account implements AccountInterface,Serializable, Cloneable{
    /**
     * Rep Invariant
     * if !((_type.equals("chequing")) || _type.equals("savings"));
     * return true
     * else
     * return false
     * 
     * AF(c)
     * String A = _type
     * Boolean B = _overdraft
     * double C = _balance
     * 
     * System.out.println("Account Type = " +A)
     * System.out.println("Account Overdraft = "+B)
     * System.out.println("Account Balance = "+C)
     */
    
    
    private String _type ; 
    private double _balance;
    private boolean _overdraft;
    public Account(String type , double balance)
    {
        setAccountType(type);
        _balance = balance;
    }
    public boolean setAccountType(String s)
    {
        if (!(s.equals("saving")||s.equals("chequing")))
            return false;
        this._type=s;
        return true;
    }
    public String getAccountType()
    {
        String temp = this._type;
        return temp;
    }
    public boolean add (double amount)
    {
        if(amount<=0)
            return false;
        _balance =_balance +amount ; 
        return true;
    }
    public boolean subtract (double amount)
    {
        if(amount<=0)
            return false;
        if (_type.equals("saving"))
        {
            if (_balance<amount)
                return false;
            _balance =_balance - amount ; 

        }
        if(this._type.equals("chequing"))
        {
            _balance =_balance - amount ; 
            if(_balance<0)
            {
                 _overdraft=true;

            }
        }
        return true;
    }
    public boolean is_overdraft()
    {
        return _overdraft;
    }
    
    public double getBalance()
    {
        return _balance;
    }
    public Account clone() throws CloneNotSupportedException
    {
        // just calling super.clone is enough since we don't have any refrence type in state variables.
        Account temp = (Account) super.clone();
        return temp;
    }
    public String toString()
    {
        String result = null;
        String A = _type;
        Boolean B = _overdraft;
        double C = _balance;
        result = ("Account Type = "+A+" Account Overdraft = "+B+" Account Balance = "+C);
        return result;
    }
    public boolean repOK() 
    {
        boolean result; 
        result = ((_type.equals("chequing")) || _type.equals("savings"));
        return result;
    }
}
