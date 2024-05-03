package com.example.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UserDatabase Class
 * Manages the user database stored in a CSV file
 * @author Eric Lim
 */
public class UserDatabase {
    
    private static final String CSV_FILE_PATH = "src/com/example/Data/registry.csv";

    public UserDatabase() {
        createCsvFileIfNotExists();
    }

    private void createCsvFileIfNotExists() {
        File file = new File(CSV_FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.println(user.toCsvString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User userToUpdate) {
        File tempFile = new File(CSV_FILE_PATH + ".tmp");
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
             PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.parseCsv(line);
                if (user.getUsername().equals(userToUpdate.getUsername())) {
                    writer.println(userToUpdate.toCsvString());
                } else {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.renameTo(new File(CSV_FILE_PATH));
    }

    public User fetchUser(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.parseCsv(line);
                if (user.getUsername().equals(username)) {
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyLogin(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.parseCsv(line);
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
