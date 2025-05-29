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

public class PlayerTest {

    @Test
    public void testPlayerInitialization() {
        Player p = new Player("Adhish");

        assertEquals("Adhish", p.getName());
        assertEquals(0, p.getScore());
    }

    @Test
    public void testUpdateScore() {
        Player p = new Player("Zi");
        p.updateScore(500);

        assertEquals(500, p.getScore());
    }

    @Test
    public void testMultipleScoreUpdates() {
        Player p = new Player("Zi");
        p.updateScore(300);
        assertEquals(300, p.getScore());

        p.updateScore(750);
        assertEquals(750, p.getScore());
    }
}
