package com.example.template;

public class User {

    private String name;
    private int gamesPlayed;
    private int gamesWon;
    private int guesses;
    public User(String name, int gamesPlayed, int gamesWon) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.name = name;
        this.guesses = 0;
    }

    public User(String CSS_User) {
        String[] broken = CSS_User.split(",");
        if (broken.length == 4) {
            this.name = broken[0].strip();
            this.gamesPlayed = Integer.parseInt(broken[1].strip());
            this.gamesWon = Integer.parseInt(broken[2].strip());
            this.guesses = Integer.parseInt(broken[3].strip());
        }
    }

    public void addGame(boolean won, int rowsTaken) {
        gamesPlayed++;
        if (won) {
            gamesWon++;
            guesses+=rowsTaken;
        }
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public double getAverageGuessesPerGame() {
        return (0.0+guesses)/gamesWon;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", " + gamesPlayed + ", " + gamesWon + ", " + guesses;
    }
}
