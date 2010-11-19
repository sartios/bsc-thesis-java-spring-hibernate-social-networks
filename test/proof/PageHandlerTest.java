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
public class PageHandlerTest {

    public PageHandlerTest() {
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
     * Test of getPage method, of class PageHandler.
     */
    @Test
    public void testGetPage() {
        System.out.println("getPage");
        String homeURL = "";
        StringBuffer content = null;
        PageHandler.getPage(homeURL, content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}