package com.example.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * UserDatabase Class
 * <p>
 * Manages the user database stored in a CSV file
 * @author Eric Lim, Arjenis Montenegro
 */
public class UserDatabase {
    
    private static final String CSV_FILE_PATH = "src/com/example/Data/registry.csv";

    /**
     * Default constructor for UserDatabase.
     * It also checks and creates the CSV file if it does not exist.
     */
    public UserDatabase() {
        createCsvFileIfNotExists();
    }

    /**
     * Checks if the CSV file exists, if not, creates a new one.
     */
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

    /**
     * Adds a new user to the database.
     * 
     * @param user The User object to be added to the database.
     */
    public void addUser(User user) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.println(user.toCsvString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the details of an existing user in the database.
     * 
     * @param userToUpdate The User object with updated details.
     */
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

    /**
     * Fetches a user from the database based on the username.
     * 
     * @param username The username of the user to be fetched.
     * @return The User object if found, null otherwise.
     */
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

    /**
     * Verifies the login credentials of a user.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @return true if the credentials are correct, false otherwise.
     */
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
    
    /**
     * Checks if a user exists in the database based on the username.
     * 
     * @param username The username of the user.
     * @return true if the user exists, false otherwise.
     */
    public boolean userExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.parseCsv(line);
                if (user.getUsername().equalsIgnoreCase(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // /**
    //  * Checks if there is an admin user in the database.
    //  * 
    //  * @return true if an admin user exists, false otherwise.
    //  */
    // public boolean hasAdminUser() {
    //     // Check if there is any user with admin
    //     try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
    //         String line;
    //         while ((line = reader.readLine()) != null) {
    //             User user = User.parseCsv(line);
    //             if (user.isAdmin()) {
    //                 return true;
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //     return false;
    // }

    /**
     * Deletes a user from the database.
     *
     * This method reads the user database from a CSV file line by line. If a user with the provided username and password is found, 
     * it is removed from the database and returned. The remaining users are written to a temporary file. 
     * Finally, the original CSV file is replaced with the temporary file.
     *
     * @param username the username of the user to be deleted
     * @param password the password of the user to be deleted
     * @return the deleted User object if a user with the provided username and password is found, null otherwise
     * @throws IOException if an I/O error occurs when reading from the file or writing to the file
     */
    public boolean deleteUser(String username, String password) {
        File tempFile = new File(CSV_FILE_PATH + ".tmp");
        User deletedUser = null;
        PrintWriter writer = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            writer = new PrintWriter(new FileWriter(tempFile));
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.parseCsv(line);
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    deletedUser = user;
                    continue;
                }
                writer.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            try {
                Files.move(tempFile.toPath(), new File(CSV_FILE_PATH).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return deletedUser != null;
    }
}
