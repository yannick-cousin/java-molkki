package com.molkky;

public class Player {
  private String name;
  private int score = 0;
  private int lancerSansPoints = 0;
  private boolean win = false;
  private boolean endgame = false;

  // constructor
  Player(String name) {
    this.name = name;
  }

  // GET SET
  public String getName() {
    return name;
  }

  public int getScore() {
    return score;
  }

  public int getLancerSansPoints() {
    return lancerSansPoints;
  }

  public boolean getWin() { return win; }

  public boolean getEndGame() {return endgame; }

  private void shouldEndGame() {
    if(score == 50) {
      win = true;
      endgame = true;
    }
    else if(score > 50)
      score = 25;
    else if(lancerSansPoints == 3) {
      endgame = true;
      System.out.println("Fin de partie");
    }
  }

  // METIER
  public void lancer(int[] quilles) {
    if (quilles.length == 1) {
      score += quilles[0];
      lancerSansPoints = 0;
    } else if (quilles.length > 1) {
      score += quilles.length;
      lancerSansPoints = 0;
    } else {
      lancerSansPoints += 1;
    }
    shouldEndGame();
  }
}
