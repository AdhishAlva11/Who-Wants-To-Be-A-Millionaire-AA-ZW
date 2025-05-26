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
public class SwitchQuestionTest {
    
    public SwitchQuestionTest() {
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
     * Test of use method, of class SwitchQuestion.
     */
    @Test
    public void testUse() {
        System.out.println("use");
        Question question = null;
        SwitchQuestion instance = new SwitchQuestion();
        instance.use(question);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of switchUsed method, of class SwitchQuestion.
     */
    @Test
    public void testSwitchUsed() {
        System.out.println("switchUsed");
        SwitchQuestion instance = new SwitchQuestion();
        Question expResult = null;
        Question result = instance.switchUsed();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
