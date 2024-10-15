package com.example.template;

public class Person {
    String name;
    NerdleQuestion bestQuestion;
    int solvedIn;
    int userId;

    public Person(String name){
        //Read other id's from file
        //Assign bestQuestion when applicable
        //Same with solved in
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
