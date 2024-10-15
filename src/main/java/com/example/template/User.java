package com.example.template;

public class User {
    String name;
    NerdleQuestion bestQuestion;
    int solvedIn;
    int userId;

    public User(String name){
        //Read other id's from file
        //Assign bestQuestion when applicable
        //Same with solved in
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
