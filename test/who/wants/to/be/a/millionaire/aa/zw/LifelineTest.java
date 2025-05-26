/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ziraa
 */
public class LifelineTest {
    
    public LifelineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isUsed method, of class Lifeline.
     */
    @Test
    public void testIsUsed() {
        System.out.println("isUsed");
        Lifeline instance = new LifelineImpl();
        boolean expResult = false;
        boolean result = instance.isUsed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of use method, of class Lifeline.
     */
    @Test
    public void testUse() {
        System.out.println("use");
        Question question = null;
        Lifeline instance = new LifelineImpl();
        instance.use(question);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class LifelineImpl extends Lifeline {

        public void use(Question question) {
        }
    }
    
}
