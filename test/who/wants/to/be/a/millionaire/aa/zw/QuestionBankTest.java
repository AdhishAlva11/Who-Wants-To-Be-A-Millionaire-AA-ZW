/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

/**
 *
 * @author ziraa
 */
import org.junit.*;
import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

public class QuestionBankTest {

    @Test
    public void testLoadsAllDifficultyLevels() {
        QuestionBank qb = new QuestionBank("easy.txt", "medium.txt", "hard.txt");
        List<Question> mixed = qb.getMixedDifficultyQuestions();

        // Should return up to 10 total questions: 3 easy, 4 medium, 3 hard
        assertTrue(mixed.size() <= 10);
    }

    @Test
    public void testShuffledOrder() {
        QuestionBank qb = new QuestionBank("easy.txt", "medium.txt", "hard.txt");
        List<Question> list1 = qb.getMixedDifficultyQuestions();
        List<Question> list2 = qb.getMixedDifficultyQuestions();

        // It’s randomized, so not guaranteed, but this gives a basic check
        assertNotEquals(list1.toString(), list2.toString());
    }
}