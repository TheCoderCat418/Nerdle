package com.example.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserFile {
    private static final File nerdleFolder = new File(System.getenv("APPDATA") + "\\ConantNerdle");

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
            writer.write("default, 0, 0, 0\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Run to remove from AppData
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
        try {
            Scanner in = new Scanner(new File(nerdleFolder.getAbsolutePath() + "\\users.nerdle"));
            ArrayList<String> lines = new ArrayList<>();
            boolean isNew = true;
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.split(",")[0].strip().equals(user.getName())) {
                    lines.add(user.toString());
                    isNew = false;
                } else {
                    lines.add(line);
                }
            }
            if (isNew) {
                lines.add(user.toString());
            }
            FileWriter writer = new FileWriter(nerdleFolder.getAbsolutePath() + "\\users.nerdle");
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void removeUser(String user) {
        if (!user.equals("default")) {
            try {
                Scanner in = new Scanner(new File(nerdleFolder.getAbsolutePath() + "\\users.nerdle"));
                ArrayList<String> lines = new ArrayList<>();
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    if (!line.split(",")[0].strip().equals(user)) {
                        lines.add(line);
                    }
                }

                FileWriter writer = new FileWriter(nerdleFolder.getAbsolutePath() + "\\users.nerdle");
                for (String line : lines) {
                    writer.write(line + "\n");
                }
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<User> getUsers() {
        try {
            Scanner in = new Scanner(new File(nerdleFolder.getAbsolutePath() + "\\users.nerdle"));
            ArrayList<User> users = new ArrayList<>();
            while (in.hasNextLine()) {
                users.add(new User(in.nextLine()));
            }
            return users;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(String userName) {
        for (User user : getUsers()) {
            if (user.getName().equals(userName)) {
                return user;
            }
        }
        return null;
    }
}
