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
public class ManageHighScoreTest {
    
    public ManageHighScoreTest() {
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
     * Test of updateScore method, of class ManageHighScore.
     */
    @Test
    public void testUpdateScore() {
        System.out.println("updateScore");
        String name = "";
        int score = 0;
        ManageHighScore instance = new ManageHighScore();
        instance.updateScore(name, score);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveScores method, of class ManageHighScore.
     */
    @Test
    public void testSaveScores() {
        System.out.println("saveScores");
        ManageHighScore instance = new ManageHighScore();
        instance.saveScores();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printScores method, of class ManageHighScore.
     */
    @Test
    public void testPrintScores() {
        System.out.println("printScores");
        ManageHighScore instance = new ManageHighScore();
        instance.printScores();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
