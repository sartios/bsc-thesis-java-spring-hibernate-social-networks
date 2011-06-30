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
public class RestHandlerTest {

    public RestHandlerTest() {
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
     * Test of getWall method, of class RestHandler.
     */
    @Test
    public void testGetFeed() {
        System.out.println("getFeed");
        String token = "";
        String id = "";
        String expResult = "";
        String result = RestHandler.getWall(token, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFriends method, of class RestHandler.
     */
    @Test
    public void testGetFriends() {
        System.out.println("getFriends");
        String token = "";
        String expResult = "";
        String result = RestHandler.getFriends(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNews method, of class RestHandler.
     */
    @Test
    public void testGetNews() {
        System.out.println("getNews");
        String token = "";
        String expResult = "";
        String result = RestHandler.getNews(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPagesUserLikes method, of class RestHandler.
     */
    @Test
    public void testGetPagesUserLikes() {
        System.out.println("getPagesUserLikes");
        String token = "";
        String expResult = "";
        String result = RestHandler.getPagesUserLikes(token);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}