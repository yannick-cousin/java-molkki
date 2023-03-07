package com.molkky;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Party {
	// private Player[] players = new Player[0];
	private ArrayList<Player> players = new ArrayList<Player>();
	private int currentRound = 1;
	private int idxPlayer = 0;
	private boolean finDePartie = false;

	//constructor
	Party() { }

	//GET SET
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public int getIdxPlayer() {
		return idxPlayer;
	}

	//METIER
	public void addPlayer(Player player) {
		players.add(player);
	}

	public Map getScores() {
		Map<String, Integer> scores = new HashMap<String, Integer>();
		players.forEach(player ->
						scores.put(player.getName(), player.getScore()));
		return scores;
	}

	public Map getScoresFromAPlayer(String name) {
		Map<String, Integer> scores = new HashMap<String, Integer>();
			for(Player player : players) {
				if(player.getName().contains(name)) {
					scores.put(player.getName(), player.getScore());
				}
			}
		return scores;
	}

	public void play(int[] quilles) {
		if(idxPlayer+1 > players.size()) {
			idxPlayer = 0;
			currentRound++;
		}
		players.get(idxPlayer).lancer(quilles);
		if(players.get(idxPlayer).getEndGame()) {
			finDePartie = true;
		} else {
			idxPlayer++;
		}
	}
}
