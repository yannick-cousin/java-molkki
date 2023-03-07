package com.molkky;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PartyTest {
	@Test
	public void partyWithOnePlayer() {
		Party test = new Party();

		Player yannick = new Player("Yannick");

		test.addPlayer(yannick);

		assertEquals(1, test.getPlayers().size());
	}

	@Test
	public void partyWithTwoOrMorePlayers() {
		Party test = new Party();

		Player yannick = new Player("Yannick");
		Player vivien = new Player("Vivien");

		test.addPlayer(yannick);
		test.addPlayer(vivien);

		assertEquals(2, test.getPlayers().size());
	}

	@Test
	public void scoresFromAnyPlayers() {
		Party test = new Party();

		Player yannick = new Player("Yannick");
		Player vivien = new Player("Vivien");
		Player florian = new Player("Florian");

		test.addPlayer(yannick);
		test.addPlayer(vivien);
		test.addPlayer(florian);

		Map scoreAttendu = new HashMap();
		scoreAttendu.put("Yannick", 0);
		scoreAttendu.put("Vivien", 0);
		scoreAttendu.put("Florian", 0);

		Map scoresATest = test.getScores();

		assertEquals(scoreAttendu,scoresATest);
	}

	@Test
	public void afficherChaqueLancerEtScoreTotal() {
		// Création d'un jeu de lancers
		int[] quillesOne = {4,9};
		int[] quillesTwo = {11};
		int[] quillesThree = {3,4,7,9,5};
		int[] quillesFour = {1,2,4,5,7,8,10,11};

		// Création des joueurs
		Player yannick = new Player("Yannick");
		Player vivien = new Player("Vivien");

		// Création de la partie
		Party test = new Party();

		// Ajout des joueurs à la partie
		test.addPlayer(yannick);
		test.addPlayer(vivien);

		// Premier lancer par le premier joueur
		test.play(quillesTwo);

		// Création des données attendu après le lancer
		Map scoreAttenduOne = new HashMap();
		scoreAttenduOne.put("Yannick", 11);

		// Récupération des scores du joueur
		Map scoresATest = test.getScoresFromAPlayer("Yannick");

		// TEST
		assertEquals(scoreAttenduOne,scoresATest);

		// Lancer du second joueur
		test.play(quillesOne);

		// Création des données attendu après le lancer
		Map scoreAttenduTwo = new HashMap();
		scoreAttenduTwo.put("Vivien", 2);

		// Récupération des scores du joueur
		scoresATest = test.getScoresFromAPlayer("Vivien");

		// TEST
		assertEquals(scoreAttenduTwo,scoresATest);

		// Création des données test pour l'ensemble des joueurs sur ce tour
		Map scoresExpected = new HashMap();
		scoresExpected.put("Yannick", 11);
		scoresExpected.put("Vivien", 2);

		// Récupération des données de l'ensemble des joueurs
		Map scoreEndRound = test.getScores();

		// TEST
		assertEquals(scoresExpected, scoreEndRound);

		// Démarrage d'un nouveau tour
		// Premier joueur
		test.play(quillesThree);

		// Second joueur
		test.play(quillesFour);

		// Création des données test pour l'ensemble des joueurs sur ce tour
		scoresExpected.clear();
		scoresExpected.put("Yannick", 16);
		scoresExpected.put("Vivien", 10);


		// Récupération des données de l'ensemble des joueurs
		scoreEndRound = test.getScores();

		// TEST
		assertEquals(scoresExpected, scoreEndRound);
	}

	@Test
	public void aPlayerDontTouchForTwoRoundButItsOk() {
		// Reproduction bug :
		// Quand je rates 2 lancés, puis un qui est bon, puis un raté, je perds la partie

		// Création d'un jeu de lancers
		int[] quillesOne = {4,9};
		int[] quillesTwo = {11};
		int[] quillesThree = {3,4,7,9,5};
		int[] quillesFour = {1,2,4,5,7,8,10,11};
		int[] quillesNothing = new int[0];

		// Création des joueurs
		Player yannick = new Player("Yannick");
		Player vivien = new Player("Vivien");

		// Création de la partie
		Party test = new Party();

		// Ajout des joueurs à la partie
		test.addPlayer(yannick);
		test.addPlayer(vivien);

		// Début de la partie, deux round raté pour le second joueur
		test.play(quillesTwo);
		test.play(quillesNothing);
		test.play(quillesTwo);
		test.play(quillesNothing);

		assertEquals(2, test.getPlayers().get(1).getLancerSansPoints());

		// Premier round réussi pour le second joueur
		test.play(quillesTwo);
		test.play(quillesFour);

		// Le lancer réussi doit remettre le compteur de lancer non réussi à 0
		assertEquals(0, test.getPlayers().get(1).getLancerSansPoints());

		// Nouveau coup raté de la part du second joueur
		test.play(quillesTwo);
		test.play(quillesNothing);

		assertEquals(1, test.getPlayers().get(1).getLancerSansPoints());
		assertEquals(false, test.getPlayers().get(1).getEndGame());
		//
	}
}
