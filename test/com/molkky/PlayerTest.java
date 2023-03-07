package com.molkky;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    static int[] quilles = {12};
    static int[] quillesTwo = {2,8};

    @Test
    public void oneQuilleEqualScores() {
        Player yannick = new Player("Yannick");
        assertNotNull(yannick);

        yannick.lancer(quilles);
        assertEquals(12, yannick.getScore());
    }

    @Test
    public void manyQuillesEqualScores() {
        Player yannick = new Player("Yannick");
        int[] quilles = {3, 4, 6};
        yannick.lancer(quilles);
        assertEquals(3, yannick.getScore());
    }

    @Test
    public void fiftyPointsItsWin() {
        Player yannick = new Player("Yannick");
        yannick.lancer(quilles);
        yannick.lancer(quilles);
        yannick.lancer(quilles);
        yannick.lancer(quilles);
        yannick.lancer(quillesTwo);
        assertEquals(50, yannick.getScore());
        assertTrue(yannick.getWin());
        assertTrue(yannick.getEndGame());
    }

    @Test
    public void moreFiftyItsLose() {
        Player yannick = new Player("Yannick");
        yannick.lancer(quilles);
        yannick.lancer(quilles);
        yannick.lancer(quilles);
        yannick.lancer(quilles);
        yannick.lancer(quilles);
        assertEquals(25, yannick.getScore());
    }

    @Test
    public void moreThreeSuckLaunchItsLose() {
        Player yannick = new Player("Yannick");

        int[] rater = {};

        yannick.lancer(rater);
        yannick.lancer(rater);
        yannick.lancer(rater);

        assertTrue(yannick.getEndGame());
        assertFalse(yannick.getWin());
    }
}