/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.project;

import java.util.Map;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ron
 */
public class ManagerTest {
    
    public ManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of get_manager method, of class Manager.
     */
    @Test
    public void testGet_manager() throws Exception {
        System.out.println("get_manager");
        String account = "";
        Manager expResult = null;
        Manager result = Manager.get_manager(account);
        assertEquals(expResult, result);
    }

    /**
     * Test of new_manager method, of class Manager.
     */
    @Test
    public void testNew_manager() throws Exception {
        System.out.println("new_manager");
        String username = "";
        String password = "";
        boolean expResult = false;
        boolean result = Manager.new_manager(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of load_all method, of class Manager.
     */
    @Test
    public void testLoad_all() throws Exception {
        System.out.println("load_all");
        Map<String, Manager> expResult = null;
        Map<String, Manager> result = Manager.load_all();
        assertEquals(expResult, result);
    }

    /**
     * Test of delete_manager method, of class Manager.
     */
    @Test
    public void testDelete_manager() throws Exception {
        System.out.println("delete_manager");
        String username = "";
        boolean expResult = false;
        boolean result = Manager.delete_manager(username);
        assertEquals(expResult, result);
    }

    /**
     * Test of set_username method, of class Manager.
     */
    @Test
    public void testSet_username() {
        System.out.println("set_username");
        String s = "";
        Manager instance = null;
        instance.set_username(s);
    }

    /**
     * Test of set_password method, of class Manager.
     */
    @Test
    public void testSet_password() {
        System.out.println("set_password");
        String s = "";
        Manager instance = null;
        instance.set_password(s);
    }

    /**
     * Test of get_username method, of class Manager.
     */
    @Test
    public void testGet_username() {
        System.out.println("get_username");
        Manager instance = null;
        String expResult = "";
        String result = instance.get_username();
        assertEquals(expResult, result);
    }

    /**
     * Test of authenticate method, of class Manager.
     */
    @Test
    public void testAuthenticate() {
        System.out.println("authenticate");
        String username = "";
        String password = "";
        Manager instance = null;
        boolean expResult = false;
        boolean result = instance.authenticate(username, password);
        assertEquals(expResult, result);
    }

    /**
     * Test of take_deposit method, of class Manager.
     */
    @Test
    public void testTake_deposit() {
        System.out.println("take_deposit");
        String username = "";
        double amount = 0.0;
        Manager instance = null;
        boolean expResult = false;
        boolean result = instance.take_deposit(username, amount);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Manager.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Manager instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of repOK method, of class Manager.
     */
    @Test
    public void testRepOK() {
        System.out.println("repOK");
        Manager instance = null;
        boolean expResult = false;
        boolean result = instance.repOK();
        assertEquals(expResult, result);
    }
    
}
