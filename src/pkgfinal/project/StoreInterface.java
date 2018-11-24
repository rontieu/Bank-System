
package pkgfinal.project;
/**
 * 
 * @author Damoon Azarpazhooh || Student number 500-664-523
 * @author Ron Tieu || Student number 500-686-482
 */
public interface StoreInterface {
    /**
     * @dev this function would return the current state the application is are in
     * @return a String that represents the current state 
     */
    public String get_state();
    /**
     * @dev this function would take an action from the user and changes the current state to next state per given action
     * @param Action is a string that represents that action taken by the user
     */
    public void reducer(String Action);
}
