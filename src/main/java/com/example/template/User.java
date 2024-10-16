package com.example.template;

public class User {
    String name;
    int gamesPlayed;
    int gamesWon;
    int userId;

    public User(String name, int gamesPlayed, int gamesWon){
        this.userId = UserFile.getGreatestUserId()+1;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.name = name;
    }

    public User(String CSS_User){
        String[] broken = CSS_User.split(",");
        if(broken.length == 4){
            this.name = broken[0].strip();
            this.gamesPlayed = Integer.parseInt(broken[1].strip());
            this.gamesWon = Integer.parseInt(broken[2].strip());
            this.userId = Integer.parseInt(broken[3].strip());
        }
    }

    public String getName(){
        return name;
    }

    public int getUserId(){
        return userId;
    }
    @Override
    public String toString(){
        return name + ", " + gamesPlayed + ", " + gamesWon + ", " + userId;
    }
}
