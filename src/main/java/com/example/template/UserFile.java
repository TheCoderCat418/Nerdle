package com.example.template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserFile {
    private static File nerdleFolder = new File(System.getenv("APPDATA") + "\\ConantNerdle");

    public static void createNerdleFolder() {
        if (!nerdleFolder.mkdir()) {
            if (nerdleFolder.exists()) {
                System.out.println("Failed to create ConantNerdle folder! It is already there!");
            } else {
                System.out.println("Failed to create ConantNerdle folder! Unknown error!");
            }
            return;
        }
        try {
            FileWriter writer = new FileWriter(nerdleFolder.getAbsolutePath() + "\\users.nerdle");
            writer.write("default, 0, 0, 0");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void destroyNerdleFolder() {
        for (File file : nerdleFolder.listFiles()) {
            if (!file.delete()) {
                System.out.println("Could not delete!");
            }
        }
        if (!nerdleFolder.delete()) {
            System.out.println("Could not delete!");
        }
    }

    public static void addUser(User user) {
        Scanner in = new Scanner(nerdleFolder.getAbsolutePath() + "\\users.nerdle");
        ArrayList<String> lines = new ArrayList<>();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (Integer.parseInt(line.split(",")[3].strip()) == user.getUserId()) {
                lines.add(user.toString());
            } else {
                lines.add(line);
            }
        }
        try {
            FileWriter writer = new FileWriter(nerdleFolder.getAbsolutePath() + "\\users.nerdle");
            for (String line : lines) {
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getGreatestUserId() {
        Scanner in = new Scanner(nerdleFolder.getAbsolutePath() + "\\users.nerdle");
        int lines = 0;
        while (in.hasNextLine()) {
            String line = in.nextLine();
            lines++;
        }
        return lines - 1;
    }

    public static ArrayList<User> getUsers() {
        Scanner in = new Scanner(nerdleFolder.getAbsolutePath() + "\\users.nerdle");
        ArrayList<User> users = new ArrayList<>();
        while (in.hasNextLine()) {
            users.add(new User(in.nextLine()));
        }
        return users;
    }

    public static User getUser(int userId){
        for(User user : getUsers()){
            if(user.getUserId() == userId){
                return user;
            }
        }
        return null;
    }
}
