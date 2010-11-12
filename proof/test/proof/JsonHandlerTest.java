/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proof;

import java.util.Map;
import org.apache.commons.beanutils.DynaBean;
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
public class JsonHandlerTest {

    public JsonHandlerTest() {
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
     * Test of ExtractIdsFromRest method, of class JsonHandler.
     */
    @Test
    public void testExtractIdsFromRest() {
        System.out.println("ExtractIdsFromRest");
        String responseList = "";
        String[] expResult = null;
        String[] result = JsonHandler.ExtractIdsFromRest(responseList);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeed method, of class JsonHandler.
     */
    @Test
    public void testGetFeed() {
        System.out.println("getFeed");
        String content = "";
        Map expResult = null;
        Map result = JsonHandler.getFeed(content);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getComments method, of class JsonHandler.
     */
    @Test
    public void testGetComments() {
        System.out.println("getComments");
        DynaBean aDynaBean = null;
        Map expResult = null;
        Map result = JsonHandler.getComments(aDynaBean);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reCaster method, of class JsonHandler.
     */
    @Test
    public void testReCaster() {
        System.out.println("reCaster");
        Object aBean = null;
        DynaBean expResult = null;
        DynaBean result = JsonHandler.reCaster(aBean);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPreviusPaging method, of class JsonHandler.
     */
    @Test
    public void testGetPreviusPaging() {
        System.out.println("getPreviusPaging");
        String content = "";
        String expResult = "";
        String result = JsonHandler.getPreviusPaging(content);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextPaging method, of class JsonHandler.
     */
    @Test
    public void testGetNextPaging() {
        System.out.println("getNextPaging");
        String content = "";
        String expResult = "";
        String result = JsonHandler.getNextPaging(content);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}