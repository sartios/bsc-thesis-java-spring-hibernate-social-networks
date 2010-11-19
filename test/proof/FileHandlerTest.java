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
public class FileHandlerTest {

    public FileHandlerTest() {
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
     * Test of readFile method, of class FileHandler.
     */
    @Test
    public void testReadFile() throws Exception {
        System.out.println("readFile");
        String filename = "";
        String expResult = "";
        String result = FileHandler.readFile(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableFromFile method, of class FileHandler.
     */
    @Test
    public void testGetTableFromFile() throws Exception {
        System.out.println("getTableFromFile");
        String filename = "";
        String[] expResult = null;
        String[] result = FileHandler.getTableFromFile(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeFile method, of class FileHandler.
     */
    @Test
    public void testWriteFile_String_String() throws Exception {
        System.out.println("writeFile");
        String filename = "";
        String content = "";
        FileHandler.writeFile(filename, content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeFile method, of class FileHandler.
     */
    @Test
    public void testWriteFile_String_StringArr() throws Exception {
        System.out.println("writeFile");
        String filename = "";
        String[] content = null;
        FileHandler.writeFile(filename, content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendFile method, of class FileHandler.
     */
    @Test
    public void testAppendFile() throws Exception {
        System.out.println("appendFile");
        String filename = "";
        String content = "";
        FileHandler.appendFile(filename, content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}