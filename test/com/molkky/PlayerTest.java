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

    @Test
    public void aPlayerDontTouchForTwoRoundButItsOk() {
        // Reproduction bug :
        // Quand je rates 2 lancés, puis un qui est bon, puis un raté, je perds la partie

        // Création d'un jeu de lancers
        int[] quillesTwo = {11};
        int[] quillesNothing = new int[0];

        // Création du joueur
        Player yannick = new Player("Yannick");

        // Deux lancers raté
        yannick.lancer(quillesNothing);
        yannick.lancer(quillesNothing);

        // TEST
        assertEquals(2, yannick.getLancerSansPoints());

        yannick.lancer(quillesTwo);

        // Le lancer réussi doit remettre le compteur de lancer non réussi à 0
        assertEquals(0, yannick.getLancerSansPoints());

        // Nouveau coup raté de la part joueur
        yannick.lancer(quillesNothing);

        // TEST => Le compteur passe à 1
        assertEquals(1, yannick.getLancerSansPoints());
        assertEquals(false, yannick.getEndGame());
    }
}