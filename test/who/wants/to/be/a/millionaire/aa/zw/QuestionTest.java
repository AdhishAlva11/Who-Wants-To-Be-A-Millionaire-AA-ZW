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

public class QuestionTest {

    @Test
    public void testQuestionCreation() {
        String text = "What is the capital of France?";
        String[] options = {"A) Paris", "B) London", "C) Rome", "D) Berlin"};
        String correct = "A";

        Question q = new Question(text, options, correct);

        assertEquals(text, q.getQuestionText());
        assertArrayEquals(options, q.getOptions());
        assertEquals(correct, q.getCorrectAnswer());
    }

    @Test
    public void testEmptyQuestion() {
        Question q = new Question("", new String[]{"", "", "", ""}, "");

        assertEquals("", q.getQuestionText());
        assertArrayEquals(new String[]{"", "", "", ""}, q.getOptions());
        assertEquals("", q.getCorrectAnswer());
    }

}
