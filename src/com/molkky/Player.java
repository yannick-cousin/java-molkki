package com.molkky;

public class Player {
  private String name;
  private int score = 0;
  private int tour = 1;
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

  public int getTour() {
    return tour;
  }

  public boolean getWin() { return win; }

  public boolean getEndGame() {return endgame; }

  private void setTour(int tour) {
    this.tour = tour;
  }

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
    } else if (quilles.length > 1) {
      score += quilles.length;
    } else {
      lancerSansPoints += 1;
    }
    shouldEndGame();
    setTour(getTour() + 1);
  }


}
