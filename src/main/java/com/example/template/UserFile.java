package com.example.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserFile {
    private static File nerdleFolder = new File(System.getenv("APPDATA")+"\\ConantNerdle");
    private static void createNerdleFolder(){
        if(!nerdleFolder.mkdir()){
            if(nerdleFolder.exists()) {
                System.out.println("Failed to create ConantNerdle folder! It is already there!");
            }else{
                System.out.println("Failed to create ConantNerdle folder! Unknown error!");
            }
            return;
        }
        try {
            FileWriter writer = new FileWriter(nerdleFolder.getAbsolutePath()+"\\users.nerdle");
            writer.write("default, 0");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void destroyNerdleFolder(){
        for(File file : nerdleFolder.listFiles()){
            if(!file.delete()){
                System.out.println("Could not delete!");
            }
        }
        if(!nerdleFolder.delete()){
            System.out.println("Could not delete!");
        }
    }
    public static void addUser(User user){
        return;
    }
    public static User[] getUsers(){
        destroyNerdleFolder();
        createNerdleFolder();
        return null;
    }
    public static
}
