package com.example.backend;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

/**
 * User Class
 * @author Eric Lim
 */
public class User {

    private static final UserDatabase DATABASE = new UserDatabase();
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private String username;
    private String password;
    private String email;
    private String securityQ; 
    private String securityA;
    private boolean admin;
    private LocalDate lastLogin;
    private List<TakeOutSlip> takeOutSlips;

    public User() {
        this.username = null;
        this.password = null;
        this.email = null;
        this.securityQ = null;
        this.securityA = null;
        this.takeOutSlips = new ArrayList<>();
        this.lastLogin = null;
        this.admin = false;
    }

    public User(String username, String password) {
        login(username, password);
    }
    
    public boolean createNewUser(String name, String password, String email, String securityQ, String securityA, boolean admin) {
        if (!isValidPasswordLength(password) || !isValidEmailFormat(email) || !isValidSecurityQA(securityQ, securityA)) {
            return false;
        }

        setUsername(name);
        setPassword(password);
        setEmail(email);
        setSecurityQ(securityQ);
        setSecurityA(securityA);
        setAdmin(admin);
        
        DATABASE.addUser(this);

        return true;
    }

    public boolean login(String username, String password) {
        if (DATABASE.verifyLogin(username, password)) {
            User temp = DATABASE.fetchUser(username);

            setUsername(temp.getUsername());
            setPassword(temp.getPassword());
            setEmail(temp.getEmail());
            setSecurityQ(temp.getSecurityQ());
            setSecurityA(temp.getSecurityA());
            setTakeOutSlips(temp.getTakeOutSlips());
            setLastLogin(LocalDate.now());
            setAdmin(temp.isAdmin());

            return true;
        }
        return false;
    }
    
    public boolean logout() {
        setLastLogin(LocalDate.now());
        DATABASE.updateUser(this);
        return true;
    }

    public boolean resetPassword(String newPassword) {
        if (!isValidPasswordLength(newPassword)) {
            setPassword(newPassword);
            return false;
        }
        return false;
    }

    public String toCsvString() {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(",");
        sb.append(password).append(",");
        sb.append(email).append(",");
        sb.append(securityQ).append(",");
        sb.append(securityA).append(",");
        sb.append(new String(serializeSlips(takeOutSlips))).append(",");
        sb.append(",");
        sb.append(admin);
        return sb.toString();
    }

    public static User parseCsv(String csv) {
        String[] parts = csv.split(",");
        User user = new User();
        user.setUsername(parts[0]);
        user.setPassword(parts[1]);
        user.setEmail(parts[2]);
        user.setSecurityQ(parts[3]);
        user.setSecurityA(parts[4]);
        user.setTakeOutSlips(deserializeSlips(parts[5].getBytes()));
        user.setAdmin(Boolean.parseBoolean(parts[parts.length - 1]));
        return user;
    }

    private byte[] serializeSlips(List<TakeOutSlip> slips) {
        // Serialize the list of TakeOutSlip objects
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(getTakeOutSlips());
            byte[] serializedSlips = baos.toByteArray();
            return serializedSlips;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static List<TakeOutSlip> deserializeSlips(byte[] serializedSlips) {
        try {
            // Deserialize the serialized data back into a List<TakeOutSlip>
            ByteArrayInputStream bais = new ByteArrayInputStream(serializedSlips);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (List<TakeOutSlip>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValidPasswordLength(String password) {
        return password.length() >= MIN_PASSWORD_LENGTH || !this.password.equals(password);
    }

    private boolean isValidEmailFormat(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidSecurityQA(String securityQ, String securityA) {
        // return securityQ != null && !securityQ.isEmpty() &&
        //        securityA != null && !securityA.isEmpty();
        return true;
    }

    // Getters and setters for all attributes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public String getSecurityA() {
        return securityA;
    }

    public void setSecurityA(String securityA) {
        this.securityA = securityA;
    }

    public List<TakeOutSlip> getTakeOutSlips() {
        return takeOutSlips;
    }

    public void setTakeOutSlips(List<TakeOutSlip> takeOutSlips) {
        this.takeOutSlips = takeOutSlips;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }
}
