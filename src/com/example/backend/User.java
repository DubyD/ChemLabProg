package com.example.backend;

import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;


public class User {

    private String username;
    private String password;
    private String email;
    private boolean privileges;
    private String securityQ; 
    private String securityA;
    private String signature;
    private List<TakeOutSlip> takeOutSlips;
    private boolean isActive;
    private LocalDate lastLogin;
    private int failedLoginAttempts;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    /**
     * Creates a new user with default values.
     * <p>
     * 4/30/2024 - Implemented method EL
     */
    public User() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.privileges = false;
        this.securityQ = null;
        this.securityA = null;
        this.signature = null;
        this.takeOutSlips = null;
        this.isActive = false;
        this.lastLogin = null;
        this.failedLoginAttempts = 0;
    }

    /**
     * Creates a new user with the provided username and password.
     * <p>
     * 4/30/2024 - Implemented method EL
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        login(username, password);
    }
    
    /**
     * Logs in the user with the provided username and password.
     * <p>
     * 4/30/2024 - Implemented method EL
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if the login is successful, false otherwise.
     
    public boolean login(String username, String password) {
        // Fetch user information from the database

        // Update User class information
        updateInfo(username, password, email, securityQ, securityA, signature, takeOutSlips, true, LocalDate.now());

        // Return true if the login is successful
        return false;
    } **/
    
    public boolean login(String username, String password) {
        // Simulated check for correct username and password
        if ("admin".equals(username) && "password123".equals(password)) {
            // Update last login time
            setLastLogin(LocalDate.now());
            // Reset failed login attempts
            setFailedLoginAttempts(0);
            return true; // Successful login
        } else {
            // Handle failed login attempt
            handleFailedLoginAttempt();
            return false; // Failed login
        }
    }
    
    
    /**
     * Logs out the user.
     * <p>
     * 4/30/2024 - Implemented method EL
     * 
     * @return True if the logout is successful, false otherwise.
     */
    public boolean logout() {
        // Update User class information
        updateInfo(username, password, email, securityQ, securityA, signature, takeOutSlips, false, LocalDate.now());

        // Store user information in the database

        // Return true if the login is successful
        return false;
    }

    /**
     * Resets the password of the user.
     * <p>
     * 4/30/2024 - Implemented method EL
     * 
     * @param newPassword The new password of the user.
     * @return True if the password is reset successfully, false otherwise.
     */
    public boolean resetPassword(String newPassword) {
        if (isValidPasswordLength(newPassword)) {
            setPassword(newPassword);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Asks the user a security question.
     * <p>
     * 4/30/2024 - Implemented method EL
     * 
     * @return True if the security question was successfully answered, false otherwise.
     */
    public boolean askSecurityQuestion() {
        return false;
    }

    // Method to validate password length
    private boolean isValidPasswordLength(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    // Method to validate email format
    private boolean isValidEmailFormat(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Method to handle failed login attempts
    public void handleFailedLoginAttempt() {
        failedLoginAttempts++;
    }

    // Method to validate security question and answer
    private boolean isValidSecurityQA(String securityQ, String securityA) {
        return securityQ != null && !securityQ.isEmpty() &&
               securityA != null && !securityA.isEmpty();
    }

    /**
     * Updates the information of the user.
     * <p>
     * 4/30/2024 - Implemented method EL
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @param email The email of the user.
     * @param securityQ The security question of the user.
     * @param securityA The security answer of the user.
     * @param signature The signature of the user.
     * @param takeOutSlips The take out slips of the user.
     * @param isActive The status of the user.
     * @return True if the information is updated successfully, false otherwise.
     */
    public boolean updateInfo(String username, String password, String email, String securityQ, String securityA,
                              String signature, List<TakeOutSlip> takeOutSlips, boolean isActive, LocalDate lastLogin) {
        // Validate inputs
        if (isValidEmailFormat(email) && isValidSecurityQA(securityQ, securityA)) {
            setUsername(username);
            setPassword(password);
            setEmail(email);
            setSecurityQ(securityQ);
            setSecurityA(securityA);
            setSignature(signature);
            setTakeOutSlips(takeOutSlips);
            setIsActive(isActive);
            setLastLogin(lastLogin);
            return true;
        } else {
            return false;
        }
    }


    // Getters and setters for all attributes

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPrivileges() {
        return this.privileges;
    }

    public boolean getPrivileges() {
        return this.privileges;
    }

    public void setPrivileges(boolean privileges) {
        this.privileges = privileges;
    }

    public String getSecurityQ() {
        return this.securityQ;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public String getSecurityA() {
        return this.securityA;
    }

    public void setSecurityA(String securityA) {
        this.securityA = securityA;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public List<TakeOutSlip> getTakeOutSlips() {
        return this.takeOutSlips;
    }

    public void setTakeOutSlips(List<TakeOutSlip> takeOutSlips) {
        this.takeOutSlips = takeOutSlips;
    }

    public boolean isIsActive() {
        return this.isActive;
    }

    public boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDate getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getFailedLoginAttempts() {
        return this.failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }
}
