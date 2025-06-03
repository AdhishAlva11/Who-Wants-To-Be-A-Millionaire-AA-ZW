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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class FiftyFiftyTest {

    @Test
    public void testFiftyFiftyShowsTwoOptionsAndIncludesCorrect() {
        Question q = new Question(
                "What is the capital of New Zealand?",
                new String[]{"A) Auckland", "B) Wellington", "C) Nelson", "D) Taupo"},
                "B"
        );

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        FiftyFifty ff = new FiftyFifty();
        ff.use(q);

        System.setOut(System.out); // Reset output

        String printed = output.toString();

        // Count how many options are printed
        int count = 0;
        for (String line : printed.split("\n")) {
            if (line.trim().matches("^[A-D]\\).*")) {
                count++;
            }
        }

        assertEquals("Should display exactly 2 options", 2, count);
        assertTrue("Should include correct answer", printed.contains("B) Wellington"));
    }

    @Test
    public void testFiftyFiftyCanOnlyBeUsedOnce() {
        Question q = new Question("Test Question", new String[]{"A) A", "B) B", "C) C", "D) D"},"C");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        FiftyFifty ff = new FiftyFifty();
        ff.use(q);
        ff.use(q); // Second call

        System.setOut(System.out); // Reset output

        String printed = output.toString();
        int useMessageCount = printed.split("Remaining options:").length - 1;
        assertEquals("Should only show remaining options once", 1, useMessageCount);
        assertTrue("Should display 'already used' message", printed.contains("already used"));
    }
}
