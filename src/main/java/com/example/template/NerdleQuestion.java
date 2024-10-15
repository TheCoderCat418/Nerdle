package com.example.template;

import java.util.ArrayList;

public class NerdleQuestion {
    private final String question;
    private final ArrayList<Character> map;
    public NerdleQuestion(String question) throws Exception {
        this.question = question;
        this.map = mapEquation();
        if(!isVaild() || this.map == null){
            throw new Exception("Question Not Vaild!");
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
                        return null;
                    }
                    flags.add(currentChar);
                    break;
            }
        }
        return flags;
    }

    public boolean isVaild() {
        if (question.length() != 8) {
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
            } else if (s == '+' || s == '-' || s == '*' || s == '/') {
                operators++;
                if (operators > 3) {
                    return false;
                }
            }
        }
        ArrayList<String> brokenEquation = new ArrayList<>();
        for (int i = 0; i < map.size(); i++) {
            if(!charIs(map.get(i)).equals("number")){
                brokenEquation.add("operator");
                continue;
            }
            int b = 0;
            StringBuilder numberString = new StringBuilder();
            while (charIs(map.get(i + b)).equals("number")) {
                numberString.append(map.get(i + b));
                b++;
                if(i + b > map.size()-1){
                    break;
                }
            }
            brokenEquation.add(numberString.toString());
            i += b-1;
        }
        boolean EOE = false;
        int index = 1;
        while(!EOE){
            int opIndex = brokenEquation.indexOf("operator");
            if(opIndex != -1){
                int internalIndex = 0;
                for(Character c : map){
                    if(!charIs(c).equals("number")){
                        internalIndex++;
                        if(internalIndex == index){
                            brokenEquation.set(opIndex, Character.toString(c));
                            index++;
                            break;
                        }
                    }
                }
            }else{
                EOE = true;
            }
        }

        //SOLVE
        EOE = false;
        while (!EOE){
            if(brokenEquation.contains("*")){
                index = brokenEquation.indexOf("*");
                brokenEquation.set(index, Integer.toString(solve(Integer.parseInt(brokenEquation.get(index-1)), "*", Integer.parseInt(brokenEquation.get(index+1)))));
                brokenEquation.remove(index-1);
                brokenEquation.remove(index);
            }else if(brokenEquation.contains("/")){
                index = brokenEquation.indexOf("/");
                brokenEquation.set(index, Integer.toString(solve(Integer.parseInt(brokenEquation.get(index-1)), "/", Integer.parseInt(brokenEquation.get(index+1)))));
                brokenEquation.remove(index-1);
                brokenEquation.remove(index);
            }else if(brokenEquation.contains("+")){
                index = brokenEquation.indexOf("+");
                brokenEquation.set(index, Integer.toString(solve(Integer.parseInt(brokenEquation.get(index-1)), "+", Integer.parseInt(brokenEquation.get(index+1)))));
                brokenEquation.remove(index-1);
                brokenEquation.remove(index);
            }else if(brokenEquation.contains("-")){
                index = brokenEquation.indexOf("-");
                brokenEquation.set(index, Integer.toString(solve(Integer.parseInt(brokenEquation.get(index-1)), "-", Integer.parseInt(brokenEquation.get(index+1)))));
                brokenEquation.remove(index-1);
                brokenEquation.remove(index);
            }else{
                EOE = true;
            }
        }
        if(brokenEquation.size() == 3){
            return Integer.parseInt(brokenEquation.getFirst()) == Integer.parseInt(brokenEquation.getLast());
        }
        return false;
    }

    private int solve(int a, String op, int b){
        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }

    public ArrayList<Integer> checkUserInput(ArrayList<Character> userInput){
        if(userInput.size() != 8) {
            throw new RuntimeException("User input length incorrect!");
        }
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

    private String charIs(char Char){
        if(Char == '*' || Char == '/' || Char == '+' || Char == '-'){
            return "operator";
        }else if(Char == '='){
            return "equal";
        }
        return "number";
    }
    public String getQuestion(){
        return question;
    }
}
