/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

/**
 *
 * @author ziraa
 */
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.util.Arrays;

public class SwitchQuestionTest {

    private final String testFileName = "switch.txt";

    @Test
    public void testSwitchReturnsQuestion() throws IOException {

        SwitchQuestion sw = new SwitchQuestion();
        Question newQ = sw.switchUsed();

        assertNotNull("Should return a question on first use", newQ);
        assertTrue("Returned object must be a Question", newQ instanceof Question);
    }

    @Test
    public void testSwitchCanOnlyBeUsedOnce() throws IOException {

        SwitchQuestion sw = new SwitchQuestion();
        Question first = sw.switchUsed();
        Question second = sw.switchUsed(); // Should be null after use

        assertNotNull("First use should return a question", first);
        assertNull("Second use should return null", second);
    }
}