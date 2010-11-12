/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proof;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author npetalidis
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({proof.FileHandlerTest.class,proof.PageHandlerTest.class,proof.testFunctionsTest.class,proof.TaskHandlerTest.class,proof.timeHandlerTest.class,proof.RestHandlerTest.class,proof.JsonHandlerTest.class,proof.FbFeedTest.class})
public class ProofSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}