/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proof;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author npetalidis
 */
public class TaskHandlerTest {

    public TaskHandlerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class TaskHandler.
     */
    @Test
    public void testGetInsance() {
        System.out.println("getInsance");
        String token = "";
        TaskHandler expResult = null;
        TaskHandler result = TaskHandler.getInstance(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveFriendFeeds method, of class TaskHandler.
     */
    @Test
    public void testSaveFriendFeeds() throws Exception {
        System.out.println("saveFriendFeeds");
        TaskHandler instance = null;
        instance.saveFriendFeeds();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveSiteFeeds method, of class TaskHandler.
     */
    @Test
    public void testSaveSiteFeeds() throws Exception {
        System.out.println("saveSiteFeeds");
        TaskHandler instance = null;
        instance.saveSiteFeeds();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveNews method, of class TaskHandler.
     */
    @Test
    public void testSaveNews() throws Exception {
        System.out.println("saveNews");
        TaskHandler instance = null;
        instance.saveNews();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}