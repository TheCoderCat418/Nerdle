package com.example.template;

import java.util.ArrayList;

public class NerdleQuestion {
    private String question;
    private ArrayList<Character> map;
    public NerdleQuestion(String question){
        this.question = question;
        this.map = mapEquation();
        if(!isVaild()){
            throw new RuntimeException("Incorrect question format. '" + question +"'");
        }
    }

    private ArrayList<Character> mapEquation(){
        ArrayList<Character> flags = new ArrayList<>();
        for(int i = 0; i<question.length();i++){
            char currentChar = question.charAt(i);
            switch (currentChar){
                case '=':
                    flags.add('=');
                    break;
                case '+':
                    flags.add('+');
                    break;
                case '-':
                    flags.add('-');
                    break;
                case '*':
                    flags.add('*');
                    break;
                case '/':
                    flags.add('/');
                    break;
                default:
                    //Validate number by convering to int
                    try {
                        Integer.parseInt(String.valueOf(currentChar));
                    }catch (NumberFormatException nfe){
                        throw new RuntimeException("Invaild character. '"+question+"'");
                    }
                    flags.add(currentChar);
                    break;
            }
        }
        return flags;
    }

    public boolean isVaild(){
        if(question.length() != 8){
            return false;
        }
        //Vaildate map and symbols
        boolean hasEquals = false;
        int operators = 0;
        for (char s : map) {
            if (s == '=') {
                if (hasEquals) {
                    return false;
                }
                hasEquals = true;
            }else if(s == '+' || s == '-' || s == '*' || s =='/'){
                operators++;
                if(operators > 3){
                    return false;
                }
            }
        }
        //Equation solver later
        return true;
    }

    public ArrayList<Integer> checkUserInput(ArrayList<Character> userInput){
        ArrayList<Integer> results = new ArrayList<>();
        //KEY CODES (VERY IMPORTANT!!!)
        //
        // -1 Not in equation
        //  0 In wrong place | NOT USED IN C LEVEL
        //  1 Correct

        //Doulbe for need l;ater
        if(userInput.size() != 8){
            throw new RuntimeException("User input length incorrect!");
        }
        for(int i = 0; i<userInput.size();i++){
            if(userInput.get(i).equals(map.get(i))){
                results.add(1);
            }else{
                results.add(-1);
            }
        }
        return results;
    }

    public String getQuestion(){
        return question;
    }
}
