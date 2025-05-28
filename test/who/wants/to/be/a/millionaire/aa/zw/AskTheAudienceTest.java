/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AskTheAudienceTest {

    @Test
    public void testAskTheAudienceVoteTotals100() {
        // Redirect system output
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            Question q = new Question(
                    "What is the capital of New Zealand?",
                    new String[]{"A) Auckland", "B) Wellington", "C) Nelson", "D) Taupo"},
                    "B"
            );

            AskTheAudience ata = new AskTheAudience();
            ata.use(q);

            // Extract vote percentages from printed output
            String printed = output.toString();
            Pattern pattern = Pattern.compile("- (\\d+)%");
            Matcher matcher = pattern.matcher(printed);

            int total = 0;
            while (matcher.find()) {
                total += Integer.parseInt(matcher.group(1));
            }

            assertEquals("Total audience vote should be 100%", 100, total);

        } finally {
            // Restore original System.out
            System.setOut(originalOut);
        }
    }

    @Test
    public void testAskTheAudienceCanOnlyBeUsedOnce() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(output));

        try {
            Question q = new Question(
                    "What is the capital of New Zealand?",
                    new String[]{"A) Auckland", "B) Wellington", "C) Nelson", "D) Taupo"},
                    "B"
            );

            AskTheAudience ata = new AskTheAudience();
            ata.use(q);  // First use
            ata.use(q);  // Second use should show "already used" message

            String printed = output.toString();
            int useCount = printed.split("Audience has voted:").length - 1;

            assertEquals("AskTheAudience should only be used once", 1, useCount);
            assertTrue("Should show already used message", printed.contains("already used"));
        } finally {
            System.setOut(originalOut);
        }
    }

}
