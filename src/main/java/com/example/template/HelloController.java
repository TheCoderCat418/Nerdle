package com.example.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {

    @FXML
    public Label lblDisplay;
    @FXML
    public TextField txtInput;
    public TextField txt00, txt01, txt02, txt03, txt04, txt05, txt06, txt07,
            txt08, txt09, txt10, txt11, txt12, txt13, txt14, txt15,
            txt16, txt17, txt18, txt19, txt20, txt21, txt22, txt23,
            txt24, txt25, txt26, txt27, txt28, txt29, txt30, txt31,
            txt32, txt33, txt34, txt35, txt36, txt37, txt38, txt39,
            txt40, txt41, txt42, txt43, txt44, txt45, txt46, txt47;

    private ArrayList<TextField> rows = new ArrayList<>();
    private NerdleQuestion currentQuestion;
    private NerdleFile nf;
    private int rowCheck = 1;

    public void addRowsToArr() {
        rows.add(txt00);
        rows.add(txt01);
        rows.add(txt02);
        rows.add(txt03);
        rows.add(txt04);
        rows.add(txt05);
        rows.add(txt06);
        rows.add(txt07);
        rows.add(txt08);
        rows.add(txt09);
        rows.add(txt10);
        rows.add(txt11);
        rows.add(txt12);
        rows.add(txt13);
        rows.add(txt14);
        rows.add(txt15);
        rows.add(txt16);
        rows.add(txt17);
        rows.add(txt18);
        rows.add(txt19);
        rows.add(txt20);
        rows.add(txt21);
        rows.add(txt22);
        rows.add(txt23);
        rows.add(txt24);
        rows.add(txt25);
        rows.add(txt26);
        rows.add(txt27);
        rows.add(txt28);
        rows.add(txt29);
        rows.add(txt30);
        rows.add(txt31);
        rows.add(txt32);
        rows.add(txt33);
        rows.add(txt34);
        rows.add(txt35);
        rows.add(txt36);
        rows.add(txt37);
        rows.add(txt38);
        rows.add(txt39);
        rows.add(txt40);
        rows.add(txt41);
        rows.add(txt42);
        rows.add(txt43);
        rows.add(txt44);
        rows.add(txt45);
        rows.add(txt46);
        rows.add(txt47);

    }

    public void initialize() {
        //Load and check Nerdle File
        nf = new NerdleFile(NerdleFile.DEFAULT_FILE_PATH);
        addRowsToArr();
        rowLocker(-10);
        rowLocker(1);
        currentQuestion = nf.getQuestions().get((int)(nf.getQuestions().size() * Math.random()));

    }


    public void handleCheckAnswer(ActionEvent actionEvent) {
        ArrayList<Character> userAnswer = new ArrayList<>();
        for (int i = rowCheck * 8 - 8; i < rowCheck * 8; i++) {
            String temp = rows.get(i).getText();
            if(temp.length() != 1){ // Incomplete
                return;
            }
            userAnswer.add(temp.charAt(0));

        }
        ArrayList<Integer> results = currentQuestion.checkUserInput(userAnswer);
        int correct = 0;
        for(int i = 0; i<results.size(); i++){
            if(results.get(i) == 1){
                rows.get(rowCheck * 8 - 8 + i).setStyle("-fx-control-inner-background: green;");
                correct++;
            }
        }

        rowLocker(-1*rowCheck);
        if(rowCheck < 6 && correct != 8) {
            rowCheck++;
            rowLocker(rowCheck);
        }else{
            if(correct == 8){
                //Won
                lblDisplay.setText("You won!");
            }else{
                //Lost
                lblDisplay.setText("You lost. It was: " + currentQuestion.getQuestion());
            }
        }

    }

    public void handleColorChangeExample(ActionEvent actionEvent) {
        txt00.setStyle("-fx-control-inner-background: red;");
    }

    //Row locking to prevent modifications to past and future rows
    //Pass in a row number. If the row number is negative, it will lock. Vise versa.
    //To lock/Unlock all rows, use row 10
    public void rowLocker(int row) {
        if (Math.abs(row) == 10) {
            for (TextField tf : rows){
                if(row<0){
                    tf.setEditable(false);
                }else{
                    tf.setEditable(true);
                }
            }
        }else {
            for (int i = Math.abs(row) * 8 - 8; i < Math.abs(row) * 8; i++) {
                if (row < 0) {
                    rows.get(i).setEditable(false);
                } else {
                    rows.get(i).setEditable(true);
                }
            }
        }
    }
}