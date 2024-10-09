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
        if(userInput.size() != 8) {
            throw new RuntimeException("User input length incorrect!");
        };
        ArrayList<Integer> results = new ArrayList<>(8);
        for(int i = 0; i<8; i++){
            results.add(-2);
        }

        ArrayList<Character> questionMapCopy = new ArrayList<>(8);
        questionMapCopy.addAll(map);
        //KEY CODES (VERY IMPORTANT!!!)
        // -2 Not Processed
        // -1 Not in equation
        //  0 In wrong place
        //  1 Correct
        for(int i = 0; i<results.size(); i++){
            if(userInput.get(i).equals(questionMapCopy.get(i))){
                results.set(i, 1);
                questionMapCopy.set(i, '}');
            }
        }
        for(int i =0; i<results.size();i++) {
            if (results.get(i) == -2) {
                for (int z = 0; z < questionMapCopy.size(); z++) {
                    if (questionMapCopy.get(z).equals(userInput.get(i))) {
                        results.set(i, 0);
                        questionMapCopy.set(z, '}');
                        break;
                    }
                }
                if (results.get(i) == -2) {
                    results.set(i, -1);
                }
            }
        }
        return results;
    }

    public String getQuestion(){
        return question;
    }
}
