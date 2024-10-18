package com.example.template;

public class User {

    private String name;
    private int gamesPlayed;
    private int gamesWon;

    public User(String name, int gamesPlayed, int gamesWon) {
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.name = name;
    }

    public User(String CSS_User) {
        String[] broken = CSS_User.split(",");
        if (broken.length == 3) {
            this.name = broken[0].strip();
            this.gamesPlayed = Integer.parseInt(broken[1].strip());
            this.gamesWon = Integer.parseInt(broken[2].strip());
        }
    }

    public void addGame(boolean won) {
        gamesPlayed++;
        if (won) {
            gamesWon++;
        }
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", " + gamesPlayed + ", " + gamesWon;
    }
}
