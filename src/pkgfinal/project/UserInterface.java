package pkgfinal.project;
/**
 * 
 * @author Damoon Azarpazhooh || Student number 500-664-523
 * @author Ron Tieu || Student number 500-686-482
 */
public interface UserInterface {
    
    
    
    /**
     * @dev this function would return the current users username;
     * @return a string that represents customers username
     */
    public String get_username();
    
    /**
     * @dev checks if the given credentials are correct to login or not.
     * @param username is a String that represents user input
     * @param password is a password that represents user input
     * @return a boolean value that shows if the authentication has been successful or not. 
     */
    public boolean authenticate(String username, String password);
}