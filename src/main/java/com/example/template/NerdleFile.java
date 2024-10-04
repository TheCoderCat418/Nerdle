package com.example.template;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class NerdleFile {
    public static File DEFAULT_FILE_PATH = new File("src/main/resources/default.nerdle");
    private final File file;
    private final ArrayList<NerdleQuestion> nerdleQuestions;
    public NerdleFile(File file, ArrayList<NerdleQuestion> nerdleQuestions){
        this.file = file;
        this.nerdleQuestions = nerdleQuestions;

    }
    public static NerdleFile loadNerdleFile(File file){
        if(!file.exists()){
            throw new RuntimeException("File Not Found");
        }
        ArrayList<NerdleQuestion> nqs = new ArrayList<>();

        try {
//            FileReader reader = new FileReader(file);
//            Scanner in = new Scanner(reader); Proof of concept

            Scanner in = new Scanner(file);
            while(in.hasNextLine()){
                String line = in.nextLine();
                nqs.add(new NerdleQuestion(line));
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new NerdleFile(file, nqs);
    }


}
