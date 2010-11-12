/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proof;

import java.util.Calendar;
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
public class timeHandlerTest {

    public timeHandlerTest() {
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
     * Test of convertFbToDate method, of class timeHandler.
     */
    @Test
    public void testConvertFbToDate() {
        System.out.println("convertFbToDate");
        String aDate = "2010-05-27T19:24:17+0000";
        
        Calendar result = timeHandler.convertFbToDate(aDate);

 
        assertEquals(result.getTime().toString(),"Thu May 27 22:24:17 EEST 2010");
        
    }

}